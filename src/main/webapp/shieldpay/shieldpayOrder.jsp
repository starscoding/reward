<%@ page import="com.smile.azxx.util.RandomGUID" %><%--
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
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="css/dashang.css" rel="stylesheet">
</head>
<%
    String linkId = request.getParameter("linkId");
%>
<body>
<div id="background" style="position:absolute;z-index:-1;width:100%;height:100%;top:0px;left:0px;">
    <img src="img/5.jpg" width="100%" height="100%">
</div>

<p class="sheng">
    <a onclick="showRed()">打赏观看</a>
</p>

<div class="content">
    <div class="nav" style="opacity:0.7">

        <img src="img/redbg.png">

        <p class="p1" style="font-size: 13px;">
            <a onclick="closeRed()" style="position: absolute; top: 0px; left: 4%;font-size:20px;color:#9d2129;">×</a>
        </p>

        <p class="p3">
            <span id="price">0</span>
            <span style="font-size:12px;">元</span>
        </p>

        <p class="p5">
            (内容由用户发布,并非平台提供,赏金归发布者)
        </p>
        <div class="reward" style="">
            <div class="button" style="width:100%;font-size:15px;height:40px;line-height:40px;">
                <a onclick="shieldpay()"
                   style="background:#fae2b2;border-radius:10px; color:#d35b4d;display:inline-block;width:100%;height:40px;font-weight:bold;margin-left:1px">打赏</a>&nbsp;
            </div>
            <a href="#" onclick="showInfo();">
                <button type="button" class="submit1">我也要赚钱</button>
            </a>
        </div>

        <div style="text-align:center; color:#fff; font-size:14px; margin-top:8px; padding:10px; ">
        </div>
    </div>
</div>

<div class="daxiao">
    <span>视频大小：</span>
    <span id="filesize">0</span>
    <span>M，时长：</span>
    <span id="timelength">0</span>
</div>

<div class="footer">
    <a href="ts.jsp">&nbsp;&nbsp;&nbsp;投诉</a>
</div>

<div style="width:100%;height:100%;background:#000;position:fixed;top:0; filter:alpha(opacity=50); -moz-opacity:0.5; -khtml-opacity: 0.5; opacity:0.5;"
     id="touming">
</div>

<div class="modal" id="infoModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">招募中，联系方式如下</h4>
            </div>
            <div class="modal-body">"微信号：smiel;<br><br>QQ：1111111";</div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $.ajax({
        type: "post",
        url: smile.baseURL() + "/wxpay/getVideoInfo",
        data: {
            linkId: "<%=linkId%>"
        },
        dataType: "json",
        success: function (data) {
            if (data != null && data.success == 'true') {
                $("#filesize").text(data.data.filesize);
                $("#timelength").text(data.data.timelength);
                $('#price').text(data.data.price);
            }
        }
    })
    function closeRed() {
        $('.nav').css('display', 'none');
        $('.sheng').css('display', 'block');
    }
    function showRed() {
        $('.nav').css('display', 'block');
        $('.sheng').css('display', 'none');
    }

    function shieldpay() {
        var _loading = '<div class="_loading" style="position:fixed;left:50%;top:40%;margin-left:-40px;width:90px;height:80px;border-radius:5%;background:#000;opacity:0.8;background:#000 url(img/loading.gif) center 12px no-repeat;background-size:25px;z-index:99999;color:#000000;text-align:center;font-size:12px;"><br><br><br>正在提交订单...</div>';
        $('body').append(_loading);
        window.location.href = "jumptips.jsp?linkId=" + "<%=linkId%>";
    }

    function showInfo() {
        $("#infoModal").modal("show");
    }

</script>
</html>
