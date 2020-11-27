package com.huaxia.opas.cache.service.riskcheck.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.LueneService;
import com.huaxia.opas.cache.service.riskcheck.RiskFieldMachService;
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

public class Step1_Field_Opas_Biz_Life_Appln_RiskList implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 数据
		String projectCode = "";
		// 主副
		if ("1".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
		}
		// 附卡
		if ("2".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info2.getC2Idnbr());
		}
		// 主卡
		if ("3".equals(appType)) {
			projectCode = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
		}
		params.put("projectCode", projectCode);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String idnbr = (String) initParams.get("projectCode");
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		RiskCheckResult checkResult = new RiskCheckResult("is_life_cust", "opas_biz_life_appln", CacheConsts.RISK_TYPE_BLACK);
		checkResult.setApplyType(appType);
		listriskcheck.add(checkResult);
		List<String> hitIdnbr = null;
		String queryAppId = appId;
		//团办吗
		String apbatch = "";
		if(apply_Info1 != null){
			 queryAppId = appId;
			 apbatch = StringUtil.trimToEmpty(apply_Info1.getC4Apbatch());
		}
		KeyfiledMarchinfo kfm = new KeyfiledMarchinfo();
		kfm.setAutoId(StringUtil.randomUUIDPlainString());
		kfm.setAppId(queryAppId);
		kfm.setIs_life_cust("N");
		// 判断数据库是否已经存在对应appid记录，没有新增，有则修改
		Map<String, String> oldriskinfo = riskCheckService.Query_checkResult(queryAppId);
		// 带有团办码XXXXXX的申请件 在黑名单环节需进行“华夏人寿白名单”匹配，匹配主键为“申请人身份证号”
		if(apbatch.equals("XM0031") && !"".equals(StringUtil.trimToEmpty(idnbr))){
			//匹配华夏人寿白名单
			hitIdnbr = riskCheckService.IS_LIFE_CUST(idnbr);
		}
		if(hitIdnbr != null && hitIdnbr.size()>0){
			kfm.setIs_life_cust("Y");
			checkResult.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
			checkResult.setPriKeyValue(idnbr);
		}
		if(oldriskinfo != null && oldriskinfo.size()>0){
			// 更新数据库记录
			riskCheckService.update_checkResult(kfm);
		}else{
			// 保存入库
			riskCheckService.save_checkResult(kfm);
		}
		// 更新 opas_biz_life_appln
		kfm.setAppId(queryAppId.substring(0,appId.length()-1));
		riskCheckService.update_opas_biz_life_appln_life_cust(kfm);
		return listriskcheck;
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {

	}

}
