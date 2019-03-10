var table;
var rewardDetail;
var withdrawDetail;
var userDetail;
var orderinfo = {
    init: function () {
        this.initSelect();
        this.initTalble();
    },
    initSelect : function () {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/roleService/getAllRoles",
            async : false,
            data: {
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true'&& isNotBank(data.data)) {
                    selectData = data.data;
                    for(var i = 0;i<data.data.length;i++){
                        var item = data.data[i];
                        if(item.name=='合伙人')
                            $("#type").append("<option value='"+item.id+"'>"+item.name+"</option>");
                    }
                } else {
                    toastr.error('提示', '获取角色失败！');
                }
            }
        })
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
                title: '用户名',
                target: 1,
                data: function (item) {
                    return item.userName;
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
                title: '总收入',
                target: 3,
                data: function (item) {
                    return item.total;
                }
            }, {
                title: '下级提现',
                target: 4,
                data: function (item) {
                    return item.withdraw;
                }
            }, {
                title: '下级代理数量',
                target: 5,
                data: function (item) {
                    return item.agencyNum;
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
                url: smile.baseURL() + "/orderService/getPartnerDetail",
                type: "post",
                data: {
                    "startTime": $('#startTime').val(),
                    "endTime": $('#endTime').val()
                }
            },
            columns: columns,
            select: {
                style: 'single'
            },
            columnDefs: [  {
                orderable: false,
                className: 'select-checkbox',
                targets:   0
            }],
            order: [[3, 'desc']],
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
    rewardDetail: function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if (selectedNum != 1) {
            toastr.warning('提示', '请选择一行记录！');
            return;
        }
        var userName = selectedDatas[0].userName;

        $("#rewardDetailModal").modal("show");

        if (rewardDetail == null) {
            var columns = [
                {
                    title: '用户名',
                    target: 0,
                    data: function (item) {
                        return item.userName;
                    }
                },
                {
                    title: '打赏时间',
                    target: 0,
                    data: function (item) {
                        return item.rewardTime;
                    }
                }, {
                    title: '价格',
                    target: 0,
                    data: function (item) {
                        return item.amout;
                    }
                }, {
                    title: '视频名称',
                    target: 0,
                    data: function (item) {
                        return item.videoTitle;
                    }
                }];
            rewardDetail = $('#rewardDetailList').DataTable({
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
                "aLengthMenu": [10, 20, 50, 100],
                "ajax": {
                    url: smile.baseURL() + "/orderService/getOrdersByPName",
                    type: "post",
                    data: {
                        userName: userName
                    }
                },
                columns: columns,
                columnDefs: [],
                order: [[0, 'asc']],
                "language": {
                    "url": smile.baseURL() + "/static/data/chinese.json"
                }
            });
        } else {
            var param = {
                "userName": userName
            };
            rewardDetail.settings()[0].ajax.data = param;
            rewardDetail.ajax.reload();
        }

    },
    withdrawDetail: function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if (selectedNum != 1) {
            toastr.warning('提示', '请选择一行记录！');
            return;
        }
        var userName = selectedDatas[0].userName;

        $("#withdrawDetailModal").modal("show");

        if (withdrawDetail == null) {
            var columns = [
                {
                    title: '支付宝账号',
                    target: 0,
                    data: function (item) {
                        return item.alipayaccount;
                    }
                }, {
                    title: '真实姓名',
                    target: 1,
                    data: function (item) {
                        return item.fullName;
                    }
                }, {
                    title: '用户名',
                    target: 2,
                    data: function (item) {
                        return item.name;
                    }
                }, {
                    title: '提现金额',
                    target: 3,
                    data: function (item) {
                        return item.amount;
                    }
                }, {
                    title: '提现时间',
                    target: 4,
                    data: function (item) {
                        return item.time;
                    }
                }];
            withdrawDetail = $('#withdrawDetailList').DataTable({
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
                "aLengthMenu": [10, 20, 50, 100],
                "ajax": {
                    url: smile.baseURL() + "/withdrawmng/getWithdrawByPName",
                    type: "post",
                    data: {
                        userName: userName
                    }
                },
                columns: columns,
                columnDefs: [],
                order: [[0, 'asc']],
                "language": {
                    "url": smile.baseURL() + "/static/data/chinese.json"
                }
            });
        } else {
            var param = {
                "userName": userName
            };
            withdrawDetail.settings()[0].ajax.data = param;
            withdrawDetail.ajax.reload();
        }

    },
    userDetail: function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if (selectedNum != 1) {
            toastr.warning('提示', '请选择一行记录！');
            return;
        }
        var userName = selectedDatas[0].userName;

        $("#userDetailModal").modal("show");

        if (userDetail == null) {
            var columns = [
                {
                    title: '用户名',
                    target: 0,
                    data: function (item) {
                        return item.username;
                    }
                }, {
                    title: '联系方式',
                    target: 1,
                    data: function (item) {
                        return item.wechat+"|"+item.qq;
                    }
                }, {
                    title: '创建时间',
                    target: 2,
                    data: function (item) {
                        return item.createTime;
                    }
                }];
            userDetail = $('#userDetailList').DataTable({
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
                "aLengthMenu": [10, 20, 50, 100],
                "ajax": {
                    url: smile.baseURL() + "/userService/getUserByPName",
                    type: "post",
                    data: {
                        userName: userName
                    }
                },
                columns: columns,
                order: [[0, 'asc']],
                "language": {
                    "url": smile.baseURL() + "/static/data/chinese.json"
                }
            });
        } else {
            var param = {
                "userName": userName
            };
            userDetail.settings()[0].ajax.data = param;
            userDetail.ajax.reload();
        }

    },
    showAddModal : function () {
        $("#addUserModal").modal("show");
    },
    add: function () {
        var username = $('#username').val();
        var password = $('#password').val();
        var type = $('#type').val();
        if (isBank(username)) {
            $('#tipDiv').html("请输入用户名！");
            return;
        }
        if (isBank(password)) {
            $('#tipDiv').html("请输入密码！");
            return;
        }
        if (isBank(type)) {
            $('#tipDiv').html("请选择用户类型！");
            return;
        }

        $.ajax({
            type: "post",
            url: smile.baseURL() + "/userService/addOrUpdateUser",
            data: {
                username: username,
                password: password,
                type: type
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '保存成功！');
                    $('#addUserModal').modal("hide");
                    table.ajax.reload();
                } else {
                    toastr.error('提示', '保存失败！');
                }
            }
        })
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