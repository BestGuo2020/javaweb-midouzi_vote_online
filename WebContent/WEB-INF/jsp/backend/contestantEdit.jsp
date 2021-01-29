<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
						<c:when test="${contestant != null}">
							<h4>编辑选手信息</h4>
						</c:when>
						<c:otherwise>
							<h4>添加选手信息</h4>
						</c:otherwise>
					</c:choose>
				</div>
				<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="<%=path %>/manage/userinfo">主页</a></li>
						<li class="breadcrumb-item active" aria-current="page"><a href="<%=path %>/manage/activity">活动</a></li>
						<c:choose>
						<c:when test="${contestant != null}">
							<li class="breadcrumb-item active" aria-current="page">编辑选手信息</li>
						</c:when>
						<c:otherwise>
							<li class="breadcrumb-item active" aria-current="page">添加选手信息</li>
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
				<form id="contestantForm" enctype="multipart/form-data" method="post">
					<input type="hidden" name="aid" value="${aid}">
					<input type="hidden" name="id" value="${contestant.id}">
					<div class="form-group">
						<label>选手名</label>
						<input class="form-control" type="text" value="${contestant.name}" name="name">
					</div>
					<div class="form-group">
						<label>选手图片</label>
						<input type="file" class="form-control-file form-control height-auto" name="images">
					</div>
					<div class="form-group">
						<label>选手简介</label>
						<textarea id="mytextarea" name="introduce" style="resize: none; height: 327px" class="form-control"></textarea>
					</div>
					<div class="form-group text-center">
						<button type="submit" class="btn btn-success">${contestant != null ? '更新选手' : '添加选手'}</button>
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
	$("input[name='demo3']").TouchSpin({
	    initval: 1,
	    min: 1,
	    max: 10
	});
	$("#demo4").TouchSpin({
	    initval: 1,
	    min: 1,
	    max: 10
	});
	$(document).ready(function(){
			
		// 表单提交
		$("#contestantForm").ajaxForm({
			url: "<%=path %>/manage/contestantEdit?type=update",
			type: "POST",
			dataType: "json",
			success: function(e) {
				if(e.status == 'success') {
					alert(e.msg);
					location.href = '/inlinevote/manage/contestant?activityId=${aid}';
				} else if (e.status == 'error') {
					alert(e.msg);
				} else if (e.status == 'login') {
					alert(e.msg);
					top.location.href = '/inlinevote/manage/login';
				}
			}
		});
		
	});
	</script>
	<c:if test="${contestant != null}">
		<script>
			document.onreadystatechange = function() {
				var html = `${contestant.introduce}`;
				if(document.readyState == "complete") {
					tinymce.editors[0].setContent(html);
				}
			}
		</script>
	</c:if>
</div>
</body>
</html>