package pay.model.request;

/**
 * 微信支付接口模型基类
 */
public class WeixinpayModel {

    // 应用ID
    private String appid;

    // 商户的Key
    private String key;

//    // 商户号
//    private static String partnerid;

    // 随机字符串，用于签名
    private String nonce_str;

    // 签名
    private String sign;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
