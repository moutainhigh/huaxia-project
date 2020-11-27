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
		<input type="hidden" id="uuid" name="uuid" value="${data.uuid}" />
		<section class="form-layout">
			<table border="1">
				<caption>批量定时任务修改</caption>
				<tr>
					<td><label>任务名称：</label></td>
					<td>${data.jobName}</td>
				</tr>
				<tr>
					<td><label>运行时间：</label></td>
					<td><input type="text" id="runTime" name="runTime" value="${data.runTime}" /></td>
				</tr>
				
				
				<tr>
					<td><label>状态：</label></td>
					<td><input type="radio" name="turn" <c:if test="${data.turn == '1'}">checked="checked"</c:if> value="1" />开启&nbsp;<input type="radio" name="turn" <c:if test="${data.turn == '2'}">checked="checked"</c:if> value="2" />关闭</label></td>
				</tr>
				
				<tr>
					<td><label>任务简称：</label></td>
					<td>${data.jobJc}</td>
				</tr>
			</table>
		</section>
		<section class="layout horizontal">
			<input type="button" id="btnClose" value="关闭窗口">
			<input type="button" id="btnModify" value="修改提交">
		</section>
	</form>
</body>
<script type="text/javascript">


$(function() {
	$('#btnClose').on('click', function() {
		window.close();
	});
	$('#btnModify').on('click', function() {
		
		
		if ($('#runTime').val() == '') {
			alert('"运行时间"不能为空!');
			$('#runTime').focus();
			return false;
		}
		if ($('#turn').val() == '') {
			alert('请选择"状态"!');
			$('#turn').focus();
			return false;
		}
		$.ajax({
			url : "${basePath}/batch/quartz/update.do",
			type : 'post',
			data : $('#frmList').serialize(),
			dataType : "json",
			success : function(response, status) {
				if (status == 'success') {
					alert(response.message);
					if (response.result) {
						window.opener.refresh();
						window.close();
					}
				}
			},
			error : function(error, status) {
				alert('[更新批量定时任务] 交易请求异常,状态码[' + status + ']');
			}
		});
	});
});
</script>
</html>