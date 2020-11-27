<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>百融多头借贷查询</title>
<script type="text/javascript"
	src="${basePath}/js/locale/easyui-lang-zh_CN.js"></script>
</head>
<body>
	<form id="frmQuery" name="frmQuery">
		<section class="layout horizontal">
			<label>开始时间：</label><input id="txtStartTime" class="easyui-datebox"
				name="startTime" /> <label>结束时间：</label><input id="txtEndTime"
				class="easyui-datebox" name="endTime" /> <input id="btnReset"
				type="button" value="重置" /> <input id="btnQuery" type="button"
				value="查询" />
		</section>
		<section class="table-layout">
			<fieldset>
				<legend>百融多头借贷手动查询统计</legend>
				<table>
					<tr>
						<td><label>批量实际查询：</label></td>
						<td><input type="text" id="SHOW00070001" disabled="disabled" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
		<section class="table-layout">
			<fieldset>
				<legend>百融多头借贷渠道查询统计</legend>
				<table>
					<tr>
						<td><label>审批实际查询：</label></td>
						<td><input type="text" id="SHOW00070010" disabled="disabled" /></td>
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
						<td><label>批量最大查询：</label></td>
						<td colspan="3"><input type="text" id="TASK00070001_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00070001_startTime" class="easyui-datebox"
							style="width: 136px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00070001_endTime" class="easyui-datebox"
							style="width: 136px" /></td>
						<td><input id="TASK00070001_btnSave" type="button"
							value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>渠道查询数量设置（审批系统）</legend>
				<table>

					<tr>
						<td><label>最大查询条数：</label></td>
						<td colspan="3"><input type="text" id="TASK00070010_maxCount" /></td>
						<td><label>生效开始时间：</label></td>
						<td><input id="TASK00070010_startTime" class="easyui-datebox"
							style="width: 136px" /></td>
						<td><label>生效结束时间：</label></td>
						<td><input id="TASK00070010_endTime" class="easyui-datebox"
							style="width: 136px" /></td>
						<td><input id="TASK00070010_btnSave" type="button"
							value="保存提交" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
