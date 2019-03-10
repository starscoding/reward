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
    <link href="${vendorDir}/toastr/toastr.min.css" rel="stylesheet">
    <link href="${vendorDir}/datatables/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    <link href="${vendorDir}/datatables-plugins/Select/css/select.bootstrap.min.css" rel="stylesheet">
    <link href="${vendorDir}/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <style type="text/css">
        #payConfList_info {
            float: left;
        }

        #payConfList_length {
            float: left;
            margin-top: 7px;
        }

        #payConfList_paginate {
            float: right;
            margin-top: 7px;
        }

        #payConfList_filter {
            float: left;
        }
    </style>
</head>

<body>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">系统配置</h3>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <!-- Nav tabs -->
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#payconfig" data-toggle="tab">支付参数</a>
                    </li>
                    <li><a href="#rewardconfig" data-toggle="tab" onclick="sysconfig.getAgencyConf()">代理级别返佣</a>
                    </li>
                    <li><a href="#sysconfig" data-toggle="tab" onclick="sysconfig.getSysConf()">系统参数</a>
                    </li>
                    <li><a href="#autopayconfig" data-toggle="tab">提现微信参数</a>
                    </li>
                    <li><a href="#shorturl" data-toggle="tab" onclick="sysconfig.getShiedlPayConf()">三方支付配置</a>
                    </li>
                    <li><a href="#otherConf" data-toggle="tab" onclick="sysconfig.getFilePathConf()">其他参数</a>
                    </li>
                </ul>

                <!-- Tab panes -->
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="payconfig">
                        <div class="form-group" style="float: right;margin-top: 10px;">
                            <button id="add" class="btn btn-primary btn-xs p310" onclick="sysconfig.add()"><i
                                    class="icon_plus_alt"></i> 新增
                            </button>
                            <button id="edit" class="btn btn-primary btn-xs p310" onclick="sysconfig.edit()"><i
                                    class="icon_pencil-edit_alt"></i> 编辑
                            </button>
                            <button id="on" class="btn btn-success btn-xs p310" onclick="sysconfig.use()"><i
                                    class="glyphicon glyphicon-ok-sign"></i> 启用
                            </button>
                            <button id="off" class="btn btn-warning btn-xs" onclick="sysconfig.disable()"><i
                                    class="glyphicon glyphicon-minus-sign"></i> 禁用
                            </button>
                            <button id="delete" class="btn btn-danger btn-xs p310" onclick="sysconfig.showSureModal()">
                                <i class="icon_minus_alt"></i> 删除
                            </button>
                            <button id="resh" class="btn btn-info btn-xs p310" onclick="sysconfig.refresh()"><i
                                    class="icon_refresh"></i> 刷新
                            </button>
                        </div>
                        <table id="payConfList" class="table table-striped table-bordered table-hover" width="100%">
                            <thead>
                            <tr></tr>
                            </thead>
                            <tbody></tbody>
                            <tfoot></tfoot>
                        </table>
                    </div>
                    <div class="tab-pane fade" id="rewardconfig">
                        <form class="form-horizontal" role="form" style="margin-top: 20px;">
                            <div class="form-group">
                                <label for="effectiveJunior" class="col-sm-4 control-label">有效下级:</label>
                                <div class="col-sm-4">
                                    营业额满 <input type="number" id="effectiveJunior" size="10"> ${CurrenccyName}为有效下级
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="generalReward" class="col-sm-4 control-label">普通代理:</label>
                                <div class="col-sm-4">
                                    佣金 <input type="number" id="generalReward" size="10">%
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="primaryAgency" class="col-sm-4 control-label">初级代理:</label>
                                <div class="col-sm-6">
                                    满 <input type="number" id="primaryAgency" size="10"> 个有效下级自动升级，佣金
                                    <input type="number" id="primaryReward" size="10">%
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="advancedAgency" class="col-sm-4 control-label">高级代理:</label>
                                <div class="col-sm-6">
                                    满 <input type="number" id="advancedAgency" size="10"> 个有效下级自动升级，佣金
                                    <input type="number" id="advancedReward" size="10">%
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-5 col-sm-4">
                                    <button type="button" class="btn btn-primary"
                                            onclick="sysconfig.updateAgencyConf()">修改
                                    </button>
                                    <button type="button" class="btn btn-default" style="margin-left: 20px;">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="sysconfig">
                        <form class="form-horizontal" style="margin-top: 20px;">
                            <div class="form-group" >
                                <label for="autowithdraw" class="col-sm-3 control-label">维护设置:</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline">
                                        <input type="radio" name="maintainflag" id="openM" value="0"> 开启维护
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="maintainflag" id="cosleM" value="1"
                                               checked> 关闭维护
                                    </label>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="safeDomain" class="col-sm-3 control-label">安全域名:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="safeDomain">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="authDomain" class="col-sm-3 control-label">接口鉴权域名:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="authDomain">
                                </div>
                            </div>
                            <div class="form-group" style="display: none">
                                <label for="jumpDomain" class="col-sm-3 control-label">跳转域名:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="jumpDomain" disabled>
                                </div>
                            </div>
                            <div class="form-group" style="display: none">
                                <label for="randomStrExpireDomain" class="col-sm-3 control-label">随机字符过期域名:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="randomStrExpireDomain" disabled>
                                    <div>[👆验证随机字符超过时间跳转的域名]</div>
                                </div>
                            </div>
                            <div class="form-group" style="display: none">
                                <label for="videoDomain" class="col-sm-3 control-label">片库域名:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="videoDomain" disabled>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="invitationCode" class="col-sm-3 control-label">邀请码价格:</label>
                                <div class="col-sm-3">
                                    <input type="number" class="form-control" id="invitationCode">
                                </div>
                                <div class="col-sm-1" style="margin-top: 5px;margin-left: -15px;">
                                    ${CurrenccyName}
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="invitationCondition" class="col-sm-3 control-label">邀请码条件:</label>
                                <div class="col-sm-3">
                                    <input type="number" class="form-control" id="invitationCondition">
                                    <div>[👆营业额满足数量]</div>
                                </div>
                                <div class="col-sm-1" style="margin-top: 5px;margin-left: -15px;">
                                    ${CurrenccyName}
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="kfWechat" class="col-sm-3 control-label">客服微信:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="kfWechat">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="withdraw" class="col-sm-3 control-label">提现扣除佣金:</label>
                                <div class="col-sm-3">
                                    <input type="number" class="form-control" id="withdraw">
                                </div>
                                <div class="col-sm-1" style="margin-top: 5px;margin-left: -15px;">
                                    %
                                </div>
                            </div>
                            <div class="form-group" style="display: none">
                                <label for="autowithdraw" class="col-sm-3 control-label">提现设置:</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline">
                                        <input type="radio" name="withdrawConf" id="autowithdraw" value="auto"> 自动提现
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="withdrawConf" id="manulwithdraw" value="manul"
                                               checked> 人工审核
                                    </label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">公告:</label>
                                <div class="col-sm-9" id="editor">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">邀请码说明:</label>
                                <div class="col-sm-5" id="codeEditor">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-3 control-label">提现说明:</label>
                                <div class="col-sm-5" id="withdrawEditor" >
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-3">
                                    <button type="button" class="btn btn-primary"
                                            onclick="sysconfig.updateSysConf()">修改
                                    </button>
                                    <button type="button" class="btn btn-default" style="margin-left: 20px;">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="autopayconfig">
                        <form class="form-horizontal" style="margin-top: 20px;">
                            <div class="form-group">
                                <label for="appid_withdraw" class="col-sm-3 control-label">AppId:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="appid_withdraw">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="bizAccount_withdraw" class="col-sm-3 control-label">商户号:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="bizAccount_withdraw">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="key_withdraw" class="col-sm-3 control-label">支付密钥:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="key_withdraw">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="appsecret_withdraw" class="col-sm-3 control-label">AppSecret:</label>
                                <div class="col-sm-3">
                                    <input type="text" class="form-control" id="appsecret_withdraw">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-3">
                                    <button type="button" class="btn btn-primary"
                                            onclick="sysconfig.updateAgencyConf()">修改
                                    </button>
                                    <button type="reset" class="btn btn-default" style="margin-left: 20px;">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="shorturl">
                        <form class="form-horizontal" style="margin-top: 20px;">

                            <div class="form-group">
                                <label for="autowithdraw" class="col-sm-3 control-label">支付方式:</label>
                                <div class="col-sm-3">
                                    <label class="radio-inline">
                                        <input type="radio" name="payType" id="weixin" value="weixin"> 微信支付
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="payType" id="shield" value="shield"
                                               checked> 盾牌
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="payType" id="cloud" value="cloud"> 云支付
                                    </label>
                                </div>
                            </div>
                            <fieldset >
                                <legend >盾牌支付配置</legend>
                                <div class="form-group">
                                    <label for="shieldUrl" class="col-sm-3 control-label">接口地址:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="shieldUrl">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="shieldAppId" class="col-sm-3 control-label">APPID:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="shieldAppId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="shieldKey" class="col-sm-3 control-label">KEY:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="shieldKey">
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset>
                                <legend >云支付配置</legend>
                                <div class="form-group">
                                    <label for="cloudUrl" class="col-sm-3 control-label">接口地址:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="cloudUrl">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="cloudName" class="col-sm-3 control-label">商户名称:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="cloudName">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="cloudAppId" class="col-sm-3 control-label">商户号:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="cloudAppId">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="cloudKey" class="col-sm-3 control-label">商户密钥:</label>
                                    <div class="col-sm-3">
                                        <input type="text" class="form-control" id="cloudKey">
                                    </div>
                                </div>
                            </fieldset>

                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-3">
                                    <button type="button" class="btn btn-primary"
                                            onclick="sysconfig.updateShieldPayConf()">修改
                                    </button>
                                    <button type="reset" class="btn btn-default" style="margin-left: 20px;">重置</button>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="tab-pane fade" id="otherConf">
                        <form class="form-horizontal" style="margin-top: 20px;">
                            <div class="form-group">
                                <label for="authTxtPath" class="col-sm-3 control-label">验证文件路径:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="authTxtPath">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="videoPath" class="col-sm-3 control-label">视频路径:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="videoPath">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="videoImgPath" class="col-sm-3 control-label">视频缩略图路径:</label>
                                <div class="col-sm-5">
                                    <input type="text" class="form-control" id="videoImgPath">
                                </div>
                            </div>
                            <%--<div class="form-group">--%>
                            <%--<label for="autowithdraw" class="col-sm-3 control-label">支付方式:</label>--%>
                            <%--<div class="col-sm-3">--%>
                            <%--<label class="radio-inline">--%>
                            <%--<input type="radio" name="payType" id="weixin" value="weixin"> 微信支付--%>
                            <%--</label>--%>
                            <%--<label class="radio-inline">--%>
                            <%--<input type="radio" name="payType" id="shield" value="shield"--%>
                            <%--checked> 第三方支付--%>
                            <%--</label>--%>
                            <%--</div>--%>
                            <%--</div>--%>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-3">
                                    <button type="button" class="btn btn-primary"
                                            onclick="sysconfig.updatePathConf()">修改
                                    </button>
                                    <button type="reset" class="btn btn-default" style="margin-left: 20px;">重置</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-6 -->
