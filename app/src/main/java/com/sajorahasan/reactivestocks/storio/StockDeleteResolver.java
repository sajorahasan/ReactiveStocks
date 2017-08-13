package com.sajorahasan.reactivestocks.storio;

import android.support.annotation.NonNull;

import com.pushtorefresh.storio.sqlite.operations.delete.DefaultDeleteResolver;
import com.pushtorefresh.storio.sqlite.queries.DeleteQuery;
import com.sajorahasan.reactivestocks.Stock;

/**
 * Created by Sajora on 13-08-2017.
 */

public class StockDeleteResolver extends DefaultDeleteResolver<Stock> {
    @NonNull
    @Override
    protected DeleteQuery mapToDeleteQuery(@NonNull Stock object) {
        return DeleteQuery.builder()
                .table(StockTable.TABLE)
                .where(StockTable.Columns.ID + " = ?")
                .whereArgs(object.getId())
                .build();
    }
}
