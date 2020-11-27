<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>简项公安查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<table border="1" class="table-layout">
		<caption>简项公安结果信息</caption>
		<tr>
			<td>报告编号</td>
			<td>${info.trnId}</td>
			<td>创建时间</td>
			<td>${info.crtTime}</td>
			<td rowspan="3"><img src="data:image/png;base64,${info.xp}" /s></td>
		</tr>
		<c:if test="${info.searchResult =='库中无此号'}">
				<tr>
					<td>证件号</td>
					<td>${info.gmsfhm}</td>
					<th>证件号核查结果</th>
					<td>${info.searchResult}</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>${info.xm}</td>
					<th>姓名核查结果</th>
					<td>${info.searchResult}</td>
				</tr>
		</c:if>
		<c:if test="${info.searchResult !='库中无此号'}">
				<tr>
					<td>证件号</td>
					<td>${info.gmsfhm}</td>
					<th>证件号核查结果</th>
					<td>${info.gmsfhmResult}</td>
				</tr>
				<tr>
					<td>姓名</td>
					<td>${info.xm}</td>
					<th>姓名核查结果</th>
					<td>${info.xmResult}</td>
				</tr>
		</c:if>
	</table>
</body>
</html>