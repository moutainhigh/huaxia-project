<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>第三方查询模块</title>
<script type="text/javascript" src="${basePath}/js/framework.util.js"></script>
<style type="text/css">
.page {
	line-height: 3em;
}
.page-button-disable {
	padding: 2px 5px;
	border: 1px solid #95B8E7;
	color: #6E6E6E;
	border-color: buttonface;
}
.page-button {
	padding: 2px 5px;
	border: 1px solid #95B8E7;
}

.page-button-color {
	color: #FF0000;
}

.search-layout {
	padding: 0px 10px 10px 10px;
}
.search-layout table {
	margin-bottom: 10px;
}
.search-layout label {
	font: normal 12px Arial;
}
.search-layout table {
	margin: 0px auto 10px;
}
</style>
</head>
<body>
	<form id="frmSetting">
		<section class="table-layout">
			<fieldset>
				<legend>用户登录限制设置<input type="hidden" id="setting_config_id" value="${queryConfigId}" /><input type="hidden" id="setting_config_name" value="${queryConfigName}" /></legend>
				<table>
					<tr>
						<td><label>最大登录次数：</label></td>
						<td><input type="text" id="setting_max_count" name="maxCount" /></td>
						<td><label>登录限制时间：</label></td>
						<td><input id="setting_interval_time" name="intervalTime" style="width:146px" />（单位：小时）</td>
						<td><input id="btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
</body>
<script type="text/javascript">
function fnLoadForm() {
	$.ajax({
		url : "${basePath}/setting/login/query.do",
		type : 'post',
		data : {'id' : $('#setting_config_id').val()},
		dataType : "json",
		success : function(response, status) {
			if (response.result) {
				if (response.record != null) {
					$('#setting_max_count').val(response.record.maxCount);
					$('#setting_interval_time').val(response.record.intervalTime);
				}
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
}
$(function() {
	fnLoadForm();
	$('#btnSave').on('click', function() {
		var count = $('#setting_max_count').val();
		if (count == undefined || count == '') {
			alert('"最大登录条数"为必输项!');
			return false;
		}
		var interval = $('#setting_interval_time').val();
		if (interval == undefined || interval == '') {
			alert('"登录限制时间"为必输项!');
			return false;
		}
		$.ajax({
			url : "${basePath}/setting/login/save.do",
			type : 'post',
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify({
				'id' : $('#setting_config_id').val(),
				'name' : $('#setting_config_name').val(),
				'maxCount' : count,
				'intervalTime' : interval
			}),
			dataType : "json",
			success : function(response, status) {
				if (response.result) {
					alert('用户登录限制设置保存成功!');
				}
			},
			error : function(error, status) {
				alert('交易请求异常,状态码[' + status + ']');
			}
		});
	});
});
</script>
</html>