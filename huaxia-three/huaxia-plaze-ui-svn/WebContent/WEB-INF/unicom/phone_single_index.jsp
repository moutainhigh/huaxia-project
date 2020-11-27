<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>手机实名认证查询</title>
</head>
<body>
	<form id="frmList" enctype="multipart/form-data" method="post"
		name="frmList" action="submit.do">
		<table style="border-collapse: separate; border-spacing: 10px;">
			<tr>
				<td align="right">姓名：</td>
				<td><input class="easyui-textbox" id="name" type="text"
					name="name" data-options="required:true" style="width: 165px" /></td>
			</tr>
			<tr>
				<td align="right">证件号码：</td>
				<td><input class="easyui-textbox" id="cert_no" type="text"
					name="certNo" data-options="required:true" style="width: 165px" /></td>
			</tr>
			<tr>
				<td align="right">手机号码：</td>
				<td><input class="easyui-textbox" id="cert_no" type="text"
					name="mobile" data-options="required:true" style="width: 165px" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input
					style="text-align: center; display: inline-block; width: 93px; height: 30px"
					type="button" value="提交查询" onclick="fnSubmit()" /></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	function fnSubmit() {
		var w = window.open('about:blank');
		$('#frmList').form(
				'submit',
				{
					success : function(data) {
						
						var response = eval('(' + data + ')');
						if (response.result == 1) {
							
							w.location.href = '${basePath}/unicom/phone/single/view/detail.do?trnId='	+ response.trnId;
										$('#frmList').form('clear');
						} else if (response.result == 2) {
							$.messager.alert('提示', '24小时该[姓名+证件号码]提交查询成功!',
									'info', function() {
										$('#frmList').form('clear');
									});
						} else if (response.result == 0) {
							$.messager.alert('提示', '提交查询失败!', 'warning',
									function() {
										$('#frmList').form('clear');
									});
						} else if (response.result == -1) {
							$.messager.alert('提示', '该时间段不能查询!', 'warning',
									function() {
										$('#frmList').form('clear');
									});
						} else if (response == -2) {
							$.messager.alert('提示', '该时间段内查询数量已达上限!', 'warning',
									function() {
										$('#frmList').form('clear');
									});
						}
					}
				})
	}
</script>
</html>