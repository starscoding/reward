<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <%@ include file="/pages/common/base.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="css/dashang.css" rel="stylesheet">
</head>
<style>
    div {
        border-width: 1px;
        border-bottom: 2px;
        /*font-size: 50px;*/
        background-color: #000000 !important;
    }
</style>
<%
    String linkId = request.getParameter("linkId");
%>
<body>
<div class="container-fluid" id="bodyContent">
    <div class="row" style="height: 100%;" id="content">
        <div style="margin-top: 300px;font-size: x-large;display: none;background-color: #000000;color: #92faf3;"
             id="tip">支付中...
        </div>
        <img src="img/black.jpg" id="tipImg" style="height: 80%;width: 100%;">
    </div>
</div>
</body>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">

    $(function () {
        var u = navigator.userAgent;
        var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1; //android终端
//        var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
        var isiOS = u.indexOf('iPhone') > -1 || u.indexOf('iPhone') > -1; //ios终端

        var ua = window.navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == 'micromessenger') {
            $("#bodyContent").css("background-color", "#000000");
            $("#content").css("background-color", "#000000");
            if (isAndroid) {
                $("#tipImg").attr("src", "img/android_browser_tips.png")
            } else if (isiOS) {
                $("#tipImg").attr("src", "img/ios_browser_tips.png")
            } else {
//                $("#tipImg").attr("src", "img/ios_browser_tips.png")
            }
        } else {
            $("#bodyContent").css("background-color", "#ffffff");
            $("#content").css("background-color", "#ffffff");
            $("#tipImg").css("dispaly", "none");
            $('#tip').css('display', 'block');
            $.ajax({
                type: "post",
                url: smile.baseURL() + "/shieldPay/getPayWay",
                data: {},
                dataType: "json",
                success: function (data) {
                    if (data != null && data.success == 'true') {
                        var qryurl = data.data;
                        console.log(smile.baseURL() + qryurl);
                        $.ajax({
                            type: "post",
                            url: smile.baseURL() + qryurl,
                            data: {
                                linkId: "<%=linkId%>"
                            },
                            dataType: "json",
                            success: function (r) {
                                if (r != null && r.success == 'true') {
                                    window.location.href = r.data;
                                } else {
                                    window.location.href = "/reward/pages/error/404.html";
                                }
                            }
                        });
                    } else {
                        window.location.href = "/reward/pages/error/404.html";
                    }
                }
            });


        }
    });
</script>
</html>
