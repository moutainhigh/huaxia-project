<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>手机实名制查询结果</title>
<style type="text/css">
fieldset {
	margin-bottom: 10px;
}

table tr td label {
	display: block;
	width: 160px;
	text-align: right;
	font-weight: bold;
}
</style>
</head>
<body>
	<form id="unicomDetailForm">
		<fieldset>
			<legend>联通运营商查询结果</legend>
			<table class="formtable">
				<tr>
					<td><label>查询状态：</label></td>
					<td><input name="status" id="status" type="text"
						style="border: none; text-align: left;width: 100px;" /></td>
					<td><label>查询时间：</label></td>
					<td><input name="crtTime" id="crtTime" class="easyui-datebox"
						readonly="readonly" style="width: 120px;"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>被查询人信息</legend>
			<table class="formtable">
				<tr>
					<td><label>身份证号：</label></td>
					<td colspan="5"><input name="certNo" id="certNo" type="text"
						style="border: none; text-align: left; width: 300px;" /></td>
				</tr>
				
				<tr>
					<td><label>返回结果：</label></td>
					<td colspan="5"><input name="errorDesc" id="errorDesc"
						type="text" style="border: none; text-align: left; width: 164px;" /></td>
				</tr>
				<tr>
					<td><label>校验结果：</label></td>
					<td><input name="checkResult" id="checkResult" type="text"
						style="border: none; text-align: left; width: 300px;" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
<script>
	$(function() {
		var data = JSON.parse('${records}');
		if (data.status == "1") {
			data.status = "成功";
		} else {
			data.status = "失败";
		}
		if (data.checkResult == "00") {
			data.checkResult = "手机号、证件号、姓名均一致";
		} else if (data.checkResult == "01") {
			data.checkResult = "手机号一致，证件号和姓名不一致";
		} else if (data.checkResult == "02") {
			data.checkResult = "手机号和证件号一致，姓名不一致";
		} else if (data.checkResult == "03") {
			data.checkResult = "手机号和姓名一致，证件号不一致";
		} else if (data.checkResult == "04") {
			data.checkResult = "手机号一致，无身份证或姓名信息";
		} else if (data.checkResult == "20") {
			data.checkResult = "一致";
		} else if (data.checkResult == "21") {
			data.checkResult = "不一致";
		}
		$("#unicomDetailForm").form("load", data);
	})
</script>