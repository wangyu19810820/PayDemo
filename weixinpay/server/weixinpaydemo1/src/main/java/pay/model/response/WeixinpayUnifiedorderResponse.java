package pay.model.response;

public class WeixinpayUnifiedorderResponse extends WeixinpaySuccessResponse {

    // 交易类型
    private String trade_type;

    // 预支付交易会话标识
    private String prepay_id;

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    @Override
    public String toString() {
        return "WeixinpayUnifiedorderResponse{" +
                "trade_type='" + trade_type + '\'' +
                ", prepay_id='" + prepay_id + '\'' +
                "} " + super.toString();
    }
}
