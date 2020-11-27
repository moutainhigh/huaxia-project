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
		<table border="1" class="table-layout">
			<caption>人行查询数量月统计报表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="25%">报表时间</th>
				<th width="22%">手动单条查询数量</th>
				<th width="22%">手动批量查询数量</th>
				<th width="26%">审批渠道查询数量</th>
			</tr>
			<c:forEach items="${records}" var="record" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td>${record.RPT_MONTH}</td>
				<td>${record.QUERY_SINGLE}</td>
				<td>${record.QUERY_BATCH}</td>
				<td>${record.QUERY_CAMS}</td>
			</tr>
			</c:forEach>
		</table>
		<!-- 分页代码 & 开始 -->
		<c:if test="${page.totalCount > 0}">
			<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}" totalCount="${page.totalCount}" />
		</c:if>
		<input type="hidden" id="pageNo" name="pageNo" />
		<input type="hidden" id="pageSize" name="pageSize" />
		<!-- 分页代码 & 结束 -->
		</form>
	</div>
</body>
<script type="text/javascript">
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/report/pboc/page/list.do";
	f.submit();
}
</script>
</html>