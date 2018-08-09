package pay;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayTradeCreateResponse;

import java.text.SimpleDateFormat;
import java.util.Date;

import static pay.Constants.*;

public class AliPayOrder {

    public static AlipayTradeCreateResponse makeOrder(AlipayTradeCreateRequest req) throws AlipayApiException {
        AlipayTradeCreateResponse resp=null;
        AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL,APP_ID, APP_PRIVATE_KEY,"json",CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
        resp =alipayClient.execute(req);
        return  resp;
    }

    public static AlipayTradeCreateRequest getTestPayRequest(){
        AlipayTradeCreateRequest  req=new AlipayTradeCreateRequest();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        String notify_url="http://xxx.xxxxxxxx.xxx/notifyServlet";
        req.setNotifyUrl(notify_url);
        JSONObject json=new JSONObject();
        json.put("out_trade_no", "999"+sdf.format(new Date()));
        json.put("total_amount", 0.01);
        json.put("subject", "THESUBJECT");
        json.put("buyer_id", "我们之前获取到的user_id");
        String jsonStr=json.toString();
        req.setBizContent(jsonStr);
        return req;
    }

    public static void main(String[] args) {
        try {
            AlipayTradeCreateResponse resp=AliPayOrder.makeOrder(AliPayOrder.getTestPayRequest());
            if(resp.isSuccess()){
                System.out.println("下单成功");
            }else{
                System.out.println("下单失败");
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }

}
