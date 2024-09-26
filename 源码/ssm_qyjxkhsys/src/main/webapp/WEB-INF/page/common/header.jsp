<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/7/1
  Time: 13:20
  界面的header公用部分，包含公用的js，css等。
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="renderer" content="webkit">
    <meta charset="utf-8"/>

    <title>绩效管理系统</title>

    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href="/ssm_qyjxkhsys/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="/ssm_qyjxkhsys/assets/css/font-awesome.min.css" rel="stylesheet"/>


    <!--[if IE 7]>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/http%20_fonts.googleapis.com_css%20family=Open+Sans%20400,300.css"/>

    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/ace-rtl.min.css"/>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/ace-skins.min.css"/>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/select2.css" />
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/datepicker.css" />
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/bootstrap-timepicker.css" />
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/daterangepicker.css" />

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/ace-ie.min.css"/>
    <![endif]-->
    <link href="/ssm_qyjxkhsys/custom/css/base.css" rel="stylesheet"/>

    <script src="/ssm_qyjxkhsys/assets/js/ace-extra.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/vue.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/lodash.min.js"></script>

    <!--[if lt IE 9]>
    <script src="/ssm_qyjxkhsys/assets/js/html5shiv.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/respond.min.js"></script>
    <![endif]-->

    <!-- basic scripts -->

    <!--[if !IE]> -->

    <script src="/ssm_qyjxkhsys/assets/js/jquery-2.0.3.min.js"></script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script src="/ssm_qyjxkhsys/assets/js/jquery-1.10.2.min.js"></script>
    <![endif]-->



    <!--[if !IE]> -->

    <script type="text/javascript">
        window.jQuery || document.write("<script src='/assets/js/jquery-2.0.3.min.js'>" + "<" + "/script>");
    </script>

    <!-- <![endif]-->

    <!--[if IE]>
    <script type="text/javascript">
        window.jQuery || document.write("<script src='/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
    </script>
    <![endif]-->

    <script type="text/javascript">
        if ("ontouchend" in document) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
    </script>
    <script src="/ssm_qyjxkhsys/assets/js/bootstrap.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/typeahead-bs2.min.js"></script>

    <!-- ace scripts -->

    <script src="/ssm_qyjxkhsys/assets/js/ace-elements.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/ace.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/select2.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery.ajaxfileupload.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/date-time/bootstrap-datepicker.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/date-time/bootstrap-timepicker.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/date-time/locales/bootstrap-datepicker.zh-CN.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/date-time/moment.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/date-time/daterangepicker.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery.nestable.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery-validation/js/jquery.validate.js"  type="text/javascript" ></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery-validation/js/localization/messages_zh.js"  type="text/javascript" ></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery-validation/js/additional-methods.js" type="text/javascript" ></script>
    <!-- inline scripts related to this page -->
    <script src="/ssm_qyjxkhsys/assets/js/jquery.datagrid.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery.extends.js"></script>

    <script>
        $.validator.setDefaults({
            debug: true
        })
    </script>

</head>

<body class="navbar-fixed breadcrumbs-fixed">
<div class="navbar navbar-default navbar-fixed-top" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="navbar-container" id="navbar-container">
        <div class="navbar-header pull-left">
            <a href="#" class="navbar-brand">
                <small>
                    <i class="icon-leaf green"></i>
                    <span class="white"> 绩效管理系统</span>
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation">
            <ul class="nav ace-nav" id="aceNav">

                <li class="light-blue">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        ${sessionScope.LOGINER.name}

                        <i class="icon-caret-down"></i>
                    </a>

                    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                        <li>
                            <a href="#" class="change-password" >
                                <i class="icon-cog"></i>
                                修改密码
                            </a>
                        </li>
                        <li class="divider"></li>

                        <li>
                            <a href="/ssm_qyjxkhsys/info/logout">
                                <i class="icon-off"></i>
                                安全退出
                            </a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>

<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>

    <div class="modal fade"  id="changePasswordModal" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header no-padding">
                    <div class="table-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            <span class="white">&times;</span>
                        </button>
                        修改密码
                    </div>
                </div>
                <div class="modal-body">
                    <!-- BEGIN FORM-->
                    <form  class="form-horizontal" id="changePasswordForm" onsubmit="return false">
                        <div class="form-body">
                            <div class="form-group">
                                <label class="col-md-3 control-label">原密码 </label>

                                <div class="col-md-7">
                                    <input type="password" class="form-control " placeholder="密码长度6-18个字符" name="passwordOld" autofocus>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">新密码 </label>

                                <div class="col-md-7">
                                    <input type="password" class="form-control "  placeholder="密码长度6-18个字符" name="password">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-3 control-label">确认密码 </label>

                                <div class="col-md-7">
                                    <input type="password" class="form-control "  placeholder="密码长度6-18个字符" name="passwordConfirm">
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer no-margin-top">
                    <button class="btn btn-sm btn-default" data-dismiss="modal">
                        <i class="icon-remove"></i>
                        关闭
                    </button>
                    <button class="btn btn-sm btn-primary confirm" >
                        <i class="icon-ok"></i>
                        确认
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

    <div id="imagePreview" class="modal fade" role="dialog" style="z-index: 99999;">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header no-padding">
                    <div class="table-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            <span class="white">&times;</span>
                        </button>
                        图片查看
                    </div>
                </div>

                <div class="modal-body padding-6">
                    <div>
                        <img id="image" style="width: 100%;" />
                    </div>
                </div>

                <div class="modal-footer no-margin-top">
                    <button class="btn btn-sm btn-default" data-dismiss="modal">
                        <i class="icon-remove"></i>
                        关闭
                    </button>
                </div>
            </div>
        </div>
    </div>

    <!-- 此处是通用的确认提示框和消息操作成功的提示框 -->
    <div id="confirmModal" class="modal fade" role="dialog">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header no-padding">
                    <div class="table-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                            <span class="white">&times;</span>
                        </button>
                        确认
                    </div>
                </div>

                <div class="modal-body padding-6">
                    <h4 class="confirm-title"></h4>
                </div>

                <div class="modal-footer no-margin-top">
                    <button class="btn btn-sm btn-default" data-dismiss="modal">
                        <i class="icon-remove"></i>
                        取消
                    </button>
                    <button class="btn btn-sm btn-primary" id="confirmSureBtn">
                        <i class="icon-ok"></i>
                        确定
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- PAGE CONTENT ENDS -->
    <div id="messageAlert" class="alert alert-danger alert-dismissible top " role="alert">
            <span class="message">
                操作成功
            </span>
    </div>


    <script type="text/javascript">
        var changePassword = function () {
            var data = $("#changePasswordForm").serializeObject();

            if(data.passwordOld == "" || data.password == "" || data.passwordConfirm == ""
                    || data.passwordOld.length <6 || data.passwordOld.length > 20
                    || data.password.length <6 || data.password.length > 20
                    || data.passwordConfirm.length <6 || data.passwordConfirm.length > 20){

                $.error("请输入密码，密码长度在6-20之间");
                return ;
            }
            if(data.passwordOld == data.password){
                $.error("新密码不能与旧密码相同");
                return ;
            }
            if(data.password != data.passwordConfirm){
                $.error("两次密码不同");
                return ;
            }

            $.post("${ctx}/info/changePassword",data, function (result) {
                if (result.status == STATUS.SUCCESS) {
                    $.success("密码修改成功");
                    $("#changePasswordModal").modal("hide");
                } else {
                    $.error(result.message);
                }
            })
        }

        $(function () {
            $(".change-password").click(function(){
                $("#changePasswordForm")[0].reset();
                $("#changePasswordModal").modal("show");

            })
            $("#changePasswordModal button.confirm").click(changePassword);
            $("#changePasswordModal button.btn-default").click(function () {
                $("#changePasswordForm")[0].reset();
            });

            $("#breadcrumbs").addClass("breadcrumbs-fixed")
        })

    </script>

    <div class="main-container-inner">
        <a class="menu-toggler" id="menu-toggler" href="#">
            <span class="menu-text"></span>
        </a>

        <div class="sidebar " id="sidebar">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">

                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span>

                    <span class="btn btn-info"></span>

                    <span class="btn btn-warning"></span>

                    <span class="btn btn-danger"></span>
                </div>
            </div><!-- #sidebar-shortcuts -->

            <jsp:include page="menu.jsp"></jsp:include>

            <div class="sidebar-collapse" id="sidebar-collapse">
                <i class="icon-double-angle-left" data-icon1="icon-double-angle-left"
                   data-icon2="icon-double-angle-right"></i>
            </div>

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
            </script>
        </div>
