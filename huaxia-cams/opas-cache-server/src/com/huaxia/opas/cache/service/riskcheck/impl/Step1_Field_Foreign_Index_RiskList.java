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
import com.huaxia.opas.domain.riskcheck.KeyfiledMarchinfo;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

public class Step1_Field_Foreign_Index_RiskList implements RiskFieldMachService {

	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		// 数据
		String c1Idnbr="";
		String c1Cname="";
		String c1Idtype="";
		
		String c2Idnbr="";
		String c2Cname="";
		String c2Idtype="";
		// 主副
		if ("1".equals(appType)) {
			c1Idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
			c1Cname = StringUtil.trimToEmpty(apply_Info1.getC1Cname());
			c1Idtype = StringUtil.trimToEmpty(apply_Info1.getC1Idtype());
		}
		// 附卡
		if ("2".equals(appType)||"1".equals(appType)) {
			c2Idnbr = StringUtil.trimToEmpty(apply_Info2.getC2Idnbr());
			c2Cname = StringUtil.trimToEmpty(apply_Info2.getC2Cname());
			c2Idtype = StringUtil.trimToEmpty(apply_Info2.getC2Idtype());
		}
		// 主卡
		if ("3".equals(appType)) {
			c1Idnbr = StringUtil.trimToEmpty(apply_Info1.getC1Idnbr());
			c1Cname = StringUtil.trimToEmpty(apply_Info1.getC1Cname());
			c1Idtype = StringUtil.trimToEmpty(apply_Info1.getC1Idtype());
		}
		params.put("c1Idnbr", c1Idnbr);
		params.put("c1Cname", c1Cname);
		params.put("c1Idtype", c1Idtype);
		params.put("c2Idnbr", c2Idnbr);
		params.put("c2Cname", c2Cname);
		params.put("c2Idtype", c2Idtype);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String c1Idnbr = (String) initParams.get("c1Idnbr");
		String c1Cname = (String) initParams.get("c1Cname");
		String c1Idtype = (String) initParams.get("c1Idtype");
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("c1Idnbr", c1Idnbr);
		params.put("c1Cname", c1Cname);
		params.put("c1Idtype", c1Idtype);
		
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		RiskCheckResult checkResult = new RiskCheckResult("AML", "FOREIGN_INDEX", CacheConsts.RISK_TYPE_BLACK);
		RiskCheckResult checkLevel = new RiskCheckResult("LEVEL", "FOREIGN_INDEX", CacheConsts.RISK_TYPE_BLACK);
		RiskCheckResult checkResult2 = new RiskCheckResult("AML2", "FOREIGN_INDEX", CacheConsts.RISK_TYPE_BLACK);
		RiskCheckResult checkLevel2 = new RiskCheckResult("LEVEL2", "FOREIGN_INDEX", CacheConsts.RISK_TYPE_BLACK);

		checkResult.setApplyType(appType);
		checkLevel.setApplyType(appType);
		listriskcheck.add(checkResult);
		listriskcheck.add(checkLevel);
		
		checkResult2.setApplyType(appType);
		checkLevel2.setApplyType(appType);
		listriskcheck.add(checkResult2);
		listriskcheck.add(checkLevel2);
		List<String> hitIdnbr = null;
		List<String> hitIdnbr2 = null;
		String risklevel = null;
		String risklevel2 = null;
		String queryAppId = appId;
		if (apply_Info1 != null) {
			queryAppId = appId;
		}
		KeyfiledMarchinfo kfm = new KeyfiledMarchinfo();
		kfm.setAutoId(StringUtil.randomUUIDPlainString());
		kfm.setAml("0");
		kfm.setAppId(queryAppId);
		// 判断数据库是否已经存在对应appid记录，没有新增，有则修改
		Map<String, String> oldriskinfo = riskCheckService.Query_checkResult(queryAppId);
		if(!("2").equals(appType)){
			if(!"".equals(StringUtil.trimToEmpty(c1Idnbr))){
				//根据身份证号码 姓名 查出来证件类型
				//反洗钱匹配  身份证号码 证件号码 姓名 全部命中 算命中
				hitIdnbr = riskCheckService.FOREIGN_INDEX_AML(params);
				risklevel = riskCheckService.selectRiskLevel(c1Idnbr);
			}
			if(hitIdnbr != null && hitIdnbr.size()>0){
				String cardType = hitIdnbr.get(0);
				if("51".equals(cardType)&&"01".equals(c1Idtype)){
					kfm.setAml("1");
					checkResult.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					checkResult.setPriKeyValue(c1Idnbr);
				}
				if("52".equals(cardType)&&"03".equals(c1Idtype)){
					kfm.setAml("1");
					checkResult.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					checkResult.setPriKeyValue(c1Idnbr);
				}
				if("01".equals(cardType)&&!"01".equals(c1Idtype)&&!"03".equals(c1Idtype)){
					kfm.setAml("1");
					checkResult.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					checkResult.setPriKeyValue(c1Idnbr);
				}
			}
			//客户风险等级
			if(risklevel != null && !risklevel.isEmpty()){
				checkLevel.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				checkLevel.setPriKeyValue(risklevel);
			}
			if(oldriskinfo != null && oldriskinfo.size()>0){
				// 更新数据库记录
				riskCheckService.update_checkResult(kfm);
			}else{
				// 保存入库
				riskCheckService.save_checkResult(kfm);
			}
		}
		
		if(!("3").equals(appType)){
			String c2Idnbr = (String) initParams.get("c2Idnbr");
			String c2Idtype = (String) initParams.get("c2Idtype");
			String c2Cname = (String) initParams.get("c2Cname");
			
			/*Map<String, Object> param = new HashMap<String, Object>();*/
			params.put("c1Idnbr", c2Idnbr);
			params.put("c1Cname", c2Cname);
			params.put("c1Idtype", c2Idtype);
			
			
			if(!"".equals(StringUtil.trimToEmpty(c2Idnbr))){
				hitIdnbr2 = riskCheckService.FOREIGN_INDEX_AML(params);
				risklevel2 = riskCheckService.selectRiskLevel(c2Idnbr);
			}
			if(hitIdnbr2 != null && hitIdnbr2.size()>0){
				String cardType = hitIdnbr2.get(0);
				if("51".equals(cardType)&&"01".equals(c2Idtype)){
					checkResult2.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					checkResult2.setPriKeyValue(c2Idnbr);
				}
				if("52".equals(cardType)&&"03".equals(c2Idtype)){
					checkResult2.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					checkResult2.setPriKeyValue(c2Idnbr);
				}
				if("01".equals(cardType)&&!"01".equals(c2Idtype)&&!"03".equals(c2Idtype)){
					checkResult2.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					checkResult2.setPriKeyValue(c2Idnbr);
				}
				
			}
			//客户风险等级
			if(risklevel2 != null && !risklevel2.isEmpty()){
				checkLevel2.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				checkLevel2.setPriKeyValue(risklevel2);
			}
			
		}

		return listriskcheck;
	}

	@Override
	public void init(String appId, RiskCheck_Apply_C1 apply_Info1, RiskCheck_Apply_C2 apply_Info2, String appType)
			throws CoreException {

	}

}
