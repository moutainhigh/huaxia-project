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
import com.huaxia.opas.domain.riskcheck.CompanyRisk;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 库2：单位类风险名单库 申请人申请表“单位名称”与库中“单位名称”一致
 * 
 * @author qianxiwen
 *
 */
public class Step1_Field_Opas_Company_RiskList implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String,Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String,Object> params = new HashMap<String, Object>();
		// 申请人申请表“单位名称”与库中“单位名称”一致
		List<String> STR_APPLY_MOBILE_ID = new ArrayList<String>();
		if ("1".equals(appType)) {// 主附卡
			//String c1cname = this.regex_match(StringUtil.trimToEmpty(apply_Info1.getC1Coname()));
			//String c2cname = this.regex_match(StringUtil.trimToEmpty(apply_Info1.getC1Fconame()));
			String c1cname = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
			String c2cname = StringUtil.trimToEmpty(apply_Info1.getC1Fconame());
			if (!"".equals(c1cname)) {
				STR_APPLY_MOBILE_ID.add(c1cname);// 现单位
			}
			if (!"".equals(c2cname)) {
				STR_APPLY_MOBILE_ID.add(c2cname);// 前公司
			}
		} else if ("2".equals(appType)) {// 附卡

		} else if ("3".equals(appType)) {// 主卡
//			String c1cname = this.regex_match(StringUtil.trimToEmpty(apply_Info1.getC1Coname()));
//			String c2cname = this.regex_match(StringUtil.trimToEmpty(apply_Info1.getC1Fconame()));
			String c1cname = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
			String c2cname = StringUtil.trimToEmpty(apply_Info1.getC1Fconame());
			if (!"".equals(c1cname)) {
				STR_APPLY_MOBILE_ID.add(c1cname);// 现单位
			}
			if (!"".equals(c2cname)) {
				STR_APPLY_MOBILE_ID.add(c2cname);// 前公司
			}
		}
		params.put("STR_APPLY_MOBILE_ID", STR_APPLY_MOBILE_ID);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		List<String> STR_APPLY_MOBILE_ID = (List<String>) initParams.get("STR_APPLY_MOBILE_ID");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<CompanyRisk> listcompanyName = null;
		if (STR_APPLY_MOBILE_ID != null && STR_APPLY_MOBILE_ID.size() > 0) {
			listcompanyName = riskCheckService.Query_OPAS_COMPANY_RISKLIST(STR_APPLY_MOBILE_ID);
		}
		RiskCheckResult check = new RiskCheckResult();
		check.setFieldKey("APPLY_COMPANY_NAME");
		check.setTableName("OPAS_COMPANY_RISKLIST");
		check.setRiskType(CacheConsts.RISK_TYPE_BLACK);
		check.setApplyType(appType);
		if (listcompanyName != null && listcompanyName.size() > 0 && STR_APPLY_MOBILE_ID != null
				&& STR_APPLY_MOBILE_ID.size() > 0) {
			for (CompanyRisk companyRisk : listcompanyName) {
				for (String companyName : STR_APPLY_MOBILE_ID) {
					// 匹配成功
					/*if (lueneService.getMachResult(companyName, companyRisk.getCompanyName(), "")) {
						check.setBaseDataValue(companyRisk.getCompanyName());
						check.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						check.setPriKeyValue(companyRisk.getAutoId());
						break;
					}*/
					if (companyName.equals(companyRisk.getCompanyName())) {
						check.setBaseDataValue(companyRisk.getCompanyName());
						check.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						check.setPriKeyValue(companyRisk.getAutoId());
						break;
					}
				}
			}
		}

		listriskcheck.add(check);

		STR_APPLY_MOBILE_ID.clear();
		return listriskcheck;

	}
	
	private  String regex_match(String str) {
		String result = "";
		String regex = "[`~!@#$%^&*()+=|{}':;',[-]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p1 = Pattern.compile(regex);
		Matcher matcher = p1.matcher(str);
		result = matcher.replaceAll("").trim();
		return result;
	}
	
	public LueneService getLueneService() {
		return lueneService;
	}

	public void setLueneService(LueneService lueneService) {
		this.lueneService = lueneService;
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
