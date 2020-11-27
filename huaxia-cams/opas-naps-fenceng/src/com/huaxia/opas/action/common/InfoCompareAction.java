package com.huaxia.opas.action.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.compare.RevCompInfo;
import com.huaxia.opas.domain.input.BizInpApplC1;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheck;
import com.huaxia.opas.service.apply.ApplyInfoService;
import com.huaxia.opas.service.compare.EtcPadService;
import com.huaxia.opas.service.compare.RevCompInfoService;
import com.huaxia.opas.service.thirdparty.PBOCService;
import com.huaxia.opas.service.thirdparty.PoliceService;
import com.huaxia.opas.service.thirdparty.QiYeService;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.TransBean2Map;
import com.ibm.icu.util.Calendar;

import net.sf.json.JSONArray;

/**
 * 修改人行信息的匹配结果从反欺诈系统返回的结果获取
 * 
 * @author wangtao
 * @version v1.1 2017-10-20 初始版本v1.0
 */
public class InfoCompareAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(InfoCompareAction.class);

	@Resource(name = "revCompInfoService")
	private RevCompInfoService revCompInfoService;
	@Resource(name = "policeService")
	private PoliceService policeService;
	@Resource(name = "applyInfoService")
	private ApplyInfoService applyService;
	@Resource(name = "pbocService")
	private PBOCService pbocService;
	@Resource(name = "qiYeService")
	private QiYeService qiYeService;
	
	@Resource(name = "etcPadService")
	private EtcPadService etcPadService;
	
	@Value("${CRETR_TIME}")
	private String datestr;
	public void addInfoCompare(Context ctx) throws CoreException, ParseException {
		Gson json = new Gson();
		Map<String, Object> dataMap = ctx.getDataMap();
		if (dataMap.get("houseAddressCheck") != null) {
			dataMap.put("homeAddress", dataMap.get("houseAddressCheck"));
		}
		if (dataMap.get("billAddressCheck") != null) {
			dataMap.put("orderAddress", dataMap.get("billAddressCheck"));
		}
		String userCode = (String) dataMap.get("userCode");
		String appId = (String) dataMap.get("appId");
		String commitFlag = (String) dataMap.get("commitFlag");
		String nodeFlag = (String) dataMap.get("nodeFlag");
		RevCompInfo revCompInfo = json.fromJson(json.toJson(dataMap), RevCompInfo.class);
		revCompInfo.setCrtDate(new Date());
		RevCompInfo revCompInfo2 = revCompInfoService.selectByPrimaryKey(revCompInfo.getAppId());

		Map<String, Object> beanMap = new HashMap<>();
		Map<String, Object> newMap = new HashMap<>();
		// 获取申请件编号的第7位
		String isWebBolt = ctx.getData("isWebBolt");
		try {
			if (revCompInfo2 == null) {
				/**
				 * 比较页面修改的值落入关键信息修改记录表,下面的newMap是第一次初时加载时的页面值
				 */
				// Map<String,String> map1 =
				// (Map<String,String>)TransBean2Map.transBean2Map(map.get("revCompInfo"));
				// Map<String, Object> map1 =
				// TransBean2Map.transBean2Map(staticMap.get("revCompInfo"));
				// Map<String,Object> map2= (Map<String, Object>)
				// staticMap.get("rest");

				// Set<Entry<String, Object>> entrySet1 = map1.entrySet();
				// Set<Entry<String, Object>> entrySet2 = map2.entrySet();
				// for (Entry<String, Object> entry1 : entrySet1) {
				// String key = entry1.getKey();
				// Object value = entry1.getValue();
				// newMap.put(key, value);
				// }
				// for (Entry<String, Object> entry2 : entrySet2) {
				// String key = entry2.getKey();
				// Object value = entry2.getValue();
				// newMap.put(key, value);
				// }
				RevCompInfo revCompInfo3 = getRevCompInfo(appId);
				newMap = TransBean2Map.transBean2Map(revCompInfo3);
				beanMap = TransBean2Map.transBean2Map(revCompInfo);
				beanMap.remove("crtDate");
				beanMap.remove("appId");
				revCompInfoService.insert(revCompInfo, newMap, beanMap, appId, userCode, isWebBolt, commitFlag);
			} else {
				if ("02".equals(nodeFlag)) {
					newMap = TransBean2Map.transBean2Map(revCompInfo2);
				} else {
					RevCompInfo revCompInfo4 = getRevCompInfo(appId);
					newMap = TransBean2Map.transBean2Map(revCompInfo4);
				}
				beanMap = TransBean2Map.transBean2Map(revCompInfo);
				newMap.remove("crtDate");
				beanMap.remove("crtDate");
				beanMap.remove("appId");
				newMap.remove("appId");
				revCompInfoService.updateByPrimaryKey(revCompInfo, newMap, beanMap, appId, userCode, isWebBolt,
						commitFlag);
			}

		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		ctx.setData("isSuccess", true);
	}

	public RevCompInfo getRevCompInfo(String appId) throws CoreException, ParseException {
		RevCompInfo revCompInfo = new RevCompInfo();
		/*RevCompInfo revCompInfo = null;
		String flag = "1";
		try {
			revCompInfo = revCompInfoService.selectByPrimaryKey(appId);
		} catch (CoreException e) {
			//ctx.setData("exMsg", e.getMessage());
			//throw e;
			e.printStackTrace();
		}
		if (revCompInfo == null) {
			revCompInfo = new RevCompInfo();
			flag = "0";
		}*/
		Map<String, String> currNodeMap = revCompInfoService.selectCurrNodeKey(appId);
		String currNode = null;
		if(currNodeMap != null){
			currNode = currNodeMap.get("CURR_NODE");
		}
		BizInpApplC1 t = new BizInpApplC1();
		String isPad = appId.substring(6,7);
		t.setAppId(appId);
		BizInpApplC1 bizInpApplC1 = applyService.get(t);
		Date crtTime = bizInpApplC1.getCrtTime();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		crtTime = f.parse(f.format(crtTime));
		Date datestr1 =  f.parse(datestr);
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", appId);
		KeyfiledMarchinfo kfmi = revCompInfoService.selectKeyfiledMarchinfoByAppId(map);
		// 人行首张贷记卡发卡日期大于六个月
		// 人行信用提示信息表--一代人行用的表
		//String mon = revCompInfoService.selectMonByAppId(appId);
		//二代人行（目前使用的表）：信贷交易提示信息--PBOC_CREDIT_TRANSACTION_DEL，人行个人基本信息表--opas_pboc_personal_info;
		Map<String,Object> months = revCompInfoService.selectMonthsByAppId(appId);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
		int monret = 0;
		if (months != null && !"".equals(months)) {
			//首笔业务发放月份  --专门查人行首张贷记卡发卡日期
			String firstYwGrantMonth = (String) months.get("FIRST_YW_GRANT_MONTH");
			//报告时间
			String reportTime = (String) months.get("REPORT_TIME");
			Date d1;
			Date d2;
			try {
				if(StringUtil.isNotBlank(firstYwGrantMonth) && StringUtil.isNotBlank(reportTime)){
					d1 = sdf.parse(firstYwGrantMonth);
					d2 = sdf.parse(reportTime);
					monret = getMonth(d1, d2);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (monret > 6) {
			revCompInfo.setPeobankFirstcard6months("1");//人行首张贷记卡发卡日期大于6个月
			revCompInfo.setApplyWorkinfoTure("1");//申请人工作信息真实
		} else {
			revCompInfo.setPeobankFirstcard6months("");
		}
		// 社保公积金信息近6个月缴交正常
		/*Map<String, String> blazeMap = revCompInfoService.selectGjjMonth(appId);
		String c1Coname = bizInpApplC1.getC1Coname();// 申请表信息单位地址
		if (blazeMap != null) {
			String gjjPayStatus = blazeMap.get("RETURN_GJJ_STATUS");// 公积金缴交状态
			String gjjEndMonth = blazeMap.get("RETURN_GJJ_TOMONTH");// 公积金缴至月份
			String sbCurrPayStatus = blazeMap.get("RETURN_SOC_SAVE_TYPE");// 社保缴交状态
			String sbCurrPaycompany = blazeMap.get("RETURN_SOC_UNIT");// 社保缴交单位
			String subAppId = "20" + appId.substring(0, 2) + "." + appId.substring(2, 4);
			SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy.MM");
			Date applyDate = sdfFormat.parse(subAppId);
			int month = 10;
			if (gjjEndMonth != null) {
				Date gjjEndDate = sdfFormat.parse(gjjEndMonth);
				month = getMonth(gjjEndDate, applyDate);
			}
			Boolean sflag = false;
			if (c1Coname != null && sbCurrPaycompany != null && c1Coname.equals(sbCurrPaycompany)) {
				sflag = true;
			}
			if ("1".equals(gjjPayStatus) || month <= 6 || "1".equals(sbCurrPayStatus) || sflag) {
				revCompInfo.setSbandgjj6monthsStatus("1");
			}
		}*/

		// 查询第三方信息手机和第三方信息单址(ivs的)
		Map<String, String> ivsMap = revCompInfoService.selectIvsMap(appId);
		if (ivsMap != null) {
			String codeAddrEnglish = ivsMap.get("CODEADDRENGLISH");
			String codePhoenEnglish = ivsMap.get("CODEPHOENENGLISH");
			String[] addrArray = { "ADDR_Match_Recency_Bad", "ADDR_Match_Trust_Self_Recency_Bad",
					"ADDR_Match_Recency_Good", "ADDR_Match_Trust_Self_Recency_Good", "ADDR_Match_Trust_Other" };
			String[] phoneArray = { "PHONE_Match_Sharing_Good", "PHONE_Match_Trust_Self_Sharing_Good",
					"PHONE_Match_Recency_Bad", "PHONE_Match_Trust_Self_Recency_Bad", "PHONE_Match_Recency_Good",
					"PHONE_Match_Trust_Self_Recency_Good", "PHONE_Match_Reliability_Good",
					"PHONE_Match_Trust_Self_Reliability_Good", "PHONE_Match_Trust_Other" };
			for (int i = 0; i < addrArray.length; i++) {
				if (addrArray[i].equals(codeAddrEnglish)) {
					revCompInfo.setThirddataAddr("1");// 第三方信息单址
					revCompInfo.setApplyWorkTure("1");//申请单位真实
				}
			}
			for (int j = 0; j < phoneArray.length; j++) {
				if (phoneArray[j].equals(codePhoenEnglish)) {
					revCompInfo.setThirdPhone("1");// 第三方信息手机
					revCompInfo.setApplyerTrue("1");// 申请人真实
				}
			}
		}

		// 经营状态非异常且企业网法人姓名=申请表申请人姓名
		Map<String, String> EetFrnameMap = qiYeService.queryEetFrnameByAppId(appId);
		if (EetFrnameMap != null && EetFrnameMap.get("FRNAME") != null && EetFrnameMap.get("FRNAME") != "") {
			String entStatus = EetFrnameMap.get("ENTSTATUS");
			String frName = EetFrnameMap.get("FRNAME");
			String c1CName = bizInpApplC1.getC1Cname();
			if (("在营（开业）".equals(entStatus) || "开业".equals(entStatus) || "存续".equals(entStatus)
					|| "正常".equals(entStatus) || "正常执业".equals(entStatus) || "正常经营".equals(entStatus))
					&& frName.equals(c1CName)) {
				revCompInfo.setBusinetworkLegal("1");//工商网法人
			}
		}

		String matchResult = null;
		if(kfmi != null){
			matchResult = kfmi.getMarchResult();
		}		
		String company1 = "";// 人行单位名称
		String compAddr1 = "";// 人行单位地址
		String ccompPhone1 = "";// 人行单位电话
		String cellPhoneCheck = "";// 人行手机
		String billAddressCheck = "";// 人行账单地址
		String officwebName = "";// 官方单名
		String officwebAddr = "";// 官方单址
		String officwebTel = "";// 官方单电
		String padPositionAddrFlag = "";//Pad定位地址标识
		String houseAddressCheck = "";// 人行住宅地址是否与申请表家庭地址是否匹配
		String currentStatus = ""; // 当前参保状态
		String lastUnitName = "";
		String lastUnitName1 = "";
		String lastUnitName2 = "";
		String lastUnitName3 = "";
		String lastUnitName4 = "";		
		String lastUnitName5 = "";  //温州公积金区域数据
		String lastUnitName6 = "";	//银川公积金区域数据
		String lastUnitName7 = "";

		String status = "";
		String status1 = "";
		String status2= "";
		String status3= "";
		String status4= "";
		String status5= "";
		String status6= "";
		String status7= "";
		
		String insuredStatus = "";//厦门参保状态
		/*------------------------------------------------------------------------------------------*/
		//深圳区域数据当前参保状态为正常时
		Map<String, String> sisz2Map = revCompInfoService.selectCurrentStatus(appId);
		if(sisz2Map != null){
			currentStatus = sisz2Map.get("CURRENT_STATUS");// 当前参保状态
		}
		//厦门易联众
		Map<String, String> repDataMap = revCompInfoService.selectInsuredStatus(appId);
		if(repDataMap != null){
			insuredStatus = repDataMap.get("INSURED_STATUS");//厦门参保状态
		}
		/*------------------------------------------------------------------------------------------*/
		if (matchResult != null && !"".equals(matchResult)) {
			JSONArray ja = JSONArray.fromObject(matchResult);
			List<RiskCheck> listrisk = (List) JSONArray.toCollection(ja, RiskCheck.class);
			for (RiskCheck r : listrisk) {
				/*------------------------------------------------------------------------------------------*/
				//鹏元
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("PY_PCR_CRS_CRT_SISZ2_HI5Y".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("PY_PCR_CRS_CRT_SISZ2_HI5Y".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//易联众
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("YLZ_REP_DATA".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("YLZ_REP_DATA".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//人行
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("OPAS_PBOC_PUBLIC_ACCFUND".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName2 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("OPAS_PBOC_PUBLIC_ACCFUND".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status2 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//杭州区域数据
				if ((("DEPTNAME").equals(r.getFieldKey()))
						&& ("HZ_GJJXX".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName3 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("HZ_GJJXX".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status3 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//厦门公积金区域数据
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("XM_GJJ_JCXX".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName4 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("XM_GJJ_JCXX".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status4 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}

				//宁波社保
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("NB_BASE".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName7 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("NB_BASE".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status7 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}

				//温州公积金区域数据
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("WZ_GR_GJJZL".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName5 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("WZ_GR_GJJZL".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status5 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				//银川公积金区域数据
				if ((("COMPANY").equals(r.getFieldKey()))
						&& ("YC_INFO".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					lastUnitName6 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("STATUS").equals(r.getFieldKey()))
						&& ("YC_INFO".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					status6 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}

				/*------------------------------------------------------------------------------------------*/
				
				if ((("COMPANY").equals(r.getFieldKey())) && ("OPAS_PBOC_PROFESSION_INFO".equals(r.getTableName()))
						&& ("3".equals(r.getRiskType()))) {
					company1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("COMP_ADDR").equals(r.getFieldKey()) && ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					compAddr1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				/*if ((("C_COMP_PHONE").equals(r.getFieldKey()) && ("OPAS_PBOC_PERSONAL_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					ccompPhone1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if ((("CELL_PHONE").equals(r.getFieldKey()) && ("OPAS_PBOC_PERSONAL_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					cellPhoneCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}*/
								
				if ((("UNIT_PHONE").equals(r.getFieldKey())
						&& ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					ccompPhone1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
		
				if ((("PHONE_NUM").equals(r.getFieldKey())
						&& ("PBOC_PHONE_NUMBER_DETAIL").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					cellPhoneCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}

				// 新加官方单名+单址 官方单名+单电的判断 2018-05-22
				if (("ENTNAME".equals(r.getFieldKey()) && ("TRD_QYHY_INFO_DATA").equals(r.getTableName())
						&& "2".equals(r.getRiskType()))) {
					officwebName = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if (("DOM".equals(r.getFieldKey()) && ("TRD_QYHY_INFO_DATA").equals(r.getTableName())
						&& "2".equals(r.getRiskType()))) {
					officwebAddr = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				if (("TEL".equals(r.getFieldKey()) && ("TRD_QYHY_INFO_DATA").equals(r.getTableName())
						&& "2".equals(r.getRiskType()))) {
					officwebTel = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				
				//新增  Pad定位地址的判断 2019-11-11
				if (("BASE_STATION".equals(r.getFieldKey()) && ("CCARD_APP_PAD_ADDITION").equals(r.getTableName())
						&& "2".equals(r.getRiskType()))) {
					padPositionAddrFlag = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}

				if ("B".equals(bizInpApplC1.getC4Cycadd1())) {
					// 如果主卡寄往的地址是单位地址,查询人行个人职业信息表获得人行单位地址proessionInfoList
					if ((("COMP_ADDR").equals(r.getFieldKey()) && ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
							&& "3".equals(r.getRiskType()))) {
						billAddressCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
					}
				} else if ("H".equals(bizInpApplC1.getC4Cycadd1())) {
					// 如果主卡寄往的地址是家庭地址
					if ((("RESIDENT_ADDR").equals(r.getFieldKey())
							&& ("OPAS_PBOC_RESIDENCE_INFO").equals(r.getTableName()) && "3".equals(r.getRiskType()))) {
						billAddressCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
					}
				} else {
					billAddressCheck = "";
				}
				// 根据申请表住宅地址和人行住宅地址是否匹配
				if ((("RESIDENT_ADDR").equals(r.getFieldKey()) && ("OPAS_PBOC_RESIDENCE_INFO").equals(r.getTableName())
						&& "3".equals(r.getRiskType()))) {
					houseAddressCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
				}
				/*------------------------------------------------------------------------------------------*/
				
				/*if ("0".equals(flag) && ("1".equals(status)|| "1".equals(status1))||"1".equals(status2)) {
					revCompInfo.setSbandgjj6monthsStatus(status);
				}
				if ("0".equals(flag) && ("1".equals(lastUnitName)|| "1".equals(lastUnitName1))||"1".equals(lastUnitName2)) {
					revCompInfo.setSbandgjj6monthsStatus(status);
				}*/
				
				if ( "1".equals(status)|| "1".equals(status1)||"1".equals(status2)||"1".equals(status3)
						||"1".equals(status4)||"1".equals(status5)||"1".equals(status6)||"1".equals(status7)) {
					revCompInfo.setSbandgjj6monthsStatus("1");//社保或公积金信息近6个月缴交状态正常
				}
				if ("1".equals(lastUnitName)|| "1".equals(lastUnitName1)||"1".equals(lastUnitName3)
						||"1".equals(lastUnitName4)||"1".equals(lastUnitName5)||"1".equals(lastUnitName6)||"1".equals(lastUnitName7)) {
					revCompInfo.setRegionalDataListName("1");//区域数据单名
				}
				
				/*if ("1".equals(lastUnitName)|| "1".equals(lastUnitName1)||"1".equals(lastUnitName2)) {
					revCompInfo.setRegionalDataListName("1");//区域数据单名
				}*/
				
				/*if ("1".equals(status)) {
					revCompInfo.setSbandgjj6monthsStatus(status);
				}
				if ("1".equals(status1)) {
					revCompInfo.setSbandgjj6monthsStatus(status);
				}
				if ("1".equals(status2)) {
					revCompInfo.setSbandgjj6monthsStatus(status);
				}*/
				/*if ("1".equals(company1) && "正常参保".equals(currentStatus.trim()) && "参保缴费".equals(insuredStatus.trim())) {
					revCompInfo.setRegionalDataListName(company1);
				}*/
				/*if ("1".equals(company1)) {
					revCompInfo.setRegionalDataListName(company1);//区域数据单名
				}*/
				/*------------------------------------------------------------------------------------------*/
				if ("1".equals(company1)) {
					//申请单位真实模块下 ：单位名称是否一致 取值 PEOBANK_COMPANY_NAME 人行单位名称，页面上用name="company" 赋值
					revCompInfo.setCompany(company1);
					//revCompInfo.setPeobankCompanyName("1");//人行单位名称
					if ("1".equals(compAddr1) || "1".equals(ccompPhone1)) {
						revCompInfo.setApplyWorkTure("1");//申请单位真实
					}
				}
				if ("1".equals(compAddr1)) {
					revCompInfo.setPeobankCompanyNameAddr(compAddr1);//人行单位地址
				}
				if ("1".equals(ccompPhone1)) {
					revCompInfo.setPeobankCompanyNameTel(ccompPhone1);//人行单位电话
				}
				if ("1".equals(cellPhoneCheck)) {
					revCompInfo.setPeobankPhone(cellPhoneCheck);//人行手机
					revCompInfo.setApplyerTrue("1");// 申请人真实
				}
				if ("1".equals(billAddressCheck)) {
					revCompInfo.setOrderAddress(billAddressCheck);// 账单地址真实  账单地址
				}
				if ("1".equals(officwebName) && "1".equals(officwebAddr)) {
					revCompInfo.setOfficwebNameAddr("1");//官网单名+单址
				}
				if ("1".equals(officwebName) && "1".equals(officwebTel)) {
					revCompInfo.setOfficwebNameTel("1");//官网单名+单电
				}
				
				//Pad定位地址     控制 比对页面Pad定位地址的勾选
				if ("1".equals(padPositionAddrFlag)) {
					revCompInfo.setPadPositionAddr("1");//Pad定位地址
					revCompInfo.setApplyWorkTure("1");//申请单位真实
				}
				
				// 住宅地址
				if ("1".equals(houseAddressCheck)) {
					revCompInfo.setHomeAddress("1");//住宅地址
				}
				
				/*if("A".equals(isPad) && "01".equals(currNode) && crtTime.compareTo(datestr1) == 1){
			        revCompInfo.setApplyInputfull("1");//抄录文字完整有效
			        revCompInfo.setSignFull("1");//签名完整有效
			        revCompInfo.setCertifiPeriodfull("1");//身份证有效期填写完整有效
				}*/
				
				//ETC迅卡审批功能优化：审批系统录入环节“抄录文字完整有效、签名完整有效、身份证有效期填写完整有效”三个勾选项需根据PAD系统后送结果进行自动勾选判断。
				String isSignatureIntegrityValid = null;//申请人签名及抄录文字是否完整有效
				String isIdDateIntegrityValid = null;//申请人身份证明文件完成有效
				//根据appid查询  PAD进件追加信息表中的两个字段值 ： 申请人签名及抄录文字是否完整有效、申请人身份证明文件完成有效
				Map<String, String> signatureValidAndIdDateValid = etcPadService.querySignatureAndIdByAppId(appId);
				
				if(signatureValidAndIdDateValid!=null){
					isSignatureIntegrityValid = signatureValidAndIdDateValid.get("isSignatureIntegrityValid");
					isIdDateIntegrityValid = signatureValidAndIdDateValid.get("isIdDateIntegrityValid");
				}
				
				if("A".equals(isPad) && "01".equals(currNode)){
					
					if("1".equals(isSignatureIntegrityValid)){
						revCompInfo.setApplyInputfull("1");//抄录文字完整有效
			        	revCompInfo.setSignFull("1");//签名完整有效
					}
					
					if("1".equals(isIdDateIntegrityValid)){
						revCompInfo.setCertifiPeriodfull("1");//身份证有效期填写完整有效
					}
					
				}
			}
		}
		return revCompInfo;
	}

	public void queryInfoCompare(Context ctx) throws CoreException, ParseException, InterruptedException {
		Map<String, Object> map = ctx.getDataMap();
		String currNode = null;
		String appId = map.get("appId").toString().split("&")[0];
		String isPad = appId.substring(6,7);
		Map<String, String> currNodeMap = revCompInfoService.selectCurrNodeKey(appId);
		if(currNodeMap != null){
			 currNode = currNodeMap.get("CURR_NODE");
		}
		RevCompInfo revCompInfo = null;
		String flag = "1";
		try {
			revCompInfo = revCompInfoService.selectByPrimaryKey(appId);
		} catch (CoreException e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
		if (revCompInfo == null) {
			revCompInfo = new RevCompInfo();
			flag = "0";
		}
		// 根据前台传过来的appId,获取到申请件信息的寄往地址
		BizInpApplC1 t = new BizInpApplC1();
		t.setAppId(appId);
		BizInpApplC1 bizInpApplC1 = applyService.get(t);
		Date crtTime = bizInpApplC1.getCrtTime();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		crtTime = f.parse(f.format(crtTime));
		Date datestr1 =  f.parse(datestr);
		Map<String, String> map2 = new HashMap<String, String>();
		map2.put("appId", appId);
		map2.put("FQZ_REQUESTTYPE", "2");// 三方比对请求批次是 “2”
		// 人行单名单址等
		KeyfiledMarchinfo kfmi = revCompInfoService.selectKeyfiledMarchinfoByAppId(map2);
		if ("0".equals(flag)) {
			// 人行首张贷记卡发卡日期大于六个月
			// 人行信用提示信息表--一代人行用的表
			//String mon = revCompInfoService.selectMonByAppId(appId);
			//二代人行（目前使用的表）：信贷交易提示信息--PBOC_CREDIT_TRANSACTION_DEL，人行个人基本信息表--opas_pboc_personal_info;
			Map<String,Object> months = revCompInfoService.selectMonthsByAppId(appId);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM");
			int monret = 0;
			if (months != null && !"".equals(months)) {
				//首笔业务发放月份  --专门查人行首张贷记卡发卡日期
				String firstYwGrantMonth = (String) months.get("FIRST_YW_GRANT_MONTH");
				//报告时间
				String reportTime = (String) months.get("REPORT_TIME");
				Date d1;
				Date d2;
				try {
					/*if(firstYwGrantMonth != null && !firstYwGrantMonth.trim().isEmpty() && !"".equals(firstYwGrantMonth) 
							&& reportTime != null && !reportTime.trim().isEmpty() && !"".equals(reportTime)){
						d1 = sdf.parse(firstYwGrantMonth);
						d2 = sdf.parse(reportTime);
						monret = getMonth(d1, d2);
					}*/
					if(StringUtil.isNotBlank(firstYwGrantMonth) && StringUtil.isNotBlank(reportTime)){
						d1 = sdf.parse(firstYwGrantMonth);
						d2 = sdf.parse(reportTime);
						monret = getMonth(d1, d2);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			if (monret > 6) {
				revCompInfo.setPeobankFirstcard6months("1");//人行首张贷记卡发卡日期大于6个月
				revCompInfo.setApplyWorkinfoTure("1");//申请人工作信息真实
			} else {
				revCompInfo.setPeobankFirstcard6months("");
			}

			// 社保公积金信息近6个月缴交正常
			/*Map<String, String> blazeMap = revCompInfoService.selectGjjMonth(appId);
			String c1Coname = bizInpApplC1.getC1Coname();// 申请表信息单位地址
			if (blazeMap != null) {
				String gjjPayStatus = blazeMap.get("RETURN_GJJ_STATUS");// 公积金缴交状态
				String gjjEndMonth = blazeMap.get("RETURN_GJJ_TOMONTH");// 公积金缴至月份
				String sbCurrPayStatus = blazeMap.get("RETURN_SOC_SAVE_TYPE");// 社保缴交状态
				String sbCurrPaycompany = blazeMap.get("RETURN_SOC_UNIT");// 社保缴交单位
				String subAppId = "20" + appId.substring(0, 2) + "." + appId.substring(2, 4);
				SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy.MM");
				Date applyDate = sdfFormat.parse(subAppId);
				int month = 10;
				if (gjjEndMonth != null) {
					Date gjjEndDate = sdfFormat.parse(gjjEndMonth);
					month = getMonth(gjjEndDate, applyDate);
				}
				Boolean sflag = false;
				if (c1Coname != null && sbCurrPaycompany != null && c1Coname.equals(sbCurrPaycompany)) {
					sflag = true;
				}
				if ("1".equals(gjjPayStatus) || month <= 6 || "1".equals(sbCurrPayStatus) || sflag) {
					revCompInfo.setSbandgjj6monthsStatus("1");
				}
			}*/

			// 查询第三方信息手机和第三方信息单址(ivs的)
			Map<String, String> ivsMap = revCompInfoService.selectIvsMap(appId);
			if (ivsMap != null) {
				String codeAddrEnglish = ivsMap.get("CODEADDRENGLISH");
				String codePhoenEnglish = ivsMap.get("CODEPHOENENGLISH");
				String[] addrArray = { "ADDR_Match_Recency_Bad", "ADDR_Match_Trust_Self_Recency_Bad",
						"ADDR_Match_Recency_Good", "ADDR_Match_Trust_Self_Recency_Good", "ADDR_Match_Trust_Other" };
				String[] phoneArray = { "PHONE_Match_Sharing_Good", "PHONE_Match_Trust_Self_Sharing_Good",
						"PHONE_Match_Recency_Bad", "PHONE_Match_Trust_Self_Recency_Bad", "PHONE_Match_Recency_Good",
						"PHONE_Match_Trust_Self_Recency_Good", "PHONE_Match_Reliability_Good",
						"PHONE_Match_Trust_Self_Reliability_Good", "PHONE_Match_Trust_Other" };
				for (int i = 0; i < addrArray.length; i++) {
					if (addrArray[i].equals(codeAddrEnglish)) {
						revCompInfo.setThirddataAddr("1");// 第三方信息单址
						revCompInfo.setApplyWorkTure("1");//申请单位真实
					}
				}
				for (int j = 0; j < phoneArray.length; j++) {
					if (phoneArray[j].equals(codePhoenEnglish)) {
						revCompInfo.setThirdPhone("1");// 第三方信息手机
						revCompInfo.setApplyerTrue("1");// 申请人真实
					}
				}
			}

			// 经营状态非异常且企业网法人姓名=申请表申请人姓名
			Map<String, String> EetFrnameMap = qiYeService.queryEetFrnameByAppId(appId);
			if (EetFrnameMap != null && EetFrnameMap.get("FRNAME") != null && EetFrnameMap.get("FRNAME") != "") {
				String entStatus = EetFrnameMap.get("ENTSTATUS");
				String frName = EetFrnameMap.get("FRNAME");
				String c1CName = bizInpApplC1.getC1Cname();
				if (("在营（开业）".equals(entStatus) || "开业".equals(entStatus) || "存续".equals(entStatus)
						|| "正常".equals(entStatus) || "正常执业".equals(entStatus) || "正常经营".equals(entStatus))
						&& frName.equals(c1CName)) {
					revCompInfo.setBusinetworkLegal("1");//工商网法人
				}
			}

			// 增强公安
			// Map<String,String> policeMap =
			// revCompInfoService.selectPoliceMapByAppId(appId);
			// if(policeMap!=null&&!"".equals(policeMap)){
			// String
			// identityNoCheckRst=policeMap.get("identityNoCheckRst")!=null?policeMap.get("identityNoCheckRst"):"";
			// String
			// nameCheckRst=policeMap.get("nameCheckRst")!=null?policeMap.get("nameCheckRst"):"";
			// if("一致".equals(identityNoCheckRst)&&"一致".equals(nameCheckRst)){
			// revCompInfo.setPubPolice("1");
			// revCompInfo.setApplyWorkinfoTure("1");
			// }
			// }
		}

		// 查appId的人行信息 单位名称 单位地址 单位电话 住宅地址
		List<Map<String, String>> allPbocApppid = pbocService.queryPbocCompanyAndAddressByAppId(appId);
		//一代人行 单位电话，手机号码
		//List<Map<String, String>> compPhonePbocApppid = pbocService.queryCompPhoneByAppId(appId);
		//单位电话（二代人行）
		List<Map<String, String>> allCompPhonePbocApppid = pbocService.queryAllCompPhoneByAppId(appId);
		//手机号码（二代人行）
		List<Map<String, String>> allCellPhonePbocApppid = pbocService.queryAllCellphoneByAppId(appId);
		List<Map<String, String>> hmaddPhonePbocApppid = pbocService.queryResidentAddrByAppId(appId);
		StringBuffer company = new StringBuffer();
		StringBuffer compAddr = new StringBuffer();
		StringBuffer ccompPhone = new StringBuffer();
		StringBuffer cellPhone = new StringBuffer();
		if (allPbocApppid != null && allPbocApppid.size() > 0) {
			for (int i = 0; i < allPbocApppid.size(); i++) {
				company.append((i == allPbocApppid.size() - 1) ? allPbocApppid.get(i).get("company")
						: allPbocApppid.get(i).get("company") + "<br />");
				compAddr.append((i == allPbocApppid.size() - 1) ? allPbocApppid.get(i).get("compAddr")
						: allPbocApppid.get(i).get("compAddr") + "<br />");
			}
		}
		//人行信息  单位电话 、手机
		/*if (compPhonePbocApppid != null && compPhonePbocApppid.size() > 0) {
			for (int i = 0; i < compPhonePbocApppid.size(); i++) {
				ccompPhone.append((i == compPhonePbocApppid.size() - 1) ? compPhonePbocApppid.get(i).get("ccompPhone")
						: compPhonePbocApppid.get(i).get("ccompPhone") + "<br />");
				cellPhone.append((i == compPhonePbocApppid.size() - 1) ? compPhonePbocApppid.get(i).get("cellPhone")
						: compPhonePbocApppid.get(i).get("cellPhone") + "<br />");
			}
		}*/
		
		//人行信息  单位电话
		if (allCompPhonePbocApppid != null && allCompPhonePbocApppid.size() > 0) {
			for (int i = 0; i < allCompPhonePbocApppid.size(); i++) {
				ccompPhone.append((i == allCompPhonePbocApppid.size() - 1) ? allCompPhonePbocApppid.get(i).get("ccompPhone")
						: allCompPhonePbocApppid.get(i).get("ccompPhone") + "<br />");
			}
		}
		/*if (allCompPhonePbocApppid != null && allCompPhonePbocApppid.size() > 0) {
			for (int i = 0; i < allCompPhonePbocApppid.size(); i++) {
				ccompPhone.append((i == allCompPhonePbocApppid.size() - 1) ? 
						(allCompPhonePbocApppid.get(i).get("ccompPhone") == null ? "空" : allCompPhonePbocApppid.get(i).get("ccompPhone"))
						: (allCompPhonePbocApppid.get(i).get("ccompPhone") == null ? "空<br />":allCompPhonePbocApppid.get(i).get("ccompPhone")+ "<br />"));
			}
		}*/
		//人行信息 手机
		if (allCellPhonePbocApppid != null && allCellPhonePbocApppid.size() > 0) {
			for (int i = 0; i < allCellPhonePbocApppid.size(); i++) {
				cellPhone.append((i == allCellPhonePbocApppid.size() - 1) ? allCellPhonePbocApppid.get(i).get("cellPhone")
						: allCellPhonePbocApppid.get(i).get("cellPhone") + "<br />");
			}
		}
		
		// 公司地址C1_COADD1  申请表信息 单位地址
		StringBuffer sbaddrCom = new StringBuffer();
		sbaddrCom.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Coadd1()));
		sbaddrCom.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Coadd2()));
		sbaddrCom.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Coadd3()));
		sbaddrCom.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Coadd4()));

		// 申请人住宅地址  申请表信息 住宅地址
		StringBuffer houseAddressC1 = new StringBuffer();
		houseAddressC1.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd1()));
		houseAddressC1.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd2()));
		houseAddressC1.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd3()));
		houseAddressC1.append(StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd4()));
		// 申请人人行家庭住址
		StringBuffer houseAddress = new StringBuffer();
		if (hmaddPhonePbocApppid != null && hmaddPhonePbocApppid.size() > 0) {
			for (int i = 0; i < hmaddPhonePbocApppid.size(); i++) {
				houseAddress
						.append((i == hmaddPhonePbocApppid.size() - 1) ? hmaddPhonePbocApppid.get(i).get("residentAddr")
								: hmaddPhonePbocApppid.get(i).get("residentAddr") + "<br />");

			}
		}

		// 公司名称  申请表信息 单位名称
		String cocname = StringUtil.trimToEmpty(bizInpApplC1.getC1Coname());
		// 公司电话  申请表信息 单位电话
		String C1_COTEL = StringUtil.trimToEmpty(bizInpApplC1.getC1Cotel());
		// 申请人手机  申请表信息  手机
		String cell_phone = StringUtil.trimToEmpty(bizInpApplC1.getC1Mobile());
		// 匹配结果
		String company1 = "";// 人行单位名称
		String compAddr1 = "";// 人行单位地址
		String ccompPhone1 = "";// 人行单位电话
		String cellPhoneCheck = "";// 人行手机
		String billAddressCheck = "";// 人行账单地址
		String officwebName = "";// 官方单名
		String officwebAddr = "";// 官方单址
		String officwebTel = "";// 官方单电
		String padPositionAddrFlag = "";//Pad定位地址标识
		String houseAddressCheck = "";// 人行住宅地址是否与申请表家庭地址是否匹配
		String currentStatus = ""; // 当前参保状态
		//start （深圳）鹏元，（厦门）易联众，人行，杭州区域数据,厦门公积金区域数据  模糊匹配用到的变量
		String lastUnitName = "";  	//鹏远
		String lastUnitName1 = ""; 	//易联众
		String lastUnitName2 = ""; 	//人行
		String lastUnitName3 = "";	//杭州区域数据
		String lastUnitName4 = "";	//厦门公积金区域数据
		String lastUnitName5 = "";  //温州公积金区域数据
		String lastUnitName6 = "";	//银川公积金区域数据
		String lastUnitName7 = "";//宁波社保

		String status = "";
		String status1 = "";
		String status2= "";
		String status3= "";
		String status4= "";
		String status5= "";
		String status6= "";
		String status7= "";
		
		//end （深圳）鹏元，（厦门）易联众，人行，杭州区域数据，厦门公积金区域数据  模糊匹配用到的变量
		String insuredStatus = "";//厦门参保状态
		//String latestPaymentDate = "";//厦门最近一次交费日期
		//String isOnJob = "";//是否在职
		//Date applyDate = null;
		//Map<String, String> payStatusMap = null;
		//int month = 10;
		//int month1 = 10;
		if ("0".equals(flag)) {
			if (kfmi != null) {
				/*------------------------------------------------------------------------------------------*/
				//深圳区域数据当前参保状态为正常时
				Map<String, String> sisz2Map = revCompInfoService.selectCurrentStatus(appId);
				/*String subAppId = "20" + appId.substring(0, 2) + "." + appId.substring(2, 4);
				SimpleDateFormat sdfFormat = new SimpleDateFormat("yyyy.MM");
				applyDate = sdfFormat.parse(subAppId);*/
				if(sisz2Map != null){
					currentStatus = sisz2Map.get("CURRENT_STATUS");// 当前参保状态
					/*String infoDate = sisz2Map.get("INFO_DATE");// 信息获取日期
					if (infoDate != null || infoDate != "") {
						infoDate = infoDate.substring(0, 4) + "." + infoDate.substring(4, 6);
						Date infoDate1 = sdfFormat.parse(infoDate);
						month = getMonth(infoDate1, applyDate);
					}*/
				}
				//厦门易联众
				Map<String, String> repDataMap = revCompInfoService.selectInsuredStatus(appId);
				if(repDataMap != null){
					insuredStatus = repDataMap.get("INSURED_STATUS");//厦门参保状态
					/*latestPaymentDate = repDataMap.get("LATEST_PAYMENT_DATE");//厦门最近一次交费日期
					isOnJob = repDataMap.get("IS_ON_JOB");//是否在职
					if(latestPaymentDate.length() == 6){
						latestPaymentDate = latestPaymentDate.substring(0, 4) + "." + latestPaymentDate.substring(4, 6);
						Date latestPaymentDate1 = sdfFormat.parse(latestPaymentDate);
						month1 = getMonth(latestPaymentDate1, applyDate);
					}*/
				}
				//同时
				//payStatusMap = revCompInfoService.selectPayStatus(appId);
				/*------------------------------------------------------------------------------------------*/
				String matchResult = kfmi.getMarchResult();
				if (matchResult != null && !"".equals(matchResult)) {
					JSONArray ja = JSONArray.fromObject(matchResult);
					List<RiskCheck> listrisk = (List) JSONArray.toCollection(ja, RiskCheck.class);
					
					for (RiskCheck r : listrisk) {
						/*------------------------------------------------------------------------------------------*/
						//鹏元
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("PY_PCR_CRS_CRT_SISZ2_HI5Y".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							lastUnitName = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("STATUS").equals(r.getFieldKey()))
								&& ("PY_PCR_CRS_CRT_SISZ2_HI5Y".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							status = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						//易联众
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("YLZ_REP_DATA".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							lastUnitName1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("STATUS").equals(r.getFieldKey()))
								&& ("YLZ_REP_DATA".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							status1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						//人行
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("OPAS_PBOC_PUBLIC_ACCFUND".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							lastUnitName2 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("STATUS").equals(r.getFieldKey()))
								&& ("OPAS_PBOC_PUBLIC_ACCFUND".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							status2 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						//杭州区域数据
						if ((("DEPTNAME").equals(r.getFieldKey()))
								&& ("HZ_GJJXX".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							lastUnitName3 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("STATUS").equals(r.getFieldKey()))
								&& ("HZ_GJJXX".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							status3 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						//厦门公积金区域数据
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("XM_GJJ_JCXX".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							lastUnitName4 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("STATUS").equals(r.getFieldKey()))
								&& ("XM_GJJ_JCXX".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							status4 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}

						//宁波社保
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("NB_BASE".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							lastUnitName7 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("STATUS").equals(r.getFieldKey()))
								&& ("NB_BASE".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							status7 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}

						//温州公积金区域数据
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("WZ_GR_GJJZL".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							lastUnitName5 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("STATUS").equals(r.getFieldKey()))
								&& ("WZ_GR_GJJZL".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							status5 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						//银川公积金区域数据
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("YC_INFO".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							lastUnitName6 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("STATUS").equals(r.getFieldKey()))
								&& ("YC_INFO".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							status6 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}

						/*------------------------------------------------------------------------------------------*/
						if ((("COMPANY").equals(r.getFieldKey()))
								&& ("OPAS_PBOC_PROFESSION_INFO".equals(r.getTableName()))
								&& ("3".equals(r.getRiskType()))) {
							company1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if ((("COMP_ADDR").equals(r.getFieldKey())
								&& ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							compAddr1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						/*if ((("C_COMP_PHONE").equals(r.getFieldKey())
								&& ("OPAS_PBOC_PERSONAL_INFO").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							ccompPhone1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}*/
						if ((("UNIT_PHONE").equals(r.getFieldKey())
								&& ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							ccompPhone1 = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						/*if ((("CELL_PHONE").equals(r.getFieldKey())
								&& ("OPAS_PBOC_PERSONAL_INFO").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							cellPhoneCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}*/
						if ((("PHONE_NUM").equals(r.getFieldKey())
								&& ("PBOC_PHONE_NUMBER_DETAIL").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							cellPhoneCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}

						// 新加官方单名+单址 官方单名+单电的判断 2018-05-22
						if (("ENTNAME".equals(r.getFieldKey()) && ("TRD_QYHY_INFO_DATA").equals(r.getTableName())
								&& "2".equals(r.getRiskType()))) {
							officwebName = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if (("DOM".equals(r.getFieldKey()) && ("TRD_QYHY_INFO_DATA").equals(r.getTableName())
								&& "2".equals(r.getRiskType()))) {
							officwebAddr = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						if (("TEL".equals(r.getFieldKey()) && ("TRD_QYHY_INFO_DATA").equals(r.getTableName())
								&& "2".equals(r.getRiskType()))) {
							officwebTel = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}

						//新增  Pad定位地址的判断 2019-11-11
						if (("BASE_STATION".equals(r.getFieldKey()) && ("CCARD_APP_PAD_ADDITION").equals(r.getTableName())
								&& "2".equals(r.getRiskType()))) {
							padPositionAddrFlag = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}
						
						
						if ("B".equals(bizInpApplC1.getC4Cycadd1())) {
							// 如果主卡寄往的地址是单位地址,查询人行个人职业信息表获得人行单位地址proessionInfoList
							if ((("COMP_ADDR").equals(r.getFieldKey())
									&& ("OPAS_PBOC_PROFESSION_INFO").equals(r.getTableName())
									&& "3".equals(r.getRiskType()))) {
								billAddressCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
							}
						} else if ("H".equals(bizInpApplC1.getC4Cycadd1())) {
							// 如果主卡寄往的地址是家庭地址
							if ((("RESIDENT_ADDR").equals(r.getFieldKey())
									&& ("OPAS_PBOC_RESIDENCE_INFO").equals(r.getTableName())
									&& "3".equals(r.getRiskType()))) {
								billAddressCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
							}
						} else {
							billAddressCheck = "";
						}
						// 根据申请表住宅地址和人行住宅地址是否匹配
						if ((("RESIDENT_ADDR").equals(r.getFieldKey())
								&& ("OPAS_PBOC_RESIDENCE_INFO").equals(r.getTableName())
								&& "3".equals(r.getRiskType()))) {
							houseAddressCheck = "1".equals(r.getRiskResult()) ? r.getRiskResult() : "";
						}

					}
				}
			}
		}

		StringBuffer billAddress = new StringBuffer();
		String billAddressC1 = "";
		if ("B".equals(bizInpApplC1.getC4Cycadd1())) {
			// 如果主卡寄往的地址是单位地址,查询人行个人职业信息表获得人行单位地址proessionInfoList
			if (allPbocApppid != null && allPbocApppid.size() > 0) {
				for (int i = 0; i < allPbocApppid.size(); i++) {
					billAddress.append((i == allPbocApppid.size() - 1) ? allPbocApppid.get(i).get("compAddr")
							: allPbocApppid.get(i).get("compAddr") + "<br />");
				}
			}
			billAddressC1 = sbaddrCom.toString();
		} else if ("H".equals(bizInpApplC1.getC4Cycadd1())) {
			// 如果主卡寄往的地址是家庭地址 hmaddPhonePbocApppid
			if (hmaddPhonePbocApppid != null && hmaddPhonePbocApppid.size() > 0) {
				for (int i = 0; i < hmaddPhonePbocApppid.size(); i++) {
					billAddress.append(
							(i == hmaddPhonePbocApppid.size() - 1) ? hmaddPhonePbocApppid.get(i).get("residentAddr")
									: hmaddPhonePbocApppid.get(i).get("residentAddr") + "<br />");
				}
			}
			billAddressC1 = StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd1())
					+ StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd2())
					+ StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd3())
					+ StringUtil.trimToEmpty(bizInpApplC1.getC1Hmadd4());
		} else {
			billAddressC1 = "";
		}

		Map<String, String> rest = new HashMap<>();
		/*------------------------------------------------------------------------------------------*/

		if ("0".equals(flag) && ("1".equals(status)|| "1".equals(status1)||"1".equals(status2)||"1".equals(status3)
				||"1".equals(status4)||"1".equals(status5)||"1".equals(status6)||"1".equals(status7))) {
			revCompInfo.setSbandgjj6monthsStatus("1");//社保或公积金信息近6个月缴交状态正常
		}

		if ("0".equals(flag) && ("1".equals(lastUnitName)|| "1".equals(lastUnitName1)||"1".equals(lastUnitName3)
				||"1".equals(lastUnitName4)||"1".equals(lastUnitName5)||"1".equals(lastUnitName6)||"1".equals(lastUnitName7))) {
			revCompInfo.setRegionalDataListName("1");//区域数据单名
		}
		
		/*if ("0".equals(flag) && ("1".equals(lastUnitName)|| "1".equals(lastUnitName1)||"1".equals(lastUnitName2))) {
			revCompInfo.setRegionalDataListName("1");//区域数据单名
		}*/
		
		/*
		if ("1".equals(lastUnitName) && "0".equals(flag) && month <= 6 && "正常参保".equals(currentStatus.trim())) {
			revCompInfo.setSbandgjj6monthsStatus(lastUnitName);
		}
		if ("1".equals(lastUnitName1) && "0".equals(flag) && "参保缴费".equals(insuredStatus.trim()) && month1 <= 6 && "是".equals(isOnJob.trim())) {
			revCompInfo.setSbandgjj6monthsStatus(lastUnitName1);
		}
		if(payStatusMap != null){
			if(month1 <= 6 && "0".equals(flag) && "001|缴交".equals(payStatusMap.get("PAY_STATUS").trim()) && "1".equals(lastUnitName2)){
				revCompInfo.setSbandgjj6monthsStatus(lastUnitName2);
			}
		}*/
		/*if ("1".equals(company1) && "0".equals(flag) && "正常参保".equals(currentStatus.trim()) && "参保缴费".equals(insuredStatus.trim())) {
			revCompInfo.setRegionalDataListName(company1);
		}*/
		
		/*if ("1".equals(company1)) {
			revCompInfo.setRegionalDataListName(company1);//区域数据单名
		}*/
		
		/*------------------------------------------------------------------------------------------*/
		if ("1".equals(company1) && "0".equals(flag)) {
			//申请单位真实模块下 ：单位名称是否一致 取值 PEOBANK_COMPANY_NAME 人行单位名称，页面上用name="company" 赋值
			revCompInfo.setCompany(company1);
			revCompInfo.setPeobankCompanyName("1");//人行单位名称
			if ("1".equals(compAddr1) || "1".equals(ccompPhone1)) {
				revCompInfo.setApplyWorkTure("1");//申请单位真实
			}
		}
		if ("1".equals(compAddr1) && "0".equals(flag)) {
			revCompInfo.setPeobankCompanyNameAddr(compAddr1);//人行单位地址
		}
		if ("1".equals(ccompPhone1) && "0".equals(flag)) {
			revCompInfo.setPeobankCompanyNameTel(ccompPhone1);//人行单位电话
		}
		if ("1".equals(cellPhoneCheck) && "0".equals(flag)) {
			revCompInfo.setPeobankPhone(cellPhoneCheck);//人行手机
			revCompInfo.setApplyerTrue("1");// 申请人真实
		}
		if ("1".equals(billAddressCheck) && "0".equals(flag)) {
			revCompInfo.setOrderAddress(billAddressCheck);// 账单地址真实   账单地址
		}
		if ("1".equals(officwebName) && "1".equals(officwebAddr) && "0".equals(flag)) {
			revCompInfo.setOfficwebNameAddr("1");//官网单名+单址
		}
		if ("1".equals(officwebName) && "1".equals(officwebTel) && "0".equals(flag)) {
			revCompInfo.setOfficwebNameTel("1");//官网单名+单电
		}
		
		//Pad定位地址     控制 比对页面Pad定位地址的勾选
		if ("1".equals(padPositionAddrFlag) && "0".equals(flag)) {
			revCompInfo.setPadPositionAddr("1");//Pad定位地址
			revCompInfo.setApplyWorkTure("1");//申请单位真实	
		}
		
		
		// 住宅地址
		if ("1".equals(houseAddressCheck) && "0".equals(flag)) {
			revCompInfo.setHomeAddress("1");//住宅地址
		}
		
		 /* if("A".equals(isPad) && "01".equals(currNode) && crtTime.compareTo(datestr1) == 1){
    	revCompInfo.setApplyInputfull("1");//抄录文字完整有效
    	revCompInfo.setSignFull("1");//签名完整有效
    	revCompInfo.setCertifiPeriodfull("1");//身份证有效期填写完整有效
	}*/
		
		//ETC迅卡审批功能优化：审批系统录入环节“抄录文字完整有效、签名完整有效、身份证有效期填写完整有效”三个勾选项需根据PAD系统后送结果进行自动勾选判断。
		String isSignatureIntegrityValid = null;//申请人签名及抄录文字是否完整有效
		String isIdDateIntegrityValid = null;//申请人身份证明文件完成有效
		//根据appid查询  PAD进件追加信息表中的两个字段值 ： 申请人签名及抄录文字是否完整有效、申请人身份证明文件完成有效
		Map<String, String> signatureValidAndIdDateValid = etcPadService.querySignatureAndIdByAppId(appId);
		
		if(signatureValidAndIdDateValid!=null){
			isSignatureIntegrityValid = signatureValidAndIdDateValid.get("isSignatureIntegrityValid");
			isIdDateIntegrityValid = signatureValidAndIdDateValid.get("isIdDateIntegrityValid");
		}
		if("A".equals(isPad) && "01".equals(currNode)){
			
			if("1".equals(isSignatureIntegrityValid)){
				revCompInfo.setApplyInputfull("1");//抄录文字完整有效
	        	revCompInfo.setSignFull("1");//签名完整有效
			}
			
			if("1".equals(isIdDateIntegrityValid)){
				revCompInfo.setCertifiPeriodfull("1");//身份证有效期填写完整有效
			}
			
		}
		
		
        //申请单位真实    根据以下字段被选中而选中
        String peobankCompanyName = revCompInfo.getPeobankCompanyName();//单位名称   人行单位名称
        String peobankCompanyNameAddr = revCompInfo.getPeobankCompanyNameAddr();//单位地址 人行单位地址
        String peobankCompanyNameTel = revCompInfo.getPeobankCompanyNameTel();//单位电话
        String company114NameAddr = revCompInfo.getCompany114NameAddr();//114单名+单址
        String company114NameTel = revCompInfo.getCompany114NameTel();//114单名+单电
        String pyuanCompanyNameAddr = revCompInfo.getPyuanCompanyNameAddr();//鹏元单名+单址
        String pyuanCompanyNameTel = revCompInfo.getPyuanCompanyNameTel();//鹏元单名+单电
        String officwebNameAddr = revCompInfo.getOfficwebNameAddr();//官方单名+单址
        String officwebNameTel = revCompInfo.getOfficwebNameTel();//官方单名+单电
        String noOfficwebNameAddr = revCompInfo.getNoOfficwebNameAddr();//非官方单名+单址
        String noOfficwebNameTel = revCompInfo.getNoOfficwebNameTel();//非官方单名+单电
        String otherThirdNameAddr = revCompInfo.getOtherThirdNameAddr();//其他第三方信息单名+单址
        String thirddataAddr = revCompInfo.getThirddataAddr();//第三方信息单址
        String regionalDataListName = revCompInfo.getRegionalDataListName();//区域数据单名
        String padPositionAddr = revCompInfo.getPadPositionAddr();//Pad定位地址
        if(("1".equals(peobankCompanyName) && ("1".equals(peobankCompanyNameAddr) || "1".equals(peobankCompanyNameTel))) 
        		|| "1".equals(company114NameAddr) || "1".equals(company114NameTel) 
        		|| "1".equals(pyuanCompanyNameAddr) || "1".equals(pyuanCompanyNameTel)
        		|| "1".equals(officwebNameAddr) || "1".equals(officwebNameTel)
        		|| "1".equals(noOfficwebNameAddr) || "1".equals(noOfficwebNameTel)
        		|| "1".equals(otherThirdNameAddr) || "1".equals(thirddataAddr)
        		|| "1".equals(regionalDataListName) || "1".equals(padPositionAddr)
        	){
        	revCompInfo.setApplyWorkTure("1");//申请单位真实
        }
        
        //申请人工作信息真实   根据以下字段被选中而选中
        String sbandgjj6monthsStatus = revCompInfo.getSbandgjj6monthsStatus();//社保公积金信息近6个月缴交正常
        String businetworkLegal = revCompInfo.getBusinetworkLegal();//工商网法人
        String pubPolice = revCompInfo.getPubPolice();//增强公安
        String businetworkMinor = revCompInfo.getBusinetworkMinor();//工商网股东
        String peobankFirstcard6months = revCompInfo.getPeobankFirstcard6months();//人行首张贷记卡发卡日期 > 6个月
        String insurance = revCompInfo.getInsurance();//保监会
        String others = revCompInfo.getOthers();//其他
        if("1".equals(sbandgjj6monthsStatus) || "1".equals(businetworkLegal) || "1".equals(pubPolice)
        		|| "1".equals(businetworkMinor) || "1".equals(peobankFirstcard6months) || "1".equals(insurance)
        		|| "1".equals(others)
        		){
        	revCompInfo.setApplyWorkinfoTure("1");//申请人工作信息真实
        }
        
        //申请人真实   根据以下字段被选中而选中
        String peobankPhone = revCompInfo.getPeobankPhone();//人行手机
        String thirdPhone = revCompInfo.getThirdPhone();//第三方信息手机
        String otherthirdInfo = revCompInfo.getOtherthirdInfo();//其他第三方信息
        if("1".equals(peobankPhone) || "1".equals(thirdPhone) || "1".equals(otherthirdInfo )){
        	revCompInfo.setApplyerTrue("1");//申请人真实
        }
       
		

		rest.put("companyPboc", StringUtil.trim(company.toString()));// 人行单名
		rest.put("compAddrPboc", StringUtil.trim(compAddr.toString()));// 人行单址
		rest.put("ccompPhonePboc", StringUtil.trim(ccompPhone.toString()));// 人行单电
		rest.put("compAddrC1", sbaddrCom.toString());// 公司单址
		rest.put("companyC1", cocname);// 公司单名
		rest.put("ccompPhoneC1", C1_COTEL);// 公司单电
		rest.put("cellPhoneC1", cell_phone);// 申请表手机
		rest.put("cellPhonePboc", StringUtil.trim(cellPhone.toString()));// 人行手机
		rest.put("billAddress", billAddress.toString());// 人行账单地址
		rest.put("billAddressC1", billAddressC1);// 申请表账单地址(根据orderAddress是否为1显示账单地址是否匹配)
		rest.put("houseAddress", StringUtil.trim(houseAddress.toString()));// 申请人人行家庭住址  住宅地址
		rest.put("houseAddressC1", StringUtil.trim(houseAddressC1.toString()));// 申请人住宅地址  申请表信息 住宅地址
		ctx.setData("revCompInfo", JSON.toJSONString(revCompInfo));
		ctx.setData("rest", JSON.toJSONString(rest));
		ctx.setData("flag", flag);
	}

	// 计算两个日期相差的月份
	private static int getMonth(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(d1);
		c2.setTime(d2);
		int m1 = c1.get(Calendar.MONTH);
		int m2 = c2.get(Calendar.MONTH);
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int ret = (y2 - y1) * 12 + m2 - m1;
		return ret;
	}

}
