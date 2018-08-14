package http;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;
import pay.model.request.WeixinpayUnifiedorderModel;

import java.util.Map;
import java.util.TreeMap;

/**
 * Apache的BeanUtils单元测试
 * 用封装好的方式，实现反射的功能
 */
public class BeanUtilsTest {

    @Test
    public void test() throws Exception {
        WeixinpayUnifiedorderModel model = new WeixinpayUnifiedorderModel();
        model.setAppid("aaa");
        model.setBody("bbb");

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

    }
}
