package d.trade.mine;

import com.alibaba.fastjson.JSONObject;
import com.bigone.BigOneClient;
import com.chen.service.CoinParkServcie;
import com.dong.invest.model.ex.bigone.BigOneAsset;
import com.dong.invest.model.ex.bigone.BigOneOrder;
import com.exchange.CoinParkExchange;
import com.dong.invest.model.pairs.SymbolPair;
import d.trade.duichong.TradeResult;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * @author handong
 * @date 2018-08-10 10:56
 */
public class CoinParkMiner {

    private static Logger LOGGER = LoggerFactory.getLogger(CoinParkMiner.class);
    CoinParkExchange coinParkExchange = new CoinParkExchange();
    private double amount = 500d;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:00:00");


    public static void main(String[] args) throws Exception {
        CoinParkMiner bigOneMiner = new CoinParkMiner();
        bigOneMiner.mine();
    }

    @Test
    public void mine() throws Exception {

//        File file = new File("/Users/syd/Downloads/cpmine.log");
//        SymbolPair symbolPair = new SymbolPair();
//        symbolPair.setBasicToken("USDT");
//        symbolPair.setRealToken("CP");
//        symbolPair.setMarketId(symbolPair.getRealToken() + "-" + symbolPair.getBasicToken());
//        double basic = 0.00002;
//        double scale = 0.00001;

        CoinParkServcie.getAccount();

//        String url ="https://api.coinpark.cc/v1/mdata";
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("cmd", "api/pairList");
//        jsonObject.put("body", new HashMap<String,String>());
//        String res = utils.HttpUtil.doPost(url,jsonObject);
//        System.out.println(res);
//        logStatus(file,symbolPair,logs);
        //买卖和跑
//        int j = 0;
//        int total = 0;
//        int failCount = 0;
//        while (true) {
//            if (failCount > 10) {
//                System.out.println("失误，超过十次，停止");
//                logs.add("失误，超过十次，停止");
//                logStatus(file, symbolPair, logs);
//                break;
//            }
//            total ++;
////            if (System.currentTimeMillis() % (3600000) == 0) {
////                logs.add("----每小时统计----");
//////                logStatus(file,symbolPair, logs);
////            }
//            BigMinerStatus bigMinerStatus = BigOneServcie.getOneMiner();
//            if (bigMinerStatus.getTradeMineOne() > 2950000) {
//                String nowDateStr = simpleDateFormat.format(new Date());
//                Date nowDate = simpleDateFormat.parse(nowDateStr);
//                Date date = DateUtils.addHours(nowDate, 1);
//                logStatus(file, symbolPair, logs);
//                System.out.println("当前时间: "+nowDateStr+" 启动时间:"+ simpleDateFormat.format(date));
//                Thread.sleep(date.getTime() - System.currentTimeMillis());
//
//            }
//
//            BigOneTicker bigOneTicker = BigOneServcie.getTicker(symbolPair);
//
//            double dif = bigOneTicker.getAsk().getPrice() - bigOneTicker.getBid().getPrice();
//            if (dif > basic) {
//                double price = bigOneTicker.getAsk().getPrice() - scale;
//                TradeResult result = selfTrade(price, symbolPair);
//                if (!result.isAllSuccess()) {
//                    failCount ++;
//                }
//            } else {
//                System.out.println("跳过"+j ++ +"/" + total);
//                Thread.sleep(5000);
//            }
//        }

    }

    private void logStatus(File file, SymbolPair symbolPair, List<String> logs) throws Exception {
        List<BigOneAsset> bigOneAssetsNOw = BigOneClient.getAccount();
        BigOneAsset oneAsset1 = findOneAssets(bigOneAssetsNOw, symbolPair.getRealToken());
        BigOneAsset usdtAsset1 = findOneAssets(bigOneAssetsNOw, symbolPair.getBasicToken());


        logs.add("-------\n");
        logs.add("TIME:"+ simpleDateFormat.format(new Date()));
        logs.add("ONE:"+ (oneAsset1.getBalance()));
        logs.add("USD:" + (usdtAsset1.getBalance()));
        logs.add("-------\n");
        FileUtils.writeLines(file, logs);
        logs.clear();
    }

    private BigOneAsset findOneAssets(List<BigOneAsset> bigOneAssets, String usdt) {
        for (BigOneAsset bigOneAsset : bigOneAssets) {
            if (bigOneAsset.getAsset_id().equals(usdt)) {
                return bigOneAsset;
            }
        }
        return null;
    }

    private TradeResult selfTrade(double price, SymbolPair symbolPair) {
        TradeResult tradeResult = coinParkExchange.tradeAnsyc(price, 10000, symbolPair);
        System.out.println("交易订单发送成功" + tradeResult.isAllSuccess());
        return tradeResult;
    }


    @Test
    public void testOrders() throws Exception {
        SymbolPair symbolPair = new SymbolPair();
        symbolPair.setBasicToken("USDT");
        symbolPair.setRealToken("CP");
        symbolPair.setMarketId(symbolPair.getRealToken() + "-" + symbolPair.getBasicToken());
        BigOneOrder bigOneOrder = coinParkExchange.createOrder(4, 500,symbolPair, false);
        System.out.println(bigOneOrder);
        int cunt = 0;
        while (bigOneOrder.getState().equals("PENDING")) {
            bigOneOrder = BigOneClient.getOrder(bigOneOrder.getId());
            System.out.println(bigOneOrder.getState());
            if (cunt ++ > 5) {
                bigOneOrder = coinParkExchange.cancelOrder(bigOneOrder.getId());
            }
        }

        System.out.println("OIK");
    }
}
