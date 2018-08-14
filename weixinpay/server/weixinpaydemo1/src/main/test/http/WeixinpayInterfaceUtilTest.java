package http;

import org.junit.Test;
import pay.model.app.WeixinpayAppPayModel;
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
        model.setOut_trade_no("201808141016bbb");
        model.setSpbill_create_ip("122.12.11.11");
        model.setTotal_fee("1");
        model.setTrade_type("APP");

        WeixinpayUnifiedorderResponse result = WeixinpayInterfaceUtil.unifiedorder(model);
        System.out.println(result);
    }

    @Test
    public void orderqueryTest() throws Exception {
        WeixinpayOrderqueryModel model = new WeixinpayOrderqueryModel();
        model.setOut_trade_no("201808141016aaa");
        WeixinpayOrderqueryResponse result = WeixinpayInterfaceUtil.unifiedorder(model);
        System.out.println(result);
    }

    @Test
    public void closeorderTest() throws Exception {
        WeixinpayCloseorderModel model = new WeixinpayCloseorderModel();
        model.setOut_trade_no("201808141016aaa");
        WeixinpayCloseorderResponse result = WeixinpayInterfaceUtil.closeorder(model);
        System.out.println(result);
    }

    @Test
    public void refundTest() throws Exception {
        WeixinpayRefundModel model = new WeixinpayRefundModel();
        model.setOut_trade_no("201808141016aaa");
        model.setTotal_fee("1");
        model.setRefund_fee("1");
        model.setOut_refund_no("201808141016aaarefund");
        model.setNotify_url("http://abc.com/refund");
        WeixinpayRefundResponse result = WeixinpayInterfaceUtil.refund(model);
        System.out.println(result);
    }

    @Test
    public void refundqueryTest() throws Exception {
        WeixinpayRefundqueryModel model = new WeixinpayRefundqueryModel();
        model.setOut_trade_no("201808141016aaa");
        WeixinpayRefundResponse result = WeixinpayInterfaceUtil.refundquery(model);
        System.out.println(result);
    }

    @Test
    public void generateAppParameter() throws Exception {
        WeixinpayAppPayModel model = WeixinpayInterfaceUtil.generateAppParameter("wx14101633274726c4e9653c742493372920");
        System.out.println(model);
    }
}
