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
    <title>用户管理</title>
    <!-- Bootstrap CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="${elegantCss}" rel="stylesheet"/>
    <link href="${vendorDir}/datatables/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="${vendorDir}/datatables-plugins/Select/css/select.bootstrap.min.css" rel="stylesheet">
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <style type="text/css">
        #userList_info{
            float: left;
        }
        #userList_length{
            float: left;
            margin-top: 7px;
        }
        #userList_paginate{
            float: right;
            margin-top: 7px;
        }
        #userList_filter{
            float: left;
        }
    </style>
</head>

<body >
<%--<div class="row">--%>
<%--<div class="row col-lg-12" >--%>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">用户管理</h3>
            <%--<a href="projects.html" class="btn btn-primary btn-xs p310"><i class="im-plus"></i> 创建新项目</a>--%>
            <%--<a href="projects.html" class="btn btn-primary btn-xs p310"><i class="im-pencil2"></i> 编辑</a>--%>
            <%--<a href="projects.html" class="btn btn-primary btn-xs p310"><i class="im-remove4"></i> 删除</a>--%>
            <%--<a href="projects.html" class="btn btn-primary btn-xs p310"><i class="im-spell-check"></i> 批量审核</a>--%>
            <%--<button id="tb-refresh" href="projects.html" class="btn btn-primary btn-xs p310"><i class="im-spinner10 fa-spin"></i> 刷新</button>--%>
        </div>
        <div class="panel-body">
            <%--<div>--%>
                <%----%>
            <%--</div>--%>
            <!-- search star -->
                <div class="form-group" style="float: right;">
                    <button id="add" class="btn btn-primary btn-xs p310" onclick="userMng.addUser()"><i class="icon_plus_alt"></i> 新增</button>
                    <button id="edit" class="btn btn-primary btn-xs p310" onclick="userMng.editUser()"><i class="icon_pencil-edit_alt"></i> 编辑</button>
                    <button id="delete" class="btn btn-primary btn-xs p310" onclick="userMng.showSureModal()"><i class="icon_minus_alt"></i> 删除</button>
                    <button id="resh" class="btn btn-primary btn-xs p310" onclick="userMng.refresh()"><i class="icon_refresh"></i> 刷新</button>
                </div>
            
            <!-- 定义一个表格元素 -->
            <table id="userList" class="table table-striped table-bordered table-hover" width="100%">
                <thead>
                <tr>
                    <%--<th>序号</th>--%>
                    <%--<th><input type="checkbox" class="checkall" /></th>--%>
                    <th></th>
                    <th>ID</th>
                    <th>用户名</th>
                    <th>用户类型</th>
                    <th>微信号</th>
                    <th>QQ</th>
                    <%--<th>代理级别</th>--%>
                    <th>上级用户</th>
                    <th>创建时间</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
                <tfoot></tfoot>
                <!-- tbody是必须的 -->
            </table>
        </div>
    </div>

<!-- 模态框（Modal） -->
<div class="modal" id="userModal" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="userModalLabel"><i class=" icon_id_alt"></i>&nbsp;新增用户</h4>
            </div>
            <div class="modal-body">
                <form class="adduser-form" id="loginForm" action="" method="post">
                    <div class="login-wrap">
                        <div class="alert-error" style="color: #f67a6e" id="tipDiv"></div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_profile"></i></span>
                            <input type="text" class="form-control" placeholder="用户名" id="username" autofocus>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_key"></i></span>
                            <input type="password" class="form-control" placeholder="登录密码" id="password">
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_tags"></i></span>
                            <select class="form-control" placeholder="登录密码" id="type" name="type">
                                <%--<option value="">--请选择用户类型--</option>--%>
                                <%--<option value="0">管理员</option>--%>
                                <%--<option value="1">合伙人</option>--%>
                                <%--<option value="2">代理人</option>--%>
                                <%--<option value="3">上传人</option>--%>
                            </select>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_key"></i></span>
                            <input type="password" class="form-control" placeholder="提现密码" id="fetchPwd">
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_chat"></i></span>
                            <input type="text" class="form-control"  placeholder="微信" id="wechat" >
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="social_twitter_circle"></i></span>
                            <input type="text" class="form-control" placeholder="QQ" id="qq" >
                        </div>

                    </div>
                </form>
            </div>
            <div class="modal-footer" style="text-align: center;" >
                <button type="button" class="btn btn-primary" onclick="userMng.addOrUpdateUser()">提交</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal" id="sureModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">确认删除?</div>
            <div class="modal-footer" style="text-align: center;" >
                <button type="button" class="btn btn-primary" onclick="userMng.deleteUsers()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<%--</div>--%>
<%--</div>--%>


<!-- JQuery js-->
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${dataTablesJs}"></script>
<script type="text/javascript" src="${dataTables4bJs}"></script>
<%--<script src="${vendorDir}/datatables-responsive/dataTables.responsive.js"></script>--%>
<script src="${vendorDir}/datatables-plugins/Select/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${jsDir}/usermng/userMng.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $(function () {
            userMng.init();
    });
</script>
</body>
</html>
