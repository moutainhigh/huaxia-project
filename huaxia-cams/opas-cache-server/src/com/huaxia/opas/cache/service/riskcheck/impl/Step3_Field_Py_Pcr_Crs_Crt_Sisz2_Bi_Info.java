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
 * 区域数据 单位名称	公积金信息近6个月缴交正常 标识
 * @author ad 2019/05/27
 */
public class Step3_Field_Py_Pcr_Crs_Crt_Sisz2_Bi_Info implements RiskFieldMachService {
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
		Map<String, String> phoneMap = (Map<String, String>) initParams.get("phoneMap");
		RiskCheckResult check_UNITSTATUS = new RiskCheckResult("STATUS", "PY_PCR_CRS_CRT_SISZ2_HI5Y",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_UNITSTATUS.setApplyType(appType);
		RiskCheckResult check_UNITNAME = new RiskCheckResult("COMPANY", "PY_PCR_CRS_CRT_SISZ2_HI5Y",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_UNITNAME.setApplyType(appType);
		listriskcheck.add(check_UNITNAME);
		listriskcheck.add(check_UNITSTATUS);
		Map<String,Object> pyInfo = riskCheckService.Query_PY_PCR_CRS_CRT_SISZ2_BI(appId);

		String dateNum = "20"+appId.substring(0, 6);
		phoneMap.put("dateNum", dateNum);
		
		if(null!=pyInfo) {
			if("正常参保".equals((String)pyInfo.get("currentStatus"))&&null!=pyInfo.get("unitName")&&null!=pyInfo.get("infoDate")){
				//正常参保 并且单名命中  并且缴交月份是近六个月之内
				if(!"".equals((String)pyInfo.get("unitName"))&&!"".equals(trimToEmpty((String)pyInfo.get("infoDate")))){
					phoneMap.put("infoDate", (String) pyInfo.get("infoDate"));
					Float month = riskCheckService.Query_Py_InfoDate(phoneMap);
					if (month != null) {
						if (month > 0 &&month <= 6) {
							//当区域数据中“当前参保状态为正常”且“单位名称”与申请表单位名称模糊匹配一致时，“区域数据名单标志”置标志位。
							if (lueneService.getMachResult(companyName, (String)pyInfo.get("unitName"), "")) {
								check_UNITNAME.setBaseDataValue((String)pyInfo.get("unitName"));
								check_UNITNAME.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
								check_UNITNAME.setPriKeyValue(appId);
								//当区域数据当前参保状态为正常时，且流水号日期减最新缴费日期≤6个月时，“社保/公积金信息近6个月缴交正常”置标志位
								check_UNITSTATUS.setBaseDataValue((String)pyInfo.get("infoDate"));
								check_UNITSTATUS.setPriKeyValue(appId);
								check_UNITSTATUS.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);// 未过期
							}
						} 
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
