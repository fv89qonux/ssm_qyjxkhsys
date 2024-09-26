<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="money"></jsp:param>
    <jsp:param name="menuName" value="money"></jsp:param>
</jsp:include>

<!-- 自定义内容区 -->
<div class="main-content">
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb">
            <li>
                <i class="icon-print"></i>
                <a href="#">报表打印</a>
            </li>
        </ul>

    </div>
    <style>
        .ui-datepicker-calendar {
            display: none;
        }
    </style>
    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="hr hr-8 dotted hr-double"></div>
                    <form id="searchForm" class="form-horizontal form-query clearfix col-sm-12" onsubmit="return false">
                        <div class="form-group">
                            <label class="control-label col-md-4">员工：</label>
                            <div class="col-md-7">
                                <select name="userId" style="width: 100%">
                                    <option value="">-全部-</option>
                                    <c:forEach var="u" items="${users}">
                                        <option value="${u.id}">${u.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">月份：</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="month" id="month"
                                       />
                            </div>
                        </div>
                        <div class="form-group query-btn-box">
                            <button type="submit" class="btn btn-sm btn-search" onclick="print()">
                                <i class="icon-"></i>
                                <span class="hidden-480">打印</span>
                            </button>
                        </div>
                    </form>
                </div>


            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
</div>
<!-- js引入区域或者自定义区域 -->
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/report.js">
    //表格列的格式化函数

</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

