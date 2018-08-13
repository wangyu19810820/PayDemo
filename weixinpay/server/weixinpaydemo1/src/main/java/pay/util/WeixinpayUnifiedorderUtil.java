package pay.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import pay.exception.WeixinpayException;
import pay.model.request.WeixinpayUnifiedorderModel;

import javax.net.ssl.SSLContext;

public class WeixinpayUnifiedorderUtil {

    public static String invoke(WeixinpayUnifiedorderModel model) throws WeixinpayException {
        try {
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (certificate, authType) -> true).build();

            CloseableHttpClient client = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
            HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String xmlData = WeixinpayUtil.generateXML(model);
            System.out.println(xmlData);

            httpPost.setHeader("Content-Type", "application/xml");  //
            httpPost.setEntity(new StringEntity(xmlData, ContentType.create("application/xml", "utf-8")));
            HttpResponse response = client.execute(httpPost);
            System.out.println(response.toString());
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            System.out.println(result);

            return "";
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }
}
