package pay.model.request;

/**
 * 微信支付接口模型基类
 */
public class WeixinpanServerModel extends WeixinpayModel {

    // 商户号
    private String mch_id;

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }
}
