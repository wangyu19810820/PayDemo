package pay.model.request;

public class WeixinpayOrderqueryModel extends WeixinpanServerModel {

    // 商户订单号
    private String out_trade_no;

//    // 微信订单号, 与out_trade_no二选一，微信文档推荐用transaction_id
//    private String transaction_id;

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }


//    public String getTransaction_id() {
//        return transaction_id;
//    }
//
//    public void setTransaction_id(String transaction_id) {
//        this.transaction_id = transaction_id;
//    }
}
