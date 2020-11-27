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
	<form id="frmList" method="post">
		<table border="1" class="table-layout">
			<caption>人行征信批次列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">批次号</th>
				<th width="20%">导入文件名</th>
				<th width="20%">导入时间</th>
				<th width="15%">导入总条数</th>
				<th width="10%">操作人员工号</th>
				<th width="10%">操作人姓名</th>
			</tr>
			<c:forEach items="${records}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td>${list.batchId}</td>
					<td><a href="javascript:void();"
						onclick='fileDetail("${list.fileId}")'>${list.fileName}</a></td>
					<td>${list.crtTime}</td>
					<td>${list.totalRecord}</td>
					<td>${list.staffId}</td>
					<td>${list.staffName}</td>
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
</body>
<script type="text/javascript">
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/pboc/batch/batchResult.do";
		f.submit();
	}

	function fileDetail(fileId) {
		window
				.open(
						"${basePath}/pboc/batch/filedetail.do?fileId=" + fileId,
						'_blank');
	}
</script>
</html>