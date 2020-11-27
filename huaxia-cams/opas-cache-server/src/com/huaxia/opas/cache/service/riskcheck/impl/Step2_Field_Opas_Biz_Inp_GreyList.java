package com.huaxia.opas.cache.service.riskcheck.impl;

import java.math.BigDecimal;
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
 * 灰名单(个人)检查
 * 
 * @author jiangming.yang
 *
 */
public class Step2_Field_Opas_Biz_Inp_GreyList implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		if ("1".equals(appType)) {// 主卡

		} else {

		}
	}
	
	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		String projectCode = "";
		// 主副
		if ("1".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
		}
		// 附卡
		if ("2".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info2.getC2Idnbr());
		}
		// 主卡
		if ("3".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
		}
		params.put("projectCode", projectCode);
		return params;
	}
	
	@Override
	public synchronized List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = initParams(appId, apply_Info1, apply_Info2, appType);
		String credNo = (String) initParams.get("projectCode");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		RiskCheckResult check_grey = new RiskCheckResult("CRED_NO", "OPAS_FILE_APPLICATION_DETAIL",
				CacheConsts.RISK_TYPE_TEAM);
		check_grey.setApplyType(appType);
		RiskCheckResult check_grey1 = new RiskCheckResult("CRED_NO1", "OPAS_FILE_APPLICATION_DETAIL",
				CacheConsts.RISK_TYPE_TEAM);
		check_grey1.setApplyType(appType);
		listriskcheck.add(check_grey);
		listriskcheck.add(check_grey1);
		//
		List<String> greyAppIdList = riskCheckService.Query_OPAS_FILE_APPLICATION_DETAIL_BY_CREDNO(credNo);
		StringBuffer ret  = new StringBuffer();
		StringBuffer ret2  = new StringBuffer();
		Integer indateRes = null;
		String for15AppId = appId.substring(0, appId.length()-1);
		if(greyAppIdList != null && greyAppIdList.size()>0){
			initParams.put("appId", appId);
			for (String greyAppId : greyAppIdList) {
				if(!(for15AppId+"1").equals(greyAppId) && !(for15AppId+"2").equals(greyAppId)){
					initParams.put("appId2", greyAppId);
					// 决策时间-进入灰名单库时间 -- 修改为进件时间 - 进入黑名单时间
					Integer indate = riskCheckService.Query_OPAS_BIZ_Grey_Indate(initParams);
					// 查询有效期限
					Map<String, Object> resonIndate = riskCheckService.Query_OPAS_BIZ_Grey_ResonIndate(greyAppId);
					if (resonIndate != null && resonIndate.get("resonTimeLimit") != null
							&& resonIndate.get("resonTimeLimit") != "") {
						indateRes = Integer.valueOf(resonIndate.get("resonTimeLimit").toString());
					}
					if (indateRes != null && indate != null) {
						if (indate.intValue() >0 && indate.intValue() < indateRes.intValue()) {
							ret.append(greyAppId+"|");
							if("0".equals(check_grey1.getRiskResult())){
								check_grey1.setPriKeyValue(indateRes.toString());
								check_grey1.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
							}
						}
					}
					initParams.remove("appId2");
				}
			}
		}
		if(!"".equals(ret.toString())){
			check_grey.setBaseDataValue(appId);
			check_grey.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
			check_grey.setPriKeyValue(ret.toString());
		}
		return listriskcheck;
	}

}
