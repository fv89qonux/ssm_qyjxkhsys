var saveSysConfig = function () {
    var config = $("#sysConfigForm").serializeObject();
    var a = [];
    $.each(config, function (k, v) {
        var b = {
            code: k,
            value: v
        };
        a.push(b)
    })

    $.post("/ssm_qyjxkhsys/info/sysConfig/saveSysConfig", {json: JSON.stringify(a)}, function (result) {
        if (result.status == STATUS.SUCCESS) {
            $.success("配置信息保存成功");
        } else {
            $.error(result.message);
        }
    })
}