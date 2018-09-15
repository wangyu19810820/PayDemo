<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta charset="UTF-8">
    <script src="jquery-3.3.1.min.js" type="application/javascript"></script>
</head>
<body>
    aaa
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
    <script>
        var test = window.location.href;
        alert(test);
        function GetUrlParms()
        {
            var args=new Object();
            var query=location.search.substring(1);//获取查询串
            var pairs=query.split("&");//在逗号处断开
            for(var   i=0;i<pairs.length;i++)
            {
                var pos=pairs[i].indexOf('=');//查找name=value
                if(pos==-1)   continue;//如果没有找到就跳过
                var argname=pairs[i].substring(0,pos);//提取name
                var value=pairs[i].substring(pos+1);//提取value
                args[argname]=unescape(value);//存为属性
            }
            return args;
        }

        var args = new Object();
        args = GetUrlParms();
        alert(args["code"]);

        function payBtnClick() {
            $.post('/piPaiCampus/GzhServlet', {
                'ip':ip,
                'code':args["code"],
            },
            function (result) {
                alert(result);
                var jsonObj = JSON.parse(result);
                alert(jsonObj)
                onBridgeReady(jsonObj);
            });
        }

        function onBridgeReady(jsonObj){
            WeixinJSBridge.invoke(
                'getBrandWCPayRequest', {
                    "appId":jsonObj["appId"],     //公众号名称，由商户传入
                    "timeStamp":jsonObj["timeStamp"],         //时间戳，自1970年以来的秒数
                    "nonceStr":jsonObj["nonceStr"], //随机串
                    "package":jsonObj["package"],
                    "signType":jsonObj["signType"],         //微信签名方式：
                    "paySign":jsonObj["paySign"] //微信签名
                },
                function(res){
                    if(res.err_msg == "get_brand_wcpay_request:ok" ){
                        // 使用以上方式判断前端返回,微信团队郑重提示：
                        //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                        alert(jsonObj['outTradeNo']);
                        $.post('/piPaiCampus/GzhSuccessServlet', {
                            'outTradeNo':jsonObj['outTradeNo'],
                        },
                        function (result) {

                        });
                    } else {
                        alert('not ok')
                    }
                });
        }
    </script>
</body>
</html>
