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
		<input type="hidden" id="role_id" value="${records.roleId}" />
		<section class="form-layout">
			<table border="1">
				<caption>修改角色</caption>
				<tr>
					<td><label>角色名称：</label></td>
					<td><input id="role_name" name="name" value="${records.roleName}"></td>
				</tr>
				<tr>
					<td><label>角色描述：</label></td>
					<td><input id="role_desc" name="link" value="${records.roleDesc}" style="width:300px;"></td>
				</tr>
				<tr>
					<td><label>角色状态：</label></td>
					<td><select id="role_status" name="status" groupCode="ACTIVE_STATUS" selectCode="${record.roleStatus}" style="width:146px;"></select></td>
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
var o = $('#role_status');
function choose(o) {
	$('#user_team_id').val(o.id);
	$('#user_team_name').val(o.name);
}

$(function() {
	fnLoadCache();
	$('#btnClose').on('click', function() {
		window.close();
	});
	$('#btnModify').on('click', function() {
		var status = o.find('option:selected').val();
		if (status == '') {
			alert('"角色状态"为必输项!');
			o.focus();
			return false;
		}
		var name = $('#role_name').val();
		if (name == undefined || name.length == 0) {
			alert('角色名称为必输项!');
			$('#role_name').focus();
			return;
		}
		$.ajax({
			url : "${basePath}/system/role/update.do",
			type : 'post',
			data : {
				'roleId' : $('#role_id').val(),
				'roleName' : name,
				'roleDesc' : $('#role_desc').val(),
				'roleStatus' : $('#role_status').val()
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
				alert('[角色管理] 交易请求异常,状态码[' + status + ']');
			}
		});
	});
	$('#btnShowMenu').on('click', function() {
		window.open("${basePath}/common/menu/pageList.do",'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=800,height=400');
	});
});

function fnLoadCache() {
	$.ajax({
		url : "${basePath}/system/loadDictByKey.do",
		type : 'post',
		data : {
			'groupCode' : o.attr('groupCode')
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				o.fnLoadComponent(response);
				$('#role_status option[value="' + o.attr('selectCode') + '"]').attr('selected', true);
			}
		},
		error : function(error, status) {
			alert('"用户状态"加载异常,状态码[' + status + ']');
		}
	});
	
}
</script>
</html>