<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>联通地址类信息单条查询</title>
</head>
<body>
	<form id="frmList" enctype="multipart/form-data" method="post"
		name="frmList" action="submit.do">
		<table style="border-collapse: separate; border-spacing: 10px;">
			<tr>
				<td align="right">手机号码：</td>
				<td><input  id="mobile" type="text"
					name="mobile" data-options="required:true" style="width: 165px" /></td>
			</tr>
			<tr>
				<td><label>地址类别(工作/居住)：</label></td>
				<td>
					<select id="apiKey" name="apiKey"  style="width:146px;">
						<option value="">请选择</options>
						<option value="360015">工作地址</options>
						<option value="360016">居住地址</options>
						
					</select>
				</td>
			</tr>
			<tr>
				<td align="right">详细地址：</td>
				<td><input  id="address" type="text"  
					name="address" data-options="required:true"  style="width: 165px" />参考格式(省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码)</td>
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
		if ($('#mobile').val() == '') {
			alert('"手机号码"不能为空!');
			$('#mobile').focus();
			return false;
		}
		if ($('#apiKey').val() == '') {
			alert('"地质类别"不能为空!');
			$('#apiKey').focus();
			return false;
		}
		if ($('#address').val() == '') {
			alert('"详细地址"不能为空!');
			$('#address').focus();
			return false;
		}
		
		$('#frmList').form(
				'submit',
				{
					success : function(resultData) {
						var response = eval('(' + resultData + ')');
						if (response.result == 1) {
							w.location.href=
									"${basePath}/unicom/address/single/view/detail.do?trnId="
											+ response.trnId;
										$('#frmList').form('clear');
						} else if (response.result == 2) {
							$.messager.alert('提示', '24小时该[手机号+地址]提交查询成功!',
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
						} else if (response.result == -2) {
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