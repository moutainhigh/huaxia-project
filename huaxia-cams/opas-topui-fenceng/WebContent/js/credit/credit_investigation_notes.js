/**
 * 标准卡-征信注纪
 * by 刘志辉
 */

	function GetQueryString(name){
		 var reg = new RegExp("(^|&)"+name+"=([^&]*)(&|$)");
		 var r = window.location.search.substr(1).match(reg);
		 if(r!=null) return unescape(r[2]);
		 return null;
	}
var appId = GetQueryString("appId");
var userId = 'user';
var dic01 = new Array("0:否","1:是");//ss
var a = new Array("S:未婚","M:已婚","O:其它");
var objParam;
var pbocResultOld;
var credit_COMPANY_NAME;
var credit_COMPANY_TEL;
var xiangqingJson;
var tblRole = $("#datalist");
var editWindow = $("#winEdit");
var editForm = $("#editForm");
var addForm = $("#zxeditForm");
var dianhe_options = "<option value=''>---请选择---</option>"+
					 "<option value='申请单位真实(1)'>申请单位真实(1)</option>"+
					 "<option value='申请人信息真实(2)'>申请人信息真实(2)</option>"+
					 "<option value='申请人真实(3)'>申请人真实(3)</option>"+
					 "<option value='(单位+人信息)真实1+2'>(单位+人信息)真实1+2</option>"+
					 "<option value='(人信息+人)真实2+3'>(人信息+人)真实2+3</option>"+
					 "<option value='(单位+人)真实1+3'>(单位+人)真实1+3</option>"+
					 "<option value='(单位+人信息+人)真实1+2+3'>(单位+人信息+人)真实1+2+3</option>"+
					 "<option value='开放式问题ok'>开放式问题ok</option>"+
					 "<option value='宅电ok'>宅电ok</option>"+
					 "<option value='无宅电'>无宅电</option>"+
					 "<option value='NA'>NA</option>"+
					 "<option value='NC'>NC</option>"+
					 "<option value='关机'>关机</option>"+
					 "<option value='停机'>停机</option>"+
					 "<option value='空号'>空号</option>"+
					 "<option value='无效电话'>无效电话</option>"+
					 "<option value='正核正常'>正核正常</option>";
