package com.dong;

import com.dong.invest.model.Exchange;
import com.dong.invest.model.ex.bigone.BigOneTicker;
import com.dong.invest.model.ex.bigone.BigPrice;
import com.dong.invest.model.pairs.SymbolPair;
import com.exchange.BigOneExchange;
import com.exchange.HuoBiExchange;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BigOneAPITest {

    File file = new File("/Users/handong/Downloads/real.log");

    /**
     * [BTG-BTC, IOST-ETH, AIT-ETH, UIP-BTC, SNGLS-ETH, TCT-ETH, VEN-ETH, BTO-ETH, INK-BTC, TNB-BTC, CHAT-BTC, ZRX-BTC, GCT-ETH, GXS-BTC, XIN-ETH, QUBE-ETH, JLP-ETH, ZEC-BTC, ELF-BTC, EOSDAC-ETH, BCH-BTC, ADX-BTC, CVC-BTC, LUN-BTC, EKT-BTC, CRE-BTC, BTS-BTC, ETC-BTC, NEO-BTC, SHOW-BTC, EOS-BTC, BCD-BTC, BTO-BTC, CHAT-ETH, MAG-ETH, ETH-BTC, LINK-BTC, SNGLS-BTC, PIX-BTC, QTUM-BTC, BTM-BTC, MTN-BTC, DEW-ETH, MCO-BTC, GNT-BTC, AIT-BTC, FGC-ETH, BOE-ETH, DEW-BTC, EDG-BTC, HMC-ETH, CRE-ETH, DTA-ETH, ATN-BTC, FAIR-BTC, PRS-BTC, TCT-BTC, IOST-BTC, GCS-ETH, OCT-ETH, LTC-BTC, OMG-BTC, MAG-BTC, LUN-ETH, EOS-ETH, MANA-BTC, IDT-ETH, MANA-ETH, EOSDAC-BTC, XIN-BTC, SBTC-BTC, HOT-ETH, BOT-ETH, MYST-BTC, TNT-BTC, GCS-BTC, AE-BTC, GCT-BTC, MUSK-BTC, CDT-BTC, UIP-ETH, DTA-BTC, FGC-BTC, VEN-BTC, PST-BTC, IDT-BTC, CANDY-ETH, BCDN-BTC, 1ST-BTC, READ-BTC, HMC-BTC, QUBE-BTC, KNC-BTC, GNX-BTC, MTN-ETH, TNB-ETH, PRS-ETH, OMG-ETH, DGD-BTC, PAY-BTC, SNT-BTC, QUN-BTC, BAT-BTC, STORJ-BTC, AT-BTC, UTO-ETH, BCH-USDT, EOS-USDT, BTC-USDT, ETH-USDT, ONE-USDT, BIG-BTC, BIG-ETH, ONE-EOS]

     * @throws Exception
     */
    @Test
    public void testBigOne() throws Exception {
        HuoBiExchange huoBiExchange = new HuoBiExchange();

        BigOneExchange bigOneClient = new BigOneExchange();

        List<String> haveCoins = new ArrayList<>();
        for (String coin : bigOneClient.symbols().keySet()) {
            if (huoBiExchange.symbols().containsKey(coin)) {
                haveCoins.add(coin);
            }
        }

        List<Exchange> exchanges = new ArrayList<>();
        exchanges.add(bigOneClient);
        exchanges.add(huoBiExchange);

        int time = 0;
        while (true) {
                for (String coin : haveCoins) {
                    try {
                        if (!coin.contains("usdt")) {
                            continue;
                        }
                        checkPriceCanBuy(coin,exchanges);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            System.out.println("决策第"+time ++ +"轮结束");

        }
    }

    private void checkPriceCanBuy(String coin, List<Exchange> exchanges) {
        List<BigOneTicker> tickers = new ArrayList<>();
        for (Exchange exchange : exchanges) {
            BigOneTicker ticker = exchange.getTicker(exchange.symbols.get(coin));
            tickers.add(ticker);
            ticker.setExchange(exchange);
        }
        BigOneTicker askTicker = null;

        double highestBidPrice = 0;
        BigOneTicker bidTicker = null;

        double minAskPrice = 999999999;
        for (BigOneTicker ticker : tickers) {
            //交易所卖价 < 另外交易所的买价
            if (ticker.getAsk().getPrice().doubleValue() < minAskPrice) {
                minAskPrice = ticker.getAsk().getPrice().doubleValue();
                askTicker = ticker;
            }

            if (ticker.getBid().getPrice().doubleValue() > highestBidPrice ) {
                highestBidPrice = ticker.getBid().getPrice().doubleValue();
                bidTicker = ticker;
            }

        }

        if (askTicker.getExchange().getName().equalsIgnoreCase(bidTicker.getExchange().getName())) {
            return;
        }

        if (askTicker != null && bidTicker != null) {
            double get = highestBidPrice - minAskPrice;
            if (get > 0) {

                List<String> logs = new ArrayList<>();
                System.out.println(coin +"决策满足条件:" + toStr(highestBidPrice) + "  -  " + toStr(minAskPrice) +" = " + toStr(get));

                if (get <= (minAskPrice + highestBidPrice) * 0.002) {
                    System.out.println("    FAIL:"+coin +"盈利不满足条件:" + toStr(highestBidPrice) + "  -  " + toStr(minAskPrice) +" = " + toStr(get - (minAskPrice + highestBidPrice) * 0.002));
                    return;
                }
                logs.add("[SUCCESS:"+coin +"][决策和盈利都满足条件:" + toStr(highestBidPrice) + "  -  " + toStr(minAskPrice) +" - " +(minAskPrice + highestBidPrice) * 0.002 +" = " + toStr(get - (minAskPrice + highestBidPrice) * 0.002) +"]");
                try {
                    FileUtils.writeLines(file, logs);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
//                System.out.println("失败决策："+coin +": " + askTicker.getAsk() +" "+minAskPrice + ":" + bidTicker.getBid() + " " + highestBidPrice);

            }
        }
    }


    public double[] countPriceAndAmount(List<BigPrice> bigPrices) {
        double amount = 0;
        double priceTotal = 0;
        for (BigPrice bigPrice : bigPrices) {
            amount += bigPrice.getAmount();
            priceTotal += bigPrice.getPrice() * bigPrice.getAmount();
            System.out.println(bigPrice.getAmount() + "_" + bigPrice.getPrice());
        }
        return new double[]{amount,priceTotal};

    }

    public static String toStr(Double d) {
        Double dob = new Double(d);
        DecimalFormat decimalFormat = new DecimalFormat("##.##");
        return decimalFormat.format(d);
    }

}
