package pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;

import static pay.Constants.*;

public class BillDownload {

    public static void main(String[] args) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL,APP_ID,APP_PRIVATE_KEY,
                    "json",CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
            AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
            request.setBizContent("{" +
                    "\"bill_type\":\"trade\"," +
                    "\"bill_date\":\"2018-08-09\"" +
                    "  }");
            AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
