<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>学历信息查询</title>
</head>
</head>
<body>
	<form id="frmList" enctype="multipart/form-data" method="post"
		name="frmList" action="submit.do">
		<table style="border-collapse: separate; border-spacing: 5px;">
			<caption>学历信息批量导入</caption>
			<tr>
				<td colspan="2"><a href="javascript:files.click();"
					class="easyui-linkbutton" iconCls="icon-add">批量导入数据文件</a></td>
			</tr>
			<tr>
				<td><input id="files" type="file" onchange="filesDataChange()"
					style="display: none" multiple="true" name="files" /> <textarea
						style="width: 600px; height: 100px;" id="filesName"
						name="filesName" readonly>
					</textarea></td>
				<td><p>批量导入模板示例：</p> <pre>姓名|证件号码</pre></td>
			</tr>
			<tr>
				<td colspan="2"><input
					style="text-align: center; display: inline-block; width: 93px; height: 30px"
					type="button" value="提交查询" onclick="onSubmit()" /></td>
				<td></td>
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
			$.messager.alert('提示', "请选择数据文件", 'warning');
			return;
		}

		$("#modelLoading").show();
		$('#frmList').form('submit', {
			success : function(data) {
				if (data.indexOf("提交成功") != -1) {
					$("#modelLoading").hide();
					$.messager.alert('提示', data, 'info', function() {
						refresh();
					});
				} else {
					$("#modelLoading").hide();
					$.messager.alert("提示", data, "warning");
				}
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
			$.messager.alert('提示', "最多不能导入超过[ 10 ]个文件", 'warning');
			return;
		}
		for (var i = 0; i < fileList.length; i++) {
			if (fileList[i].name.indexOf("txt") == -1
					&& fileList[i].name.indexOf("xls") == -1) {
				$.messager.alert("提示", "请选择txt文件或者Excel文件", "warning");
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
		window.location.href = "${basePath}/id5/edu/batch/index.do";
	}
</script>
</html>