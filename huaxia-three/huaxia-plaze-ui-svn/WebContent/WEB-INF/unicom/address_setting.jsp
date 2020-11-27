<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>地址类信息查询数量限制</title>
<script type="text/javascript" src="${basePath}/js/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<form id="frmQuery" name="frmQuery">
		<section class="layout horizontal">
			<label>开始时间：</label><input id="txtStartTime" class="easyui-datebox" name="startTime" />
			<label>结束时间：</label><input id="txtEndTime" class="easyui-datebox" name="endTime" />
			<input id="btnReset" type="button" value="重置" />
			<input id="btnQuery" type="button" value="查询" />
		</section>
		<section class="table-layout">
			<fieldset>
				<!-- 001102 -->
				<legend>工作地址查询统计</legend>
				<table>
					<tr>
						<td><label>单条实际查询：</label></td>
						<td><input type="text" id="SHOW00110200" disabled="disabled" /></td>
						<td><label>审批实际查询：</label></td>
						<td><input type="text" id="SHOW00110210" disabled="disabled" /></td>
					</tr>
					<tr>
						<td><label>单条最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00110200_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00110200_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00110200_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00110200_btnSave" type="button" value="保存提交" /></td>
					</tr>
					<tr>
						<td><label>审批最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00110210_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00110210_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00110210_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00110210_btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<!-- 001103 -->
				<legend>居住地址查询统计</legend>
				<table>
					<tr>
						<td><label>单条实际查询：</label></td>
						<td><input type="text" id="SHOW00110300" disabled="disabled" /></td>
						<td><label>审批实际查询：</label></td>
						<td><input type="text" id="SHOW00110310" disabled="disabled" /></td>
					</tr>
					<tr>
						<td><label>单条最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00110300_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00110300_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00110300_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00110300_btnSave" type="button" value="保存提交" /></td>
					</tr>
					<tr>
						<td><label>审批最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00110310_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00110310_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00110310_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00110310_btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
</body>
<script type="text/javascript">

