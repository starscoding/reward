<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>加载中</title>
    <%@ include file="/pages/common/base.jsp" %>
    <%
        String linkId = request.getParameter("linkId");
        String type = request.getParameter("type");
    %>
</head>
<body>
</body>
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script language="javascript">
    toOrder();
    function toOrder() {
        var linkId = "<%=linkId%>";
        var type ="<%=type%>";
        if(isBank(linkId)||linkId=="null"){
            alert("缺少必要的参数!");
            return;
        }
        if(isBank(type)||type=="null"){
            type="";
        }
        $.ajax({
            type : "post",
            url: smile.baseURL() + "/wxpay/isReward",
            data: {
                linkId : linkId,
                type : type
            },
            dataType: "json",
            success: function (data) {
                if(data!=null && data.success=='true'){
                    document.location=data.data;
                }
            }
        })
    }

</script>
</html>
