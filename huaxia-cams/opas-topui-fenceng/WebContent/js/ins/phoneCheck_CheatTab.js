//拼接当前件规则
function getTriggeredRule(yioApp){
	var allRule=(yioApp.ruleTriggered1==null?"":yioApp.ruleTriggered1+",")+
	(yioApp.ruleTriggered2==null?"":yioApp.ruleTriggered2+",")+
	(yioApp.ruleTriggered3==null?"":yioApp.ruleTriggered3+",")+
	(yioApp.ruleTriggered4==null?"":yioApp.ruleTriggered4+",")+
	(yioApp.ruleTriggered5==null?"":yioApp.ruleTriggered5+",")+
	(yioApp.ruleTriggered6==null?"":yioApp.ruleTriggered6+",")+
	(yioApp.ruleTriggered7==null?"":yioApp.ruleTriggered7+",")+
	(yioApp.ruleTriggered8==null?"":yioApp.ruleTriggered8+",")+
	(yioApp.ruleTriggered9==null?"":yioApp.ruleTriggered9+",")+
	(yioApp.ruleTriggered10==null?"":yioApp.ruleTriggered10+",")+
	(yioApp.ruleTriggered11==null?"":yioApp.ruleTriggered11+",")+
	(yioApp.ruleTriggered12==null?"":yioApp.ruleTriggered12+",")+
	(yioApp.ruleTriggered13==null?"":yioApp.ruleTriggered13+",")+
	(yioApp.ruleTriggered14==null?"":yioApp.ruleTriggered14+",")+
	(yioApp.ruleTriggered15==null?"":yioApp.ruleTriggered15+",")+
	(yioApp.ruleTriggered16==null?"":yioApp.ruleTriggered16+",")+
	(yioApp.ruleTriggered17==null?"":yioApp.ruleTriggered17+",")+
	(yioApp.ruleTriggered18==null?"":yioApp.ruleTriggered18+",")+
	(yioApp.ruleTriggered19==null?"":yioApp.ruleTriggered19+",")+
	(yioApp.ruleTriggered20==null?"":yioApp.ruleTriggered20+",")+
	(yioApp.ruleTriggered21==null?"":yioApp.ruleTriggered21+",")+
	(yioApp.ruleTriggered22==null?"":yioApp.ruleTriggered22+",")+
	(yioApp.ruleTriggered23==null?"":yioApp.ruleTriggered23+",")+
	(yioApp.ruleTriggered24==null?"":yioApp.ruleTriggered24+",")+
	(yioApp.ruleTriggered25==null?"":yioApp.ruleTriggered25+",")+
	(yioApp.ruleTriggered26==null?"":yioApp.ruleTriggered26+",")+
	(yioApp.ruleTriggered27==null?"":yioApp.ruleTriggered27+",")+
	(yioApp.ruleTriggered28==null?"":yioApp.ruleTriggered28+",")+
	(yioApp.ruleTriggered29==null?"":yioApp.ruleTriggered29+",")+
	(yioApp.ruleTriggered30==null?"":yioApp.ruleTriggered30+",")+
	(yioApp.ruleTriggered31==null?"":yioApp.ruleTriggered31+",")+
	(yioApp.ruleTriggered32==null?"":yioApp.ruleTriggered32+",")+
	(yioApp.ruleTriggered33==null?"":yioApp.ruleTriggered33+",")+
	(yioApp.ruleTriggered34==null?"":yioApp.ruleTriggered34+",")+
	(yioApp.ruleTriggered35==null?"":yioApp.ruleTriggered35+",")+
	(yioApp.ruleTriggered36==null?"":yioApp.ruleTriggered36+",")+
	(yioApp.ruleTriggered37==null?"":yioApp.ruleTriggered37+",")+
	(yioApp.ruleTriggered38==null?"":yioApp.ruleTriggered38+",")+
	(yioApp.ruleTriggered39==null?"":yioApp.ruleTriggered39+",")+
	(yioApp.ruleTriggered40==null?"":yioApp.ruleTriggered40+",")+
	(yioApp.ruleTriggered41==null?"":yioApp.ruleTriggered41+",")+
	(yioApp.ruleTriggered42==null?"":yioApp.ruleTriggered42+",")+
	(yioApp.ruleTriggered43==null?"":yioApp.ruleTriggered43+",")+
	(yioApp.ruleTriggered44==null?"":yioApp.ruleTriggered44+",")+
	(yioApp.ruleTriggered45==null?"":yioApp.ruleTriggered45+",")+
	(yioApp.ruleTriggered46==null?"":yioApp.ruleTriggered46+",")+
	(yioApp.ruleTriggered47==null?"":yioApp.ruleTriggered47+",")+
	(yioApp.ruleTriggered48==null?"":yioApp.ruleTriggered48+",")+
	(yioApp.ruleTriggered49==null?"":yioApp.ruleTriggered49+",")+
	(yioApp.ruleTriggered50==null?"":yioApp.ruleTriggered50+",")+
	(yioApp.ruleTriggered51==null?"":yioApp.ruleTriggered51+",")+
	(yioApp.ruleTriggered52==null?"":yioApp.ruleTriggered52+",")+
	(yioApp.ruleTriggered53==null?"":yioApp.ruleTriggered53+",")+
	(yioApp.ruleTriggered54==null?"":yioApp.ruleTriggered54+",")+
	(yioApp.ruleTriggered55==null?"":yioApp.ruleTriggered55+",")+
	(yioApp.ruleTriggered56==null?"":yioApp.ruleTriggered56+",")+
	(yioApp.ruleTriggered57==null?"":yioApp.ruleTriggered57+",")+
	(yioApp.ruleTriggered58==null?"":yioApp.ruleTriggered58+",")+
	(yioApp.ruleTriggered59==null?"":yioApp.ruleTriggered59+",")+
	(yioApp.ruleTriggered60==null?"":yioApp.ruleTriggered60+",")+
	(yioApp.ruleTriggered61==null?"":yioApp.ruleTriggered61+",")+
	(yioApp.ruleTriggered62==null?"":yioApp.ruleTriggered62+",")+
	(yioApp.ruleTriggered63==null?"":yioApp.ruleTriggered63+",")+
	(yioApp.ruleTriggered64==null?"":yioApp.ruleTriggered64+",")+
	(yioApp.ruleTriggered65==null?"":yioApp.ruleTriggered65+",")+
	(yioApp.ruleTriggered66==null?"":yioApp.ruleTriggered66+",")+
	(yioApp.ruleTriggered67==null?"":yioApp.ruleTriggered67+",")+
	(yioApp.ruleTriggered68==null?"":yioApp.ruleTriggered68+",")+
	(yioApp.ruleTriggered69==null?"":yioApp.ruleTriggered69+",")+
	(yioApp.ruleTriggered70==null?"":yioApp.ruleTriggered70+",")+
	(yioApp.ruleTriggered71==null?"":yioApp.ruleTriggered71+",")+
	(yioApp.ruleTriggered72==null?"":yioApp.ruleTriggered72+",")+
	(yioApp.ruleTriggered73==null?"":yioApp.ruleTriggered73+",")+
	(yioApp.ruleTriggered74==null?"":yioApp.ruleTriggered74+",")+
	(yioApp.ruleTriggered75==null?"":yioApp.ruleTriggered75);
	return allRule;
}
//拼接当前件家庭全地址
function gethomeAddress(dataApp){
	var homeAddress = (dataApp.homeAddr1==null?"":dataApp.homeAddr1+",")+
	  (dataApp.homeAddr2==null?"":dataApp.homeAddr2+",")+
	  (dataApp.homeAddr3==null?"":dataApp.homeAddr3+",")+
	  (dataApp.homeAddr4==null?"":dataApp.homeAddr4+",")+
	  (dataApp.homeAddr5==null?"":dataApp.homeAddr5);
	return homeAddress;
}
//拼接对比件家庭全地址
function getconHomeAddress(datCon){
	var conHomeAddress = (datCon.homeAddr1==null?"":datCon.homeAddr1+",")+
	 (datCon.homeAddr2==null?"":datCon.homeAddr2+",")+
	 (datCon.homeAddr3==null?"":datCon.homeAddr3+",")+
	 (datCon.homeAddr4==null?"":datCon.homeAddr4+",")+
	 (datCon.homeAddr5==null?"":datCon.homeAddr5);
	return conHomeAddress;
}
//拼接当前件单位全地址
function getcompanyAddress(dataApp){
	var companyAddress = (dataApp.compAddrField1==null?"":dataApp.compAddrField1+",")+
	 (dataApp.compAddrField2==null?"":dataApp.compAddrField2+",")+
	 (dataApp.compAddrField3==null?"":dataApp.compAddrField3+",")+
	 (dataApp.compAddrField4==null?"":dataApp.compAddrField4+",")+
	 (dataApp.compAddrField5==null?"":dataApp.compAddrField5);
	return companyAddress;
}
//拼接对比件单位全地址
function getconCompanyAddress(datCon){
	var conCompanyAddress = (datCon.compAddrField1==null?"":datCon.compAddrField1+",")+
	(datCon.compAddrField2==null?"":datCon.compAddrField2+",")+
	(datCon.compAddrField3==null?"":datCon.compAddrField3+",")+
	(datCon.compAddrField4==null?"":datCon.compAddrField4+",")+
	(datCon.compAddrField5==null?"":datCon.compAddrField5);
	return conCompanyAddress;
}
//获得亲核工作字段
function getcheckWork(dataApp){
	var tablefiled = (dataApp.signReserveField==null?"":dataApp.signReserveField);
	if(tablefiled!=""&&tablefiled!=null){
	var checkWork = tablefiled.split("|");
	   if(checkWork.length>2){
	return checkWork[2];
	   }else{
	   return "";
	   }
	}else{
	return "";
	}
}
//获得亲见签名字段
function getcheckSignature(dataApp){
	var tablefiled = (dataApp.signReserveField==null?"":dataApp.signReserveField);
	if(tablefiled!=""&&tablefiled!=null){
	var checkSignature = tablefiled.split("|");
	if(checkSignature.length>1){
	return checkSignature[1];
	   }else{
	return "";	
	}
	}else{
	return "";
	}
}
//获得亲核住宅字段
function getcheckHome(dataApp){
	var tablefiled = (dataApp.signReserveField==null?"":dataApp.signReserveField);
	if(tablefiled!=""&&tablefiled!=null){
	var checkHome = tablefiled.split("|");
	if(checkHome.length>3){
	return checkHome[3];
	}else{
	return "";	
	}
	}else{
	return "";
	}
}

