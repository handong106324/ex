package com.dong.invest.model.pairs;

import com.dong.invest.annotation.ExchangePair;
import com.dong.invest.model.Kline;
import org.json.JSONArray;
import utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

public class KlineGet {
    public List<Kline> loadKline(SymbolPair symbolPair, String interVal) {
        List<Kline> klineList = new ArrayList<Kline>();
        BinancePairsKlineGet binancePairsKlineGet = new BinancePairsKlineGet();
        ExchangePair exchangePair = binancePairsKlineGet.getClass().getAnnotation(ExchangePair.class);
        try {
            String param = "?symbol="+symbolPair.getRealToken()+symbolPair.getBasicToken();
            param+= ("&interval=" + interVal);
            String data = HttpUtil.doGet(exchangePair.basicUrl() + exchangePair.pairsUrl() + param);
            JSONArray jsonArray = new JSONArray(data);
            for (int i = 0 ; i< jsonArray.length();i ++) {
                JSONArray object = (JSONArray) jsonArray.get(i);
                String[] klineParams = new String[8];
                for (int j = 0 ;j < 8;j++) {
                    klineParams[j] = object.get(j).toString();
                }
                Kline kline = new Kline(klineParams);
                klineList.add(kline);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return klineList;
    }
}
