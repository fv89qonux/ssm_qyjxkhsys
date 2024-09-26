<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="information"></jsp:param>
    <jsp:param name="menuName" value="information"></jsp:param>
</jsp:include>

<div class="main-content">
    <style>
        .circle {
            display: inline-block;
            width: 20px;
            height: 20px;
            -webkit-border-radius: 50%;
            -moz-border-radius: 50%;
            border-radius: 50%;
            border: 1px solid #cccccc;
        }

        .circle.red {
            background: red;
        }

        .circle.yellow {
            background: yellow;
        }

        .circle.green {
            background: green;
        }
        .datepicker{
            z-index: 9999 !important;
        }
    </style>
    <!-- 自定义内容区 -->
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
                <a href="#">原材料看板</a>
            </li>
            <li class="active">原材料看板</li>
        </ul>

    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">


                <div class="row">
                    <div class="hr hr-8 dotted hr-double"></div>
                    <form id="searchForm" class="form-horizontal form-query clearfix col-sm-12" onsubmit="return false">
                        <div class="form-group" style="width: 450px;">
                            <label class="control-label col-md-5">关键字：</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="name" placeholder="通过零件号、名称搜索">
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
                        <%--<c:forEach var="resource" items="${resources}">--%>
                            <%--<c:if test="${resource.type == 2 && resource.code == 'information'}">--%>
                                <%--<button class="btn btn-sm ${resource.clazz}" onclick="${resource.method}">--%>
                                    <%--<i class="${resource.icon}"></i>${resource.name}--%>
                                <%--</button>--%>
                            <%--</c:if>--%>
                        <%--</c:forEach>--%>
                    </div>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="informationGrid" class="datagrid"
                                   data-options="{url : '/ssm_qyjxkhsys/info/information/findMaterials',onLoadSuccess:onLoadSuccess}">
                                <thead>
                                <tr>
                                    <th field="number">物料代码</th>
                                    <th field="model">物料型号</th>
                                    <th field="name">物料名称</th>
                                    <th field="batch">最旧批次</th>
                                    <th field="count">及时库存数</th>
                                    <th field="status" align="center" format="fmtStatus">库存状态</th>
                                    <th field="arriveTime" format="arriveTime">计划到货时间</th>
                                    <th field="arriveCount" format="arrive">计划到货数</th>
                                    <th field="status1" align="center" width="80" format="fmtStatus1">到货后库存状态</th>
                                    <th field="id" align="center" width="60" format="fmtOpt">操作</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="informationModal" class="modal fade" role="dialog">
                    <div class="modal-dialog"  >
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <span id="modalTitle">跟进信息填写</span>
                                </div>
                            </div>

                            <div class="modal-body no-padding">
                                <form id="informationForm" class="form-horizontal clearfix col-sm-12 hr-20"
                                      onsubmit="return false">
                                    <input type="hidden" name="number" id="number"/>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="control-label col-md-4"> 零件名称：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" id="name" name="name" value="" disabled/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="control-label col-md-4">预计到货时间：</label>
                                                <div class="col-md-6">
                                                    <input type="text" class="form-control" name="arriveTime" id="arriveTime"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="control-label col-md-4">预计到货数量：</label>
                                                <div class="col-md-6">
                                                    <input type="number" class="form-control" name="arriveCount" id="arriveCount"/>
                                                </div>
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
                                <button class="btn btn-sm btn-danger " onclick="clearInformation()">
                                    <i class="icon-cancel"></i>
                                    清空
                                </button>
                                <button class="btn btn-sm btn-primary " onclick="saveInformation()">
                                    <i class="icon-ok"></i>
                                    保存
                                </button>
                            </div>
                        </div><!-- /.modal-content -->
                    </div><!-- /.modal-dialog -->
                </div><!-- PAGE CONTENT ENDS -->
            </div><!-- /.col -->
        </div><!-- /.row -->
    </div>
    <!-- /.page-content -->
</div>
<!-- js引入区域或者自定义区域 -->
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/information.js?v=2">
</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

