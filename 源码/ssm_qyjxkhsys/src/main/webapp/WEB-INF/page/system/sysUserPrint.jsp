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
        width: 600px;
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
        <th colspan="4">员工个人基本信息</th>
    </tr>
    <tr>
        <td>姓名</td>
        <td>${user.name}</td>
        <td>性别</td>
        <td>
            <c:if test="${user.sex == 1}">男</c:if>
            <c:if test="${user.sex != 1}">女</c:if>
        </td>
    </tr>
    <tr>
        <td>手机</td>
        <td>${user.phone}</td>
        <td>入职时间</td>
        <td><fmt:formatDate value="${user.joinTime}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
        <td>系统账号</td>
        <td>${user.account}</td>
        <td>创建时间</td>
        <td><fmt:formatDate value="${user.gmtTime}" pattern="yyyy-MM-dd"/></td>
    </tr>
    <tr>
        <td>地址</td>
        <td colspan="3" style="text-align: left;">${user.address}</td>
    </tr>
</table>
</body>
</html>
