<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/5/9
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>打赏</title>
    <meta charset="utf-8">
    <%@ include file="/pages/common/base.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="smile">
    <meta name="author" content="smile">
    <meta name="keyword" content="smile">
</head>

<%
    String appId = request.getParameter("appId");
    String timeStamp = request.getParameter("timeStamp");
    String nonceStr = request.getParameter("nonceStr");
    String package_ = request.getParameter("package");
    String signType = request.getParameter("signType");
    String paySign = request.getParameter("paySign");

%>
<body>
接口调用成功1
</body>
<script type="application/javascript">
    //    onBridgeReady();
    <%--var appId = "<%=appId%>";--%>
    <%--var timeStamp = "<%=timeStamp%>";--%>
    <%--var nonceStr = "<%=nonceStr%>";--%>
    <%--var package = "<%=package_%>";--%>
    <%--var signType = "<%=signType%>";--%>
    <%--var paySign = "<%=paySign%>";--%>
    <%--alert(">>>" + appId + ">" + timeStamp + ">" + nonceStr + ">" + package + ">" + signType + ">" + paySign);--%>
    //    onBridgeReady();
    alert(WeixinJSBridge);
    function onBridgeReady() {
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId": "wx2421b1c4370ec43b",     //公众号名称，由商户传入
                "timeStamp": "1395712654",         //时间戳，自1970年以来的秒数
                "nonceStr": "e61463f8efa94090b1f366cccfbbb444", //随机串
                "package": "prepay_id=u802345jgfjsdfgsdg888",
                "signType": "MD5",         //微信签名方式：
                "paySign": "70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名
            },
            function (res) {
                if (res.err_msg == "get_brand_wcpay_request:ok") {
                }     // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
            }
        );
    }
    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady();
    }

</script>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
</html>
