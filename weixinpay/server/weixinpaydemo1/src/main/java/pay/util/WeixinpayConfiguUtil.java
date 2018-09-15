package pay.util;

public class WeixinpayConfiguUtil {

    private static WeixinpayConfiguUtil instance = new WeixinpayConfiguUtil();

    // 应用ID
    private String appid;

    // 商户号
    private String mch_id;

    // key
    private String key;

    // App用到的固定参数
    private String packageStr;

    // 统一下单url
    private String unifiedorderUrl;

    // 查询订单url
    private String orderqueryUrl;

    // 关闭订单
    private String closeorderUrl;

    // 申请退款
    private String refundUrl;

    // 查询退款
    private String refundqueryUrl;

    // 支付回调地址
    private String payNotifyUrl;

    // 退款回调地址
    private String refundNotifyUrl;

    private WeixinpayConfiguUtil() {
//        this.appid = "wx49680a1b177c1fd7";
//        this.mch_id = "1494287092";
//        this.key = "81abddd648f64ae78d2f08696874262d";

        // 微信APP支付
//        this.appid = "wx49680a1b177c1fd7";
//        this.mch_id = "1509949201";
//        this.key = "0757cc3ee0d74ba9aae6e3d9d85a7398";
//        this.packageStr = "Sign=WXPay";

        // 公众号支付
        this.appid = "wxd2b107737ba0c59e";
        this.mch_id = "1494287092";
        this.key = "81abddd648f64ae78d2f08696874262d";
//        this.key = "0a5cc615744c63c51e12c822d46c0bf4";
        this.packageStr = "";

        this.unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        this.orderqueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
        this.closeorderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";
        this.refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        this.refundqueryUrl = "https://api.mch.weixin.qq.com/pay/refundquery";
        this.payNotifyUrl = "http://abc.com/pay";
        this.refundNotifyUrl = "http://abc.com/refund";
    }

    public static WeixinpayConfiguUtil getInstance() {
        return instance;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPackageStr() {
        return packageStr;
    }

    public void setPackageStr(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getUnifiedorderUrl() {
        return unifiedorderUrl;
    }

    public void setUnifiedorderUrl(String unifiedorderUrl) {
        this.unifiedorderUrl = unifiedorderUrl;
    }

    public String getOrderqueryUrl() {
        return orderqueryUrl;
    }

    public void setOrderqueryUrl(String orderqueryUrl) {
        this.orderqueryUrl = orderqueryUrl;
    }

    public String getCloseorderUrl() {
        return closeorderUrl;
    }

    public void setCloseorderUrl(String closeorderUrl) {
        this.closeorderUrl = closeorderUrl;
    }

    public String getRefundUrl() {
        return refundUrl;
    }

    public void setRefundUrl(String refundUrl) {
        this.refundUrl = refundUrl;
    }

    public String getRefundqueryUrl() {
        return refundqueryUrl;
    }

    public void setRefundqueryUrl(String refundqueryUrl) {
        this.refundqueryUrl = refundqueryUrl;
    }

    public String getPayNotifyUrl() {
        return payNotifyUrl;
    }

    public void setPayNotifyUrl(String payNotifyUrl) {
        this.payNotifyUrl = payNotifyUrl;
    }

    public String getRefundNotifyUrl() {
        return refundNotifyUrl;
    }

    public void setRefundNotifyUrl(String refundNotifyUrl) {
        this.refundNotifyUrl = refundNotifyUrl;
    }
}
