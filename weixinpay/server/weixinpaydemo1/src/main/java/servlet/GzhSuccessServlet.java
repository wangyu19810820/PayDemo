package servlet;

import pay.exception.WeixinpayException;
import pay.model.request.WeixinpayOrderqueryModel;
import pay.model.response.WeixinpayOrderqueryResponse;
import pay.util.WeixinpayInterfaceUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GzhSuccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String outTradeNo = req.getParameter("outTradeNo");
        System.out.println(outTradeNo);

        WeixinpayOrderqueryModel model = new WeixinpayOrderqueryModel();
        model.setOut_trade_no(outTradeNo);
        WeixinpayOrderqueryResponse result = null;
        try {
            result = WeixinpayInterfaceUtil.unifiedorder(model);
        } catch (WeixinpayException e) {
            e.printStackTrace();
        }
        System.out.println(result);
    }
}
