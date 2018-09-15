package servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import https.HttpClientUtil;
import org.apache.commons.beanutils.BeanUtils;
import pay.exception.WeixinpayException;
import pay.model.request.WeixinpayGzhModel;
import pay.model.request.WeixinpayUnifiedorderModel;
import pay.model.response.WeixinpayUnifiedorderResponse;
import pay.util.WeixinpayConfiguUtil;
import pay.util.WeixinpayInterfaceUtil;
import pay.util.WeixinpayUtil;
import sdk.WXPayUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

public class GzhServlet  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ip = req.getParameter("ip");
        System.out.println(ip);
        String code = req.getParameter("code");
        String getOpenidUrl = String.format("https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxd2b107737ba0c59e&secret=0a5cc615744c63c51e12c822d46c0bf4&code=%s&grant_type=authorization_code", code);
        String openidResult = HttpClientUtil.doGet(getOpenidUrl);
        System.out.println(openidResult);
        JSONObject jsonObject = JSONObject.parseObject(openidResult);
        String openid = String.valueOf(jsonObject.get("openid"));
        System.out.println(openid);

        WeixinpayUnifiedorderModel model = new WeixinpayUnifiedorderModel();
        model.setAppid("wxd2b107737ba0c59e");
        model.setBody("bbb");
        model.setNotify_url("http://121.43.170.199/recall");
        model.setOut_trade_no(UUID.randomUUID().toString().replace("-", ""));
        model.setSpbill_create_ip(ip);
        model.setTotal_fee("1");
        model.setTrade_type("JSAPI");
        model.setOpenid(openid);

        try {
            WeixinpayUnifiedorderResponse result = WeixinpayInterfaceUtil.unifiedorder(model);
            System.out.println(result);

            WeixinpayGzhModel gzhModel = new WeixinpayGzhModel();
            gzhModel.setAppId("wxd2b107737ba0c59e");
            gzhModel.setNonceStr(WXPayUtil.generateNonceStr());
            gzhModel.setPackageStr("prepay_id=" + result.getPrepay_id());
            gzhModel.setSignType("MD5");
            gzhModel.setTimeStamp(("" + new Date().getTime()).substring(0, 10));
            Map<String, String> map = BeanUtils.describe(gzhModel);
            map.remove("packageStr");
            map.put("package", gzhModel.getPackageStr());
            WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
            String sign = WeixinpayUtil.generateSign(map, config.getKey());
            System.out.println(sign);

            //
            map.put("paySign", sign);
            map.put("outTradeNo", model.getOut_trade_no());
            String jsonObjectResult = JSON.toJSONString(map);

            resp.getOutputStream().println(jsonObjectResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