//获得ImpTime导入时间
function getDateFormat(dataApp){
	var dateFormat = dataApp.crtDate;
	if(dateFormat!=""&&dateFormat!=null){
	var date = new Date(dateFormat);
	    return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
	}else{
	return "";
	}
	
}

//获得BirthDte出生日期
function getDateForBirthDate(dataApp){
	var dateFormat = dataApp.applyerBirthDate;
	if(dateFormat!=""&&dateFormat!=null){
	var date = new Date(dateFormat);
	    return date.getFullYear()+"/"+(date.getMonth()+1)+"/"+date.getDate();
	}else{
	return "";
	}
	
}
//获得公司区号+公司电话
function getPhoneArear(dataApp){
	
	var compTelArea = dataApp.compTelArea==null?"":dataApp.compTelArea;//区号
	var compTel = dataApp.compTel==null?"":dataApp.compTel;//电话
	var compPhone="";
	if(compTelArea!=null){
	compTelArea = getSub(compTelArea);
	}
	compPhone =compTelArea+compTel;
	return compPhone==null?"":compPhone;
}
//获得家庭区号+家庭电话
function getfamilyArear(dataApp){
	var applyerHomeTelArea = dataApp.applyerHomeTelArea==null?"":dataApp.applyerHomeTelArea;//区号
	var applyerHomeTel = dataApp.applyerHomeTel==null?"":dataApp.applyerHomeTel;//电话
	var applyerHomePhone = "";
	if(applyerHomeTelArea!=null){
	applyerHomeTelArea = getSub(applyerHomeTelArea);
	}
	applyerHomePhone =applyerHomeTelArea+applyerHomeTel;
	return applyerHomePhone==null?"":applyerHomePhone;
}

