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
<link rel="stylesheet" type="text/css" href="${basePath}/css/common/page.css">
</head>
<body>
	<div>
		<form id="frmList" method="post">
			<section class="layout horizontal">
				<label>菜单名称：<input type="text" id="txtName" name="name" value="${page.map.name}" /></label>
				<input id="btnReset" type="button" value="重置" />
				<input id="btnQuery" type="submit" value="查询" />
			</section>
			<table border="1" class="table-layout">
				<caption>菜单列表</caption>
				<tr>
					<th width="5%">序号</th>
					<th width="22%">菜单编号</th>
					<th width="15%">菜单名称</th>
					<th width="25%">菜单地址</th>
					<th width="8%">菜单等级</th>
					<th width="15%">所属菜单</th>
					<th width="10%">操作</th>
				</tr>
				<c:forEach items="${records}" var="menu" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td>${menu.id}</td>
					<td style="text-align:left;">${menu.name}</td>
					<td style="text-align:left;">${menu.link}</td>
					<td>${menu.level}</td>
					<td style="text-align:left;">${menu.pname}</td>
					<td><a href="javascript:void();" onclick='fnModifyMenu("${menu.id}")'>修改</a></td>
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
				<input type="button" id="btnAdd" value="添加菜单">
			</section>
		</form>
	</div>
</body>
<script type="text/javascript">
function refresh() {
	var f = $('#frmList');
	f.action = "${basePath}/system/menu/pageList.do";
	f.submit();
}
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/system/menu/pageList.do";
	f.submit();
}
function fnModifyMenu(id) {
	window.open("${basePath}/system/menu/get.do?id=" + id,'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
}
function fnRemoveMenu(id) {
	if (confirm('确定删除该记录?')) {
		$.ajax({
			url : "${basePath}/system/menu/remove.do",
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
		$('#txtName').val('').focus();
	});
	$('#btnQuery').on('click', function() {
		$('#pageNo').val('1');
	});
	$('#btnAdd').on('click', function() {
		window.open("${basePath}/system/menu/add.do",'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
	});
});
</script>
</html>