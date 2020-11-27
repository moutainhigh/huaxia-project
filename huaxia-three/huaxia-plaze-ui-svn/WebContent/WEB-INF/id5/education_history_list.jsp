<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>学历信息查询</title>
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
	<div>
		<form id="frmList" method="post"
			action="${basePath}/id5/edu/history/pagelist.do">
			<section class="search-layout horizontal">
				<table>
					<tr>
						<td><label>证件号码：<input type="text" id="identityCard"
								name="identityCard" value="${page.map.identityCard}"
								style="width: 160px;" /></label></td>
					</tr>
				</table>
				<input id="btnReset" type="button" onclick="resetVal()" value="重置" />&nbsp;&nbsp;
				<input id="btnQuery" type="submit" value="查询" />
			</section>
			<div id="History">
				<table border="1" class="table-layout">
					<caption>学历信息列表</caption>
					<tr>
						<th width="5%">序号</th>
						<th width="10%">报告编号</th>
						<th width="10%">证件号</th>
						<th width="10%">姓名</th>
						<th width="10%">查询结果</th>
						<th width="15%">创建时间</th>
						<th width="10%">查看详情</th>
					</tr>
					<c:forEach items="${records}" var="history" varStatus="sequence">
						<tr>
							<td>${sequence.count}</td>
							<td>${history.trnId}</td>
							<td>${history.identityCard}</td>
							<td>${history.userName}</td>
							<td>${history.queryResult}</td>
							<td>${history.crtTime}</td>
							<td><a href="javascript:void();"
								onclick='detail("${history.trnId}")'>查看详情</a></td>
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
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var showPage = "${page}";
		if (showPage == "") {
			$("#History").hide();
		}
		var info = "${info}";
		if (info != "") {
			$.messager.alert("提示", info, "warning");
		}

	});
	function resetVal() {
		$('#identityCard').val('');
	}
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.submit();
	}

	function detail(trnId) {
		window.open("${basePath}/id5/edu/query/detail.do?trnId=" + trnId,
				'_blank');
	}
</script>
</html>