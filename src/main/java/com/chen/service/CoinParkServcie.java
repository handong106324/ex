package com.chen.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dong.invest.model.ex.bigone.*;
import com.dong.invest.model.pairs.SymbolPair;
import com.utils.ExchangeUrlUtils;
import com.utils.JwtToken;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import utils.HttpUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CoinParkServcie {
    public List<BigOneTicker> getTickers() {
        String tickersUrl = "/tickers";
        List<BigOneTicker> realList = new ArrayList<>();
        try {
            String result = (HttpUtil.doGet(ExchangeUrlUtils.CP_API_URL+tickersUrl,new HashMap<>()));
            JSONObject marketList = (JSONObject) JSON.parse(result);
            for (Object bigData : marketList.getJSONArray("data")) {
                BigOneTicker bigOneMarket = JSONObject.toJavaObject((JSONObject)bigData, BigOneTicker.class);
                realList.add(bigOneMarket);
            }
            System.out.println(realList.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return realList;
    }

    public BigOneTicker getTicker(SymbolPair symbolPair) {
        String tickersUrl = "/v1/mdata?cmd=ticker&pair="+symbolPair.getRealToken() + "-" + symbolPair.getBasicToken();
        String result = (HttpUtil.doGet(ExchangeUrlUtils.CP_API_URL+tickersUrl,new HashMap<>()));
        JSONObject marketList = (JSONObject) JSON.parse(result);
        BigOneTicker bigOneMarket = JSONObject.toJavaObject(marketList.getJSONObject("data"), BigOneTicker.class);
        return bigOneMarket;
    }

    public List<SymbolPair> getMarkets() {
        String tickersUrl = ExchangeUrlUtils.CP_API_URL + "/v1/mdata?cmd=pairList";
        String result = HttpUtil.doGet(tickersUrl);
        JSONObject jsonObject = JSONObject.parseObject(result);
        List<SymbolPair> realList = new ArrayList<>();
        JSONArray array = jsonObject.getJSONArray("data");
        for (Object o : array) {
            String pari = ((JSONObject)o).getString("name");
            String[] paris = StringUtils.split(pari, "-");
            SymbolPair symbolPair = new SymbolPair();
            symbolPair.setBasicToken(paris[1]);
            symbolPair.setRealToken(paris[0]);

            realList.add(symbolPair);
        }
        return realList;
    }

    public BigOrderBook getOrderBook(String marketId) {
        String url = ExchangeUrlUtils.CP_API_URL + "/markets/"+marketId+"/depth";
        String result = send(url);
        JSONObject object = JSONObject.parseObject(result).getJSONObject("data");
        return object.toJavaObject(BigOrderBook.class);
    }


    /**

     * @return
     * @throws Exception
     */
    public List<BigOneAsset>  getAccount() throws Exception {
        String url = ExchangeUrlUtils.CP_API_URL + "/viewer/accounts";


        return new ArrayList<>();
    }

    private static long getTrades(String marketId, long endTime, File file) throws IOException {

        return 0;
    }


    /**
     * market_id	market uuid	d2185614-50c3-4588-b146-b8afe7534da6	true
     side	order side	one of "ASK"/"BID"	true
     price	order price	string	true
     amount	order amount	string, must larger than 0	true

     */
    public boolean createOrder(String marketId,String side,String price,String amount) throws Exception {

        return true;
    }


    public String send(String url , Map<String,Object> params) {
        String result = (HttpUtil.doGet(url,params));
        return result;
    }

    public String send(String url) {
        String result = (HttpUtil.doGet(url,new HashMap<>()));
        return result;
    }

    public List<SymbolPair> getAllSymbolPairs() {
        return getMarkets();
    }

    public CurrentMarketInfo getTickers(SymbolPair symbolPair) {
        CurrentMarketInfo currentMarketInfo = new CurrentMarketInfo();
        BigOneTicker ticker = getTicker(symbolPair);
        currentMarketInfo.setAskAmount(ticker.getAsk().getAmount());
        currentMarketInfo.setAskPrice(ticker.getAsk().getPrice());
        currentMarketInfo.setBidAmount(ticker.getBid().getAmount());
        currentMarketInfo.setBidPrice(ticker.getBid().getPrice());
        return currentMarketInfo;
    }

    /**
     * buy : ask
     * @param
     * @param symbolPair
     * @return
     */
    public TradeResult trade(double price,double amount, SymbolPair symbolPair, boolean isBuy) {
        String side = "ASK";
        if (!isBuy) {
            side = "BID";
        }
        TradeResult tradeResult = new TradeResult();
        try {
            tradeResult.setSuccess(false);
            boolean isSuc = createOrder(symbolPair.getRealToken() + "-" + symbolPair.getBasicToken(),side, ""+price, ""+amount);
            tradeResult.setSuccess(isSuc);
        } catch (Exception e) {
            e.printStackTrace();
            tradeResult.setSuccess(false);
            tradeResult.setErrorCode("1");
            tradeResult.setErrMsg("" + e.getMessage());
        }
        return tradeResult;
    }

}
