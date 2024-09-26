<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="config"></jsp:param>
    <jsp:param name="menuName" value="config"></jsp:param>
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
                <i class="icon-cog"></i>
                <a href="#">系统设置</a>
            </li>
        </ul>

    </div>

    <div class="page-content">

        <div class="row">
            <div style="width: 700px;">
                <form id="sysConfigForm" class="form-horizontal clearfix col-sm-12 hr-20" onsubmit="return false">
                    <div class="form-group">
                        <div class="col-md-7 col-lg-offset-4">
                            <span class="form-control-static text-danger">注：该配置包含各个考勤类型的扣款工资和计薪天数等。</span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">迟到：</label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" name="CHIDAO" value="${CHIDAO.value}"
                                   placeholder="迟到一次扣罚的工资数">
                        </div>
                        <div class="col-md-1">
                            （元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">早退：</label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" name="ZAOTUI" value="${ZAOTUI.value}"
                                   placeholder="早退一次扣罚的工资数">
                        </div>
                        <div class="col-md-1">
                            （元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">临时签退：</label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" name="LINSHIQIANTUI"
                                   value="${LINSHIQIANTUI.value}" placeholder="临时签退一次扣罚的工资数">
                        </div>
                        <div class="col-md-1">
                            （元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">业务纰漏：</label>
                        <div class="col-md-7">
                            <input type="number" class="form-control" name="YEWUPILOU"
                                   value="${YEWUPILOU.value}" placeholder="业务纰漏一次扣罚的工资数">
                        </div>
                        <div class="col-md-1">
                            （元）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">事假：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="SHIJIA" value="${SHIJIA.value}"
                                   placeholder="事假扣工资多少天，一般为1天">
                        </div>
                        <div class="col-md-1">
                            （天）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">病假：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="BINGJIA" value="${BINGJIA.value}"
                                   placeholder="病扣工资多少天，一般为0.5天">
                        </div>
                        <div class="col-md-1">
                            （天）
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-4">计薪天数：</label>
                        <div class="col-md-7">
                            <input type="text" class="form-control" name="YIGEYUE" value="${YIGEYUE.value}"
                                   placeholder="一个月的计薪天数，一般为22天">
                        </div>
                        <div class="col-md-1">
                            （天）
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-4">
                            <button class="btn btn-sm btn-primary" onclick="saveSysConfig()">
                                <i class="icon-save"></i>保存配置
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/sysConfig.js"></script>

<jsp:include page="../common/footer.jsp"></jsp:include>

