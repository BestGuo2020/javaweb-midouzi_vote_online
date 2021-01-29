<%@page import="vote509.entity.Activity"%>
<%@page import="vote509.util.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@ include file="../commons/base.jsp" %>
<%@ include file="../commons/styles.jsp" %>
<%@ include file="../commons/styles-backend.jsp" %>
<link rel="stylesheet" type="text/css" href="vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.css">
</head>
<body>
<div class="xs-pd-20-10 pd-ltr-20">
	<div class="page-header">
		<div class="row">
			<div class="col-md-6 col-sm-12">
				<div class="title">
					<c:choose>
						<c:when test="${activity != null}">
							<h4>编辑投票活动</h4>
						</c:when>
						<c:otherwise>
							<h4>添加投票活动</h4>
						</c:otherwise>
					</c:choose>
				</div>
				<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="<%=path %>/manage/userinfo">主页</a></li>
						<li class="breadcrumb-item active" aria-current="page"><a href="<%=path %>/manage/activity">活动</a></li>
						<c:choose>
						<c:when test="${activity != null}">
							<li class="breadcrumb-item active" aria-current="page">编辑活动信息</li>
						</c:when>
						<c:otherwise>
							<li class="breadcrumb-item active" aria-current="page">添加活动信息</li>
						</c:otherwise>
					</c:choose>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 mb-30">
			<div class="card-box pd-30 height-100-p">
				<form id="activityForm" enctype="multipart/form-data" method="post">
					<div class="form-group">
						<label>活动名称</label>
						<input class="form-control" type="text" name="activityName" value="${activity.name}">
					</div>
					<div class="form-group">
						<label>活动封面</label>
						<input type="file" class="form-control-file form-control height-auto" name="image">
					</div>
					<div class="form-group">
						<label>活动介绍</label>
						<textarea id="mytextarea" name="introduce" class="form-control" style="resize: none; height: 327px"></textarea>
					</div>
					<div class="form-group">
						<label>活动时间范围</label>
						<c:choose>
							<c:when test="${activity != null}">
								<input type="hidden" name="id" value="${activity.id}">
								<% Activity ac = (Activity) request.getAttribute("activity"); %>
								<input value="<%=DateUtils.getTime(ac.getStarttime()) %> - <%=DateUtils.getTime(ac.getStoptime()) %>" class="form-control datetimepicker-range" name="area" data-date-format="yyyy/mm/dd" type="text" placeholder="请设置开始时间和结束时间">
							</c:when>
							<c:otherwise>
								<input class="form-control datetimepicker-range" name="area" data-date-format="yyyy/mm/dd" type="text" placeholder="请设置开始时间和结束时间">
							</c:otherwise>
						</c:choose>
						
					</div>
					<input id="demo6" type="hidden" value="1" name="times" class="form-control">
					<div class="form-group">
						<label>投票规则设定</label>
						<div class="row">
							<div class="col-md-6 col-sm-12">
								<div class="custom-control custom-checkbox mb-5 pt-10">
									<c:choose>
										<c:when test="${activity != null && activity.times > 1}">
											<input type="checkbox" checked name="sss" class="custom-control-input" id="customCheck3-2">
											<label class="custom-control-label" for="customCheck3-2">一个ip每天可以投给几位选手</label>
										</c:when>
										<c:otherwise>
											<input type="checkbox" name="sss" class="custom-control-input" id="customCheck3-2">
											<label class="custom-control-label" for="customCheck3-2">一个ip每天可以投给几位选手</label>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
							<div class="col-md-6 col-sm-12">
								<div class="form-group">
									<c:choose>
										<c:when test="${activity != null && activity.times > 1}">
											<input id="demo4" type="text" name="times" class="form-control" value="${activity.times}">
										</c:when>
										<c:otherwise>
											<input id="demo4" type="text" name="times" class="form-control" disabled>
										</c:otherwise>
									</c:choose>
								</div>
							</div>
						</div>
					</div>
					<div class="form-group text-center">
						<c:choose>
							<c:when test="${activity != null}">
								<button type="submit" class="btn btn-success">编辑投票活动</button>
							</c:when>
							<c:otherwise>
								<button type="submit" class="btn btn-success">创建投票活动</button>
								<input type="reset" class="btn btn-success" value="重置"/>
							</c:otherwise>
						</c:choose>
					</div>
				</form>
			</div>
		</div>
	</div>
	<jsp:include page="../commons/bottom.jsp"></jsp:include>
	<jsp:include page="../commons/scripts.jsp"></jsp:include>
	<jsp:include page="../commons/scripts-backend.jsp"></jsp:include>
	<script src="vendors/tinymce/tinymce.min.js"></script>
	<script src="vendors/bootstrap-touchspin/jquery.bootstrap-touchspin.js"></script>
	<script src="vendors/scripts/jquery.form.js"></script>
	<script type="text/javascript">
	tinymce.init({
	    selector: '#mytextarea',
	    language:'zh_CN'
	});
	$("#demo3").TouchSpin({
	    initval: 1,
	    min: 1,
	    max: 10
	});
	$("#demo4").TouchSpin({
	    initval: 1,
	    min: 1,
	    max: 10
	});
	// 启用，禁用选择框
	$("#customCheck3-1").change(function(e){
		console.log(this.checked);
		$("#demo3")[0].disabled = !this.checked;
		if(!this.checked) {
			$("#demo3")[0].value = 1;
		}
	});
	$("#customCheck3-2").change(function(e){
		console.log(this.checked);
		$("#demo4")[0].disabled = !this.checked;
		if(!this.checked) {
			$("#demo4")[0].value = 1;
		}
	});
	
	$(document).ready(function(){
		
		// 表单提交
		$("#activityForm").ajaxForm({
			url: "<%=path %>/manage/activityEdit?type=update",
			type: "POST",
			dataType: "json",
			success: function(e) {
				if(e.status == 'success') {
					alert(e.msg);
					location.href = '/inlinevote/manage/activity';
				} else if (e.status == 'error') {
					alert(e.msg);
				} else if (e.status == 'login') {
					alert(e.msg);
					top.location.href = '/inlinevote/manage/login';
				}
			}
		});
		
	});

	$("#demo3").change(function() {
		$("#demo6").val(this.value);
	});
	</script>
	<c:if test="${activity != null}">
	<script>
		document.onreadystatechange = function() {
			var html = `${activity.introduece}`;
			if(document.readyState == "complete") {
				tinymce.editors[0].setContent(html);
				$("#demo6").val("${activity.times}");
			}
		}
	</script>
	</c:if>
</div>
</body>
</html>