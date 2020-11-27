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
import com.huaxia.opas.domain.decision.TeamdealList;
import com.huaxia.opas.domain.riskcheck.RiskCheckResult;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C1;
import com.huaxia.opas.domain.riskcheck.RiskCheck_Apply_C2;
import com.huaxia.opas.service.riskcheck.RiskCheckService;
import com.huaxia.opas.util.StringUtil;

/**
 * 团办名单 申请表团办号码与团办名单内团办号码字段进行匹配（精确匹配）
 * @author qianxiwen   2017/06/17 jiangming.yang 修改
 * 2020/8/4新增 
 * 标准卡团办名单匹配如果团办名单命中则命中，
 * 如果团办名单未命中，用项目号继续匹配。
 * 
 * 易达金逻辑不变
 */
public class Step2_Field_Opas_TeamDeal_List implements RiskFieldMachService {
	@Resource(name = "lueneServiceImpl")
	private LueneService lueneService;
	@Resource(name = "riskcheckdaoService")
	private RiskCheckService riskCheckService;

	public Map<String, Object> initParams(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> params = new HashMap<String, Object>();
		String teamCode = "";
		String c5Jctype = "";
		// 主附卡
		if ("1".equals(appType)) {
			teamCode = StringUtil.trimToEmpty(apply_Info1.getC4Apbatch());//团办号
			c5Jctype = StringUtil.trimToEmpty(apply_Info1.getC5Jctype());//项目号
			
		}
		// 附卡
		if ("2".equals(appType)) {

		}
		// 主卡
		if ("3".equals(appType)) {
			teamCode = StringUtil.trimToEmpty(apply_Info1.getC4Apbatch());
			c5Jctype = StringUtil.trimToEmpty(apply_Info1.getC5Jctype());
		}
		params.put("teamCode", teamCode);
		params.put("c5Jctype", c5Jctype);
		return params;
	}

	@Override
	public List<RiskCheckResult> macheField(String appId, RiskCheck_Apply_C1 apply_Info1,
			RiskCheck_Apply_C2 apply_Info2, String appType) throws CoreException {
		Map<String, Object> initParams = this.initParams(appId, apply_Info1, apply_Info2, appType);
		String teamCode = (String) initParams.get("teamCode");
		String c5Jctype = (String) initParams.get("c5Jctype");
		List<TeamdealList> teamdeallist = null;
		//查看此app_id  是标准卡还 是易达金   
		Map<String, String>  matchingCard = riskCheckService.queryMatchingCardFlagAndYdjFalg(appId);
		RiskCheckResult check_TEAMDEAL_CODE = null;
		//易达金
		if("1".equals(matchingCard.get("ydjFlag"))||"2".equals(matchingCard.get("matchingCardFlag"))){
			check_TEAMDEAL_CODE = new RiskCheckResult("TEAMDEAL_CODE", "OPAS_TEAMDEAL_LIST",CacheConsts.RISK_TYPE_TEAM);
			teamdeallist = riskCheckService.Query_OPAS_TEAMDEAL_LIST(teamCode.toUpperCase());
		}else{//标准卡
			check_TEAMDEAL_CODE = new RiskCheckResult("TEAM_ID", "OPAS_TEAMLIST",CacheConsts.RISK_TYPE_TEAM);
			teamdeallist = riskCheckService.Query_OPAS_TEAMDEAL_BZK_LIST(teamCode.toUpperCase());
		}
		List<RiskCheckResult> listriskcheck = new ArrayList<RiskCheckResult>();
		
		boolean flag = true;
		
		check_TEAMDEAL_CODE.setApplyType(appType);
		if (teamdeallist != null && teamdeallist.size() > 0) {
			for (int i = 0; i < teamdeallist.size(); i++) {
				TeamdealList tmpTeamdealList = teamdeallist.get(i);
				check_TEAMDEAL_CODE.setPriKeyValue(tmpTeamdealList.getAutoId());
				check_TEAMDEAL_CODE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
				check_TEAMDEAL_CODE.setBaseDataValue(tmpTeamdealList.getTeamdealCode());
				flag=false;
				break;
			}
		}
		if(flag&&"2".equals(matchingCard.get("ydjFlag"))){
			teamdeallist = riskCheckService.Query_OPAS_TEAMDEAL_BZK_LIST(c5Jctype.toUpperCase());
			if (teamdeallist != null && teamdeallist.size() > 0) {
				for (int i = 0; i < teamdeallist.size(); i++) {
					TeamdealList tmpTeamdealList = teamdeallist.get(i);
					check_TEAMDEAL_CODE.setPriKeyValue(tmpTeamdealList.getAutoId());
					check_TEAMDEAL_CODE.setRiskResult(CacheConsts.RISK_CHECK_SUCCEED);
					check_TEAMDEAL_CODE.setBaseDataValue(tmpTeamdealList.getTeamdealCode());
					break;
				}
			}
		}
		
		listriskcheck.add(check_TEAMDEAL_CODE);
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

	}

}
