package com.huaxia.opas.allot.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.util.ConcurrentDateUtil;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotMappingRule;
import com.huaxia.opas.domain.allot.AllotNumber;
import com.huaxia.opas.domain.allot.AllotSwitch;
import com.huaxia.opas.domain.allot.AllotTime;
import com.huaxia.opas.service.allot.AllotApplyHisService;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.allot.AllotMappingRuleService;
import com.huaxia.opas.service.allot.AllotMethodService;
import com.huaxia.opas.service.allot.AllotQueueService;
import com.huaxia.opas.service.allot.AllotRuleService;
import com.huaxia.opas.service.allot.AllotSwitchService;

import net.sf.json.JSONObject;
/**
 * 自动批量定时分件
 * @author  wangdebin
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-12  修复标准卡自动分件审查 征信 审批生命周期记录缺少组中文的问题
 *  2017-10-23  添加自动分件映射多个组功能
 *  2017-10-27  修改自动分件组到人标准卡数据组装
 *  2017-10-27  添加自动分件数量功能
 *  2017-11-03  修改标准卡征信、审批自动失效问题
 *  2017-11-03  标准卡征信环节跟据征信策略自动到相应的队列
 *  2018-09-11  自动分件节假日不分件
 *  含义：ruleType：自动分件环节码,timeCount：自动分件设置情况(0为未设置,不自动分件),ruleCodeList：规则码映射集合
 *  ruleList：规则实体集合,codeList:规则映射组集合,groupNum:映射组数量,applyNum:符合条件申请件总量
 *  pingNum： 每个组平均分配的申请件(变化中,向下取整),junNum： 每个组平均分配的申请件(固定值,向下取整),
 *  yuNum:各组平均分配后剩余件,autoNum:当天设置该环节自动分件数量,autoNum2:当天该环节已自动分件数量,autoYuNum:当天需要自动分件数量
 *  ruleScript：详细规则,switchCode：1-标准卡池开关2-标准卡征信队列开关3-标准卡组开关 4-易达金池开关5-易达金组开关
 *  map里参数：currNode（当前环节：01-录入，02-征信，03-审批）secondNode（1-池，2-队列，3-组）
 *  strType(1-易达金或反欺诈分件查询，2-分页页面展示查询，3-符合条件申请件数量查询，4-标准卡分件查询)
 *  shareWay(老查询，用于易达金或反欺诈分件查询 1-易达金84、85卡自动  2-易达金非84、85自动  3-其它情况)
 *  userCode（system），ydjFlag：易达金标识，currStatus：0-池中未分配未挂起，5-池中未分配已挂起
 *  1-组未分配未挂起2-组中未分配已挂起3-个人已分配未挂起4-个人已分配已挂起6-征信队列未分配未挂起7-征信队列未分配已挂起 
 *  flag：流程节点记录区分标识,currGroup：组,autoAllot：自动分件件标识,levelType:映射级别 1队列 2组,allResult:本次查询申请件总数量。
 * ------------------------------------------------
 */
/**
 * @author user
 *
 */
