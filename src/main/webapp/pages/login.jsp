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
<body class="login-img3-body">

<div class="container">

    <form class="login-form" id="loginForm" action="${ctx}/login" method="post">
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="alert-error" style="color: #f67a6e" id="tipDiv">${message}</div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_profile"></i></span>
                <input type="text" id="username" class="form-control" placeholder="账号" name="username" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" id="password" class="form-control" placeholder="密码" name="password">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_clock"></i></span>
                <input type="text" id="verifyCode" class="form-control" placeholder="验证码" name="verifyCode" onkeypress="if (event.keyCode == 13) login.verfy()">
                <%--<img src="${ctx}/getVerifyImage" onclick="login.changeImg()" id="veryCodeImg" class="input-group-addon" style="width: 120px"/>--%>
                <span class="input-group-addon"   style="padding: 0px;">
                    <img src="${ctx}/getVerifyImage" id="veryCodeImg" onclick="login.changeImg('${ctx}')" style="padding: 0px;border: 2px;" alt="看不清楚，换一张" title="看不清楚，换一张">
                </span>
            </div>
            <%--<label class="checkbox">--%>
                <%--<input type="checkbox" value="remember-me">记住密码--%>
                <%--<span class="pull-right"> <a href="#"> 忘记密码？</a></span>--%>
            <%--</label>--%>
            <button class="btn btn-primary btn-lg btn-block" type="button" onclick="login.verfy()">登录</button>
            <%--<a href="resources/register.jsp">注册</a>--%>
            <button class="btn btn-info btn-lg btn-block" type="button" onclick="login.toRegisterPage()">注册</button>
        </div>
    </form>

</div>

</body>

</html>
