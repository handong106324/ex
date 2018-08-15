package com.dong.invest.model.ex.bigone;

import java.util.List;

public class BigOrderBook {
    private String market_id;
    private List<BigPrice> bids;
    private List<BigPrice> asks;

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public List<BigPrice> getBids() {
        return bids;
    }

    public void setBids(List<BigPrice> bids) {
        this.bids = bids;
    }

    public List<BigPrice> getAsks() {
        return asks;
    }

    public void setAsks(List<BigPrice> asks) {
        this.asks = asks;
    }

    @Override
    public String toString() {
        return "BigOrderBook{" +
                "market_id='" + market_id + '\'' +
                ", bids=" + bids +
                ", asks=" + asks +
                '}';
    }
}
