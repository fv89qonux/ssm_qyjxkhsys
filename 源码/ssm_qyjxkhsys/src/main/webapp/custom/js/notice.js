$(function () {
    initFormValidate();
})
//获取表格对象
var $noticeGrid = $("#noticeGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $noticeGrid.datagrid("load", data);
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
var saveNotice = function () {
    $("#noticeForm").submit();
}


/**
 * 弹出添加的弹出框
 */
var toAddNotice = function () {
    //初始化数据内容
    var role = {
        id: null,
        name: null,
        description: null
    }

    $("#noticeForm").deserialize(role);
    $("#noticeForm").validate().resetForm();
    $("#noticeForm .has-error").removeClass("has-error");
    $("#noticeModal").modal("show");
}

/**
 * 弹出修改的弹出框
 */
var toUpdateNotice = function () {
    var role = $noticeGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        $("#noticeForm").deserialize(role);
        $("#noticeForm").validate().resetForm();
        $("#noticeForm .has-error").removeClass("has-error")
        $("#noticeModal").modal("show");
    }
}

/**
 * 判断用户是否选中，弹出确认框
 */
var toDeleteNotice = function () {
    var selected = $noticeGrid.datagrid("getSelectedIds");
    if (selected) {
        $.confirm("你确定要删除这些公告吗？", function () {
            $.post("/ssm_qyjxkhsys/info/notice/deleteNotice", {ids: selected}, function (result) {
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
    $('#noticeForm').validate({
        rules: {
            name: {
                required: true,
                maxlength: 50
            },
            description: {
                required: true,
                maxlength: 200
            }
        },
        messages: {
            name: {
                required: "请输入公告标题",
                maxlength: "公告标题不能长度50个字符"
            },
            description: {
                required: "请输入公告内容",
                maxlength: "公告内容不能长200个字符"
            }
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var user = $(form).serializeObject();

            var url = null;
            if (user.id == "") {
                url = "/ssm_qyjxkhsys/info/notice/createNotice";
            } else {
                url = "/ssm_qyjxkhsys/info/notice/updateNotice";
            }

            $.post(url, user, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $noticeGrid.datagrid("refresh");
                    $("#noticeModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });
}