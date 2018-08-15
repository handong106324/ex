package com.dong.invest.model.assets;

import com.dong.invest.model.pairs.SymbolPair;

public class BinanceAssets {
    private SymbolPair symbolPair;

    private double amount;

    public SymbolPair getSymbolPair() {
        return symbolPair;
    }

    public void setSymbolPair(SymbolPair symbolPair) {
        this.symbolPair = symbolPair;
    }

    public double getTotalToBasic() {
        return amount * symbolPair.getPrice();
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
