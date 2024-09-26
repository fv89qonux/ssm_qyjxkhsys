$(function () {
    initFormValidate();
    //$("select").select2({
    //    language: "zh-CN"
    //});
    $('#month,#month2,#month3').datepicker({
        format: 'yyyy-mm',
        language: 'zh-CN',
        autoclose: true,
        startView: 'months',
        maxViewMode: 'months',
        minViewMode: 'months'
    });

    $("#userId").change(function () {
            var v = this.value;
            if (v != "") {
                $.getJSON("/ssm_qyjxkhsys/info/deptItem/find?user="+v, function (result) {
                    if(result.status == 100){
                        vm.items = result.data;
                    }else{
                        $.error(result.data);
                    }
                })

            } else {
                vm.items = []
            }
        }
    )
})
//获取表格对象
var $evalGrid = $("#evalGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $evalGrid.datagrid("load", data);
}

var fmtNodes = function (v) {
    var node = "";
    _.each(v, function (eval) {
        if (eval && eval.type == 1 && eval.level == 2)
            node += eval.name + ",";
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
var saveEval = function () {
    $("#evalForm").submit();
}


/**
 * 弹出添加的弹出框
 */
var toAddEval = function () {
    //初始化数据内容
    var role = {
        id: null,
        name: null,
        userId: null,
        month: null
    }

    vm.$set("items", []);
    $("#evalForm").deserialize(role);
    $("#evalForm").validate().resetForm();
    $("#evalForm .has-error").removeClass("has-error");
    $("#evalModal").modal("show");
}

/**
 * 判断用户是否选中，弹出确认框
 */
var toDeleteEval = function () {
    var selected = $evalGrid.datagrid("getSelectedIds");
    if (selected) {
        $.confirm("你确定要删除这些指标吗？", function () {
            $.post("/ssm_qyjxkhsys/info/eval/deleteEval", {ids: selected}, function (result) {
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
    var role = $evalGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        location.href = 'info?id=' + role.id;
    }
}

/**
 * 初始化form的验证信息
 */
var initFormValidate = function () {
    $('#evalForm').validate({
        rules: {
            userId: {
                required: true
            },
            score: {
                required: true,
                pInt: true
            },
            month: {
                required: true
            }
        },
        messages: {
            userId: {
                required: "请输入选择被考核人"
            },
            score: {
                required: "请输入分值",
                pInt: "分值只能为正整数"
            },
            month: {
                required: "请选择所属月份"
            }
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var user = $(form).serializeObject();
            if (vm.items.length == 0) {
                $.error("请添加需要评估的绩效指标");
            }
            var url = "/ssm_qyjxkhsys/info/eval/createEval";

            var items = [];
            _.each(vm.items, function (item) {
                items.push({
                    itemId : item.item,
                    score : item.score
                })
            })

            user.total = vm.total();
            user.items = JSON.stringify(items);

            $.post(url, user, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $evalGrid.datagrid("refresh");
                    $("#evalModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });
}