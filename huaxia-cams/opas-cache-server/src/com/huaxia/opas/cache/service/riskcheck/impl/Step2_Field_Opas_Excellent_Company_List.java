package com.huaxia.opas.cache.service.riskcheck.impl;

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
import com.huaxia.opas.domain.decision.ExcellentCompanyList;
import com.huaxia.opas.domain.riskcheck.KeyfiledResultInfo;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 优质单位准入白名单
 * 1.姓名，证件号，单位名称，手机号匹配启用状态 有效期内  完全匹配 输出“1”
 * 2.姓名 证件号，单位名称  且手机号为空， 完全匹配 输出“2”
 * 3.姓名 证件号 完全匹配 且没有命中“1”和“2” 输出“3”
 * 4.其他情况输出“-1”
 * c1Coname    c4Fconm2 单位名称
c1Cname  姓名
c1Idnbr 证件号
c1Mobile 手机号
 * @author andong
 *
 */
public class Step2_Field_Opas_Excellent_Company_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		String companyName = "";
		String name = "";//姓名
		String idnbr = "";//证件号
		String mobile = "";//手机号
		// 主副卡
		if ("1".equals(appType)) {
			name = StringUtil.trimToEmpty(apply_Info1.getC1Cname());
			idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
			mobile = StringUtil.trimToEmpty(apply_Info1.getC1Mobile());
			companyName = StringUtil.deleteWhiteSpace(apply_Info1.getC1Coname())+StringUtil.trimToEmpty(apply_Info1.getC4Fconm2());;
		}
		// 附卡
		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			name = StringUtil.trimToEmpty(apply_Info1.getC1Cname());
			idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
			mobile = StringUtil.trimToEmpty(apply_Info1.getC1Mobile());
			companyName = StringUtil.deleteWhiteSpace(apply_Info1.getC1Coname())+StringUtil.trimToEmpty(apply_Info1.getC4Fconm2());
		}
		params.put("name", name);
		params.put("mobile", mobile);
		params.put("idnbr", idnbr);
		params.put("companyName", companyName);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		String name = (String) initParams.get("name");
		String mobile = (String) initParams.get("mobile");
		String idnbr = (String) initParams.get("idnbr");
		String companyName = (String) initParams.get("companyName");
		KeyfiledResultInfo keyfiledResultInfo = new KeyfiledResultInfo();
		//优质企业名单
		ExcellentCompanyList excellentCompany = riskCheckService.Query_OPAS_EXCELLENT_COMPANY_LIST(initParams);
		RiskCheckResult check_status = new RiskCheckResult("STATUS", "OPAS_EXCELLENT_COMPANY_LIST",
				CacheConsts.RISK_TYPE_TEAM);
		check_status.setApplyType(appType);
		keyfiledResultInfo.setAppId(appId);
		keyfiledResultInfo.setCrtDate(new Date());
		if(excellentCompany!=null) {
			if(null == excellentCompany.getPhone()&&(companyName.equals(excellentCompany.getCompanyName()))) {
				check_status.setBaseDataValue("2");
				check_status.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_status.setPriKeyValue("2");
				keyfiledResultInfo.setYzBmd("2");
			}else if(companyName.equals(excellentCompany.getCompanyName())&&mobile.equals(excellentCompany.getPhone())){
				check_status.setBaseDataValue("1");
				check_status.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_status.setPriKeyValue("1");
				keyfiledResultInfo.setYzBmd("1");
			}else {
				check_status.setBaseDataValue("3");
				check_status.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_status.setPriKeyValue("3");
				keyfiledResultInfo.setYzBmd("3");
			}
		} else {
			check_status.setBaseDataValue("-1");
			check_status.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
			check_status.setPriKeyValue("-1");
			keyfiledResultInfo.setYzBmd("-1");
		}
		riskCheckService.update_OPAS_KEYFILED_RESULTINFO(keyfiledResultInfo,apply_Info1);
		listriskcheck.add(check_status);
		
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
