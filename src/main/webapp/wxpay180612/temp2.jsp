<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/5/10
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/pages/common/base.jsp" %>
    <%
//        String code = "abc";
        String code = request.getParameter("code");
    %>
</head>
<body>
临时跳转
</body>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script language="javascript">
    <%--var code = <%=code%>;--%>
    var code = "<%=code%>";
    alert("temp:"+code);
    var url ="http://www.isspark.com/reward"+"/wxpay/order2.jsp?code="+code;
    document.location = url;
</script>
</html>