//获得直联亲属区号+公司电话
function getPhoneMan1(dataApp){
	var linkMan1TelArea = dataApp.linkMan1TelArea==null?"":dataApp.linkMan1TelArea;//区号
	var linkMan1Phon = dataApp.linkMan1Phon==null?"":dataApp.linkMan1Phon;//电话
	var linkMan1Phone="";
	if(linkMan1TelArea!=null){
	linkMan1TelArea = getSub(linkMan1TelArea);
	}
	linkMan1Phone = linkMan1TelArea+linkMan1Phon;
	return linkMan1Phone==null?"":linkMan1Phone;
}

//获得他联亲属区号+公司电话
function getPhoneMan2(dataApp){
	var linkMan2TelArea = dataApp.linkMan2TelArea==null?"":dataApp.linkMan2TelArea;//他联亲属区号
	var linkMan2Phon = dataApp.linkMan2Phon==null?"":dataApp.linkMan2Phon;//他联亲属电话
	var linkPhone="";
	if(linkMan2TelArea!=null){
	
	linkMan2TelArea =getSub(linkMan2TelArea);
	}
	linkPhone = linkMan2TelArea+linkMan2Phon;
	
	return linkPhone==null?"":linkPhone;
}

//区号处理--likt
function getSub(phoneArea){
	var as = phoneArea.split("");
	var n = 0;
	for(var i = 0 ; i<as.length ; i++){
	if((as[i]+"")=="0"){
	n++;
	}else{
	break;
	}
	}
	if(n!=0){
	phoneArea = phoneArea.substring(n);
	}
	return phoneArea;
	
}

