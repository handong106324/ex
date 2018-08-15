package com.dong.invest.model.ex.bigone;

import java.util.Date;

public class BigOneOrderNode {

    private Date updated_at;
    private String state;
    private String side;
    private String price;
    private String market_uuid;
    private String market_id;
    private Date inserted_at;
    private Long id;
    private double filled_amount;
    private Double avg_deal_price;
    private Double amount;

    @Override
    public String toString() {
        return "BigOneOrderNode{" +
                "updated_at=" + updated_at +
                ", state='" + state + '\'' +
                ", side='" + side + '\'' +
                ", price='" + price + '\'' +
                ", market_uuid='" + market_uuid + '\'' +
                ", market_id='" + market_id + '\'' +
                ", inserted_at=" + inserted_at +
                ", id=" + id +
                ", filled_amount=" + filled_amount +
                ", avg_deal_price=" + avg_deal_price +
                ", amount=" + amount +
                '}';
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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

    public Date getInserted_at() {
        return inserted_at;
    }

    public void setInserted_at(Date inserted_at) {
        this.inserted_at = inserted_at;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFilled_amount() {
        return filled_amount;
    }

    public void setFilled_amount(double filled_amount) {
        this.filled_amount = filled_amount;
    }

    public Double getAvg_deal_price() {
        return avg_deal_price;
    }

    public void setAvg_deal_price(Double avg_deal_price) {
        this.avg_deal_price = avg_deal_price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
