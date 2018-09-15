<!doctype html>
<%@ page import="java.util.Enumeration" pageEncoding="UTF-8" %>
<html lang="zh">
    <head>
        <meta charset="UTF-8">
        <script src="jquery-3.3.1.min.js" type="application/javascript"></script>
        <script>
            function payBtnClick() {
                // alert(navigator.userAgent);
                // var index = navigator.userAgent.indexOf("MicroMessenger/");
                // if (index == -1) {
                //     alert('您使用的不是微信浏览器')
                //     return;
                // }
                // var weixinVersion = navigator.userAgent.substr(index + "MicroMessenger/".length, 1);
                // if (parseInt(weixinVersion) < 5) {
                //     alert('您使用的微信版本小于5，不支持微信的公众号支付')
                //     return
                // }

                // $.post('/GzhServlet', {
                //     'ip':ip,
                //
                // },
                // function (result) {
                //     alert(result);
                //     onBridgeReady(result);
                // });
                // $.get(
                //     'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2b107737ba0c59e&redirect_uri=http%3a%2f%2fwww.smallpai.com%2fpiPaiCampus%2fGzhServlet&response_type=code&scope=snsapi_base&state=123#wechat_redirect',
                //     function (result) {
                //         alert(result);
                //     }
                // )

                onBridgeReady();
            }

            function onBridgeReady(prepay_id){
                WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId":"wxd2b107737ba0c59e",     //公众号名称，由商户传入
                        "timeStamp":"1536997931",         //时间戳，自1970年以来的秒数
                        "nonceStr":"112233", //随机串
                        "package":"prepay_id=wx15163803488540813b3792e74161420366",
                        "signType":"MD5",         //微信签名方式：
                        "paySign":"318A9C048A9E0C6E637119FF3E160F26" //微信签名
                    },
                    function(res){
                        if(res.err_msg == "get_brand_wcpay_request:ok" ){
                            // 使用以上方式判断前端返回,微信团队郑重提示：
                            //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                        }
                    });
            }
        </script>
    </head>
<body>
    <h2>Jersey RESTful Web Application!</h2>
    <p><a href="webapi/myresource">Jersey resource</a>
    <p>Visit <a href="http://jersey.java.net">Project Jersey website</a>
            <%
        Enumeration enu = request.getHeaderNames();//取得全部头信息
        while (enu.hasMoreElements()) {//以此取出头信息
            String headerName = (String) enu.nextElement();
            String headerValue = request.getHeader(headerName);//取出头信息内容
    %>
    <h5><%=headerName%><font color="red">--></font> <font color="blue"><%=headerValue%></font>
    </h5>
    <%
        if (headerName.equals("x-forwarded-for")) {
    %>
        <script>
            var ip = '<%=headerValue%>';
        </script>
    <%
        }
    %>
    <%
        }
    %>
    <button onclick="payBtnClick()">微信公众号支付</button>
    <a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd2b107737ba0c59e&redirect_uri=http%3a%2f%2fwww.smallpai.com%2fpiPaiCampus%2fGzhServlet&response_type=code&scope=snsapi_base&state=123#wechat_redirect">test</a>
    <br/>
    <br/>
    <a href="http://www.smallpai.com/piPaiCampus/index.jsp">index.jsp</a>
</body>
</html>
