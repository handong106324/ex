package com.dong;

import com.bigone.BigOneClient;
import com.chen.service.CoinParkServcie;
import com.dong.invest.model.Exchange;
import com.dong.invest.model.ex.bigone.BigOneTicker;
import com.dong.invest.model.ex.bigone.BigPrice;
import com.dong.invest.model.pairs.SymbolPair;
import com.exchange.BigOneExchange;
import com.exchange.HuoBiExchange;
import com.huobi.response.MergedResponse;
import com.huobi.response.Symbol;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BigOneAPITest {

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
                        checkPriceCanBuy(coin,exchanges);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(coin + "决策第"+time ++ +"轮结束");

                }


        }
    }

    private void checkPriceCanBuy(String coin, List<Exchange> exchanges) {
        List<BigOneTicker> tickers = new ArrayList<>();
        for (Exchange exchange : exchanges) {
            BigOneTicker ticker = exchange.getTicker(exchange.symbols.get(coin));
            tickers.add(ticker);
            ticker.setExchange(exchange);
        }

        Exchange maxExchange = null;

        double highest = 0;
        Exchange highExchange = null;

        double lowPrice = tickers.get(0).getAsk().getPrice();
        for (BigOneTicker ticker : tickers) {
            //交易所卖价 < 另外交易所的买价
            if (ticker.getAsk().getPrice().doubleValue() < lowPrice) {
                lowPrice = ticker.getAsk().getPrice().doubleValue();
                maxExchange = ticker.getExchange();
            }

            if (ticker.getBid().getPrice().doubleValue() > highest ) {
                highest = ticker.getBid().getPrice().doubleValue();
                highExchange = ticker.getExchange();
            }


        }
        System.out.println(coin +": " + maxExchange.getName() +" "+lowPrice + ":" + highExchange.getName() + " " + highest);

        if (maxExchange != null && highExchange != null) {
            if (lowPrice > highest) {
                System.out.println("决策满足条件");
            } else {

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
