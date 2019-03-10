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
    <link href="${vendorDir}/videojs/css/video-js.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/buttons/1.5.1/css/buttons.bootstrap.min.css"/>

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

        #detailList_info {
            float: left;
        }

        #detailList_length {
            float: left;
            margin-top: 7px;
        }

        #detailList_paginate {
            float: right;
            margin-top: 7px;
        }

        #detailList_filter {
            float: left;
        }


    </style>
</head>

<body oncontextmenu=self.event.returnValue=false>
<%--<div class="row">--%>
<%--<div class="row col-lg-12" >--%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">链接管理</h3>
    </div>
    <div class="panel-body">
        <!-- search star -->
        <div class="form-group" style="float: right;">
            <%--<button id="add" class="btn btn-danger btn-xs p310" onclick="invitationCodeMng.add()"><i--%>
            <%--class="icon_trash_alt"></i> 删除--%>
            <%--</button>--%>
            <button id="export" class="btn btn-info btn-s" onclick="linkmng.export()"><i
                    class="icon_documents"></i> 批量导出
            </button>
            <button id="export" class="btn btn-info btn-s" onclick="linkmng.showPriceModal()"><i
                    class="icon_currency_alt"></i> 设置价格
            </button>
            <button id="export" class="btn btn-danger btn-s" onclick="linkmng.showSureModal()"><i
                    class="icon_trash"></i> 删除
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

<div class="modal" id="videoModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="linkmng.clearVideo()" aria-hidden="true">&times;</button>
                <h6 class="modal-title" id="videotitle">提示</h6>
            </div>
            <div class="modal-body">
                <video id="videoplay" class="video-js vjs-default-skin vjs-big-play-centered" controls preload="none"
                       width="640" height="264"
                       poster=""
                       data-setup="{}">
                    <source src="http://localhost:8080/reward/video/A9EBB75E4316895F42EF65A4ABB29F49" id='videomp4'
                            type='video/mp4'/>
                    <%--<source src="http://localhost:8080/reward/video/8E0360562F1C44272CEDDA429E32BA58" type='video/webm' />--%>
                    <%--<source src="http://localhost:8080/reward/video/8E0360562F1C44272CEDDA429E32BA58" type='video/ogg' />--%>
                    <%--<source src="http://视频地址格式2.webm" type='video/webm' />--%>
                    <%--<source src="http://视频地址格式3.ogv" type='video/ogg' />--%>
                </video>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <%--<button type="button" class="btn btn-primary" onclick="resourceMng.delete()">确认</button>--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal" id="detailModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="detailModalLabel">详情</h4>
            </div>
            <div class="modal-body">
                <table id="detailList" class="table table-striped table-bordered table-hover" width="100%">
                    <thead></thead>
                    <tbody></tbody>
                    <tfoot></tfoot>
                </table>
            </div>
            <div class="modal-footer" <%--style="text-align: center;"--%>>
                <%--<button type="button" class="btn btn-primary" onclick="invitationCodeMng.delete()">确认</button>--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
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
                <button type="button" class="btn btn-primary" onclick="linkmng.delete()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal" id="LinksModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="linkModalLabel">复制链接</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <textarea class="form-control" rows="10" id="links"></textarea>
                </div>
                <form class="form-inline">
                    <div class="form-group">
                        <label for="sensitiveWord">敏感词</label>
                        <input type="text" class="form-control" id="sensitiveWord" placeholder="敏感词">
                    </div>
                    <div class="form-group">
                        <label for="noSensitiveWord">替换为</label>
                        <input type="email" class="form-control" id="noSensitiveWord" placeholder="非敏感词">
                    </div>
                    <button type="button" class="btn btn-primary" onclick="linkmng.replaceWord();">替换</button>
                </form>

            </div>
            <div class="modal-footer" <%--style="text-align: center;"--%>>
                <%--<button type="button" class="btn btn-primary" id="copy">复制</button>--%>
                <button class="btn btn-primary" data-clipboard-action="copy" data-clipboard-target="#links">复制</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal" id="priceModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="priceModalLabel">编辑信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" style="margin-top: 20px;">
                    <div class="form-group">
                        <label for="price" class="col-sm-3 control-label">价格(${CurrenccyName}):</label>
                        <div class="col-sm-5">
                            <input type="number" class="form-control" id="price" placeholder="">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" <%--style="text-align: center;"--%>>
                <button type="button" class="btn btn-primary" onclick="linkmng.updatePrice()">确认</button>
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
<script type="text/javascript" src="${vendorDir}/videojs/js/video.min.js"></script>
<script type="text/javascript" src="${vendorDir}/zclip/jquery.zclip.js"></script>
<script type="text/javascript" src="${vendorDir}/clipboard/clipboard.min.js"></script>
<script type="text/javascript" src="${jsDir}/linkmng/linkmng.js"></script>
<script type="text/javascript" src="${jsDir}/common/Utils.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/dataTables.buttons.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/buttons/1.5.1/js/buttons.bootstrap.min.js"></script>
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
        linkmng.init();
    });
</script>
</body>
</html>
