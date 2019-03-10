var table;
var reqType;
var currentRole;
var oldResources;
var newResources;
var invitationCodeMng = {
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
    },
    edit : function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if(selectedNum!=1){
            toastr.warning('提示', '请选择一行编辑！');
            return;
        }
        var data = selectedDatas[0];
        currentRole = data.id;
        $('#addModal').modal("show");
        $('#roleName').val(data.name);
        $('#roleValue').val(data.value);
        $('#desc').val(data.description);
        reqType ="update";
    },
    showSureModal : function () {
        var selectedNum = table.rows('.selected').data().length;
        if(selectedNum<1){
            toastr.warning('提示', '至少选择一行记录！');
            return;
        }
        $('#sureModal').modal("show");
    },
    "delete" : function () {
        var selectedDatas = table.rows('.selected').data();
        var ids = "";
        for(var i=0;i<selectedDatas.length;i++){
            var data = selectedDatas[i];
            if(i==selectedDatas.length-1){
                ids += data.id;
            }else{
                ids += data.id+",";
            }
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/roleService/delRoles",
            data : {
                roleIds : ids
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    toastr.success('提示', '删除成功！');
                    $('#sureModal').modal("hide");
                    table.ajax.reload();
                }else{
                    toastr.error('提示', '删除失败！');
                }
            }
        });
    },
    setCheckedNode : function (checkableTree,roleId) {
        var self = this;
        oldResources = new Array();
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/resourceService/getResourceByRoleId",
            data : {
                roleId : roleId
            },
            async : false,
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    for(var i=0;i<data.data.length;i++){
                        oldResources.push(data.data[i].id);
                        self.initCheckedNode(checkableTree,data.data[i].name);
                    }
                }else{
                    toastr.error('提示', '获取资源失败！');
                }
            }
        });
    },
    initCheckedNode: function ($checkableTree,checkedNode) {
        var findCheckableNodess = function() {
            return $checkableTree.treeview('search', [ checkedNode, { ignoreCase: false, exactMatch: true } ]);
        };
        var checkableNodes = findCheckableNodess();
        $checkableTree.treeview('checkNode', [ checkableNodes, { silent: $('#chk-check-silent').is(':checked') }]);
    },
    accredit : function () {
        var delResourcesId = new Array();
        var addResourcesId = new Array();
        var allHave = [];
        for(var i = 0;i<oldResources.length;i++){
            var hasData = false;
            for (var j=0;j<newResources.length;j++){
                if(oldResources[i]==newResources[j]){
                    hasData = true;
                    break;
                }

            }
            if(hasData)
                allHave.push(oldResources[i]);
            else
                delResourcesId.push(oldResources[i]);
        }
        for(var i = 0;i<newResources.length;i++){
            var hasData = false;
            for (var j=0;j<allHave.length;j++){
                if(newResources[i]==allHave[j]){
                    hasData = true;
                    break;
                }
            }
            if(!hasData){
                addResourcesId.push(newResources[i]);
            }
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/roleService/roleAccredit",
            data : {
                roleId : currentRole,
                delResourceIds : delResourcesId,
                addResourceIds : addResourcesId
            },
            async : false,
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    toastr.success('提示', '授权成功！');
                    $('#accreditModal').modal("hide");
                    table.ajax.reload();
                }else{
                    toastr.error('提示', '授权失败！');
                }
            }
        });
    }
};