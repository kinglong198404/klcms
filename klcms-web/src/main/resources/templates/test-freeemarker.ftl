<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<h1>FreeMarker</h1>
<div id="div01" class="myDiv" >这是显示欢迎信息 ${hello}</div>
<hr/>
<div >${hello}</div>

<hr/>

<#list users as user>
<span>序号：</span> ${user_index}
<span>用户：</span> ${user}
</#list>
</body>
</html>