//Decision Reason转换中文--likt
function getDecision(appMemo){
	var  fraudDecisionReason = appMemo.fraudDecisionReason==null?"":appMemo.fraudDecisionReason;
	if(fraudDecisionReason!=null){
	fraudDecisionReason = fraudDecisionReason.toUpperCase();
	if(fraudDecisionReason=="A01"){
	return "A01——ID黑名单——I类黑名单";
	}else
	if(fraudDecisionReason=="A02"){
	return "A02——电话黑名单 ";
	}else
	if(fraudDecisionReason=="A03"){
	return "A03——单位黑名单 ";
	}else
	if(fraudDecisionReason=="A04"){
	return "A04——地址黑名单   ";
	}else
	if(fraudDecisionReason=="A05"){
	return "A05——其他黑名单 ";
	}else
	if(fraudDecisionReason=="A11"){
	return "A11——黑名单——虚假ID（ID、户籍证明）";
	}else
	if(fraudDecisionReason=="A12"){
	return "A12——黑名单——身份盗用 ";
	}else
	if(fraudDecisionReason=="A13"){
	return "A13——黑名单——身份盗用（盗用人） ";
	}else
	if(fraudDecisionReason=="A21"){
	return "A21——黑名单——办卡公司 ";
	}else
	if(fraudDecisionReason=="A22"){
	return "A22——黑名单——套现公司";
	}else
	if(fraudDecisionReason=="A23"){
	return "A23——黑名单——批量欺诈";
	}else
	if(fraudDecisionReason=="A31"){
	return "A31——黑名单——虚假公司 ";
	}else
	if(fraudDecisionReason=="A32"){
	return "A32——黑名单——虚假雇佣";
	}else
	if(fraudDecisionReason=="A33"){
	return "A33——黑名单——虚假工作信息";
	}else
	if(fraudDecisionReason=="A34"){
	return "A34——黑名单——虚假证明文件";
	}else
	if(fraudDecisionReason=="A35"){
	return "A35——黑名单——其他信息虚假";
	}else
	if(fraudDecisionReason=="B01"){
	return "B01——虚假ID（ID、户籍证明）";
	}else
	if(fraudDecisionReason=="B02"){
	return "B02——身份盗用   ";
	}else
	if(fraudDecisionReason=="B03"){
	return "B03——身份盗用——盗用人";
	}else
	if(fraudDecisionReason=="C01"){
	return "C01——办卡公司 ";
	}else
	if(fraudDecisionReason=="C02"){
	return "C02——套现公司 ";
	}else
	if(fraudDecisionReason=="C03"){
	return "C03——其他类型欺诈申请（办卡公司、批量欺诈）";
	}else
	if(fraudDecisionReason=="D01"){
	return "D01——虚假公司   ";
	}else
	if(fraudDecisionReason=="D02"){
	return "D02——虚假公司（网查无信息）";
	}else
	if(fraudDecisionReason=="E01"){
	return "E01——虚假工作信息  ";
	}else
	if(fraudDecisionReason=="E02"){
	return "E02——虚假住宅信息";
	}else
	if(fraudDecisionReason=="E03"){
	return "E03——其他信息虚假";
	}else
	if(fraudDecisionReason=="E04"){
	return "E04——客户否认办卡,但表填关键信息真实";
	}else
	if(fraudDecisionReason=="F01"){
	return "F01——收入证明虚假（公司收入证明） ";
	}else
	if(fraudDecisionReason=="F02"){
	return "F02——资产证明虚假（房产证、车辆行驶证）";
	}else
	if(fraudDecisionReason=="F03"){
	return "F03——财力证明虚假（银行对账单、税单） ";
	}else
	if(fraudDecisionReason=="F04"){
	return "F04——企业证明虚假（营业执照）";
	}else
	if(fraudDecisionReason=="F05"){
	return "F05——工作证明虚假（工作证、名片、工牌） ";
	}else
	if(fraudDecisionReason=="F06"){
	return "F06——居住证明虚假（公用事业费账单、暂住证、租房合同）";
	}else
	if(fraudDecisionReason=="F07"){
	return "F07——其他虚假文件";
	}else
	if(fraudDecisionReason=="G01"){
	return "G01——虚假雇佣（单位无此员工）";
	}else
	if(fraudDecisionReason=="H01"){
	return "H01——无卡分期自动判定";
	}else
	if(fraudDecisionReason=="H02"){
	return "H02——自动判定条件（RJ40）  ";
	}else
	if(fraudDecisionReason=="H03"){
	return "H03——自动判定条件（RJ43）";
	}else
	if(fraudDecisionReason=="H04"){
	return "H04——自动判定条件（RJ44） ";
	}else
	if(fraudDecisionReason=="H05"){
	return "H05——自动判定条件（RJ25） ";
	}else
	if(fraudDecisionReason=="H06"){
	return "H06——自动判定条件（RJ48） ";
	}else
	if(fraudDecisionReason=="H07"){
	return "H07——自动判定条件（RJ09）";
	}else
	if(fraudDecisionReason=="H08"){
	return "H08——自动判定条件（RJ52）  ";
	}else
	if(fraudDecisionReason=="H09"){
	return "H09——自动判定条件（RJ96）";
	}else
	if(fraudDecisionReason=="H10"){
	return "H10——自动判定条件（RJ79）";
	}else
	if(fraudDecisionReason=="H11"){
	return "H11——芝麻IVS高风险自动判定（RJ62）";
	}else
	if(fraudDecisionReason=="H12"){
	return "H12——黑名单IP（RJ62）";
	}else
	if(fraudDecisionReason=="H13"){
	return "H13——强特征网络申请自动判定（RJ57） ";
	}else
	if(fraudDecisionReason=="H14"){
	return "H14——特定地区网络风险申请自动判定（RJ57）";
	}else 
	if(fraudDecisionReason=="H15"){
	return "H15——黑名单ID（RJ62）";
	}else 
	if(fraudDecisionReason=="H16"){
	return "H16——黑名单中介信息";
	}else 
	if(fraudDecisionReason=="H17"){
	return "H17——线上高风险特征申请自动判定";
	}else 
	if(fraudDecisionReason=="H18"){
	return "H18——宁海地区网络申请自动判定";
	}else 
	if(fraudDecisionReason=="H19"){
	return "H19——欺诈分数高分段网络申请自动判定（RJ79）";
	}else 
	if(fraudDecisionReason=="H20"){
	return "H20——无效合影，人像识别未通过（RJ62）";
	}else 
	if(fraudDecisionReason=="H21"){
	return "H21——IHC手工提报（否认办卡,RJ52)";
	}else 
	if(fraudDecisionReason=="H22"){
	return "H22——IHC手工提报（办卡公司、他人代办,RJ57）";
	}else 
	if(fraudDecisionReason=="H23"){
	return "H23——IHC手工提报（单址、单电非报单位或单位信息无法报出,RJ73）";
	}else 
	if(fraudDecisionReason=="H24"){
	return "H24——IHC手工提报（单位无app或app 已离职,RJ78）";
	}else 
	if(fraudDecisionReason=="H25"){
	return "H25——IHC手工提报（表单电空号/停机/异常,RJ16）";
	}else 
	if(fraudDecisionReason=="H26"){
	return "H26——IHC手工提报（其他异常情况,RJ79）";
	}else 
	if(fraudDecisionReason=="H27"){
	return "H27——青春贷钱好贷产品实时流程自动判定";
	}else
	if(fraudDecisionReason=="X01"){
	return "X01——STP项目拒绝申请 ";
	}else
	if(fraudDecisionReason=="Z01"){
	return "Z01——腾讯650分免工作核查   ";
	}else
	if(fraudDecisionReason=="Z02"){
	return "Z02——腾讯E01免个人核实     ";
	}else
	if(fraudDecisionReason=="Z03"){
	return "Z03——宁泽涛卡";
	}else
	if(fraudDecisionReason=="Z04"){
	return "Z04——自动判定清白方案";
	}else
	if(fraudDecisionReason=="Z05"){
	return "Z05——芝麻IVS低风险 ";
	}else
	if(fraudDecisionReason=="Z06"){
	return "Z06——芝麻IVS低风险自动判定";
	}else
	if(fraudDecisionReason=="Z07"){
	return "Z07——分行协助完成核实 ";
	}else{
	return "";
	}
	}else{
	return "";
	}
}

