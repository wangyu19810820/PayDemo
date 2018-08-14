package http;

import org.junit.Test;
import pay.model.request.*;
import pay.model.response.WeixinpayCloseorderResponse;
import pay.model.response.WeixinpayOrderqueryResponse;
import pay.model.response.WeixinpayRefundResponse;
import pay.model.response.WeixinpayUnifiedorderResponse;
import pay.util.WeixinpayInterfaceUtil;

public class WeixinpayInterfaceUtilTest {

    @Test
    public void unifiedorderTest() throws Exception {
        WeixinpayUnifiedorderModel model = new WeixinpayUnifiedorderModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setBody("bbb");
        model.setNotify_url("http://abc.com");
        model.setOut_trade_no("201808141016aaa");
        model.setSpbill_create_ip("122.12.11.11");
        model.setTotal_fee("1");
        model.setTrade_type("APP");
        model.setKey("0757cc3ee0d74ba9aae6e3d9d85a7398");
        model.setMch_id("1509949201");
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

    @Test
    public void closeorderTest() throws Exception {
        WeixinpayCloseorderModel model = new WeixinpayCloseorderModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setMch_id("1494287092");
        model.setOut_trade_no("1");
        model.setKey("81abddd648f64ae78d2f08696874262d");
        model.setMch_id("1494287092");
        model.setNonce_str("abc");

        WeixinpayCloseorderResponse result = WeixinpayInterfaceUtil.closeorder(model);
        System.out.println(result);
    }

    @Test
    public void refundTest() throws Exception {
        WeixinpayRefundModel model = new WeixinpayRefundModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setMch_id("1494287092");
        model.setOut_trade_no("1");
        model.setKey("81abddd648f64ae78d2f08696874262d");
        model.setMch_id("1494287092");
        model.setNonce_str("abc");
        model.setTotal_fee("1");
        model.setRefund_fee("1");

        WeixinpayRefundResponse result = WeixinpayInterfaceUtil.refund(model);
        System.out.println(result);
    }

    @Test
    public void refundqueryTest() throws Exception {
        WeixinpayRefundqueryModel model = new WeixinpayRefundqueryModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setMch_id("1494287092");
        model.setKey("81abddd648f64ae78d2f08696874262d");
        model.setMch_id("1494287092");
        model.setNonce_str("abc");
        model.setOut_trade_no("1");

        WeixinpayRefundResponse result = WeixinpayInterfaceUtil.refundquery(model);
        System.out.println(result);
    }
}
