<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../init.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta charset=UTF-8>
<script type="text/javascript" src="${basePath}/js/jquery.watermark.js"></script>
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
	margin: 0;
	padding: 0;
}

.dvi-table input {
	color: blue;
	disabled: disabled;
	height: 14px;
	width: 100%;
	line-height: 14px;
	margin-top: 0px;
	border-top-style: none;
	border-left-style: none;
	border-right-style: none;
	border-bottom-style: none;
	text-align: center;
	font-size: 12px;
}

table {
	border-collapse: collapse;
}

table td {
	padding: 4px 0px;
	text-align: center;
}

table.tableTdColor td {
	border: 1px solid #c0c0c0;
	text-align: center;
	background-color: white;
}

#tail_table td {
	text-align: left;
}
</style>
<script type="text/javascript">
	function getDictNameByList(groupCode, value) {
		var result = "";
		$.ajax({
			url : "${basePath}/system/queryDictName.do",
			type : 'post',
			data : {
				'groupCode' : groupCode,
				'value' : value
			},
			async : false,
			success : function(data) {
				result = data;
			},
			error : function(error, status) {
				alert('交易请求异常,状态码[' + status + ']');
			}
		});
		return result;
	}
</script>
</head>
<body style="padding: 10px;">
	<h2>
		个人信用报告<br>（授权机构版）
	</h2>
	<form id="infoCollForm">
		<h3>概要信息</h3>
		<table class="dvi-table" border="1" style="width: 98%">
			<tr style="font-size: 13px" class="head">
				<th align="left" style="width: 51%;">报告编号:<input
					style="width: 50%; text-align: left;" type="text" id="reportNo"
					name="reportNo" readonly="readonly" /></th>
				<th align="right" style="width: 49%;">报告时间:<input
					style="width: 30%; text-align: left;" type="text" id="reportTime"
					name="reportTime" readonly="readonly" /></th>
			</tr>
		</table>
		<table class="dvi-table" border="1"
			style="width: 98%; border-top: 0px;">
			<tr style="font-size: 13px">
				<th style="width: 16%;">被查询者姓名</th>
				<th style="width: 16%;">被查询者证件类型</th>
				<th style="width: 19%;">被查询者证件号码</th>
				<th style="width: 24%;">查询机构</th>
				<th style="width: 25%;">查询原因</th>
			</tr>
			<tr>
				<td><input type="text" id="queryedName" name="queryedName"
					readonly="readonly" /></td>
				<td><input type="text" id="queryedCertType"
					name="queryedCertType" readonly="readonly" /></td>
				<td><input type="text" id="queryedCertNo" name="queryedCertNo"
					readonly="readonly" /></td>
				<td><input type="text" id="queryOrg" name="queryOrg"
					readonly="readonly" /></td>
				<td><input type="text" id="qryReason" name="qryReason"
					readonly="readonly" /></td>
			</tr>

		</table>
		<br>
		<c:if test="${idCardList !='[null]'}">
			<table class="dvi-table" border="1" style="width: 98%">
				<tr>
					<th colspan="2">其他证件信息</th>
				</tr>
				<tr>
					<th width="50%">证件类型</th>
					<th>证件号码</th>
				</tr>
				<c:forEach items="${idCardList}" var="list" varStatus="sequence">
					<tr>
						<td><script type="text/javascript">
							document.write(getDictNameByList("CERT_TYPE",
									"${list.identityType}"))
						</script></td>
						<td>${list.identityNum}</td>

					</tr>
				</c:forEach>
			</table>
			<br>
		</c:if>
		<table id="fangQiZa" class="dvi-table" border="1" style="width: 98%">
			<tr>
				<th colspan="2">防欺诈警示</th>
			</tr>
			<tr>
				<th colspan="2">信息主体设置防欺诈警示,联系电话:<input style="width: 5%"
					type="text" id="phone" name="phone" readonly="readonly" /></th>
			</tr>
			<tr>
				<th width="50%">生效日期</th>
				<th>截止日期</th>
			</tr>
			<tr>
				<td><input type="text" id="effectiveDate" name="effectiveDate"
					readonly="readonly" /></td>
				<td><input type="text" id="deadLine" name="deadLine"
					readonly="readonly" /></td>
			</tr>
		</table>
		<br>
		<table class="dvi-table" border="1"
			style="width: 98%; text-align: center">
			<tr style="font-size: 13px" class="head">
				<th>异议信息提示</th>
			</tr>

			<tr>
				<td>信息主体对信用报告内容提出了<input style="width: 5%" type="text"
					id="dissentCount" name="dissentCount" readonly="readonly" />
					条异议正在处理中！请浏览相关文档
				</td>
			</tr>

		</table>
		<h3>一 、个人基本信息</h3>
		<h3>(一)身份信息</h3>
		<table class="dvi-table" style="width: 98%" border="">
			<tr style="font-size: 13px" align="center">
				<th>性別</th>
				<th>出生日期</th>
				<th>婚姻状况</th>
				<th>学历</th>
				<th>学位</th>
				<th>就业状况</th>
				<th>国籍</th>
				<th>电子邮箱</th>
			</tr>
			<tr align="center">
				<td><input type="text" id="gender" name="gender"
					readonly="readonly" /></td>
				<td><input type="text" id="birthDay" name="birthDay"
					readonly="readonly" /></td>
				<td><input type="text" id="maritalStatus" name="maritalStatus"
					readonly="readonly" /></td>
				<td><input type="text" id="residencyStatus"
					name="residencyStatus" readonly="readonly" /></td>
				<td><input type="text" id="degree" name="degree"
					readonly="readonly" /></td>

				<td><input type="text" id="workStatus" name="workStatus"
					readonly="readonly" /></td>
				<td><input type="text" id="nationality" name="nationality"
					readonly="readonly" /></td>
				<td><input type="text" id="EMAIL" name="email"
					readonly="readonly" /></td>
			</tr>
			<tr style="font-size: 13px">
				<th colspan="4">通讯地址</th>
				<th colspan="4">户籍地址</th>
			</tr>
			<tr>
				<td colspan="4"><input type="text" id="communicationAddr"
					name="communicationAddr" readonly="readonly"
					style="font-size: 13px" /></td>
				<td colspan="4"><input type="text" id="domicileAddr"
					name="domicileAddr" readonly="readonly" style="font-size: 13px" /></td>
			</tr>
			<tr>
				<th>编号</th>
				<th colspan="3">手机号码</th>
				<th colspan="4">信息更新日期</th>
			</tr>
			<c:forEach items="${phoneList}" var="list" varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td colspan="3">${list.phoneNum}</td>
					<td colspan="4">${list.updatedDate}</td>

				</tr>
			</c:forEach>
		</table>
		<h3>(二)配偶信息</h3>
		<table class="dvi-table" style="width: 98%" border="">

			<thead>
				<tr align="center" style="font-size: 13px">
					<th>姓名</th>
					<th>证件类型</th>
					<th>证件号码</th>
					<th>工作单位</th>
					<th>联系电话</th>
				</tr>
			</thead>
			<tbody>
				<tr align="center">
					<td><input type="text" id="mateName" name="mateName"
						readonly="readonly" /></td>
					<td><input type="text" id="mateCertType" name="mateCertType"
						readonly="readonly" /></td>
					<td><input type="text" id="mateCertNo" name="mateCertNo"
						readonly="readonly" /></td>
					<td><input type="text" id="mateCompany" name="mateCompany"
						readonly="readonly" /></td>
					<td><input type="text" id="mateContactTel"
						name="mateContactTel" readonly="readonly" /></td>
				</tr>
			</tbody>
		</table>
		<h3>(三)居住信息</h3>
		<table class="dvi-table" style="width: 98%" border="">
			<tr>
				<th width="5%">编号</th>
				<th width="45%">居住地址</th>
				<th width="15%">住宅电话</th>
				<th width="15%">居住状况</th>
				<th width="20%">信更新日期</th>
			</tr>
			<c:forEach items="${residenceInfoList}" var="list"
				varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td>${list.residentAddr}</td>
					<td>${list.homePhone}</td>
					<td><script type="text/javascript">
						document.write(getDictNameByList("LIVE_CON_BANK",
								"${list.resideState}"))
					</script></td>
					<td>${list.infoUpdTime}</td>
				</tr>
			</c:forEach>
		</table>
		<h3>（四）职业信息</h3>
		<table class="dvi-table" style="width: 98%" border="">
			<tr>
				<th width="5%">编号</th>
				<th width="30%">工作单位</th>
				<th width="15%">单位性质</th>
				<th width="25%">单位地址</th>
				<th width="25%">单位电话</th>
			</tr>
			<c:forEach items="${proessionInfoList}" var="list"
				varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td>${list.company}</td>
					<td><script type="text/javascript">
						document.write(getDictNameByList("UNIT_NATURE_BANK",
								"${list.unitCharacter}"))
					</script></td>
					<td>${list.compAddr}</td>
					<td>${list.unitPhone}</td>
				</tr>
			</c:forEach>
		</table>
		<table class="dvi-table" style="width: 98%; border-top: 0px;"
			border="">
			<tr>
				<th width="5%">编号</th>
				<th width="20%">职业</th>
				<th width="26%">行业</th>
				<th width="10%">职务</th>
				<th width="5%">职称</th>
				<th width="17%">进入本单位年份</th>
				<th width="17%">信息更新日期</th>
			</tr>
			<c:forEach items="${proessionInfoList}" var="list"
				varStatus="sequence">
				<tr>
					<td>${sequence.count}</td>
					<td><script type="text/javascript">
						document.write(getDictNameByList("JOB_BANK",
								"${list.job}"))
					</script></td>
					<td><script type="text/javascript">
						document.write(getDictNameByList("GMJJ_BANK",
								"${list.industryType}"))
					</script></td>
					<td><script type="text/javascript">
						document.write(getDictNameByList("DUTY_BANK",
								"${list.occupationCode}"))
					</script></td>
					<td><script type="text/javascript">
						document.write(getDictNameByList("ACHIEVEMENT_BANK",
								"${list.technicalTitle}"))
					</script></td>
					<td>${list.curCompWorkStatY}</td>
					<td>${list.infoUpdTime}</td>
				</tr>
			</c:forEach>
		</table>

		<h3>二 、信息概要</h3>
		<h3>（一）个人信用报告数字解读</h3>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th width="33%">数字解读</th>
				<th width="33%">相对位置</th>
				<th width="34%">说明</th>
			</tr>
			<tr>
				<td><input type="text" id="numberRead" name="numberRead"
					readonly="readonly" /></td>
				<td><input type="text" id="oppositePosition"
					name="oppositePosition" readonly="readonly" /></td>
				<td><input type="text" id="descContent" name="descContent"
					readonly="readonly" /></td>
			</tr>
		</table>


		<h3>(二)信贷交易信息提示</h3>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="2" width="33%">业务类型</th>
				<th width="33%">账户数</th>
				<th width="34%">首笔业务发放月份</th>
			</tr>
			<tr>
				<td rowspan="3">贷款</td>
				<td>个人住房贷款</td>
				<td><input type="text" id="houseLoan" name="houseLoan"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="houseFirstMonth"
					name="houseFirstMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>个人商用房贷款（包括商用两住房）</td>
				<td><input type="text" id="businessLoad" name="businessLoad"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="businessFirstMonth"
					name="businessFirstMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>其他类贷款</td>
				<td><input type="text" id="otherLoad" name="otherLoad"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="otherFirstMonth"
					name="otherFirstMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td rowspan="2">信用卡</td>
				<td>贷记卡</td>
				<td><input type="text" id="loanCard" name="loanCard"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="loanCardFirstMonth"
					name="loanCardFirstMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>准贷记卡</td>
				<td><input type="text" id="standardLoancard"
					name="standardLoancard" value="---" readonly="readonly" /></td>
				<td><input type="text" id="standardFirstMonth"
					name="standardFirstMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>其他</td>
				<td>---</td>
				<td><input type="text" id="otherTrn" name="otherTrn"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="otherTrnFirstMonth"
					name="otherTrnFirstMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td colspan="2">合计</td>
				<td><input type="text" id="acountAmount" name="acountAmount"
					value="---" readonly="readonly" /></td>
				<td>---</td>
			</tr>

		</table>
		<h3>(三)信贷交易违约信息概要</h3>

		<table class="dvi-table" border="1" style="width: 98%">
			<tr>
				<th colspan="3">被追偿信息汇总</th>
			</tr>
			<tr>
				<th width="30%">业务类型</th>
				<th width="30%">账户数</th>
				<th width="40%">余额</th>
			</tr>
			<tr>
				<td>资产处置业务</td>
				<td><input type="text" id="assetsServiceAccount"
					name="assetsServiceAccount" readonly="readonly" value="---" /></td>
				<td><input type="text" id="assetsServiceBa"
					name="assetsServiceBa" readonly="readonly" value="---" /></td>
			</tr>
			<tr>
				<td>垫款业务</td>
				<td><input type="text" id="endServiceAccount"
					name="endServiceAccount" readonly="readonly" value="---" /></td>
				<td><input type="text" id="endServiceBa" name="endServiceBa"
					readonly="readonly" value="---" /></td>
			</tr>
			<tr>
				<td>合计</td>
				<td><input type="text" id="serviceAccountTotal"
					name="serviceAccountTotal" readonly="readonly" value="---" /></td>
				<td><input type="text" id="serviceBaTotal"
					name="serviceBaTotal" readonly="readonly" value="---" /></td>
			</tr>
		</table>
		<br>
		<table class="dvi-table" border="1" style="width: 98%">
			<tr>
				<th colspan="2">呆账信息汇总</th>
			</tr>
			<tr>
				<th width="50%">账户数</th>
				<th width="50%">余额</th>
			</tr>
			<tr>
				<td><input type="text" id="badDebtsAccount"
					name="badDebtsAccount" readonly="readonly" /></td>
				<td><input type="text" id="badDebtsBa" name="badDebtsBa"
					readonly="readonly" /></td>
			</tr>

		</table>
		<br>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="5">逾期（透支）信息汇总</th>
			</tr>
			<tr>
				<th width="30%">账户类型</th>
				<th width="10%">账户数</th>
				<th width="10%">月份数</th>
				<th width="25%">单月最高逾期/透支总额</th>
				<th width="25%">最长逾期/透支月数</th>
			</tr>
			<tr>
				<td>非循环贷账户</td>
				<td><input type="text" id="noCycleAccount"
					name="noCycleAccount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="noCycleMonth" name="noCycleMonth"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="noCycleOverDue"
					name="noCycleOverDue" value="---" readonly="readonly" /></td>
				<td><input type="text" id="noCycleOverDueMonth"
					name="noCycleOverDueMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>循环额度下分账户</td>
				<td><input type="text" id="cycleSepAccount"
					name="cycleSepAccount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleSepMonth" name="cycleSepMonth"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleSepOverDue"
					name="cycleSepOverDue" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleSepOverDueMonth"
					name="cycleSepOverDueMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>循环贷账户</td>
				<td><input type="text" id="cycleAccount" name="cycleAccount"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleMonth" name="cycleMonth"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleOverDue" name="cycleOverDue"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleOverDueMonth"
					name="cycleOverDueMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>贷记卡账户</td>
				<td><input type="text" id="loanCardAccount"
					name="loanCardAccount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="loanCardMonth" name="loanCardMonth"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="loanCardMonthOverDue"
					name="loanCardMonthOverDue" value="---" readonly="readonly" /></td>
				<td><input type="text" id="loanCardDueMonth"
					name="loanCardDueMonth" value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<td>准贷记卡账户</td>
				<td><input type="text" id="staLoanCardAccount"
					name="staLoanCardAccount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="staLoanCardMonth"
					name="staLoanCardMonth" value="---" readonly="readonly" /></td>
				<td><input type="text" id="staLoanCardMonthOverDue"
					name="staLoanCardMonthOverDue" value="---" readonly="readonly" /></td>
				<td><input type="text" id="staLoanCardDueMonth"
					name="staLoanCardDueMonth" value="---" readonly="readonly" /></td>
			</tr>


		</table>
		<h3>(四)信贷交易授信及负债信息概要</h3>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="5">非循环贷账户信息汇总</th>
			</tr>
			<tr>
				<th width="25%">管理机构数</th>
				<th width="15%">账户数</th>
				<th width="15%">授信总额</th>
				<th width="15%">余额</th>
				<th width="30%">最近6个月平均还款</th>
			</tr>
			<tr>
				<td><input type="text" id="noCycleManageOrganCount"
					name="noCycleManageOrganCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="noCycleAccountCount"
					name="noCycleAccountCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="noCycleCreditTotalAmount"
					name="noCycleCreditTotalAmount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="noCycleBalance"
					name="noCycleBalance" value="---" readonly="readonly" /></td>
				<td><input type="text" id="noCycle6MsAvg" name="noCycle6MsAvg"
					value="---" readonly="readonly" /></td>

			</tr>


		</table>
		<br>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="5">循环额度下分账户信息汇总</th>
			</tr>
			<tr>
				<th width="25%">管理机构数</th>
				<th width="15%">账户数</th>
				<th width="15%">授信总额</th>
				<th width="15%">余额</th>
				<th width="30%">最近6个月平均还款</th>
			</tr>
			<tr>
				<td><input type="text" id="cycleLveManageOrganCount"
					name="cycleLveManageOrganCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleLveAccountCount"
					name="cycleLveAccountCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleLveCreditTotalAmount"
					name="cycleLveCreditTotalAmount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleLveBalance"
					name="cycleLveBalance" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleLve6MsAvg"
					name="cycleLve6MsAvg" value="---" readonly="readonly" /></td>
			</tr>
		</table>
		<br>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="5">循环贷账户信息汇总</th>
			</tr>
			<tr>
				<th width="25%">管理机构数</th>
				<th width="15%">账户数</th>
				<th width="15%">授信总额</th>
				<th width="15%">余额</th>
				<th width="30%">最近6个月平均还款</th>
			</tr>
			<tr>
				<td><input type="text" id="cycleManageOrganCount"
					name="cycleManageOrganCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleAccountCount"
					name="cycleAccountCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleCreditTotalAmount"
					name="cycleCreditTotalAmount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycleBalance" name="cycleBalance"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="cycle6MsAvg" name="cycle6MsAvg"
					value="---" readonly="readonly" /></td>
			</tr>


		</table>
		<br>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="7">贷记卡账户信息汇总</th>
			</tr>
			<tr>
				<th width="20%">发卡机构数</th>
				<th width="10%">账户数</th>
				<th width="13%">授信总额</th>
				<th width="14%">单家机构最高授信额</th>
				<th width="15%">单家机构最低授信额</th>
				<th width="13%">已用额度</th>
				<th width="15%">最近6个月平均使用额度</th>
			</tr>
			<tr>
				<td><input type="text" id="fkOrganCount" name="fkOrganCount"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="accountCount" name="accountCount"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="creditTotalAmount"
					name="creditTotalAmount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="singleBankMax" name="singleBankMax"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="singleBankMin" name="singleBankMin"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="usedLimit" name="usedLimit"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="recent6msAvg" name="recent6msAvg"
					value="---" readonly="readonly" /></td>
			</tr>


		</table>
		<br>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="7">准贷记卡账户信息汇总</th>
			</tr>
			<tr>
				<th width="20%">发卡机构数</th>
				<th width="10%">账户数</th>
				<th width="13%">授信总额</th>
				<th width="14%">单家机构最高授信额</th>
				<th width="15%">单家机构最低授信额</th>
				<th width="13%">透支余额</th>
				<th width="15%">最近6个月平均使用透支余额</th>
			</tr>
			<tr>
				<td><input type="text" id="staFkOrganCount"
					name="staFkOrganCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="staAccountCount"
					name="staAccountCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="staCreditTotalAmount"
					name="staCreditTotalAmount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="staSingleBankMax"
					name="staSingleBankMax" value="---" readonly="readonly" /></td>
				<td><input type="text" id="staSingleBankMin"
					name="staSingleBankMin" value="---" readonly="readonly" /></td>
				<td><input type="text" id="staOverDueBal" name="staOverDueBal"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="recent6msAvgTzye"
					name="recent6msAvgTzye" value="---" readonly="readonly" /></td>
			</tr>


		</table>
		<br>
		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="6">相关还款责任信息汇总</th>
			</tr>
			<tr>
				<th colspan="6">为个人</th>
			</tr>
			<tr>
				<th colspan="3">担保责任</th>
				<th colspan="3">其他相关还款责任</th>
			</tr>
			<tr>
				<th width="10%">账户数</th>
				<th width="20%">担保金额</th>
				<th width="20%">余额</th>
				<th width="10%">账户数</th>
				<th width="20%">还款责任金额</th>
				<th width="20%">余额</th>
			</tr>
			<tr>
				<td><input type="text" id="peEnAccountCount"
					name="peEnAccountCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="peEnRepay" name="peEnRepay"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="peEnBalance" name="peEnBalance"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="peReAccountCount"
					name="peReAccountCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="peReRepay" name="peReRepay"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="peReBalance" name="peReBalance"
					value="---" readonly="readonly" /></td>
			</tr>
			<tr>
				<th colspan="6">为企业</th>
			</tr>
			<tr>
				<th colspan="3">担保责任</th>
				<th colspan="3">其他相关还款责任</th>
			</tr>
			<tr>
				<th width="10%">账户数</th>
				<th width="20%">担保金额</th>
				<th width="20%">余额</th>
				<th width="10%">账户数</th>
				<th width="20%">还款责任金额</th>
				<th width="20%">余额</th>
			</tr>
			<tr>
				<td><input type="text" id="coEnAccountCount"
					name="coEnAccountCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="coEnRepay" name="coEnRepay"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="coEnBalance" name="coEnBalance"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="coReAccountCount"
					name="coReAccountCount" value="---" readonly="readonly" /></td>
				<td><input type="text" id="coReRepay" name="coReRepay"
					value="---" readonly="readonly" /></td>
				<td><input type="text" id="coReBalance" name="coReBalance"
					value="---" readonly="readonly" /></td>
			</tr>


		</table>
		<br>
		<h3>(五)非信贷交易信息概要</h3>
		<table id="noCredit" class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="3">后付费业务欠费信息汇总</th>
			</tr>
			<tr>
				<td>业务类型</td>
				<td>账户数</td>
				<td>欠费金额</td>
			</tr>

		</table>
		<br>
		<h3>(六)公共信息概要</h3>
		<table id="PublicMesCollect" class="dvi-table" style="width: 98%;"
			border="">
			<tr>
				<th colspan="3">公共信息汇总</th>
			</tr>
			<tr>
				<td>信息类型</td>
				<td>记录数</td>
				<td>涉及金额</td>
			</tr>

		</table>
		<br>
		<h3>(七)查询记录概要</h3>

		<table class="dvi-table" style="width: 98%;" border="">
			<tr>
				<th colspan="2">最近一个月内查询机构数</th>
				<th colspan="3">最近一个月内查询机次数</th>
				<th colspan="3">最近二年内的查询次数</th>
			</tr>
			<tr>
				<td>贷款审批</td>
				<td>信用卡审批</td>
				<td>贷款审批</td>
				<td>信用卡审批</td>
				<td>本人查询</td>
				<td>贷后管理</td>
				<td>担保资格审查</td>
				<td>特约商户实名审查</td>
			</tr>
			<tr>
				<td><input type="text" id="queryOrg1mSum2"
					name="queryOrg1mSum2" value="---" readonly="readonly" /></td>
				<td><input type="text" id="queryOrg1mSum3"
					name="queryOrg1mSum3" value="---" readonly="readonly" /></td>
				<td><input type="text" id="queryRec1mSum2"
					name="queryRec1mSum2" value="---" readonly="readonly" /></td>
				<td><input type="text" id="queryRec1mSum3"
					name="queryRec1mSum3" value="---" readonly="readonly" /></td>
				<td><input type="text" id="queryRec1mSum4"
					name="queryRec1mSum4" value="---" readonly="readonly" /></td>
				<td><input type="text" id="queryRec2ySum1"
					name="queryRec2ySum1" value="---" readonly="readonly" /></td>
				<td><input type="text" id="queryRec2ySum5"
					name="queryRec2ySum5" value="---" readonly="readonly" /></td>
				<td><input type="text" id="queryRec2ySum6"
					name="queryRec2ySum6" value="---" readonly="readonly" /></td>
			</tr>

		</table>
		<br>

		<h3>三 信贷交易信息明细</h3>

		<h3>（一）被追偿信息</h3>
		<div id="creditDebt"></div>
		<h3>（二）非循环贷账户</h3>
		<div id="creditNoCycle"></div>
		<h3>（三）循环额度下分账户</h3>
		<div id="cycleSplit"></div>
		<h3>（四）循环贷账户</h3>
		<div id="cycleCredit"></div>
		<h3>（五）贷记卡账户</h3>
		<div id="creditCard"></div>
		<h3>（六）准贷记卡账户</h3>
		<div id="toCreditCard"></div>
		<h3>（七）相关还款责任人信息</h3>
		<div id="relateRepayPe">
			<h3>有相关还款责任的个人借款</h3>
		</div>
		<div id="relateRepayCom">
			<h3>有相关还款责任的企业借款</h3>
		</div>
		<h3>（八）授信协议信息</h3>
		<div id="pcaMessage"></div>
		<h3>四 非信贷交易信息明细</h3>
		<h3>后付费记录</h3>
		<div id="telpayment"></div>
		<h3>五 公共信息明细</h3>
		<h3>(一)欠税记录</h3>
		<div id="taxarrear"></div>
		<h3>(二)民事判决记录</h3>
		<div id="civjudge"></div>
		<h3>(三)强制执行记录</h3>
		<div id="forceexe"></div>
		<h3>(四)行政处罚记录</h3>
		<div id="adminPunish"></div>
		<h3>(五)住房公积金参缴记录</h3>
		<div id="accfund"></div>
		<h3>(六)低保救助记录</h3>
		<div id="salvation"></div>
		<h3>(七)执业资格记录</h3>
		<div id="competence"></div>
		<h3>(八)行政奖励记录</h3>
		<div id="adminaward"></div>
		<h3>六 本人声明</h3>
		<div id="perAnno"></div>
		<h3>七 异议标注</h3>
		<div id="difLabel"></div>

		<h3>八 查询记录</h3>
		<div id="queryRec"></div>
	</form>
	<h3>报告说明</h3>
	<table id="tail_table"
		style="font-size: 11px; font-color: black; width: 98%">
		<thead>
			<tr>
				<td>
			1.本报告由中国人民银行征信中心出具，依据截至报告时点的个人征信系统记录的信息生成。除查询记录外，其他信息均由相关机构提供，征信中心不保证其真实性和准确性，但承诺在信息汇总、加工、整合的全过程中保持客观、中立的地位.<br/>
			2. 本报告中的“数字解读”仅供使用本信用报告的银行等授信机构参考，授信机构应自行承担使用“数字解读”的相关法律责任。<br/>
			3. “数字解读”将信用报告内容解读为一个数值，是对信用主体未来信贷违约可能性的预测，其取值范围为 0 到 1000，分值越高，违约可能性越低；“相对位置”是信用主体的数字解读值在全部人群中的百分比排序位置，比如“>50%” 代表该数字解读值高于50%的信用主体；“说明”中的“影响因素”是影响信用主体获得更高数字解读值的原因，根据当前信用报告的实际情况给出，最多有两条。“数字解读”显示为“--”的，仅代表无法根据当前信用报告内
