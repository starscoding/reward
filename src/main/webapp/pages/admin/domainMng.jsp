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
    <title>打赏明细</title>
    <!-- Bootstrap CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="${vendorDir}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${elegantCss}" rel="stylesheet"/>
    <link href="${vendorDir}/datatables/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="${vendorDir}/jPlayer/dist/skin/pink.flag/css/jplayer.pink.flag.min.css" rel="stylesheet" type="text/css" />
    <link href="${vendorDir}/datatables-plugins/Select/css/select.bootstrap.min.css" rel="stylesheet">
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <link href="${distDir}/css/sb-admin-2.css" rel="stylesheet">
    <link href="${vendorDir}/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
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
        #rewardDetailList_info{
            float: left;
        }
        #rewardDetailList_length{
            float: left;
            margin-top: 7px;
        }
        #rewardDetailList_paginate{
            float: right;
            margin-top: 7px;
        }
        #rewardDetailList_filter{
            float: left;
        }
        #withdrawDetailList_info{
            float: left;
        }
        #withdrawDetailList_length{
            float: left;
            margin-top: 7px;
        }
        #withdrawDetailList_paginate{
            float: right;
            margin-top: 7px;
        }
        #withdrawDetailList_filter{
            float: left;
        }
        #userDetailList_info{
            float: left;
        }
        #userDetailList_length{
            float: left;
            margin-top: 7px;
        }
        #userDetailList_paginate{
            float: right;
            margin-top: 7px;
        }
        #userDetailList_filter{
            float: left;
        }
    </style>
</head>

<body >
<%--<div class="row">--%>
<%--<div class="row col-lg-12" >--%>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">域名管理</h3>
        </div>
        <div class="panel-body">
            <div class="form-group" style="float: right;">
                <button id="add" class="btn btn-primary btn-xs p310" onclick="domainMng.showEditModal('add')"><i class="icon_plus_alt"></i> 新增</button>
                <button id="edit" class="btn btn-primary btn-xs p310" onclick="domainMng.showEditModal('edit')"><i class="icon_pencil-edit_alt"></i> 编辑</button>
                <button id="delete" class="btn btn-danger btn-xs p310" onclick="domainMng.showSureModal()"><i class="icon_trash_alt"></i> 删除</button>
                <button id="use" class="btn btn-success btn-xs p310" onclick="domainMng.use()"><i class="glyphicon glyphicon-ok-sign"></i> 启用</button>
                <button id="ban" class="btn btn-danger btn-xs p310" onclick="domainMng.ban()"><i class="glyphicon glyphicon-minus-sign"></i> 禁用</button>
            </div>
            <!-- 定义一个表格元素 -->
            <table id="userList" class="table table-striped table-bordered table-hover" width="100%">
                <thead>
                <tr>
                </tr>
                </thead>
                <tbody>
                </tbody>
                <tfoot></tfoot>
                <!-- tbody是必须的 -->
            </table>
        </div>
    </div>

<div class="modal" id="editModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="rewardDetailModalLabel">编辑信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" style="margin-top: 20px;">
                    <div class="form-group">
                        <label for="domain" class="col-sm-3 control-label">域名:</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="domain" placeholder="例如：www.baidu.com">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" <%--style="text-align: center;"--%>>
                <button type="button" class="btn btn-primary" onclick="domainMng.addOrUpdate()">确认</button>
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
                <h4 class="modal-title" id="withdrawDetailModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                确定删除？
            </div>
            <div class="modal-footer" <%--style="text-align: center;"--%>>
                <button type="button" class="btn btn-primary" onclick="domainMng.delete()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal" id="userDetailModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="userDetailModalLabel">详情</h4>
            </div>
            <div class="modal-body">
                <table id="userDetailList" class="table table-striped table-bordered table-hover" width="100%">
                    <thead></thead>
                    <tbody></tbody>
                    <tfoot></tfoot>
                </table>
            </div>
            <div class="modal-footer" <%--style="text-align: center;"--%>>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>



<!-- JQuery js-->
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${dataTablesJs}"></script>
<script type="text/javascript" src="${dataTables4bJs}"></script>
<%--<script src="${vendorDir}/datatables-responsive/dataTables.responsive.js"></script>--%>
<script src="${vendorDir}/datatables-plugins/Select/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${jsDir}/admin/domainMng.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    var currencyName = "${CurrenccyName}";
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
        domainMng.init();
    });
</script>
</body>
</html>
