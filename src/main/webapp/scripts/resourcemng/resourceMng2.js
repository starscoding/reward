var table;
var reqType;
var currentResource;
var table;
var resourceMng = {
    init: function () {
        table = $('#tb').treegridData({
            id: 'id',
            parentColumn: 'parentId',
            type: "GET", //请求数据的ajax类型
            url: smile.baseURL() + "/resourceService/getAllResource",   //请求数据的ajax的url
            ajaxParams: {}, //请求数据的ajax的data属性
            expandColumn: null,//在哪一列上面显示展开按钮
            striped: true,   //是否各行渐变色
            bordered: true,  //是否显示边框
            expandAll: false,  //是否全部展开
            columns: [
                {
                    title: '中文名',
                    field: 'name'
                }, {
                    title: '资源名',
                    field: 'value'
                }, {
                    title: 'URL',
                    field: 'url'
                }, {
                    title: '资源类型',
                    field: 'type'
                },/* {
                 title: '图标',
                 field: 'cls'
                 },*/ {
                    title: '描述',
                    field: 'description'
                }
            ]
        });
    },
    addBrother: function () {
        var selectedDatas = table.getSelectDatas();
        var selectedNum = selectedDatas.length;
        if (selectedNum != 1) {
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
    addSon: function () {
        var selectedDatas = table.getSelectDatas();
        var selectedNum = selectedDatas.length;
        if (selectedNum != 1) {
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
    addOrUpdate: function () {
        var resourceName = $('#resourceName').val();
        var resourceValue = $('#resourceValue').val();
        var url = $('#url').val();
        var type = $('#type').val();
        var cls = $('#cls').val();
        var desc = $('#desc').val();
        if (isBank(resourceName)) {
            $('#tipDiv').html("请输入资源中文名！");
            return;
        }
        if (isBank(resourceValue)) {
            $('#tipDiv').html("请输入资源名！");
            return;
        }
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/resourceService/addOrUpdateResource",
            data: {
                name: resourceName,
                value: resourceValue,
                description: desc,
                url: url,
                type: type,
                cls: cls,
                reqType: reqType,
                id: currentResource,
                parentId: currentResource
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '保存成功！');
                    $('#addModal').modal("hide");
                    // table.ajax.reload();
                    window.location.reload();
                } else {
                    toastr.error('提示', '保存失败！');
                }
            }
        })
    },
    refresh: function () {
        // table.ajax.reload();
        window.location.reload();
    },
    edit: function () {
        var selectedDatas = table.getSelectDatas();
        var selectedNum = selectedDatas.length;
        if (selectedNum != 1) {
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
        reqType = "update";
    },
    showSureModal: function () {
        var selectedDatas = table.getSelectDatas();
        var selectedNum = selectedDatas.length;
        if (selectedNum < 1) {
            toastr.warning('提示', '至少选择一行记录！');
            return;
        }
        // console.log(table.rows('.selected').data()[0].id);
        $('#sureModal').modal("show");
    },
    "delete": function () {
        var selectedDatas = table.getSelectDatas();
        var ids = "";
        for (var i = 0; i < selectedDatas.length; i++) {
            var data = selectedDatas[i];
            if (i == selectedDatas.length - 1) {
                ids += data.id;
            } else {
                ids += data.id + ",";
            }
        }
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/resourceService/delResources",
            data: {
                resourceIds: ids
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '删除成功！');
                    $('#sureModal').modal("hide");
                    // table.ajax.reload();
                    window.location.reload();
                } else {
                    toastr.error('提示', '删除失败！');
                }
            }
        });
    }
}