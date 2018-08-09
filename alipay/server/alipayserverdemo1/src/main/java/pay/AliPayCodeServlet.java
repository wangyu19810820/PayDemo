package pay;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

/**
 * 发送给用户的链接，用以获取用户ID
 */
public class AliPayCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("code执行");
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String redirect_uri= URLEncoder.encode("http://l91.168.1.1/alipayUserId", "UTF-8");//这里是你获取code使用的回调地址
        StringBuffer url=new StringBuffer("https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?redirect_uri="+redirect_uri+
                "&app_id="+Constants.APP_ID+"&scope=auth_base&state=123456");
        response.sendRedirect(url.toString());
        out.flush();
        out.close();

    }
}
