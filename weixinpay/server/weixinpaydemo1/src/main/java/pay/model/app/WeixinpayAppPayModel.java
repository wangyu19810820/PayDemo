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

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getPrepayId() {
        return prepayId;
    }

    public void setPrepayId(String prepayId) {
        this.prepayId = prepayId;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "WeixinpayAppPayModel{" +
                "partnerId='" + partnerId + '\'' +
                ", prepayId='" + prepayId + '\'' +
                ", packageStr='" + packageStr + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
