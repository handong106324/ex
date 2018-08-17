package com.chen.service;

import com.ApiFactory;
import com.ApiKey;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.binance.util.CryptUtil;
import com.dong.invest.model.ex.bigone.*;
import com.dong.invest.model.pairs.SymbolPair;
import com.okcoin.rest.MD5Util;
import com.utils.ExchangeUrlUtils;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import utils.HttpUtil;

import java.util.*;

@Service
public class CoinParkServcie {


    public static BigOneTicker getTicker(SymbolPair symbolPair) {
        String tickersUrl = "/v1/mdata?cmd=ticker&pair="+symbolPair.getRealToken() + "-" + symbolPair.getBasicToken();
        String result = (HttpUtil.doGet(ExchangeUrlUtils.CP_API_URL+tickersUrl,new HashMap<>()));
        JSONObject marketList = (JSONObject) JSON.parse(result);
        BigOneTicker bigOneMarket = JSONObject.toJavaObject(marketList.getJSONObject("data"), BigOneTicker.class);
        return bigOneMarket;
    }

    public static List<SymbolPair> getMarkets() {
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


    /**
     PATH: apipath
     METHOD: POST
     PARAMS:
     {
     "cmds": "[{\"cmd\":\"user/userInfo\",\"body\":{}}]",
     "apikey":"52135958969bedca0809ac10b9caba758022b0a6",
     "sign":"6a21e39e3f68b6fc2227c4074c7e6a6c"
     }

     var sign = CryptoJS.HmacMD5(cmds, secret).toString();//6a21e39e3f68b6fc2227c4074c7e6a6c

     * @return
     * @throws Exception
     */
    public static List<BigOneAsset>  getAccount() throws Exception {
        String url = ExchangeUrlUtils.CP_API_URL +"/v1/transfer";
        ApiKey apiKey = ApiFactory.getKey("CP");
//        Map<String,String> param = new HashMap<>();
        Map<String,String> cmdObj = new HashMap<>();
        cmdObj.put("cmd","transfer/assets");
        cmdObj.put("body","{}");

//        JSONArray cmds = new JSONArray();
//        cmds.add(cmdObj);
//        param.put("cmds",cmds.toJSONString());
        cmdObj.put("apiKey", apiKey.getApiKey());
        String sign = MD5Util.buildMysignV1(cmdObj, apiKey.getSecret());
//        param.put("sign", HMAC.encryptHMAC(cmds.toJSONString(),key));

        cmdObj.put("sign", sign);
        String res = HttpUtil.doPost(url, cmdObj);
        System.out.println("res:"+res);
        return new ArrayList<>();
    }




    public static BigOneOrder createOrder(String marketId, String side, String price, String amount) {
        try {
            String url = ExchangeUrlUtils.B1_API_URL + "/viewer/orders";
            JSONObject param = new JSONObject();
            param.put("market_id", marketId);
            param.put("side",side);
            param.put("price",price);
            param.put("amount", amount);
            String result = "";//HttpUtil.doPostWithToken(url, param ,JwtToken.createToken(getServerTime(),ApiFactory.getKey("B1")));
            JSONObject res = JSONObject.parseObject(result);
            return res.getJSONObject("data").toJavaObject(BigOneOrder.class);
        } catch (Exception e) {
            return null;
        }
    }

    public String send(String url , Map<String,Object> params) {
        String result = (HttpUtil.doGet(url,params));
        return result;
    }

    public String send(String url) {
        String result = (HttpUtil.doGet(url,new HashMap<>()));
        return result;
    }

    public static List<SymbolPair> getAllSymbolPairs() {
        return getMarkets();
    }

    public static CurrentMarketInfo getTickers(SymbolPair symbolPair) {
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
    public static TradeResult trade(double price, double amount, SymbolPair symbolPair, boolean isBuy) {
        String side = "ASK";
        if (!isBuy) {
            side = "BID";
        }
        TradeResult tradeResult = new TradeResult();
        try {
            tradeResult.setSuccess(false);
            BigOneOrder order = createOrder(symbolPair.getRealToken() + "-" + symbolPair.getBasicToken(),side, ""+price, ""+amount);
            System.out.println(order.getId() +"成功" + order.getState());
            if (order != null && StringUtils.isNotBlank(order.getId())) {
                tradeResult.setSuccess(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            tradeResult.setSuccess(false);
            tradeResult.setErrorCode("1");
            tradeResult.setErrMsg("" + e.getMessage());
        }
        return tradeResult;
    }


    public static TradeResult tradeAsync(double price, double amount, SymbolPair symbolPair, boolean isBuy,long timeOut) {
        String side = "ASK";

        TradeResult tradeResult = new TradeResult();

        return tradeResult;
    }

    public static BigOneOrder getOrder(String orderId) throws Exception {
        String url = ExchangeUrlUtils.B1_API_URL + "/viewer/orders/" +orderId;
        String result = "";//HttpUtil.doGet(url, new HashMap<>() ,JwtToken.createToken(getServerTime(), ApiFactory.getKey("B1")));
        JSONObject res = JSONObject.parseObject(result);
        return res.getJSONObject("data").toJavaObject(BigOneOrder.class);
    }

    public static BigOneOrder cancelOrder(String orderId) throws Exception {
        String url = ExchangeUrlUtils.B1_API_URL + "/viewer/orders/" +orderId +"/cancel";
        String result = "";// HttpUtil.doPostWithToken(url, new HashMap<>() ,JwtToken.createToken(getServerTime(),ApiFactory.getKey("B1")));
        JSONObject res = JSONObject.parseObject(result);
        return res.getJSONObject("data").toJavaObject(BigOneOrder.class);
    }
}
