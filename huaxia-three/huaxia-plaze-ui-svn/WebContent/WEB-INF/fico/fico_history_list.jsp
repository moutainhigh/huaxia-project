<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>FICO历史数据查询</title>
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
			action="${basePath}/fico/history/pagelist.do">
			<section class="search-layout horizontal">
				<table>
					<tr>
						<td><label>证件号：<input type="text" id="cert_no"
								name="certNo" value="${page.map.certNo}" style="width: 160px;" /></label></td>
					</tr>
				</table>
				<input id="btnReset" type="button" onclick="resetVal()" value="重置" />
				<input id="btnQuery" type="submit" value="查询" />
			</section>
			<div id="History">
				<table border="1" class="table-layout" style="table-layout:fixed">
					<caption>历史数据列表</caption>
					<tr>
						<th width="5%">序号</th>
						<th width="11%">手机号前三位</th>		
						<th width="11%">日期</th>
						<th width="11%">创建时间</th>
						<th width="11%">风险等级</th>
						<th width="11%">查询时间</th>
						<th width="15%">手机号</th>
						<th>证件号</th>
					</tr>
					<c:forEach items="${records}" var="fico" varStatus="sequence">
						<tr>
							<td  width="5%">${sequence.count}</td>
							<td  width="11%">${fico.telNumForth}</td>
							<td  width="11%">${fico.ficoDate}</td>
							<td width="11%">${fico.crtTime}</td>
							<td  width="11%">${fico.restLevel}</td>
							<td width="11%">${fico.queryTime}</td>
							<td  width="15%">${fico.telNum}</td>
							<td ><div>${fico.idNum}</div></td>
						</tr>
					</c:forEach>
				</table>
				<!-- 分页代码 & 开始 -->
		<c:if test="${page.totalCount > 0}">
			<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}" totalCount="${page.totalCount}" />
		</c:if>
		<input type="hidden" id="pageNo" name="pageNo" />
		<input type="hidden" id="pageSize" name="pageSize" />
		<input type="hidden" id="txtUserId" name="userId" />
		<!-- 分页代码 & 结束 -->
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		var showPage = "${showPage}";
		if (showPage == "") {
			$("#History").hide();
		}
		var info = "${info}";
		if (info != "") {
			$.messager.alert("提示", info, "warning");
		}
	})
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/fico/history/pagelist.do";
	f.submit();
}
	function resetVal() {
		$('#cert_no').val('');
		$('#cert_no').val('').focus();
	}
</script>
</html>