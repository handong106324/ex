package com.dong.invest.model.pairs;

import java.text.DecimalFormat;

public class SymbolPairTicker {
    /**
     * 交易对
     */
    private String basicToken;

    private String realToken;

    private double price;

    public String getBasicToken() {
        return basicToken;
    }

    public void setBasicToken(String basicToken) {
        this.basicToken = basicToken;
    }

    public String getRealToken() {
        return realToken;
    }

    public void setRealToken(String realToken) {
        this.realToken = realToken;
    }

    public double getPrice() {
        return price;
    }

    public String getPriceFormat() {
        DecimalFormat decimalFormat = new DecimalFormat("##.###########");
        return decimalFormat.format(price);
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
