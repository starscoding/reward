<%--
  Created by IntelliJ IDEA.
  User: smile
  Date: 2018/4/10
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="/pages/common/base.jsp" %>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="smile">
    <meta name="author" content="smile">
    <meta name="keyword" content="smile">
    <title>用户信息设置</title>
    <!-- Bootstrap CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="${elegantCss}" rel="stylesheet"/>
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <link href="${vendorDir}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>

<body>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">系统配置</h3>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#contact" data-toggle="tab">联系方式</a>
                    </li>
                    <li><a href="#loginPwd" data-toggle="tab">登录密码修改</a>
                    </li>
                    <li><a href="#fetchPwd" data-toggle="tab">提取密码修改</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="contact">
                        <form class="form-horizontal" style="margin-top: 20px;">
                            <div class="form-group">
                                <label for="wechat" class="col-sm-3 control-label">微信:</label>
                                <div class="col-sm-3">
                                    <input type="email" class="form-control" id="wechat">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="qq" class="col-sm-3 control-label">QQ:</label>
                                <div class="col-sm-3">
                                    <input type="email" class="form-control" id="qq">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-3">
                                    <button type="button" class="btn btn-primary"
                                            onclick="userInfoSet.updateContact()">修改
                                    </button>
                                    <button type="button" class="btn btn-default" style="margin-left: 20px;" onclick="userInfoSet.contactReset()">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="loginPwd">
                        <form class="form-horizontal" style="margin-top: 20px;">
                            <div class="form-group">
                                <label for="oldPwd" class="col-sm-3 control-label">旧密码:</label>
                                <div class="col-sm-3">
                                    <input type="password" class="form-control" id="oldPwd">
                                </div>
                                <div class="alert-error col-sm-3" style="color: #f67a6e" id="pwdTip"></div>
                            </div>
                            <div class="form-group">
                                <label for="newPwd" class="col-sm-3 control-label">新密码:</label>
                                <div class="col-sm-3">
                                    <input type="password" class="form-control" id="newPwd">
                                </div>
                                <div class="alert-error col-sm-3" style="color: #f67a6e" id="newPwdTip"></div>
                            </div>
                            <div class="form-group">
                                <label for="repeatNewPwd" class="col-sm-3 control-label">重复新密码:</label>
                                <div class="col-sm-3">
                                    <input type="password" class="form-control" id="repeatNewPwd">
                                </div>
                                <div class="form-group alert-error col-sm-3" style="color: #f67a6e" id="tipDiv"></div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-3">
                                    <button type="button" class="btn btn-primary"
                                            onclick="userInfoSet.alterPwd()">修改
                                    </button>
                                    <button type="reset" class="btn btn-default" style="margin-left: 20px;">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <div class="tab-pane fade" id="fetchPwd">
                        <form class="form-horizontal" style="margin-top: 20px;">
                            <div class="form-group">
                                <label for="oldFetchPwd" class="col-sm-3 control-label">旧提取密码:</label>
                                <div class="col-sm-3">
                                    <input type="password" class="form-control" id="oldFetchPwd">
                                </div>
                                <div class="alert-error col-sm-3" style="color: #f67a6e" id="pwdFetchTip"></div>
                            </div>
                            <div class="form-group">
                                <label for="newFetchPwd" class="col-sm-3 control-label">新提取密码:</label>
                                <div class="col-sm-3">
                                    <input type="password" class="form-control" id="newFetchPwd">
                                </div>
                                <div class="alert-error col-sm-3" style="color: #f67a6e" id="newFetchPwdTip"></div>
                            </div>
                            <div class="form-group">
                                <label for="repeatNewFetchPwd" class="col-sm-3 control-label">重复新提取密码:</label>
                                <div class="col-sm-3">
                                    <input type="password" class="form-control" id="repeatNewFetchPwd">
                                </div>
                                <div class="form-group alert-error col-sm-3" style="color: #f67a6e" id="tipDivFetch"></div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-3">
                                    <button type="button" class="btn btn-primary"
                                            onclick="userInfoSet.alterFetchPwd()">修改
                                    </button>
                                    <button type="reset" class="btn btn-default" style="margin-left: 20px;">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-6 -->
</div>

<!-- JQuery js-->
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${vendorDir}/filestyle/bootstrap-filestyle.min.js"></script>
<script type="text/javascript" src="${jsDir}/agency/userInfoSet.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $(function () {
        userInfoSet.init();
    });
</script>
</body>
</html>
