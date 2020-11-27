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
</style>
</head>
<body>
	<div>
		<form id="frmList" method="post">
		<section class="layout horizontal">
			<label>团队名称：<input type="text" id="team_team_name" name="teamName" value="${page.map.teamName}" /></label>
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>
		<table border="1" class="table-layout">
			<caption>团队列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="30%">团队名称</th>
				<th width="35%">团队描述</th>
				<th width="10%">团队状态</th>
				<th width="20%">操作</th>
			</tr>
			<c:forEach items="${records}" var="record" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td style="text-align:left;">${record.teamName}</td>
				<td style="text-align:left;">${record.teamDesc}</td>
				<td>
			    <c:if test="${record.teamStatus=='0'}">禁用</c:if>
				<c:if test="${record.teamStatus=='1'}">启动</c:if>
				</td>
				<td><a href="javascript:void();" onclick='fnModifyTeam("${record.teamId}")'>修改</a>
				<c:if test="${record.teamStatus == '1'}">&nbsp;|&nbsp;
				<a href="javascript:void();" onclick='fnRemoveTeam("${record.teamId}")'>删除</a>
				</c:if></td>
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
		</form>
		<form id="frmSave" method="post">
			<section class="layout horizontal">
				<input type="button" id="btnAdd" value="添加团队">
			</section>
		</form>
	</div>
</body>
<script type="text/javascript">
function refresh() {
	var f = $('#frmList');
	f.action = "${basePath}/system/team/pageList.do";
	f.submit();
}
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/system/team/pageList.do";
	f.submit();
}
function fnModifyTeam(id) {
	window.open("${basePath}/system/team/get.do?id=" + id,'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
}
function fnRemoveTeam(id) {
	if (confirm('确定删除该记录?')) {
		$.ajax({
			url : "${basePath}/system/team/remove.do",
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
		$('#team_team_name').val('').focus();
	});
	$('#btnQuery').on('click', function() {
		$('#pageNo').val('1');
	});
	$('#btnAdd').on('click', function() {
		window.open('${basePath}/system/team/add.do','_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
	});
});
</script>
</html>