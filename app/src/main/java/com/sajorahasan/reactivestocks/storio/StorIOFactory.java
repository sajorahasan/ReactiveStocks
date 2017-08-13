package com.sajorahasan.reactivestocks.storio;

import android.content.Context;

import com.pushtorefresh.storio.sqlite.SQLiteTypeMapping;
import com.pushtorefresh.storio.sqlite.StorIOSQLite;
import com.pushtorefresh.storio.sqlite.impl.DefaultStorIOSQLite;
import com.sajorahasan.reactivestocks.Stock;

/**
 * Created by Sajora on 13-08-2017.
 */

public class StorIOFactory {
    private static StorIOSQLite INSTANCE;

    public synchronized static StorIOSQLite get(Context context) {
        if (INSTANCE != null) {
            return INSTANCE;
        }

        INSTANCE = DefaultStorIOSQLite.builder()
                .sqliteOpenHelper(new StorIODbHelper(context))
                .addTypeMapping(Stock.class, SQLiteTypeMapping.<Stock>builder()
                        .putResolver(new StockPutResolver())
                        .getResolver(new StockGetResolver())
                        .deleteResolver(new StockDeleteResolver())
                        .build())
                .build();

        return INSTANCE;
    }
}
