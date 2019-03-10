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
    <link href="dashang.css" rel="stylesheet">
</head>
<%
    //    String safeDomain = request.getParameter("safeDomain");
    String ordersn = request.getParameter("orderId");
//    String appId = request.getParameter("appId");
//    String price = request.getParameter("price");
%>
<body>
    支付状态检查中
</body>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">

    $(function () {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/shieldPay/hasPay",
            data: {
                id:"<%=ordersn%>"
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    document.location="video.jsp?linkId="+data.data;
                }else{
                    location.reload();
                }
            }
        })
    });
</script>
</html>
