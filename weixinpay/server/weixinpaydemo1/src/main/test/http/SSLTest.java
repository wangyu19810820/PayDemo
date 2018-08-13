package http;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.net.ssl.SSLContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertThat;

public class SSLTest {

    // https的get请求
    @Test
    public void test1() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true).build();

        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpGet httpGet = new HttpGet("https://www.baidu.com/");
        httpGet.setHeader("Accept", "text/html");

        HttpResponse response = client.execute(httpGet);
        System.out.println(response.getStatusLine().getStatusCode());
        String srtResult = EntityUtils.toString(response.getEntity());//获得返回的结果
        System.out.println(srtResult);
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
    }

    // https的post请求
    @Test
    public void test2() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true).build();

        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpPost httpPost = new HttpPost("https://www.baidu.com/");
        httpPost.setHeader("Accept", "text/html");

        HttpResponse response = client.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());
        String srtResult = EntityUtils.toString(response.getEntity());//获得返回的结果
        System.out.println(srtResult);
//        assertThat(response.getStatusLine().getStatusCode(), equalTo(200));
    }

    // 发送json数据
    @Test
    public void test3() throws Exception {
        String jsonParams = "{a:\"aa\"}";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/DemoServlet");
        RequestConfig requestConfig = RequestConfig.custom().
                setConnectTimeout(180 * 1000).setConnectionRequestTimeout(180 * 1000)
                .setSocketTimeout(180 * 1000).setRedirectsEnabled(true).build();

        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json");  //
        try {
            httpPost.setEntity(new StringEntity(jsonParams, ContentType.create("application/json", "utf-8")));
            System.out.println("request parameters" + EntityUtils.toString(httpPost.getEntity()));
            HttpResponse response = httpClient.execute(httpPost);
            System.out.println(" code:"+response.getStatusLine().getStatusCode());
            System.out.println("doPostForInfobipUnsub response"+response.getStatusLine().toString());
            System.out.println(response.getStatusLine().getStatusCode());
//            return String.valueOf(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 发送xml数据
    @Test
    public void test4() throws Exception {
//        String xmlString = "<?xml version=\"1.0\"?><abc><h1>abc</h1></abc>";

//        String xmlString = "<html>" + "<head>" + "<title>dom4j解析一个例子</title>"
//                + "<script>" + "<username>yangrong</username>"
//                + "<password>123456</password>" + "</script>" + "</head>"
//                + "<body>" + "<result>0</result>" + "<form>"
//                + "<banlce>1000</banlce>" + "<subID>36242519880716</subID>"
//                + "</form>" + "</body>" + "</html>";

        String xmlString = "<?xml version=\"1.0\"?><root><appid>wx2421b1c4370ec43b</appid><attach>支付测试</attach><body>APP支付测试</body>"
                + "<mch_id>10000100</mch_id>"
                + "<nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>"
                + "<notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>"
                + "<out_trade_no>1415659990</out_trade_no>"
                + "<spbill_create_ip>14.23.150.211</spbill_create_ip>"
                + "<total_fee>1</total_fee>"
                + "<trade_type>APP</trade_type>"
                + "<sign>0CB01533B8C1EF103065174F50BCA001</sign>"
                + "</root>";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8080/DemoServlet");
        post.setHeader("Content-Type","application/xml");  //
        post.setEntity(new StringEntity(xmlString, ContentType.create("application/xml", "utf-8")));
        HttpResponse response = client.execute(post);
        System.out.println(response.toString());
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println(result);
    }

    // https发送xml
    @Test
    public void test5() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true).build();

        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpPost httpPost = new HttpPost("https://www.baidu.com/");


        String xmlData = "<?xml version=\"1.0\"?><body><h1>abc</h1></body>";
//        CloseableHttpClient client = HttpClients.createDefault();

        httpPost.setHeader("Content-Type","application/xml");  //
        httpPost.setEntity(new StringEntity(xmlData, ContentType.create("application/xml", "utf-8")));
        HttpResponse response = client.execute(httpPost);
        System.out.println(response.toString());
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println(result);
    }

    @Test
    public void uuidTest() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        // 81abddd648f64ae78d2f08696874262d
    }
}
