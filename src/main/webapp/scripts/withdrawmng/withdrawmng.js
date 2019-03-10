var table;
var reqType;
var withdrawmng = {
    init: function () {
        this.initTime();
        this.initTalble();
        this.initCountInfo();
    },
    initCountInfo:function () {
        var nowTime = new Date();
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/withdrawmng/countWithdrawAmount",
            data : {
                isSuccess : "0"
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#totalWithdrawSuccess").html(data.data);
                }else{
                    $("#totalWithdrawSuccess").html("获取失败");
                }
            }
        });
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/withdrawmng/countWithdrawAmount",
            data : {
                isSuccess : "1"
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#totalWithdrawFailure").html(data.data);
                }else{
                    $("#totalWithdrawFailure").html("获取失败");
                }
            }
        });
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/withdrawmng/countWithdrawAmount",
            data : {
                date : nowTime.format("yyyy-MM-dd"),
                isSuccess : "0"
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#todayWithdrawSuccess").html(data.data);
                }else{
                    $("#todayWithdrawSuccess").html("获取失败");
                }
            }
        });
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/withdrawmng/countWithdrawAmount",
            data : {
                date : nowTime.format("yyyy-MM-dd"),
                isSuccess : "1"
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#todayWithdrawFailure").html(data.data);
                }else{
                    $("#todayWithdrawFailure").html("获取失败");
                }
            }
        });
    },
    initTime: function () {
        var nowTime = new Date();

        $('.form_datetime').datetimepicker({
            format:'yyyy-mm-dd',//设置格式
            language: 'zh-CN',
            autoclose: true,
            weekStart: 1,
            todayBtn:  1,
            autoclose: 1,
            todayHighlight: 1,
            startView: 2,
            minView: 2,
        });

        // nowTime.setHours(nowTime.getHours() + 24);
        $('#endTime').val(nowTime.format("yyyy-MM-dd"));
        nowTime.setHours(nowTime.getHours() - 24*3);
        $('#startTime').val(nowTime.format("yyyy-MM-dd"));
    },
    initTalble: function () {
        var columns = [
            {
                title: '',
                target: 0,
                data: function (item) {
                    return "";
                }
            },{
                title: '二维码',
                target: 1,
                data: function (item) {
                    var result = "<img src='"+smile.baseURL()+item.url+"' style='width: 150px;height: 150px;'/>";

                    return result;
                }
            },{
                title: '提现用户',
                target: 2,
                data: function (item) {
                    return item.name;
                }
            },{
                title: '提现时间',
                target: 3,
                data: function (item) {
                    return item.time;
                }
            }, {
                title: '提现金额',
                target: 4,
                data: function (item) {
                    return item.amount;
                }
            }, {
                title: '支付宝账号',
                target: 5,
                data: function (item) {
                    return item.alipayaccount;
                }
            }, {
                title: '姓名',
                target: 6,
                data: function (item) {
                    return item.fullName;
                }
            }, {
                title: '手续费',
                target: 7,
                data: function (item) {
                    return item.amount;
                }
            }, {
                title: '实际金额',
                target: 8,
                data: function (item) {
                    return item.amount;
                }
            }, {
                title: '提现状态',
                target: 9,
                data: function (item) {
                    var result = "";
                    if(item.opStatus=="0")
                        result = "<span style='color: #761c19'><b>处理中</b></span>";
                    else if(item.opStatus=="1"){
                        result = "<span style='color: #2ac845'>已提现</span>";
                    }else if(item.opStatus=="2"){
                        result = "<span style='color: #761c19'>拒绝提现</span>";
                    }
                    return result;
                }
            }];
        table = $('#userList').DataTable( {
            "processing": true,
            "serverSide": false,
            "searching": false,//是否开启搜索
            "bLengthChange": true, //改变每页显示数据数量
            "bPaginate": true, //翻页功能,
            "autoWidth": true,
            "dom": 'frt<"bottom"ilp<"clear">>',
            "aLengthMenu": [10, 20, 50, 100],
            "ajax": {
                url: smile.baseURL() + "/withdrawmng/getWithdrawInfo",
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
            order: [[3, 'desc']],
            "language": {
                "url": smile.baseURL() + "/static/data/chinese.json"
            }
        } );
    },
    query: function () {
        var param = {
            "startTime": $('#startTime').val(),
            "endTime": $('#endTime').val()
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    },
    refresh : function () {
        table.ajax.reload();
    },
    showSureModal : function (status) {
        var selectedNum = table.rows('.selected').data().length;
        if(selectedNum<1){
            toastr.warning('提示', '至少选择一行记录！');
            return;
        }
        if(status=='1')
            $('#sureModal').modal("show");
        else
            $('#denySureModal').modal("show");
    },
    agree : function () {
        var selectedDatas = table.rows('.selected').data();
        var ids=[];
        for(var i=0;i<selectedDatas.length;i++){
            var data = selectedDatas[i];
            ids.push(data.id);
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/withdrawmng/updateWithdrawStatus",
            data : {
                ids : ids,
                status : '1'
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    toastr.success('提示', '提现成功！');
                    $('#sureModal').modal("hide");
                    table.ajax.reload();
                }else{
                    toastr.error('提示', '提现失败！');
                }
            }
        });
    },
    deny : function () {
        var selectedDatas = table.rows('.selected').data();
        var ids=[];
        for(var i=0;i<selectedDatas.length;i++){
            var data = selectedDatas[i];
            ids.push(data.id);
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/withdrawmng/updateWithdrawStatus",
            data : {
                ids : ids,
                status : '2'
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    toastr.success('提示', '操作成功！');
                    $('#denySureModal').modal("hide");
                    table.ajax.reload();
                }else{
                    toastr.error('提示', '操作失败！');
                }
            }
        });
    }
};