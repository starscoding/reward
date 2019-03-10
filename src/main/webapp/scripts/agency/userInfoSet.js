var userInfo;
var userInfoSet = {
    init : function () {
        this.initContact();
    },
    initContact : function () {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/userService/getUserInfo",
            data: {},
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    userInfo = data.data;
                    $("#wechat").val(userInfo.wechat);
                    $("#qq").val(userInfo.qq);
                    // toastr.success('提示', '删除成功！');
                } else {
                    // toastr.error('提示', '删除失败！');
                }
            }
        });
    },
    contactReset : function () {
        $("#wechat").val(userInfo.wechat);
        $("#qq").val(userInfo.qq);
    },
    updateContact : function () {
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/userService/updateContact",
            data: {
                wechat : $("#wechat").val(),
                qq : $("#qq").val()
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
    alterPwd : function () {
        var oldPwd = $("#oldPwd").val();
        var newPwd = $("#newPwd").val();
        var repeatNewPwd = $("#repeatNewPwd").val();
        $("#pwdTip").html("");
        $("#newPwdTip").html("");
        $("#tipDiv").html("");
        if(isBank(oldPwd)){
            $("#pwdTip").html("密码不能为空！");
            return;
        }
        if(isBank(newPwd)){
            $("#newPwdTip").html("密码不能为空！");
            return;
        }
        if(newPwd!=repeatNewPwd){
            $("#tipDiv").html("俩次密码不同！");
            return;
        }

        $.ajax({
            type: "post",
            url: smile.baseURL() + "/userService/alterPwd",
            data: {
                oldPwd : oldPwd,
                newPwd : newPwd
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '修改成功！');
                } else {
                    toastr.error('提示', data.msg);
                }
            }
        });
    },
    alterFetchPwd : function () {
        var oldPwd = $("#oldFetchPwd").val();
        var newPwd = $("#newFetchPwd").val();
        var repeatNewPwd = $("#repeatNewFetchPwd").val();
        $("#pwdFetchTip").html("");
        $("#newFetchPwdTip").html("");
        $("#tipDivFetch").html("");
        if(isBank(oldPwd)){
            $("#pwdFetchTip").html("密码不能为空！");
            return;
        }
        if(isBank(newPwd)){
            $("#newFetchPwdTip").html("密码不能为空！");
            return;
        }
        if(newPwd!=repeatNewPwd){
            $("#tipDivFetch").html("俩次密码不同！");
            return;
        }

        $.ajax({
            type: "post",
            url: smile.baseURL() + "/userService/alterFetchPwd",
            data: {
                oldPwd : oldPwd,
                newPwd : newPwd
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '修改成功！');
                } else {
                    toastr.error('提示', data.msg);
                }
            }
        });
    }
}