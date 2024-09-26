<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="system"></jsp:param>
    <jsp:param name="menuName" value="deptItem"></jsp:param>
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
                <a href="#">部门绩效设置</a>
            </li>
        </ul>

    </div>

    <div class="page-content" id="app">

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

                            <button class="btn btn-sm btn-primary" onclick="toUpdateItem()">
                                <i class="icon-edit"></i>设置指标
                            </button>

                            <button class="btn btn-sm btn-info" onclick="toInfo()">
                                <i class="icon-edit"></i>查看详情</button>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="deptItemGrid" class="datagrid"
                                   data-options="{url : '/ssm_qyjxkhsys/info/dept/findDepts'}">
                                <thead>
                                <tr>
                                    <th field="id" align="center" checkbox="true" width="60">
                                    </th>
                                    <th field="name">部门名称</th>
                                    <th field="jx" format="jx">绩效设置</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="deptItemModal" class="modal fade" role="dialog">
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
                                <form id="deptItemForm" class="form-horizontal clearfix col-sm-12 hr-20">
                                    <input type="hidden" name="id"/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">部门名称：</label>
                                        <div class="col-md-7 form-control-static">
                                             <span class="" id="name">
                                             </span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">指标配置：</label>
                                        <div class="col-md-7">
                                            <a class="btn btn-success btn-xs" @click="add()">
                                                <i class="icon-plus"></i>
                                            </a>
                                             <span style="margin-left: 16px;color: red;">注：权重之和必须为100%</span>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-6 col-md-offset-1   form-control-static">
                                            <b>考核指标</b>
                                        </div>
                                        <div class="col-md-4 form-control-static">
                                           <b> 权重（%）</b>
                                        </div>
                                    </div>
                                    <div class="form-group" v-for="item in items">
                                        <div class="col-md-6 col-md-offset-1">
                                            <select name="itemId" v-model="item.item" style="width: 100%" required>
                                                <option value="">请选择考核指标</option>
                                                <c:forEach var="u" items="${items}">
                                                    <option value="${u.id}">${u.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="col-md-4">
                                            <input type="number" class="form-control" v-model="item.qz" name="qz"
                                                   placeholder="权重，百分比"/>
                                        </div>
                                        <div class="col-md-1">
                                            <a class="btn btn-danger btn-xs" @click="remove(item)">
                                                <i class="icon-remove"></i>
                                            </a>
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
<script>
    var vm = new Vue({
        el: "#app",
        data: {
            items: []
        },
        methods: {
            add: function () {
                this.items.push({itemId : '',qz : null});
            },
            remove: function (item) {
                this.items.$remove(item);
            }
        }
    })
</script>
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/deptItem.js">

</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

