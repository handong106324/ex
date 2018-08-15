package d.trade.duichong;

public class CurrentMarketInfo {
    private double askPrice;
    private double askAmount;
    private double bidPrice;
    private double bidAmount;

    public double getAskPrice() {
        return askPrice;
    }

    public void setAskPrice(double askPrice) {
        this.askPrice = askPrice;
    }

    public double getAskAmount() {
        return askAmount;
    }

    public void setAskAmount(double askAmount) {
        this.askAmount = askAmount;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public void setBidAmount(double bidAmount) {
        this.bidAmount = bidAmount;
    }


    @Override
    public String toString() {
        return "CurrentMarketInfo{" +
                "askPrice=" + askPrice +
                ", askAmount=" + askAmount +
                ", bidPrice=" + bidPrice +
                ", bidAmount=" + bidAmount +
                '}';
    }
}
