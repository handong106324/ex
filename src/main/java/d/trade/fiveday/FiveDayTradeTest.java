package d.trade.fiveday;

/**
 * 五日线
 */
public class FiveDayTradeTest implements Runnable {
    /**
     * 交易所
     */
    private String ex;

    /**
     * 时长
     */
    private long time;

    /**
     * 盈利次数
     */
    private long winTime;

    /**
     * 策略失败次数
     */
    private long failTime;

    /**
     * 总盈利
     */
    private long winMoneyAmount;

    /**
     * 总损失
     */
    private long failMoneyAmount;

    /**
     * 五日线计算区间
     */
    private long fiveDayCountTime;

    /**
     * 上次五日线计算时间
     */
    private long lastCountTime;

    /**
     * 五日线计算次数
     */
    private long continueTime;

    /**
     * 最少生效次数
     */
    private long lestCountTime;

    /**
     * 0 canBuy
     * 1  canSell
     */
    private int status = -1;

    /**
     * 0 buy
     * 1 sell
     */
    private int tradeStatus = -1;

    /**
     * 上次是否上升
     */
    private boolean lastIsUp;

    private long lastFiveDay;

    private double amount;

    private double currentPrice;
    @Override
    public void run() {
        checkCanBuy();
        if (status == -1) {
            return;
        }

        if (status == 0) {
            if (tradeStatus == 0) {
                return;
            } else {
                buy(amount, currentPrice);
            }
        } else {
            if (tradeStatus == 1) {
                return;
            } else {
                sell(amount, currentPrice);
            }
        }
    }

    private void sell(double amount,double currentPrice) {

        System.out.println("sell "+ amount + " at price =" + currentPrice +  " cost " + amount * currentPrice);
    }
    private void buy(double amount,double currentPrice) {
        System.out.println("buy "+ amount + " at price =" + currentPrice +  " cost " + amount * currentPrice);
    }

    /**
     * 获取本次五日线/当前价格和数量
     * @return
     */
    public double getCurrentFiveDay() {


        return 0;
    }

    private void checkCanBuy() {
        if (status != -1) {
            return;
        }
        //get five day current value
        double currentFiveDay = getCurrentFiveDay();
        if (currentFiveDay >= lastFiveDay) {
            if (lastIsUp) continueTime++;
            else {
                continueTime = 0;
                lastIsUp = false;
            }
        } else {
            if (!lastIsUp) {
                continueTime ++;
            } else {
                lastIsUp = true;
                continueTime = 0;
            }
        }

        if (continueTime >= lestCountTime) {
            if (lastIsUp) {
                status = 0;
            } else {
                status = 1;
            }
        }
    }
}
