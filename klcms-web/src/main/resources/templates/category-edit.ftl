<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1,user-scalable=no">
<title>i编程网</title>
<link rel="stylesheet" href="/css/layout.css">
<link rel="stylesheet" href="/css/theme.css">
<link rel="stylesheet" href="/plugins/bootstrap/css/bootstrap.css" />
<link href="/plugins/fontawesome/css/all.min.css" rel="stylesheet" />
<style>
.textarea {
	width: 465px;
	height: 435px;
}

.center-content {
	width: 80% !important;
}
</style>


</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top">
		<div class="kl-container">
			<div class="navbar-header">
				<div class="float-left" style="text-align: center;">
					<img class="float-left" src="/images/logo_iblue.png"
						style="width: 28px; height: 28px; margin-top: 5px" /> <a href="/"
						class="navbar-brand">编程网</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<hr class="kl-container" style="margin-top: 0; margin-bottom: 0;" />

	<div class="kl-container body-content" style="margin-top: 12px">
		<div>
			<div class="left-side "></div>
			<div class="center-content">
				<form id="interestform" action="/category/save?isAdd=${isAdd}"
					method="post" enctype="multipart/form-data" accept-charset="UTF-8">
					<div class="form-horizontal">
						<#if isAdd=='true'>
						<h2>新增类别</h2>
						<#else>
						<h2>编辑类别</h2></#if>
						<hr />
						<div class="form-group row">
							<label class="control-label col-md-2">所属父类</label>
							<div class="col-md-10 col-lg-7">
								<input class="form-control" type="text"
									value="${category.parentName!}(${category.pid})"
									disabled="disabled" /> <input class="form-control"
									type="hidden" name="pid" value="${category.pid}" />
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-2">类别编号</label>
							<!-- 类别编号需在父级编号后添加两位 -->
							<div class="col-md-10 col-lg-7">
								<input class="form-control" type="text" name="id"
									value="${category.id!}" ${(isAdd== 'true')?string('','disabled')}"/>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-2">字体图标</label>
							<div class="col-md-9 col-lg-6">
								<!-- 字体图标的class -->
								<input class="form-control" type="text" name="icon" id="icon"
									value="${category.icon!}" />
							</div>
							<div>
								<i id="icon-class" class="${category.icon!}" style="font-size:1.2rem;margin-top:8px;"></i>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-2">类别名称</label>
							<div class="col-md-10 col-lg-7">
								<input class="form-control" type="text" name="name"
									value="${category.name!}" />
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-2">备注</label>
							<div class="col-md-10 col-lg-7">
								<textarea class="form-control" name="remark" rows="5" cols="10">${category.remark!}</textarea>
							</div>
						</div>

						<div class="form-group row">
							<div class="col-sm-9 row justify-content-end">
								<a type="button" class="btn btn-default col-sm-3 offset-1"
									href="/category/list.html?parentId=${category.pid}">取 消</a>
								<button type="submit" class="btn btn-primary col-sm-3 offset-1">保
									存</button>
							</div>
						</div>
					</div>
				</form>
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
	<script>
		$(function(){	
			$("#icon").change(function(){
				var icon_class = $("#icon").val();
				$("#icon-class").attr("class",icon_class);
			});
		});
	</script>
</body>

</html>


