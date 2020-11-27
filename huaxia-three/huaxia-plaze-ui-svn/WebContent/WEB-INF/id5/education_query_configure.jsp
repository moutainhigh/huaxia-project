<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>学历信息查询</title>
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
				<legend>学历手动查询统计</legend>
				<table>
					<tr>
						<td><label>单条实际查询：</label></td>
						<td><input type="text" id="SHOW00030000" disabled="disabled" /></td>
						<td><label>批量实际查询：</label></td>
						<td><input type="text" id="SHOW00030001" disabled="disabled" /></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>学历渠道查询统计</legend>
				<table>
					<tr>
						<td><label>审批实际查询：</label></td>
						<td><input type="text" id="SHOW00030010" disabled="disabled" /></td>
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
						<td colspan="3"><input type="text" id="TASK00030000_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00030000_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00030000_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00030000_btnSave" type="button" value="保存提交" /></td>
					</tr>
					<tr>
						<td><label>批量最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00030001_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00030001_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00030001_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00030001_btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
		<section class="table-layout">
			<fieldset>
				<legend>渠道查询数量设置</legend>
				<table>
					<tr>
						<td><label>审批最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00030010_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00030010_startTime" class="easyui-datebox" style="width:146px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00030010_endTime" class="easyui-datebox" style="width:146px" /></td>
						<td><input id="TASK00030010_btnSave" type="button" value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
