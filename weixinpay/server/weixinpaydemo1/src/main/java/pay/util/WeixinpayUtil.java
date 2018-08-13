package pay.util;

import org.apache.commons.beanutils.BeanUtils;
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
import pay.exception.WeixinpayException;
import pay.model.request.WeixinpayModel;
import pay.model.response.WeixinpayResponse;
import pay.model.response.WeixinpayUnifiedorderResponse;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class WeixinpayUtil {

    public static <T extends WeixinpayResponse> T invoke(WeixinpayModel requestModel, Class<T> responseClassName, String url)
            throws WeixinpayException {
        try {
            SSLContext sslContext = new SSLContextBuilder()
                    .loadTrustMaterial(null, (certificate, authType) -> true).build();

            CloseableHttpClient client = HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(new NoopHostnameVerifier())
                    .build();
            HttpPost httpPost = new HttpPost(url);
            String xmlData = WeixinpayUtil.generateXML(requestModel);
            System.out.println(xmlData);
            httpPost.setHeader("Content-Type", "application/xml");
            httpPost.setEntity(new StringEntity(xmlData, ContentType.create("application/xml", "utf-8")));

            HttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, "UTF-8");
            T responseModel = WeixinpayUtil.<T>resolve(result, responseClassName);
            return responseModel;
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }

    public static <T extends WeixinpayResponse> T resolve(String xml, Class<T> cls) throws WeixinpayException {
        try {
            T obj = cls.newInstance();
            Document doc = DocumentHelper.parseText(xml);
            Element rootElt = doc.getRootElement(); // 获取根节点
            Iterator iter = rootElt.elementIterator();
            Map<String, String> map = new HashMap<>();

            while (iter.hasNext()) {
                Element recordEle = (Element) iter.next();
                System.out.println("name:" + recordEle.getName() + ",value:" + recordEle.getText());
                map.put(recordEle.getName(), recordEle.getText());
            }
            BeanUtils.populate(obj, map);
            return obj;
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }

    public static String generateXML(WeixinpayModel model) throws WeixinpayException {
        try {
            Map<String, String> map = new TreeMap(BeanUtils.describe(model));
            StringBuffer param = new StringBuffer();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if ("class".equals(entry.getKey())) {
                    continue;
                }
                if ("key".equals(entry.getKey())) {
                    continue;
                }
                if ("sign".equals(entry.getKey())) {
                    continue;
                }
                param.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            param.append("key=" + model.getKey());

            String encodeStr = DigestUtils.md5Hex(param.toString()).toUpperCase();
            map.put("sign", encodeStr);

            return generateXML(map);
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }

    private static String generateXML(Map<String, String> map) throws IOException {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("xml");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if ("class".equals(entry.getKey())) {
                continue;
            }
            if ("key".equals(entry.getKey())) {
                continue;
            }
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
