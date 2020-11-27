package com.huaxia.opas.action.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.crm.CRMCommonService;

/**
 * 
 * @author 刘志辉 CRM
 */

public class CRMCustInfoAction implements Action {

	private static Logger logger = LoggerFactory
			.getLogger(CRMCustInfoAction.class);
	
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Resource(name = "CRMCommonService")
	private CRMCommonService crmCommonService;

	public void queryCustCRMInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]客户CRM详细信息");
		}
		// <!-- 基本 职业 汇总 资产 view信息 -->
		Map<String, Object> custDetailInfo = new HashMap<String, Object>();
		custDetailInfo = crmCommonService.queryCRMInfo(appId);
		// 借记卡和网银 担保 贷款的list
		List<Object> jiejikaDetailList = crmCommonService.selectJiejikaDetailList(appId);
		List<Object> wangyinDetailList = crmCommonService.selectWangyinDetailList(appId);
		List<Object> danbaoDetailList = crmCommonService.selectDanbaoDetailList(appId);
		List<Object> daikuanDetailList = crmCommonService.selectDaikuanDetailList(appId);
		if (custDetailInfo != null) {
			//String jsonObject = JSON.toJSONString(custDetailInfo);
			context.setData("success", true);
			context.setData("jsonStr", custDetailInfo);
			context.setData("jiejika",jiejikaDetailList);
			context.setData("wangyin",wangyinDetailList);
			context.setData("danbao",danbaoDetailList);
			context.setData("daikuan",daikuanDetailList);
			return;
		}
		context.setData("success", false);
	}
}
