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
 * 单位电话 单位地址 单位名称--是否命中 标准卡征信信息名单表(标准卡) 中信息
 * 
 * @author yang.jiangming
 *
 */
public class Step2_Field_Opas_NormCredit_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImplTest")
	private LueneService lueneService;
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneServiceO;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String,Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String,Object> params = new HashMap<String, Object>();
		// 申请人公司电话是否命中 标准卡征信信息名单表中“公司电话号码”
		 String STR_APPLY_COTEL_ID = new String("");
		// 单位地址
		 String companyAddress = new String("");
		// 单位名称
		 String companyName = new String("");
	
		// 主卡
		if ("1".equals(appType)) {
			STR_APPLY_COTEL_ID = this.trimToEmpty(apply_Info1.getC1Cotel());
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
			// 公司地址C1_COADD1
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd4()));
			companyAddress = sbaddrCom.toString();
		}
		if ("2".equals(appType)) {
			// 附卡
			STR_APPLY_COTEL_ID = this.trimToEmpty(apply_Info2.getC2Cotel());
		}
		if ("3".equals(appType)) {
			STR_APPLY_COTEL_ID = this.trimToEmpty(apply_Info1.getC1Cotel());
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
			// 公司地址C1_COADD1
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd4()));
			companyAddress = sbaddrCom.toString();
		}
		 params.put("STR_APPLY_COTEL_ID", STR_APPLY_COTEL_ID);
		 params.put("companyAddress", companyAddress);
		 params.put("companyName", companyName);
		 return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {

		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		 String STR_APPLY_COTEL_ID = (String) initParams.get("STR_APPLY_COTEL_ID");
		 String companyAddress = (String) initParams.get("companyAddress");
		 String companyName = (String) initParams.get("companyName");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		// 电话
		RiskCheckResult cotel = new RiskCheckResult();
		cotel.setRiskType(CacheConsts.RISK_TYPE_TEAM);
		cotel.setTableName("OPAS_CREDITINFO_LIST_TEMP");
		// 单位地址
		RiskCheckResult check = new RiskCheckResult();
		check.setTableName("OPAS_CREDITINFO_LIST_TEMP");
		check.setRiskType(CacheConsts.RISK_TYPE_TEAM);
		// 单位名称
		RiskCheckResult namecheck = new RiskCheckResult();
		namecheck.setTableName("OPAS_CREDITINFO_LIST_TEMP");
		namecheck.setRiskType(CacheConsts.RISK_TYPE_TEAM);
		cotel.setFieldKey("COMPANY_TEL");
		cotel.setApplyType(appType);
		check.setFieldKey("COMPANY_ADDR");
		check.setApplyType(appType);
		namecheck.setFieldKey("COMPANY_NAME");
		namecheck.setApplyType(appType);
		StringBuffer telList = new StringBuffer();
		StringBuffer addList = new StringBuffer();
		StringBuffer compList = new StringBuffer();
		List<Map<String, String>> mapCotel = riskCheckService.Query_OPAS_COTEL_RISKLIST(STR_APPLY_COTEL_ID);
		if (mapCotel != null && mapCotel.size() > 0) {
			for (Map<String, String> map : mapCotel) {
				if(lueneServiceO.getMachResult(STR_APPLY_COTEL_ID, map.get("companyTel"), "1")){
					telList.append(map.get("autoId")+"|");
				}
			}
		}
		List<Map<String, String>> addressList = riskCheckService.Query_OPAS_COMPANY_ADDRESS_RISKLIST();
		if (addressList != null && addressList.size() > 0) {
			for (Map<String, String> map : addressList) {
				if (lueneServiceO.getMachResult(companyAddress, map.get("companyAddress"), "")) {
					addList.append(map.get("autoId")+"|");

				}
			}
		}

		List<Map<String, String>> nameList = riskCheckService.Query_OPAS_COMPANY_RISKLIST();
		if (nameList != null && nameList.size() > 0) {
			for (Map<String, String> map : nameList) {
				if (lueneServiceO.getMachResult(companyName, map.get("companyName"), "")) {
					compList.append(map.get("autoId")+"|");
				}
			}
		}
		if(!"".equals(telList.toString())){
			cotel.setPriKeyValue(telList.toString());
			cotel.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
		}
		if(!"".equals(addList.toString())){
			check.setPriKeyValue(addList.toString());
			check.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
		}
		if(!"".equals(compList.toString())){
			namecheck.setPriKeyValue(compList.toString());
			namecheck.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
		}
		listriskcheck.add(namecheck);
		listriskcheck.add(check);
		listriskcheck.add(cotel);
		return listriskcheck;
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

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {
		
	}

}
