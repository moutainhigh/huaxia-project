<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
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

<table class="dv-table"  style="width:100%;border:0" id="detail">
	<tr  style="font-size: 12px">
		<td > 五级分类:
		<input id="accClass5state"	type="text" name="accClass5state"  readonly="readonly" style="color: black;"></td>
		<td >机构类型:
		<input id="loanOrCreditOrgType"	type="text" name="loanOrCreditOrgType"  readonly="readonly" style="color: black;"></td>
		<td >本金余额:
		<input id="accCorpusBal"	type="text" name="accCorpusBal" readonly="readonly" style="color: black;"></td>
		<td >剩余还款期数:
		<input id="accRemPaymentCycNum"	type="text" name="accRemPaymentCycNum" readonly="readonly" style="color: black;"></td>
	</tr>
	<tr  style="font-size: 12px">
		<td >本月应还款:
		<input id="accCurMduePayBal"	type="text" name="accCurMduePayBal" readonly="readonly" style="color: black;"></td>
		<td >应还款日:
		<input id="accDuePaymentDate"	type="text" name="accDuePaymentDate" readonly="readonly" style="color: black;"></td>
		<td >本月实还款:
		<input id="accCurMrealPayAmt"	type="text" name="accCurMrealPayAmt" readonly="readonly" style="color: black;"></td>
		<td >最后一次还款日期:
		<input id="accLatestPaymentDate"	type="text" name="accLatestPaymentDate" readonly="readonly" style="color: black;"></td>
	</tr>
	<tr  style="font-size: 12px">
		<td >当前逾期期数:
		<input id="overDueCurOverDueNum"	type="text" name="overDueCurOverDueNum" readonly="readonly" style="color: black;"></td>
		<td >当前逾期金额:
		<input id="overDueCurOverDueAmt"	type="text" name="overDueCurOverDueAmt" readonly="readonly" style="color: black;"></td>
		<td >逾期31-60天未还本金:
		<input id="overDueUnPayCor31to60d"	type="text" name="overDueUnPayCor31to60d" readonly="readonly" style="color: black;"></td>
		<td >逾期61-90天未还本金:
		<input id="overDueUnPayCor61to90d"	type="text" name="overDueUnPayCor61to90d" readonly="readonly" style="color: black;"></td>
	</tr>
	<tr  style="font-size: 12px">
		<td >逾期91-180天未还本金:
		<input id="overDueUnPayCor91to180"	type="text" name="overDueUnPayCor91to180" readonly="readonly" style="color: black;"></td>
		<td >逾期180天以上未还本金:
		<input id="overDueUnPayCor180dplus"	type="text" name="overDueUnPayCor180dplus" readonly="readonly" style="color: black;"></td>
		<td >还款起始月:
		<input id="m24PaymentStrMonth" type="text" name="m24PaymentStrMonth" readonly="readonly" style="color: black;"></td>
		<td >还款截止月:
		<input id="m24PaymentEndMonth"	type="text" name="m24PaymentEndMonth" readonly="readonly" style="color: black;"></td>
	</tr>
</table>
<fieldset style="border:none;">
		<legend align="left">
				<label style="font-size:12px;font-weight:bold"><input style="border:none;width:60px;font-size:14px;font-weight:bold" type="text" name="m24PaymentStrMonth" readonly=readonly>-&nbsp;&nbsp;<input style="border:none;width:60px;font-size:14px;font-weight:bold" type="text" name="m24PaymentEndMonth" readonly=readonly>的还款记录</label>
			</legend>	
	<table >
	<tr><td>
	<input 	style="width:3.5%; color: black;" type="text" name="text_0" readonly="readonly" >
	<input  style="width:3.5%; color: black;" type="text" name="text_1" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_2" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_3" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_4" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_5" readonly="readonly">
	<input  style="width:3.5%; color: black;"  type="text" name="text_6" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_7" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_8" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_9" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_10" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_11" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_12" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_13" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_14" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_15" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_16" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_17" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_18" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_19" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_20" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_21" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_22" readonly="readonly">
	<input  style="width:3.5%; color: black;" type="text" name="text_23" readonly="readonly">
	</td></tr>
	</table>
	<label style="font-size:12px;font-weight:bold"><input style="border:none;width:60px;font-size:14px;font-weight:bold" type="text" name="m60OverDueStrMonth" readonly=readonly>-&nbsp;&nbsp;<input style="border:none;width:60px;font-size:14px;font-weight:bold" type="text" name="m60OverDueEndMonth" readonly=readonly>的逾期记录</label>
</fieldset>
</form>
<div id="<%=request.getParameter("overDue")%>"  style="width: 99%;height:170px">
</div> 
<div id="<%=request.getParameter("loanCardSpetrad")%>"  style="width: 99%;height:130px">
</div> 
<script type="text/javascript">
$(function(){
	var detailForm=$('#<%=request.getParameter("seq") %>');
	var seq = "<%=request.getParameter("seq") %>";
	var seqjson={"seq":seq};
	$cf.ajax({
		url : "/opas-naps/loanCardInfo_query_seq.json",
		data : seqjson,
		onSuccess : function(response) {
			//给输入框和下拉框赋值写法（后台传给前台一个json串）
			var jsonloanCardInfo = response.RSP_BODY.jsonloanCardInfo;
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
			//五级分类  去掉 类似  1| 的处理
			if(loanCardInfojson.accClass5state){	
			//showSplitChineseById("accClass5state");
			var value=loanCardInfojson.accClass5state;
			if(value!=null&&value!=""){
				var val=value.split("|");
				loanCardInfojson.accClass5state=val[val.length-1]; 
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
 	var loanCardSpetrad='<%=request.getParameter("loanCardSpetrad") %>';
 	var tblObjSpetrad=$("#"+loanCardSpetrad);
 	tblObjSpetrad.datagrid({
		url:"/opas-naps/findLoanSpecialDeal.json",
		border:true,
		//animate:true,
		singleSelect:false,
		//pagePosition:'bottom',
		rownumbers:true,loadMsg:'数据加载中.....',
		pagination:true,pageSize:10,pageList:[10,20,50],
		queryParams:{"faRecSeq":seq,"messType":"01"},
		columns:[[{title:'特殊交易类型',field:'type',width:'15%',align:'center',formatter:showSplitChinese},
		  		{title:'发生日期',field:'occurDate',width:'15%',align:'center'},
				{title:'变更月数',field:'changingMonths',width:'10%',align:'center'},
				{title:'发生金额',field:'occurAmt',width:'13%',align:'center'},
				{title:'明细记录',field:'detailRec',width:'45%',align:'center'}
				]]
		});
 	
 	
})
//对数据进行 分割 去掉 | 前的数据  如：1|自置  分割为  自置 
	function showSplitChinese(value, row, index) {
		if(value!=null&&value!=""){
			var val=value.split("|");
			return val[val.length-1];
		}else{
			return "";
		}
	}  
	/* function showSplitChineseById(id) {
	    var value=$("#"+id).val();
		if(value!=null&&value!=""){
			var val=value.split("|");
			$("#"+id).val(val[val.length-1]); 
		}
	}   */
</script>
</body>
</html>