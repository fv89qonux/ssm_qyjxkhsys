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
                <a href="#">绩效查看</a>
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
                    <div class="col-sm-12">
                        <button class="btn btn-sm btn-info" onclick="toInfo()">
                            <i class="icon-info"></i>查看考核详情
                        </button>
                    </div>
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
                                    <th field="gmtTime" format="fmtTime">评估时间</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>
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
            add: function () {
                this.items.push({itemId: '', score: null});
//                this.$nextTick(function () {
//                    $("select").select2({
//                        language: "zh-CN"
//                    });
//                })
            },
            remove: function (item) {
                this.items.$remove(item);
            }
        }
    })
</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

