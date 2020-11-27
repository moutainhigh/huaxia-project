<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>fico风险预警查询</title>
</head>
</head>
<body>
	<form id="frmList" enctype="multipart/form-data" method="post"
		name="frmList" action="submit.do">
		<table style="border-collapse: separate; border-spacing: 5px;">
			<caption>FICO批量导入</caption>
			<tr>
				<td align="left" colspan="2"><a
					href="javascript:files.click();" class="easyui-linkbutton"
					iconCls="icon-add">批量导入数据文件</a></td>
			</tr>
			<tr>
				<td><input id="files" type="file" onchange="filesDataChange()"
					style="display: none" multiple="true" name="files" /> <textarea
						style="width: 800px; height: 100px;" id="filesName"
						name="filesName" readonly="readonly">
					</textarea></td>
				<td><p>批量导入模板示例：</p> <pre>身份证号码|手机号码|手机前三位|查询日期|我行查询序号</pre></td>
			</tr>
			<tr>
				<td colspan="2"><input
					style="text-align: center; display: inline-block; width: 93px; height: 30px"
					type="button" value="提交查询" onclick="onSubmit()" /></td>
			</tr>
		</table>
	</form>
	<div id="modelLoading"
		style="position: fixed; z-index: 150; width: 100%; height: 100%; top: 0; left: 0; background: #fff; filter: alpha(opacity = 50); -moz-opacity: 0.5; -khtml-opacity: 0.5; opacity: 0.5; padding-top: 25%; display: none;">
		<div align='center'>
			<img src='/huaxia-plaze-ui/images/index/loading.gif' border='0' />
		</div>
	</div>
</body>
<script type="text/javascript">
	function onSubmit() {
		if ($("#files").val() == "") {
			alert("请选择数据文件");
			return;
		}
		$("#modelLoading").show();
		$('#frmList').form(
				'submit',
				{
					success : function(json) {
						$("#modelLoading").hide();
					/* 	if(json || json == 0){
							$.messager.alert('提示', "上传错误，请检查文件总大小是否超过20M", 'warning'); 
						} */
						var response = eval('(' + json + ')');
						if(!response.success){
							$.messager.alert('提示', response.message, 'warning'); 
						}
						if (response.failure.length == 0) {
							$.messager.alert('提示', '批量文件上传成功!', '', function() {
								refresh();
							});
						} else {
							var text = '<div>';
							for (var i = 0; i < response.failure.length; i++) {
								text += '[ ' + (i + 1) + ' ] ' + response.failure[i];
								if (i < response.failure.length - 1) {
									text += '<br/>';
								}
							}
							text += '</div>';
							$.messager.alert('提示', '批量文件上传结果（失败）：<br/>' + text , '', function() {
								refresh();
							}); 
						}
					},
					error : function(response) {
						var response = eval('(' + json + ')');
						$("#modelLoading").hide();
						$.messager.alert(response.message);
					}
				})
	}
	/* 选择为数据文件后触发的事件 */
	function filesDataChange() {
		//需要显示的文件名
		var name = "";
		//获取导入数据文件属性
		var files = document.getElementById("files");
		//显示文件名的textarea
		var filesName = document.getElementById("filesName");
		//文件列表
		var fileList = files.files;
		if (fileList.length > 10) {
			alert("最多不能导入超过10个文件");
			return;
		}
		for (var i = 0; i < fileList.length; i++) {
			if (fileList[i].name.indexOf("txt") == -1
					&& fileList[i].name.indexOf("xls") == -1) {
				alert("请选择txt文件");
				files.value = "";
				filesName.value = "";
				return;
			}
			name += i + 1 + "." + fileList[i].name + "\n";
		}
		//显示文件名的textarea
		filesName.value = name;
	}
	function refresh() {
		window.location.href = "${basePath}/fico/batch/index.do";
	}
</script>
</html>