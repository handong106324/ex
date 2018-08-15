package com.dong.invest.model;

import com.dong.invest.model.pairs.SymbolPair;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;

import java.util.List;

/**
 * 交易所
 */
public abstract class Exchange {
    private String name;

    /**
     * 获取所哟的交易对
     * @return
     */
    public abstract List<SymbolPair> getAllSymbolPairs();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract CurrentMarketInfo getMarketTradeInfo(SymbolPair symbolPair);

    public abstract TradeResult buy(double buyPrice,double buyAmount, SymbolPair symbolPair);

    public abstract TradeResult sell(double sellPrice,double sellAmount, SymbolPair symbolPair);
}
