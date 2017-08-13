package com.sajorahasan.reactivestocks.storio;

/**
 * Created by Sajora on 13-08-2017.
 */
public class StockTable {
    public static final String TABLE = "stock_updates";

    public static class Columns {
        static final String ID = "_id";
        static final String STOCK_SYMBOL = "stock_symbol";
        static final String PRICE = "price";
        static final String DATE = "date";
    }

    private StockTable() {
    }

    public static String createTableQuery() {
        return "CREATE TABLE " + TABLE + "("
                + Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Columns.STOCK_SYMBOL + " TEXT NOT NULL, "
                + Columns.DATE + " LONG NOT NULL, "
                + Columns.PRICE + " LONG NOT NULL"
                + ");";
    }
}
