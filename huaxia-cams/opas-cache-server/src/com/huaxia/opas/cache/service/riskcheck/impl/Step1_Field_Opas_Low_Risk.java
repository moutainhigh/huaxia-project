package com.huaxia.opas.cache.service.riskcheck.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.Resource;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.service.LueneService;
import com.huaxia.opas.cache.service.riskcheck.RiskFieldMachService;
import com.huaxia.opas.domain.riskcheck.KeyfiledResultInfo;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
	审批系统根据申请件中推荐人姓名、推荐人工号字段与低风险客户名单库中姓名、手机号进行匹配，
	并新增匹配结果字段tj_match，如比对一致且启停状态为“启用”
 * @author ad
 *
 */
public class Step1_Field_Opas_Low_Risk implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;
	
	public Map<String,Object> initParam(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String,Object> params = new HashMap<String, Object>();
		String c4Recname = "";//推荐人姓名
		String c4Recemp = "";//推荐人工号
		// 主副
		if ("1".equals(appType)) {// C1_HMADD1
			c4Recname = StringUtil.trimToEmpty(apply_Info1.getC4Recname());
			c4Recemp = StringUtil.trimToEmpty(apply_Info1.getC4Recemp());
		}
		// 附卡
		if ("2".equals(appType)) {// C1_HMADD1

		}
		// 主卡
		if ("3".equals(appType)) {// C1_HMADD1
			c4Recname = StringUtil.trimToEmpty(apply_Info1.getC4Recname());
			c4Recemp = StringUtil.trimToEmpty(apply_Info1.getC4Recemp());
		}
		params.put("c4Recname", c4Recname);
		params.put("c4Recemp", c4Recemp);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParam = this.initParam(appId, apply_Info1, apply_Info2, appType);
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		KeyfiledResultInfo keyfiledResultInfo = new KeyfiledResultInfo();
		String tjMatch = "";
		List<String> tjMatchs= riskCheckService.Query_OPAS_LOW_RISK_LIST(initParam);
		//优质企业名单
		keyfiledResultInfo.setAppId(appId);
		keyfiledResultInfo.setCrtDate(new Date());
		if(tjMatchs.size()>0){
			Set<String> set = new TreeSet<>();
			set.addAll(tjMatchs);
			tjMatch = StringUtil.deleteWhiteSpace(set.toString());
			tjMatch = tjMatch.substring(1, tjMatch.length());
			tjMatch = tjMatch.substring(0, tjMatch.length()-1);
			tjMatch = tjMatch.replace(",","|");
			keyfiledResultInfo.setTjMatch(tjMatch);
			riskCheckService.update_OPAS_KEYFILED_RESULTINFO(keyfiledResultInfo,apply_Info1);
		}
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
