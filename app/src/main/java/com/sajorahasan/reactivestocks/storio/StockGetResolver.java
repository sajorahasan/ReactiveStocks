package com.sajorahasan.reactivestocks.storio;

import android.database.Cursor;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.operations.get.DefaultGetResolver;
import com.sajorahasan.reactivestocks.Stock;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Sajora on 13-08-2017.
 */

public class StockGetResolver extends DefaultGetResolver<Stock> {
    @NonNull
    @Override
    public Stock mapFromCursor(@NonNull Cursor cursor) {

        final int id = cursor.getInt(cursor.getColumnIndexOrThrow(StockTable.Columns.ID));
        final long dateLong = cursor.getLong(cursor.getColumnIndexOrThrow(StockTable.Columns.DATE));
        final long priceLong = cursor.getLong(cursor.getColumnIndexOrThrow(StockTable.Columns.PRICE));
        final String stockSymbol = cursor.getString(cursor.getColumnIndexOrThrow(StockTable.Columns.STOCK_SYMBOL));

        Date date = getDate(dateLong);
        BigDecimal price = getPrice(priceLong);

        final Stock stock = new Stock(stockSymbol, price, date);
        stock.setId(id);
        return stock;
    }

    private Date getDate(long dateLong) {
        return new Date(dateLong);
    }

    private BigDecimal getPrice(long priceLong) {
        return new BigDecimal(priceLong).scaleByPowerOfTen(-4);
    }

}
