var columns = new Array("c1c2Flag","appProd:ALLOT_APPPROD","c4Vercode:ZDHXKBM","c4Gtoc","c1Nation:GUOJI","c2Natncd1:ZDNATIONALITYGJ","c2Natncd2:ZDNATIONALITYGJ","c1Idtype:ZDSQRZJLX",
		"c1Marst:ZDSQRHYZK","c1Educls:ZDJYCDID","c1Hmst","c1Cocode:ZDSSXY",	"c1Cokind:ZDDWXZ","c1Title:GANGWEIZHIWU","c4Cycadd1:ALLOT_CARDTO1","c4Pinreq",
		"c4Usprod","c4Othtype","c1Reship:ZXQSRELATIONSHIP","c1Xship1:ZXQSRELATIONSHIP","c4Vercod2:ZDHXKBM","c2Gender:ZDSEXXB","c1Gender:ZDSEXXB","c1Telvl:ZHICHENG",
		"c2Nation:GUOJI","c2Idtype:ZDSQRZJLX","c2Relship","c4Excode","c5Msgfree","c5Ptplan:MEALPLAN","c4Messreq","c4Rushflg","c5Sercd11",
		"c5Sercd12","c5Sercd13","c4Stmcode","appAcctyp:ZDHXNFDM","c4Giftn2","c4Clyn1","c4Abtype","c4Apsour","c4Relship","houseInfoFrom:FCXXLY",
		"hxCardcentreStaff:KZXYGYGXZ","hxCardcentreStaffLevel:KZXYGZJ","hxBankStaff:ZHYGYGXZ","hxBankStaffLevel:ZHYGZJ","carHand","perLoanstatus",
		"pubLoanstatus","sbCurrPayStatus","gjjPayStatus:GJIJINZT","eduType:XLXX","eduModel","c1Class:ZDSQRKHLB","c4Rushfee","salaryCategroy:SRLX",
		"balanceCategroy:ZCLX","projectCategroy:XMLX","carCategroy","specialProject","passResult","approveResult:YSJL","approveCode:JJBM",
		"c6Ethnic1:nation","c6Ethnic2:nation","c1Telvl:ZHICHENG","industryCustomer","companyCustomer","educationCustomer","houseLoanCustomer","carLoanCustomer",
		"cardCustomer","existCustomer","provSecCustomer","registryCustomer","fixedAssetsCustomer","currAssetsCustomer","incomeCustomer","otherCustomer","idCertify",
		"workCertify","companyCertify","educationCertify","houseLoanCertify","carLoanCertify","cardCertify","existCertify","provCertify","secCertify","houseCertify",
		"carCertify","currAssetsCertify","incomeCertify","otherCertify","applyInputfull","signFull","nationalInutfull","certifiPeriodfull","company","peobankCompanyNameAddr",
		"peobankCompanyNameTel","company114NameAddr","company114NameTel","pyuanCompanyNameAddr","pyuanCompanyNameTel","officwebNameAddr","officwebNameTel","noOfficwebNameAddr",
		"noOfficwebNameTel","otherThirdNameAddr","thirddataAddr","applyWorkinfoTure","sbandgjj6monthsStatus","businetworkLegal","pubPolice","businetworkMinor",
		"peobankFirstcard6months","insurance","others","peobankPhone","thirdPhone","otherthirdInfo","orderAddress","billAddressCheck","homeAddress","houseAddressCheck",
		"applyerTrue","applyWorkTure","individual","individualMonth","thirdPartPay");


