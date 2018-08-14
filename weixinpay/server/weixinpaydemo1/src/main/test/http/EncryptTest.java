package http;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public class EncryptTest {

    @Test
    public void test1() {
        String text = "appid=wx49680a1b177c1fd7&body=test&mch_id=1494287092&nonce_str=ibuaiVcKdpRxkhJA \u200BBC4C8CC2B03D7EE04E8E8A499BB2F773&key=81abddd648f64ae78d2f08696874262d";
        String encodeStr = DigestUtils.md5Hex(text).toUpperCase();
        System.out.println("MD5加密后的字符串为:"+encodeStr);
        Assert.assertEquals("5F0B79DCCAFAE3838E4CB15A37F957D6", encodeStr);
    }

    @Test
    public void test2() {
        String key = "81abddd648f64ae78d2f08696874262d";
        Map<String, String> map = new TreeMap<>();
        map.put("appid", "wx49680a1b177c1fd7");
        map.put("mch_id", "1494287092");
        map.put("body", "test");
        map.put("nonce_str", "abc");
        map.put("spbill_create_ip", "14.23.150.211");
        map.put("total_fee", "1");
        map.put("trade_type", "APP");
        map.put("out_trade_no", "1");

        StringBuffer param = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            param.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        param.append("key=" + key);

        System.out.println(param.toString());
        String encodeStr = DigestUtils.md5Hex(param.toString()).toUpperCase();
        System.out.println(encodeStr);
    }

    @Test
    public void test3() throws Exception {
        String key = "81abddd648f64ae78d2f08696874262d";
        Map<String, String> map = new TreeMap<>();
        map.put("appid", "wx49680a1b177c1fd7");
        map.put("mch_id", "1494287092");
        map.put("body", "test");
        map.put("nonce_str", "abc");
        map.put("spbill_create_ip", "14.23.150.211");
        map.put("total_fee", "1");
        map.put("trade_type", "APP");
        map.put("out_trade_no", "1");

        StringBuffer param = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            param.append(entry.getKey() + "=" + entry.getValue() + "&");
        }
        param.append("key=" + key);

        System.out.println(param.toString());
        String encodeStr = DigestUtils.md5Hex(param.toString()).toUpperCase();
        System.out.println(encodeStr);
        map.put("sign", encodeStr);
        String result = generateXML(map);
        System.out.println(result);
    }

    @Test
    public void test4() throws Exception {
        Map<String, String> map = new TreeMap<>();
        map.put("appid", "wx49680a1b177c1fd7");
        map.put("mch_id", "1494287092");
        map.put("body", "test");
        map.put("nonce_str", "abc");
        map.put("spbill_create_ip", "14.23.150.211");
        map.put("total_fee", "1");
        map.put("trade_type", "APP");
        map.put("out_trade_no", "1");
        String result = generateXML(map);
        System.out.println(result);
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

    @Test
    public void test11() {
        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
    }
}
