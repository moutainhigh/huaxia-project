<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>查询日志</title>
<script type="text/javascript" src="${basePath}/js/framework.util.js"></script>
<style type="text/css">
.page {
	line-height: 3em;
}

.page-button-disable {
	padding: 2px 5px;
	border: 1px solid #95B8E7;
	color: #6E6E6E;
	border-color: buttonface;
}

.page-button {
	padding: 2px 5px;
	border: 1px solid #95B8E7;
}

.page-button-color {
	color: #FF0000;
}

.search-layout {
	padding: 0px 10px 10px 10px;
}

.search-layout table {
	margin-bottom: 10px;
}

.search-layout label {
	font: normal 12px Arial;
}

.search-layout table {
	margin: 0px auto 10px;
}
</style>
</head>
<body>
	<div style="position: relative;">
		<form id="frmList" method="post" action='${basePath}/numread/log/page/list.do'>
			<section class="search-layout horizontal">
				<table>
					<tr>
						<td><label>被查询人姓名：<input type="text" id="name"
								name="name" value="${page.map.name}" style="width: 160px;" /></label></td>
						<td><label>被查询人证件号：<input type="text" id="crtNo"
								name="crtNo" value="${page.map.crtNo}" style="width: 160px;" /></label></td>
						<td><label>查询方式：<select class="form-control"
								name="queryModel" id="queryModel" style="width: 160px;">
									<option value="">请选择</option>
									<option value="2">批量实时</option>
									<option value="3">单条查找</option>
							</select></label></td>
						<td><label>操作人用户名：<input type="text"
								id="crtUser" name="crtUser"
								value="${page.map.crtUser}" style="width: 160px;" /></label></td>

					</tr>

				</table>
				<input id="btnReset" type="button" onclick="resetVal();" value="重置" />
				<input id="btnQuery" type="submit" value="查询" />
			</section>
			<table border="1" class="table-layout">
				<caption>人行离线数字解读查询日志列表</caption>
				<tr>
					<th width="4%">序号</th>
					<th width="10%">被查询人姓名</th>
					<th width="15%">被查询人证件号</th>
					<th width="15%">查询方式</th>
					<th width="15%">操作时间</th>
					<th width="15%">操作人用户名</th>
				</tr>

				<c:forEach items="${records}" var="log" varStatus="sequence">
					<tr>
						<td>${sequence.count}</td>
						<td>${log.name}</td>
						<td>${log.certNo}</td>
						<td><c:if test="${log.queryModel=='2'}">
							批量实时
							</c:if><c:if test="${log.queryModel=='3'}">
							单条查找
							</c:if></td>
						<td>${log.crtTime}</td>
						<td>${log.crtUser}</td>
					</tr>
				</c:forEach>
			</table>
			<!-- 分页代码 & 开始 -->
			<c:if test="${page.totalCount > 0}">
				<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}"
					totalCount="${page.totalCount}" />
			</c:if>
			<!-- 分页代码 & 结束 -->
			<input type="hidden" id="pageNo" name="pageNo" /> <input
				type="hidden" id="pageSize" name="pageSize" /> <input type="hidden"
				id="txtUserId" name="userId" />
		</form>
	</div>
</body>
<script type="text/javascript">
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/numread/log/page/list.do";
		f.submit();
	}
	$(function() {
		// 给下拉列表赋值查询条件
		$("#queryModel").val("${page.map.queryModel}");

	});
	function resetVal() {
		$('#name').val('');
		$('#crtNo').val('');
		$('#queryModel').val('');
		$('#crtUser').val('');
	}
</script>
</html>