function parseRow(row,flag,index){
	var columnname = row.columnName;
	for(var i=0;i<columns.length;i++){
		var choese = columns[i].split(":");
		if(columnname == choese[0]){
			if(choese.length>1){
				var ret = "<select class=\"easyui-combobox\" readonly style=\"width: 110px\" name="+columnname+flag+index+" id="+columnname+flag+index+" dict_type="+choese[1]+" data-options=\"required:true,editable:false,valueField:'dictDetailCode',textField:'dictDetailName'\"></select>";
				if(flag == "2"){
					ret = row.modifyValue != null && row.modifyValue != ""?ret:"";
					return ret;
				}else{
					ret = row.currValue != null && row.currValue != ""?ret:"";
					return ret;
				}
			}else{
				if(flag == "2"){
					var ret =  parseToChinese(columnname,row,flag);
					if(ret == "next"){
						return row.modifyValue;
					}else{
						return ret;
					}
				}else{
					var ret =  parseToChinese(columnname,row,flag);
					if(ret == "next"){
						return row.currValue;
					}else{
						return ret;
					}
				}
			}
		}
	}
	if(flag == "2"){
		return row.modifyValue;
	}else{
		return row.currValue;
	}
}

//信息修改记录  
function creditControlUpdateMessageRecord(){
	$("#applyDealView").window('open');
	$('#tb_applyDealView').datagrid({
		url:"/opas-naps/applyDealView.json",
		border:false,
		/* animate:true, */
		pagination:true,
		nowrap:false,
		fitColumns:true,
		singleSelect:false,
		rownumbers:true,
		pageSize:15,
		queryParams: {
			"appId" : appId
		},
		pageList:[15,20,50],
		columns:[[{
			title:'修改日期',
			field:'modifyDate',
			formatter: function (val,row,index){
				var currDate = formatDate(val);
				return currDate;
			},
			width:"150px",
			halign:'center'
		},{
			title:'操作员',
			field:'operator',
			width:"90px",
			halign:'center'
		},{
			title:'申请人',
			field:'applyName',
			width:"90px",
			halign:'center'
		},{
			title:'证件类别',
			field:'cretifiType',
			width:"100px",
			halign:'center',
			formatter:function(value,row,index){
				if(value=='01'){
					return "18位身份证";
				}else if(value=='02'){
					return "15位身份证";
				}else if(value=='03'){
					return "外国护照";
				}else if(value=='04'){
					return "港澳台居民来往内地通行证台胞证";
				}else if(value=='05'){
					return "军官证";
				}else if(value=='90'){
					return "港澳居民居住证";
				}else if(value=='91'){
					return "台湾居民居住证";
				}else if(value=='07'){
					return "外国人永久居留身份证";
				}else if(value=='20'){
					return "其他";
				}
		}
		},{
			title:'证件号码',
			field:'cretifiId',
			width:"200px",
			halign:'center'
		},{
			title:'数据项',
			field:'keyWordsId',
			width:"150px",
			halign:'center'
		}
		,{
			title:'原信息',
			field:'currValue',
			width:"160px",
			halign:'center',
			formatter:function(value,row,index){
				if(value=="null" || value == null){
					return "";
				}else{
					var ret = parseRow(row,"1",index);
					return ret;
				}
				/* return "<select class=\"easyui-combobox\" style=\"width: 110px\" name=\"c2Natncd1\" id=\"c2Natncd1\" dict_type=\"ZDNATIONALITYGJ\" data-options=\"required:true,editable:true,valueField:'dictDetailCode',textField:'dictDetailName'\"></select>"; */
			}
		},{
			title:'修改后信息',
			field:'modifyValue',
			width:"160px",
			halign:'center',
			formatter:function(value,row,index){
				if(value=="null" || value == null){
					return "";
				}else{
					var ret = parseRow(row,"2",index);
					return ret;
				}
				//var hah = row.modifyValue;
				/* return "<select class=\"easyui-combobox\" style=\"width: 110px\" name=\"c2Natncd1\" id=\"c2Natncd1\"  dict_type=\"ZDNATIONALITYGJ\" data-options=\"required:true,editable:true,valueField:'dictDetailCode',textField:'dictDetailName'\"></select>"; */ 
			}
		},{
			title:'复核状态',
			field:'reviewStatus',
			width:"100px",
			halign:'center',
			formatter:function(value,row,index){
					if(value=='1'){
						return "通过";
					}else if(value=='0'){
						return "拒绝";
					}
			}
		},{
			title:'复核人',
			field:'reviewer',
			width:"100px",
			halign:'center'
		}
		]],
		onLoadSuccess:function(value){
			$cf.loadAllDict();
			var rows = value.rows;
			//alert(value.rows[5].modifyValue);
			for(var i=0;i<rows.length;i++){
				if($("#"+rows[i].columnName+"1"+i+"").combobox){
					$("#"+rows[i].columnName+"1"+i+"").combobox('setValue',value.rows[i].currValue);
				}
				if($("#"+rows[i].columnName+"2"+i+"").combobox){
					$("#"+rows[i].columnName+"2"+i+"").combobox('setValue',value.rows[i].modifyValue);
				}
			}
		}
	});	
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
	var d = date.getDate();
	var h = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	m = m < 10 ? ('0' + m) : m;
	d = d < 10 ? ('0' + d) : d;
	h = h < 10 ? ('0' + h) : h;
	minute = minute < 10 ? ('0' + minute) : minute;
	second = second < 10 ? ('0' + second) : second;
	return y + "-" + m + "-" + d + " " + h + ":" + minute + ":"
			+ second;
}; 

