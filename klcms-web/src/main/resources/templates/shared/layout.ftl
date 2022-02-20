<!DOCTYPE html>
<#macro layout>
<html>
<head>
<!--解决：IE11以及360浏览器文档模式默认为IE7版本，导致报错和布局错乱的问题-->
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1,user-scalable=no">
<title>i编程网</title>
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="/css/layout.css">
<link rel="stylesheet" href="/css/theme.css">
<link rel="stylesheet" href="/plugins/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="/plugins/fontawesome/css/all.min.css" />
</head>
<body>
	<div class="navbar">
		<div class="kl-container">
			<div class="navbar-header float-left">
				<div class="float-left" style="text-align: center;">
					<img class="float-left" src="/images/logo_iblue.png"
						style="width: 28px; height: 28px; margin-top: 8px" /> <a href="/"
						class="navbar-brand">编程网</a>
				</div>
			</div>
		</div>
		<div class="float-left text-secondary"
			style="margin: 15px 200px 0 20px; font-size: 0.9rem;">项目实战记录，问题排查汇总</div>
		<div class="float-right" style="margin: 15px 2% 0 20px;">
			<#if article?exists && edit?exists> <a href="/">返回</a>
			<#elseif article?exists> <a href="javascript:history.go(-1)">返回</a>
			</#if>
			<#if Session.loginUser?exists> <a href="/category/list.html">系统管理</a>
			<a href="/user/logout">退出</a> <#else> <a href="/login">登录</a></#if>
		</div>
	</div>
	<hr class="kl-container" style="margin-top: 0; margin-bottom: 0;" />

	<div class="kl-container body-content" style="margin-top: 12px">
		<div>
			<div class="left-side ">
				<ul>
					<#list categories as cat>
					<li class="menu"><#if cat.id==categoryId!> <a
							href="/index.html?category=${cat.id}" class="menu-selected">${cat.name}</a>
						<#else> <a href="/index.html?category=${cat.id}">${cat.name}</a></#if>

						<ul>
							<#list cat.sublist as subcat>
							<li class="sub-menu"><#if subcat.id==categoryId!> <a
									href="/index.html?category=${subcat.id}" class="menu-selected">${subcat.name}</a>
								<#else> <a href="/index.html?category=${subcat.id}">${subcat.name}</a></#if></li>
							</#list>
						</ul></li>
					</#list>
				</ul>
			</div>
			<div id="content" class="center-content">
				<#nested />
			</div>
			<div class="right-side">
				<div class="side-title">
					<div>技术网站导航</div>
				</div>
				<ul class="ulCategory">
					<li><em>IDE</em></li>
					<li><a href='https://www.jetbrains.com/idea/' target='_blank'>IDEA</a></li>
					<li><a href='https://www.eclipse.org/' target='_blank'>Eclipse</a></li>
					<li><a href='https://developer.android.com/studio/index.html'
						target='_blank'>Android Studio</a></li>
					<li><a href='http://maven.apache.org' target='_blank'>Maven官网</a></li>
			

					<li><em>前端</em></li>
					<li><a href='http://www.cnblogs.com/ghostwu/p/7499237.html'
						target='_blank'>Webpack</a></li>
					<li><a href='https://nodejs.org/en/' target='_blank'>Nodejs</a></li>
					<li><a href='https://cn.vuejs.org/v2/api/' target='_blank'>Vue</a></li>
					<li><a href='https://angular.io/' target='_blank'>Angular</a></li>
					<li><a href='https://getbootstrap.com/' target='_blank'>Bootstrap</a></li>
					<li><a href='https://element.eleme.cn/#/zh-CN' target='_blank'>Element-UI</a></li>

					<li><em>后端</em></li>
					<li><a href='https://spring.io/projects' target='_blank'>Spring</a></li>
					<li><a href='https://spring.io/projects/spring-boot/'
						target='_blank'>Spring Boot</a></li>
					<li><a href='https://getbootstrap.com/' target='_blank'>Spring
							Cloud</a></li>
					<li><a href='https://spring.io/projects/spring-security'
						target='_blank'>Spring Security</a></li>
					<li><a href='https://www.rabbitmq.com/getstarted.html'
						target='_blank'>RabbitMQ</a></li>
					<li><a href='https://developer.emqx.io/docs/broker/v3/cn/'
						target='_blank'>EMQ</a></li>


				</ul>
			</div>
		</div>
		<div class="clearfix"></div>
		<hr />
		<footer>
			Copyright &copy;2014-${.now?string["yyyy"]}, i编程网-iprogram.com.cn,
			All Rights Reserved
			<script type="text/javascript">
				var cnzz_protocol = (("https:" == document.location.protocol) ? " https://"
						: " http://");
				document
						.write(unescape("%3Cspan id='cnzz_stat_icon_1253705588'%3E%3C/span%3E%3Cscript src='"
								+ cnzz_protocol
								+ "s11.cnzz.com/z_stat.php%3Fid%3D1253705588%26show%3Dpic' type='text/javascript'%3E%3C/script%3E"));
			</script>

		</footer>

	</div>

	<script src="/webjars/jquery/3.3.1/jquery.js"></script>
	<script src="/plugins/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript">
		function searchArticles() {
			var kw = $("#txt_kw").val();
			//encodeURIComponent方法在编码单个指请求参 数时是最常用的，它可以将参数中的中文、特殊字符进行转义，但不会影响整个URL。
			//encodeURI会保留#等特殊字符，因此用来对参数编码，不能完全达到预期目标
			kw = encodeURIComponent(kw);
			var url = $("#link_search").attr("url");
			if (kw.length > 0) {
				if (url.indexOf('?') > 0) {
					url = url + "&keyword=" + kw;
				} else {
					url = url + "?keyword=" + kw;
				}
			}
			location.href = url;
		}

		function onEnter(e) {
			var keynum
			if (window.event) // IE
			{
				keynum = e.keyCode
			} else if (e.which) // Netscape/Firefox/Opera
			{
				keynum = e.which
			}
			if (keynum == 13) {
				searchArticles();
			}
		}
	</script>

</body>
</html>
</#macro>
