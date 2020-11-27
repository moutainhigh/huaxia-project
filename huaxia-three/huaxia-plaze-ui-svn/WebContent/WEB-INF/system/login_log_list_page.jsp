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
	<div>
		<form id="frmList" method="post">
		<section class="search-layout horizontal">
			<table>
				<tr>
					<td><label>登录名称：<input type="text" id="log_account" name="account" value="${page.map.account}" style="width:160px;" /></label></td>
					<td><label>用户姓名：<input type="text" id="log_staff_name" name="staffName" value="${page.map.staffName}" style="width:160px;" /></label></td>
					<td><label>证件号码：<input type="text" id="log_cert_no" name="certNo" value="${page.map.certNo}" style="width:160px;" /></label></td>
				</tr>
				<tr>
					<td><label>操作动作：<input type="text" id="log_action" name="action" value="${page.map.action}" style="width:160px;" /></label></td>
					<td><label>开始时间：<input type="text" id="log_start_time" class="easyui-datetimebox" name="startTime" value="${page.map.startTime}" style="width:160px;" /></label></td>
					<td><label>结束时间：<input id="log_end_time" class="easyui-datetimebox" name="endTime" value="${page.map.endTime}" style="width:160px;" /></label></td>
				</tr>
			</table>
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>
		<table border="1" class="table-layout">
			<caption>登录日志列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">登录名称</th>
				<th width="15%">用户姓名</th>
				<th width="15%">证件号码</th>
				<th width="20%">日期时间</th>
				<th width="15%">终端地址</th>
				<th width="15%">操作动作</th>
			</tr>
			<c:forEach items="${records}" var="record" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td>${record.account}</td>
				<td>${record.staffName}</td>
				<td>${record.certNo}</td>
				<td>${record.crtTime}</td>
				<td>${record.ip}</td>
				<td>${record.action}</td>
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
	</div>
</body>
<script type="text/javascript">
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/system/log/login/pageList.do";
	f.submit();
}
$(function() {
	$('#btnReset').on('click', function() {
		$('#log_staff_name').val('');
		$('#log_cert_no').val('');
		$('#log_action').val('');
		$('#log_start_time').datebox('setValue', '');
		$('#log_end_time').datebox('setValue', '');
		$('#log_account').val('').focus();
	});
	$('#btnQuery').on('click', function() {
		$('#frmList').attr('src', "${basePath}/system/log/login/pageList.do");
		$('#frmList').submit();
	});
});
</script>
</html>