$(function(){
	var appId = GetQueryString("appId");
	var CRM_view=$("#CRM_view");
	$cf.ajax({
		data : {
			"appId" : appId
		},
		url : "/opas-naps/custCRMInfoView.json",
		onSuccess : function(res) {
			var jsoncustInfo = res.RSP_BODY.jsonStr;
			//进件日期
			var inOrderDate = jsoncustInfo.inOrderDate;
			/*****************************
			 * 近3个月金融可用总资产日均余额
			 * 近3个月储蓄存款日均余额
			 * 近3个月理财日均余额
			 *****************************/			
			if(inOrderDate%3==0){//3,6,9,12
				$("#threeMonthTotalAvg").textbox('setValue',jsoncustInfo.useAumQavg);//近3个月金融可用总资产日均余额
				$("#threeMonthPushMoneyAvg").textbox('setValue',jsoncustInfo.deptQavg);//近3个月储蓄存款日均余额
				$("#threeMMonthLcAvg").textbox('setValue',jsoncustInfo.finQavg);//近3个月理财日均余额
			}else{//1,2,5,7,8,10,11
				$("#threeMonthTotalAvg").textbox('setValue',jsoncustInfo.useAumYavg);
				$("#threeMonthPushMoneyAvg").textbox('setValue',jsoncustInfo.deptYavg);
				$("#threeMMonthLcAvg").textbox('setValue',jsoncustInfo.finYavg);
			}
			//数据导入页面
			CRM_view.form("load", jsoncustInfo);
			var jiejika = res.RSP_BODY.jiejika;
			var wangyin = res.RSP_BODY.wangyin;
			$('#jiejika').datagrid('loadData',jiejika); 
			$('#wangyin').datagrid('loadData', wangyin); 
			$('#danbao').datagrid('loadData', res.RSP_BODY.danbao);
			$('#daikuan').datagrid('loadData', res.RSP_BODY.daikuan);
		}
	});
});
function GetQueryString(name){
	 var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
	 var r = window.location.search.substr(1).match(reg);
	 if(r!=null) return unescape(r[2]);
	 return null;
}