var table;
var rewardDetail;
var withdrawDetail;
var orderinfo = {
    init: function () {
        this.initTalble();

        $.ajax({
            url: smile.baseURL() + "/marketInfo/getAgencyDetail",
            type: "post",
            data: {
            },
            dataType : "json",
            success : function (data) {
                if(isNotBank(data)&&data.success=="true"){
                    var title = [];
                    var datas = [];
                    for(var i = 0;i<data.data.length;i++){
                        var d = data.data[i];
                        title.push(d.userName);
                        datas.push({
                            value : d.total,
                            name  : d.userName
                        });
                    }
                    var dom = document.getElementById("container");
                    var myChart = echarts.init(dom);
                    var app = {};
                    option = null;
                    app.title = '环形图';
                    option = {
                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b}: {c} ({d}%)"
                        },
                        legend: {
                            orient: 'vertical',
                            x: 'left',
                            data:title
                        },
                        series: [
                            {
                                name:'总收入',
                                type:'pie',
                                radius: ['50%', '70%'],
                                avoidLabelOverlap: false,
                                label: {
                                    normal: {
                                        show: false,
                                        position: 'center'
                                    },
                                    emphasis: {
                                        show: true,
                                        textStyle: {
                                            fontSize: '30',
                                            fontWeight: 'bold'
                                        }
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        show: false
                                    }
                                },
                                data:datas
                            }
                        ]
                    };
                    if (option && typeof option === "object") {
                        myChart.setOption(option, true);
                    }
                }
            }
        })
    },
    initTalble: function () {
        var columns = [
            {
                title: '',
                target: 10,
                data: function (item) {
                    return "";
                }
            },{
                title: '用户名',
                target: 0,
                data: function (item) {
                    return item.userName;
                }
            }, {
                title: '创建时间',
                target: 1,
                data: function (item) {
                    return item.createTime;
                }
            }, {
                title: '联系方式',
                target: 2,
                data: function (item) {
                    var wechat = item.wechat==''?'无':item.wechat;
                    var qq = item.qq==''?'无':item.qq;
                    return "<li class='fa fa-wechat' style='color: #2ac845'></li> : "+wechat+"\t"+"<li class='fa  fa-qq' style='color: #00a0df'></li> : "+qq;
                }
            }, {
                title: '当前级别',
                target: 3,
                data: function (item) {
                    return item.level;
                }
            }, {
                title: '上级推广人',
                target: 4,
                data: function (item) {
                    return item.parent;
                }
            }, {
                title: '所属合伙人',
                target: 5,
                data: function (item) {
                    return item.partner;
                }
            }, {
                title: '总收入',
                target: 6,
                data: function (item) {
                    return item.total;
                }
            }, {
                title: '已提现',
                target: 7,
                data: function (item) {
                    return item.hasWithdraw;
                }
            }, {
                title: '未提现',
                target: 8,
                data: function (item) {
                    return item.notWithdraw;
                }
            }, {
                title: '有效下级',
                target: 9,
                data: function (item) {
                    return item.effectNum;
                }
            }];
        table = $('#userList').DataTable({
            "processing": true,
            "serverSide": false,
            "searching": true,//是否开启搜索
            "bLengthChange": true, //改变每页显示数据数量
            "bPaginate": true, //翻页功能,
            "autoWidth": true,
            // "sPaginationType": "full_numbers",
            // "dom": 'f<"top">t<"bottom"irlp><"clear">',
            // responsive: true,
            "dom": 'frt<"bottom"ilp<"clear">>',
            "aLengthMenu": [10, 20, 50, 100],
            "ajax": {
                url: smile.baseURL() + "/marketInfo/getAgencyDetail",
                type: "post",
                data: {
                    "startTime": $('#startTime').val(),
                    "endTime": $('#endTime').val()
                }
            },
            columns: columns,
            columnDefs: [  {
                orderable: false,
                className: 'select-checkbox',
                targets:   0
            }],
            select :{
                style : 'single'
            },
            // columnDefs: [{
            //     "visible": false,
            //     "targets": 0
            // }],
            order: [[7, 'desc']],
            "language": {
                "url": smile.baseURL() + "/static/data/chinese.json"
            }
        });
    },
    refresh: function () {
        var param = {
            "startTime": $('#startTime').val(),
            "endTime": $('#endTime').val()
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    },
    rewardDetail : function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if(selectedNum!=1){
            toastr.warning('提示', '请选择一行编辑！');
            return;
        }
        var userName = selectedDatas[0].userName;

        $("#rewardDetailModal").modal("show");

        if(rewardDetail==null){
            var columns = [
                {
                    title: '打赏时间',
                    target: 0,
                    data: function (item) {
                        return item.rewardTime;
                    }
                },{
                    title: '价格',
                    target: 0,
                    data: function (item) {
                        return item.amout;
                    }
                },{
                    title: '视频名称',
                    target: 0,
                    data: function (item) {
                        return item.videoTitle;
                    }
                }];
            rewardDetail = $('#rewardDetailList').DataTable( {
                "processing": true,
                "serverSide": false,
                "searching": false,//是否开启搜索
                "bLengthChange": true, //改变每页显示数据数量
                "bPaginate": true, //翻页功能,
                "autoWidth": true,
                // "sPaginationType": "full_numbers",
                // "dom": 'f<"top">t<"bottom"irlp><"clear">',
                // responsive: true,
                "dom": 'frt<"bottom"ilp<"clear">>',
                "aLengthMenu" : [10, 20, 50,100],
                "ajax": {
                    url : smile.baseURL()+"/orderService/getOrdersByUserName",
                    type: "post",
                    data : {
                        userName : userName
                    }
                },
                columns: columns,
                columnDefs: [],
                order: [[ 0, 'asc' ]],
                "language": {
                    "url": smile.baseURL()+"/static/data/chinese.json"
                }
            } );
        }else{
            var param = {
                "userName" : userName
            };
            rewardDetail.settings()[0].ajax.data = param;
            rewardDetail.ajax.reload();
        }

    },
    withdrawDetail : function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if(selectedNum!=1){
            toastr.warning('提示', '请选择一行编辑！');
            return;
        }
        var userName = selectedDatas[0].userName;

        $("#withdrawDetailModal").modal("show");

        if(withdrawDetail==null){
            var columns = [
                {
                    title: '支付宝账号',
                    target: 0,
                    data: function (item) {
                        return item.alipayaccount;
                    }
                },{
                    title: '真实姓名',
                    target: 1,
                    data: function (item) {
                        return item.fullName;
                    }
                },{
                    title: '用户名',
                    target: 2,
                    data: function (item) {
                        return item.name;
                    }
                },{
                    title: '提现金额',
                    target: 3,
                    data: function (item) {
                        return item.amount;
                    }
                },{
                    title: '提现时间',
                    target: 4,
                    data: function (item) {
                        return item.time;
                    }
                }];
            withdrawDetail = $('#withdrawDetailList').DataTable( {
                "processing": true,
                "serverSide": false,
                "searching": false,//是否开启搜索
                "bLengthChange": true, //改变每页显示数据数量
                "bPaginate": true, //翻页功能,
                "autoWidth": true,
                // "sPaginationType": "full_numbers",
                // "dom": 'f<"top">t<"bottom"irlp><"clear">',
                // responsive: true,
                "dom": 'frt<"bottom"ilp<"clear">>',
                "aLengthMenu" : [10, 20, 50,100],
                "ajax": {
                    url : smile.baseURL()+"/withdrawmng/getWithdrawByName",
                    type: "post",
                    data : {
                        userName : userName
                    }
                },
                columns: columns,
                columnDefs: [],
                order: [[ 0, 'asc' ]],
                "language": {
                    "url": smile.baseURL()+"/static/data/chinese.json"
                }
            } );
        }else{
            var param = {
                "userName" : userName
            };
            withdrawDetail.settings()[0].ajax.data = param;
            withdrawDetail.ajax.reload();
        }

    },
    showSureModal : function () {
        var selectedNum = table.rows('.selected').data().length;
        // var selectedDatas = table.rows('.selected').data();
        if(selectedNum<1){
            toastr.warning('提示', '请选择一行编辑！');
            return;
        }
        $('#sureModal').modal("show");
    },
    "delete" : function () {
        var selectedDatas = table.rows('.selected').data();
        var selectedNum = table.rows('.selected').data().length;
        var names = [];
        for(var i = 0;i<selectedNum;i++){
            names.push(selectedDatas[i].userName)
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/userService/delUserByNames",
            data : {
                names : names
            },
            dataType : "json",
            success : function (data) {

                if(isNotBank(data)&&data.success=="true"){
                    toastr.success(data.msg);
                    $("#sureModal").modal("hide");
                    table.ajax.reload();
                }else{
                    toastr.warning(data.msg);
                }
            }

        })
    }
}