(function ($) {
    "use strict";

    $.fn.treegridData = function (options, param) {
        //如果是调用方法
        if (typeof options == 'string') {
            return $.fn.treegridData.methods[options](this, param);
        }

        //如果是初始化组件
        options = $.extend({}, $.fn.treegridData.defaults, options || {});
        var target = $(this);
        // debugger;
        //得到根节点
        target.getRootNodes = function (data) {
            var result = [];
            $.each(data, function (index, item) {
                if (!item[options.parentColumn]) {
                    result.push(item);
                }
            });
            return result;
        };
        var j = 0;
        //递归获取子节点并且设置子节点
        target.getChildNodes = function (data, parentNode, parentIndex, tbody) {
            $.each(data, function (i, item) {
                if (item[options.parentColumn] == parentNode[options.id]) {
                    var tr = $('<tr></tr>');
                    var nowParentIndex = (parentIndex + (j++) + 1);
                    tr.addClass('treegrid-' + nowParentIndex);
                    tr.addClass('treegrid-parent-' + parentIndex);
                    $.each(options.columns, function (index, column) {
                        var td = $('<td></td>');
                        td.text(item[column.field]);
                        tr.append(td);
                    });
                    tr.attr('id', item.id);
                    tr.click(function () {
                        $.each(data, function (index, row) {
                            $("#" + row.id).removeClass('selectedRow');
                        });
                        if (tr.hasClass('selectedRow'))
                            tr.removeClass('selectedRow');
                        else
                            tr.addClass('selectedRow');
                    });
                    tbody.append(tr);
                    target.getChildNodes(data, item, nowParentIndex, tbody)

                }
            });
        };

        target.getAllDatas =  function () {
            for(var i=0;i<options.datas.length;i++){
                var type = options.datas[i].type;
                if(type=='网页'){
                    options.datas[i].type = '0';
                }else if(type=='目录'){
                    options.datas[i].type = '1';
                }else if(type=='操作权限'){
                    options.datas[i].type = '2';
                }
            }
            return options.datas;
        }

        target.getSelectDatas = function () {
            var result = [];
            for(var i=0;i<options.datas.length;i++){
                var type = options.datas[i].type;
                if(type=='网页'){
                    options.datas[i].type = '0';
                }else if(type=='目录'){
                    options.datas[i].type = '1';
                }else if(type=='操作权限'){
                    options.datas[i].type = '2';
                }
            }
            $.each(options.datas, function (index, row) {
                if($("#" + row.id).hasClass('selectedRow')){
                    result.push(row)
                }
            });
            return result;
        }

        target.addClass('table');
        if (options.striped) {
            target.addClass('table-striped');
        }
        if (options.bordered) {
            target.addClass('table-bordered');
        }
        if (options.url) {
            $.ajax({
                type: options.type,
                url: options.url,
                data: options.ajaxParams,
                dataType: "JSON",
                success: function (data, textStatus, jqXHR) {
                    // debugger;
                    if (data != null && data != '' && data.data != null && data.data.length > 0) {
                        //构造表头
                        options.datas = data.data;

                        for(var i=0;i<data.data.length;i++){
                            var type = data.data[i].type;
                            if(type=='0'){
                                data.data[i].type = '网页';
                            }else if(type=='1'){
                                data.data[i].type = '目录';
                            }else if(type=='2'){
                                data.data[i].type = '操作权限';
                            }
                        }

                        var thr = $('<tr></tr>');
                        $.each(options.columns, function (i, item) {
                            var th = $('<th style="padding:10px;"></th>');
                            th.text(item.title);
                            thr.append(th);
                        });
                        var thead = $('<thead></thead>');
                        thead.append(thr);
                        target.append(thead);

                        //构造表体
                        var tbody = $('<tbody></tbody>');
                        var rootNode = target.getRootNodes(data.data);
                        $.each(rootNode, function (i, item) {
                            var tr = $('<tr></tr>');
                            tr.addClass('treegrid-' + (j + i));
                            $.each(options.columns, function (index, column) {
                                var td = $('<td></td>');
                                td.text(item[column.field]);
                                tr.append(td);
                            });
                            tr.attr('id', item.id);
                            tr.click(function () {
                                $.each(data.data, function (index, row) {
                                    $("#" + row.id).removeClass('selectedRow');
                                });
                                if (tr.hasClass('selectedRow'))
                                    tr.removeClass('selectedRow');
                                else
                                    tr.addClass('selectedRow');
                            });
                            tbody.append(tr);
                            target.getChildNodes(data.data, item, (j + i), tbody);
                        });
                        target.append(tbody);
                        target.treegrid({
                            expanderExpandedClass: options.expanderExpandedClass,
                            expanderCollapsedClass: options.expanderCollapsedClass
                        });
                        if (!options.expandAll) {
                            target.treegrid('collapseAll');
                        }
                    }
                }
            });
        }
        else {
            //也可以通过defaults里面的data属性通过传递一个数据集合进来对组件进行初始化....有兴趣可以自己实现，思路和上述类似
        }
        return target;
    };

    $.fn.treegridData.methods = {
        getAllNodes: function (target, data) {
            return target.treegrid('getAllNodes');
        },
        getAllDatas: function () {

            return options.datas;
        }
        //组件的其他方法也可以进行类似封装........
    };

    $.fn.treegridData.defaults = {
        id: 'id',
        parentColumn: 'parentId',
        data: [],    //构造table的数据集合
        type: "GET", //请求数据的ajax类型
        url: null,   //请求数据的ajax的url
        ajaxParams: {}, //请求数据的ajax的data属性
        expandColumn: null,//在哪一列上面显示展开按钮
        expandAll: true,  //是否全部展开
        striped: false,   //是否各行渐变色
        bordered: false,  //是否显示边框
        columns: [],
        // expanderExpandedClass: 'glyphicon glyphicon-triangle-bottom',//展开的按钮的图标
        // expanderCollapsedClass: 'glyphicon glyphicon-triangle-right'//缩起的按钮的图标
        expanderExpandedClass: 'fa fa-angle-double-down point',//展开的按钮的图标
        expanderCollapsedClass: 'fa fa-angle-double-right point'//缩起的按钮的图标

    };
})(jQuery);