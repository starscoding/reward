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
    <title>生成链接</title>
    <!-- Bootstrap CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="${elegantCss}" rel="stylesheet"/>
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <link href="${vendorDir}/datatables/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="${vendorDir}/datatables-plugins/Select/css/select.bootstrap.min.css" rel="stylesheet">
    <link href="${vendorDir}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/buttons/1.5.1/css/buttons.bootstrap.min.css"/>
    <link href="${vendorDir}/videojs/css/video-js.min.css" rel="stylesheet">
    <style type="text/css">
        #videoList_info {
            float: left;
        }

        #videoList_length {
            float: left;
            margin-top: 7px;
        }

        #videoList_paginate {
            float: right;
            margin-top: 7px;
        }

        #videoList_filter {
            float: left;
        }

        #privateVideoList_info {
            float: left;
        }

        #privateVideoList_length {
            float: left;
            margin-top: 7px;
        }

        #privateVideoList_paginate {
            float: right;
            margin-top: 7px;
        }

        #privateVideoList_filter {
            float: left;
        }
        .buttons-select-none{
            top:10px;
        }
        .buttons-select-all{
            top:10px;
        }
    </style>
</head>

<body oncontextmenu=self.event.returnValue=false>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">生成链接</h3>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#publicVideo" data-toggle="tab">公共片库</a>
                    </li>
                    <li><a href="#privateVideo" data-toggle="tab">私有片库</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="publicVideo">
                        <div class="form-group" style="float: right;margin-top: 10px;">
                            <button id="add" class="btn btn-warning btn-s" onclick="createLink.createLinks('0')"><i
                                    class="fa fa-link"></i> 生成链接
                            </button>
                        </div>
                        <table id="videoList" class="table table-bordered table-hover" width="100%">
                            <thead>
                            <tr></tr>
                            </thead>
                            <tbody></tbody>
                            <tfoot></tfoot>
                        </table>
                    </div>
                    <div class="tab-pane fade" id="privateVideo">
                        <div class="form-group" style="float: right;margin-top: 10px;">
                            <button id="upload" class="btn btn-primary btn-s"
                                    onclick="createLink.showUploadModal()"><i
                                    class="icon_upload"></i> 上传
                            </button>
                            <button id="createLink" class="btn btn-warning btn-s"
                                    onclick="createLink.createLinks('1')"><i
                                    class="fa fa-link"></i> 生成链接
                            </button>
                        </div>
                        <table id="privateVideoList" class="table table-striped table-bordered table-hover"
                               width="100%">
                            <thead>
                            <tr></tr>
                            </thead>
                            <tbody></tbody>
                            <tfoot></tfoot>
                        </table>
                    </div>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-6 -->
</div>

<!-- 模态框（Modal） -->
<div class="modal" id="uploadModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="addModalLabel"><i class=" icon_id_alt"></i>&nbsp;上传视频</h4>
                <h5 class="modal-title" style="color: #ff0000"><i class="glyphicon glyphicon-hand-right"></i>视频名称默认为文件名称，上传后不可更改</h5>
            </div>
            <div class="modal-body">
                <input type="file" id="uploadFile" name="uploadFile" multiple="multiple">
                <input type="hidden" id="reqType">
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="createLink.upload()">上传</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal" id="videoModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="createLink.clearVideo()"
                        aria-hidden="true">&times;</button>
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

<div class="modal" id="processing" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
            </div>
            <div class="modal-body">
                <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-success" role="progressbar"
                         aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;" id="progress">
                        <i class="fa fa-spinner fa-pulse" style="font-size: 20px;margin-right: 10px;"></i>
                        上传中,已完成<span id="son">40% 完成</span>
                    </div>
                </div>
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
<script type="text/javascript" src="${vendorDir}/videojs/js/video.min.js"></script>
<script type="text/javascript" src="${jsDir}/linkmng/createLink.js"></script>
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
        createLink.init();
    });
</script>
</body>
</html>
