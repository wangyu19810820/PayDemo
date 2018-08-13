package pay.util;

import pay.exception.WeixinpayException;
import pay.model.request.*;
import pay.model.response.*;

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

    // 关闭订单
    public static WeixinpayCloseorderResponse closeorder(WeixinpayCloseorderModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayCloseorderResponse.class, config.getCloseorderUrl());
    }

    // 申请退款
    public static WeixinpayRefundResponse refund(WeixinpayRefundModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayRefundResponse.class, config.getRefundUrl());
    }

    // 查询退款
    public static WeixinpayRefundResponse refundquery(WeixinpayRefundqueryModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayRefundResponse.class, config.getRefundquery());
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
