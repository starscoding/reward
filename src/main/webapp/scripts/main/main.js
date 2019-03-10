var main = {
    init : function (userName) {
        $("#loadIndexPage").click();
        var self = this;
        $.ajax({
            type : "post",
            dataType : "json",
            // url : "http://localhost:8080/getMenu",
            url : smile.baseURL()+"/getMenu",
            async: false,
            data : {
                userName :userName
            },
            success : function(data) {
                var records = data.data;
                if(isNotBank(records)){
                    var rst;
                    // var rst = " <ul class='nav'>";
                    $('#side-menu').metisMenu('dispose');
                    for(var i=0;i<records.length;i++){
                        var item = records[i];
                        if(isBank(item.parentId)){
                            $('#side-menu').append(self.createMenuItem(item.id,item.url,item.cls,item.name,item.type));
                        }
                    }
                    for(var i=0;i<records.length;i++){
                        var item = records[i];
                        if(isNotBank(item.parentId)){
                            var el = $(' <ul class="nav nav-second-level"></ul>');
                            el.append('<li>'+self.createMenuItem(item.id,item.url,item.cls,item.name,item.type)+'</li>');
                            $('#'+item.parentId).append(el)
                        }
                    }
                    $('#side-menu').metisMenu();
                }
            }
        });
    },
    createMenuItem : function (id,url,cls,name,type) {
        selft = this;
        var item = "";
        // if(type==1){
        //     item = "<li> <a href='#'><i class='"+cls+" fa-fw'></i> "+name+"<span class='fa arrow'></span></a>"
        //         + "<ul class='nav nav-second-level'> ";
        // }else{
        //     item = "<li id='"+id+"'><a href='#' onclick='main.toPage(\""+url+"\")'><i class='"+cls+"'></i>&nbsp;&nbsp;"+name+"</a></li>";
        // }
        if(type==1) {
            item = "<li id='" + id + "'><a href='#' ><i class='" + cls + " fa-fw' ></i>&nbsp;&nbsp;" + name + "<span class='fa arrow'></span></a></li>";
        }else{
            item = "<li id='" + id + "'><a href='#' onclick='main.toPage(\"" + url + "\")'><i class='" + cls + " fa-fw'></i>&nbsp;&nbsp;" + name + "</a></li>";
        }
        return item;
    },
    toPage : function (url) {
        $('#iframeDiv').attr("src",smile.baseURL()+url);
    }
}