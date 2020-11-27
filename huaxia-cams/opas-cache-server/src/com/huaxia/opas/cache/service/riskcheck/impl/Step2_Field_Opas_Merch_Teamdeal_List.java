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
import com.huaxia.opas.domain.decision.MerchTeamdealList;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

public class Step2_Field_Opas_Merch_Teamdeal_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String,Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String,Object> params = new HashMap<String, Object>();
		 String TeamDealNo = "";
		// 主副卡
		if ("1".equals(appType)) {
			TeamDealNo = StringUtil.trimToEmpty(apply_Info1.getC4Apbatch());
		}
		// 附卡
		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			TeamDealNo = StringUtil.trimToEmpty(apply_Info1.getC4Apbatch());
		}
		params.put("TeamDealNo", TeamDealNo);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		 String TeamDealNo = (String) initParams.get("TeamDealNo");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<MerchTeamdealList> merchTeamdealList = riskCheckService.Query_OPAS_MERCH_TEAMDEAL_LIST(TeamDealNo);
		RiskCheckResult check_TEAMDEAL_NO = new RiskCheckResult("TEAMDEAL_NO", "OPAS_MERCH_TEAMDEAL_LIST",
				CacheConsts.RISK_TYPE_TEAM);
		check_TEAMDEAL_NO.setApplyType(appType);
		if (merchTeamdealList != null && merchTeamdealList.size() > 0) {
			for (int i = 0; i < merchTeamdealList.size(); i++) {
				MerchTeamdealList tmpMerchTeamdealList = merchTeamdealList.get(i);
				check_TEAMDEAL_NO.setPriKeyValue(tmpMerchTeamdealList.getAutoId());
				check_TEAMDEAL_NO.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_TEAMDEAL_NO.setBaseDataValue(tmpMerchTeamdealList.getTeamdealNo());
				break;
			}
		}
		listriskcheck.add(check_TEAMDEAL_NO);
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
