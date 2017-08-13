package com.sajorahasan.reactivestocks.storio;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.operations.put.DefaultPutResolver;
import com.pushtorefresh.storio.sqlite.queries.InsertQuery;
import com.pushtorefresh.storio.sqlite.queries.UpdateQuery;
import com.sajorahasan.reactivestocks.Stock;

/**
 * Created by Sajora on 13-08-2017.
 */

public class StockPutResolver extends DefaultPutResolver<Stock> {
    @NonNull
    @Override
    protected InsertQuery mapToInsertQuery(@NonNull Stock object) {
        return InsertQuery.builder()
                .table(StockTable.TABLE)
                .build();
    }

    @NonNull
    @Override
    protected UpdateQuery mapToUpdateQuery(@NonNull Stock object) {
        return UpdateQuery.builder()
                .table(StockTable.TABLE)
                .where(StockTable.Columns.ID + " = ?")
                .whereArgs(object.getId())
                .build();
    }

    @NonNull
    @Override
    protected ContentValues mapToContentValues(@NonNull Stock entity) {
        final ContentValues contentValues = new ContentValues();

        contentValues.put(StockTable.Columns.ID, entity.getId());
        contentValues.put(StockTable.Columns.STOCK_SYMBOL, entity.getStockSymbol());
        contentValues.put(StockTable.Columns.PRICE, getPrice(entity));
        contentValues.put(StockTable.Columns.DATE, getDate(entity));

        return contentValues;
    }

    private long getDate(@NonNull Stock entity) {
        return entity.getDate().getTime();
    }

    private long getPrice(@NonNull Stock entity) {
        return entity.getPrice().scaleByPowerOfTen(-4).longValue();
    }

}
