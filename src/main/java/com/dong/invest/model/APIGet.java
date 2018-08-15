package com.dong.invest.model;

/**
 * 获取api
 */
public class APIGet {

    /**
     * 交易所代码
     */
    private String exchange;

    /**
     * 交易所名称
     */
    private String exchangeName;

    /**
     * 交易所官网地址
     */

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public String getExchangeUrl() {
        return exchangeUrl;
    }

    public void setExchangeUrl(String exchangeUrl) {
        this.exchangeUrl = exchangeUrl;
    }

    private String exchangeUrl;




}
