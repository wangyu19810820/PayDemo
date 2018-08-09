package pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;

import static pay.Constants.*;

/**
 *  预创建交易，
 *  传送交易号，商品名，价格，
 *  返回二维码编码
 */
public class TradePrecreate {

    public static void main(String[] args) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
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
