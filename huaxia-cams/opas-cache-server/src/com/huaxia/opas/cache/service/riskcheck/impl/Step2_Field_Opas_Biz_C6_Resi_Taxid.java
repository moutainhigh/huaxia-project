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

public class Step2_Field_Opas_Biz_C6_Resi_Taxid implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	/**  2019/11/11
	 * （1）若“税收居民个人声明”勾选为“仅为中国内地税收居民”，且申请表“国家/地区”勾选为非“境内居民”，校验项“国家/证件信息与税收居民逻辑检查”展示为“不一致”，点击“不一致”后弹屏展示“国家/地区与税收居民逻辑不一致”。
		（2）若“税收居民个人声明”勾选为“仅为中国内地税收居民”，且“证件类别”勾选为非“居民身份证”或“军官证“时，校验项“国家/证件信息与税收居民逻辑检查”展示为“不一致”；点击“不一致”后弹屏展示“证件类别与税收居民逻辑不一致”。
		（3）除以上，校验项“国家/证件信息与税收居民逻辑检查”展示为“一致”。
	*/
	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> strMap = new HashMap<String, String>();
		if ("1".equals(appType)) {
			strMap.put("c1Nation", StringUtil.trimToEmpty(apply_Info1.getC1Nation()));
			strMap.put("c6ResiTaxid1", StringUtil.trimToEmpty(apply_Info1.getC6ResiTaxid1()));
			strMap.put("c1Idtype", StringUtil.trimToEmpty(apply_Info1.getC1Idtype()));
		}
		if ("2".equals(appType)||"1".equals(appType)) {
			strMap.put("c2Nation", StringUtil.trimToEmpty(apply_Info2.getC2Nation()));
			strMap.put("c6ResiTaxid2", StringUtil.trimToEmpty(apply_Info2.getC6ResiTaxid2()));
			strMap.put("c2Idtype", StringUtil.trimToEmpty(apply_Info2.getC2Idtype()));
		}
		if ("3".equals(appType)) {
			strMap.put("c1Nation", StringUtil.trimToEmpty(apply_Info1.getC1Nation()));
			//税收居民个人声明
			strMap.put("c6ResiTaxid1", StringUtil.trimToEmpty(apply_Info1.getC6ResiTaxid1()));
			strMap.put("c1Idtype", StringUtil.trimToEmpty(apply_Info1.getC1Idtype()));
		}
		params.put("strMap", strMap);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		Map<String, String> strMap = (Map<String, String>) initParams.get("strMap");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		//主卡内部交叉
		RiskCheckResult check_C6RESITAXID = new RiskCheckResult("C6_RESI_TAXID1", "OPAS_BIZ_INP_APPL_C1", CacheConsts.RISK_TYPE_TEAM);
		check_C6RESITAXID.setApplyType(appType);
		RiskCheckResult check_Blaze = new RiskCheckResult("C6_RESI_TAXID2", "OPAS_BIZ_INP_APPL_C1",CacheConsts.RISK_TYPE_TEAM);
		check_Blaze.setApplyType(appType);
		
		//附属卡内部交叉
		RiskCheckResult check_C6RESITAXID2 = new RiskCheckResult("C6_RESI_TAXID1", "OPAS_BIZ_INP_APPL_C2", CacheConsts.RISK_TYPE_TEAM);
		check_C6RESITAXID2.setApplyType(appType);
		RiskCheckResult check_Blaze2 = new RiskCheckResult("C6_RESI_TAXID2", "OPAS_BIZ_INP_APPL_C2",CacheConsts.RISK_TYPE_TEAM);
		check_Blaze2.setApplyType(appType);
		
		boolean flag = false;
		
		StringBuffer ret = new StringBuffer();
		StringBuffer ret1 = new StringBuffer();
		// 主附卡申请
		if ("1".equals(appType)||"3".equals(appType)) {
			if (strMap.get("c6ResiTaxid1")!=null){
				//国家/地区与税收居民逻辑不一致
				if((!"1".equals(StringUtil.trimToEmpty(strMap.get("c1Nation")))&&!"5".equals(StringUtil.trimToEmpty(strMap.get("c1Nation"))))
						&&"1".equals(StringUtil.trimToEmpty(strMap.get("c6ResiTaxid1")))){
					ret.append("1");
					flag = true;
				} 
				//证件类别与税收居民逻辑不一致
				if ("1".equals(StringUtil.trimToEmpty(strMap.get("c6ResiTaxid1")))
						&&(!"01".equals(StringUtil.trimToEmpty(strMap.get("c1Idtype")))
						    &&!"05".equals(StringUtil.trimToEmpty(strMap.get("c1Idtype"))))){
					ret.append("2");
					flag = true;
				} 
				//一致
				if(!flag) {
					ret.append("3");
				}
			}
			
			
			
		}
		
		// 主卡申请
		if (!"3".equals(appType)) {
			if (strMap.get("c6ResiTaxid2")!=null){
				//国家/地区与税收居民逻辑不一致
				if ((!"1".equals(StringUtil.trimToEmpty(strMap.get("c2Nation")))&&!"5".equals(StringUtil.trimToEmpty(strMap.get("c2Nation"))))
						&&"1".equals(StringUtil.trimToEmpty(strMap.get("c6ResiTaxid2")))){
					ret1.append("1");
					flag = true;
				} 
				//证件类别与税收居民逻辑不一致
				if ("1".equals(StringUtil.trimToEmpty(strMap.get("c6ResiTaxid2")))
						&&(!"01".equals(StringUtil.trimToEmpty(strMap.get("c2Idtype")))
								&&!"05".equals(StringUtil.trimToEmpty(strMap.get("c2Idtype"))))){
					ret1.append("2");
					flag = true;
				} 
				//一致
				if(!flag) {
					ret1.append("3");
				}
			}
		}
		if (!"".equals(ret.toString())) {
			check_C6RESITAXID.setPriKeyValue(ret.toString());
			check_C6RESITAXID.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
			check_Blaze.setPriKeyValue(ret.toString());
			check_Blaze.setRiskResult(ret.toString());
		} else {
			check_C6RESITAXID.setPriKeyValue("4");
			check_C6RESITAXID.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
			check_Blaze.setPriKeyValue("4");
			check_Blaze.setRiskResult("4");
		}
		if (!"".equals(ret1.toString())) {
			check_C6RESITAXID2.setPriKeyValue(ret.toString());
			check_C6RESITAXID2.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
			check_Blaze2.setPriKeyValue(ret.toString());
			check_Blaze2.setRiskResult(ret.toString());
		} else {
			check_C6RESITAXID2.setPriKeyValue("4");
			check_C6RESITAXID2.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
			check_Blaze2.setPriKeyValue("4");
			check_Blaze2.setRiskResult("4");
		}
		listriskcheck.add(check_C6RESITAXID);
		listriskcheck.add(check_Blaze);
		listriskcheck.add(check_C6RESITAXID2);
		listriskcheck.add(check_Blaze2);
		return listriskcheck;
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
