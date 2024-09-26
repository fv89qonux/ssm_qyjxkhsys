$(function () {

    var now = new Date();
    now.setDate(now.getDate() -1 );

    $('#arriveTime').datepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN'
    });

    $.validator.addMethod('znumber', function (value, element, params) {
        if (!isNaN(value) && value >= 0) {
            return true;
        } else {
            return false;
        }
    }, '必须为正数');


    $("#attachmentBox").on("click", '.attachment-remove', function () {
        $(this).parent().remove();
    })


    $("#informationGrid").on("click", '.btn-opt', function () {
        var number = $(this).attr("data-number");

        var data = $("#informationGrid").datagrid("getData").data;
        var row = null;
        for(var i in data){
            var r = data[i];
            if(r.number == number){
                row =  data[i];
            }
        }
        $("#informationModal #name").val(row.name);
        $("#informationModal #number").val(row.number);
        $("#informationModal #arriveTime").val(row.arriveTime?fmtDate(row.arriveTime):"");
        $("#informationModal #arriveCount").val(row.arriveCount);

        $("#informationModal").modal("show");
    });

})
//获取表格对象
var $informationGrid = $("#informationGrid");
//证件人表单对象
var $informationForm = $("#informationForm");

//搜索按钮的事件
var search = function () {
    var data = $("#searchForm").serializeObject();
    $informationGrid.datagrid("load", data);
}

var fmtStatus = function (value, row) {
    //此处显示灯泡的逻辑
    if (row.count < row.low) {
        return "<label class='circle red'></label>"
    } else if (row.count > row.high) {
        return "<label class='circle yellow'></label>"
    } else {
        return "<label class='circle green'></label>"
    }
}

var arriveTime = function (v,row) {
    if ( v != null) {
        return fmtDate(v);
    }else{
        return '--';
    }
}

var arrive = function (v,row) {
    if (  v != null) {
        return v;
    }else{
        return '--';
    }
}

var fmtStatus1 = function (value, row) {
    if (  row.arriveCount != null) {
        //此处计算新的状态
        var count = row.count + row.arriveCount;
        if (count < row.low) {
            return "<label class='circle red'></label>"
        } else if (count > row.high) {
            return "<label class='circle yellow'></label>"
        } else {
            return "<label class='circle green'></label>"
        }
    }else{
        return '--';
    }
}

var fmtOpt = function (v,row) {
    return "<a class='btn btn-xs btn-primary btn-opt' data-number='"+row.number+"' >跟进</a>";
}

var onLoadSuccess = function (result) {
}

var imagePreview = function (el) {
    var $el = $(el);
    $.imagePreview($el.data("src"));
}
var saveInformation = function () {
    var data = $("#informationForm").serializeObject();
    console.log(data);
    $.post("/ssm_qyjxkhsys/info/information/saveInformation",data,function (result) {
        if (result.status == STATUS.SUCCESS) {
            $.success("跟进信息保存成功");
            search();
            $("#informationModal").modal("hide");
        } else {
            $.error(result.message);
        }
    })

}

var clearInformation = function () {
    $("#informationModal #arriveTime").val("");
    $("#informationModal #arriveCount").val("");
}

