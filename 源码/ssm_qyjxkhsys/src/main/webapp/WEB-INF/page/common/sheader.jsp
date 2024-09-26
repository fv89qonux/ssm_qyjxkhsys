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

<body class=" ">
