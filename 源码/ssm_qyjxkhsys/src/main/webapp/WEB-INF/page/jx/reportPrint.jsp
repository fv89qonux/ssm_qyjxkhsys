<%--
  Created by IntelliJ IDEA.
  User: ZM
  Date: 2017/4/12
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>员工信息打印</title>
</head>
<body>
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
        <th colspan="7">员工个人基本信息</th>
    </tr>
    <tr>
        <td>姓名</td>
        <td colspan="3">${user.name}</td>
        <td>性别</td>
        <td  colspan="2">
            <c:if test="${user.sex == 1}">男</c:if>
            <c:if test="${user.sex != 1}">女</c:if>
        </td>
    </tr>
    <tr>
        <td>手机</td>
        <td  colspan="3">${user.phone}</td>
        <td>入职时间</td>
        <td  colspan="2"><fmt:formatDate value="${user.joinTime}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
        <td>系统账号</td>
        <td  colspan="3">${user.account}</td>
        <td>创建时间</td>
        <td  colspan="2"><fmt:formatDate value="${user.gmtTime}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
        <td>地址</td>
        <td colspan="7" style="text-align: left;">${user.address}</td>
    </tr>
    <tr>
        <th colspan="7">考勤明细</th>
    </tr>
    <tr>
        <th  colspan="1" field="month">月份</th>
        <th>迟到</th>
        <th field="jj">早退</th>
        <th field="jbgz">临时签退</th>
        <th field="jbgz">业务纰漏</th>
        <th field="yfgz">病假</th>
        <th field="yfgz">事假</th>
    </tr>
<c:forEach var="a" items="${attens}">
    <tr>
        <td  colspan="1" field="month">${a.key}</td>
        <td field="jbgz">
            <c:forEach var="b" items="${a.value}">
                <c:if test="${b.type == 'CHIDAO'}">
                    ${b.count}次 <br/>
                    <span style="color: red;">-${b.price}</span>元
                </c:if>
            </c:forEach>
        </td>
        <td field="jj"> <c:forEach var="b" items="${a.value}">
            <c:if test="${b.type == 'ZAOTUI'}">
                ${b.count}次<br/>
                <span style="color: red;">-${b.price}</span>元
            </c:if>
        </c:forEach></td>
        <td field="ykgz"> <c:forEach var="b" items="${a.value}">
            <c:if test="${b.type == 'LINSHIQIANTUI'}">
                ${b.count}次<br/>
                <span style="color: red;">-${b.price}</span>元
            </c:if>
        </c:forEach></td>
        <td field="ykgz"> <c:forEach var="b" items="${a.value}">
            <c:if test="${b.type == 'YEWUPILOU'}">
                ${b.count}次<br/>
                <span style="color: red;">-${b.price}</span>元
            </c:if>
        </c:forEach></td>
        <td field="yfgz"> <c:forEach var="b" items="${a.value}">
            <c:if test="${b.type == 'BINGJIA'}">
                ${b.count}天<br/>
                <span style="color: red;">-${b.price}</span>元
            </c:if>
        </c:forEach></td>
        <td field="yfgz"> <c:forEach var="b" items="${a.value}">
            <c:if test="${b.type == 'SHIJIA'}">
                ${b.count}天<br/>
                <span style="color: red;">-${b.price}</span>元
            </c:if>
        </c:forEach></td>
    </tr>
</c:forEach>
    <tr>
        <th colspan="7">员工工资明细</th>
    </tr>

    <tr>
        <th  field="month">月份</th>
        <th  colspan="2" field="jbgz">基本工资</th>
        <th field="jj">奖金</th>
        <th field="ykgz">应扣工资</th>
        <th colspan="2" field="yfgz">应发工资</th>
    </tr>
    <c:forEach var="a" items="${list}">
        <tr>
            <td >${a.month}</td>
            <td  colspan="2">${a.jbgz}</td>
            <td>${a.jj}</td>
            <td>${a.ykgz}</td>
            <td colspan="2">${a.yfgz}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
