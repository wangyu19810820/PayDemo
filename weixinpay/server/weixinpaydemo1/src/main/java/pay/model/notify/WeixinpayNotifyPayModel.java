package pay.model.notify;

import pay.model.response.WeixinpaySuccessResponse;

public class WeixinpayNotifyPayModel extends WeixinpaySuccessResponse {

    // 用户标识
    private String openid;

    // 是否关注公众账号
    private String is_subscribe;

    // 交易类型
    private String trade_type;

    // 总金额
    private String total_fee;

    // 货币种类
    private String fee_type;

    // 现金支付金额
    private String cash_fee;

    // 现金支付货币类型
    private String cash_fee_type;

    // 代金券金额
    private String coupon_fee;

    // 代金券使用数量
    private String coupon_count;

    // 代金券ID
    private String coupon_id_$n;

    // 单个代金券支付金额
    private String coupon_fee_$n;

    // 微信支付订单号
    private String transaction_id;

    // 商户订单号
    private String out_trade_no;

    // 商家数据包
    private String attach;

    // 支付完成时间
    private String time_end;

    @Override
    public String toString() {
        return "WeixinpayNotifyPayModel{" +
                "openid='" + openid + '\'' +
                ", is_subscribe='" + is_subscribe + '\'' +
                ", trade_type='" + trade_type + '\'' +
                ", total_fee='" + total_fee + '\'' +
                ", fee_type='" + fee_type + '\'' +
                ", cash_fee='" + cash_fee + '\'' +
                ", cash_fee_type='" + cash_fee_type + '\'' +
                ", coupon_fee='" + coupon_fee + '\'' +
                ", coupon_count='" + coupon_count + '\'' +
                ", coupon_id_$n='" + coupon_id_$n + '\'' +
                ", coupon_fee_$n='" + coupon_fee_$n + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", out_trade_no='" + out_trade_no + '\'' +
                ", attach='" + attach + '\'' +
                ", time_end='" + time_end + '\'' +
                "} " + super.toString();
    }
}
