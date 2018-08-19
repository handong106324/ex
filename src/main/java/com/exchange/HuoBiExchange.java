package com.exchange;

import com.ApiFactory;
import com.dong.invest.model.Exchange;
import com.dong.invest.model.ex.bigone.BigOneOrder;
import com.dong.invest.model.ex.bigone.BigOneTicker;
import com.dong.invest.model.pairs.SymbolPair;
import com.huobi.api.ApiClient;
import com.huobi.response.Symbol;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuoBiExchange extends Exchange {
    private List<SymbolPair> symbolPairs;
    ApiClient apiClient = new ApiClient(ApiFactory.getKey("HB").getApiKey(),
            ApiFactory.getKey("HB").getSecret());
    @Override
    public List<SymbolPair> getAllSymbolPairs() {
        List<Symbol> symbols = apiClient.getSymbols();
        if (null != symbolPairs) {
            return symbolPairs;
        }
        symbolPairs = new ArrayList<>();
        for (Symbol symbol : symbols) {
            SymbolPair symbolPair = new SymbolPair();
            symbolPair.setBasicToken(symbol.quoteCurrency);
            symbolPair.setRealToken(symbol.baseCurrency);
            symbolPair.setMarketId(symbol.symbol);
            this.symbols.put(symbol.symbol,symbolPair);
            symbolPairs.add(symbolPair);
        }
        return symbolPairs;
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

    @Override
    public String getName() {
        return "HB";
    }

    @Override
    public BigOneTicker getTicker(SymbolPair symbo) {
       return apiClient.ticker(symbo);

    }


}
