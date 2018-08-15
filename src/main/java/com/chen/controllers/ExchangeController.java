package com.chen.controllers;

import com.alibaba.fastjson.JSON;
import com.dong.invest.model.Exchange;
import com.dong.invest.model.pairs.SymbolPair;
import net.paoding.rose.web.annotation.DefValue;
import net.paoding.rose.web.annotation.HttpFeatures;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Path("")
public class ExchangeController {

    private static List<Exchange> exchanges;
    public static void initExchanges() {
        if (null == exchanges) {
            exchanges = new ArrayList<>();
        }


    }


    @Get({"", "listPairs"})
    @HttpFeatures(contentType = "json")
    public String listAllPairs(@Param("pairs") @DefValue("") String[] pairs) {
        //json result
        Map<String,Object> result = new HashMap<>();
        //json for symbol-pair
        Map<String, List<SymbolPair>> pairResult = new HashMap<>();

        List<SymbolPair> allPairList  = new ArrayList<>();
        boolean filter = true;
        if (null == pairs || (pairs.length == 1 && StringUtils.isBlank(pairs[0]))) {
            filter = false;
        }
        for (Exchange exchange : exchanges) {
            List<SymbolPair> symbolPairs = exchange.getAllSymbolPairs();
            for (SymbolPair symbolPair : symbolPairs) {
                List<SymbolPair> symbolPairList = pairResult.get(symbolPair.getRealToken());
                if (null == symbolPairList) {
                    symbolPairList = new ArrayList<>();
                    pairResult.put(symbolPair.getRealToken(), symbolPairList);
                }

                symbolPairList.add(symbolPair);
            }
        }


        return JSON.toJSONString(result);
    }
}
