package pay.model.app;

public class WeixinpayAppPayModel {

    // 商户号
    private String partnerid;

    // 预支付交易会话ID，统一下单接口返回
    private String prepayid;

    // 扩展字段，暂填写固定值Sign=WXPay
    private String packageStr;

    // 随机字符串
    private String noncestr;

    // 时间戳，标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数
    private String timestamp;

    // 签名
    private String sign;

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
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
                "partnerid='" + partnerid + '\'' +
                ", prepayid='" + prepayid + '\'' +
                ", packageStr='" + packageStr + '\'' +
                ", noncestr='" + noncestr + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", sign='" + sign + '\'' +
                '}';
    }
}
