package pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;

import static pay.Constants.*;

public class TradeRefundQuery {

    public static void main(String[] args) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL,
                    APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
            request.setBizContent("{" +
//                    "\"trade_no\":\"20150320010101001\"," +
                    "\"out_trade_no\":\"S6RE1P8R48R5V10\"," +
                    "\"out_request_no\":\"S6RE1P8R48R5V10_refund\"" +
                    "  }");
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
