package com.dong.invest.model.pairs;

import com.dong.invest.annotation.ExchangePair;
import com.dong.invest.model.APIGet;
import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import utils.HttpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 交易对
 */
public class PairsGet extends APIGet {

    private List<SymbolPair> pairList;

    public List<SymbolPair> getPairList() {
        return pairList;
    }

    public void setPairList(List<SymbolPair> pairList) {
        this.pairList = pairList;
    }

    public void loadPairs() {
        try {
            ExchangePair pair = this.getClass().getAnnotation(ExchangePair.class);

            String content = HttpUtil.doGet(pair.basicUrl() + pair.pairsUrl());

            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0;i < jsonArray.length();i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                SymbolPair symbolPair = new SymbolPair();
                String coinPair = jsonObject.getString("symbol");
                if (coinPair.endsWith("BTC")) {
                    symbolPair.setBasicToken("BTC");
                } else if (coinPair.endsWith("ETH")) {
                    symbolPair.setBasicToken("ETH");
                } else if (coinPair.endsWith("USDT")) {
                    symbolPair.setBasicToken("USDT");
                } else if (coinPair.endsWith("BNB")) {
                    symbolPair.setBasicToken("BNB");
                } else {
                    continue;
                }
                symbolPair.setRealToken(StringUtils.substring(coinPair,0,
                        coinPair.length() - symbolPair.getBasicToken().length()));

                symbolPair.setPrice(jsonObject.getDouble("price"));
                if (this.pairList == null) {
                    this.pairList = new ArrayList<SymbolPair>();
                }
                this.pairList.add(symbolPair);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadAllPairPrice() {

    }
}
