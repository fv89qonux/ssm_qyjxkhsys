<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="renderer" content="webkit">
    <meta charset="utf-8"/>
    <title>绩效管理系统</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <!-- basic styles -->

    <link href="/ssm_qyjxkhsys/assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/font-awesome.min.css"/>

    <!--[if IE 7]>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/font-awesome-ie7.min.css"/>
    <![endif]-->

    <!-- page specific plugin styles -->

    <!-- fonts -->

    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/http%20_fonts.googleapis.com_css%20family=Open+Sans%20400,300.css"/>

    <!-- ace styles -->

    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/ace.min.css"/>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/ace-rtl.min.css"/>

    <!--[if lte IE 8]>
    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/ace-ie.min.css"/>
    <![endif]-->

    <!-- inline styles related to this page -->

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

    <!--[if lt IE 9]>
    <script src="/ssm_qyjxkhsys/assets/js/html5shiv.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="login-layout">
<div class="main-container">
    <div class="main-content">
        <div class="row">
            <div class="col-sm-10 col-sm-offset-1">
                <div class="login-container">
                    <div class="center">
                        <h1>
                            <i class="icon-leaf green"></i>
                            <span class="white">绩效管理系统</span>
                        </h1>
                        <h4 class="blue">&copy; v1.0.0</h4>
                    </div>

                    <div class="space-6"></div>

                    <div class="position-relative">
                        <div id="login-box" class="login-box visible widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header blue lighter bigger">
                                        <i class="icon-coffee green"></i>
                                        系统入口
                                    </h4>

                                    <div class="space-6"></div>

                                    <form id="loginForm" onsubmit="return false">
                                        <fieldset>
                                            <div class="form-group">
                                                <label class="block clearfix">
															<span class="block input-icon input-icon-right">
																<input type="text" class="form-control" name="name"
                                                                       placeholder="用户名或手机号码" autofocus/>
																<i class="icon-user"></i>
															</span>
                                                </label>
                                            </div>
                                            <div class="form-group">

                                                <label class="block clearfix">
															<span class="block input-icon input-icon-right">
																<input type="password" class="form-control"
                                                                       name="password" placeholder="登录密码"/>
																<i class="icon-lock"></i>
															</span>
                                                </label>

                                            </div>
                                            <div class="form-group">

                                                <label class="block clearfix ">
                                                    <input type="checkbox" value="1" class="ace checkbox" name="remember"/>
                                                    <span class="lbl"></span>
                                                    记住密码
                                                </label>

                                            </div>

                                            <div class="form-group clearfix" id="validatediv">


                                                <%--<table >--%>
                                                <%--<tr>--%>
                                                <%--<td width="30%">--%>
                                                <%--<span class=" input-icon input-icon-right " >--%>
                                                <%--<input type="text" class="form-control " placeholder="验证码" name="randomCode"  />--%>
                                                <%--</span>--%>
                                                <%--</td>--%>
                                                <%--<td width="30%">--%>
                                                <%--<img id="validateImg" style="cursor: pointer;" src="/ssm_qyjxkhsys/info/randomCode" onclick="changeValidateCode(this)"/>--%>
                                                <%--</td>--%>
                                                <%--<td width="40%" align="right">--%>
                                                <%--<span  ><a href="javascript:;" onclick="changeValidateCode();">看不清，换一张</a>	</span>--%>
                                                <%--</td>--%>

                                                <%--</tr>--%>
                                                <%--</table>--%>


                                            </div>
                                            <div id="login_info" style="color:red;display: none;">
                                                ddd
                                            </div>
                                            <div class="space"></div>

                                            <div class="text-center">
                                                <%--<label class="inline">--%>
                                                <%--<input type="checkbox" class="ace" />--%>
                                                <%--<span class="lbl">记住我</span>--%>
                                                <%--</label>--%>

                                                <button type="submit" id="loginBtn"
                                                        class="width-80  btn btn-sm btn-primary" onclick="login()">
                                                    <i class="icon-key"></i>
                                                    登录
                                                </button>
                                            </div>

                                            <div class="space-4"></div>
                                        </fieldset>
                                    </form>

                                    <div class="social-or-login center">
                                        <span class="bigger-110">声明</span>
                                    </div>

                                    <div class="social-login center">
                                     </div>
                                </div><!-- /widget-main -->

                                <%--<div class="toolbar clearfix">--%>
                                <%--<div>--%>
                                <%--<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">--%>
                                <%--<i class="icon-arrow-left"></i>--%>
                                <%--忘记密码--%>
                                <%--</a>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                            </div><!-- /widget-body -->
                        </div><!-- /login-box -->

                        <div id="forgot-box" class="forgot-box widget-box no-border">
                            <div class="widget-body">
                                <div class="widget-main">
                                    <h4 class="header red lighter bigger">
                                        <i class="icon-key"></i>
                                        获取密码
                                    </h4>

                                    <div class="space-6"></div>
                                    <p>
                                        Enter your email and to receive instructions
                                    </p>

                                    <form>
                                        <fieldset>
                                            <label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control"
                                                                   placeholder="Email"/>
															<i class="icon-envelope"></i>
														</span>
                                            </label>

                                            <div class="clearfix">
                                                <button type="button" class="width-35 pull-right btn btn-sm btn-danger">
                                                    <i class="icon-lightbulb"></i>
                                                    Send Me!
                                                </button>
                                            </div>
                                        </fieldset>
                                    </form>
                                </div><!-- /widget-main -->

                                <div class="toolbar center">
                                    <a href="#" onclick="show_box('login-box'); return false;"
                                       class="back-to-login-link">
                                        Back to login
                                        <i class="icon-arrow-right"></i>
                                    </a>
                                </div>
                            </div><!-- /widget-body -->
                        </div><!-- /forgot-box -->


                    </div><!-- /position-relative -->
                </div>
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
</div><!-- /.main-container -->

