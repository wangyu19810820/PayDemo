package pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayObject;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import static pay.Constants.*;

public class TradeAppPay {

    public static void main(String[] args) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(SERVER_URL,APP_ID,APP_PRIVATE_KEY,
                    "json",CHARSET,ALIPAY_PUBLIC_KEY,SIGN_TYPE);
            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            AlipayTradeAppPayModel bizModel = new AlipayTradeAppPayModel();
            bizModel.setOutTradeNo("70501111111S001111119");
            bizModel.setSubject("aa");
            bizModel.setTotalAmount("11");
            request.setBizModel(bizModel);
//            request.setBizContent("{" +
//                    "\"timeout_express\":\"90m\"," +
//                    "\"total_amount\":\"9.00\"," +
//                    "\"seller_id\":\"2088102176255782\"," +
//                    "\"product_code\":\"QUICK_MSECURITY_PAY\"," +
//                    "\"body\":\"Iphone6 16G\"," +
//                    "\"subject\":\"大乐透\"," +
//                    "\"out_trade_no\":\"70501111111S001111119\"," +
//                    "\"time_expire\":\"2016-12-31 10:05\"," +
//                    "\"goods_type\":\"0\"," +
//                    "\"promo_params\":\"{\\\"storeIdType\\\":\\\"1\\\"}\"," +
//                    "\"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
//                    "\"royalty_info\":{" +
//                    "\"royalty_type\":\"ROYALTY\"," +
//                    "        \"royalty_detail_infos\":[{" +
//                    "          \"serial_no\":1," +
//                    "\"trans_in_type\":\"userId\"," +
//                    "\"batch_no\":\"123\"," +
//                    "\"out_relation_id\":\"20131124001\"," +
//                    "\"trans_out_type\":\"userId\"," +
//                    "\"trans_out\":\"2088101126765726\"," +
//                    "\"trans_in\":\"2088101126708402\"," +
//                    "\"amount\":0.1," +
//                    "\"desc\":\"分账测试1\"," +
//                    "\"amount_percentage\":\"100\"" +
//                    "          }]" +
//                    "    }," +
//                    "\"extend_params\":{" +
//                    "\"sys_service_provider_id\":\"2088511833207846\"," +
//                    "\"hb_fq_num\":\"3\"," +
//                    "\"hb_fq_seller_percent\":\"100\"," +
//                    "\"industry_reflux_info\":\"{\\\\\\\"scene_code\\\\\\\":\\\\\\\"metro_tradeorder\\\\\\\",\\\\\\\"channel\\\\\\\":\\\\\\\"xxxx\\\\\\\",\\\\\\\"scene_data\\\\\\\":{\\\\\\\"asset_name\\\\\\\":\\\\\\\"ALIPAY\\\\\\\"}}\"," +
//                    "\"card_type\":\"S0JP0000\"" +
//                    "    }," +
//                    "\"sub_merchant\":{" +
//                    "\"merchant_id\":\"19023454\"," +
//                    "\"merchant_type\":\"alipay: 支付宝分配的间连商户编号, merchant: 商户端的间连商户编号\"" +
//                    "    }," +
//                    "\"enable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//                    "\"store_id\":\"NJ_001\"," +
//                    "\"specified_channel\":\"pcredit\"," +
//                    "\"disable_pay_channels\":\"pcredit,moneyFund,debitCardExpress\"," +
//                    "\"settle_info\":{" +
//                    "        \"settle_detail_infos\":[{" +
//                    "          \"trans_in_type\":\"cardSerialNo\"," +
//                    "\"trans_in\":\"A0001\"," +
//                    "\"summary_dimension\":\"A0001\"," +
//                    "\"amount\":0.1" +
//                    "          }]" +
//                    "    }," +
//                    "\"invoice_info\":{" +
//                    "\"key_info\":{" +
//                    "\"is_support_invoice\":true," +
//                    "\"invoice_merchant_name\":\"ABC|003\"," +
//                    "\"tax_num\":\"1464888883494\"" +
//                    "      }," +
//                    "\"details\":\"[{\\\"code\\\":\\\"100294400\\\",\\\"name\\\":\\\"服饰\\\",\\\"num\\\":\\\"2\\\",\\\"sumPrice\\\":\\\"200.00\\\",\\\"taxRate\\\":\\\"6%\\\"}]\"" +
//                    "    }," +
//                    "\"ext_user_info\":{" +
//                    "\"name\":\"李明\"," +
//                    "\"mobile\":\"16587658765\"," +
//                    "\"cert_type\":\"IDENTITY_CARD\"," +
//                    "\"cert_no\":\"362334768769238881\"," +
//                    "\"min_age\":\"18\"," +
//                    "\"fix_buyer\":\"F\"," +
//                    "\"need_check_info\":\"F\"" +
//                    "    }," +
//                    "\"business_params\":\"{\\\"data\\\":\\\"123\\\"}\"" +
//                    "  }"
//            );
            AlipayTradeAppPayResponse response = alipayClient.pageExecute(request);
            if(response.isSuccess()){
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
