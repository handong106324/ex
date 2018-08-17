package com.exchange;

import com.bigone.BigOneClient;
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
        return BigOneClient.getAllSymbolPairs();
    }

    @Override
    public CurrentMarketInfo getMarketTradeInfo(SymbolPair symbolPair) {
        return BigOneClient.getTickers(symbolPair);
    }

    @Override
    public TradeResult buy(double buyPrice,double buyAmount, SymbolPair symbolPair) {
        return BigOneClient.trade(buyPrice,buyAmount, symbolPair,true);
    }

    @Override
    public TradeResult sell(double sellPrice,double sellAmount, SymbolPair symbolPair) {
        return BigOneClient.trade(sellPrice,sellAmount, symbolPair,false);
    }

    @Override
    public BigOneOrder createOrder(double sellPrice, double sellAmount, SymbolPair symbolPair, boolean isBuy) {
        return BigOneClient.createOrder(symbolPair.getMarketId(),isBuy?"BID":"ASK",sellPrice+"",""+sellAmount);
    }

    @Override
    public TradeResult tradeAnsyc(double sellPrice,double sellAmount, SymbolPair symbolPair) {
        return BigOneClient.tradeAsync(sellPrice,sellAmount, symbolPair,false, 600000);
    }


    @Override
    public String getName() {
        return "B1";
    }

    @Override
    public BigOneOrder cancelOrder(String id) throws Exception {
        return BigOneClient.cancelOrder(id);
    }
}
