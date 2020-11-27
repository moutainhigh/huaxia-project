 /**
 * 分件跨域路径js
 */
//本地
var allotPath='http://106.128.6.76:8091';
$(function(){
	fnLoadWsCode();
	$("#serialNum").combobox({
		onChange:function(){
			moreSelect("serialNum");
		}
	});
	$("#appRegion").combobox({
		onChange:function(){
			moreSelect("appRegion");
		}
	});
	$("#appChannel").combobox({
		onChange:function(){
			moreSelect("appChannel");
		}
	});
	$("#appInput").combobox({
		onChange:function(){
			moreSelect("appInput");
		}
	});
	$("#appProd").combobox({
		onChange:function(){
			moreSelect("appProd");
		}
	});
	$("#creditDecisionLayer").combobox({
		onChange:function(){
			moreSelect("creditDecisionLayer");
		}
	});
	$("#creditDecisionResult").combobox({
		onChange:function(){
			moreSelect("creditDecisionResult");
		}
	});
	$("#fqzValue").combobox({
		onChange:function(){
			moreSelect("fqzValue");
		}
	});
	$("#riskCode").combobox({
		onChange:function(){
			moreSelect("riskCode");
		}
	});
	$("#riskLevel").combobox({
		onChange:function(){
			moreSelect("riskLevel");
		}
	});
	$("#wsCode").combobox({
		onChange:function(){
			moreSelect("wsCode");
		}
	});
	$("#decisionResult").combobox({
		onChange:function(){
			moreSelect("decisionResult");
		}
	});
	$("#custFlag").combobox({
		onChange:function(){
			moreSelect("custFlag");
		}
	});
	$("#resultInfo").combobox({
		onChange:function(){
			moreSelect("resultInfo");
		}
	});
	//预审结论 批准时隐藏  拒绝时显示
	$('#approveResult').combobox({//1通过   2拒绝
		onChange : function(n, o) {
			switch (n) {
			case '1':
				fnInitYsjl();
				break;
			case '2':
				$("#resultInfo+.combo").show();
				break;
			default:
				fnInitYsjl();
			}
			
		}
	});
	$("#onlineTime").combobox({
		onChange:function(){
			moreSelect("onlineTime");
		}
	});
	$("#phoneCertification").combobox({
		onChange:function(){
			moreSelect("phoneCertification");
		}
	});
	$("#serialNum2").combobox({
		onChange:function(){
			moreSelect("serialNum2");
		}
	});
	$("#appRegion2").combobox({
		onChange:function(){
			moreSelect("appRegion2");
		}
	});
	$("#appChannel2").combobox({
		onChange:function(){
			moreSelect("appChannel2");
		}
	});
	$("#appInput2").combobox({
		onChange:function(){
			moreSelect("appInput2");
		}
	});
	$("#appProd2").combobox({
		onChange:function(){
			moreSelect("appProd2");
		}
	});
	$("#creditDecisionLayer2").combobox({
		onChange:function(){
			moreSelect("creditDecisionLayer2");
		}
	});
	$("#creditDecisionResult2").combobox({
		onChange:function(){
			moreSelect("creditDecisionResult2");
		}
	});
	$("#fqzValue2").combobox({
		onChange:function(){
			moreSelect("fqzValue2");
		}
	});
	$("#riskCode2").combobox({
		onChange:function(){
			moreSelect("riskCode2");
		}
	});
	$("#riskLevel2").combobox({
		onChange:function(){
			moreSelect("riskLevel2");
		}
	});
	$("#wsCode2").combobox({
		onChange:function(){
			moreSelect("wsCode2");
		}
	});
	$("#decisionResult2").combobox({
		onChange:function(){
			moreSelect("decisionResult2");
		}
	});
	$("#custFlag2").combobox({
		onChange:function(){
			moreSelect("custFlag2");
		}
	});
	$("#resultInfo2").combobox({
		onChange:function(){
			moreSelect("resultInfo2");
		}
	});
	//预审结论 批准时隐藏  拒绝时显示
	$('#approveResult2').combobox({//1通过   2拒绝
		onChange : function(n, o) {
			switch (n) {
			case '1':
				fnInitYsjl2();
				break;
			case '2':
				$("#resultInfo2+.combo").show();
				break;
			default:
				fnInitYsjl2();
			}
			
		}
	});
	$("#onlineTime2").combobox({
		onChange:function(){
			moreSelect("onlineTime2");
		}
	});
	$("#phoneCertification2").combobox({
		onChange:function(){
			moreSelect("phoneCertification2");
		}
	});
});
function fnInitYsjl(){
	$("#resultInfo+.combo").hide();
	$("#resultInfo").combobox('select','');
}
function fnInitYsjl2(){
	$("#resultInfo2+.combo").hide();
	$("#resultInfo2").combobox('select','');
}
//初始化网申渠道代码
function fnLoadWsCode(){
	$cf.ajax({
		url:'/opas-naps/queryAllNet.json',
		onSuccess:function(ret){
			var wsCodeInfo=ret.RSP_BODY.wsCodeInfo;
			var wsCodeInfoList=eval('('+wsCodeInfo+')');
			$("#wsCode").combobox({data:wsCodeInfoList});
			$("#wsCode").combobox("setValue","");
			$("#wsCode2").combobox({data:wsCodeInfoList});
			$("#wsCode2").combobox("setValue","");
		}
	});
}
//选择框多选优化
function moreSelect(idFlag){
	var str=$("#"+idFlag).combobox('getText');
	if(str.indexOf("--请选择--")>-1&&str!='--请选择--'){
		if(str.substr(-1)=='-'){
			$("#"+idFlag).combobox('setText',str.replace('--请选择--',''));
		}else{
			$("#"+idFlag).combobox('setText',str.replace('--请选择--,',''));
		}
		//移除请选择
		$("#"+idFlag).combobox('unselect','');
	}else if(str==''){//添加请选择
		$("#"+idFlag).combobox('setText','--请选择--');
		$("#"+idFlag).combobox('select','');
	}
}
function getFormMulData(dataObject,flag) {
	var jsonObject = '{';
	var k = 0;
	var selectList=new Array("serialNum","appRegion","appChannel",
			"appInput","appProd","creditDecisionLayer","creditDecisionResult",
			"fqzValue","riskCode","riskLevel","wsCode","decisionResult","custFlag",
			"resultInfo","onlineTime","phoneCertification"
			);
	$.each(dataObject, function() {
		k++;
		var objName = this.name;
		//避免重复
		if(jsonObject.indexOf(objName)==-1){
			var objValue;
			if(selectList.indexOf(objName)!='-1'){
				if(flag=='2'){
					objValue =$("#"+objName+"2").combobox("getValues");
				}else{
					objValue =$("#"+objName+"").combobox("getValues");
				}
			}else{
				objValue = this.value;
			}
			jsonObject += '"' + objName + '":';
			jsonObject += '"' + objValue + '"';
			if (k < dataObject.length) {
				jsonObject += ",";
			}
		}
	});
	jsonObject += '}';
	return eval("(" + jsonObject + ")");
}
//查询自动件已分配
function fnCountApp(ydjFlag){
	//校验开始时间 、结束时间  不能同时为空   开始时间必须比结束时间早  
	var selectMsg=repSelectTime();
	if(selectMsg!=null&&selectMsg!=''){
		alert(selectMsg);
		return;
	}
	var shareTimeValue=$("#shareTime").combobox("getValues");
	var appTimeStart = $('#appTimeStart').datebox('getValue');
	var appTimeEnd = $('#appTimeEnd').datebox('getValue');
	var crtTimeStart = $('#crtTimeStart').datebox('getValue');
	var crtTimeEnd = $('#crtTimeEnd').datebox('getValue');
	if(shareTimeValue == "1"){
		if(appTimeStart == "" || appTimeEnd == ""){//没有选择起止时间
			showWaringBox('请选择起止时间.');
			return null;
		}
		var appTimeDay = GetDateDiff(appTimeStart,appTimeEnd);
		if (appTimeDay > 6){
			alert("条形码日期区间不能超过7天");
			return;
		}
	}else if(shareTimeValue == "2"){
		if(crtTimeStart == "" || crtTimeEnd == ""){//没有选择起止时间
			showWaringBox('请选择起止时间.');
			return null;
		}
		var crtTimeDay = GetDateDiff(crtTimeStart,crtTimeEnd);
		if (crtTimeDay > 6){
			alert("进件日期区间不能超过7天");
			return;
		}
	}
	var currNode="";
	if(ydjFlag=='1'){
		currNode='02';
	}else if(ydjFlag=='2'){
		var str=$('#ruleType').combobox("getValue"); 
		if(str=='5'){//标准卡审查规则
			currNode='01';
		}else if(str=='6'){//征信
			currNode='02';
		}else if(str=='7'){//审批
			currNode='03';
		}else{
			$.messager.alert("操作提示", "自动分件数量实时查询规则类型必须选值!", "error");
			return;
		}
	}
	var forms = $("#ruleinfo_form");
	var jsondata =getFormMulData(forms.serializeArray(),'1');
	jsondata.currNode=currNode;
	jsondata.ydjFlag=ydjFlag;
	jsondata.strType="5";
	jsondata.secondNode="1";
   $cf.ajax({
     	url:"/opas-allot-server/countAutoApp.json",
      	data:{
     		'jsondata':jsondata
     		}, 
     	onSuccess:function(data){
     		if (data.RSP_BODY.isSuccess) {
				$.messager.alert("操作提示", "查询成功 !", "info", function() {
					fnReset(1);
					var allResult=data.RSP_BODY.allResult;
					var total=data.RSP_BODY.total;
					$("#info_alloted").text(allResult);
					$(".countAppId").text(total);
					if(currNode=='01'){
						$("#bzkReviewCount").textbox('setValue',allResult);
					}else if(currNode=='02'&&ydjFlag=='2'){
						$("#bzkCreditCount").textbox('setValue',allResult);
					}else if(currNode=='02'&&ydjFlag=='1'){
						$("#ydjCreditCount").textbox('setValue',allResult);
					}else if(currNode=='03'){
						$("#bzkApprovalCount").textbox('setValue',allResult);
					}
				});
			} else {
				$.messager.alert("操作提示", "查询异常!", "error");
				return;
			}
       }
   	 });
   window.scroll(0,0);
}
function repSelectTime(){//开始时间与结束时间之间的间隔不能大于31天 否则无效 如果有一天为空为它赋值
	var selectTime1=$("#selectTime1").datetimebox("getValue");
	var selectTime2=$("#selectTime2").datetimebox("getValue");
	var myDate;
	var nowDate;
	var num=0;
	if(selectTime1==""&&selectTime2==""){
		return "开始时间和结束时间同时为空";
	}else if(selectTime1!=""&&selectTime2!=""){
		if(selectTime1>selectTime2){
			return "开始时间晚于结束时间";
		}
		myDate=new Date(selectTime1);
		nowDate=new Date(selectTime2);
		num=(nowDate-myDate)/(1000*3600*24);
		if(num>31){
			return "查询时间段大于31天";
		}
	}else if(selectTime1==""&&selectTime2!=""){
		return "开始时间为空";
	}else if(selectTime1!=""&&selectTime2==""){
		myDate=new Date(selectTime1);
		nowDate=new Date();
		num=(nowDate-myDate)/(1000*3600*24);
		if(num>31){
			return "查询时间段大于31天";
		}
	}
}
//格式化城市 
function formatAppCity(val, row, index) {
	if(val=='11'){
		return "北京";
	}else if(val=='12'){
		return "天津";
	}else if(val=='13'){
		return "石家庄";
	}else if(val=='14'){
		return "济南";
	}else if(val=='15'){
		return "呼和浩特";
	}else if(val=='16'){
		return "太原 ";
	}else if(val=='17'){
		return "青岛";
	}else if(val=='18'){
		return "烟台 ";
	}else if(val=='19'){
		return "聊城";
	}else if(val=='10'){
		return "保定";
	}else if(val=='1A'){
		return "东营";
	}else if(val=='1B'){
		return "鄂尔多斯";
	}else if(val=='1C'){
		return "唐山";
	}else if(val=='1D'){
		return "包头";
	}else if(val=='1E'){
		return "沧州";
	}else if(val=='1F'){
		return "临沂";
	}else if(val=='1G'){
		return "长治";
	}else if(val=='1H'){
		return "滨海";
	}else if(val=='1I'){
		return "日照";
	}else if(val=='1J'){
		return "潍坊";
	}else if(val=='1K'){
		return "邯郸";
	}else if(val=='1L'){
		return "济宁";
	}else if(val=='1M'){
		return "滨州";
	}else if(val=='1N'){
		return "廊坊";
	}else if(val=='1O'){
		return "锦州";
	}
	else if(val=='21'){
		return "上海";
	}else if(val=='22'){
		return "杭州";
	}else if(val=='23'){
		return "南京";
	}else if(val=='24'){
		return "苏州";
	}else if(val=='25'){
		return "宁波";
	}else if(val=='26'){
		return "温州";
	}else if(val=='27'){
		return "无锡";
	}else if(val=='28'){
		return "常州";
	}else if(val=='29'){
		return "绍兴";
	}else if(val=='20'){
		return "镇江";
	}else if(val=='2A'){
		return "南通";
	}else if(val=='2B'){
		return "合肥";
	}else if(val=='2C'){
		return "台州";
	}else if(val=='2D'){
		return "徐州";
	}else if(val=='2E'){
		return "湖州";
	}else if(val=='2F'){
		return "盐城";
	}else if(val=='2G'){
		return "南昌";
	}else if(val=='2H'){
		return "嘉兴";
	}else if(val=='2I'){
		return "扬州";
	}else if(val=='2J'){
		return "泰州";
	}else if(val=='2K'){
		return "舟山";
	}else if(val=='2L'){
		return "淮安";
	}else if(val=='2M'){
		return "吉安";
	}else if(val=='2N'){
		return "芜湖";
	}else if(val=='2O'){
		return "金华";
	}else if(val=='2T'){
		return "宿迁";
	}
	else if(val=='31'){
		return "广州";
	}else if(val=='32'){
		return "深圳";
	}else if(val=='33'){
		return "福州";
	}else if(val=='34'){
		return "泉州";
	}else if(val=='35'){
		return "东莞";
	}else if(val=='36'){
		return "厦门";
	}else if(val=='37'){
		return "佛山";
	}else if(val=='38'){
		return "珠海";
	}else if(val=='39'){
		return "中山";
	}else if(val=='30'){
		return "海口";
	}else if(val=='3A'){
		return "龙岩";
	}else if(val=='3B'){
		return "江门";
	}
	else if(val=='40'){
		return "洛阳";
	}else if(val=='41'){
		return "武汉";
	}else if(val=='42'){
		return "长沙";
	}else if(val=='43'){
		return "襄樊";
	}else if(val=='44'){
		return "郴州";
	}else if(val=='45'){
		return "郑州";
	}else if(val=='46'){
		return "宜昌";
	}else if(val=='47'){
		return "黄冈";
	}
	else if(val=='51'){
		return "重庆";
	}else if(val=='52'){
		return "成都";
	}else if(val=='53'){
		return "昆明";
	}else if(val=='54'){
		return "玉溪";
	}else if(val=='55'){
		return "南宁";
	}else if(val=='56'){
		return "贵阳";
	}
	else if(val=='61'){
		return "西安";
	}else if(val=='62'){
		return "乌鲁木齐";
	}else if(val=='63'){
		return "银川";
	}
	else if(val=='71'){
		return "沈阳";
	}else if(val=='72'){
		return "大连";
	}else if(val=='73'){
		return "鞍山";
	}else if(val=='74'){
		return "长春";
	}else if(val=='75'){
		return "营口";
	}else if(val=='76'){
		return "哈尔滨";
	}else if(val=='90'){
		return "补件";
	}else if(val=='91'){
		return "潜在风险";
	}else if(val=='93'){
		return "快速发卡";
	}else if(val=='96'){
		return "VIP";
	}else if(val=='98'){
		return "异常队列";
	}else if(val=='ZZ'){
		return "网申存量";
	}else if(val=='1P'){
		return "临汾";
	}else if(val=='1Q'){
		return "张家口 ";
	}else{
		return "未知 ";
	}
}
//监听窗口变化,调节标准卡自动分件页面div高度  wdb
function initAutoDivHeight(){
	$(window).on("resize",function(){
		var d_height;
		if(screen.availHeight>950){//宽屏
			d_height=850;
		}else{//窄屏
			d_height=700;
		}
		$("#autoHeight").css("height",d_height);
	}).trigger("resize");
}
//wenyh
function GetDateDiff(startDate,endDate) {
	var startTime = new Date(Date.parse(startDate.replace(/-/g,"/"))).getTime();
	var endTime = new Date(Date.parse(endDate.replace(/-/g,"/"))).getTime();
	var dates = Math.abs((startTime - endTime))/(1000*60*60*24);
	return dates;
}
//wenyh
function showWaringBox(message){
	$.messager.alert('提示',message,'warning');
}