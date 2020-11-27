<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>手机实名认证查询</title>
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
			action="${basePath}/unicom/phone/history/pagelist.do">
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
						<th width="10%">序号</th>
						<th width="10%">报告编号</th>
						<th width="10%">证件号</th>
						<th width="10%">手机号</th>
						<th width="10%">姓名</th>
						<th width="10%">查询结果</th>
						<th width="10%">查询时间</th>
						<th width="10%">操作时间</th>
						<th width="10%">操作用户工号</th>
					</tr>
					<c:forEach items="${records}" var="history" varStatus="sequence">
						<tr>
							<td>${sequence.count}</td>
							<td>${history.trnId}</td>
							<td> 
							<a href="javascript:void();" onclick='detail("${history.trnId}")'>${history.certNo}</a>
						</td>
							<td>${history.mobile}</td>
							<td>${history.name}</td>
							 <td><c:if test="${history.status =='1'}">
								成功</c:if> 
							<c:if test="${history.status =='2'}">
								失败
							</c:if></td>
							<td>${history.crtTime}</td>
							<td>${history.operateTime}</td>
							<td>${history.staffId}</td>
						</tr>
					</c:forEach>
				</table>
				<!-- 分页代码 & 开始 -->
				<c:if test="${page.totalCount > 0}">
					<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}"
						totalCount="${page.totalCount}" />
				</c:if>
				<input type="hidden" id="pageNo" name="pageNo" /> <input
					type="hidden" id="pageSize" name="pageSize" /> <input
					type="hidden" id="txtUserId" name="userId" />
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
	})
	function page(p, s, e) {
		$('#pageNo').val(p);
		$('#pageSize').val(s);
		var f = $('#frmList');
		f.action = "${basePath}/unicom/phone/history/page/list.do";
		f.submit();
	}
	function resetVal() {
		$('#cert_no').val('');
		$('#cert_no').val('').focus();
	}
	function detail(trnId) {
		window.open("${basePath}/unicom/phone/batch/view/detail.do?trnId="+ trnId,'_blank',
						'toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
	}
</script>
</html>