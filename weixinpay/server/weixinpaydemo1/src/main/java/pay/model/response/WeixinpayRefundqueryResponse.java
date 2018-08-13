package pay.model.response;

public class WeixinpayRefundqueryResponse extends WeixinpaySuccessResponse {

    // 微信订单号
    private String transaction_id;

    // 商户订单号
    private String out_trade_no;

    // 订单总退款次数
    private String total_refund_count;

    // 订单总金额
    private String total_fee;

    // 订单金额货币种类
    private String fee_type;

    // 现金支付金额
    private String cash_fee;

    // 现金支付货币类型
    private String cash_fee_type;

    // 应结订单金额
    private String settlement_total_fee;

    // 退款笔数
    private String refund_count;

    // 商户退款单号
    private String out_refund_no_$n;

    // 微信退款单号
    private String refund_id_$n;

    // 退款渠道
    private String refund_channel_$n;

    // 退款金额
    private String refund_fee_$n;

    // 代金券退款金额
    private String coupon_refund_fee_$n;

    // 代金券使用数量
    private String coupon_refund_count_$n;

    // 代金券ID
    private String coupon_refund_id_$n_$m;

    // 代金券类型
    private String coupon_type_$n_$m;

    // 单个代金券退款金额
    private String coupon_refund_fee_$n_$m;

    // 退款状态
    private String refund_status_$n;

    // 退款资金来源
    private String refund_account_$n;

    // 退款入账账户
    private String refund_recv_accout_$n;

    // 退款成功时间
    private String refund_success_time_$n;

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTotal_refund_count() {
        return total_refund_count;
    }

    public void setTotal_refund_count(String total_refund_count) {
        this.total_refund_count = total_refund_count;
    }

    public String getTotal_fee() {
        return total_fee;
    }

    public void setTotal_fee(String total_fee) {
        this.total_fee = total_fee;
    }

    public String getFee_type() {
        return fee_type;
    }

    public void setFee_type(String fee_type) {
        this.fee_type = fee_type;
    }

    public String getCash_fee() {
        return cash_fee;
    }

    public void setCash_fee(String cash_fee) {
        this.cash_fee = cash_fee;
    }

    public String getCash_fee_type() {
        return cash_fee_type;
    }

    public void setCash_fee_type(String cash_fee_type) {
        this.cash_fee_type = cash_fee_type;
    }

    public String getSettlement_total_fee() {
        return settlement_total_fee;
    }

    public void setSettlement_total_fee(String settlement_total_fee) {
        this.settlement_total_fee = settlement_total_fee;
    }

    public String getRefund_count() {
        return refund_count;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }

    public String getOut_refund_no_$n() {
        return out_refund_no_$n;
    }

    public void setOut_refund_no_$n(String out_refund_no_$n) {
        this.out_refund_no_$n = out_refund_no_$n;
    }

    public String getRefund_id_$n() {
        return refund_id_$n;
    }

    public void setRefund_id_$n(String refund_id_$n) {
        this.refund_id_$n = refund_id_$n;
    }

    public String getRefund_channel_$n() {
        return refund_channel_$n;
    }

    public void setRefund_channel_$n(String refund_channel_$n) {
        this.refund_channel_$n = refund_channel_$n;
    }

    public String getRefund_fee_$n() {
        return refund_fee_$n;
    }

    public void setRefund_fee_$n(String refund_fee_$n) {
        this.refund_fee_$n = refund_fee_$n;
    }

    public String getCoupon_refund_fee_$n() {
        return coupon_refund_fee_$n;
    }

    public void setCoupon_refund_fee_$n(String coupon_refund_fee_$n) {
        this.coupon_refund_fee_$n = coupon_refund_fee_$n;
    }

    public String getCoupon_refund_count_$n() {
        return coupon_refund_count_$n;
    }

    public void setCoupon_refund_count_$n(String coupon_refund_count_$n) {
        this.coupon_refund_count_$n = coupon_refund_count_$n;
    }

    public String getCoupon_refund_id_$n_$m() {
        return coupon_refund_id_$n_$m;
    }

    public void setCoupon_refund_id_$n_$m(String coupon_refund_id_$n_$m) {
        this.coupon_refund_id_$n_$m = coupon_refund_id_$n_$m;
    }

    public String getCoupon_type_$n_$m() {
        return coupon_type_$n_$m;
    }

    public void setCoupon_type_$n_$m(String coupon_type_$n_$m) {
        this.coupon_type_$n_$m = coupon_type_$n_$m;
    }

    public String getCoupon_refund_fee_$n_$m() {
        return coupon_refund_fee_$n_$m;
    }

    public void setCoupon_refund_fee_$n_$m(String coupon_refund_fee_$n_$m) {
        this.coupon_refund_fee_$n_$m = coupon_refund_fee_$n_$m;
    }

    public String getRefund_status_$n() {
        return refund_status_$n;
    }

    public void setRefund_status_$n(String refund_status_$n) {
        this.refund_status_$n = refund_status_$n;
    }

    public String getRefund_account_$n() {
        return refund_account_$n;
    }

    public void setRefund_account_$n(String refund_account_$n) {
        this.refund_account_$n = refund_account_$n;
    }

    public String getRefund_recv_accout_$n() {
        return refund_recv_accout_$n;
    }

    public void setRefund_recv_accout_$n(String refund_recv_accout_$n) {
        this.refund_recv_accout_$n = refund_recv_accout_$n;
    }

    public String getRefund_success_time_$n() {
        return refund_success_time_$n;
    }

    public void setRefund_success_time_$n(String refund_success_time_$n) {
        this.refund_success_time_$n = refund_success_time_$n;
    }
}
