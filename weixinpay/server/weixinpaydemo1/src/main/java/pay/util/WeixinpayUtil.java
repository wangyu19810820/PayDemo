package pay.util;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import pay.model.request.WeixinpayModel;

import java.io.StringWriter;
import java.util.Map;
import java.util.TreeMap;

public class WeixinpayUtil {

    public static String generateXML(WeixinpayModel model) throws Exception {
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
        System.out.println(param.toString());

        String encodeStr = DigestUtils.md5Hex(param.toString()).toUpperCase();
        map.put("sign", encodeStr);

        return generateXML(map);
    }

    private static String generateXML(Map<String, String> map) throws Exception {
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
