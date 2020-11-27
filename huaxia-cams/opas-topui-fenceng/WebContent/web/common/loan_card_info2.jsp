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
	<tr  style="font-size: 12px">
		<td >机构类型: 
		<input id="loanOrCreditOrgType"	type="text" name="loanOrCreditOrgType" readonly="true" style="color: black;"></td>
		<td >共享额度:
		<input id="debCardPayShareLimit"	type="text" name="debCardPayShareLimit" readonly="true" style="color: black;"></td>
		<td >已用额度:<input id="debCardPayOverdraftAmt"	type="text" name="debCardPayOverdraftAmt" readonly="true" style="color: black;"></td>
		<td >最近6个月平均使用额度:
		<input id="debCardPay6mavgUsedAmt"	type="text" name="debCardPay6mavgUsedAmt" readonly="true" style="color: black;"></td>
	</tr>
	<tr  style="font-size: 12px">
		<td >最大使用额度: 
		<input id="debCardPayMaxUsedBal"	type="text" name="debCardPayMaxUsedBal" readonly="true" style="color: black;"></td>
		<td >本月应还款:
		<input id="accCurMduePayBal"	type="text" name="accCurMduePayBal" readonly="true" style="color: black;"></td>
		<td >账单日: 
		<input id="accDuePaymentDate"	type="text" name="accDuePaymentDate" readonly="true" style="color: black;"></td>
		<td >本月实还款:
		<input id="accCurMrealPayAmt"	type="text" name="accCurMrealPayAmt" readonly="true" style="color: black;"></td>
	</tr>
	<tr  style="font-size: 12px">
		<td >最后一次还款日期: 
		<input id="accLatestPaymentDate"	type="text" name="accLatestPaymentDate" readonly="true" style="color: black;"></td>
		<td >当前逾期期数:
		<input id="overDueCurOverDueNum"	type="text" name="overDueCurOverDueNum" readonly="true" style="color: black;"></td>
		<td >当前逾期金额: 
		<input id="overDueCurOverDueAmt"	type="text" name="overDueCurOverDueAmt" readonly="true" style="color: black;"></td>
		<td></td>
	</tr>
	<tr  style="font-size: 12px">
	    <td>还款起始月:
		<input id="m24PaymentStrMonth" type="text" name="m24PaymentStrMonth" readonly="true" style="color: black;"></td>
		<td>还款截止月:
		<input id="m24PaymentEndMonth"	type="text" name="m24PaymentEndMonth" readonly="true" style="color: black;"></td>
		<td></td><td></td>
	</tr>
</table>
<fieldset  style="border:none;">
		<legend align="left">
				<label style="font-size:12px;font-weight:bold"><input style="border:none;width:60px;font-size:14px;font-weight:bold" type="text" name="m24PaymentStrMonth" readonly=readonly>-&nbsp;&nbsp;<input style="border:none;width:60px;font-size:14px;font-weight:bold" type="text" name="m24PaymentEndMonth" readonly=readonly>的还款记录</label>
			</legend>	
	<table >
	<tr><td>
	<input 	style="width:3.5%; color: black;" type="text" name="text_0" readonly="true" >
	<input  style="width:3.5%; color: black;" type="text" name="text_1" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_2" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_3" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_4" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_5" readonly="true">
	<input  style="width:3.5%; color: black;"  type="text" name="text_6" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_7" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_8" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_9" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_10" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_11" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_12" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_13" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_14" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_15" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_16" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_17" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_18" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_19" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_20" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_21" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_22" readonly="true">
	<input  style="width:3.5%; color: black;" type="text" name="text_23" readonly="true">
	</td></tr>
	</table>
	<label style="font-size:12px;font-weight:bold"><input style="border:none;width:60px;font-size:14px;font-weight:bold" type="text" name="m60OverDueStrMonth" readonly=readonly>-&nbsp;&nbsp;<input style="border:none;width:60px;font-size:14px;font-weight:bold" type="text" name="m60OverDueEndMonth" readonly=readonly>的逾期记录</label>
</fieldset>
</form>
<div id="<%=request.getParameter("overDue")%>"  style="width: 99%;height:170px">
</div> 
<script type="text/javascript">
var seq = "<%=request.getParameter("seq") %>";
$(function(){
	var detailForm=$('#<%=request.getParameter("seq") %>');
	
	var seqjson={"seq":seq};
	$cf.ajax({
		url : "/opas-naps/loanCardInfo_query_seq.json",
		data : seqjson,
		onSuccess : function(ret) {
			//给输入框和下拉框赋值写法（后台传给前台一个json串）
			var jsonloanCardInfo = ret.RSP_BODY.jsonloanCardInfo;
			var loanCardInfojson = eval('(' +jsonloanCardInfo+ ')');
			if(loanCardInfojson.m24PaymentStatus){
				var a = loanCardInfojson.m24PaymentStatus;
				var  inputs=detailForm.find("input");
				for(var i= 0;i<a.length;i++){
				var a1=a.charAt(i);
				var monthName="text_"+i;
				for(var i1=0;i1<inputs.length;i1++){
					var inputName=inputs[i1].name;
					if(inputName==monthName){
						inputs[i1].value=a1;
						break;
					}
				}
				}
				}
			detailForm.form("load", loanCardInfojson);
		}
	});
	var overDue = '<%=request.getParameter("overDue") %>';
 	var tblObj=$("#"+overDue);
 	tblObj.datagrid({
		url:"/opas-naps/query_lastlist_Seq.json",
		border:false,
		//animate:true,
		singleSelect:false,
		//pagePosition:'bottom',
		rownumbers:true,loadMsg:'数据加载中.....',
		pagination:true,pageSize:10,pageList:[10,20,50],
		queryParams:{"faRecSeq":seq},
		columns:[[{title:'逾期月份',field:'month',width:'33%',align:'center'},
		  		{title:'逾期持续月数',field:'lastMonthsStr',width:'25%',align:'center'},
				{title:'逾期金额',field:'amt',width:'33%',align:'center'}
				]]
		});
})

</script>
</body>
</html>