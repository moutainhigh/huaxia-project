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
import com.huaxia.opas.domain.thirdparty.PbocInfo;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 手机号码 人行
 * 
 * @author andong
 *
 */
public class Step3_Field_Opas_Pboc_Personal_Info implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		// 申请人手机号码
		String CellPhone = (String) initParams.get("CellPhone");
		Map<String, String> phoneMap = (Map<String, String>) initParams.get("phoneMap");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<PbocInfo> pbocinfolist = riskCheckService.Query_OPAS_PBOC_PERSONAL_INFO(phoneMap);
		//C_COMP_PHONE原来的代码  不做变化
		RiskCheckResult check_PHONE_NUM = new RiskCheckResult("PHONE_NUM","PBOC_PHONE_NUMBER_DETAIL",CacheConsts.RISK_TYPE_THIRDPARTY);
		//
		RiskCheckResult check_MOBILE = new RiskCheckResult("MOBILE","PBOC_PHONE_NUMBER_DETAIL",CacheConsts.RISK_TYPE_THIRDPARTY);
		
		boolean bool_PHONE_NUM = true;
		boolean bool_MOBILE = true;
		listriskcheck.add(check_PHONE_NUM);
		listriskcheck.add(check_MOBILE);
		check_PHONE_NUM.setApplyType(appType);
		check_MOBILE.setApplyType(appType);
		
		StringBuffer sb3 = new StringBuffer();
		if (!pbocinfolist.isEmpty()) {
			for (int i = 0; i < 1; i++) {
				PbocInfo tmpPbocInfo = pbocinfolist.get(i);
				String str = this.trimToEmpty(tmpPbocInfo.getPhoneNum());
				str = str.length() > 11 ? str.substring(str.length() - 11, str.length()) : str;
				CellPhone = CellPhone.length() > 11 ? CellPhone.substring(CellPhone.length() - 11, CellPhone.length())
						: CellPhone;
				if (!"".equals(CellPhone) && lueneService.getMachResult(CellPhone, str, "1") && bool_PHONE_NUM) {
					check_PHONE_NUM.setPriKeyValue(tmpPbocInfo.getAppId());
					check_PHONE_NUM.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_PHONE_NUM.setBaseDataValue(tmpPbocInfo.getCellPhone());
					bool_PHONE_NUM = false;
					sb3.append("手机号码    人行    是；");
				}
				if (!bool_PHONE_NUM)
					break;

			}
			for (int i = 0; i < pbocinfolist.size(); i++) {
				PbocInfo tmpPbocInfo = pbocinfolist.get(i);
				String str = this.trimToEmpty(tmpPbocInfo.getPhoneNum());
				str = str.length() > 11 ? str.substring(str.length() - 11, str.length()) : str;
				CellPhone = CellPhone.length() > 11 ? CellPhone.substring(CellPhone.length() - 11, CellPhone.length())
						: CellPhone;
				if (!"".equals(CellPhone) && lueneService.getMachResult(CellPhone, str, "1") && bool_MOBILE) {
					check_MOBILE.setPriKeyValue(tmpPbocInfo.getAppId());
					check_MOBILE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_MOBILE.setBaseDataValue(tmpPbocInfo.getCellPhone());
					bool_MOBILE = false;
				}
				if (!bool_MOBILE)
					break;

			}
		}
		// 保存至电核转用(只有易达金保存  标准卡不保存 )
		if ("1".equals(apply_Info1.getYdjFlag())) {
			String talId = StringUtil.randomGUIDPlainString();
			Integer count = riskCheckService.Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(appId);
			if (count > 0) { // 更新
				TelcheckAddnote tk = riskCheckService.Query_TelcheckAddnote_BY_APPID(appId);
				String olds = StringUtil.trimToEmpty(tk.getBigMemo()).replace("手机号码    人行    是；", "");
				sb3.append(olds);
				tk.setBigMemo(sb3.toString());
				riskCheckService.Update_OPAS_TELCHECK_ADDNOTE(tk);
			} else { // 插入
				if (!"".equals(sb3.toString())) {
					TelcheckAddnote tt = new TelcheckAddnote();
					tt.setAutoId(StringUtil.randomGUIDPlainString());
					tt.setAppId(appId);
					tt.setCrtUser("SYSTEM");
					tt.setBigMemo(sb3.toString());
					tt.setTypeFlag("5.5");
					tt.setTalId(talId);
					riskCheckService.insert_OPAS_TELCHECK_ADDNOTE(tt);
				}
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
				if (!"".equals(sb3.toString())) {
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
		}
		return listriskcheck;
	}

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 申请人手机号码
		String CellPhone = new String("");
		Map<String, String> phoneMap = new HashMap<String, String>();

		if ("1".equals(appType)) {
			CellPhone = this.trimToEmpty(apply_Info1.getC1Mobile());
			phoneMap.put("CellPhone", CellPhone);
			phoneMap.put("appId", appId);
		}
		//
		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			CellPhone = this.trimToEmpty(apply_Info1.getC1Mobile());
			phoneMap.put("CellPhone", CellPhone);
			phoneMap.put("appId", appId);
		}
		params.put("CellPhone", CellPhone);
		params.put("phoneMap", phoneMap);
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
