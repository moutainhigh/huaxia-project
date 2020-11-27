<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>人行征信查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<div>
		<form id="frmList" method="post">
			<table border="1" class="table-layout">
				<caption>人行征信批次文件明细列表</caption>
				<tr>
					<th width="5%">序号</th>
					<th width="20%">证件号</th>
					<th width="15%">姓名</th>
					<th width="20%">导入时间</th>
					<th width="20%">查询时间</th>
					<th width="15%">查询状态</th>
				</tr>
				<c:forEach items="${records}" var="list" varStatus="sequence">
					<tr>
						<td>${sequence.count}</td>
						<td><a href="javascript:void();"
							onclick='queryResult("${list.trnId}","${list.reviewStatus}")'>${list.certNo}</a></td>
						<td>${list.name}</td>
						<td>${list.crtTime}</td>
						<td>${list.reviewTime}</td>
						<td><c:choose>
								<c:when test="${list.reviewStatus =='0'}">
					待复核
					</c:when>
								<c:when test="${list.reviewStatus =='1'}">
					 复核通过
					</c:when>
								<c:when test="${list.reviewStatus =='2'}">
					正在查询
					</c:when>
								<c:when test="${list.reviewStatus =='3'}">
					 查询成功
					</c:when>
								<c:when test="${list.reviewStatus =='5'}">
					已退回
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
		f.action = "${basePath}/pboc/batch/reviewQuery.do";
		f.submit();
	}
	function queryResult(trnId, status) {
		if (status != '3') {
			alert("暂时没有人行报告");
			return;
		}
		var w = window.open('about:blank');
		w.location.href = '${basePath}/pboc/report/queryResult.do?uniqueId=' + trnId;
	}
</script>
</html>