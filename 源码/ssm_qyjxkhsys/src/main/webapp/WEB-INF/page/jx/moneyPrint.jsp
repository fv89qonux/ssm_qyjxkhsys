<%--
  Created by IntelliJ IDEA.
  User: ZM
  Date: 2017/4/12
  Time: 22:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>员工工资打印</title>
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
        <th colspan="6">员工工资表</th>
    </tr>
    <tr>
        <th field="userName">姓名</th>
        <th field="month">月份</th>
        <th field="jbgz">基本工资</th>
        <th field="jj">奖金</th>
        <th field="ykgz">应扣工资</th>
        <th field="yfgz">应发工资</th>
    </tr>
    <c:forEach var="a" items="${list}">
        <tr>
            <td>${a.userName}</td>
            <td>${a.month}</td>
            <td>${a.jbgz}</td>
            <td>${a.jj}</td>
            <td>${a.ykgz}</td>
            <td>${a.yfgz}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
