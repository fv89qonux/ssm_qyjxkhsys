<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="eval"></jsp:param>
    <jsp:param name="menuName" value="eval"></jsp:param>
</jsp:include>

<!-- 自定义内容区 -->
<div class="main-content">

    <link rel="stylesheet" href="/ssm_qyjxkhsys/assets/css/zTree/metro.css"/>
    <script type="application/javascript" src="/ssm_qyjxkhsys/assets/js/ztree/jquery.ztree.all.min.js"></script>
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
                <i class="icon-screenshot"></i>
                <a href="#">绩效评估</a>
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
                            <label class="control-label col-md-4">评估名称：</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="name">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-4">被考核人：</label>
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
                            <label class="control-label col-md-4">所属月份：</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="month" id="month2">
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
                            <button class="btn btn-sm btn-success" onclick="toAddEval()">
                                <i class="icon-plus"></i>添加绩效评估
                            </button>
                            <button class="btn btn-sm btn-danger" onclick="toDeleteEval()">
                                <i class="icon-remove"></i>删除此次评估
                            </button>
                            <button class="btn btn-sm btn-info" onclick="toInfo()">
                                <i class="icon-info"></i>查看考核详情
                            </button>
                        </div>
                    </c:if>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="evalGrid" class="datagrid"
                                   data-options="{url : '/ssm_qyjxkhsys/info/eval/findEvals'}">
                                <thead>
                                <tr>
                                    <th field="id" align="center" checkbox="true" width="60">
                                    </th>
                                    <th field="name">评估名称</th>
                                    <th field="userName">被考核人</th>
                                    <th field="month">所属月份</th>
                                    <th field="total">总分</th>
                                    <th field="gmtTime" format="fmtTime">评估时间</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="evalModal" class="modal fade" role="dialog">
                    <div class="modal-dialog" style="width: 900px;">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <span>评估信息</span>
                                </div>
                            </div>

                            <div class="modal-body no-padding">
                                <form id="evalForm" class="form-horizontal clearfix col-sm-12 hr-20">
                                    <input type="hidden" name="id"/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">评估名称：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="name" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">被考核人：</label>
                                        <div class="col-md-7">
                                            <select name="userId" id="userId" style="width: 100%" required>
                                                <option value="">-请选择被考核人-</option>
                                                <c:forEach var="u" items="${users}">
                                                    <option value="${u.id}">${u.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">所属月份：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="month" id="month" required/>
                                        </div>
                                    </div>
                                    <div class="hr hr-8 dotted "></div>
                                    <div class="form-group">
                                        <div class="col-md-5 col-md-offset-1 form-control-static">
                                            考核指标
                                        </div>
                                        <div class="col-md-3 form-control-static">
                                            权重（%）
                                        </div>
                                        <div class="col-md-3 form-control-static">
                                            分值（0-100）
                                        </div>
                                    </div>
                                    <div class="form-group" v-for="item in items">
                                        <div class="col-md-5  col-md-offset-1  form-control-static">
                                            {{item.itemName}}
                                        </div>
                                        <div class="col-md-3  form-control-static">
                                            {{item.qz}}%
                                        </div>
                                        <div class="col-md-2">
                                            <input type="number" class="form-control" v-model="item.score" name="score"
                                                   placeholder=""/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <div class="col-md-3 col-md-offset-6 form-control-static text-right">
                                            总分：
                                        </div>
                                        <div class="col-md-3 form-control-static" style="font-weight: bold;color: red;">
                                                {{total()}}
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer no-margin-top">
                                <button class="btn btn-sm btn-default " data-dismiss="modal">
                                    <i class="icon-remove"></i>
                                    取消
                                </button>
                                <button class="btn btn-sm btn-primary " onclick="saveEval()">
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
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/eval.js">

</script>
<script>
    var vm = new Vue({
        el: "#app",
        data: {
            items: []
        },
        methods: {
            total : function () {
                var score = 0;
                _.each(this.items, function (item) {
                    if(item.score)
                        score += parseFloat(item.score * item.qz /100);
                })
                return score;
            }
        }
    })
</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

