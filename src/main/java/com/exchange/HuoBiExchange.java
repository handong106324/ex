package com.exchange;

import com.dong.invest.model.Exchange;
import com.dong.invest.model.ex.bigone.BigOneOrder;
import com.dong.invest.model.pairs.SymbolPair;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;

import java.util.List;

public class HuoBiExchange extends Exchange {
    @Override
    public List<SymbolPair> getAllSymbolPairs() {
        return null;
    }

    @Override
    public BigOneOrder createOrder(double sellPrice, double sellAmount, SymbolPair symbolPair, boolean isBuy) {
        return null;
    }

    @Override
    public TradeResult tradeAnsyc(double sellPrice, double sellAmount, SymbolPair symbolPair) {
        return null;
    }


    @Override
    public CurrentMarketInfo getMarketTradeInfo(SymbolPair symbolPair) {
        return null;
    }

    @Override
    public TradeResult buy(double buyPrice, double buyAmount, SymbolPair symbolPair) {
        return null;
    }

    @Override
    public TradeResult sell(double sellPrice, double sellAmount, SymbolPair symbolPair) {
        return null;
    }

    @Override
    public BigOneOrder cancelOrder(String id) throws Exception {
        return null;
    }

}
