package com.exchange;

import com.chen.service.CoinParkServcie;
import com.dong.invest.model.Exchange;
import com.dong.invest.model.ex.bigone.BigOneOrder;
import com.dong.invest.model.ex.bigone.BigOneTicker;
import com.dong.invest.model.pairs.SymbolPair;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CoinParkExchange extends Exchange {
    @Override
    public List<SymbolPair> getAllSymbolPairs() {
        return CoinParkServcie.getAllSymbolPairs();
    }

    @Override
    public CurrentMarketInfo getMarketTradeInfo(SymbolPair symbolPair) {
        return CoinParkServcie.getTickers(symbolPair);
    }

    @Override
    public TradeResult buy(double buyPrice,double buyAmount, SymbolPair symbolPair) {
        return CoinParkServcie.trade(buyPrice,buyAmount, symbolPair,true);
    }

    @Override
    public TradeResult sell(double sellPrice,double sellAmount, SymbolPair symbolPair) {
        return CoinParkServcie.trade(sellPrice,sellAmount, symbolPair,false);
    }

    @Override
    public BigOneOrder createOrder(double sellPrice, double sellAmount, SymbolPair symbolPair, boolean isBuy) {
        return CoinParkServcie.createOrder(symbolPair.getMarketId(),isBuy?"BID":"ASK",sellPrice+"",""+sellAmount);
    }

    @Override
    public TradeResult tradeAnsyc(double sellPrice,double sellAmount, SymbolPair symbolPair) {
        return CoinParkServcie.tradeAsync(sellPrice,sellAmount, symbolPair,false, 600000);
    }

    @Override
    public BigOneOrder cancelOrder(String id) throws Exception {
        return CoinParkServcie.cancelOrder(id);
    }

    @Override
    public BigOneTicker getTicker(SymbolPair symbo) {
        return CoinParkServcie.getTicker(symbo);
    }

    @Override
    public String getName() {
        return "CP";
    }
}
