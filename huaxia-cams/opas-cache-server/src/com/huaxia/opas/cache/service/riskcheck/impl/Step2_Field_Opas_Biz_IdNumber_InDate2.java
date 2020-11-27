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
 * 附卡证件有效期检查
 * 
 * @author dong.an
 *
 */
public class Step2_Field_Opas_Biz_IdNumber_InDate2 implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		/*
		 * 附卡证件有效期
		 */
		Date idNumberInDate = null;

		if ("1".equals(appType)) {// 主附同审
			idNumberInDate = apply_Info2.getC5Idte2();
		}
		/*
		 * 单申附卡
		 */
		if ("2".equals(appType)) {
			idNumberInDate = apply_Info2.getC5Idte2();
		}
		params.put("idNumberInDate", idNumberInDate);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
	
		Date idNumberInDate = (Date) initParams.get("idNumberInDate");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		if (!"3".equals(appType)) {
			RiskCheckResult check_IdIndate = new RiskCheckResult("C5_IDTE1", "OPAS_BIZ_INP_APPL_C2",
					CacheConsts.RISK_TYPE_TEAM);
			RiskCheckResult check_Blaze = new RiskCheckResult("C5_IDTE2", "OPAS_BIZ_INP_APPL_C2",
					CacheConsts.RISK_TYPE_TEAM);
			check_IdIndate.setApplyType(appType);
			check_Blaze.setApplyType(appType);
			listriskcheck.add(check_IdIndate);
			listriskcheck.add(check_Blaze);
		
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			if (idNumberInDate != null) {
				String formatInDate = sf.format(idNumberInDate);
				boolean flag = false;
				if("01".equals(StringUtil.trimToEmpty(apply_Info2.getC2Idtype()))){ //证件为身份证
					if ("2999-12-31".equals(formatInDate)) {
						/*if("01".equals(StringUtil.trimToEmpty(apply_Info2.getC2Idtype()))){ //证件为身份证
							years = riskCheckService.queryAgeByDateOfIssueC2(appId);
						}else{					
							years = riskCheckService.queryYearBetweenIssuingDateC2(appId);
						}*/
						check_IdIndate.setBaseDataValue(sf.format(idNumberInDate));
						check_IdIndate.setPriKeyValue("4");
						check_IdIndate.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);// 长期
						check_Blaze.setRiskResult("4");
						check_Blaze.setPriKeyValue("4");
						flag = true;
					} 
				}
				if("1900-01-01".equals(formatInDate)){
					 check_IdIndate.setPriKeyValue("5");
					 check_IdIndate.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);// 空
					 check_Blaze.setRiskResult("5");
					 check_Blaze.setPriKeyValue("5");
					 flag = true;
				} 
				if(!flag) {
					HashMap<Object, Object> map = new HashMap<>();
					map.put("appId", appId);
					String dateNum = "20"+appId.substring(0, 6);
					map.put("dateNum", dateNum);
					map.put("idNumberInDate", idNumberInDate);
					Integer days = riskCheckService.Query_OPAS_BIZ_IsInDateByIDindate(map);
					if (days != null) {
						if (days.intValue() > 60) {
							check_IdIndate.setBaseDataValue(sf.format(idNumberInDate));
							check_IdIndate.setPriKeyValue("3");
							check_IdIndate.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);// 未过期
							check_Blaze.setRiskResult("3");
							check_Blaze.setPriKeyValue("3");
						} else if (days.intValue() < 0) {
							check_IdIndate.setBaseDataValue(sf.format(idNumberInDate));
							check_IdIndate.setPriKeyValue("2");
							check_IdIndate.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);// 过期
							check_Blaze.setRiskResult("2");
							check_Blaze.setPriKeyValue("2");
						} else if (days.intValue() >= 0 && days.intValue() <= 60) {
							check_IdIndate.setBaseDataValue(sf.format(idNumberInDate));
							check_IdIndate.setPriKeyValue("1");
							check_IdIndate.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);// 关注
							check_Blaze.setRiskResult("1");
							check_Blaze.setPriKeyValue("1");
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

}

