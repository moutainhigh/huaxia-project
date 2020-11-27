package com.huaxia.opas.rule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.huateng.huaxia.xom.ApplyMainCardInfo;
import com.huateng.huaxia.xom.EnterpriseNetwork;
import com.huateng.huaxia.xom.HaveCardInfo;
import com.huateng.huaxia.xom.IVSDataAnalyze;
import com.huateng.huaxia.xom.IVSRiskLevel;
import com.huateng.huaxia.xom.PadInfor;
import com.huateng.huaxia.xom.QuyushujuInfo;
import com.huateng.huaxia.xom.RepInfor;
import com.huateng.huaxia.xom.RepInforCheck;
import com.huateng.huaxia.xom.RuleOut;
import com.huateng.huaxia.xom.RuleResultInfo;
import com.huateng.huaxia.xom.SingleRuleOut;
import com.huateng.huaxia.xom.StatisInfo;
import com.huateng.huaxia.xom.TYDataAnalyze;
import com.huateng.huaxia.xom.WebInforCheck;
import com.huateng.huaxia.xom.YuShenInfo;
import com.huateng.neofp.core.CoreException;
import com.huateng.toprules.api.TopRulesSession;
import com.huateng.toprules.api.common.TopRulesConstants;
import com.huateng.toprules.api.session.TopRulesRequest;
import com.huateng.toprules.api.session.TopRulesResponse;
import com.huateng.toprules.api.session.TopRulesServiceProvider;
import com.huateng.toprules.api.session.TopRulesServiceProviderManager;
import com.huaxia.opas.cache.common.CacheConsts;
import com.huaxia.opas.cache.service.rule.RuleModelBuildService;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.service.rule.InvokeRuleService;

public class InvokeRuleServiceImpl implements InvokeRuleService, Serializable {

	private static final long serialVersionUID = 8171546651087161192L;
	private static final Logger log = Logger.getLogger(InvokeRuleServiceImpl.class);
	@Resource(name = "ruleModelBuildService")
	private RuleModelBuildService ruleModelBuildService;
//	@Resource(name = "opasbizinpapplc1Daox")
//	private Opasbizinpapplc1Dao opasbizinpapplc1Daox;
//	@Resource(name = "opasbizapprovainfosuppDaox")
//	private OpasbizapprovainfosuppDao opasbizapprovainfosuppDaox;

