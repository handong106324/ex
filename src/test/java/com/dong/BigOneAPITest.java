package com.dong;

import com.chen.service.BigOneServcie;
import com.chen.service.CoinParkServcie;
import com.dong.invest.model.ex.bigone.BigOneTicker;
import com.dong.invest.model.ex.bigone.BigOrderBook;
import com.dong.invest.model.ex.bigone.BigPrice;
import com.dong.invest.model.pairs.SymbolPair;
import d.trade.duichong.CurrentMarketInfo;
import d.trade.duichong.TradeResult;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.List;

public class BigOneAPITest {

    /**
     * [BTG-BTC, IOST-ETH, AIT-ETH, UIP-BTC, SNGLS-ETH, TCT-ETH, VEN-ETH, BTO-ETH, INK-BTC, TNB-BTC, CHAT-BTC, ZRX-BTC, GCT-ETH, GXS-BTC, XIN-ETH, QUBE-ETH, JLP-ETH, ZEC-BTC, ELF-BTC, EOSDAC-ETH, BCH-BTC, ADX-BTC, CVC-BTC, LUN-BTC, EKT-BTC, CRE-BTC, BTS-BTC, ETC-BTC, NEO-BTC, SHOW-BTC, EOS-BTC, BCD-BTC, BTO-BTC, CHAT-ETH, MAG-ETH, ETH-BTC, LINK-BTC, SNGLS-BTC, PIX-BTC, QTUM-BTC, BTM-BTC, MTN-BTC, DEW-ETH, MCO-BTC, GNT-BTC, AIT-BTC, FGC-ETH, BOE-ETH, DEW-BTC, EDG-BTC, HMC-ETH, CRE-ETH, DTA-ETH, ATN-BTC, FAIR-BTC, PRS-BTC, TCT-BTC, IOST-BTC, GCS-ETH, OCT-ETH, LTC-BTC, OMG-BTC, MAG-BTC, LUN-ETH, EOS-ETH, MANA-BTC, IDT-ETH, MANA-ETH, EOSDAC-BTC, XIN-BTC, SBTC-BTC, HOT-ETH, BOT-ETH, MYST-BTC, TNT-BTC, GCS-BTC, AE-BTC, GCT-BTC, MUSK-BTC, CDT-BTC, UIP-ETH, DTA-BTC, FGC-BTC, VEN-BTC, PST-BTC, IDT-BTC, CANDY-ETH, BCDN-BTC, 1ST-BTC, READ-BTC, HMC-BTC, QUBE-BTC, KNC-BTC, GNX-BTC, MTN-ETH, TNB-ETH, PRS-ETH, OMG-ETH, DGD-BTC, PAY-BTC, SNT-BTC, QUN-BTC, BAT-BTC, STORJ-BTC, AT-BTC, UTO-ETH, BCH-USDT, EOS-USDT, BTC-USDT, ETH-USDT, ONE-USDT, BIG-BTC, BIG-ETH, ONE-EOS]

     * @throws Exception
     */
    @Test
    public void testBigOne() throws Exception {
        CoinParkServcie servcie = new CoinParkServcie();

        List<SymbolPair> pairs = servcie.getMarkets();
        for (SymbolPair symbolPair : pairs) {
            System.out.println(symbolPair.getRealToken() +"-" + symbolPair.getBasicToken());
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
