package com.exchange;

import com.ApiFactory;
import com.ApiKey;
import com.dong.invest.model.Exchange;
import com.dong.invest.model.ex.bigone.BigOneOrder;
import com.dong.invest.model.ex.bigone.BigOneTicker;
import com.dong.invest.model.pairs.SymbolPair;
import com.huobi.api.ApiClient;
import com.huobi.response.Symbol;
import com.okcoin.rest.stock.IStockRestApi;
import com.okcoin.rest.stock.impl.StockRestApi;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;
import org.apache.http.HttpException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OKExchange extends Exchange {
    ApiKey apiKey = ApiFactory.getKey("OK");

    String url_prex = "https://www.okex.com/";  //注意：请求URL 国际站https://www.okcoin.com ; 国内站https://www.okcoin.cn

    /**
     * get请求无需发送身份认证,通常用于获取行情，市场深度等公共信息
     *
     */
    IStockRestApi stockGet = new StockRestApi(url_prex);

    /**
     * post请求需发送身份认证，获取用户个人相关信息时，需要指定api_key,与secret_key并与参数进行签名，
     * 此处对构造方法传入api_key与secret_key,在请求用户相关方法时则无需再传入，
     * 发送post请求之前，程序会做自动加密，生成签名。
     *
     */
    IStockRestApi stockPost = new StockRestApi(url_prex, apiKey.getApiKey(), apiKey.getSecret());

    private List<SymbolPair> symbolPairs;
    ApiClient apiClient = new ApiClient(ApiFactory.getKey("OK").getApiKey(),
            ApiFactory.getKey("OK").getSecret());
    @Override
    public List<SymbolPair> getAllSymbolPairs()  {
        try {
            String res =  stockGet.tickers();
            System.out.println(res);
            List<Symbol> symbols = new ArrayList<>();
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
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
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
        try {
            String res =  stockGet.ticker(symbo.getMarketId());
            System.out.println(res);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


}
