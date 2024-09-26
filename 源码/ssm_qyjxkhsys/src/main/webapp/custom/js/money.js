$(function () {
    $("select").select2({
        language: "zh-CN"
    });
    initFormValidate();

    $('#month,#month2,#month3').datepicker({
        format: 'yyyy-mm',
        language: 'zh-CN',
        autoclose: true,
        startView: 'months',
        maxViewMode: 'months',
        minViewMode: 'months'
    });

})

//获取表格对象
var $moneyGrid = $("#moneyGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $moneyGrid.datagrid("load", data);
}

var fmtSex = function (v, row) {
    if (v = 1) {
        return "男"
    } else {
        return "女"
    }
}

/**
 * 保存工资信息
 */
var saveMoney = function () {
    $("#moneyForm").submit();
}


/**
 * 弹出添加工资的弹出框
 */
var toAddMoney = function () {
    //初始化数据内容
    var money = {
        id: null,
        userId: null,
        jbgz: null,
        jj: null,
        month: null
    }
    $("#moneyForm").deserialize(money);
    $("#moneyForm").validate().resetForm();
    $("#moneyForm .has-error").removeClass("has-error");

    $("#moneyModal").modal("show");
}

/**
 * 弹出修改工资的弹出框
 */
var toUpdateMoney = function () {
    var money = $moneyGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (money) {
        money.month = moment(money.month).format("YYYY-MM");
        $("#moneyForm").deserialize(money);
        $("#moneyForm").validate().resetForm();
        $("#moneyForm .has-error").removeClass("has-error");

        $("#moneyModal").modal("show");
    }
}

var toPrint = function () {
    var data = $("#searchForm").serializeObject();
    location.href = "print?userId=" + data.userId + "&month=" + data.month;
}


var initFormValidate = function () {
    $('#moneyForm').validate({
        rules: {
            jbgz: {
                required: true,
                money: true
            },
            jj: {
                required: true,
                money: true
            },
            month: {
                required: true
            }
        },
        messages: {
            jbgz: {
                required: "请输入基本工资",
                money: '基本工资格式错误'
            },
            jj: {
                required: "请输入奖金",
                money: '奖金格式错误'
            },
            month: {
                required: "请选择所属月份"
            }
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var money = $(form).serializeObject();

            var url = "/ssm_qyjxkhsys/info/money/saveUserMoney";

            $.post(url, money, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $moneyGrid.datagrid("refresh");
                    $("#moneyModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });

}