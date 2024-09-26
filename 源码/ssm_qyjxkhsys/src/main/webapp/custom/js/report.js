$(function () {
    $("select").select2({
        language: "zh-CN"
    });

    $('#month,#month2,#month3').datepicker({
        format: 'yyyy-mm',
        language: 'zh-CN',
        autoclose: true,
        startView: 'months',
        maxViewMode: 'months',
        minViewMode: 'months'
    });

})

//搜索按钮的事件
var print = function () {
    var data = $("#searchForm").serializeObject();
    if (data.userId == '' || data.userId == null) {
        $.error("请选择需要打印的员工");
        return;
    }
    location.href = "print?userId=" + data.userId + "&month=" + data.month;
}