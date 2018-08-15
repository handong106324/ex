package com.chen.service;

import java.util.Date;

/**
 * @author handong
 * @date 2018-08-10 17:52
 */
public class TradeHistoryInfo {
    private String taker_side;
    private double price;
    private Double amount;

    private String inserted_at;

    public String getTaker_side() {
        return taker_side;
    }

    public void setTaker_side(String taker_side) {
        this.taker_side = taker_side;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getInserted_at() {
        return inserted_at;
    }

    public void setInserted_at(String inserted_at) {
        this.inserted_at = inserted_at;
    }
}
