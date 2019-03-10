<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/5/11
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统维护中</title>
    <%@ include file="/pages/common/base.jsp" %>
    <meta charset="utf-8">
    <link href="${bootstrapCss}" rel="stylesheet">
</head>

<style>
    #floater {
        float: left;
        height: 50%;
        margin-bottom: -120px;
    }

    #top {
        float: right;
        width: 100%;
        text-align: center;
    }

    #content {
        clear: both;
        height: 240px;
        position: relative;
        text-align: center;
    }
</style>
<body>
<div id="top"></div>
<div id="floater"></div>
<div id="content"><span  style="text-align: center;color: #ff291f;font-size: 45px;">系统维护中，请稍后再试！</span>
</div>


</body>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
</html>
