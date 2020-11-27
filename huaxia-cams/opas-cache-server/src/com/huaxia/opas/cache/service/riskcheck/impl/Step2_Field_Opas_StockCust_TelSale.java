package com.huaxia.opas.cache.service.riskcheck.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

public class Step2_Field_Opas_StockCust_TelSale implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	/*
	 * 身份证号码精确匹配；仅仅对名单库内状态为开启的进行匹配
	 */
	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		Map<String, String> strMap = new HashMap<String, String>();
		
		if ("1".equals(appType)) {
			strMap.put("Idnbr", StringUtil.trimToEmpty(apply_Info1.getC1Idnbr()));
		}
		if ("2".equals(appType)) {
			strMap.put("Idnbr", StringUtil.trimToEmpty(apply_Info2.getC2Idnbr()));
		}
		if ("3".equals(appType)) {
			strMap.put("Idnbr", StringUtil.trimToEmpty(apply_Info1.getC1Idnbr()));
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
		RiskCheckResult stockCust = new RiskCheckResult("CERTIFI_NO", "OPAS_STOCKCUST_TELSALE_LIST",CacheConsts.RISK_TYPE_TEAM);
		stockCust.setApplyType(appType);
		listriskcheck.add(stockCust);
		// 主附卡申请
		if ("1".equals(appType)) {
			Integer count = riskCheckService.QUERY_OPAS_STOCKCUST_IDNBR(strMap);
			if(count != null && count.intValue()>0){
				stockCust.setPriKeyValue(count.toString());
				stockCust.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				stockCust.setBaseDataValue(strMap.get("Idnbr"));
			}
		}
		// 副卡申请
		if ("2".equals(appType)) {

		}
		// 主卡申请
		if ("3".equals(appType)) {
			Integer count = riskCheckService.QUERY_OPAS_STOCKCUST_IDNBR(strMap);
			if(count != null && count.intValue()>0){
				stockCust.setPriKeyValue(count.toString());
				stockCust.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				stockCust.setBaseDataValue(strMap.get("Idnbr"));
			}
		}
		// 存量电销之匹配打标
		List<String> autoIds = riskCheckService.QUERY_OPAS_STOCKCUST_IDNBR_AUTOIDS(strMap);
		if(autoIds != null && autoIds.size()>0){
			for (String autoId : autoIds) {
				//更新 存量电销表  标记
				riskCheckService.UPDATE_OPAS_STOCKCUST_IDNBR_BY_AUTOID(autoId);
			}
		}
		strMap.clear();
		return listriskcheck;
	}

	private String regex_match(String str) {
		String result = "";
		String regex = "[`~!@#$%^&*()+=|{}':;',[-]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p1 = Pattern.compile(regex);
		Matcher matcher = p1.matcher(str);
		result = matcher.replaceAll("").trim();
		return result;
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
