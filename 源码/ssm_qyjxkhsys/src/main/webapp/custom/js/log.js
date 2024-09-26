
//获取表格对象
var $logGrid = $("#logGrid");


//搜索按钮的事件
var search = function(){
    var data = $("#searchForm").serializeObject();
    $logGrid.datagrid("load",data);
}

//用户格式化列的函数，建议加上前缀fmt
var fmtTime = function (value, row) {
    return moment(value).format("YYYY-MM-DD HH:mm:ss");
}

var fmtDuration = function(value){
    return value+"毫秒";
}

var fmtExp = function (v) {
    if (v == 0) {
        return "正常";
    } else {
        return "异常";
    }
}

var fmtHotelName = function(v){
    if(v){
        return v;
    }else{
        return "--";
    }
}
