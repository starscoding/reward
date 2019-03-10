var table;
var selectData;
var rewardDetail;
var editType;
var domainMng = {
    init: function () {
        this.initTalble();
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
                title: '域名',
                target: 1,
                data: function (item) {
                    return item.domain;
                }
            }, {
                title: '状态',
                target: 2,
                data: function (item) {
                    var result = "";
                    if(item.status=='0'){
                        result = "<span style='color: #128018;font-size: 16px;'><b>在用中</b></span>";
                    }else if(item.status=='1'){
                        result = "<span style='color: #3b79c6;font-size: 16px;'><b>备用中</b></span>";
                    }else{
                        result = "<span style='color: #f84a0f;font-size: 16px;'><b>已禁用</b></span>";
                    }
                    return result;
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
                url: smile.baseURL() + "/domainMng/getAllDomain",
                type: "post"
            },
            columns: columns,
            select: {
                style: 'muti'
            },
            columnDefs: [  {
                orderable: false,
                className: 'select-checkbox',
                targets:   0
            }],
            order: [[2, 'asc']],
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
                    target: 1,
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
                order: [[2, 'desc']],
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
    showEditModal : function (type) {
        editType = type;
        if(type=="add"){
            $("#editModal").modal("show");
            $("#domain").val("");
        }else{
            var selectedNum = table.rows('.selected').data().length;
            var selectedDatas = table.rows('.selected').data();
            if (selectedNum != 1) {
                toastr.warning('提示', '请选择一行记录编辑！');
                return;
            }
            $("#editModal").modal("show");
            $("#domain").val(selectedDatas[0].domain);
        }
    },
    addOrUpdate : function () {
        var data = table.rows('.selected').data()[0];
        if(isBank($("#domain").val())){
            toastr.warning("请填写域名！");
            return;
        }
        var oldDomain = "";
        if(editType=="edit"){
            oldDomain = data.domain;
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/domainMng/addOrUpdateDomain",
            data :{
                domain : $("#domain").val(),
                status : "1",
                oldDomain : oldDomain,
                type : editType
            },
            dataType : "json",
            success : function (data) {
                if(isNotBank(data)&&data.success=="true"){
                    $("#editModal").modal("hide");
                    toastr.success(data.msg);
                }else{
                    toastr.error(data.msg);
                }
                table.ajax.reload();
            }
        })
    },
    showSureModal : function () {
        var selectNum = table.row('.selected').data().length;
        if(selectNum<1){
            toastr.warning("请选择一行记录删除！");
        }
        $("#sureModal").modal("show");
    },
    "delete" : function () {
        var datas = table.rows('.selected').data();
        var ids = [];
        for (var i = 0 ;i<datas.length;i++){
            ids.push(datas[i].domain);
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/domainMng/deleteDomain",
            data :{
                ids : ids
            },
            dataType : "json",
            success : function (data) {
                if(isNotBank(data)&&data.success=="true"){
                    $("#sureModal").modal("hide");
                    toastr.success(data.msg);
                }else{
                    toastr.error(data.msg);
                }
                table.ajax.reload();
            }
        })
    },
    use : function () {
        var selectNum = table.rows('.selected').data().length;
        if(selectNum!=1){
            toastr.warning("请选择一行记录！");
            return;
        }
        var data = table.rows('.selected').data()[0];
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/domainMng/setDomainUse",
            data :{
                domain : data.domain
            },
            dataType : "json",
            success : function (data) {
                if(isNotBank(data)&&data.success=="true"){
                    toastr.success(data.msg);
                }else{
                    toastr.error(data.msg);
                }
                table.ajax.reload();
            }
        })
    },
    ban : function () {
        var selectNum = table.row('.selected').data().length;
        if(selectNum<1){
            toastr.warning("请至少选择一行记录！");
            return;
        }
        var datas = table.rows('.selected').data();
        var ids = [];
        for (var i = 0 ;i<datas.length;i++){
            ids.push(datas[i].domain);
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/domainMng/banDomain",
            data :{
                ids : ids
            },
            dataType : "json",
            success : function (data) {
                if(isNotBank(data)&&data.success=="true"){
                    toastr.success(data.msg);
                }else{
                    toastr.error(data.msg);
                }
                table.ajax.reload();
            }
        })
    }
}