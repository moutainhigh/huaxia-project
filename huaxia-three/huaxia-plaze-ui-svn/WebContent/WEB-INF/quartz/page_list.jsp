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
			<label>任务名称：<input type="text" id="jobName" name="jobName" value="${page.map.jobName}" /></label>
			<label>状态：
			<select id="turn" name="turn">
			<option value="">请选择</option>
			<option value="1">开启</option>
			<option value="2">关闭</option>
			</select></label>
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>
		<table border="1" class="table-layout">
			<caption>任务列表</caption>
			<tr>
				<th width="5%">序号</th>
				<th width="15%">任务名称</th>
				<th width="15%">运行时间</th>
				<th width="5%">状态</th>
				<th width="15%">任务简称</th>
				<th width="15%">创建时间</th>
				<th width="15%">修改时间</th>
				<th width="15%">操作</th>
			</tr>
			<c:forEach items="${data}" var="quartz" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td>${quartz.jobName}</td>
				<td>${quartz.runTime}</td>
				<td>
				<c:choose>
					<c:when test="${quartz.turn == '1'}">开启</c:when>
					<c:when test="${quartz.turn == '2'}">关闭</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
				</td>
				<td>${quartz.jobJc}</td>
				<td>${quartz.crtTime}</td>
				<td>${quartz.updTime}</td>
				<td>
					<a href="javascript:void();" onclick='fnModifyUser("${quartz.uuid}")'>修改</a>&nbsp;|&nbsp;
					<a href="javascript:void();" onclick='fnRemoveUser("${quartz.uuid}")'>删除</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<!-- 分页代码 & 开始 -->
		<c:if test="${page.totalCount > 0}">
			<page:show pageNo="${page.pageNo}" pageSize="${page.pageSize}" totalCount="${page.totalCount}" />
		</c:if>
		<input type="hidden" id="pageNo" name="pageNo" />
		<input type="hidden" id="pageSize" name="pageSize" />
		<input type="hidden" id="uuid" name="uuid" />
		<!-- 分页代码 & 结束 -->
		</form>
		<form id="frmSave" method="post">
			<section class="layout horizontal">
				<input type="button" id="btnAdd" value="添加">
			</section>
		</form>
	</div>
</body>
<script type="text/javascript">
function refresh() {
	var f = $('#frmList');
	f.action = "${basePath}/batch/quartz/pageList.do";
	f.submit();
}
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/batch/quartz/pageList.do";
	f.submit();
}
function fnModifyUser(id) {
	window.open("${basePath}/batch/quartz/query.do?id=" + id,'_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
}
function fnRemoveUser(id) {
	if (confirm('确定删除该记录?')) {
		$.ajax({
			url : "${basePath}/batch/quartz/delete.do",
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
	$('#turn').val("${page.map.turn}");
	$('#btnReset').on('click', function() {
		$('#jobName').val('');
		$('#turn option[value=""]').attr('selected', true);
	});
	$('#btnQuery').on('click', function() {
		$('#pageNo').val('1');
	});
	$('#btnAdd').on('click', function() {
		window.open('${basePath}/batch/quartz/add.do','_blank','toolbar=yes,location=yes,directories=no,status=no,menubar=yes,scrollbars=yes,resiable=no,copyhistory=yes,width=600,height=600');
	});
});
</script>
</html>