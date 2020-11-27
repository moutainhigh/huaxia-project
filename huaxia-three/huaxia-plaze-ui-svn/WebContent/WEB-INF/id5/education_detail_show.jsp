<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/tld/page.tld" prefix="page"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset=UTF-8>
<title>学历信息查询</title>
<style type="text/css">
fieldset {
	margin-bottom: 10px;
}
table tr td label {
	display: block;
	width: 160px;
	text-align: right;
	font: normal 14px bold;
}
</style>
</head>
<body>
	<form id="educationDetailForm">
		<fieldset>
			<legend>学历摘要</legend>
			<table>
				<tr>
					<td><label>报告编号：</label></td>
					<td><input name="trnId" id="trnId" type="text"
						style="border: none;" /></td>
				</tr>
				<tr>
					<td><label>查询时间：</label></td>
					<td><input name="crtTime" id="crtTime"
						class="easyui-datebox" readonly="readonly" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>被核查人信息</legend>
			<table class="formtable">
				<tr>
					<td><label>姓名：</label></td>
					<td><input name="userName" id="userName" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>身份证号：</label></td>
					<td colspan="5"><input name="identityCard" id="identityCard"
						type="text" style="border: none; text-align: left; width: 164px;" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>学历核查信息</legend>
			<table class="formtable">
				<tr>
					<td><label>查询结果：</label></td>
					<td><input name="queryResult" id="queryResult" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>性别：</label></td>
					<td><input name="sex2" id="sex2" type="text"
						style="border: none; text-align: left;" /></td>
					<td rowspan="7"><img id="photo"  alt="photo" src="data:image/png;base64,${records.photo}"
						width="120" height="180" ></td>
				</tr>
				<tr>
					<td><label>姓名：</label></td>
					<td><input name="userName" id="userName" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>出生日期：</label></td>
					<td colspan="2"><input name="birthday2" id="birthday2"
						type="text" style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>原始身份证号：</label></td>
					<td><input name="identityCard" id="identityCard" type="text"
						style="border: none; text-align: left;width: 164px;" /></td>
					<td><label>身份证原始发证地：</label></td>
					<td colspan="2"><input name="idcorict2" id="idcorict2"
						type="text" style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>毕业院校：</label></td>
					<td><input name="graduate" id="graduate" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>专业：</label></td>
					<td colspan="2"><input name="specialityName"
						id="specialityName" type="text"
						style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>学历：</label></td>
					<td><input name="educationDegree" id="educationDegree"
						type="text" style="border: none; text-align: left;" /></td>
					<td><label>学历类型：</label></td>
					<td colspan="3"><input name="studyStyle" id="studystyle"
						type="text" style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>入学年份：</label></td>
					<td><input name="enrolDate" id="enrolDate" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>毕业时间：</label></td>
					<td colspan="3"><input name="graduateTime" id="graduateTime"
						type="text" style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>学习形式：</label></td>
					<td><input name="dstudyStyle" id="dstudyStyle" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>毕业结论：</label></td>
					<td colspan="3"><input name="studyResult" id="studyResult"
						type="text" style="border: none; text-align: left;" /></td>
				</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>毕业院校情况</legend>
			<table class="formtable">
				<tr>
					<td><label>学校曾用名：</label></td>
					<td colspan="3"><input name="hisName" id="hisName" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>学校所在城市：</label></td>
					<td colspan="3"><input name="schoolCity" id="schoolCity"
						type="text" style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>学校性质：</label></td>
					<td colspan="3"><input name="schoolTrade" id="schoolTrade"
						type="text" style="border: none; text-align: left;" /></td>
					<td><label>学校类别：</label></td>
					<td colspan="3"><input name="schoolCategory"
						id="schoolCategory" type="text"
						style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>学校所属主管单位：</label></td>
					<td colspan="3"><input name="organization" id="organization"
						type="text" style="border: none; text-align: left;" /></td>
					<td><label>办学形式：</label></td>
					<td colspan="3"><input name="educationApproach"
						id="educationApproach" type="text"
						style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>办学层次：</label></td>
					<td colspan="3"><input name="level_" id="level_" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>学校创建年限：</label></td>
					<td colspan="3"><input name="createYear" id="createYear"
						type="text" style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>创建日期：</label></td>
					<td colspan="3"><input name="createDate" id="createDate"
						type="text" style="border: none; text-align: left;" /></td>
					<td><label>学校院士人数：</label></td>
					<td colspan="3"><input name="academicianNsum"
						id="academiciannum" type="text"
						style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>博士后流动站数目：</label></td>
					<td colspan="3"><input name="bshldznum" id="bshldznum"
						type="text" style="border: none; text-align: left;" /></td>
					<td><label>博士点数目：</label></td>
					<td colspan="3"><input name="bsdnum" id="bsdnum" type="text"
						style="border: none; text-align: left;" /></td>
				</tr>
				<tr>
					<td><label>硕士点数目：</label></td>
					<td colspan="3"><input name="ssdnum" id="ssdnum" type="text"
						style="border: none; text-align: left;" /></td>
					<td><label>国家重点学科数目：</label></td>
					<td colspan="3"><input name="zdxknum" id="zdxknum" type="text"
						style="border: none; text-align: left;" /></td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
<script>
	$(function() {
		$("#educationDetailForm").form("load", JSON.parse('${records}'));
	})
</script>
</html>