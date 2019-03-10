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
    <title>推广详情</title>
    <!-- Bootstrap CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="${elegantCss}" rel="stylesheet"/>
    <link href="${vendorDir}/datatables/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="${vendorDir}/datatables-plugins/Select/css/select.bootstrap.min.css" rel="stylesheet">
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <link href="${vendorDir}/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
        <h3 class="panel-title">推广详情</h3>
    </div>

    <div class="panel-body">
        <div class="row">
            <div class="col-lg-12 col-md-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <div class="row">
                            <div class="col-xs-9">
                                <div>
                                    <span style="font-size: 16px;">
                                    <i class="fa fa-bell"></i>
                                    营业总额：<span style="color: #ff7a12;font-size: 22px;" id="turnover">0</span> ${CurrenccyName}；
                                    推广总数：<span style="color: #ff7a12;font-size: 22px;" id="marketinfo">0</span> 个；
                                    今日营业额：<span style="color: #ff7a12;font-size: 22px;" id="todayTurnover">0</span> ${CurrenccyName}；
                                    今日推广数：<span style="color: #ff7a12;font-size: 22px;" id="todayMarketinfo">0</span> 个；
                                    </span>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
        <div class="row" >
            <div class="col-lg-12 col-md-9" >
                <div class="form-inline" >
                    <div class="form-group">
                        <label for="startTime">开始时间：</label>
                        <div class="input-group date form_datetime col-md-9" data-link-field="startTime" data-link-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" id="startTime" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <%--<input type="hidden" id="startTime" value="" /><br/>--%>
                    </div>
                    <div class="form-group">
                        <label for="endTime" >结束时间：</label>
                        <div class="input-group date form_datetime col-md-9"  data-link-field="endTime" data-link-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" id="endTime" value="" readonly>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <%--<input type="hidden" id="endTime" value="" /><br/>--%>
                    </div>
                    <button class="btn btn-primary" onclick="marketInfo.query()"><i class="fa fa-search"></i> 查询</button>
                </div>
            </div>
        </div>

        <!-- 定义一个表格元素 -->
        <table id="turnoverList" class="table table-striped table-bordered table-hover" width="100%" >
            <thead></thead>
            <tbody></tbody>
            <tfoot></tfoot>
            <!-- tbody是必须的 -->
        </table>

        <!-- 定义一个表格元素 -->
        <table id="userList" class="table table-striped table-bordered table-hover" width="100%" >
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
<div class="modal" id="sureModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">确认删除?</div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="invitationCodeMng.delete()">确认</button>
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
<script type="text/javascript" src="${vendorDir}/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${jsDir}/partner/marketInfo.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $(function () {
        marketInfo.init();
    });
</script>
</body>
</html>
