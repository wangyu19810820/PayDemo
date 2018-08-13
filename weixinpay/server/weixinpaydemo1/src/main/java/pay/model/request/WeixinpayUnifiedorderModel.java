package pay.model.request;

// 统一下单
public class WeixinpayUnifiedorderModel extends WeixinpanServerModel {

    // 商户订单号
    private String out_trade_no;

    // 商品描述
    private String body;

    // 终端IP
    private String spbill_create_ip;

    // 总金额,单位为分
    private String total_fee;

    // 通知地址,接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
    private String notify_url;

    // 交易类型,值为APP
    private String trade_type;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSpbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getNotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
}
