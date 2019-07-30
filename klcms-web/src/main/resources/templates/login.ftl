<!DOCTYPE html>
<html lang="en"  xmlns:th="http://www.thymeleaf.org">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<meta name="description" content="">
		<meta name="author" content="">
		<title>登录</title>

		<link href="/plugins/bootstrap/css/bootstrap.min.css"  rel="stylesheet">
		<link href="/css/signin.css" rel="stylesheet">
	</head>
	<body class="text-center">
		<form class="form-signin" action="/user/login" method="post">
			<img class="mb-4" src="/images/logo_iblue.png" alt="" width="72" height="72">
			<h1 class="h3 mb-3 font-weight-normal" >请登录</h1>

			<!-- Request["msg"]! 加! 如果绑定的属性为null,不会报错 -->
			<p style="color: red" >${Request["msg"]!}</p>
			<label class="sr-only" >用户名</label>
			<input type="text"  name="username" class="form-control" placeholder="用户名" required autofocus="">
			<label class="sr-only" >密码</label>
			<input type="password" name="password" class="form-control" placeholder="密码" required>
			<div class="checkbox mb-3">
				<label>
          			<input type="checkbox" value="remember-me"/> 记住我
        		</label>
			</div>
			<button class="btn btn-lg btn-primary btn-block" type="submit" >登 录</button>
			
			<p class="mt-5 mb-3 text-muted">© 2014-${.now?string["yyyy"]}</p>
			<a class="btn btn-sm" href="/index.html?l='zh_CN')">中文</a>
			<a class="btn btn-sm" href="/index.html?l='en_US')">English</a>
		</form>
	</body>

</html>