$(function () {
    initResourceTree();
    initFormValidate();
})
//获取表格对象
var $sysRoleGrid = $("#sysRoleGrid");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $sysRoleGrid.datagrid("load", data);
}

var fmtNodes = function(v){
    var node = "";
    _.each(v,function(item){
        if(item && item.type == 1 && item.level == 2)
            node += item.name + ",";
    })
    if(node == ""){
        return "--";
    }

    node = node.substr(0,node.length - 1);
    return node;
}

/**
 *
 * 初始化菜单资源树
 */
var initResourceTree = function () {
    var setting = {
        async: {
            enable: true,
            url: "/info/sysResource/getAllSysResources",
            dataFilter: filter
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pid",
                rootPId: 0
            }
        },
        check: {
            enable: true,
            chkboxType: {"Y": "ps", "N": "s"}
        },
        view: {
            showIcon: false
        }
    };
    $.fn.zTree.init($("#resourceTree"), setting);

    function filter(treeId, parentNode, data) {
        data = data.data ? data.data : [];
        data.push({id: "-1", name: "根节点", pid: "-2", level: 0});

        var result = [];
        //默认展开1-2级
        _.each(data, function (item) {
            if (item.level == 0 || item.level == 1) {
                item.open = true;
            }
            result.push(item);
        })

        return result;
    }
}

/**
 * 保存信息
 */
var saveSysUser = function () {
    $("#sysRoleForm").submit();
}


/**
 * 弹出添加的弹出框
 */
var toAddSysRole = function () {
    //初始化数据内容
    var role = {
        id: null,
        name: null
    }

    $("#sysRoleForm").deserialize(role);
    $("#sysRoleForm").validate().resetForm();
    $("#sysRoleForm .has-error").removeClass("has-error");
    $("#sysRoleModal").modal("show");
}

/**
 * 弹出修改的弹出框
 */
var toUpdateSysRole = function () {
    var role = $sysRoleGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        $("#sysRoleForm").deserialize(role);
        $("#sysRoleForm").validate().resetForm();
        $("#sysRoleForm .has-error").removeClass("has-error")
        $("#sysRoleModal").modal("show");
    }
}

/**
 * 判断用户是否选中，弹出确认框
 */
var toDeleteSysRole = function () {
    var selected = $sysRoleGrid.datagrid("getSelectedIds");
    if (selected) {
        $.confirm("你确定要删除这些角色吗？", function () {
            $.post("/ssm_qyjxkhsys/info/sysRole/deleteSysRole", {ids: selected}, function (result) {
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
 * 权限配置
 */
var toConfigMenu = function () {
    var role = $sysRoleGrid.datagrid("getSelectedOne");

    //判断是否已选中
    if (role) {
        $("#configResourceForm").deserialize(role)
        $("#configResourceModal .name").html(role.name);

        //设置tree选中
        //先全部清除选中
        var tree = $.fn.zTree.getZTreeObj("resourceTree");
        tree.checkAllNodes(false);
        if (role.resources && role.resources.length > 0) {
            //设置根节点
            role.resources.push({id: "-1", name: "根节点", pid: "-2", level: 0})
            _.each(role.resources,function(resource){
                var nodes = tree.getNodesByParam("id",resource.id);
                if(nodes.length > 0){
                    tree.checkNode(nodes[0],true,false);
                }
            })
        }

        $("#configResourceModal").modal("show");
    }
}

/**
 * 保存角色的菜单等数据
 */
var saveRoleResource = function () {
    var roleId = $("#configResourceForm").serializeObject().id;

    var resourceIds = [];
    var checked = $.fn.zTree.getZTreeObj("resourceTree").getCheckedNodes(true);
    _.each(checked, function (item) {
        if (item.id != "-1")
            resourceIds.push(item.id);
    })

    $.post("/ssm_qyjxkhsys/info/sysRole/saveSysRoleResources", {
        id: roleId,
        resourceIds: resourceIds.join(",")
    }, function (result) {
        if (result.status == STATUS.SUCCESS) {
            $.success("权限设置成功");
            //刷新表格数据
            search();
            $("#configResourceModal").modal("hide");
        } else {
            $.error(result.message);
        }
    })
}

/**
 * 初始化form的验证信息
 */
var initFormValidate = function(){
    $('#sysRoleForm').validate({
        rules:{
            name:{
                required:true,
                userName: true,
                maxlength :20
            }
        },
        messages:{
            name:{
                required:"请输入角色名称,只能为中文或者英文",
                userName: "角色名称只能为中文或者英文",
                maxlength : "角色名称不能长度20个字符"
            }
        },
        submitHandler: function (form) {
            //获取表单内的所有数据
            var user = $(form).serializeObject();

            var url = null;
            if (user.id == "") {
                url = "/ssm_qyjxkhsys/info/sysRole/createSysRole";
            } else {
                url = "/ssm_qyjxkhsys/info/sysRole/updateSysRole";
            }

            $.post(url, user, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("保存成功");
                    //刷新表格数据
                    $sysRoleGrid.datagrid("refresh");
                    $("#sysRoleModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })

        }
    });
}