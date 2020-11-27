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
import com.huaxia.opas.domain.sysparm.TargetcompanyList;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

public class Step2_Field_Opas_TargetCompany_List implements RiskFieldMachService {

	@Resource(name = "lueneServiceImplTest")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		String CompanyName = "";
		// 主附
		if ("1".equals(appType)) {
			CompanyName = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
		}
		// 附
		if ("2".equals(appType)) {

		}
		// 主
		if ("3".equals(appType)) {
			CompanyName = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
		}
		params.put("CompanyName", CompanyName);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String CompanyName = (String) initParams.get("CompanyName");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<TargetcompanyList> targetcompanyList = riskCheckService.Query_OPAS_TARGETCOMPANY_LIST();
		RiskCheckResult check_COMPANY_NAME = new RiskCheckResult("COMPANY_NAME", "OPAS_TARGETCOMPANY_LIST",
				CacheConsts.RISK_TYPE_TEAM);
		check_COMPANY_NAME.setApplyType(appType);
		if (targetcompanyList != null && targetcompanyList.size() > 0) {
			for (int i = 0; i < targetcompanyList.size(); i++) {
				TargetcompanyList tmpTargetcompanyList = targetcompanyList.get(i);
				if (lueneService.getMachResult(CompanyName, tmpTargetcompanyList.getCompanyName(), "")) {
					check_COMPANY_NAME.setPriKeyValue(tmpTargetcompanyList.getAutoId());
					check_COMPANY_NAME.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_COMPANY_NAME.setBaseDataValue(tmpTargetcompanyList.getCompanyName());
					break;
				}

			}
		}
		listriskcheck.add(check_COMPANY_NAME);
		return listriskcheck;
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
