<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <%@ include file="/pages/common/base.jsp" %>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="smile">
    <meta name="author" content="smile">

    <title>打赏系统</title>

    <!-- Bootstrap Core CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="${distDir}/css/sb-admin-2.css" rel="stylesheet">


    <link href="${elegantCss}" rel="stylesheet"/>
    <!-- Custom Fonts -->
    <link href="${vendorDir}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <!-- MetisMenu CSS -->
    <link href="${metismenuCss}" rel="stylesheet">

    <link href="${cssDir}/mm-vertical.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="main.jsp">打赏系统 v1.2</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">
            <!-- /.dropdown -->
            <li><img src="smile.jpg" class="img-circle" style="height: 30px;width: 30px;"></li>
            <li style="margin-left: 25px;margin-top: 10px;">您好，<shiro:principal/></li>
            <li class="divider"></li>
            <li><a href="${ctx}/logout"><i class="fa fa-sign-out fa-fw"></i> 退出</a>
            </li>
            <%--<li class="dropdown">--%>
            <%--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--%>
            <%--<i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>--%>
            <%--</a>--%>
            <%--<ul class="dropdown-menu">--%>

            <%--<li><a href="#"><i class="fa fa-user fa-fw"></i> 用户信息</a>--%>
            <%--</li>--%>
            <%--<li class="divider"></li>--%>
            <%--<li><a href="#"><i class="fa fa-gear fa-fw"></i> 系统设置</a>--%>
            <%--</li>--%>
            <%--<li class="divider"></li>--%>
            <%--<li><a href="${ctx}/logout"><i class="fa fa-sign-out fa-fw"></i> 退出</a>--%>
            <%--</li>--%>
            <%--</ul>--%>
            <!-- /.dropdown-user -->
            <%--</li>--%>
            <!-- /.dropdown -->
        </ul>

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li>
                        <a href="#" id="loadIndexPage" onclick="$('#iframeDiv').attr('src','index.jsp');"><i
                                class="fa fa-dashboard fa-fw"></i>&nbsp;&nbsp;个人主页</a>
                    </li>

                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->

    </nav>

    <div id="page-wrapper" style="padding: 0px;height: 100%;border-width: 0px;">
        <div style="height: 100%;">
        <iframe src="" id="iframeDiv" height="100%" width="100%"
                <%--onload="this.height=0;var fdh=(this.Document?this.Document.body.scrollHeight:this.contentDocument.body.offsetHeight);this.height=(fdh>700?fdh:700);"--%>
                frameborder="0" onload="changeFrameHeight()"></iframe>
        </div>

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<!-- jQuery -->
<script src="${vendorDir}/jquery/jquery.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${vendorDir}/bootstrap/js/bootstrap.min.js"></script>
<!-- Metis Menu Plugin JavaScript -->
<script src="${vendorDir}/metisMenu/metisMenu.min.js"></script>
<!-- Custom Theme JavaScript -->
<script src="${distDir}/js/sb-admin-2.js"></script>
<script src="${jsDir}/main/main.js"></script>
<script src="${commnJs}"></script>

<script type="text/javascript">
    var userName = '<shiro:principal/>';
    function changeFrameHeight(){
        var ifm= document.getElementById("iframeDiv");
        ifm.height=document.documentElement.clientHeight-65;
    }

    window.onresize=function(){
        changeFrameHeight();
    }
    $(function () {
        main.init(userName);
    });
</script>
</body>

</html>
