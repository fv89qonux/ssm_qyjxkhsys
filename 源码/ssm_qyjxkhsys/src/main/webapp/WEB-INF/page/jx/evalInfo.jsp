<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="system"></jsp:param>
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
            <li>
                <a>绩效考核详情</a>
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
                <th colspan="6">员工个人基本信息</th>
            </tr>
            <tr>
                <td>姓名</td>
                <td colspan="2">${user.name}</td>
                <td>性别</td>
                <td colspan="2">
                    <c:if test="${user.sex == 1}">男</c:if>
                    <c:if test="${user.sex != 1}">女</c:if>
                </td>
            </tr>
            <tr>
                <td>手机</td>
                <td colspan="2">${user.phone}</td>
                <td>入职时间</td>
                <td colspan="2"><fmt:formatDate value="${user.joinTime}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td>系统账号</td>
                <td colspan="2">${user.account}</td>
                <td>创建时间</td>
                <td colspan="2"><fmt:formatDate value="${user.gmtTime}" pattern="yyyy-MM-dd"/></td>
            </tr>
            <tr>
                <td>地址</td>
                <td colspan="6" style="text-align: left;">${user.address}</td>
            </tr>
            <tr>
                <th colspan="6">绩效考核评估详情</th>
            </tr>
            <tr>
                <td>绩效考核名称</td>
                <td colspan="2">${eval.name}</td>
                <td>所属月份</td>
                <td colspan="2">${eval.month}</td>
            </tr>
            <tr>
                <th colspan="6">绩效考核指标信息</th>
            </tr>

            <tr>
                <th>考核指标名称</th>
                <th>考核项目</th>
                <th>考核目标</th>
                <th>评分标准</th>
                <th>权重（%）</th>
                <th>评估分值</th>
            </tr>
            <c:forEach var="item" items="${items}">
                <tr>
                    <td>${item.item.name}</td>
                    <td>${item.item.project}</td>
                    <td>${item.item.target}</td>
                    <td>${item.item.standard}</td>
                    <td>${item.item.qz}</td>
                    <td>${item.score}</td>
                </tr>
            </c:forEach>
            <tr>
                <th colspan="5" style="text-align: right">总分</th>
                <th style="font-weight: bold;color: red;">
                    ${eval.total}
                </th>
            </tr>
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

