<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="notice"></jsp:param>
    <jsp:param name="menuName" value="notice"></jsp:param>
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
                <a href="#">员工工资</a>
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
                            <button type="submit" class="btn btn-sm btn-search" onclick="search()">
                                <i class="icon-search"></i>
                                <span class="hidden-480">查询</span>
                            </button>
                        </div>
                    </form>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                    <div class="col-sm-12">
                        <button class="btn btn-sm btn-success" onclick="toAddMoney()">
                            <i class="icon-plus"></i>添加
                        </button>
                        <button class="btn btn-sm btn-primary" onclick="toUpdateMoney()">
                            <i class="icon-edit"></i>修改
                        </button>
                        <button class="btn btn-sm btn-info" onclick="toPrint()">
                            <i class="icon-print"></i>打印工资列表
                        </button>
                    </div>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="moneyGrid" class="datagrid"
                                   data-options="{url : '/ssm_qyjxkhsys/info/money/findMonies'}">
                                <thead>
                                <tr>
                                    <th field="id" align="center" checkbox="true" width="60">
                                    </th>
                                    <th field="userName">姓名</th>
                                    <th field="month">月份</th>
                                    <th field="jbgz">基本工资</th>
                                    <th field="jj">奖金</th>
                                    <th field="ykgz">应扣工资</th>
                                    <th field="yfgz">应发工资</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="moneyModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <span id="modalTitle">工资信息</span>
                                </div>
                            </div>

                            <div class="modal-body no-padding">
                                <form id="moneyForm" class="form-horizontal clearfix col-sm-12 hr-20" onsubmit="">
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
                                            <input type="text" class="form-control" name="month" id="month3"
                                                   required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">基本工资：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="jbgz">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">奖金：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="jj">
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer no-margin-top">
                                <button class="btn btn-sm btn-default " data-dismiss="modal">
                                    <i class="icon-remove"></i>
                                    取消
                                </button>
                                <button class="btn btn-sm btn-primary " onclick="saveMoney()">
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
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/money.js">
    //表格列的格式化函数

</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

