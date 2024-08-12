<#include "/shared/layout.ftl"> <@layout>

<div>

<style>
.textarea {
	width: 465px;
	height: 435px;
}

.center-content {
	width: 79% !important;
}
.right-side{
	display:none !important;
}
</style>
	<form id="interestform" action="/save" method="post"
		enctype="multipart/form-data" accept-charset="UTF-8">
		<div class="form-horizontal">
			<#if article.id==0>
			<h2>新增文章</h2>
			<#else>
			<h2>编辑文章</h2></#if>

			<hr />
			<span id="errmsg"></span> <input type="hidden" name="id"
				id="articleId" value="${article.id}" /> <input type="hidden"
				name="author" value="${article.author!}" /> <input id="hd_cat"
				type="hidden" name="categoryId" value="${article.categoryId!}" />
			<#if article.createTime?exists> <input type="hidden"
				name="createTime"
				value="${article.createTime?string('yyyy-MM-dd HH:mm:ss')}" /> </#if>

			<div class="form-group row">
				<label class="control-label col-md-1">标题</label>
				<div class="col-md-10 col-lg-7">
					<input class="form-control" type="text" name="title"
						value="${article.title!}" />
				</div>
			</div>
			<div class="form-group row">
				<label class="control-label col-md-1">关键词</label>
				<div class="col-md-10 col-lg-7">
					<input class="form-control" type="text" name="keyword"
						value="${article.keyword!}" />
				</div>
			</div>
			<div class="form-group row">
				<label class="control-label col-md-1">是否专题</label>
				<div class="col-md-10 col-lg-7">
				    <input class="form-control" type="text" name="isSubject"
						value="${article.isSubject!}" />
				</div>
			</div>
			<div class="form-group row">
				<label class="control-label col-md-1" class="control-label col-md-1">类别</label>


				<div class="col-md-5 ">
					<select class="form-control" id="sel_cat"
						onchange="loadSubCategory($(this).val())"></select>
				</div>
				<div class="col-md-5">
					<select class="form-control" id="sel_subcat"
						onchange="resetCategory($(this).val())"></select>
				</div>
			</div>

			<div class="form-group row">
				<label class="control-label col-md-1" class="control-label col-md-1">内容</label>
				<div class="col-md-11">
					<textarea id="txtMsg" name="content" class="control-textarea">
									<!-- 通过后台传递给模板的数据赋值，转义过的标签&lt;dependencies&gt;会被自动反转义为<dependencies>
									         因此改为通过ajax请求接口，得到的文章内容（不会被反转义）通过js赋值给CKEditor编辑器。
									 ${article.content!}	 -->
									 
								</textarea>
				</div>
			</div>
			<div class="form-group ">
				<div style="backgroud-color: red"
					class="col-sm-offset-6 col-sm-6 row float-right">
					<button type="button" class="btn btn-default col-sm-5 offset-1"
						onclick="jumpTolist();">取 消</button>
					<button type="button" class="btn btn-primary col-sm-5 offset-1"
						onclick="ckUpdate();">保 存</button>
				</div>
			</div>
		</div>
	</form>
	<script src="/webjars/jquery/3.3.1/jquery.js"></script>
	<script src="/plugins/bootstrap/js/bootstrap.js"></script>
	<script src="/plugins/ckeditor/ckeditor.js"></script>

	<script type="text/javascript">
		$(function() {
			var editor = CKEDITOR.replace('txtMsg');
			loadCategory();

			var id = $("#articleId").val();
			setEditData(id);
		});

		/**
		 *  通过后台传递给模板的数据赋值，转义过的标签&lt;dependencies&gt;会被自动反转义为<dependencies>
		 *    因此改为通过ajax请求接口，得到的文章内容（不会被反转义）通过js赋值给CKEditor编辑器。
		 */
		function setEditData(id) {
			$.get("/getContent/" + id, function(content) {
				if (content != null) {
					console.log(content);
					CKEDITOR.instances.txtMsg.setData(content);
				} else {
					alert("获取数据失败！异常信息：");
				}
			});
		}

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

</div>

</@layout> 