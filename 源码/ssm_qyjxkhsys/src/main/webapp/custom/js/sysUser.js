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
var $sysUserGrid = $("#sysUserGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $sysUserGrid.datagrid("load", data);
}

var fmtRole = function (v, row) {
    if (v == null && row.admin == 1) {
        return "超级管理员";
    } else {
        return v;
    }
}


var fmtSex = function (v, row) {
    if (v == 1) {
        return "男"
    } else {
        return "女"
    }
}

/**
 * 保存员工信息
 */
var saveSysUser = function () {
    $("#sysUserForm").submit();
}


/**
 * 弹出添加员工的弹出框
 */
var toAddSysUser = function () {
    //初始化数据内容
    var sysUser = {
        id: null,
        name: null,
        roleId: null,
        account: null,
        password: null,
        confirmPassword: null,
        job : null,
        phone : null,
        address : null,
        joinTime : null,
        sex : null
    }
    $("#passwordBox,#confirmPasswordBox").show();
    $("#sysUserForm").deserialize(sysUser);
    $("#sysUserForm").validate().resetForm();
    $("#sysUserForm .has-error").removeClass("has-error");

    $("#sysUserModal").modal("show");
}

/**
 * 弹出修改员工的弹出框
 */
var toUpdateSysUser = function () {
    var sysUser = $sysUserGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (sysUser) {

        //禁止修改超级管理员
        if (sysUser.admin == 1) {
            $.error("禁止操作超级管理员");
            return;
        }
        sysUser.joinTime = fmtDate(sysUser.joinTime);

        $("#passwordBox,#confirmPasswordBox").hide();
        //自行封装，将数据填充到form中，要求key与name保持一致
        $("#sysUserForm").deserialize(sysUser);
        $("#sysUserForm").validate().resetForm();
        $("#sysUserForm .has-error").removeClass("has-error");


        $("#sysUserModal").modal("show");
    }
}

/**
 * 重置员工密码 界面
 */
var toResetPwd = function () {
    var sysUser = $sysUserGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (sysUser) {
        //禁止修改超级管理员
        if (sysUser.admin == 1) {
            $.error("禁止操作超级管理员");
            return;
        }
        sysUser.password = null;
        $("#passwordResetForm .has-error").removeClass("has-error");
        $("#passwordResetForm").validate().resetForm();
        $("#passwordResetForm").deserialize(sysUser);
        $("#passwordResetModal").modal("show");
    }
}

var printUser = function () {
    var sysUser = $sysUserGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (sysUser) {
        location.href = "print?id=" + sysUser.id;
    }
}

/**
 * 重置员工密码
 */
var resetPassword = function () {
    $("#passwordResetForm").submit();
}


/**
 * 判断员工是否选中，弹出确认框
 */
var toDeleteSysUser = function () {

    var selected = $sysUserGrid.datagrid("getSelectedIds");
    var selectedNode = $sysUserGrid.datagrid("getSelected");
    if (selected) {
        if (_.filter(selectedNode, function (item) {
                return item.admin == 1
            }).length > 0) {
            $.error("禁止操作超级管理员");
            return;
        }
        $.confirm("你确定要删除这些员工吗？", function () {
            $.post("/ssm_qyjxkhsys/info/sysUser/deleteSysUser", {ids: selected}, function (result) {
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
    $('#sysUserForm').validate({
        rules: {
            name: {
                required: true,
                userName: true,
                maxlength: 10
            },
            roleId: {
                required: true
            },
            account: {
                required: true,
                minlength: 6,
                maxlength: 18
            },
            password: {
                required: true,
                minlength: 6,
                maxlength: 18
            },
            confirmPassword: {
                required: true,
                minlength: 6,
                maxlength: 18,
                equalTo: "#sysUserForm [name='password']"
            },
            sex: {
                required: true
            },
            joinTime: {
                required: true
            },
            job: {
                required: true,
                maxlength: 20
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
            roleId: {
                required: "请选择角色"
            },
            account: {
                required: "请输入账号",
                minlength: "登录账号长度不能少于6个字符",
                maxlength: "登录账号长度不能多于18个字符"
            },
            password: {
                required: "请输入密码",
                minlength: "密码长度不能少于6个字符",
                maxlength: "密码长度不能多于18个字符"
            },
            confirmPassword: {
                required: "请输入确认密码",
                minlength: "确认密码长度不能少于6个字符",
                maxlength: "确认密码长度不能多于18个字符",
                equalTo: "两次密码输入不相符"
            },
            sex: {
                required: "请选择性别"
            },
            joinTime: {
                required: "请选择入职时间"
            },
            job: {
                required: "请输入职位",
                maxlength: "职位长度不能超出20个字符"
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
            var sysUser = $(form).serializeObject();

            var url = null;
            if (sysUser.id == "") {
                url = "/ssm_qyjxkhsys/info/sysUser/createSysUser";
            } else {
                url = "/ssm_qyjxkhsys/info/sysUser/updateSysUser";
            }

            $.post(url, sysUser, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $sysUserGrid.datagrid("refresh");
                    $("#sysUserModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });

    $("#passwordResetForm").validate({
        rules: {
            password: {
                required: true,
                minlength: 6,
                maxlength: 18
            }
        },
        messages: {
            password: {
                required: "请输入新密码",
                minlength: "新密码长度不能少于6个字符",
                maxlength: "新密码长度不能多于18个字符"
            }
        },
        submitHandler: function (form) {
            var sysUser = $(form).serializeObject();

            $.post("/ssm_qyjxkhsys/info/sysUser/resetPassword", sysUser, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("密码重置成功");
                    $("#passwordResetModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })
        }
    })

}