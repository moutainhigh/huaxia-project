<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<title>人行征信查询</title>
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
		<form id="frmList" method="post"  action="${basePath}/pboc/history/pagelist.do">
		<section class="search-layout horizontal">
			<table>
				<tr>
					<td><label>证件号码：<input type="text" id="cert_no" name="certNo" value="${page.map.certNo}" style="width:160px;" /></label></td>
				</tr>
			</table>
			<input id="btnReset" type="button" onclick="resetVal()" value="重置" />
			<input id="btnQuery" type="submit" value="查询" />
		</section>  
		<div id="History">
		<table border="1" class="table-layout" >
			<caption>人行征信历史数据列表</caption>
			<tr>
				<th width="10%">序号</th>
				<th width="20%">报告编号</th>
				<th width="20%">报告时间</th>
				<th width="20%">证件号 </th>
				<th width="20%">查询时间 </th>
			</tr>
			<c:forEach items="${records}" var="history" varStatus="sequence">
			<tr>
				<td>${sequence.count}</td>
				<td><a href="javascript:showDetail('${history.uniqueId}')">${history.reportNo}</a></td>
				<td>${history.reportTime}</td>
				<td>${history.certNo}</td>
				<td>${history.queryTime}</td>
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
var uniqueId="1856456";
$(function() {
	var showPage = "${showPage}";
	if(showPage == ""){
		$("#History").hide();
	}
	var info = "${info}";
	if(info != ""){
	$.messager.alert("提示", info, "warning");
	}
})
function page(p,s,e) {
	$('#pageNo').val(p);
	$('#pageSize').val(s);
	var f = $('#frmList');
	f.action = "${basePath}/pboc/history/pagelist.do";
	f.submit();
}

function resetVal(){
	$('#cert_no').val('');
	$('#cert_no').val('').focus();
}

function showDetail(id) {
	var w = window.open('about:blank');
	/******获取url地址中的参数account*************************/
	var url= parent.window.location.search;
	var theRequest=new Object();
	if(url.indexOf("?")!=-1){
		var str=url.substr(1);
		var strs=str.split("&");
	/* 	$.messager.alert("提示",str, "warning");
		$.messager.alert("提示",strs[0], "warning"); */
		for(var i=0;i<strs.length;i++){
			theRequest[strs[i].split("=")[0]]=(strs[i].split("=")[1]);
		}
	}
	var account=theRequest.account;//用户登录名 
	w.location.href = '${basePath}/pboc/report/queryResult.do?uniqueId=' + id+'&account='+account; 
}
</script>
</html>