//全名
function getAllName(dataApp){ 
	var applyerCPin = dataApp.applyerCPin==null?"":dataApp.applyerCPin;
	var applyerCName = dataApp.applyerCName==null?"":dataApp.applyerCName;
	var allName="";
	if(applyerCPin!=null && applyerCName!=null){
	 allName = applyerCPin+applyerCName;
	}
	return allName;
}


//邮箱大小写转换
function getEmailStyle(dataApp){
	var applyerEmail = dataApp.applyerEmail==null?"":dataApp.applyerEmail;
	var email = "";
	if(applyerEmail!=null){
	email = applyerEmail.toUpperCase();//小写转大写
	//email = applyerEmail.toLowerCase();//大写转小写
	}
	 return email;
}

//家庭地址2去掉省份和市
function getSubHomeAdd(dataApp){
	var homeAdd =dataApp.homeAddr2==null?"":dataApp.homeAddr2;
	if(homeAdd!=null){
	var mm = homeAdd.indexOf("省");
	if(mm!=-1){
	 homeAdd = homeAdd.substring(mm+1, homeAdd.length);
	}
	var nn = homeAdd.indexOf("市");
	if(nn!=-1){
	 homeAdd = homeAdd.substring(nn+1, homeAdd.length);
	}
	}
	return homeAdd;
}

