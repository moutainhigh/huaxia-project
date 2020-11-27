<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>简项公安查询</title>
</head>
<body>
	<form id="frmList" enctype="multipart/form-data" method="post" name="frmList" action="submit.do">
		<table style="border-collapse: separate; border-spacing: 5px;">
			<tr>
				<td align="right">姓名：</td>
				<td><input class="easyui-textbox" id="name" type="text"
					name="name" data-options="required:true" style="width: 165px"></input></td>
			</tr>
			<tr>
				<td align="right">证件号码：</td>
				<td><input class="easyui-textbox" id="cert_no" type="text"
					name="certNo" data-options="required:true,validType:'length[4,20]'"
					style="width: 165px"></input></td>
			</tr>
			<tr>
				<td></td>
				<td><input id="btnQuery" type="button"
					style="text-align: center; display: inline-block; width: 93px; height: 30px"
					value="提交查询" onclick="onSubmit()" /></td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
	function onSubmit() {
		// 校验输入框是否有警示的
		var isValid = $("#frmList").form('validate');
		if (!isValid) {
			return;
		} else {
			$('#btnQuery').attr('disabled', true);
			var w = window.open('about:blank');
			$('#frmList').form('submit', {
				success : function(response) {
					var o = eval('(' + response + ')');
					if (o.result) {
						w.location.href = '${basePath}/nciic/police/batch/get/filedetail.do?trnId=' + o.trnId;
					} else {
						$.messager.alert('提示', '查询失败', 'warning');
					}
				}
			});
			$('#frmList').form('clear');
			$('#btnQuery').attr('disabled', false);
		}
	}
</script>
</html>