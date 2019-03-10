var table;
var selected;
var updateFileFlag='0';
var editor;
var codeEditor;
var withdrawEditor;
var sysconfig = {
    init : function () {
        this.initTalble();
        // var um = UM.getEditor('announcement');

        var E = window.wangEditor;
        editor = new E('#editor');
        // 或者 var editor = new E( document.getElementById('editor') )
        editor.create();

        codeEditor = new E('#codeEditor');
        var editorConf = [
            'head',  // 标题
            'bold',  // 粗体
            'fontSize',  // 字号
            'fontName',  // 字体
            'italic',  // 斜体
            'underline',  // 下划线
            'foreColor',  // 文字颜色
            'backColor',  // 背景颜色
            'list',  // 列表
            'justify',  // 对齐方式
            'emoticon',  // 表情
        ];
        codeEditor.customConfig.menus = editorConf;
        codeEditor.create();

        withdrawEditor = new E('#withdrawEditor');
        withdrawEditor.customConfig.menus = editorConf;
        withdrawEditor.create();
    },
    initTalble: function () {
        $('#uploadFile').filestyle({
            buttonText : ' 上传文件 ',
            'onChange': function (files) {
                updateFileFlag = '1';
            }
        });
        var columns = [
            {
                title: '',
                target: 6,
                data: function (item) {
                    return "";
                }
            },{
                title: 'AppId',
                target: 0,
                data: function (item) {
                    return item.appid;
                }
            }, {
                title: 'AppSecret',
                target: 1,
                data: function (item) {
                    return item.appsecret;
                }
            }, {
                title: '接口备注',
                target: 1,
                data: function (item) {
                    return item.comment;
                }
            }, {
                title: '商户号',
                target: 2,
                data: function (item) {
                    return item.bizAccount;
                }
            }, {
                title: '秘钥KEY',
                target: 3,
                data: function (item) {
                    return item.key;
                }
            }, {
                title: '验证TXT',
                target: 4,
                data: function (item) {
                    return item.fileName;
                }
            }, {
                title: '状态',
                target: 5,
                data: function (item) {
                    var result = "";
                    if(item.status=='0'){
                        result = "<span style='color: #128018;font-size: 16px;'><b>在用中</b></span>";
                    }else if(item.status=='1'){
                        result = "<span style='color: #3b79c6;font-size: 16px;'><b>备用中</b></span>";
                    }else{
                        result = "<span style='color: #f84a0f;font-size: 16px;'><b>已禁用</b></span>";
                    }
                    //0 使用中 1-备用中 2-已废弃
                    return result;
                }
            }];
        table = $('#payConfList').DataTable({
            "processing": true,
            "serverSide": false,
            "searching": false,//是否开启搜索
            "bLengthChange": true, //改变每页显示数据数量
            "bPaginate": true, //翻页功能,
            "autoWidth": true,
            "dom": 'frt<"bottom"ilp<"clear">>',
            "aLengthMenu": [10, 20, 50, 100],
            "ajax": {
                url: smile.baseURL() + "/configService/getPayConf",
                type: "post",
                data: {
                }
            },
            select :{
                style : 'os'
            },
            columns: columns,
            columnDefs: [  {
                orderable: false,
                className: 'select-checkbox',
                targets:   0
            }],
            order: [[7, 'asc']],
            "language": {
                "url": smile.baseURL() + "/static/data/chinese.json"
            }
        });
    },
    add: function () {
        $('#addModal').modal("show");
        $('#appId').val("");
        $('#appSecret').val("");
        $('#comment').val("");
        $('#key').val("");
        $('#bizAccount').val("");
        $(":file").filestyle('clear');
        $('#reqType').val("add");

    },
    addOrUpdate: function () {
        // $('#form1').submit();
        var appId = $('#appId').val();
        var appSecret = $('#appSecret').val();
        var comment = $('#comment').val();
        var key = $('#key').val();
        var bizAccount = $('#bizAccount').val();
        var reqType = $('#reqType').val();
        var file = $('#uploadFile')[0].files[0];
        var formdata = new FormData();
        formdata.append("appId",appId);
        formdata.append("appSecret",appSecret);
        formdata.append("comment",comment);
        formdata.append("key",key);
        formdata.append("bizAccount",bizAccount);
        formdata.append("reqType",reqType);
        formdata.append('file', file);
        formdata.append('updateFileFlag',updateFileFlag);

        if (isBank(file)&&reqType=="add") {
            $('#tipDiv').html("请上传验证文件！");
            return;
        }
        if (isBank(appSecret)) {
            $('#tipDiv').html("请填写AppSecret！");
            return;
        }
        if (isBank(key)) {
            $('#tipDiv').html("请填写密钥KEY！");
            return;
        }
        if(isBank(appId)) {
            $('#tipDiv').html("请填写AppID！");
            return;
        }
        if(isBank(bizAccount)) {
            $('#tipDiv').html("请填写商户号！");
            return;
        }
        var url = "";
        if(reqType=="update"&&file==null){
            url = smile.baseURL() + "/configService/updatePayConf";
        }else{
            url = smile.baseURL() + "/configService/addOrUpdatePayConf";
        }
        $.ajax({
            type: "post",
            url: url,
            processData: false,
            contentType: false,
            cache: false,//上传文件无需缓存
            data: formdata,
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '保存成功！');
                    $('#addModal').modal("hide");
                    table.ajax.reload();
                } else {
                    toastr.error('提示', '保存失败！');
                }
            }
        })
    },
    refresh: function () {
        table.ajax.reload();
    },
    edit: function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if (selectedNum != 1) {
            toastr.warning('提示', '请选择一行编辑！');
            return;
        }
        var data = selectedDatas[0];
        selected = data.id;
        $('#addModal').modal("show");
        $('#appId').val(data.appid);
        $('#appSecret').val(data.appsecret);
        $('#comment').val(data.comment);
        $('#key').val(data.key);
        $('#bizAccount').val(data.bizAccount);
        // $(":file").filestyle('clear');
        $(":file").filestyle('placeholder', data.fileName);
        $('#appId').attr("disabled","disabled");
        $('#reqType').val("update");
    },
    showSureModal: function () {
        var selectedNum = table.rows('.selected').data().length;
        if (selectedNum < 1) {
            toastr.warning('提示', '至少选择一行记录！');
            return;
        }
        $('#sureModal').modal("show");
    },
    "delete": function () {
        var selectedDatas = table.rows('.selected').data();
        var appIds = [];
        for (var i = 0; i < selectedDatas.length; i++) {
            var data = selectedDatas[i];
            // if (i == selectedDatas.length - 1) {
            //     userIds += data.id;
            // } else {
            //     userIds += data.id + ",";
            // }
            appIds.push(data.appid);
        }
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/delPayConf",
            data: {
                appIds: appIds
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '删除成功！');
                    $('#sureModal').modal("hide");
                    table.ajax.reload();
                } else {
                    toastr.error('提示', '删除失败！');
                }
            }
        });
    },
    use : function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if (selectedNum != 1) {
            toastr.warning('提示', '请选择一项！');
            return;
        }
        var data = selectedDatas[0];
        if(data.status=='0'){
            toastr.warning('提示', '已经在使用中！');
            return;
        }
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/updateStatus",
            data: {
                appIds : [data.appid],
                status : '0'
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '修改成功！');
                    table.ajax.reload();
                } else {
                    toastr.error('提示', '修改失败！');
                }
            }
        });
    },
    disable : function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if (selectedNum < 1) {
            toastr.warning('提示', '请至少选择一项！');
            return;
        }
        var appIds = [];
        for (var i = 0; i < selectedDatas.length; i++) {
            var data = selectedDatas[i];
            appIds.push(data.appid);
        }
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/updateStatus",
            data: {
                appIds : appIds,
                status : '2'
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    table.ajax.reload();
                } else {
                    toastr.error('提示', '修改失败！');
                }
            }
        });
    },
    updateAgencyConf : function () {
        var effectiveJunior = $('#effectiveJunior').val();
        var generalReward = $('#generalReward').val();
        var primaryAgency = $('#primaryAgency').val();
        var primaryReward = $('#primaryReward').val();
        var advancedAgency = $('#advancedAgency').val();
        var advancedReward = $('#advancedReward').val();
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/updateAgencyConf",
            data: {
                effectiveJunior : effectiveJunior,
                generalReward : generalReward,
                primaryAgency : primaryAgency,
                primaryReward : primaryReward,
                advancedAgency : advancedAgency,
                advancedReward : advancedReward
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '修改成功！');
                } else {
                    toastr.error('提示', '修改失败！');
                }
            }
        });
    },
    getAgencyConf : function () {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/getConfByGroupName",
            data: {
                groupName : "agencyConf"
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    var d = data.data;
                    if(d!=null && d!=""){
                        $('#effectiveJunior').val(d.effectiveJunior);
                        $('#generalReward').val(d.generalReward);
                        $('#primaryAgency').val(d.primaryAgency);
                        $('#primaryReward').val(d.primaryReward);
                        $('#advancedAgency').val(d.advancedAgency);
                        $('#advancedReward').val(d.advancedReward);
                    }
                } else {
                    toastr.error('提示', '获取配置信息失败！');
                }
            }
        });
    },
    getSysConf : function () {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/getConfByGroupName",
            data: {
                groupName : "sysConf"
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    var d = data.data;
                    if(d!=null && d!=""){
                        $('#safeDomain').val(d.safeDomain);
                        $('#invitationCode').val(d.invitationCode);
                        $('#invitationCondition').val(d.invitationCondition);
                        $('#kfWechat').val(d.kfWechat);
                        $('#withdraw').val(d.withdraw);
                        $('#authDomain').val(d.authDomain);
                        // $('#notice').val(d.notice);
                        editor.txt.html(d.notice);
                        codeEditor.txt.html(d.codeNotice);
                        withdrawEditor.txt.html(d.withdrawNotice);
                        if(d.maintainflag=="0"){
                            $("#openM").attr("checked",true);
                        }else{
                            $("#cosleM").attr("checked",true);
                        }
                    }
                } else {
                    toastr.error('提示', '获取配置信息失败！');
                }
            }
        });
    },
    updateSysConf : function () {
        var safeDomain = $('#safeDomain').val();
        var invitationCode = $('#invitationCode').val();
        var invitationCondition = $('#invitationCondition').val();
        var kfWechat = $('#kfWechat').val();
        var withdraw = $('#withdraw').val();
        var authDomain = $('#authDomain').val();
        var notice = editor.txt.html();
        var codeNotice = codeEditor.txt.html();
        var withdrawNotice = withdrawEditor.txt.html();
        var maintainflag = $("input[name='maintainflag']:checked").val()
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/updateSysConf",
            data: {
                safeDomain : safeDomain,
                invitationCode : invitationCode,
                invitationCondition : invitationCondition,
                kfWechat : kfWechat,
                withdraw : withdraw,
                authDomain : authDomain,
                notice : notice,
                maintainflag : maintainflag,
                codeNotice : codeNotice,
                withdrawNotice : withdrawNotice
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        });
    },
    updatePathConf : function () {
        var authTxtPath = $('#authTxtPath').val();
        var videoPath = $('#videoPath').val();
        var videoImgPath = $('#videoImgPath').val();
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/updatePathConf",
            data: {
                authTxtPath : authTxtPath,
                videoPath : videoPath,
                payType : $("input[name='payType']:checked").val(),
                videoImgPath : videoImgPath
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        });
    },
    getFilePathConf : function () {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/getConfByGroupName",
            data: {
                groupName : "filepath"
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    var d = data.data;
                    if(d!=null && d!=""){
                        $('#authTxtPath').val(d.authTxtPath);
                        $('#videoPath').val(d.videoPath);
                        $('#videoImgPath').val(d.videoImgPath);
                        if(d.payType=="weixin"){
                            $("#weixin").attr("checked",true);
                        }else{
                            $("#shield").attr("checked",true);
                        }
                    }
                } else {
                    toastr.error('提示', '获取配置信息失败！');
                }
            }
        });
    },
    updateShieldPayConf : function () {
        var shieldAppId = $('#shieldAppId').val();
        var shieldKey = $('#shieldKey').val();
        var shieldUrl = $('#shieldUrl').val();
        var cloudUrl = $('#cloudUrl').val();
        var cloudName = $('#cloudName').val();
        var cloudAppId = $('#cloudAppId').val();
        var cloudKey = $('#cloudKey').val();
        var payType = $("input[name='payType']:checked").val();
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/updateShieldPayConf",
            data: {
                shieldAppId : shieldAppId,
                shieldKey : shieldKey,
                shieldUrl : shieldUrl,
                payType : payType,
                cloudUrl : cloudUrl,
                cloudName : cloudName,
                cloudAppId : cloudAppId,
                cloudKey : cloudKey
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success(data.msg);
                } else {
                    toastr.error(data.msg);
                }
            }
        });
    },
    getShiedlPayConf : function () {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/configService/getConfByGroupName",
            data: {
                groupName : "shieldPay"
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    var d = data.data;
                    if(d!=null && d!=""){
                        $('#shieldAppId').val(d.shieldAppId);
                        $('#shieldKey').val(d.shieldKey);
                        $('#shieldUrl').val(d.shieldUrl);
                        $('#cloudUrl').val(d.cloudUrl);
                        $('#cloudName').val(d.cloudName);
                        $('#cloudAppId').val(d.cloudAppId);
                        $('#cloudKey').val(d.cloudKey);
                        if(d.payType=="weixin"){
                            $("#weixin").attr("checked",true);
                        }else if(d.payType=="cloud") {
                            $("#cloud").attr("checked",true);
                        }else{
                            $("#shield").attr("checked",true);
                        }
                    }
                } else {
                    toastr.error('提示', '获取配置信息失败！');
                }
            }
        });
    }
}