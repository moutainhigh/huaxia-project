<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>学历信息查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<form id="frmList" method="post">
		<table border="1" class="table-layout">
			<caption>学历批次文件明细列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">证件号</th>
				<th width="15%">姓名</th>
				<th width="15%">导入时间</th>
				<th width="15%">查询时间</th>
				<th width="10%">查询状态</th>
			</tr>
			<c:forEach items="${records}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td><c:if test="${list.queryStatus =='0'}">
					${list.certNo}
					</c:if> <c:if test="${list.queryStatus =='1'}">
							<a href="javascript:void();" onclick='detail("${list.trnId}")'>${list.certNo}</a>
						</c:if></td>
					<td>${list.name}</td>
					<td>${list.crtTime}</td>
					<td>${list.queryTime}</td>
					<td><c:if test="${list.queryStatus =='0'}">
					正在查询
					</c:if> <c:if test="${list.queryStatus =='1'}">
					查询成功
					</c:if> <c:if test="${list.queryStatus =='2'}">
					查询失败
					</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
<script type="text/javascript">
	function detail(id) {
		window.open('${basePath}/id5/edu/query/detail.do?trnId=' + id, '_blank');
	}
</script>
</html>