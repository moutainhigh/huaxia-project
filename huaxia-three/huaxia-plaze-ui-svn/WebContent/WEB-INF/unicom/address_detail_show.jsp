<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>联通地质类信息查询结果</title>
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
			<legend>联通地址类信息查询结果</legend>
			<table class="formtable">
				<tr>
					<td><label>查询状态：</label></td>
					<td><input name="code" id="code" type="text"
						style="border: none; text-align: left;width: 100px;" /></td>
					<td><label>查询时间：</label></td>
					<td><input name="crtTime" id="crtTime" class="easyui-datebox"
						readonly="readonly" style="width: 120px;"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>被查询信息</legend>
			<table class="formtable">
				<tr>
					<td><label>手机号：</label></td>
					<td colspan="5"><input name="mobile" id=""mobile"" type="text"
						style="border: none; text-align: left; width: 300px;" /></td>
				</tr>
				<tr>
					<td><label>校验结果：</label></td>
					<td><input name="checkDesc" id="checkDesc" type="text"
						style="border: none; text-align: left; width: 350px;" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
<script>
	$(function() {
		var data = JSON.parse('${records}');
		if ("00"==data.code || "14" ==data.code || "16"==data.code) {
			data.code = "成功";
		} else {
			data.code = "失败";
		}
		$("#unicomDetailForm").form("load", data);
	})
</script>