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
 * 当人行公积金缴交状态为正常，且流水号日期减最新缴费日期≤6个月时，
 * “社保/公积金信息近6个月缴交正常”
 * @author ad
 *
 */
public class Step3_Field_Opas_Pboc_Public_Accfund_Info implements RiskFieldMachService {
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
	
		RiskCheckResult check_STATUS = new RiskCheckResult("STATUS", "OPAS_PBOC_PUBLIC_ACCFUND",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_STATUS.setApplyType(appType);
		RiskCheckResult check_COMPANY = new RiskCheckResult("COMPANY", "OPAS_PBOC_PUBLIC_ACCFUND",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_COMPANY.setApplyType(appType);
		listriskcheck.add(check_STATUS);
		listriskcheck.add(check_COMPANY);
		Map<String, Object> pbocinfo = riskCheckService.Query_OPAS_PBOC_PUBLIC_ACCFUND_INFO(appId);
		
		Map<String, String> phoneMap = new HashMap<String, String>();
		String dateNum = "20"+appId.substring(0, 4);
		phoneMap.put("dateNum", dateNum);
		
		if (pbocinfo != null) {
			//当人行当前参保状态为001|缴交 时，并且缴交月份是近6个月之内  并且单名需要命中
			if(("001|缴交".equals((String)pbocinfo.get("payStatus"))||"1".equals((String)pbocinfo.get("payStatus")))
					&&!"".equals(trimToEmpty((String)pbocinfo.get("payComp")))&&!"".equals(trimToEmpty((String)pbocinfo.get("payYm")))){
				if(!"0000.00".equals((String)pbocinfo.get("payYm"))&&!"0000-00".equals((String)pbocinfo.get("payYm"))){
					String payYm = (String)pbocinfo.get("payYm");
					//java字符串去空格冒(:)号点(.)横杠(-)
					payYm = payYm.replaceAll("[[\\s-.:punct:]]", "");
					phoneMap.put("payYm", payYm);
					Float month = riskCheckService.Query_Pboc_InfoDate(phoneMap);
					if (month != null) {
						if (month > 0 &&month <= 6) {
							if (lueneService.getMachResult(companyName, (String)pbocinfo.get("payComp"), "")
									) {
								
								check_COMPANY.setPriKeyValue(appId);
								check_COMPANY.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
								check_COMPANY.setBaseDataValue((String)pbocinfo.get("payComp"));
								//且流水号日期减最新缴费日期≤6个月时，“社保/公积金信息近6个月缴交正常”置标志位
								check_STATUS.setBaseDataValue((String)pbocinfo.get("infoDate"));
								check_STATUS.setPriKeyValue(appId);
								check_STATUS.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);// 未过期
							}
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
