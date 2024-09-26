<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="atten"></jsp:param>
    <jsp:param name="menuName" value="atten"></jsp:param>
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
                <i class="icon-screenshot"></i>
                <a href="#">考勤管理</a>
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
                                <input type="text" class="form-control" name="month" id="month2"
                                />
                            </div>
                        </div>
                        <div class="form-group query-btn-box">
                            <button type="submit" class="btn btn-sm btn-search" onclick="search()">
                                <i class="icon-search"></i>
                                <span class="hidden-480">查询</span>
                            </button>
                        </div>
                    </form>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                    <div class="col-sm-12">
                        <button class="btn btn-sm btn-success" onclick="toAddAtten()">
                            <i class="icon-plus"></i>添加考勤记录
                        </button>
                        <button class="btn btn-sm btn-danger" onclick="toDeleteAtten()">
                            <i class="icon-remove"></i>删除考勤记录
                        </button>
                    </div>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="attenGrid" class="datagrid"
                                   data-options="{url : '/ssm_qyjxkhsys/info/atten/findAttens'}">
                                <thead>
                                <tr>
                                    <th field="id" align="center" checkbox="true" width="60">
                                    </th>
                                    <th field="userName">姓名</th>
                                    <th field="month">月份</th>
                                    <th field="type" format="fmtType">考勤类型</th>
                                    <th field="time" format="fmtDate">发生时间</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="attenModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <span id="modalTitle">考勤记录</span>
                                </div>
                            </div>

                            <div class="modal-body no-padding">
                                <form id="attenForm" class="form-horizontal clearfix col-sm-12 hr-20" onsubmit="">
                                    <input type="hidden" name="id"/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">员工：</label>
                                        <div class="col-md-7">
                                            <select name="userId" style="width: 100%">
                                                <c:forEach var="u" items="${users}">
                                                    <option value="${u.id}">${u.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">所属月份：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="month" id="month"
                                                   required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">考勤类型：</label>
                                        <div class="col-md-7">
                                            <select name="type" style="width: 100%">
                                                <option value="CHIDAO">迟到</option>
                                                <option value="ZAOTUI">早退</option>
                                                <option value="LINSHIQIANTUI">临时签退</option>
                                                <option value="YEWUPILOU">业务纰漏</option>
                                                <option value="SHIJIA">事假</option>
                                                <option value="BINGJIA">病假</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">发生时间：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="time" id="time"
                                                   required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">备注：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="remark"
                                                   placeholder="选填，如 迟到8分钟"
                                            />
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer no-margin-top">
                                <button class="btn btn-sm btn-default " data-dismiss="modal">
                                    <i class="icon-remove"></i>
                                    取消
                                </button>
                                <button class="btn btn-sm btn-primary " onclick="saveAtten()">
                                    <i class="icon-ok"></i>
                                    保存
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div><!-- /.page-content -->
</div>
<!-- js引入区域或者自定义区域 -->
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/atten.js">
    //表格列的格式化函数

</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

