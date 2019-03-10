<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.net.URLEncoder" %><%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/5/9
  Time: 12:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <%
        String appId = request.getParameter("appId");
        String safeDomain = request.getParameter("safeDomain");
        String linkId = request.getParameter("linkId");
        String ldDomain = request.getParameter("ldDomain");
//        String url = "http://www.isspark.com/reward/wxpay/order2.jsp?id=abcdef&order=cdefg";
        String url = "http://"+safeDomain+"/reward/wxpay/order2.jsp?appId="+appId+"&linkId="+linkId+"&safeDomain="+safeDomain+"&ldDomain="+ldDomain;
        //test
//        String url = "127.0.0.1:7090/reward/wxpay/order.jsp";
        String encodeUrl = URLEncoder.encode(url);
//        String encodeUrl = url;
    %>
    <title>加载中</title>

</head>
<body>
<%--<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx20d21ee2da2cce0d&redirect_uri=<%=encodeUrl%>&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect">--%>
    <%--授权1</a>--%>
</body>
<script language="javascript">

    var url ="https://open.weixin.qq.com/connect/oauth2/authorize?appid=<%=appId%>&redirect_uri=<%=encodeUrl%>&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
    document.location = url;
</script>
</html>
