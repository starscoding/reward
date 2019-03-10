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
    <link href="${awesomeCss}" rel="stylesheet"/>
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
            <h3 class="panel-title">资源管理</h3>
        </div>
        <div class="panel-body">
            <!-- search star -->
                <div class="form-group" style="float: right;">
                    <button id="addBrother" class="btn btn-primary btn-xs p310" onclick="resourceMng.addBrother()"><i class="icon_plus_alt"></i> 新增兄弟节点</button>
                    <button id="addSon" class="btn btn-primary btn-xs p310" onclick="resourceMng.addSon()"><i class="icon_plus_alt"></i> 新增子节点</button>
                    <button id="edit" class="btn btn-primary btn-xs p310" onclick="resourceMng.edit()"><i class="icon_pencil-edit_alt"></i> 编辑</button>
                    <button id="delete" class="btn btn-primary btn-xs p310" onclick="resourceMng.showSureModal()"><i class="icon_minus_alt"></i> 删除</button>
                    <button id="resh" class="btn btn-primary btn-xs p310" onclick="resourceMng.refresh()"><i class="icon_refresh"></i> 刷新</button>
                </div>
            
            <!-- 定义一个表格元素 -->
            <table id="userList" class="table table-striped" width="100%">
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

<!-- 模态框（Modal） -->
<div class="modal" id="addModal" tabindex="-1" role="dialog"  aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="addModalLabel"><i class=" icon_id_alt"></i>&nbsp;新增资源</h4>
            </div>
            <div class="modal-body">
                <form class="adduser-form" id="loginForm" action="" method="post">
                    <div class="login-wrap">
                        <div class="alert-error" style="color: #f67a6e" id="tipDiv"></div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_group"></i></span>
                            <input type="text" class="form-control" placeholder="中文名" id="resourceName" autofocus>
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_group"></i></span>
                            <input type="text" class="form-control" placeholder="值" id="resourceValue">
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_link"></i></span>
                            <input type="text" class="form-control" placeholder="URL" id="url">
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_tags_alt"></i></span>
                            <input type="text" class="form-control" placeholder="资源类型" id="type">
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_piechart"></i></span>
                            <input type="text" class="form-control" placeholder="图标" id="cls">
                        </div>
                        <div class="form-group input-group">
                            <span class="input-group-addon"><i class="icon_document"></i></span>
                            <input type="text" class="form-control" placeholder="描述" id="desc">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="text-align: center;" >
                <button type="button" class="btn btn-primary" onclick="resourceMng.addOrUpdate()">提交</button>
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
                <button type="button" class="btn btn-primary" onclick="resourceMng.delete()">确认</button>
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
<script src="${vendorDir}/datatables-plugins/dataTables.treeGrid.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${jsDir}/resourcemng/resourceMng.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript">
    $(function () {
        resourceMng.initTalble();
    });
</script>
</body>
</html>
