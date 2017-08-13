package com.sajorahasan.reactivestocks.api.gson;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by Sajora on 06-08-2017.
 */

public class YahooStockQuote {
    private String symbol;

    @SerializedName("Name")
    private String name;

    @SerializedName("LastTradePriceOnly")
    private BigDecimal lastTradePriceOnly;

    @SerializedName("DaysLow")
    private BigDecimal daysLow;

    @SerializedName("DaysHigh")
    private BigDecimal daysHigh;

    @SerializedName("Volume")
    private String volume;

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getLastTradePriceOnly() {
        return lastTradePriceOnly;
    }

    public BigDecimal getDaysLow() {
        return daysLow;
    }

    public BigDecimal getDaysHigh() {
        return daysHigh;
    }

    public String getVolume() {
        return volume;
    }
}
