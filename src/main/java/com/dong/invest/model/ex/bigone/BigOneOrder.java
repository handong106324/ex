package com.dong.invest.model.ex.bigone;

/**
 * @author handong
 * @date 2018-08-15 15:42
 */
public class BigOneOrder {
    private String updated_at;

    private String state;

    private String side;

    private double price;

    private String market_uuid;

    private String market_id;

    private String inserted_at;

    private String id;

    private String filled_amount;

    private String avg_deal_price;

    private Double amount;

    @Override
    public String toString() {
        return "BigOneOrder{" +
                "updated_at='" + updated_at + '\'' +
                ", state='" + state + '\'' +
                ", side='" + side + '\'' +
                ", price=" + price +
                ", market_uuid='" + market_uuid + '\'' +
                ", market_id='" + market_id + '\'' +
                ", inserted_at='" + inserted_at + '\'' +
                ", id='" + id + '\'' +
                ", filled_amount='" + filled_amount + '\'' +
                ", avg_deal_price='" + avg_deal_price + '\'' +
                ", amount=" + amount +
                '}';
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
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

    public String getInserted_at() {
        return inserted_at;
    }

    public void setInserted_at(String inserted_at) {
        this.inserted_at = inserted_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFilled_amount() {
        return filled_amount;
    }

    public void setFilled_amount(String filled_amount) {
        this.filled_amount = filled_amount;
    }

    public String getAvg_deal_price() {
        return avg_deal_price;
    }

    public void setAvg_deal_price(String avg_deal_price) {
        this.avg_deal_price = avg_deal_price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
