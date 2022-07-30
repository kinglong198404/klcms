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
						style="width: 27px; height: 27px; margin-top: 8px" /> <a href="/"
						class="navbar-brand">编程网</a>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
	<hr class="kl-container" style="margin-top: 0; margin-bottom: 0;" />

	<div class="kl-container body-content" style="margin-top: 12px">
		<div>
			<div class="left-side ">
				<ul>
					<li class="sub-menu"><a href="/admin/setting.html">系统设置</a></li>
					<li class="sub-menu"><a href="/category/list.html"
						class="menu-selected">类别管理</a></li>
				</ul>
			</div>
			<div class="center-content">
				<div class="card">
					<div class="card-header">
						<span class="header-title">${parent.name}</span>
						<div class="header-btn" >
							<a class="btn btn-info" href="/category/add/${parent.id}">新增类别
							</a>
						</div>
					</div>
					<div class="card-body">
						<!--列表-->
						<table class="table table-hover table-bordered table-striped">
							<thead>
								<tr>
									<th>编号</th>
									<th>名称</th>
									<th>备注</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<#list list as category>
								<tr>
									<td class="text-center">${category.id}</td>
									<td class="text-center">${category.name}</td>
									<td class="text-center" style="max-width:300px">${category.remark!}</td>
									<td class="text-center" style="max-width:160px">
									<a class="btn btn-info"
										href="/category/list.html?parentId=${category.id}">管理 </a> 
									<a class="btn btn-info"
										href="/category/edit/${category.id}.html">修改 </a> 
										<a
										class="btn btn-danger"
										href="/category/delete/${parent.id}/${category.id}"
										onclick="if(confirm('确定删除?')==false)return false;">删除
											</a></td>
								</tr>
								</#list>

							</tbody>
						</table>
					</div>
				</div>
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
	<script src="/plugins/ckeditor/ckeditor.js"></script>

	<script type="text/javascript">
		$(function() {
			var editor = CKEDITOR.replace('txtMsg');
			loadCategory();
		});

		//处理CKEDITOR的值
		function ckUpdate() {
			var editor = CKEDITOR.instances.txtMsg;
			editor.updateElement();
			if (editor.mode == 'wysiwyg') {
				//可在提交前进行自定义的处理
			}
			$("#interestform").submit();//用来激发上面的submit绑定事件。
		}

		//获取一级类别数据
		loadCategory = function() {
			$.get("/category/rootlist", function(list) {
				if (list != null) {
					$("#sel_cat").children().remove();
					for (i = 0; i < list.length; i++) {
						var item = list[i];
						$(
								'<option value="' + item.id + '">' + item.name
										+ '</option>').appendTo($("#sel_cat"));
					}

					var sel_cat = $("#hd_cat").val(); //取当前文档所属类别的前4位
					if (sel_cat.length > 4) {
						sel_cat = sel_cat.substr(0, 4);
					}
					$("#sel_cat").val(sel_cat);
					loadSubCategory($("#sel_cat").val());
				} else {
					alert("获取数据失败！异常信息：");
				}
			});
		}

		//获取二级类别数据
		loadSubCategory = function(parentId) {
			$.get("/category/sublist/" + parentId, function(list) {
				if (list != null) {
					var category = $("#hd_cat").val();
					var isInit = false;//是否已初始化
					$("#sel_subcat").children().remove();
					for (i = 0; i < list.length; i++) {
						var item = list[i];
						if (item.id == category) {
							isInit = true;
						}
						$(
								'<option value="' + item.id + '">' + item.name
										+ '</option>').appendTo(
								$("#sel_subcat"));
					}

					if (isInit) {
						//按当前文档所属类别初始化二级类别
						$("#sel_subcat").val(category)
					} else {
						//大类别已变化，使用二级类别中默认的选中项给 文档所属类别赋值
						resetCategory($("#sel_subcat").val());
					}

				} else {
					alert("获取数据失败！异常信息：");
				}
			});
		}

		//重设文档所属类别的值
		resetCategory = function(categoryId) {
			$("#hd_cat").val(categoryId);
		}

		jumpTolist = function() {
			window.location.href = "/index.html";
		}
	</script>

</body>

</html>


