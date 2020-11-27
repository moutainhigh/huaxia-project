<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>人行离线数字解读查询结果</title>
<style type="text/css">
fieldset {
	margin-bottom: 10px;
}

table tr td label {
	display: block;
	width: 160px;
	text-align: right;
	font-weight: bold;
}
</style>
</head>
<body>
	<form id="unicomDetailForm">
		<fieldset>
			<table class="formtable">
				<tr>
					<th>姓名：</th>
					<td colspan="2"><input name="name" id="name" type="text"
						style="border: none; text-align: left; width: 100px;" readOnly=/></td>
						<th>证件类型：</th>
					<td colspan="2"><input name="idType" id="idType" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
						<th>证件号码：</th>
					<td colspan="2"><input name="idNum" id="idNum" type="text"
						style="border: none; text-align: left; width: 200px;" /></td>
						<th>产品日期：</th>
					<td colspan="2"><input name="productDate" id="productDate" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
						<th>查询结果类型：</th>
					<td colspan="2"><input name="resultType" id="resultType" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					
				</tr>
				<tr>
					<th>影响因素个数：</th>
					<td colspan="2"><input name="factorNum" id="factorNum" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					<th>影响因素01：</th>
					<td colspan="2"><input name="factor1" id="factor1" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					<th>影响因素02：</th>
					<td colspan="2"><input name="factor2" id="factor2" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					<th>影响因素03：</th>
					<td colspan="2"><input name="factor3" id="factor3" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					<th>影响因素04：</th>
					<td colspan="2"><input name="factor4" id="factor4" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					
				</tr>
				<tr>
					<th>影响因素05：</th>
					<td colspan="2"><input name="factor5" id="factor5" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					<th>数字解读值：</th>
					<td colspan="2"><input name="score" id="score" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					<th>查询结果描述：</th>
					<td colspan="2"><input name="resultDesc" id=""resultDesc"" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
					<th>相对位置：</th>
					<td colspan="2"><input name="position" id=""position"" type="text"
						style="border: none; text-align: left; width: 100px;" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
<script>
	$(function() {
		var data = JSON.parse('${batchDetail}');
		console.log(data);
		$("#unicomDetailForm").form("load", data);
	})
</script>