//公司地址2去掉省份和市
function getSubCompAdd(dataApp){
  var compAddrField = dataApp.compAddrField2==null?"":dataApp.compAddrField2;
  if(compAddrField!=null){
	  var aa  = compAddrField.indexOf("省");
	  if(aa!=-1){
	  compAddrField = compAddrField.substring(aa+1, compAddrField.length);
	  } 
	  var bb  = compAddrField.indexOf("市");
	  if(bb!=-1){
	  compAddrField = compAddrField.substring(bb+1, compAddrField.length);
	  }
  }
  return compAddrField;
}

//Action Taken 转为中文--likt
function getNodeTotype(appMemo){
	var fraudFlag = appMemo.fraudFlag==null?"":appMemo.fraudFlag;
	if(fraudFlag!=null && fraudFlag!="" ){
	if(fraudFlag=="K" || fraudFlag=="K " || fraudFlag==" K"){
	return "确认欺诈";
	}else if(fraudFlag=="S" || fraudFlag=="S " || fraudFlag==" S"){
	return "疑似欺诈";
	}else if(fraudFlag=="U" || fraudFlag=="U " || fraudFlag==" U"){
	return "调查中";
	}else if(fraudFlag=="F" || fraudFlag=="F " || fraudFlag==" F"){
	return "无欺诈";
	}else{
	return fraudFlag;
	}
	}else{
	return "";
	}
}



//履历转换中文--likt
function getRemark(appMemo){
	var  suspectDecisionReason = appMemo.suspectDecisionReason==null?"":appMemo.suspectDecisionReason;
	if(suspectDecisionReason!=null){
	suspectDecisionReason=suspectDecisionReason.toUpperCase();
	if(suspectDecisionReason=="S01"){
	return "S01——工作信息可疑，公共渠道无有效电话";
	}else
	if(suspectDecisionReason=="S02"){
	return "S02——工作信息可疑，就职2天3Call失败";
	}else
	if(suspectDecisionReason=="S03"){
	return "S03——工作信息可疑，第三方无法确认 ";
	}else
	if(suspectDecisionReason=="S04"){
	return "S04——个人信息可疑，个人2天3Call失败";
	}else
	if(suspectDecisionReason=="S05"){
	return "S05——个人信息可疑，客户不配合核实";
	}else
	if(suspectDecisionReason=="S06"){
	return "S06——个人信息可疑，客户手机异常";
	}else
	if(suspectDecisionReason=="S07"){
	return "S07——无法排除批量欺诈嫌疑 ";
	}else
	if(suspectDecisionReason=="S08"){
	return "S08——无法排除欺诈嫌疑";
	}else
	if(suspectDecisionReason=="S09"){
	return "S09——该节点无欺诈策略";
	}else{
	return "";
	}
	}else{
	return "";
	}
}