容给出数字解读值，并无其他含义。无法给出数字解读值的具体原因见“说明”;<br/>
			4. 本报告的信贷交易信息提示中，“业务类型”为“其他” 的汇总信息不包含“资产处置” 和“垫款” 业务。<br/>
			5. 本报告中如果没有“信贷交易违约信息概要”信息，说明信用主体最近 5 年内没有连续逾期。<br/>
			6. 对于存在授信限额的协议信息，信息主体的可用额度需结合“授信协议信息” 中的授信额度、授信限额信息和余额进行估算。<br/>
			7. 本报告中的信贷交易授信及负债信息概要展示的信息是指未结清/未销户的授信及负债信息。<br/>
			8. 本报告的借贷交易明细信息中，循环贷账户的到期日期是指账户授信额度的到期日期。<br/>
			9. 本报告的借贷交易明细信息中，借贷账户展示最近 5 年的还款情况，包括当前还款状态和当前逾期总额。<br/>
			10.对于通过自助渠道办理的“小额、 高频” 业务，金融机构将合并报送相关账户，展示在本报告的借贷交易明细信息 中；此时账户的还款方式为“不区分还款方式”，该账户的还款频率统一约定为“月”，“还款期数”按月计算， 其 还款信息按月进行观测和更新。<br/>
			11.本报告中的逾期准贷记卡账户是指该账户 60 天以上的透支行为。<br/>
			12.本报告中的还款期数为“--”是指该账户是非分期还款。<br/>
			13.本报告不展示 5 年前已经结束的违约行为，以及 5 年前的欠税记录、强制执行记录、民事判决记录、行政处罚记录、电信欠费记录。<br/>
			14.机构说明是数据提供机构对具体业务添加的特别说明信息。<br/>
			15.本人声明是信息主体对信用报告中的信息所附注的简要说明，信用主体对本人声明的真实性负责。<br/>
			16.异议标注是征信中心添加的，用于说明信用主体对信用报告中的哪些信息有异议。<br/>
			17.本报告内容涉及个人隐私，查询者应依法使用、妥善保管。因使用不当造成个人信息泄露的，征信中心将不承担相 关责任。
			18.本报告中所有金额（除“有相关还款责任的企业借款”中的金额外）均为人民币金额，参照查询日前一天的汇率。<br/>
			19.本报告整合了数据提供机构以信息主体不同证件报送的信息</td>
			</tr>
		</thead>

	</table>
	<div id="windaijika"></div>
