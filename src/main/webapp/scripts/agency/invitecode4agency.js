var table;
var reqType;
var invitationCodeMng = {
    init : function () {
        this.initTalble();
        this.initNotice();
    },
    initNotice : function () {
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/configService/getConfByName",
            data : {
                name : "codeNotice"
            },
            dataType : "json",
            success : function (data) {
                if(isNotBank(data)&&data.success=="true"){
                    $("#codeNotice").append(data.data);
                }
            }
        })
    },
    initTalble: function () {
        var columns = [
            {
                title: '邀请码',
                target: 0,
                data: function (item) {
                    return item.code;
                }
            }, {
                title: '购买时间',
                target: 1,
                data: function (item) {
                    return item.createTime;
                }
            }, {
                title: '状态',
                target: 2,
                data: function (item) {
                    var result = "";
                    if(item.status=='0'){
                        result = "<span style='color:#128018;'>未激活</span>"
                    }else if(item.status=='1'){
                        result = "<span style='color:#ff0000;'>已激活</span>"
                    }
                    return result;
                }
            }, {
                title: '生成者',
                target: 3,
                data: function (item) {
                    return item.createName;
                }
            }, {
                title: '激活人',
                target: 4,
                data: function (item) {
                    return item.activeName;
                }
            }];
        table = $('#userList').DataTable( {
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
            "aLengthMenu" : [10, 20, 50,100],
            select :{
                style : 'os'
            },
            // "pagingType": "input",
            "ajax": {
                url : smile.baseURL()+"/invitationCode/getCodesByStatus",
                type: "post"
            },
            columns: columns,
            columnDefs: [ ],
            order: [[ 1, 'asc' ]],
            "language": {
                "url": smile.baseURL()+"/static/data/chinese.json"
            }
        } );
    },
    buyCode : function () {
        var num = $("#codeNum").val();
        if(isBank(num)||num<1){
            toastr.warning("请输入正确的数量！");
            return;
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/invitationCode/buyInviteCode",
            data : {
                num : num
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    toastr.success(data.msg);
                    table.ajax.reload();
                }else{
                    toastr.error(data.msg);
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
    },
    showBuyModal : function () {
        $('#buyCodeModal').modal("show");
    }
};