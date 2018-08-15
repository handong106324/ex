/**
 * @author 54chen(陈臻) [chenzhen@xiaomi.com czhttp@gmail.com]
 * @since 2012-4-10 上午11:14:46
 */

package com.chen.controllers;

import com.alibaba.fastjson.JSON;
import com.dong.invest.model.ex.bigone.BigOneTicker;
import com.utils.ExchangeUrlUtils;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;
import utils.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@Path("bigone")
public class BigOneController {


    @Get("tickers")
    @Post("tickers")
    public String tickers() throws IOException{
        String tickersUrl = "/tickers";
        try {
            String result = (HttpUtil.doGet(ExchangeUrlUtils.B1_API_URL+tickersUrl,new HashMap<>()));
            List<BigOneTicker> marketList = (List) JSON.parseArray(result);
            for (BigOneTicker bigOneMarket : marketList) {
                System.out.println(bigOneMarket.getMarket_id() + "-" + bigOneMarket.getVolume() + " " + bigOneMarket.getClose());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "big-list";
    }


}
