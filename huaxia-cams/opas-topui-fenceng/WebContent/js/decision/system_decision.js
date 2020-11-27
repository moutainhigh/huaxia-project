/**
 * 系统决策的JS
 * by 刘志辉 2017-3-8 15:10:16
 */
	//关联菜单
	var commonRistLit;
	var custId;
	var cardCustId;
	var ishaveInriskInfo_column;
	$(function(){
		$cf.loadAllDict();//加载数据字典
		var custBaseInfoForm=$("#custBaseInfoForm");
		var sysDecisionForm=$("#sysDecisionForm");
		var haveCardcustInfoForm=$("#viewForm");
		var a = new Array("0:女","1:男","M:男","F:女");
		var s = new Array("0:完全匹配","1:库中无此号","2:身份证号匹配","3:姓名不匹配");//公安状态码表
		var c = new Array("0:否","1:是");//
		var cc = new Array("0:否","1:是");//
		var t = new Array("0:不接受","1:可以接受");//是否接受降级
		var p = new Array("0:正常发卡","1:快速发卡","2:紧急发卡");//快速发卡标示
		var rule = new Array();//规则
		$cf.ajax({
			data : {
				"appId" : appId
			},
			url : "/opas-naps/systemDecision_view.json",
			onSuccess : function(res) {
				var jsoncustInfo = res.RSP_BODY.jsonStr;
				var ctrStr = res.RSP_BODY.ctrStr;
				var custBaseInfojson = eval('(' +jsoncustInfo+ ')');
				var fqzRst = res.RSP_BODY.dataMap;
				
				commonRistLit = custBaseInfojson;
				custBaseInfoForm.form("load", custBaseInfojson);
				
				custId = custBaseInfojson.isOneselfCust;
				cardCustId = custBaseInfojson.isHavecardCust;
				ishaveInriskInfo_column = custBaseInfojson.ishaveInriskInfo_column;
				var isHavecardStandard = custBaseInfojson.appLable;
				var rzLableCode = custBaseInfojson.rzLableCode;
				var rzLable = custBaseInfojson.rzLable;
				$("#qc").val("否");
				$("#jd").val("否");
				$("#hh").val("否");
				if(rzLableCode==undefined||rzLableCode==null||rzLableCode==""){
					if(rzLable==undefined||rzLable==null||rzLable==""){
						$("#isRzLableCode").val("否");
					}else{
						$("#isRzLableCode").val("是");
						$("#isRzLableCode").attr("style","background-color:pink;color:red");
					}					
				}else{
					$("#isRzLableCode").val("是");
					$("#isRzLableCode").attr("style","background-color:pink;color:red");
					if(rzLableCode.indexOf("4-QC")>-1){
						$("#qc").val(valueChange(rzLableCode,cc,"#qc"));
					}
					if(rzLableCode.indexOf("7-JD")>-1){
						$("#jd").val(valueChange(rzLableCode,cc,"#jd"));
					}
					if(rzLableCode.indexOf("7-HH")>-1){
						$("#hh").val(valueChange(rzLableCode,cc,"#hh"));
					}
				}
				//年龄高亮小于18大于65
				if(custBaseInfojson.age< 18 ||custBaseInfojson.age > 65){
					$("#age").attr("style","background-color:pink;color:red");
				}
				if(rzLable==null||rzLable==""){
					rzLable = "否";
				}
				$("#rzLable").val(rzLable);//是否符合年轻高学历入组标准	
				//$("#rzLable").val(valueChange(rzLable,cc,"#rzLable"));
				$("#crtTime").textbox('setValue',ctrStr);//系统决策时间
				$("#creditDecisionResult").textbox('setValue',valChange(custBaseInfojson.creditDecisionResult));//征信策略结果
				$("#applyCardValue").val(custBaseInfojson.prodName);//卡种
				/*客户基本信息   客户标签   政策入组规则提示*/
				$("#sex").val(valueChangeOther(custBaseInfojson.sex,a));//性别
				$("#isOneselfCustValue").val(valueChange(custBaseInfojson.isOneselfCust,cc,"#isOneselfCustValue"));//是否我行客户	
				$("#isHavecardCustValue").val(valueChange(custBaseInfojson.isHavecardCust,cc,"#isHavecardCustValue"));//是否已持卡客户	
				$("#isPrefiltercustStandardValue").val(valueChange(custBaseInfojson.isPrefiltercustStandard,c,"#isPrefiltercustStandardValue"));//是否符合预筛选客户入组标准	
				
				$("#isHavecardStandardValue").val(valueChange(isHavecardStandard,c,'#isHavecardStandardValue'));//是否符合我行客户入组标准
				
				$("#C4_gtoc").val(valueChangeOther(custBaseInfojson.c4_gtoc,cc));//是否接受降级	
				$("#C4_rushflgValue").val(valueChangeOther(custBaseInfojson.c4_rushflg,p));//快速发卡标示	
				if(custBaseInfojson.c4Apbatch==null || custBaseInfojson.c4Apbatch==""){
					$("#teamnoIsmatchValue").val("空");
				}else{
					$("#teamnoIsmatchValue").val(valueChange(custBaseInfojson.teamnoIsmatch,cc,"#teamnoIsmatchValue"));//团办号是否匹配
				}
				//客户风险等级
				var custwashmoneyRisklevel = custBaseInfojson.custwashmoneyRisklevel;
				if(custwashmoneyRisklevel=="0"){
					$("#custwashmoneyRisklevel").val("低");
				}else if(custwashmoneyRisklevel=="1"){
					$("#custwashmoneyRisklevel").val("较低");
				}else if(custwashmoneyRisklevel=="2"){
					$("#custwashmoneyRisklevel").val("中");
				}else if(custwashmoneyRisklevel=="3"){
					$("#custwashmoneyRisklevel").val("较高");
					$("#custwashmoneyRisklevel").attr("style","background-color:pink;color:red");
				}else if(custwashmoneyRisklevel=="4"){
					$("#custwashmoneyRisklevel").val("高");
					$("#custwashmoneyRisklevel").attr("style","background-color:pink;color:red");
				}else{
					$("#custwashmoneyRisklevel").val("否");
				} 
				/*风险信息检查*/
				$("#identityRisklist").val(valueChange(custBaseInfojson.identityRisklist,cc,"#identityRisklist"));//身份类风险名单库
				$("#companyRisklist").val(valueChange(custBaseInfojson.companyRisklist,cc,"#companyRisklist"));//单位类风险名单库
				$("#telRisklist").val(valueChange(custBaseInfojson.telRisklist,cc,"#telRisklist"));//推广员风险名单库
				$("#addrRisklist").val(valueChange(custBaseInfojson.addrRisklist,cc,"#addrRisklist"));//推广员风险名单库
				$("#promoterRisklist").val(valueChange(custBaseInfojson.promoterRisklist,cc,"#promoterRisklist"));//推广员风险名单库
				$("#unionRisklist").val(valueChange(custBaseInfojson.unionRisklist,cc,"#unionRisklist"));//银联风险名单
				$("#sameRisklist").val(valueChange(custBaseInfojson.sameRisklist,cc,"#sameRisklist"));//同业风险名单
				$("#policeBadinfo").val(valueChange(custBaseInfojson.policeBadinfo,cc,"#policeBadinfo"));//公安负面信息
				$("#bairongRisklist").val(valueChange(custBaseInfojson.bairongRisklist,cc,"#bairongRisklist"));//百融风险名单
				$("#antRisklist").val(valueChange(custBaseInfojson.antRisklist,cc,"#antRisklist"));//蚂蚁风险名单
				$("#washmoneyRisklist").val(valueChange(custBaseInfojson.washmoneyRisklist,cc,"#washmoneyRisklist"));//洗钱风险名单
				//$("#custwashmoneyRisklevel").val(valueChange(custBaseInfojson.custwashmoneyRisklevel,cc,"#custwashmoneyRisklevel"));//客户洗钱风险等级
				$("#phoneCertification").val("未查");//手机实名认证结果
				//内部数据检查
				$("#repeatCheck").val(valueChange(custBaseInfojson.repeatCheck,cc,"#repeatCheck"));
				$("#graylist").val(valueChange(custBaseInfojson.graylist,cc,"#graylist"));
				$("#relatedcheckTel").val(valueChange(custBaseInfojson.relatedcheckTel,cc,"#relatedcheckTel"));
				$("#relatedcheckHomeaddr").val(valueChange(custBaseInfojson.relatedcheckHomeaddr,cc,"#relatedcheckHomeaddr"));
				$("#relatedcheckCompanyaddr").val(valueChange(custBaseInfojson.relatedcheckCompanyaddr,cc,"#relatedcheckCompanyaddr"));
				$("#relatedcheckNearlyTelno").val(valueChange(custBaseInfojson.relatedcheckNearlyTelno,cc,"#relatedcheckNearlyTelno"));
				$("#nosafePromoter").val(valueChange(custBaseInfojson.nosafePromoter,cc,"#nosafePromoter"));
				$("#applyinfoBuscheck").val(valueChange(custBaseInfojson.applyinfoBuscheck,cc,"#applyinfoBuscheck"));
				$("#ishaveInriskInfo").val(valueChange(custBaseInfojson.ishaveInriskInfo,cc,"#ishaveInriskInfo"));
				otherInit(custBaseInfojson.certifinoVailud);
				if(custBaseInfojson.nationaity==null||custBaseInfojson.nationaity==""){
					$("#nationaity").combobox('setValue','CHN');
				}
				$("#decisionResult").val(valueChangeFQZ(custBaseInfojson.decisionResult,cc,"#decisionResult"));
				
				//反欺诈
				var fqz = fqzRst.fqzRstDesp
				$("#fqzRstTime").textbox('setValue',fqz[0].crt_date);//反欺詐时间
				$("#fqzValue").textbox('setValue',fqz[0].fqzValue);//反欺詐决策结果
				$("#fqzRequesttype").val(fqz[0].fqzRequesttype);//反欺詐结果描述
				$("#fqzRisknoZ").val(valueChangeFQZ(fqz[0].fqzRiskno,cc,"#fqzRisknoZ"));//反欺詐结果描述
				$("#fqzRisknoW").val(valueChangeFQZ(fqz[1].fqzRiskno,cc,"#fqzRisknoW"));//反欺詐结果描述
				$("#fqzRisknoH").val(valueChangeFQZ(fqz[2].fqzRiskno,cc,"#fqzRisknoH"));//反欺詐结果描述
				
			}
		});
		$('#datalist').datagrid({    
		    url:'/opas-naps/queryCheckinerResultInfo.json',
	    	queryParams: {
	    		appId: appId
	    	}
		});
		
		$('#tb_IsNotFqzRstDespWin').datagrid({    
		    url:'',
		    border:false,
			fit:true,
			fitColumns:true,
			rownumbers:true,
			columns:[[{
				title:'校验规则 ',
				field:'fqzRishCategory',			 
				halign:'center',
				formatter:function(value,row,index){
					if (row.fqzRishCategory1=='Z01_6'){
						return "<span style='color:red'>"+value+"</span>";
					} else if(row.fqzRishCategory1=='Z01_3'){
						return "<span style='color:red'>"+value+"</span>";
					} else if(row.fqzRishCategory1=='Z01_4'){
						return "<span style='color:red'>"+value+"</span>";
					} else if(row.fqzRishCategory1=='Z01_5'){
						return "<span style='color:red'>"+value+"</span>";
					} else if(row.fqzRishCategory1=='Z06_1'){
						if (row.fqzRiskdesc1=='SNA_D'){
							return "<span style='color:red'>"+value+"</span>";
						} else {
							return value
						}
					} else {
						return value
					}
				},
				width:'37%',
				editor:{
					type:'validatebox',
					options:{
						required:true
					}
				}
			},{
				title:'详情描述',
				field:'fqzRiskdesc',
				halign:'center',
				width:'60%',
				editor:{
					type:'validatebox',
					options:{
						required:true
					}
				}
			}]]
		});
	});
	function valCardName(str){
		if(str==null||str =="")	
			return; 
		$cf.ajax({
			data : {
				"productCode" : str//'1601240945P00101'
			},
			url : "/opas-naps/systemDecision_cardName.json",
			onSuccess : function(res) {
				var	card = res.RSP_BODY.cardProduct;
				str = card.productName;
				$("#applyCardValue").val(str);//卡种
			}
		});
		
	}
	function otherInit(res){
		if(res=='1'){
			$("#certifinoVailud").val("关注");
			$("#certifinoVailud").attr("style","background-color:pink;color:red");
		}else if(res=='2'){
			$("#certifinoVailud").val("已过期");
			$("#certifinoVailud").attr("style","background-color:pink;color:red");
		}else if(res=='3'){
			$("#certifinoVailud").val("未过期");
		}else if(res=='4'){
			$("#certifinoVailud").val("长期");
		}else{
			$("#certifinoVailud").val("空");
			$("#certifinoVailud").attr("style","background-color:pink;color:red");
		}
	}
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
	function valChange(str){
		if(str=='L'){
			return "低风险人工征信L";
		}else if(str=='L1'){
			return "低风险征信L1";
		}else if(str=='L2'){
			return "低风险征信L2";
		}else if(str=='L3-1'){
			return "W低风险征信L3.1";
		}else if(str=='L3-2'){
			return "W低风险免点核（人工排查）L3.2";
		}else if(str=='LV'){
			return "低风险人工征信LV";
		}else if(str=='M'){
			return "中风险人工征信M";
		}else if(str=='H'){
			return "高风险人工征信H";
		}else if(str=='H1'){
			return "高风险征信H1";
		}else if(str=='S100'){
			return "系统征信自动通过S";
		}
	}
	//转码，高亮
	function valueChange(oldVal,arr,gl){
		var len = arr.length;
		var obj = '';
		if(oldVal!=null&&oldVal!=undefined&&oldVal!=""){
			for(var i=0;i<len;i++){
				var temp=arr[i].split(":");
				if(oldVal=='0'){
					return temp[1];
				}else if(oldVal!='0'&&i==1){
					obj = temp[1];
				}
			}
		}else{
			return "否";
		}
		if(oldVal!=null&&oldVal!='0'){
			$(gl).attr("style","background-color:pink;color:red");
		}
		return obj;
	}
	
	//反欺诈结果描述 不满足的时候变红色
	function valueChangeFQZ(oldVal,arr,gl){
		if(oldVal=='不满足'){
			$(gl).attr("style","color:red;width:90px;");
		} else if(oldVal=='直接拒绝'){
			$(gl).attr("style","color:red;");
		} else if(oldVal=='建议拒绝'){
			$(gl).attr("style","color:red;");
		} 
		return oldVal;
	}
	
	 function openWin(winName) {
		 $cf.ajax({
				data : {
					"appId" : appId//'1601240945P00101'
				},
				url : "/opas-naps/systemDecision_havaCardCustInfo_view.json",
				onSuccess : function(res) {
					var jsonHaveCardCustInfo = res.RSP_BODY.haveCardInfoJsonStr;
					var haveCardCustInfojson = eval('(' +jsonHaveCardCustInfo+ ')');
					$("#applyerTel").val(haveCardCustInfojson.applyerTel);
					$("#acctBzkStatus").val(haveCardCustInfojson.acctBzkStatus);
					$("#companyTel").val(haveCardCustInfojson.companyTel);
					$("#acctYdjStatus").val(haveCardCustInfojson.acctYdjStatus);
					$("#companyAddr").val(haveCardCustInfojson.companyAddr);
					$("#havecardType").val(haveCardCustInfojson.havecardType);
					$("#houseTel").val(haveCardCustInfojson.houseTel);
					$("#cardLimit").val(haveCardCustInfojson.cardLimit);
					$("#nearlyContactTel").val(haveCardCustInfojson.nearlyContactTel);
					$("#cardStatus").val(haveCardCustInfojson.cardStatus);
					$("#nearlyContactName").val(haveCardCustInfojson.nearlyContactName);
					$("#cycdueLimit").val(haveCardCustInfojson.cycdueLimit);
					$("#billAddr").val(haveCardCustInfojson.billAddr);
					$("#cycdueTimes").val(haveCardCustInfojson.cycdueTimes);
					$("#addressBillType").val(haveCardCustInfojson.addressBillType);
				}
			});
			winObj = $("#" + winName + "");
			winObj.window({
				closed : false
			});
		}
	//关闭窗体
	function onConcle(obj) {
		var winObj = $("#" + obj);
		winObj.window("close");
	}
	
	/********** 京东小白卡 *************/
	function openIsMathJDCard(){
		if($("#applyCardValue").val()=='华夏京东联名信用卡'){
			window.open('jd_card_info.html?appId='+appId,"newWindow",'height=880px,width=1000px');
			}
		else{
			return;
		}
			
	}
	//是否已持卡客户
	function openIsHavecardCustWin(obj){
		if($("#isHavecardCustValue").val()=='否'||$("#isHavecardCustValue").val()==""){
			return;
		}else{
			window.open('system_decision_havecard_info.html?appId='+appId+'&flag=2',"newWindow",'height=1200px,width=600px');
		}
	}
	//是否显示CRM
	function openCRMCustWin(obj){
		if($("#isOneselfCustValue").val()=='否'||$("#isOneselfCustValue").val()==""){
			return;
		}else{
			window.open('crm_info_view.html?appId='+appId,"newWindow",'height=1000px,width=1000px');
		}
	}
	function openCommonWin(obj,flag,appId){
		var jsonUrl = 'querySystemDecision_tab_view';
		var tbCommonWin = "tb_"+obj;
		/*$cf.ajax({
			url : "/opas-naps/"+jsonUrl+".json",
			data : {
				"appId" : appId,
				"flag":flag
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
		}); */
		var opts = $("#"+tbCommonWin).datagrid("options");
		var s = {"appId":appId,"flag":flag};
		opts.url = "/opas-naps/"+jsonUrl+".json";
		$("#"+tbCommonWin).datagrid("reload", s);
		showWin(obj);
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
		return y+"-"+m+"-"+d;  
	};
	
	//检查情况
	function checkConditionFmt(val, row, index) {
		if (val == 1) {
			return "已查-系统";
		} else if (val == 2) {
			return "已查-专项";
		} else if (val == 3) {
			return "已查-自查";
		} else if (val == 4) {
			return "已查-核销";
		} else {
			return val;
		}
	}
	//检查结果
	function ctfStatusFmt(val, row, index) {
		if (val == 0) {
			return "正常";
		} else if (val == 1) {
			return "问题-申请资料类";
		} else if (val == 2) {
			return "问题-流程类";
		} else if (val == 3) {
			return "问题-核实话术类";
		} else if (val == 4) {
			return "问题-注记类";
		} else if (val == 5) {
			return "问题-信息采集类";
		} else if (val == 6) {
			return "问题-审批评估类";
		} else if (val == 7) {
			return "问题-工单投诉类";
		} else if (val == 8) {
			return "问题-其他类";
		} else {
			return val;
		}
	}