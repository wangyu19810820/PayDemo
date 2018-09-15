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

    // 公众号支付，统一下单接口，
    // 和APP支付不同点：appid是公众号，spbill_create_ip是用户端ip，trade_type是JSAPI
    @Test
    public void unifiedorderGZHTest() throws Exception {
        WeixinpayUnifiedorderModel model = new WeixinpayUnifiedorderModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setBody("bbb");
        model.setNotify_url("http://abc.com");
        model.setOut_trade_no("201808141016dddd");
        model.setSpbill_create_ip("122.12.11.11");
        model.setTotal_fee("1");
        model.setTrade_type("JSAPI");

        WeixinpayUnifiedorderResponse result = WeixinpayInterfaceUtil.unifiedorder(model);
        System.out.println(result);
    }

    // 统一下单接口
    @Test
    public void unifiedorderTest() throws Exception {
        WeixinpayUnifiedorderModel model = new WeixinpayUnifiedorderModel();
        model.setAppid("wx49680a1b177c1fd7");
        model.setBody("bbb");
        model.setNotify_url("http://abc.com");
        model.setOut_trade_no("201808141016dddd");
        model.setSpbill_create_ip("122.12.11.11");
        model.setTotal_fee("1");
        model.setTrade_type("APP");

        WeixinpayUnifiedorderResponse result = WeixinpayInterfaceUtil.unifiedorder(model);
        System.out.println(result);
    }

    // 订单查询，订单查询。微信推荐：支付成功后，不要以客户端数据为准，而要以微信服务器回调或者查询为准。
    @Test
    public void orderqueryTest() throws Exception {
        WeixinpayOrderqueryModel model = new WeixinpayOrderqueryModel();
        model.setOut_trade_no("201808141016dddd");
        WeixinpayOrderqueryResponse result = WeixinpayInterfaceUtil.unifiedorder(model);
        System.out.println(result);
    }

    // 关闭订单
    @Test
    public void closeorderTest() throws Exception {
        WeixinpayCloseorderModel model = new WeixinpayCloseorderModel();
        model.setOut_trade_no("201808141016aaa");
        WeixinpayCloseorderResponse result = WeixinpayInterfaceUtil.closeorder(model);
        System.out.println(result);
    }

    // 申请退款
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

    // 申请退款查询
    @Test
    public void refundqueryTest() throws Exception {
        WeixinpayRefundqueryModel model = new WeixinpayRefundqueryModel();
        model.setOut_trade_no("201808141016aaa");
        WeixinpayRefundResponse result = WeixinpayInterfaceUtil.refundquery(model);
        System.out.println(result);
    }

    // 为App准备参数。先调用统一下单接口，获取预支付id，再用此工具重新签名，连同其他数据，一并返回给App使用
    @Test
    public void generateAppParameter() throws Exception {
        WeixinpayAppPayModel model = WeixinpayInterfaceUtil.generateAppParameter("wx222222449241569f7d1881170438505732");
        System.out.println(model);
    }
}
