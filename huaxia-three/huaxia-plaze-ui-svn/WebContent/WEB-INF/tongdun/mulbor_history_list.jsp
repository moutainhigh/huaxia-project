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
<title>同盾多头借贷查询</title>
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
			action="${basePath}/tongdun/mulbor/history/pagelist.do">
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
				<table border="1" class="table-layout">
					<caption>历史数据列表</caption>
					<tr>
						<th width="11%">序号</th>
						<th width="11%">证件号</th>
						<th width="11%">手机号</th>
						<th width="11%">姓名</th>
						<th width="11%">创建时间</th>
						<th width="11%">是否调用成功</th>
						<th width="11%">决定</th>
						<th width="11%">风险分数</th>
						<th width="">详情</th>
					</tr>

					<c:forEach items="${records}" var="mulbor" varStatus="sequence">
						<tr>
							<td>${sequence.count}</td>
							<td>${mulbor.certNo}</td>
							<td>${mulbor.mobile}</td>
							<td>${mulbor.crtUser}</td>
							<td><fmt:formatDate value="${mulbor.crtTime}" type="date"
									pattern="yyyy-MM-dd HH:mm:ss" /></td>
							<td><c:if test="${mulbor.success=='false'}">
					调用失败
				</c:if> <c:if test="${mulbor.success=='true'}">
					调用成功
				</c:if></td>
							<td><c:if test="${mulbor.finalDecision=='PASS'}">
						通过
					</c:if> <c:if test="${mulbor.finalDecision=='REVIEW'}">
						复核
					</c:if> <c:if test="${mulbor.finalDecision=='REJECT'}">
						拒绝
					</c:if></td>
							<td>${mulbor.finalScore}</td>
							<td><a href="javascript:void();"
								onclick='queryResult("${mulbor.trnId}")'>详情</a></td>
						</tr>
					</c:forEach>
				</table>
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

	function resetVal() {
		$('#cert_no').val('');
		$('#cert_no').val('').focus();
	}
	function queryResult(id) {
		window.open('${basePath}/tongdun/mulbor/batch/view/detail.do?trnId='
				+ id, '_blank');
	}
</script>
</html>