	@Override
	public Map<String, Object> invokeRule(String appId, String node) throws Exception {
		log.info("调用规则引擎服务接口开始!!!申请件编号[" + appId + "]、工作流节点编号[" + node + "]");
		if (appId == null || node == null) {
			throw new CoreException("编号为空或者节点为空!");
		}
		List<Opasbizinpapplc1> opasbizinpapplc1s = ruleModelBuildService.selectByPrimaryKey(appId);
		Opasbizinpapplc1 on1 = opasbizinpapplc1s.get(0);
		
		// 2017/09/22 添加三方app_id
		String appIdThd = new String();
		if("1".equals(on1.getMatchingCardFlag())){
			if("1".equals(appId.substring(15))){
				appIdThd = appId.substring(0,15)+"2";
			}else{
				appIdThd = appId.substring(0,15)+"1";
			}
		}else{
			appIdThd = appId;
		}
		
		// 规则引擎API调用
		String projectName = null;
		String version = null;
		String ruleSetName = null;
		String ruleSetVersion = null;
		Boolean flag = false;

		// 构造规则集执行会话
		TopRulesServiceProvider provider = TopRulesServiceProviderManager
				.register(TopRulesConstants.API_RULEEXECUTE_PROVIDER);
		log.info("构造规则集执行会话provider" + provider);
		// 步骤2: 设置provider对象参数(API调用,无参数配置)
		// 步骤3: 获取session对象
		TopRulesSession session = provider.createRuleSession();
		log.info("构造规则集执行会话session" + session);
		// 步骤4: 设置规则参数对象
		// 设置请求参数
		Map<String, Object> inParams = new HashMap<String, Object>();
		// 判断为哪个节点的规则
		if ("1".equals(on1.getYdjFlag())) {
			if ("1".equals(on1.getMatchingCardFlag())) {
				projectName = "project02";
			} else {
				projectName = "project01";
			}
		} else {
			projectName = "project02";
		}
		// 组装模型
		// 网络第三方数据
		WebInforCheck wic = ruleModelBuildService.creatWebInforCheck(on1,appIdThd);
		// 主卡申请件信息
		ApplyMainCardInfo amci = ruleModelBuildService.creatApplyMainCardInfo(on1);
		// 已持卡信息
		HaveCardInfo hci = ruleModelBuildService.creatHaveCardInfo(on1,appIdThd);
		// 芝麻信用数据
		IVSDataAnalyze ida = new IVSDataAnalyze();
		// 芝麻检查风险等级
		IVSRiskLevel irl = new IVSRiskLevel();
		// 人行征信信息
		RepInfor ri = ruleModelBuildService.creatRepInfor(on1,appIdThd);
		// 人行征信数据校验
		RepInforCheck rc = ruleModelBuildService.creatRepInforCheck(on1,appIdThd);
		// 统计信息
		StatisInfo si = ruleModelBuildService.creatStatisInfo(on1);
		// 天御分信息
		TYDataAnalyze tydata = ruleModelBuildService.createTYDataAnalyze(on1,appIdThd);
		//企业网信息
		EnterpriseNetwork epn = ruleModelBuildService.createEnterpriseNetwork(on1,appIdThd);
		//预审信息
		YuShenInfo yuShenInfo = ruleModelBuildService.createYuShenModel(on1,appIdThd);
		//区域数据
		QuyushujuInfo qysjInfo = ruleModelBuildService.createQysjInfoModel(on1,appIdThd);
		//pad数据
		PadInfor padInfor = ruleModelBuildService.creatPadInforModel(on1);

		inParams.put("HaveCardInfo", hci);
		inParams.put("ApplyMainCardInf", amci);
		inParams.put("RepInfor", ri);
		inParams.put("RepInforCheck", rc);
		inParams.put("WebInforCheck", wic);
		inParams.put("IVSDataAnalyze", ida);
		inParams.put("IVSRiskLevel", irl);
		inParams.put("StatisInfo", si);
		inParams.put("TYDataAnalyze", tydata);
		inParams.put("EnterpriseNetwor", epn);
		inParams.put("YuShenInfo", yuShenInfo);
		inParams.put("QuyushujuInfo", qysjInfo);
		inParams.put("PadInfor", padInfor);

		RuleOut ro = new RuleOut();
		SingleRuleOut sro = new SingleRuleOut();
		// 征信策略节点规则输出结果
		inParams.put("RuleOut", ro);
		// 征信策略节点规则条件结果
		inParams.put("SingleRuleOut", sro);
		

		// 步骤5: 设置请求对象参数
		TopRulesRequest request = new TopRulesRequest();
		request.setProjectName(projectName);
		request.setVersion(version);
		request.setRuleSetName(ruleSetName);
		request.setRuleSetVersion(ruleSetVersion);
		request.setFlag(flag);
		request.setInputParameters(inParams);
		// 2.4.1版本在执行时新增业务编号，用于系统监控中按业务编号查看执行日志
		request.setBusinessNo("xxx信审");
		// 2.4.1版本在执行时新增是否记录规则流转信息及节点命中详情
		request.setSaveRuleFlowInfoFlag(true);
		// 步骤6: 调用规则执行方法
		// 执行规则集
		TopRulesResponse response = null;
		try {
			response = session.executeRules(request);
		} catch (Exception e) {
			log.error("规则引擎平台调用出错",e);
		}
		// 步骤7: 处理规则执行返回值
		// 处理响应参数
		Map<String, Object> outParams = new HashMap<String, Object>();
		outParams = response.getOutputParameters();
		// 返回给业务客户端的结果
		Map<String, Object> outPut = new HashMap<String, Object>();
		if (outParams == null) {
			log.info("没有收到执行结果，规则执行失败");
		} else {
			if (CacheConsts.CREDIT_TACTICS.equals(node.trim())) {
				RuleOut out = (RuleOut) outParams.get("RuleOut");
				SingleRuleOut sroout = (SingleRuleOut) outParams.get("SingleRuleOut");
				// 风险等级返回结果
				List<String> riskLevels = new ArrayList<String>();
				// 征信策略条件结果
				List<String> outResults = new ArrayList<String>();
				// 0717 from wjf 命中风险ID输出
				List<String> ruleIds = new ArrayList<String>();
				if (out != null) {
					List<RuleResultInfo> ruleResultInfos = out.getRuleResultInfos();
					for (RuleResultInfo rrf : ruleResultInfos) {
						riskLevels.add(rrf.getRiskLevel());
						ruleIds.add(rrf.getRuleId());
						log.info(rrf.getRuleId());
					}
				} else {
					log.info("输出参数找不到，请确保输出参数名正确");
				}
				if (sroout != null) {
					outResults = sroout.getOutResults();
				}
				outPut.put("riskLevels", riskLevels);
				outPut.put("outResults", outResults);
				outPut.put("ruleIds", ruleIds);
			}
			if ("1".equals(on1.getYdjFlag())) {
				outPut.put("YdjFlag", "1");
			} else {
				outPut.put("YdjFlag", "2");
			}
		}
		outPut.put("appId", appId);
		log.info("调用规则引擎服务接口结束!!!" + outPut);
		return outPut;
	}


}
