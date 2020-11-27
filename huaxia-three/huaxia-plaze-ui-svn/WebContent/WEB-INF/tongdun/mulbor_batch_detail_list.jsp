<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>同盾多头借贷查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<div>
		<form id="frmList" method="post">
			<table border="1" class="table-layout">
				<caption>同盾多头借贷批次文件明细列表</caption>
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
						<td><c:choose>
								<c:when test="${list.reviewStatus =='1'}">
									<a href="javascript:void();"
										onclick='queryResult("${list.trnId}")'>${list.certNo}</a>
								</c:when>
								<c:otherwise>
									${list.certNo}
							</c:otherwise>
							</c:choose></td>
						<td>${list.name}</td>
						<td>${list.crtTime}</td>
						<td>${list.reviewTime}</td>
						<td><c:choose>
								<c:when test="${list.reviewStatus =='0'}">
					待查询
					</c:when>
								<c:when test="${list.reviewStatus =='1'}">
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
		f.action = "${basePath}/tongdun/mulbor/batch/pagelist/filedetail.do";
		f.submit();
	}
	function queryResult(id) {
		window.open('${basePath}/tongdun/mulbor/batch/view/detail.do?trnId='
				+ id, '_blank');
	}
</script>
</html>