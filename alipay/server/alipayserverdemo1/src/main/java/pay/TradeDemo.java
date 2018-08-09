package pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import static pay.Constants.*;

public class TradeDemo {

    public static void main(String[] args) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL,
                    APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();//创建API对应的request类
            request.setBizContent("{" +
                    "   \"out_trade_no\":\"20150320010101001\"," +
                    "   \"trade_no\":\"2014112611001004680073956707\"" +
                    "  }");//设置业务参数
            AlipayTradeQueryResponse response = alipayClient.execute(request);//通过alipayClient调用API，获得对应的response类
            System.out.print(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
