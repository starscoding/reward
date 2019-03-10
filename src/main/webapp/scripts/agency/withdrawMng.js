var table;
var reqType;
var withdrawMng = {
    init : function () {
      this.initCountInfo();
      this.initTalble();
      this.initNotice();
        $(':file').filestyle({
            buttonText : '上传图片'
        });
    },
    preview : function (filePath) {
        $('#codeImg').css("display","block");
        $('#codeImg').attr("src",URL.createObjectURL($('#QRCode')[0].files[0]));
    },
    initNotice : function () {
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/configService/getConfByName",
            data : {
                name : "withdrawNotice"
            },
            dataType : "json",
            success : function (data) {
                if(isNotBank(data)&&data.success=="true"){
                    $("#withdrawNotice").append(data.data);
                }
            }
        })
    },
    initCountInfo : function () {
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/withdrawmng/countBalance",
            data : {},
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#balance").html(data.data+"￥");
                }else{
                    $("#marketinfo").html("获取失败");
                }
            }
        });
    },
    initTalble: function () {
        var columns = [
            {
                title: '提现时间',
                target: 0,
                data: function (item) {
                    return item.time;
                }
            }, {
                title: '提现金额',
                target: 1,
                data: function (item) {
                    return item.amount;
                }
            }, {
                title: '支付宝账号',
                target: 2,
                data: function (item) {
                    return item.alipayaccount;
                }
            }, {
                title: '姓名',
                target: 3,
                data: function (item) {
                    return item.fullName;
                }
            }, {
                title: '手续费',
                target: 4,
                data: function (item) {
                    return item.amount;
                }
            }, {
                title: '实际金额',
                target: 5,
                data: function (item) {
                    return item.amount;
                }
            }, {
                title: '提现状态',
                target: 6,
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
            }, {
                title: '提现状态',
                target: 6,
                data: function (item) {
                    var result = "<img src='"+smile.baseURL()+item.url+"' style='width: 150px;height: 150px;'/>";

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
            // "sPaginationType": "full_numbers",
            // "dom": 'f<"top">t<"bottom"irlp><"clear">',
            // responsive: true,
            "dom": 'frt<"bottom"ilp<"clear">>',
            "aLengthMenu" : [10, 20, 50,100],
            // select :{
            //     style : 'os'
            // },
            // "pagingType": "input",
            "ajax": {
                url : smile.baseURL()+"/withdrawmng/getWithdrawInfo",
                type: "post",
                data : {
                    "status": 1
                }
            },
            columns: columns,
            columnDefs: [ ],
            order: [[ 6, 'asc' ]],
            "language": {
                "url": smile.baseURL()+"/static/data/chinese.json"
            }
        } );
    },
    showModal : function () {
        $("#addModal").modal("show");
    },
    add : function () {
        var alipayAccount = $("#alipayAccount").val();
        var name = $("#name").val();
        var amount = $("#amount").val();
        var fethpwd = $("#fethpwd").val();
        var file = $('#QRCode')[0].files[0];
        var formdata = new FormData();
        formdata.append("alipayAccount",alipayAccount);
        formdata.append("name",name);
        formdata.append("amount",amount);
        formdata.append("fethpwd",fethpwd);
        formdata.append("file",file);

        if(isBank(alipayAccount)){
            toastr.warning('提示', "请输入支付宝账号！");
            return;
        }
        if(isBank(name)){
            toastr.warning('提示', "请输入真实姓名！");
            return;
        }
        if(isBank(amount)){
            toastr.warning('提示', "请输入金额！");
            return;
        }
        if(isBank(fethpwd)){
            toastr.warning('提示', "请输入提现密码！");
            return;
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/withdrawmng/applyForWithdraw",
            processData: false,
            contentType: false,
            cache: false,//上传文件无需缓存
            data : formdata,
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    toastr.success('提示', '提交申请成功！');
                    $("#addModal").modal("hide");
                    table.ajax.reload();
                }else{
                    toastr.error('提示', data.msg);
                }
            }
        });
    },
    query : function (status) {
        var param = {
            "status": status
        };
        table.settings()[0].ajax.data = param;
        table.ajax.reload();
    },
    refresh : function () {
        table.ajax.reload();
    }
};