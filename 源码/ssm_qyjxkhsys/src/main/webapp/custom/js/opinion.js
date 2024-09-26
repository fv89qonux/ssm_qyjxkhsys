$(function () {
    initFormValidate();
})
//获取表格对象
var $opinionGrid = $("#opinionGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $opinionGrid.datagrid("load", data);
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
var saveOpinion = function () {
    $("#opinionForm").submit();
}


/**
 * 弹出添加的弹出框
 */
var toAddOpinion = function () {
    //初始化数据内容
    var role = {
        content: null
    }

    $("#opinionForm").deserialize(role);
    $("#opinionForm").validate().resetForm();
    $("#opinionForm .has-error").removeClass("has-error");
    $("#opinionModal").modal("show");
}

/**
 * 弹出修改的弹出框
 */
var toUpdateOpinion = function () {
    var role = $opinionGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        $("#opinionForm").deserialize(role);
        $("#opinionForm").validate().resetForm();
        $("#opinionForm .has-error").removeClass("has-error")
        $("#opinionModal").modal("show");
    }
}

/**
 * 判断用户是否选中，弹出确认框
 */
var toDeleteOpinion = function () {
    var selected = $opinionGrid.datagrid("getSelectedIds");
    if (selected) {
        $.confirm("你确定要删除这些意见吗？", function () {
            $.post("/ssm_qyjxkhsys/info/opinion/deleteOpinion", {ids: selected}, function (result) {
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
    $('#opinionForm').validate({
        rules: {
            content: {
                required: true,
                maxlength: 200
            }
        },
        messages: {
            content: {
                required: "请输入意见内容",
                maxlength: "意见内容不能长度200个字符"
            },
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var user = $(form).serializeObject();

            var url = null;
            if (user.id == "") {
                url = "/ssm_qyjxkhsys/info/opinion/createOpinion";
            } else {
                url = "/ssm_qyjxkhsys/info/opinion/updateOpinion";
            }

            $.post(url, user, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $opinionGrid.datagrid("refresh");
                    $("#opinionModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });
}