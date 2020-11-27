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
	<div>
		<form id="frmList" method="post">
		<table border="1" class="table-layout">
			<caption>实时阻断查询列表</caption>
			<tr>
				<th width="20%">登录名称</th>
				<th width="20%">操作内容</th>
				<th width="30%">阻断原因</th>
				<th width="10%">查询条数</th>
				<th width="20%">操作时间 </th>
			</tr>
			<c:forEach items="${records}" var="log" varStatus="sequence">
			<tr>
				<td>${log.crtUser}</td>
				<td>
				<c:if test="${log.queryType=='0'}">单条实时查询</c:if>
				<c:if test="${log.queryType=='1'}">单条实时查找</c:if>
				</td>
	            <td>${log.preventQueryReason}</td>
	            <td>${log.preventQueryCount}</td>
				<td>${log.crtTime}</td>
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
	f.action = "${basePath}/pboc/log/zdcx.do";
	f.submit();
}
</script>
</html>