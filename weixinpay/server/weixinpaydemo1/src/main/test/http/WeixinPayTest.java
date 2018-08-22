package http;

import org.apache.commons.codec.digest.DigestUtils;
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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import javax.net.ssl.SSLContext;
import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;

/**
 * 微信支付：统一下单接口调试（用的是比较原始的方式，没有用封装API）
 * mch_id是商户号，key是商户的商户设置中的key
 *
 * 签名类型，默认MD5
 * 签名生成规则
 * 第一步：对参数按照key=value的格式，并按照参数名ASCII字典序排序
 * 第二步：拼接API密钥
 * 用MD5加密，就是签名sign
 *
 * 将参数和签名，组装成xml
 */
public class WeixinPayTest {

    @Test
    public void test1() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true).build();

        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");


//        String xmlData = "<xml><appid>wx49680a1b177c1fd7</appid><body>test</body>"
//                + "<mch_id>1494287092</mch_id>"
//                + "<nonce_str>1add1a30ac87aa2db72f57a2375d8fec</nonce_str>"
//                + "<notify_url>http://wxpay.wxutil.com/pub_v2/pay/notify.v2.php</notify_url>"
//                + "<out_trade_no>1415659990</out_trade_no>"
//                + "<spbill_create_ip>14.23.150.211</spbill_create_ip>"
//                + "<total_fee>1</total_fee>"
//                + "<trade_type>APP</trade_type>"
//                + "<sign>0CB01533B8C1EF103065174F50BCA001</sign>"
//                + "</xml>";

//        String xmlData = "<xml><appid>wx49680a1b177c1fd7</appid><body>test</body><mch_id>1509949201</mch_id><nonce_str>abc</nonce_str><spbill_create_ip>14.23.150.211</spbill_create_ip><total_fee>1</total_fee><trade_type>APP</trade_type><out_trade_no>1</out_trade_no><sign>8F8E7A69DF5EF4798D035E814CD904DB</sign></xml>";
        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?><xml><appid>wx49680a1b177c1fd7</appid><body>test</body><mch_id>1494287092</mch_id><nonce_str>abc</nonce_str><spbill_create_ip>14.23.150.211</spbill_create_ip><total_fee>1</total_fee><trade_type>APP</trade_type><out_trade_no>1</out_trade_no><sign>F0F04F9C67531B05F131C64F54E6ABD4</sign></xml>";

        httpPost.setHeader("Content-Type","application/xml");  //
        httpPost.setEntity(new StringEntity(xmlData, ContentType.create("application/xml", "utf-8")));
        HttpResponse response = client.execute(httpPost);
        System.out.println(response.toString());
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println(result);
    }

    @Test
    public void test2() throws Exception {
        SSLContext sslContext = new SSLContextBuilder()
                .loadTrustMaterial(null, (certificate, authType) -> true).build();

        CloseableHttpClient client = HttpClients.custom()
                .setSSLContext(sslContext)
                .setSSLHostnameVerifier(new NoopHostnameVerifier())
                .build();
        HttpPost httpPost = new HttpPost("https://api.mch.weixin.qq.com/pay/unifiedorder");
        String xmlData = generateXML();
        System.out.println(xmlData);

        httpPost.setHeader("Content-Type","application/xml");  //
        httpPost.setEntity(new StringEntity(xmlData, ContentType.create("application/xml", "utf-8")));
        HttpResponse response = client.execute(httpPost);
        System.out.println(response.toString());
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity, "UTF-8");
        System.out.println(result);
    }

    public String generateXML() throws Exception {
        String key = "0757cc3ee0d74ba9aae6e3d9d85a7398";
        Map<String, String> map = new TreeMap<>();
        map.put("appid", "wx49680a1b177c1fd7");
        map.put("mch_id", "1509949201");
        map.put("body", "test");
        map.put("nonce_str", "abc");
        map.put("spbill_create_ip", "14.23.150.211");
        map.put("total_fee", "1");
        map.put("notify_url", "1");
        map.put("trade_type", "APP");
        map.put("out_trade_no", "aaaaaabbbbbb");

        StringBuffer param = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            param.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        param.append("key=" + key);

        String encodeStr = DigestUtils.md5Hex(param.toString()).toUpperCase();
        map.put("sign", encodeStr);
        String result = generateXML(map);
        return result;
    }

    public String generateXML(Map<String, String> map) throws Exception {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            Element element = root.addElement(entry.getKey());
            element.setText(entry.getValue());
        }

        StringWriter sw = new StringWriter();
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("utf-8");
        XMLWriter xmlWriter = new XMLWriter(sw, format);
        xmlWriter.write(document);
        return sw.toString();
    }
}
