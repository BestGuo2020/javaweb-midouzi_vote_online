<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>用户注册</title>
<link rel="stylesheet" href="css/style.css">
<body>

<div class="register-container">
	<h1>欢迎注册</h1>
	
	<div class="connect">
		<p>祢豆子投票网站，欢迎您！</p>
	</div>
	
	<form action="/inlinevote/Register" method="post" id="registerForm">
		<div>
			<input type="text" name="username" class="username" placeholder="请输入用户名" autocomplete="off"/>
		</div>
		<div>
			<input type="password" name="password" class="password" placeholder="请输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		<div>
			<input type="password" name="confirm_password" class="confirm_password" placeholder="请再次输入密码" oncontextmenu="return false" onpaste="return false" />
		</div>
		
		<div>
			<input type="email" name="email" class="email" placeholder="请输入邮箱" oncontextmenu="return false" onpaste="return false" />
		</div>

		<button id="submit" type="submit">注册</button>
	</form>
	<a href="manage/login">
		<button type="button" class="register-tis">已有账号</button>
	</a>

<a href="/inlinevote/">
		<button type="button" class="register-tis">到投票主站看看</button>
	</a>
</div>

</body>
<script src="js/jquery.min.js"></script>
<script src="js/common.js"></script>

<script src="js/supersized.3.2.7.min.js"></script>
<script src="js/supersized-init.js"></script>
<script src="js/jquery.validate.min.js?var1.14.0"></script>
</html>