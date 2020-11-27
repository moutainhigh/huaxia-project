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
import com.huaxia.opas.domain.riskcheck.IfsCustInfo;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;

/**
 * 手机号码 crm 单位电话 crm 单位名称 crm 单位地址 crm 住址地址 crm
 * 
 * @author qianxiwen
 *
 */
public class Step3_Field_Sp_Aps_Ifs_Cust_Info implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 申请人手机号码
		String CellPhone = new String("");
		// 申请人单位电话
		String CompPhone = new String("");
		// 单位名称COMPANY
		String companyName = new String("");
		// 单位地址
		String companyaddress = new String("");
		// 住宅地址RESIDENT_ADDR
		String residentAddr = new String("");

		Map<String, String> phoneMap = new HashMap<String, String>();

		// 主卡
		if ("1".equals(appType)) {
			CellPhone = this.trimToEmpty(apply_Info1.getC1Mobile());
			CompPhone = this.trimToEmpty(apply_Info1.getC1Cotel());
			phoneMap.put("CellPhone", CellPhone);
			phoneMap.put("CompPhone", CompPhone);
			phoneMap.put("appId", appId);
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd4()));
			companyaddress = this.trimToEmpty(sbaddrCom.toString());
			// 住宅地址
			StringBuffer sbaddrHm = new StringBuffer();
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd1()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd2()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd3()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd4()));
			residentAddr = this.trimToEmpty(sbaddrHm.toString());
		}

		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			CellPhone = this.trimToEmpty(apply_Info1.getC1Mobile());
			CompPhone = this.trimToEmpty(apply_Info1.getC1Cotel());
			phoneMap.put("CellPhone", CellPhone);
			phoneMap.put("CompPhone", CompPhone);
			phoneMap.put("appId", appId);
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd4()));
			companyaddress = this.trimToEmpty(sbaddrCom.toString());
			// 住宅地址
			StringBuffer sbaddrHm = new StringBuffer();
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd1()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd2()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd3()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd4()));
			residentAddr = this.trimToEmpty(sbaddrHm.toString());
		}
		params.put("CellPhone", CellPhone);
		params.put("CompPhone", CompPhone);
		params.put("companyName", companyName);
		params.put("companyaddress", companyaddress);
		params.put("residentAddr", residentAddr);
		params.put("phoneMap", phoneMap);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String CellPhone = (String) initParams.get("CellPhone");
		String CompPhone = (String) initParams.get("CompPhone");
		String companyName = (String) initParams.get("companyName");
		String companyaddress = (String) initParams.get("companyaddress");
		String residentAddr = (String) initParams.get("residentAddr");
		Map<String, String> phoneMap = (Map<String, String>) initParams.get("phoneMap");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		boolean bool_MOBILEPHONE = true;
		boolean bool_BUSPHONE = true;
		boolean bool_COMPANY = true;
		boolean bool_BUSADDR = true;
		boolean bool_HOMEADDR = true;
		RiskCheckResult check_MOBILEPHONE = new RiskCheckResult("MOBILEPHONE", "SP_APS_IFS_CUST_INFO",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_MOBILEPHONE.setApplyType(appType);
		RiskCheckResult check_BUSPHONE = new RiskCheckResult("BUSPHONE", "SP_APS_IFS_CUST_INFO",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_BUSPHONE.setApplyType(appType);
		RiskCheckResult check_COMPANY = new RiskCheckResult("COMPANY", "SP_APS_IFS_CUST_INFO",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_COMPANY.setApplyType(appType);
		RiskCheckResult check_BUSADDR = new RiskCheckResult("BUSADDR", "SP_APS_IFS_CUST_INFO",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_BUSADDR.setApplyType(appType);
		RiskCheckResult check_HOMEADDR = new RiskCheckResult("HOMEADDR", "SP_APS_IFS_CUST_INFO",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		check_HOMEADDR.setApplyType(appType);
		listriskcheck.add(check_MOBILEPHONE);
		listriskcheck.add(check_BUSPHONE);
		listriskcheck.add(check_COMPANY);
		listriskcheck.add(check_BUSADDR);
		listriskcheck.add(check_HOMEADDR);
		List<IfsCustInfo> listIfsCustInfophone = riskCheckService.Query_SP_APS_IFS_CUST_INFO_PHONE(phoneMap);
//		List<IfsCustInfo> listIfsCustInfoaddr = riskCheckService.Query_SP_APS_IFS_CUST_INFO_ADDR(appId);
		for (int i = 0; i < listIfsCustInfophone.size(); i++) {
			IfsCustInfo tmpIfsCustInfo = listIfsCustInfophone.get(i);
			if (CellPhone.equals(tmpIfsCustInfo.getMobilephone()) && bool_MOBILEPHONE) {
				check_MOBILEPHONE.setPriKeyValue(tmpIfsCustInfo.getMobilephone());
				check_MOBILEPHONE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_MOBILEPHONE.setBaseDataValue(tmpIfsCustInfo.getMobilephone());
				bool_MOBILEPHONE = false;
			}
			if (CompPhone.equals(tmpIfsCustInfo.getBusphone()) && bool_BUSPHONE) {
				check_BUSPHONE.setPriKeyValue(tmpIfsCustInfo.getBusphone());
				check_BUSPHONE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_BUSPHONE.setBaseDataValue(tmpIfsCustInfo.getBusphone());
				bool_BUSPHONE = false;
			}
			if (lueneService.getMachResult(companyName, tmpIfsCustInfo.getCompany(), "") && !"".equals(companyName)
					&& bool_COMPANY) {
				check_COMPANY.setPriKeyValue(tmpIfsCustInfo.getAppId());
				check_COMPANY.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_COMPANY.setBaseDataValue(tmpIfsCustInfo.getCompany());
				bool_COMPANY = false;
			}
			if (lueneService.getMachResult(companyaddress, tmpIfsCustInfo.getBusaddr(), "")
					&& !"".equals(companyaddress) && bool_BUSADDR) {
				check_BUSADDR.setPriKeyValue(tmpIfsCustInfo.getAppId());
				check_BUSADDR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_BUSADDR.setBaseDataValue(tmpIfsCustInfo.getBusaddr());
				bool_BUSADDR = false;
			}
			if (lueneService.getMachResult(residentAddr, tmpIfsCustInfo.getHomeaddr(), "") && !"".equals(residentAddr)
					&& bool_HOMEADDR) {
				check_HOMEADDR.setPriKeyValue(tmpIfsCustInfo.getAppId());
				check_HOMEADDR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_HOMEADDR.setBaseDataValue(tmpIfsCustInfo.getHomeaddr());
				bool_HOMEADDR = false;
			}
			if (!bool_BUSPHONE && !bool_MOBILEPHONE && !bool_COMPANY && !bool_BUSADDR && !bool_HOMEADDR) {
				break;
			}
		}
		return listriskcheck;
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
