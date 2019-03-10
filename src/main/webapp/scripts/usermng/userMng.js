var table;
var reqType;
var currentUser;
var selectData;
var userMng = {
    init : function () {
        this.initSelect();
        this.initTalble();
    },
    initTalble: function () {
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
            select: {
                style: 'multi'
            },
            // "pagingType": "input",
            "ajax": {
                url: smile.baseURL() + "/userService/getAllUsers",
                type: "post",
                data: {
                    "userName": "admin"
                }
            },
            columns: [
                {
                    "data": "", "mRender": function () {
                    return "";
                }
                },
                {"data": "id"},
                {"data": "username"},
                {
                    "data": "type",
                    "mRender": function (data) {
                        var result = "未知";
                        for(var i=0;i<selectData.length;i++){
                            if(selectData[i].id==data){
                                result = selectData[i].name;
                                break;
                            }
                        }
                        return result;
                    }
                },
                {"data": "wechat"},
                {"data": "qq"},
                {"data": "parentName"},
                {"data": "createTime"}
            ],
            columnDefs: [{
                orderable: false,
                className: 'select-checkbox',
                targets: 0
            }, {
                "visible": false,
                "targets": 1
            }],
            order: [[6, 'desc']],
            "language": {
                "url": smile.baseURL() + "/static/data/chinese.json"
            }
        });
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
                    $("#type").append("<option value=''>--请选择类型--</option>");
                   for(var i = 0;i<data.data.length;i++){
                       var item = data.data[i];
                       $("#type").append("<option value='"+item.id+"'>"+item.name+"</option>");
                   }
                } else {
                    toastr.error('提示', '获取角色失败！');
                }
            }
        })
    },
    addUser: function () {
        $('#userModal').modal("show");
        $('#username').val("");
        $('#password').val("");
        $('#type').val("");
        $('#fetchPwd').val("");
        $('#wechat').val("");
        $('#qq').val("");
        reqType = "add";
    },
    addOrUpdateUser: function () {
        var username = $('#username').val();
        var password = $('#password').val();
        var fetchPwd = $('#fetchPwd').val();
        var type = $('#type').val();
        var wechat = $('#wechat').val();
        var qq = $('#qq').val();
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
                fetchPwd: fetchPwd,
                type: type,
                wechat: wechat,
                qq: qq,
                reqType: reqType,
                id: currentUser
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '保存成功！');
                    $('#userModal').modal("hide");
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
    editUser: function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if (selectedNum != 1) {
            toastr.warning('提示', '请选择一行编辑！');
            return;
        }
        var data = selectedDatas[0];
        currentUser = data.id;
        $('#userModal').modal("show");
        $('#username').val(data.username);
        $('#password').val(data.password);
        $('#type').val(data.type);
        $('#fetchPwd').val(data.fetchPwd);
        $('#wechat').val(data.wechat);
        $('#qq').val(data.qq);
        reqType = "update";
    },
    showSureModal: function () {
        var selectedNum = table.rows('.selected').data().length;
        if (selectedNum < 1) {
            toastr.warning('提示', '至少选择一行记录！');
            return;
        }
        console.log(table.rows('.selected').data()[0].id);
        $('#sureModal').modal("show");
    },
    deleteUsers: function () {
        var selectedDatas = table.rows('.selected').data();
        var userIds = "";
        for (var i = 0; i < selectedDatas.length; i++) {
            var data = selectedDatas[i];
            if (i == selectedDatas.length - 1) {
                userIds += data.id;
            } else {
                userIds += data.id + ",";
            }
        }
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/userService/delUsers",
            data: {
                userIds: userIds
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
    }
}