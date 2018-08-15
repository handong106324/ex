package com.dong.invest.model.ex.bigone;

public class BigPrice {
    private Double price;
    private Double amount;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BigPrice{" +
                "price=" + price +
                ", amount=" + amount +
                '}';
    }
}
