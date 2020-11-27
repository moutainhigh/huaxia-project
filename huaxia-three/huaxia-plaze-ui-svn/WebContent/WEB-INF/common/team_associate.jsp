<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>第三方查询模块</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/normalize-layout-form.css"></link>
<link rel="stylesheet" type="text/css" href="${basePath}/css/common/page.css">
<script type="text/javascript" src="${basePath}/js/framework.js"></script>
<script type="text/javascript" src="${basePath}/js/framework.util.js"></script>
</head>
<body>
	<form id="frmList" method="post">
		<section class="layout horizontal">
			<input type="button" class="btn-close" value="关闭窗口">
			<input type="button" class="btn-associate" value="关联提交">
		</section>
		<table border="1" class="table-layout">
			<caption>团队关联</caption>
			<tr>
				<th width="8%">选择</th>
				<th width="52%">团队名称</th>
			</tr>
			<c:forEach items="${records}" var="record" varStatus="sequence">
			<c:if test="${record.teamStatus=='1'}">
			<tr>
				<td>
				<c:choose>
				<c:when test="${record.checked == '1'}"><input type="radio" name="id" value="${record.teamId}" title="${record.teamName}" checked="checked" /></c:when>
				<c:otherwise><input type="radio" name="id" value="${record.teamId}" title="${record.teamName}" /></c:otherwise>
				</c:choose>
				</td>
				<td style="text-align:left;">${record.teamName}</td>
			</tr>
			</c:if>
			</c:forEach>
		</table>
		<section class="layout horizontal">
			<input type="button" class="btn-close" value="关闭窗口">
			<input type="button" class="btn-associate" value="关联提交">
		</section>
	</form>
</body>
<script type="text/javascript">
$(function() {
	$('#btnReset').on('click',function() {
		$('#txtName').val('').focus();
	});
	$('.btn-close').on('click',function() {
		window.close();
	});
	$('.btn-associate').on('click', function() {
		if ($('input[name="id"]:checked').length == 0) {
			alert('请选择一条记录!');
			return;
		}
		
		var id = $('input[name="id"]:checked').val();
		var name = $('input[name="id"]:checked').attr('title');
		window.opener.choose({'id':id,'name':name});
		window.close();
	});
});
</script>
</html>