</div>
<!-- /.row -->
<!-- 模态框（Modal） -->
<div class="modal" id="addModal" tabindex="-1" role="dialog" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="addModalLabel"><i class=" icon_id_alt"></i>&nbsp;编辑信息</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" enctype="multipart/form-data" method="post" id="form1"
                      action="/configService/addOrUpdatePayConf">
                    <div class="form-group">
                        <div class="alert-error" style="color: #f67a6e" id="tipDiv"></div>
                        <label for="appId" class="col-sm-3 control-label">AppId:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="appId" name="appId" placeholder="请输入AppId">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="appSecret" class="col-sm-3 control-label">AppSecret:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="appSecret" name="appSecret"
                                   placeholder="请输入AppSecret">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="comment" class="col-sm-3 control-label">接口备注:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="comment" name="comment" placeholder="请输入接口备注">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="bizAccount" class="col-sm-3 control-label">商户号:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="bizAccount" name="bizAccount"
                                   placeholder="请输入商户号">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="key" class="col-sm-3 control-label">密钥KEY:</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="key" name="key" placeholder="请输入密钥KEY">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="appSecret" class="col-sm-3 control-label">验证Txt:</label>
                        <div class="col-sm-9">
                            <input type="file" id="uploadFile" name="uploadFile">
                            <input type="hidden" id="reqType">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer" style="text-align: center;">
                <button type="button" class="btn btn-primary" onclick="sysconfig.addOrUpdate()">提交</button>
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
                <button type="button" class="btn btn-primary" onclick="sysconfig.delete()">确认</button>
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
<%--<script type="text/javascript" charset="utf-8" src="${vendorDir}/umeditor/umeditor.config.js"></script>--%>
<%--<script type="text/javascript" charset="utf-8" src="${vendorDir}/umeditor/umeditor.min.js"></script>--%>
<%--<script type="text/javascript" src="${vendorDir}/umeditor/lang/zh-cn/zh-cn.js"></script>--%>
<script type="text/javascript" src="${vendorDir}/wangeditor/wangEditor.min.js"></script>
<script type="text/javascript" src="${jsDir}/sysconfig/sysconfig.js"></script>
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
        sysconfig.init();
    });
</script>
</body>
</html>
