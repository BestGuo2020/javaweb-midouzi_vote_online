<%@page import="vote509.entity.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="../commons/base.jsp" %>
<%@ include file="../commons/styles.jsp" %>
<%@ include file="../commons/styles-backend.jsp" %>
<%
	User u = (User) request.getSession().getAttribute("user");
%>
<title>祢豆子在线投票网-站点后台</title>
</head>
<body>

<div class="header">
	<div class="header-left">
		<div class="menu-icon dw dw-menu"></div>
	</div>
	<div class="header-right">
		<div class="user-info-dropdown">
			<div class="dropdown">
				<a class="dropdown-toggle" href="#" role="button" data-toggle="dropdown">
					<span class="user-icon">
						<img src="<%=path %>/<%=u.getHeader() %>" alt="">
					</span>
					<span class="user-name"><%=u.getUsername() %></span>
				</a>
				<div class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">
					<a class="dropdown-item" href="javascript:;"><i class="dw dw-user1"></i> <%=u.getEmail() %></a>
					<a class="dropdown-item" href="manage/logout"><i class="dw dw-logout"></i> 退出登录</a>
					<a class="dropdown-item" href="javascript:del(<%=u.getId()%>);"><i class="dw dw-user-13"></i> 注销当前用户</a>
				</div>
			</div>
		</div>
		<div class="github-link">
			<a href="https://github.com/dropways/deskapp" target="_blank"><img src="vendors/images/github.svg" alt=""></a>
		</div>
	</div>
</div>

<div class="left-side-bar">
	<div class="brand-logo">
		<a href="manage/index">
			祢豆子投票后台
		</a>
		<div class="close-sidebar" data-toggle="left-sidebar-close">
			<i class="ion-close-round"></i>
		</div>
	</div>
	<div class="menu-block customscroll">
		<div class="sidebar-menu">
			<ul id="accordion-menu">
				<li class="dropdown">
					<a href="<%=path %>/manage/userinfo" target="module" class="dropdown-toggle no-arrow">
						<span class="micon dw dw-house-1"></span><span class="mtext">主页</span>
					</a>
				</li>
				<li class="dropdown">
					<a href="javascript:;" class="dropdown-toggle">
						<span class="micon dw dw-wall-clock"></span><span class="mtext">我的活动管理</span>
					</a>
					<ul class="submenu">
						<li><a href="<%=path %>/manage/activity?status=all" target="module">全部</a></li>
						<li><a href="<%=path %>/manage/activity?status=nostart" target="module">未开始</a></li>
						<li><a href="<%=path %>/manage/activity?status=start" target="module">进行中</a></li>
						<li><a href="<%=path %>/manage/activity?status=end" target="module">已结束</a></li>
					</ul>
				</li>
				<li class="dropdown">
					<a href="<%=path %>/manage/contestant" target="module" class="dropdown-toggle no-arrow">
						<span class="micon dw dw-user-11"></span><span class="mtext">选手管理</span>
					</a>
				</li>
			</ul>
		</div>
	</div>
</div>
<div class="mobile-menu-overlay"></div>

<div class="main-container">
	<iframe src="<%=path %>/manage/userinfo" name="module" width="100%" height="100%"></iframe>
</div>
<jsp:include page="../commons/scripts.jsp"></jsp:include>
<jsp:include page="../commons/scripts-backend.jsp"></jsp:include>
<script>
	function del(id) {
		if(confirm("用户注销以后将会清空你的任何痕迹，确定要注销用户吗？")) {
			location.href="<%=path %>/manage/index?status=del&id=" + id;
			return true;
		} else {
			return false;
		}
	}
</script>
</body>
</html>