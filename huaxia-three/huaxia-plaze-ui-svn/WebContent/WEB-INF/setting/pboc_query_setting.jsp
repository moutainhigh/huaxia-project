<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>第三方查询模块</title>
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
				<legend>人行征信手动查询统计</legend>
				<table>
					<tr>
						<td><label>单条实际查询：</label></td>
						<td><input type="text" id="SHOW00010000" disabled="disabled" /></td>
						<td><label>批量实际查询：</label></td>
						<td><input type="text" id="SHOW00010001" disabled="disabled" /></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>人行征信渠道查询统计</legend>
				<table>
					<tr>
						<td><label>审批实际查询：</label></td>
						<td><input type="text" id="SHOW00010010" disabled="disabled" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
	<form id="frmSetting" name="frmSetting">
		<section class="table-layout">
			<fieldset>
				<legend>手动查询数量设置</legend>
				<table>
					<tr>
						<td><label>单条最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00010000_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00010000_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00010000_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00010000_btnSave" type="button" value="保存提交" /></td>
					</tr>
					<tr>
						<td><label>批量最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00010001_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00010001_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00010001_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00010001_btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>渠道查询数量设置（审批系统）</legend>
				<table>
					<tr>
						<td><label>最大查询条数：</label></td>
						<td colspan="3"><input type="text" id="TASK00010010_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00010010_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00010010_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00010010_btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
</body>
<script type="text/javascript">
function checkVilide(type){
	if(type == 0){
		if (($("#TASK00010000_maxCount").val() == '')) {
			alert("单条查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00010000_startTime').datebox('getValue') == '')) {
			alert("单条设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00010000_endTime').datebox('getValue') == '')) {
			alert("单条设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 1){
		if (($("#TASK00010001_maxCount").val() == '')) {
			alert("批量查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00010001_startTime').datebox('getValue') == '')) {
			alert("批量设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00010001_endTime').datebox('getValue') == '')) {
			alert("批量设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 2){
		if (($("#TASK00010010_maxCount").val() == '')) {
			alert("审批查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00010010_startTime').datebox('getValue') == '')) {
			alert("审批设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00010010_endTime').datebox('getValue') == '')) {
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
	
	/*
	for (var i in o) {
		if (new RegExp('(' + i + ')').test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[i] : ('00' + o[i]).substr(('' + o[i]).length));
		}
	}
	*/
	
	return format;
}
function fnInitLoad() {
	/** 人行征信数量查询 */
	$.ajax({
		url : "${basePath}/setting/pboc/query/count.do",
		type : 'post',
		data : {
			'startTime' : (new Date().format('yyyy') + '-01-01'),
			'endTime' : (new Date().format('yyyy') + '-12-31')
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				// 单条最大查询
				$('#SHOW00010000').val(response.TASK00010000.TASK_COUNT);
				
				// 批量最大查询
				$('#SHOW00010001').val(response.TASK00010001.TASK_COUNT);
				
				// 审批系统渠道最大查询
				$('#SHOW00010010').val(response.TASK00010010.TASK_COUNT);
			}
		},
		error : function(error, status) {
			alert('交易请求异常,状态码[' + status + ']');
		}
	});
	/** 人行征信配置查询 */
	$.ajax({
		url : "${basePath}/setting/pboc/query/configure.do",
		type : 'post',
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				if(response.TASK00010000 != undefined){
				// 单条最大查询
				$('#TASK00010000_maxCount').val(response.TASK00010000.maxCount);
				$('#TASK00010000_startTime').datebox('setValue', response.TASK00010000.startTimeString);
				$('#TASK00010000_endTime').datebox('setValue', response.TASK00010000.endTimeString);
				}
				if(response.TASK00010001 != undefined){
				// 批量最大查询				
				$('#TASK00010001_maxCount').val(response.TASK00010001.maxCount);
				$('#TASK00010001_startTime').datebox('setValue', response.TASK00010001.startTimeString);
				$('#TASK00010001_endTime').datebox('setValue', response.TASK00010001.endTimeString);
				}
				if(response.TASK00010010 != undefined){
				// 审批系统渠道最大查询				
				$('#TASK00010010_maxCount').val(response.TASK00010010.maxCount);
				$('#TASK00010010_startTime').datebox('setValue', response.TASK00010010.startTimeString);
				$('#TASK00010010_endTime').datebox('setValue', response.TASK00010010.endTimeString);
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
		url : "${basePath}/setting/pboc/query/configure/save.do",
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
	/** 手动单条查询保存 */
	$('#TASK00010000_btnSave').on('click', function() {
		if(checkVilide(0) != null){
			var maxCount = $('#TASK00010000_maxCount').val();
			var startTime = $('#TASK00010000_startTime').datebox('getValue');
			var endTime = $('#TASK00010000_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				fnSettingSave('000100', '00',maxCount, startTime, endTime);
			}else{
				alert('请输入正确的数量【0-99999999】');
				return ;
			}
		}	
	});
	/** 手动批量查询保存 */
	$('#TASK00010001_btnSave').on('click', function() {
		if(checkVilide(1) != null){
			var maxCount = $('#TASK00010001_maxCount').val();
			var startTime = $('#TASK00010001_startTime').datebox('getValue');
			var endTime = $('#TASK00010001_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				fnSettingSave('000100','01', maxCount, startTime, endTime);
			}else{
				alert('请输入正确的数量【0-99999999】');
				return ;
			}
		}	
	});
	/** 审批渠道查询配置保存 */
	$('#TASK00010010_btnSave').on('click', function() {
		
		if(checkVilide(2) != null){
			var maxCount = $('#TASK00010010_maxCount').val();
			if (maxCount == undefined || maxCount == '') {
				alert('"最大查询条数"为必输项!');
				return false;
			}
			var startTime = $('#TASK00010010_startTime').datebox('getValue');
			var endTime = $('#TASK00010010_endTime').datebox('getValue');
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					alert("开始时间不能大于结束时间");
					return;
				}
				
				if (startTime == undefined || startTime == '' || endTime == undefined || endTime == '') {
					alert('"生效开始时间"或"生效结束时间"为必输项!');
					return false;
				}
				fnSettingSave('000100','10', maxCount, startTime, endTime);
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
			url : "${basePath}/setting/pboc/query/count.do",
			type : 'post',
			data : {
				'startTime' : $('#txtStartTime').datebox('getValue'),
				'endTime' : $('#txtEndTime').datebox('getValue')
			},
			dataType : "json",
			success : function(response, status) {
				if (status == 'success') {
					// 单条最大查询
					$('#SHOW00010000').val(response.TASK00010000.TASK_COUNT);
					
					// 批量最大查询
					$('#SHOW00010001').val(response.TASK00010001.TASK_COUNT);
					
					// 审批系统渠道最大查询
					$('#SHOW00010010').val(response.TASK00010010.TASK_COUNT);
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