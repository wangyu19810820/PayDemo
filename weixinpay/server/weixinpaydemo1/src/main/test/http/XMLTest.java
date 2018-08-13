package http;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.Iterator;

public class XMLTest {

    @Test
    public void test() throws Exception {
        String xml = "<xml><return_code><![CDATA[FAIL]]></return_code>\n" +
                "<return_msg><![CDATA[商户号该产品权限未开通，请前往商户平台>产品中心检查后重试]]></return_msg>\n" +
                "</xml>";
        Document doc = DocumentHelper.parseText(xml);
        Element rootElt = doc.getRootElement(); // 获取根节点
        System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
        Iterator iter = rootElt.elementIterator();

        while (iter.hasNext()) {
            Element recordEle = (Element) iter.next();
            System.out.println("name:" + recordEle.getName() + ",value:" + recordEle.getText());
        }
    }
}
