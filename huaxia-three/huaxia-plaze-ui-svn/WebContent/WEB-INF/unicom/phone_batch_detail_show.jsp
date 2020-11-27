<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>手机实名认证查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<form id="frmList" method="post">
		<table border="1" class="table-layout">
			<caption>手机实名认证查询结果</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">交易编号</th>
				<th width="20%">查询时间</th>
				<th width="20%">是否成功</th>
				<th width="40%">结果描述</th>
			</tr>
			<c:forEach items="${records}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td>${list.trnId}</td>
					<td>${list.crtTime}</td>
					<td><c:if test="${list.status =='1'}">
					成功
					</c:if> <c:if test="${list.status =='2'}">
					失败
					</c:if></td>
					<td><c:if test="${list.code =='00'}">
					一致
					</c:if> <c:if test="${list.code !='00'}">
					不一致
					</c:if></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
<script type="text/javascript">
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/unicom/phone/batch/view/detail.do";
		f.submit();
	}

	function detail(fid) {
		window.open(
				"${basePath}/unicom/phone/batch/view/detail.do?batchFileId="
						+ fid, '_blank');
	}
</script>
</html>