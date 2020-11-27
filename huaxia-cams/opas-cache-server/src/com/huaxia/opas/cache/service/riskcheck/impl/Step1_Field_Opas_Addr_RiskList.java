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
import com.huaxia.opas.domain.riskcheck.AddrRiskList;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 申请人地址与黑名单比较
 * 
 * @author qianxiwen
 *
 */
public class Step1_Field_Opas_Addr_RiskList implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;
	
	public Map<String,Object> initParam(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2,
			String appType) throws CoreException {
		Map<String,Object> params = new HashMap<String, Object>();
		// 申请人所有地址
		List<String> LIST_APPLY_ALL_ADDR = new ArrayList<String>();
		// 主副
		if ("1".equals(appType)) {// C1_HMADD1
			// 家庭地址
			RiskCheck_Apply_C1 toDept = riskCheckService.Query_For_Depot(appId);
			StringBuffer sbaddrHm = new StringBuffer();
			sbaddrHm.append(StringUtil.trimToEmpty(toDept.getC1Hmadd1()));
			sbaddrHm.append(StringUtil.trimToEmpty(toDept.getC1Hmadd2()));
			sbaddrHm.append(StringUtil.trimToEmpty(toDept.getC1Hmadd3()));
			sbaddrHm.append(StringUtil.trimToEmpty(toDept.getC1Hmadd4()));
			// 公司地址C1_COADD1
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(StringUtil.trimToEmpty(toDept.getC1Coadd1()));
			sbaddrCom.append(StringUtil.trimToEmpty(toDept.getC1Coadd2()));
			sbaddrCom.append(StringUtil.trimToEmpty(toDept.getC1Coadd3()));
			sbaddrCom.append(StringUtil.trimToEmpty(toDept.getC1Coadd4()));
			if (!"".equals(sbaddrHm.toString())) {
				//LIST_APPLY_ALL_ADDR.add(this.regex_match(sbaddrHm.toString()));
				LIST_APPLY_ALL_ADDR.add(sbaddrHm.toString());
			}
			if (!"".equals(sbaddrCom.toString())) {
				//LIST_APPLY_ALL_ADDR.add(this.regex_match(sbaddrCom.toString()));
				LIST_APPLY_ALL_ADDR.add(sbaddrCom.toString());
			}
		}
		// 附卡
		if ("2".equals(appType)) {// C1_HMADD1

		}
		// 主卡
		if ("3".equals(appType)) {// C1_HMADD1
			// 家庭地址
			RiskCheck_Apply_C1 toDept = riskCheckService.Query_For_Depot(appId);
			StringBuffer sbaddrHm = new StringBuffer();
			sbaddrHm.append(StringUtil.trimToEmpty(toDept.getC1Hmadd1()));
			sbaddrHm.append(StringUtil.trimToEmpty(toDept.getC1Hmadd2()));
			sbaddrHm.append(StringUtil.trimToEmpty(toDept.getC1Hmadd3()));
			sbaddrHm.append(StringUtil.trimToEmpty(toDept.getC1Hmadd4()));
			// 公司地址C1_COADD1
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(StringUtil.trimToEmpty(toDept.getC1Coadd1()));
			sbaddrCom.append(StringUtil.trimToEmpty(toDept.getC1Coadd2()));
			sbaddrCom.append(StringUtil.trimToEmpty(toDept.getC1Coadd3()));
			sbaddrCom.append(StringUtil.trimToEmpty(toDept.getC1Coadd4()));
			if (!"".equals(sbaddrHm.toString())) {
				//LIST_APPLY_ALL_ADDR.add(this.regex_match(sbaddrHm.toString()));
				LIST_APPLY_ALL_ADDR.add(sbaddrHm.toString());
			}
			if (!"".equals(sbaddrCom.toString().trim())) {
				//LIST_APPLY_ALL_ADDR.add(this.regex_match(sbaddrCom.toString()));
				LIST_APPLY_ALL_ADDR.add(sbaddrCom.toString());
			}
		}
		params.put("LIST_APPLY_ALL_ADDR", LIST_APPLY_ALL_ADDR);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParam = this.initParam(appId, apply_Info1, apply_Info2, appType);
		List<String> LIST_APPLY_ALL_ADDR = (List<String>) initParam.get("LIST_APPLY_ALL_ADDR");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<AddrRiskList> addrisklist = null;
		if (LIST_APPLY_ALL_ADDR != null && LIST_APPLY_ALL_ADDR.size() > 0) {
			 addrisklist = riskCheckService.Query_OPAS_ADDR_RISKLIST(LIST_APPLY_ALL_ADDR);
		}
		// 申请人所有地址是否命中单位地址类黑名单库
		RiskCheckResult check_APPLY_COMPANY_ADDR = new RiskCheckResult();
		boolean bool_APPLY_COMPANY_ADDR = true;
		// 申请人所有地址是否命中住宅地址类黑名单库
		RiskCheckResult check_APPLY_ALL_ADDR_LIVING_ADDR = new RiskCheckResult();
		check_APPLY_COMPANY_ADDR.setFieldKey("APPLY_COMPANY_ADDR");
		check_APPLY_COMPANY_ADDR.setRiskType(CacheConsts.RISK_TYPE_BLACK);
		check_APPLY_COMPANY_ADDR.setTableName("OPAS_ADDR_RISKLIST");
		check_APPLY_ALL_ADDR_LIVING_ADDR.setFieldKey("APPLY_ALL_ADDR_LIVING_ADDR");
		check_APPLY_ALL_ADDR_LIVING_ADDR.setRiskType(CacheConsts.RISK_TYPE_BLACK);
		check_APPLY_ALL_ADDR_LIVING_ADDR.setTableName("OPAS_ADDR_RISKLIST");
		boolean bool_APPLY_ALL_ADDR_LIVING_ADDR = true;
		check_APPLY_COMPANY_ADDR.setApplyType(appType);
		check_APPLY_ALL_ADDR_LIVING_ADDR.setApplyType(appType);

		if (addrisklist != null && addrisklist.size() > 0 && LIST_APPLY_ALL_ADDR!=null && LIST_APPLY_ALL_ADDR.size()>0) {
			for (int i = 0; i < addrisklist.size(); i++) {
				AddrRiskList tmpaddrisk = addrisklist.get(i);
				for (int j = 0; j < LIST_APPLY_ALL_ADDR.size(); j++) {
				/*if (lueneService.getMachResult(tmpaddrisk.getCompanyAddr(), LIST_APPLY_ALL_ADDR.get(j), "")
							&& bool_APPLY_COMPANY_ADDR) {
						check_APPLY_COMPANY_ADDR.setPriKeyValue(tmpaddrisk.getAutoId());
						check_APPLY_COMPANY_ADDR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						check_APPLY_COMPANY_ADDR.setBaseDataValue(LIST_APPLY_ALL_ADDR.get(j));
						bool_APPLY_COMPANY_ADDR = false;
					}
					if (lueneService.getMachResult(tmpaddrisk.getLivingAddr(), LIST_APPLY_ALL_ADDR.get(j), "")
							&& bool_APPLY_ALL_ADDR_LIVING_ADDR) {
						check_APPLY_ALL_ADDR_LIVING_ADDR.setPriKeyValue(tmpaddrisk.getAutoId());
						check_APPLY_ALL_ADDR_LIVING_ADDR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						check_APPLY_ALL_ADDR_LIVING_ADDR.setBaseDataValue(LIST_APPLY_ALL_ADDR.get(j));
						bool_APPLY_ALL_ADDR_LIVING_ADDR = false;
					}*/
					if(LIST_APPLY_ALL_ADDR.get(j).equals(tmpaddrisk.getCompanyAddr()) && bool_APPLY_COMPANY_ADDR){
						check_APPLY_COMPANY_ADDR.setPriKeyValue(tmpaddrisk.getAutoId());
						check_APPLY_COMPANY_ADDR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						check_APPLY_COMPANY_ADDR.setBaseDataValue(LIST_APPLY_ALL_ADDR.get(j));
						bool_APPLY_COMPANY_ADDR = false;
					}
					if(LIST_APPLY_ALL_ADDR.get(j).equals(tmpaddrisk.getLivingAddr()) && bool_APPLY_ALL_ADDR_LIVING_ADDR){
						check_APPLY_ALL_ADDR_LIVING_ADDR.setPriKeyValue(tmpaddrisk.getAutoId());
						check_APPLY_ALL_ADDR_LIVING_ADDR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
						check_APPLY_ALL_ADDR_LIVING_ADDR.setBaseDataValue(LIST_APPLY_ALL_ADDR.get(j));
						bool_APPLY_ALL_ADDR_LIVING_ADDR = false;
					}
					if (!bool_APPLY_COMPANY_ADDR && !bool_APPLY_ALL_ADDR_LIVING_ADDR) {
						break;
					}
				}

			}
		}
		listriskcheck.add(check_APPLY_COMPANY_ADDR);
		listriskcheck.add(check_APPLY_ALL_ADDR_LIVING_ADDR);
		LIST_APPLY_ALL_ADDR.clear();
		return listriskcheck;
	}
	
	private  String regex_match(String str) {
		String result = "";
		String regex = "[`~!@#$%^&*()+=|{}':;',[-]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p1 = Pattern.compile(regex);
		Matcher matcher = p1.matcher(str);
		result = matcher.replaceAll("").trim();
		return result;
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
