package pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.response.AlipayTradeCreateResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

// 网上Demo，创建交易，其实单独调用AliPayOrder的main方法，也能完成相同的测试
public class AliPayOrderServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {

            AlipayTradeCreateResponse resp=AliPayOrder.makeOrder(AliPayOrder.getTestPayRequest());
            if(resp.isSuccess()){
                System.out.println("下单成功");
                out.write(resp.getBody());
            }else{
                System.out.println("下单失败");//如果下单失败阿里的sdk会自己打印错误日志到控制台
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        out.flush();
        out.close();
    }

}
