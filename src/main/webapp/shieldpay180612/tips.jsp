<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <meta charset="utf-8">
    <%@ include file="/pages/common/base.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="dashang.css" rel="stylesheet">
</head>
<style>
    div {
        border-width: 1px;
        border-bottom: 2px;
        /*font-size: 50px;*/
        background-color: #000000;
    }
</style>
<%
    String linkId = request.getParameter("linkId");
    String type = request.getParameter("type");
%>
<body>
<div class="container-fluid" id="bodyContent">
    <div class="row" style="height: 100%;" id="content">
        <div style="text-align:center;display: none">
            《我喜欢你是寂静的》
            作者：【智利】聂鲁达
            朗诵：篁竹瑾

            我喜欢你是寂静的，仿佛你消失了一样，
            你从远处聆听我，我的声音却无法触及你。
            好像你的双眼已经飞离去，如同一个吻，封缄了你的嘴。
            如同所有的事物充满了我的灵魂，
            你从所有的事物中浮现，充满了我的灵魂。
            你像我的灵魂，一只梦的蝴蝶。你如同忧郁这个词。
            我喜欢你是寂静的，好像你已远去。
            你听起来像在悲叹，,一只如鸽悲鸣的蝴蝶。
            你从远处听见我，我的声音无法触及你：
            让我在你的沉默中安静无声。
            并且让我借你的沉默与你说话，
            你的沉默明亮如灯，简单如指环，
            你就像黑夜，拥有寂寞与群星。
            你的沉默就是星星的沉默，遥远而明亮。
            我喜欢你是寂静的，仿佛你消失了一样，
            遥远而且哀伤，仿佛你已经死了。
            彼时，一个字，一个微笑，已经足够。
            而我会觉得幸福，因那不是真的而觉得幸福。
        </div>
        <img src="5.jpg" id="tipImg" style="height: 80%;width: 100%;">
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
                $("#tipImg").attr("src", "android_browser_tips.png")
            } else if (isiOS) {
                $("#tipImg").attr("src", "ios_browser_tips.png")
            } else {
            }
        } else {
            $("#bodyContent").css("background-color", "#ffffff");
            $("#content").css("background-color", "#ffffff");
            $("#tipImg").css("dispaly", "none");
//            $("#content").html("跳转中...");
            var type = "<%=type%>";
//            var type = "abc";
            if (isBank(type) || type == 'null') {
                window.location.href = "../wxpay/toOrder.jsp?linkId=" + "<%=linkId%>&type=1";
            } else {
                $.ajax({
                    type: "post",
                    url: smile.baseURL() + "/shieldPay/toPay",
                    data: {
                        linkId: "<%=linkId%>"
                    },
                    dataType: "json",
                    success: function (data) {
                        if (data != null && data.success == 'true') {
                            document.location = data.data.data;
                        } else {
                            document.location = "/reward/pages/error/404.html";
                        }
                    }
                })
            }

        }
    });
</script>
</html>
