<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2021/7/1
  Time: 13:21
  菜单
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="nav nav-list">
    <li class="">
        <a href="/ssm_qyjxkhsys/info/index" class="dropdown-toggle">
            <i class="icon-home home-icon"></i>
            <span class="menu-text"> 首页 </span>
        </a>
    </li>
    <li class="<c:if test="${param.menuName == 'notice'}">active</c:if>">
        <a href="/ssm_qyjxkhsys/info/notice/toList" class="dropdown-toggle">
            <i class="icon-cog"></i>
            <span class="menu-text"> 公告管理 </span>
        </a>
    </li>
    <li class="<c:if test="${param.menuName == 'opinion'}">active</c:if>">
        <a href="/ssm_qyjxkhsys/info/opinion/toList" class="dropdown-toggle">
            <i class="icon-pencil"></i>
            <span class="menu-text"> 意见反馈 </span>
        </a>
    </li>
    <c:if test="${sessionScope.LOGINER.admin != 1}">
        <li class="<c:if test="${param.menuName == 'eval'}">active</c:if>">
            <a href="/ssm_qyjxkhsys/info/eval/toList2" class="dropdown-toggle">
                <i class="icon-print"></i>
                <span class="menu-text"> 绩效查看 </span>
            </a>
        </li>
        <li class="<c:if test="${param.menuName == 'week'}">active</c:if>">
            <a href="/ssm_qyjxkhsys/info/week/toList" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 周报信息 </span>
            </a>
        </li>
    </c:if>
    <c:if test="${sessionScope.LOGINER.admin == 1}">
        <li class="<c:if test="${param.menuName == 'dept'}">active</c:if>">
            <a href="/ssm_qyjxkhsys/info/dept/toList" class="dropdown-toggle">
                <i class="icon-desktop"></i>
                <span class="menu-text"> 部门管理 </span>
            </a>
        </li>
        <li class="<c:if test="${param.menuName == 'member'}">active</c:if>">
            <a href="/ssm_qyjxkhsys/info/sysUser/toList" class="dropdown-toggle">
                <i class="icon-user"></i>
                <span class="menu-text"> 员工管理 </span>
            </a>
        </li>
        <li class="<c:if test="${param.menuName == 'item'}">active</c:if>">
            <a href="/ssm_qyjxkhsys/info/item/toList" class="dropdown-toggle">
                <i class="icon-screenshot"></i>
                <span class="menu-text"> 绩效指标 </span>
            </a>
        </li>
        <li class="<c:if test="${param.menuName == 'deptItem'}">active</c:if>">
            <a href="/ssm_qyjxkhsys/info/deptItem/toList" class="dropdown-toggle">
                <i class="icon-edit"></i>
                <span class="menu-text"> 部门绩效设置 </span>
            </a>
        </li>
        <li class="<c:if test="${param.menuName == 'eval'}">active</c:if>">
            <a href="/ssm_qyjxkhsys/info/eval/toList" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 绩效评估 </span>
            </a>
        </li>
        <li class="<c:if test="${param.menuName == 'week'}">active</c:if>">
            <a href="/ssm_qyjxkhsys/info/week/toList" class="dropdown-toggle">
                <i class="icon-tag"></i>
                <span class="menu-text"> 周报信息 </span>
            </a>
        </li>
    </c:if>
</ul>
<!-- /.nav-list -->