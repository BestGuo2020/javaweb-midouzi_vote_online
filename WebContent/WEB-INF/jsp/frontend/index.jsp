<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/WEB-INF/jsp/commons/base.jsp"%>
<%@ include file="/WEB-INF/jsp/commons/styles.jsp"%>
<style type="text/css">
.custom-bg {
	background-color: rgb(243, 147, 83)
}

.color-white:hover {
	color: #fff;
}

@font-face {
	font-family: stcaiyun;
	src: url(vendors/fonts/STCAIYUN.TTF)
}

.compnay-set {
	font-family: stcaiyun;
}

.carousel-item {
	height: 400px;
}

#myCarousel {
	margin-top: -17px;
}
</style>

<title>祢豆子在线投票网</title>
</head>
<body>
	<div
		class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 custom-bg border-bottom shadow-sm">
		<h2 class="my-0 mr-md-auto font-weight-normal color-white compnay-set">祢豆子在线投票网</h2>
		<a class="color-white" target="_blank" href="manage/login">投票后台登录</a>
	</div>
	<div id="myCarousel" class="carousel slide" data-ride="carousel">
		<ol class="carousel-indicators">
			<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			<li data-target="#myCarousel" data-slide-to="1"></li>
			<li data-target="#myCarousel" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="vendors/images/1.jpg" style="opacity: 0.4; width: 100%;"/>
			</div>
			<div class="carousel-item">
				<img src="vendors/images/2.jpg" style="opacity: 0.4"/>
				<div style="position: absolute; width: 100%;  bottom: 20px; text-align: center;">
					<h3 class="pb-10">快速</h3>
					<p>只需几步就能创建投票活动</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="vendors/images/3.jpg" style="opacity: 0.4"/>
				<div style=" width: 100%; position: absolute; bottom: 20px; text-align: center;">
					<h3 class="pb-10">简单</h3>
					<p>小学生也能看懂的操作</p>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#myCarousel" role="button"
			data-slide="prev"> <span class="carousel-control-prev-icon"
			aria-hidden="true"></span> <span class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#myCarousel" role="button"
			data-slide="next"> <span class="carousel-control-next-icon"
			aria-hidden="true"></span> <span class="sr-only">Next</span>
		</a>
	</div>
	<div class="container mb-8">
		<div class="col-lg-12 mb-30" style="text-align: center;">
			<h3 class="pt-30 pb-30">投票，就这么简单</h3>
		</div>
		<div class="col-lg-12 mb-30" style="margin-top: 30px">
			<div class="pd-20 card-box">
				<h5 class="h4 mb-20 text-center">全部活动</h5>
				<div class="row pd-20 text-center">
					<div class="card-columns">
						<c:forEach items="${activities}" var="activity">
							<div class="card card-box">
								<img class="card-img-top" src="<%=path %>${activity.image}" alt="Card image cap">
								<div class="card-body pd-10">
									<a href="vote?uId=${activity.userid}&aId=${activity.id}" class="card-title weight-500 h5">${activity.name}</a>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="/WEB-INF/jsp/commons/scripts.jsp"></jsp:include>
</body>
</html>