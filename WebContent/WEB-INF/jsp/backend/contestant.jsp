<%@page import="vote509.entity.Contestant"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@ include file="../commons/base.jsp" %>
<link rel="stylesheet" type="text/css" href="<%=path %>/vendors/datatables/css/dataTables.bootstrap4.min.css">
<link rel="stylesheet" type="text/css" href="<%=path %>/vendors/datatables/css/responsive.bootstrap4.min.css">
<%@ include file="../commons/styles.jsp" %>
<%@ include file="../commons/styles-backend.jsp" %>
<style>
.ion-chevron-left,.ion-chevron-right {
	padding: 1px;
}
</style>
</head>
<body>
<div class="xs-pd-20-10 pd-ltr-20">
	<div class="page-header">
		<div class="row">
			<div class="col-md-6 col-sm-12">
				<div class="title">
					<h4>选手管理</h4>
				</div>
				<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="<%=path %>/manage/userinfo">主页</a></li>
						<c:if test="${null != activityName}">
						<li class="breadcrumb-item" aria-current="page"><a href="<%=path %>/manage/activity">我的活动管理</a></li>
						</c:if>
						<li class="breadcrumb-item active" aria-current="page">选手管理</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 mb-30">
			<div class="card-box pd-30">
				<% 
				String activityId = request.getParameter("activityId");
				if(null == activityId || "".equals(activityId) || "null".equals(activityId)) {
				%>
				<div class="clearfix mb-20">需要操作选手，请在“我的活动管理”中选择一个活动，然后在管理你的活动的选手</div>
				<a href="<%=path %>/manage/activity" class="btn btn-primary">点此进入我的活动管理</a>
				<%} else { %>
				<div class="clearfix mb-20" style="position: relative;">
					<h5>当前活动选手管理</h5>
					<a href="<%=path %>/manage/contestantEdit?aid=<%=activityId %>" class="btn btn-primary" style="position: absolute; top: -8px; right: 0">添加选手</a>
				</div>
				<table class="data-table table stripe hover nowrap">
					<thead align="center">
						<tr>
							<th scope="col">选手编号</th>
							<th scope="col">选手姓名</th>
							<th scope="col">票数</th>
							<th scope="col" class="datatable-nosort sorting_disabled">是否弃权</th>
							<th scope="col" class="datatable-nosort sorting_disabled">操作</th>
						</tr>
					</thead>
					<tbody align="center">
						<c:forEach var="contestant" items="${contestants}">
							<tr>
								<th scope="row">${contestant.contestantid}</th>
								<td>${contestant.name}</td>
								<td>${contestant.votes}</td>
								<td>
									<c:choose>
										<c:when test="${contestant.giveup == 0}">
											<span class="badge badge-success">未弃权</span>
										</c:when>
										<c:otherwise>
											<span class="badge badge-dark">已弃权</span>
										</c:otherwise>
									</c:choose>
								</td>
								<td>
									<c:choose>
										<c:when test="${contestant.giveup == 0}">
											<a href='<%=path %>/manage/contestantEdit?id=${contestant.id}&aid=${activityId}' style='color: #69f'>修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:;' onclick="locked(1, ${contestant.id})" style='color: #69f'>弃权</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:;' onclick="del(${contestant.id})" style='color: #69f'>删除</a>
										</c:when>
										<c:otherwise>
											<a href='<%=path %>/manage/contestantEdit?id=${contestant.id}&aid=${activityId}' style='color: #69f'>修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:;' onclick="locked(0, ${contestant.id})" style='color: #69f'>取消弃权</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:;' onclick="del(${contestant.id})" style='color: #69f'>删除</a>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div class="pt-10 pb-10">
					<span>按弃权筛选</span>
					<a href="<%=path %>/manage/contestant?activityId=<%=activityId %>&activityName=${activityName}&giveup=1" class="btn btn-success">弃权</a>
					<a href="<%=path %>/manage/contestant?activityId=<%=activityId %>&activityName=${activityName}&giveup=0" class="btn btn-success">未弃权</a>
					<a href="<%=path %>/manage/contestant?activityId=<%=activityId %>&activityName=${activityName}" class="btn btn-success">重置</a>
				</div>
				<form>
					<select name="type" id="select">
						<option value="">请选择查找的条件</option>
						<option value="1">按选手号查找</option>
						<option value="2">按选手名查找</option>
						<option value="3">按选票区间查找</option>
					</select>
					<input type="hidden" name="activityId" value="<%=activityId %>">
					<input type="hidden" name="giveup" value="${giveup }">
					<input type="hidden" name="activityName" value="${activityName }">
					<input type="text" name="keyword" id="text" value="${ keyword == null ? '' : keyword}" placeholder=""/>
					<input type="submit" class="btn btn-primary" value="搜索">
					<script type="text/javascript">
						var select = document.getElementById("select");
						select.onchange = function() {
							var text = document.getElementById("text");
							if(select.value == 1) {
								text.placeholder = "请输入选手号"
							}
							else if(select.value == 2) {
								text.placeholder = "请输入选手名"
							}
							else if(select.value == 3) {
								text.placeholder = "输入样例“20-30”"
							}
						}
					</script>
				</form>
				<%}%>
			</div>
		</div>
		<c:if test="${msg != null }">
			<script type="text/javascript">alert("${msg}")</script>
		</c:if>
	</div>
	<jsp:include page="../commons/bottom.jsp"></jsp:include>
	<jsp:include page="../commons/scripts.jsp"></jsp:include>
	<jsp:include page="../commons/scripts-backend.jsp"></jsp:include>
	<script src="<%=path %>/vendors/datatables/js/jquery.dataTables.min.js"></script>
	<script src="<%=path %>/vendors/datatables/js/dataTables.bootstrap4.min.js"></script>
	<script src="<%=path %>/vendors/datatables/js/dataTables.responsive.min.js"></script>
	<script src="<%=path %>/vendors/datatables/js/responsive.bootstrap4.min.js"></script>
	<script src="<%=path %>/vendors/datatables/js/dataTables.buttons.min.js"></script>
	<script type="text/javascript">
		$('.data-table').DataTable({
			scrollCollapse: true,
			autoWidth: false,
			responsive: true,
			searching:false,
			columnDefs: [{
				targets: "datatable-nosort",
				orderable: false,
			}],
			"lengthMenu": [[10, 20, 30, -1], [10, 20, 30, "全部"]],
			"language": {
				"info": "第_PAGE_页（共_PAGES_页）",
				paginate: {
					next: '<i class="ion-chevron-right"></i>',
					previous: '<i class="ion-chevron-left"></i>'  
				},
				"sLengthMenu": "显示 _MENU_ 条记录",
				"emptyTable": "暂无数据",
				"infoEmpty": "没有记录可以显示"
			},
		});
		
		// 弃权操作
		function locked(giveup, id) {
			if(confirm("确定这么操作？")) {
				$.ajax({
					type: "POST",
					url: "<%=path %>/manage/contestantEdit?type=giveup",
					dataType: "json",
					data: {
						isGiveup: giveup,
						cid: id,
					},
					success: function(e) {
						if(e.status == 'success') {
							alert(e.msg);
							location.reload();
						} else if (e.status == 'error') {
							alert(e.msg);
						} else if (e.status == 'login') {
							alert(e.msg);
							top.location.href = '/inlinevote/manage/login';
						}
					}
				});
			}
		}
		
		// 删除操作
		function del(id) {
			if(confirm("删除之后将不复存在，确定要删除该选手？")) {
				$.ajax({
					type: "POST",
					url: "<%=path %>/manage/contestantEdit?type=del",
					dataType: "json",
					data: {
						cid: id,
					},
					success: function(e) {
						if(e.status == 'success') {
							alert(e.msg);
							location.reload();
						} else if (e.status == 'error') {
							alert(e.msg);
						} else if (e.status == 'login') {
							alert(e.msg);
							top.location.href = '/inlinevote/manage/login';
						}
					}
				});
			}
		}
	</script>
</div>
</body>
</html>