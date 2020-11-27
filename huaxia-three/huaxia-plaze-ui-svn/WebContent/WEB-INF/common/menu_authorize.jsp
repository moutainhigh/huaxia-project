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
			<input type="button" class="btn-authorize" value="授权提交">
		</section>
		<table border="1" class="table-layout">
			<caption>角色授权</caption>
			<tr>
				<th width="8%">选择</th>
				<th width="52%">菜单名称</th>
				<th width="40%">所属菜单</th>
			</tr>
			<c:forEach items="${records}" var="menu" varStatus="sequence">
			<tr>
				<td>
				<c:choose>
				<c:when test="${menu.checked == '1'}"><input type="checkbox" name="id" value="${menu.id}" checked="checked" /></c:when>
				<c:otherwise><input type="checkbox" name="id" value="${menu.id}" /></c:otherwise>
				</c:choose>
				</td>
				<td style="text-align:left;">
					<c:if test="${menu.level > 1}">&nbsp;&nbsp;&nbsp;&nbsp;-&nbsp;${menu.name}</c:if>
					<c:if test="${menu.level == 1}">+&nbsp;${menu.name}</c:if>
				</td>
				<td style="text-align:left;">${menu.pname}</td>
			</tr>
			</c:forEach>
		</table>
		<input type="hidden" id="txtRoleId" name="roleId" />
		<section class="layout horizontal">
			<input type="button" class="btn-close" value="关闭窗口">
			<input type="button" class="btn-authorize" value="授权提交">
		</section>
	</form>
</body>
<script type="text/javascript">
$(function() {
	$('#txtRoleId').val(window.opener.document.getElementById('txtRoleId').value);
	$('#btnReset').on('click',function() {
		$('#txtName').val('').focus();
	});
	$('.btn-close').on('click',function() {
		window.close();
	});
	$('.btn-authorize').on('click', function() {
		var a = [];
		$('input[name="id"]:checked').each(function(i,v) {
			a[i] = v.value;
		});
		
		var id = $('#txtRoleId').val();
		$.ajax({
			url : "${basePath}/system/role/authorize.do",
			type : 'post',
			contentType : "application/json",
			data : JSON.stringify({
				'id' : id,
				'menus' : a
			}),
			dataType : "json",
			success : function(response, status) {
				if (response.result) {
					alert(response.message);
					window.close();
				} else {
					alert(response.message);
				}
			},
			error : function(error, status) {
				alert(error + '=' + status);
			}
		});
	});
});
</script>
</html>