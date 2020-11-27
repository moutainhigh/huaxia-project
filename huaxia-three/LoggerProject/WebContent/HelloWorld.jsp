<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>TestLog</title>
</head>
<body>
Hello World!
<form id="frmList1" method="post"
		name="frmList1" action="http://localhost:8080/LoggerProject/test/abc.do">
		<table>
			<tr>
				<td colspan="2">
					<input type="button" onclick="onSubmit1()" value="点击输出日志"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
//提交
function onSubmit1() {
	/* alter("Hello World!");/*  */ 
	var f = $('#frmList1');
	f.submit();
}
</script>
</html>