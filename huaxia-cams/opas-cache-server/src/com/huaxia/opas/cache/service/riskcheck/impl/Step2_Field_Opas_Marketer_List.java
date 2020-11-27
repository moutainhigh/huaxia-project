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
 * 营销员白名单-推广工号、推广机构代码(送决策用)
 * 
 * @author jiangming.yang
 *
 * 2019/01/08
 * 新增身份证号码匹配
 * @author dong.an
 * 
 */
public class Step2_Field_Opas_Marketer_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 推广工号
		String c4Abuser = "";
		String c5Abcode = "";
		//卡产品
		String appProd = "";

		if ("1".equals(appType)) {
			c4Abuser = StringUtil.trimToEmpty(apply_Info1.getC4Abuser());
			c5Abcode = StringUtil.trimToEmpty(apply_Info1.getC5Abcode());
			appProd = StringUtil.trimToEmpty(apply_Info1.getAppProd());
		} else if ("2".equals(appType)) {

		} else if ("3".equals(appType)) {
			c4Abuser = StringUtil.trimToEmpty(apply_Info1.getC4Abuser());
			c5Abcode = StringUtil.trimToEmpty(apply_Info1.getC5Abcode());
			appProd = StringUtil.trimToEmpty(apply_Info1.getAppProd());
		}
		params.put("c4Abuser", c4Abuser);
		params.put("appProd", appProd);
		params.put("c5Abcode", c5Abcode);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String c4Abuser = (String) initParams.get("c4Abuser");
		String c5Abcode = (String) initParams.get("c5Abcode");
		String appProd = (String) initParams.get("appProd");
		
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		//pad进件匹配
		if ("A".equals(appId.substring(6, 7))) {
			List<Map<String, String>> result = riskCheckService.Query_OPAS_MARKETER_LIST();
			RiskCheckResult checkResult = new RiskCheckResult("SPREAD_NO", "OPAS_MARKETER_LIST",CacheConsts.RISK_TYPE_TEAM);
			//RiskCheckResult checkResult1 = new RiskCheckResult("SPREAD_ORG", "OPAS_MARKETER_LIST",CacheConsts.RISK_TYPE_TEAM);
			checkResult.setApplyType(appType);
			//checkResult1.setApplyType(appType);
			//营销员白名单匹配
			if (result != null && result.size() > 0) {
				for (Map<String, String> infoRisk : result) {
					if (!"".equals(StringUtil.trimToEmpty(c4Abuser))&&!"".equals(StringUtil.trimToEmpty(c5Abcode))) {
						if (c4Abuser.equals(infoRisk.get("spreadNo"))&&c5Abcode.equals(infoRisk.get("spreadOrg"))) {
							// 匹配成功
							checkResult.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
							checkResult.setPriKeyValue("1");
						}
					}
				}
			}
			listriskcheck.add(checkResult);
		}
		return listriskcheck;
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {

	}

}
