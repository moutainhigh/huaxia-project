package com.huaxia.opas.action.thirdparty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huaxia.opas.service.thirdparty.ThreeSearchLimitRuleService;


/**
 * 第三方-查询限制规则
 * 
 * @author gaohui
 *
 */
public class ThreeSearchLimitRuleAction implements Action {

	//private static Logger logger = LoggerFactory.getLogger(ThreeSearchLimitRuleAction.class);

	@Resource(name = "threeSearchLimitRuleService")
	private ThreeSearchLimitRuleService threeSearchLimitRuleService;
	
	public ThreeSearchLimitRuleService getThreeSearchLimitRuleService() {
		return threeSearchLimitRuleService;
	}

	public void setThreeSearchLimitRuleService(ThreeSearchLimitRuleService threeSearchLimitRuleService) {
		this.threeSearchLimitRuleService = threeSearchLimitRuleService;
	}

	/**
	 *@Title:saveAddRule
	 *@Description:三方 添加新规则
	 *@param context
	 *@throws Exception
	 *@author: gaohui
	 *@Date:2017年10月25日上午9:34:49
	 */
	public void saveAddRule(Context context) {
		Map map = context.getDataMap();
		
		Map<String,Object> queryParamMap=new HashMap<String,Object>();
		queryParamMap.put("bRuleType", map.get("bRuleType"));
		queryParamMap.put("ruleUniqueCode", map.get("ruleUniqueCode"));
		int uniqueCount=threeSearchLimitRuleService.queryCountByTypeUniqueCode(queryParamMap);
		if(uniqueCount>0){
			context.setData("success", false);
			context.setData("message", "规则已经存在，添加失败。");
			return ;
		}
		
		Map<String,Object> ParamMap=new HashMap<String,Object>();
		ParamMap.put("crtUser", map.get("userId"));
		ParamMap.put("lstUpdUser", map.get("userId"));
		ParamMap.put("bRuleType", map.get("bRuleType"));
		ParamMap.put("ruleContent", map.get("ruleContent"));
		ParamMap.put("inputTrader", map.get("ruleOperator"));
		ParamMap.put("channel", map.get("ruleChannel"));
		ParamMap.put("city", map.get("ruleArea"));
		ParamMap.put("ruleUniqueCode", map.get("ruleUniqueCode"));
		try{
		threeSearchLimitRuleService.saveAddRule(ParamMap);
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			context.setData("message", "添加失败，请联系管理员。");
			return ;
		}
		context.setData("success", true);
	}
	/**
	 *@Title:queryExistedRule
	 *@Description:根据 规则类型查询已存在的规则
	 *@param context
	 *@author: gaohui
	 *@Date:2017年10月25日下午3:21:06
	 */
	public void queryExistedRule(Context context) {
		Map map = context.getDataMap();
		Map<String,Object> queryParamMap=new HashMap<String,Object>();
		queryParamMap.put("bRuleType", map.get("bRuleType"));
		List<Map<String,Object>> ruleList= threeSearchLimitRuleService.queryExistedRuleByType(queryParamMap);
		context.setData("ruleList", ruleList);
	}
	/**
	 *@Title:deleteRulesByIdList
	 *@Description:根据id集合删除表中数据
	 *@param context
	 *@author: gaohui
	 *@Date:2017年10月25日下午4:45:12
	 */
	public void deleteRulesByIdList(Context context) {
		Map map = context.getDataMap();
		Map<String,Object> deleteParamMap=new HashMap<String,Object>();
		List<String> listId=(List<String>) map.get("listId");
		deleteParamMap.put("listId", listId);
		try{
		threeSearchLimitRuleService.deleteRulesByIdList(deleteParamMap);
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	/**
	 *@Title:saveSearchNumControl
	 *@Description:保存或修改 最大查询条数 和起止时间的限制
	 *@param context
	 *@author: gaohui
	 *@Date:2017年10月26日下午4:02:05
	 */
	public void saveOrUpdateSearchNumControl(Context context){
		
		Map map = context.getDataMap();
		Map<String,Object> saveOrUpdateParamMap=new HashMap<String,Object>();
		
		saveOrUpdateParamMap.put("crtUser", map.get("userId"));
		saveOrUpdateParamMap.put("lstUpdUser", map.get("userId"));
		saveOrUpdateParamMap.put("limitModuleType", map.get("limitModuleType"));
		
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		Date limitStartDate = new Date();
		Date limitEndDate = new Date();
		try {
			limitStartDate = fmt.parse(map.get("limitStartDate").toString());
			limitEndDate = fmt.parse(map.get("limitEndDate").toString());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		saveOrUpdateParamMap.put("limitStartDate", limitStartDate);
		saveOrUpdateParamMap.put("limitEndDate", limitEndDate);
		saveOrUpdateParamMap.put("limitQueryCount", map.get("limitQueryCount"));
		
		try{
		threeSearchLimitRuleService.saveOrUpdateSearchNumControl(saveOrUpdateParamMap);
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	/**
	 *@Title:querySearchNumControlTimeQuantum
	 *@Description:根据类型 和时间段 返显数量查询限制的内容
	 *@param context
	 *@author: gaohui
	 *@Date:2017年10月26日下午6:42:15
	 */
	public void querySearchNumControlTimeQuantum(Context context){
		
		Map map = context.getDataMap();
		Map<String,Object> queryParamMap=new HashMap<String,Object>();
		queryParamMap.put("limitModuleType", map.get("limitModuleType"));
		try{
		Map<String,Object>	htmlMap=threeSearchLimitRuleService.querySearchNumControlTimeQuantum(queryParamMap);
		context.setData("success", true);
		if(htmlMap!=null){
		Gson gson=new Gson();
		String jsonObject=gson.toJson(htmlMap);
		context.setData("htmlMap", jsonObject);
		}else{
			context.setData("success", false);
		}
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
	}
	
	/**
	 *@Title:saveOrUpdateTypeLimitSearch
	 *@Description:保存或修改 限制查询 不同类型的 启用停用 
	 *@param context
	 *@author: gaohui
	 *@Date:2017年10月27日上午11:16:33
	 */
	public void saveOrUpdateTypeLimitSearch(Context context){
		
		Map initialMap = context.getDataMap();
		try{
		threeSearchLimitRuleService.saveOrUpdateTypeLimitSearch(initialMap);
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	/**
	 *@Title:queryTypeLimitSearch
	 *@Description:返显 限制查询 不同类型的 启用 数据 
	 *@param context
	 *@author: gaohui
	 *@Date:2017年10月27日上午11:17:46
	 */
	public void queryTypeLimitSearch(Context context){
		
		Map map = context.getDataMap();
		Map<String,Object> queryParamMap=new HashMap<String,Object>();
		queryParamMap.put("bRuleType",map.get("bRuleType"));
		queryParamMap.put("sRuleType",map.get("sRuleType"));
		queryParamMap.put("flagActive",map.get("flagActive"));
		
		try{
		List<Map<String,Object>> htmlMap=threeSearchLimitRuleService.queryTypeLimitSearch(queryParamMap);
		context.setData("success", true);
		if(htmlMap!=null){
		Gson gson=new Gson();
		String jsonObject=gson.toJson(htmlMap);
		context.setData("htmlMap", jsonObject);
		}else{
			context.setData("success", false);
		}
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
	}
	/**
	 *@Title:queryVerhicleCheckInfo
	 *@Description:征信信息页面    查询车辆的核查信息
	 *@param context
	 *@author: gaohui
	 *@Date:2017年10月30日下午4:22:32
	 */
	public void queryVerhicleCheckInfo(Context context){
		Map map = context.getDataMap();
		Map<String,Object> queryParamMap=new HashMap<String,Object>();
		queryParamMap.put("appId",map.get("appId"));
		try{
		Map<String,Object> htmlMap=threeSearchLimitRuleService.queryVerhicleCheckInfo(queryParamMap);
		context.setData("success", true);
		if(htmlMap!=null){
		Gson gson=new Gson();
		String jsonObject=gson.toJson(htmlMap);
		context.setData("htmlMap", jsonObject);
		}else{
			context.setData("success", false);
		}
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		
	}
	/**
	 *@Title:queryVerhicleCheckInfo
	 *@Description:征信信息页面    宝信汽车信息查询
	 *@param context
	 *@author: gaohui
	 *@Date:2017年10月30日下午4:22:32
	 */
	public void queryBxVerhicleCheckInfo(Context context){
		Map map = context.getDataMap();
		//Map<String,Object> queryParamMap=new HashMap<String,Object>();
		//queryParamMap.put("appId",map.get("appId"));
		try{
		Map<String,Object> htmlMap=threeSearchLimitRuleService.queryBxVerhicleCheckInfo(map.get("appId").toString());
		context.setData("success", true);
		if(htmlMap!=null){
		Gson gson=new Gson();
		String jsonObject=gson.toJson(htmlMap);
		context.setData("htmlMap", jsonObject);
		}else{
			context.setData("success", false);
		}
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
	}	
	/**
	 * 多头借贷详情显示
	 */
	public void queryLoanNumber(Context context) throws Exception {		
		String yearNumber = context.getData("queryYear");
		try{
			Map<String,String> yearMap = threeSearchLimitRuleService.queryLoanNumber(yearNumber);
			Map<String,String> yearMap1 = threeSearchLimitRuleService.querySumLoanNumber(yearNumber);
			context.setData("success", true);
			if(yearMap != null){
				context.setData("yearMap", yearMap);
			}else{
				yearMap = new HashMap<String,String>();
				yearMap.put("JAN", "没有查询数据");
				yearMap.put("FEB", "没有查询数据");
				yearMap.put("MAR", "没有查询数据");
				yearMap.put("APR", "没有查询数据");
				yearMap.put("MAY", "没有查询数据");
				yearMap.put("JUN", "没有查询数据");
				yearMap.put("JUL", "没有查询数据");
				yearMap.put("AUG", "没有查询数据");
				yearMap.put("SEP", "没有查询数据");
				yearMap.put("OCT", "没有查询数据");
				yearMap.put("NOV", "没有查询数据");
				yearMap.put("DEC", "没有查询数据");
				context.setData("yearMap", yearMap);
			}
			if(yearMap1 != null){
				context.setData("yearMap1", yearMap1);
			}else{
				yearMap1 = new HashMap<String,String>();
				yearMap1.put("JAN1", "没有查询数据");
				yearMap1.put("FEB1", "没有查询数据");
				yearMap1.put("MAR1", "没有查询数据");
				yearMap1.put("APR1", "没有查询数据");
				yearMap1.put("MAY1", "没有查询数据");
				yearMap1.put("JUN1", "没有查询数据");
				yearMap1.put("JUL1", "没有查询数据");
				yearMap1.put("AUG1", "没有查询数据");
				yearMap1.put("SEP1", "没有查询数据");
				yearMap1.put("OCT1", "没有查询数据");
				yearMap1.put("NOV1", "没有查询数据");
				yearMap1.put("DEC1", "没有查询数据");
				context.setData("yearMap1", yearMap1);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);		
		}
		
	}
	
	/**
	 * 互联网多头借贷详情显示
	 */
	public void queryLoanNumberNet(Context context) throws Exception {		
		String yearNumber = context.getData("queryYear");
		try{
			Map<String,String> yearMap = threeSearchLimitRuleService.queryLoanNumberNet(yearNumber);
			context.setData("success", true);
			if(yearMap != null){
				context.setData("yearMap", yearMap);
			}else{
				yearMap = new HashMap<String,String>();
				yearMap.put("JAN", "没有查询数据");
				yearMap.put("FEB", "没有查询数据");
				yearMap.put("MAR", "没有查询数据");
				yearMap.put("APR", "没有查询数据");
				yearMap.put("MAY", "没有查询数据");
				yearMap.put("JUN", "没有查询数据");
				yearMap.put("JUL", "没有查询数据");
				yearMap.put("AUG", "没有查询数据");
				yearMap.put("SEP", "没有查询数据");
				yearMap.put("OCT", "没有查询数据");
				yearMap.put("NOV", "没有查询数据");
				yearMap.put("DEC", "没有查询数据");
				context.setData("yearMap", yearMap);
			}
		}catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);		
		}
		
	}
	
	/**
	 *@Title:saveBaoXinTypeLimitSearch
	 *@Description:保信汽车卡产品添加设置
	 *@param context
	 *@author: lipengfei
	 *@Date:2020年4月20日下午1:00:12
	 */
	public void saveBaoXinTypeLimitSearch(Context context){
		try{			
			// 获取添加卡产品ID
			Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
			linkedHashMap = context.getData("jsondata");
			String kaId = linkedHashMap.get("appProd");
			String[] kaIds = kaId.split(",");
			for (int i = 0; i < kaIds.length; i++) {
				Map<String,Object> kaMap = new HashMap<String,Object>(); // 保存参数map
				kaMap.put("ruleSettingValue", kaIds[i]); // 卡产品Id
				kaMap.put("userId", context.getData("userId")); // 操作者Id
				kaMap.put("bRuleType","001900"); //三方标志,001900-保信汽车
				kaMap.put("sRuleType", "appProd"); // 默认值
				kaMap.put("flagActive", "1"); // 启停标志,默认启用			
				threeSearchLimitRuleService.saveBaoXinTypeLimitSearch(kaMap);			
			}
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	
	public void saveVoiceTypeLimitSearch(Context context){
		try{			
			// 获取添加卡产品ID
			Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
			linkedHashMap = context.getData("jsondata");
			String kaId = linkedHashMap.get("appProd");
			String[] kaIds = kaId.split(",");
			for (int i = 0; i < kaIds.length; i++) {
				Map<String,Object> kaMap = new HashMap<String,Object>(); // 保存参数map
				kaMap.put("ruleSettingValue", kaIds[i]); // 卡产品Id
				kaMap.put("userId", context.getData("userId")); // 操作者Id
				kaMap.put("bRuleType","010001"); //三方标志
				kaMap.put("sRuleType", "appProd"); // 默认值
				kaMap.put("flagActive", "1"); // 启停标志,默认启用			
				threeSearchLimitRuleService.saveBaoXinTypeLimitSearch(kaMap);			
			}
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	public void saveAllTypeLimitSearch4Voice(Context context){
		try{			
			List<String> typeLimitList = threeSearchLimitRuleService.queryAllTypeLimitSearch(); // 查询所有启用状态卡产品信息
			for (String typeId : typeLimitList) {
				Map<String,Object> kaMap = new HashMap<String,Object>(); // 保存参数map
				kaMap.put("ruleSettingValue", typeId); // 卡产品Id
				kaMap.put("userId", context.getData("userId")); // 操作者Id
				kaMap.put("bRuleType","010001"); //三方标志,001900-保信汽车
				kaMap.put("sRuleType", "appProd"); // 默认值
				kaMap.put("flagActive", "1"); // 启停标志,默认启用			
				threeSearchLimitRuleService.saveBaoXinTypeLimitSearch(kaMap);
			}
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	/**
	 *@Title:saveAllTypeLimitSearch
	 *@Description:保信汽车卡产品一键添加启用卡产品设置
	 *@param context
	 *@author: lipengfei
	 *@Date:2020年5月2日下午3:10:22
	 */
	public void saveAllTypeLimitSearch(Context context){
		try{			
			List<String> typeLimitList = threeSearchLimitRuleService.queryAllTypeLimitSearch(); // 查询所有启用状态卡产品信息
			for (String typeId : typeLimitList) {
				Map<String,Object> kaMap = new HashMap<String,Object>(); // 保存参数map
				kaMap.put("ruleSettingValue", typeId); // 卡产品Id
				kaMap.put("userId", context.getData("userId")); // 操作者Id
				kaMap.put("bRuleType","001900"); //三方标志,001900-保信汽车
				kaMap.put("sRuleType", "appProd"); // 默认值
				kaMap.put("flagActive", "1"); // 启停标志,默认启用			
				threeSearchLimitRuleService.saveBaoXinTypeLimitSearch(kaMap);
			}
			/*for (int i = 0; i < typeLimitList.length; i++) {
				Map<String,Object> kaMap = new HashMap<String,Object>(); // 保存参数map
				kaMap.put("ruleSettingValue", kaIds[i]); // 卡产品Id
				kaMap.put("userId", context.getData("userId")); // 操作者Id
				kaMap.put("bRuleType","001900"); //三方标志,001900-保信汽车
				kaMap.put("sRuleType", "appProd"); // 默认值
				kaMap.put("flagActive", "1"); // 启停标志,默认启用			
				threeSearchLimitRuleService.saveBaoXinTypeLimitSearch(kaMap);			
			}*/
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	
	/**
	 *@Title:queryBaoXinRule
	 *@Description:查询保信汽车已经存在的卡产品信息
	 *@param context
	 *@author: lipengfei
	 *@Date:2020年4月20日下午3:27:43
	 */
	public void queryBaoXinRule(Context context) {		
		Map map = context.getDataMap();
		Map<String,Object> queryParamMap=new HashMap<String,Object>();
		queryParamMap.put("bRuleType",map.get("bRuleType"));
		queryParamMap.put("sRuleType",map.get("sRuleType"));
		queryParamMap.put("flagActive",map.get("flagActive"));
		List<Map<String,Object>> ruleList=threeSearchLimitRuleService.queryBaoXinRuleByType(queryParamMap);
		context.setData("success", true);
		context.setData("ruleList", ruleList);
	}
	
	/**
	 *@Title:deleteBaoXinRulesByIdList
	 *@Description:保信汽车卡产品删除设置
	 *@param context 需要删除的ID集合
	 *@author: lipengfei
	 *@Date:2020年4月20日下午1:00:12
	 */
	public void deleteBaoXinRulesByIdList(Context context) {
		Map map = context.getDataMap();
		Map<String,Object> deleteParamMap=new HashMap<String,Object>();
		List<String> listId=(List<String>) map.get("listId");
		deleteParamMap.put("listId", listId);
		try{
			threeSearchLimitRuleService.deleteBaoXinRulesByIdList(deleteParamMap);
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	
	/**
	 * 保信汽车查询成功详细展示
	 */
	public void queryBaoXinNumberNet(Context context) throws Exception {		
		String yearNumber = context.getData("queryYear");
		try{
			Map<String,String> yearMap = threeSearchLimitRuleService.queryBaoXinNumberNet(yearNumber);
			context.setData("success", true);
			if(yearMap != null){
				context.setData("yearMap", yearMap);
			}else{
				yearMap = new HashMap<String,String>();
				yearMap.put("JAN", "没有查询数据");
				yearMap.put("FEB", "没有查询数据");
				yearMap.put("MAR", "没有查询数据");
				yearMap.put("APR", "没有查询数据");
				yearMap.put("MAY", "没有查询数据");
				yearMap.put("JUN", "没有查询数据");
				yearMap.put("JUL", "没有查询数据");
				yearMap.put("AUG", "没有查询数据");
				yearMap.put("SEP", "没有查询数据");
				yearMap.put("OCT", "没有查询数据");
				yearMap.put("NOV", "没有查询数据");
				yearMap.put("DEC", "没有查询数据");
				context.setData("yearMap", yearMap);
			}
		}catch (Exception e) {
			e.printStackTrace();
			context.setData("success", false);		
		}
		
	}
	
	
	/**
	 *@Title:queryPadPortraitComparisonRule
	 *@Description:查询pad人像比对已经存在的卡产品信息
	 *@param context
	 *@author: chenmeng
	 *@Date:2020年4月29日下午2:21:43
	 */
	public void queryPadPortraitComparisonRule(Context context) {		
		Map map = context.getDataMap();
		Map<String,Object> queryParamMap=new HashMap<String,Object>();
		queryParamMap.put("bRuleType",map.get("bRuleType"));
		queryParamMap.put("sRuleType",map.get("sRuleType"));
		queryParamMap.put("flagActive",map.get("flagActive"));
		List<Map<String,Object>> ruleList=threeSearchLimitRuleService.queryPadPortraitComparisonRuleByType(queryParamMap);
		context.setData("success", true);
		context.setData("ruleList", ruleList);
	}
	
	/**
	 *@Title:savePadPortraitComparisonTypeLimitSearch
	 *@Description:pad人像比对卡产品添加设置
	 *@param context
	 *@author: chenmeng
	 *@Date:2020年4月29日下午2:46:12
	 */
	public void savePadPortraitComparisonTypeLimitSearch(Context context){
		try{			
			// 获取添加卡产品ID
			Map<String, String> linkedHashMap = new LinkedHashMap<String, String>();
			linkedHashMap = context.getData("jsondata");
			String kaId = linkedHashMap.get("appProd");
			String[] kaIds = kaId.split(",");
			for (int i = 0; i < kaIds.length; i++) {
				Map<String,Object> kaMap = new HashMap<String,Object>(); // 保存参数map
				kaMap.put("ruleSettingValue", kaIds[i]); // 卡产品Id
				kaMap.put("userId", context.getData("userId")); // 操作者Id
				kaMap.put("bRuleType","000201"); //三方标志,000201-pad人像比对
				kaMap.put("sRuleType", "appProd"); // 默认值
				kaMap.put("flagActive", "1"); // 启停标志,默认启用			
				threeSearchLimitRuleService.savePadPortraitComparisonTypeLimitSearch(kaMap);			
			}
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	
	/**
	 *@Title:deletePadPortraitComparisonRulesByIdList
	 *@Description:pad人像比对卡产品删除设置
	 *@param context 需要删除的ID集合
	 *@author: chenmeng
	 *@Date:2020年4月229日下午3:25:35
	 */
	public void deletePadPortraitComparisonRulesByIdList(Context context) {
		Map map = context.getDataMap();
		Map<String,Object> deleteParamMap=new HashMap<String,Object>();
		List<String> listId=(List<String>) map.get("listId");
		deleteParamMap.put("listId", listId);
		try{
			threeSearchLimitRuleService.deletePadPortraitComparisonRulesByIdList(deleteParamMap);
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	
	/**
	 *@Title:saveAllTypeLimitSearchPad
	 *@Description:pad人像比对卡产品一键添加启用卡产品设置
	 *@param context
	 *@author: chenmeng
	 *@Date:2020年5月6日上午10:54:22
	 */
	public void saveAllTypeLimitSearchPad(Context context){
		try{			
			List<String> typeLimitList = threeSearchLimitRuleService.queryAllTypeLimitSearch(); // 查询所有启用状态卡产品信息
			for (String typeId : typeLimitList) {
				Map<String,Object> kaMap = new HashMap<String,Object>(); // 保存参数map
				kaMap.put("ruleSettingValue", typeId); // 卡产品Id
				kaMap.put("userId", context.getData("userId")); // 操作者Id
				kaMap.put("bRuleType","000201"); //三方标志,000201-pad人像比对
				kaMap.put("sRuleType", "appProd"); // 默认值
				kaMap.put("flagActive", "1"); // 启停标志,默认启用			
				threeSearchLimitRuleService.savePadPortraitComparisonTypeLimitSearch(kaMap);
			}
			/*for (int i = 0; i < typeLimitList.length; i++) {
				Map<String,Object> kaMap = new HashMap<String,Object>(); // 保存参数map
				kaMap.put("ruleSettingValue", kaIds[i]); // 卡产品Id
				kaMap.put("userId", context.getData("userId")); // 操作者Id
				kaMap.put("bRuleType","001900"); //三方标志,001900-保信汽车
				kaMap.put("sRuleType", "appProd"); // 默认值
				kaMap.put("flagActive", "1"); // 启停标志,默认启用			
				threeSearchLimitRuleService.saveBaoXinTypeLimitSearch(kaMap);			
			}*/
		}catch(Exception e){
			e.printStackTrace();
			context.setData("success", false);
			return ;
		}
		context.setData("success", true);
	}
	

}
