package pay.model.request;

public class WeixinpayRefundqueryModel extends WeixinpanServerModel {

    // 微信订单号，transaction_id、out_trade_no、out_refund_no、refund_id四选一
//    private String transaction_id;

    // 商户订单号
    private String out_trade_no;
//
//    // 商户退款单号
//    private String out_refund_no;
//
//    // 微信退款单号
//    private String refund_id;


    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
