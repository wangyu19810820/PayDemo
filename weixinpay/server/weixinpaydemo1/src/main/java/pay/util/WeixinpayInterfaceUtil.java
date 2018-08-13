package pay.util;

import pay.exception.WeixinpayException;
import pay.model.request.WeixinpayModel;
import pay.model.request.WeixinpayOrderqueryModel;
import pay.model.request.WeixinpayUnifiedorderModel;
import pay.model.response.WeixinpayOrderqueryResponse;
import pay.model.response.WeixinpayResponse;
import pay.model.response.WeixinpayUnifiedorderResponse;

public class WeixinpayInterfaceUtil {

    // 统一下单
    public static WeixinpayUnifiedorderResponse unifiedorder(WeixinpayUnifiedorderModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayUnifiedorderResponse.class,  config.getUnifiedorderUrl());
    }

    // 查询订单
    public static WeixinpayOrderqueryResponse unifiedorder(WeixinpayOrderqueryModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayOrderqueryResponse.class, config.getOrderqueryUrl());
    }

    private static <T extends WeixinpayModel, K extends WeixinpayResponse> K invoke(
            T requestModel, Class<K> responseClassName, String url)
            throws WeixinpayException {
        try {
            K responseModel = WeixinpayUtil.<K>invoke(requestModel, responseClassName, url);
            return responseModel;
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }
}
