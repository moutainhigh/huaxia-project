<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>公安人像比对</title>
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
				<legend>公安人像比对查询统计</legend>
				<table>
					<tr>
						<td><label>审批实际查询：</label></td>
						<td><input type="text" id="SHOW00020110" disabled="disabled" /></td>
						<td><label>人脸识别查询：</label></td>
						<td><input type="text" id="SHOW00020113" disabled="disabled" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
	<form id="frmSetting" name="frmSetting">
		<section class="table-layout">
			<fieldset>
				<legend>渠道查询数量设置（审批系统）</legend>
				<table>
					<tr>
						<td><label>最大查询条数：</label></td>
						<td colspan="3"><input type="text" id="TASK00020110_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00020110_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00020110_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00020110_btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
	<form id="frmSetting2" name="frmSetting2">
		<section class="table-layout">
			<fieldset>
				<legend>渠道查询数量设置（人脸识别系统）</legend>
				<table>
					<tr>
						<td><label>最大查询条数：</label></td>
						<td colspan="3"><input type="text" id="TASK00020113_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00020113_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00020113_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00020113_btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
</body>
<script type="text/javascript">
function checkVilide(type){
	if(type == 0){
		if (($("#TASK00020100_maxCount").val() == '')) {
			alert("单条查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00020100_startTime').datebox('getValue') == '')) {
			alert("单条设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00020100_endTime').datebox('getValue') == '')) {
			alert("单条设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 1){
		if (($("#TASK00020101_maxCount").val() == '')) {
			alert("批量查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00020101_startTime').datebox('getValue') == '')) {
			alert("批量设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00020101_endTime').datebox('getValue') == '')) {
			alert("批量设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 2){
		if (($("#TASK00020110_maxCount").val() == '')) {
			alert("审批查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00020110_startTime').datebox('getValue') == '')) {
			alert("审批设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00020110_endTime').datebox('getValue') == '')) {
			alert("审批设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 3){
		if (($("#TASK00020113_maxCount").val() == '')) {
			alert("人脸识别系统的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00020113_startTime').datebox('getValue') == '')) {
			alert("人脸识别系统设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00020113_endTime').datebox('getValue') == '')) {
			alert("人脸识别系统设置数量的结束时间不能为空");
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
	/** 人像数量查询 */
	$.ajax({
		url : "${basePath}/nciic/xp/configurate/query/count.do",
		type : 'post',
		data : {
			'startTime' : (new Date().format('yyyy') + '-01-01'),
			'endTime' : (new Date().format('yyyy') + '-12-31')
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				// 审批系统渠道最大查询
				$('#SHOW00020110').val(response.TASK00020110.TASK_COUNT);
				$('#SHOW00020113').val(response.TASK00020113.TASK_COUNT);
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
	/** 人像配置查询 */
	$.ajax({
		url : "${basePath}/nciic/xp/configurate/query/configure.do",
		type : 'post',
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				if(response.TASK00020110 != undefined){
				// 审批系统渠道最大查询				
				$('#TASK00020110_maxCount').val(response.TASK00020110.maxCount);
				$('#TASK00020110_startTime').datebox('setValue', response.TASK00020110.startTimeString);
				$('#TASK00020110_endTime').datebox('setValue', response.TASK00020110.endTimeString);
				}
				if(response.TASK00020113 != undefined){
					//人脸识别系统渠道最大查询				
					$('#TASK00020113_maxCount').val(response.TASK00020113.maxCount);
					$('#TASK00020113_startTime').datebox('setValue', response.TASK00020113.startTimeString);
					$('#TASK00020113_endTime').datebox('setValue', response.TASK00020113.endTimeString);
				}
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
}
function fnSettingSave(source, channel, max, start, end) {
	$.ajax({
		url : "${basePath}/nciic/xp/configurate/query/configure/save.do",
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
	/** 审批渠道查询配置保存 */
	$('#TASK00020110_btnSave').on('click', function() {
		if(checkVilide(2) != null){
			var maxCount = $('#TASK00020110_maxCount').val();
			if (maxCount == undefined || maxCount == '') {
				alert('"最大查询条数"为必输项!');
				return false;
			}
			var startTime = $('#TASK00020110_startTime').datebox('getValue');
			var endTime = $('#TASK00020110_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				
				if (startTime == undefined || startTime == '' || endTime == undefined || endTime == '') {
					alert('"生效开始时间"或"生效结束时间"为必输项!');
					return false;
				}
				fnSettingSave('000201','10', maxCount, startTime, endTime);
			}else{
				alert('请输入正确的数量【0-99999999】');
				return ;
			}
		}		
	});
	/** 人脸识别渠道查询配置保存 */
	$('#TASK00020113_btnSave').on('click', function() {
		if(checkVilide(3) != null){
			var maxCount = $('#TASK00020113_maxCount').val();
			if (maxCount == undefined || maxCount == '') {
				alert('"最大查询条数"为必输项!');
				return false;
			}
			var startTime = $('#TASK00020113_startTime').datebox('getValue');
			var endTime = $('#TASK00020113_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				
				if (startTime == undefined || startTime == '' || endTime == undefined || endTime == '') {
					alert('"生效开始时间"或"生效结束时间"为必输项!');
					return false;
				}
				fnSettingSave('000201','13', maxCount, startTime, endTime);
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
			url : "${basePath}/nciic/xp/configurate/query/count.do",
			type : 'post',
			data : {
				'startTime' : $('#txtStartTime').datebox('getValue'),
				'endTime' : $('#txtEndTime').datebox('getValue')
			},
			dataType : "json",
			success : function(response, status) {
				if (status == 'success') {
					// 审批系统渠道最大查询
					$('#SHOW00020110').val(response.TASK00020110.TASK_COUNT);
					// 人脸识别系统渠道最大查询
					$('#SHOW00020113').val(response.TASK00020113.TASK_COUNT);
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