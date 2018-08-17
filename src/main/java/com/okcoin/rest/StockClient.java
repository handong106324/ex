package com.okcoin.rest;

import java.io.IOException;

import com.ApiFactory;
import com.ApiKey;
import org.apache.http.HttpException;


import com.alibaba.fastjson.JSONObject;
import com.okcoin.rest.stock.IStockRestApi;
import com.okcoin.rest.stock.impl.StockRestApi;

/**
 * 现货 REST API 客户端请求
 *
 * @author zhangchi
 */
public class StockClient {

    public static void main(String[] args) throws HttpException, IOException {
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

        //现货行情
        String tikerInfo = stockGet.ticker("btc_usdt");
        print(tikerInfo);
        //现货市场深度
        String depth = stockGet.depth("btc_usdt");
        print(depth);
        //现货OKCoin历史交易信息
        String trades = stockGet.trades("btc_usdt", "20");

        print(trades);

        //现货用户信息
        String userInfo = stockPost.userinfo();

        print("userInfo:" + userInfo);
//        //现货下单交易
//        String tradeResult = stockPost.trade("btc_usd", "buy", "50", "0.02");
//        System.out.println(tradeResult);
//        JSONObject tradeJSV1 = JSONObject.parseObject(tradeResult);
//        String tradeOrderV1 = tradeJSV1.getString("order_id");
//
//        //现货获取用户订单信息
//        stockPost.order_info("btc_usd", tradeOrderV1);
//
//        //现货撤销订单
//        stockPost.cancel_order("btc_usd", tradeOrderV1);
//
//        //现货批量下单
//        stockPost.batch_trade("btc_usd", "buy", "[{price:50, amount:0.02},{price:50, amount:0.03}]");
//
//        //批量获取用户订单
//        stockPost.orders_info("0", "btc_usd", "125420341, 125420342");

        //获取用户历史订单信息，只返回最近七天的信息
        String orderHIst= stockPost.order_history("btc_usdt", "0", "1", "20");

        print(orderHIst);

    }

    public static void print(String s) {
        System.out.println(s);
    }
}
