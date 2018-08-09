package pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

public class AliPayUserIdServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("回调成功");
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String auth_code="";
        auth_code=request.getParameter("auth_code");
        AlipayClient client= alipayClient();
        AlipaySystemOauthTokenRequest req = new AlipaySystemOauthTokenRequest();
        req.setCode(auth_code);
        req.setGrantType("authorization_code");
        String userId="";

        try {
            AlipaySystemOauthTokenResponse asotr=client.execute(req);
            userId+= asotr.getUserId();//注意这里请用getUserID的方法获得，AlipaySystemOauthTokenResponse 还有个方法getAlipayUserId获得到的就是32位的user_id
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private DefaultAlipayClient alipayClient(){
        DefaultAlipayClient alipayclient=new DefaultAlipayClient("https://openapi.alipay.com/gateway.do","你的appid",   "你的公钥","json", "GBK",
                "你的私钥","你私钥的加密方式");
        return alipayclient;
    }

}
