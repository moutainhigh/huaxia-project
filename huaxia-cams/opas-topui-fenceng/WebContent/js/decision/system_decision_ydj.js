/**
 * 系统决策的JS
 * by 刘志辉 2017-3-8 15:10:16
 */
	var commonRistLit;
	var commonInnerRistLit;
	var appIds;
	var custId;
	var cardCustId;
	var ishaveInriskInfo_column;
	//关联菜单
	$(function(){
		$cf.loadAllDict();//加载数据字典
		var custBaseInfoForm=$("#custBaseInfoForm");
		var haveCardcustInfoForm=$("viewForm");
		var a = new Array("0:女","1:男","M:男","F:女");
		var s = new Array("0:完全匹配","1:库中无此号","2:身份证号匹配","3:姓名不匹配");//公安状态码表
		var c = new Array("0:否","1:是");//ss
		var cc = new Array("0:否","1:是");
		var p = new Array("0:正常发卡","1:快速发卡","2:紧急发卡");//快速发卡标示
		var appId = GetQueryString("appId");
		/*	$cf.ajax({
				data : {
					"appId" : appId//'2017030814135013'
				},
				url : "/opas-naps/systemDecisionYdj_view.json",
				onSuccess : function(res) {
					var jsoncustInfo = res.RSP_BODY.jsonStr;
					var time = res.RSP_BODY.crtTime;
					var custBaseInfojson = eval('(' +jsoncustInfo+ ')');
					var fqzRst = res.RSP_BODY.dataMap;

					commonRistLit = custBaseInfojson;
					custBaseInfoForm.form("load", custBaseInfojson);
					
					custId = custBaseInfojson.isOneselfCust;
					cardCustId = custBaseInfojson.isHavecardCust;
					ishaveInriskInfo_column = custBaseInfojson.ishaveInriskInfo_column;
					var isHavecardStandard = custBaseInfojson.appLable;
					if(isHavecardStandard!=null&&isHavecardStandard!=''){
						if(isHavecardStandard.indexOf('H07')<0){
							isHavecardStandard = null;
						}
					}
					//年龄高亮小于18大于65
					if(custBaseInfojson.age< 18 || custBaseInfojson.age > 65){
						$("#age").attr("style","background-color:pink;color:red");
					}
					//判断存量客户电销名单是否需要高亮
					if(custBaseInfojson.stockcustTelsale!=null && custBaseInfojson.stockcustTelsale > 0){
						$("#stockcustTelsale").attr("style","background-color:pink;color:red");
					}
					$("#crtTime").textbox('setValue',time);//征信策略结果
					$("#applyCard").val(custBaseInfojson.prodName);//卡种
					//决策结果
					//客户基本信息   客户标签   政策入组规则提示
					$("#sex").val(valueChangeOther(custBaseInfojson.sex,a));//性别
					$("#policeStatus").val(valueChangeOther(custBaseInfojson.policeStatus,s));//链接公安状态	
					$("#isOneselfCust").val(valueChange(custBaseInfojson.isOneselfCust,cc,"#isOneselfCust"));//是否我行客户	
					$("#isHavecardCust").val(valueChange(custBaseInfojson.isHavecardCust,cc,"#isHavecardCust"));//是否已持卡客户	
					$("#isPrefiltercustStandardValue").val(valueChange(custBaseInfojson.isPrefiltercustStandard,cc,"#isPrefiltercustStandardValue"));//是否符合预筛选客户入组标准	
					$("#isHavecardStandardValue").val(valueChange(isHavecardStandard,c,'#isHavecardStandardValue'));//是否符合我行客户入组标准
					$("#C4_rushflg").val(valueChangeOther(custBaseInfojson.c4_rushflg,p));//快速发卡标示	
					//=========
					
					
					if(custBaseInfojson.c4Apbatch==null || custBaseInfojson.c4Apbatch==""){
						$("#teamnoIsmatch").val("空");
					}else{
						$("#teamnoIsmatch").val(valueChangeCus(custBaseInfojson.teamnoIsmatch,cc,"#teamnoIsmatch"));//团办号是否匹配
					}
					//$("#teamnoIsmatch").val(valueChangeCus(custBaseInfojson.teamnoIsmatch,cc,"#teamnoIsmatch"));//团办号是否匹配
					$("#companyMindan").val(valueChangeCus(custBaseInfojson.companyMindan,cc,"#companyMindan"));//团办号是否匹配
					$("#importSchoolMindan").val(valueChangeCus(custBaseInfojson.importSchoolMindan,cc,"#importSchoolMindan"));//团办号是否匹配
					$("#goodCompanyMindan").val(valueChangeCus(custBaseInfojson.goodCompanyMindan,cc,"#goodCompanyMindan"));//团办号是否匹配
					$("#businessTuanbanMindan").val(valueChangeCus(custBaseInfojson.businessTuanbanMindan,cc,"#businessTuanbanMindan"));//团办号是否匹配
					$("#tsProjectMindan").val(valueChangeCus(custBaseInfojson.tsProjectMindan,cc,"#tsProjectMindan"));//团办号是否匹配
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
					风险信息检查
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
					//$("#certifinoVailud").val(valueChange(custBaseInfojson.certifinoVailud,cc,"#certifinoVailud"));
					$("#relatedcheckTel").val(valueChange(custBaseInfojson.relatedcheckTel,cc,"#relatedcheckTel"));
					$("#relatedcheckHomeaddr").val(valueChange(custBaseInfojson.relatedcheckHomeaddr,cc,"#relatedcheckHomeaddr"));
					$("#relatedcheckCompanyaddr").val(valueChange(custBaseInfojson.relatedcheckCompanyaddr,cc,"#relatedcheckCompanyaddr"));
					$("#relatedcheckNearlyTelno").val(valueChange(custBaseInfojson.relatedcheckNearlyTelno,cc,"#relatedcheckNearlyTelno"));
					$("#nosafePromoter").val(valueChange(custBaseInfojson.nosafePromoter,cc,"#nosafePromoter"));
					//$("#riskchannelCheck").val(valueChange(custBaseInfojson.riskchannelCheck,cc,"#riskchannelCheck"));
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
			});*/
			
			
			//客户基本信息、风险信息、反欺诈信息查询	
			$cf.ajax({
				data : {
					"appId" : appId//'2017030814135013'
				},
				url : "/opas-naps/queryCustInfoMesByAppID.json",
				onSuccess : function(res) {
					var jsoncustInfo = res.RSP_BODY.jsonStr;
					var custBaseInfojson = eval('(' +jsoncustInfo+ ')');
					var fqzRst = res.RSP_BODY.dataMap;
	
					commonRistLit = custBaseInfojson;
					//custBaseInfoForm.form("load", custBaseInfojson);
					
					custId = custBaseInfojson.isOneselfCust;
					cardCustId = custBaseInfojson.isHavecardCust;
					ishaveInriskInfo_column = custBaseInfojson.ishaveInriskInfo_column;
					var isHavecardStandard = custBaseInfojson.appLable;
					if(isHavecardStandard!=null&&isHavecardStandard!=''){
						if(isHavecardStandard.indexOf('H07')<0){
							isHavecardStandard = null;
						}
					}
					//赋值
					$("#appId").val(custBaseInfojson.appId);//流水号
					$("#custName").val(custBaseInfojson.custName);//客户姓名
					$("#sex").combobox('setValue',valueChangeOther(custBaseInfojson.sex,a));//性别
					if(custBaseInfojson.nationaity==null||custBaseInfojson.nationaity==""){
						$("#nationaity").combobox('setValue','CHN');
					}else{
						$("#nationaity").combobox('setValue',custBaseInfojson.nationaity);
					}//国籍
					//年龄高亮小于18大于65
					$("#age").val(custBaseInfojson.age);
					if(custBaseInfojson.age< 18 || custBaseInfojson.age > 65){
						$("#age").attr("style","background-color:pink;color:red");
					}
					$("#certifiType").combobox('setValue',custBaseInfojson.certifiType);//证件类型
					$("#certifiNo").val(custBaseInfojson.certifiNo);//证件号码
					$("#policeStatus").val(valueChangeOther(custBaseInfojson.policeStatus,s));//链接公安状态
					$("#workCompany").textbox('setValue',custBaseInfojson.workCompany);//工作单位
					$("#officeTel").val(custBaseInfojson.officeTel);//办公电话
					$("#mobileNo").val(custBaseInfojson.mobileNo);//手机电话
					$("#applyCard").val(custBaseInfojson.prodName);//卡种
					$("#C4_rushflg").val(valueChangeOther(custBaseInfojson.c4_rushflg,p));//快速发卡标示
					
					//判断存量客户电销名单是否需要高亮
					$("#stockcustTelsale").val(custBaseInfojson.stockcustTelsale);
					if(custBaseInfojson.stockcustTelsale!=null && custBaseInfojson.stockcustTelsale > 0){
						$("#stockcustTelsale").attr("style","background-color:pink;color:red");
					}
					
					//名单
					if(custBaseInfojson.c4Apbatch==null || custBaseInfojson.c4Apbatch==""){
						$("#teamnoIsmatch").val("空");
					}else{
						$("#teamnoIsmatch").val(valueChangeCus(custBaseInfojson.teamnoIsmatch,cc,"#teamnoIsmatch"));//团办号是否匹配
					}
					//$("#teamnoIsmatch").val(valueChangeCus(custBaseInfojson.teamnoIsmatch,cc,"#teamnoIsmatch"));//团办号是否匹配
					$("#companyMindan").val(valueChangeCus(custBaseInfojson.companyMindan,cc,"#companyMindan"));//目标企业名单是否匹配
					$("#importSchoolMindan").val(valueChangeCus(custBaseInfojson.importSchoolMindan,cc,"#importSchoolMindan"));//重点院校名单是否匹配
					$("#goodCompanyMindan").val(valueChangeCus(custBaseInfojson.goodCompanyMindan,cc,"#goodCompanyMindan"));//优质企业名单是否匹配
					$("#businessTuanbanMindan").val(valueChangeCus(custBaseInfojson.businessTuanbanMindan,cc,"#businessTuanbanMindan"));//商户团办名单是否匹配
					$("#tsProjectMindan").val(valueChangeCus(custBaseInfojson.tsProjectMindan,cc,"#tsProjectMindan"));//特殊项目名单是否匹配
					//客户标签
					$("#isOneselfCust").val(valueChange(custBaseInfojson.isOneselfCust,cc,"#isOneselfCust"));//是否我行客户	
					$("#isHavecardCust").val(valueChange(custBaseInfojson.isHavecardCust,cc,"#isHavecardCust"));//是否已持卡客户	
					$("#isPrefiltercustStandardValue").val(valueChange(custBaseInfojson.isPrefiltercustStandard,cc,"#isPrefiltercustStandardValue"));//是否符合预筛选客户入组标准	
					$("#isHavecardStandardValue").val(valueChange(isHavecardStandard,c,'#isHavecardStandardValue'));//是否符合我行客户入组标准
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
					//风险信息检查
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
			
			//决策结果信息查询
			$cf.ajax({
				data : {
					"appId" : appId//'2017030814135013'
				},
				url : "/opas-naps/querySysResultInfoByAppID.json",
				onSuccess : function(res) {
					var jsoncustInfo = res.RSP_BODY.jsonsysInfo;
					var time = res.RSP_BODY.crtTime;
					var custBaseInfojson = eval('(' +jsoncustInfo+ ')');
					//征信策略
					$("#creditDecisionResult").textbox('setValue',custBaseInfojson.creditDecisionResult);//征信策略结果
					$("#creditDecisionDesc").textbox('setValue',custBaseInfojson.creditDecisionDesc);//征信策略结果描述
					$("#crtTime").textbox('setValue',time);//征信策略结果时间
					//审核决策
					$("#decisionResult").val(valueChangeFQZ(custBaseInfojson.decisionResult,cc,"#decisionResult"));//审核决策结果
					$("#decisionDesp").textbox('setValue',custBaseInfojson.decisionDesp);//审核决策结果描述
					$("#proposedLmt").textbox('setValue',custBaseInfojson.proposedLmt);//审核建议额度
					$("#tacType").textbox('setValue',custBaseInfojson.tacType);//策略类型
					$("#cdtScore").textbox('setValue',custBaseInfojson.cdtScore);//征信评分
					$("#numberRead").textbox('setValue',custBaseInfojson.numberRead);//中征信"信用1000"评分
					$("#appScroe").textbox('setValue',custBaseInfojson.appScroe);//申请人评分
					
					//参考数据
					$("#violRefLmt").textbox('setValue',custBaseInfojson.violRefLmt);//违例参考额度
					$("#unPlgeDti").textbox('setValue',custBaseInfojson.unPlgeDti);//无抵押DTI
					$("#violLmtUp").textbox('setValue',custBaseInfojson.violLmtUp);//违例额度上限
					$("#cpDti").textbox('setValue',custBaseInfojson.cpDti);//综合DTI
					$("#sampMIncome").textbox('setValue',custBaseInfojson.sampMIncome);//税前月收入
					$("#mue").textbox('setValue',custBaseInfojson.mue);//风险敞口MUE
					$("#matchIncome").textbox('setValue',custBaseInfojson.matchIncome);//行职业收入
					//风险定价
					$("#custLevelTagRate").textbox('setValue',custBaseInfojson.custLevelTagA);//利率
					$("#rateName").textbox('setValue',custBaseInfojson.rateName);//
					$("#custLevelTagLimit").textbox('setValue',custBaseInfojson.custLevelTagB);//最低还款额
					$("#repayRationValue").textbox('setValue',custBaseInfojson.repayRationValue);//
					$("#custLevelTagTerm").textbox('setValue',custBaseInfojson.custLevelTagC);//免息还款期
					$("#repayFreeValue").textbox('setValue',custBaseInfojson.repayFreeValue);//
				}
			});
			
			//内部数据信息查询	
			$cf.ajax({
				data : {
					"appId" : appId//'2017030814135013'
				},
				url : "/opas-naps/queryInnerDataCheckByAppID.json",
				onSuccess : function(res) {
					var jsoncustInfo = res.RSP_BODY.jsoninnerDataCheck;
					var custBaseInfojson = eval('(' +jsoncustInfo+ ')');
					commonInnerRistLit = custBaseInfojson;
					//内部数据检查
					$("#repeatCheck").val(valueChange(custBaseInfojson.repeatCheck,cc,"#repeatCheck"));//重复申请检查
					$("#graylist").val(valueChange(custBaseInfojson.graylist,cc,"#graylist"));//灰名单检查
					//$("#certifinoVailud").val(valueChange(custBaseInfojson.certifinoVailud,cc,"#certifinoVailud"));//
					$("#relatedcheckTel").val(valueChange(custBaseInfojson.relatedcheckTel,cc,"#relatedcheckTel"));//关联性检查-手机
					$("#relatedcheckHomeaddr").val(valueChange(custBaseInfojson.relatedcheckHomeaddr,cc,"#relatedcheckHomeaddr"));//关联性检查-家庭地址
					$("#relatedcheckCompanyaddr").val(valueChange(custBaseInfojson.relatedcheckCompanyaddr,cc,"#relatedcheckCompanyaddr"));//关联性检查-非公司的邮寄地址
					$("#relatedcheckNearlyTelno").val(valueChange(custBaseInfojson.relatedcheckNearlyTelno,cc,"#relatedcheckNearlyTelno"));//关联性检查-直系亲属手机号码
					$("#nosafePromoter").val(valueChange(custBaseInfojson.nosafePromoter,cc,"#nosafePromoter"));//不安全推广
					//$("#riskchannelCheck").val(valueChange(custBaseInfojson.riskchannelCheck,cc,"#riskchannelCheck"));//
					$("#applyinfoBuscheck").val(valueChange(custBaseInfojson.applyinfoBuscheck,cc,"#applyinfoBuscheck"));//申请信息逻辑检查
					$("#ishaveInriskInfo").val(valueChange(custBaseInfojson.ishaveInriskInfo,cc,"#ishaveInriskInfo"));//是否命中内部风险信息
					otherInit(custBaseInfojson.certifinoVailud);//证件号码有效期
					
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
	/*****************小数点处理********************/
	function valChangell(obj) {
		alert(obj.toFixed(2));
		return obj.toFixed(2);
	}
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
				$("#applyCard").textbox('setValue',str);//卡种
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
			//$("#certifinoVailud").attr("style","background-color:pink;color:red");
		}else{
			$("#certifinoVailud").val("空");
			$("#certifinoVailud").attr("style","background-color:pink;color:red");
		}
	}
	//转码
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
	//转码，高亮基本信息使用
	function valueChangeCus(oldVal,arr,gl){
		var len = arr.length;
		var obj = '';
		if(oldVal!==null&&oldVal!==undefined&&oldVal!==""){
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
			$(gl).attr("style","background-color:pink;color:red;width: 110px;");
		}
		return obj;
	}
	//转码，高亮
	function valueChange(oldVal,arr,gl){
		var len = arr.length;
		var obj = '';
		if(oldVal!==null&&oldVal!==undefined&&oldVal!==""){
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
			$(gl).attr("style","color:red;width:90px");
		}  else if(oldVal=='直接拒绝'){
			$(gl).attr("style","color:red;");
		} else if(oldVal=='建议拒绝'){
			$(gl).attr("style","color:red;");
		}
		return oldVal;
	}
	//是否显示CRM
	function openCRMCustWin(obj){
		if($("#isOneselfCust").val()=='否'||$("#isOneselfCust").val()==null||$("#isOneselfCust").val()==''){
			return;
		}else{
			window.open('crm_info_view.html?appId='+appId+'&flag=1',"newWindow",'height=1200px,width=1000px');
		}
	}
	//关闭窗体
	function onConcle(obj) {
		var winObj = $("#" + obj);
		winObj.window("close");
	}
	
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