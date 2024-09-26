$(function () {
    initFormValidate();
})
//获取表格对象
var $weekGrid = $("#weekGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $weekGrid.datagrid("load", data);
}

var fmtNodes = function (v) {
    var node = "";
    _.each(v, function (item) {
        if (item && item.type == 1 && item.level == 2)
            node += item.name + ",";
    })
    if (node == "") {
        return "--";
    }

    node = node.substr(0, node.length - 1);
    return node;
}

/**
 * 保存信息
 */
var saveWeek = function () {
    $("#weekForm").submit();
}


/**
 * 弹出添加的弹出框
 */
var toAddWeek = function () {
    //初始化数据内容
    var role = {
        content: null
    }

    $("#weekForm").deserialize(role);
    $("#weekForm").validate().resetForm();
    $("#weekForm .has-error").removeClass("has-error");
    $("#weekModal").modal("show");
}

/**
 * 弹出修改的弹出框
 */
var toUpdateWeek = function () {
    var role = $weekGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        $("#weekForm").deserialize(role);
        $("#weekForm").validate().resetForm();
        $("#weekForm .has-error").removeClass("has-error")
        $("#weekModal").modal("show");
    }
}

/**
 * 判断用户是否选中，弹出确认框
 */
var toDeleteWeek = function () {
    var selected = $weekGrid.datagrid("getSelectedIds");
    if (selected) {
        $.confirm("你确定要删除这些意见吗？", function () {
            $.post("/ssm_qyjxkhsys/info/week/deleteWeek", {ids: selected}, function (result) {
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

/**
 * 初始化form的验证信息
 */
var initFormValidate = function () {
    $('#weekForm').validate({
        rules: {
            content: {
                required: true,
                maxlength: 200
            },
            title: {
                required: true,
                maxlength: 100
            }
        },
        messages: {
            content: {
                required: "请输入内容",
                maxlength: "内容不能长度200个字符"
            }, title: {
                required: "请输入标题",
                maxlength: "标题不能长度200个字符"
            },
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var user = $(form).serializeObject();

            var url = null;
            if (user.id == "") {
                url = "/ssm_qyjxkhsys/info/week/createWeek";
            } else {
                url = "/ssm_qyjxkhsys/info/week/updateWeek";
            }

            $.post(url, user, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $weekGrid.datagrid("refresh");
                    $("#weekModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });
}