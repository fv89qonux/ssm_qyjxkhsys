/**
 * 基于jquery的一些扩展方法
 * Created by smith on 2015/11/13.
 */
(function ($) {

    /**
     * 将form对象的数据转换成json对象。
     *
     */
    $.fn.serializeObject = function () {
        var a = this.serializeArray();
        var d = {};
        $.each(a, function (i, v) {
            d[v.name] = $.trim(v.value);
        })
        return d;
    }

    /**
     * 将对象反序列化到form中
     * @returns {{}}
     */
    $.fn.deserialize = function (data) {
        var me = this;
        $.each(data, function (k, v) {
            v = $.trim(v);
            me.find("input[type='number'][name='" + k + "'],input[type='text'][name='" + k + "'],input[type='password'][name='" + k + "'],input[type='hidden'][name='" + k + "'],textarea[name='" + k + "']").val(v);
            me.find("input:radio[name='" + k + "'][value='" + v + "'],input:checkbox[name='" + k + "'][value='" + v + "']").prop("checked", true);
            me.find("select[name='" + k + "'] option[value='" + v + "']").prop("selected", true);
        })

        return me;
    }


    $.fn.geti18nInfo = function (title) {
        var data = [];
        this.find(".language").each(function () {
            var language = $(this).find("[name='i18nLanguage']").val();
            var name = $(this).find("[name='i18nName']").val();
            data.push({
                languageId: language,
                name: name
            })
        })

        //首先判断是否存在重复语言
        var languages = [];
        _.each(data, function (item) {
            if (languages.indexOf(item.languageId) == -1) {
                languages.push(item.languageId);
            }
        });

        if (languages.length != data.length) {
            $.error("不能存在重复语言设置");
            return false;
        }

        //判断是否已全部填写
        var result = _.filter(data, function (item) {
            return item.name == ""
        });
        if (result.length > 0) {
            $.error("请填写" + title);
            return false;
        }

        return data;

    }

    $.fn.seti18nInfo = function (data) {
        var me = this;
        var languageBox = [];
        _.each(data, function () {
            languageBox.push({});
        })

        languageVm.languageList = languageBox

        languageVm.$nextTick(function () {
            _.each(data, function (item, i) {
                me.find(".language [name='i18nLanguage']").eq(i).val(item.languageId);
                me.find(".language [name='i18nName']").eq(i).val(item.name);
            })
        })
    }

    $.fn.initI18nInfo = function () {
        languageVm.languageList = [{}];
    }

    $.fn.geti18nmInfo = function (title) {
        var data = [];
        this.find(".language-m").each(function () {
            var language = $(this).find("[name='i18nmLanguage']").val();
            var name = $(this).find("[name='i18nmName']").val();
            var description = $(this).find("[name='i18nmDescription']").val();
            data.push({
                languageId: language,
                name: name,
                description: description
            })
        })

        //首先判断是否存在重复语言
        var languages = [];
        _.each(data, function (item) {
            if (languages.indexOf(item.languageId) == -1) {
                languages.push(item.languageId);
            }
        });

        if (languages.length != data.length) {
            $.error("不能存在重复语言设置");
            return false;
        }

        if (this.find(".i18nmRequired").val() == 'true') {
            //判断是否已全部填写
            var result = _.filter(data, function (item) {
                return item.name == ""
            });
            if (result.length > 0) {
                $.error("请完整的填写" + title);
                return false;
            }

        }

        return data;

    }


    $.fn.seti18nmInfo = function (data) {
        var me = this;
        var languageBox = [];
        _.each(data, function () {
            languageBox.push({});
        })

        languageMVm.languageList = languageBox;

        languageMVm.$nextTick(function () {
            _.each(data, function (item, i) {
                me.find(".language-m [name='i18nmLanguage']").eq(i).val(item.languageId);
                me.find(".language-m [name='i18nmName']").eq(i).val(item.name);
                me.find(".language-m [name='i18nmDescription']").eq(i).val(item.description);
            })
        })
    }

    $.fn.initI18nmInfo = function () {
        languageMVm.languageList = [{}];
    }

    $.fn.getImageInfo = function (title) {
        var data = [];
        this.find(".language-img").each(function () {
            var language = $(this).find("[name='i18mImgLanguage']").val();
            var name = $(this).find("[name='i18mImgName']").val();
            var description = $(this).find("[name='i18mImgDescription']").val();
            data.push({
                languageId: language,
                name: name,
                description: description
            })
        })

        //首先判断是否存在重复语言
        var languages = [];
        _.each(data, function (item) {
            if (languages.indexOf(item.languageId) == -1) {
                languages.push(item.languageId);
            }
        });

        if (languages.length != data.length) {
            $.error("图片国际化证件人中不能存在重复语言设置");
            return false;
        }


        //判断是否已全部填写
        if (this.find(".i18mImgRequired").val() == 'true') {
            var result = _.filter(data, function (item) {
                return item.name == ""
            });
            if (result.length > 0) {
                $.error("请填写" + title);
                return false;
            }
        }

        return data;

    }

    $.fn.setImageSimpleInfo = function (fileName,path) {
        this.find("."+fileName+"PreviewBox").show();
        this.find("."+fileName+"Review").attr("src", path);
    }

    $.fn.setImageInfo = function (data) {
        var me = this;

        this.find("#commonImgUploadReviewBox").show();
        //显示已选文件
        this.find("#commonImgUploadReview").attr("src", data.path);

        //初始化图片的国际化证件人内容
        var languageBox = [];
        _.each(data.imageInfos, function () {
            languageBox.push({});
        })

        languageImgVm.languageList = languageBox;

        languageMVm.$nextTick(function () {
            _.each(data.imageInfos, function (item, i) {
                me.find(".language-img [name='i18mImgLanguage']").eq(i).val(item.languageId);
                me.find(".language-img [name='i18mImgName']").eq(i).val(item.name);
                me.find(".language-img [name='i18mImgDescription']").eq(i).val(item.description);
            })
        })
    }

    $.fn.initImageInfo = function (fileName) {
        this.find("."+fileName+"Box").hide();
        languageImgVm.languageList = [{}];
        this.initImageFile(fileName);
    }


    $.fn.initImageSimpleInfo = function (fileName) {
        this.find("."+fileName+"PreviewBox").hide();
        this.initImageFile(fileName);
    }

    $.fn.initImageFile = function (fileName) {
        this.find("."+fileName+"FileBox .ace-file-input").html("<input type='file' id='" + fileName + "' name='" + fileName + "' />");
        this.find("."+fileName+"FileBox .ace-file-input #" + fileName).ace_file_input(window.setting[fileName+"UploadSetting"]);
    }

    $.fn.initVideoFile = function (fileName) {
        this.find("#commonVideoFileBox .ace-file-input").html("<input type='file' id='" + fileName + "' name='" + fileName + "' />");
        this.find("#commonVideoFileBox .ace-file-input #" + fileName).ace_file_input(commonVideoUploadSetting);
    }

    $.fn.error = function(error){
        this.parents('.form-group').addClass("has-error");
        if(this.next().hasClass("valid-error")){
            this.next().find('.error-msg').html(error);
        }else{
            var i = '<div class="valid-error"><i class="icon-remove" ></i><span class="error-msg">'+error+'</span></div>';
            this.after(i);
        }
    }

    $.imagePreview = function(imgPath){
        $("#imagePreview #image").attr("src",imgPath);
        $("#imagePreview").modal("show");
    }

    $.confirm = function (title, callback) {
        if (!callback) {
            throw new Error("请配置确定的回调函数");
        }
        $("#confirmModal #confirmSureBtn").off().click(callback);
        $("#confirmModal .confirm-title").html(title);
        $("#confirmModal").modal("show");
    }
    $.confirmHide = function () {
        $("#confirmModal").modal("hide");
    }
    $.success = function (message) {
        $("#messageAlert").removeClass("alert-danger").addClass("alert-success").find(".message").html(message);
        $("#messageAlert").fadeIn();
        setTimeout(function () {
            $("#messageAlert").fadeOut();
        }, 2000);
    }
    $.error = function (message,time) {
        $("#messageAlert").removeClass("alert-success").addClass("alert-danger").find(".message").html(message);
        $("#messageAlert").fadeIn();
        setTimeout(function () {
            $("#messageAlert").fadeOut();
        }, time?time:3000);
    }

    $.valid = {
        money: function (data) {
            return /^\d{0,8}\.{0,1}(\d{1,2})?$/.test(data)
        },
        phone : function(data){
            return /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test(data);
        },
        pInt : function(data){
            return /^[1-9]*[1-9][0-9]*$/.test(data);
        }
    }

})(jQuery);

