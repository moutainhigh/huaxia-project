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
 * 杭州公积金区域数据 
 * @author ad 20202/3/30
 */
public class Step3_Field_Hz_Gjjxx_Info implements RiskFieldMachService {
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
		RiskCheckResult check_STATUS = new RiskCheckResult("STATUS", "HZ_GJJXX",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_STATUS.setApplyType(appType);
		RiskCheckResult check_DEPTNAME = new RiskCheckResult("DEPTNAME", "HZ_GJJXX",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_DEPTNAME.setApplyType(appType);
		listriskcheck.add(check_STATUS);
		listriskcheck.add(check_DEPTNAME);
		Map<String,Object> gjjxxInfo = riskCheckService.Query_Gjjxx_Info(appId);
		
		Map<String, String> phoneMap = new HashMap<String, String>();
		String dateNum = "20"+appId.substring(0, 4);
		phoneMap.put("dateNum", dateNum);
		
		if(null!=gjjxxInfo) {
			if("正常".equals((String)gjjxxInfo.get("status"))&&!"".equals(trimToEmpty((String)gjjxxInfo.get("status")))){
				//公积金缴存状态“正常”且流水号日期减最近缴存年月≤6个月，且公积金单位名称与申请表单位名称模糊匹配一致时，
				//则比对页面“申请人工作信息真实”中的“社保/公积金信息近6个月缴交正常”栏位进行系统自动勾选。
				if(!"".equals(trimToEmpty((String)gjjxxInfo.get("deptName")))&&!"".equals(trimToEmpty((String)gjjxxInfo.get("payYm")))){
					
					if (lueneService.getMachResult(companyName, (String)gjjxxInfo.get("deptName"), "")) {
						//单名匹配
						check_DEPTNAME.setBaseDataValue((String)gjjxxInfo.get("deptName"));
						check_DEPTNAME.setPriKeyValue(appId);
						check_DEPTNAME.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						
						String payYm = (String)gjjxxInfo.get("payYm");
						phoneMap.put("payYm", payYm);
						//因为都是计算月份 跟人行计算使用同一个方法
						Float month = riskCheckService.Query_Pboc_InfoDate(phoneMap);
						if (month != null&&(month > 0 &&month <= 6)) {
							check_STATUS.setBaseDataValue((String)gjjxxInfo.get("payPm"));
							check_STATUS.setPriKeyValue(appId);
							check_STATUS.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
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