//账单寄送方式--likt
function getBillSendWayType(dataApp){
	var billSendWay = dataApp.billSendWay==null?"":dataApp.billSendWay;
	if(billSendWay!=null && billSendWay!="" ){
	
	billSendWay=billSendWay.toUpperCase();
	
	if(billSendWay=="LE" || billSendWay==" LE" || billSendWay=="LE "){
	return "LE——信件电子邮件寄送帐单";
	}else if(fraudFlag=="EM" || fraudFlag==" EM" || fraudFlag=="EM "){
	return "EM——电子邮件寄送帐单";
	}else if(fraudFlag=="LT" || fraudFlag==" LT" || fraudFlag=="LT "){
	return "LT——信件寄送帐单";
	}else{
	return billSendWay;
	}
	}else{
	return "";
	}
}

//账单邮寄地--likt
function getBillSendAddr1(dataApp){
	var billSendAddr1 = dataApp.billSendAddr1==null?"":dataApp.billSendAddr1;
	if(billSendAddr1!=null && billSendAddr1!=""){
	billSendAddr1 = billSendAddr1.toUpperCase();
	if(billSendAddr1=="H" || billSendAddr1==" H" || billSendAddr1=="H "){
	return "H——家庭地址";
	}else if(billSendAddr1=="B" || billSendAddr1==" B" || billSendAddr1=="B "){
	return "B——单位地址";
	}else if(billSendAddr1=="F" || billSendAddr1==" F" || billSendAddr1=="F "){
	return "F——户籍地址";
	}else if(billSendAddr1=="W" || billSendAddr1==" W" || billSendAddr1=="W "){
	return "W——房产地址";
	}else{
	return billSendAddr1;
	}
	
	}else{
	return "";
	}
}

//卡片邮寄地--likt
function getBillSendAddr(dataApp){
	var billSendAddr = dataApp.billSendAddr==null?"":dataApp.billSendAddr;
	if(billSendAddr!=null && billSendAddr!=""){
	billSendAddr = billSendAddr.toUpperCase();
	if(billSendAddr=="H" || billSendAddr==" H" || billSendAddr=="H "){
	return "H——家庭地址";
	}else if(billSendAddr=="B" || billSendAddr==" B" || billSendAddr=="B "){
	return "B——单位地址";
	}else if(billSendAddr=="F" || billSendAddr==" F" || billSendAddr=="F "){
	return "F——户籍地址";
	}else if(billSendAddr=="W" || billSendAddr==" W" || billSendAddr=="W "){
	return "W——房产地址";
	}else{
	return billSendAddr;
	}
	
	}else{
	return "";
	}
}

//卡片递送方式--likt
function getCardSendWay(dataApp){
	var cardSendWay = dataApp.cardSendWay==null?"":dataApp.cardSendWay;
	if(cardSendWay!=null && cardSendWay!=""){
	cardSendWay = cardSendWay.toUpperCase();
	if(cardSendWay=="COLL" || cardSendWay=="COLL " || cardSendWay==" COLL"){
	return "COLL——分行领取";
	}else if(cardSendWay=="POST" || cardSendWay=="POST " || cardSendWay==" POST"){
	return "POST——邮寄到指定地址";
	}else{
	return cardSendWay;
	}
	}else{
	return "";
	}
}

