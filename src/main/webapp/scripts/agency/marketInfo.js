var table;
var reqType;
var marketInfo = {
    init : function () {
      this.initCountInfo();
      this.initTalble();
    },
    initCountInfo : function () {
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/marketInfo/countMarketNum",
            data : {},
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#marketinfo").html(data.data+"");
                }else{
                    $("#marketinfo").html("获取失败");
                }
            }
        });
    },
    initTalble: function () {
        var columns = [
            {
                title: '用户名',
                target: 0,
                data: function (item) {
                    return item.activeName;
                }
            }, {
                title: '注册时间',
                target: 1,
                data: function (item) {
                    return item.createTime;
                }
            }, {
                title: '所用邀请码',
                target: 2,
                data: function (item) {
                    return item.code;
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
            select :{
                style : 'os'
            },
            // "pagingType": "input",
            "ajax": {
                url : smile.baseURL()+"/invitationCode/getCodesByStatus",
                type: "post",
                data : {
                    "status": 1
                }
            },
            columns: columns,
            columnDefs: [ ],
            order: [[ 1, 'asc' ]],
            "language": {
                "url": smile.baseURL()+"/static/data/chinese.json"
            }
        } );
    },
    add : function () {
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/invitationCode/createCode",
            data : {},
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    toastr.success('提示', '生成邀请码成功！');
                    table.ajax.reload();
                }else{
                    toastr.error('提示', '生成邀请码失败！');
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