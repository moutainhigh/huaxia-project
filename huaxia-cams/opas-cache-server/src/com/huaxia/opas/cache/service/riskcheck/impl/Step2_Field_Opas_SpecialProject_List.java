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
import com.huaxia.opas.domain.sysparm.SpecialprojectList;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

public class Step2_Field_Opas_SpecialProject_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		String projectCode = "";
		// 主副
		if ("1".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info1.getC4Apbatch());
		}
		// 附卡
		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info1.getC4Apbatch());
		}
		params.put("projectCode", projectCode);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String projectCode = (String) initParams.get("projectCode");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<SpecialprojectList> specialprojectList = riskCheckService.Query_OPAS_SPECIALPROJECT_LIST(projectCode);
		RiskCheckResult check_PROJECT_CODE = new RiskCheckResult("PROJECT_CODE", "OPAS_SPECIALPROJECT_LIST",
				CacheConsts.RISK_TYPE_TEAM);
		check_PROJECT_CODE.setApplyType(appType);
		if (specialprojectList != null && specialprojectList.size() > 0) {
			for (int i = 0; i < specialprojectList.size(); i++) {
				SpecialprojectList tmpSpecialprojectList = specialprojectList.get(i);
				check_PROJECT_CODE.setPriKeyValue(tmpSpecialprojectList.getAutoId());
				check_PROJECT_CODE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_PROJECT_CODE.setBaseDataValue(tmpSpecialprojectList.getProjectCode());
				break;
			}
		}
		listriskcheck.add(check_PROJECT_CODE);
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
