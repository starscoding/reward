var table;
var reqType;
var currentRole;
var oldResources;
var newResources;
var roleMng = {
    initTalble: function () {
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
                style : 'multi'
            },
            // "pagingType": "input",
            "ajax": {
                url : smile.baseURL()+"/roleService/getAllRoles",
                type: "post",
                data: {
                    "userName" : "admin"
                }
            },
            columns: [
                 { "data": "","mRender":function () {
                     return "";
                 } },
                { "data": "id"},
                { "data": "name" },
                { "data": "value" },
                { "data": "description" }
            ],
            columnDefs: [ {
                orderable: false,
                className: 'select-checkbox',
                targets:   0
            },{
                "visible": false,
                "targets": 1
            } ],
            order: [[ 1, 'asc' ]],
            "language": {
                "url": smile.baseURL()+"/static/data/chinese.json"
            }
        } );
    },
    add : function () {
        $('#addModal').modal("show");
        $('#roleName').val("");
        $('#roleValue').val("");
        $('#desc').val("");
        reqType = "add";
    },
    addOrUpdate : function () {
        var roleName = $('#roleName').val();
        var roleValue = $('#roleValue').val();
        var desc = $('#desc').val();
        if(isBank(roleName)){
            $('#tipDiv').html("请输入角色中文名！");
            return;
        }
        if(isBank(roleValue)){
            $('#tipDiv').html("请输入角色名！");
            return;
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/roleService/addOrUpdateRole",
            data : {
                name : roleName,
                value : roleValue,
                desc : desc,
                reqType : reqType,
                id : currentRole
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    toastr.success('提示', '保存成功！');
                    $('#addModal').modal("hide");
                    table.ajax.reload();
                }else{
                    toastr.error('提示', '保存失败！');
                }
            }
        })
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
    showAccreditModal : function () {
        var self = this;
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if(selectedNum!=1){
            toastr.warning('提示', '请选择一个记录授权！');
            return;
        }
        var data = selectedDatas[0];
        currentRole = data.id;
        newResources = new Array();
        $('#accreditModal').modal("show");
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/resourceService/getAllResources",
            data : {},
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    var checkableTree = $('#treeview-checkable').treeview({
                        data: data.data,
                        // data: defaultData,
                        color: "#428bca",
                        showIcon : false,
                        expandIcon: 'glyphicon glyphicon-chevron-right',
                        collapseIcon: 'glyphicon glyphicon-chevron-down',
                        nodeIcon: 'glyphicon glyphicon-bookmark',
                        showCheckbox: true,
                        // showTags : true,
                        onNodeChecked: function(event, node) {
                            // $('#checkable-output').prepend('<p>' + node.text + ' was checked</p>');
                            newResources.push(node.id);
                        },
                        onNodeUnchecked: function (event, node) {
                            // $('#checkable-output').prepend('<p>' + node.text + ' was unchecked</p>');
                            newResources.splice($.inArray(node.id,newResources),1);
                        }
                    });
                    self.setCheckedNode(checkableTree,currentRole);
                }else{
                    toastr.error('提示', '获取资源失败！');
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