<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
    <%@ include file="/pages/common/base.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="smile">
    <meta name="author" content="smile">
    <meta name="keyword" content="smile">
    <title>打赏系统登录</title>
    <link rel="shortcut icon" href="${ctx}/static/img/favicon.png">
    <!-- Bootstrap CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${bootstrapThemeCss}" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${elegantCss}" rel="stylesheet"/>
    <link href="${awesomeCss}" rel="stylesheet"/>
    <!-- Custom styles -->
    <link href="${cssDir}/style.css" rel="stylesheet">
    <link href="${cssDir}/style-responsive.css" rel="stylesheet"/>
    <!-- JQuery js-->
    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${jsDir}/login/login.js"></script>
    <script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
    <script type="text/javascript">
        $(function() {
            login.init();
        });
    </script>
</head>
<body >
<div class="zdx3"><button onclick="pay()">共需支付10元&nbsp;&nbsp;确认支付</button></div>


</body>
<script>
    function pay() {
        var url="${ctx}/wechat/pay?money=${sumPrice}"; //注意此处的basePath是没有端口号的域名地址。如果包含:80,在提交给微信时有可能会提示 “redirect_uri参数错误” 。
        //money为订单需要支付的金额
        //state中存放的为商品订单号
        var weixinUrl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx20d21ee2da2cce0d&redirect_uri="+encodeURI(url)+"&response_type=code&scope=snsapi_userinfo&state=abc#wechat_redirect";
        window.location.href=encodeURI(weixinUrl);
    }

</script>
</html>
