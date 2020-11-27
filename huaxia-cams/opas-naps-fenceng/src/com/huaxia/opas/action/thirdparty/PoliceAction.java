package com.huaxia.opas.action.thirdparty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.PoliceService;

import net.sf.json.JSONObject;

/**
 * 第三方-公安
 * 
 * @author zhiguo.li
 *
 */
public class PoliceAction implements Action {

	private static Logger logger = LoggerFactory.getLogger(PoliceAction.class);
	@Resource(name = "policeService")
	private PoliceService policeService;

	/**
	 * 查询附卡公安摘要信息
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void queryPoliceInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		String cardFlag = (String) context.getData("cardFlag");
		Assert.notNull(appId, "请求申请件编号为空!");

		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]公安摘要信息");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("appId", appId);
		map.put("cardFlag", cardFlag);
		Map<String, String> policeSummaryInfo = getPoliceService().querySummaryByAppId(map);
		if (policeSummaryInfo != null) {
			JSONObject jsonObject = JSONObject.fromObject(policeSummaryInfo);
			context.setData("success", true);
			context.setData("applyPoliceSummary", jsonObject.toString());
			return;
		}

		context.setData("success", false);
	}
	
	/**
	 * 查询公安摘要信息
	 * 
	 * @param context
	 * @throws Exception
	 */
	public void querySummaryInfo(Context context) throws Exception {
		String appId = (String) context.getData("appId");
		String cardFlag = (String) context.getData("cardFlag");
		Map<String, String> param = new HashMap<String, String>();
		param.put("appId", appId);
		param.put("cardFlag", cardFlag);
		
		Assert.notNull(appId, "请求申请件编号为空!");

		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]公安摘要信息");
		}
		Map<String, String> police = new HashMap<String, String>();
		//增强公安信息
		Map<String, String> policeSummaryInfo = getPoliceService().selectpoliceSummaryInfo(param);
		//人像比对结果selectPoliceXpDetail
		Map<String, String> policeXp= getPoliceService().selectPoliceXpInfo(appId);
		if(policeXp!=null){
			if(("一致").equals(policeXp.get("resultGmsfhm"))&&
					("一致").equals(policeXp.get("resultXm"))&&
					("系统判断为同一人").equals(policeXp.get("resultFx"))){
				String resultRx ="一致";
				context.setData("resultRx", resultRx);
			}else {
				String resultRx ="不一致";
				context.setData("resultRx", resultRx);
				context.setData("errorMessage", policeXp.get("errorMessage"));
			}
		} else {
			String resultRx ="";
			context.setData("resultRx", resultRx);
		}
		if (policeSummaryInfo != null) {
			JSONObject jsonObject = JSONObject.fromObject(policeSummaryInfo);
			context.setData("success", true);
			context.setData("applyPoliceSummary", jsonObject.toString());
			return;
		} else {
			String identityNoCheckRst ="";
			police.put("identityNoCheckRst", identityNoCheckRst);
			JSONObject jsonObject = JSONObject.fromObject(police);
			context.setData("applyPoliceSummary", jsonObject.toString());
		}
		context.setData("success", false);
	}
	
	public void queryDetailInfo(Context context) throws Exception {
		
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");

		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]公安详细信息");
		}
		
		//Map<String, String> map=getPoliceService().querytaskStatusInfoByAppId(appId);
		Map<String, String> map = new HashMap<>();
		map.put("appId", appId);
		Map<String, String> policeDetailInfo = getPoliceService().selectDetailInfo(map);
		if (policeDetailInfo != null) {
		//	JSONObject jsonObject = JSONObject.fromObject(policeDetailInfo);
			Object o =policeDetailInfo.get("birthDay");
			DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			policeDetailInfo.put("birthDay", sdf.format(o));
			String jsonObject= JSON.toJSONString(policeDetailInfo);
			context.setData("success", true);
			context.setData("applyPoliceDetail", jsonObject.toString());
			return;
		}

		context.setData("success", false);
	}
	
	public void queryPoliceDetailInfo(Context context) throws Exception {

		Map dataMap = context.getDataMap();
		// 参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", (String) dataMap.get("appId"));
		map.put("cardFlag", (String) dataMap.get("cardFlag"));
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");
		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]公安详细信息");
		}
		Map<String, String> policeDetailInfo = getPoliceService().selectPoliceDetailInfo(map);
		Map<String, String> policeXpInfo = policeService.selectPoliceXpDetail(appId);
		if (policeXpInfo!= null) {
			String jsonObject= JSON.toJSONString(policeXpInfo);
			context.setData("success", true);
			context.setData("policeXpInfo", jsonObject.toString());
		}
		if (policeDetailInfo!= null) {
			String jsonObject= JSON.toJSONString(policeDetailInfo);
			context.setData("success", true);
			context.setData("applyPoliceDetail", jsonObject.toString());
			return;
		}
		context.setData("success", false);
	}
	
	public void queryDetailList(Context context) throws Exception {
		Map dataMap = context.getDataMap();
		// 参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("appId", (String) dataMap.get("appId"));
		
		String appId = (String) context.getData("appId");
		Assert.notNull(appId, "请求申请件编号为空!");

		if (logger.isDebugEnabled()) {
			logger.debug("查询申请件[" + appId + "]公安详细信息");
		}
		
		List<Object> policeDetailList = new ArrayList<>();
		Map<String, String> map1 = new HashMap<String, String>();
		map1.put("appId", appId);
		policeDetailList = getPoliceService().selectDetailList(map1);
		Map<String, Object> dataMap1 = new HashMap<String, Object>();
		dataMap1.put("rows", policeDetailList);
		context.setDataMap(dataMap1);
	}
	public PoliceService getPoliceService() {
		return policeService;
	}

	public void setPoliceService(PoliceService policeService) {
		this.policeService = policeService;
	}

}
