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
import com.huaxia.opas.domain.thirdparty.ProfessionInfo;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 单位名称 人行 单位地址 人行 单位电话
 * 
 * @author andong
 *
 */
public class Step3_Field_Opas_Pboc_Profession_Info implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String companyName = (String) initParams.get("companyName");
		// 单位地址
		String companyaddress = (String) initParams.get("companyaddress");
		// 申请人单位电话
		String CompPhone = (String) initParams.get("CompPhone");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<ProfessionInfo> pbocinfolist = riskCheckService.Query_OPAS_PBOC_PROFESSION_INFO(appId);
		RiskCheckResult check_COMPANY = new RiskCheckResult("COMPANY","OPAS_PBOC_PROFESSION_INFO",CacheConsts.RISK_TYPE_THIRDPARTY);
		boolean bool_COMPANY = true;
		RiskCheckResult check_COMP_ADDR = new RiskCheckResult("COMP_ADDR","OPAS_PBOC_PROFESSION_INFO",CacheConsts.RISK_TYPE_THIRDPARTY);
		boolean bool_COMP_ADDR = true;
		//C_COMP_PHONE原来的代码  不做变化
		RiskCheckResult check_UNIT_PHONE = new RiskCheckResult("UNIT_PHONE","OPAS_PBOC_PROFESSION_INFO",CacheConsts.RISK_TYPE_THIRDPARTY);
		boolean bool_UNIT_PHONE = true;
		listriskcheck.add(check_COMPANY);
		listriskcheck.add(check_COMP_ADDR);
		listriskcheck.add(check_UNIT_PHONE);
		check_COMPANY.setApplyType(appType);
		check_COMP_ADDR.setApplyType(appType);
		check_UNIT_PHONE.setApplyType(appType);

		StringBuffer sb3 = new StringBuffer();
		if (pbocinfolist != null) {
			for (int i = 0; i < pbocinfolist.size(); i++) {
				ProfessionInfo tmpProfessionInfo = pbocinfolist.get(i);
				if (lueneService.getMachResult(companyName, tmpProfessionInfo.getCompany(), "") && bool_COMPANY) {
					check_COMPANY.setPriKeyValue(appId);
					check_COMPANY.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_COMPANY.setBaseDataValue(tmpProfessionInfo.getCompany());
					bool_COMPANY = false;
					sb3.append("单位名称    人行    是；");
				}
				if (lueneService.getMachResult(companyaddress, tmpProfessionInfo.getCompAddr(), "") && bool_COMP_ADDR) {
					check_COMP_ADDR.setPriKeyValue(appId);
					check_COMP_ADDR.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_COMP_ADDR.setBaseDataValue(tmpProfessionInfo.getCompany());
					bool_COMP_ADDR = false;
					sb3.append("单位地址    人行    是；");
					if ("B".equals(apply_Info1.getC4Cycadd1())) { // 邮寄地址是单位地址
						sb3.append("账单地址    人行    是；");
					}
				}
				if (!"".equals(CompPhone) && lueneService.getMachResult(CompPhone, pbocinfolist.get(0).getUnitPhone(), "1")
						&& bool_UNIT_PHONE) {
					check_UNIT_PHONE.setPriKeyValue(appId);
					check_UNIT_PHONE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_UNIT_PHONE.setBaseDataValue(tmpProfessionInfo.getUnitPhone());
					bool_UNIT_PHONE = false;
					sb3.append("单位电话    人行    是；");
				}
				if (!bool_COMP_ADDR && !bool_COMPANY && !bool_UNIT_PHONE) {
					break;
				}
			}
		}
		// 保存至电核转用(只有易达金保存  标准卡不保存 )
		if ("1".equals(apply_Info1.getYdjFlag())) {
			String talId = StringUtil.randomGUIDPlainString();
			Integer count = riskCheckService.Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(appId);
			if (count > 0) { // 更新
				TelcheckAddnote tk = riskCheckService.Query_TelcheckAddnote_BY_APPID(appId);
				String olds = StringUtil.trimToEmpty(tk.getBigMemo()).replace("单位名称    人行    是；", "").replace("单位地址    人行    是；", "").replace("单位电话    人行    是；", "");
				if ("B".equals(apply_Info1.getC4Cycadd1())) { // 邮寄地址是单位地址
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
		// 单位名称COMPANY
		String companyName = new String("");
		// 单位地址
		String companyaddress = new String("");
		// 申请人单位电话
		String CompPhone = new String("");
		// 主卡
		if ("1".equals(appType)) {
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
			CompPhone = this.trimToEmpty(apply_Info1.getC1Cotel());
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd4()));
			companyaddress = this.trimToEmpty(sbaddrCom.toString());
		}
		if ("2".equals(appType)) {

		}
		if ("3".equals(appType)) {
			companyName = this.trimToEmpty(apply_Info1.getC1Coname());
			CompPhone = this.trimToEmpty(apply_Info1.getC1Cotel());
			StringBuffer sbaddrCom = new StringBuffer();
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd1()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd2()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd3()));
			sbaddrCom.append(this.trimToEmpty(apply_Info1.getC1Coadd4()));
			companyaddress = this.trimToEmpty(sbaddrCom.toString());
		}
		params.put("companyName", companyName);
		params.put("companyaddress", companyaddress);
		params.put("CompPhone", CompPhone);
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
