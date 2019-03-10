var table;
var reqType;
var currentResource;
var resourceMng = {
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
                "style" : 'single',//single,multi
                "selector": 'td:not(:first-child)'
            },
            // "pagingType": "input",
            "ajax": {
                url : smile.baseURL()+"/resourceService/getAllResources",
                type: "post",
                data: {
                    // "userName" : "admin"
                }
            },
            // "column" : columns,
            'treeGrid': {
                'left': 30,
                'expandIcon': '<i class="fa fa-plus"></i>',
                'collapseIcon': '<i class="fa fa-minus"></i>'
            },
            "columns": [
                 // { "data": "","mRender":function () {
                 //     return "";
                 // } },
                {
                    title: '',
                    target: 0,
                    "className": 'treegrid-control',
                    "data": function (item) {
                        if (item.children && item.children.length>0) {
                            return '<i class="fa fa-plus"></i>';
                        }
                        return '';
                    }
                },{
                    title: 'ID',
                    target: 1,
                    data: function (item) {
                        return item.id;
                    }
                },{
                    title: '中文名',
                    target: 2,
                    data: function (item) {
                        return item.name;
                    }
                },{
                    title: '资源名',
                    target: 3,
                    data: function (item) {
                        return item.value;
                    }
                },{
                    title: '地址',
                    target: 4,
                    data: function (item) {
                        return item.url;
                    }
                },{
                    title: '资源类型',
                    target: 5,
                    data: function (item) {
                        return item.type;
                    }
                },{
                    title: '图标',
                    target: 6,
                    data: function (item) {
                        // return item.cls;
                        return '<i class="'+item.cls+'"></i>';
                        // return '<i class="icon_plus_alt"></i>';
                    }
                },{
                    title: '描述',
                    target: 7,
                    data: function (item) {
                        return item.description;
                    }
                },{
                    title: '父节点ID',
                    target: 8,
                    data: function (item) {
                        return item.parentId;
                    }
                }
            ],
            columnDefs: [{
                "visible": false,
                "targets": 1
            },{
                // "width": "1%", "targets": 0
            }, {
                // "bSortable": false, "aTargets": [ 0 ]
            },{
                "visible": false,
                "targets": 8
            }
                // {
                //     orderable: false,
                //     className: 'select-checkbox',
                //     targets:   0
                // },
            ],
            order: [[ 1, 'asc' ]],
            "language": {
                "url": smile.baseURL()+"/static/data/chinese.json"
            }
        } );
    },
    addBrother : function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if(selectedNum!=1){
            toastr.warning('提示', '请选择一行记录！');
            return;
        }
        var data = selectedDatas[0];
        $('#addModal').modal("show");
        $('#resourceName').val("");
        $('#resourceValue').val("");
        $('#type').val("");
        $('#cls').val("");
        $('#desc').val("");
        $('#URL').val("");
        reqType = "add";
        currentResource = data.parentId;
    },
    addSon : function () {
        var selectedNum = table.rows('.selected').data().length;
        var selectedDatas = table.rows('.selected').data();
        if(selectedNum!=1){
            toastr.warning('提示', '请选择一行记录！');
            return;
        }
        var data = selectedDatas[0];
        $('#addModal').modal("show");
        $('#resourceName').val("");
        $('#resourceValue').val("");
        $('#type').val("");
        $('#cls').val("");
        $('#desc').val("");
        $('#URL').val("");
        reqType = "add";
        currentResource = data.id;
    },
    addOrUpdate : function () {
        var resourceName = $('#resourceName').val();
        var resourceValue = $('#resourceValue').val();
        var url = $('#url').val();
        var type = $('#type').val();
        var cls = $('#cls').val();
        var desc = $('#desc').val();
        if(isBank(resourceName)){
            $('#tipDiv').html("请输入资源中文名！");
            return;
        }
        if(isBank(resourceValue)){
            $('#tipDiv').html("请输入资源名！");
            return;
        }
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/resourceService/addOrUpdateResource",
            data : {
                name : resourceName,
                value : resourceValue,
                description : desc,
                url : url,
                type : type,
                cls : cls,
                reqType : reqType,
                id : currentResource,
                parentId : currentResource
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
        currentResource = data.id;
        $('#addModal').modal("show");
        $('#resourceName').val(data.name);
        $('#resourceValue').val(data.value);
        $('#type').val(data.type);
        $('#cls').val(data.cls);
        $('#desc').val(data.description);
        $('#url').val(data.url);
        reqType ="update";
    },
    showSureModal : function () {
        var selectedNum = table.rows('.selected').data().length;
        if(selectedNum<1){
            toastr.warning('提示', '至少选择一行记录！');
            return;
        }
        console.log(table.rows('.selected').data()[0].id);
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
            url : smile.baseURL()+"/resourceService/delResources",
            data : {
                resourceIds : ids
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
    }
}