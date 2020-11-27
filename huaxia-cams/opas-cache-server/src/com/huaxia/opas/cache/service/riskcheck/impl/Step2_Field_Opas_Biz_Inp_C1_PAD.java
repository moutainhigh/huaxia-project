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


//pad地址单名
public class Step2_Field_Opas_Biz_Inp_C1_PAD implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;
	
	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 单位名称COMPANY
		String homAddress = new String("");
		// 主卡
		if ("1".equals(appType)) {
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd4()));
			homAddress = this.trimToEmpty(sbaddrCom.toString());
		}
		if ("2".equals(appType)) {
		}
		if ("3".equals(appType)) {
			// 家庭地址
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd4()));
			homAddress = this.trimToEmpty(sbaddrCom.toString());
		}
		params.put("homAddress", homAddress);
		return params;
	}
	
	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		//只有pad进件才匹配
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		if ("A".equals(appId.substring(6, 7))) {
			String homAddress = (String) initParams.get("homAddress");
			// 单位名称
			RiskCheckResult check = new RiskCheckResult("BASE_STATION", "CCARD_APP_PAD_ADDITION",
					CacheConsts.RISK_TYPE_TEAM);
			RiskCheckResult check_addr = new RiskCheckResult("PAD_LOCATION_ADDRESS", "OPAS_PAD_LOCATION_ADDRESS_INFO",
					CacheConsts.RISK_TYPE_TEAM);
			check.setApplyType(appType);
			check_addr.setApplyType(appType);
			
			//PAD定位地址查询
			Map<String, String> map = riskCheckService.QueryPadAddr(appId);
			if(map!=null){
				if (homAddress != null && StringUtil.deleteWhiteSpace(map.get("baseStation"))!=null) {
					String baseStation = StringUtil.deleteWhiteSpace(map.get("baseStation"));
					if (lueneService.getMachResult(homAddress, baseStation, "")) {
						check.setBaseDataValue(appId);
						check.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						check.setPriKeyValue(map.get("baseStation"));
					}
				}
				
				if (!"".equals(StringUtil.trimToEmpty(map.get("baseStation")))){
					//地址类白名单库查询  通过地区查询地址库 进行匹配
					String baseStation = map.get("baseStation").replaceAll(" +", "");
					String cityId =  appId.substring(8, 10);
					List<String> result = riskCheckService.Query_OPAS_PAD_LOCATION_ADDRESS_INFO(cityId);
					if (result != null && result.size() > 0) {
						for (String addr : result) {
							if (!"".equals(StringUtil.trimToEmpty(addr))) {
								addr = StringUtil.deleteWhiteSpace(addr);
								if (baseStation.equals(addr)) {
									// 匹配成功
									check_addr.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
									check_addr.setPriKeyValue("1");
								}
							}							
						}
					}
				}				
			}
			listriskcheck.add(check);
			listriskcheck.add(check_addr);
		}
		return listriskcheck;
			
		
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {
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