function parseToChinese(columnname,row,flag){
	var thisValue = "";
	if(flag == "2"){
		thisValue = row.modifyValue;
	}else{
		thisValue = row.currValue;
	}
	if(columnname == 'c4Gtoc'){ //是否愿意降级
		var mapper = new Array("1:愿意","0:不愿意");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c1Hmst"){ //房屋	
		var mapper = new Array("1:自购有贷款","2:自购无贷款","3:单位分配","5:租用","4:与父母同住","6:其他/集体宿舍");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Pinreq"){ //主卡刷卡消费是否凭密码
		var mapper = new Array("1:凭密码+签名","0:凭签名");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Usprod"){ //您的华夏银行的开户种类 
		var mapper = new Array("1:至尊金卡","2:普通华夏卡","3:华夏信用卡","4:个人贷款","5:储蓄");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Othtype"){ //您的持卡情况
		var mapper = new Array("D:无信用卡","C:持有华夏信用卡","X:持有他行信用卡");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Excode"){ //还款方式
		var mapper = new Array("T:全额","M:最低");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c5Msgfree"){ //促销活动的短信通知
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Messreq" || columnname == "c4Rushflg" || columnname == "c5Sercd11" || columnname == "c5Sercd12" || columnname == "c5Sercd13"){ //增值服务
		var mapper = new Array("1:愿意","0:不愿意");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Stmcode"){ //账单寄送方式
		var mapper = new Array("EM:电子账单","LT:纸质账单","EL:纸质账单和同时接受电子账单");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Giftn2"){ //优享方案
		var mapper = new Array("8001:轻松周转（8001）","8002:大借小还（8002）","8080:个人开卡礼","5180:团办活动");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Clyn1"){ //行驶证情况
		var mapper = new Array("1:有","0:无");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Abtype"){ //推广方式
		var mapper = new Array("1:亲见签名","2:亲访单位","3:亲访住宅","4:未亲签未亲访");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Apsour"){ //进件来源
		var mapper = new Array("1:我行客户","2:陌生拜访","3:他人转件","4:设点营销","5:电话营销","6:信函营销","7:自进件","8:转介绍","9:其他");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Relship"){ //推广人员与申请人关系
		var mapper = new Array("1:亲戚","2:朋友","3:同学","4:客户","5:其他");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c2Relship"){ // 与主卡持有/申请人关系
		var mapper = new Array("1:配偶","2:子女","3:父母","6:其他","5:朋友","4:兄弟");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "carHand"){ // 车辆手别
		var mapper = new Array("1:一手","2:二手");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "perLoanstatus"){ // 贷款状态
		var mapper = new Array("1:正常","0:非正常");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "pubLoanstatus"){ // 贷款状态
		var mapper = new Array("1:正常","0:非正常");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "sbCurrPayStatus"){ // 当前缴交状态
		var mapper = new Array("1:正常","0:非正常");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "eduType"){ // 学历状态
		var mapper = new Array("1:大专","2:本科","3:硕士","4:博士及以上","0:其他");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "eduModel"){ // 学习形式
		var mapper = new Array("1:非全日制","0:全日制");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "c4Rushfee"){ // 是否收取快速发卡费用
		var mapper = new Array("1:收取","0:不收取");
		return codeToChinese(thisValue,mapper);
	}
	
	if(columnname == "salaryCategroy"){ // 收入类型
		var mapper = new Array("1:收入流水","2:收入/工作证明","3:密薪工资单","4:其他");
		return codeToChinese(thisValue,mapper);
	}
	
	if(columnname == "balanceCategroy"){ // 资产类型
		var mapper = new Array("1:存款","2:理财","3:基金","4:保险","5:国债","6:股票","7:其他");
		return codeToChinese(thisValue,mapper);
	}
	
	if(columnname == "projectCategroy"){ // 项目类型
		var mapper = new Array("1:CC0006");
		return codeToChinese(thisValue,mapper);
	}
	
	if(columnname == "projectCategroy"){ // 项目类型
		var mapper = new Array("1:CC0006");
		return codeToChinese(thisValue,mapper);
	}
	
	if(columnname == "carCategroy"){ // 车辆性质
		var mapper = new Array("0:营运","1:非营运");
		return codeToChinese(thisValue,mapper);
	}
	
	if(columnname == "specialProject"){ // 是否是特殊项目
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}

	if(columnname == "industryCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "companyCustomer"){ //绩优企业客户
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "educationCustomer"){ //学历客户
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "houseLoanCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "carLoanCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "cardCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "existCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "provSecCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "registryCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "fixedAssetsCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "currAssetsCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "incomeCustomer"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "otherCustomer"){ 
		var mapper = new Array("1:其他","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "idCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "workCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "companyCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "educationCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "houseLoanCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "carLoanCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "cardCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "existCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "provCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "secCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "houseCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "carCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "currAssetsCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "incomeCertify"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "otherCertify"){ 
		var mapper = new Array("1:其他","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "passResult"){
		var mapper = new Array("1:A01","2:A02","3:A03","4:A04",
				"5:A05","6:A07","7:A08","8:B01","9:B02","10:B03",
				"11:B04","12:B05","13:B06","14:B07","15:B08","16:B09",
				"17:B10","18:B11","19:B12","20:C03","21:C04","22:C05",
				"23:C06","24:C07","25:C08","26:其他");
		return codeToChinese(thisValue,mapper);
	}
	
	if(columnname == "applyInputfull"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "signFull"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "nationalInutfull"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "certifiPeriodfull"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "company"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "peobankCompanyNameAddr"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "peobankCompanyNameTel"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "company114NameAddr"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "company114NameTel"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "pyuanCompanyNameAddr"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "pyuanCompanyNameTel"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "officwebNameAddr"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	
	
	if(columnname == "officwebNameTel"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "noOfficwebNameAddr"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "noOfficwebNameTel"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "otherThirdNameAddr"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "thirddataAddr"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "applyWorkinfoTure"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "sbandgjj6monthsStatus"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "businetworkLegal"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "pubPolice"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "businetworkMinor"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "peobankFirstcard6months"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "insurance"){ 
		var mapper = new Array("1:是","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "others"){ 
		var mapper = new Array("1:其他","null:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "peobankPhone"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "thirdPhone"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "otherthirdInfo"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "orderAddress"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "billAddressCheck"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "homeAddress"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "houseAddressCheck"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "houseAddressCheck"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "applyWorkTure"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "applyerTrue"){ 
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}
	if(columnname == "individual"){ // 是否个体
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}	
	/*if(columnname == "individualMonth"){ // 个体成立日期
		var mapper = new Array("个体成立日期");
		return codeToChinese(thisValue,mapper);
	}*/	
	if(columnname == "thirdPartPay"){ // 是否第三方代缴
		var mapper = new Array("1:是","0:否");
		return codeToChinese(thisValue,mapper);
	}	
	return "next";
}

function codeToChinese(thisValue,mapper){
	for(var i=0;i<mapper.length;i++){
		var detail = mapper[i].split(":");
		if(thisValue == detail[0]){
			return detail[1];
		}
	}
	return "参数出问题!";

}