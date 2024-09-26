$(function () {
    initFormValidate();
})
//获取表格对象
var $deptGrid = $("#deptGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $deptGrid.datagrid("load", data);
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

var jx = function (v,row) {
    if(v)
        return "<label class='label label-success' title='点击查看' onclick='info(\""+row.id+"\")'>"+v+"条指标</label>";
    else
        return "<label class='label label-danger'>未设指标</label>";
}

/**
 * 保存信息
 */
var saveDept = function () {
    $("#deptForm").submit();
}


/**
 * 弹出添加的弹出框
 */
var toAddDept = function () {
    //初始化数据内容
    var role = {
        id: null,
        name: null,
        description: null
    }

    $("#deptForm").deserialize(role);
    $("#deptForm").validate().resetForm();
    $("#deptForm .has-error").removeClass("has-error");
    $("#deptModal").modal("show");
}

/**
 * 弹出修改的弹出框
 */
var toUpdateDept = function () {
    var role = $deptGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        $("#deptForm").deserialize(role);
        $("#deptForm").validate().resetForm();
        $("#deptForm .has-error").removeClass("has-error")
        $("#deptModal").modal("show");
    }
}

/**
 * 判断用户是否选中，弹出确认框
 */
var toDeleteDept = function () {
    var selected = $deptGrid.datagrid("getSelectedIds");
    if (selected) {
        $.confirm("你确定要删除这些部门吗？", function () {
            $.post("/ssm_qyjxkhsys/info/dept/deleteDept", {ids: selected}, function (result) {
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
    $('#deptForm').validate({
        rules: {
            name: {
                required: true,
                maxlength: 50
            }
        },
        messages: {
            name: {
                required: "请输入部门名称",
                maxlength: "部门名称不能长度50个字符"
            }
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var user = $(form).serializeObject();

            var url = null;
            if (user.id == "") {
                url = "/ssm_qyjxkhsys/info/dept/createDept";
            } else {
                url = "/ssm_qyjxkhsys/info/dept/updateDept";
            }

            $.post(url, user, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $deptGrid.datagrid("refresh");
                    $("#deptModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });
}