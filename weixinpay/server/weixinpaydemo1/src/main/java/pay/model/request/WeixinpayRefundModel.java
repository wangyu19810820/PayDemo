package pay.model.request;

public class WeixinpayRefundModel extends WeixinpanServerModel {

//    // 微信生成的订单号，在支付通知中有返回
//    private String transaction_id;

    // 商户订单号, 与transaction_id二选一
    private String out_trade_no;

    // 商户退款单号，商户系统内部唯一，同一退款单号多次请求只退一笔
    private String out_refund_no;

    // 订单金额，单位为分，只能为整数
    private String total_fee;

    // 退款金额，单位为分，只能为整数
    private String refund_fee;

    // 退款结果通知url，如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效
    private String notify_url;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getOut_refund_no() {
        return out_refund_no;
    }

    public void setOut_refund_no(String out_refund_no) {
        this.out_refund_no = out_refund_no;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getRefund_fee() {
        return refund_fee;
    }

    public void setRefund_fee(String refund_fee) {
        this.refund_fee = refund_fee;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
}
