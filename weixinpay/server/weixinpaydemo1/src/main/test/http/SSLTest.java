package http;

import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.net.ssl.SSLContext;

import static org.junit.Assert.assertThat;

public class SSLTest {

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

    @Test
    public void test3() throws Exception {
        String jsonParams = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://..........................");
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
            return String.valueOf(response.getStatusLine().getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
            return "post failure :caused by-->" + e.getMessage().toString();
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

}
