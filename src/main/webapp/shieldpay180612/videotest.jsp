<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/5/19
  Time: 12:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%@ include file="/pages/common/base.jsp" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="smile">
    <meta name="author" content="smile">

    <!-- Bootstrap Core CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <style>
        div {
            border-width: 1px;
            border-bottom: 2px;
            /*font-size: 50px;*/
            background-color: #000000;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="height: 10%;">
    </div>
    <div class="row" style="height: 10%;">
        <h4  style="text-align: center;color: #ff291f;">💨视频大小：101M,视频时长:00:00:00</h4>
    </div>
    <div class="row" style="height: 50%;">
        <div class="col-sm-12 ">
            <video src="../video/6B22CBAE78D7408518C14445B451D4C8" width="100%" height="100%" controls="controls">abc
            </video>
        </div>
    </div>
    <div class="row" style="height: 10%;">
    </div>
    <div class="row" style="height: 20%;">
        <p class="lead" style="color: #ffeb26">由于网络波动原因，出现视频卡顿无法播放等情况时，可等待缓冲或刷新页面重试！</p>
    </div>
</div>

</body>
<!-- jQuery -->
<script src="${vendorDir}/jquery/jquery.min.js"></script>
<!-- Bootstrap Core JavaScript -->
<script src="${vendorDir}/bootstrap/js/bootstrap.min.js"></script>
</html>
