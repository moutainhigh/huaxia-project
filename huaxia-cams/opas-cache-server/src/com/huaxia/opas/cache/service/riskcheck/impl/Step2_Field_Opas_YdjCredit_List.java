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
 * 单位名称 单位电话--是否命中 易达金征信信息名单表(标准卡) 中信息
 * 
 * @author yang.jiangming
 *
 */
public class Step2_Field_Opas_YdjCredit_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 单位名称
		String companyName = new String("");
		// 申请人公司电话是否命中 易达金征信信息名单表中“公司电话号码”
		String STR_APPLY_COTEL_ID = new String("");
		// 主附卡
		if ("1".equals(appType)) {
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
			STR_APPLY_COTEL_ID = this.trimToEmpty(apply_Info1.getC1Cotel());
		} else if ("2".equals(appType)) {
			// 附卡
			companyName = this.trimToEmpty(apply_Info2.getC2Coname());
			STR_APPLY_COTEL_ID = this.trimToEmpty(apply_Info2.getC2Cotel());
		} else if ("3".equals(appType)) {
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
			STR_APPLY_COTEL_ID = this.trimToEmpty(apply_Info1.getC1Cotel());
		}
		params.put("companyName", companyName);
		params.put("STR_APPLY_COTEL_ID", STR_APPLY_COTEL_ID);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String companyName = (String) initParams.get("companyName");
		String STR_APPLY_COTEL_ID = (String) initParams.get("STR_APPLY_COTEL_ID");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		// 单位名称
		RiskCheckResult check = new RiskCheckResult();
		check.setFieldKey("COMPANY_NAME");
		check.setApplyType(appType);
		check.setTableName("OPAS_CREDIT_TELCHECK_LIST");
		check.setRiskType(CacheConsts.RISK_TYPE_TEAM);
		List<Map<String, String>> nameList = riskCheckService.Query_OPAS_YDJ_COMPANY_RISKLIST();
		if (nameList != null && nameList.size() > 0) {
			for (Map<String, String> map : nameList) {
				if (lueneService.getMachResult(companyName, map.get("companyName"), "")) {
					check.setBaseDataValue(map.get("companyName"));
					check.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check.setPriKeyValue(map.get("autoId"));
					break;
				}
			}
		}
		// 单位电话
		/*
		 * RiskCheckResult cotel=new RiskCheckResult();
		 * cotel.setFieldKey("COMPANY_TEL");
		 * cotel.setRiskType(CacheConsts.RISK_TYPE_TEAM);
		 * cotel.setTableName("OPAS_CREDIT_TELCHECK_LIST"); Map<String, String>
		 * mapCotel =
		 * riskCheckService.Query_OPAS_YDJ_COTEL_RISKLIST(STR_APPLY_COTEL_ID);
		 * if(mapCotel != null && mapCotel.size() >0){
		 * cotel.setBaseDataValue(mapCotel.get("companyTel"));
		 * cotel.setPriKeyValue(mapCotel.get("autoId"));
		 * cotel.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED); }
		 * listriskcheck.add(cotel); listriskcheck.add(check);
		 */
		listriskcheck.add(check);
		return listriskcheck;
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

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {

	}

}
