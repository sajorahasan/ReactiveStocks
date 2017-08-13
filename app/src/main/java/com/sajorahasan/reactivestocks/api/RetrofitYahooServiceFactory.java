package com.sajorahasan.reactivestocks.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sajora on 06-08-2017.
 */

public class RetrofitYahooServiceFactory {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://query.yahooapis.com/v1/public/")
            .build();

    public YahooService create() {
        return retrofit.create(YahooService.class);
    }

}
