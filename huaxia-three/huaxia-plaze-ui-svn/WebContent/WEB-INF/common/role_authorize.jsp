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
			<caption>用户授权</caption>
			<tr>
				<th width="8%">选择</th>
				<th width="52%">角色名称</th>
			</tr>

			<c:forEach items="${records}" var="role" varStatus="sequence">
		    <c:if test="${role.roleStatus=='1'}">
			  <tr>
			 	 <td>
				 <c:choose>
				 <c:when test="${role.checked == '1'}"><input type="checkbox" name="id" value="${role.roleId}" checked="checked" /></c:when>
				 <c:otherwise><input type="checkbox" name="id" value="${role.roleId}" /></c:otherwise>
				 </c:choose>
				 </td>
				 <td style="text-align:left;">${role.roleName}</td>
			  </tr>
			</c:if>
			</c:forEach>
		</table>
		<input type="hidden" id="txtUserId" name="userId" />
		<section class="layout horizontal">
			<input type="button" class="btn-close" value="关闭窗口">
			<input type="button" class="btn-authorize" value="授权提交">
		</section>
	</form>
</body>
<script type="text/javascript">
$(function() {
	$('#txtUserId').val(window.opener.document.getElementById('txtUserId').value);
	$('#btnReset').on('click',function() {
		$('#txtName').val('').focus();
	});
	$('.btn-close').on('click',function() {
		window.close();
	});
	$('.btn-authorize').on('click', function() {
		if ($('input[name="id"]:checked').length == 0) {
			alert('请选择一条记录!');
			return;
		}
		
		var a = [];
		$('input[name="id"]:checked').each(function(i,v) {
			a[i] = v.value;
		});
		
		var id = $('#txtUserId').val();
		$.ajax({
			url : "${basePath}/system/user/authorize.do",
			type : 'post',
			contentType : "application/json",
			data : JSON.stringify({
				'id' : id,
				'roles' : a
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