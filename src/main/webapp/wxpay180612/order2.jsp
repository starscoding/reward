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
    String code = request.getParameter("code");
    String linkId = request.getParameter("linkId");
    String safeDomain = request.getParameter("safeDomain");
    String appId = request.getParameter("appId");
    String ldDomain = request.getParameter("ldDomain");
%>
<body>
</body>
<script type="application/javascript">
    var code = "<%=code%>";
    var linkId = "<%=linkId%>";
    var safeDomain = "<%=safeDomain%>";
    var appId = "<%=appId%>";
    var ldDomain = "<%=ldDomain%>"
    function onBridgeReady() {
        <%--alert("<%=safeDomain%>");--%>
        $.ajax({
            type: "post",
            url: "http://<%=safeDomain%>/reward/wxpay/topay",
//            url : "http://www.isspark.com/reward"+"/wxpay/topay",
            data: {
                code: code,
                linkId: linkId,
                safeDomain: safeDomain,
                appId: appId
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    var r = data.data;
                    WeixinJSBridge.invoke(
                        'getBrandWCPayRequest', {
                            "appId": r.appId,     //公众号名称，由商户传入
                            "timeStamp": r.timeStamp + "",         //时间戳，自1970年以来的秒数
                            "nonceStr": r.nonceStr, //随机串
                            "package": r.package,
                            "signType": r.signType,         //微信签名方式：
                            "paySign": r.paySign //微信签名
                        },
                        function (res) {
                            if (res.err_msg == "get_brand_wcpay_request:ok") {
//                                alert('支付成功');
//                                WeixinJSBridge.call('closeWindow');
                                //支付成功后跳转的页面
                                <%--document.location="http://"+ldDomain+"/reward/wxpay/video.jsp?linkId=<%=linkId%>&orderId="r.orderId;--%>
                                document.location="http://"+ldDomain+"/reward/wxpay/checkStatus.jsp?orderId="+r.orderId;
                            } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
                                alert('支付取消');
                                WeixinJSBridge.call('closeWindow');
                            } else if (res.err_msg == "get_brand_wcpay_request:fail") {
                                alert('支付失败');
                                WeixinJSBridge.call('closeWindow');
                            }    // 使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
                        }
                    );
//                    onBridgeReady(r.appId, r.timeStamp, r.nonceStr, r.package, r.signType, r.paySign);
                }

            }
        })

    }
    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady;
    }

    //    function onBridgeReady(appid,timeStamp,nonceStr,package,signType,paySign){
    //        alert(WeixinJSBridge);
    //        WeixinJSBridge.invoke(
    //            'getBrandWCPayRequest', {
    //                "appId":appid,     //公众号名称，由商户传入
    //                "timeStamp":timeStamp+"",         //时间戳，自1970年以来的秒数
    //                "nonceStr":nonceStr, //随机串
    //                "package":package,
    //                "signType":signType,         //微信签名方式：
    //                "paySign": paySign//微信签名
    //            },
    //            function(res) {
    //                if (res.err_msg == "get_brand_wcpay_request:ok") {
    //                    alert('支付成功');
    //                    //支付成功后跳转的页面
    //                } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
    //                    alert('支付取消');
    //                    WeixinJSBridge.call('closeWindow');
    //                } else if (res.err_msg == "get_brand_wcpay_request:fail") {
    //                    alert('支付失败');
    //                    WeixinJSBridge.call('closeWindow');
    //                }
    //            }//使用以上方式判断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回ok,但并不保证它绝对可靠。            }
    //        );
    //    }
    //    $(document).read();
    //    setTimeout(alert("timeout"),20000);
</script>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
</html>
