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
				<caption>单条复核退回列表</caption>
				<tr>
					<th width="5%">序号</th>
					<th width="8%">用户工号</th>
					<th width="10%">查询人姓名</th>
					<th width="10%">查询人ID</th>
					<th width="15%">提交复核申请时间</th>
					<th width="10%">被查询人姓名</th>
					<th width="10%">被查询人ID</th>
					<th width="15%">退回时间</th>
					<th width="12%">退回原因</th>
					<th width="5%">操作</th>
				</tr>
				<c:forEach items="${records}" var="list" varStatus="sequence">
					<tr>
						<td>${sequence.count}</td>
						<td>${list.staffId}</td>
						<td>${list.staffName}</td>
						<td>${list.staffCert}</td>
						<td>${list.crtTime}</td>
						<td>${list.name}</td>
						<td>${list.certNo}</td>
						<td>${list.refuseTime}</td>
						<td>${list.refuseReason}</td>
						<td><a href="javascript:void();"
							onclick='fnModify("${list.trnId}")'>修改</a></td>
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
	function refresh() {
		var f = $('#frmList');
		f.action = "${basePath}/credit/log/pageList.do";
		f.submit();
	}
	function fnModify(id) {
		window
				.open(
						"${basePath}/pboc/single/get.do?id=" + id,
						'_blank');
	}
	//翻页显示
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/pboc/single/reviewRefuse.do";
		f.submit();
	}
</script>
</html>