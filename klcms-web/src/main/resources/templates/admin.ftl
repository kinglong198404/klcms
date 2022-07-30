<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>i编程网</title>

<!-- 遇到字体不显示问题：
         在pom.xml配置文件中添加以下代码，让静态资源支持字体图标文件 
     1.不能使用sb-admin代码中的fontawesome资源文件（被修改过），应使用从官网上提取出来的css和webfonts
     2.pom.xml中添加配置文件，以下的nonFilteredFileExtensions部分
		<plugin>
			<groupId>org.apache.maven.plugins</groupId>
			<artifactId>maven-resources-plugin</artifactId>
			<configuration>
				<delimiters>
					<delimit>$</delimit>
				</delimiters>
				<nonFilteredFileExtensions>
					<nonFilteredFileExtension>woff</nonFilteredFileExtension>
					<nonFilteredFileExtension>woff2</nonFilteredFileExtension>
					<nonFilteredFileExtension>eot</nonFilteredFileExtension>
					<nonFilteredFileExtension>ttf</nonFilteredFileExtension>
					<nonFilteredFileExtension>svg</nonFilteredFileExtension>
				</nonFilteredFileExtensions>
			</configuration>
		</plugin>
 -->
<link rel="icon"  href="/favicon.ico"type="image/x-icon" />
<link href="/plugins/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="/plugins/fontawesome/css/all.min.css" rel="stylesheet" />
<link rel="stylesheet" href="/css/layout.css">
<link rel="stylesheet" href="/css/theme.css">
<link href="css/sb-admin.css" rel="stylesheet" />

</head>

