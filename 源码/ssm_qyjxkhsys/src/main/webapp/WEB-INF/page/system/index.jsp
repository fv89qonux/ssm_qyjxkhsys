<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="index"></jsp:param>
    <jsp:param name="menuName" value="index"></jsp:param>
</jsp:include>


<div class="main-content">


    <script src="/ssm_qyjxkhsys/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery.slimscroll.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/jquery.sparkline.min.js"></script>
    <script src="/ssm_qyjxkhsys/assets/js/Chart.min.js"></script>
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb">
            <li>
                <i class="icon-home home-icon"></i>
                <a href="#">首页</a>
            </li>
            <li class="active">控制台</li>
        </ul><!-- .breadcrumb -->
    </div>

    <div class="page-content">
        <div class="row">
            <div class="col-md-12">
                <div class="alert alert-block alert-success">
                    <i class="icon-ok green"></i>
                    欢迎使用
                    <strong class="green">
                        绩效管理系统。
                        <small>(V 1.0)</small>
                    </strong>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/sys.index.js">
</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

