package com.huaxia.opas.cache.service.riskcheck.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

/**
 * 与风险信息库的信息进行匹配-单位名称、推广姓名、推广工号、推广机构代码、单位电话、身份证
 * 
 * @author jiangming.yang
 *
 * 2019/01/08
 * 新增身份证号码匹配
 * @author dong.an
 * 
 */
public class Step2_Field_Opas_Inner_RiskInfo_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		String companyName = "";
		// 推广姓名
		String c4Abname = "";
		// 推广工号
		String c4Abuser = "";
		String c5Abcode = "";
		String c1Cotel = "";
		String c1Idnbr = "";

		if ("1".equals(appType)) {
			companyName = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
			c4Abname = StringUtil.trimToEmpty(apply_Info1.getC4Abname());
			c4Abuser = StringUtil.trimToEmpty(apply_Info1.getC4Abuser());
			c5Abcode = StringUtil.trimToEmpty(apply_Info1.getC5Abcode());
			c1Cotel = StringUtil.trimToEmpty(apply_Info1.getC1Cotel());
			c1Idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
		} else if ("2".equals(appType)) {

		} else if ("3".equals(appType)) {
			companyName = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
			c4Abname = StringUtil.trimToEmpty(apply_Info1.getC4Abname());
			c4Abuser = StringUtil.trimToEmpty(apply_Info1.getC4Abuser());
			c5Abcode = StringUtil.trimToEmpty(apply_Info1.getC5Abcode());
			c1Cotel = StringUtil.trimToEmpty(apply_Info1.getC1Cotel());
			c1Idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
		}
		params.put("companyName", companyName);
		params.put("c4Abname", c4Abname);
		params.put("c4Abuser", c4Abuser);
		params.put("c5Abcode", c5Abcode);
		params.put("c1Cotel", c1Cotel);
		params.put("c1Idnbr", c1Idnbr);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String companyName = (String) initParams.get("companyName");
		String c4Abname = (String) initParams.get("c4Abname");
		String c4Abuser = (String) initParams.get("c4Abuser");
		String c5Abcode = (String) initParams.get("c5Abcode");
		String c1Cotel = (String) initParams.get("c1Cotel");
		String c1Idnbr = (String) initParams.get("c1Idnbr");
		
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<Map<String, String>> result = riskCheckService.Query_OPAS_INNER_RISKINFO_RISKLIST();
		RiskCheckResult checkResult = new RiskCheckResult("COMPANY_NAME", "OPAS_INNER_RISKINFO_LIST",CacheConsts.RISK_TYPE_TEAM);
		RiskCheckResult checkResult1 = new RiskCheckResult("SPREAD_PER", "OPAS_INNER_RISKINFO_LIST",CacheConsts.RISK_TYPE_TEAM);
		RiskCheckResult checkResult2 = new RiskCheckResult("SPREAD_NO", "OPAS_INNER_RISKINFO_LIST",CacheConsts.RISK_TYPE_TEAM);
		RiskCheckResult checkResult3 = new RiskCheckResult("SPREAD_ORG", "OPAS_INNER_RISKINFO_LIST",CacheConsts.RISK_TYPE_TEAM);
		RiskCheckResult checkResult4 = new RiskCheckResult("COMPANY_TEL", "OPAS_INNER_RISKINFO_LIST",CacheConsts.RISK_TYPE_TEAM);
		RiskCheckResult checkResult5 = new RiskCheckResult("CERTIFI_NO", "OPAS_INNER_RISKINFO_LIST",CacheConsts.RISK_TYPE_TEAM);
		RiskCheckResult checkResult6 = new RiskCheckResult("COMPANY_LEVEL", "OPAS_INNER_RISKINFO_LIST",CacheConsts.RISK_TYPE_TEAM);
		
		
		checkResult.setApplyType(appType);
		checkResult1.setApplyType(appType);
		checkResult2.setApplyType(appType);
		checkResult3.setApplyType(appType);
		checkResult4.setApplyType(appType);
		checkResult5.setApplyType(appType);
		checkResult6.setApplyType(appType);
		// 判断
		int flag = 0;
		if (result != null && result.size() > 0) {
			for (Map<String, String> infoRisk : result) {
				if (companyName != null && companyName != "") {
					if (lueneService.getMachResult(companyName, infoRisk.get("companyName"), "")) {
						// 匹配成功
						checkResult.setBaseDataValue(companyName);
						checkResult.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						checkResult.setPriKeyValue(infoRisk.get("autoId"));
						//break;
						flag++;
					}
				}
				if (c4Abname != null && c4Abname != "") {
					if (c4Abname.equals(infoRisk.get("spreadPer"))) {
						// 匹配成功
						checkResult1.setBaseDataValue(c4Abname);
						checkResult1.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						checkResult1.setPriKeyValue(infoRisk.get("autoId"));
						//break;
						flag++;
					}
				}
				if (c4Abuser != null && c4Abuser != "") {
					if (c4Abuser.equals(infoRisk.get("spreadNo"))) {
						// 匹配成功
						checkResult2.setBaseDataValue(c4Abuser);
						checkResult2.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						checkResult2.setPriKeyValue(infoRisk.get("autoId"));
						//break;
						flag++;
					}
				}
				if (c5Abcode != null && c5Abcode != "") {
					if (c5Abcode.equals(infoRisk.get("spreadOrg"))) {
						// 匹配成功
						checkResult3.setBaseDataValue(c5Abcode);
						checkResult3.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						checkResult3.setPriKeyValue(infoRisk.get("autoId"));
						//break;
						flag++;
					}
				}
				
				if (c1Cotel != null && c1Cotel != "") {
					if (lueneService.getMachResult(c1Cotel, infoRisk.get("companyTel"), "1")) {
						// 匹配成功  c1Cotel.equals(infoRisk.get("companyTel"))
						checkResult4.setBaseDataValue(c1Cotel);
						checkResult4.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						checkResult4.setPriKeyValue(infoRisk.get("autoId"));
						//break;
						flag++;
					}
				}
				if (c1Idnbr != null && c1Idnbr != "") {
					if (c1Idnbr.equals(infoRisk.get("certifiNo"))) {
						// 匹配成功
						checkResult5.setBaseDataValue(c1Idnbr);
						checkResult5.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						checkResult5.setPriKeyValue(infoRisk.get("autoId"));
						//break;
						flag++;
					}
				}
				
				if(flag >0){
					//加一个公司等级  给系统决策页面用 PriKeyValue给companyLevel的值方便系统决策页面使用
					if (!"".equals(infoRisk.get("companyLevel"))) {
						checkResult6.setBaseDataValue(infoRisk.get("companyLevel"));
						checkResult6.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						checkResult6.setPriKeyValue(infoRisk.get("companyLevel"));
					}
					break;
				}
			}
		}
		listriskcheck.add(checkResult);
		listriskcheck.add(checkResult1);
		listriskcheck.add(checkResult2);
		listriskcheck.add(checkResult3);
		listriskcheck.add(checkResult4);
		listriskcheck.add(checkResult5);
		listriskcheck.add(checkResult6);
		return listriskcheck;
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {

	}

}
