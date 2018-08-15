package com.dong.invest.model.assets;

/**
 * 资产
 */
public class Assets {
    private String exchangeName;;

    private double assetsTotalWithBTC;

    private double assetsTotalWithETH;

    private double assetsTotalWithCNY;

    public String getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public double getAssetsTotalWithBTC() {
        return assetsTotalWithBTC;
    }

    public void setAssetsTotalWithBTC(double assetsTotalWithBTC) {
        this.assetsTotalWithBTC = assetsTotalWithBTC;
    }

    public double getAssetsTotalWithETH() {
        return assetsTotalWithETH;
    }

    public void setAssetsTotalWithETH(double assetsTotalWithETH) {
        this.assetsTotalWithETH = assetsTotalWithETH;
    }

    public double getAssetsTotalWithCNY() {
        return assetsTotalWithCNY;
    }

    public void setAssetsTotalWithCNY(double assetsTotalWithCNY) {
        this.assetsTotalWithCNY = assetsTotalWithCNY;
    }
}
