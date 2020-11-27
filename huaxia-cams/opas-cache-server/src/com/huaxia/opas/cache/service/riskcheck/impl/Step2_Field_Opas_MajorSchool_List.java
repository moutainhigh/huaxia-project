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
import com.huaxia.opas.domain.sysparm.MajorschoolList;
import com.huaxia.opas.service.riskcheck.RiskCheckService;

/**
 * 重点院校 通过申请人单位名称与重点院校名单内学校名称字段进行匹配（模糊匹配或申请人单位名称可完全落在名单单位名称字段内）
 * 
 * @author qianxiwen
 *
 */
public class Step2_Field_Opas_MajorSchool_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImplTest")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		String schoolName = "";
		// 主副卡
		if ("1".equals(appType)) {
			schoolName = apply_Info1.getC1Coname();
		}
		// 附卡
		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			schoolName = apply_Info1.getC1Coname();
		}
		params.put("schoolName", schoolName);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String schoolName = (String) initParams.get("schoolName");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<MajorschoolList> MajorschoolListlist = riskCheckService.Query_OPAS_MAJORSCHOOL_LIST();
		RiskCheckResult check_MAJORSCHOOL_NAME = null;
		check_MAJORSCHOOL_NAME = new RiskCheckResult("MAJORSCHOOL_NAME", "OPAS_MAJORSCHOOL_LIST",
				CacheConsts.RISK_TYPE_TEAM);
		check_MAJORSCHOOL_NAME.setApplyType(appType);
		if (MajorschoolListlist != null && MajorschoolListlist.size() > 0) {
			for (int i = 0; i < MajorschoolListlist.size(); i++) {
				MajorschoolList tmpMajorschoolList = MajorschoolListlist.get(i);
				if (lueneService.getMachResult(schoolName, tmpMajorschoolList.getMajorschoolName(), "")) {
					check_MAJORSCHOOL_NAME.setPriKeyValue(tmpMajorschoolList.getAutoId());
					check_MAJORSCHOOL_NAME.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_MAJORSCHOOL_NAME.setBaseDataValue(tmpMajorschoolList.getMajorschoolName());
					break;
				}

			}
		}
		listriskcheck.add(check_MAJORSCHOOL_NAME);
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
