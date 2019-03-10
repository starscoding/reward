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
    <title>邀请码</title>
    <!-- Bootstrap CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="${elegantCss}" rel="stylesheet"/>
    <link href="${vendorDir}/datatables/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="${vendorDir}/datatables-plugins/Select/css/select.bootstrap.min.css" rel="stylesheet">
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <style type="text/css">
        #userList_info {
            float: left;
        }

        #userList_length {
            float: left;
            margin-top: 7px;
        }

        #userList_paginate {
            float: right;
            margin-top: 7px;
        }

        #userList_filter {
            float: left;
        }
    </style>
</head>

<body>
<%--<div class="row">--%>
<%--<div class="row col-lg-12" >--%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">邀请码</h3>
    </div>
    <div class="panel-body">
        <!-- search star -->
        <div class="panel panel-warning">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-12">
                        <div>
                            <span style="font-size: 16px;" id = "codeNotice">
                            <%--夜间不处理任何提现，提现到账时间每天<span style="color: #ff7a12;font-size: 22px;" >中午12点到13点统一处理。</span>--%>
                                <%--请各位代理填好个人支付信息，为了你的安全，<span style="color: #ff7a12;font-size: 22px;" >超过2000元请联系客服安全提现！</span>--%>
                                <%--客服微信：<span style="color: #ff7a12;font-size: 22px;" >mao10202717</span>,客服QQ:<span style="color: #ff7a12;font-size: 22px;" >123</span>--%>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="form-group" style="float: right;">
            <button id="add" class="btn btn-primary btn-xs p310" onclick="invitationCodeMng.showBuyModal()"><i
                    class="icon_currency_alt"></i> 购买邀请码
            </button>
            <button id="edit" class="btn btn-primary btn-xs p310" onclick="invitationCodeMng.query()"><i
                    class="icon_box-checked"></i> 全部邀请码
            </button>
            <button id="delete" class="btn btn-info btn-xs p310" onclick="invitationCodeMng.query('1')"><i
                    class="icon_star"></i> 已激活
            </button>
            <button id="resh" class="btn btn-primary btn-xs p310" onclick="invitationCodeMng.query('0')"><i
                    class="icon_star_alt"></i> 未激活
            </button>
        </div>

        <!-- 定义一个表格元素 -->
        <table id="userList" class="table table-striped table-bordered table-hover" width="100%">
            <thead></thead>
            <tbody></tbody>
            <tfoot></tfoot>
            <!-- tbody是必须的 -->
        </table>
    </div>
</div>

<!-- 模态框（Modal） -->
<div class="modal" id="addModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="addModalLabel"><i class=" icon_id_alt"></i>&nbsp;新增角色</h4>
            </div>
            <div class="modal-body">
                <form class="adduser-form" id="loginForm" action="" method="post">
                    <div class="login-wrap">
                        <div class="alert-error" style="color: #f67a6e" id="tipDiv"></div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_group"></i></span>
                            <input type="text" class="form-control" placeholder="角色中文名" id="roleName" autofocus>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_group"></i></span>
                            <input type="text" class="form-control" placeholder="角色名" id="roleValue">
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_document"></i></span>
                            <input type="text" class="form-control" placeholder="描述" id="desc">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="invitationCodeMng.addOrUpdate()">提交</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal" id="buyCodeModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">购买邀请码</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" style="margin-top: 20px;">
                    <div class="form-group">
                        <label for="codeNum" class="col-sm-3 control-label">数量:</label>
                        <div class="col-sm-5">
                            <input type="number" class="form-control" id="codeNum" placeholder="请输入邀请码数量">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="invitationCodeMng.buyCode()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal" id="accreditModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="accreditModalLabel">授权</h4>
            </div>
            <div class="modal-body">
                <div id="treeview-checkable" class=""></div>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="invitationCodeMng.accredit()">确认</button>
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
<script src="${vendorDir}/datatables-plugins/Select/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${jsDir}/agency/invitecode4agency.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $(function () {
        invitationCodeMng.init();
    });
</script>
</body>
</html>
