package pay.model.app;

public class WeixinpayAppPayModel {

    // 商户号
    private String partnerId;

    // 预支付交易会话ID，统一下单接口返回
    private String prepayId;

    // 扩展字段，暂填写固定值Sign=WXPay
    private String packageStr;

    // 随机字符串
    private String nonceStr;

    // 时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数
    private String timeStamp;

    // 签名
    private String sign;

}
