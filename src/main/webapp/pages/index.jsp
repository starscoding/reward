<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/5/13
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@ include file="/pages/common/base.jsp" %>
    <link href="${bootstrapCss}" rel="stylesheet">
</head>
<body>
<div id = "notice" class="container">

</div>
</body>

<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $(function () {
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/configService/getConfByName",
            data : {
                name : "notice"
            },
            dataType : "json",
            success : function (data) {
                if(isNotBank(data)&&data.success=="true"){
                    $("#notice").append(data.data);
                }
            }
        })
    });
</script>
</html>
