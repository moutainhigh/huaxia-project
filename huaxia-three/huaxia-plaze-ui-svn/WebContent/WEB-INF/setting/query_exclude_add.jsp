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
				<caption>添加例外</caption>
				<tr>
					<td><label>登录名称：</label></td>
					<td><input id="txt_account" name="account"></td>
				</tr>
				<tr>
					<td><label>用户姓名：</label></td>
					<td><input id="show_staff_name" disabled="disabled"/><input type="hidden" id="txt_staff_name" name="staffName"/></td>
				</tr>
				<tr>
					<td><label>查询开始日期：</label></td>
					<td><input id="start_query_date" class="easyui-datebox" name="excludeStartDate"></td>
				</tr>
				<tr>
					<td><label>查询结束日期：</label></td>
					<td><input id="end_query_date" class="easyui-datebox" name="excludeEndDate"></td>
				</tr>
				<tr>
					<td><label>查询时间：</label></td>
					<td><input id="txt_query_time" name="excludeTime">（格式：00:00-23:59）</td>
				</tr>
				<tr>
					<td><label>查询事由：</label></td>
					<td><input id="txt_query_cause" name="excludeCause" style="width:300px;"></td>
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
function fnEventUI() {
	$('#txt_account').on('blur', function() {
		var account = $(this).val();
		if (account != undefined && account != '') {
			$.ajax({
				url : "${basePath}/system/user/getName.do",
				type : 'post',
				data : {
					'account' : account
				},
				dataType : "json",
				success : function(response, status) {
					if (status == 'success') {
						if (response.name != undefined) {
							$('#txt_staff_name').val(response.name);
							$('#show_staff_name').val(response.name);
						} else {
							$('#txt_staff_name').val('');
							$('#show_staff_name').val('');
						}
					}
				},
				error : function(error, status) {
					alert('[例外管理] 交易请求异常,状态码[' + status + ']');
				}
			});
		}
	});
	$('#txt_query_time').on('blur', function() {
		var value = $(this).val();
		if (value == undefined || value == '') {
			return;
		}
		if (!value.match(/^\d{2}:\d{2}-\d{2}:\d{2}$/)) {
			alert('时间格式不符合要求!');
			return false;
		}
	});
}
$(function() {
	fnEventUI();
	$('#btnClose').on('click', function() {
		window.close();
	});
	$('#btnSave').on('click', function() {
		if ($('#txt_account').val() == '') {
			alert('"登录名称"不能为空!');
			$('#txt_account').focus();
			return false;
		}
		if ($('#txt_staff_name').val() == '') {
			alert('请检查"登录名称"是否正确?');
			$('#txt_account').focus();
			return false;
		}
		if ($('#start_query_date').datebox('getValue') == '' || $('#end_query_date').datebox('getValue') == '') {
			alert('"查询日期"不能为空!');
			$('#start_query_date').focus();
			return false;
		}
		if ($('#start_query_date').datebox('getValue') > $('#end_query_date').datebox('getValue')) {
			alert('开始日期不能大于结束日期!');
			$('#start_query_date').focus();
			return false;
		}
		if ($('#txt_query_time').val() == '') {
			alert('"查询时间"不能为空!');
			$('#txt_query_time').focus();
			return false;
		}
		if (!$('#txt_query_time').val().match(/^\d{2}:\d{2}-\d{2}:\d{2}$/)) {
			alert('时间格式不符合要求!');
			return false;
		}
		$.ajax({
			url : "${basePath}/setting/query/exclude/save.do",
			type : 'post',
			data : $('#frmList').serialize(),
			dataType : "json",
			success : function(response, status) {
				if (status == 'success') {
					alert(response.message);
					window.opener.refresh();
					window.close();
				}
			},
			error : function(error, status) {
				alert('[例外管理] 交易请求异常,状态码[' + status + ']');
			}
		});
	});
});
</script>
</html>