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
			<caption>手机实名认证批次文件列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">文件名</th>
				<th width="20%">导入时间</th>
				<th width="15%">导入总条数</th>
				<th width="10%">操作人员工号</th>
				<th width="10%">操作人姓名</th>
			</tr>
			<c:forEach items="${records}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td><a href="javascript:void();"
						onclick='detail("${list.batchFileId}")'>${list.batchFileName}</a></td>
					<td>${list.crtTime}</td>
					<td>${list.batchFileRecordSize}</td>
					<td>${list.staffId}</td>
					<td>${list.staffName}</td>
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
		f.action = "${basePath}/phone/get/file_detail.do";
		f.submit();
	}

	function detail(fid) {
		window.open(
				"${basePath}/unicom/phone/batch/pagelist/filedetail.do?batchFileId="
						+ fid, '_blank');
	}
</script>
</html>