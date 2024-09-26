$(function () {
    initFormValidate();
})
//获取表格对象
var $itemGrid = $("#itemGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $itemGrid.datagrid("load", data);
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
var saveItem = function () {
    $("#itemForm").submit();
}


/**
 * 弹出添加的弹出框
 */
var toAddItem = function () {
    //初始化数据内容
    var role = {
        id: null,
        name: null,
        project: null,
        standard: null,
        target: null,
        score: null
    }

    $("#itemForm").deserialize(role);
    $("#itemForm").validate().resetForm();
    $("#itemForm .has-error").removeClass("has-error");
    $("#itemModal").modal("show");
}

/**
 * 弹出修改的弹出框
 */
var toUpdateItem = function () {
    var role = $itemGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        $("#itemForm").deserialize(role);
        $("#itemForm").validate().resetForm();
        $("#itemForm .has-error").removeClass("has-error")
        $("#itemModal").modal("show");
    }
}

/**
 * 判断用户是否选中，弹出确认框
 */
var toDeleteItem = function () {
    var selected = $itemGrid.datagrid("getSelectedIds");
    if (selected) {
        $.confirm("你确定要删除这些指标吗？", function () {
            $.post("/ssm_qyjxkhsys/info/item/deleteItem", {ids: selected}, function (result) {
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

var toItemInfo = function () {
    var role = $itemGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {


    }
}

/**
 * 初始化form的验证信息
 */
var initFormValidate = function () {
    $('#itemForm').validate({
        rules: {
            name: {
                required: true,
                maxlength: 50
            },
            project: {
                required: true,
                maxlength: 200
            },
            target: {
                required: true,
                maxlength: 200
            },
            standard: {
                required: true,
                maxlength: 200
            }
        },
        messages: {
            name: {
                required: "请输入指标名称",
                maxlength: "指标名称不能长度50个字符"
            },
            project: {
                required: "请输入考核项目",
                maxlength: "指标名称不能长度200个字符"
            },
            target: {
                required: "请输入考核目标",
                maxlength: "考核目标不能长度200个字符"
            },
            standard: {
                required: "请输入评分标准",
                maxlength: "评分标准不能长度200个字符"
            }
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var user = $(form).serializeObject();

            var url = null;
            if (user.id == "") {
                url = "/ssm_qyjxkhsys/info/item/createItem";
            } else {
                url = "/ssm_qyjxkhsys/info/item/updateItem";
            }

            $.post(url, user, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $itemGrid.datagrid("refresh");
                    $("#itemModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });
}