//BusTyp公司分类--likt
function getCompBusiCategoryCode(dataApp){
	var compBusiCategoryCode = dataApp.compBusiCategoryCode==null?"":dataApp.compBusiCategoryCode;
	if(compBusiCategoryCode!=null && compBusiCategoryCode!=""){
	compBusiCategoryCode = compBusiCategoryCode.toLowerCase();
	if(compBusiCategoryCode=="a" || compBusiCategoryCode==" a" || compBusiCategoryCode=="a "){
	return "a——农、林、牧、渔业";
	}else if(compBusiCategoryCod=="b" ||compBusiCategoryCode==" b" || compBusiCategoryCode=="b "){
	return "b——采掘业";
	}else if(compBusiCategoryCod=="c" ||compBusiCategoryCode==" c" || compBusiCategoryCode=="c "){
	return "c——制造业";
	}else if(compBusiCategoryCod=="d" ||compBusiCategoryCode==" d" || compBusiCategoryCode=="d "){
	return "d——电力、煤气及水的生产和供应业";
	}else if(compBusiCategoryCod=="e" ||compBusiCategoryCode==" e" || compBusiCategoryCode=="e "){
	return "e——建筑业";
	}else if(compBusiCategoryCod=="f" ||compBusiCategoryCode==" f" || compBusiCategoryCode=="f "){
	return "f——交通运输、仓储和邮政业";
	}else if(compBusiCategoryCod=="g" ||compBusiCategoryCode==" g" || compBusiCategoryCode=="g "){
	return "g——信息传输、计算机服务和软件业";
	}else if(compBusiCategoryCod=="h" ||compBusiCategoryCode==" h" || compBusiCategoryCode=="h "){
	return "h——批发和零售业";
	}else if(compBusiCategoryCod=="i" ||compBusiCategoryCode==" i" || compBusiCategoryCode=="i "){
	return "i——住宿和餐饮业";
	}else if(compBusiCategoryCod=="j" ||compBusiCategoryCode==" j" || compBusiCategoryCode=="j "){
	return "j——金融业";
	}else if(compBusiCategoryCod=="k" ||compBusiCategoryCode==" k" || compBusiCategoryCode=="k "){
	return "k——房地产业";
	}else if(compBusiCategoryCod=="l" ||compBusiCategoryCode==" l" || compBusiCategoryCode=="l "){
	return "l——租赁和商务服务业";
	}else if(compBusiCategoryCod=="m" ||compBusiCategoryCode==" m" || compBusiCategoryCode=="m "){
	return "m——科学研究、技术服务业和地质勘察业";
	}else if(compBusiCategoryCod=="n" ||compBusiCategoryCode==" n" || compBusiCategoryCode=="n "){
	return "n——水利、环境和公共设施管理业";
	}else if(compBusiCategoryCod=="o" ||compBusiCategoryCode==" o" || compBusiCategoryCode=="o "){
	return "o——居民服务和其他服务业";
	}else if(compBusiCategoryCod=="p" ||compBusiCategoryCode==" p" || compBusiCategoryCode=="p "){
	return "p——教育";
	}else if(compBusiCategoryCod=="q" ||compBusiCategoryCode==" q" || compBusiCategoryCode=="q "){
	return "q——卫生、社会保障和社会福利业";
	}else if(compBusiCategoryCod=="r" ||compBusiCategoryCode==" r" || compBusiCategoryCode=="r "){
	return "r——文化、体育和娱乐业";
	}else if(compBusiCategoryCod=="s" ||compBusiCategoryCode==" s" || compBusiCategoryCode=="s "){
	return "s——公共管理和社会组织";
	}else if(compBusiCategoryCod=="t" ||compBusiCategoryCode==" t" || compBusiCategoryCode=="t "){
	return "t——国际组织";
	}else if(compBusiCategoryCod=="z" ||compBusiCategoryCode==" z" || compBusiCategoryCode=="z "){
	return "z——未知";
	}else {
	return compBusiCategoryCod;
	}
	}else{
	return "";
	}
	
}

//Rel联系人关系/RelShip直系亲属关系--likt
function getLinkMan2Relation(dataApp){
	var linkMan2Relation = dataApp.linkMan2Relation==null?"":dataApp.linkMan2Relation;
	if(linkMan2Relation!=null && linkMan2Relation!=""){
	linkMan2Relation = linkMan2Relation.toUpperCase();
	if(linkMan2Relation=="M" || linkMan2Relation=="M " || linkMan2Relation==" M"){
	return "M——Mate—配偶";
	}else if(linkMan2Relation=="F" || linkMan2Relation=="F " || linkMan2Relation==" F"){
	return "F——Family—子女";
	}else if(linkMan2Relation=="P" || linkMan2Relation=="P " || linkMan2Relation==" P"){
	return "P——Parent—父母";
	}else if(linkMan2Relation=="B" || linkMan2Relation=="B " || linkMan2Relation==" B"){
	return "B——Brothers and sisters—兄弟姐妹";
	}else if(linkMan2Relation=="R" || linkMan2Relation=="R " || linkMan2Relation==" R"){
	return "R——Friends—朋友";
	}else if(linkMan2Relation=="C" || linkMan2Relation=="C " || linkMan2Relation==" C"){
	return "C——Colleague—同事";
	}else if(linkMan2Relation=="O" || linkMan2Relation=="O " || linkMan2Relation==" O"){
	return "O——Others—其他";
	}else{
	return linkMan2Relation;
	}
	}else{
	return "";
	}
	
}