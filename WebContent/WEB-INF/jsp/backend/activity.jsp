<%@page import="vote509.vo.ActivityVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@ include file="../commons/base.jsp" %>
<%@ include file="../commons/styles.jsp" %>
<%@ include file="../commons/styles-backend.jsp" %>
</head>
<body>
<div class="xs-pd-20-10 pd-ltr-20">
	<div class="page-header">
		<div class="row">
			<div class="col-md-6 col-sm-12">
				<div class="title">
					<% String rr = request.getParameter("status"); // 状态
					String type = request.getParameter("type"); // 查询类别
					String keyword = request.getParameter("keyword"); // 查询关键字
					   if("all".equals(rr) || rr == null) {
						   out.print("<h4>全部活动</h4>");
					   } else if ("nostart".equals(rr)) {
						   out.print("<h4>未开始</h4>");
					   }
					   else if ("start".equals(rr)) {
						   out.print("<h4>进行中</h4>");
					   }
					   else if ("end".equals(rr)) {
						   out.print("<h4>已结束</h4>");
					   } else {
						   out.print("<h4>全部活动</h4>");
					   }
					%>
				</div>
				<nav aria-label="breadcrumb" role="navigation">
					<ol class="breadcrumb">
						<li class="breadcrumb-item"><a href="<%=path %>/manage/userinfo">主页</a></li>
						<li class="breadcrumb-item active" aria-current="page">我的活动管理</li>
						<%
					   if("all".equals(rr) || rr == null) {
						   out.print("<li class='breadcrumb-item active' aria-current='page'>全部活动</li>");
					   } else if ("nostart".equals(rr)) {
						   out.print("<li class='breadcrumb-item active' aria-current='page'>未开始</li>");
					   }
					   else if ("start".equals(rr)) {
						   out.print("<li class='breadcrumb-item active' aria-current='page'>进行中</li>");
					   }
					   else if ("end".equals(rr)) {
						   out.print("<li class='breadcrumb-item active' aria-current='page'>已结束</li>");
					   } else {
						   out.print("<li class='breadcrumb-item active' aria-current='page'>全部活动</li>");
					   }
					   %>
					</ol>
				</nav>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12 col-md-12 col-sm-12 mb-30">
			<div class="card-box pd-30 height-100-p">
				<div style="display: flex; justify-content: space-between;">
					<div class="mb-30">
						<a href="manage/activityEdit" class="btn btn-success">创建活动</a>
						<input type="button" class="btn btn-primary" id="del_btn" value="删除"/>
					</div>
					<form>
						<input name="type" type="hidden" value="search">
						<input type="hidden" name="status" value="<%=rr %>">
						<input type="text" name="keyword" value="<%=keyword == null ? "" : keyword %>" placeholder="请输入要查找的活动"/>
						<input type="submit" class="btn btn-primary" value="搜索">
					</form>
				</div>
				<div class="browser-visits">
					<table class="table" id="tab">
						<thead align="center">
							<tr>
								<th>
									<input type="checkbox" id="selectAll"/>全选  <input type="checkbox" id="ReverseSelect"/>反选
								</th>
								<th scope="col">编号</th>
								<th scope="col">活动名称</th>
								<th scope="col">总票数</th>
								<th scope="col">总选手数</th>
								<th scope="col">活动开始时间</th>
								<th scope="col">活动结束时间</th>
								<th scope="col">活动状态</th>
								<th scope="col">操作</th>
							</tr>
						</thead>
						<tbody align="center">
							<% 
							int p = Integer.parseInt((String)request.getAttribute("page"));
							int count = (Integer) request.getAttribute("count");
							int pp = (int) Math.ceil(count / 10.0);
							// System.out.print(count);
							%>
							<%
							List<ActivityVo> activities = (List<ActivityVo>) request.getAttribute("activities");
							if(activities.size() != 0) {
								int i = (p - 1) * 10;
								for(ActivityVo activity : activities) {
								%>
								<tr>
									<td><input name='aid' value='<%=activity.getAid() %>' type='checkbox' /></td>
									<td scope="row" ><%=i + 1 %></td>
									<td><a href="<%=path %>/manage/contestant?activityId=<%=activity.getAid() %>"><%=activity.getName() %></a></td>
									<td><%=activity.getCountVotes() %></td>
									<td><%=activity.getContestantCount() %></td>
									<td><%=activity.getStartTime() %></td>
									<td><%=activity.getStopTime() %></td>
									<td><% int status = activity.getStatus(); 
										switch(status) {
										case 0:
											out.print("<span class='badge badge-secondary'>未开始</span>");
											break;
										case 1:
											out.print("<span class='badge badge-success'>进行中</span>");
											break;
										case 2:
											out.print("<span class='badge badge-dark'>已结束</span>");
											break;
										}
									%></td>
									<td>
										<a href='<%=path %>/manage/activityEdit?aid=<%=activity.getAid() %>' style='color: #69f'>修改</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='javascript:;' onclick="del('<%=activity.getAid()%>')" style='color: #69f'>删除</a>
									</td>
								</tr>
								<%
								i++;
								}
							} else {%>
								<tr>
									<td colspan="9" align="center">暂无数据</td>
								</tr>
						  <%}%>
						</tbody>
					</table>
				</div>
				<div class="text-center">
					<!-- 分页 -->
					<%if(pp != 0){ %>
					<div class="blog-pagination pb-20">
						<div class="btn-toolbar justify-content-center mb-15">
							<div class="btn-group">
								<a href="<%=path %>/manage/activity?page=1&status=<%=rr %>&type=<%=type %>&keyword=<%=keyword == null ? "" : keyword %>" class="btn btn-outline-primary prev" title="首页"><i class="fa fa-angle-double-left"></i></a>
								<a href="<%=path %>/manage/activity?page=<%=p == 1 ? 1 : p - 1 %>&status=<%=rr %>&type=<%=type %>&keyword=<%=keyword == null ? "" : keyword %>" class="btn btn-outline-primary prev" title="上一页"><i class="fa fa-angle-left"></i></a>
								<%
								
								if(pp <= 5) {
									for(int j = 1; j <= pp; j++) {%>
										<%if(j == p) {
											%>
											<span class="btn btn-primary current"><%=j %></span>
											<%
										} else {
											%>
											<a href="<%=path %>/manage/activity?page=<%=j %>&status=<%=rr %>&type=<%=type %>&keyword=<%=keyword == null ? "" : keyword %>" class="btn btn-outline-primary current"><%=j %></a>
											<%
										}%>
								<% }
								} else {
									if(p <= 3) {
										/*
										|1|  2  3  4 5
										 1  |2| 3  4 5
										 1   2  |3| 4 5
										*/
										for(int j = 1; j <= 5; j++) {
									%>
											<%if(j == p) {
												%>
												<span class="btn btn-primary current"><%=j %></span>
												<%
											} else {
												%>
												<a href="<%=path %>/manage/activity?page=<%=j %>&status=<%=rr %>&type=<%=type %>&keyword=<%=keyword == null ? "" : keyword %>" class="btn btn-outline-primary current"><%=j %></a>
												<%
											}%>
									<% } 
									} else if (p + 3 > pp) {%>
									<%
										/*
										5  6 |7| 8  9
										5  6  7 |8| 9
										5  6  7  8 |9|
										如果是这样
										1 2 |3| 4 5
										2 3 |4| 5 6
										2 3 4 |5| 6
										2 3 4 5 |6|
										*/
										for(int j = pp - 4; j <= pp; j++) {%>
											<%
											if(j == p) {
												%>
												<span class="btn btn-primary current"><%=j %></span>
												<%
											} else {
												%>
												<a href="<%=path %>/manage/activity?page=<%=j %>&status=<%=rr %>&type=<%=type %>&keyword=<%=keyword == null ? "" : keyword %>" class="btn btn-outline-primary current"><%=j %></a>
												<%
											}%>
									<% } 
									} else {%> 
									<%
										/*
										2 3 |4| 5 6
										3 4 |5| 6 7
										4 5 |6| 7 8
										*/
										for(int j = p - 2; j < p + 3; j++) {%>
											<%
											if(j == p) {
												%>
												<span class="btn btn-primary current"><%=j %></span>
												<%
											} else {
												%>
												<a href="<%=path %>/manage/activity?page=<%=j %>&status=<%=rr %>&type=<%=type %>&keyword=<%=keyword == null ? "" : keyword %>" class="btn btn-outline-primary current"><%=j %></a>
												<%
											}
										}%>
									<%	
									} 
								}
								
								// 判断是否超过页数
								if(p + 1 > pp) {
								%>
								<a href="javascript:;" style="cursor: not-allowed;" class="btn btn-outline-primary next" title="下一页"><i class="fa fa-angle-right"></i></a>
							  <%} else {%>
							  	<a href="<%=path %>/manage/activity?page=<%=p + 1 %>&status=<%=rr %>&type=<%=type %>&keyword=<%=keyword == null ? "" : keyword %>" class="btn btn-outline-primary next" title="下一页"><i class="fa fa-angle-right"></i></a>
							  <%} %>
								<a href="<%=path %>/manage/activity?page=<%=pp %>&status=<%=rr %>&type=<%=type %>&keyword=<%=keyword == null ? "" : keyword %>" class="btn btn-outline-primary next" title="末页"><i class="fa fa-angle-double-right"></i></a>
							</div>
							<div>
								<form style="padding-top: 7px; padding-left: 10px;">
									跳转到第 <input type="number" style="width: 50px;" name="page"> 页&nbsp;
									<input type="submit" class="btn btn-success" style="margin-top: -7px" value="跳转">
									<input type="hidden" name="status" value="<%=rr %>">
									<input type="hidden" name="type" value="<%=type %>"/>
									<input type="hidden" name="keyword" value="<%=keyword == null ? "" : keyword %>"/>
								</form>
							</div>
						</div>
					</div>
					<%} %>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../commons/bottom.jsp"></jsp:include>
	<jsp:include page="../commons/scripts.jsp"></jsp:include>
	<jsp:include page="../commons/scripts-backend.jsp"></jsp:include>
	<script type="text/javascript">
		function del(id) {
			if(confirm("删除该活动将会删除参与该活动的选手，你确定要删除该活动吗？")) {
				$.ajax({
					type: "POST",
					url: "<%=path %>/manage/activityEdit?type=del",
					dataType: "json",
					data: {
						aid: id,
					},
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
			}
		}
		
		var i=0;
        //全选
        $("#selectAll").on("click",function(){
            if(i==0){
                //把所有复选框选中
                $("#tab tbody td :checkbox").prop("checked", true);
                i=1;
            }else{
                $("#tab tbody td :checkbox").prop("checked", false);
                i=0;
            }
            
        });
        
        //反选
        $("#ReverseSelect").on("click",function(){
            
            $("#tab tbody td :checkbox").each(function(){
                //遍历所有复选框，然后取值进行 !非操作
                $(this).prop("checked", !$(this).prop("checked"));
            })
        });
        
        $("#del_btn").on("click",function(){
        	if (confirm("确定要删除选中的数据吗？")) {
        		var arr=new Array();
                $('#tab tbody input:checkbox[name=aid]:checked').each(function(i){
                    arr[i] = $(this).val();
                });
                var vals = arr.join(",");
                
                $.ajax({
                    type: "post",
                    url: 'manage/activity',
                    data: {
                    	"status":"delete",
                    	"aids":vals
                    },
                    dataType: "json",
                    success: function(data) {
                        if (data.status == 'success') {
                        	alert(data.msg);
                        	location.reload();
                        }
                        else if(data.status == 'login') {
                        	alert(data.msg);
                        	top.location.href = '/inlinevote/manage/login'
                        } else {
                        	alert(data.msg);
                        	location.reload();
                        }
                    }
                });
        	}
        })
	</script>
</div>
</body>
</html>