package pay.util;

public class WeixinpayConfiguUtil {

    private static WeixinpayConfiguUtil instance = new WeixinpayConfiguUtil();

    // 应用ID
    private String appid;

    // 商户号
    private String mch_id;

    // key
    private String key;

    // 统一下单url
    private String unifiedorderUrl;

    // 查询订单url
    private String orderqueryUrl;

    // 关闭订单
    private String closeorderUrl;

    // 申请退款
    private String refundUrl;

    // 查询退款
    private String refundquery;

    private WeixinpayConfiguUtil() {
        this.appid = "wx49680a1b177c1fd7";
        this.mch_id = "1494287092";
        this.key = "81abddd648f64ae78d2f08696874262d";
        this.unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        this.orderqueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
        this.closeorderUrl = "https://api.mch.weixin.qq.com/pay/closeorder";
        this.refundUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        this.refundquery = "https://api.mch.weixin.qq.com/pay/refundquery";
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

    public String getRefundquery() {
        return refundquery;
    }

    public void setRefundquery(String refundquery) {
        this.refundquery = refundquery;
    }
}
