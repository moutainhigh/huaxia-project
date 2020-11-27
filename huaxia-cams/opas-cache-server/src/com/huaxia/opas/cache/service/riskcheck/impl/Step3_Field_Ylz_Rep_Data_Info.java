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
 * 厦门易联众区域数据 单位名称	公积金信息缴交正常 标识
 * @author ad 2019/05/29
 */
public class Step3_Field_Ylz_Rep_Data_Info implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;
	

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 单位名称COMPANY
		String companyName = new String("");

		Map<String, String> phoneMap = new HashMap<String, String>();

		// 主卡
		if ("1".equals(appType)) {
			phoneMap.put("appId", appId);
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
		}

		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			phoneMap.put("appId", appId);
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
		}
		params.put("companyName", companyName);
		params.put("phoneMap", phoneMap);
		return params;
	}
	
	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String companyName = (String) initParams.get("companyName");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		RiskCheckResult check_STATUS = new RiskCheckResult("STATUS", "YLZ_REP_DATA",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_STATUS.setApplyType(appType);
		RiskCheckResult check_COMPANYNAME = new RiskCheckResult("COMPANY", "YLZ_REP_DATA",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_COMPANYNAME.setApplyType(appType);
		listriskcheck.add(check_STATUS);
		listriskcheck.add(check_COMPANYNAME);
		Map<String,Object> ylzInfo = riskCheckService.Query_YLZ_REP_DATA(appId);
		
		if(null!=ylzInfo) {
			if("参保缴费".equals((String)ylzInfo.get("insuredStatus"))&&"是".equals((String)ylzInfo.get("isOnJob"))
					&&null!=ylzInfo.get("companyName")&&null!=ylzInfo.get("latestPaymentDate")
					&&(!"3个月以上".equals((String)ylzInfo.get("latestPaymentDate")))||!"-2".equals((String)ylzInfo.get("latestPaymentDate"))){
				//所有条件同时满足才算命中  参保缴费 并且在职  并且缴交月份是近3个月  并且单名需要命中
				if(!"".equals(trimToEmpty((String)ylzInfo.get("companyName")))&&!"".equals(trimToEmpty((String)ylzInfo.get("latestPaymentDate")))){
					if (lueneService.getMachResult(companyName, (String)ylzInfo.get("companyName"), "")) {
						//当区域数据中“当前参保状态为正常”且“单位名称”与申请表单位名称模糊匹配一致时，“区域数据名单标志”置标志位。
						check_COMPANYNAME.setBaseDataValue((String)ylzInfo.get("companyName"));
						check_COMPANYNAME.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						check_COMPANYNAME.setPriKeyValue(appId);
						//当区域数据当前参保状态为正常时，且流水号日期减最新缴费日期≤6个月时，“社保/公积金信息近6个月缴交正常”置标志位
						check_STATUS.setBaseDataValue((String)ylzInfo.get("infoDate"));
						check_STATUS.setPriKeyValue(appId);
						check_STATUS.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);// 未过期
					}
				}
			}
		}
		
		return listriskcheck;
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {
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

	/**
	 * 去掉两端空格，如果传入参数是null，返回是""（空字符串）
	 */
	public String trimToEmpty(String str) {
		if (str == null) {
			return "";
		}
		return str.trim();
	}

}
