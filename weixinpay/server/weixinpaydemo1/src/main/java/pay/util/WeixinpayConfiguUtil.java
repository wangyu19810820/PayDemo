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

    private WeixinpayConfiguUtil() {
        this.appid = "wx49680a1b177c1fd7";
        this.mch_id = "1494287092";
        this.key = "81abddd648f64ae78d2f08696874262d";
        this.unifiedorderUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        this.orderqueryUrl = "https://api.mch.weixin.qq.com/pay/orderquery";
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
}