<!-- basic scripts -->

<!--[if !IE]> -->

<script src="/ssm_qyjxkhsys/assets/js/jquery-2.0.3.min.js"></script>
<script src="/ssm_qyjxkhsys/assets/js/jquery.extends.js"></script>

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
    window.jQuery || document.write("<script src='/assets/js/jquery-1.10.2.min.js'>" + "<" + "/script>");
</script>
<![endif]-->

<script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='/assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
</script>

<!-- inline scripts related to this page -->

<script type="text/javascript">

    $(function () {
        if (localStorage.getItem("login.remember")) {
            var loginer = {};
            loginer.remember = localStorage.getItem("login.remember");
            loginer.name = localStorage.getItem("login.name");
            loginer.password = localStorage.getItem("login.password");
            $("#loginForm").deserialize(loginer);
        }

    })

    function login() {
        var $btn = $("#loginBtn");

        //账号密码判断
        var loginer = $("#loginForm").serializeObject();
        if (loginer.name == "") {
            error("请输入用户名或者手机号码");
            return;
        }
        if (loginer.name.length < 4 || loginer.name.length > 18) {
            error("用户名长度在4-18位之间");
            return;
        }
        if (loginer.password == "") {
            error("请输入登录密码");
            return;
        }
        if (loginer.password.length < 5 || loginer.password.length > 18) {
            error("登录密码长度在5-18位之间");
            return;
        }
//        if(loginer.randomCode == ""){
//            error("请输入验证码");
//            return ;
//        }
//        if(loginer.randomCode.length != 4){
//            error("验证码长度为4位");
//            return ;
//        }

        hideError();
        $btn.html("登录中...").prop("disabled", true);

        //发起请求
        $.post("/ssm_qyjxkhsys/info/toLogin", loginer, function (result) {
            if (result.status == STATUS.SUCCESS) {
                if (loginer.remember) {
                    localStorage.setItem("login.remember", loginer.remember);
                    localStorage.setItem("login.name", loginer.name);
                    localStorage.setItem("login.password", loginer.password);
                } else {
                    localStorage.removeItem("login.remember");
                    localStorage.removeItem("login.name");
                    localStorage.removeItem("login.password");
                }
                $btn.html("登录成功，跳转中...")
                location.href = "/ssm_qyjxkhsys/info/index";
            } else {
                $btn.html("<i class='icon-key'></i>登录").prop("disabled", false);
                error(result.message);
                changeValidateCode();
            }
        })

    }

    var error = function (message) {
        $("#login_info").html(message);
        $("#login_info").fadeIn();
    }
    var hideError = function () {
        $("#login_info").fadeOut();
    }

    function show_box(id) {
        jQuery('.widget-box.visible').removeClass('visible');
        jQuery('#' + id).addClass('visible');
    }

    function changeValidateCode() {
        var timenow = new Date().getTime();
        $("#validateImg").attr("src", "/info/randomCode?d=" + timenow);
    }
</script>
</body>
</html>
