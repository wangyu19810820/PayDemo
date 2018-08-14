package servlet;

import org.apache.commons.beanutils.BeanUtils;
import org.dom4j.Document;
import pay.model.notify.WeixinpayNotifyPayModel;
import pay.model.notify.WeixinpayNotifyRefundModel;
import pay.model.notify.WeixinpayNotifyResponse;
import pay.util.WeixinpayUtil;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class WeixinpayRefundNotifyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            ServletInputStream sis = null;
            String xmlData="";
            sis=req.getInputStream();
            int size = req.getContentLength();
            if (size > 0) {
                // 用于缓存每次读取的数据
                byte[] buffer = new byte[size];
                // 用于存放结果的数组
                byte[] xmldataByte = new byte[size];
                int count = 0;
                int rbyte = 0;
                // 循环读取
                while (count < size) {
                    // 每次实际读取长度存于rbyte中
                    rbyte = sis.read(buffer);
                    for (int i = 0; i < rbyte; i++) {
                        xmldataByte[count + i] = buffer[i];
                    }
                    count += rbyte;
                }
                xmlData = new String(xmldataByte, "UTF-8");
                WeixinpayNotifyRefundModel requestModel = WeixinpayUtil.resolve(xmlData, WeixinpayNotifyRefundModel.class);
                System.out.println(requestModel);
            }
            Document doc = null;

            WeixinpayNotifyResponse response = new WeixinpayNotifyResponse();
            response.setReturn_code("SUCCESS");

            Map<String, String> map = BeanUtils.describe(response);
            String responseText = WeixinpayUtil.generateXML(map);
            resp.setContentType("application/xml;charset=UTF-8");
            PrintWriter out = resp.getWriter();
            out.println(responseText);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
