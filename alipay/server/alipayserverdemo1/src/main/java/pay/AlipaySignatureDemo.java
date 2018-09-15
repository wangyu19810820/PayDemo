package pay;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

public class AlipaySignatureDemo {

    public static void main(String[] args) throws Exception {
//        // 经测试下来，这些参数无法省略
        String c1 = "app_id=2018070560549460&biz_content={\"total_amount\":\"0.02\",\"subject\":\"回锅肉1\",\"out_trade_no\":\"125378966625190915\"," +
                "\"product_code\":\"aa\"}&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2018-09-13 12:30:00&version=1.0";
        c1 = "app_id=2018070560549460&biz_content={\"product_code\":\"QUICK_MSECURITY_PAY\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"body\":\"我是测试数据\",\"out_trade_no\":\"125378966625190915\"}&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2018-09-14 10:00:00&version=1.0";
        System.out.println(c1);

        try {
            String oriSign = SignUtils.sign(c1, Constants.APP_PRIVATE_KEY, true);
            System.out.println(oriSign);
            String result1 = AlipaySignature.rsa256Sign(c1, Constants.APP_PRIVATE_KEY, Constants.CHARSET);
            System.out.println(result1);
            String result12 = java.net.URLEncoder.encode(result1, Constants.CHARSET);
            System.out.println(result12);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        String signature = AlipaySignatureDemo.signature("0.02", "回锅肉1", "125378966625190915");
//        System.out.println(signature);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("signature", signature);
//        jsonObject.put("a", "abc");
//        System.out.println(JSON.toJSON(jsonObject));
    }

    public static String signature(String totalAmount, String subject, String outTradeNo) throws Exception {
        // 经测试下来，这些参数无法省略
//        String c1 = "app_id=2018070560549460&biz_content={\"seller_id\":\"\",\"total_amount\":\"0.01\",\"subject\":\"abcdefghijklmn\",\"out_trade_no\":\"S6RE1P8R48R5V15\"," +
//                "\"product_code\":\"QUICK_MSECURITY_PAY\"}&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2018-08-10 10:15:00&version=1.0";
        String result = String.format("app_id=2018070560549460&biz_content={\"total_amount\":\"%s\",\"subject\":\"%s\",\"out_trade_no\":\"%s\"," +
                "\"product_code\":\"aa\"}&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2018-09-13 12:30:00&version=1.0", totalAmount, subject, outTradeNo);
        String result1 = AlipaySignature.rsa256Sign(result, Constants.APP_PRIVATE_KEY, Constants.CHARSET);
        System.out.println(result1);
        String result12 = java.net.URLEncoder.encode(result1, Constants.CHARSET);
        System.out.println(result12);
        return result12;
    }
}
