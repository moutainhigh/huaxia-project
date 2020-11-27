<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>人行离线数字解读查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<form id="frmList" method="post">
		<table border="1" class="table-layout">
			<caption>人行数字解读批次查询明细列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">证件号码</th>
				<th width="15%">姓名</th>
				<th width="15%">证件类型</th>
				<th width="15%">产品日期</th>
				<th width="10%">查询状态</th>
			</tr>
			<c:forEach items="${records}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td><c:choose>
						<c:when test="${list.status =='3'}">
						<a href="javascript:void();" onclick='detail("${list.trnId}")'>${list.certNo}</a>
						</c:when>
						<c:otherwise>${list.certNo}</c:otherwise>
					</c:choose></td>
					<td>${list.name}</td>
					<td>${list.certType}</td>
					<td>${list.productDate}</td>
					<td><c:if test="${list.status =='0'}">
					待查询
					</c:if> <c:if test="${list.status =='1'}">
					正在查询
					</c:if><c:if test="${list.status =='2'}">
					写入请求文件失败
					</c:if><c:if test="${list.status =='3'}">
					查询成功
					</c:if> <c:if test="${list.status =='4'}">
					查询失败
					</c:if>
					
					</td>
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
		<!-- 批次文件id -->
		<input type="hidden" id="batchFileId" name="batchFileId" value="${batchFileId}" />
	</form>
</body>
<script type="text/javascript">
function page(p, s, e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/numread/batch/pagelist/filedetail.do";
	f.submit();
}
	function detail(trnId) {
		window
				.open(
						"${basePath}/numread/batch/view/detail.do?trnId="
								+ trnId,
						'_blank');
	}
</script>
</html>