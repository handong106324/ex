package com.dong.invest.model.pairs;

import com.dong.invest.annotation.ExchangePair;

/**
 * 币安交易对
 */
@ExchangePair(basicUrl = "https://api.binance.com", pairsUrl = "/api/v1/ticker/allPrices")
public class BinancePairsGet extends PairsGet {


}