</body>
<script type="text/javascript">
function checkVilide(type){
	if(type == 0){
		if (($("#TASK00030000_maxCount").val() == '')) {
			$.messager.alert("提示","单条查询的设置最大数量不能为空", "warning");
			return null;
		}
		if (($('#TASK00030000_startTime').datebox('getValue') == '')) {
			$.messager.alert("提示","单条设置数量的开始时间不能为空", "warning");
			return null;
		}
		if (($('#TASK00030000_endTime').datebox('getValue') == '')) {
			$.messager.alert("提示","单条设置数量的结束时间不能为空", "warning");
			return null;
		}
	}
	if(type == 1){
		if (($("#TASK00030001_maxCount").val() == '')) {
			$.messager.alert("提示","批量查询的设置最大数量不能为空", "warning");
			return null;
		}
		if (($('#TASK00030001_startTime').datebox('getValue') == '')) {
			$.messager.alert("提示","批量设置数量的开始时间不能为空", "warning");
			return null;
		}
		if (($('#TASK00030001_endTime').datebox('getValue') == '')) {
			$.messager.alert("提示","批量设置数量的结束时间不能为空", "warning");
			return null;
		}
	}
	if(type == 2){
		if (($("#TASK00030010_maxCount").val() == '')) {
			$.messager.alert("提示","审批查询的设置最大数量不能为空", "warning");
			return null;
		}
		if (($('#TASK00030010_startTime').datebox('getValue') == '')) {
			$.messager.alert("提示","审批设置数量的开始时间不能为空", "warning");
			return null;
		}
		if (($('#TASK00030010_endTime').datebox('getValue') == '')) {
			$.messager.alert("提示","审批设置数量的结束时间不能为空", "warning");
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
	
	/** 接口查询数量统计 */
	$.ajax({
		url : "${basePath}/id5/edu/configurate/query/count.do",
		type : 'post',
		data : {
			'startTime' : (new Date().format('yyyy') + '-01-01'),
			'endTime' : (new Date().format('yyyy') + '-12-31')
		},
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				// 单条最大查询
				$('#SHOW00030000').val(response.TASK00030000.TASK_COUNT);
				
				// 批量最大查询
				$('#SHOW00030001').val(response.TASK00030001.TASK_COUNT);
				
				// 审批系统渠道最大查询
				$('#SHOW00030010').val(response.TASK00030010.TASK_COUNT);
			}
		},
		error : function(error, status) {
			$.messager.alert("提示",'交易请求异常,状态码[' + status + ']', "warning");
		}
	});
	
	/**学历接口配置显示 */
	$.ajax({
		url : "${basePath}/id5/edu/configurate/showConfig.do",
		type : 'post',
		dataType : "json",
		success : function(response, status) {
			if (status == 'success') {
				if(response.TASK00030000 != undefined){
					// 单条最大查询
					$('#TASK00030000_maxCount').val(response.TASK00030000.maxCount);
					$('#TASK00030000_startTime').datebox('setValue', response.TASK00030000.startTimeString);
					$('#TASK00030000_endTime').datebox('setValue', response.TASK00030000.endTimeString);
					
				}
				if(response.TASK00030001 != undefined){
				// 批量最大查询				
				$('#TASK00030001_maxCount').val(response.TASK00030001.maxCount);
				$('#TASK00030001_startTime').datebox('setValue', response.TASK00030001.startTimeString);
				$('#TASK00030001_endTime').datebox('setValue', response.TASK00030001.endTimeString);
				}
				if(response.TASK00030010 != undefined){
				// 审批最大查询				
				$('#TASK00030010_maxCount').val(response.TASK00030010.maxCount);
				$('#TASK00030010_startTime').datebox('setValue', response.TASK00030010.startTimeString);
				$('#TASK00030010_endTime').datebox('setValue', response.TASK00030010.endTimeString);
				}
			}
		},
		error : function(error, status) {
			$.messager.alert("提示",'交易请求异常,状态码[' + status + ']', "warning");
		}
	});
}
function fnSettingSave(source, channel, max, start, end) {
	$.ajax({
		url : "${basePath}/id5/edu/configurate/save.do",
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
				$.messager.alert("提示",response.message, "info");
			}
		},
		error : function(error, status) {
			$.messager.alert("提示",'交易请求异常,状态码[' + status + ']', "warning");
			
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
		fnInitLoad();
	});
	/** 手动单条查询保存 */
	$('#TASK00030000_btnSave').on('click', function() {
		if(checkVilide(0) != null){
			var maxCount = $('#TASK00030000_maxCount').val();
			var startTime = $('#TASK00030000_startTime').datebox('getValue');
			var endTime = $('#TASK00030000_endTime').datebox('getValue');
			var numberTest = new RegExp("^\d{n}$");
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					$.messager.alert("提示","开始时间不能大于结束时间", "warning");
					fnInitLoad();
					return;
				}
				fnSettingSave('000300', '00',maxCount, startTime, endTime);
			}else{
				$.messager.alert("提示",'请输入正确的数量【0-99999999】', "warning");
				fnInitLoad();
				return ;
			}
		}
	});
	/** 手动批量查询保存 */
	$('#TASK00030001_btnSave').on('click', function() {
		if(checkVilide(1) != null){
			var maxCount = $('#TASK00030001_maxCount').val();
			var startTime = $('#TASK00030001_startTime').datebox('getValue');
			var endTime = $('#TASK00030001_endTime').datebox('getValue');	
			if(maxCount>=0 && maxCount<100000000){
				if(startTime > endTime){
					$.messager.alert("提示","开始时间不能大于结束时间", "warning");
					fnInitLoad();
					return;
				}
				fnSettingSave('000300','01', maxCount, startTime, endTime);
			}else{
				$.messager.alert("提示",'请输入正确的数量【0-99999999】', "warning");
				fnInitLoad();
				return ;
			}
		}
	});
	/** 审批查询保存 */
	$('#TASK00030010_btnSave').on('click', function() {
		if(checkVilide(2) != null){
		var maxCount = $('#TASK00030010_maxCount').val();
		var startTime = $('#TASK00030010_startTime').datebox('getValue');
		var endTime = $('#TASK00030010_endTime').datebox('getValue');
		if(maxCount>=0 && maxCount<100000000){
			if(startTime > endTime){
				$.messager.alert("提示","开始时间不能大于结束时间", "warning");
				fnInitLoad();
				return;
			}
			fnSettingSave('000300', '10' , maxCount, startTime, endTime);
		}else{
			$.messager.alert("提示", '请输入正确的数量【0-99999999】', "warning");
			fnInitLoad();
			return ;
		}
		}
	});
	/** 查询条数统计 */
	$('#btnQuery').on('click', function() {
		var queryStartTime = $('#txtStartTime').datebox('getValue');
		if (queryStartTime == undefined || queryStartTime == '') {
			$.messager.alert("提示", '筛选条件"开始时间"为必输项', "warning");
			return;
		}
		var queryEndTime = $('#txtEndTime').datebox('getValue');
		if (queryEndTime == undefined || queryEndTime == '') {
			$.messager.alert("提示", '筛选条件"结束时间"为必输项', "warning");
			return;
		}
		if(queryEndTime < queryStartTime){
			$.messager.alert("提示", '开始时间不能大于结束时间', "warning");
			
	return;
							}
							$
									.ajax({
										url : "${basePath}/id5/edu/configurate/query/count.do",
										type : 'post',
										data : {
											'startTime' : $('#txtStartTime')
													.datebox('getValue'),
											'endTime' : $('#txtEndTime')
													.datebox('getValue')
										},
										dataType : "json",
										success : function(response, status) {
											if (status == 'success') {
												// 单条最大查询
												$('#SHOW00030000')
														.val(
																response.TASK00030000.TASK_COUNT);

												// 批量最大查询
												$('#SHOW00030001')
														.val(
																response.TASK00030001.TASK_COUNT);

												// 审批系统渠道最大查询
												$('#SHOW00030010')
														.val(
																response.TASK00030010.TASK_COUNT);
											}
										},
										error : function(error, status) {
											$.messager.alert("提示",
													'交易请求异常,状态码[' + status
															+ ']', "warning");

										}
									});
						});
	});
</script>
</html>