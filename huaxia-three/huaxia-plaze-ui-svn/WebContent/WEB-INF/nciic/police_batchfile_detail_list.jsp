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
			<caption>简项公安批次文件明细列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">证件号</th>
				<th width="20%">姓名</th>
				<th width="15%">导入时间</th>
				<th width="10%">查询时间</th>
				<th width="10%">查询状态</th>
			</tr>
			<c:forEach items="${records}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td><a href="javascript:void();"
						onclick='detail("${list.trnId}","${list.queryStatus}")'>${list.certNo}</a></td>
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
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/nciic/police/batch/pagelist.do";
		f.submit();
	}

	function detail(fid, status) {
		if (status != '1') {
			alert("暂时没有查询结果");
			return;
		}
		window.open('${basePath}/nciic/police/batch/get/filedetail.do?trnId=' + fid, '_blank');
	}
</script>
</html>