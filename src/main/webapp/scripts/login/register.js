/**
 * Created by smile on 2018/4/9.
 */
var register = {
    init : function(){
        $("#username").val("");
        $("#password").val("");
    },
    verfy : function () {
        var username = $('#username').val();
        var password = $('#password').val();
        var repeatPwd = $('#repeatPwd').val();
        var fetchPwd = $('#fetchPwd').val();
        var repeatFetchPwd = $('#repeatFetchPwd').val();
        var invitionCode = $('#invitionCode').val();
        var verifyCode = $('#verifyCode').val();
        if(isBank(username)){
            $('#tipDiv').html("请输入用户名！");
            return;
        }
        if(isBank(password)){
            $('#tipDiv').html("请输入密码！");
            return;
        }
        if(password!=repeatPwd){
            $('#tipDiv').html("密码输入不一致！");
            return;
        }
        if(isBank(fetchPwd)){
            $('#tipDiv').html("请输入提取密码！");
            return;
        }
        if(password!=repeatFetchPwd){
            $('#tipDiv').html("提取密码输入不一致！");
            return;
        }
        if(isBank(verifyCode)){
            $('#tipDiv').html("请输入验证码！");
            return;
        }

        $('#loginForm').submit();
    },
    toRegisterPage : function () {
        window.open(smile.baseURL()+"/pages/register.jsp");
    },
    changeImg : function (url) {
        $('#veryCodeImg').attr("src", url+"/getVerifyImage?t="+new Date().getTime());
    }
}