//初始化
$(function(){
	$cf.loadAllDict();//加载数据字典
	
	var custZJInfoForm=$("#custZJInfoForm");
	var custProcInfoForm = $("#custProcInfoForm");
	var custBankInfoForm = $("#custBankInfoForm");
	var yanzhenForm = $("#yanzhenForm");
	
	$("#Y_appId").val(appId);
	$("#operator").val(userId);	
	$cf.ajax({
		data : {
			"appId" : appId
		},
		url : "/opas-naps/creditNotesQuery.json",
		onSuccess : function(res) {
			var jsoncustInfo = res.RSP_BODY.appl;
			var c5Idte1 = res.RSP_BODY.c5Idte1;
			var sbirth = res.RSP_BODY.sbirth;
			var custProcInfo = res.RSP_BODY.pboc;//人行摘要信息
			var custBankInfo = res.RSP_BODY.bank;//二代人行摘要信息
			var queryed_cert_type = "01";
			if(custBankInfo != null){
				queryed_cert_type = custBankInfo["QUERYED_CERT_TYPE"];
			}
			if(queryed_cert_type == "10"){
				$('#custProcInfoForm').hide();
			}else{
				$('#custBankInfoForm').hide();
			}
			var yanzhen = res.RSP_BODY.yanzhenResMap;
			objParam = jsoncustInfo;//为全局变量赋值（此处不用，为保存做准备）
			custZJInfoForm.form("load", jsoncustInfo);
			custProcInfoForm.form("load",custProcInfo);
			custBankInfoForm.form("load",custBankInfo);
			yanzhenForm.form("load", yanzhen);
			$('#c5Idte1').textbox('setValue',c5Idte1);
			$('#c1Birth').textbox('setValue',sbirth);
			$("#c1Marst").textbox('setValue',valueChangeOther(jsoncustInfo.c1Marst,a));//是否婚配
		//	$("Y_companyName").val($("#c1Coname").val());
		//	$("Y_companyTel").val($("#c1Cotel").val());
		//	$("Y_companyAddr").val($("#c1CoaddTotal").val());
			//根据生日计算出生肖
		/*	$("#shenxiao").textbox('setValue',getShenXiao(jsoncustInfo.c1Birth));
			//字典转码textbox('setValue',appId);
			$("#credit_COMPANY_NAME").val(valueChange(yanzhen.credit_COMPANY_NAME,dic01,"#credit_COMPANY_NAME"));
			$("#credit_COMPANY_TEL").val(valueChange(yanzhen.credit_COMPANY_TEL,dic01,"#credit_COMPANY_TEL"));
			$("#crm_MOBILEPHONE").val(valueChange(yanzhen.crm_MOBILEPHONE,dic01,"#crm_MOBILEPHONE"));
			$("#crm_BUSPHONE").val(valueChange(yanzhen.crm_BUSPHONE,dic01,"#crm_BUSPHONE"));
			$("#crm_PHONE").val(valueChange(yanzhen.crm_PHONE,dic01,"#crm_PHONE"));
			$("#crm_COMPANY").val(valueChange(yanzhen.crm_COMPANY,dic01,"#crm_COMPANY"));
			$("#crm_BUSADDR").val(valueChange(yanzhen.crm_BUSADDR,dic01,"#crm_BUSADDR"));
			$("#crm_HOMEADDR").val(valueChange(yanzhen.crm_HOMEADDR,dic01,"#crm_HOMEADDR"));*/
			$("#crm_HOMEADDR").val(valueChange('0',dic01,"#crm_HOMEADDR"));
			$("#pboc_C_COMP_PHONE").val(valueChange('0',dic01,"#pboc_C_COMP_PHONE"));
			$("#pboc_RESI_TEL").val(valueChange('0',dic01,"#pboc_RESI_TEL"));
			test();

			//获得当前流程所在节点
			var spNode = parent.getCurrNode();
			if("03"==spNode){//审批
				$("#buttonArea").hide();
				$("#button").hide();
				$("#jiyaotijiao").hide();
				$("#bohao").hide();
				
				$('#F_telNo').textbox('textbox').attr("disabled",true);
//				$('#bohao').textbox('textbox').attr("disabled",true);
				$('#F_memo').prop("disabled",true);
				
//				$("#app").combobox({readonly:true});
				$("#F_telcheckReuslt").combobox({disabled:true});
				$("#F_noteObject").combobox({disabled:true});
				$("#F_telType").combobox({disabled:true});
				$("#F_telSource").combobox({disabled:true});
				$("#G_zxCode2").combobox({disabled:true});
				$("#G_passCode").combobox({disabled:true});
////				 
				$("#zhengxintianjia").linkbutton('disable');
				$("#wentiku").linkbutton('disable');
				$("#shenchaku").linkbutton('disable');
				$("#zhengxinkuchaxun").linkbutton('disable');
				
				
				
			}
		}
	});
	function valueChangeOther(oldVal,arr){
		var len = arr.length;
		if(oldVal!==null&&oldVal!==undefined&&oldVal!==""){
			for(var i=0;i<len;i++){
				var temp=arr[i].split(":");
				if(oldVal==temp[0]){					
					return temp[1];
				}
			}
		}
		return oldVal;
	}
	
	$('#datalist').datagrid({    
	    url:'/opas-naps/notesMemoQuery.json',
    	queryParams: {
    		appId: appId
    	}
	});
	//设置有值的输入框只读
	$("input[type='text']").each(function(){
		var val=$(this).val();
		if(val!==null&&val!==undefined&&val!==""){
			$(this).attr("readonly","readonly");
		}
	});
	$("#G_zxCode2").combobox({
		onSelect:function(rec){
			showGcode();
		}
	});
	
});
//电话核实部分
function test(){
	var spNode = parent.getCurrNode();
	if(spNode=='03'){
		
		var str = '电核情况：<select id="yzSelected" disabled="disabled">'+dianhe_options+'</select>';
	}else{
		var str = '电核情况：<select id="yzSelected" >'+dianhe_options+'</select>';
	}
	$("#app").append(str);
//	var param = $("#yzSelected").val();
//	alert(param);
	//<a href="javascript:void(0)" class="easyui-linkbutton" onclick="onSubmitJiYao('insert')">纪要提交</a>&nbsp;
//	$("#app").append("<ol id='dianhe'>");
//	$("#yanzheng tr:gt(0)").each(function(i){//遍历行
//		$(this).children("td").each(function(j){//遍历列
//			if($(this).children("input").val()=="是"){ //筛选符合条件的value
//			var s = "验证信息来源："+$("#yanzheng tr").eq(0).children().eq(j).text()+"。" +
//					"验证信息项："+$("#yanzheng tr:gt(0)").eq(i).children().eq(0).text()+"。验证结果：一致。";
//				$("#app").append("<li><input type='checkbox' id='checkbox1'/>" +
//						s + "</li>");
//			}
//		});
//	});
//	$("#app").append("</ol>");
	$("#app").append("<input type='button' onclick='onSubmitDianHe()' class='easyui-linkbutton'  id= 'button' value='提交'/>&nbsp;");
	
}

