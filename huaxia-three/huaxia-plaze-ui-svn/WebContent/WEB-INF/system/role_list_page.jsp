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
			<label>角色名称：<input type="text" id="txtRoleName" name="roleName" value="${page.map.roleName}" /></label>
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>
		<table border="1" class="table-layout">
			<caption>角色列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="20%">角色名称</th>
				<th width="40%">角色描述</th>
				<th width="10%">角色状态</th>
				<th width="25%">操作</th>
			</tr>
			<c:forEach items="${records}" var="record" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td style="text-align:left;">${record.roleName}</td>
				<td style="text-align:left;">${record.roleDesc}</td>
				<td>
				<c:if test="${record.roleStatus=='0'}">禁用</c:if>
				<c:if test="${record.roleStatus=='1'}">启动</c:if>
				</td>
				<td><a href="javascript:void();" onclick='fnModifyRole("${record.roleId}")'>修改</a>
				<c:if test="${record.roleStatus == '1'}">&nbsp;|&nbsp;
				<a href="javascript:void();" onclick='fnRemoveRole("${record.roleId}")'>删除</a>
				</c:if>
				&nbsp;|&nbsp;<a href="javascript:void();" onclick='fnRoleAuth("${record.roleId}")'>授权</a></td>
			</tr>
			</c:forEach>
		</table>
		<!-- 分页代码 & 开始 -->
		<c:if test="${page.totalCount > 0}">
			<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}" totalCount="${page.totalCount}" />
		</c:if>
		<input type="hidden" id="pageNo" name="pageNo" />
		<input type="hidden" id="pageSize" name="pageSize" />
		<input type="hidden" id="txtRoleId" name="roleId" />
		<!-- 分页代码 & 结束 -->
		</form>
		<form id="frmSave" method="post">
			<section class="layout horizontal">
				<input type="button" id="btnAdd" value="添加角色">
			</section>
		</form>
	</div>
</body>
<script type="text/javascript">
function refresh() {
	var f = $('#frmList');
	f.action = "${basePath}/system/role/pageList.do";
	f.submit();
}
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/system/role/pageList.do";
	f.submit();
}
function fnModifyRole(id) {
	window.open("${basePath}/system/role/get.do?id=" + id,'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
}
function fnRemoveRole(id) {
	if (confirm('确定删除该记录?')) {
		$.ajax({
			url : "${basePath}/system/role/remove.do",
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
function fnRoleAuth(id) {
	$('#txtRoleId').val(id);
	window.open("${basePath}/common/menu/list.do?id=" + id,'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
}
$(function() {
	$('#btnReset').on('click', function() {
		$('#txtRoleName').val('').focus();
	});
	$('#btnQuery').on('click', function() {
		$('#pageNo').val('1');
	});
	$('#btnAdd').on('click', function() {
		window.open("${basePath}/system/role/add.do",'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
	});
});
</script>
</html>