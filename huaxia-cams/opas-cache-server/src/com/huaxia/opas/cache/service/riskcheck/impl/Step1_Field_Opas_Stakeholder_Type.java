package com.huaxia.opas.cache.service.riskcheck.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * 关系人类型名单库
 * 关系人类型,0-非关系人,1-关联自然人,2-员工,4-申请员工
 * @author ad
 *
 */
public class Step1_Field_Opas_Stakeholder_Type implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;
	
	public Map<String,Object> initParam(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String,Object> params = new HashMap<String, Object>();
		String companyName = "";
		String c4Recname = "";//推荐人姓名
		String c4Recemp = "";//推荐人工号
		// 主副
		if ("1".equals(appType)) {// C1_HMADD1
			//单位名称
			companyName = StringUtil.deleteWhiteSpace(apply_Info1.getC1Coname())+StringUtil.trimToEmpty(apply_Info1.getC4Fconm2());;
			c4Recname = StringUtil.trimToEmpty(apply_Info1.getC4Recname());
			c4Recemp = StringUtil.trimToEmpty(apply_Info1.getC4Recemp());
		}
		// 附卡
		if ("2".equals(appType)) {// C1_HMADD1

		}
		// 主卡
		if ("3".equals(appType)) {// C1_HMADD1
			companyName = StringUtil.deleteWhiteSpace(apply_Info1.getC1Coname())+StringUtil.trimToEmpty(apply_Info1.getC4Fconm2());;
			c4Recname = StringUtil.trimToEmpty(apply_Info1.getC4Recname());
			c4Recemp = StringUtil.trimToEmpty(apply_Info1.getC4Recemp());
		}
		params.put("companyName", companyName);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParam = this.initParam(appId, apply_Info1, apply_Info2, appType);
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		String companyName = (String) initParam.get("companyName");
		String c4Recname = (String) initParam.get("c4Recname");
		String c4Recemp = (String) initParam.get("c4Recemp");
		KeyfiledResultInfo keyfiled = new KeyfiledResultInfo();
		keyfiled = riskCheckService.Query_KEYFILED_RESULTINFO(keyfiled);
		if(keyfiled!=null){
			String stakeholderType = keyfiled.getStakeholderType();
			keyfiled.setAppId(appId);
			keyfiled.setCrtDate(new Date());
			//优质企业名单
			if(null==stakeholderType||"0".equals(stakeholderType)) {
				if(companyName.contains("华夏银行")||companyName.contains("华夏卡中心")||companyName.contains("华夏总行")
						||companyName.contains("华夏分行")||companyName.contains("华夏支行")) {
					keyfiled.setStakeholderType("4");
					riskCheckService.update_OPAS_KEYFILED_RESULTINFO(keyfiled,apply_Info1);
				}
			} 
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
