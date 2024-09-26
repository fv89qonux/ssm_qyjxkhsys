$(function () {
    initFormValidate();
})
//获取表格对象
var $deptItemGrid = $("#deptItemGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $deptItemGrid.datagrid("load", data);
}

var fmtNodes = function (v) {
    var node = "";
    _.each(v, function (deptItem) {
        if (deptItem && deptItem.type == 1 && deptItem.level == 2)
            node += deptItem.name + ",";
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
var saveDept = function () {

    if(vm.items.length== 0){
        $.error("请选择考核指标");
        return ;
    }

    //计算权重是否等于100%
    var qz = 0;
    _.each(vm.items, function (item) {
        qz += parseInt(item.qz);
        item.dept = id;
    })

    if(qz != 100){
        $.error("权重之和必须为100%");
        return ;
    }

    var data = {
        dept : id,
        json : JSON.stringify(vm.items)
    }

    $.post("/ssm_qyjxkhsys/info/deptItem/save",data, function (result) {
        if(result.status == 100){
            $.success("保存成功");
            search();
            $("#deptItemModal").modal("hide");
        }else{
            $.error(result.data);
        }
    })
}


var id = null;

/**
 * 弹出修改的弹出框
 */
var toUpdateItem = function () {
    var role = $deptItemGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        $("#name").html(role.name);
        $.getJSON("/ssm_qyjxkhsys/info/deptItem/find?dept="+role.id, function (result) {
            if(result.status == 100){
                vm.items = result.data;
                id = role.id
                $("#deptItemModal").modal("show");
            }else{
                $.error(result.data);
            }
        })


    }
}

/**
 * 判断用户是否选中，弹出确认框
 */
var toDeleteItem = function () {
    var selected = $deptItemGrid.datagrid("getSelectedIds");
    if (selected) {
        $.confirm("你确定要删除这些指标吗？", function () {
            $.post("/ssm_qyjxkhsys/info/deptItem/deleteItem", {ids: selected}, function (result) {
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

var toInfo = function () {
    var role = $deptItemGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        location.href = 'deptItemInfo?id=' + role.id;
    }
}

var jx = function (v,row) {
    if(v)
        return "<label class='label label-success' >"+v+"条指标</label>";
    else
        return "<label class='label label-danger'>未设指标</label>";
}

/**
 * 初始化form的验证信息
 */
var initFormValidate = function () {
    $('#deptItemForm').validate({
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
                url = "/ssm_qyjxkhsys/info/deptItem/createItem";
            } else {
                url = "/ssm_qyjxkhsys/info/deptItem/updateItem";
            }

            $.post(url, user, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $deptItemGrid.datagrid("refresh");
                    $("#deptItemModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });
}