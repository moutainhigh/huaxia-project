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
	<form id="frmSettingQueryA">
		<section class="table-layout">
			<fieldset>
				<legend>人行单条实时查询阻断设置<input type="hidden" id="q_a_config_id" value="${queryConfigIdOfA}" /><input type="hidden" id="q_a_config_name" value="${queryConfigNameOfA}" /></legend>
				<table>
					<tr>
						<td><label>最大查询条数：</label></td>
						<td><input type="text" id="q_a_max_count" name="maxCount" /></td>
						<td><label>查询间隔时间：</label></td>
						<td><input id="q_a_interval_time" name="intervalTime" style="width:146px" />（单位：分钟）</td>
						<td><input id="btnQuerySaveOfA" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
	<form id="frmSettingSearchA">
		<section class="table-layout">
			<fieldset>
				<legend>人行单条实时查找阻断设置<input type="hidden" id="s_a_config_id" value="${searchConfigIdOfA}" /><input type="hidden" id="s_a_config_name" value="${searchConfigNameOfA}" /></legend>
				<table>
					<tr>
						<td><label>最大查询条数：</label></td>
						<td><input type="text" id="s_a_max_count" name="maxCount" /></td>
						<td><label>查询间隔时间：</label></td>
						<td><input id="s_a_interval_time" name="intervalTime" style="width:146px" />（单位：分钟）</td>
						<td><input id="btnSearchSaveOfA" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
	<form id="frmSettingQueryB">
		<section class="table-layout">
			<fieldset>
				<legend>相同证件号码<strong id="q_b_hour"></strong>查询阻断设置<input type="hidden" id="q_b_config_id" value="${queryConfigIdOfB}" /><input type="hidden" id="q_b_config_name" value="${queryConfigNameOfB}" /></legend>
				<table>
					<tr>
						<td><label>最大查询条数：</label></td>
						<td><input type="text" id="q_b_max_count" name="maxCount" /></td>
						<td><label>查询间隔时间：</label></td>
						<td><input id="q_b_interval_time" name="intervalTime" value="24" disabled="disabled" style="width:146px" />（单位：小时）</td>
						<td><input id="btnQuerySaveOfB" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
	<form id="frmSettingSearchB">
		<section class="table-layout">
			<fieldset>
				<legend>相同证件号码<strong id="s_b_hour"></strong>查找阻断设置<input type="hidden" id="s_b_config_id" value="${searchConfigIdOfB}" /><input type="hidden" id="s_b_config_name" value="${searchConfigNameOfB}" /></legend>
				<table>
					<tr>
						<td><label>最大查询条数：</label></td>
						<td><input type="text" id="s_b_max_count" name="maxCount" /></td>
						<td><label>查询间隔时间：</label></td>
						<td><input id="s_b_interval_time" name="intervalTime" value="24" disabled="disabled" style="width:146px" />（单位：小时）</td>
						<td><input id="btnSearchSaveOfB" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
