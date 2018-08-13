package http;

import org.junit.Test;
import pay.model.request.WeixinpayUnifiedorderModel;
import pay.util.WeixinpayUnifiedorderUtil;

public class WeixinpayUnifiedorderUtilTest {

    @Test
    public void test() throws Exception {
        WeixinpayUnifiedorderModel model = new WeixinpayUnifiedorderModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setBody("bbb");
        model.setNotify_url("http://abc.com");
        model.setOut_trade_no("1");
        model.setSpbill_create_ip("122.12.11.11");
        model.setTotal_fee("1");
        model.setTrade_type("APP");
        model.setKey("81abddd648f64ae78d2f08696874262d");
        model.setMch_id("1494287092");
        model.setNonce_str("abc");

        WeixinpayUnifiedorderUtil.invoke(model);
    }
}
