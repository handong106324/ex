package com.dong.invest.model.exchange;

import com.chen.service.BigOneServcie;
import com.chen.service.CoinParkServcie;
import com.dong.invest.model.Exchange;
import com.dong.invest.model.pairs.SymbolPair;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinParkExchange extends Exchange {
    @Autowired
    private CoinParkServcie bigOneServcie;
    @Override
    public List<SymbolPair> getAllSymbolPairs() {
        return bigOneServcie.getAllSymbolPairs();
    }

    @Override
    public CurrentMarketInfo getMarketTradeInfo(SymbolPair symbolPair) {
        return bigOneServcie.getTickers(symbolPair);
    }

    @Override
    public TradeResult buy(double buyPrice,double buyAmount, SymbolPair symbolPair) {
        return bigOneServcie.trade(buyPrice,buyAmount, symbolPair,true);
    }

    @Override
    public TradeResult sell(double sellPrice,double sellAmount, SymbolPair symbolPair) {
        return bigOneServcie.trade(sellPrice,sellAmount, symbolPair,false);
    }

    @Override
    public String getName() {
        return "CP";
    }
}