function onSubmitDianHe(){
	var param = $("#yzSelected").val();
//	alert("111"+param);
	updateMemo(null,appId,param,'1');
//	$("#app").find("li").each(function(){
//		//alert($(this).children("input").is(":checked"));  获取单选框状态
//		//alert($(this).text());                            获取li标签里值
//		if($(this).children("input").is(":checked")==true){
//		//	$("#yanzheng2").append("<li>"+$(this).text()+"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+$("#yzSelected").val()+"</li>");
//			updateMemo(autoId,appId,$(this).text()+"验证信息电话核实："+$("#yzSelected").val(),'1');
//		}
//	});
}

//人行转码
function valueBoxChange(param,id0,id1){
	if(param=='0'){
		$(id0).prop("checked",true);
		$(id1).prop("checked",false);
	}else{
		$(id0).prop("checked",false);
		$(id1).prop("checked",true);
	}
}
//转码，高亮
function valueChange(oldVal,arr,gl){
	var len = arr.length;
	var obj = '';
	if(oldVal!==null&&oldVal!==undefined&&oldVal!==""){
		for(var i=0;i<len;i++){
			var temp=arr[i].split(":");
			if(oldVal==temp[0]){
				obj = temp[1];
			}
		}
	}
	if(oldVal!=null&&oldVal=='1'){
		$(gl).attr("style","background-color:pink;color:red");
	}
	return obj;
}
//页面保存
function onBizInpApplC1(){
	var blockCode= $("#G_zxCode2").combobox('getValue');
	if(blockCode==""){
		$.messager.alert('征信调查提示',"请选择过件码。",'warning');
		return null;
	}
	$cf.ajax({
		url : "/opas-naps/saveTelcheckResult.json",
		data : {
			"appId" : appId,
			"blockCode" : blockCode,
			"creditRefuseReason" : $("#reasion").val(),
			"ydjFlag" : '2' //1易达金 2标准卡
		},
		onSuccess : function(dataRes) {
			var msg = dataRes.RSP_BODY.msg;
			//alert(msg);
			$.messager.alert('征信调查提示',msg,'warning');
		}
	});
/*	var bizInpApplC1Data = {
			"insideAppNo":$("#insideAppNo").val(),
			"appId":appId,
			"c1Nation":$("#c1Nation").val(),
			//"c5Idte1":$("#c5Idte1").val(),
			"c1Ename":$("#c1Ename").val(),
			"c1Hmtel":$("#c1Hmtel").val(),
			"c1Hmare":$("#c1Hmare").val(),
			"c1Mobile":$("#c1Mobile").val(),
			"c1HmaddTotal":$("#c1HmaddTotal").val(),
			"c1Coname":$("#c1Coname").val(),
			"c1Codept":$("#c1Codept").val(),
			"c1Coduty":$("#c1Coduty").val(),
			"c1Cotel":$("#c1Cotel").val(),
			"c1Coext":$("#c1Coext").val(),
			"c1CoaddTotal":$("#c1CoaddTotal").val(),
			"c1Coyr":$("#c1Coyr").val(),
			"c1Earn":$("#c1Earn").val(),
			"c4Cycadd1":$("#c4Cycadd1").val(),
			"c1Marst":$("#c1Marst").val(),
			"qjRemark":$("#G_memo").val()
	};
	$cf.ajax({
		url : "/opas-naps/saveBizInpApplC1.json",
		data : {
			"bizInpApplC1Data" : bizInpApplC1Data,
			"objParam" : objParam
		},
		onSuccess : function(dataRes) {
			var msg = dataRes.RSP_BODY.msg;
			alert(msg);
		}
	});*/
}
//征信查询  的JS
function onZhengxinQuery(str){
	winObj = $("#" + str);
	$('#tblWhiteList').datagrid({    
	    url:'/opas-naps/creditwhitelist_queryList.json',
    	queryParams: {
    		appId: appId
    	},
    	onLoadSuccess:function(data){
    		//alert(data.msg);
    	}
	});
	winObj.window({
		closed : false
	});
}
function addTo4(){
	var row=$("#tblWhiteList").datagrid("getSelections");
	for(var i=0;i<row.length;i++){
		alert(row[i].bigMemo);
	//	updateMemo(null,appId,row[i].bigMemo,'1');
	}
	onConcle('baimingdan');
}

