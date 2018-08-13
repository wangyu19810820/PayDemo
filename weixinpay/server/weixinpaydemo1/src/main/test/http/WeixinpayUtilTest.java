package http;

import org.junit.Test;
import pay.model.request.WeixinpayUnifiedorderModel;
import pay.model.response.WeixinpayResponse;
import pay.util.WeixinpayUtil;

public class WeixinpayUtilTest {

    @Test
    public void test1() throws Exception {
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

        String xml = WeixinpayUtil.generateXML(model);
        System.out.println(xml);
    }

    @Test
    public void test2() throws Exception {
        String xml = "<xml><return_code><![CDATA[FAIL]]></return_code>\n" +
                "<return_msg><![CDATA[商户号该产品权限未开通，请前往商户平台>产品中心检查后重试]]></return_msg>\n" +
                "</xml>";
        WeixinpayResponse obj = WeixinpayUtil.<WeixinpayResponse>resolve(xml, WeixinpayResponse.class);
        System.out.println(obj);
    }
}
