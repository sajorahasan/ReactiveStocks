package com.sajorahasan.reactivestocks;

import com.sajorahasan.reactivestocks.api.gson.YahooStockQuote;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Sajora on 06-08-2017.
 */

public class Stock implements Serializable {
    private Integer id;
    private final String stockSymbol;
    private final BigDecimal price;
    private final Date date;

    public Stock(String stockSymbol, BigDecimal price, Date date) {
        this.stockSymbol = stockSymbol;
        this.price = price;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getDate() {
        return date;
    }

    public static Stock create(YahooStockQuote r) {
        return new Stock(r.getSymbol(), r.getLastTradePriceOnly(), new Date());
    }
}
