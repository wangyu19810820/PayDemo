package pay.notify;

import org.apache.commons.beanutils.BeanUtils;
import pay.exception.WeixinpayException;
import pay.model.notify.WeixinpayNotifyPayModel;
import pay.model.notify.WeixinpayNotifyRefundModel;
import pay.model.notify.WeixinpayNotifyResponse;
import pay.util.WeixinpayUtil;

import java.util.Map;

// 微信回调工具类
public class WeixinpayNotify {

    // 支付回调，解析并返回数据
    public static WeixinpayNotifyPayModel pay(String xml) throws WeixinpayException {
        WeixinpayNotifyPayModel requestModel = WeixinpayUtil.resolve(xml, WeixinpayNotifyPayModel.class);
        return requestModel;
    }

    // 退款回调，解析并返回数据
    public static WeixinpayNotifyRefundModel refund(String xml) throws WeixinpayException {
        WeixinpayNotifyRefundModel requestModel = WeixinpayUtil.resolve(xml, WeixinpayNotifyRefundModel.class);
        return requestModel;
    }

    // 微信回调过程中，操作成功后，返回给微信的数据
    public static String responseSuccess() throws WeixinpayException {
        try {
            WeixinpayNotifyResponse response = new WeixinpayNotifyResponse();
            response.setReturn_code("SUCCESS");

            Map<String, String> map = BeanUtils.describe(response);
            String responseText = WeixinpayUtil.generateXML(map);
            return responseText;
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }

    // 微信回调过程中，操作失败后，返回给微信的数据
    public static String responseFail(String reason) throws WeixinpayException {
        try {
            WeixinpayNotifyResponse response = new WeixinpayNotifyResponse();
            response.setReturn_code("FAIL");
            response.setReturn_msg(reason);

            Map<String, String> map = BeanUtils.describe(response);
            String responseText = WeixinpayUtil.generateXML(map);
            return responseText;
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }
}
