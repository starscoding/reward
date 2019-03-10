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
        <div class="panel panel-primary">
            <div class="panel-heading">
                <div class="row">
                    <div class="col-xs-9">

                        <div>
                            <span style="font-size: 16px;">
                            <i class="icon_volume-high"></i>
                            推广数量：<span style="color: #ff7a12;font-size: 22px;" id="marketinfo">0</span> 个；
                            </span>
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
<!-- JQuery js-->
<script type="text/javascript" src="${jqueryJs}"></script>
<script type="text/javascript" src="${bootstrapJs}"></script>
<script type="text/javascript" src="${dataTablesJs}"></script>
<script type="text/javascript" src="${dataTables4bJs}"></script>
<script src="${vendorDir}/datatables-plugins/Select/js/dataTables.select.min.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${jsDir}/agency/marketInfo.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $(function () {
        marketInfo.init();
    });
</script>
</body>
</html>
