<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../commons/base.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="../commons/styles.jsp" %>
<title>活动主题 - 祢豆子投票票</title>
<link rel="stylesheet" href="vendors/styles/pagination.css">
<style>
	.list-group-item {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
	}
	.bottom {
		display: flex;
		justify-content: space-between;
		padding: 20px 40px;
	}
</style>
</head>
<body>
	<div class="container">
		<div class="pd-ltr-20">
			<div class="min-height-200px">
				<!-- 参赛选手 -->
				<div class="card-box text-center">
					<h5 class="pd-20">
						${contestant.contestantid}号选手：${contestant.name}
					</h5>
					<hr style="margin: 0;">
						<a target="_blank" href="vote/detail?status=download&cid=${contestant.id}">
							<img style="width: 553px; height: 320px; margin-top: 10px;" class="pd-10" src="<%=path %>/${contestant.image}"/>
						</a>
					<div class="pd-20 text-left">
						<div>选手简介：</div>
						${contestant.introduce}
					</div>
					<hr style="margin: 0;">
					<div class="bottom">
						<button class="btn btn-outline-info">获得票数：${vote}</button>
						<a href="<%=path %>/vote?aId=${aid}&uId=${uid}" class="btn btn-primary">继续投票</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../commons/scripts.jsp" %>
</body>
</html>