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

/**
 * 当公积金缴交状态为正常，且流水号日期减最新缴费日期≤6个月时，
 * “社保/公积金信息近6个月缴交正常”
 * @author ad
 *
 */
public class Step3_Field_Xm_Gjj_Jcxx_Info implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String companyName = (String) initParams.get("companyName");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		if ("36".equals(appId.substring(8,10))) {
		
			RiskCheckResult check_STATUS = new RiskCheckResult("STATUS", "XM_GJJ_JCXX",
					CacheConsts.RISK_TYPE_THIRDPARTY);
			check_STATUS.setApplyType(appType);
			RiskCheckResult check_COMPANY = new RiskCheckResult("COMPANY", "XM_GJJ_JCXX",
					CacheConsts.RISK_TYPE_THIRDPARTY);
			check_COMPANY.setApplyType(appType);
			listriskcheck.add(check_STATUS);
			listriskcheck.add(check_COMPANY);
			String company = riskCheckService.Query_XM_GJJ_JCXX(appId);
			
			Map<String, String> param = new HashMap<String, String>();
			String dateNum = "20"+appId.substring(0, 6);
			param.put("dateNum", dateNum);
			param.put("appId", appId);
			
			//
			if(!"".equals(trimToEmpty(company))){
				String[] strs = company.split("\\|");
				String comp = "";
				for(int i = 0; i < strs.length; i++){
					if(!"".equals(trimToEmpty(strs[i]))){
						comp=strs[1];
						break;
					}
				}
				if (lueneService.getMachResult(companyName, comp, "")) {
					check_COMPANY.setPriKeyValue(appId);
					check_COMPANY.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_COMPANY.setBaseDataValue(company);
					Float month = riskCheckService.Query_Xm_Gjj_InfoDate(param);
					if (month != null) {
						if (month > 0 &&month <= 6) {
							//且流水号日期减最新缴费日期≤6个月时，“社保/公积金信息近6个月缴交正常”置标志位
							check_STATUS.setBaseDataValue(appId);
							check_STATUS.setPriKeyValue(appId);
							check_STATUS.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						}
					}
				}
					
			}
		}
		return listriskcheck;
	}

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 单位名称COMPANY
		String companyName = new String("");
		// 主卡
		if ("1".equals(appType)) {
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
		}
		if ("2".equals(appType)) {

		}
		if ("3".equals(appType)) {
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
		}
		params.put("companyName", companyName);
		return params;
	}

	/**
	 * 去掉两端空格，如果传入参数是null，返回是""（空字符串）
	 * 
	 * @param str
	 * @return
	 */
	public String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
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
