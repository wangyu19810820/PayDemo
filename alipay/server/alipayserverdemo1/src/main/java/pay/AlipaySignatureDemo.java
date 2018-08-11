package pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;

public class AlipaySignatureDemo {

    public static void main(String[] args) {
        String c1 = "app_id=2018070560549460&biz_content={\"seller_id\":\"\",\"total_amount\":\"0.01\",\"subject\":\"1\",\"out_trade_no\":\"S6RE1P8R48R5V11\"," +
                "\"product_code\":\"QUICK_MSECURITY_PAY\"}&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2018-08-10 10:15:00&version=1.0";
//        String c2 = "app_id=2018070560549460&biz_content={\"seller_id\"%3A\"\"%2C\"total_amount\"%3A\"0.01\"%2C\"subject\"%3A\"1\"%2C\"out_trade_no\"%3A\"S6RE1P8R48R5V10\"%2C\"product_code\"%3A\"QUICK_MSECURITY_PAY\"}&charset=utf-8&method=alipay.trade.app.pay&sign_type=RSA2&timestamp=2018-08-10 09%3A26%3A08&version=1.0";

        try {
            String result1 = AlipaySignature.rsa256Sign(c1, Constants.APP_PRIVATE_KEY, Constants.CHARSET);
            System.out.println(result1);
            String result12 = java.net.URLEncoder.encode(result1, Constants.CHARSET);
            System.out.println(result12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
