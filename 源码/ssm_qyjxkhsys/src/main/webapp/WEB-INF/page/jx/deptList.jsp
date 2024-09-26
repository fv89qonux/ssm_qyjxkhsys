<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="system"></jsp:param>
    <jsp:param name="menuName" value="dept"></jsp:param>
</jsp:include>

<!-- 自定义内容区 -->
<div class="main-content">
    <style>
        .root_open {
            background-position: -126px -64px !important;
        }

        .root_close {
            background-position: -106px -64px !important;
        }
    </style>
    <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try {
                ace.settings.check('breadcrumbs', 'fixed')
            } catch (e) {
            }
        </script>

        <ul class="breadcrumb">
            <li>
                <i class="icon-desktop"></i>
                <a href="#">部门管理</a>
            </li>
        </ul>

    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="hr hr-8 dotted hr-double"></div>
                    <form id="searchForm" class="form-horizontal form-query clearfix col-sm-12" onsubmit="return false">
                        <div class="form-group">
                            <label class="control-label col-md-4">部门名称：</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="name">
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
                    <c:if test="${sessionScope.LOGINER.admin == 1}">
                        <div class="col-sm-12">
                            <button class="btn btn-sm btn-success" onclick="toAddDept()">
                                <i class="icon-plus"></i>添加
                            </button>

                            <button class="btn btn-sm btn-primary" onclick="toUpdateDept()">
                                <i class="icon-edit"></i>修改
                            </button>


                            <button class="btn btn-sm btn-danger" onclick="toDeleteDept()">
                                <i class="icon-remove"></i>删除
                            </button>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="deptGrid" class="datagrid"
                                   data-options="{url : '/ssm_qyjxkhsys/info/dept/findDepts'}">
                                <thead>
                                <tr>
                                    <th field="id" align="center" checkbox="true" width="60">
                                    </th>
                                    <th field="name">部门名称</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="deptModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <span>部门信息</span>
                                </div>
                            </div>

                            <div class="modal-body no-padding">
                                <form id="deptForm" class="form-horizontal clearfix col-sm-12 hr-20">
                                    <input type="hidden" name="id"/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">部门名称：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="name"
                                                   placeholder="部门名称不能超出50个字">
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer no-margin-top">
                                <button class="btn btn-sm btn-default " data-dismiss="modal">
                                    <i class="icon-remove"></i>
                                    取消
                                </button>
                                <button class="btn btn-sm btn-primary " onclick="saveDept()">
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

<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/dept.js">
    //表格列的格式化函数

</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

