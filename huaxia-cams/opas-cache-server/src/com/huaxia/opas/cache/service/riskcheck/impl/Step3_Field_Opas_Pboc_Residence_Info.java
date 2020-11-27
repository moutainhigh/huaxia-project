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
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.domain.riskcheck.Union3014Risk;
import com.huaxia.opas.domain.thirdparty.ResidenceInfo;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 住址地址 人行 银联账单地址和银联手机
 * 
 * @author qianxiwen
 *
 */
public class Step3_Field_Opas_Pboc_Residence_Info implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String residentAddr = (String) initParams.get("residentAddr");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<ResidenceInfo> pbocinfolist = riskCheckService.Query_OPAS_PBOC_RESIDENCE_INFO(appId);
		RiskCheckResult check_RESIDENT_ADDR = new RiskCheckResult();
		// boolean bool_RESIDENT_ADDR=true;
		check_RESIDENT_ADDR.setRiskType(CacheConsts.RISK_TYPE_THIRDPARTY);
		check_RESIDENT_ADDR.setTableName("OPAS_PBOC_RESIDENCE_INFO");
		check_RESIDENT_ADDR.setFieldKey("RESIDENT_ADDR");
		check_RESIDENT_ADDR.setApplyType(appType);

		listriskcheck.add(check_RESIDENT_ADDR);
		StringBuffer sb3 = new StringBuffer();
		if (pbocinfolist != null && pbocinfolist.size() > 0) {
			for (int i = 0; i < pbocinfolist.size(); i++) {
				ResidenceInfo tmpResidenceInfo = pbocinfolist.get(i);
				if (lueneService.getMachResult(residentAddr, tmpResidenceInfo.getResidentAddr(), "")) {
					check_RESIDENT_ADDR.setPriKeyValue(appId);
					check_RESIDENT_ADDR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_RESIDENT_ADDR.setBaseDataValue(tmpResidenceInfo.getResidentAddr());
					if ("H".equals(apply_Info1.getC4Cycadd1())) { // 邮寄地址是住宅地址
						sb3.append("账单地址    人行    是；");
					}
					break;
				}

			}
		}

		// 银联接口3014响应报文对应表(OPAS_INTERFACE_3014) 账单地址 -- 申请表账单地址 BILLING_ADDRESS
		RiskCheckResult billingAddress = new RiskCheckResult("BILLING_ADDRESS", "OPAS_INTERFACE_3014",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		billingAddress.setApplyType(appType);
		listriskcheck.add(billingAddress);
		// 银联接口3002响应报文对应表(OPAS_INTERFACE_3002)存量客户信息 手机号码 -- 申请表手机号码
		// BILLING_ADDRESS
		RiskCheckResult mobilePhone = new RiskCheckResult("MOBILEPHONE", "OPAS_INTERFACE_3002",
				CacheConsts.RISK_TYPE_THIRDPARTY);
		mobilePhone.setApplyType(appType);
		listriskcheck.add(mobilePhone);
		if (apply_Info1 != null) {
			// 判断此件的账单地址
			StringBuffer billing = new StringBuffer();
			if ("H".equals(apply_Info1.getC4Cycadd1())) { // 邮寄地址是住宅地址
				billing.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd1()));
				billing.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd2()));
				billing.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd3()));
				billing.append(StringUtil.trimToEmpty(apply_Info1.getC1Hmadd4()));

			}
			if ("B".equals(apply_Info1.getC4Cycadd1())) { // 邮寄地址是单位地址
				billing.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd1()));
				billing.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd2()));
				billing.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd3()));
				billing.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd4()));
			}
			if (!"".equals(billing.toString())) { // 查银联的账单地址
				List<Union3014Risk> billings = riskCheckService.INTERFACE_3014_Billing_Address(appId);
				if (billings != null && billings.size() > 0) {
					for (Union3014Risk union3014Risk : billings) {
						StringBuffer billing3014 = new StringBuffer();
						billing3014.append(this.trimToEmpty(union3014Risk.getAdd1()));
						billing3014.append(this.trimToEmpty(union3014Risk.getAdd2()));
						billing3014.append(this.trimToEmpty(union3014Risk.getAdd3()));
						billing3014.append(this.trimToEmpty(union3014Risk.getAdd4()));
						billing3014.append(this.trimToEmpty(union3014Risk.getAdd5()));
						if (!"".equals(billing3014.toString())) {
							if (lueneService.getMachResult(billing.toString(), billing3014.toString(), "")) {
								billingAddress.setPriKeyValue(union3014Risk.getAppId());
								billingAddress.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
								billingAddress.setBaseDataValue(billing3014.toString());
								break;
							}
						}
					}
				}
			}
			// 手机匹配
			String c1Mobile = this.trimToEmpty(apply_Info1.getC1Mobile());
			if (!"".equals(c1Mobile)) { // 查询银联手机
				List<String> unionCells = riskCheckService.INTERFACE_3002_mobilePhone(appId);
				c1Mobile = c1Mobile.length() > 11 ? c1Mobile.substring(c1Mobile.length() - 11, c1Mobile.length())
						: c1Mobile;
				if (unionCells != null && unionCells.size() > 0) {
					for (String cell : unionCells) {
						cell = this.trimToEmpty(cell);
						cell = cell.length() > 11 ? cell.substring(cell.length() - 11, cell.length()) : cell;
						if (lueneService.getMachResult(c1Mobile, cell, "1")) {
							mobilePhone.setPriKeyValue(appId);
							mobilePhone.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
							mobilePhone.setBaseDataValue(cell);
							break;
						}
					}
				}
			}
		}
		if (apply_Info2 != null) {
			// 手机匹配
			String c1Mobile = this.trimToEmpty(apply_Info2.getC2Mobile());
			if (!"".equals(c1Mobile)) { // 查询银联手机
				List<String> unionCells = riskCheckService.INTERFACE_3002_mobilePhone(appId);
				c1Mobile = c1Mobile.length() > 11 ? c1Mobile.substring(c1Mobile.length() - 11, c1Mobile.length())
						: c1Mobile;
				if (unionCells != null && unionCells.size() > 0) {
					for (String cell : unionCells) {
						cell = this.trimToEmpty(cell);
						cell = cell.length() > 11 ? cell.substring(cell.length() - 11, cell.length()) : cell;
						if (lueneService.getMachResult(c1Mobile, cell, "1")) {
							mobilePhone.setPriKeyValue(appId);
							mobilePhone.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
							mobilePhone.setBaseDataValue(cell);
							break;
						}
					}
				}
			}
		}

		// 保存至电核转用(只有易达金保存  标准卡不保存 )
		if ("1".equals(apply_Info1.getYdjFlag())) {
			String talId = StringUtil.randomGUIDPlainString();
			Integer count = riskCheckService.Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(appId);
			if (count > 0) { // 更新
				TelcheckAddnote tk = riskCheckService.Query_TelcheckAddnote_BY_APPID(appId);
				String olds = StringUtil.trimToEmpty(tk.getBigMemo());
				if ("H".equals(apply_Info1.getC4Cycadd1())) { // 邮寄地址是住宅地址
					olds = olds.replace("账单地址    人行    是；", "");
				}
				sb3.append(olds);
				tk.setBigMemo(sb3.toString());
				riskCheckService.Update_OPAS_TELCHECK_ADDNOTE(tk);
			} else { // 插入
				TelcheckAddnote tt = new TelcheckAddnote();
				tt.setAutoId(StringUtil.randomGUIDPlainString());
				tt.setAppId(appId);
				tt.setCrtUser("SYSTEM");
				tt.setBigMemo(sb3.toString());
				tt.setTypeFlag("5.5");
				tt.setTalId(talId);
				riskCheckService.insert_OPAS_TELCHECK_ADDNOTE(tt);
			}
			// 套卡
			String currAppIdFront = appId.substring(0, 15);// 流水号 前十五位
			String currAppIdLast = appId.substring(15);// 流水号 最后一位
			String matchAppId = null;
			if ("1".equals(currAppIdLast)) {
				matchAppId = currAppIdFront + "2";
			} else {
				matchAppId = currAppIdFront + "1";
			}
			Integer count1 = riskCheckService.Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(matchAppId);
			if (count1 > 0) { // 更新
				TelcheckAddnote tk = riskCheckService.Query_TelcheckAddnote_BY_APPID(matchAppId);
				tk.setBigMemo(sb3.toString());
				riskCheckService.Update_OPAS_TELCHECK_ADDNOTE(tk);
			} else { // 插入
				TelcheckAddnote tt = new TelcheckAddnote();
				tt.setAutoId(StringUtil.randomGUIDPlainString());
				tt.setAppId(matchAppId);
				tt.setCrtUser("SYSTEM");
				tt.setBigMemo(sb3.toString());
				tt.setTypeFlag("5.5");
				tt.setTalId(talId);
				riskCheckService.insert_OPAS_TELCHECK_ADDNOTE(tt);
			}
		}
		return listriskcheck;
	}

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 住宅地址RESIDENT_ADDR
		String residentAddr = new String("");
		// 主卡
		if ("1".equals(appType)) {
			// 家庭地址
			StringBuffer sbaddrHm = new StringBuffer();
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd1()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd2()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd3()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd4()));
			residentAddr = this.trimToEmpty(sbaddrHm.toString());
		}
		if ("2".equals(appType)) {

		}
		if ("3".equals(appType)) {
			// 家庭地址
			StringBuffer sbaddrHm = new StringBuffer();
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd1()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd2()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd3()));
			sbaddrHm.append(this.trimToEmpty(apply_Info1.getC1Hmadd4()));
			residentAddr = this.trimToEmpty(sbaddrHm.toString());
		}
		params.put("residentAddr", residentAddr);
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
		// TODO Auto-generated method stub

	}
}
