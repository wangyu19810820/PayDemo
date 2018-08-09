package pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;

import static pay.Constants.*;

public class TradePrecreate {

    public static void main(String[] args) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            AlipayTradePrecreateModel model = new AlipayTradePrecreateModel();
            request.setBizModel(model);
            model.setOutTradeNo(String.valueOf(System.currentTimeMillis()));
            model.setTotalAmount("88.88");
            model.setSubject("Iphone6 16G");
            AlipayTradePrecreateResponse response = alipayClient.execute(request);
            System.out.print(response.getBody());
            System.out.print(response.getQrCode());
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }
}
