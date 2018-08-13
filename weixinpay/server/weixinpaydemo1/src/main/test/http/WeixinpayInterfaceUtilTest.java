package http;

import org.junit.Test;
import pay.model.request.WeixinpayOrderqueryModel;
import pay.model.request.WeixinpayUnifiedorderModel;
import pay.model.response.WeixinpayOrderqueryResponse;
import pay.model.response.WeixinpayUnifiedorderResponse;
import pay.util.WeixinpayInterfaceUtil;
import pay.util.WeixinpayUnifiedorderUtil;

public class WeixinpayInterfaceUtilTest {

    @Test
    public void unifiedorderTest() throws Exception {
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

        WeixinpayUnifiedorderResponse result = WeixinpayInterfaceUtil.unifiedorder(model);
        System.out.println(result);
    }

    @Test
    public void orderqueryTest() throws Exception {
        WeixinpayOrderqueryModel model = new WeixinpayOrderqueryModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setMch_id("1494287092");
        model.setTransaction_id("1");
        model.setKey("81abddd648f64ae78d2f08696874262d");
        model.setMch_id("1494287092");
        model.setNonce_str("abc");

        WeixinpayOrderqueryResponse result = WeixinpayInterfaceUtil.unifiedorder(model);
        System.out.println(result);
    }
}
