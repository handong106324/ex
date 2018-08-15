package com.dong;

import com.binance.util.CryptUtil;
import com.dong.invest.model.Kline;
import com.dong.invest.model.assets.AssetsGet;
import com.dong.invest.model.assets.BinanceAccountGet;
import com.dong.invest.model.pairs.BinanceInterval;
import com.dong.invest.model.pairs.BinancePairsGet;
import com.dong.invest.model.pairs.KlineGet;
import com.dong.invest.model.pairs.SymbolPair;
import org.junit.Test;
import utils.HttpUtil;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class BinanceAPITest {

    @Test
    public void testPairs() {
        try {
            BinancePairsGet binancePairsGet = new BinancePairsGet();

            binancePairsGet.loadPairs();

            for (SymbolPair symbolPair : binancePairsGet.getPairList()) {
                System.out.println(symbolPair.getBasicToken()+"/" + symbolPair.getRealToken() +"  "
                        + symbolPair.getPriceFormat());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testKline() {
        DecimalFormat decimalFormat = new DecimalFormat("##.########");

        int maDuriation = 5;
        SymbolPair symbolPair = new SymbolPair();
        symbolPair.setBasicToken("ETH");
        symbolPair.setRealToken("QSP");
        KlineGet klineGet = new KlineGet();
        BinanceInterval binanceInterval = BinanceInterval.FIFTEEN_MIN;
        String interval = binanceInterval.getInterval();
        long sleepTime = binanceInterval.getTimes();
        while (true) {
            List<Kline> klineList = klineGet.loadKline(symbolPair, interval);

            LinkedBlockingQueue<Double> linkedBlockingQueue = new LinkedBlockingQueue(maDuriation);

            int index = 0;
            for (Kline kline : klineList) {
                if(index == klineList.size() - 1){
                    System.out.println(decimalFormat.format(kline.getClose()));
                }
                if (linkedBlockingQueue.size() == maDuriation) {
                    linkedBlockingQueue.poll();
                }
                linkedBlockingQueue.add(kline.getClose());
                index ++;
                double ma = ma(linkedBlockingQueue, maDuriation);
//                System.out.println("----    "+decimalFormat.format(ma));
                if (index >= (klineList.size() - 1)) {
                    if (kline.getClose() > ma) {
//                        System.out.println("    五日线之上");
                        //是否可买：是否回踩？
                        // 1： close 小于open
                        if (kline.getClose() < kline.getOpen() && isPercent(kline, 0.05, true)) {
                            System.out.println("    回踩5%，可买入");
                        }

//                    //是否可卖
//                    if (kline.getClose() > kline.getOpen() && isPercent(kline, 0.05, false)) {
//                        System.out.println("    回踩5%，可买入");
//                    }


                    } else {
//                    System.out.println("    五日线之下");
                        //是否可买

                        //是否可卖
                    }
                }
            }

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

//        BinancePairsGet binancePairsGet = new BinancePairsGet();
//
//        binancePairsGet.loadPairs();
//
//        BinancePairsKlineGet binancePairsKlineGet = new BinancePairsKlineGet();
//        loadKlines(binancePairsGet.getPairList(),BinanceInterval.ONE_HOUR.getInterval());
    }

    private boolean isPercent(Kline kline, double v, boolean isDown) {
        if (isDown) {
            double percent = (kline.getClose() - kline.getLow())/(kline.getOpen() - kline.getLow());
            return v > percent;
        }

        double percent = (kline.getHigh() - kline.getClose())/(kline.getHigh() - kline.getLow());
        return v > percent;

    }

    private double ma(LinkedBlockingQueue<Double> linkedBlockingQueue, int maDuriation) {
        if (linkedBlockingQueue.size() < maDuriation) {
            return 0;
        }
        Double total = 0.0D;
        Iterator<Double> iterator = linkedBlockingQueue.iterator();
        while (iterator.hasNext()) {
            Double thisVal =  iterator.next();
            total += thisVal;
        }
        return total/maDuriation;
    }

    public void loadKlines(List<SymbolPair> pairList, String oneHour) {
        for (SymbolPair symbolPair : pairList) {
            KlineGet klineGet = new KlineGet();
            klineGet.loadKline(symbolPair, oneHour);
        }
    }
    @Test
    public void testDepth() {

    }

    @Test
    public void getAccount() {
        BinanceAccountGet binanceAccountGet = new BinanceAccountGet();
        String recvWindow = 1000+"";
        String timestamp = System.currentTimeMillis() +"";
        AssetsGet assetsGet = binanceAccountGet.getClass().getAnnotation(AssetsGet.class);
        if (null != assetsGet) {
            try {

                String hash = CryptUtil.hmacSHA256(assetsGet.apiKey(), assetsGet.secectKey());
                System.out.println(hash);
                String param = "?signature=" + hash+"&timestamp=" + timestamp;
                String res = HttpUtil.doGet(assetsGet.exchangeUrl()+ assetsGet.accountUrl() +param);
                System.out.println(res);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static String byte2hex(byte[] b)
    {
        StringBuilder hs = new StringBuilder();
        String stmp;
        for (int n = 0; b!=null && n < b.length; n++) {
            stmp = Integer.toHexString(b[n] & 0XFF);
            if (stmp.length() == 1)
                hs.append('0');
            hs.append(stmp);
        }
        return hs.toString().toUpperCase();
    }

}
