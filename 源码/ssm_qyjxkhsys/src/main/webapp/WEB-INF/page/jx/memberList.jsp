<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="../common/header.jsp">
    <jsp:param name="topMenuName" value="member"></jsp:param>
    <jsp:param name="menuName" value="member"></jsp:param>
</jsp:include>

<!-- 自定义内容区 -->
<div class="main-content">
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
                <a href="#">员工管理</a>
            </li>
        </ul>

    </div>

    <div class="page-content">

        <div class="row">
            <div class="col-xs-12">
                <div class="row">
                    <div class="hr hr-8 dotted hr-double"></div>
                    <form id="searchForm" class="form-horizontal form-query clearfix col-sm-12" onsubmit="return false">
                        <div class="form-group">
                            <label class="control-label col-md-4">姓名：</label>
                            <div class="col-md-7">
                                <input type="text" class="form-control" name="name" placeholder="员工姓名">
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
                        <button class="btn btn-sm btn-success" onclick="toAddMember()">
                            <i class="icon-plus"></i>添加
                        </button>
                        <button class="btn btn-sm btn-primary" onclick="toUpdateMember()">
                            <i class="icon-edit"></i>修改
                        </button>
                        <button class="btn btn-sm btn-danger" onclick="toDeleteMember()">
                            <i class="icon-remove"></i>删除
                        </button>
                    </div>
                    <div class="clearfix"></div>
                    <div class="hr hr-8 dotted hr-double"></div>
                </div>

                <div class="row">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table id="memberGrid" class="datagrid"
                                   data-options="{url : '/ssm_qyjxkhsys/info/member/findMembers'}">
                                <thead>
                                <tr>
                                    <th field="id" align="center" checkbox="true" width="60">
                                    </th>
                                    <th field="name">姓名</th>
                                    <th field="sex" format="fmtSex">性别</th>
                                    <th field="phone">手机</th>
                                    <th field="joinTime" format="fmtDate">入职时间</th>
                                    <th field="address">地址</th>
                                </tr>
                                </thead>
                            </table>
                        </div>
                    </div>
                </div>

                <div id="memberModal" class="modal fade" role="dialog">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header no-padding">
                                <div class="table-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                        <span class="white">&times;</span>
                                    </button>
                                    <span id="modalTitle">员工信息</span>
                                </div>
                            </div>

                            <div class="modal-body no-padding">
                                <form id="memberForm" class="form-horizontal clearfix col-sm-12 hr-20" onsubmit="">
                                    <input type="hidden" name="id"/>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">姓名：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="name"  maxlength="10">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">性别：</label>
                                        <div class="col-md-7">
                                            <select class="form-control" name="sex">
                                                <option value="">-请选择-</option>
                                                <option value="1">男</option>
                                                <option value="0">女</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">入职时间：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="joinTime" id="joinTime" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">手机：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="phone" maxlength="20" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">地址：</label>
                                        <div class="col-md-7">
                                            <input type="text" class="form-control" name="address" maxlength="50" >
                                        </div>
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer no-margin-top">
                                <button class="btn btn-sm btn-default " data-dismiss="modal">
                                    <i class="icon-remove"></i>
                                    取消
                                </button>
                                <button class="btn btn-sm btn-primary " onclick="saveMember()">
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
<script type="text/javascript" src="/ssm_qyjxkhsys/custom/js/member.js">
    //表格列的格式化函数

</script>

<jsp:include page="../common/footer.jsp"></jsp:include>

