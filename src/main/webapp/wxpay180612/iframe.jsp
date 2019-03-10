<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/5/10
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/pages/common/base.jsp" %>
    <title>打赏看视频</title>
</head>
<%
    String code = request.getParameter("code");
%>
<body>

<%--<iframe id="iframe" width="100%" height="100%" frameborder="0"></iframe>--%>
<div id="iDiv" style="height: 100px;width: 500px;"></div>

</body>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script>
    $(function () {
        var code = "<%=code%>";
        alert("iframe:"+code);
//        $('#iframe').attr("src",smile.baseURL()+"/wxpay/order.jsp?code="+code);
//        $('#iframe').attr("src","http://www.isspark.com/reward"+"/wxpay/order.jsp?code="+code);
//        $('#iDiv').load("http://www.isspark.com/reward"+"/wxpay/order.jsp?code="+code);
        $('#iDiv').load(smile.baseURL()+"/wxpay/order.jsp?code="+code);
        window.onblur = function() {
            //暂停动画或视频
            alert();
        };
    })
</script>
</html>
