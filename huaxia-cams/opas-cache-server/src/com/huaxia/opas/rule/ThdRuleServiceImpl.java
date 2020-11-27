package com.huaxia.opas.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.huateng.huaxia.xom.QueryThdSide;
import com.huateng.huaxia.xom.SingleRuleOut;
import com.huateng.toprules.api.TopRulesSession;
import com.huateng.toprules.api.common.TopRulesConstants;
import com.huateng.toprules.api.session.TopRulesRequest;
import com.huateng.toprules.api.session.TopRulesResponse;
import com.huateng.toprules.api.session.TopRulesServiceProvider;
import com.huateng.toprules.api.session.TopRulesServiceProviderManager;
import com.huaxia.opas.cache.service.rule.PlazeRuleModelBuildService;
import com.huaxia.opas.domain.rule.Opasbizinpapplc1;
import com.huaxia.opas.service.rule.ThdRuleService;

public class ThdRuleServiceImpl implements ThdRuleService{

	private static final Logger log = Logger.getLogger(ThdRuleServiceImpl.class);

	@Resource(name = "plazeRuleModelBuildService")
	private PlazeRuleModelBuildService plazeRuleModelBuildService;

	//@Override
	public Map<String, Object> invokeRule(String appId) throws Exception {
		//log.error("\n-----------申请件号" + appId + "测试开始------------");
		log.info("调用规则引擎服务接口开始!!!申请件编号[" + appId + "]");

		if (appId == null) {
			throw new Exception("编号为空或者节点为空!");
		}
		
		log.info("第三方查询平台规则引擎服务接口开始!!!");

		// 判断为哪个节点的规则
		Opasbizinpapplc1 on1 = plazeRuleModelBuildService.queryOpasbizinpapplc1(appId);
		QueryThdSide qts = plazeRuleModelBuildService.createQueryThdSide(on1);

		// 构造规则集执行会话
		TopRulesServiceProvider provider = TopRulesServiceProviderManager
				.register(TopRulesConstants.API_RULEEXECUTE_PROVIDER);
		TopRulesSession session = provider.createRuleSession();

		// 设置请求参数
		Map<String, Object> inParams = new HashMap<String, Object>();
		inParams.put("QueryThdSide", qts);
		inParams.put("SingleRuleOut", new SingleRuleOut());

		// 设置请求对象参数
		TopRulesRequest request = new TopRulesRequest();
		request.setProjectName("project03");
		request.setVersion(null);
		request.setRuleSetName(null);
		request.setRuleSetVersion(null);
		request.setFlag(false);
		request.setInputParameters(inParams);
		// 2.4.1版本在执行时新增业务编号，用于系统监控中按业务编号查看执行日志
		request.setBusinessNo("第三方查询平台");
		// 2.4.1版本在执行时新增是否记录规则流转信息及节点命中详情
		request.setSaveRuleFlowInfoFlag(true);
		
		// 返回给业务客户端的结果
		Map<String, Object> outPut = new HashMap<String, Object>();
		
		// 执行规则集
		TopRulesResponse response = null;
		try {
			response = session.executeRules(request);
			Map<String, Object> outParams = response.getOutputParameters();
			if (outParams != null) {
				List<String> sroout = ((SingleRuleOut) outParams.get("SingleRuleOut")).getOutResults();
				outPut.put("plaze_3rd_task", sroout);
				log.info("规则输出信息:" + sroout.toString());
			}
		} catch (Exception e) {
			log.error("规则引擎平台调用出错",e);
		} finally {
			response = null;
		}
		
		log.info("第三方查询平台规则引擎服务接口结束!!!");
		//log.error("\n-----------申请件号" + appId + "测试完成------------\n");
		return outPut;
	}

	

}
