package com.sajorahasan.reactivestocks.storio;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

/**
 * Created by Sajora on 13-08-2017.
 */

class StorIODbHelper extends SQLiteOpenHelper {
    public StorIODbHelper(@NonNull Context context) {
        super(context, "reactiveStocks.db", null, 1);
    }

    @Override
    public void onCreate(@NonNull SQLiteDatabase db) {
        db.execSQL(StockTable.createTableQuery());
    }

    @Override
    public void onUpgrade(@NonNull SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