public class AutoTimingAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(AutoTimingAction.class);
	
	@Resource(name="allotApplyService")
	private AllotApplyService allotApplyService;
	
	@Resource(name="allotApplyHisService")
	private AllotApplyHisService allotApplyHisService;
	
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
	
	@Resource(name = "allotMethodService")
	private AllotMethodService allotMethodService;
	
	private static String OPERATOR = "and";
	@Value(value="commitCountEveryTime")
	private static final int commitCountEveryTime=10000;
	/**
	* @Description:易达金征信  快速件    main 自动分配主方法
	* @Description:目前采用一条映射对应一个组  规则可以多次映射  
	* @author wangdebin 
	* @version  V1.0
	* @param context  
	 */
	public void ydjFastCreditMain() {
		try {
			//规则映射表  根据快速规则分件
			String ruleType="1";
			boolean checkResult=checkMethod("4",ruleType);//校验
			if(!checkResult){//校验不通过
				return;
			}
			autoYdjModule(ruleType, "02", "1", "1");
		} catch (Exception e) {
			log.error("AutoTimingAction.ydjFastCreditMain occur error "+e);
		}
	}
	/**
	* @Description:易达金征信  普通件    main 自动分配主方法
	* @Description:目前采用一条映射对应一个组  规则可以多次映射  
	* @author wangdebin 
	* @version  V1.0
	* @param context  
	 */
	public void ydjCreditMain() {
		try {
			String ruleType = "3";
			boolean checkResult=checkMethod("4",ruleType);//校验
			if(!checkResult){//校验不通过
				return;
			}
			autoYdjModule(ruleType, "02", "1", "2");
		} catch (Exception e) {
			log.error("AutoTimingAction.ydjCreditMain occur error"+e);
		}
	}
	/**
	* @Description:标准卡审查    main 自动分配主方法
	* @author  wangdebin
	* @version  V1.0
	* @param context  
	 */
	public void bzkReviewMain() {
		try {
			String ruleType = "5";
			boolean checkResult=checkMethod("1",ruleType);//校验
			if(!checkResult){//校验不通过
				return;
			}
			autoBzkModule(ruleType, "01", "3", "3");
		} catch (Exception e) {
			log.error("AutoTimingAction.bzkReviewMain occur error" + e);
		}
	}
	/**
	* @Description:标准卡  审批    main 自动分配主方法
	* @author  wangdebin
	* @version  V1.0
	* @param context  
	 */
	public void bzkApprovalMain() {
		try {
			String ruleType = "7";
			boolean checkResult=checkMethod("1",ruleType);//校验
			if(!checkResult){//校验不通过
				log.info("校验未通过==================");
				return;
			}
			log.info("校验通过,执行流转情况");
			autoBzkModule(ruleType, "03", "3", "3");
		} catch (Exception e) {
			log.error("AutoTimingAction.bzkApprovalMain occur error" + e);
		}
	}
	/**
	 * 快速分件 组到人平均分件
	 * @param groupCode
	 * @param comShareQueue
	 * @throws Exception 
	 * @throws CoreException
	 */
	public boolean fastGToU(Map dataMap) throws Exception {
		log.info("AutoTimingAction.fastGToU  Enter" );
		boolean result = false;
		// 组存储表
		String currGroup = (String) dataMap.get("currGroup") == null ? "" : (String) dataMap.get("currGroup");
		String ydjFlag = (String) dataMap.get("ydjFlag") == null ? "" : (String) dataMap.get("ydjFlag");
		String currNode = (String) dataMap.get("currNode") == null ? "" : (String) dataMap.get("currNode");
		String shareWay = (String) dataMap.get("shareWay") == null ? "" : (String) dataMap.get("shareWay");
		AllotCommon allotCommom;
		int allResult=0;
		try {
			//查询符合该规则的申请件总数  
		    Map<String,Object> allMap=new HashMap<String,Object>();
		    //参与该组自动分件的组员标识
		    dataMap.put("autoAllot", "1");
		    dataMap.put("secondNode", "3");
		    dataMap.put("strType", "5");
		    allMap=allotApplyService.saveApplyByConditions(dataMap);
		    allResult=(Integer) allMap.get("allResult");
			List<AllotApply> allotAppList = queryApplyList(dataMap, allResult,"2");
			if (allotAppList == null||allotAppList.size() == 0) {
				log.info("AutoTimingAction.fastGToU   自动分件组内没有待分配的件");
				return true;
			}
			// 申请件详细信息集合
			List<AllotApply> allotApplyList = new ArrayList<AllotApply>();
			// 申请件编号集合
			List<String> lifeList = new ArrayList<String>();
			// 查询该组信息
			allotCommom = allotCommonService.queryGroupByOrgNo(currGroup);
			// 全部满足分件的组员信息
			List<AllotCommon> allotUserList = allotCommonService.queryAllotUserByOrgId(dataMap, allotCommom);
			if (allotUserList == null || allotUserList.size() == 0){
				log.info("AutoTimingAction.fastGToU 自动分件组内没有该环节的组员");
				return true;
			}
			Map<String, Object> lifeMap = new HashMap<String, Object>();
			List<String> currStatusList=new ArrayList<String>();
			currStatusList.add("1");
			lifeMap.put("currNode", currNode);
			lifeMap.put("currStatusList", currStatusList);
			lifeMap.put("ydjFlag", ydjFlag);
			lifeMap.put("secondNode", "3");
			lifeMap.put("flag", "9");
			Date now = new Date();
			for (int i = 0; i < allotAppList.size(); i++) {
				int j = i % allotUserList.size();
				AllotApply allotApply = allotAppList.get(i);
				AllotCommon allotUser = allotUserList.get(j);
				String currUser = allotUser.getUserCode() == null ? "system" : allotUser.getUserCode();
				String lastUser = currUser;
				String currName = allotUser.getUserName() == null ? "系统" : allotUser.getUserName();
				//易达件
				if("1".equals(ydjFlag)){
					String appNo = allotApply.getAppNo();
					if (appNo == null || "".equals(appNo)) {
						appNo = allotApply.getAppId().substring(0, 15);
					}
					// 组中未分配
					lifeMap.put("appId", appNo);
					int appNum = allotApplyService.queryHandCount(lifeMap);
					// 套卡区分
					if (appNum == 1) {// 该环节只有一件(标准卡1件放行 易达金除了捞件情况 其他单件不分 )
						AllotApply allotApply2 = allotApplyService.queryHandByAppId(lifeMap);
						String appId = allotApply2.getAppId();
						// 此处与其它地方不同
						lifeList.add(appId + "-" + currUser + "-" + currName);
						// 逆向标识 1和2 捞件两种情况 只有一件时候 可以分下去
						String backFlag = allotApply2.getBackFlag();
						// 易达金标识
						String yf = allotApply2.getYdjFlag();
						// 套卡标识
						String matchingCardFlag = allotApply2.getMatchingCardFlag();
						// 捞件情况01 02只捞回一件 仍可以分件
						String laoJianFlag = allotApply2.getLaoJianFlag();
						if ("0".equals(matchingCardFlag) || ("2".equals(yf))
								|| ("1".equals(yf) && ("1".equals(matchingCardFlag) || "2".equals(matchingCardFlag))
										&& ("01".equals(laoJianFlag) || "02".equals(laoJianFlag)))) {// 非易达金套卡\捞件
							allotApply2.setLstTeamDate(now);
							allotApply2.setUserDate(now);
							allotApply2.setCurrUser(currUser);
							allotApply2.setLastUser(lastUser);
							allotApply2.setCurrUserName(currName);
							allotApply2.setCurrStatus("3");
							allotApplyList.add(allotApply2);
						}
					} else if (appNum == 2) {// 套卡(算一件)
						List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
						for (AllotApply app : appList) {
							// 此处与其它地方不同
							lifeList.add(app.getAppId() + "-" + currUser + "-" + currName);
							app.setLstTeamDate(now);
							app.setUserDate(now);
							app.setCurrStatus("3");
							app.setCurrUser(currUser);
							app.setLastUser(lastUser);
							app.setCurrUserName(currName);
							allotApplyList.add(app);
						}
					}
				}else if("2".equals(ydjFlag)){//标准卡
					String appId=allotApply.getAppId();
					allotApply.setAppId(appId);
					// 此处与其它地方不同
					lifeList.add(appId + "-" + currUser + "-" + currName);
					allotApply.setLstTeamDate(now);
					allotApply.setUserDate(now);
					allotApply.setCurrStatus("3");
					allotApply.setCurrUser(currUser);
					allotApply.setLastUser(lastUser);
					allotApply.setCurrUserName(currName);
					allotApplyList.add(allotApply);
				}
			}
			if (allotApplyList.size() > 0) {
				//二期分批次更新
				updateBatchList(allotApplyList, lifeList, lifeMap);
				lifeList.clear();
				allotApplyList.clear();
				log.info("自动分件--批量分件组到人成功!");
			}
		} catch (CoreException e) {
			log.error("AutoTimingAction.fastGToU occur 自动分件发生异常 - error" + e);
		}
		log.info("AutoTimingAction.fastGToU  Out");
		return result;
	}
	/**
	 * 自动分件数量插入
	 * @param 
	 * @return
	 * @throws CoreException
	 */
	public synchronized void saveAutoNumber(String ruleType, String ruleCode) throws CoreException{
		AllotNumber allotNumber=new AllotNumber();
		if("1".equals(ruleType)||"3".equals(ruleType)){
			allotNumber.setCurrNode("02");
			allotNumber.setYdjFlag("1");
		}else if("4".equals(ruleType)){
			allotNumber.setCurrNode("03");
			allotNumber.setYdjFlag("1");
		}else if("5".equals(ruleType)){
			allotNumber.setCurrNode("01");
			allotNumber.setYdjFlag("2");
		}else if("6".equals(ruleType)){
			allotNumber.setCurrNode("02");
			allotNumber.setYdjFlag("2");
		}else if("7".equals(ruleType)){
			allotNumber.setCurrNode("03");
			allotNumber.setYdjFlag("2");
		}
		allotNumber.setTypeFlag("2");
		allotNumber.setRuleCode(ruleCode);
		//初始值设为0
		allotNumber.setAutoNumber("0");
		allotRuleService.saveAllotNumber(allotNumber);
	}
	/**
	 * 自动分件数量更新
	 * @param 
	 * @return
	 * @throws CoreException
	 */
	public synchronized void updateAutoNumber(String ruleType,int autoNumber, String ruleCode) throws CoreException{
		AllotNumber allotNumber=new AllotNumber();
		if("1".equals(ruleType)||"3".equals(ruleType)){
			allotNumber.setCurrNode("02");
			allotNumber.setYdjFlag("1");
		}else if("4".equals(ruleType)){
			allotNumber.setCurrNode("03");
			allotNumber.setYdjFlag("1");
		}else if("5".equals(ruleType)){
			allotNumber.setCurrNode("01");
			allotNumber.setYdjFlag("2");
		}else if("6".equals(ruleType)){
			allotNumber.setCurrNode("02");
			allotNumber.setYdjFlag("2");
		}else if("7".equals(ruleType)){
			allotNumber.setCurrNode("03");
			allotNumber.setYdjFlag("2");
		}
		allotNumber.setTypeFlag("2");
		int autoNumber2=autoNumber+queryAutoNumber(ruleType, "2");
		allotNumber.setAutoNumber(String.valueOf(autoNumber2));
		allotNumber.setRuleCode(ruleCode);//20200909-wenyh
		allotNumber.setFlag("1");
		allotRuleService.updateAllotNumber(allotNumber);
	}
	/**
	 * 自动分件数量查询
	 * @param 
	 * @return
	 * @throws CoreException
	 */
	public int queryAutoNumber(String ruleType,String typeFlag) throws CoreException{
		Map<String,Object> map=new HashMap<String,Object>();
		if("1".equals(ruleType)||"3".equals(ruleType)){
			map.put("currNode", "02");
			map.put("ydjFlag", "1");
		}else if("4".equals(ruleType)){
			map.put("currNode", "03");
			map.put("ydjFlag", "1");
		}else if("5".equals(ruleType)){
			map.put("currNode", "01");
			map.put("ydjFlag", "2");
		}else if("6".equals(ruleType)){
			map.put("currNode", "02");
			map.put("ydjFlag", "2");
		}else if("7".equals(ruleType)){
			map.put("currNode", "03");
			map.put("ydjFlag", "2");
		}
		map.put("typeFlag", typeFlag);
		if("2".equals(typeFlag)){
			map.put("crtDate", new Date());
		}
		int num=Integer.parseInt(allotRuleService.countAllotNumber(map)==null?"0":allotRuleService.countAllotNumber(map));
		return num;
	}
	/**
	 * 自动分件查询
	 * @param 
	 * @return
	 * @throws CoreException
	 */
	public AllotNumber queryAllotNumber(String ruleType,String typeFlag) throws CoreException{
		Map<String,Object> map=new HashMap<String,Object>();
		if("1".equals(ruleType)||"3".equals(ruleType)){
			map.put("currNode", "02");
			map.put("ydjFlag", "1");
		}else if("4".equals(ruleType)){
			map.put("currNode", "03");
			map.put("ydjFlag", "1");
		}else if("5".equals(ruleType)){
			map.put("currNode", "01");
			map.put("ydjFlag", "2");
		}else if("6".equals(ruleType)){
			map.put("currNode", "02");
			map.put("ydjFlag", "2");
		}else if("7".equals(ruleType)){
			map.put("currNode", "03");
			map.put("ydjFlag", "2");
		}
		map.put("typeFlag", typeFlag);
		if("2".equals(typeFlag)){
			map.put("crtDate", new Date());
		}
		AllotNumber allotNumber=allotRuleService.queryAllotNumber(map);
		return allotNumber;
	}
	/**
	* @Description:标准卡  征信   main 自动分配主方法 征信队列根据征信策略结果自动分到相应的队列里
	* @author  wangdebin
	* @version  V1.1 二期标卡征信自动
	 */
	public void bzkCreditMain() {
		try {
			String ruleType = "6";
			boolean checkResult=checkMethod("1",ruleType);//校验
			if(!checkResult){//校验不通过
				return;
			}
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> ruleMap = new HashMap<String, Object>();
			List<String> ruleTypeList = new ArrayList<String>();
			ruleTypeList.add(ruleType);
			ruleMap.put("ruleTypeList", ruleTypeList);
			//查询组的情况(根据征信策略自动到相应的队列)
			for(int k=0;k<2;k++){
				if(k==0){//队列
					ruleMap.put("levelType", "1");
				}else if(k==1){//组
					ruleMap.put("levelType", "2");
				}
				List<String> ruleCodeList = allotMappingRuleService.selectRuleCodeByMap(ruleMap);
				// 规则映射到   队列 或  组数量
				int groupNum = 0,applyNum = 0,pingNum = 0,junNum = 0,yuNum = 0,autoYuNum=0,allResult=0;
				if (ruleCodeList.isEmpty() == true) {
					continue;
				}
				List<String> ruleCodeNewList=new ArrayList<String>();
				for(String codeStr:ruleCodeList){
					if(!ruleCodeNewList.contains(codeStr)){
						ruleCodeNewList.add(codeStr);
					}
				}
				for (String ruleCode : ruleCodeNewList) {
					ruleMap.put("ruleCode", ruleCode);
					List<AllotMappingRule> ruleList = allotMappingRuleService.queryAllMappingRule(ruleMap);
					List<String> codeList = new ArrayList<String>();
					List<String> codeList2 = new ArrayList<String>();
					AllotMappingRule amr = new AllotMappingRule();
					// 将该规则映射的组或队列放到集合中
					if (ruleList.size() == 0) {
						log.info("标准卡征信环节没有配置规则或没有将规则映射到队列或组");
						continue;
					}
					for (int i = 0; i < ruleList.size(); i++) {
						amr = ruleList.get(i);
						if (amr != null) {
							if (amr.getLevelCode() != null) {
								codeList2.add(amr.getLevelCode());
							}
						}
					}
					for(int i=0;i<codeList2.size();i++){
						codeList.addAll(Arrays.asList(codeList2.get(i).split(",")));
					}
					//集合去重复的组或队列
					HashSet h=new HashSet(codeList);
					codeList.clear();
					codeList.addAll(h);
					groupNum=codeList.size();
					String ruleScript = "",levelCode="",currName="",levelType="";
					Map<String, Object> map = new HashMap<String, Object>();
					AllotMappingRule allotRule = ruleList.get(0)==null?new AllotMappingRule():ruleList.get(0);
					ruleScript = allotRule.getRuleScript();
					levelType=allotRule.getLevelType();
					JSONObject jasonObject = JSONObject.fromObject(ruleScript);
					map = mapper.readValue(ruleScript, Map.class);
					map.put("ruleType", ruleType);
					map.put("currNode", "02");
					map.put("secondNode", "1");
					map.put("shareWay", "3");
					map.put("strType", "3");
					map.put("userCode", "system");
					map.put("ydjFlag", "2");
					map.put("levelType",levelType);
					if("1".equals(levelType)){
						map.put("flag", "10");
					}else{
						map.put("flag", "8");
					}
					Map<String, Object> allMap = new HashMap<String, Object>();
					//判断自动分件数量,根据数量判断是否继续自动分件
					int autoNum=queryAutoNumber(ruleType, "1");
					AllotNumber allotNumber=queryAllotNumber(ruleType, "2");
					int autoNum2=0;
					if(allotNumber==null){
						saveAutoNumber(ruleType,ruleCode);
					}else{
						autoNum2=queryAutoNumber(ruleType, "2");
					}
					if(autoNum2>=autoNum&&autoNum!=0&&k==1){// 组到组员自动还是手动
						updateByUser(map, codeList,"3");
					}else if(autoNum2<autoNum||autoNum==0){
						allMap = allotApplyService.saveApplyByConditions(map);
						allResult=(Integer) allMap.get("allResult");
						List<AllotApply> list = queryApplyList(map, allResult,"2");
						log.info("AutoTimingAction.bzkCreditMain 满足该标准卡征信环节自动分件解析规则的申请件数量" + list.size());
						applyNum = list.size();
						autoYuNum=autoNum-autoNum2;
						if(autoYuNum<applyNum&&autoNum>0){
							pingNum=autoYuNum/groupNum;
							junNum=autoYuNum/groupNum;
							yuNum=autoYuNum%groupNum;
						}else{
							//平均分配  每个组或队列要分到的申请件(变化中)
							pingNum=applyNum/groupNum;
							//不变的
							junNum=applyNum/groupNum;
							//取余
							yuNum=applyNum%groupNum;
						}
						// 申请件详细信息集合
						List<AllotApply> allotApplyList = new ArrayList<AllotApply>();
						// 申请件编号集合
						List<String> lifeList = new ArrayList<String>();
						Date sqlDate = new Date();
						// 标准卡套卡不进表 不用区分
						for (int j = 0; j < groupNum; j++) {
							levelCode = codeList.get(j).trim();
							map.put("code",levelCode);
							//levelType判断组或者队列
							if("2".equals(levelType)){
								AllotCommon allotCommon = allotCommonService.queryGroupByOrgNo(levelCode);
								//查询组中文
								if (allotCommon != null) {
									currName = allotCommon.getOrgName();
								}
							}
							// 初始化pingNum
							pingNum = junNum;
							while (pingNum > 0 && applyNum > 0) {
								AllotApply allotApply = list.get(pingNum - 1);
								String appId = allotApply.getAppId();
								//log.info("AutoTimingAction.bzkCreditMain 满足该标准卡征信环节自动分件解析规则的申请件流水号==" + appId);
								switch(Integer.parseInt(levelType)){
									case 1:
										allotApply.setCurrQueue(levelCode);
										allotApply.setQueueDate(sqlDate);
										allotApply.setLastQueue(getQueueName(levelCode));
										allotApply.setCurrStatus("6");
										break;
									case 2:
										//查询征信策略 将申请件赋值到征信队列(将.转化为-)
										String creditResult=StringUtils.trimToEmpty(allotApplyService.queryResultByAppId(appId));
										if(!"".equals(creditResult)&&(allotApply.getCurrQueue()==null||"".equals(allotApply.getCurrQueue()))){
											allotApply.setCurrQueue(creditResult.replace(".","-"));
											map.put("code2",creditResult.replace(".","-"));
											allotApply.setLastQueue(getQueueName(creditResult.replace(".","-")));
											allotApply.setQueueDate(sqlDate);
										}
										if(allotApply.getGroupDate()==null){
											allotApply.setGroupDate(sqlDate);
										}
										allotApply.setGroupTeamDate(sqlDate);
										allotApply.setLastGroup(currName);
										allotApply.setCurrGroup(levelCode);
										allotApply.setCurrStatus("1");
										break;
									default:
										break;
								}
								allotApply.setLstTeamDate(sqlDate);
								lifeList.add(appId);
								allotApplyList.add(allotApply);
								list.remove(pingNum - 1);
								pingNum--;
								applyNum--;
							}
							// 开始批量分件
							if (allotApplyList.size() > 0) {
								//二期批次更新
								updateBatchList(allotApplyList, lifeList, map);
								//更新自动分件数量
								updateAutoNumber(ruleType, junNum, ruleCode);
								lifeList.clear();
								allotApplyList.clear();
								log.info("标准卡征信自动分件--批量分件成功!--本次分件申请件数量"
										+ junNum + "个.");
							}
						}
						Random random = new Random();
						// 将余下的件随机分配下去
						while (yuNum > 0 && codeList.size() > 0) {
							int index = random.nextInt((int) codeList.size());
							levelCode = codeList.get(index);
							AllotApply allotApply = list.get(0);
							switch(Integer.parseInt(levelType)){
								case 1:
									allotApply.setCurrQueue(levelCode);
									allotApply.setLastQueue(getQueueName(levelCode));
									allotApply.setQueueDate(sqlDate);
									allotApply.setCurrStatus("6");
									break;
								case 2:
									// 查询组中文名
									AllotCommon allotCommon = allotCommonService.queryGroupByOrgNo(levelCode);
									if (allotCommon != null) {
										currName = allotCommon.getOrgName();
									}
									//查询征信策略 将申请件赋值到征信队列
									String creditResult=StringUtils.trimToEmpty(allotApplyService.queryResultByAppId(allotApply.getAppId()));
									if(!"".equals(creditResult)&&(allotApply.getCurrQueue()==null||"".equals(allotApply.getCurrQueue()))){
										allotApply.setCurrQueue(creditResult.replace(".","-"));
										map.put("code2",creditResult.replace(".","-"));
										allotApply.setLastQueue(getQueueName(creditResult.replace(".","-")));
									}
									if(allotApply.getGroupDate()==null){
										allotApply.setGroupDate(sqlDate);
									}
									allotApply.setGroupTeamDate(sqlDate);
									allotApply.setCurrGroup(levelCode);
									allotApply.setLastGroup(currName);
									allotApply.setCurrStatus("1");
									break;
								default:
									break;
							}
							allotApply.setLstTeamDate(sqlDate);
							lifeList.add(allotApply.getAppId());
							allotApplyList.add(allotApply);
							// 组装数据 单件调用
							map.put("code",levelCode);
							map.put("lifeList", lifeList);
							map.put("allotApplyList", allotApplyList);
							allotMethodService.updateMethod(map);
							//单个更新自动分件数量
							updateAutoNumber(ruleType, 1, ruleCode);
							lifeList.clear();
							allotApplyList.clear();
							//log.info(allotApply.getAppId()+"标准卡征信自动分件--分件成功!");
							list.remove(0);
							yuNum--;
						}
						// 组到组员自动还是手动
						if(k==1){
							updateByUser(map, codeList,"3");
						}
					}
				}
			}
		} catch (Exception e) {
			log.error("AutoTimingAction.bzkCreditMain occur error" + e);
		}
	}
	//维护队列名称
	public String getQueueName(String queueCode){
		String queueName="";
		if("L".equals(queueCode)){
			queueName="低风险人工征信L";
		}else if("L1".equals(queueCode)){
			queueName="低风险自动征信L1";
		}else if("L2".equals(queueCode)){
			queueName="低风险自动征信L2";
		}else if("L3-1".equals(queueCode)){
			queueName="W低风险自动征信L3.1";
		}else if("L3-2".equals(queueCode)){
			queueName="W低风险免电核(人工排查)L3.2";
		}else if("LV".equals(queueCode)){
			queueName="低风险人工征信LV";
		}else if("M".equals(queueCode)){
			queueName="中风险人工征信M";
		}else if("H".equals(queueCode)){
			queueName="高风险人工征信H";
		}else if("H1".equals(queueCode)){
			queueName="高风险自动征信H1";
		}else if("S100".equals(queueCode)){
			queueName="系统征信自动通过";
		}else if("L1-1".equals(queueCode)){
			queueName="低风险征信L1.1";
		}else if("WL1-1".equals(queueCode)){
			queueName="W低风险征信L1.1";
		}else if("LR1-1".equals(queueCode)){
			queueName="低风险征信LR1.1";
		}else if("WLR1-1".equals(queueCode)){
			queueName="W低风险征信LR1.1";
		}else if("LR1-2".equals(queueCode)){
			queueName="低风险征信LR1.2";
		}else if("WLR1-2".equals(queueCode)){
			queueName="W低风险征信LR1.2";
		}else if("LR1-3".equals(queueCode)){
			queueName="低风险人工侧核LR1.3";
		}else if("WLR1-3".equals(queueCode)){
			queueName="W低风险人工侧核LR1.3";
		}else if("LR3".equals(queueCode)){
			queueName="低风险征信LR3";
		}else if("WLR3".equals(queueCode)){
			queueName="W低风险征信LR3";
		}else if("LR2-1".equals(queueCode)){
			queueName="低风险征信LR2.1";
		}else if("WLR2-1".equals(queueCode)){
			queueName="W低风险征信LR2.1";
		}else if("LR2-2".equals(queueCode)){
			queueName="低风险征信LR2.2";
		}else if("WLR2-2".equals(queueCode)){
			queueName="W低风险征信LR2.2";
		}else if("LR2-3".equals(queueCode)){
			queueName="低风险人工侧核LR2.3";
		}else if("WLR2-3".equals(queueCode)){
			queueName="W低风险人工侧核LR2.3";
		}else if("MR2-1".equals(queueCode)){
			queueName="中风险人工审核MR2.1";
		}else if("WMR2-1".equals(queueCode)){
			queueName="W中风险人工审核MR2.1";
		}else if("MR2-2".equals(queueCode)){
			queueName="中风险人工征信-关注正核MR2.2";
		}else if("WMR2-2".equals(queueCode)){
			queueName="W中风险人工征信-关注正核MR2.2";
		}else if("HR".equals(queueCode)){
			queueName="高风险人工征信HR";
		}else if("HR2".equals(queueCode)){
			queueName="高风险人工征信HR-关注正核";
		}else if("RGZX".equals(queueCode)){
			queueName="人工征信";
		}
		return queueName;
	}
	/**
	 * 批量批次更新
	 * @param list、list2
	 * @param lifeMap
	 * @return result
	 */
	public int updateBatchList(List<AllotApply> list,List<String> list2,Map<String,Object> lifeMap)
	throws Exception{
		int result=0,end=0,totalWeight=list.size();
		//更新申请件大于一万件多次更新
		if(totalWeight>commitCountEveryTime){
			//需要提交的次数  
			int	commitCount=(int)Math.ceil(totalWeight/(double)commitCountEveryTime);
			for(int i=0;i<commitCount;i++){
				try {
					if(i<commitCount-1){
						end=commitCountEveryTime;
					}else if(i==commitCount-1){
						end=totalWeight-i*commitCountEveryTime;
					}
					//组装数据
					lifeMap.put("lifeList", list2.subList(0, end));
					lifeMap.put("allotApplyList", list.subList(0, end));
					//分件
					result=allotMethodService.updateBatchMethod(lifeMap);
					list2.subList(0, end).clear();
					list.subList(0, end).clear();
				} catch (Exception e) {
					log.error("分件批量批次更新报错", e);
				}
			}
		}else{
			//组装数据
			lifeMap.put("lifeList", list2);
			lifeMap.put("allotApplyList", list);
			//分件
			result=allotMethodService.updateBatchMethod(lifeMap);
		}
		return result;
	}
	/**
	 * totalWeight-本次自动分件总数量 
	 * @param dataMap  
	 * @return appList 分批次查询返回
	 */
	public List<AllotApply> queryApplyList(Map<String, Object> dataMap,int totalWeight,String ydjFlag) throws Exception{
		//组到人区分标识
		String autoAllot=(String) (dataMap.get("autoAllot")==null?"":dataMap.get("autoAllot"));
		if("1".equals(autoAllot)){
			dataMap.put("strType", "6");
		}else{
			if("1".equals(ydjFlag)){
				dataMap.put("strType", "1");
			}else if("2".equals(ydjFlag)){
				dataMap.put("strType", "4");
			}
		}
		dataMap.put("rownumFlag","true");
		int start=0,end=0;
		List<AllotApply> appList=new ArrayList<AllotApply>();
		if(totalWeight>commitCountEveryTime){
			//需要提交的次数  
			int	commitCount=(int)Math.ceil(totalWeight/(double)commitCountEveryTime);
			for(int i=0;i<commitCount;i++){
				try {
					//开始
					start=i*commitCountEveryTime;
					//数量
					end=Math.min(commitCountEveryTime,totalWeight-start);
					dataMap.put("curNum", start);
					dataMap.put("pageNum", end);
					Map<String,Object> rownumMap=allotApplyService.saveApplyByConditions(dataMap);
					if(rownumMap!=null&&rownumMap.size()>0){
						appList.addAll((List<AllotApply>) rownumMap.get("list2"));
					}
				} catch (Exception e) {
					log.error("转移或回收查询报错", e);
				}
			}
		}else if(totalWeight<commitCountEveryTime&&totalWeight!=0){
			end=totalWeight;
			dataMap.put("curNum", start);
			dataMap.put("pageNum", end);
			Map<String,Object> allMap=allotApplyService.saveApplyByConditions(dataMap);
			if(allMap.size()>0){
				appList=(List<AllotApply>) allMap.get("list2");
			}
		}
		return appList;
	}
	//日志管理
	public String getMsg(String ruleType) throws Exception{
		String msg="";
		if("1".equals(ruleType)){//易达金征信快速
			msg="AutoTimingAction.ydjFastCreditMain 易达金征信快速";
		}else if("3".equals(ruleType)){//易达金征信
			msg="AutoTimingAction.ydjCreditMain 易达金征信环节";
		}else if("5".equals(ruleType)){//标准卡录入
			msg="AutoTimingAction.bzkReviewMain 标准卡录入环节";
		}else if("6".equals(ruleType)){//标准卡征信
			msg="AutoTimingAction.bzkCreditMain 标准卡征信环节";
		}else if("7".equals(ruleType)){//标准卡审批
			msg="AutoTimingAction.bzkApprovalMain 标准卡审批环节";
		}
		return msg;
	}
	public boolean checkMethod(String switchCode,String ruleType) throws Exception{
		String msg= getMsg(ruleType);
		log.info(msg+" Enter");
		AllotSwitch allotSwitch = allotSwitchService.queryAllotSwitchByCode(switchCode);
		String switchStatus = allotSwitch.getSwitchStatus()==null?"0":allotSwitch.getSwitchStatus();
		if(!"1".equals(switchStatus)&&!"1".equals(ruleType)){//易达金快速件不校验 开关模式
			log.info(msg+"总开关为手动模式，需要人工分配操作");
			return false;
		}
		int timeCount = 0,holidayCount=0;
		Map<String, Object> ruleMap = new HashMap<String, Object>();
		List<String> ruleTypeList = new ArrayList<String>();
		ruleTypeList.add(ruleType);
		ruleMap.put("ruleTypeList", ruleTypeList);
		// 查询征信普通件分件时间是否到了
		Date startTime = null,endTime = null, now = null;
		now =ConcurrentDateUtil.parse(ConcurrentDateUtil.format(new Date(System.currentTimeMillis())));
		timeCount = allotMappingRuleService.countAllotTime(ruleMap);
		if(timeCount != 1){
			log.info(msg+"自动分件时间未设置");
			return false;
		}
		//查询是否假期 0否  1是  假期不分件
//		holidayCount=allotSwitchService.queryHoliday(); 
		holidayCount=allotSwitch.getNum();
		if(holidayCount!=0){
			log.info(msg+"今天为节假日，不能自动分件");
			return false;
		}
		// 同时满足在开始和结束时间段内参与分件
		AllotTime allotTime = allotMappingRuleService.queryAllotTime(ruleMap);
		if (allotTime != null) {
			startTime = ConcurrentDateUtil.parse(allotTime.getStartTime());
			endTime = ConcurrentDateUtil.parse(allotTime.getEndTime());
		}
		if (now.getTime() < startTime.getTime() || now.getTime() > endTime.getTime()) {
			log.info(msg+"自动分件时间未在设置范围内");
			return false;
		}
		return true;
	}
	public List<String> getRuleCodeList(String ruleType) throws Exception{
		// 查询标准卡审批环节的映射情况(有多少规则 每条规则对应的组 )
		List<String> ruleCodeList = allotMappingRuleService.queryRuleCode(ruleType);
		List<String> ruleCodeNewList=new ArrayList<String>();
		if(ruleCodeList==null){
			return ruleCodeNewList;
		}
		for(String codeStr:ruleCodeList){
			if(!ruleCodeNewList.contains(codeStr)){
				ruleCodeNewList.add(codeStr);
			}
		}
		return ruleCodeNewList;
	}
	//获取队列或者组
	public List<String> getGroupList(List<AllotMappingRule> ruleList ) throws Exception{
		List<String> codeList = new ArrayList<String>();
		if(ruleList.size()==0){
			return codeList;
		}
		AllotMappingRule amr = new AllotMappingRule();
		// 将该规则映射的组放到集合中
		for(int i=0;i<ruleList.size();i++){
			amr=ruleList.get(i);
			List<String> codeList2=Arrays.asList(amr.getLevelCode().split(","));
			codeList.addAll(codeList2);
		}
		//集合去重复的组
		HashSet h=new HashSet(codeList);
		codeList.clear();
		codeList.addAll(h);
		return codeList;
	}
	
	public void updateByUser(Map<String, Object> map,List<String> codeList,String switchCode) throws Exception{
		String ruleType=map.get("ruleType")==null?"":map.get("ruleType").toString();
		String levelType=map.get("levelType")==null?"":map.get("levelType").toString();
		String msg=getMsg(ruleType);
		// 组到组员自动还是手动
		AllotSwitch allotSwitch2 = allotSwitchService.queryAllotSwitchByCode(switchCode);
		String switchStatus2 = allotSwitch2.getSwitchStatus();
		if(!"1".equals(switchStatus2)&&!"1".equals(ruleType)) {// 组自动,易达金快速件不校验开关模式
			log.info(msg+"组到人分件模式为手动模式，需要人工分配操作");
			return;
		}
		if(codeList.size()==0||!"2".equals(levelType)){
			return;
		}
		List<String> currStatusList=new ArrayList<String>();
		currStatusList.add("1");
		for (String currGroup : codeList) {
			map.put("currGroup", currGroup);
			map.put("currStatusList", currStatusList);
			fastGToU(map);
			log.info(msg+"自动模式完成");
		}
	}
	public void autoYdjModule(String ruleType,String currNode,String strType,String shareWay)  throws Exception{
		String msg=getMsg(ruleType);
		Map<String,Object> ruleMap=new HashMap<String,Object>();
		List<String> ruleTypeList=new ArrayList<String>();
		ruleTypeList.add(ruleType);
		ruleMap.put("ruleTypeList", ruleTypeList);
		List<String> ruleCodeNewList=getRuleCodeList(ruleType);//查询征信环节的映射情况(有多少规则  每条规则对应的组 )
		if(ruleCodeNewList.isEmpty() == true){
			log.info(msg+"没有配置规则或没有将规则映射到队列或组");
			return;
		}
		ObjectMapper mapper = new ObjectMapper();
		//规则映射到组数量
		int groupNum=0,applyNum=0,pingNum=0,junNum=0,yuNum=0,autoYuNum=0;
		for (String ruleCode : ruleCodeNewList) {
			//查询ruleCode映射了几个组 组装数据
			ruleMap.put("ruleCode", ruleCode);
			List<AllotMappingRule> ruleList=allotMappingRuleService.queryAllMappingRule(ruleMap);
			if(ruleList.size() <= 0){
				log.info(msg+"没有配置规则或没有将规则映射到队列或组2");
				return;
			}
			AllotMappingRule amr = ruleList.get(0);//同样规则映射有多个  取第一条取出规则
			if(amr==null){
				log.info(msg+"没有配置规则或没有将规则映射到队列或组3");
				return;
			}
			List<String> codeList =getGroupList(ruleList );
			groupNum=codeList.size();
			//查询规则详细信息
			String ruleScript="";
			Map<String,Object> map=new HashMap<String,Object>();
			ruleScript=amr.getRuleScript();
			JSONObject  jasonObject = JSONObject.fromObject(ruleScript);
			map=mapper.readValue(ruleScript, Map.class);
			map.put("ruleType", ruleType);
			map.put("currNode", currNode);
			map.put("secondNode", "1");
			map.put("strType",strType);
			map.put("shareWay", shareWay);
			map.put("userCode","system");
			map.put("ydjFlag","1");
			map.put("flag","8");
			map.put("levelType","2");
			Map<String,Object> allMap=new HashMap<String,Object>();
			//判断自动分件数量,根据数量判断是否继续自动分件
			int autoNum=queryAutoNumber(ruleType, "1");
			AllotNumber allotNumber=queryAllotNumber(ruleType, "2");
			int autoNum2=0;
			if(allotNumber==null){
				saveAutoNumber(ruleType,ruleCode);
			}else{
				autoNum2=queryAutoNumber(ruleType, "2");
			}
			if(autoNum2>=autoNum&&autoNum!=0){//当天池里自动分件申请件数量已达到设置  不能在池里取件
			    //根据组 groupCode 查相应的组合组员信息 组到组员自动还是手动
				updateByUser(map, codeList, "5");
			}else if(autoNum2<autoNum||autoNum==0){
				//查询符合该规则的申请件总数  
				allMap=allotApplyService.saveReviewPoolByCondition(map);
				List<AllotApply> list =(List<AllotApply>) allMap.get("list2");
				applyNum=list.size();
				autoYuNum=autoNum-autoNum2;
				if(autoYuNum<applyNum&&autoNum>0){
					pingNum=autoYuNum/groupNum;
					junNum=autoYuNum/groupNum;
					yuNum=autoYuNum%groupNum;
				}else{
					//平均分配  每个组要分到的申请件(变化中)
					pingNum=applyNum/groupNum;
					//不变的
					junNum=applyNum/groupNum;
					//取余
					yuNum=applyNum%groupNum;
				}
				String levelCode="";
				//申请件详细信息集合
				List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
				//申请件编号集合  
				List<String> lifeList=new ArrayList<String>();
				Date sqlDate=new Date();
				 for(int j=0;j<groupNum;j++){
					levelCode=codeList.get(j).trim();
				    log.info(msg+"满足自动分件的组代码"+levelCode);
					//查询组中文名
					String currName="";
					AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(levelCode);
					if(allotCommon!=null){
						currName=allotCommon.getOrgName();
					}
					//初始化pingNum
					pingNum=junNum;
					while(pingNum>0 && applyNum>0){
					AllotApply apply=list.get(pingNum-1);
					String appNo=apply.getAppNo();
					//log.info(msg+"满足自动分件解析规则申请件流水号前15位"+appNo);
					if(appNo==null||"".equals(appNo)){
						appNo=apply.getAppId().substring(0,15);
					}
				 	map.put("appId", appNo);
				 	map.put("code", levelCode);
				 	int appNum = allotApplyService.queryHandCount(map);
				 	//套卡区分
					if(appNum==1){//该环节只有一件
						AllotApply allotApply=allotApplyService.queryHandByAppId(map);
						String appId=allotApply.getAppId();
						//log.info(msg+"满足自动分件解析规则申请件流水号"+appId);
						//易达金标识
						String  yf=allotApply.getYdjFlag();
						//套卡标识
						String matchingCardFlag=allotApply.getMatchingCardFlag();
						//捞件情况01  02只捞回一件 仍可以分件
						String laoJianFlag=allotApply.getLaoJianFlag();
						if("0".equals(matchingCardFlag)||("2".equals(yf))||("1".equals(yf)&&("1".equals(matchingCardFlag)||"2".equals(matchingCardFlag))
								&&("01".equals(laoJianFlag)||"02".equals(laoJianFlag)))){//非易达金套卡 或者 捞件
							lifeList.add(allotApply.getAppId());
							allotApply.setCurrGroup(levelCode);
						 	allotApply.setLastGroup(currName);
						 	if(allotApply.getGroupDate()==null){
						 		allotApply.setGroupDate(sqlDate);
						 	}
							allotApply.setGroupTeamDate(sqlDate);
							allotApply.setLstTeamDate(sqlDate);
							allotApply.setCurrNode("02");
						 	allotApply.setCurrStatus("1");
						 	allotApply.setDelStatus("0");
						 	allotApplyList.add(allotApply);
						}
						}else if(appNum==2){//套卡(算一件)
							List<AllotApply> appList = allotApplyService.queryHand(map);
							for(AllotApply app:appList){
								lifeList.add(app.getAppId());
								app.setCurrGroup(levelCode);
								app.setLastGroup(currName);
								if(app.getGroupDate()==null){
									app.setGroupDate(sqlDate);
								}
							 	app.setGroupTeamDate(sqlDate);
							 	app.setLstTeamDate(sqlDate);
							 	app.setCurrNode("02");
							 	app.setCurrStatus("1");
							 	app.setDelStatus("0");
							 	allotApplyList.add(app);
							}
						}
						list.remove(pingNum-1);
						pingNum--;
						applyNum--;
					 }
					    //开始批量分件
					 if(allotApplyList.size()>0){
						//二期批次更新
						updateBatchList(allotApplyList, lifeList, map);
						//更新自动分件数量
						updateAutoNumber(ruleType, junNum, ruleCode);
						lifeList.clear();
						log.info(msg+"自动分件--批量分件成功!--本次分件申请件数量"+junNum+"个.");
						}
						//集合清空
						allotApplyList.clear();
					}
				    Random random = new Random();
					//将余下的件随机分配下去
					while(yuNum>0&&codeList.size()>0){
						int index =random.nextInt((int)codeList.size());
						levelCode=codeList.get(index);
						AllotApply allotApply=list.get(0);
						map.put("appId", allotApply.getAppNo());
						map.put("code",levelCode);
						//套卡区分
						int appNum = allotApplyService.queryHandCount(map);
						//查询组中文名
						String currName="";
						AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(levelCode);
						if(allotCommon!=null){
							currName=allotCommon.getOrgName();
						}
						if(appNum==1){
							AllotApply allotApply2=allotApplyService.queryHandByAppId(map);
							allotApply2.setLstTeamDate(sqlDate);
							String appId=allotApply2.getAppId();
							lifeList.add(appId);
							allotApply2.setCurrGroup(levelCode);
							allotApply2.setLastGroup(currName);
							allotApply2.setGroupTeamDate(sqlDate);
							allotApply2.setLstTeamDate(sqlDate);
							if(allotApply2.getGroupDate()==null){
								allotApply2.setGroupDate(sqlDate);
							}
							allotApply2.setCurrStatus("1");
							allotApplyList.add(allotApply2);
						}else if(appNum==2){//套卡(算一件)
							List<AllotApply> appList = allotApplyService.queryHand(map);
							for(AllotApply app:appList){
								app.setLstTeamDate(sqlDate);
								String appId=app.getAppId();
								lifeList.add(appId);
								app.setCurrGroup(levelCode);
								app.setLastGroup(currName);
								app.setGroupTeamDate(sqlDate);
								app.setLstTeamDate(sqlDate);
								if(app.getGroupDate()==null){
									app.setGroupDate(sqlDate);
								}
								app.setCurrStatus("1");
								allotApplyList.add(app);
							}
						}
						//组装数据 单件调用
						map.put("lifeList", lifeList);
						map.put("allotApplyList", allotApplyList);
						allotMethodService.updateMethod(map);
						//单个更新自动分件数量
						updateAutoNumber(ruleType, 1, ruleCode);
						lifeList.clear();
						allotApplyList.clear();
						list.remove(0);
						yuNum--;
					}
					//根据组 groupCode 查相应的组合组员信息 组到组员自动还是手动
					updateByUser(map, codeList, "5");
			}
		}
	}
	public void autoBzkModule(String ruleType,String currNode,String strType,String shareWay)  throws Exception{
		String msg=getMsg(ruleType);
		Map<String, Object> ruleMap = new HashMap<String, Object>();
		List<String> ruleTypeList = new ArrayList<String>();
		ruleTypeList.add(ruleType);
		ruleMap.put("ruleTypeList", ruleTypeList);
		List<String> ruleCodeNewList=getRuleCodeList(ruleType);
		if(ruleCodeNewList.isEmpty() == true){
			log.info(msg+"没有配置规则或没有将规则映射到队列或组");
			return;
		}
		ObjectMapper mapper = new ObjectMapper();
		// 规则映射到组数量
		int groupNum = 0,applyNum = 0,pingNum = 0,junNum = 0,yuNum = 0,autoYuNum=0,allResult=0;
		for (String ruleCode : ruleCodeNewList) {
			ruleMap.put("ruleCode", ruleCode);
			List<AllotMappingRule> ruleList = allotMappingRuleService.queryAllMappingRule(ruleMap);
			if(ruleList.size() <= 0){
				log.info(msg+"没有配置规则或没有将规则映射到队列或组2");
				return;
			}
			AllotMappingRule amr = ruleList.get(0);//同样规则映射有多个  取第一条取出规则
			if(amr==null){
				log.info(msg+"没有配置规则或没有将规则映射到队列或组3");
				return;
			}
			List<String> codeList =getGroupList(ruleList );
			groupNum=codeList.size();
			Map<String, Object> map = new HashMap<String, Object>();
			String  ruleScript = amr.getRuleScript();
		    JSONObject jasonObject = JSONObject.fromObject(ruleScript);
			map = mapper.readValue(ruleScript, Map.class);
			map.put("ruleType", ruleType);
			map.put("currNode", currNode);
			map.put("secondNode", "1");
			map.put("strType", strType);
			map.put("shareWay", shareWay);
			map.put("userCode", "system");
			map.put("ydjFlag", "2");
			map.put("flag", "8");
			map.put("levelType","2");
			Map<String, Object> allMap = new HashMap<String, Object>();
			//判断自动分件数量,根据数量判断是否继续自动分件
			int autoNum=queryAutoNumber(ruleType, "1");
			AllotNumber allotNumber=queryAllotNumber(ruleType, "2");
			int autoNum2=0;
			if(allotNumber==null){
				saveAutoNumber(ruleType,ruleCode);
			}else{
				autoNum2=queryAutoNumber(ruleType, "2");
			}
			if(autoNum2>=autoNum&&autoNum!=0){// 组到组员自动还是手动
				updateByUser(map, codeList,"3");
			}else if(autoNum2<autoNum||autoNum==0){
				// 查询符合该规则的申请件总数,二期更改 数量大于1万件分批次查询
				allMap = allotApplyService.saveApplyByConditions(map);
				allResult=(Integer) allMap.get("allResult");
				List<AllotApply> list = queryApplyList(map, allResult,"2");
				applyNum = list.size();
				autoYuNum=autoNum-autoNum2;
				if(autoYuNum<applyNum&&autoNum>0){
					pingNum=autoYuNum/groupNum;
					junNum=autoYuNum/groupNum;
					yuNum=autoYuNum%groupNum;
				}else{
					//平均分配  每个组要分到的申请件(变化中)
					pingNum=applyNum/groupNum;
					//不变的
					junNum=applyNum/groupNum;
					//取余
					yuNum=applyNum%groupNum;
				}
				String levelCode = "",currName = "";
				// 申请件详细信息集合
				List<AllotApply> allotApplyList = new ArrayList<AllotApply>();
				// 申请件编号集合
				List<String> lifeList = new ArrayList<String>();
				Date sqlDate = new Date();
				// 标准卡套卡不进表 不用区分
				for (int j = 0; j < groupNum; j++) {
					levelCode = codeList.get(j).trim();
					map.put("code",levelCode);
					log.info(msg+" 满足自动分件的组代码" + levelCode);
					// 查询组中文
					AllotCommon allotCommon = allotCommonService.queryGroupByOrgNo(levelCode);
					if (allotCommon != null) {
						currName = allotCommon.getOrgName();
					}
					// 初始化pingNum
					pingNum = junNum;
					while (pingNum > 0 && applyNum > 0) {
						AllotApply allotApply = list.get(pingNum - 1);
						String appId = allotApply.getAppId();
						lifeList.add(appId);
						//log.info(msg+" 满足自动分件解析规则的申请件流水号=" + appId);
						allotApply.setCurrGroup(levelCode);
						allotApply.setLastGroup(currName);
						allotApply.setLstTeamDate(sqlDate);
						allotApply.setGroupTeamDate(sqlDate);
						//分组日期 审批按征信来 不用变 
						if(allotApply.getGroupDate()==null){
							allotApply.setGroupDate(sqlDate);
						}
						allotApply.setCurrNode(currNode);
						allotApply.setCurrStatus("1");
						allotApply.setDelStatus("0");
						allotApplyList.add(allotApply);
						list.remove(pingNum - 1);
						pingNum--;
						applyNum--;
					}
					// 开始批量分件
					if (allotApplyList.size() > 0) {
						//二期批次更新
						updateBatchList(allotApplyList, lifeList, map);
						//更新自动分件数量
						updateAutoNumber(ruleType, junNum, ruleCode);
						lifeList.clear();
						allotApplyList.clear();
						log.info(msg+"--批量分件成功!--本次分件申请件数量"
								+ junNum + "个.");
					}
				}
				Random random = new Random();
				// 将余下的件随机分配下去
				while (yuNum > 0 && codeList.size() > 0) {
					int index = random.nextInt((int) codeList.size());
					levelCode = codeList.get(index);
					AllotApply allotApply = list.get(0);
					// 查询组中文名
					AllotCommon allotCommon = allotCommonService.queryGroupByOrgNo(levelCode);
					if (allotCommon != null) {
						currName = allotCommon.getOrgName();
					}
					allotApply.setLstTeamDate(sqlDate);
					allotApply.setGroupTeamDate(sqlDate);
					allotApply.setCurrGroup(levelCode);
					allotApply.setLastGroup(currName);
					allotApply.setCurrStatus("1");
					lifeList.add(allotApply.getAppId());
					allotApplyList.add(allotApply);
					// 组装数据 单件调用
					map.put("appId", allotApply.getAppNo());
					map.put("code", levelCode);
					map.put("lifeList", lifeList);
					map.put("allotApplyList", allotApplyList);
					allotMethodService.updateMethod(map);
					//单个更新自动分件数量
					updateAutoNumber(ruleType, 1, ruleCode);
					lifeList.clear();
					allotApplyList.clear();
					//log.info(allotApply.getAppId()+"标准卡审批自动分件--分件成功!");
					list.remove(0);
					yuNum--;
				}
				updateByUser(map, codeList,"3");
			}
		}
	}
}
