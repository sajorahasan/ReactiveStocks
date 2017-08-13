package com.sajorahasan.reactivestocks;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.pushtorefresh.storio.sqlite.queries.Query;
import com.sajorahasan.reactivestocks.api.RetrofitYahooServiceFactory;
import com.sajorahasan.reactivestocks.api.YahooService;
import com.sajorahasan.reactivestocks.storio.StockTable;
import com.sajorahasan.reactivestocks.storio.StorIOFactory;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static hu.akarnokd.rxjava.interop.RxJavaInterop.toV2Observable;

public class MainActivity extends RxAppCompatActivity {
    private static final String TAG = "MainActivity";

    @BindView(R.id.hello_world_txv)
    TextView helloWorld;

    @BindView(R.id.no_data_available)
    TextView noDataAvailableView;

    @BindView(R.id.stocks_recycler_view)
    RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private StocksDataAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxJavaPlugins.setErrorHandler(ErrorHandler.get());

        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new StocksDataAdapter();
        recyclerView.setAdapter(adapter);

        Observable.just("Please use this app responsibly!")
                .subscribe(s -> helloWorld.setText(s));

        YahooService yahooService = new RetrofitYahooServiceFactory().create();

        String query = "select * from yahoo.finance.quote where symbol in ('AAPL','GOOG','MSFT')";
        String env = "store://datatables.org/alltableswithkeys";

        Observable.interval(0, 5, TimeUnit.SECONDS)
                .compose(bindToLifecycle())
                .flatMap(i-> yahooService.yqlQuery(query,env).toObservable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(error -> {
                    Toast.makeText(this, "We couldn't reach internet - falling back to local data", Toast.LENGTH_SHORT).show();
                })
                .observeOn(Schedulers.io())
                .map(r -> r.getQuery().getResults().getQuote())
                .flatMap(Observable::fromIterable)
                .map(Stock::create)
                .doOnNext(this::saveStockData)
                .onExceptionResumeNext(
                        toV2Observable(StorIOFactory.get(this)
                                .get()
                                .listOfObjects(Stock.class)
                                .withQuery(Query.builder()
                                        .table(StockTable.TABLE)
                                        .orderBy("date DESC")
                                        .limit(50)
                                        .build())
                                .prepare()
                                .asRxObservable())
                                .take(1)
                                .flatMap(Observable::fromIterable)
                )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stock -> {
                    Log.d(TAG, "onCreate: update " + stock.getStockSymbol());
                    noDataAvailableView.setVisibility(View.GONE);
                    adapter.add(stock);
                }, error -> {
                    if (adapter.getItemCount() == 0) {
                        noDataAvailableView.setVisibility(View.VISIBLE);
                    }
                });


//        Observable.interval(0, 5, TimeUnit.SECONDS)
//                .flatMap(i -> yahooService.yqlQuery(query, env).toObservable())
//                .subscribeOn(Schedulers.io())
//                .map(r -> r.getQuery().getResults().getQuote())
//                .flatMap(Observable::fromIterable)
//                .map(Stock::create)
//                .doOnNext(this::saveStockData)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(stock -> {
//                    Log.d(TAG, "onCreate: update " + stock.getStockSymbol());
//                    noDataAvailableView.setVisibility(View.GONE);
//                    adapter.add(stock);
//                }, error -> {
//                    if (adapter.getItemCount() == 0) {
//                        noDataAvailableView.setVisibility(View.VISIBLE);
//                    }
//                });

    }

    private void saveStockData(Stock stock) {
        Log.d(TAG, "saveStockData: " + stock.getStockSymbol());
        StorIOFactory.get(this)
                .put()
                .object(stock)
                .prepare()
                .asRxSingle()
                .subscribe();
    }
}
