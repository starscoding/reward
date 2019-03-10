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
    <link href="${vendorDir}/datatables-plugins/Select/css/select.bootstrap.min.css" rel="stylesheet">
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <link href="${vendorDir}/datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <link href="${vendorDir}/videojs/css/video-js.min.css" rel="stylesheet">
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

<body oncontextmenu=self.event.returnValue=false>
<%--<div class="row">--%>
<%--<div class="row col-lg-12" >--%>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">打赏明细</h3>
        </div>
        <div class="panel-body">
            <%--<div>--%>
                <%----%>
            <%--</div>--%>
            <!-- search star -->
                <div class="row">
                    <div class="col-lg-12 col-md-6">
                        <div class="panel panel-primary">
                            <div class="panel-heading">
                                <div class="row">
                                    <div class="col-xs-9">

                                        <div>
                                            <span style="font-size: 16px;">
                                            <i class="fa fa-bell"></i>
                                            打赏收入：<span style="color: #ff7a12;font-size: 22px;" id="totalTurnover">0</span> ${CurrenccyName}；
                                            佣金收入：<span style="color: #ff7a12;font-size: 22px;" id="totalCommission">0</span> ${CurrenccyName}；
                                            今日打赏收入：<span style="color: #ff7a12;font-size: 22px;" id="todayTurnover">0</span> ${CurrenccyName}；
                                            今日佣金收入：<span style="color: #ff7a12;font-size: 22px;" id="todayCommission">0</span> ${CurrenccyName}；
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
                        <div class="form-inline">
                            <div class="form-group">
                                <label for="startTime">开始时间：</label>
                                <div class="input-group date form_datetime col-md-9" data-link-field="startTime">
                                    <input class="form-control" size="16" type="text" id="startTime" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <%--<input type="hidden" id="startTime" value="" /><br/>--%>
                            </div>
                            <div class="form-group">
                                <label for="endTime" >结束时间：</label>
                                <div class="input-group date form_datetime col-md-9"  data-link-field="endTime">
                                    <input class="form-control" size="16" type="text" id="endTime" value="" readonly>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                                </div>
                                <%--<input type="hidden" id="endTime" value="" /><br/>--%>
                            </div>
                            <button class="btn btn-primary" onclick="orderinfo.refresh()"><i class="fa fa-search"></i> 查询</button>
                        </div>
                    </div>
                </div>
                <%--<div class="form-group" style="float: right;">--%>
                    <%--<button id="resh" class="btn btn-primary btn-xs p310" onclick="userMng.refresh()"><i class="icon_refresh"></i> 刷新</button>--%>
                <%--</div>--%>
            
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

<div class="modal" id="videoModal" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" onclick="orderinfo.clearVideo()" aria-hidden="true">&times;</button>
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
            <div class="modal-footer" style="text-align: center;" >
                <%--<button type="button" class="btn btn-primary" onclick="resourceMng.delete()">确认</button>--%>
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>--%>
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
<script type="text/javascript" src="${vendorDir}/datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${vendorDir}/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="${vendorDir}/videojs/js/video.min.js"></script>
<script type="text/javascript" src="${jsDir}/orderinfo/orderinfo4agency.js"></script>
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
        videojs.options.flash.swf = "../../static/vendor/videojs/js/video-js.swf";
        orderinfo.init();
    });
</script>
</body>
</html>
