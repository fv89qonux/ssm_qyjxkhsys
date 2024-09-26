$(function () {
    $("#sys,#position").select2({
        language: "zh-CN"
    });
    initFormValidate();

    $('#joinTime').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN'
    });

})

//获取表格对象
var $memberGrid = $("#memberGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $memberGrid.datagrid("load", data);
}

var fmtSex = function (v, row) {
    if(v = 1){
        return "男"
    }else {
        return "女"
    }
}

/**
 * 保存员工信息
 */
var saveMember = function () {
    $("#memberForm").submit();
}


/**
 * 弹出添加员工的弹出框
 */
var toAddMember = function () {
    //初始化数据内容
    var member = {
        id: null,
        name: null,
        joinTime: null,
        phone: null,
        address: null,
        sex: null
    }
    $("#memberForm").deserialize(member);
    $("#memberForm").validate().resetForm();
    $("#memberForm .has-error").removeClass("has-error");

    $("#memberModal").modal("show");
}

/**
 * 弹出修改员工的弹出框
 */
var toUpdateMember = function () {
    var member = $memberGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (member) {
        member.joinTime = fmtDate(member.joinTime);
        $("#memberForm").deserialize(member);
        $("#memberForm").validate().resetForm();
        $("#memberForm .has-error").removeClass("has-error");

        $("#memberModal").modal("show");
    }
}


/**
 * 判断员工是否选中，弹出确认框
 */
var toDeleteMember = function () {

    var selected = $memberGrid.datagrid("getSelectedIds");
    var selectedNode = $memberGrid.datagrid("getSelected");
    if (selected) {
        $.confirm("你确定要删除这些员工吗？", function () {
            $.post("/ssm_qyjxkhsys/info/member/deleteMember", {ids: selected}, function (result) {
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
    $('#memberForm').validate({
        rules: {
            name: {
                required: true,
                userName: true,
                maxlength: 10
            },
            sex: {
                required: true
            },
            joinTime: {
                required: true
            },
            phone: {
                required: true,
                minlength: 11,
                maxlength: 11
            },
            address: {
                required: true,
                maxlength: 50
            }
        },
        messages: {
            name: {
                required: "请输入员工姓名,只能为中文或者英文",
                userName: "员工姓名只能为中文或者英文",
                maxlength: "员工姓名长度不能超出10个字符"
            },
            sex: {
                required: "请选择性别"
            },
            joinTime :{
                required: "请选择入职时间"
            },
            phone: {
                required: "请输入手机",
                minlength: "手机号码格式错误",
                maxlength: "手机号码格式错误"
            },
            address: {
                required: "请输入地址",
                maxlength: "地址长度不能多于50个字符"
            }
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var member = $(form).serializeObject();

            var url = null;
            if (member.id == "") {
                url = "/ssm_qyjxkhsys/info/member/createMember";
            } else {
                url = "/ssm_qyjxkhsys/info/member/updateMember";
            }

            $.post(url, member, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $memberGrid.datagrid("refresh");
                    $("#memberModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });

}