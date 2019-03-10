<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <%@ include file="/pages/common/base.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="smile">
    <meta name="author" content="smile">
    <meta name="keyword" content="smile">
    <title>打赏系统登录</title>
    <link rel="shortcut icon" href="${ctx}/resources/img/favicon.png">
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

    <script type="text/javascript" src="${jqueryJs}"></script>
    <script type="text/javascript" src="${jsDir}/login/register.js"></script>
    <script type="text/javascript" src="${jsDir}/common/Utils.js"></script>

</head>

<body class="login-img3-body">

<div class="container" >


    <form class="login-form" id="loginForm" action="${ctx}/register" method="post" style="margin-top: 100px">
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="input-group">
                <div class="alert-error" style="color: #f67a6e" id="tipDiv">${message}</div>
            </div>
            <div class="input-group">
                <%--<div class="alert-error" style="color: #f67a6e" id="tipDiv">${message}</div>--%>
                <span class="input-group-addon"><i class="icon_profile"></i></span>
                <input type="text" class="form-control" placeholder="用户名" name="username" id="username"autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" class="form-control" placeholder="登录密码" name="password" id="password">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key"></i></span>
                <input type="password" class="form-control" placeholder="重复登录密码" name="repeatPwd" id="repeatPwd">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" class="form-control" placeholder="提现密码" name="fetchPwd" id="fetchPwd">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key"></i></span>
                <input type="password" class="form-control" placeholder="重复提现密码" name="repeatFetchPwd" id="repeatFetchPwd">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="social_share"></i></span>
                <input type="text" class="form-control" placeholder="邀请码" name="invitionCode" id="invitionCode">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_clock"></i></span>
                <input type="text" id="verifyCode" class="form-control" placeholder="验证码" name="verifyCode" onkeypress="if (event.keyCode == 13) login.verfy()" id="verifyCode">
                <%--<img src="${ctx}/getVerifyImage" onclick="login.changeImg()" id="veryCodeImg" class="input-group-addon" style="width: 120px"/>--%>
                <span class="input-group-addon"   style="padding: 0px;">
                    <img src="${ctx}/getVerifyImage" id="veryCodeImg" onclick="register.changeImg('${ctx}')" style="padding: 0px;border: 2px;" alt="看不清楚，换一张" title="看不清楚，换一张">
                </span>
            </div>
            <button class="btn btn-info btn-lg btn-block" type="button" onclick="register.verfy()">注册</button>
        </div>
    </form>

</div>
</body>
</html>
