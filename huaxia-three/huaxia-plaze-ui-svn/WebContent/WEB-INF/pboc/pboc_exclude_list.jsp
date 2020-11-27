<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>人行查询异常监控</title>
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
					<td><label>被查询人姓名：<input type="text" id="log_name" name="name" value="${page.map.name}" style="width:160px;" /></label></td>
				</tr>
				<tr>
					<td><label>查询结果：
					<select class="form-control" name="queryResult" id="query_result" style="width:160px;">
					<option value="">请选择</option>
					<option value="0">成功</option>
					<option value="1">失败</option>
					</select></label></td>
					<td><label>查询方式：
					<select class="form-control" name="queryType" id="query_type" style="width:160px;margin-left:-5px" >
						<option value="">请选择</option>
						<option value="0">单条实时</option>
						<option value="1">单条查找</option>
					</select></label></td>
				</tr>
				<tr>
					<td><label>开始时间：<input type="text" id="log_start_time" class="easyui-datetimebox" name="startTime" value="${page.map.startTime}" style="width:160px;" /></label></td>
					<td><label>结束时间：<input id="log_end_time" class="easyui-datetimebox" name="endTime" value="${page.map.endTime}" style="width:160px;" /></label></td>
				</tr>
			</table>
			<input id="btnReset" type="button" onclick="resetVal();" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>  
		
		<table border="1" class="table-layout">
			<caption>人行例外查询列表</caption>
			<tr>
				<th width="4%">序号</th>
				<th width="6%">登录名称</th>
				<th width="8%">用户姓名</th>
				<th width="10%">用户证件号码 </th>
				<th width="8%">操作时间 </th>
				<th width="8%">被查询人姓名</th>
				<th width="10%">被查询人证件号码</th>
				<th width="18%">查询原因</th>
				<th width="8%">查询结果</th>
				<th width="8%">IP</th>
				<th width="6%">查询方式</th>
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
				<td>${log.queryResult}</td>
				<td>${log.ip}</td>
				<td>
				<c:if test="${log.queryType=='0'}">
				单条实时
				</c:if>
				<c:if test="${log.queryType=='1'}">
				单条查找
				</c:if>
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
	$("#query_result").val("${page.map.queryResult}");
	$("#query_type").val("${page.map.queryType}");
});

function resetVal(){
	$('#log_account').val('');
	$('#log_staff_name').val('');
	$('#log_name').val('');
	$('#query_result').val('');
	$('#query_Type').val('');
	$('#log_start_time').datebox('setValue', '');
	$('#log_end_time').datebox('setValue', '');
	$('#log_account').val('').focus();
}
</script>
</html>