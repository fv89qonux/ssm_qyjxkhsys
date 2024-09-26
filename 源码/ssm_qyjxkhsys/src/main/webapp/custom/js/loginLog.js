
//获取表格对象
var $loginLogGrid = $("#loginLogGrid");


//搜索按钮的事件
var search = function(){
    var data = $("#searchForm").serializeObject();
    $loginLogGrid.datagrid("load",data);
}

//用户格式化列的函数，建议加上前缀fmt
var fmtTime = function (value, row) {
    return moment(value).format("YYYY-MM-DD HH:mm:ss");
}

