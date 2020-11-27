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
		<form id="frmList" name="frmList" method="post">
		<section class="search-layout horizontal">
			<table>
				<tr>
					<td align="right"><label>用户名：<input type="text" id="log_account1" name="account1" value="${page.map.account1}" style="width:160px;" /></label></td>
					<td align="right"><label>姓名：<input type="text" id="log_user_name" name="userName" value="${page.map.userName}" style="width:160px;" /></label></td>
					<td align="right"><label>变更前状态：<select id="log_before_status" name="before" groupCode="ACCOUNT_STATUS" selectCode="${page.map.operateBefore}" style="width:160px;"></select></label></td>
				</tr>
				<tr>
					<td align="right"><label>变更后状态：<select id="log_after_status" name="after" groupCode="ACCOUNT_STATUS" selectCode="${page.map.operateAfter}" style="width:160px;"></select></label></td>
					<td align="right"><label>操作员用户名：<input type="text" id="log_account2" name="account2" value="${page.map.operateAcct}" style="width:160px;" /></label>
					<td align="right"><label>操作时间：<input id="log_operate_time" class="easyui-datebox" name="operateTime" value="${page.map.operateTime}" style="width:160px;" /></label></td>
				</tr>
			</table>
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>
		<table border="1" class="table-layout">
			<caption>用户操作日志列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">用户名</th>
				<th width="15%">姓名</th>
				<th width="15%">变更前状态</th>
				<th width="20%">变更后状态</th>
				<th width="15%">操作员用户名</th>
				<th width="15%">操作时间</th>
			</tr>
			<c:forEach items="${records}" var="record" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td>${record.userAcct}</td>
				<td>${record.userName}</td>
				<td>
				<c:if test="${record.operateBefore == '1'}">开通</c:if>
				<c:if test="${record.operateBefore == '2'}">关闭</c:if>
				<c:if test="${record.operateBefore == '3'}">停用</c:if>
				<c:if test="${record.operateBefore == '4'}">锁定</c:if>
				<c:if test="${record.operateBefore == '5'}">禁用</c:if>
				</td>
				<td>
				<c:if test="${record.operateAfter == '1'}">开通</c:if>
				<c:if test="${record.operateAfter == '2'}">关闭</c:if>
				<c:if test="${record.operateAfter == '3'}">停用</c:if>
				<c:if test="${record.operateAfter == '4'}">锁定</c:if>
				<c:if test="${record.operateAfter == '5'}">禁用</c:if>
				</td>
				<td>${record.operateAcct}</td>
				<td>${record.operateTime}</td>
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
var before = $('#log_before_status');
var after = $('#log_after_status');
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/system/log/login/pageList.do";
	f.submit();
}
function fnLoadCache() {
	$.ajax({
		url : "${basePath}/system/loadDictByKey.do",
		type : 'post',
		data : {
			'groupCode' : before.attr('groupCode')
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				before.fnLoadComponent(response);
				$('#log_before_status option[value="' + before.attr('selectCode') + '"]').attr('selected', true);
			}
		},
		error : function(error, status) {
			alert('"变更前状态"加载异常,状态码[' + status + ']');
		}
	});
	$.ajax({
		url : "${basePath}/system/loadDictByKey.do",
		type : 'post',
		data : {
			'groupCode' : after.attr('groupCode')
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				after.fnLoadComponent(response);
				$('#log_after_status option[value="' + after.attr('selectCode') + '"]').attr('selected', true);
			}
		},
		error : function(error, status) {
			alert('"变更后状态"加载异常,状态码[' + status + ']');
		}
	});
}
$(function() {
	fnLoadCache();
	$('#btnReset').on('click', function() {
		$('#log_account1').val('').focus();
		$('#log_user_name').val('');
		$('#log_before_status').val('');
		$('#log_after_status').val('');
		$('#log_operate_time').datebox('setValue', '');
		$('#log_account2').val('');
	});
	$('#btnQuery').on('click', function() {
		$('#frmList').attr('src', "${basePath}/system/log/login/pageList.do");
		$('#frmList').submit();
	});
});
</script>
</html>