function checkVilide(type){
	if(type == 0){//工作地址  单条设置
		if (($("#TASK00110200_maxCount").val() == '')) {
			alert("单条查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00110200_startTime').datebox('getValue') == '')) {
			alert("单条设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00110200_endTime').datebox('getValue') == '')) {
			alert("单条设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 1){//工作地址 审批设置
		if (($("#TASK00110210_maxCount").val() == '')) {
			alert("审批查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00110210_startTime').datebox('getValue') == '')) {
			alert("审批设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00110210_endTime').datebox('getValue') == '')) {
			alert("审批设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 2){//居住地址 单条设置
		if (($("#TASK00110300_maxCount").val() == '')) {
			alert("单条查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00110300_startTime').datebox('getValue') == '')) {
			alert("单条设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00110300_endTime').datebox('getValue') == '')) {
			alert("单条设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 3){//居住地址 审批设置
		if (($("#TASK00110310_maxCount").val() == '')) {
			alert("审批查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00110310_startTime').datebox('getValue') == '')) {
			alert("审批设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00110310_endTime').datebox('getValue') == '')) {
			alert("审批设置数量的结束时间不能为空");
			return null;
		}
	}
	return 0;
} 
Date.prototype.format = function(format) {
	var o = {
		'M+' : this.getMonth() + 1, // Month
		'd+' : this.getDate(),      // Day
		'h+' : this.getHours(),     // Hour
		'm+' : this.getMinutes(),   // Minute
		's+' : this.getSeconds(),   // Second
		'q+' : Math.floor((this.getMonth() + 3) / 3), // Quarter
		'S+' : this.getMilliseconds()                 // Millisecond
	};
	
	if (/(y+)/i.test(format)) {
		format = format.replace(RegExp.$1,(this.getFullYear() + '').substr(4 - RegExp.$1.length));
	}
	return format;
}
function fnInitLoad() {
	/** 联通地址类信息    已查询数量统计 */
	$.ajax({
		url : "${basePath}/unicom/address/single/setting/query/count.do",
		type : 'post',
		data : {
			'startTime' : (new Date().format('yyyy') + '-01-01'),
			'endTime' : (new Date().format('yyyy') + '-12-31')
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				// 工作地址       单条已查询查询
				$('#SHOW00110200').val(response.TASK00110200.TASK_COUNT);
				// 工作地址      审批已查询数量
				$('#SHOW00110210').val(response.TASK00110210.TASK_COUNT);
				
				// 居住地址     单条已查询数量
				$('#SHOW00110300').val(response.TASK00110300.TASK_COUNT);
				// 居住地址     审批已查询数量
				$('#SHOW00110310').val(response.TASK00110310.TASK_COUNT);
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
	/** 联通地址类信息     查询数量配置 */
	$.ajax({
		url : "${basePath}/unicom/address/single/setting/query/configure.do",
		type : 'post',
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				if(response.TASK00110200 != undefined){
				// 工作地址    单条最大查询
				$('#TASK00110200_maxCount').val(response.TASK00110200.maxCount);
				$('#TASK00110200_startTime').datebox('setValue', response.TASK00110200.startTimeString);
				$('#TASK00110200_endTime').datebox('setValue', response.TASK00110200.endTimeString);
				}
				if(response.TASK00110210 != undefined){
				// 工作地址   审批最大查询				
				$('#TASK00110210_maxCount').val(response.TASK00110210.maxCount);
				$('#TASK00110210_startTime').datebox('setValue', response.TASK00110210.startTimeString);
				$('#TASK00110210_endTime').datebox('setValue', response.TASK00110210.endTimeString);
				}
				if(response.TASK00110300 != undefined){
				// 居住地址 单条最大查询				
				$('#TASK00110300_maxCount').val(response.TASK00110300.maxCount);
				$('#TASK00110300_startTime').datebox('setValue', response.TASK00110300.startTimeString);
				$('#TASK00110300_endTime').datebox('setValue', response.TASK00110300.endTimeString);
				}
				if(response.TASK00110310 != undefined){
				// 居住地址 审批最大查询				
				$('#TASK00110310_maxCount').val(response.TASK00110310.maxCount);
				$('#TASK00110310_startTime').datebox('setValue', response.TASK00110310.startTimeString);
				$('#TASK00110310_endTime').datebox('setValue', response.TASK00110310.endTimeString);
				}
				
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
}
/**
 * 更新 查询数量设置
 */
function fnSettingSave(source, channel, max, start, end) {
	$.ajax({
		url : "${basePath}/unicom/address/single/setting/query/save.do",
		type : 'post',
		contentType : 'application/json;charset=utf-8',
		data : JSON.stringify({
			'sourceCode' : source,
			'channelCode' : channel,
			'maxCount' : max,
			'startTime' : start,
			'endTime' : end
		}),
		dataType : "json",
		success : function(response, status) {
			if (response.result) {
				alert(response.message);
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
}
$(function() {
	/** 初始加载统计 */
	fnInitLoad();
	/** 筛选条件重置 */
	$('#btnReset').on('click', function() {
		$('#txtStartTime').datebox('setValue', '').focus();
		$('#txtEndTime').datebox('setValue', '');
	});
	/** 工作地址  手动单条查询保存 */
	$('#TASK00110200_btnSave').on('click', function() {
		if(checkVilide(0) != null){
			var maxCount = $('#TASK00110200_maxCount').val();
			var startTime = $('#TASK00110200_startTime').datebox('getValue');
			var endTime = $('#TASK00110200_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				fnSettingSave('001102', '00',maxCount, startTime, endTime);
			}else{
				alert('请输入正确的数量【0-99999999】');
				return ;
			}
		}
	});
	/** 工作地址 手动审批查询保存 */
	$('#TASK00110210_btnSave').on('click', function() {
		if(checkVilide(1) != null){
			var maxCount = $('#TASK00110210_maxCount').val();
			var startTime = $('#TASK00110210_startTime').datebox('getValue');
			var endTime = $('#TASK00110210_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				fnSettingSave('001102','10', maxCount, startTime, endTime);
			}else{
				alert('请输入正确的数量【0-99999999】');
				return ;
			}
		}
	});
	/** 居住地址 手动单条查询数量设置保存 */
	$('#TASK00110300_btnSave').on('click', function() {
		if(checkVilide(2) != null){
			var maxCount = $('#TASK00110300_maxCount').val();
			var startTime = $('#TASK00110300_startTime').datebox('getValue');
			var endTime = $('#TASK00110300_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				fnSettingSave('001103', '00',maxCount, startTime, endTime);
			}else{
				alert('请输入正确的数量【0-99999999】');
				return ;
			}
		}
	});
	/** 居住地址 审批查询数量限制保存 */
	$('#TASK00110310_btnSave').on('click', function() {
		if(checkVilide(3) != null){
			var maxCount = $('#TASK00110310_maxCount').val();
			var startTime = $('#TASK00110310_startTime').datebox('getValue');
			var endTime = $('#TASK00110310_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				fnSettingSave('001103', '10',maxCount, startTime, endTime);
			}else{
				alert('请输入正确的数量【0-99999999】');
				return ;
			}
		}
	});
	/** 查询条数统计 */
	$('#btnQuery').on('click', function() {
		var queryStartTime = $('#txtStartTime').datebox('getValue');
		if (queryStartTime == undefined || queryStartTime == '') {
			alert('筛选条件"开始时间"为必输项!');
			return;
		}
		var queryEndTime = $('#txtEndTime').datebox('getValue');
		if (queryEndTime == undefined || queryEndTime == '') {
			alert('筛选条件"结束时间"为必输项!');
			return;
		}
		$.ajax({
			url : "${basePath}/unicom/address/single/setting/query/count.do",
			type : 'post',
			data : {
				'startTime' : $('#txtStartTime').datebox('getValue'),
				'endTime' : $('#txtEndTime').datebox('getValue')
			},
			dataType : "json",
			success : function(response, status) {
				if (status == 'success') {
					// 工作地址       单条已查询查询
					$('#SHOW00110200').val(response.TASK00110200.TASK_COUNT);
					// 工作地址      审批已查询数量
					$('#SHOW00110210').val(response.TASK00110210.TASK_COUNT);
					
					// 居住地址     单条已查询数量
					$('#SHOW00110300').val(response.TASK00110300.TASK_COUNT);
					// 居住地址     审批已查询数量
					$('#SHOW00110310').val(response.TASK00110310.TASK_COUNT);
				}
			},
			error : function(error, status) {
				alert('交易请求异常,状态码[' + status + ']');
			}
		});
	});
});
</script>
</html>