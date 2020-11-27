<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="../../topui/topui.js"></script>
<script type="text/javascript" src="../../js/author/common.js"></script>

<link rel="stylesheet" href="../../css/common/style.css" type="text/css"></link>


<style type="text/css">
	.dv-table td{
		border:0;
	}
	.dv-label{
		font-weight:bold;
		color:#15428B;
		width:100px;
	}
</style>
<title>Insert title here</title>
</head>
<body>

<form id="<%=request.getParameter("seq") %>">
<table class="dv-table" border="0" style="width:100%;" id="detail">
	<tr style="font-size: 12px">
		<td >机构类型:
		<input id="loanOrCreditOrgType"	type="text" name="loanOrCreditOrgType" readonly="true" style="color: black;"></td>
		<td >共享额度:
		<input id="debCardPayShareLimit"	type="text" name="debCardPayShareLimit" readonly="true" style="color: black;"></td>
		<td >透支余额:
		<input id="debCardPayOverdraftAmt"	type="text" name="debCardPayOverdraftAmt" readonly="true" style="color: black;"></td>
		<td>最近6个月平均透支余额:
		<input id="debCardPay6mavgUsedAmt"	type="text" name="debCardPay6mavgUsedAmt" readonly="true" style="color: black;"></td>
	</tr>
	<tr style="font-size: 12px">
		<td >最大透支余额:
		<input id="debCardPayMaxUsedBal"	type="text" name="debCardPayMaxUsedBal" readonly="true" style="color: black;"></td>
		<td >账单日:
		<input id="accDuePaymentDate"	type="text" name="accDuePaymentDate" readonly="true" style="color: black;"></td>
		<td >本月实还款:
		<input id="accCurMrealPayAmt"	type="text" name="accCurMrealPayAmt" readonly="true" style="color: black;"></td>
		<td >最后一次还款日期:
		<input id="accLatestPaymentDate"	type="text" name="accLatestPaymentDate" readonly="true" style="color: black;"></td>
		
	</tr>
	<tr style="font-size: 12px">
		<td >透支180天以上未还款:
		<input id="overDueUnPayCor180dplus"	type="text" name="overDueUnPayCor180dplus" readonly="true" style="color: black;"></td>
		<td ></td>
		<td></td>
		<td></td>
	</tr>
</table>
</form>
<table><tr>

</tr></table>
<script type="text/javascript">

$(function(){
	var detailForm=$('#<%=request.getParameter("seq") %>');
	var seq = "<%=request.getParameter("seq") %>";
	var seqjson={"seq":seq};
	$cf.ajax({
		url : "/opas-naps/loanCardInfo_query_seq.json",
		data : seqjson,
		onSuccess : function(ret) {
			//给输入框和下拉框赋值写法（后台传给前台一个json串）
			var jsonloanCardInfo = ret.RSP_BODY.jsonloanCardInfo;
			var loanCardInfojson = eval('(' +jsonloanCardInfo+ ')');
			detailForm.form("load", loanCardInfojson);
		}
	});
})
</script>
</body>
</html>