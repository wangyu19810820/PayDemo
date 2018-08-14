package http;

import org.junit.Test;
import pay.model.request.WeixinpayUnifiedorderModel;
import pay.model.response.WeixinpayResponse;
import pay.util.WeixinpayConfiguUtil;
import pay.util.WeixinpayUtil;

/**
 * 微信支付封装API的单元测试
 *
 */
public class WeixinpayUtilTest {

    // 测试将Bean转成XML
    @Test
    public void test1() throws Exception {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        WeixinpayUnifiedorderModel model = new WeixinpayUnifiedorderModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setBody("bbb");
        model.setNotify_url("http://abc.com");
        model.setOut_trade_no("1");
        model.setSpbill_create_ip("122.12.11.11");
        model.setTotal_fee("1");
        model.setTrade_type("APP");
        model.setKey(config.getKey());
        model.setMch_id(config.getMch_id());
        model.setNonce_str("abc");

        String xml = WeixinpayUtil.generateXML(model);
        System.out.println(xml);
    }

    // 测试将XML转换成Bean
    @Test
    public void test2() throws Exception {
        String xml = "<xml><return_code><![CDATA[FAIL]]></return_code>\n" +
                "<return_msg><![CDATA[商户号该产品权限未开通，请前往商户平台>产品中心检查后重试]]></return_msg>\n" +
                "</xml>";
        WeixinpayResponse obj = WeixinpayUtil.<WeixinpayResponse>resolve(xml, WeixinpayResponse.class);
        System.out.println(obj);
    }
}
