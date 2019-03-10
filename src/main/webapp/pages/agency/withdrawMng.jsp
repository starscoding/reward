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
        <h3 class="panel-title">提现管理</h3>
    </div>
    <div class="panel-body">
        <div class="panel panel-warning">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-12">
                        <div>
                            <span style="font-size: 16px;" id="withdrawNotice">
                            <%--<i class="icon_volume-high"></i>--%>
                            <%--夜间不处理任何提现，提现到账时间每天<span style="color: #ff7a12;font-size: 22px;" >中午12点到13点统一处理。</span>--%>
                                <%--请各位代理填好个人支付信息，为了你的安全，<span style="color: #ff7a12;font-size: 22px;" >超过2000元请联系客服安全提现！</span>--%>
                                <%--客服微信：<span style="color: #ff7a12;font-size: 22px;" >mao10202717</span>,客服QQ:<span style="color: #ff7a12;font-size: 22px;" >123</span>--%>
                            </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="panel panel-info">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-12">
                        <div>
                            <span style="font-size: 16px;">
                                可提现金额：<span style="color: #ff7a12;font-size: 22px;" id="balance">0￥</span>
                            </span>
                            <button type="button" class="btn btn-primary" onclick="withdrawMng.showModal()">申请提现</button>
                        </div>
                    </div>
                </div>
            </div>
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
                <h4 class="modal-title" id="addModalLabel"><i class=" icon_id_alt"></i>&nbsp;提现申请</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" enctype="multipart/form-data" method="post" id="form1"
                      action="/configService/addOrUpdatePayConf">
                    <div class="form-group">
                        <div class="alert-error" style="color: #f67a6e" id="tipDiv"></div>
                        <label for="alipayAccount" class="col-sm-3 control-label">支付宝账号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="alipayAccount" name="alipayAccount" placeholder="请输入支付宝账号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">支付宝真实姓名:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name"
                                   placeholder="请输入支付宝真实姓名">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="amount" class="col-sm-3 control-label">提现金额:</label>
                        <div class="col-sm-9">
                            <input type="number" class="form-control" id="amount" name="amount" placeholder="请输入提现金额">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fethpwd" class="col-sm-3 control-label">提现密码:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="fethpwd" name="fethpwd"
                                   placeholder="请输入提现密码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="QRCode" class="col-sm-3 control-label">二维码图片:</label>
                        <div class="col-sm-9">
                            <input type="file" id="QRCode" name="QRCode" accept="image/*" onchange="withdrawMng.preview(this.value)" />
                        </div>
                    </div>
                    <div class="form-group">
                        <%--<label for="QRCode" class="col-sm-3 control-label">二维码图片:</label>--%>
                        <div class="col-sm-9" style="text-align: center;left: 40%;">
                            <img src="#" style="display: none;width: 150px;height: 150px;" id = "codeImg">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="withdrawMng.add()">提交</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<!-- JQuery js-->
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${dataTablesJs}"></script>
<script type="text/javascript" src="${dataTables4bJs}"></script>
<script src="${vendorDir}/datatables-plugins/Select/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${vendorDir}/filestyle/bootstrap-filestyle.min.js"></script>
<script type="text/javascript" src="${jsDir}/agency/withdrawMng.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $(function () {
        toastr.options = {
            closeButton: false,
            debug: false,
            progressBar: false,
            positionClass: "toast-top-center",
            onclick: null,
            showDuration: "300",
            hideDuration: "1000",
            timeOut: "5000",
            extendedTimeOut: "1000",
            showEasing: "swing",
            hideEasing: "linear",
            showMethod: "fadeIn",
            hideMethod: "fadeOut"
        };
        withdrawMng.init();
    });
</script>
</body>
</html>
