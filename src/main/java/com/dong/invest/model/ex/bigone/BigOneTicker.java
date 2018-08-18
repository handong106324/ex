package com.dong.invest.model.ex.bigone;

import com.dong.invest.model.Exchange;

public class BigOneTicker {

    private Double volume;
    private Double open;
    private String market_uuid;
    private String market_id;
    private Double low;
    private Double high;
    private String daily_change_perc;
    private Double daily_change;
    private Double close;
    private BigPrice bid;
    private BigPrice ask;
    private Exchange exchange;

    public BigOneTicker(){}

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public String getMarket_uuid() {
        return market_uuid;
    }

    public void setMarket_uuid(String market_uuid) {
        this.market_uuid = market_uuid;
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public String getDaily_change_perc() {
        return daily_change_perc;
    }

    public void setDaily_change_perc(String daily_change_perc) {
        this.daily_change_perc = daily_change_perc;
    }

    public Double getDaily_change() {
        return daily_change;
    }

    public void setDaily_change(Double daily_change) {
        this.daily_change = daily_change;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public BigPrice getBid() {
        return bid;
    }

    public void setBid(BigPrice bid) {
        this.bid = bid;
    }

    public BigPrice getAsk() {
        return ask;
    }

    public void setAsk(BigPrice ask) {
        this.ask = ask;
    }

    @Override
    public String toString() {
        return "BigOneMarket{" +
                "volume=" + volume +
                ", open=" + open +
                ", market_uuid='" + market_uuid + '\'' +
                ", market_id='" + market_id + '\'' +
                ", low=" + low +
                ", high=" + high +
                ", daily_change_perc='" + daily_change_perc + '\'' +
                ", daily_change=" + daily_change +
                ", close=" + close +
                ", bid=" + bid +
                ", ask=" + ask +
                '}';
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }
}

