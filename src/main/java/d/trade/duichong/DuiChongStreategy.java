package d.trade.duichong;

import com.dong.invest.model.Exchange;
import com.dong.invest.model.pairs.SymbolPair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DuiChongStreategy {

    private static Logger LOGGER = LoggerFactory.getLogger(DuiChongStreategy.class);
    private SymbolPair symbolPair;

    private Exchange exchangeOne;

    private Exchange exchangeTwo;


    public void execute() {

        CurrentMarketInfo oneMarketInfo = getMarketInfo(exchangeOne, symbolPair);
        CurrentMarketInfo twoMarketInfo = getMarketInfo(exchangeTwo, symbolPair);

        double oneAskPrice = oneMarketInfo.getAskPrice();
        double oneAskAmount = oneMarketInfo.getAskAmount();
        double oneBidPrice = oneMarketInfo.getBidPrice();
        double oneBidAmount = oneMarketInfo.getBidAmount();

        double twoBidPrice = twoMarketInfo.getBidPrice();
        double twoBidAmount = twoMarketInfo.getBidAmount();
        double twoAskPrice = twoMarketInfo.getAskPrice();
        double twoAskAmount = twoMarketInfo.getAskAmount();

        TradeResult askResult;
        TradeResult bidResult;
        double win = 0;
        StringBuilder builder = new StringBuilder();
        if ((oneAskPrice - twoBidPrice) > 0 && (oneAskPrice - twoBidPrice)/oneAskPrice > 0.002) {
            double amount = oneAskAmount > twoBidAmount?twoBidAmount:oneAskAmount;
             askResult = ask(exchangeOne, amount, oneAskPrice,symbolPair);
             bidResult = bid(exchangeTwo, amount, twoBidPrice, symbolPair);
             builder.append(exchangeOne.getName() + " ask " + amount + "" + symbolPair.getRealToken() + " at " + oneAskPrice);
             builder.append(exchangeTwo.getName() + " bid " + amount + "" + symbolPair.getRealToken() + " at " + twoBidPrice);
             win = oneAskPrice * amount - amount * twoBidPrice;
        } else if ((twoAskPrice - oneBidPrice) > 0 && (twoAskPrice - oneBidPrice)/twoAskPrice > 0.002) {
            double amount = twoAskAmount > oneBidAmount?twoAskAmount:oneBidAmount;
             askResult = ask(exchangeOne, amount, oneAskPrice,symbolPair);
             bidResult = bid(exchangeTwo, amount, twoBidPrice,symbolPair);

             win = twoAskPrice * amount - amount * oneBidPrice;
        } else {
            return;
        }

        if (askResult.isSuccess() && bidResult.isSuccess()) {
            LOGGER.info(builder.toString() + " win " + win);
        } else if (!askResult.isSuccess() && !bidResult.isSuccess()) {

        } else {
            if (askResult.isSuccess()) {

            }

            if (bidResult.isSuccess()) {

            }
        }
    }

    private TradeResult bid(Exchange exchange, double amount, double price, SymbolPair symbolPair) {
        return exchange.sell( price, amount, symbolPair);
    }

    private TradeResult ask(Exchange exchange, double amount, double oneAskPrice,SymbolPair symbolPair) {
        return exchange.buy( oneAskPrice, amount,symbolPair);
    }

    private CurrentMarketInfo getMarketInfo(Exchange exchange, SymbolPair symbolPair) {
        return exchange.getMarketTradeInfo(symbolPair);
    }
}
