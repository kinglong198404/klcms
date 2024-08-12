<#include "/shared/layout.ftl">
<@layout>
<div>
				<div style="margin: 5px 0">
					<div class="float-left" style="width: 45%; min-width: 276px; margin-left: 10px;">
						<div class="input-group">
							<input type="text" class="form-control" id="txt_kw"
					value="${keyword!}" placeholder="输入关键字检索" aria-label="Search"
					aria-describedby="basic-addon2" onkeydown="onEnter(event);">
							<div class="input-group-append">
								<button id="link_search"
						url="/index.html?category=${categoryId!}"
						onclick="searchArticles()" class="btn btn-primary" type="button">
									<i class="fas fa-search"></i>
								</button>
							</div>
						</div>
						<div class="form-check" class="float-left">
						<#if getSubject>
						      <input class="form-check-input" checked type="checkbox" onclick="checkboxOnclick(this)"
						  id="ck_subject" style="width:18px;height:18px;margin-top:8px" />
						  <#else>
						   <input class="form-check-input" type="checkbox" id="ck_subject" onclick="checkboxOnclick(this)"
						  style="width:18px;height:18px;margin-top:8px" />
                        </#if>
						
						  <label class="form-check-label" for="ck_subject" style="font-size:18px;margin-left:6px">专题文章</label>
						</div>
						<input id="category" type="hidden" value="${categoryId!}"
				class="text-info border-bottom " />
					</div>

					<div class="float-right ">
						<#if Session.loginUser?exists> <a id="link_add"
				href="/add/${categoryId!}" class="btn btn-primary btn-sm">新增文章</a>
						</#if>
					</div>
					<div class="clearfix"></div>
				</div>
				<ul id="ul_main">
					<#list articles as art>
					<li>
						<div class="article-item">
							<h3 class="article-title">
								<a href="./item/${art.id}.html" title="${art.title}">${art.title}</a>
							</h3>
							<!-- freemarker模板使用java.util.Date 报错 Can't convert the date-like value to string because it isn't 
	 							 known if it's a date (no time part), time or date-time value		
	 							  解决方案：前端使用<div >${art.createTime?string('yyyy-MM-dd HH:mm')}</div> -->
							<!--<div >${art.createTime?datetime}</div>-->
							<div>作者： ${art.authorName!} &nbsp; &nbsp; &nbsp; 发表时间：
								${art.createTime?string('yyyy-MM-dd HH:mm')}</div>
							
							<#if art.keywordList?exists>
							<div>关键词：<#list art.keywordList as kw><strong>${kw}</strong> &nbsp;</#list></div>
							</#if>
							
							<#if art.imgSrc?exists>
							<div><img style="max-width:100%;max-height:500px;margin-top:12px;margin-bottom:12px" src="${art.imgSrc}"></div>
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
					href="/index.html?category=${categoryId!}&keyword=${encodeKeyword!}&isSubject=${subjectValue}&page=${page-1}">上一页</a></li>

							<#if (page lte 3 && (page+3) gt pageCount)> <#list
					1..pageCount as i>
							<li
					class="${(i == page)?string('page-item active', 'page-item')}"><a
					class="page-link"
					href="/index.html?category=${categoryId!}&keyword=${encodeKeyword!}&isSubject=${subjectValue}&page=${i}">${i}</a></li>
							</#list> <#elseif (page lte 3)> <#list 1.. (page+3) as i>
							<li
					class="${(i == page)?string('page-item active', 'page-item')}"><a
					class="page-link"
					href="/index.html?category=${categoryId!}&keyword=${encodeKeyword!}&isSubject=${subjectValue}&page=${i}">${i}</a></li>
							</#list> <#elseif (page+3) gt pageCount> <#list
					(page-3)..pageCount as i>
							<li
					class="${(i == page)?string('page-item active', 'page-item')}"><a
					class="page-link"
					href="/index.html?category=${categoryId!}&keyword=${encodeKeyword!}&isSubject=${subjectValue}&page=${i}">${i}</a></li>
							</#list> <#else> <#list (page-3)..(page+3) as i>
							<li
					class="${(i == page)?string('page-item active', 'page-item')}"><a
					class="page-link"
					href="/index.html?category=${categoryId!}&keyword=${encodeKeyword!}&isSubject=${subjectValue}&page=${i}">${i}</a></li>
							</#list></#if>

							<li class="page-item disabled"><span class="page-link">${page}/${pageCount}</span></li>
							<li
					class="${(pageCount == page)?string('page-item disabled', 'page-item')}"><a
					class="page-link"
					href="/index.html?category=${categoryId!}&keyword=${encodeKeyword!}&isSubject=${subjectValue}&page=${page+1}">下一页</a></li>
						</ul>
					</nav>

				</div>
				</#if>
			</div>
</@layout>


			