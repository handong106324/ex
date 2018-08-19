package com.bigone;

import com.ApiFactory;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chen.service.TradeHistoryInfo;
import com.dong.invest.model.ex.bigone.*;
import com.dong.invest.model.pairs.SymbolPair;
import com.utils.ExchangeUrlUtils;
import com.utils.JwtToken;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;
import d.trade.mine.BigMinerStatus;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import utils.HttpUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class BigOneClient {
    public static List<BigOneTicker> getTickers() {
        String tickersUrl = "/tickers";
        List<BigOneTicker> realList = new ArrayList<>();
        try {
            String result = (HttpUtil.doGet(ExchangeUrlUtils.B1_API_URL+tickersUrl,new HashMap<>()));
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

    public static  BigOneTicker getTicker(SymbolPair symbolPair) {
        String tickersUrl = "//markets/"+symbolPair.getRealToken() + "-" + symbolPair.getBasicToken()+"/ticker";
        String result = (HttpUtil.doGet(ExchangeUrlUtils.B1_API_URL+tickersUrl,new HashMap<>()));
        JSONObject marketList = (JSONObject) JSON.parse(result);
        BigOneTicker bigOneMarket = JSONObject.toJavaObject(marketList.getJSONObject("data"), BigOneTicker.class);
        return bigOneMarket;
    }

    public static  List<SymbolPair> getMarkets() {
        String tickersUrl = ExchangeUrlUtils.B1_API_URL + "/markets";
        String result = send(tickersUrl);
        if (StringUtils.isBlank(result)) {
            return new ArrayList<>();
        }
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

    public static  long getServerTime() {
        String pingUrl = "/ping";
        String result = send(ExchangeUrlUtils.B1_API_URL + pingUrl);

        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getLong("timestamp");
    }

    public static BigMinerStatus getOneMiner() {
        String oneUrl = "/one";
        String result = send(ExchangeUrlUtils.B1_API_URL + oneUrl);

        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getJSONObject("data").toJavaObject(BigMinerStatus.class);
    }

    public static  BigOrderBook getOrderBook(String marketId) {
        String url = ExchangeUrlUtils.B1_API_URL + "/markets/"+marketId+"/depth";
        String result = send(url);
        JSONObject object = JSONObject.parseObject(result).getJSONObject("data");
        return object.toJavaObject(BigOrderBook.class);
    }


    /**

     * @return
     * @throws Exception
     */
    public static  List<BigOneAsset>  getAccount() throws Exception {
        String url = ExchangeUrlUtils.B1_API_URL + "/viewer/accounts";

        List<BigOneAsset> list = new ArrayList<>();
        String result = HttpUtil.doGet(url,new JSONObject(), JwtToken.createToken(getServerTime(),ApiFactory.getKey("B1")));
        JSONArray accounts = JSONObject.parseObject(result).getJSONArray("data");
        for (Object acc : accounts) {
            list.add(((JSONObject)acc).toJavaObject(BigOneAsset.class));
        }
        return list;
    }

    public static double checkUpOrDown(String marketId, long timeLen) {
        long startTime = System.currentTimeMillis() - timeLen;

        double totalAskAmount = 0;
        double totalBidAmount = 0;
        long curTime;

        List<TradeHistoryInfo> tradeHistoryInfos = getTradeInfoList(marketId);

        for (TradeHistoryInfo tradeHistoryInfo : tradeHistoryInfos) {
            curTime = transToDate(tradeHistoryInfo.getInserted_at()).getTime();
            if (curTime < startTime) {
//                continue;
            }

            if ("ASK".equals(tradeHistoryInfo.getTaker_side())) {
                totalAskAmount += tradeHistoryInfo.getAmount();
            } else {
                totalBidAmount += tradeHistoryInfo.getAmount();
            }
        }

        System.out.println(totalAskAmount+"-"+totalBidAmount);
        return totalAskAmount/totalBidAmount;
    }


    public static List<TradeHistoryInfo> getTradeInfoList(String marketId) {
        String rsult = HttpUtil.doGet(ExchangeUrlUtils.B1_API_URL + "/markets/"+marketId+"/trades");
        JSONObject jsonObject = JSONObject.parseObject(rsult).getJSONObject("data");
        JSONArray array = jsonObject.getJSONArray("edges");
        List<TradeHistoryInfo> list = new ArrayList<>();
        for (Object tradeObj : array) {
            JSONObject data = (JSONObject) tradeObj;
            JSONObject info = data.getJSONObject("node");
            TradeHistoryInfo historyInfo = JSONObject.toJavaObject(info, TradeHistoryInfo.class);
            list.add(historyInfo);
        }

        return list;
    }

    /**
     * @return
     * @throws Exception
     */
    public static  BigOneOrderList viewerOrders() throws Exception {
        String url = ExchangeUrlUtils.B1_API_URL + "/viewer/orders";

        BigOneOrderList order = new BigOneOrderList();
        List<BigOneOrderNode> list = new ArrayList<>();

        String result = HttpUtil.doGet(url,new JSONObject(), JwtToken.createToken(getServerTime(),ApiFactory.getKey("B1")));
        JSONObject accounts = JSONObject.parseObject(result).getJSONObject("data");

        order.setPage_info(accounts.getJSONObject("page_info").toJavaObject(BigOnePageInfo.class));

        for (Object acc : accounts.getJSONArray("edges")) {
            list.add(((JSONObject)acc).getJSONObject("node").toJavaObject(BigOneOrderNode.class));
        }

        order.setEdges(list);
        return order;
    }

    /**
     * market_id	market uuid	d2185614-50c3-4588-b146-b8afe7534da6	true
     side	order side	one of "ASK"/"BID"	true
     price	order price	string	true
     amount	order amount	string, must larger than 0	true

     */
    public static BigOneOrder createOrder(String marketId, String side, String price, String amount) {
        try {
            String url = ExchangeUrlUtils.B1_API_URL + "/viewer/orders";
            JSONObject param = new JSONObject();
            param.put("market_id", marketId);
            param.put("side",side);
            param.put("price",price);
            param.put("amount", amount);
            String result = HttpUtil.doPostWithToken(url, param ,JwtToken.createToken(getServerTime(),ApiFactory.getKey("B1")));
            JSONObject res = JSONObject.parseObject(result);
            return res.getJSONObject("data").toJavaObject(BigOneOrder.class);
        } catch (Exception e) {
            return null;
        }
    }


    public static  String send(String url , Map<String,Object> params) {
        String result = (HttpUtil.doGet(url,params));
        return result;
    }

    public static  String send(String url) {
        String result = (HttpUtil.doGet(url,new HashMap<>()));
        return result;
    }

    public static  List<SymbolPair> getAllSymbolPairs() {
        return getMarkets();
    }

    public static  CurrentMarketInfo getTickers(SymbolPair symbolPair) {
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
    public static  TradeResult trade(double price,double amount, SymbolPair symbolPair, boolean isBuy) {
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

    public static Date transToDate(String time) {
        String at = time;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if (time.contains("T")) {
            String[] ars = StringUtils.split(time,"[Z|.|T]");
            at = ars[0] + " " + ars[1];
        }

        try {
            Date date = simpleDateFormat.parse(at);
            if(time.contains("T")) {
                DateUtils.addHours(date, 8);
            }
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TradeResult tradeAsync(double price, double amount, SymbolPair symbolPair, boolean isBuy,long timeOut) {
        String side = "ASK";

        TradeResult tradeResult = new TradeResult();

        try {
            BigOneOrder orderSell = createOrder(symbolPair.getRealToken() + "-" + symbolPair.getBasicToken(),side, ""+price, ""+amount);
            side = "BID";
            BigOneOrder orderBuy = createOrder(symbolPair.getRealToken() + "-" + symbolPair.getBasicToken(),side, ""+price, ""+amount);

            long start = System.currentTimeMillis();
            while (orderBuy.getState().equals("PENDING") || orderSell.getState().equals("PENDING")) {
                orderBuy = getOrder(orderBuy.getId());
                orderSell = getOrder(orderSell.getId());

                if ((System.currentTimeMillis() - start) > timeOut) {
                    if (orderBuy.getState().equals("PENDING")) {
                        cancelOrder(orderBuy.getId());
                    }

                    if (orderSell.getState().equals("PENDING")) {
                        cancelOrder(orderSell.getId());
                    }
                }
            }

            orderBuy = getOrder(orderBuy.getId());
            orderSell = getOrder(orderSell.getId());

            if (orderBuy.getState().equals("FILLED") && orderSell.getState().equals("FILLED")) {
                tradeResult.setAllSuccess(true);
            }
            if (orderBuy.getState().equals("CANCELED") && orderSell.getState().equals("CANCELED")) {
                tradeResult.setAllSuccess(true);
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

        return tradeResult;
    }

    public static BigOneOrder getOrder(String orderId) throws Exception {
        String url = ExchangeUrlUtils.B1_API_URL + "/viewer/orders/" +orderId;
        String result = HttpUtil.doGet(url, new HashMap<>() ,JwtToken.createToken(getServerTime(),ApiFactory.getKey("B1")));
        JSONObject res = JSONObject.parseObject(result);
        return res.getJSONObject("data").toJavaObject(BigOneOrder.class);
    }

    public static BigOneOrder cancelOrder(String orderId) throws Exception {
        String url = ExchangeUrlUtils.B1_API_URL + "/viewer/orders/" +orderId +"/cancel";
        String result = HttpUtil.doPostWithToken(url, new HashMap<>() ,JwtToken.createToken(getServerTime(),ApiFactory.getKey("B1")));
        JSONObject res = JSONObject.parseObject(result);
        return res.getJSONObject("data").toJavaObject(BigOneOrder.class);
    }
}
