package com.sajorahasan.reactivestocks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sajora on 06-08-2017.
 */

public class StocksDataAdapter extends RecyclerView.Adapter<StocksViewHolder> {

    private final List<Stock> stockList = new ArrayList<>();


    @Override
    public StocksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_item, parent, false);
        return new StocksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(StocksViewHolder holder, int position) {
        Stock stock = stockList.get(position);
        holder.setStockSymbol(stock.getStockSymbol());
        holder.setPrice(stock.getPrice());
        holder.setDate(stock.getDate());
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public void add(Stock newStock) {
        for (Stock stockUpdate : stockList) {
            if (stockUpdate.getStockSymbol().equals(newStock.getStockSymbol())) {
                if (stockUpdate.getPrice().equals(newStock.getPrice())) {
                    return;
                }
                break;
            }
        }
        this.stockList.add(0, newStock);
        notifyItemInserted(0);
    }
}
