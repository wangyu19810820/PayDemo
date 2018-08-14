package pay.util;

import org.apache.commons.beanutils.BeanUtils;
import pay.exception.WeixinpayException;
import pay.model.app.WeixinpayAppPayModel;
import pay.model.request.*;
import pay.model.response.*;

import java.util.Map;

public class WeixinpayInterfaceUtil {

    // 统一下单
    public static WeixinpayUnifiedorderResponse unifiedorder(WeixinpayUnifiedorderModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        WeixinpayInterfaceUtil.initModelWithConfig(model, config);
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayUnifiedorderResponse.class,  config.getUnifiedorderUrl());
    }

    // 查询订单
    public static WeixinpayOrderqueryResponse unifiedorder(WeixinpayOrderqueryModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        WeixinpayInterfaceUtil.initModelWithConfig(model, config);
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayOrderqueryResponse.class, config.getOrderqueryUrl());
    }

    // 关闭订单
    public static WeixinpayCloseorderResponse closeorder(WeixinpayCloseorderModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        WeixinpayInterfaceUtil.initModelWithConfig(model, config);
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayCloseorderResponse.class, config.getCloseorderUrl());
    }

    // 申请退款
    public static WeixinpayRefundResponse refund(WeixinpayRefundModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        WeixinpayInterfaceUtil.initModelWithConfig(model, config);
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayRefundResponse.class, config.getRefundUrl());
    }

    // 查询退款
    public static WeixinpayRefundResponse refundquery(WeixinpayRefundqueryModel model)
            throws WeixinpayException {
        WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
        WeixinpayInterfaceUtil.initModelWithConfig(model, config);
        return WeixinpayInterfaceUtil.invoke(model, WeixinpayRefundResponse.class, config.getRefundqueryUrl());
    }

    // 为App端调用接口，准备参数
    public static WeixinpayAppPayModel generateAppParameter(String prepayid)
            throws WeixinpayException {
        try {
            WeixinpayConfiguUtil config = WeixinpayConfiguUtil.getInstance();
            WeixinpayAppPayModel model = new WeixinpayAppPayModel();
            model.setPartnerid(config.getMch_id());
            model.setPrepayid(prepayid);
            model.setNoncestr(WeixinpayUtil.generateRandomString());
            model.setPackageStr(config.getPackageStr());
            model.setTimestamp(WeixinpayUtil.generateTimestamp());

            Map<String, String> map = BeanUtils.describe(model);
            map.put("appid", config.getAppid());
            map.remove("packageStr");
            map.put("package", model.getPackageStr());
            model.setSign(WeixinpayUtil.generateSign(map, config.getKey()));
            return model;
        } catch (Exception e) {
            throw new WeixinpayException(e.getMessage());
        }
    }

    // 调用所有微信接口的统一途径
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

    // 从统一配置中初始化请求参数：appid，mch_id，key
    private static void initModelWithConfig(WeixinpanServerModel model, WeixinpayConfiguUtil config) {
        if (model.getAppid() == null || "".equals(model.getAppid())) {
            model.setAppid(config.getAppid());
        }
        if (model.getMch_id() == null || "".equals(model.getMch_id())) {
            model.setMch_id(config.getMch_id());
        }
        if (model.getKey() == null || "".equals(model.getKey())) {
            model.setKey(config.getKey());
        }
        if (model.getNonce_str() == null || "".equals(model.getNonce_str())) {
            model.setNonce_str(WeixinpayUtil.generateRandomString());
        }
    }
}