</body>
<script type="text/javascript">
function fnLoadForm() {
	$.ajax({
		url : "${basePath}/setting/prevent/query/a.do",
		type : 'post',
		data : {'id' : $('#q_a_config_id').val()},
		dataType : "json",
		success : function(response, status) {
			if (response.result) {
				if (response.record != null) {
					$('#q_a_max_count').val(response.record.maxCount);
					$('#q_a_interval_time').val(response.record.intervalTime);
				}
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
	$.ajax({
		url : "${basePath}/setting/prevent/query/a.do",
		type : 'post',
		data : {'id' : $('#s_a_config_id').val()},
		dataType : "json",
		success : function(response, status) {
			if (response.result) {
				if (response.record != null) {
					$('#s_a_max_count').val(response.record.maxCount);
					$('#s_a_interval_time').val(response.record.intervalTime);
				}
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
	$.ajax({
		url : "${basePath}/setting/prevent/query/b.do",
		type : 'post',
		data : {'id' : $('#q_b_config_id').val()},
		dataType : "json",
		success : function(response, status) {
			if (response.result) {
				if (response.record != null) {
					$('#q_b_max_count').val(response.record.maxCount);
					$('#q_b_interval_time').val(response.record.intervalTime);
					$('#q_b_hour').text(response.record.intervalTime + '小时');
				}
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
	$.ajax({
		url : "${basePath}/setting/prevent/query/b.do",
		type : 'post',
		data : {'id' : $('#s_b_config_id').val()},
		dataType : "json",
		success : function(response, status) {
			if (response.result) {
				if (response.record != null) {
					$('#s_b_max_count').val(response.record.maxCount);
					$('#s_b_interval_time').val(response.record.intervalTime);
					$('#s_b_hour').text(response.record.intervalTime + '小时');
				}
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
}
function fnSave(id,name,count,interval,msg) {
	$.ajax({
		url : "${basePath}/setting/prevent/save.do",
		type : 'post',
		contentType : 'application/json;charset=utf-8',
		data : JSON.stringify({
			'id' : id,
			'name' : name,
			'maxCount' : count,
			'intervalTime' : interval
		}),
		dataType : "json",
		success : function(response, status) {
			if (response.result) {
				alert(msg + '设置保存成功!');
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
}
$(function() {
	/** 人行阻断配置 */
	fnLoadForm();
	/** 人行单条实时查询阻断设置保存 */
	$('#btnQuerySaveOfA').on('click', function() {
		var msg = '人行单条实时查询阻断';
		var count = $('#q_a_max_count').val();
		if (count == undefined || count == '') {
			alert(msg + '"最大查询条数"为必输项!');
			return false;
		}
		var interval = $('#q_a_interval_time').val();
		if (interval == undefined || interval == '') {
			alert(msg + '"查询间隔时间"为必输项!');
			return false;
		}
		var id = $('#q_a_config_id').val();
		var name = $('#q_a_config_name').val();
		fnSave(id,name,count,interval,msg);
	});
	/** 人行单条实时查找阻断设置保存 */
	$('#btnSearchSaveOfA').on('click', function() {
		var msg = '人行单条实时查找阻断';
		var count = $('#s_a_max_count').val();
		if (count == undefined || count == '') {
			alert('人行单条实时查找阻断"最大查询条数"为必输项!');
			return false;
		}
		var interval = $('#s_a_interval_time').val();
		if (interval == undefined || interval == '') {
			alert('人行单条实时查找阻断"查询间隔时间"为必输项!');
			return false;
		}
		var id = $('#s_a_config_id').val();
		var name = $('#s_a_config_name').val();
		fnSave(id,name,count,interval,msg);
	});
	/** 相同证件号码N小时查询阻断设置保存 */
	$('#btnQuerySaveOfB').on('click', function() {
		var msg = '相同证件号码查询阻断';
		var count = $('#q_b_max_count').val();
		if (count == undefined || count == '') {
			alert(msg + '"最大查询条数"为必输项!');
			return false;
		}
		var interval = $('#q_b_interval_time').val();
		if (interval == undefined || interval == '') {
			alert(msg + '"查询间隔时间"为必输项!');
			return false;
		}
		var id = $('#q_b_config_id').val();
		var name = $('#q_b_config_name').val();
		fnSave(id,name,count,interval,msg);
		$('#q_b_hour').text(interval + '小时');
	});
	/** 相同证件号码N小时查找阻断设置保存 */
	$('#btnSearchSaveOfB').on('click', function() {
		var msg = '相同证件号码查找阻断';
		var count = $('#s_b_max_count').val();
		if (count == undefined || count == '') {
			alert(msg + '"最大查询条数"为必输项!');
			return false;
		}
		var interval = $('#s_b_interval_time').val();
		if (interval == undefined || interval == '') {
			alert(msg + '"查询间隔时间"为必输项!');
			return false;
		}
		var id = $('#s_b_config_id').val();
		var name = $('#s_b_config_name').val();
		fnSave(id,name,count,interval,msg);
		$('#s_b_hour').text(interval + '小时');
	});
});
</script>
</html>