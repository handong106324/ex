package com.dong.invest.model;

public class Kline {
    /**
     * [
     1499040000000,      // Open time
     "0.01634790",       // Open
     "0.80000000",       // High
     "0.01575800",       // Low
     "0.01577100",       // Close
     "148976.11427815",  // Volume
     1499644799999,      // Close time
     "2434.19055334",    // Quote asset volume
     308,                // Number of trades
     "1756.87402397",    // Taker buy base asset volume
     "28.46694368",      // Taker buy quote asset volume
     "17928899.62484339" // Can be ignored
     ]
     */
    public Kline(String[] objects) {
        openTime = Long.parseLong(objects[0]);
        open = Double.parseDouble(objects[1]);
        high = Double.parseDouble(objects[2]);
        low = Double.parseDouble(objects[3]);
        close = Double.parseDouble(objects[4]);
        volume = Double.parseDouble(objects[5]);
        closeTime = Long.parseLong(objects[6]);
//         = Long.parseLong(objects[7]);
//        openTime = Long.parseLong(objects[8]);
//        openTime = Long.parseLong(objects[9]);
    }

    private long openTime;

    private double open;

    private double high;

    private double low;

    private double close;

    private  double volume;

    private long closeTime;

    private long total;

    private int tradeCount;

    private double buyBaseAssetVolume;

    private double buyQuoteAssetVolume;

    public long getOpenTime() {
        return openTime;
    }

    public void setOpenTime(long openTime) {
        this.openTime = openTime;
    }

    public double getOpen() {
        return open;
    }

    public void setOpen(double open) {
        this.open = open;
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    public double getLow() {
        return low;
    }

    public void setLow(double low) {
        this.low = low;
    }

    public double getClose() {
        return close;
    }

    public void setClose(double close) {
        this.close = close;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public long getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(long closeTime) {
        this.closeTime = closeTime;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTradeCount() {
        return tradeCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public double getBuyBaseAssetVolume() {
        return buyBaseAssetVolume;
    }

    public void setBuyBaseAssetVolume(double buyBaseAssetVolume) {
        this.buyBaseAssetVolume = buyBaseAssetVolume;
    }

    public double getBuyQuoteAssetVolume() {
        return buyQuoteAssetVolume;
    }

    public void setBuyQuoteAssetVolume(double buyQuoteAssetVolume) {
        this.buyQuoteAssetVolume = buyQuoteAssetVolume;
    }
}
