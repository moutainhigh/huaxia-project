<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>FICO文件明细查询</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/css/common/page.css"></link>
</head>
<body>
	<div>
		<form id="frmList" method="post">
			<table border="1" class="table-layout">
				<caption>FICO批次文件明细列表</caption>
				<tr>
					<th width="5%">序号</th>
					<th width="10%">证件号</th>
					<th width="10%">手机号</th>
					<th width="10%">手机号前三位</th>
					<th width="10%">文件查询日期</th>
					<th width="10%">导入时间</th>
					<th width="10">状态</th>
					<th width="10">查询时间</th>
					<th >风险等级</th>
				</tr>
				<c:forEach items="${records}" var="list" varStatus="sequence">
					<tr>
						<td>${sequence.count}</td>
						<td>${list.certNo}</td>
						<td>${list.mobile}</td>
						<td>${list.topThreeMobile}</td>
						<td>${list.inputSysDate}</td>
						<td>${list.crtTime}</td>
							<td><c:choose>
								<c:when test="${list.queryStatus =='0'}">
					未发起查询
					</c:when>
								<c:when test="${list.queryStatus =='1'}">
					 正在查询
					</c:when>
								<c:when test="${list.queryStatus =='2'}">
					查询成功
					</c:when>
								<c:when test="${list.queryStatus =='3'}">
					 查询失败
					</c:when>
								<c:otherwise>
					状态异常
					</c:otherwise>
							</c:choose></td>
						<td>${list.queryTime}</td>
						<td>${list.riskLevel}</td>
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
	//翻页显示
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}fico/batch/pagelist/filedetail.do";
		f.submit();
	}

</script>
</html>