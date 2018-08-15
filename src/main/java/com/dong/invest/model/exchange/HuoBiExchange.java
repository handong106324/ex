package com.dong.invest.model.exchange;

import com.dong.invest.model.Exchange;
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

}