<body id="page-top">
	<!-- top bar -->
	<nav class="navbar navbar-expand navbar-dark bg-dark static-top">
		<!-- Logo -->
		<img class="float-left" src="/images/logo_iblue.png"
						style="width: 27px; height: 27px; margin-top: 8px" />
		<a class="navbar-brand mr-1" href="index.html">编程网</a>

		<!-- 切换按钮：控制左侧菜单的显示和隐藏 -->
		<button class="btn btn-link btn-sm text-white order-1 order-sm-0"
			id="sidebarToggle" href="#">
			<i class="fas fa-bars"></i>
		</button>

		<!-- Navbar Search -->
		<form
			class="d-none d-md-inline-block form-inline ml-auto mr-0 mr-md-3 my-2 my-md-0">
			<!--<div class="input-group">
				<input type="text" class="form-control" placeholder="Search for..."
					aria-label="Search" aria-describedby="basic-addon2">
				<div class="input-group-append">
					<button class="btn btn-primary" type="button">
						<i class="fas fa-search"></i>
					</button>
				</div>
			</div> -->
		</form>
		<!-- Navbar Right Menu-->
		<ul class="navbar-nav ml-auto ml-md-0">
			<#if Session.loginUser?exists> <li class="nav-item "><a class="nav-link" href="/category/list.html">系统管理</a></li>
			<li class="nav-item "><a class="nav-link"  href="/user/logout">退出</a> </li>
			<#else> <li class="nav-item "><a class="nav-link"  href="/login">登录</a></li></#if>
			<!--
			<li class="nav-item dropdown no-arrow mx-1"><a
				class="nav-link dropdown-toggle" href="#" id="alertsDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"><span class="badge badge-danger">9+</span><i class="fas fa-bell fa-fw"></i>
				</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="alertsDropdown">
					<a class="dropdown-item" href="#">Action</a> <a
						class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
			<li class="nav-item dropdown no-arrow">
				<a
				class="nav-link dropdown-toggle" href="#" id="userDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="fas fa-user-circle fa-fw"></i>
			</a>
				<div class="dropdown-menu dropdown-menu-right"
					aria-labelledby="userDropdown">
					<a class="dropdown-item" href="#">Settings</a> <a
						class="dropdown-item" href="#">Activity Log</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#" data-toggle="modal"
						data-target="#logoutModal">Logout</a>
				</div></li>-->
		</ul>

	</nav>

	<div id="wrapper">

		<!-- Sidebar -->
		<ul class="sidebar navbar-nav">
			<#list categories as cat>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="pagesDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> <i class="${cat.icon!}"></i>
					<span>${cat.name}</span></a>

				<div class="dropdown-menu" aria-labelledby="pagesDropdown">
					<#list cat.sublist as subcat> <a
						class="${(subcat.id==categoryId!)?string('dropdown-item menu-selected','dropdown-item')}"
						href="/admin.html?category=${subcat.id}">${subcat.name}</a> </#list>
				</div></li>
			</#list>
		</ul>

		<!-- content-wrapper -->
		<div id="content-wrapper row"
			style="padding-top: 10px; max-width: 100%;">

			<div class="center-content" style="border-left: 0 outset #e8e8e8;">
				<div style="margin: 5px 0">

					<div class="float-left" style="width: 55%;  min-width: 276px; margin-left: 10px;">
						<div class="input-group">
							<input type="text" class="form-control" id="txt_kw"
								value="${keyword!}" placeholder="输入关键字检索" aria-label="Search"
								aria-describedby="basic-addon2" onkeydown="onEnter(event);">
							<div class="input-group-append">
								<button id="link_search"
									url="/admin.html?category=${categoryId!}"
									onclick="searchArticles()" class="btn btn-primary"
									type="button">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>

						<input id="category" type="hidden" value="${categoryId!}"
							class="text-info border-bottom " />
					</div>

					<div class="float-right " style="margin-right:12px;">
						<#if Session.loginUser?exists> <a id="link_add"
							href="/add/0202" class="btn btn-primary">新增文章</a> </#if>
					</div>
					<div class="clearfix"></div>
				</div>
				<ul id="ul_main">
					<#list articles as art>
					<li>
						<div class="article-item">
							<h4 class="article-title">
								<a href="./item/${art.id}.html">${art.title}</a>
							</h4>
							<!-- freemarker模板使用java.util.Date 报错 Can't convert the date-like value to string because it isn't 
	 							 known if it's a date (no time part), time or date-time value		
	 							  解决方案：前端使用<div >${art.createTime?string('yyyy-MM-dd HH:mm')}</div> -->
							<!--<div >${art.createTime?datetime}</div>-->
							<div>作者： ${art.authorName!} &nbsp; &nbsp; &nbsp; 发表时间：
								${art.createTime?string('yyyy-MM-dd HH:mm')}</div>
							<#if art.keyword?exists>
							<div>关键词：${art.keyword!}</div>
							</#if>
							<div class="summary">${art.text}</div>
						</div>
					</li>
					</#list>
				</ul>
				<!-- 注意：快捷键Ctrl+Shift+F格式化代码，导致freemarker代码错乱 -->
				<#if (pageCount> 0)>
				<div class="meneame padding10-right" style="text-align: right">
					<nav aria-label="Page navigation example">

						<ul class="pagination justify-content-end">
							<li
								class="${(1 == page)?string('page-item disabled', 'page-item')}"><a
								class="page-link"
								href="/admin.html?category=${categoryId!}&keyword=${encodeKeyword!}&page=${page-1}">上一页</a></li>

							<#if (page lt 3 && (page+3) gt pageCount)> <#list
								1..pageCount as i>
							<li
								class="${(i == page)?string('page-item active', 'page-item')}"><a
								class="page-link"
								href="/admin.html?category=${categoryId!}&keyword=${encodeKeyword!}&page=${i}">${i}</a></li>
							</#list> <#elseif (page lt 3)> <#list 1.. (page+3) as i>
							<li
								class="${(i == page)?string('page-item active', 'page-item')}"><a
								class="page-link"
								href="/admin.html?category=${categoryId!}&keyword=${encodeKeyword!}&page=${i}">${i}</a></li>
							</#list> <#elseif (page+3) gt pageCount> <#list
								(page-3)..pageCount as i>
							<li
								class="${(i == page)?string('page-item active', 'page-item')}"><a
								class="page-link"
								href="/admin.html?category=${categoryId!}&keyword=${encodeKeyword!}&page=${i}">${i}</a></li>
							</#list> <#else> <#list (page-3)..(page+3) as i>
							<li
								class="${(i == page)?string('page-item active', 'page-item')}"><a
								class="page-link"
								href="/admin.html?category=${categoryId!}&keyword=${encodeKeyword!}&page=${i}">${i}</a></li>
							</#list></#if>

							<li class="page-item disabled"><span class="page-link">${page}/${pageCount}</span></li>
							<li
								class="${(pageCount == page)?string('page-item disabled', 'page-item')}"><a
								class="page-link"
								href="/admin.html?category=${categoryId!}&keyword=${encodeKeyword!}&page=${page+1}">下一页</a></li>
						</ul>
					</nav>

				</div>
				</#if>

				<hr />
				<footer>
					<div>Copyright &copy;2014-${.now?string["yyyy"]},
						i编程网-iprogram.com.cn, All Rights Reserved</div>
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
			<!-- /.container-fluid -->
			<div class="right-side" style="margin-right: 12px;">
				<div class="side-title">
					<div>技术网站导航</div>
				</div>
				<ul class="ulCategory">
					<li><em>IDE、设计工具</em></li>
					<li><a href='https://www.jetbrains.com/idea/' target='_blank'>IDEA</a></li>
					<li><a href='https://www.eclipse.org/' target='_blank'>Eclipse</a></li>
					<li><a href='https://developer.android.com/studio/index.html'
						target='_blank'>Android Studio</a></li>
					<li><a href='http://maven.apache.org' target='_blank'>Maven官网</a></li>
					<li><a href='http://maven.apache.org/download.cgi'
						target='_blank'>Maven下载</a></li>
					<li><a href='http://www.sparxsystems.com/' target='_blank'>Enterprise
							Architect</a></li>


					<li><em>前端</em></li>
					<li><a href='http://www.cnblogs.com/ghostwu/p/7499237.html'
						target='_blank'>Webpack</a></li>
					<li><a href='https://nodejs.org/en/' target='_blank'>Nodejs</a></li>
					<li><a href='https://cn.vuejs.org/v2/api/' target='_blank'>Vue</a></li>
					<li><a href='https://angular.io/' target='_blank'>Angular</a></li>
					<li><a href='https://getbootstrap.com/' target='_blank'>Bootstrap</a></li>
					<li><a href='https://element.eleme.cn/#/zh-CN' target='_blank'>Element-UI</a></li>
					<li><a href='https://mint-ui.github.io/#!/zh-cn'
						target='_blank'>Mint-UI</a></li>
					<li><a href='https://www.highcharts.com' target='_blank'>Highcharts</a></li>
					<li><a href='https://echarts.baidu.com/' target='_blank'>Echarts</a></li>
					<li><a
						href='http://lbsyun.baidu.com/index.php?title=jspopular3.0'
						target='_blank'>百度地图（Web）</a></li>

					<li><em>移动端</em></li>
					<li><a
						href='https://developers.weixin.qq.com/miniprogram/dev/framework/'
						target='_blank'>微信小程序</a></li>
					<li><a href='https://github.com/yangjie10930' target='_blank'>Android资源</a></li>

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

					<li><em>行业领域</em></li>
					<li><a href='http://wiki.jabbercn.org/' target='_blank'>XMPP中文网</a></li>
					<li><a href='http://mina.apache.org/vysper-project/'
						target='_blank'>vysper</a></li>

				</ul>
			</div>
			<!-- /.content-wrapper -->

		</div>
		<!-- /#wrapper -->

		<!-- Scroll to Top Button
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>-->

		<script src="/webjars/jquery/3.3.1/jquery.js"></script>
		<!-- 遇到菜单dropdown时报错Unexpected token export：
	         要引用umd目录下的两个js（否则不支持export语法导致报错），
	         两个js在bootstrap.bundle.js之前引用，popper-utils.js在popper.js之前-->
		<script src="/plugins/bootstrap/js/popper-utils.js"></script>
		<script src="/plugins/bootstrap/js/popper.js"></script>
		<script src="/plugins/bootstrap/js/bootstrap.js"></script>
		<script src="/plugins/bootstrap/js/bootstrap.bundle.js"></script>

		<!-- Custom scripts for all pages-->
		<script src="/plugins/sb-admin.min.js"></script>
		<script type="text/javascript">
			$(function(){
				//如果是移动设备，将左侧菜单收起
				if( /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent) ){
					$("#sidebarToggle").click();
				}
			});
			
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
