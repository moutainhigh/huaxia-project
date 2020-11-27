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
		<input type="hidden" id="user_id" name="userId" value="${record.userId}" />
		<input type="hidden" id="user_account" name="account" value="${record.account}" />
		<input type="hidden" id="user_status_before" name="beforeStatus" value="${record.status}" />
		<section class="form-layout">
			<table border="1">
				<caption>用户信息修改</caption>
				<tr>
					<td><label>登录名称：</label></td>
					<td>${record.account}</td>
				</tr>
				<tr>
					<td><label>用户工号：</label></td>
					<td><input type="text" id="user_staff_id" name="staffId" value="${record.staffId}" /></td>
				</tr>
				<tr>
					<td><label>用户姓名：</label></td>
					<td><input type="text" id="user_staff_name" name="staffName" value="${record.staffName}" /></td>
				</tr>
				<tr>
					<td><label>证件类型：</label></td>
					<td><select id="user_cert_type" name="certType" groupCode="CERT_TYPE" selectCode="${record.certType}" style="width:146px;"></select></td>
				</tr>
				<tr>
					<td><label>证件号码：</label></td>
					<td><input type="text" id="user_cert_no" name="certNo" value="${record.certNo}" /></td>
				</tr>
				<tr>
					<td><label>部门：</label></td>
					<td><input type="text" id="user_department" name="department" value="${record.department}" /></td>
				</tr>
				<tr>
					<td><label>团队：</label></td>
					<td><input type="hidden" id="user_team_id" name="team" value="${record.team}" /><input type="text" id="user_team_name" readonly="readonly" value="${record.teamName}" />&nbsp;<input type="button" id="btnShowTeam" value="团队选择" /></td>
				</tr>
				<tr>
					<td><label>岗位：</label></td>
					<td><input type="text" id="user_post" name="post" value="${record.post}" /></td>
				</tr>
				<tr>
					<td><label>手机号码：</label></td>
					<td><input type="text" id="user_mobile" name="mobile" value="${record.mobile}" /></td>
				</tr>
				<tr>
					<td><label>终端地址：</label></td>
					<td><input type="text" id="user_ip" name="ip" value="${record.ip}" /></td>
				</tr>
				<tr>
					<td><label>用户状态：</label></td>
					<td><select id="user_status" name="status" groupCode="ACCOUNT_STATUS" selectCode="${record.status}" style="width:146px;"></select></td>
				</tr>
				<tr>
					<td><label>是否有人行查询权限：</label></td>
					<td><input type="radio" name="pbocAuth" <c:if test="${record.pbocAuth == '1'}">checked="checked"</c:if> value="1" />是&nbsp;<input type="radio" name="pbocAuth" <c:if test="${record.pbocAuth == '0'}">checked="checked"</c:if> value="0" />否</label></td>
				</tr>
				<tr>
					<td><label>人行查询权限：<input type="hidden" id="user_pboc_auth_query" value="${record.pbocAuthQuery}" /></label></td>
					<td><input type="radio" name="pbocAuthQuery" value="1">贷前管理&nbsp;<input type="radio" name="pbocAuthQuery" value="2">贷后管理&nbsp;<input type="radio" name="pbocAuthQuery" checked="checked" value="3">特约商户查询</td>
				</tr>
				<tr>
					<td><label>人行查询时间：</label></td>
					<td><input type="text" id="user_pboc_auth_query_time" name="pbocAuthQueryTime" value="${record.pbocAuthQueryTime}" /></td>
				</tr>
				<tr>
					<td><label>人行单日最大查询条数：</label></td>
					<td><input type="text" id="user_pboc_query_max_count" name="pbocDayQueryMaxCount" value="${record.pbocDayQueryMaxCount}"></td>
				</tr>
				<tr>
					<td><label>人行单日最大查找条数：</label></td>
					<td><input type="text" id="user_pboc_search_max_count" name="pbocDaySearchMaxCount" value="${record.pbocDaySearchMaxCount}"></td>
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
var o = $('#user_status');
var s = $('#user_cert_type');
function choose(o) {
	$('#user_team_id').val(o.id);
	$('#user_team_name').val(o.name);
}
function fnInit() {
	if ($('input[name="pbocAuth"]:checked').val() == 0) {
		$('input[name="pbocAuthQuery"]').each(function(i,v) {
			$(this).attr('disabled','disabled');
		});
		$('#user_pboc_auth_query_time').attr('disabled','disabled');
	}
}
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
				$('#user_status option[value="' + o.attr('selectCode') + '"]').attr('selected', true);
			}
		},
		error : function(error, status) {
			alert('"用户状态"加载异常,状态码[' + status + ']');
		}
	});
	$.ajax({
		url : "${basePath}/system/loadDictByKey.do",
		type : 'post',
		data : {
			'groupCode' : s.attr('groupCode')
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				s.fnLoadComponent(response);
				$('#user_cert_type option[value="' + s.attr('selectCode') + '"]').attr('selected', true);
			}
		},
		error : function(error, status) {
			alert('"证件类型"加载异常,状态码[' + status + ']');
		}
	});
}
function fnEventUI() {
	$('input[type="radio"][name="pbocAuth"]').change(function() {
		if (this.value == 0) {
			var time = $('#user_pboc_auth_query_time').val();
			$('input[name="pbocAuthQuery"]').each(function(i,v) {
				$(this).attr('checked',false).prop('disabled',true);
			});
			$('#user_pboc_auth_query_time').val('').prop('disabled',true);
		} else {
			$('input[name="pbocAuthQuery"]').each(function(i,v) {
				$(this).prop('disabled',false);
			});
			$('#user_pboc_auth_query_time').val('9:00-17:00').prop('disabled',false);
		}
	});
	$('#btnShowTeam').on('click', function() {
		window.open("${basePath}/common/team/list.do",'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=800,height=400');
	});
}
$(function() {
	fnInit();
	fnLoadCache();
	fnEventUI();
	var array = $('#user_pboc_auth_query').val();
	for (var idx = 0; idx < array.length; idx++) {
		$('input[name="pbocAuthQuery"]').each(function(i,v) {
			if ($(this).val() == array[idx]) {
				$(this).prop('checked', true);
			}
		});
	}
	$('#btnClose').on('click', function() {
		window.close();
	});
	$('#btnModify').on('click', function() {
		var status = o.find('option:selected').val();
		if (status == '') {
			alert('请选择"用户状态"!');
			o.focus();
			return false;
		}
		var ip = $('#user_ip').val();
		if (ip == undefined || ip.length == 0) {
			alert('"终端地址"不能为空!');
			$('#user_ip').focus();
			return false;
		}
		if ($('#user_staff_name').val() == '') {
			alert('"用户姓名"不能为空!');
			$('#user_staff_name').focus();
			return false;
		}
		if ($('#user_cert_type').val() == '') {
			alert('请选择"证件类型"!');
			$('#user_cert_type').focus();
			return false;
		}
		if ($('#user_cert_no').val() == '') {
			alert('"证件号码"不能为空!');
			$('#user_cert_no').focus();
			return false;
		}
		if ($('#user_mobile').val() == '') {
			alert('"手机号码"不能为空!');
			$('#user_mobile').focus();
			return false;
		}	
		if ($('#user_department').val() == '') {
			alert('"部门"不能为空!');
			$('#user_department').focus();
			return false;
		}
		if ($('#user_post').val() == '') {
			alert('"岗位"不能为空!');
			$('#user_post').focus();
			return false;
		}
		if ($('input[name="pbocAuth"]:checked').val() == '1') {
			if ($('input[name="pbocAuthQuery"]:checked').val()==null) {
				alert('"人行查询权限"不能为空!');
				return false;
			}
			if ($('#user_pboc_auth_query_time').val() == '') {
				alert('"人行查询时间"不能为空!');
				$('#user_pboc_auth_query_time').focus();
				return false;
		    }	
		}
		$.ajax({
			url : "${basePath}/system/user/update.do",
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
				alert('[用户管理] 交易请求异常,状态码[' + status + ']');
			}
		});
	});
});
</script>
</html>