$(function () {
    $("select").select2({
        language: "zh-CN"
    });
    initFormValidate();

    $('#month,#month2').datepicker({
        format: 'yyyy-mm',
        language: 'zh-CN',
        autoclose: true,
        startView: 'months',
        maxViewMode: 'months',
        minViewMode: 'months'
    });

    $('#time').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN'
    });

})

//获取表格对象
var $attenGrid = $("#attenGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $attenGrid.datagrid("load", data);
}

var fmtType = function (v, row) {

    if (v == "CHIDAO") {
        return "迟到"
    }
    if (v == "ZAOTUI") {
        return "早退"
    }
    if (v == "LINSHIQIANTUI") {
        return "临时签退"
    }
    if (v == "YEWUPILOU") {
        return "业务纰漏"
    }
    if (v == "SHIJIA") {
        return "事假"
    }
    if (v == "BINGJIA") {
        return "病假"
    }
}

/**
 * 保存考勤信息
 */
var saveAtten = function () {
    $("#attenForm").submit();
}


/**
 * 弹出添加考勤的弹出框
 */
var toAddAtten = function () {
    //初始化数据内容
    var atten = {
        id: null,
        name: null,
        month: null,
        type: null,
        time: null,
    }
    $("#attenForm").deserialize(atten);
    $("#attenForm").validate().resetForm();
    $("#attenForm .has-error").removeClass("has-error");

    $("#attenModal").modal("show");
}


/**
 * 判断考勤是否选中，弹出确认框
 */
var toDeleteAtten = function () {

    var selected = $attenGrid.datagrid("getSelectedIds");
    var selectedNode = $attenGrid.datagrid("getSelected");
    if (selected) {
        $.confirm("你确定要删除这些考勤吗？", function () {
            $.post("/ssm_qyjxkhsys/info/atten/deleteAtten", {ids: selected}, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("删除成功");
                    //刷新表格数据
                    search();
                    $.confirmHide();
                } else {
                    $.error(result.message);
                }
            })

        });
    }
}

var initFormValidate = function () {
    $('#attenForm').validate({
        rules: {
            time: {
                required: true
            },
            month: {
                required: true
            }
        },
        messages: {
            time: {
                required: "请选择发生日期"
            },
            month: {
                required: "请选择所属月份"
            }
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var atten = $(form).serializeObject();
            var url = "/ssm_qyjxkhsys/info/atten/createAtten";

            $.post(url, atten, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $attenGrid.datagrid("refresh");
                    $("#attenModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });

}