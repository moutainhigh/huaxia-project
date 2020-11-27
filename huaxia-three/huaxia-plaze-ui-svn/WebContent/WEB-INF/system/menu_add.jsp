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
				<caption>添加菜单</caption>
				<tr>
					<td><label>菜单名称：</label></td>
					<td><input id="menu_name" name="name" style="width:300px;"></td>
				</tr>
				<tr>
					<td><label>所属菜单：</label></td>
					<td><input id="menu_parent_name" readonly="readonly" style="width:190px;" />&nbsp;<input type="button" id="btnShowMenu" value="菜单选择" />&nbsp;<input type="button" id="btnClean" value="清除" /><input type="hidden" id="menu_parent_id" name="pid" /></td>
				</tr>
				<tr>
					<td><label>菜单地址：</label></td>
					<td><input id="menu_addr" name="link" style="width:300px;"></td>
				</tr>
				<tr>
					<td><label>菜单级别：</label></td>
					<td><input id="menu_level" name="level" /></td>
				</tr>
				<tr>
					<td><label>菜单索引：</label></td>
					<td><input id="menu_index" name="index" /></td>
				</tr>
				<!--  
				<tr>
					<td><label>菜单状态：</label></td>
					<td><select id="menu_status" name="status" groupCode="RESOURCE_STATUS" default="1"></select></td>
				</tr>
				-->
			</table>
		</section>
		<section class="layout horizontal">
			<input type="button" id="btnClose" value="关闭窗口">
			<input type="button" id="btnAdd" value="添加提交">
		</section>
	</form>
</body>
<script type="text/javascript">
function choose(o) {
	$('#menu_parent_id').val(o.id);
	$('#menu_parent_name').val(o.name);
	$.ajax({
		url : "${basePath}/system/menu/queryNextById.do",
		type : 'post',
		data : {
			'id' : o.id
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				$('#menu_level').attr('readonly', 'readonly').val(response.level);
				$('#menu_index').attr('readonly', 'readonly').val(response.index);
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
}
$(function() {
	$('#menu_level').on('blur', function() {
		var v = $(this).val();
		if (parseInt(v) > 2) {
			alert('菜单层级不能超过2级!');
			$(this).val('').focus();
			return;
		}
		if (v == '1') {
			$('#menu_index').val('1');
		}
	});
	$('#btnClose').on('click', function() {
		window.close();
	});
	$('#btnClean').on('click', function() {
		$('#menu_parent_id').val('');
		$('#menu_parent_name').val('');
		$('#menu_level').val('');
		$('#menu_index').val('');
	});
	$('#btnAdd').on('click', function() {
		var addr = $('#menu_addr').val();
		if (addr == undefined || addr.length == 0) {
			var level = $('#menu_level').val();
			if (parseInt(level) == 1) {
				addr = '';
			} else {
				addr = '/404.html';
			}
		}
		$.ajax({
			url : "${basePath}/system/menu/save.do",
			type : 'post',
			data : {
				'name' : $('#menu_name').val(),
				'pid' : $('#menu_parent_id').val(),
				'link' : addr,
				'level' : $('#menu_level').val(),
				'index' : $('#menu_index').val()
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
				alert('[系统管理] 交易请求异常,状态码[' + status + ']');
			}
		});
	});
	$('#btnShowMenu').on('click', function() {
		window.open("${basePath}/common/menu/pageList.do",'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=800,height=400');
	});
});
</script>
</html>