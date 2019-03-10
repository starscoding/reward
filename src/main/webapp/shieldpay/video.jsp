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
    <title>播放视频</title>
    <%@ include file="/pages/common/base.jsp" %>
    <meta charset="utf-8">
    <link href="${bootstrapCss}" rel="stylesheet">
    <%
        String linkId = request.getParameter("linkId");
    %>
</head>
<style>
    div {
        /*font-size: 50px;*/
        background-color: #000000;
    }
</style>
<body>

<div class="container-fluid">
    <div class="row" style="height: 10%;">
    </div>
    <div class="row" style="height: 10%;">
        <h4  style="text-align: center;color: #ff291f;">💨视频大小：<span id="filesize">0</span>Mb,视频时长：<span id="timeLength">0</span></h4>
    </div>
    <div class="row" style="height: 50%;">
        <div class="col-sm-12 ">
            <video src="#" width="100%" height="100%" controls="controls" id="videodiv">abc
                您的浏览器不支持 video 标签。
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
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${vendorDir}/jPlayer/dist/jplayer/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="application/javascript">
    $(function () {
        play("<%=linkId%>");
    });

    function play(linkId) {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/wxpay/getVideoInfo",
            data: {
                linkId: linkId
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    var record = data.data;
                    var url = smile.baseURL() + "/wxpay/sendVideo?fileName=" + record.id;
                    $("#videodiv").attr("src", url);
                    $("#filesize").text(record.fileSize);
                    $("#timeLength").text(record.timeLength);
                }
            }
        });
    }
</script>
</html>
