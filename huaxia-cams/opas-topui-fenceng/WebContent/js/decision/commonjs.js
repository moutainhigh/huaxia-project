/**
 * 系统决策公用js
 * author:xuzhiguo 20170311
 */
function GetQueryString(name){
	 var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
	 var r = window.location.search.substr(1).match(reg);
	 if(r!=null) return unescape(r[2]);
	 return null;
}
/*风险名单公用js*/
function openCommonRisklistWin(obj,autoId){
	var tbCommonWin = "tb_"+obj;
	$cf.ajax({
		url : "/opas-naps/queryRisklist.json",
		data : {
			"autoId" : autoId,
			"flag" : obj
		},
		onSuccess : function(dataRes) {
			debugger;
			var dataMap = {
				"rows" : dataRes.RSP_BODY.rows,
				"total" : dataRes.RSP_BODY.total
			};
			$("#"+tbCommonWin).datagrid("loadData", dataMap);
			$("#"+tbCommonWin).datagrid('getRows');
		}
	}); 
	showWin(obj);
}
function commonWinYjd(obj,flag,appId,paramMap){
	var jsonUrl = 'querySystemDecision_tab_view';
	var tbCommonWin = "tb_"+obj;
	var opts = $("#"+tbCommonWin).datagrid("options");
	var s = {"appId":appId,"flag":flag,"paramMap":paramMap,"paramFlag":"2"};
	opts.url = "/opas-naps/"+jsonUrl+".json";
	$("#"+tbCommonWin).datagrid("reload", s);
}
function openCommonWinYjd(obj,flag,appId){
	var jsonUrl = 'querySystemDecision_tab_view';
	var tbCommonWin = "tb_"+obj;
	$("#"+tbCommonWin).datagrid('options').url="/opas-naps/"+jsonUrl+".json";
	$("#"+tbCommonWin).datagrid('reload', {"appId" : appId,"flag":flag,"paramFlag":"1"});
	/*$cf.ajax({
		url : "/opas-naps/"+jsonUrl+".json",
		data : {
			"appId" : appId,
			"flag":flag,
			"paramFlag":"1"
		},
		onSuccess : function(dataRes) {
			if (dataRes.RSP_BODY.isSuccess) {
				var dataMap = {
					"rows" : dataRes.RSP_BODY.rows,
					"total" : dataRes.RSP_BODY.total
				};
				$("#"+tbCommonWin).datagrid("loadData", dataMap);
				$("#"+tbCommonWin).datagrid('getRows');
			} 
		}
	});*/
	showWin(obj);
}
//展示窗口
function showWin(obj) {
	var winObj = $("#" + obj);
	winObj.window({
		closed : false
	});
}

function showOpenWin(obj,param) {
	var tbCommonWin = "tb_"+obj;
	var dataMap = {
			"rows" : new Array(),
			"total" : 0
	};
	$("#"+tbCommonWin).datagrid("loadData",dataMap);
	$("#"+tbCommonWin).datagrid('getRows');
	/*if($("#"+param).val()=='是')	
		return; */
	var winObj = $("#" + obj);
	winObj.window({
		closed : false
	});
	$("#"+obj+"Condion").show();
}
//关闭窗体
function onConcle(obj) {
	var winObj = $("#" + obj);
	winObj.window({
		closed : true
	});
}
//日期格式转换
function formatDate(value) {
	var str = getTaskTime(value);
	if(str.indexOf('1970')>=0){
		return '';
	}else{
		return str;
	}
}
//日期格式转换
function formatDateNoSFM(value) {
	var str = getTaskTimeNoSFM(value);
	if(str.indexOf('1970')>=0){
		return '';
	}else{
		return str;
	}
}
//工具方法：格式化CST类型时间
function getTaskTimeNoSFM(strDate) {   
	if(null==strDate || ""==strDate){  
	    return "";  
	}  
	var dateStr=strDate.trim().split(" ");  
	var strGMT = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";  
	var date = new Date(Date.parse(strGMT));  
	var y = date.getFullYear();  
	var m = date.getMonth() + 1;    
		m = m < 10 ? ('0' + m) : m;  
	var d = date.getDate();    
		d = d < 10 ? ('0' + d) : d;    
	return y+"-"+m+"-"+d;  
};
//工具方法：格式化CST类型时间
function getTaskTime(strDate) {   
	if(null==strDate || ""==strDate){  
	    return "";  
	}  
	var dateStr=strDate.trim().split(" ");  
	var strGMT = dateStr[0]+" "+dateStr[1]+" "+dateStr[2]+" "+dateStr[5]+" "+dateStr[3]+" GMT+0800";  
	var date = new Date(Date.parse(strGMT));  
	var y = date.getFullYear();  
	var m = date.getMonth() + 1;    
		m = m < 10 ? ('0' + m) : m;  
	var d = date.getDate();    
		d = d < 10 ? ('0' + d) : d;  
	var h = date.getHours();  
		h = h < 10 ? ('0' + h) : h;  
	var minute = date.getMinutes();    
	    minute = minute < 10 ? ('0' + minute) : minute;  
	var second = date.getSeconds();  
	second = second < 10 ? ('0' + second) : second;  
	  
	return y+"-"+m+"-"+d+" "+h+":"+minute+":"+second;  
};
function approveResultFormat(val, row, index){
	var flag = row.flag;
	if(flag==null){
		return "";
	}else{
		var spflag = row.approveResult;
		if(spflag=="1"){
			return "批准";
		}else if(spflag=="0"){
			return "拒绝";
		}
	}
	
}
function approveCreditLimitFormat(val, row, index){
	var flag = row.flag;
	if(flag==null){
		return "";
	}else{
		var spflag = row.approveResult;
		if(spflag=="0"){
			return "";
		}else{
			return row.approveCreditLimit
		}
	}
}
function applyStatus(val, row, index){
	var flag = row.flag;
	if(flag==null){
		return "待审";
	}else if(flag=='2'){
		return "归档";
	}else{
		return "待归档";
	}
}
function approveResonCode(val, row, index){
	var flag = row.flag;
	if(flag==null){
		return "";
	}else{
		return row.creditRefuseReason;
	}
}