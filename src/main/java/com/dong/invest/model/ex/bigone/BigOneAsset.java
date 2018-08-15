package com.dong.invest.model.ex.bigone;

public class BigOneAsset {
    private Double locked_balance;
    private Double balance;
    private String asset_uuid;
    private String asset_id;

    public Double getLocked_balance() {
        return locked_balance;
    }

    public void setLocked_balance(Double locked_balance) {
        this.locked_balance = locked_balance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getAsset_uuid() {
        return asset_uuid;
    }

    public void setAsset_uuid(String asset_uuid) {
        this.asset_uuid = asset_uuid;
    }

    public String getAsset_id() {
        return asset_id;
    }

    public void setAsset_id(String asset_id) {
        this.asset_id = asset_id;
    }

    @Override
    public String toString() {
        return "BigOneAsset{" +
                "locked_balance=" + locked_balance +
                ", balance=" + balance +
                ", asset_uuid='" + asset_uuid + '\'' +
                ", asset_id='" + asset_id + '\'' +
                '}';
    }
}
