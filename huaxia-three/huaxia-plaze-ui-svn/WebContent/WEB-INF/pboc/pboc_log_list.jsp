<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>人行征信查询</title>
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
	<div style="position:relative;">
		<form id="frmList" method="post">
		<section class="search-layout horizontal">
			<table>
				<tr>
					<td><label>登录名称：<input type="text" id="log_account" name="account" value="${page.map.account}" style="width:160px;" /></label></td>
					<td><label>用户姓名：<input type="text" id="log_staff_name" name="staffName" value="${page.map.staffName}" style="width:160px;" /></label></td>
					<td><label>被查询人姓名：<input type="text" id="log_name" name="name" value="${page.map.name}" style="width:160px;" /></label></td>
				</tr>
				<tr>
					<td><label>查询结果：<select class="form-control" name="queryResult" id="query_result" style="width:160px;">
					<option value="">请选择</option>
					<option value="0">成功</option>
					<option value="1">失败</option>
					</select></label></td>
					<td><label>查询方式：<select class="form-control" name="queryType" id="query_type" style="width:160px;" >
						<option value="">请选择</option>
						<option value="0">单条实时</option>
						<option value="1">单条查找</option>
					</select></label></td>
					<td><label style="margin-left:24px;">查询原因：<select class="form-control" name="queryReason" id="query_reason"  style="width:160px;">
						<option value="">请选择</option>
						<option value="贷前">贷前</option>
						<option value="贷后">贷后</option>
						<option value="特约商户查询">特约商户查询</option>
					</select></label>
					</td>
				</tr>
				<tr>
					<td><label>开始时间：<input id="log_start_time" class="easyui-datetimebox" name="startTime" value="${page.map.startTime}" style="width:160px;" /></label></td>
					<td colspan="2"><label>结束时间：<input id="log_end_time" class="easyui-datetimebox" name="endTime" value="${page.map.endTime}" style="width:160px;" /></label></td>
				</tr>
			</table>
			<input id="btnReset" type="button" onclick="resetVal();" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>  
		<table border="1" class="table-layout">
			<caption>人行查询日志列表</caption>
			<tr>
				<th width="4%">序号</th>
				<th width="6%">登录名称</th>
				<th width="6%">用户姓名</th>
				<th width="10%">用户证件号码 </th>
				<th width="7%">操作时间 </th>
				<th width="8%">被查询人姓名</th>
				<th width="10%">被查询人证件号码</th>
				<th width="20%">查询原因</th>
			 <th width="6%">查询结果</th> 
				<th width="8%">IP</th>
				<th width="8%">查询方式</th>
				<th width="6%">操作动作</th>
			</tr>
			
			<c:forEach items="${records}" var="log" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td>${log.crtUser}</td>
				<td>${log.staffName}</td>
				<td>${log.staffCertNo}</td>
				<td>${log.crtTime}</td>
				<td>${log.name}</td>
				<td>${log.certNo}</td>
				<td>${log.queryReason}</td>
				<td>
				<c:if test="${log.queryResult=='0'}">
		       	待复核
				</c:if>
				<c:if test="${log.queryResult=='1'}">
				复核通过
				</c:if>
				<c:if test="${log.queryResult=='2'}">
				正在查询
				</c:if>
				<c:if test="${log.queryResult=='3'}">
				查询成功
				</c:if>
				<c:if test="${log.queryResult=='4'}">
				查询失败
				</c:if>
				<c:if test="${log.queryResult=='5'}">
				已退回
				</c:if>
				<c:if test="${log.queryResult==''}">
				</c:if>
				</td>
				<td>${log.ip}</td>
				<td>
				<c:if test="${log.queryType=='0'}">
				单条实时查询
				</c:if>
				<c:if test="${log.queryType=='1'}">
				单条实时查找
				</c:if>
				</td>
				<td>
				<c:if test="${log.queryAction=='1'}">
		       	提交复核
				</c:if>
				<c:if test="${log.queryAction=='2'}">
				<!-- 提交查询 -->复核通过
				</c:if>
				<c:if test="${log.queryAction=='3'}">
				复核退回
				</c:if>
				<c:if test="${log.queryAction=='4'}">
				<!-- 批量查询 -->复核通过
				</c:if>
				<c:if test="${log.queryAction=='5'}">
				批量退回
				</c:if>
				<c:if test="${log.queryAction=='6'}">
				单条查找
				</c:if>
				<c:if test="${log.queryAction=='7'}">
				阻断用户
				</c:if>
				<c:if test="${log.queryAction=='8'}">
				阻断查询
				</c:if>
				</td>
			</tr>
			</c:forEach>
		</table>
		<!-- 分页代码 & 开始 -->
		<c:if test="${page.totalCount > 0}">
			<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}" totalCount="${page.totalCount}" />
		</c:if>
		<!-- 分页代码 & 结束 -->
		<input type="hidden" id="pageNo" name="pageNo" />
		<input type="hidden" id="pageSize" name="pageSize" />
		<input type="hidden" id="txtUserId" name="userId" />
		</form>
		<label style="position:absolute;right:0;bottom:12px;"><a href="javascript:void(0);" id="search_download" style="text-decoration:none;color:grey;">个人信用查询报告查询登记簿下载</a><input type="hidden" id="search_params" /></label>
	</div>
</body>
<script type="text/javascript">
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/pboc/log/pageList.do";
	f.submit();
}
$(function() {
	// 给下拉列表赋值查询条件
	$("#query_result").val("${page.map.queryResult}");
	// 给下拉列表赋值查询条件
	$("#query_type").val("${page.map.queryType}");
	$("#query_reason").val("${page.map.queryReason}");
	
	// 个人信用查询报告查询登记簿下载
	$('#search_download').on('click', function() {
		var a = $('#log_account').val();
		var b = encodeURIComponent($('#log_staff_name').val());
		var c = encodeURIComponent($('#log_name').val());
		var d = $('#query_result').val();
		var e = $('#query_type').val();
		var f = $('#log_start_time').datebox('getValue');
		var g = $('#log_end_time').datebox('getValue');
		var h = encodeURIComponent($('#query_reason').val());
		if (a=='' && b=='' && c=='' && d=='' && e=='' && f=='' && h=='') {
			alert('请选择筛选条件后进行下载!');
			return false;
		}
		var args = encodeURI('account|'+(a)+'$staffName|'+(b)+'$name|'+(c)+'$queryResult|'+(d)+'$queryType|'+(e)+'$startTime|'+(f)+'$endTime|'+(g)+'$queryReason|'+(h));
		var w = window.open('about:blank');
		w.location.href = '${basePath}/pboc/log/download.do?params=' + args;
	});
});
function resetVal(){
	$('#log_account').val('');
	$('#log_staff_name').val('');
	$('#log_name').val('');
	$('#query_result').val('');
	$('#query_type').val('');
	$('#log_start_time').datebox('setValue', '');
	$('#log_end_time').datebox('setValue', '');
	$('#log_account').val('').focus();
}
</script>
</html>