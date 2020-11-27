<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>第三方查询模块</title>
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
		<form id="frmList" method="post">
		<section class="layout horizontal">
			<label>登录名称：<input type="text" id="txtAccount" name="account" value="${page.map.account}" /></label>
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>
		<table border="1" class="table-layout">
			<caption>人行查询排除列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="10%">登录名称</th>
				<th width="10%">用户姓名</th>
				<th width="10%">查询开始日期</th>
				<th width="10%">查询结束日期</th>
				<th width="10%">查询时间</th>
				<th width="40%">查询事由</th>
				<th width="5%">操作</th>
			</tr>
			<c:forEach items="${records}" var="record" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td>${record.account}</td>
				<td>${record.staffName}</td>
				<td>${record.excludeStartDate}</td>
				<td>${record.excludeEndDate}</td>
				<td>${record.excludeTime}</td>
				<td style="text-align:left;">${record.excludeCause}</td>
				<td><a href="javascript:void();" onclick='fnRemove("${record.queryId}")'>删除</a></td>
			</tr>
			</c:forEach>
		</table>
		<!-- 分页代码 & 开始 -->
		<c:if test="${page.totalCount > 0}">
			<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}" totalCount="${page.totalCount}" />
		</c:if>
		<input type="hidden" id="pageNo" name="pageNo" />
		<input type="hidden" id="pageSize" name="pageSize" />
		<!-- 分页代码 & 结束 -->
		</form>
		<form id="frmSave" method="post">
			<section class="layout horizontal">
				<input type="button" id="btnAdd" value="添加例外">
			</section>
		</form>
	</div>
</body>
<script type="text/javascript">
function refresh() {
	var f = $('#frmList');
	f.action = "${basePath}/setting/query/exclude/pageList.do";
	f.submit();
}
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/setting/query/exclude/pageList.do";
	f.submit();
}
function fnRemove(id) {
	if (confirm('确定删除该记录?')) {
		$.ajax({
			url : "${basePath}/setting/query/exclude/remove.do",
			type : 'post',
			data : {
				'id' : id,
			},
			dataType : "json",
			success : function(response, status) {
				if (response.result) {
					alert(response.message);
					refresh();
				} else {
					alert(response.message);
				}
			},
			error : function(error, status) {
				alert(error + '=' + status);
			}
		});
	}
}
$(function() {
	$('#btnReset').on('click', function() {
		$('#txtAccount').val('').focus();
	});
	$('#btnAdd').on('click', function() {
		window.open("${basePath}/setting/query/exclude/add.do",'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
	});
});
</script>
</html>