package pay;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import static pay.Constants.*;

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
        String redirect_uri= URLEncoder.encode("http://191.168.3.108:8080/alipayUserId", "UTF-8");//这里是你获取code使用的回调地址
        StringBuffer url=new StringBuffer("https://openauth.alipaydev.com/oauth2/publicAppAuthorize.htm?redirect_uri="+redirect_uri+
                "&app_id="+Constants.APP_ID+"&scope=auth_base&state=123456");
        response.sendRedirect(url.toString());
        out.flush();
        out.close();

    }
}