//征信添加 
function onZzhengxinAdd(str){
	winObj = $("#" + str);
	winObj.window({
		closed : false
	});
}
//问题库
function onQusetionWin(str){
	winObj = $("#" + str);
	$cf.ajax({
		url : "/opas-naps/queryConfQuestion.json",
		data : {
			"appId" : appId,//"123456789",
			"status" : "1"
		},
		onSuccess : function(dataRes) {
			var dataMap = {
				"rows" : dataRes.RSP_BODY.rows,
				"total" : dataRes.RSP_BODY.total
			};
			$("#tb_"+str).datagrid("loadData", dataMap);
			$("#tb_"+str).datagrid('getRows');
		}
	}); 
	winObj.window({
		closed : false
	});
}
//审查库
function onReviewWin(){
	var content = '<iframe src="info_compare.html"  width="600px" height="600px"></iframe>';
	var boarddiv='<div id="detailWin"></div>';
	$(document.body).append(boarddiv);
	var win = $("#detailWin").dialog({
		content:content,
		title:'详情展示'
	});
}
//过件码联动显示
function showGcode(){
	//var temp=$("#G_zxCode").val();
	var temp=$("#G_zxCode2").combobox('getValue');
	if(temp=='H' ){
		$("#G_passCode").html("<option value=''>高风险人工征信通过(H100)</option>");
		$("#G_passCode").append("<option value='H900'>高风险人工征信未通过(H900)</option>");
	}else if(temp=='M' ){
		$("#G_passCode").html("<option value=''>人工征信通过(M100)</option>");
		$("#G_passCode").append("<option value='M900'>人工征信未通过(M900)</option>");
	}else if(temp=='L1' || temp=='L2' ||temp=='L' ){
		$("#G_passCode").html("<option value=''>低风险人工征信通过(L100)</option>");
		$("#G_passCode").append("<option value='L900'>低风险人工征信未通过(L900)</option>");
	}else{
		$("#G_passCode").html("<option value=''>无法选择</option>");
	}
	$("#tempDiv").html("");
}
function showReasion(){
	var temp=$("#G_passCode").val();
	if(temp.indexOf("900")>-1){
		$("#tempDiv").append("未通过原因：<select id='reasion'><option value='1'>当地无营业网点</option><option value='2'>未亲见签名</option><option value='1'>其他拒绝原因</option></select>");
	}
}
//显示征信详情 js   
function showDetail(obj){//xiangqingJson
	$("#xiangqing").window({
		closed : false
	});
} 
function addTo2(){
	//updateMemo(null,appId,xiangqingJson,1);
	var row=$("#tb_xiangqing").datagrid("getSelections");
	for(var i=0;i<row.length;i++){
		alert(row[i].bigMemo);
	//	updateMemo(null,appId,row[i].bigMemo,'1');
	}
	updateMemo(null,appId,"流水号：1545651565； 单位名称：华夏银行；  单位地址：石景山； ",'1');
	onConcle('xiangqing');
}
//修改、拼接  大备注       objJsonStr:json串 实体映射,flag:0删除 ，1插入更新    此处待修改成可批量发送，
function updateMemo(autoId,appId,objJsonStr,flag){
	$('#datalist').datagrid({    
	    url:'/opas-naps/saveOrUpdateNotes.json',
    	queryParams: {
    		autoId:autoId,
    		appId: appId,
    		bigMemo:objJsonStr,
    		flagStr:flag
    	},
    	onLoadSuccess:function(data){
    		//alert(data.msg);
    	}
	});
}
//问题库  电核结果保存 OPAS_QUESTION_RESULT
function saveToDianHe(str){
	//updateMemo(null,appId,xiangqingJson,1);
	var row=$("#tb_questionWin").datagrid("getSelections");
	for(var i=0;i<row.length;i++){
		updateMemo(null,appId,"问题描述："+row[i].questionDesc+"；问题答案："+row[i].fromTableNo+"；电核结果：",'1');
		//alert(row[i].fromTableNo+" "+row[i].i);
	}
	onConcle('questionWin');
	
}
function updateResult(autoId,appId,q,a,result){
	$cf.ajax({
		url : "/opas-naps/saveOrUpdateResult.json",
		data : {
			"autoId":autoId,
    		"appId":appId,
    		"question": q,
    		"answer":a,
    		"result":result
		}
	});
}
//征信库添加页面后台逻辑
function onZhenXinKuAddWin(){
	var jsondata = getFormData($("#zxeditForm").serializeArray());
	$cf.ajax({
		url : "/opas-naps/creditwhitelist_save.json",
		data : {
			"appId":$("#Y_appId").val()
			
		},
		onSuccess : function(response) {
			if (response.RSP_BODY.isSuccess) {
				$.messager.alert("操作提示", "修改成功!", "info", function() {
					onConcle("zhenXinKuAddWin");
				});
			} else {
				$.messager.alert("操作提示", "保存异常,原因:" + data.exMsg, "error");
				onConcle("zhenXinKuAddWin");
				return;
			}
		}
	});
	/*var zhenXinKuData = {
		"appId":appId,
		"companyName":$("#Y_companyName").val(),
		"companyTel":$("#Y_companyTel").val(),
		"createDate":$("#Y_createDate").datebox('getValue'),
		"memo":$("#Y_memo").val(),
		"operator":userId
	};
	$cf.ajax({
		url : "/opas-naps/creditTelCheckSave.json",
		data : {
			"zhenXinKuData" : zhenXinKuData
		},
		onSuccess : function(dataRes) {
			onConcle('zhenXinKuAddWin');
			var msg = dataRes.RSP_BODY.msg;
			alert(msg);
		}
	});*/
}
function fun(obj){
	if(obj=='yes'){
		$("#yes_moneyUserIsCompl").prop("checked",true);
		$("#no_moneyUserIsCompl").prop("checked",false);
	}else{
		$("#yes_moneyUserIsCompl").prop("checked",false);
		$("#no_moneyUserIsCompl").prop("checked",true);
	}
}
function fun(obj,param){
	var va_yes = param+"1";
	var va_no = param+"0";
	if(obj=='yes'){
		$(va_yes).prop("checked",true);
		$(va_no).prop("checked",false);
	}else{
		$(va_yes).prop("checked",false);
		$(va_no).prop("checked",true);
	}
}
function formatOper(val, row, index) {   
	var spNode = parent.getCurrNode();
	if("03"==spNode){
		return "<a href=javascript:editNote();></a>&nbsp;<a href=javascript:deleteNote();></a>";
	}else{
		
		return "<a href=javascript:editNote();>修改</a>&nbsp;<a href=javascript:deleteNote();>删除</a>";
	}
}
function formatOper2(val, row, index) {    
	return "<input type='radio' name='"+index+"'  value='一致'/>一致&nbsp;<input type='radio' name='"+index+"' value='不一致'/>不一致&nbsp;";
}
function editNote() {
	var roleObj = $("#datalist").datagrid("getSelected");//alert(roleObj);
	winObj = $("#winEdit");
	winObj.window({
		closed : false,
		collapsible : false,
		minimizable : false,
		onBeforeOpen : setData(roleObj)
	});
}
function setData(data) {
	 $("#editForm").form("load", data);
	$("#id").attr("readonly", "readonly");
	$("#id").attr("disabled", "disabled");
}
function deleteNote(){
	var roleData = $("#datalist").datagrid("getSelected");
	if(roleData.autoId!=null||roleData.autoId!=undefined||roleData.autoId!=''){
		if(confirm("确认要删除吗？")==true){
			updateMemo(roleData.autoId,roleData.appId,null,'0');
		}
	}else{
		alert("删除失败，请刷新重试或与管理员联系");
	}
}
//纪要修改
function onUpdate(){
	var roleData = isRequired('editForm');
	var param = "autoId:"+roleData.autoId+",appId:"+roleData.appId+",bigMemo:"+roleData.bigMemo;
	updateMemo(roleData.autoId,roleData.appId,roleData.bigMemo,'1');
	onConcle('winEdit')
}
//纪要提交
function onSubmitJiYao(){
	var param = "电话来源："+ $("#F_telSource").combobox('getValue')+",电话类型："+
	$("#F_telType").combobox('getValue')+",电话号码："+$("#F_telNo").val()+",照会对象 "+$("#F_noteObject").combobox('getValue')+
	",电核结论:"+$("#F_telcheckReuslt").combobox('getValue')+",备注:"+$("#F_memo").val();
	updateMemo(null,appId,param,'1');
	$("#F_telSource").combobox('setValue','');
	$("#F_telType").combobox('setValue','');
	$("#F_noteObject").combobox('setValue','');
	$("#F_telcheckReuslt").combobox('setValue','');
	$("#F_telNo").textbox('setValue','');
	$("#F_memo").val('');
	/*var pbocResultNew = $("input[name='pboc_CELL_PHONE']:checked").val()+""+
					$("input[name='pboc_RESI_TEL']:checked").val()+""+
					$("input[name='pboc_C_COMP_PHONE']:checked").val()+""+
					$("input[name='pboc_COMPANY']:checked").val()+""+
					$("input[name='pboc_COMP_ADDR']:checked").val()+""+
					$("input[name='pboc_RESIDENT_ADDR']:checked").val();
	var diaochaJiYaoData = {
			"appId" : appId,
			"telSource" : $("#F_telSource").val(),
			"telType" : $("#F_telType").val(),
			"telNo" : $("#F_telNo").val(),
			"telSource" : $("#F_telSource").val(),
			"noteObject" : $("#F_noteObject").val(),
			"telcheckReuslt" : $("#F_telcheckReuslt").val(),
			"memo" : $("#F_memo").val(),
			"moneyUserIsCompl" : $("input[name='moneyUserIsCompl']:checked").val(),
			"passCode":$("#G_passCode").val(),
			"pbocResultOld":pbocResultOld,
			"pbocResultNew":pbocResultNew,
			"flag":obj
		};
	$cf.ajax({
		url : "/opas-naps/surveyJiYaoSave.json",
		data : {
			"diaochaJiYaoData" : diaochaJiYaoData
		},
		onSuccess : function(dataRes) {
			var msg = dataRes.RSP_BODY.msg;
			alert(msg);
		}
	}); */
}
//关闭窗体
function onConcle(obj) {
	var winObj = $("#" + obj);
	winObj.window("close");
}//字段格式转换
function formatCode(value) {
	if(value=='0'){
		return "停用";
	}else{
		return "启用";
	}
}
//获得生肖
function getShenXiao(birthyear){
	if(birthyear==null||birthyear==''){
		return;
	}
	var toyear=1997;
	var birthShenXiao = "OX";
	var x = (toyear-birthyear)%12;
	if((x==-11)||(x==0)){
		birthShenXiao = "鼠";
	}else if(x==0){
		birthShenXiao = "牛";
	}else if((x==11)||(x==-1)){
		birthShenXiao = "虎";
	}else if((x==10)||(x==-2)){
		birthShenXiao = "兔";
	}else if((x==9)||(x==-3)){
		birthShenXiao = "龙";
	}else if((x==8)||(x==-4)){
		birthShenXiao = "蛇";
	}else if((x==7)||(x==-5)){
		birthShenXiao = "马";
	}else if((x==6)||(x==-6)){
		birthShenXiao = "羊";
	}else if((x==5)||(x==-7)){
		birthShenXiao = "猴";
	}else if((x==4)||(x==-8)){
		birthShenXiao = "鸡";
	}else if((x==3)||(x==-9)){
		birthShenXiao = "狗";
	}else if((x==2)||(x==-10)){
		birthShenXiao = "猪";
	}
	return birthShenXiao;
}