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
    <title>视频管理</title>
    <!-- Bootstrap CSS -->
    <link href="${bootstrapCss}" rel="stylesheet">
    <link href="${elegantCss}" rel="stylesheet"/>
    <link href="${vendorDir}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${vendorDir}/datatables/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="${vendorDir}/datatables-plugins/Select/css/select.bootstrap.min.css" rel="stylesheet">
    <%--<link href="${vendorDir}/jPlayer/dist/skin/pink.flag/css/jplayer.pink.flag.min.css" rel="stylesheet" type="text/css" />--%>
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <link href="${vendorDir}/videojs/css/video-js.min.css" rel="stylesheet">
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

        .modal-dialog {
            margin: 100px auto;
        }

        .vjs-paused .vjs-big-play-button,
        .vjs-paused.vjs-has-started .vjs-big-play-button {
            display: block;
        }
    </style>
</head>

<body oncontextmenu=self.event.returnValue=false>
<%--<div class="row">--%>
<%--<div class="row col-lg-12" >--%>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">视频管理</h3>
    </div>
    <div class="panel-body">
        <!-- search star -->
        <div class="form-group" style="float: right;">
            <button id="add" class="btn btn-primary btn-xs p310" onclick="videoMng.showUploadModal()"><i
                    class="icon_upload"></i> 上传
            </button>
            <button id="edit" class="btn btn-danger btn-xs p310" onclick="videoMng.showSureModal()"><i
                    class="icon_minus_alt"></i> 删除
            </button>
            <button id="refresh" class="btn btn-primary btn-xs p310" onclick="videoMng.refresh()"><i
                    class="icon_minus_alt"></i> 刷新
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
<div class="modal" id="uploadModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="addModalLabel"><i class=" icon_id_alt"></i>&nbsp;上传视频</h4>
            </div>
            <div class="modal-body">
                <input type="file" id="uploadFile" name="uploadFile" multiple="multiple">
                <input type="hidden" id="reqType">
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="videoMng.upload()">上传</button>
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
                <button type="button" class="btn btn-primary" onclick="videoMng.delete()">确认</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

<div class="modal" id="processing" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <%--<div class="modal-header">--%>
            <%--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
            <%--</div>--%>
            <div class="modal-body">
                <div class="progress progress-striped active">
                    <div class="progress-bar progress-bar-success" role="progressbar"
                         aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"
                         style="width: 0%;" id="progress">
                        <i class="fa fa-spinner fa-pulse" style="font-size: 20px;margin-right: 10px;"></i>
                        <span id="son">40% 完成</span>
                    </div>
                </div>
                <%--<i class="fa fa-spinner fa-pulse" style="font-size: 20px;margin-right: 10px;"></i>上传中，请稍后......--%>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>


<div class="modal" id="videoModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="videoMng.clearVideo()" aria-hidden="true">&times;</button>
                <h6 class="modal-title" id="videotitle">提示</h6>
            </div>
            <div class="modal-body">
                <%--<div id="jp_container_1" class="jp-video jp-video-270p" role="application" aria-label="media player">--%>
                <%--<div class="jp-type-single">--%>
                <%--<div id="videoPlayer" class="jp-jplayer"></div>--%>
                <%--<div class="jp-gui">--%>
                <%--<div class="jp-video-play">--%>
                <%--<button class="jp-video-play-icon" role="button" tabindex="0">play</button>--%>
                <%--</div>--%>
                <%--<div class="jp-interface">--%>
                <%--<div class="jp-progress">--%>
                <%--<div class="jp-seek-bar">--%>
                <%--<div class="jp-play-bar"></div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>--%>
                <%--<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>--%>
                <%--<div class="jp-details">--%>
                <%--<div class="jp-title" aria-label="title">&nbsp;</div>--%>
                <%--</div>--%>
                <%--<div class="jp-controls-holder">--%>
                <%--<div class="jp-volume-controls">--%>
                <%--<button class="jp-mute" role="button" tabindex="0">mute</button>--%>
                <%--<button class="jp-volume-max" role="button" tabindex="0">max volume</button>--%>
                <%--<div class="jp-volume-bar">--%>
                <%--<div class="jp-volume-bar-value"></div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="jp-controls">--%>
                <%--<button class="jp-play" role="button" tabindex="0">play</button>--%>
                <%--<button class="jp-stop" role="button" tabindex="0">stop</button>--%>
                <%--</div>--%>
                <%--<div class="jp-toggles">--%>
                <%--<button class="jp-repeat" role="button" tabindex="0">repeat</button>--%>
                <%--<button class="jp-full-screen" role="button" tabindex="0">full screen</button>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--<div class="jp-no-solution">--%>
                <%--<span>Update Required</span>--%>
                <%--To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.--%>
                <%--</div>--%>
                <%--</div>--%>
                <%--</div>--%>
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
            <%--<div class="modal-footer" style="text-align: center;" >--%>
            <%--<button type="button" class="btn btn-primary" onclick="resourceMng.delete()">确认</button>--%>
            <%--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
            <%--</div>--%>
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
<script type="text/javascript" src="${vendorDir}/filestyle/bootstrap-filestyle.min.js"></script>
<script type="text/javascript" src="${vendorDir}/jPlayer/dist/jplayer/jquery.jplayer.min.js"></script>
<script type="text/javascript" src="${vendorDir}/toastr/toastr.min.js"></script>
<script type="text/javascript" src="${vendorDir}/videojs/js/video.min.js"></script>
<script type="text/javascript" src="${jsDir}/videoMng/videoMng.js"></script>
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
        videojs.options.flash.swf = "../../static/vendor/videojs/js/video-js.swf";
//        $("#videoModal").draggable({
//            handle: ".modal-header",
//            cursor: 'move',
//            refreshPositions: false
//        });
        videoMng.initTalble();
    });
</script>
</body>
</html>
