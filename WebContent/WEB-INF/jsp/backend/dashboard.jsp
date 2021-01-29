<%@page import="vote509.vo.ActivityVo"%>
<%@page import="java.util.List"%>
<%@page import="vote509.entity.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>祢豆子在线投票网</title>
<%@ include file="../commons/base.jsp" %>
<%@ include file="../commons/styles.jsp" %>
<%@ include file="../commons/styles-backend.jsp" %>
<%
	User u = (User) request.getSession().getAttribute("user");
%>
</head>
<body>
<div class="xs-pd-20-10 pd-ltr-20">
	<div class="page-header">
		<div class="row">
			<div class="col-md-6 col-sm-12">
				<div class="title">
					<h4>概览</h4>
				</div>
				<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="<%=path %>/manage/userinfo">主页</a></li>
						<li class="breadcrumb-item active" aria-current="page">概览</li>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	<div class="page-header">
		<div class="row">
			<div class="col-md-6 col-sm-12">
				<div class="title">
					<h5>欢迎您！<%=u.getUsername() %></h5>
				</div>
			</div>
			<div class="col-md-6 col-sm-12 text-right">
				<div class="dropdown">
					<a class="btn btn-primary" href="#"  data-toggle="modal" data-target="#bd-example-modal-lg" role="button">
						修改信息
					</a>
				</div>
			</div>
			<div class="modal fade bs-example-modal-lg" id="bd-example-modal-lg" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-lg modal-dialog-centered">
					<form class="modal-content" action="manage/userinfo?type=update" enctype="multipart/form-data" method="post">
						<div>
							<%
								String msg = request.getParameter("msg");
								if(msg != null) {
									out.print("<script>'alert(" + msg + ")'</script>");
								}
							%>
							<div class="modal-header">
								<h4 class="modal-title" id="myLargeModalLabel">修改用户信息</h4>
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
							</div>
							<div class="modal-body">
								<input name="id" type="hidden">
								<div class="form-group">
									<label>昵称</label>
									<input class="form-control" name="username" type="text" value="<%=u.getUsername() %>">
								</div>
								<div class="form-group">
									<label>密码</label>
									<input class="form-control" name="password" type="password" placeholder="(未更改)">
								</div>
								<div class="form-group">
									<label>邮箱</label>
									<input class="form-control" name="email" type="email" value="<%=u.getEmail() %>">
								</div>
								<div class="form-group">
									<label>头像</label>
									<input class="form-control" name="image" type="file" accept=".jpg,.png">
								</div>
							</div>
							<div class="modal-footer">
								<button type="submit" class="btn btn-primary">更新</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="row clearfix progress-box">
		<div class="col-lg-3 col-md-6 col-sm-12 mb-30">
			<div class="card-box pd-30 height-100-p">
				<div class="text-center">
					<p class="text-blue" style="font-size: 40px;"><%=request.getAttribute("countActivity") %></p>
					<h5 class="text-blue padding-top-10 h5">全部活动数</h5>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-6 col-sm-12 mb-30">
			<div class="card-box pd-30 height-100-p">
				<div class="progress-box text-center">
					<p class="text-light-green" style="font-size: 40px;"><%=request.getAttribute("activityDoing") %></p>
					<h5 class="text-light-green padding-top-10 h5">正在进行的活动数</h5>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-6 col-sm-12 mb-30">
			<div class="card-box pd-30 height-100-p">
				<div class="progress-box text-center">
					<p class="text-light-orange" style="font-size: 40px;"><%=request.getAttribute("countTickets") %></p>
					<h5 class="text-light-orange padding-top-10 h5">总票数</h5>
				</div>
			</div>
		</div>
		<div class="col-lg-3 col-md-6 col-sm-12 mb-30">
			<div class="card-box pd-30 height-100-p">
				<div class="progress-box text-center">
					<p class="text-light-purple" style="font-size: 40px;"><%=request.getAttribute("countLooks") %></p>
					<h5 class="text-light-purple padding-top-10 h5">总活动访问数</h5>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 mb-30">
			<div class="card-box pd-30 height-100-p">
				<h2 class="mb-30 h4">最近的活动</h2>
				<div class="browser-visits">
					<table class="table">
						<thead align="center">
							<tr>
								<th scope="col">活动编号</th>
								<th scope="col">活动名称</th>
								<th scope="col">总票数</th>
								<th scope="col">总选手数</th>
							</tr>
						</thead>
						<tbody align="center">
							<%
							List<ActivityVo> activities = (List<ActivityVo>) request.getAttribute("activities");
							int i = 1;
							for(ActivityVo activity : activities) {
							%>
							<tr>
								<td scope="row" ><%=i %></td>
								<td><%=activity.getName() %></td>
								<td><%=activity.getCountVotes() %></td>
								<td><%=activity.getContestantCount() %></td>
							</tr>
							<%
							i++;
							}
							%>
						</tbody>
					</table>
				</div>
				<div class="text-center">
					<small>仅展示最近的5个正在进行的活动</small>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../commons/bottom.jsp"></jsp:include>
	<jsp:include page="../commons/scripts.jsp"></jsp:include>
	<jsp:include page="../commons/scripts-backend.jsp"></jsp:include>
</div>
</body>
</html>