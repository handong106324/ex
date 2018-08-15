package d.trade.duichong;

public class TradeResult {
    private boolean isSuccess;
    private boolean isAllSuccess;
    private String errorCode;
    private String errMsg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "TradeResult{" +
                "isSuccess=" + isSuccess +
                ", errorCode='" + errorCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }

    public boolean isAllSuccess() {
        return isAllSuccess;
    }

    public void setAllSuccess(boolean allSuccess) {
        isAllSuccess = allSuccess;
    }
}
