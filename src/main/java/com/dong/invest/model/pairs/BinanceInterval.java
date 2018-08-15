package com.dong.invest.model.pairs;

public enum  BinanceInterval {
    ONEMIN("1m", 60000),
    THREE_MIN("3m", 3 * 60000),
    FIVE_MIN("5m", 5 * 60000),
    FIFTEEN_MIN("15m", 15 * 60000),
    THRITY_MIN("30m", 30 * 60000),
    ONE_HOUR("1h", 60 * 60000),
    TWO_HOUR("2h", 120 * 60000),
    FOUR_HOUR("4h", 240 * 60000),
    SIX_HOUR("6h", 360 * 60000),
    EIGHT_HOUR("8h", 480 * 60000),
    HALF_DAY("12h", 720 * 60000),
    ONE_DAY("1d", 1440 * 60000),
    THREE_DAY("3d", 4320 * 60000),
    ONE_WEEK("1w", 10000 * 60000),
    ONE_MONTH("1M",300000 * 60000);

    private String interval;
    private long times;

    BinanceInterval(String interval, long times) {
        this.interval = interval;
        this.times = times;
    }




    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }
}
