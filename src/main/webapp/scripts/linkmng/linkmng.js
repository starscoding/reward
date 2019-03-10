var table;
var reqType;
var detailTable;
var player;
var linkmng = {
    init: function () {
        this.initTalble();
        var clipboard = new ClipboardJS('.btn');

        clipboard.on('success', function (e) {
            toastr.success("复制成功，赶快去推广吧！");
        });

        clipboard.on('error', function (e) {
            toastr.warning("复制失败，请手动复制！");
        });
    },
    initTalble: function () {
        var columns = [
            {
                title: '<input type="checkbox" name="select_all" id="select-all">',
                target: 0,
                data: function (item) {
                    return "";
                }
            }, {
                title: 'ID',
                target: 1,
                data: function (item) {
                    return item.videoId;
                }
            }/*,{
             title: '时长',
             target: 0,
             data: function (item) {
             return item.timeLength;
             }
             }*/, {
                title: '详情【点击查看视频】',
                target: 2,
                data: function (item) {
                    var result = '<a href="#" onclick="linkmng.watchVideo(\'' + item.videoUrl + '\',\''+item.videoTitle + '\',\''+item.imgName+'\')">' + item.videoTitle + '</a>';
                    // var result = '<button type="button" class="btn btn-link" onclick="linkmng.watchVideo(\'' + item.videoUrl + '\')">' + item.videoTitle + '</button>';
                    return result;
                }
            }, {
                title: '价格',
                target: 3,
                data: function (item) {
                    return item.price;
                }
            }, {
                title: '短链接',
                target: 4,
                data: function (item) {
                    return item.shortUrl;
                }
            }, {
                title: '视频属性',
                target: 5,
                data: function (item) {
                    var result = "";
                    if (item.videoType == "0") {
                        result = "公共"
                    } else {
                        result = "私有";
                    }
                    return result;
                }
            }, {
                title: '收入',
                target: 6,
                data: function (item) {
                    var result = "0";
                    if (!isBank(item.income)) {
                        result = item.income + "&nbsp;&nbsp;|<button type='button' class='btn btn-link' onclick='linkmng.detail(\"" + item.linkId + "\")'>查看详情</button>";
                    }
                    return result;
                }
            }, {
                title: '收入',
                target: 7,
                data: function (item) {
                    return item.linkId;
                }
            }];
        table = $('#userList').DataTable({
            "processing": true,
            "serverSide": false,
            "searching": false,//是否开启搜索
            "bLengthChange": true, //改变每页显示数据数量
            "bPaginate": true, //翻页功能,
            "autoWidth": true,
            // "sPaginationType": "full_numbers",
            // "dom": 'f<"top">t<"bottom"irlp><"clear">',
            // responsive: true,
            "dom": 'Bfrt<"bottom"ilp<"clear">>',
            "aLengthMenu": [10, 20, 50, 100],
            "select": {
                style: 'muti'
            },
            // "pagingType": "input",
            "ajax": {
                url: smile.baseURL() + "/linkmng/getVideoAndLinkInfo",
                type: "post"
            },
            columns: columns,
            columnDefs: [{
                "visible": true,
                "targets": 1
            }, {
                "visible": false,
                "targets": 7
            }, {
                orderable: false,
                className: 'select-checkbox',
                targets: 0
            }],
            buttons: [
                'selectAll',
                'selectNone'
            ],
            order: [[6, 'desc']],
            "language": {
                "url": smile.baseURL() + "/static/data/chinese.json"
            }
        });
        table.on('draw.dt',function() {
            table.column(1, {
                search: 'applied',
                order: 'applied'
            }).nodes().each(function(cell, i) {
                //i 从0开始，所以这里先加1

                i = i+1;
                //服务器模式下获取分页信息，使用 DT 提供的 API 直接获取分页信息

                var page = table.page.info();
                //当前第几页，从0开始

                var pageno = page.page;
                //每页数据

                var length = page.length;
                //行号等于 页数*每页数据长度+行号

                var columnIndex = (i+pageno*length);
                cell.innerHTML = columnIndex;
            });
        });
    },
    refresh: function () {
        table.ajax.reload();
    },
    showSureModal: function () {
        var selectedNum = table.rows('.selected').data().length;
        if (selectedNum < 1) {
            toastr.warning('提示', '至少选择一行记录！');
            return;
        }
        $('#sureModal').modal("show");
    },
    "delete": function () {
        var selectedDatas = table.rows('.selected').data();
        var ids = [];
        for (var i = 0; i < selectedDatas.length; i++) {
            ids.push(selectedDatas[i].linkId);
        }
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/linkmng/delLinkInfos",
            data: {
                ids: ids
            },
            dataType: "json",
            success: function (data) {
                if (data != null && data.success == 'true') {
                    toastr.success('提示', '删除成功！');
                    $('#sureModal').modal("hide");
                    table.ajax.reload();
                } else {
                    toastr.error('提示', '删除失败！');
                }
            }
        });
    },
    watchVideo: function (url,title,imgName) {
        console.log(imgName);
        $('#videoModal').modal('show');
        // alert(smile.baseURL()+url);
        player = videojs('videoplay');
        $("#videoplay").attr("poster",smile.baseURL() +"/data/videoImg/"+ imgName);
        $('#videotitle').text(title);
        $(".vjs-poster:first").css("background-image","url("+smile.baseURL() +"/data/videoImg/"+ imgName+")");
        videojs("videoplay").ready(function(){
            var player = this;
            player.width(550);
            player.pause()
            $('#videomp4').attr('src', smile.baseURL() + url);
            player.load();
        });
    },
    clearVideo: function () {
        $('#videoModal').modal('hide');
        // $('#videoPlayer').jPlayer( "clearMedia" );
        player.pause();
    },
    detail: function (linkId) {
        $("#detailModal").modal("show");

        if (detailTable == null) {
            var columns = [
                {
                    title: '打赏时间',
                    target: 0,
                    data: function (item) {
                        return item.rewardTime;
                    }
                }, {
                    title: '价格',
                    target: 0,
                    data: function (item) {
                        return item.amount;
                    }
                }];
            detailTable = $('#detailList').DataTable({
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
                    url: smile.baseURL() + "/linkmng/getOrdersByLinkId",
                    type: "post",
                    data: {
                        linkId: linkId
                    }
                },
                columns: columns,
                columnDefs: [],
                order: [[0, 'asc']],
                buttons: [
                    'selectAll',
                    'selectNone'
                ],
                "language": {
                    "url": smile.baseURL() + "/static/data/chinese.json"
                }
            });
        } else {
            var param = {
                "linkId": linkId
            };
            detailTable.settings()[0].ajax.data = param;
            detailTable.ajax.reload();
        }

    },
    "export": function () {
        var selectedDatas = table.rows('.selected').data();
        var ids = "";
        if (selectedDatas.length < 1) {
            toastr.warning("请至少选择一条记录！");
            return;
        }
        $("#LinksModal").modal("show");
        var result = "";
        for (var i = 0; i < selectedDatas.length; i++) {
            data = selectedDatas[i];
            result += data.videoTitle + data.shortUrl + "\n";
        }
        $("#links").val(result);
        $("#copy").zclip({
            path: smile.baseURL() + '/static/vendor/zclip/ZeroClipboard.swf',
            copy: $('#links').val(),
            afterCopy: function () {
                toastr.success("已复制到剪切板！");
            }
        });
    },
    showPriceModal: function () {
        var selectedDatas = table.rows('.selected').data();
        var ids = [];
        if (selectedDatas.length < 1) {
            toastr.warning("请至少选择一条记录！");
            return;
        }
        $("#priceModal").modal("show");
        $("#price").val("3.99");
        table.rows('.select-checkbox').select();
    },
    updatePrice: function () {
        var selectedDatas = table.rows('.selected').data();
        var ids = [];
        for (var i = 0; i < selectedDatas.length; i++) {
            ids.push(selectedDatas[i].linkId);
        }
        var price = $("#price").val();
        if (isBank(price)) {
            toastr.warning("请输入价格！");
            return;
        }
        if (parseInt(price)<2.5||parseInt(price)>30) {
            toastr.warning("请合理设置价格！");
            return;
        }
        $.ajax({
            type: "post",
            url: smile.baseURL() + "/linkmng/updatePrice",
            data: {
                ids: ids,
                price: price
            },
            dataType: "json",
            success: function (data) {
                if (isNotBank(data) && data.success == "true") {
                    $("#priceModal").modal("hide");
                    toastr.success(data.msg);
                    table.ajax.reload();
                } else {
                    toastr.warning(data.msg);
                }
            }
        })
    },
    replaceWord : function () {
        var sensitiveWord = $('#sensitiveWord').val();
        var noSensitiveWord = $('#noSensitiveWord').val();
        alert(sensitiveWord+','+noSensitiveWord);
        var content = $('#links').val();
        content = content.replace(new RegExp(sensitiveWord,"g"),noSensitiveWord);
        $('#links').val(content);
    }
};