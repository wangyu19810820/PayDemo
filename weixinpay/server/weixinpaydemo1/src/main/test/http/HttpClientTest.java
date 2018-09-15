package http;

import https.HttpClientUtil;
import org.junit.Test;

public class HttpClientTest {

    @Test
    public void testGet() {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd2b107737ba0c59e&secret=0a5cc615744c63c51e12c822d46c0bf4&code=061xjIyL11VJZ519KtyL1sPHyL1xjIyo&grant_type=authorization_code";
        String result = HttpClientUtil.doGet(url);
        System.out.println(result);
    }
}
