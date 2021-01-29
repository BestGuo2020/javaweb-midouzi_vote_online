<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../commons/base.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@ include file="../commons/styles.jsp" %>
<title>${activity.name} - 祢豆子在线投票网</title>
<link rel="stylesheet" href="vendors/styles/pagination.css">
</head>
<body>
	<div class="container">
		<div class="pd-ltr-20">
			<div class="min-height-200px mb-30">
				<!-- 标题 -->
				<div class="page-header mb-20">
					<div class="row">
						<div class="col-md-12 col-sm-12">
							<div class="title text-center">
								<h4>${activity.name}</h4>
								<small class="text-muted">
									<i class="icon-copy dw dw-wall-clock2"></i>&nbsp;活动时间：${activity.starttime} ~ ${activity.stoptime}
									&nbsp;&nbsp;
									<i class="icon-copy dw dw-eye"></i>&nbsp;浏览量：${activity.looks}
									&nbsp;&nbsp;
									<i class="icon-copy dw dw-analytics-3"></i>&nbsp;票数：${activity.countvotes}<br>
									<i class="icon-copy dw dw-user1"></i>&nbsp;创建者：${user.username}<br>
									<font color="red">当前活动，每天可以投1张选票给选手，每天可投给${activity.times}个选手</font>
								</small>
							</div>
						</div>
					</div>
				</div>
				<!-- 投票活动内容 -->
				<div class="row clearfix">
					<div class="col-lg-12 col-md-12 col-sm-12 mb-30">
						<div class="pd-20 card-box">
							<div class="tab">
								<ul class="nav nav-tabs customtab" role="tablist">
									<li class="nav-item">
										<a class="nav-link active" data-toggle="tab" href="#home2" role="tab" aria-selected="true">活动介绍</a>
									</li>
								</ul>
								<div class="tab-content">
									<div class="tab-pane fade active show" id="home2" role="tabpanel">
										<div class="pt-20 pb-20">
											${activity.introduece}
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 参赛选手 -->
				<div class="card-box">
					<div class="title pd-20 text-center" style="position: relative;">
						<h5>参赛选手</h5>
						<small><a href="./" style="position: absolute; top: 25px; right: 19px;">进入主页</a></small>
					</div>
					<form class="mb-5 pl-20 pb-20 pr-20" style="display: flex; flex-direction: row;" method="get">
						<input type="hidden" name="aId" value="${activity.id}">
						<input type="hidden" name="uId" value="${activity.userid}">
						<input class="form-control" name="keyword" style="width: 692px" type="text" placeholder="输入内容，选择需要搜索的条件，对选手检索">
						<select name="type" class="custom-select" style="margin-left: 20px; width: 191px">
							<option value="1">按编号搜索</option>
							<option value="2">按名称搜索</option>
							<option value="3">按选票区间搜索</option>
						</select>
						<button type="submit" class="btn btn-primary md-10" style="margin-left: 20px">
							<i class="dw dw-search2 search-icon"></i>
							搜索
						</button>
					</form>
					<!-- 一行里面放三位选手 -->
					<div class="row clearfix mb-5 pl-20 pb-20 pr-20">
						<c:forEach items="${contestants}" var="contestant">
							<div class="col-md-3 mb-20">
								<div class="card card-box">
									<a href="<%=path %>/vote/detail?cid=${contestant.id}&aid=${activity.id}&uid=${activity.userid}&vote=${contestant.votes}">
										<img style="width: 228px; height: 128px;" class="card-img-top" src="<%=path %>/${contestant.image}" alt="Card image cap">
									</a>
									<div class="card-body text-center">
										<h5 class="card-title">${contestant.contestantid}号：${contestant.name}</h5>
										<p class="card-text">获得票数：${contestant.votes}</p>
										<a href="javascript:voting(${activity.id}, ${contestant.id})" class="btn btn-primary" style="font-size: 12px;">投票</a>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../commons/scripts.jsp" %>
	<script src="vendors/scripts/pagination.build.js"></script>
	<script type="text/javascript">
		function voting(aid, cid) {
			if(confirm("确定要投一票吗？")) {
				$.ajax({
					url: '<%=path%>/vote',
					data: {
						uid: "${activity.userid}",
						aid: aid,
						cid: cid
					},
					dataType: "json",
					type: "post",
					success: function(e) {
						if(e.status == 'ok'){
							alert(e.msg);
							location.reload();
						} else {
							alert(e.msg);
						}
					}
				});
			}
		}
	</script>
	<c:if test="${msg != null}">
		<script type="text/javascript">
			alert("${msg}");
		</script>
	</c:if>
</body>
</html>