</body>

<script>
	var url = location.href;
	//报告编号
	var uniqueId =url.split("=")[1];
	if (uniqueId != '') {
		uniqueId = $.trim(uniqueId);
		uniqueId = uniqueId.split("&")[0];;
	}

	$(function() {
		//查询基本信息
		$.ajax({
			url : "${basePath}/pboc/report/queryBaseInfo.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId
			},
			success : function(data) {
				$("#infoCollForm").form("load", data);
				if (data.effectiveDate == undefined && data.phone == undefined
						&& data.deadLine == undefined) {
					$("#fangQiZa").hide();
				}
				getDictName('queryedCertType', 'CERT_TYPE',
						data.queryedCertType);
				if ($("#queryOrg").val() != undefined
						&& $("#queryOrg").val() == "B10811000H0001") {
					$('#queryOrg').val("华夏银行股份有限公司");
				}
				if ($("#queryOrg").val() != undefined
						&& $("#queryOrg").val() == "X1100010000045") {
					$('#queryOrg').val("华夏银行股份有限公司");
				}
				getDictName('qryReason', 'CXYY_BANK', data.qryReason);
				getDictName('gender', 'SEX_BANK', data.gender);
				getDictName('maritalStatus', 'HYZK_BANK', data.maritalStatus);
				getDictName('residencyStatus', 'XUDM_BANK',
						data.residencyStatus);
				getDictName('degree', 'XWDM_BANK', data.degree);
				getDictName('workStatus', 'JYZK_BANK', data.workStatus);
				getDictName('nationality', 'SJGG_AREA_NAME_BANK',
						data.nationality);
				getDictName('mateCertType', 'CERT_TYPE', data.mateCertType);

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//个人数字解读
		$.ajax({
			url : "${basePath}/pboc/report/queryNumberRead.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId
			},
			success : function(data) {
				$("#infoCollForm").form("load", data);
				if (data.descContent != undefined) {
					var arr = data.descContent.split(",");
					//分数影响因素
					var descContent = "";
					for (var i = 0; i < arr.length; i++) {
						descContent += getDictNameByList("FENSHUYXYS_BANK",
								arr[i])
								+ " ";
					}
					$("#descContent").val(descContent);
				}

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//信贷交易信息提示
		$.ajax({
			url : "${basePath}/pboc/report/queryCreditTransaction.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId
			},
			dataType : "json",
			success : function(data) {
				$("#infoCollForm").form("load", data);

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//逾期透支信息汇总
		$.ajax({
			url : "${basePath}/pboc/report/queryOverDue.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId
			},
			success : function(data) {
				$("#infoCollForm").form("load", data);

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//信贷交易违约信息概要
		$.ajax({
			url : "${basePath}/pboc/report/queryCreditTranAndDebt.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId
			},
			dataType : "json",
			success : function(data) {
				$("#infoCollForm").form("load", data.infoCycle);
				$("#infoCollForm").form("load", data.infoCredit);
				$("#infoCollForm").form("load", data.infoStaCredit);
				$("#infoCollForm").form("load", data.repay);

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//后付费业务账户信息汇总
		$.ajax({
			url : "${basePath}/pboc/report/queryNoCreditLoanSummary.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId
			},
			success : function(data) {
				var str = "";
				if (data[0] != null) {
					for (var i = 0; i < data.length; i++) {
						str += "<tr>"
								+ "<td>"
								+ getDictNameByList("HFF_YWLX_BANK",
										data[i].hffServType) + "</td>" + "<td>"
								+ data[i].arrearageCount + "</td>" + "<td>"
								+ data[i].arrearageMoney + "</td>" + "<tr>";
					}
				}
				$("#noCredit").append(str);

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//公共信息概要
		$.ajax({
			url : "${basePath}/pboc/report/queryPublicMesCollect.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId
			},
			success : function(data) {
				var str = "";
				if (data[0] != null) {
					for (var i = 0; i < data.length; i++) {
						str += "<tr>"
								+ "<td>"
								+ getDictNameByList("PUBLIC_MES_TYPE_BANK",
										data[i].ggxnType) + "</td>" + "<td>"
								+ data[i].recordCount + "</td>" + "<td>"
								+ data[i].involveMoney + "</td>" + "<tr>";
					}
				}
				$("#PublicMesCollect").append(str);

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//查询记录概要
		$.ajax({
			url : "${basePath}/pboc/report/queryRecords.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId
			},
			success : function(data) {
				$("#infoCollForm").form("load", data);
				//查询记录概要
				if (data.lastQueryOrganCode != undefined
						&& data.lastQueryOrganCode == "B10811000H0001") {
					$('#lastQueryOrganCode').val("华夏银行股份有限公司");
				} else {
					getDictName('lastQueryOrganCode', 'ORG_TYPE_BANK',
							data.lastQueryOrganCode);
				}
				getDictName('lastQueryReason', 'CXYY_BANK',
						data.lastQueryReason);

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//信贷交易信息明细 被追偿信息
		$.ajax({
			url : "${basePath}/pboc/report/queryUrgeGet.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
				'accountType' : 'C1'
			},
			success : function(data) {
				showView(data, "creditDebt");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});

		//信贷交易信息明细 非循环贷
		$.ajax({
			url : "${basePath}/pboc/report/queryUrgeGet.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
				'accountType' : 'D1'
			},
			success : function(data) {
				showView(data, "creditNoCycle");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//信贷交易信息明细 循环额度下分账户
		$.ajax({
			url : "${basePath}/pboc/report/queryUrgeGet.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
				'accountType' : 'R4'
			},
			success : function(data) {
				showView(data, "cycleSplit");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		///信贷交易信息明细 循环贷账户
		$.ajax({
			url : "${basePath}/pboc/report/queryUrgeGet.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
				'accountType' : 'R1'
			},
			success : function(data) {
				showView(data, "cycleCredit");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		///信贷交易信息明细 贷记卡账户
		$.ajax({
			url : "${basePath}/pboc/report/queryUrgeGet.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
				'accountType' : 'R2'
			},
			success : function(data) {
				showView(data, "creditCard");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//信贷交易信息明细 准贷记卡
		$.ajax({
			url : "${basePath}/pboc/report/queryUrgeGet.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
				'accountType' : 'R3'
			},
			success : function(data) {
				showView(data, "toCreditCard");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//有相关还款的个人借款
		$.ajax({
			url : "${basePath}/pboc/report/queryRelateRepayMessage.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
				'identityType' : '1',
			},
			success : function(data) {
				showViewRepay(data, "relateRepayPe");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//有相关还款的企业借款
		$.ajax({
			url : "${basePath}/pboc/report/queryRelateRepayMessage.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
				'identityType' : '2',
			},
			success : function(data) {
				showViewRepay(data, "relateRepayCom");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//授信协议信息
		$.ajax({
			url : "${basePath}/pboc/report/queryPcaMessage.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
			},
			success : function(data) {
				showPcaMessage(data, "pcaMessage");

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//后付费记录
		$.ajax({
			url : "${basePath}/pboc/report/queryTelpaymentMessage.do",
			type : 'post',
			data : {
				'uniqueId' : uniqueId,
			},
			success : function(data) {
				showTelpaymentMessage(data);

			},
			error : function(error, status) {
				alert('请求异常');
			}
		});
		//欠税记录
		$
				.ajax({
					url : "${basePath}/pboc/report/queryTaxarrearMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<table class="dvi-table" style="width:98%;" border="">';
						str += '<tr> <th>编号</th> <th>主管税务机关</th><th>欠税总额</th><th>欠税统计日期</th> </tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>" + (i + 1) + "</td>" + "<td>"
									+ data[i].TAX_ORG_NAME + "</td>" + "<td>"
									+ data[i].OUTSTD_TAXES_AMT_SUM + "</td>"
									+ "<td>" + data[i].OUTSTD_TAX_STAT_DT
									+ "</td>" + "</tr>";
						}
						str += "</table>";
						$("#taxarrear").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//民事判决记录
		$
				.ajax({
					url : "${basePath}/pboc/report/queryCivjudgeMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th>编号</th> <th>立案法院</th><th>案由</th><th>立案日期</th><th>结案方式</th> </tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>"
									+ "<td>"
									+ (i + 1)
									+ "</td>"
									+ "<td>"
									+ data[i].REG_COURT
									+ "</td>"
									+ "<td>"
									+ data[i].CASE_REASON
									+ "</td>"
									+ "<td>"
									+ data[i].REG_DATE
									+ "</td>"
									+ "<td>"
									+ getDictNameByList('MSPJ_JAFS_BANK',
											data[i].CLOSE_CASE_WAY) + "</td>"
									+ "</tr>";
						}
						str += "</table>"
						str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="">'
								+ '<tr> <th>编号</th> <th>判决/调解结果</th><th>判决/调解生效日期</th><th>诉讼标的</th><th>诉讼标的金额</th> </tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>" + (i + 1) + "</td>" + "<td>"
									+ data[i].JUDG_MEDIATE_RST + "</td>"
									+ "<td>" + data[i].JUDG_MEDIATE_EFFCT_DT
									+ "</td>" + "<td>" + data[i].LAW_OBJECT
									+ "</td>" + "<td>" + data[i].LAW_OBJECT_AMT
									+ "</td>" + "</tr>";
						}
						str += "</table>";
						str = str.replace(/undefined/g, "");
						$("#civjudge").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//强制执行记录
		$
				.ajax({
					url : "${basePath}/pboc/report/queryForceexeMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th>编号</th> <th>执行法院</th><th>执行案由</th><th>立案日期</th><th>结案方式</th> </tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>"
									+ "<td>"
									+ (i + 1)
									+ "</td>"
									+ "<td>"
									+ data[i].EXECUTE_COURT
									+ "</td>"
									+ "<td>"
									+ data[i].EXECUTE_CASE_RSN
									+ "</td>"
									+ "<td>"
									+ data[i].REG_DATE
									+ "</td>"
									+ "<td>"
									+ getDictNameByList('QZZX_JAFS_BANK',
											data[i].CLOSE_CASE_WAY) + "</td>"
									+ "</tr>";
						}
						str += '</table><table class="dvi-table" style="width:98%;border-top:0px;" border="">'
								+ '<tr> <th>编号</th> <th>案件状态</th><th>结案日期</th><th>申请执行标的</th><th>申请执行标的价值</th><th>已执行标的</th><th>已执行标的金额</th> </tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>" + (i + 1) + "</td>" + "<td>"
									+ data[i].CASE_STATUS + "</td>" + "<td>"
									+ data[i].CLOSE_CASE_DATE + "</td>"
									+ "<td>" + data[i].APPLY_EXE_OBJ + "</td>"
									+ "<td>" + data[i].APPLY_EXE_OBJ_AMT
									+ "</td>" + "<td>"
									+ data[i].ALREADY_EXE_OBJ + "</td>"
									+ "<td>" + data[i].ALREADY_EXE_OBJ_AMT
									+ "</td>" + "</tr>";
						}
						str += "</table>";
						str = str.replace(/undefined/g, "");
						$("#forceexe").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//行政处罚记录
		$
				.ajax({
					url : "${basePath}/pboc/report/queryAdminPunishMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th>编号</th> <th>处罚机构</th><th>处罚内容</th><th>处罚金额</th> <th>生效日期</th><th>截止日期</th><th>行政复议结果</th></tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>" + (i + 1) + "</td>" + "<td>"
									+ data[i].PUNISH_ORGANIZ + "</td>" + "<td>"
									+ data[i].PUNISH_CONTENT + "</td>" + "<td>"
									+ data[i].PUNISH_AMT + "</td>" + "<td>"
									+ data[i].EFFECT_DATE + "</td>" + "<td>"
									+ data[i].END_DATE + "</td>" + "<td>"
									+ data[i].AD_REVIEW_RESULT + "</td>"
									+ "</tr>";
						}
						str = str.replace(/undefined/g, "");
						str += "</table>";
						$("#adminPunish").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//住房公积金参缴记录 
		$
				.ajax({
					url : "${basePath}/pboc/report/queryAccfundMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th>参缴地</th> <th>参加日期</th><th>处缴月份</th><th>缴至月份</th> <th>缴费状态</th><th>月缴存额</th><th>个人缴存比例</th><th>单位缴存比例</th></tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>"
									+ getDictNameByList('XZQH_BANK',
											data[i].JOIN_SOCL_INSU_PL)
									+ "</td>"
									+ "<td>"
									+ data[i].JOIN_SOCL_INSU_DT
									+ "</td>"
									+ "<td>"
									+ data[i].FIRST_DEPOSIT_YM
									+ "</td>"
									+ "<td>"
									+ data[i].PAY_YM
									+ "</td>"
									+ "<td>"
									+ getDictNameByList('ZFGJJ_JFZT_BANK',
											data[i].PAY_STATUS)
									+ "</td>"
									+ "<td>"
									+ data[i].MONTHLY_DEPOSIT
									+ "</td>"
									+ "<td>"
									+ data[i].PERSON_DEPOSIT_RATE
									+ "</td>"
									+ "<td>"
									+ data[i].COMP_DEPOSIT_RATE
									+ "</td>"
									+ "</tr>"
									+ '<tr><td colspan="7">缴存单位</td><td>信息更新日期</td></tr>'
									+ '<tr><td colspan="7">' + data[i].PAY_COMP
									+ '</td><td>' + data[i].INFO_UPD_TIME
									+ '</td></tr>';
						}
						str += "</table>";
						str = str.replace(/undefined/g, "");
						$("#accfund").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//低保救助记录
		$
				.ajax({
					url : "${basePath}/pboc/report/queryAalvationMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th>编号</th> <th>人员类别</th><th>所在地</th><th>工作单位</th> <th>家庭月收入</th><th>申请日期</th><th>批准日期</th><th>信息更新日期</th></tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>"
									+ (i + 1)
									+ "</td>"
									+ "<td>"
									+ getDictNameByList('DBJZ_RYLB_BANK',
											data[i].PERSONNEL_TYPE)
									+ "</td>"
									+ "<td>"
									+ getDictNameByList('XZQH_BANK',
											data[i].PLACE) + "</td>" + "<td>"
									+ data[i].COMPANY + "</td>" + "<td>"
									+ data[i].HOME_MINCOME + "</td>" + "<td>"
									+ data[i].APP_DATE + "</td>" + "<td>"
									+ data[i].PASS_DATE + "</td>" + "<td>"
									+ data[i].INFO_UPD_TIME + "</td>" + "</tr>";

						}
						str += "</table>";
						str = str.replace(/undefined/g, "");
						$("#salvation").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//执业资格记录
		$
				.ajax({
					url : "${basePath}/pboc/report/queryCompetenceMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th>编号</th> <th>执业资格名称</th><th>等级</th><th>获得日期</th> <th>到期日期</th><th>吊销日期</th><th>颁发机构</th><th>机构所在地</th></tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>"
									+ (i + 1)
									+ "</td>"
									+ "<td>"
									+ data[i].COMPETENCY_NAME
									+ "</td>"
									+ "<td>"
									+ getDictNameByList('JYZG_DJDM_BANK',
											data[i].RANK)
									+ "</td>"
									+ "<td>"
									+ data[i].AWARD_DATE
									+ "</td>"
									+ "<td>"
									+ data[i].EXP_DATE
									+ "</td>"
									+ "<td>"
									+ data[i].REVOKE_DATE
									+ "</td>"
									+ "<td>"
									+ data[i].AWARD_ORG
									+ "</td>"
									+ "<td>"
									+ getDictNameByList('XZQH_BANK',
											data[i].ORG_PLACE) + "</td>"
									+ "</tr>";
						}
						str += "</table>";
						str = str.replace(/undefined/g, "");
						$("#competence").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//行政奖励记录
		$
				.ajax({
					url : "${basePath}/pboc/report/queryAdminawardMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th>编号</th> <th>奖励机构</th><th>奖励内容</th><th>生效日期</th> <th>截止日期</th></tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>" + (i + 1) + "</td>" + "<td>"
									+ data[i].REWARD_ORG + "</td>" + "<td>"
									+ data[i].REWARD_CONTENT + "</td>" + "<td>"
									+ data[i].EFFECT_DATE + "</td>" + "<td>"
									+ data[i].END_DATE + "</td>" + "</tr>";
						}
						str += "</table>";
						str = str.replace(/undefined/g, "");
						$("#adminaward").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//本人声明
		$
				.ajax({
					url : "${basePath}/pboc/report/queryPosLabelMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
						'type' : '3',
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th width="10%">编号</th> <th width="60%">声明内容</th><th width="30%">添加日期</th></tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>" + (i + 1) + "</td>" + "<td>"
									+ data[i].CONTENT + "</td>" + "<td>"
									+ data[i].ADD_DATE + "</td>" + "</tr>";
						}
						str += "</table>";
						$("#perAnno").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//异议标注
		$
				.ajax({
					url : "${basePath}/pboc/report/queryPosLabelMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
						'type' : '1',
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th width="10%">编号</th> <th width="60%">声明内容</th><th width="30%">添加日期</th></tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>" + "<td>" + (i + 1) + "</td>" + "<td>"
									+ data[i].CONTENT + "</td>" + "<td>"
									+ data[i].ADD_DATE + "</td>" + "</tr>";
						}
						str += "</table>";
						$("#difLabel").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});
		//查询记录
		$
				.ajax({
					url : "${basePath}/pboc/report/queryQueryRecMessage.do",
					type : 'post',
					data : {
						'uniqueId' : uniqueId,
					},
					success : function(data) {
						var str = "";
						str += '<br><table class="dvi-table" style="width:98%;" border="">'
								+ '<tr> <th>编号</th> <th>查询日期</th><th>查询机构</th><th>查询原因</th></tr>';
						for (var i = 0; i < data.length; i++) {
							str += "<tr>"
									+ "<td>"
									+ (i + 1)
									+ "</td>"
									+ "<td>"
									+ data[i].QRY_DATE
									+ "</td>"
									+ "<td>"
									+ getOrgn(data[i].QUERY_ORG,
											data[i].QUERY_ORG_TYPE)
									+ "</td>"
									+ "<td>"
									+ getDictNameByList('CXYY_BANK',
											data[i].QRY_REASON) + "</td>"
									+ "</tr>";
						}
						str += "</table>";
						$("#queryRec").html(str);

					},
					error : function(error, status) {
						alert('请求异常');
					}
				});

	});

	function showView(data, divId) {
		var str = "";
		for (var i = 0; i < data.length; i++) {
			if (data[i].accountType == "C1") {
				data[i].ACCOUNT_STATUS = getDictNameByList(
						'PERSONAL_JDZHZT_C1_BANK', data[i].ACCOUNT_STATUS);
			}
			if (data[i].accountType == "D1") {
				data[i].ACCOUNT_STATUS = getDictNameByList(
						'PERSONAL_JDZHZT_D1_BANK', data[i].ACCOUNT_STATUS);
				if (data[i].nearMonth != undefined) {
					data[i].nearMonth.MACCOUNT_STATUS = getDictNameByList(
							'PERSONAL_JDZHZT_D1_BANK',
							data[i].nearMonth.MACCOUNT_STATUS);
				}
			}
			if (data[i].accountType == "R1") {
				data[i].ACCOUNT_STATUS = getDictNameByList(
						'PERSONAL_JDZHZT_R1_BANK', data[i].ACCOUNT_STATUS);
				if (data[i].nearMonth != undefined) {
					data[i].nearMonth.MACCOUNT_STATUS = getDictNameByList(
							'PERSONAL_JDZHZT_R1_BANK',
							data[i].nearMonth.MACCOUNT_STATUS);
				}
			}
			if (data[i].accountType == "R2" || data[i].accountType == "R3") {
				data[i].ACCOUNT_STATUS = getDictNameByList(
						'PERSONAL_JDZHZT_R2_R3_BANK', data[i].ACCOUNT_STATUS);
				if (data[i].nearMonth != undefined) {
					data[i].nearMonth.MACCOUNT_STATUS = getDictNameByList(
							'PERSONAL_JDZHZT_R2_R3_BANK',
							data[i].nearMonth.MACCOUNT_STATUS);
				}
			}
			if (data[i].accountType == "R4") {
				data[i].ACCOUNT_STATUS = getDictNameByList(
						'PERSONAL_JDZHZT_R4_BANK', data[i].ACCOUNT_STATUS);
				if (data[i].nearMonth != undefined) {
					data[i].nearMonth.MACCOUNT_STATUS = getDictNameByList(
							'PERSONAL_JDZHZT_R4_BANK',
							data[i].nearMonth.MACCOUNT_STATUS);
				}
			}

			if (data[i].SERV_MANAGE_ORGAN_CODE == "B10811000H0001") {
				data[i].SERV_MANAGE_ORGAN_CODE = "华夏银行股份有限公司";
			} else {
				data[i].SERV_MANAGE_ORGAN_CODE = getDictNameByList(
						'ORG_TYPE_BANK', data[i].SERV_MANAGE_ORGAN_TYPE)
						+ '&quot;' + data[i].SERV_MANAGE_ORGAN_CODE + '&quot;';
				data[i].ACCOUNT_MARK = "******";
			}
			data[i].SERV_TYPE = getDictNameByList('PERSONAL_JDJY_YWZL_BANK',
					data[i].SERV_TYPE);
			data[i].CURRENCY = getDictNameByList('CURRENCY_BANK',
					data[i].CURRENCY);
			data[i].GUARANTY_TYPE = getDictNameByList(
					'PERSONAL_JDJY_DBFS_BANK', data[i].GUARANTY_TYPE);
			data[i].COMMON_DEBIT_CREDIT_MARK = getDictNameByList(
					'PERSONAL_JDJY_GTJK_BANK', data[i].COMMON_DEBIT_CREDIT_MARK);
			data[i].REPAYMENT_FREQUENCY = getDictNameByList(
					'PERSONAL_JDJY_HKPL_BANK', data[i].REPAYMENT_FREQUENCY);
			data[i].MODE_OF_REPAYMENT = getDictNameByList(
					'PERSONAL_JDJY_HKFS_BANK', data[i].MODE_OF_REPAYMENT);
			data[i].FIVE_CLASSIFY = getDictNameByList('FIVE_CLASSIFY_BANK',
					data[i].FIVE_CLASSIFY);
			if (data[i].CREDIT_PROTOCOL_MARK != undefined) {
				data[i].CREDIT_PROTOCOL_MARK = "(授信协议标识:"
						+ data[i].CREDIT_PROTOCOL_MARK + ")";
			}
			str += '<br>';
			if (data[i].accountType == "C1") {
				str += '<strong>账户'
						+ (i + 1)
						+ '</strong><br><table class="dvi-table" style="width:98%;" border="">'
						+ '<tr> <th>管理机构</th> <th>业务种类</th><th>债权接收日期</th><th>债权金额</th> <th>债权转移时的还款状态</th>  </tr>'
						+ "<tr>" + "<td>" + data[i].SERV_MANAGE_ORGAN_CODE
						+ "</td>" + "<td>" + data[i].SERV_TYPE + "</td>"
						+ "<td>" + data[i].KL_DATE + "</td>" + "<td>"
						+ data[i].BORROW_MONEY + "</td>" + "<td>"
						+ data[i].ASSIGN_DEBT_REPAY_STATUS + "</td>"
						+ "<tr><td colspan='5'>截至" + data[i].INFO_REPORT_DATE
						+ "</td></tr><table>";

			} else if (data[i].accountType == "D1"
					|| data[i].accountType == "R1"
					|| data[i].accountType == "R4") {
				str += '<strong>账户' + (i + 1) + '</strong>'
				str += data[i].CREDIT_PROTOCOL_MARK;
				if (data[i].accountType == "D1") {
					str += "("
							+ getDictNameByList('PERSONAL_DKFFXS_BANK',
									data[i].LOAN_DISTRIBUTE_FORM) + ")";
				}
				var showTitle = "借款金额"
				if (data[i].accountType == "R1") {
					showTitle = "账户授信额度";
					data[i].BORROW_MONEY = data[i].ACCO_CREDIT_LIMIT;
				}
				str += '<br><table class="dvi-table" style="width:98%;" border="">'
						+ '<tr> <th>管理机构</th> <th>账户标识</th><th>开立日期</th><th>到期日期</th> <th>'
						+ showTitle
						+ '</th> <th>账户币种</th></tr>'
						+ "<tr>"
						+ "<td>"
						+ data[i].SERV_MANAGE_ORGAN_CODE
						+ "</td>"
						+ "<td>"
						+ data[i].ACCOUNT_MARK
						+ "</td>"
						+ "<td>"
						+ data[i].KL_DATE
						+ "</td>"
						+ "<td>"
						+ DateSwith(data[i].DUE_DATE)
						+ "</td>"
						+ "<td>"
						+ data[i].BORROW_MONEY
						+ "</td>"
						+ "<td>"
						+ data[i].CURRENCY
						+ "</td>"
						+ "<tr>"
						+ '<tr> <th>业务种类</th> <th>担保方式</th><th>还款期数</th><th>还款频率</th> <th>还款方式</th> <th>共同借款标志</th> </tr>'
						+ "<tr>"
						+ "<td>"
						+ data[i].SERV_TYPE
						+ "</td>"
						+ "<td>"
						+ data[i].GUARANTY_TYPE
						+ "</td>"
						+ "<td>"
						+ data[i].REPAYMENT_PERIODS
						+ "</td>"
						+ "<td>"
						+ data[i].REPAYMENT_FREQUENCY
						+ "</td>"
						+ "<td>"
						+ data[i].MODE_OF_REPAYMENT
						+ "</td>"
						+ "<td>"
						+ data[i].COMMON_DEBIT_CREDIT_MARK + "</td>" + "</tr>";
				if (data[i].activeStatus == "关闭"
						|| data[i].activeStatus == "呆账") {
					str += "<tr><td colspan='6'>";
					str += "最新表现信息段</td></tr>";
				}
				str += "</table>";

			} else {
				str += '<strong>账户' + (i + 1) + '</strong>'
						+ data[i].CREDIT_PROTOCOL_MARK;
				str += '<br><table class="dvi-table" style="width:98%;" border="">'
						+ '<tr> <th>发卡机构</th> <th>账户标识</th><th>开立日期</th><th>账户授信额度</th> <th>共享授信额度</th> <th>币种</th><th>业务种类</th><th>担保方式</th> </tr>'
						+ "<tr>" + "<td>"
						+ data[i].SERV_MANAGE_ORGAN_CODE
						+ "</td>"
						+ "<td>"
						+ data[i].ACCOUNT_MARK
						+ "</td>"
						+ "<td>"
						+ data[i].KL_DATE
						+ "</td>"
						+ "<td>"
						+ data[i].ACCO_CREDIT_LIMIT
						+ "</td>"
						+ "<td>"
						+ data[i].SHARE_CREDIT_LIMIT
						+ "</td>"
						+ "<td>"
						+ data[i].CURRENCY
						+ "</td>"
						+ "<td>"
						+ data[i].SERV_TYPE
						+ "</td>"
						+ "<td>"
						+ data[i].GUARANTY_TYPE + "</td>" + "</tr>";
				if (data[i].activeStatus == "关闭"
						|| data[i].activeStatus == "呆账") {
					str += "<tr><td colspan='8'>";
					str += "最新表现信息段</td></tr>";
				}
				str += "</table>";
			}

			if (data[i].activeStatus == "未激活") {
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="1";>'
						+ '<tr> <td>截止到今天账户状态未激活 </td></tr></table>';

			}

			if (data[i].activeStatus == "关闭"
					|| data[i].activeStatus == "呆账"
					|| (data[i].activeStatus == "持续更新" && data[i].accountType == 'C1')) {
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border=""; >'
						+ ' <tr> <th width="20%">账户状态</th><th width="40%">余额</th><th width="40%">最近一次还款日期</th> </tr>'
						+ "<tr>" + "<td>"
						+ data[i].ACCOUNT_STATUS
						+ "</td>"
						+ "<td>"
						+ data[i].BALANCE
						+ "</td>"
						+ "<td>"
						+ data[i].NEAREST_REPAY_DATE
						+ "</td>"
						+ "</tr>"
						+ ' <tr> <th width="20%">转出月份</th><th width="40%">还款状态</th><th width="40%">最近一次还款金额</th> </tr>'
						+ "<tr>"
						+ "<td style='height:30px;'>"
						+ data[i].ZC_MONTH
						+ "</td>"
						+ "<td>"
						+ data[i].REPAY_STATUS
						+ "</td>"
						+ "<td>"
						+ data[i].NEAREST_REPAY_MONEY
						+ "</td>"
						+ "</tr>"
						+ ' <tr> <th width="20%">关闭日期</th><th width="40%">五级分类</th><th width="40%">信息报告日期</th> </tr>'
						+ "<tr>"
						+ "<td>"
						+ data[i].CLOSE_DATE
						+ "</td>"
						+ "<td>"
						+ getDictNameByList('FIVE_CLASSIFY_BANK',
								data[i].FIVE_CLASSIFY)
						+ "</td>"
						+ "<td>"
						+ data[i].INFO_REPORT_DATE + "</td>" + "</tr></table>";

			}
			if (data[i].activeStatus == "持续更新" && data[i].accountType != 'C1') {
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="">'
						+ "<tr><td colspan='5'>截至"
						+ data[i].nearMonth.INFO_REPORT_DATE
						+ "</td></tr>"
						+ '<tr> <th>月份</th> <th>账户状态</th><th>余额</th><th>已用额度</th> <th>未出单的大额专项分期余额</th> </tr>'
						+ "<tr>"
						+ "<td style='height:30px;'>"
						+ data[i].nearMonth.MONTH
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.MACCOUNT_STATUS
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.MBALANCE
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.USED_LIMIT
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.UNISSU_DEZX_FQYE
						+ "</td>"
						+ "</tr>"
						+ '<tr> <th>五级分类</th> <th>剩余还款期数</th><th>结算/应还款日</th><th>本月应还款</th> <th>本月实还款</th> </tr>'
						+ "<tr>"
						+ "<td style='height:30px;'>"
						+ getDictNameByList('FIVE_CLASSIFY_BANK',
								data[i].nearMonth.MFIVE_CLASSIFY)
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.REMANENT_REPAY_PERIODS
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.SETTLE_DUE_DATE
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.THIS_MONTH_DUE
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.THIS_MONTH_ACTUAL_PAY
						+ "</td>"
						+ "</tr>"
						+ '<tr> <th>最近一次还款日期</th> <th>当前逾期期数</th><th>当前逾期总额</th><th>逾期31-60天未还本金</th> <th>逾期61-90天未还本金</th> </tr>'
						+ "<tr>"
						+ "<td style='height:30px;'>"
						+ data[i].nearMonth.NEAREST_REPAY_DATE
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.CUR_OVERDUE_PERIODS
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.CUR_OVERDUE_TOTAL
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.OVERDUE3160_NREPAY_CAPITAL
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.OVERDUE6190_NREPAY_CAPITAL
						+ "</td>"
						+ "</tr>"
						+ '<tr> <th>逾期91-180天未还本金</th> <th>逾期180天以上未还本金</th><th>透支180天以上未付余额</th><th>最近6个月平均使用额度</th> <th>最近6个月平均透支余额</th> </tr>'
						+ "<tr>"
						+ "<td style='height:30px;'>"
						+ data[i].nearMonth.OVERDUE91180_NREPAY_CAPITAL
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.OVERDUE180ADD_NREPAY_CAPITAL
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.OVERDRAFT180ADD_NPAY_BALAN
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.RECENT6M_AVG_USE_LIMIT
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.RECENT6M_AVG_OVERBALANCE
						+ "</td>"
						+ "</tr>"
						+ '<tr> <th>最大使用额度</th> <th>最大透支余额</th><th>信息报告日期</th><th></th> <th></th> </tr>'
						+ "<tr>"
						+ "<td style='height:30px;'>"
						+ data[i].nearMonth.MAX_USE_LIMIT
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.MAX_OVERDRAFT_BALANCE
						+ "</td>"
						+ "<td>"
						+ data[i].nearMonth.INFO_REPORT_DATE
						+ "</td>"
						+ "<td></td>" + "<td></td>" + "</tr></table>"

				if (data[i].Dezx != undefined && data[i].Dezx.length > 0) {
					str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="1";>'
							+ '<tr><th colspan="4">大额专项分期信息</th></tr>'
							+ '<tr> <th>大额专项分期额度</th> <th>分期额度生效日期</th> <th>分期额度到期日期</th><th>已用分期金额</th></tr>';
					var dezx = data[i].Dezx;
					for (var k = 0; k < dezx.length; k++) {
						str += "<tr>" + "<td>" + dezx[k].FQED + "</td>"
								+ "<td>" + dezx[k].FQED_EFFECT_DATE + "</td>"
								+ "<td>" + dezx[k].FQED_EXPIRE_DATE + "</td>"
								+ "<td>" + dezx[k].USED_FQ_MONEY + "</td>"
								+ "</tr>";
					}
					str += "<table>";
				}
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border=""; >'
						+ '<tr><th colspan="5">最新表现信息段</th></tr>'
						+ ' <tr> <th width="20%">账户状态</th><th width="40%">余额</th><th width="40%">最近一次还款日期</th> </tr>'
						+ "<tr>" + "<td>"
						+ data[i].ACCOUNT_STATUS
						+ "</td>"
						+ "<td>"
						+ data[i].BALANCE
						+ "</td>"
						+ "<td>"
						+ data[i].NEAREST_REPAY_DATE
						+ "</td>"
						+ "</tr>"
						+ ' <tr> <th width="20%">转出月份</th><th width="40%">还款状态</th><th width="40%">最近一次还款金额</th> </tr>'
						+ "<tr>"
						+ "<td style='height:30px;'>"
						+ data[i].ZC_MONTH
						+ "</td>"
						+ "<td>"
						+ data[i].REPAY_STATUS
						+ "</td>"
						+ "<td>"
						+ data[i].NEAREST_REPAY_MONEY
						+ "</td>"
						+ "</tr>"
						+ ' <tr> <th width="20%">关闭日期</th><th width="40%">五级分类</th><th width="40%">信息报告日期</th> </tr>'
						+ "<tr>"
						+ "<td>"
						+ data[i].CLOSE_DATE
						+ "</td>"
						+ "<td> "
						+ data[i].FIVE_CLASSIFY
						+ "</td>"
						+ "<td>"
						+ data[i].INFO_REPORT_DATE + "</td>" + "</tr></table>";
			}

			if (data[i].years5 != undefined && data[i].years5 != null) {
				var startY = data[i].years5.START_YEAR_MONTH.substring(0, 4);
				var endY = data[i].years5.END_YEAR_MONTH.substring(0, 4);
				var record5 = data[i].years5.YEAR5 + ",";
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="">'
						+ '<tr><th colspan="13">'
						+ data[i].years5.START_YEAR_MONTH
						+ '-'
						+ data[i].years5.END_YEAR_MONTH + '的还款记录</th></tr>';
				str += '<tr><th></th>' + '<th>1</th>' + '<th>2</th>'
						+ '<th>3</th>' + '<th>4</th>' + '<th>5</th>'
						+ '<th>6</th>' + '<th>7</th>' + '<th>8</th>'
						+ '<th>9</th>'
						+ '<th>10</th><th>11</th><th>12</th></tr>';
				for (var j = endY; j >= startY; j--) {
					str += '<tr><td  rowspan="2">' + j + '</td>';
					var sMonth = "";
					for (var k = 1; k < 13; k++) {
						if (k <= 9) {
							sMonth = j + "-0" + k;
						} else {
							sMonth = j + "-" + k;
						}
						if (record5.indexOf(sMonth) != -1
								&& record5.length > (record5.indexOf(sMonth) + 9)) {
							var status = record5.substr(
									record5.indexOf(sMonth) + 8, 1);
							str += '<td style="height:30px;">' + status
									+ '</td>';
						} else {
							str += '<td style="height:30px;"></td>';
						}
					}
					str += "</tr><tr>"
					for (var k = 1; k < 13; k++) {
						if (k <= 9) {
							sMonth = j + "-0" + k;
						} else {
							sMonth = j + "-" + k;
						}
						if (record5.indexOf(sMonth) != -1
								&& record5.length > (record5.indexOf(sMonth) + 11)) {
							var index = record5.indexOf(",", record5
									.indexOf(sMonth));
							var status = record5.substring(record5
									.indexOf(sMonth) + 10, index);
							str += '<td style="height:30px;">' + status
									+ '</td>';
						} else {
							str += '<td style="height:30px;"></td>';
						}
					}

				}
				str += "</tr></table>"
			}
			if (data[i].specailEvent != undefined
					&& data[i].specailEvent.length > 0) {
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="1";>'
						+ '<tr> <th>特殊事件说明</th></tr>';
				var event = data[i].specailEvent;
				for (var k = 0; k < event.length; k++) {
					str += "<tr>"
							+ "<td>"
							+ getDictNameByList('PERSONAL_JDJY_TSSJ_BANK',
									event[k].TYPE) + "</td>" + "</tr>";
				}
				str += "<table>";

			}
			if (data[i].specailTrn != undefined
					&& data[i].specailTrn.length > 0) {
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="1";>'
						+ '<tr> <th>特殊交易类型</th> <th>发生日期</th> <th>变更月数</th><th>发生金额</th><th>明细记录</th></tr>';
				var trn = data[i].specailTrn;
				for (var k = 0; k < trn.length; k++) {
					str += "<tr>"
							+ "<td>"
							+ getDictNameByList('PERSONAL_JDTS_BANK',
									trn[k].TYPE) + "</td>" + "<td>"
							+ trn[k].OCCUR_DATE + "</td>" + "<td>"
							+ trn[k].CHANGING_MONTHS + "</td>" + "<td>"
							+ trn[k].OCCUR_AMT + "</td>" + "<td>"
							+ trn[k].DETAIL_REC + "</td>" + "</tr>";
				}
				str += "<table>";

			}
			if (data[i].label != undefined && data[i].label.length > 0) {
				var label = data[i].label;
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="1";>';
				for (var k = 0; k < label.length; k++) {

					str += "<tr>" + "<th>" + label[k].TYPE + "</th>"
							+ "<th>添加日期</th>" + "</tr><tr>" + "<td>"
							+ label[k].CONTENT + "</td>" + "<td>"
							+ label[k].ADD_DATE + " </td>";
				}
				str += "<table>";

			}

		}

		$('#' + divId).append(str.replace(/undefined/g, ""));

	}
	function showViewRepay(data, divId) {
		var str = "";
		if (divId === "relateRepayPe") {
			dict = "PERSONAL_JDJY_YWZL_BANK";
		} else {
			dict = "QYJD_YWDL_BANK";
		}
		for (var i = 0; i < data.length; i++) {
			str += '<br><strong>账户'
					+ (i + 1)
					+ '</strong><br><table class="dvi-table" style="width:98%;" border="">'
					+ '<tr> <th>管理机构</th> <th>业务种类</th><th>开立日期</th><th>到期日期</th> <th>责任人类型</th><th>还款责任人金额</th><th>币种</th><th>保证合同编号</th>  </tr>'
					+ "<tr>"
					+ "<td>"
					+ getOrgn(data[i].SERV_MANAGE_ORGAN,
							data[i].SERV_MANAGE_ORGAN_TYPE)
					+ "</td>"
					+ "<td>"
					+ getDictNameByList(dict, data[i].SERV_TYPE)
					+ "</td>"
					+ "<td>"
					+ data[i].OPEN_DATE
					+ "</td>"
					+ "<td>"
					+ data[i].EXPIRE_DATE
					+ "</td>"
					+ "<td>"
					+ getDictNameByList('XGHK_ZRRLX_BANK',
							data[i].REPAY_DUTY_MAN_TYPE) + "</td>" + "<td>"
					+ data[i].REPAY_DUTY_MONEY + "</td>" + "<td>"
					+ getDictNameByList('CURRENCY_BANK', data[i].CURRENCY)
					+ "</td>" + "<td>" + data[i].GUARANTEE_PACT_SERIAL
					+ "</td>" + "<tr><td colspan='8'>截止"
					+ data[i].INFO_REPORT_DATE + "</td></tr><table>";
			if (divId === "relateRepayPe") {
				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="1";>'
						+ '<tr> <th>余额</th> <th>五级分类</th> <th>还款状态</th></tr>'
						+ "<tr>"
						+ "<td>"
						+ data[i].BALANCE
						+ "</td>"
						+ "<td>"
						+ getDictNameByList('FIVE_CLASSIFY_BANK',
								data[i].FIVE_CLASSIFY)
						+ "</td>"
						+ "<td>"
						+ data[i].REPAY_STATUS + "</td>" + "</tr></table>";
			} else {

				str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="1";>'
						+ '<tr> <th>余额</th> <th>五级分类</th> <th>逾期月数</th></tr>'
						+ "<tr>"
						+ "<td>"
						+ data[i].BALANCE
						+ "</td>"
						+ "<td>"
						+ getDictNameByList('FIVE_CLASSIFY_BANK',
								data[i].FIVE_CLASSIFY)
						+ "</td>"
						+ "<td>"
						+ data[i].OVERDUE_MONTH_NUM + "</td>" + "</tr></table>";
			}

		}
		str = str.replace(/undefined/g, "");
		$('#' + divId).append(str);
	}
	function showPcaMessage(data, divId) {
		var str = "";
		for (var i = 0; i < data.length; i++) {
			str += '<br><strong>授信协议信息</strong>'
					+ (i + 1)
					+ '<br><table class="dvi-table" style="width:98%;" border="">'
					+ '<tr> <th>管理机构</th> <th>授信协议标识</th><th>生效日期</th><th>到期日期</th><th>授信额度用途</th>   </tr>'
					+ "<tr>"
					+ "<td>"
					+ getOrgn(data[i].SERV_MANAGE_ORGAN,
							data[i].SERV_MANAGE_ORGAN_TYPE)
					+ "</td>"
					+ "<td>"
					+ data[i].CREDIT_PROTOCOL_MARK
					+ "</td>"
					+ "<td>"
					+ data[i].EFFECT_DATE
					+ "</td>"
					+ "<td>"
					+ data[i].EXPIRE_DATE
					+ "</td>"
					+ "<td>"
					+ getDictNameByList('PERSONAL_SXEDUYT_BANK',
							data[i].CREDIT_LIMIT_PURPOSE)
					+ "</td>"
					+ '<tr> <th>授信额度</th> <th>授信限额</th><th>授信限额编号</th><th>已用额度</th><th>币种</th> </tr>'
					+ "<tr>" + "<td>" + data[i].CREDIT_LIMIT + "</td>" + "<td>"
					+ data[i].CREDIT_QUOTA + "</td>" + "<td>"
					+ data[i].CREDIT_QUOTA_SERIAL + "</td>" + "<td>"
					+ data[i].USED_LIMIT + "</td>" + "<td>"
					+ getDictNameByList('CURRENCY_BANK', data[i].CURRENCY)
					+ "</td>" + "</tr></table>";

		}
		str = str.replace(/undefined/g, "");
		$('#' + divId).append(str);
	}

	function showTelpaymentMessage(data) {
		var str = "";
		for (var i = 0; i < data.length; i++) {
			str += '<br><strong>账户'
					+ (i + 1)
					+ '</strong><br><table class="dvi-table" style="width:98%;" border="">'
					+ '<tr> <th>机构名称</th> <th>业务类型</th><th>业务开通日期</th><th>当前缴费状态</th><th>当前欠费金额</th> <th>记账年月</th>  </tr>'
					+ "<tr>"
					+ "<td>"
					+ data[i].TELECOM_FACILITATOR
					+ "</td>"
					+ "<td>"
					+ getDictNameByList('DXJF_YWLX_BANK', data[i].BUSI_TYPE)
					+ "</td>"
					+ "<td>"
					+ data[i].BUSI_OPN_DATE
					+ "</td>"
					+ "<td>"
					+ getDictNameByList('DXJFZH_JFZT_BANK',
							data[i].CUR_PAY_STAT) + "</td>" + "<td>"
					+ data[i].CUR_DEBT_AMT + "</td>" + "<td>"
					+ data[i].TALLY_YM + "</td>" + "</tr></table>";
			var month24 = data[i].PREST24MPAY_STAT;
			var date = data[i].TALLY_YM;
			var end = new Date(date);
			var endYear = getFormatDate(end.setMonth(end.getMonth()));
			var month24Year = "";
			var startDate = end.setMonth(end.getMonth() - 23);
			var startYear = getFormatDate(startDate);
			endY = endYear.substring(0, 4)
			startY = startYear.substring(0, 4);
			dateInfo = new Date(startDate);
			for (var j = 0; j < 24; j++) {
				if (j == 0) {
					if(month24 != undefined)
					month24Year += getFormatDate(startDate)
							+ month24.substr(j, 1);
				} else {
					if(month24 != undefined)
						{
						var sto = getFormatDate(dateInfo.setMonth(dateInfo
								.getMonth() + 1));
						month24Year += sto + month24.substr(j, 1);
						}
				}
			}
			console.log("month24Year" + month24Year);
			str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="">'
					+ '<tr><th colspan="13">'
					+ startYear
					+ '-'
					+ endYear
					+ '的还款记录</th></tr>';
			str += '<tr><th></th>' + '<th>1</th>' + '<th>2</th>' + '<th>3</th>'
					+ '<th>4</th>' + '<th>5</th>' + '<th>6</th>' + '<th>7</th>'
					+ '<th>8</th>' + '<th>9</th>'
					+ '<th>10</th><th>11</th><th>12</th></tr>';
			for (var j = endY; j >= startY; j--) {

				str += '<tr><td>' + j + '</td>';
				var sMonth = "";
				for (var k = 1; k < 13; k++) {
					if (k <= 9) {
						sMonth = j + "-0" + k;
					} else {
						sMonth = j + "-" + k;
					}
					if (month24Year.indexOf(sMonth) != -1) {
						var status = month24Year.substr(month24Year
								.indexOf(sMonth) + 7, 1);
						str += '<td>' + status + '</td>';
					} else {
						str += '<td></td>';
					}
				}

			}
			str += "</tr></table>"

		}
		str = str.replace(/undefined/g, "");
		$("#telpayment").html(str);
	}

	function getDictName(inputName, groupCode, value) {
		$.ajax({
			url : "${basePath}/system/queryDictName.do",
			type : 'post',
			data : {
				'groupCode' : groupCode,
				'value' : value
			},
			success : function(data) {
				$('#' + inputName).val(data);

			},
			error : function(error, status) {
				alert('交易请求异常,状态码[' + status + ']');
			}
		});

	}
	function getOrgn(code, type) {
		if (code == "B10811000H0001") {
			return "华夏银行股份有限公司";
		} else {
			return getDictNameByList('ORG_TYPE_BANK', type);

		}
	}
	//格式化日期 yyyy-MM-dd
	function getFormatDate(value) {
		if (value == null || value == "") {
			return value;
		}
		var date = new Date(value);
		var seperator1 = "-";
		var year = date.getFullYear();
		var month = date.getMonth() + 1;
		var strDate = date.getDate();
		if (month >= 1 && month <= 9) {
			month = "0" + month;
		}
		if (strDate >= 0 && strDate <= 9) {
			strDate = "0" + strDate;
		}
		var currentdate = year + seperator1 + month;
		return currentdate;

	}
	function DateSwith(value) {
		if (value == "2099-12-31") {
			value = "长期有效";
		}
		return value;
	}

	function showLable(data) {

		if (data[i].label != undefined && data[i].label.length > 0) {
			var label = data[i].label;
			str += '<table class="dvi-table" style="width:98%;border-top:0px;" border="1";>';
			for (var k = 0; k < label.length; k++) {

				str += "<tr>" + "<th>" + label[k].TYPE + "</th>"
						+ "<th>添加日期</th>" + "</tr><tr>" + "<td>"
						+ label[k].CONTENT + "</td>" + "<td>"
						+ label[k].ADD_DATE + " </td>";
			}
			str += "<table>";
		}
	}
</script>
<script type="text/javascript">
	$('body').watermark({
		texts : [ "${account}-${staffName}" ], //水印文字
		textColor : "#d2d2d2", //文字颜色
		textFont : '14px 微软雅黑', //字体
		width : 100, //水印文字的水平间距
		height : 100, //水印文字的高度间距（低于文字高度会被替代）
		textRotate : -30
	//-90到0， 负数值，不包含-90
	});
</script>
</html>