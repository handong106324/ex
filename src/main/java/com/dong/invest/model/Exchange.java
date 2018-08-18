package com.dong.invest.model;

import com.dong.invest.model.ex.bigone.BigOneOrder;
import com.dong.invest.model.ex.bigone.BigOneTicker;
import com.dong.invest.model.pairs.SymbolPair;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 交易所
 */
public abstract class Exchange {
    private String name;

    public Map<String,SymbolPair> symbols = new HashMap<>();


    /**
     * 获取所哟的交易对
     * @return
     */
    public abstract List<SymbolPair> getAllSymbolPairs();

    public abstract BigOneOrder createOrder(double sellPrice, double sellAmount, SymbolPair symbolPair, boolean isBuy);

    public abstract TradeResult tradeAnsyc(double sellPrice, double sellAmount, SymbolPair symbolPair);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract CurrentMarketInfo getMarketTradeInfo(SymbolPair symbolPair);

    public abstract TradeResult buy(double buyPrice,double buyAmount, SymbolPair symbolPair);

    public abstract TradeResult sell(double sellPrice,double sellAmount, SymbolPair symbolPair);

    public abstract BigOneOrder cancelOrder(String id) throws Exception;


    public Map<String, SymbolPair> symbols() {
        if (symbols.isEmpty()) {
            getAllSymbolPairs();
        }
        return symbols;
    }

    public abstract BigOneTicker getTicker(SymbolPair symbo);
}
