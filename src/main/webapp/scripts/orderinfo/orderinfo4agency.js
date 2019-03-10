var table;
var selectData;
var player;
var orderinfo = {
    init: function () {
        this.initTime();
        this.initTalble();
        this.initCountInfo();
        // this.initPlayer();
    },
    initCountInfo : function () {
        var nowTime = new Date();
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/orderService/countTurnover4Agency",
            data : {},
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#totalTurnover").html(data.data);
                }else{
                    $("#totalTurnover").html("获取失败");
                }
            }
        });
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/orderService/countCommission",
            data : {},
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#totalCommission").html(data.data);
                }else{
                    $("#totalCommission").html("获取失败");
                }
            }
        });
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/orderService/countTurnover4Agency",
            data : {
                date : nowTime.format("yyyy-MM-dd")
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#todayTurnover").html(data.data);
                }else{
                    $("#todayTurnover").html("获取失败");
                }
            }
        });
        $.ajax({
            type : "post",
            url : smile.baseURL()+"/orderService/countCommission",
            data : {
                date : nowTime.format("yyyy-MM-dd")
            },
            dataType : "json",
            success : function (data) {
                if(data!=null && data.success=='true'){
                    $("#todayCommission").html(data.data);
                }else{
                    $("#todayCommission").html("获取失败");
                }
            }
        });
    },
    initTime: function () {
        var nowTime = new Date();

        $('.form_datetime').datetimepicker({
            // format:'yyyy-mm-dd HH:ii:00',//设置格式
            language: 'zh-CN',
            autoclose: true
        });
        $('#startTime').val(nowTime.format("yyyy-MM-dd 00:00:00"));
        nowTime.setHours(nowTime.getHours() + 24);
        $('#endTime').val(nowTime.format("yyyy-MM-dd 00:00:00"));
    },
    initTalble: function () {
        var columns = [
            {
                title: '打赏金额',
                target: 0,
                data: function (item) {
                    return item.amout + currencyName;
                }
            }, {
                title: '打赏时间',
                target: 1,
                data: function (item) {
                    return item.rewardTime;
                }
            }, {
                title: '打赏视频',
                target: 2,
                data: function (item) {
                    var result = '<a href="#" onclick="orderinfo.watchVideo(\'' + item.url + '\',\''+item.videoTitle + '\',\''+item.imgName+'\')">' + item.videoTitle + '</a>';
                    // console.log(item.url);
                    // var result = '<button type="button" class="btn btn-link" onclick="orderinfo.watchVideo(\''+item.url+'\')">'+item.videoTitle+'</button>';
                    return result;
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
            "dom": 'frt<"bottom"ilp<"clear">>',
            "aLengthMenu": [10, 20, 50, 100],
            "ajax": {
                url: smile.baseURL() + "/orderService/getOrders",
                type: "post",
                data: {
                    "startTime": $('#startTime').val(),
                    "endTime": $('#endTime').val()
                }
            },
            columns: columns,
            // columnDefs: [{
            //     "visible": false,
            //     "targets": 0
            // }],
            order: [[0, 'desc']],
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
    initPlayer : function () {
        // $("#videoPlayer").jPlayer({
        //     ready: function () {
        //         $(this).jPlayer("setMedia", {
        //             title: "Big Buck Bunny",
        //             m4v: smile.baseURL()+url,
        //             ogv: smile.baseURL()+url,
        //             webmv: smile.baseURL()+url
        //             // poster: "http://www.jplayer.org/video/poster/Big_Buck_Bunny_Trailer_480x270.png"
        //         });
        //     },
        //     swfPath: smile.baseURL()+"static/vendor/jPlayer/dist/jplayer",
        //     supplied: "webmv, ogv, m4v",
        //     size: {
        //         width: "470px",
        //         height: "200px",
        //         cssClass: "jp-video-270p"
        //     },
        //     useStateClassSkin: true,
        //     autoBlur: false,
        //     smoothPlayBar: true,
        //     keyEnabled: true,
        //     remainingDuration: true,
        //     toggleDuration: true
        // });
    },
    watchVideo: function (url,title,imgName) {
        $('#videoModal').modal('show');
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
    clearVideo : function () {
        $('#videoModal').modal('hide');
        player.pause();
    }
}