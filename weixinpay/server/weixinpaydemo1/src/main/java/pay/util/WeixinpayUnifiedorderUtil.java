package pay.util;

import pay.exception.WeixinpayException;
import pay.model.request.WeixinpayUnifiedorderModel;
import pay.model.response.WeixinpayUnifiedorderResponse;

public class WeixinpayUnifiedorderUtil {

    public static WeixinpayUnifiedorderResponse invoke(WeixinpayUnifiedorderModel model) throws WeixinpayException {
        try {
            WeixinpayUnifiedorderResponse responseModel = WeixinpayUtil.<WeixinpayUnifiedorderResponse>invoke(
                    model, WeixinpayUnifiedorderResponse.class, "https://api.mch.weixin.qq.com/pay/unifiedorder");
            return responseModel;
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }
}