</body>
<script type="text/javascript">
function checkVilide(type){
	if(type == 0){
		if (($("#TASK00070000_maxCount").val() == '')) {
			alert("单条查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00070000_startTime').datebox('getValue') == '')) {
			alert("单条设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00070000_endTime').datebox('getValue') == '')) {
			alert("单条设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 1){
		if (($("#TASK00070001_maxCount").val() == '')) {
			alert("批量查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00070001_startTime').datebox('getValue') == '')) {
			alert("批量设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00070001_endTime').datebox('getValue') == '')) {
			alert("批量设置数量的结束时间不能为空");
			return null;
		}
	}
	if(type == 2){
		if (($("#TASK00070010_maxCount").val() == '')) {
			alert("审批查询的设置最大数量不能为空");
			return null;
		}
		if (($('#TASK00070010_startTime').datebox('getValue') == '')) {
			alert("审批设置数量的开始时间不能为空");
			return null;
		}
		if (($('#TASK00070010_endTime').datebox('getValue') == '')) {
			alert("审批设置数量的结束时间不能为空");
			return null;
		}
	}
	return 0;
} 

	Date.prototype.format = function(format) {
		var o = {
			'M+' : this.getMonth() + 1, // Month
			'd+' : this.getDate(), // Day
			'h+' : this.getHours(), // Hour
			'm+' : this.getMinutes(), // Minute
			's+' : this.getSeconds(), // Second
			'q+' : Math.floor((this.getMonth() + 3) / 3), // Quarter
			'S+' : this.getMilliseconds()
		// Millisecond
		};

		if (/(y+)/i.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + '')
					.substr(4 - RegExp.$1.length));
		}
		return format;
	}
	function fnInitLoad() {
		/** 百融多头借贷数量查询 */
		$.ajax({
			url : "${basePath}/bairong/als/configurate/query/count.do",
			type : 'post',
			data : {
				'startTime' : (new Date().format('yyyy') + '-01-01'),
				'endTime' : (new Date().format('yyyy') + '-12-31')
			},
			dataType : "json",
			success : function(response, status) {
				if (status == 'success') {
					// 审批系统渠道最大查询
					$('#SHOW00070010').val(response.TASK00070010.TASK_COUNT);
					$('#SHOW00070001').val(response.TASK00070001.TASK_COUNT);
				}
			},
			error : function(error, status) {
				alert('交易请求异常,状态码[' + status + ']');
			}
		});
		/** 百融多头借贷配置查询 */
		$.ajax({
			url : "${basePath}/bairong/als/configurate/query/configure.do",
			type : 'post',
			dataType : "json",
			success : function(response, status) {
				if (status == 'success') {
					if(response.TASK00070001 != undefined){
							// 批量最大查询				
							$('#TASK00070001_maxCount').val(
									response.TASK00070001.maxCount);
							$('#TASK00070001_startTime').datebox('setValue',
									response.TASK00070001.startTimeString);
							$('#TASK00070001_endTime').datebox('setValue',
									response.TASK00070001.endTimeString);
					}
					if(response.TASK00070010 != undefined){
						// 审批最大查询				
						$('#TASK00070010_maxCount').val(
								response.TASK00070010.maxCount);
						$('#TASK00070010_startTime').datebox('setValue',
								response.TASK00070010.startTimeString);
						$('#TASK00070010_endTime').datebox('setValue',
								response.TASK00070010.endTimeString);
					}
				}
			},
			error : function(error, status) {
				alert('交易请求异常,状态码[' + status + ']');
			}
		});
	}
	function fnSettingSave(source, channel, max, start, end) {
		$
				.ajax({
					url : "${basePath}/bairong/als/configurate/query/configure/save.do",
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
		/**批量渠道查询数量设置保存 */
		$('#TASK00070001_btnSave').on('click', function() {	
			if(checkVilide(1) != null){
				var maxCount = $('#TASK00070001_maxCount').val();
				var startTime = $('#TASK00070001_startTime').datebox('getValue');
				var endTime = $('#TASK00070001_endTime').datebox('getValue');
				if(maxCount>=0 && maxCount<100000000){
					if(startTime > endTime){
						alert("开始时间不能大于结束时间");
						return;
					}
					fnSettingSave('000700', '01', maxCount, startTime, endTime);
				}else{
					alert('请输入正确的数量【0-99999999】');
					return ;
				}
			}
		});
		/**审批渠道查询数量设置保存 */
		$('#TASK00070010_btnSave').on('click', function() {
			if(checkVilide(2) != null){
				var maxCount = $('#TASK00070010_maxCount').val();
				var startTime = $('#TASK00070010_startTime').datebox('getValue');
				var endTime = $('#TASK00070010_endTime').datebox('getValue');
				if(maxCount>=0 && maxCount<100000000){
					if(startTime > endTime){
						alert("开始时间不能大于结束时间");
						return;
					}
					fnSettingSave('000700', '10', maxCount, startTime, endTime);
				}else{
					alert('请输入正确的数量【0-99999999】');
					return ;
				}
			}
		});

		/** 查询条数统计 */
		$('#btnQuery')
				.on(
						'click',
						function() {
							var queryStartTime = $('#txtStartTime').datebox(
									'getValue');
							if (queryStartTime == undefined
									|| queryStartTime == '') {
								alert('筛选条件"开始时间"为必输项!');
								return;
							}
							var queryEndTime = $('#txtEndTime').datebox(
									'getValue');
							if (queryEndTime == undefined || queryEndTime == '') {
								alert('筛选条件"结束时间"为必输项!');
								return;
							}
							$
									.ajax({
										url : "${basePath}/bairong/als/configurate/query/count.do",
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
												// 审批系统渠道最大查询
												$('#SHOW00070010')
														.val(
																response.TASK00070010.TASK_COUNT);
												$('#SHOW00070001')
														.val(
																response.TASK00070001.TASK_COUNT);
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