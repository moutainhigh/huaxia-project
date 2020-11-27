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
</style>
</head>
<body>
	<div>
		<form id="frmList" method="post">
		<section class="layout horizontal">
			<label>登录名称：<input type="text" id="user_account" name="account" value="${page.map.account}" /></label>
			<label>用户姓名：<input type="text" id="user_staff_name" name="staffName" value="${page.map.staffName}" /></label>
			<label>状态：<select id="user_status" name="status" selectCode="${page.map.status}" groupCode="ACCOUNT_STATUS"></select></label>
			<label>人行查询权限：
			<select id="user_auth" name="pbocAuth">
			<option value="">请选择</option>
			<option value="1">是</option>
			<option value="0">否</option>
			</select></label>
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>
		<table border="1" class="table-layout">
			<caption>用户列表</caption>
			<tr>
				<th width="8%">员工号</th>
				<th width="10%">用户姓名</th>
				<th width="15%">证件号码</th>
				<th width="15%">部门名称</th>
				<th width="15%">团队名称</th>
				<th width="5%">状态</th>
				<th width="15%">是否有人行查询权限</th>
				<th width="15%">操作</th>
			</tr>
			<c:forEach items="${records}" var="user" varStatus="sequence">
			<tr>
				<td>${user.staffId}</td>
				<td style="text-align:left;">${user.staffName}</td>
				<td style="text-align:left;">${user.certNo}</td>
				<td style="text-align:left;">${user.department}</td>
				<td style="text-align:left;">${user.teamName}</td>
				<td>${user.statusName}</td>
				<td>
				<c:choose>
					<c:when test="${user.pbocAuth == '1'}">是</c:when>
					<c:when test="${user.pbocAuth == '0'}">否</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				</td>
				<td>
					<a href="javascript:void();" onclick='fnModifyUser("${user.userId}")'>修改</a>&nbsp;|&nbsp;
					<c:if test="${user.status == '1'}">
					<a href="javascript:void();" onclick='fnRemoveUser("${user.userId}")'>删除</a>&nbsp;|&nbsp;
					</c:if>
					<a href="javascript:void();" onclick='fnUserAuth("${user.userId}")'>授权</a>&nbsp;|&nbsp;
					<a href="javascript:void();" onclick='fnReset("${user.userId}")'>重置密码</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<!-- 分页代码 & 开始 -->
		<c:if test="${page.totalCount > 0}">
			<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}" totalCount="${page.totalCount}" />
		</c:if>
		<input type="hidden" id="pageNo" name="pageNo" />
		<input type="hidden" id="pageSize" name="pageSize" />
		<input type="hidden" id="txtUserId" name="userId" />
		<!-- 分页代码 & 结束 -->
		</form>
		<form id="frmSave" method="post">
			<section class="layout horizontal">
				<input type="button" id="btnAdd" value="添加用户">
			</section>
		</form>
	</div>
</body>
<script type="text/javascript">
function refresh() {
	var f = $('#frmList');
	f.action = "${basePath}/system/user/pageList.do";
	f.submit();
}
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/system/user/pageList.do";
	f.submit();
}
function fnModifyUser(id) {
	window.open("${basePath}/system/user/get.do?id=" + id,'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
}
function fnRemoveUser(id) {
	if (confirm('确定删除该记录?')) {
		$.ajax({
			url : "${basePath}/system/user/remove.do",
			type : 'post',
			data : {
				'id' : id,
			},
			dataType : "json",
			success : function(response, status) {
				if (response.result) {
					alert(response.message);
					refresh();
				} else {
					alert(response.message);
				}
			},
			error : function(error, status) {
				alert(error + '=' + status);
			}
		});
	}
}
function fnReset(id) {
	if (confirm('是否重置密码?')) {
		$.ajax({
			url : "${basePath}/system/user/reset.do",
			type : 'post',
			data : {
				'id' : id,
			},
			dataType : "json",
			success : function(response, status) {
				if (response.result) {
					alert(response.message);
					refresh();
				} else {
					alert(response.message);
				}
			},
			error : function(error, status) {
				alert(error + '=' + status);
			}
		});
	}
}
function fnUserAuth(id) {
	$('#txtUserId').val(id);
	window.open('${basePath}/common/role/list.do?id=' + id,'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
}

$(function() {
	$("#user_auth").val("${page.map.pbocAuth}");
	var o = $('#user_status');
	var key = o.attr('groupCode'), value = o.attr('selectCode');
	$.ajax({
		url : '${basePath}/system/loadDictByKey.do',
		type : 'post',
		data : {
			'groupCode' : key
		},
		dataType : 'json',
		success : function(response, status) {
			if (status == 'success') {
				o.fnLoadComponent(response);
				$('#user_status option[value="' + value + '"]').attr('selected', true);
			}
		},
		error : function(error, status) {
			alert('"用户状态"加载异常,状态码[' + status + ']');
		}
	});	
	$('#btnReset').on('click', function() {
		$('#user_staff_name').val('');
		$('#user_status option[value=""]').attr('selected', true);
		$('#user_account').val('').focus();
	});
	$('#btnQuery').on('click', function() {
		$('#pageNo').val('1');
	});
	$('#btnAdd').on('click', function() {
		window.open('${basePath}/system/user/add.do','_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
	});
});
</script>
</html>