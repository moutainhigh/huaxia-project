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
<script type="text/javascript" src="${basePath}/js/framework.js"></script>
<script type="text/javascript" src="${basePath}/js/framework.util.js"></script>
</head>
<body>
	<form id="frmList" name="frmList">
		<section class="form-layout">
			<table border="1">
				<caption>添加角色</caption>
				<tr>
					<td><label>角色名称：</label></td>
					<td><input id="role_name" name="roleName"></td>
				</tr>
				<tr>
					<td><label>角色描述：</label></td>
					<td><input id="role_desc" name="roleDesc" style="width:300px;"></td>
				</tr>
			</table>
		</section>
		<section class="layout horizontal">
			<input type="button" id="btnClose" value="关闭窗口">
			<input type="button" id="btnSave" value="添加提交">
		</section>
	</form>
</body>
<script type="text/javascript">
$(function() {
	$('#btnClose').on('click', function() {
		window.close();
	});
	$('#btnSave').on('click', function() {
		$.ajax({
			url : "${basePath}/system/role/save.do",
			type : 'post',
			data : {
				'roleName' : $('#role_name').val(),
				'roleDesc' : $('#role_desc').val(),
			},
			dataType : "json",
			success : function(response, status) {
				if (status == 'success') {
					alert(response.message);
					window.opener.refresh();
					window.close();
				}
			},
			error : function(error, status) {
				alert('[角色管理] 交易请求异常,状态码[' + status + ']');
			}
		});
	});
});
</script>
</html>