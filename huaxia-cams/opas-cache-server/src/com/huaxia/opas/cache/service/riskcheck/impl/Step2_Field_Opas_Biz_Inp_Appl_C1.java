package com.huaxia.opas.cache.service.riskcheck.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.LueneService;
import com.huaxia.opas.cache.service.riskcheck.RiskFieldMachService;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

public class Step2_Field_Opas_Biz_Inp_Appl_C1 implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	/*
	 * //重复申请检查 身份证 private String Idnbr=new String(""); //关联性检查-手机
	 * 申请表手机号与所有历史进件申请人手机号进行匹配 private String c1mobile=new String("");
	 */
	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 申请表直系联系人手机号与所有历史进件的直系联系人手机号进行匹配
		List<String> LIST_CON_ALL_TEL = new ArrayList<String>();
		// 申请表家庭地址与所有历史进件家庭地址进行匹配
		String hmaddr = new String("");
		Map<String, String> strMap = new HashMap<String, String>();
		String subAppId = appId.substring(appId.length() - 1, appId.length());
		if ("2".equals(subAppId)) {
			subAppId = appId.substring(0, appId.length() - 1) + "1";
		} else if ("1".equals(subAppId)) {
			subAppId = appId.substring(0, appId.length() - 1) + "2";
		}
		strMap.put("appId", appId);
		strMap.put("appId2", subAppId);
		if ("1".equals(appType)) {
			strMap.put("Idnbr", StringUtil.trimToEmpty(apply_Info1.getC1Idnbr()));
			strMap.put("c1mobile", StringUtil.trimToEmpty(apply_Info1.getC1Mobile()));
			strMap.put("xmobile1", StringUtil.trimToEmpty(apply_Info1.getC1Xmobil1()));
			strMap.put("xmobile2", StringUtil.trimToEmpty(apply_Info1.getC1Xmobil2()));
			strMap.put("c1Remobil", StringUtil.trimToEmpty(apply_Info1.getC1Remobil()));
			StringBuffer sb = new StringBuffer();
			sb.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd1()));
			sb.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd2()));
			sb.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd3()));
			sb.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd4()));
			hmaddr = sb.toString();
			strMap.put("hmadd1", this.regex_match(hmaddr));
		}
		if ("2".equals(appType)) {

		}
		if ("3".equals(appType)) {
			strMap.put("Idnbr", StringUtil.trimToEmpty(apply_Info1.getC1Idnbr()));
			strMap.put("c1mobile", StringUtil.trimToEmpty(apply_Info1.getC1Mobile()));
			strMap.put("xmobile1", StringUtil.trimToEmpty(apply_Info1.getC1Xmobil1()));
			strMap.put("xmobile2", StringUtil.trimToEmpty(apply_Info1.getC1Xmobil2()));
			//strMap.put("hmadd1", this.regex_match(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd1())));
			//strMap.put("hmadd2", this.regex_match(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd2())));
			strMap.put("c1Remobil", StringUtil.trimToEmpty(apply_Info1.getC1Remobil()));
			StringBuffer sb = new StringBuffer();
			sb.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd1()));
			sb.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd2()));
			sb.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd3()));
			sb.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd4()));
			hmaddr = sb.toString();
			strMap.put("hmadd1", this.regex_match(hmaddr));
			//strMap.put("hmadd1", hmaddr);
		}
		params.put("LIST_CON_ALL_TEL", LIST_CON_ALL_TEL);
		params.put("hmaddr", hmaddr);
		params.put("strMap", strMap);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		List<String> LIST_CON_ALL_TEL = (List<String>) initParams.get("LIST_CON_ALL_TEL");
		String hmaddr = (String) initParams.get("hmaddr");
		Map<String, String> strMap = (Map<String, String>) initParams.get("strMap");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		boolean bool_HMADD = true;
		RiskCheckResult check_C1_IDNBR = new RiskCheckResult("C1_IDNBR", "OPAS_BIZ_INP_APPL_C1",CacheConsts.RISK_TYPE_TEAM);
		check_C1_IDNBR.setApplyType(appType);
		RiskCheckResult check_C1_MOBILE = new RiskCheckResult("C1_MOBILE", "OPAS_BIZ_INP_APPL_C1",CacheConsts.RISK_TYPE_TEAM);
		check_C1_MOBILE.setApplyType(appType);
		RiskCheckResult check_HMADD = new RiskCheckResult("HMADD", "OPAS_BIZ_INP_APPL_C1", CacheConsts.RISK_TYPE_TEAM);
		check_HMADD.setApplyType(appType);
		RiskCheckResult check_XMOBIL = new RiskCheckResult("XMOBIL", "OPAS_BIZ_INP_APPL_C1",CacheConsts.RISK_TYPE_TEAM);
		check_XMOBIL.setApplyType(appType);
		RiskCheckResult CYCADD = new RiskCheckResult("CYCADD", "OPAS_BIZ_INP_APPL_C1", CacheConsts.RISK_TYPE_TEAM);
		CYCADD.setApplyType(appType);
		listriskcheck.add(check_C1_IDNBR);
		listriskcheck.add(check_C1_MOBILE);
		listriskcheck.add(check_XMOBIL);
		listriskcheck.add(check_HMADD);
		listriskcheck.add(CYCADD);
		// 主附卡申请
		if ("1".equals(appType)) {
			List<RiskCheck_Apply_C1> C1_IDNBR_list = riskCheckService.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_IDNBR(strMap);
			List<RiskCheck_Apply_C1> C1_MOBILE_list = riskCheckService.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_MOBILE(strMap);
			List<RiskCheck_Apply_C1> XMOBIL_list = riskCheckService.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_XMOBIL1To2(strMap);
			List<RiskCheck_Apply_C1> HMADD_list = null;
			if (!"".equals(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd1()))
					|| !"".equals(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd2()))) {
				HMADD_list = riskCheckService.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_HMADD1To4(strMap);
			}
			if (C1_IDNBR_list != null && C1_IDNBR_list.size() >= 1) {
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				for (int i = 0; i < C1_IDNBR_list.size(); i++) {
					sb = (i == C1_IDNBR_list.size()-1)?sb.append(C1_IDNBR_list.get(i).getAppId()):sb.append(C1_IDNBR_list.get(i).getAppId()+"|");
					sb2 = (i == C1_IDNBR_list.size()-1)?sb2.append(C1_IDNBR_list.get(i).getC1Idnbr()):sb2.append(C1_IDNBR_list.get(i).getC1Idnbr()+"|");
				}
				check_C1_IDNBR.setPriKeyValue(sb.toString());
				check_C1_IDNBR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_C1_IDNBR.setBaseDataValue(sb2.toString());
			}
			if (C1_MOBILE_list != null && C1_MOBILE_list.size() >= 1) {
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				for (int i = 0; i < C1_MOBILE_list.size(); i++) {
					sb = (i == C1_MOBILE_list.size()-1)?sb.append(C1_MOBILE_list.get(i).getAppId()):sb.append(C1_MOBILE_list.get(i).getAppId()+"|");
					sb2 = (i == C1_MOBILE_list.size()-1)?sb2.append(C1_MOBILE_list.get(i).getC1Mobile()):sb2.append(C1_MOBILE_list.get(i).getC1Mobile()+"|");
				}
				check_C1_MOBILE.setPriKeyValue(sb.toString());
				check_C1_MOBILE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_C1_MOBILE.setBaseDataValue(sb2.toString());
			}
			if (XMOBIL_list != null && XMOBIL_list.size() >= 1) {
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				for (int i = 0; i < XMOBIL_list.size(); i++) {
					sb = (i == XMOBIL_list.size()-1)?sb.append(XMOBIL_list.get(i).getAppId()):sb.append(XMOBIL_list.get(i).getAppId()+"|");
					sb2 = (i == XMOBIL_list.size()-1)?sb2.append(XMOBIL_list.get(i).getC1Xmobil1() + "_" + XMOBIL_list.get(i).getC1Xmobil2()):sb2.append(XMOBIL_list.get(i).getC1Xmobil1() + "_" + XMOBIL_list.get(i).getC1Xmobil2()+"|");
				}
				check_XMOBIL.setPriKeyValue(sb.toString());
				check_XMOBIL.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_XMOBIL.setBaseDataValue(sb2.toString());
			}
			if(HMADD_list !=null && HMADD_list.size() > 0){
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				StringBuffer sb3 = new StringBuffer();
				StringBuffer sb4 = new StringBuffer();
				for (int i = 0; i < HMADD_list.size(); i++) {
					RiskCheck_Apply_C1 tmpaddress = HMADD_list.get(i);
					if (!"".equals(hmaddr)) {
						String strAddress = StringUtil.deleteWhiteSpace(tmpaddress.getC1Hmadd1());
						if (lueneService.getMachResult(hmaddr, strAddress, "")) {
							sb = (i == HMADD_list.size()-1)?sb.append(HMADD_list.get(i).getAppId()):sb.append(HMADD_list.get(i).getAppId()+"|");
							sb2 = (i == HMADD_list.size()-1)?sb2.append(strAddress):sb2.append(strAddress+"|");
							if ("H".equals(apply_Info1.getC4Cycadd1())) {// 邮寄地址是非单位地址
								sb3 = (i == HMADD_list.size()-1)?sb3.append(HMADD_list.get(i).getAppId()):sb3.append(HMADD_list.get(i).getAppId()+"|");
								sb4 = (i == HMADD_list.size()-1)?sb4.append(strAddress):sb4.append(strAddress+"|");
								CYCADD.setPriKeyValue(sb3.toString());
								CYCADD.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
								CYCADD.setBaseDataValue(sb4.toString());
							}
							check_HMADD.setPriKeyValue(sb.toString());
							check_HMADD.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
							check_HMADD.setBaseDataValue(sb2.toString());
						}
					} else {
						break;
					}
				}
			}
		}
		// 副卡申请
		if ("2".equals(appType)) {

		}
		// 主卡申请
		if ("3".equals(appType)) {

			List<RiskCheck_Apply_C1> C1_IDNBR_list = riskCheckService.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_IDNBR(strMap);
			List<RiskCheck_Apply_C1> C1_MOBILE_list = riskCheckService.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_MOBILE(strMap);
			List<RiskCheck_Apply_C1> XMOBIL_list = riskCheckService.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_XMOBIL1To2(strMap);
			List<RiskCheck_Apply_C1> HMADD_list = null;
			if (!"".equals(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd1()))
					|| !"".equals(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd2()))) {
				HMADD_list = riskCheckService.Query_OPAS_BIZ_INP_APPL_C1_BY_C1_HMADD1To4(strMap);
			}
			if (C1_IDNBR_list != null && C1_IDNBR_list.size() >= 1) {
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				for (int i = 0; i < C1_IDNBR_list.size(); i++) {
					sb = (i == C1_IDNBR_list.size()-1)?sb.append(C1_IDNBR_list.get(i).getAppId()):sb.append(C1_IDNBR_list.get(i).getAppId()+"|");
					sb2 = (i == C1_IDNBR_list.size()-1)?sb2.append(C1_IDNBR_list.get(i).getC1Idnbr()):sb2.append(C1_IDNBR_list.get(i).getC1Idnbr()+"|");
				}
				check_C1_IDNBR.setPriKeyValue(sb.toString());
				check_C1_IDNBR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_C1_IDNBR.setBaseDataValue(sb2.toString());
			}
			if (C1_MOBILE_list != null && C1_MOBILE_list.size() >= 1) {
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				for (int i = 0; i < C1_MOBILE_list.size(); i++) {
					sb = (i == C1_MOBILE_list.size()-1)?sb.append(C1_MOBILE_list.get(i).getAppId()):sb.append(C1_MOBILE_list.get(i).getAppId()+"|");
					sb2 = (i == C1_MOBILE_list.size()-1)?sb2.append(C1_MOBILE_list.get(i).getC1Mobile()):sb2.append(C1_MOBILE_list.get(i).getC1Mobile()+"|");
				}
				check_C1_MOBILE.setPriKeyValue(sb.toString());
				check_C1_MOBILE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_C1_MOBILE.setBaseDataValue(sb2.toString());
			}
			if (XMOBIL_list != null && XMOBIL_list.size() >= 1) {
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				for (int i = 0; i < XMOBIL_list.size(); i++) {
					sb = (i == XMOBIL_list.size()-1)?sb.append(XMOBIL_list.get(i).getAppId()):sb.append(XMOBIL_list.get(i).getAppId()+"|");
					sb2 = (i == XMOBIL_list.size()-1)?sb2.append(XMOBIL_list.get(i).getC1Xmobil1() + "_" + XMOBIL_list.get(i).getC1Xmobil2()):sb2.append(XMOBIL_list.get(i).getC1Xmobil1() + "_" + XMOBIL_list.get(i).getC1Xmobil2()+"|");
				}
				check_XMOBIL.setPriKeyValue(sb.toString());
				check_XMOBIL.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_XMOBIL.setBaseDataValue(sb2.toString());
			}
			if(HMADD_list !=null && HMADD_list.size() > 0){
				StringBuffer sb = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
				StringBuffer sb3 = new StringBuffer();
				StringBuffer sb4 = new StringBuffer();
				for (int i = 0; i < HMADD_list.size(); i++) {
					RiskCheck_Apply_C1 tmpaddress = HMADD_list.get(i);
					if (!"".equals(hmaddr)) {
						String strAddress = StringUtil.deleteWhiteSpace(tmpaddress.getC1Hmadd1());
						if (lueneService.getMachResult(hmaddr, strAddress, "")) {
							sb = (i == HMADD_list.size()-1)?sb.append(HMADD_list.get(i).getAppId()):sb.append(HMADD_list.get(i).getAppId()+"|");
							sb2 = (i == HMADD_list.size()-1)?sb2.append(strAddress):sb2.append(strAddress+"|");
							if ("H".equals(apply_Info1.getC4Cycadd1())) {// 邮寄地址是非单位地址
								sb3 = (i == HMADD_list.size()-1)?sb3.append(HMADD_list.get(i).getAppId()):sb3.append(HMADD_list.get(i).getAppId()+"|");
								sb4 = (i == HMADD_list.size()-1)?sb4.append(strAddress):sb4.append(strAddress+"|");
								CYCADD.setPriKeyValue(sb3.toString());
								CYCADD.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
								CYCADD.setBaseDataValue(sb4.toString());
							}
							check_HMADD.setPriKeyValue(sb.toString());
							check_HMADD.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
							check_HMADD.setBaseDataValue(sb2.toString());
						}
					} else {
						break;
					}
				}
			}
		}
		LIST_CON_ALL_TEL.clear();
		strMap.clear();
		return listriskcheck;
	}

	private String regex_match(String str) {
		String result = "";
		String regex = "[`~!@#$%^&*()+=|{}':;',[-]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？-]";
		Pattern p1 = Pattern.compile(regex);
		Matcher matcher = p1.matcher(str);
		result = matcher.replaceAll("").trim();
		return result;
	}

	public RiskCheckService getRiskCheckService() {
		return riskCheckService;
	}

	public void setRiskCheckService(RiskCheckService riskCheckService) {
		this.riskCheckService = riskCheckService;
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {

	}

}
