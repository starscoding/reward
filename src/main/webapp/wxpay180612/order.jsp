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
    <link href="${bootstrapCss}" rel="stylesheet">
</head>

<style>

    * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
    }

    html {
        color: #fff;
        font-size: 14px;
    }

    .container {
        text-align: center;
        width: 100%;
        max-width: 960px;
        margin: 0 auto;
        padding: 15px 5px;
    / / background-color: #000000;
        background-image: url(b.jpg);
        height: 100%;
        position: absolute;
    }

    .hongbao {
        height: 380px;
        background: #A5423A;
        width: 300px;
        left: 0;
        top: 0;
        border-radius: 10px;
        margin: 0 auto;
    }

    .topcontent {
        height: 200px;
        border: 1px solid #BD503A;
        background-color: #BD503A;
        border-radius: 10px 10px 50% 50% / 10px 10px 15% 15%;
        box-shadow: 0px 4px 0px -1px rgba(0, 0, 0, 0.2);
    }

    .avatar {
        position: relative;
    }

    .avatar span {
        position: absolute;
        top: 10px;
        right: 15px;
        -webkit-transform: rotate(45deg);
        -ms-transform: rotate(45deg);
        transform: rotate(45deg);
        font-size: 2em;
        font-weight: bolder;
    }

    #close {
        color: rgba(0, 0, 0, 0.5);
    }

    .avatar img {
        border: 1px solid #BD503A;
        border-radius: 50%;
        overflow: hidden;
        margin-top: 10%;
        color: #fff;

    }

    .topcontent h2 {
        margin: 15px 0;
    }

    .text {
        color: #999;
    }

    .description {
        margin: 25px 0;
        font-size: 12px;
        font-weight: 600;
    }

    #chai {
        width: 100px;
        height: 100px;
        border: 1px solid #FFA73A;
        background-color: #FFA73A;
        border-radius: 50%;
        color: #fff;
        font-size: 25px;
        display: inline-block;
        margin-top: -50px;
        box-shadow: 0px 4px 0px 0px rgba(0, 0, 0, 0.2);
    }

    #chai span {
        margin-top: 35px;
        display: inline-block;
    }

    .rotate {
        -webkit-animation: anim .6s infinite alternate;
        -ms-animation: anim .6s infinite alternate;
        animation: anim .6s infinite alternate;
    }

    @-webkit-keyframes anim {
        from {
            -webkit-transform: rotateY(180deg);
        }
        to {
            -webkit-transform: rotateY(360deg);
        }
    }

    @-ms-keyframes anim {
        from {
            -ms-transform: rotateY(180deg);
        }
        to {
            -ms-transform: rotateY(360deg);
        }
    }

    @keyframes anim {
        from {
            transform: rotateY(180deg);
        }
        to {
            transform: rotateY(360deg);
        }
    }
</style>
<%
    String safeDomain = request.getParameter("safeDomain");
    String linkId = request.getParameter("linkId");
    String appId = request.getParameter("appId");
    String ldDomain = request.getParameter("ldDomain");
    String price = request.getParameter("price");
    String payType = request.getParameter("payType");
%>
<body>
<div class="container" id="container">
    <div class="hongbao">
        <div class="topcontent">
            <div class="avatar" style="color: #fff;">
                <h2>打赏看视频</h2>
                <span id="close" onclick="">+</span>
            </div>
            <span class="text">视频大小：</span>
            <span  class="text" id="filesize">0</span>
            <span class="text">M，时长：</span>
            <span  class="text" id="timelength">0</span>
            <h1 style="margin-top:20px;color: #fff;" id ="price">¥<%=price%></h1>


        </div>
        <div class="chai" id="chai" onclick="toOrder2()" style="color: #fff;">
            <span><b>打赏</b></span>
        </div>
        <div style="margin-top:25px;color: #fff;font-size: 15px;" onclick="showInfo();">
            <span><u>我也要赚钱</u></span>
        </div>
        <div class="description">（内容由用户发布，并非平台提供，赏金归发布者）</div>
    </div>
</div>
<div class="modal" id="infoModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">"微信号：smiel;<br><br>QQ：1111111";</div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="videoMng.delete()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
</body>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="application/javascript">
    //    $(function () {
    //
    //    });
    setInfo();
    function showInfo() {
        $("#infoModal").modal("show");
    }
    function setInfo() {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/wxpay/getVideoInfo",
            data: {
                linkId: "<%=linkId%>"
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    $("#filesize").text(data.data.fileSize);
                    $("#timelength").text(data.data.timeLength);
                }
            }
        })
    }
    function toOrder2() {
        <%--alert("<%=safeDomain%>/reward/wxpay/toOrder2.jsp?safeDomain=<%=safeDomain%>&appId=<%=appId%>&linkId=<%=linkId%>");--%>
//        document.location = "http://www.isspark.com/reward/wxpay/toOrder2.jsp";
        var paytype = "<%=payType%>";
        if(paytype=="weixin") {
            document.location = "http://<%=safeDomain%>/reward/wxpay/toOrder2.jsp?safeDomain=<%=safeDomain%>&appId=<%=appId%>&linkId=<%=linkId%>&ldDomain=<%=ldDomain%>";
        }else{
            document.location = "http://<%=safeDomain%>/reward/wxpay/toOrder2.jsp?safeDomain=<%=safeDomain%>&appId=<%=appId%>&linkId=<%=linkId%>&ldDomain=<%=ldDomain%>";
        }
    }

</script>
</html>
