package com.huaxia.opas.cache.service.riskcheck.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
 * 优质企业 申请人优质企业代码+进入系统日期（进件日期）与优质企业名单中优质企业代码+结束日期进行匹配
 * 如未成功匹配项，通过申请人单位名称+进入系统日期（进件日期）与优质企业名单中单位名称+结束日期进行匹配
 * (单位名称进行模糊匹配或申请人单位名称可完全落在名单单位名称字段内，同时进件日期在结束日期之内
 * 
 * @author jiangming.yang
 *
 */
public class Step2_Field_Opas_GoodCompany_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImplTest")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		/**
		 * 企业代码
		 */
		String projectCode = "";
		/**
		 * 进件日期
		 */
		Date crtDate = null;
		/**
		 * 单位名称
		 */
		String companyName = "";
		if ("1".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info1.getC1Cobuscd());
			crtDate = apply_Info1.getCrtDate();
			companyName = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
		} else if ("2".equals(appType)) {
			companyName = StringUtil.trimToEmpty(apply_Info2.getC2Coname());
			crtDate = apply_Info2.getCrtDate();
		} else if ("3".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info1.getC1Cobuscd());
			crtDate = apply_Info1.getCrtDate();
			companyName = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
		}
		params.put("projectCode", projectCode);
		params.put("crtDate", crtDate);
		params.put("companyName", companyName);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String projectCode = (String) initParams.get("projectCode");
		Date crtDate = (Date) initParams.get("crtDate");
		String companyName = (String) initParams.get("companyName");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		RiskCheckResult goodCOMPANY = null;
		Integer days = null;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
		if (crtDate != null) {
			String format = sf.format(crtDate);
			Map map = new HashMap();
			map.put("projectCode", projectCode);
			map.put("crtDate", format);
			days = riskCheckService.Query_OPAS_GoodCompany_ValiDate(map);
		}
		// 企业代码
		List<Map<String, String>> result = null;
		if (!"".equals(projectCode) && projectCode != null) {
			result = riskCheckService.Query_OPAS_GoodCompany_ByProjectCode(projectCode);
		}
		if (result != null && result.size() > 0) {
			goodCOMPANY = new RiskCheckResult("PROJECT_CODE", "OPAS_GOODCOMPANY_LIST", CacheConsts.RISK_TYPE_TEAM);
			goodCOMPANY.setApplyType(appType);
			// 判断时间 结束日期 - 进件日期 >0
			if (days != null && days > 0) {
				goodCOMPANY.setBaseDataValue(projectCode);
				goodCOMPANY.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				goodCOMPANY.setPriKeyValue(result.get(0).get("autoId"));
			}
			listriskcheck.add(goodCOMPANY);
		} else {
			// 根据单位名称匹配
			goodCOMPANY = new RiskCheckResult("COMPANY_NAME", "OPAS_GOODCOMPANY_LIST", CacheConsts.RISK_TYPE_TEAM);
			goodCOMPANY.setApplyType(appType);
			result = riskCheckService.Query_OPAS_GoodCompany();
			if (result != null && result.size() > 0) {
				for (Map<String, String> map : result) {
					if (lueneService.getMachResult(companyName, map.get("companyName"), "")) {
						// 匹配成功
						// 判断时间 结束日期 - 进件日期 >0
						if (crtDate != null) {
							String format = sf.format(crtDate);
							Map map2 = new HashMap();
							map2.put("projectCode", map.get("projectCode"));
							map2.put("crtDate", format);
							days = riskCheckService.Query_OPAS_GoodCompany_ValiDate(map2);
						}
						if (days != null && days > 0) {
							goodCOMPANY.setBaseDataValue(companyName);
							goodCOMPANY.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
							goodCOMPANY.setPriKeyValue(map.get("autoId"));
						}
						break;
					}
				}
			}
			listriskcheck.add(goodCOMPANY);
		}

		return listriskcheck;
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {

	}

}
