<#include "/shared/layout.ftl">
<@layout>
 
    <!-- <body oncontextmenu=self.event.returnValue=false > -->
	<div oncontextmenu="window.event.returnValue=false">
				<h1>
					<a href="#" title="${article.title}">${article.title}</a>
				</h1>
				<div style="font-size: 0.9em; border-bottom: 0.5px solid #e8e5d8;"
					class="padding5-bottom margin10-top margin15-bottom">
					作者：${article.authorName!} &nbsp;&nbsp;
					发表时间：${article.createTime?string('yyyy-MM-dd HH:mm')} &nbsp;&nbsp;
					
					<#if Session.loginUser?exists> 
					<a href="/edit/${article.id}.html" class="color-primary">编辑</a>
					<a href="/delete/${article.id}" class="color-warning" onclick="if(confirm('确定删除?')==false) return false;">删除</a>
					</#if>
					</div>
				<br />
				<#if article.keywordList?exists>
					<div>关键词：<#list article.keywordList as kw><strong>${kw}</strong> &nbsp;</#list></div>
				</#if>
				<br />
				<div id="divcontent" class="article">${article.content}</div>
			</div>
</@layout>