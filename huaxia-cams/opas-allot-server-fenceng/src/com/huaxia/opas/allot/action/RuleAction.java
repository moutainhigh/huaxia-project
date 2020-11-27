package com.huaxia.opas.allot.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.util.UUIDGenerator;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotMappingRule;
import com.huaxia.opas.domain.allot.AllotNumber;
import com.huaxia.opas.domain.allot.AllotQueue;
import com.huaxia.opas.domain.allot.AllotRule;
import com.huaxia.opas.domain.allot.AllotTime;
import com.huaxia.opas.service.allot.AllotApplyHisService;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.allot.AllotMappingRuleService;
import com.huaxia.opas.service.allot.AllotQueueService;
import com.huaxia.opas.service.allot.AllotRuleService;
import com.huaxia.opas.service.allot.AllotSwitchService;

/**
 * 规则、规则域、规则映射、队列、组 、组员   控制器
 * @author 王德彬
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-27  添加自动分件数量设置
 * ------------------------------------------------
 */

@SuppressWarnings({ "rawtypes" })
public class RuleAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(RuleAction.class);
	
	@Resource(name="allotApplyService")
	private AllotApplyService allotApplyService;
	
	@Resource(name="allotMappingRuleService")
	private AllotMappingRuleService allotMappingRuleService;
	
	@Resource(name="allotQueueService")
	private AllotQueueService allotQueueService;
	
	@Resource(name="allotRuleService")
	private AllotRuleService allotRuleService;
	
	@Resource(name = "allotSwitchService")
	private	AllotSwitchService allotSwitchService;
	
	@Resource(name="allotCommonService")
	private AllotCommonService allotCommonService;
	
	@Resource(name="allotApplyHisService")
	private AllotApplyHisService allotApplyHisService;
	
	private static String OPERATOR = "and";
	
	/**
	* @Description: 规则层级映射查询
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void queryMappingRule(Context context){
		int curNum = 0;
		int id = Integer
				.parseInt(String.valueOf(context.getDataMap().get("id") == null ? 0 : context.getDataMap().get("id")));
		//当前页
		int page = Integer
				.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		//每次查数据大小
		int rows = Integer
				.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		//当前页
		if (page > 1) {
			curNum = (page - 1) * rows;
		}
		Map paramMap=new HashMap();
		if(id==1){
			paramMap=context.getData("jsondata");
		}
		int count=0;
		List<AllotMappingRule> mappingList=new ArrayList<AllotMappingRule>();
		List<AllotMappingRule> mappingList2=new ArrayList<AllotMappingRule>();
		try {
			String ydjFlag=(String)context.getData("ydjFlag");
			List<String> ruleTypeList=new ArrayList<String>();
			if("1".equals(ydjFlag)){
				ruleTypeList.add("1");
				ruleTypeList.add("2");
				ruleTypeList.add("3");
				ruleTypeList.add("4");
			}else if("2".equals(ydjFlag)){
				ruleTypeList.add("5");
				ruleTypeList.add("6");
				ruleTypeList.add("7");
			}
			paramMap.put("ruleTypeList", ruleTypeList);
			count = allotMappingRuleService.countMappingRule(paramMap);
			if(count>0){
				mappingList=allotMappingRuleService.queryMappingRule(paramMap, curNum, rows);
			}
		} catch (CoreException e) {
			log.error("RuleAction.queryMappingRule occur error"+e);
		}
		context.setData("total", count);
		context.setData("rows", mappingList);
	}
	/**
	* @Description: 规则层级映射新增
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void saveMappingRule(Context context){
		int result=0;
		try {
			String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
			String userName=(String)context.getData("userName")==null?"":(String)context.getData("userName");
			String ydjFlag=(String)context.getData("ydjFlag")==null?"":(String)context.getData("ydjFlag");
			Map<String, AllotMappingRule> linkedHashMap = new LinkedHashMap<String, AllotMappingRule>();
			linkedHashMap = context.getData("paramData");
			Gson gson = new Gson();
			AllotMappingRule mapRule = gson.fromJson(gson.toJson(linkedHashMap), AllotMappingRule.class);
			int count=0;
			if(mapRule!=null){
				String  ruleCode=mapRule.getRuleCode()==null?"":mapRule.getRuleCode();
				if("".equals(ruleCode)){
					context.setData("isFalse",true);
				}else{
					//查询该规则是否已映射该组
					count=allotMappingRuleService.queryNum(mapRule);
					if(count>0){
						context.setData("isFalses",true);
					}else{
						if("".equals(StringUtils.trimToEmpty(mapRule.getLevelType()))){
							mapRule.setLevelType("2");
						}
						String mappingId = UUIDGenerator.getUUID();
						mapRule.setMappingId(mappingId);
						mapRule.setCrtUser(userName);
						mapRule.setLstUpdUser(userName);
						result=allotMappingRuleService.saveMappingRule(mapRule);
						if(result==0){
							context.setData("exMsg","添加失败");
							context.setData("isSuccess",false);
						}else{
							context.setData("isSuccess",true);
						}
					}
				}
			}
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.saveMappingRule occur error"+e);
		}
		
	}
	/**
	* @Description: 规则层级映射更新
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void updateMappingRule(Context context){
		int result=0;
		try {
			String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
			String userName=(String)context.getData("userName")==null?"":(String)context.getData("userName");
			String ydjFlag=(String)context.getData("ydjFlag")==null?"":(String)context.getData("ydjFlag");
			Map<String, AllotMappingRule> linkedHashMap = new LinkedHashMap<String, AllotMappingRule>();
			linkedHashMap = context.getData("jsondata");
			Gson gson = new Gson();
			AllotMappingRule mapRule = gson.fromJson(gson.toJson(linkedHashMap), AllotMappingRule.class);
			String mappingId=mapRule.getMappingId();
			String levelCode=mapRule.getLevelCode();
			String ruleCode=mapRule.getRuleCode();
			Map<String,Object> ruleMap=new HashMap<String,Object>();
			//组装数据
			ruleMap.put("levelCode",levelCode );
			ruleMap.put("ruleCode",ruleCode );
			//需要查询 更新组是否已有
			AllotMappingRule amp=allotMappingRuleService.queryRule(ruleMap);
			if(amp!=null){
				if(amp.getMappingId()!=null){
					if(amp.getMappingId().equals(mappingId)){//相同映射更新
						mapRule.setLstUpdUser(userName);
						result=allotMappingRuleService.updateMappingRule(mapRule);
					}else{//组合规则映射已存在   有更新重复的   
						context.setData("exMsg","该规则已映射相关队列或组,不能重复映射");
					}
				}
			}else{//不存在
				if("1".equals(ydjFlag)){
					mapRule.setLevelType("2");
				}
				mapRule.setLstUpdUser(userName);
				result=allotMappingRuleService.updateMappingRule(mapRule);
			}
		}catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.updateMappingRule occur error"+e);
		}
		if(result==0){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess",true);
		}
	}
	/**
	* @Description: 规则层级映射删除
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void delMappingRule(Context context){
		String mappingId=(String)context.getData("mappingId");
		int result=0;
		try {
			result=allotMappingRuleService.removeMappingRuleById(mappingId);
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.delMappingRule occur error"+e);
		}
		if(result==0){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess",true);
		}
	}
	/**
	* @Description: 规则查询
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void queryAllotRule(Context context){
		int curNum = 0;
		int id = Integer
				.parseInt(String.valueOf(context.getDataMap().get("id") == null ? 0 : context.getDataMap().get("id")));
		int curPage = Integer
				.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		//每次查数据大小
		int pageNum = Integer
				.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		//当前页
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		Map<String,Object> paramMap=new HashMap<String,Object>();
		String ruleName="";
		String crtUser="";
		if(id==1){
			paramMap = context.getData("jsondata"); 
			ruleName=((String) paramMap.get("ruleName")).trim()!=null?((String) paramMap.get("ruleName")).trim():"";
			crtUser=((String) paramMap.get("crtUser")).trim()!=null?((String) paramMap.get("crtUser")).trim():"";
		}
		String ydjFlag=(String)context.getData("ydjFlag");
		String ruleType=(String)context.getData("ruleType");
		List<String> ruleTypeList=new ArrayList<String>();
		if("1".equals(ydjFlag)){
			ruleTypeList.add("1");
			ruleTypeList.add("2");
			ruleTypeList.add("3");
			ruleTypeList.add("4");
		}else if("2".equals(ydjFlag)){
			ruleTypeList.add("5");
			ruleTypeList.add("6");
			ruleTypeList.add("7");
		}
		paramMap.put("ruleTypeList", ruleTypeList);
		paramMap.put("ruleName", ruleName);
		paramMap.put("ruleType", ruleType);
		paramMap.put("crtUser", crtUser);
		int count=0;
		List<AllotRule> allotRuleList=new ArrayList<AllotRule>();
		try {
			 count = allotRuleService.countAllotRuleByCondition(paramMap);
			 if(count>0){
				 allotRuleList=allotRuleService.queryAllotRuleByCondition(paramMap, curNum, pageNum);
			 }
			 //自动分件数量设置
			 paramMap.put("typeFlag", "1");
			 paramMap.put("ydjFlag", ydjFlag);
			 List<AllotNumber> numberList=allotRuleService.queryAllotNumberList(paramMap);
			 String ydjFlag2="";
			 String currNode2="";
			 String autoNumber="";
			 AllotNumber allotNumber=new AllotNumber();
			 if(numberList!=null&&numberList.size()>0){
				 for(AllotNumber an:numberList){
					 ydjFlag2=an.getYdjFlag()==null?"":an.getYdjFlag();
					 currNode2=an.getCurrNode()==null?"":an.getCurrNode();
					 autoNumber=an.getAutoNumber()==null?"":an.getAutoNumber();
					 if("1".equals(ydjFlag2)&&"02".equals(currNode2)){
						 allotNumber.setYdjCreditNumber(autoNumber);
						 context.setData("ydjCreditNumber", autoNumber);
					 }else if("2".equals(ydjFlag2)&&"01".equals(currNode2)){
						 allotNumber.setBzkReviewNumber(autoNumber);
						 context.setData("bzkReviewNumber", autoNumber);
					 }else if("2".equals(ydjFlag2)&&"02".equals(currNode2)){
						 allotNumber.setBzkCreditNumber(autoNumber);
						 context.setData("bzkCreditNumber", autoNumber);
					 }else if("2".equals(ydjFlag2)&&"03".equals(currNode2)){
						 allotNumber.setBzkApprovalNumber(autoNumber);
						 context.setData("bzkApprovalNumber", autoNumber);
					 }
				 }
				 context.setData("allotNumber", allotNumber);
			 }
		} catch (Exception e) {
			log.error("RuleAction.queryAllotRule occur error"+e);
		}
		context.setData("total", count);
		context.setData("rows", allotRuleList);
	}
	/**
	* @Description: 规则新增
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void saveAllotRule(Context context){
		String ruleName=(String)context.getData("ruleName")==null?"":(String)context.getData("ruleName");
		int sortFlag=Integer.parseInt((String)context.getData("sortFlag"));
		String ydjFlag=(String)context.getData("ydjFlag");
		Map<String, AllotApply> linkedHashMap = new LinkedHashMap<String, AllotApply>();
		linkedHashMap = context.getData("jsondata");
		ObjectMapper mapper = new ObjectMapper();  
		String ruleScript="";
		int result=0,count=0;
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ruleName", ruleName);
		map.put("sortFlag", sortFlag);
		List<String> ruleTypeList=new ArrayList<String>();
		if("1".equals(ydjFlag)){
			ruleTypeList.add("1");
			ruleTypeList.add("2");
			ruleTypeList.add("3");
			ruleTypeList.add("4");
		}else if("2".equals(ydjFlag)){
			ruleTypeList.add("5");
			ruleTypeList.add("6");
			ruleTypeList.add("7");
		}
		map.put("ruleTypeList", ruleTypeList);
		try {
			//规则校验分配顺序是否存在 
			count=allotRuleService.countRule(map);
			if(count==0){
				String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
				Map<String,Object> userMap=new HashMap<String,Object>();
				userMap.put("userCode",userCode);
				AllotCommon allotCommon=allotCommonService.queryUser(userMap);
				String userName="";
				if(allotCommon!=null){
					userName=allotCommon.getUserName();
				}else{
					userName="系统";
				}
				String ruleType=(String)context.getData("ruleType")==null?"":(String)context.getData("ruleType");
				String ruleStatus=(String)context.getData("ruleStatus")==null?"":(String)context.getData("ruleStatus");
				String ruleScriptDesc=(String)context.getData("ruleScriptDesc")==null?"":(String)context.getData("ruleScriptDesc");
				ruleScript = mapper.writeValueAsString(linkedHashMap);
				AllotRule allotRule=new AllotRule();
				allotRule.setRuleCode(UUIDGenerator.getUUID());
				allotRule.setRuleName(ruleName);
				allotRule.setRuleType(ruleType);
				allotRule.setRuleStatus(ruleStatus);
				allotRule.setSortFlag(sortFlag);
				allotRule.setCrtUser(userName);
				allotRule.setLstUpdUser(userName);
				allotRule.setRuleScript(ruleScript.toString());
				allotRule.setRuleScriptDesc(ruleScriptDesc.toString());
				result=allotRuleService.saveAllotRule(allotRule);
			}
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.info("RuleAction.saveAllotRule Occer e "+e);
		}
		catch (JsonGenerationException e) {
			log.info("RuleAction.saveAllotRule Occer e "+e);
		} catch (JsonMappingException e) {
			log.info("RuleAction.saveAllotRule Occer e "+e);
		} catch (IOException e) {
			log.info("RuleAction.saveAllotRule Occer e "+e);
		}
		if(count>0){
			context.setData("isSuccess",false);
			context.setData("exMsg","规则名称或分配顺序号已存在");
			
		}else if(count==0){
			if(result==0){
				context.setData("isSuccess",false);
				context.setData("exMsg","规则保存异常");
			}else{
				context.setData("isSuccess",true);
			}
		}
	}
	/**
	* @Description: 规则更新
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void updateAllotRule(Context context){
		try {
			String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
			Map<String,Object> userMap=new HashMap<String,Object>();
			userMap.put("userCode",userCode);
			AllotCommon allotCommon=allotCommonService.queryUser(userMap);
			String userName="",ruleCode2="";
			if(allotCommon!=null){
				userName=allotCommon.getUserName();
			}else{
				userName="系统";
			}
	 		String ruleCode=(String)context.getData("ruleCode")==null?"":(String)context.getData("ruleCode");
			String ruleName=(String)context.getData("ruleName")==null?"":(String)context.getData("ruleName");
			String ruleType=(String)context.getData("ruleType")==null?"":(String)context.getData("ruleType");
			String ruleScript=(String)context.getData("ruleScript")==null?"":(String)context.getData("ruleScript");
			String ruleScriptDesc=(String)context.getData("ruleScriptDesc")==null?"":(String)context.getData("ruleScriptDesc");
			String ruleStatus=(String)context.getData("ruleStatus")==null?"":(String)context.getData("ruleStatus");
			int sortFlag=Integer.parseInt((String)context.getData("sortFlag"));
			String ydjFlag=(String)context.getData("ydjFlag");
			userMap.put("sortFlag", sortFlag);
			List<String> ruleTypeList=new ArrayList<String>();
			if("1".equals(ydjFlag)){
				ruleTypeList.add("1");
				ruleTypeList.add("2");
				ruleTypeList.add("3");
				ruleTypeList.add("4");
			}else if("2".equals(ydjFlag)){
				ruleTypeList.add("5");
				ruleTypeList.add("6");
				ruleTypeList.add("7");
			}
			userMap.put("ruleTypeList", ruleTypeList);
			AllotRule allotRule=allotRuleService.queryAllotRuleByCondition(userMap);
			if(allotRule!=null){
				ruleCode2=allotRule.getRuleCode();
			}else{
				allotRule=new AllotRule();
				ruleCode2=ruleCode;
			}
			if(ruleCode.equals(ruleCode2)){
				allotRule.setRuleCode(ruleCode);
				allotRule.setRuleName(ruleName);
				allotRule.setRuleType(ruleType);
				allotRule.setRuleScript(ruleScript);
				allotRule.setRuleScriptDesc(ruleScriptDesc);
				allotRule.setRuleStatus(ruleStatus);
				allotRule.setSortFlag(sortFlag);
				allotRule.setLstUpdUser(userName);
				allotRuleService.updateAllotRule(allotRule);
				context.setData("isSuccess",true);
			}else{//当前分配顺序其它规则已经有 不能再用该分配顺序 或者规则名称重复
				context.setData("isSuccess",false);
				context.setData("exMsg","其它规则已采用该分配顺序，不得重复或者规则名称重复");
			}
		}catch (CoreException e) {
			context.setData("isSuccess",false);
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.updateAllotRule occur error"+e);
		}
	}
	/**
	* @Description: 规则删除
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void delAllotRule(Context context){
		//规则编号
		String ruleCode=(String)context.getData("ruleCode")==null?"":(String)context.getData("ruleCode");//当前页
		int result=0;
		try {
			result=allotRuleService.removeAllotRuleByRuleCode(ruleCode);
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.delAllotRule occer "+e);
		}
		if(result==0){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess",true);
		}
	}
	
	/**
	* @Description: 标准卡队列分页查询
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void queryAllotQueue(Context context){
		//当前页
		int page = Integer
				.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		//每次查数据大小
		int rows = Integer
				.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		Gson gson = new Gson();
		AllotQueue allotQueue = gson.fromJson(gson.toJson(context.getDataMap()), AllotQueue.class);
		int count=0;
		List<AllotQueue> queueList=new ArrayList<AllotQueue>();
		try {
			 count = allotQueueService.countAllotQueue(allotQueue);
			 if(count>0){
				 queueList=allotQueueService.queryAllAllotQueue(allotQueue, page, rows);
			 }
		} catch (CoreException e) {
			log.error("RuleAction.queryAllotQueue occur error"+e);
		}
		context.setData("total", count);
		context.setData("rows", queueList);
	}
	
	/**
	* @Description: 标准卡队列新增
	* @author 王德彬
	* @version  V1.0
	* @param context  标准卡 队列  组
	 */
	public void saveAllotQueue(Context context){
		int result=0;
		try{
			String userName=(String)context.getData("userName")==null?"":(String)context.getData("userName");
			Map<String, AllotQueue> linkedHashMap = new LinkedHashMap<String, AllotQueue>();
			linkedHashMap = context.getData("paramData");
			Gson gson = new Gson();
			AllotQueue allotQueue = gson.fromJson(gson.toJson(linkedHashMap), AllotQueue.class);
			String queueCode=allotQueue.getQueueCode();
			int count=0;
			count=allotQueueService.queryCountAllotQueue(allotQueue);
			if(count>0){
				context.setData("exMsg","已存在该队列代码,不得重复!");
			}else{
				String queueLevel="";
				if("H".equals(queueCode)){
					queueLevel="1";
				}else if("M".equals(queueCode)){
					queueLevel="2";
				}else if("L".equals(queueCode)||"L1".equals(queueCode)||"L2".equals(queueCode)){
					queueLevel="3";
				}
				allotQueue.setQueueLevel(queueLevel);
				// 队列配置表
				String queueId = UUIDGenerator.getUUID();
				allotQueue.setQueueId(queueId);
				allotQueue.setCrtUser(userName);
				allotQueue.setLstUpdUser(userName);
				result=allotQueueService.saveAllotQueue(allotQueue);
			}
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.saveAllotQueue occur error" + e);
		}
		if(result==0){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess", true);
		}
	}
	/**
	 * @Description: 标准卡队列更新
	 * @author 王德彬
	 * @version  V1.0
	 * @param context  
	 */
	public void updateAllotQueue(Context context){
		int result=0;
		int count=0;
		try {
			Map<String,Object> map = context.getDataMap();
			String userName=(String)map.get("userName")==null?"":(String)map.get("userName");
			Map<String, AllotQueue> linkedHashMap = new LinkedHashMap<String, AllotQueue>();
			linkedHashMap = context.getData("jsondata");
			Gson gson = new Gson();
			AllotQueue queue = gson.fromJson(gson.toJson(linkedHashMap), AllotQueue.class);
			//队列id
			String queueId=queue.getQueueId();
			queue.setLstUpdUser(userName);
			//队列代码
			String queueCode=queue.getQueueCode();
			if(queueCode!=null&&!"".equals(queueCode)){
				 count=allotQueueService.queryCountAllotQueue(queue);
				if(count==0){//不存在时修改
					result=allotQueueService.updateAllotQueue(queue);
				}else if(count==1){
					AllotQueue queue2=allotQueueService.queryAllotQueueByCode(queueCode);
					if(queueId.equals(queue2.getQueueId())){
						result=1;
					}
				}
			}
		}catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.updateAllotQueue occur error"+e);
		}
		if(result==0){
			context.setData("isSuccess",false);
			if(count>0){
				context.setData("exMsg", "队列代码不能重复");
			}else{
				context.setData("exMsg", "修改失败");
			}
		}else{
			context.setData("isSuccess", true);
		}
	}
	
	/**
	 * @Description: 标准卡队列删除
	 * @author 王德彬
	 * @version  V1.0
	 * @param context  
	 */
	public void delAllotQueue(Context context){
		String queueId=(String)context.getData("queueId");
		int result=0;
		try {
			result=allotQueueService.removeAllotQueueById(queueId);
			
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.delAllotQueue occur error"+e);
		}
		if(result==0){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess", true);
		}
	}
	/**
	* @Description: 自动分件时间查询
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void queryAllotTime(Context context){
		int id = Integer
				.parseInt(String.valueOf(context.getDataMap().get("id") == null ? 0 : context.getDataMap().get("id")));
		int curNum = 0;
		//当前页
		int page = Integer
				.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		//每次查数据大小
		int rows = Integer
				.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		//当前页
		if (page > 1) {
			curNum = (page - 1) * rows;
		}
		Map<String,Object> paramMap=new HashMap<String,Object>();
		if(id==1){
			paramMap = context.getData("jsondata"); 
		}
		
		int count=0;
		List<AllotTime> mappingList=new ArrayList<AllotTime>();
		try {
			String ydjFlag=(String)context.getData("ydjFlag");
			List<String> ruleTypeList=new ArrayList<String>();
			if("1".equals(ydjFlag)){
				ruleTypeList.add("1");
				ruleTypeList.add("2");
				ruleTypeList.add("3");
				ruleTypeList.add("4");
			}else if("2".equals(ydjFlag)){
				ruleTypeList.add("5");
				ruleTypeList.add("6");
				ruleTypeList.add("7");
			}
			paramMap.put("ruleTypeList", ruleTypeList);
			count = allotMappingRuleService.countAllotTime(paramMap);
			if(count>0){
				mappingList=allotMappingRuleService.queryAllotTime(paramMap, curNum, rows);
			}
		} catch (CoreException e) {
			log.error("RuleAction.queryAllotTime occur error"+e);
		}
		context.setData("total", count);
		context.setData("rows", mappingList);
	}
	/**
	* @Description: 自动分件时间新增
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void saveAllotTime(Context context){
		int result=0;
		try {
			String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
			String userName=(String)context.getData("userName")==null?"":(String)context.getData("userName");
			String ydjFlag=(String)context.getData("ydjFlag")==null?"":(String)context.getData("ydjFlag");
			Map<String, AllotTime> linkedHashMap = new LinkedHashMap<String, AllotTime>();
			linkedHashMap = context.getData("paramData");
			Gson gson = new Gson();
			AllotTime allotTime = gson.fromJson(gson.toJson(linkedHashMap), AllotTime.class);
			int count=0;
			String startTime="";
			String endTime="";
			if(allotTime!=null){
				String  ruleType=allotTime.getRuleType()==null?"":allotTime.getRuleType();
				if("".equals(ruleType)){
					context.setData("isFalse",true);
				}else{
					List<String> ruleTypeList=new ArrayList<String>();
					ruleTypeList.add(ruleType);
					Map<String,Object> map =new HashMap<String,Object>();
					map.put("ruleTypeList", ruleTypeList);
					//查询环节时间是否已存在
					count=allotMappingRuleService.countAllotTime(map);
					if(count>0){
						context.setData("isFalses",true);
					}else{
						if(allotTime.getStartTime().isEmpty()==false){
							String[] st=allotTime.getStartTime().split(" ");
							startTime=st[1];
						}
						if(allotTime.getEndTime().isEmpty()==false){
							String[] et=allotTime.getEndTime().split(" ");
							endTime=et[1];
						}
						String id = UUIDGenerator.getUUID();
						allotTime.setId(id);
						allotTime.setCrtUser(userName);
						allotTime.setLstUser(userName);
						allotTime.setEndTime(endTime);
						allotTime.setStartTime(startTime);
						result=allotMappingRuleService.saveAllotTime(allotTime);
						if(result==0){
							context.setData("isSuccess",false);
						}else{
							context.setData("isSuccess",true);
						}
					}
				}
			}
		} catch (CoreException e) {
			context.setData("exMsg",e.getMessage());
			log.error("RuleAction.saveAllotTime occur error"+e);
		}
		
	}
	/**
	* @Description: 自动分件时间更新
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void updateAllotTime(Context context){
		int result=0;
		try {
			String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
			String userName=(String)context.getData("userName")==null?"":(String)context.getData("userName");
			String ydjFlag=(String)context.getData("ydjFlag")==null?"":(String)context.getData("ydjFlag");
			Map<String, AllotTime> linkedHashMap = new LinkedHashMap<String, AllotTime>();
			linkedHashMap = context.getData("paramData");
			Gson gson = new Gson();
			AllotTime allotTime = gson.fromJson(gson.toJson(linkedHashMap), AllotTime.class);
			String startTime="";
			String endTime="";
			if(allotTime.getStartTime().isEmpty()==false){
				String[] st=allotTime.getStartTime().split(" ");
				startTime=st[1];
			}
			if(allotTime.getEndTime().isEmpty()==false){
				String[] et=allotTime.getEndTime().split(" ");
				endTime=et[1];
			}
			allotTime.setLstUser(userName);
			allotTime.setStartTime(startTime);
			allotTime.setEndTime(endTime);
			result=allotMappingRuleService.updateAllotTime(allotTime);;
		}catch (CoreException e) {
			log.error("RuleAction.updateAllotTime occur error"+e);
		}
		if(result==0){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess",true);
		}
	}
	/**
	* @Description: 自动分件时间删除,删除后默认全天
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void delAllotTime(Context context){
		String id=(String)context.getData("id");
		int result=0;
		try {
			result=allotMappingRuleService.removeAllotTime(id);
		} catch (CoreException e) {
			log.error("RuleAction.delAllotTime occur error"+e);
		}
		if(result==0){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess",true);
		}
	}
	/**
	* @Description: 保存自动分件数量
	* @author 王德彬
	* @version  V1.1
	* @param context  
	 */
	@SuppressWarnings("null")
	public void saveAutoNumber(Context context){
		try {
			Map map=context.getDataMap();
			String userCode=(String)context.getData("userCode")==null?"":(String)context.getData("userCode");
			String ydjFlag=(String)context.getData("ydjFlag")==null?"":(String)context.getData("ydjFlag");
			String ydjCreditNumber="";
			String bzkReviewNumber="";
			String bzkCreditNumber="";
			String bzkApprovalNumber="";
			map.put("typeFlag", "1");
			if("1".equals(ydjFlag)){
				ydjCreditNumber=(String)context.getData("ydjCreditNumber")==null?"":(String)context.getData("ydjCreditNumber");
				map.put("currNode", "02");
				map.put("autoNumber", ydjCreditNumber);
				AllotNumber allotNumber=allotRuleService.queryAllotNumber(map);
				if(allotNumber!=null){
					allotNumber.setAutoNumber(ydjCreditNumber);
					allotRuleService.updateAllotNumber(allotNumber);
				}else{
					AllotNumber allotNumber2=new AllotNumber();
					allotNumber2.setTypeFlag("1");
					allotNumber2.setAutoNumber(ydjCreditNumber);
					allotNumber2.setCurrNode("02");
					allotNumber2.setYdjFlag(ydjFlag);
					allotRuleService.saveAllotNumber(allotNumber2);
				}
			}else if("2".equals(ydjFlag)){
				bzkReviewNumber=(String) map.get("bzkReviewNumber")==null?"":(String) map.get("bzkReviewNumber");
				if(!"".equals(bzkReviewNumber)){
					map.put("typeFlag", "1");
					map.put("currNode", "01");
					map.put("autoNumber", bzkReviewNumber);
					AllotNumber allotNumber=allotRuleService.queryAllotNumber(map);
					if(allotNumber!=null){
						allotNumber.setAutoNumber(bzkReviewNumber);
						allotRuleService.updateAllotNumber(allotNumber);
					}else{
						AllotNumber allotNumber2=new AllotNumber();
						allotNumber2.setTypeFlag("1");
						allotNumber2.setAutoNumber(bzkReviewNumber);
						allotNumber2.setCurrNode("01");
						allotNumber2.setYdjFlag(ydjFlag);
						allotRuleService.saveAllotNumber(allotNumber2);
					}
				}
				bzkCreditNumber=(String) map.get("bzkCreditNumber")==null?"":(String) map.get("bzkCreditNumber");
				if(!"".equals(bzkCreditNumber)){
					map.put("typeFlag", "1");
					map.put("currNode", "02");
					map.put("autoNumber", bzkCreditNumber);
					AllotNumber allotNumber=allotRuleService.queryAllotNumber(map);
					if(allotNumber!=null){
						allotNumber.setAutoNumber(bzkCreditNumber);
						allotRuleService.updateAllotNumber(allotNumber);
					}else{
						AllotNumber allotNumber2=new AllotNumber();
						allotNumber2.setTypeFlag("1");
						allotNumber2.setAutoNumber(bzkCreditNumber);
						allotNumber2.setCurrNode("02");
						allotNumber2.setYdjFlag(ydjFlag);
						allotRuleService.saveAllotNumber(allotNumber2);
					}
				}
				bzkApprovalNumber=(String) map.get("bzkApprovalNumber")==null?"":(String) map.get("bzkApprovalNumber");
				if(!"".equals(bzkApprovalNumber)){
					map.put("typeFlag", "1");
					map.put("currNode", "03");
					map.put("autoNumber", bzkApprovalNumber);
					AllotNumber allotNumber=allotRuleService.queryAllotNumber(map);
					if(allotNumber!=null){
						allotNumber.setAutoNumber(bzkApprovalNumber);
						allotRuleService.updateAllotNumber(allotNumber);
					}else{
						AllotNumber allotNumber2=new AllotNumber();
						allotNumber2.setTypeFlag("1");
						allotNumber2.setAutoNumber(bzkApprovalNumber);
						allotNumber2.setCurrNode("03");
						allotNumber2.setYdjFlag(ydjFlag);
						allotRuleService.saveAllotNumber(allotNumber2);
					}
				}
			}
			context.setData("isSuccess",true);
		} catch (CoreException e) {
			context.setData("exMsg","自动分件申请件数量添加失败");
			context.setData("isSuccess",false);
			log.error("RuleAction.saveAllotTime occur error"+e);
		}
		
	}
	public void queryCreditDict(Context context){
		//当前页
		int page = Integer
				.parseInt(String.valueOf(context.getDataMap().get("page") == null ? 0 : context.getDataMap().get("page")));
		//每次查数据大小
		int rows = Integer
				.parseInt(String.valueOf(context.getDataMap().get("rows") == null ? 0 : context.getDataMap().get("rows")));
		int count=0;
		List<AllotQueue> queueList=new ArrayList<AllotQueue>();
		try {
			 count = allotCommonService.countCreditDict();
			 if(count>0){
				 queueList=allotCommonService.selectCreditDict("1", page, rows);
			 }
		} catch (CoreException e) {
			log.error("RuleAction.queryCreditDict occur error"+e);
		}
		context.setData("total", count);
		context.setData("rows", queueList);
	}
	
	/**
	 * 根据条件查询征信规则分配数量方法
	 * @author wenyh
	 * @param context
	 * @throws CoreException
	 */
	public void queryCreditRuleAllotNumber(Context context) throws CoreException {
		Map<String,Object> map = new HashMap<String,Object>();
		String ydjFlag = (String)context.getData("ydjFlag");
		String ruleCode = (String)context.getData("ruleCode") == null ? "" : (String)context.getData("ruleCode");
		map.put("ydjFlag", ydjFlag);
		map.put("ruleCode", ruleCode);
		try {
			String fpNumResult = allotRuleService.queryCreditRuleAllotNumber(map);
			if(fpNumResult == null){
				context.setData("fpNumResult", "0");
			}else{
				context.setData("fpNumResult", fpNumResult);
			}
			context.setData("isSuccess", true);
			log.info("RuleAction.queryCreditRuleAllotNumber 查询征信规则分配数量成功，分配数为：", fpNumResult);
		} catch (CoreException e) {
			log.error("RuleAction.queryCreditRuleAllotNumber occur error", e);
			context.setData("isSuccess", false);
		}
	}
}
