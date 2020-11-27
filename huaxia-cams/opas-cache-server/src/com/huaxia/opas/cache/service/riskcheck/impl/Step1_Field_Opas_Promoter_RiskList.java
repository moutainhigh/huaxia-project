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
 * 申请人是否命中推广员风险名单库
 * 
 * @author qianxiwen
 *
 */
public class Step1_Field_Opas_Promoter_RiskList implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String,Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String,Object> params = new HashMap<String, Object>();
		String Idnbr = "";
		String c4Abuser = "";
		// 主卡
		if ("1".equals(appType)) {
			Idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
			c4Abuser = StringUtil.trimToEmpty(apply_Info1.getC4Abuser());
		}

		if ("2".equals(appType)) {
			Idnbr = StringUtil.trimToEmpty(apply_Info2.getC2Idnbr());
		}
		// 主卡
		if ("3".equals(appType)) {
			Idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
			c4Abuser = StringUtil.trimToEmpty(apply_Info1.getC4Abuser());
		}
		params.put("Idnbr", Idnbr);
		params.put("c4Abuser", c4Abuser);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String Idnbr = (String) initParams.get("Idnbr");
		String c4Abuser = (String) initParams.get("c4Abuser");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		// 主附申请
		RiskCheckResult check = new RiskCheckResult();
		check.setFieldKey("APPLY_CERTIFI_NO");
		check.setRiskType(CacheConsts.RISK_TYPE_BLACK);
		check.setTableName("OPAS_PROMOTER_RISKLIST");
		check.setApplyType(appType);
		RiskCheckResult promoter = new RiskCheckResult();
		promoter.setFieldKey("PROMOTER_NO");
		promoter.setRiskType(CacheConsts.RISK_TYPE_BLACK);
		promoter.setTableName("OPAS_PROMOTER_RISKLIST");
		promoter.setApplyType(appType);
		Map<String, String> mapidentity = null;
		if(!"".equals(StringUtil.trimToEmpty(Idnbr))){
			mapidentity = riskCheckService.Query_OPAS_PROMOTER_RISKLIST(Idnbr);
		}
		Map<String, String> promoterList = null;
		if(!"".equals(c4Abuser)){
			promoterList = riskCheckService.QUERY_OPAS_PROMOTER_RISKLIST_C4ABUSER(c4Abuser);
		}
		if(promoterList != null && promoterList.size() > 0){
			promoter.setBaseDataValue(promoterList.get("promoterNo"));
			promoter.setPriKeyValue(promoterList.get("autoId"));
			promoter.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
		}
		if (mapidentity != null && mapidentity.size() > 0) {
			check.setBaseDataValue(mapidentity.get("certifiNo"));
			check.setPriKeyValue(mapidentity.get("autoId"));
			check.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
		}
		listriskcheck.add(check);
		listriskcheck.add(promoter);

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
