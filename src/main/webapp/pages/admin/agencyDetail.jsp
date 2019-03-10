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
    </style>
</head>

<body >
<%--<div class="row">--%>
<%--<div class="row col-lg-12" >--%>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">代理推广明细</h3>
        </div>
        <div class="panel-body">
            <div class="form-group" style="float: right;">
                <button id="agencyRewardInfo" class="btn btn-primary btn-xs p310" onclick="orderinfo.rewardDetail()"><i class="icon_document_alt"></i> 收入详情</button>
                <button id="add" class="btn btn-primary btn-xs p310" onclick="orderinfo.withdrawDetail()"><i class="icon_document_alt"></i> 提现详情</button>
                <button id="delete" class="btn btn-danger btn-xs p310" onclick="orderinfo.showSureModal()"><i class="icon_trash_alt"></i> 删除代理</button>
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
<div id="container" style="width: 100%;height: 40%;">abc</div>

<div class="modal" id="rewardDetailModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="rewardDetailModalLabel">详情</h4>
            </div>
            <div class="modal-body">
                <table id="rewardDetailList" class="table table-striped table-bordered table-hover" width="100%">
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
<div class="modal" id="withdrawDetailModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="withdrawDetailModalLabel">详情</h4>
            </div>
            <div class="modal-body">
                <table id="withdrawDetailList" class="table table-striped table-bordered table-hover" width="100%">
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

<div class="modal" id="sureModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="sureDetailModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                确定删除？
            </div>
            <div class="modal-footer" <%--style="text-align: center;"--%>>
                <button type="button" class="btn btn-primary" onclick="orderinfo.delete()">确认</button>
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
<%--<script src="${vendorDir}/datatables-responsive/dataTables.responsive.js"></script>--%>
<script src="${vendorDir}/datatables-plugins/Select/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${jsDir}/admin/agencyDetail.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts.min.js"></script>
<script type="text/javascript">
    var currencyName = "${CurrenccyName}";
    $(function () {
        orderinfo.init();
    });
</script>
</body>
</html>
