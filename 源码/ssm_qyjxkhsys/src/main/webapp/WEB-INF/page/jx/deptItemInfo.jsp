<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="deptItem"></jsp:param>
    <jsp:param name="menuName" value="deptItem"></jsp:param>
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
                <a href="#">部门绩效指标设置详情</a>
            </li>
        </ul>

    </div>

    <div class="page-content" id="app">

        <style>
            table {
                margin: auto;
                margin-top: 50px;
                width: 900px;
                border-collapse: collapse;
            }

            table tr th, table tr td {
                padding: 12px;
                text-align: center;
            }

            table tr th, table tr, table tr td {
                border: 1px solid black;
            }
        </style>
        <table>
            <tr>
                <th colspan="6">部门绩效指标设置</th>
            </tr>
            <tr>
                <td colspan="2">部门名称</td>
                <td colspan="4">${dept.name}</td>
            </tr>
            <tr>
                <th colspan="6">绩效考核指标信息</th>
            </tr>

            <tr>
                <th>指标名称</th>
                <th>考核项目</th>
                <th>考核目标</th>
                <th>评分标准</th>
                <th>分值</th>
                <th>权重</th>
            </tr>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.itemName}</td>
                    <td>${item.project}</td>
                    <td>${item.target}</td>
                    <td>${item.standard}</td>
                    <td>100分</td>
                    <td style="font-weight: bold;color: #red;">${item.qz}%</td>
                </tr>
            </c:forEach>
        </table>

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