/**
 * 数据接口返回的状态证件人
 * @type {{SUCCESS: number}}
 */
var STATUS = {
    SUCCESS: 100
}

/**
 * 格式化日期函数
 * @param v
 * @returns {*}
 */
var fmtTime = function (v) {
    if(v == "" || v == null){
        return "--";
    }
    return moment(v).format("YYYY-MM-DD HH:mm:ss");
}


/**
 * 格式化日期函数
 * @param v
 * @returns {*}
 */
var fmtDate = function (v) {
    if(v == "" || v == null){
        return "--";
    }
    return moment(v).format("YYYY-MM-DD")
}

/**
 * 格式化钱，加上￥前缀，并且两位小数
 * @param v 钱
 * @returns {*}
 */
var fmtMoney = function (v) {
    if (v.toString().indexOf(".") == -1) {
        return v + ".00";
    } else if (v.toString.split(".")[0].length == 1) {
        return v + "0";
    }
    return v;
}

/**
 * 格式化子节点，已，隔开，并且显示，比如格式化广告的酒店数组
 * @param v
 */
var fmtNodes = function(v){
    var node = "";
    _.each(v,function(item){
        node += item.name + ",";
    })
    if(node == ""){
        return "--";
    }

    node = node.substr(0,node.length - 1);
    return node;
}

try {
    /**
     * 设置ace的事件为click
     */
    window.ace.click_event = "click";
}catch (e){}


var DATE_RANGE_PICKER_DEFAULT = {
    "locale": {
        "direction": "ltr",
        "format": "YYYY-MM-DD HH:mm",
        "separator": "至",
        "applyLabel": "确定",
        "cancelLabel": "取消",
        "fromLabel": "生效日期",
        "toLabel": "失效日期",
        "customRangeLabel": "Custom",
        "daysOfWeek": [
            "日",
            "一",
            "二",
            "三",
            "四",
            "五",
            "六"
        ],
        "monthNames": [
            "一月",
            "二月",
            "三月",
            "四月",
            "五月",
            "六月",
            "七月",
            "八月",
            "九月",
            "十月",
            "十一月",
            "十二月"
        ],
        "firstDay": 1
    },
    "minDate" : new Date()
}
