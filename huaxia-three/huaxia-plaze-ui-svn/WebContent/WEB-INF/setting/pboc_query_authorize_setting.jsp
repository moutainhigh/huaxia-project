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
	<form id="frmSetting" name="frmSetting">
		<input type="hidden" id="before_query_user" value="${record.USERNAME}" />
		<input type="hidden" id="view_query_password" value="${record.PASSWORD}" />
		<section class="table-layout">
			<fieldset>
				<legend>人行征信查询授权配置</legend>
				<table>
					<tr>
						<td><label>查询用户：</label></td>
						<td colspan="3"><input type="text" id="query_user" name="queryUser" value="${record.USERNAME}" /></td>
						<td><label>查询密码：</label></td>
						<td><input type="password" id="query_password" name="queryPass" value="${record.PASSWORD}" /></td>
						<td><input id="btnSave" type="button" value="保存提交" /></td>
						<td><input id="btnView" type="button" value="查看密码" /></td>
					</tr>
				</table>
			</fieldset>
		</section>
	</form>
</body>
<script type="text/javascript">
$(function() {
	$('#btnSave').on('click', function() {
		var query_user = $('#query_user').val();
		if (query_user == undefined || query_user == '') {
			alert('"查询用户"为必输项!');
			return false;
		}
		var query_password = $('#query_password').val();
		if (query_password == undefined || query_password == '') {
			alert('"查询密码"为必输项!');
			return false;
		}
		$.ajax({
			url : "${basePath}/setting/pboc/query/authorize/configure/save.do",
			type : 'post',
			contentType : 'application/json;charset=utf-8',
			data : JSON.stringify({
				'queryUserBefore' : $('#before_query_user').val(),
				'queryUser' : query_user,
				'queryPass' : query_password
			}),
			dataType : "json",
			success : function(response, status) {
				if (response.result) {
					$('#before_query_user').val(query_user);
					alert(response.message);
				}
			},
			error : function(error, status) {
				alert('交易请求异常,状态码[' + status + ']');
			}
		});
	});
	$('#btnView').on('click', function() {
		$('#query_password').val($('#view_query_password').val()).attr('type', 'text');
	});
});
</script>
</html>