<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>人行数字解读查询</title>
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
			action="${basePath}/numread/batch/history/pagelist.do">
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
						<th width="5%">序号</th>
						<th width="15%">证件号码</th>
						<th width="15%">姓名</th>
						<th width="15%">证件类型</th>
						<th width="15%">产品日期</th>
						<th width="10%">查询状态</th>
					</tr>
					<c:forEach items="${records}" var="list" varStatus="sequence">
						<tr>
							<td>${sequence.count}</td>
					<td><c:choose>
						<c:when test="${list.status =='3'}">
						<a href="javascript:void();" onclick='detail("${list.trnId}")'>${list.certNo}</a>
						</c:when>
						<c:otherwise>${list.certNo}</c:otherwise>
					</c:choose></td>
					<td>${list.name}</td>
					<td>${list.certType}</td>
					<td>${list.productDate}</td>
					<td><c:if test="${list.status =='0'}">
					待查询
					</c:if> <c:if test="${list.status =='1'}">
					正在查询
					</c:if><c:if test="${list.status =='2'}">
					写入请求文件失败
					</c:if><c:if test="${list.status =='3'}">
					查询成功
					</c:if> <c:if test="${list.status =='4'}">
					查询失败
					</c:if>
					
					</td>
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
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/numread/batch/history/pagelist.do";
		f.submit();
	}
	function resetVal() {
		$('#cert_no').val('');
		$('#cert_no').val('').focus();
	}
	function detail(trnId) {
		window
				.open(
						"${basePath}/numread/batch/view/detail.do?trnId="
								+ trnId,
						'_blank');
	}
</script>
</html>