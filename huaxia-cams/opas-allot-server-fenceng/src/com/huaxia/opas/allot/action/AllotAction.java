package com.huaxia.opas.allot.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotMappingRule;
import com.huaxia.opas.domain.allot.AllotNumber;
import com.huaxia.opas.domain.allot.AllotRule;
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
public class AllotAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(AllotAction.class);
	
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
	
	private static final int commitCountEveryTime=5000;
	/**
	* @Description:易达金征信  快速件    main 自动分配主方法
	* @Description:目前采用一条映射对应一个组  规则可以多次映射  
	* @author wangdebin 
	* @version  V1.0
	* @param context  
	 */
	public void ydjFastCreditMain() {
		log.info("AllotAction.ydjFastCreditMain Enter  ");
		try {
			//规则映射表  根据快速规则分件
			String ruleType="1";
			int timeCount=0, holidayCount=0;
			Map<String,Object> ruleMap=new HashMap<String,Object>();
			List<String> ruleTypeList=new ArrayList<String>();
			ruleTypeList.add(ruleType);
			ruleMap.put("ruleTypeList", ruleTypeList);
			//查询征信普通件分件时间是否到了
			DateFormat sdf= new SimpleDateFormat("HH:mm");
			Date startTime=null,endTime=null,now=null;
			now=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
			timeCount=allotMappingRuleService.countAllotTime(ruleMap);
			//查询是否假期 0否  1是  假期不分件
			holidayCount=allotSwitchService.queryHoliday();
			if(timeCount==0||holidayCount>0){//未设置自动分件时间 不自动分件
				log.info("AllotAction.ydjCreditMain 易达金征信环节快速件自动分件时间未设置");
			}else if(timeCount==1&&holidayCount==0){
				AllotTime allotTime=allotMappingRuleService.queryAllotTime(ruleMap);
				if(allotTime!=null){
					startTime=sdf.parse(allotTime.getStartTime());
					endTime=sdf.parse(allotTime.getEndTime());
				}
				//同时满足在开始和结束时间段内参与分件
				if(now.getTime()>=startTime.getTime()&&now.getTime()<=endTime.getTime()){
					//规则排序  去重
					List<String> ruleCodeList=allotMappingRuleService.queryRuleCode(ruleType);
					ObjectMapper mapper = new ObjectMapper();
					int groupNum=0,applyNum=0,pingNum=0,junNum=0,yuNum=0,autoYuNum=0;
					if(ruleCodeList.isEmpty()!=true){
						List<String> ruleCodeNewList=new ArrayList<String>();
						for(String codeStr:ruleCodeList){
							if(!ruleCodeNewList.contains(codeStr)){
								ruleCodeNewList.add(codeStr);
							}
						}
						for (String ruleCode : ruleCodeNewList) {
							//查询ruleCode映射了几个组 组装数据
							ruleMap.put("ruleCode", ruleCode);
							List<AllotMappingRule> ruleList=allotMappingRuleService.queryAllMappingRule(ruleMap);
							List<String> codeList=new ArrayList<String>();
							AllotMappingRule amr=new AllotMappingRule();
							//将该规则映射的组放到集合中
							//组集合  
							for(int i=0;i<ruleList.size();i++){
								amr=ruleList.get(i);
								List<String> codeList2=Arrays.asList(amr.getLevelCode().split(","));
								codeList.addAll(codeList2);
							}
							HashSet h=new HashSet(codeList);
							codeList.clear();
							codeList.addAll(h);
							groupNum=codeList.size();
							String ruleScript="";
							Map<String,Object> map=new HashMap<String,Object>();
							if(amr!=null&&ruleList.size()>0){
								ruleScript=amr.getRuleScript();
								JSONObject  jasonObject = JSONObject.fromObject(ruleScript);
								map=mapper.readValue(ruleScript, Map.class);
								map.put("currNode", "02");
								map.put("secondNode", "1");
								map.put("strType","1");
								map.put("shareWay", "1");
								map.put("userCode","system");
								map.put("ydjFlag","1");
								map.put("flag","8");
								Map<String,Object> allMap=new HashMap<String,Object>();
								//判断自动分件数量,根据数量判断是否继续自动分件
								int autoNum=queryAutoNumber(ruleType, "1");
								AllotNumber allotNumber=queryAllotNumber(ruleType, "2");
								int autoNum2=0;
								if(allotNumber==null){
									saveAutoNumber(ruleType);
								}else{
									autoNum2=queryAutoNumber(ruleType, "2");
								}
								if(autoNum2>=autoNum&&autoNum!=0){
									//循环组分件(二期更改 )
									List<String> currStatusList=new ArrayList<String>();
									currStatusList.add("1");
									for(int j=0;j<codeList.size();j++){
										String currGroup=codeList.get(j);
										map.put("currGroup", currGroup);
										map.put("currStatusList", currStatusList);
										//组内循环平均分配
										fastGToU(map);
									}
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
										//平均分配  每个组要分到的申请件(变化中) 向下取整
										int x=applyNum/groupNum;
										pingNum=(int)Math.floor(x);
										log.info("AllotAction.ydjFastCreditMain 平均每组分到申请件个数-"+pingNum);
										//不变的
										junNum=applyNum/groupNum;
										int b=pingNum*(groupNum);
										log.info("AllotAction.ydjFastCreditMain 平均分件总件数-"+b);
										//取余(多余的件)
										 yuNum=applyNum%groupNum;
										log.info("AllotAction.ydjFastCreditMain 平均分件后剩余个数-"+yuNum);
									}
									String levelCode="";
									//申请件详细信息集合
									List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
									//申请件编号集合  
									List<String> lifeList=new ArrayList<String>();
									Date sqlDate=new Date();
									for(int i=0;i<groupNum;i++){//循环平均分配
										levelCode=codeList.get(i).trim();
										//查询组中文名
										String currName="";
										AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(levelCode);
										if(allotCommon!=null){
											currName=allotCommon.getOrgName();
										}
										//初始化pingNum
										pingNum=junNum;
										while(pingNum>0&&applyNum>0){
											AllotApply allotApp=new AllotApply();
											allotApp=list.get(pingNum-1);
											String appNo=allotApp.getAppNo();
											map.put("appId", appNo);
											map.put("code",levelCode);
											//套卡区分
											int appNum = allotApplyService.queryHandCount(map);
											if(appNum==1){
												AllotApply allotApply2=allotApplyService.queryHandByAppId(map);
												String appId=allotApply2.getAppId();
												lifeList.add(appId);
												allotApply2.setCurrGroup(levelCode);
												allotApply2.setLastGroup(currName);
												if(allotApply2.getGroupDate()==null){
													allotApply2.setGroupDate(sqlDate);
												}
												allotApply2.setLstTeamDate(sqlDate);
												allotApply2.setGroupTeamDate(sqlDate);
												allotApply2.setCurrStatus("1");
												allotApplyList.add(allotApply2);
											}else if(appNum==2){//套卡(算一件)
												List<AllotApply> appList = allotApplyService.queryHand(map);
												for(AllotApply app:appList){
													String appId=app.getAppId();
													lifeList.add(appId);
													app.setCurrGroup(levelCode);
													app.setLastGroup(currName);
													if(app.getGroupDate()==null){
														app.setGroupDate(sqlDate);
													}
													app.setLstTeamDate(sqlDate);
													app.setGroupTeamDate(sqlDate);
													app.setCurrStatus("1");
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
											updateAutoNumber(ruleType, junNum);
											lifeList.clear();
											log.info("易达金征信快速件自动分件成功!--本次分件申请件数量"+pingNum+"个.");
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
											String appId=allotApply2.getAppId();
											lifeList.add(appId);
											allotApply2.setCurrGroup(levelCode);
											allotApply2.setLastGroup(currName);
											if(allotApply2.getGroupDate()==null){
												allotApply2.setGroupDate(sqlDate);
											}
											allotApply2.setLstTeamDate(sqlDate);
											allotApply2.setGroupTeamDate(sqlDate);
											allotApply2.setCurrStatus("1");
											allotApplyList.add(allotApply2);
										}else if(appNum==2){//套卡(算一件)
											List<AllotApply> appList = allotApplyService.queryHand(map);
											for(AllotApply app:appList){
												String appId=app.getAppId();
												lifeList.add(appId);
												app.setCurrGroup(levelCode);
												app.setLastGroup(currName);
												if(app.getGroupDate()==null){
													app.setGroupDate(sqlDate);
												}
												app.setGroupTeamDate(sqlDate);
												app.setLstTeamDate(sqlDate);
												app.setCurrStatus("1");
												allotApplyList.add(app);
											}
										}
										//组装数据 单件调用
										map.put("lifeList", lifeList);
										map.put("allotApplyList", allotApplyList);
										allotMethodService.updateMethod(map);
										//单个更新自动分件数量
										updateAutoNumber(ruleType, 1);
										lifeList.clear();
										allotApplyList.clear();
										log.info("易达金征信84、85卡自动分件--分件成功!");
										list.remove(0);
										yuNum--;
									}
									//循环组分件
									List<String> currStatusList=new ArrayList<String>();
									currStatusList.add("1");
									for(int j=0;j<codeList.size();j++){
										levelCode=codeList.get(j);
										map.put("currGroup", levelCode);
										map.put("currStatusList", currStatusList);
										//组内循环平均分配
										fastGToU(map);
									}
								}
								
							}else{
								log.info("该规则已不存在");
							}
						}
					}else{
						log.info("易达金征信环节快速件没有配置规则或没有将规则映射组");
					}
				}
			}
		} catch (Exception e) {
			log.error("AllotAction.ydjFastCreditMain occur error ", e);
		}
		log.info("AllotAction.ydjFastCreditMain Out  ");
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
			String switchCode="4";
			AllotSwitch allotSwitch=allotSwitchService.queryAllotSwitchByCode(switchCode);
			String switchStatus=allotSwitch.getSwitchStatus()==null?"0":allotSwitch.getSwitchStatus();
			if("1".equals(switchStatus)){//待分配池为自动
				String ruleType="3",userCode="system";
				int timeCount=0,holidayCount=0;
				Map<String,Object> ruleMap=new HashMap<String,Object>();
				List<String> ruleTypeList=new ArrayList<String>();
				ruleTypeList.add(ruleType);
				ruleMap.put("ruleTypeList", ruleTypeList);
				//查询征信普通件分件时间是否到了
				DateFormat sdf= new SimpleDateFormat("HH:mm");
				Date startTime=null,endTime=null,now=null;
				now=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
				timeCount=allotMappingRuleService.countAllotTime(ruleMap);
				//查询是否假期 0否  1是  假期不分件
				holidayCount=allotSwitchService.queryHoliday();
				if(timeCount==0||holidayCount>0){//未设置自动分件时间  不自动分件
					log.info("AllotAction.ydjCreditMain 易达金征信环节自动分件时间未设置");
				}else if(timeCount==1&&holidayCount==0){
					AllotTime allotTime=allotMappingRuleService.queryAllotTime(ruleMap);
					if(allotTime!=null){
						startTime=sdf.parse(allotTime.getStartTime());
						endTime=sdf.parse(allotTime.getEndTime());
					}
					//同时满足在开始和结束时间段内参与分件
					if(now.getTime()>=startTime.getTime()&&now.getTime()<=endTime.getTime()){
						//查询征信环节的映射情况(有多少规则  每条规则对应的组 )
						List<String> ruleCodeList=allotMappingRuleService.queryRuleCode(ruleType);
						ObjectMapper mapper = new ObjectMapper();
						//规则映射到组数量
						int groupNum=0,applyNum=0,pingNum=0,junNum=0,yuNum=0,autoYuNum=0;
						if(ruleCodeList.isEmpty()!=true){
							List<String> ruleCodeNewList=new ArrayList<String>();
							for(String codeStr:ruleCodeList){
								if(!ruleCodeNewList.contains(codeStr)){
									ruleCodeNewList.add(codeStr);
								}
							}
							for (String ruleCode : ruleCodeNewList) {
								//查询ruleCode映射了几个组 组装数据
								ruleMap.put("ruleCode", ruleCode);
								List<AllotMappingRule> ruleList=allotMappingRuleService.queryAllMappingRule(ruleMap);
								List<String> codeList=new ArrayList<String>();
								AllotMappingRule amr=new AllotMappingRule();
								//将该规则映射的组放到集合中
								for(int i=0;i<ruleList.size();i++){
									amr=ruleList.get(i);
									List<String> codeList2=Arrays.asList(amr.getLevelCode().split(","));
									codeList.addAll(codeList2);
								}
								//集合去重复的组
								HashSet h=new HashSet(codeList);
								codeList.clear();
								codeList.addAll(h);
								groupNum=codeList.size();
								//查询规则详细信息
								String ruleScript="";
								Map<String,Object> map=new HashMap<String,Object>();
								if(amr!=null&&ruleList.size()>0){
									ruleScript=amr.getRuleScript();
									JSONObject  jasonObject = JSONObject.fromObject(ruleScript);
									map=mapper.readValue(ruleScript, Map.class);
									map.put("currNode", "02");
									map.put("secondNode", "1");
									map.put("strType","1");
									map.put("shareWay", "2");
									map.put("userCode",userCode);
									map.put("ydjFlag","1");
									map.put("flag","8");
									Map<String,Object> allMap=new HashMap<String,Object>();
									//判断自动分件数量,根据数量判断是否继续自动分件
									int autoNum=queryAutoNumber(ruleType, "1");
									AllotNumber allotNumber=queryAllotNumber(ruleType, "2");
									int autoNum2=0;
									if(allotNumber==null){
										saveAutoNumber(ruleType);
									}else{
										autoNum2=queryAutoNumber(ruleType, "2");
									}
									if(autoNum2>=autoNum&&autoNum!=0){//当天池里自动分件申请件数量已达到设置  不能在池里取件
									    //根据组 groupCode 查相应的组合组员信息 组到组员自动还是手动
										String switchCode2="5";
										AllotSwitch allotSwitch2=allotSwitchService.queryAllotSwitchByCode(switchCode2);
										String switchStatus2=allotSwitch2.getSwitchStatus();
										List<String> currStatusList=new ArrayList<String>();
										currStatusList.add("1");
										if("1".equals(switchStatus2)&&codeList.size()>0){//组自动
											for(String currGroup:codeList){
												//组内循环平均分配
												map.put("currGroup", currGroup);
												map.put("currStatusList", currStatusList);
												fastGToU(map);
											}
										}else{
											log.info("AllotAction.ydjCreditMain--易达金征信环节组到人分件模式为手动模式，需要人工分配操作");
										}
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
										    log.info("AllotAction.ydjCreditMain 满足易达金征信环节自动分件的组代码"+levelCode);
											//查询组中文名
											String currName="";
											AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(levelCode);
											if(allotCommon!=null){
												currName=allotCommon.getOrgName();
											}
											//初始化pingNum
											pingNum=junNum;
											while(pingNum>0 && applyNum>0){
											AllotApply apply=new AllotApply();
											apply=list.get(pingNum-1);
											String appNo=apply.getAppNo();
											log.info("AllotAction.ydjCreditMain 满足易达金征信环节自动分件解析规则申请件流水号前15位"+appNo);
											if(appNo==null||"".equals(appNo)){
												appNo=apply.getAppId().substring(0,15);
											}
										 	map.put("appId", appNo);
										 	map.put("code", levelCode);
										 	int appNum = allotApplyService.queryHandCount(map);
										 	//套卡区分
											if(appNum==1){//该环节只有一件
												AllotApply allotApply=new AllotApply();
												allotApply=allotApplyService.queryHandByAppId(map);
												String appId=allotApply.getAppId();
												log.info("AllotAction.ydjCreditMain 满足易达金征信环节自动分件解析规则申请件流水号"+appId);
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
												updateAutoNumber(ruleType, junNum);
												lifeList.clear();
												log.info("易达金征信自动分件--批量分件成功!--本次分件申请件数量"+junNum+"个.");
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
												updateAutoNumber(ruleType, 1);
												lifeList.clear();
												allotApplyList.clear();
												list.remove(0);
												yuNum--;
											}
											    //根据组 groupCode 查相应的组合组员信息 组到组员自动还是手动
												String switchCode2="5";
												AllotSwitch allotSwitch2=allotSwitchService.queryAllotSwitchByCode(switchCode2);
												String switchStatus2=allotSwitch2.getSwitchStatus();
												if("1".equals(switchStatus2)){//组自动
													List<String> currStatusList=new ArrayList<String>();
													currStatusList.add("1");
													for(String currGroup:codeList){
														//组内循环平均分配
														map.put("currGroup", currGroup);
														map.put("currStatusList", currStatusList);
														fastGToU(map);
														log.info("AllotAction.ydjCreditMain--易达金征信环节自动分件完成");
													}
												}else{
													log.info("AllotAction.ydjCreditMain--易达金征信环节组到人分件模式为手动模式，需要人工分配操作");
												}
									}
								
								}else{
									log.info("该规则已不存在");
								}
							}
						}else{
							log.info("易达金征信环节普通件没有配置规则或没有将规则映射组");
						}
					}else{
						log.info("易达金征信环节普通件分件时间不在设置时间内");
					}
				}
			}else{
				log.info("易达金征信环节普通件分件模式总开关为手动模式，需要人工分配操作");
			}
		} catch (Exception e) {
			log.error("AllotAction.ydjCreditMain occur error", e);
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
			String switchCode = "1";
			AllotSwitch allotSwitch = allotSwitchService.queryAllotSwitchByCode(switchCode);
			String switchStatus = allotSwitch.getSwitchStatus()==null?"0":allotSwitch.getSwitchStatus();
			if ("1".equals(switchStatus)) {// 待分配池为自动
				int timeCount = 0,holidayCount=0;
				String ruleType = "5";
				Map<String, Object> ruleMap = new HashMap<String, Object>();
				List<String> ruleTypeList = new ArrayList<String>();
				ruleTypeList.add(ruleType);
				ruleMap.put("ruleTypeList", ruleTypeList);
				// 查询征信普通件分件时间是否到了
				DateFormat sdf = new SimpleDateFormat("HH:mm");
				Date startTime = null,endTime = null,now = null;
				now = sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
				timeCount = allotMappingRuleService.countAllotTime(ruleMap);
				//查询是否假期 0否  1是  假期不分件
				holidayCount=allotSwitch.getNum();
				//holidayCount=allotSwitchService.queryHoliday();
				if (timeCount == 0||holidayCount>0) {// 未设置自动分件时间 默认
					log.info("AllotAction.bzkReviewMain 标准卡审查环节自动分件时间未设置");
				} else if (timeCount == 1&&holidayCount==0) {
					AllotTime allotTime = allotMappingRuleService.queryAllotTime(ruleMap);
					if (allotTime != null) {
						startTime = sdf.parse(allotTime.getStartTime());
						endTime = sdf.parse(allotTime.getEndTime());
					}
					// 同时满足在开始和结束时间段内参与分件
					if (now.getTime() >= startTime.getTime() && now.getTime() <= endTime.getTime()) {
						// 查询征信环节的映射情况(有多少规则 每条规则对应的组 )排序  去重
						List<String> ruleCodeList = allotMappingRuleService.queryRuleCode(ruleType);
						ObjectMapper mapper = new ObjectMapper();
						// 规则映射到组数量
						int groupNum = 0,applyNum = 0,pingNum = 0,junNum = 0,yuNum = 0,autoYuNum=0,allResult=0;
						if (ruleCodeList.isEmpty() != true) {
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
								groupNum=codeList.size();
								String ruleScript = "";
								Map<String, Object> map = new HashMap<String, Object>();
								if (amr != null && ruleList.size() > 0) {
									ruleScript = amr.getRuleScript();
									JSONObject jasonObject = JSONObject.fromObject(ruleScript);
									map = mapper.readValue(ruleScript, Map.class);
									map.put("currNode", "01");
									map.put("secondNode", "1");
									map.put("strType", "3");
									map.put("shareWay", "3");
									map.put("userCode", "system");
									map.put("ydjFlag", "2");
									map.put("flag", "8");
									Map<String, Object> allMap = new HashMap<String, Object>();
									//判断自动分件数量,根据数量判断是否继续自动分件
									int autoNum=queryAutoNumber(ruleType, "1");
									AllotNumber allotNumber=queryAllotNumber(ruleType, "2");
									int autoNum2=0;
									if(allotNumber==null){
										saveAutoNumber(ruleType);
									}else{
										autoNum2=queryAutoNumber(ruleType, "2");
									}
									if(autoNum2>=autoNum&&autoNum!=0){
										// 组到组员自动还是手动
										String switchCode2 = "3";
										AllotSwitch allotSwitch2 = allotSwitchService.queryAllotSwitchByCode(switchCode2);
										String switchStatus2 = allotSwitch2.getSwitchStatus();
										List<String> currStatusList = new ArrayList<String>();
										currStatusList.add("1");
										if ("1".equals(switchStatus2)) {// 组自动
											for (String currGroup : codeList) {
												map.put("currGroup", currGroup);
												map.put("currStatusList", currStatusList);
												fastGToU(map);
												log.info("AllotAction.bzkReviewMain 标准卡审查环节自动模式完成");
											}
										} else {
											log.info("AllotAction.bzkReviewMain 标准卡审查环节组到人分件模式为手动模式，需要人工分配操作");
										}
									}else if(autoNum2<autoNum||autoNum==0){
										// 查询符合该规则的申请件总数
										allMap = allotApplyService.saveApplyByConditions(map);
										allResult=(Integer) allMap.get("allResult");
										List<AllotApply> list = queryApplyList(map, allResult,"2");
										//符合条件的申请件数量
										applyNum = list.size();
										//自动分件设置数量减去已自动分件数量的自动分件数量
										autoYuNum=autoNum-autoNum2;
										int applyNum5000 = applyNum > 5000 ? 5000 : applyNum;//控制执行一次定时任务，最多处理5000件 add by wenyh
										autoYuNum = autoNum - autoNum2;
										if(autoYuNum<applyNum5000&&autoNum>0){
											pingNum=autoYuNum/groupNum;
											junNum=autoYuNum/groupNum;
											yuNum=autoYuNum%groupNum;
										}else{
											//平均分配  每个组要分到的申请件(变化中)
											pingNum=applyNum5000/groupNum;
											//不变的
											junNum=applyNum5000/groupNum;
											//取余
											yuNum=applyNum5000%groupNum;
										}
										String levelCode = "";
										// 申请件详细信息集合
										List<AllotApply> allotApplyList = new ArrayList<AllotApply>();
										// 申请件编号集合
										List<String> lifeList = new ArrayList<String>();
										Date sqlDate = new Date();
										// 标准卡套卡不进表 不用区分
										for (int j = 0; j < groupNum; j++) {
											levelCode = codeList.get(j).trim();
											map.put("code", levelCode);
											log.info("AllotAction.bzkReviewMain 满足标准卡审查环节环节自动分件的组代码" + levelCode);
											// 查询组中文
											String currName = "";
											AllotCommon allotCommon = allotCommonService.queryGroupByOrgNo(levelCode);
											if (allotCommon != null) {
												currName = allotCommon.getOrgName();
											}
											// 初始化pingNum
											pingNum = junNum;
											while (pingNum > 0 && applyNum > 0) {
												AllotApply allotApply = new AllotApply();
												allotApply = list.get(pingNum - 1);
												String appId = allotApply.getAppId();
												lifeList.add(appId);
												log.info("AllotAction.bzkReviewMain 满足该标准卡审查环节自动分件解析规则的申请件流水号=" + appId);
												allotApply.setCurrGroup(levelCode);
												allotApply.setLastGroup(currName);
												if(allotApply.getGroupDate()==null){
													allotApply.setGroupDate(sqlDate);
												}
												allotApply.setLstTeamDate(sqlDate);
												allotApply.setGroupTeamDate(sqlDate);
												allotApply.setCurrNode("01");
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
												updateAutoNumber(ruleType, junNum);
												lifeList.clear();
												allotApplyList.clear();
												log.info("标准卡审查自动分件--批量分件成功!--本次分件申请件数量"
														+ pingNum + "个.");
											}
										}
										Random random = new Random();
										// 将余下的件随机分配下去
										while (yuNum > 0 && codeList.size() > 0) {
											int index = random.nextInt((int) codeList.size());
											levelCode = codeList.get(index);
											AllotApply allotApply = list.get(0);
											allotApply.setLstTeamDate(sqlDate);
											lifeList.add(allotApply.getAppId());
											allotApply.setCurrGroup(levelCode);
											// 查询组中文名
											String currName = "";
											AllotCommon allotCommon = allotCommonService.queryGroupByOrgNo(levelCode);
											if (allotCommon != null) {
												currName = allotCommon.getOrgName();
											}
											allotApply.setLastGroup(currName);
											allotApply.setGroupDate(sqlDate);
											allotApply.setLstTeamDate(sqlDate);
											allotApply.setGroupTeamDate(sqlDate);
											allotApply.setCurrStatus("1");
											allotApplyList.add(allotApply);
											// 组装数据 单件调用
											map.put("code",levelCode);
											map.put("lifeList", lifeList);
											map.put("allotApplyList", allotApplyList);
											allotMethodService.updateMethod(map);
											//单个更新自动分件数量
											updateAutoNumber(ruleType, 1);
											lifeList.clear();
											allotApplyList.clear();
											log.info(allotApply.getAppId()+"标准卡审查环节自动分件--单件分件成功!");
											list.remove(0);
											yuNum--;
										}
										// 组到组员自动还是手动
										String switchCode2 = "3";
										AllotSwitch allotSwitch2 = allotSwitchService.queryAllotSwitchByCode(switchCode2);
										String switchStatus2 = allotSwitch2.getSwitchStatus();
										if ("1".equals(switchStatus2)) {// 组自动
											List<String> currStatusList=new ArrayList<String>();
											currStatusList.add("1");
											for (String currGroup : codeList) {
												map.put("currGroup", currGroup);
												map.put("currStatusList", currStatusList);
												fastGToU(map);
												log.info("AllotAction.bzkReviewMain 标准卡审查环节自动模式完成");
											}
										} else {
											log.info("AllotAction.bzkReviewMain 标准卡审查环节组到人分件模式为手动模式，需要人工分配操作");
										}
									}
								
								}
							}
						} else {
							log.info("标准卡审查自动环节没有配置规则或没有将规则映射到队列或组");
						}
					}
				}
			} else {
				log.info("标准卡审查自动环节分件模式总开关为手动模式，需要人工分配操作");
			}
		} catch (Exception e) {
			log.error("AllotAction.bzkReviewMain occur error", e);
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
			String switchCode = "1";
			AllotSwitch allotSwitch = allotSwitchService.queryAllotSwitchByCode(switchCode);
			String switchStatus = allotSwitch.getSwitchStatus()==null?"0":allotSwitch.getSwitchStatus();
			if ("1".equals(switchStatus)) {// 待分配池为自动
				int timeCount = 0,holidayCount=0;
				String ruleType = "7";
				Map<String, Object> ruleMap = new HashMap<String, Object>();
				List<String> ruleTypeList = new ArrayList<String>();
				ruleTypeList.add(ruleType);
				ruleMap.put("ruleTypeList", ruleTypeList);
				// 查询征信普通件分件时间是否到了
				DateFormat sdf = new SimpleDateFormat("HH:mm");
				Date startTime = null,endTime = null, now = null;
				now = sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
				timeCount = allotMappingRuleService.countAllotTime(ruleMap);
				//查询是否假期 0否  1是  假期不分件
				holidayCount=allotSwitchService.queryHoliday();
				if (timeCount == 0||holidayCount>0) {// 未设置自动分件时间 默认
					log.info("AllotAction.bzkApprovalMain 标准卡审批环节自动分件时间未设置");
				} else if (timeCount == 1&&holidayCount==0) {
					AllotTime allotTime = allotMappingRuleService.queryAllotTime(ruleMap);
					if (allotTime != null) {
						startTime = sdf.parse(allotTime.getStartTime());
						endTime = sdf.parse(allotTime.getEndTime());
					}
					// 同时满足在开始和结束时间段内参与分件
					if (now.getTime() >= startTime.getTime() && now.getTime() <= endTime.getTime()) {
						// 查询标准卡审批环节的映射情况(有多少规则 每条规则对应的组 )
						List<String> ruleCodeList = allotMappingRuleService.queryRuleCode(ruleType);
						ObjectMapper mapper = new ObjectMapper();
						// 规则映射到组数量
						int groupNum = 0,applyNum = 0,pingNum = 0,junNum = 0,yuNum = 0,autoYuNum=0,allResult=0;
						if (ruleCodeList.isEmpty() != true) {
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
								groupNum=codeList.size();
								String ruleScript = "";
								Map<String, Object> map = new HashMap<String, Object>();
								if (amr != null && ruleList.size() > 0) {
									ruleScript = amr.getRuleScript();
									JSONObject jasonObject = JSONObject.fromObject(ruleScript);
									map = mapper.readValue(ruleScript, Map.class);
									map.put("currNode", "03");
									map.put("secondNode", "1");
									map.put("strType", "3");
									map.put("shareWay", "3");
									map.put("userCode", "system");
									map.put("ydjFlag", "2");
									map.put("flag", "8");
									Map<String, Object> allMap = new HashMap<String, Object>();
									//判断自动分件数量,根据数量判断是否继续自动分件
									int autoNum=queryAutoNumber(ruleType, "1");
									AllotNumber allotNumber=queryAllotNumber(ruleType, "2");
									int autoNum2=0;
									if(allotNumber==null){
										saveAutoNumber(ruleType);
									}else{
										autoNum2=queryAutoNumber(ruleType, "2");
									}
									if(autoNum2>=autoNum&&autoNum!=0){
										// 组到组员自动还是手动
										String switchCode2 = "3";
										AllotSwitch allotSwitch2 = allotSwitchService.queryAllotSwitchByCode(switchCode2);
										String switchStatus2 = allotSwitch2.getSwitchStatus();
										List<String> currStatusList=new ArrayList<String>();
										currStatusList.add("1");
										if ("1".equals(switchStatus2)) {// 组自动
											for (String currGroup : codeList) {
												map.put("currGroup", currGroup);
												map.put("currStatusList", currStatusList);
												fastGToU(map);
												log.info("AllotAction.bzkApprovalMain 标准卡审批环节自动模式完成");
											}
										} else {
											log.info("AllotAction.bzkApprovalMain 标准卡审批环节组到人分件模式为手动模式，需要人工分配操作");
										}
									}else if(autoNum2<autoNum||autoNum==0){
										// 查询符合该规则的申请件总数,二期更改 数量大于1万件分批次查询
										allMap = allotApplyService.saveApplyByConditions(map);
										allResult=(Integer) allMap.get("allResult");
										List<AllotApply> list = queryApplyList(map, allResult,"2");
										applyNum = list.size();
										autoYuNum = autoNum - autoNum2;
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
											log.info("AllotAction.bzkApprovalMain 满足标准卡审批环节环节自动分件的组代码" + levelCode);
											// 查询组中文
											AllotCommon allotCommon = allotCommonService.queryGroupByOrgNo(levelCode);
											if (allotCommon != null) {
												currName = allotCommon.getOrgName();
											}
											// 初始化pingNum
											pingNum = junNum;
											while (pingNum > 0 && applyNum > 0) {
												AllotApply allotApply = new AllotApply();
												allotApply = list.get(pingNum - 1);
												String appId = allotApply.getAppId();
												lifeList.add(appId);
												log.info("AllotAction.bzkApprovalMain 满足该标准卡审批环节自动分件解析规则的申请件流水号=" + appId);
												allotApply.setCurrGroup(levelCode);
												allotApply.setLastGroup(currName);
												allotApply.setLstTeamDate(sqlDate);
												allotApply.setGroupTeamDate(sqlDate);
												//分组日期 按征信来 不用变
												allotApply.setCurrNode("03");
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
												updateAutoNumber(ruleType, junNum);
												lifeList.clear();
												allotApplyList.clear();
												log.info("标准卡审批自动分件--批量分件成功!--本次分件申请件数量"
														+ pingNum + "个.");
											}
										}
										Random random = new Random();
										// 将余下的件随机分配下去
										while (yuNum > 0 && codeList.size() > 0) {
											int index = random.nextInt((int) codeList.size());
											levelCode = codeList.get(index);
											AllotApply allotApply = list.get(0);
											map.put("appId", allotApply.getAppNo());
											map.put("code", levelCode);
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
											map.put("lifeList", lifeList);
											map.put("allotApplyList", allotApplyList);
											allotMethodService.updateMethod(map);
											//单个更新自动分件数量
											updateAutoNumber(ruleType, 1);
											lifeList.clear();
											allotApplyList.clear();
											log.info(allotApply.getAppId()+"标准卡审批自动分件--分件成功!");
											list.remove(0);
											yuNum--;
										}
										// 组到组员自动还是手动
										String switchCode2 = "3";
										AllotSwitch allotSwitch2 = allotSwitchService.queryAllotSwitchByCode(switchCode2);
										String switchStatus2 = allotSwitch2.getSwitchStatus();
										if ("1".equals(switchStatus2)) {// 组自动
											List<String> currStatusList=new ArrayList<String>();
											currStatusList.add("1");
											for (String currGroup : codeList) {
												map.put("currGroup", currGroup);
												map.put("currStatusList", currStatusList);
												fastGToU(map);
												log.info("AllotAction.bzkApprovalMain 标准卡审批环节自动模式完成");
											}
										} else {
											log.info("AllotAction.bzkApprovalMain 标准卡审批环节组到人分件模式为手动模式，需要人工分配操作");
										}
									}
								
								}
							}
						} else {
							log.info("标准卡审批自动环节没有配置规则或没有将规则映射到队列或组");
						}
					}
				}

			} else {
				log.info("标准卡审批自动环节分件模式总开关为手动模式，需要人工分配操作");
			}
		} catch (Exception e) {
			log.error("AllotAction.bzkApprovalMain occur error", e);
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
		log.info("AllotAction.fastGToU  Enter" );
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
			if (allotAppList.size() > 0) {
				// 申请件详细信息集合
				List<AllotApply> allotApplyList = new ArrayList<AllotApply>();
				// 申请件编号集合
				List<String> lifeList = new ArrayList<String>();
				// 查询该组信息
				allotCommom = allotCommonService.queryGroupByOrgNo(currGroup);
				// 全部满足分件的组员信息
				List<AllotCommon> allotUserList = allotCommonService.queryAllotUserByOrgId(dataMap, allotCommom);
				Map<String, Object> lifeMap = new HashMap<String, Object>();
				List<String> currStatusList=new ArrayList<String>();
				currStatusList.add("1");
				lifeMap.put("currNode", currNode);
				lifeMap.put("currStatusList", currStatusList);
				lifeMap.put("ydjFlag", ydjFlag);
				lifeMap.put("secondNode", "3");
				lifeMap.put("flag", "9");
				if (allotUserList != null && allotUserList.size() > 0 && allotAppList != null
						&& allotAppList.size() > 0) {
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
				} else {
					log.info("AllotAction.fastGToU 自动分件组内没有该环节的组员");
					result = false;
				}
			} else {
				log.info("AllotAction.fastGToU   自动分件组内没有待分配的件");
				result = true;
			}
		} catch (CoreException e) {
			log.error("AllotFastAction.fastGToU occur 自动分件发生异常 - error", e);
		}
		log.info("AllotAction.fastGToU  Out");
		return result;
	}
	/**
	 * 从申请表查出符合规则的内审编号
	 * @param comShareQueue
	 * @return
	 * @throws CoreException
	 */
	public static  StringBuffer findAppId(AllotRule allotRule) throws CoreException {
		String rulesInfo = StringUtils.isNotBlank(allotRule.getRuleScript()) ? allotRule.getRuleScript() : "";
		// 解析规则字符串
		String[] rules = rulesInfo.split("\\;");
//		StringBuffer conditions = new StringBuffer("node_no='" + nodeNo + "'  ");
		StringBuffer conditions = new StringBuffer("");
		String a="FAST_ISSUE_FLAG|=|f;";
		if (!"".equals(rulesInfo)) {
			for (int i = 0; i < rules.length; i++) {
				String rule = rules[i];
				String[] r = rule.split("\\|");
				String r0 = StringUtils.trimToEmpty(r[0]);
				String r1 = r[1];
				String r2 = r[2];
				if ("ROWNUM".equalsIgnoreCase(r0)) {
					r1 = " <= ";
				}
				String rr = r0 + " " + r1 + "  '" + r2 + "'  ";
				// if (i==rules.length-1) {
				// conditions.append(rr);
				// }else{
				// conditions.append(rr+OPERATOR+" ");
				// }
				conditions.append(OPERATOR + "  " + rr + "  ");
			}
		}
		return conditions;
	}
	/**
	 * 自动分件数量插入
	 * @param 
	 * @return
	 * @throws CoreException
	 */
	public synchronized void saveAutoNumber(String ruleType) throws CoreException{
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
	public synchronized void updateAutoNumber(String ruleType,int autoNumber) throws CoreException{
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
			log.info("AllotAction.bzkCreditMain Enter");
			String switchCode = "1",ruleType = "6";
			AllotSwitch allotSwitch = allotSwitchService.queryAllotSwitchByCode(switchCode);
			String switchStatus = allotSwitch.getSwitchStatus();
			if ("1".equals(switchStatus)) {// 待分配池为自动
				int timeCount = 0,holidayCount=0;
				Date startTime = null,endTime = null,now = null;
				Map<String, Object> ruleMap = new HashMap<String, Object>();
				List<String> ruleTypeList = new ArrayList<String>();
				ruleTypeList.add(ruleType);
				ruleMap.put("ruleTypeList", ruleTypeList);
				// 查询征信普通件分件时间是否到了
				DateFormat sdf = new SimpleDateFormat("HH:mm");
				now = sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
				//查询是否假期 0否  1是  假期不分件
				holidayCount=allotSwitchService.queryHoliday();
				timeCount = allotMappingRuleService.countAllotTime(ruleMap);
				if (timeCount == 0||holidayCount>0) {// 未设置自动分件时间 默认
					log.info("AllotAction.bzkCreditMain 标准卡征信环节自动分件时间未设置");
				} else if (timeCount == 1&&holidayCount==0) {
					AllotTime allotTime = allotMappingRuleService.queryAllotTime(ruleMap);
					if (allotTime != null) {
						startTime = sdf.parse(allotTime.getStartTime());
						endTime = sdf.parse(allotTime.getEndTime());
					}
					ObjectMapper mapper = new ObjectMapper();
					// 同时满足在开始和结束时间段内参与分件,根据ruleType 和levelType一起分件根据映射到队列还是组分件 
					if (now.getTime() >= startTime.getTime() && now.getTime() <= endTime.getTime()) {
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
							if (ruleCodeList.isEmpty() != true) {
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
									if (ruleList.size() > 0) {
										groupNum = ruleList.size();
										for (int i = 0; i < ruleList.size(); i++) {
											amr = ruleList.get(i);
											if (amr != null) {
												if (amr.getLevelCode() != null) {
													codeList2.add(amr.getLevelCode());
												}
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
									if (amr != null && ruleList.isEmpty() != true) {
										AllotMappingRule allotRule = ruleList.get(0)==null?new AllotMappingRule():ruleList.get(0);
										ruleScript = allotRule.getRuleScript();
										levelType=allotRule.getLevelType();
										JSONObject jasonObject = JSONObject.fromObject(ruleScript);
										map = mapper.readValue(ruleScript, Map.class);
										map.put("currNode", "02");
										map.put("secondNode", "1");
										map.put("shareWay", "3");
										map.put("strType", "3");
										map.put("userCode", "system");
										map.put("ydjFlag", "2");
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
											saveAutoNumber(ruleType);
										}else{
											autoNum2=queryAutoNumber(ruleType, "2");
										}
										if(autoNum2>=autoNum&&autoNum!=0){
											// 组到组员自动还是手动
											String switchCode2 = "3";
											AllotSwitch allotSwitch2 = allotSwitchService.queryAllotSwitchByCode(switchCode2);
											String switchStatus2 = allotSwitch2.getSwitchStatus();
											if ("1".equals(switchStatus2)&&codeList.size()>0&&"2".equals(levelType)) {// 组自动
												List<String> currStatusList=new ArrayList<String>();
												currStatusList.add("1");
												for (String currGroup : codeList) {
													map.put("currGroup", currGroup);
													map.put("currStatusList", currStatusList);
													fastGToU(map);
													log.info("AllotAction.bzkCreditMain 标准卡征信环节自动模式完成");
												}
											} 
										}else if(autoNum2<autoNum||autoNum==0){
											allMap = allotApplyService.saveApplyByConditions(map);
											allResult=(Integer) allMap.get("allResult");
											List<AllotApply> list = queryApplyList(map, allResult,"2");
											log.info("AllotAction.bzkCreditMain 满足该标准卡征信环节自动分件解析规则的申请件数量" + list.size());
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
													AllotApply allotApply = new AllotApply();
													allotApply = list.get(pingNum - 1);
													String appId = allotApply.getAppId();
													log.info("AllotAction.bzkCreditMain 满足该标准卡征信环节自动分件解析规则的申请件流水号==" + appId);
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
													updateAutoNumber(ruleType, junNum);
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
												updateAutoNumber(ruleType, 1);
												lifeList.clear();
												allotApplyList.clear();
												log.info(allotApply.getAppId()+"标准卡征信自动分件--分件成功!");
												list.remove(0);
												yuNum--;
											}
											// 组到组员自动还是手动
											String switchCode2 = "3";
											AllotSwitch allotSwitch2 = allotSwitchService.queryAllotSwitchByCode(switchCode2);
											String switchStatus2 = allotSwitch2.getSwitchStatus();
											if ("1".equals(switchStatus2)&&codeList.size()>0&&"2".equals(levelType)) {// 组自动
												List<String> currStatusList=new ArrayList<String>();
												currStatusList.add("1");
												for (String currGroup : codeList) { 
													map.put("currGroup", currGroup);
													map.put("currStatusList", currStatusList);
													fastGToU(map);
													log.info("AllotAction.bzkCreditMain 标准卡征信环节自动模式完成");
												}
											} 
										}
									} else {
										log.info("标准卡征信环节没有配置规则或没有将规则映射到队列或组");
									}
								}
							}
						}
					} else {
						log.info("标准卡征信环节分件时间不在设置时间内");
					}
				}
			} else {
				log.info("标准卡分件模式总开关为手动模式，需要人工分配操作");
			}
		} catch (Exception e) {
			log.error("AllotAction.bzkCreditMain occur error", e);
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
					log.info("分件批量批次更新报错", e.getMessage());
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
					log.info("转移或回收查询报错", e.getMessage());
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
}
