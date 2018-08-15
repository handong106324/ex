package com.dong.invest.model.exchange;

import com.chen.service.BigOneServcie;
import com.dong.invest.model.Exchange;
import com.dong.invest.model.ex.bigone.BigOneOrder;
import com.dong.invest.model.pairs.SymbolPair;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BigOneExchange extends Exchange {

    @Override
    public List<SymbolPair> getAllSymbolPairs() {
        return BigOneServcie.getAllSymbolPairs();
    }

    @Override
    public CurrentMarketInfo getMarketTradeInfo(SymbolPair symbolPair) {
        return BigOneServcie.getTickers(symbolPair);
    }

    @Override
    public TradeResult buy(double buyPrice,double buyAmount, SymbolPair symbolPair) {
        return BigOneServcie.trade(buyPrice,buyAmount, symbolPair,true);
    }

    @Override
    public TradeResult sell(double sellPrice,double sellAmount, SymbolPair symbolPair) {
        return BigOneServcie.trade(sellPrice,sellAmount, symbolPair,false);
    }

    public BigOneOrder createOrder(double sellPrice, double sellAmount, SymbolPair symbolPair, boolean isBuy) {
        return BigOneServcie.createOrder(symbolPair.getMarketId(),isBuy?"BID":"ASK",sellPrice+"",""+sellAmount);
    }

    public TradeResult tradeAnsyc(double sellPrice,double sellAmount, SymbolPair symbolPair) {
        return BigOneServcie.tradeAsync(sellPrice,sellAmount, symbolPair,false, 30000);
    }


    @Override
    public String getName() {
        return "B1";
    }

    public BigOneOrder cancelOrder(String id) throws Exception {
        return BigOneServcie.cancelOrder(id);
    }
}
