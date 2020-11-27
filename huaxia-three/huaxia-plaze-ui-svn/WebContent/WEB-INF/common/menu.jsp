<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>第三方查询模块</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/normalize-layout-form.css"></link>
<link rel="stylesheet" type="text/css" href="${basePath}/css/common/page.css">
<script type="text/javascript" src="${basePath}/js/framework.js"></script>
<script type="text/javascript" src="${basePath}/js/framework.util.js"></script>
</head>
<body>
	<form id="frmList" method="post">
		<section class="layout horizontal">
			<label>菜单名称：<input type="text" id="txtName" name="name" value="${page.map.name}" /></label>
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>
		<table border="1" class="table-layout">
			<caption>菜单列表</caption>
			<tr>
				<th width="5%">选择</th>
				<th width="95%">菜单名称</th>
			</tr>
			<c:forEach items="${records}" var="menu" varStatus="sequence">
			<tr>
				<td><input type="radio" name="id" value="${menu.id}" title="${menu.name}" /></td>
				<td style="text-align:left;">${menu.name}</td>
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
		<section class="layout horizontal">
			<input type="button" id="btnClose" value="关闭窗口">
			<input type="button" id="btnChoose" value="确认选择">
		</section>
	</form>
</body>
<script type="text/javascript">
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/common/menu/pageList.do";
	f.submit();
}
$(function() {
	$('#btnReset').on('click',function() {
		$('#txtName').val('').focus();
	});
	$('#btnClose').on('click',function() {
		window.close();
	});
	$('#btnChoose').on('click', function() {
		if ($('input[name="id"]:checked').length == 0) {
			alert('请选择一条记录!');
			return;
		}
		
		var id = $('input[name="id"]:checked').val();
		var name = $('input[name="id"]:checked').attr('title');
		window.opener.choose({'id':id,'name':name});
		window.close();
	});
});
</script>
</html>