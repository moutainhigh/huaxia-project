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
 * 身份证号与黑名单库比较
 * 
 * @author qianxiwen
 *
 */
public class Step1_Field_Opas_Identity_RiskList implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String,Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String,Object> params = new HashMap<String, Object>();
		String Idnbr = null;
		// 主卡
		if ("1".equals(appType)) {
			Idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
		}

		if ("2".equals(appType)) {
			Idnbr = StringUtil.trimToEmpty(apply_Info2.getC2Idnbr());
		}
		// 主卡
		if ("3".equals(appType)) {
			Idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
		}
		params.put("Idnbr", Idnbr);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String Idnbr = (String) initParams.get("Idnbr");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		RiskCheckResult check = new RiskCheckResult("APPLY_CERTIFI_NO", "OPAS_IDENTITY_RISKLIST",
				CacheConsts.RISK_TYPE_BLACK);
		check.setApplyType(appType);
		Map<String, String> mapidentity = null;
		if(!"".equals(StringUtil.trimToEmpty(Idnbr))){
			 mapidentity = riskCheckService.Query_OPAS_IDENTITY_RISKLIST(Idnbr);
		}
		if (mapidentity != null && mapidentity.size() > 0) {
			check.setBaseDataValue(mapidentity.get("certifiNo"));
			check.setPriKeyValue(mapidentity.get("autoId"));
			check.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
		}
		listriskcheck.add(check);

		return listriskcheck;

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
