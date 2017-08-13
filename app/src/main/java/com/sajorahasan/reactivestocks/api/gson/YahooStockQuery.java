package com.sajorahasan.reactivestocks.api.gson;

import java.util.Date;

/**
 * Created by Sajora on 06-08-2017.
 */

public class YahooStockQuery {
    private int count;
    private Date created;
    private YahooStockResults results;

    public int getCount() {
        return count;
    }

    public Date getCreated() {
        return created;
    }

    public YahooStockResults getResults() {
        return results;
    }
}
