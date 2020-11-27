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
import com.huaxia.opas.domain.decision.CreditTelcheckList;
import com.huaxia.opas.domain.decision.TelcheckAddnote;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 单位电话 征信电话核查白名单 单位名称 征信电话核查白名单
 * 
 * @author qianxiwen
 *
 */
public class Step2_Field_Opas_Creait_TelCheck_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImplTest")
	private LueneService lueneService;
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneServiceO;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		String companyTel = "";
		String companyName = "";

		// 主副卡
		if ("1".equals(appType)) {
			companyTel = StringUtil.trimToEmpty(apply_Info1.getC1Cotel());
			companyName = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
		}
		// 附卡
		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			companyTel = StringUtil.trimToEmpty(apply_Info1.getC1Cotel());
			companyName = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
		}
		params.put("companyTel", companyTel);
		params.put("companyName", companyName);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String companyTel = (String) initParams.get("companyTel");
		String companyName = (String) initParams.get("companyName");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		List<CreditTelcheckList> creditTelcheckList = riskCheckService.Query_OPAS_CREDIT_TELCHECK_LIST();
		RiskCheckResult check_COMPANY_TEL = new RiskCheckResult("COMPANY_TEL", "OPAS_CREDIT_TELCHECK_LIST",
				CacheConsts.RISK_TYPE_TEAM);
		RiskCheckResult check_COMPANY_NAME = new RiskCheckResult("COMPANY_NAME", "OPAS_CREDIT_TELCHECK_LIST",
				CacheConsts.RISK_TYPE_TEAM);
		check_COMPANY_TEL.setApplyType(appType);
		check_COMPANY_NAME.setApplyType(appType);
		StringBuffer sb = new StringBuffer();
		StringBuffer sb2 = new StringBuffer();
		StringBuffer sb3 = new StringBuffer();
		if (creditTelcheckList != null && creditTelcheckList.size() > 0) {
			for (int i = 0; i < creditTelcheckList.size(); i++) {
				CreditTelcheckList tmpcreditTelcheckList = creditTelcheckList.get(i);
				if (lueneServiceO.getMachResult(companyTel, tmpcreditTelcheckList.getCompanyTel(), "1") 
						&& !"".equals(companyTel.trim())) {
					sb.append(tmpcreditTelcheckList.getAutoId()+"|");
				}
				if (lueneService.getMachResult(companyName, tmpcreditTelcheckList.getCompanyName(), "") && !"".equals(companyName.trim())) {
					sb2.append(tmpcreditTelcheckList.getAutoId()+"|");
				}
			}
			if(!"".equals(sb.toString())){
				check_COMPANY_TEL.setPriKeyValue(sb.toString());
				check_COMPANY_TEL.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_COMPANY_TEL.setBaseDataValue(companyTel);
				sb3.append("单位电话    征信电话核查白名单    是；");
			}
			if(!"".equals(sb2.toString())){
				check_COMPANY_NAME.setPriKeyValue(sb2.toString());
				check_COMPANY_NAME.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_COMPANY_NAME.setBaseDataValue(companyName);
				sb3.append("单位名称    征信电话核查白名单    是；");
			}
		}
		// 保存至电核转用(只有易达金保存  标准卡不保存 )
		if ("1".equals(apply_Info1.getYdjFlag())) {
			String talId = StringUtil.randomGUIDPlainString();
			Integer count = riskCheckService.Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(appId);
			if(count>0){ // 更新
				TelcheckAddnote tk = riskCheckService.Query_TelcheckAddnote_BY_APPID(appId);
				String olds = StringUtil.trimToEmpty(tk.getBigMemo()).replace("单位电话    征信电话核查白名单    是；", "").replace("单位名称    征信电话核查白名单    是；", "");
				sb3.append(olds);
				tk.setBigMemo(sb3.toString());
				riskCheckService.Update_OPAS_TELCHECK_ADDNOTE(tk);
			}else{ // 插入
				TelcheckAddnote tt = new TelcheckAddnote();
				tt.setAutoId(StringUtil.randomGUIDPlainString());
				tt.setAppId(appId);
				tt.setCrtUser("SYSTEM");
				tt.setBigMemo(sb3.toString());
				tt.setTypeFlag("5.5");
				tt.setTalId(talId);
				riskCheckService.insert_OPAS_TELCHECK_ADDNOTE(tt);
			}
			//0没配发卡   1是配发卡  2  是配发卡的主卡
			if (apply_Info1.getMatchingCardFlag()!="0"){
				//套卡
				String currAppIdFront=appId.substring(0,15);//流水号 前十五位
				String currAppIdLast=appId.substring(15);//流水号 最后一位
				String matchAppId=null;
				if("1".equals(currAppIdLast)){
					matchAppId=currAppIdFront+"2";
				}else{
					matchAppId=currAppIdFront+"1";
				}
				Integer count1 = riskCheckService.Query_OPAS_TELCHECK_ADDNOTE_BY_APPID(matchAppId);
				if(count1>0){ // 更新
					TelcheckAddnote tk = riskCheckService.Query_TelcheckAddnote_BY_APPID(matchAppId);
					//String olds = StringUtil.trimToEmpty(tk.getBigMemo()).replace("单位电话    征信电话核查白名单    是；", "").replace("单位名称    征信电话核查白名单    是；", "");
					//sb3.append(olds);
					tk.setBigMemo(sb3.toString());
					riskCheckService.Update_OPAS_TELCHECK_ADDNOTE(tk);
				}else{ // 插入
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
		listriskcheck.add(check_COMPANY_TEL);
		listriskcheck.add(check_COMPANY_NAME);
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

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {
		// TODO Auto-generated method stub

	}

}
