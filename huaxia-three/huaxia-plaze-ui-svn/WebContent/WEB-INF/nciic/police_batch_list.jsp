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
	<form id="frmList" method="post">
		<table border="1" class="table-layout">
			<caption>简项公安批次列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">批次号</th>
				<th width="20%">导入时间</th>
				<th width="15%">导入总条数</th>
				<th width="10%">操作人员工号</th>
				<th width="10%">操作人姓名</th>

			</tr>
			<c:forEach items="${records}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td><a href="javascript:void();"
						onclick='detail("${list.batchId}")'>${list.batchId}</a></td>
					<td>${list.crtTime}</td>
					<td>${list.batchRecordSize}</td>
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
	<c:if test="${empty records}">
		<div style="text-align: center; color: red; margin-top: 20px;">
			没有相关数据</div>
	</c:if>

</body>
<script type="text/javascript">
	function page(p, s, e) {
		$('#pageNo').val(p);
		s
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/nciic/police/batch/pagelist.do";
		f.submit();
	}

	function detail(bid) {
		window.open("${basePath}/nciic/police/batch/batchfile/list.do?batchId="
				+ bid, '_blank');
	}
</script>
</html>