<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>百融多头借贷查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<div>
		<form id="frmList" method="post">
			<table border="1" class="table-layout">
				<caption>百融多头借贷批次文件明细列表</caption>
				<tr>
					<th width="5%">序号</th>
					<th width="13%">证件号</th>
					<th width="13%">姓名</th>
					<th width="13%">手机号</th>
					<th width="13%">发卡账户</th>
					<th width="13%">导入时间</th>
					<th width="13%">查询时间</th>
					<th width="13%">查询状态</th>
				</tr>
				<c:forEach items="${records}" var="list" varStatus="sequence">
					<tr>
						<td>${list.sequence}</td>
						<td>${list.certNo}</td>
						<td>${list.name}</td>
						<td>${list.mobile}</td>
						<td>${list.cardAccount}</td>
						<td>${list.crtTime}</td>
						<td>${list.queryTime}</td>
						<td><c:choose>
								<c:when test="${list.queryStatus =='0'}">
					待查询
					</c:when>
								<c:when test="${list.queryStatus =='1'}">
					 查询成功
					</c:when>
								<c:otherwise>
					查询失败
					</c:otherwise>
							</c:choose></td>
					</tr>
				</c:forEach>
			</table>
			<!-- 分页代码 & 开始 -->
			<c:if test="${page.totalCount > 0}">
				<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}"
					totalCount="${page.totalCount}" />
			</c:if>
			<input type="hidden" id="pageNo" name="pageNo" /> <input
				type="hidden" id="pageSize" name="pageSize" />
			<!-- 分页代码 & 结束 -->
		</form>

	</div>
</body>
<script type="text/javascript">
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/bairong/als/batch/pagelist/filedetail.do";
		f.submit();
	}
	function queryResult(trnId) {
		alert("暂时没有百融多头借贷报告");
		return;
	}
</script>
</html>