<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>简项公安查询</title>
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
			action="pagelist.do">
			<section class="search-layout horizontal">
				<table>
					<tr>
						<td><label>证件号：<input type="text" id="gmsfhm"
								name="gmsfhm" value="${page.map.gmsfhm}" style="width: 160px;" /></label></td>
					</tr>
				</table>
				<input id="btnReset" type="button" onclick="resetVal()" value="重置" />
				<input id="btnQuery" type="submit" value="查询" />
			</section>
			<div id="History">
				<table border="1" class="table-layout">
					<caption>历史数据列表</caption>
					<tr>
						<th width="10%">序号</th>
						<th width="10%">报告编号</th>
						<th width="10%">证件号</th>
						<th width="10%">证件号核查结果</th>
						<th width="10%">姓名</th>
						<th width="10%">姓名核查结果</th>
						<th width="10%">创建时间</th>
						<th width="10%">照片</th>
					</tr>

					<c:forEach items="${records}" var="history" varStatus="sequence">
						<tr>
							<td>${sequence.count}</td>
							<td>${history.trnId}</td>
							<td>${history.gmsfhm}</td>
							<c:if test="${history.searchResult !='库中无此号'}">
								<td>${history.gmsfhmResult}</td>
							</c:if>
							<c:if test="${history.searchResult =='库中无此号'}">
								<td>${history.searchResult}</td>
							</c:if>
							
							<td>${history.xm}</td>
							
							<c:if test="${history.searchResult !='库中无此号'}">
								<td>${history.xmResult}</td>
							</c:if>
							<c:if test="${history.searchResult =='库中无此号'}">
								<td>${history.searchResult}</td>
							</c:if>
							
							
							<td>${history.crtTime}</td>
							<td><img src="data:image/png;base64,${history.xp}"></td>
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
function resetVal(){
	$('#gmsfhm').val('');
	$('#gmsfhm').val('').focus();
}

function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.submit();
}
</script>
</html>