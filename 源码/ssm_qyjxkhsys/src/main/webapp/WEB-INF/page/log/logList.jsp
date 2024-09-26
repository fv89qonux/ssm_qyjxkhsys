<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/hotelHeader.jsp">
    <jsp:param name="topMenuName" value="operationLog"></jsp:param>
    <jsp:param name="menuName" value="logList"></jsp:param>
</jsp:include>

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
                <i class="icon-home home-icon"></i>
                <a href="#">操作日志管理</a>
            </li>
            <li class="active">日志查询</li>
        </ul>

    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="hr hr-8 dotted hr-double"></div>
                    <form id="searchForm" class="form-horizontal form-query clearfix col-sm-12" onsubmit="return false">
                        <div class="form-group">
                            <label class="control-label col-md-4">关键字：</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="key" placeholder="模块、方法、描述">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">状态：</label>
                            <div class="col-md-7">
                                <select name="exception" class="form-control">
                                    <option value="">全部</option>
                                    <option value="0">正常</option>
                                    <option value="1">异常</option>
                                </select>
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
                        <c:forEach var="resource" items="${resources}">
                            <c:if test="${resource.type == 2 && resource.code == 'logList'}">
                                <button class="btn btn-sm ${resource.clazz}" onclick="${resource.method}">
                                    <i class="${resource.icon}"></i>${resource.name}
                                </button>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="clearfix"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="logGrid" class="datagrid" data-options="{url : '/ssm_qyjxkhsys/info/log/findLogs'}">
                                <thead>
                                <tr>
                                    <th field="id" number="true" align="center" width="50"></th>
                                    <th field="userName" width="80">操作人</th>
                                    <th field="module" width="160">模块</th>
                                    <th field="method" width="160">方法</th>
                                    <th field="exception" format="fmtExp" width="50">状态</th>
                                    <th field="description" width="250">描述</th>
                                    <th field="optTime" format="fmtTime" width="160">操作时间</th>
                                    <th field="userIp"  width="120">用户IP地址</th>
                                    <th field="duration" format="fmtDuration" width="70">执行时间</th>

                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="userModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <span id="modalTitle">证件人</span>
                                </div>
                            </div>

                            <div class="modal-body no-padding">
                                <form id="userForm" class="form-horizontal clearfix col-sm-12 hr-20" onsubmit="return false">
                                    <input type="hidden" name="id"/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">姓名：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="name">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">性别：</label>
                                        <div class="col-md-7">
                                            <select class="form-control" name="sex">
                                                <option value="">-请选择-</option>
                                                <option value="1">男</option>
                                                <option value="0">女</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group" style="display: none;" id="modalError">
                                        <div class="col-md-8 col-md-offset-2">
                                            <div class="alert alert-danger alert-dismissible fade in no-margin"
                                                 role="alert">
                                                <button type="button" class="close" data-dismiss="alert"
                                                        aria-label="Close"><span aria-hidden="true">×</span></button>
                                                <span class="message">
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer no-margin-top">
                                <button class="btn btn-sm btn-default " data-dismiss="modal">
                                    <i class="icon-remove"></i>
                                    取消
                                </button>
                                <button class="btn btn-sm btn-primary " onclick="saveUser()">
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
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/log.js"></script>

<jsp:include page="../common/footer.jsp"></jsp:include>

