/**
 * 分件公共js
 */
//本地
var allotPath='http://106.128.6.76:8091';
	$(function(){
		fnLoadWsCode();
		fnLoadBzkOrg();
		fnLoadYdjOrg();
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
		$("#blockCode1").combobox({
			onChange:function(){
				moreSelect("blockCode1");
			}
		});
		$("#blockCode2").combobox({
			onChange:function(){
				moreSelect("blockCode2");
			}
		});
		$("#wsCode").combobox({
			onChange:function(){
				moreSelect("wsCode");
			}
		});
		$("#groupBzk").combobox({
			onChange:function(){
				moreSelect("groupBzk");
			}
		});
		$("#groupYdj").combobox({
			onChange:function(){
				moreSelect("groupYdj");
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
		$("#rcdSrc").combobox({
			onChange:function(){
				moreSelect("rcdSrc");
			}
		});
		$("#platType").combobox({
			onChange:function(){
				moreSelect("platType");
			}
		});
		$("#coprType").combobox({
			onChange:function(){
				moreSelect("coprType");
			}
		});
		$("#platRiskLvl").combobox({
			onChange:function(){
				moreSelect("platRiskLvl");
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
	});
	function fnInitYsjl(){
		$("#resultInfo+.combo").hide();
		$("#resultInfo").combobox('select','');
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
	//初始化赋值卡产品类型 
		function fnLoadAppProd() {
			$cf.ajax({
				url : '/opas-naps/queryAllCardProduct.json',
				onSuccess : function(ret) {
					//给输入框和下拉框赋值写法（后台传给前台一个json串）
					var cardInfo = ret.RSP_BODY.cardInfo;
					var cardInfoList = eval('(' + cardInfo + ')');
					$("#appProd").combobox({data:cardInfoList});
					$("#appProd").combobox("setValue","");
				}
			});
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
				}
			});
		}
		//初始化标准卡组别
		function fnLoadBzkOrg(){
			//根据组级别获取组数据-wenyh-2020-10-20
			$cf.ajax({
				url : '/opas-naps/queryOrgByOrgLevelBzk.json',
				onSuccess : function(response) {
					var resultList = response.RSP_BODY.resultList;
					var list = eval('(' +resultList+ ')');
					$("#groupBzk").combobox({data:list});
					$("#groupBzk").combobox("setValue","");
				}
			});
		}
		//初始化易达金组别
		function fnLoadYdjOrg(){
			//根据组级别获取组数据-wenyh-2020-10-20
			$cf.ajax({
				url : '/opas-naps/queryOrgByOrgLevelYdj.json',
				onSuccess : function(response) {
					var resultList = response.RSP_BODY.resultList;
					var list = eval('(' +resultList+ ')');
					$("#groupYdj").combobox({data:list});
					$("#groupYdj").combobox("setValue","");
				}
			});
		}
		//团办号去重
		function fnBatch(){
			//获取分配状态
			var list=new Array();
			list=getCurrStatus();
			var opts = tblBatch.datagrid("options");
			opts.url = "/opas-allot-server/findC4Batch.json";
			var s={
					"list":list,
					"ydjFlag":ydjFlag,
					"secondNode":secondNode,
					'userCode':userCode,
					"currNode":node
					};
			tblBatch.datagrid("reload", s); 
			tblBatch.datagrid("unselectAll");
			 batchWindow.window({
				title:'团办号查询 ',
				closed : false,
				collapsible : false,
				minimizable : false,
			}); 
		}
		//项目号去重 added by jpc
		function fnJCType() {
			// 获取分配状态
			var list = new Array();
			list = getCurrStatus();
			var opts = tblType.datagrid("options");
			opts.url = "/opas-allot-server/findC5JCType.json";
			var s = {
				"list" : list,
				"ydjFlag" : ydjFlag,
				"secondNode" : secondNode,
				'userCode' : userCode,
				"currNode" : node
			};
			tblType.datagrid("reload", s);
			tblType.datagrid("unselectAll");
			typeWindow.window({
				title : '项目号查询 ',
				closed : false,
				collapsible : false,
				minimizable : false,
			});
		}
		function onSaveBatch() {
			var orgObj = tblBatch.datagrid("getChecked");
			var list=new Array();
			for(var i=0;i<orgObj.length;i++){
				list.push(orgObj[i].c4ApBatch);
			} 
			var c4ApBatch=list.join(",");
			onConcle("winBatch");
			$("#c4ApBatch").textbox('setValue',c4ApBatch);
		}
		function onSaveType() {// added by jpc
			var orgObj = tblType.datagrid("getChecked");
			var list=new Array();
			for(var i=0;i<orgObj.length;i++){
				list.push(orgObj[i].c5JcType);
			} 
			var c5JcType=list.join(",");
			onConcle("winType");
			$("#c5JcType").textbox('setValue',c5JcType);
		}
		//推广机构
		function fnAbCode(){
			//获取分配状态
			var list=new Array();
			list=getCurrStatus();
			var opts = tblAbCode.datagrid("options");
			opts.url = "/opas-allot-server/findC5AbCode.json";
			var s={
					"list":list,
					"ydjFlag":ydjFlag,
					"secondNode":secondNode,
					'userCode':userCode,
					"currNode":node
					};
			tblAbCode.datagrid("reload", s); 
			tblAbCode.datagrid("unselectAll");
			abCodeWindow.window({
				closed : false,
				collapsible : false,
				minimizable : false,
			});
		}
		function onSaveAbCode(){
			var orgObj = tblAbCode.datagrid("getChecked");
			var list=new Array();
			for(var i=0;i<orgObj.length;i++){
				list.push(orgObj[i].c5AbCode);
			} 
			var c5AbCode=list.join(",");
			onConcle("winAbCode");
			$("#c5AbCode").textbox('setValue',c5AbCode);
		}
		//推广人员
		function fnAbUser(){
			//获取分配状态
			var list=new Array();
			list=getCurrStatus();
			var opts = tblAbUser.datagrid("options");
			opts.url = "/opas-allot-server/findC4AbUser.json";
			var s={
					"list":list,
					"ydjFlag":ydjFlag,
					"secondNode":secondNode,
					'userCode':userCode,
					"currNode":node
					};
			tblAbUser.datagrid("reload", s); 
			tblAbUser.datagrid("unselectAll");
			abUserWindow.window({
				closed : false,
				collapsible : false,
				minimizable : false,
			});
		}
		function onSaveAbUser(){
			var orgObj = tblAbUser.datagrid("getChecked");
			var list=new Array();
			for(var i=0;i<orgObj.length;i++){
				list.push(orgObj[i].c4AbUser);
			}
			var c4AbUser=list.join(",");
			onConcle("winAbUser");
			$("#c4AbUser").textbox('setValue',c4AbUser);
		}
		// 获取节点编号
		function fnGetNodeId() {
			return location.href.split("=")[1];
		}
		//获取当前分配等级（池、队列、组）
		function fnGetSecondNodeId() {
			return location.href.split("=")[2];
		}
		//区分标准卡  易达金  
		function fnGetProdNodeId() {
			return location.href.split("=")[3];
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
		//全选 或全不选 
		function selectAll(value){
			if(value==1){
				var checklist=document.getElementsByName("queueorgroup");
				if(document.getElementById("all").checked){
					for(var i=0;i<checklist.length;i++){
						checklist[i].checked=1;
					}
				}else{
					for(var j=0;j<checklist.length;j++){
						checklist[j].checked=0;
					}
				}
			}else if(value==2){
				var checklist=document.getElementsByName("queueorgroup2");
				if(document.getElementById("all2").checked){
					for(var i=0;i<checklist.length;i++){
						checklist[i].checked=1;
					}
				}else{
					for(var j=0;j<checklist.length;j++){
						checklist[j].checked=0;
					}
				}
			}else if(value==3){
				var checklist='';
				if(secondNode=='1'||secondNode=='2'){
					checklist=document.getElementsByName("acceptQueue");
				}else if(secondNode=='3'){
					checklist=document.getElementsByName("acceptUser");
				}
				if(document.getElementById("all3").checked){
					for(var i=0;i<checklist.length;i++){
						checklist[i].checked=1;
					}
				}else{
					for(var j=0;j<checklist.length;j++){
						checklist[j].checked=0;
					}
				}
			}else if(value==4){
				var checklist=document.getElementsByName("acceptGroup");
				if(document.getElementById("all4").checked){
					for(var i=0;i<checklist.length;i++){
						checklist[i].checked=1;
					}
				}else{
					for(var j=0;j<checklist.length;j++){
						checklist[j].checked=0;
					}
				}
			}
			
		}
		//去掉单选时候 将全选去掉 
		function selectOne(value){
			var checklist='';
			//取全部 queueorgroup
			if(value==1){
				 checklist=document.getElementsByName("queueorgroup");
			}else if(value==2){
				 checklist=document.getElementsByName("queueorgroup2");
			}else if(value==3){
				if(secondNode=='1'||secondNode=='2'){
					checklist=document.getElementsByName("acceptQueue");
				}else if(secondNode=='3'){
					 checklist=document.getElementsByName("acceptUser");
				}
			}else if(value==4){
				checklist=document.getElementsByName("acceptGroup");
			}
			for(var i=0;i<checklist.length;i++){
				//存在未选中的checkbox  全选去掉 选中 
				if(checklist[i].checked!=true){
					if(value==1){
						document.getElementById("all").checked=false;
					}else if(value==2){
						document.getElementById("all2").checked=false;
					}else if(value==3){
						document.getElementById("all3").checked=false;
					}else if(value==4){
						document.getElementById("all4").checked=false;
					}
					break;
				}else{//全选选中 
					if(value==1){
						document.getElementById("all").checked=true;
					}else if(value==2){
						document.getElementById("all2").checked=true;
					}else if(value==3){
						document.getElementById("all3").checked=true;
					}else if(value==4){
						document.getElementById("all4").checked=true;
					}
				}
			}
		}
		//实时计算待分配件与已分配件
		function fnJiSuan(value){
			//获取所有元素
			var checklist;
			if(value=='1'){//易达金
				checklist=document.getElementsByName("appNum");
			}else if(value=='2'){//标准卡
				checklist=document.getElementsByName("jiSuanId");
			}
			//遍历计算 当前所有待分配件
			var allValue=$("input[name='jiSuan']").val();
			var jiSuan=0;
			var allotValue=0;
			var reg=/^[0-9]*$/;
			var flag=false;
			for(var i=0;i<checklist.length;i++){
				var queueValue = $(checklist[i]).val();
				if (queueValue != null && queueValue != "") {
					//检验是否为数字
					if(reg.test(queueValue)){
						allotValue+=parseInt(queueValue);
						jiSuan=allValue-allotValue;
						if(jiSuan<0){
							alert("输入的数字太多,不满足分配!");
							$(checklist[i]).val("");
							fnJiSuan(value);
							return;
						}
						flag=true;
					}else{
						alert("当前输入的数不是纯数字");
						$(checklist[i]).val("");
						fnJiSuan(value);
						return;
					}
				} 
			}
			//重新赋值  剩余待分配件和已分配件、剩余件
			if(flag==true){//校验通过
				$(".countAppId").text(jiSuan);
				$('#info_remainder').text(jiSuan);
			}else{//不通过
				$(".countAppId").text(allValue);
				$('#info_remainder').text(0);
			}
			$('#info_alloted').text(allotValue);
		}
		function getFormData(dataObject) {
			var jsonObject = '{';
			var k = 0;
			$.each(dataObject, function() {
				k++;
				var objName = this.name;
				var objValue = this.value;
				jsonObject += '"' + objName + '":';
				jsonObject += '"' + objValue + '"';
				if (k < dataObject.length) {
					jsonObject += ",";
				}
			});
			jsonObject += '}';
			return eval("(" + jsonObject + ")");
		}
		function getFormMulData(dataObject) {
			var jsonObject = '{';
			var k = 0;
			var comList=new Array("appTimeStart","appTimeEnd","crtTimeStart","crtTimeEnd","isHaveCardCust","quickCardFlag","pubPolice");
			var textList=new Array("c1CoName","c4ApBatch","c5AbCode","c4AbUser","minNumberRead","maxNumberRead"
					,"minProposedLmt","maxProposedLmt","appId","minRiskScore","maxRiskScore","triggerType1","triggerType2"
					,"batchLabel","pbocRstFinDesp","appStatus2","secondDecisionLH","intelVoiceToArtQueue","misszdResult","stopStatus","waiBao","currQueue","c5JcType");//add c5JcType by jpc;add intelVoiceToArtQueue by wenyh
			var shareTimeValue=$("#shareTime").combobox("getValues");
			var identifyValue=$("#identify").combobox("getValues");
			$.each(dataObject, function() {
				k++;
				var objName = this.name;
				var objValue;
				if(jsonObject.indexOf(objName)==-1){//避免相同选择框重复
					if(textList.indexOf(objName)!=-1){//非选择框
						if(objName=="appId"){
							objValue=$("#appIdStr").val();
						}else if(objName=="currQueue"){
							objValue=$("#currQueue1").val();
						}else{
							objValue=$("#"+objName+"").val();
						}
					}else{
						if(comList.indexOf(objName)>-1){//如果是有隐藏的子选择框
							if(objName=="appTimeStart"&&shareTimeValue=="1"){
								objValue =$("#appTimeStart").combobox("getValues");
							}else if(objName=="appTimeEnd"&&shareTimeValue=="1"){
								objValue =$("#appTimeEnd").combobox("getValues");
							}else if(objName=="crtTimeStart"&&shareTimeValue=="2"){
								objValue =$("#crtTimeStart").combobox("getValues");
							}else if(objName=="crtTimeEnd"&&shareTimeValue=="2"){
								objValue =$("#crtTimeEnd").combobox("getValues");
							}else if(objName=="isHaveCardCust"&&identifyValue=="1"){
								objValue =$("#child_1").combobox("getValues");
							}else if(objName=="quickCardFlag"&&identifyValue=="2"){
								objValue =$("#child_2").combobox("getValues");
							}else if(objName=="pubPolice"&&identifyValue=="3"){
								objValue =$("#child_3").combobox("getValues");
							}else{
								objValue ="";
							}
						}else{//非隐藏的选择框
							objValue =$("#"+objName+"").combobox("getValues");
						}
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
		function onConcle(obj) {
			var winObj = $("#" + obj);
			winObj.window("close");
		}
		//格式化分配状态 
		function formatCurrStatus(val, row, index) {
			if(val==0){
				return "池中未挂起";
			}else if(val==1){
				return "组中未挂起";
			}else if(val==2){
				return "组中已挂起";
			}else if(val==3){
				return "组员未挂起 ";
			}else if(val==4){
				return "组员已挂起";
			}else if(val==5){
				return "池中已挂起";
			}else if(val==6){
				return "队列中未挂起";
			}else if(val==7){
				return "队列中已挂起";
			}
		}
		//格式化处理状态 
		function formatDelStatus(val, row, index) {
			if(val==0){
				return "未完成";
			}else if(val==1){
				return "已完成";
			}else if(val==2){
				return "补件";
			}else if(val==3){
				return "退回";
			}else if(val==4){
				return "挂起";
			}
		}
		//格式化节点状态  
		function formatCurrNode(val, row, index) {
			if(val==01){
				return "录入比对";
			}else if(val==02){
				return "征信调查";
			}else if(val==03){
				return "人工审批";
			}else if(val==04){
				return "归档";
			}else if(val==05){
				return "提报反欺诈";
			}
		}
		//格式化标准卡队列名称
		function formatCurrQueue(val, row, index) {
			if(val=='L'){
				return "低风险人工征信L";
			}else if(val=='L1'){
				return "低风险征信L1";
			}else if(val=='L2'){
				return "低风险征信L2";
			}else if(val=='L3-1'){
				return "W低风险征信L3.1";
			}else if(val=='L3-2'){
				return "W低风险免电核(人工排查)L3.2";
			}else if(val=='LV'){
				return "低风险人工征信LV";
			}else if(val=='M'){
				return "中风险人工征信M";
			}else if(val=='H'){
				return "高风险人工征信H";
			}else if(val=='H1'){
				return "高风险征信H1";
			}else if(val=='S100'){
				return "系统征信自动通过";
			}else if(val=="L1-1"){
				return "低风险征信L1.1";
			}else if(val=="WL1-1"){
				return "W低风险征信L1.1";
			}else if(val=="LR1-1"){
				return "低风险征信LR1.1";
			}else if(val=="WLR1-1"){
				return "W低风险征信LR1.1 ";
			}else if(val=="LR1-2"){
				return "低风险征信LR1.2";
			}else if(val=="WLR1-2"){
				return "W低风险征信LR1.2";
			}else if(val=="LR1-3"){
				return "低风险人工侧核LR1.3";
			}else if(val=="WLR1-3"){
				return "W低风险人工侧核LR1.3";
			}else if(val=="LR3"){
				return "低风险征信LR3";
			}else if(val=="WLR3"){
				return "W低风险征信LR3";
			}else if(val=="LR2-1"){
				return "低风险征信LR2.1";
			}else if(val=="WLR2-1"){
				return "W低风险征信LR2.1";
			}else if(val=="LR2-2"){
				return "低风险征信LR2.2";
			}else if(val=="WLR2-2"){
				return "W低风险征信LR2.2";
			}else if(val=="LR2-3"){
				return "低风险人工侧核LR2.3";
			}else if(val=="WLR2-3"){
				return "W低风险人工侧核LR2.3 ";
			}else if(val=="MR2-1"){
				return "中风险人工审核MR2.1";
			}else if(val=="WMR2-1"){
				return "W中风险人工审核MR2.1";
			}else if(val=="MR2-2"){
				return "中风险人工征信-关注正核MR2.2";
			}else if(val=="WMR2-2"){
				return "W中风险人工征信-关注正核MR2.2";
			}else if(val=="HR"){
				return "高风险人工征信HR";
			}else if(val=="HR2"){
				return "高风险人工征信HR-关注正核 ";
			}else if(val=="RGZX"){
				return "人工征信";
			}else if(val=="H2"){
				return "高风险人工征信H2";
			}else if(val=="LR1_TY"){
				return "低风险征信LR1-TY";
			}else if(val=="LR2_TY"){
				return "低风险征信LR2-TY";
			}else if(val=="LR3.1_TY"){
				return "低风险征信LR3.1-TY";
			}else if(val=="LR3.2_TY"){
				return "低风险征信LR3.2-TY";
			}else if(val=="LR4_TY"){
				return "低风险人工征信LR4-TY";
			}else if(val=="L4"){
				return "低风险人工征信L4";
			}else if(val=="L5"){
				return "低风险人工征信L5";
			}else if(val=="L6"){
				return "低风险人工征信L6";
			}else if(val=="LR_QY"){
				return "低风险征信LR-QY";
			}else if(val=="HC"){
				return "高风险人工征信HC";
			}else if(val=="LC"){
				return "低风险征信LC";
			}
		}
		//日期格式转换
		 function formatDate(value) {
			var str = getTaskTime(value);
			if (str.indexOf('1970') >= 0) {
				return '';
			} else {
				return str;
			}
		} 
		//工具方法：格式化CST类型时间
		function getTaskTime(strDate) {
			if (null == strDate || "" == strDate) {
				return "";
			}
			var dateStr = strDate.trim().split(" ");
			var strGMT = dateStr[0] + " " + dateStr[1] + " " + dateStr[2] + " "
					+ dateStr[5] + " " + dateStr[3] + " GMT+0800";
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
			return y + "-" + m + "-" + d + " " + h + ":" + minute + ":"
					+ second;
		};
		//分配状态 获取
		function getCurrStatus(){
			var appStatus=$("#appStatus").combobox('getValue');
			var appStatus2="";
			if($("input[type='checkbox'][name='appStatus2']").is(':checked')){
				appStatus2='24';
			};
			var secondNode=fnGetSecondNodeId();
			var currStatusList=new Array();
			if(secondNode=="1"){
				if((appStatus!=null&&appStatus!=""&&appStatus!="-1")
					||(appStatus2=="24")
					){
					if((appStatus=="1")&&(appStatus2=="24")){//池中待分配已挂起
						currStatusList.push("5");
					}else if((appStatus=="1")&&(appStatus2!="24")){//池中待分配未挂起
						currStatusList.push("0");
					}else if((appStatus=="2")&&(appStatus2!="24")){//池中已分配显示  到队列     到组或人的    已分配未挂起
						currStatusList.push("1");
						currStatusList.push("3");
						currStatusList.push("6");
					}else if((appStatus=="2")&&(appStatus2=="24")){//到  队列 、组或人    已分配 已挂起
						currStatusList.push("2");
						currStatusList.push("4");
						currStatusList.push("7");
					}else if((appStatus==null||appStatus==""||appStatus=="-1")
						&& (appStatus2=="24")){//挂起标签(显示池中 或队列中    组中  挂起件)
						currStatusList.push("5");
						currStatusList.push("2");
						currStatusList.push("7");
					}
				}else {//默认待分配  未挂起   
					currStatusList.push("0");
				}
			}else if(secondNode=="2"){
				if((appStatus!=null&&appStatus!=""&&appStatus!="-1")
						||(appStatus2=="24")
					){
					if((appStatus=="1")&&(appStatus2=="24")){//队列中  待分配已挂起
						currStatusList.push("7");
					}else if((appStatus=="1")&&(appStatus2!="24")){//队列中待分配未挂起
						currStatusList.push("6");
					}else if((appStatus=="2")&&(appStatus2!="24")){//队列中已分配到组 或人的    已分配未挂起
						currStatusList.push("1");
						currStatusList.push("3");
					}else if((appStatus=="2")&&(appStatus2=="24")){//到组或人    已分配 已挂起
						currStatusList.push("2");
						currStatusList.push("4");
					}else if((appStatus==null||appStatus==""||appStatus=="-1")
							&& (appStatus2=="24")){//挂起标签(显示队列中    组中  挂起件)
						currStatusList.push("7");
						currStatusList.push("2");
					}
				}else {
					currStatusList.push("6");
				}
			}else if(secondNode=="3"){
				if((appStatus!=null&&appStatus!=""&&appStatus!="-1")
						||(appStatus2=="24")
					){
					if((appStatus=="1")&&(appStatus2=="24")){//组中  待分配已挂起
						currStatusList.push("2");
					}else if((appStatus=="1")&&(appStatus2!="24")){//组中待分配未挂起
						currStatusList.push("1");
					}else if((appStatus=="2")&&(appStatus2!="24")){//已分配未挂起
						currStatusList.push("3");
					}else if((appStatus=="2")&&(appStatus2=="24")){// 已分配 已挂起
						currStatusList.push("4");
					}else if((appStatus==null||appStatus==""||appStatus=="-1")
							&& (appStatus2=="24")){//挂起标签(显示    组中  个人  挂起件)
						currStatusList.push("2");
						currStatusList.push("4");
					}
				}else {
					currStatusList.push("1");
				}
			}
			return currStatusList;
		}
		function buttonClean(){
			//因为 加了 value=-1 属性 以至于   form的reset 对这些input框失效了 所以单独clear
			$('#serialNum').combobox('clear');
			$('#appRegion').combobox('clear');
			$('#appChannel').combobox('clear');
			$('#appInput').combobox('clear');
			$('#appProd').combobox('clear');
			$('#shareTime').combobox('clear');
			$('#nosafePromoter').combobox('clear');
			$('#child_1').combobox('clear');
			$('#child_2').combobox('clear');
			$('#child_3').combobox('clear');
			$('#c4CyCadd1').combobox('clear');
			$('#appStatus').combobox('clear');
			$('#identify').combobox('clear');
			$('#creditDecisionLayer').combobox('clear');
			$('#creditDecisionResult').combobox('clear');
			$('#allotType').combobox('clear');
			$('#phoneCertification').combobox('clear');
			$('#fqzValue').combobox('clear');
			$('#riskCode').combobox('clear');
			$('#riskLevel').combobox('clear');
			$('#wsCode').combobox('clear');
			$('#decisionResult').combobox('clear');
			$('#custFlag').combobox('clear');
			$('#approveResult').combobox('clear');
			$('#resultInfo').combobox('clear');
			if(node=="03"){
				$('#blockCode1').combobox('clear');
				$('#blockCode2').combobox('clear');
			}
			$('#rcdSrc').combobox('clear');
			$('#platType').combobox('clear');
			$('#coprType').combobox('clear');
			$('#platRiskLvl').combobox('clear');
			$('#policeRxbd').combobox('clear');
			$('#onlineTime').combobox('clear');
			$('#group').combobox('clear');
			$('#seachForm').form('reset');
		}
		function fnChaXun(obj){
			var id,curr,currCode,isHangUp;
			if(obj==1||obj==2||obj==5||obj==6||obj==8){//转移情况查询包括挂起申请件
				id= $("input[name='transferId']").val();
				isHangUp="1";
				if(obj==1||obj==2){
					if(obj==1){
						curr= $("#id_tree_type  option:selected").val(); 
					}else if(obj==2){
						if(ydjFlag=='1'){//易达金
							curr= $("#id_tree_type  option:selected").val(); 
						}else if(ydjFlag=='2'){//标准卡
							curr= $("#id_tree_type2  option:selected").val(); 
						}
					}
					if (curr == '' ||curr == null ||curr=='-1') {
						$.messager.alert("操作提示","转移对象为空","error");
						return;
					}else{
						if(ydjFlag=='1'){//易达金
							currCode =curr;
						}else if(ydjFlag=='2'){//标准卡
							currCode =curr.substring(0,curr.length-1);
						}
					}
				}else if(obj==5||obj==8){
					currCode = $("#id_tree_type  option:selected").val();
					if (currCode == '' ||currCode == null ||currCode=='-1') {
						$.messager.alert("操作提示","转移对象为空","error");
						return;
					}
				}
			}else if(obj==3||obj==4||obj==7||obj==9){//回收情况  不包括挂起申请件
				id=$("input[name='recoveryId']").val();
				if(obj==3||obj==4){
					curr = $("#id_recovery_type  option:selected").val();
					if (curr == null || curr == ""||curr == "-1" ) {
						$.messager.alert("操作提示","回收对象一定要选值  ","error");
						return;
					}else{
						if(ydjFlag=='1'){//易达金
							currCode =curr;
						}else if(ydjFlag=='2'){//标准卡
							currCode =curr.substring(0,curr.length-1);
						}
					}
				}else if(obj==7||obj==9){
					currCode = $("#id_recovery_type  option:selected").val();
					if (currCode == null || currCode == ""||currCode == "-1" ) {
						$.messager.alert("操作提示","回收对象一定要选值  ","error");
						return;
					} 
				}
			}
			//筛选条件
			var seachForm = $("#seachForm");
			var jsondata =getFormMulData(seachForm.serializeArray());
			//查询环节、节点、人
			jsondata.id=id;
			jsondata.currNode=node;
			jsondata.secondNode=secondNode;
			jsondata.ydjFlag=ydjFlag;
			jsondata.userCode=userCode;
			jsondata.currCode=currCode;
			jsondata.strType="3";
			if(obj=='5'){
				jsondata.currStatusStr=$("#queue_tree_type option:selected").attr('currStatusStr');
				jsondata.delStatusStr=$("#queue_tree_type option:selected").attr('delStatusStr');
			}else if(obj=='7'){
				jsondata.currStatusStr=$("#recoveryQueue option:selected").attr('currStatusStr');
				jsondata.delStatusStr=$("#recoveryQueue option:selected").attr('delStatusStr');
			}
			if(isHangUp=='1'){
				jsondata.isHangUp="1";
			}
			$cf.ajax({
				url:"/opas-allot-server/fnChaXunId.json",
				data:jsondata,
				onSuccess:function(data){
					var count = data.RSP_BODY.total;
					$('#chaXunId'+obj).text(count);
				}
			})
		}
		function getWarnMsg(){
			var warnMsg;
			var fenTime1=$("#fenTime1").datetimebox("getValue");
			var fenTime2=$("#fenTime2").datetimebox("getValue");
			var appStatus=$("#appStatus").combobox("getValues");
			if(appStatus!='2'&&(fenTime1!=''||fenTime2!='')){//分配开始时间或结束时间有其一不为空 则申请件状态不能为空
				warnMsg="申请件状态为已分配时方可查询已分配件";
			}
			return warnMsg;
		}
		//挂起备注弹出框显示
		function fnHangUpRemark(){
			var orgObj = tblPost.datagrid("getChecked");
			if (orgObj == null||orgObj.length==0) {
				  $.messager.alert("操作提示","请勾选挂起申请件！","error");
				  return;
				}
			remarkWindow2.window({
				closed : false,
				collapsible : false,
				minimizable : false,
				onBeforeOpen : setData(orgObj)
			});
		}
		function initCommon(){//初始化公共属性
			fnInitIdentify();//标识
			fnInitTime();//时间
			fnLoadAppProd();//卡产品
			fnInitYsjl();//预审结论
		}
		function initButton(){//初始化按钮功能
			fnInitShow();//分配
			fnInitTransfer();//转移
			fnInitRecovery();//回收
		}
		function initCredit(){//初始化征信风险队列
			fnCreditQueue();
			fnRefuseCode();
			fnBlockCode();
		}
		//监听窗口变化,调节分件页面div高度  wdb
		function initDivHeight(obj){
			$(window).on("resize",function(){
				var d_height;
				var area_height;
				if(screen.availHeight>950){//宽屏
					d_height=850;
					area_height=60;
				}else{//窄屏
					d_height=650;
					area_height=240;
				}
				if(obj=='divResizeH'){//列表页面
					$("#divResizeH").css("height",d_height);
				}else if(obj=='area-function2'){//分件页面
					$(".area-function2").css("margin-bottom",area_height).css("text-align","center");
				}
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