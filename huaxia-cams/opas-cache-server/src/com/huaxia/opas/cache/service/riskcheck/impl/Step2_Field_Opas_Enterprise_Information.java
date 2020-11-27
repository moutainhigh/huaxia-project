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
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;
/**
 * 增加“企业网” 验证信息内容不变，
 * 当“单位名称、单位地址、单位电话”
 * 返回信息与申请表一致时，在该列内容显示“是”并标红，
 * @author yang JiangMIng
 *
 */
public class Step2_Field_Opas_Enterprise_Information implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;


	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		//单位地址
		StringBuffer comAddr = new StringBuffer();
		// 单位名称
		String comCname = "";
		// 单位电话
		String comTel = "";
		if ("1".equals(appType)) {
			comAddr.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd1()));
			comAddr.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd2()));
			comAddr.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd3()));
			comAddr.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd4()));
			comCname = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
			comTel = StringUtil.trimToEmpty(apply_Info1.getC1Cotel());
		}
		if ("2".equals(appType)) {
		}
		if ("3".equals(appType)) {
			comAddr.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd1()));
			comAddr.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd2()));
			comAddr.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd3()));
			comAddr.append(StringUtil.trimToEmpty(apply_Info1.getC1Coadd4()));
			comCname = StringUtil.trimToEmpty(apply_Info1.getC1Coname());
			comTel = StringUtil.trimToEmpty(apply_Info1.getC1Cotel());
		}
		params.put("comAddr", comAddr.toString());
		params.put("comCname", comCname);
		params.put("comTel", comTel);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		Map<String, Object> initParams = initParams(appId, apply_Info1, apply_Info2, appType);
		String comAddr = (String)initParams.get("comAddr");
		String comCname = (String)initParams.get("comCname");
		String comTel = (String)initParams.get("comTel");
		// 查看企业是否为正常营业状态
		List<Map<String,String>> enterpriseStatus = riskCheckService.Query_Enterprise_Status(appId);
		// 初始化结果
		RiskCheckResult check_comAddr = new RiskCheckResult("DOM", "TRD_QYHY_INFO_DATA", CacheConsts.RISK_TYPE_TEAM);
		check_comAddr.setApplyType(appType);
		RiskCheckResult check_comCname = new RiskCheckResult("ENTNAME", "TRD_QYHY_INFO_DATA", CacheConsts.RISK_TYPE_TEAM);
		check_comCname.setApplyType(appType);
		RiskCheckResult check_comtel = new RiskCheckResult("TEL", "TRD_QYHY_INFO_DATA", CacheConsts.RISK_TYPE_TEAM);
		check_comtel.setApplyType(appType);
		// 企业为正常营业状态时匹配信息
		if(enterpriseStatus != null && enterpriseStatus.size()>0){
			// 查询企业信息
			List<Map<String,String>> information = riskCheckService.Query_Enterprise_Information(appId);
			// 匹配
			if(information != null && information.size()>0){
				Map<String, String> map = information.get(0);
				if (lueneService.getMachResult(comAddr, StringUtil.trimToEmpty(map.get("DOM")), "")) {
					check_comAddr.setPriKeyValue(comAddr);
					check_comAddr.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_comAddr.setBaseDataValue(comAddr);
				}
				if (lueneService.getMachResult(comCname, StringUtil.trimToEmpty(map.get("ENTNAME")), "")) {
					check_comCname.setPriKeyValue(comCname);
					check_comCname.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_comCname.setBaseDataValue(comCname);
				}
				if (lueneService.getMachResult(comTel, StringUtil.trimToEmpty(map.get("TEL")), "1")) {
					check_comtel.setPriKeyValue(comAddr);
					check_comtel.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_comtel.setBaseDataValue(comAddr);
				}
			}
		}
		// 返回结果
		listriskcheck.add(check_comtel);
		listriskcheck.add(check_comCname);
		listriskcheck.add(check_comAddr);
		return listriskcheck;
	}

	private String regex_match(String str) {
		String result = "";
		String regex = "[`~!@#$%^&*()+=|{}':;',[-]\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p1 = Pattern.compile(regex);
		Matcher matcher = p1.matcher(str);
		result = matcher.replaceAll("").trim();
		return result;
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
