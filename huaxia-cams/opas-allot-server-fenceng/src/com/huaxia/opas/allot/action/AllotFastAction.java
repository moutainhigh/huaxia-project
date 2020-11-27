package com.huaxia.opas.allot.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.huaxia.opas.common.util.UUIDGenerator;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotMappingRule;
import com.huaxia.opas.domain.allot.AllotRule;
import com.huaxia.opas.domain.allot.AllotSwitch;
import com.huaxia.opas.domain.allot.AllotTime;
import com.huaxia.opas.service.allot.AllotApplyHisService;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.allot.AllotMappingRuleService;
import com.huaxia.opas.service.allot.AllotQueueService;
import com.huaxia.opas.service.allot.AllotRuleService;
import com.huaxia.opas.service.allot.AllotSwitchService;

import net.sf.json.JSONObject;

/**
 * 特殊件全自动分配(对应规则为实施操作)
 * @author wangdebin
 *
 */
public class AllotFastAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(AllotFastAction.class);
	
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

	private static String OPERATOR = "and";
	
	/**
	* @Description: 易达金单独  到人 
	* @author  wdb
	* @version  V1.0
	* @param context  
	 */
	public void ydjFastCredit() {
		log.info("AllotFastAction.ydjFastCredit Enter  "+ new Date(System.currentTimeMillis()));
		try {
			//规则映射表  根据快速规则分件
			String shareWay="creditYdjAutoFast";
			String userCode="system";
			Map<String,Object> dataMap=new HashMap<String,Object>();
			dataMap.put("currNode", "02");
			dataMap.put("secondNode", "1");
			dataMap.put("strType","1");
			dataMap.put("shareWay", shareWay);
			dataMap.put("userCode",userCode);
			dataMap.put("ydjFlag","1");
			dataMap.put("orgLevel","5");
			//平均分件(85件)  
			Map<String,Object> allMap=new HashMap<String,Object>();
			allMap=allotApplyService.saveReviewPoolByCondition(dataMap);
			List<AllotApply> fastList =(List<AllotApply>) allMap.get("list2");
			log.info("AllotFastAction.ydjFastCredit 易达金快速件征信环节申请件集合-"+fastList.toString());
			//临时直接到人  202208        商品易达金组
			List<AllotApply> appList=new ArrayList<AllotApply>();
			//有85申请件
			String currUser="202008";
			String lastUser="202008";
			String currGroup="SPYDJ";
			String lastGroup="商品易达金";
			String currStatus="3";
			String appId="";
			String taokaId="";
			if(fastList.size()>0){
				Date  nowDate =new Date();
				for(int i=0;i<fastList.size();i++){
					AllotApply app=new AllotApply();
					AllotApply app2=new AllotApply();
					app=fastList.get(i);
					appId=app.getAppNo()+"2";
					taokaId=app.getAppNo()+"1";
					app.setAppId(appId);
					app2.setAppId(taokaId);
					app.setCurrStatus(currStatus);
					app.setCurrGroup(currGroup);
					app.setLastGroup(lastGroup);
					app.setCurrUser(currUser);
					app.setLastUser(lastUser);
					app.setGroupDate(nowDate);
					app.setUserDate(nowDate);
					app2.setCurrStatus(currStatus);
					app2.setCurrGroup(currGroup);
					app2.setLastGroup(lastGroup);
					app2.setCurrUser(currUser);
					app2.setLastUser(lastUser);
					app2.setGroupDate(nowDate);
					app2.setUserDate(nowDate);
					appList.add(app);
					appList.add(app2);
				}	
				//批量分件
				allotApplyService.updateBatchApply(appList);
			}
			
		} catch (Exception e) {
			log.error("AllotFastAction.ydjFastCredit occur error "+e+"  "+new Date(System.currentTimeMillis()));
		}
		log.info("AllotFastAction.ydjFastCredit Out  "+new Date(System.currentTimeMillis()));
	}
	public void ydjFastApproval() {
		log.info("AllotFastAction.ydjFastApproval Enter  "+ new Date(System.currentTimeMillis()));
		try {
			//规则映射表  根据快速规则分件
			String shareWay="approvalYdjAutoFast";
			String userCode="system";
			Map<String,Object> dataMap=new HashMap<String,Object>();
			dataMap.put("currNode", "03");
			dataMap.put("secondNode", "1");
			dataMap.put("strType","1");
			dataMap.put("shareWay", shareWay);
			dataMap.put("userCode",userCode);
			dataMap.put("ydjFlag","1");
			dataMap.put("orgLevel","5");
			//平均分件(85件)   
			Map<String,Object> allMap=new HashMap<String,Object>();
			allMap=allotApplyService.saveReviewPoolByCondition(dataMap);
			List<AllotApply> fastList =(List<AllotApply>) allMap.get("list2");
			log.info("AllotFastAction.ydjFastApproval 易达金快速件审批环节申请件集合-"+fastList.toString());
			//临时直接到人  202208        商品易达金组
			List<AllotApply> appList=new ArrayList<AllotApply>();
			//有85申请件
			//有85申请件
			String currUser="202008";
			String lastUser="202008";
			String currGroup="SPYDJ";
			String lastGroup="商品易达金";
			String currStatus="3";
			String appId="";
			String taokaId="";
			if(fastList.size()>0){
				Date  nowDate =new Date();
				for(int i=0;i<fastList.size();i++){
					AllotApply app=new AllotApply();
					AllotApply app2=new AllotApply();
					app=fastList.get(i);
					appId=app.getAppNo()+"2";
					taokaId=app.getAppNo()+"1";
					app.setAppId(appId);
					app2.setAppId(taokaId);
					app.setCurrStatus(currStatus);
					app.setCurrGroup(currGroup);
					app.setLastGroup(lastGroup);
					app.setCurrUser(currUser);
					app.setLastUser(lastUser);
					app.setGroupDate(nowDate);
					app.setUserDate(nowDate);
					app2.setCurrStatus(currStatus);
					app2.setCurrGroup(currGroup);
					app2.setLastGroup(lastGroup);
					app2.setCurrUser(currUser);
					app2.setLastUser(lastUser);
					app2.setGroupDate(nowDate);
					app2.setUserDate(nowDate);
					appList.add(app);
					appList.add(app2);
				}	
				//批量分件
				allotApplyService.updateBatchApply(appList);
			}
			
		} catch (Exception e) {
			log.error("AllotFastAction.ydjFastApproval occur error "+e+"  "+new Date(System.currentTimeMillis()));
		}
		log.info("AllotFastAction.ydjFastApproval Out  "+new Date(System.currentTimeMillis()));
	}
	
	public void ydjFastCreditMain() {
		log.info("AllotFastAction.ydjFastCreditMain Enter  "+ new Date(System.currentTimeMillis()));
		try {
			//规则映射表  根据快速规则分件
			String ruleType="1";
			int timeCount=0;
			Map<String,Object> ruleMap=new HashMap<String,Object>();
			List<String> ruleTypeList=new ArrayList<String>();
			ruleTypeList.add(ruleType);
			ruleMap.put("ruleTypeList", ruleTypeList);
			//查询征信普通件分件时间是否到了
			DateFormat sdf= new SimpleDateFormat("HH:mm");
			Date startTime=null;
			Date endTime=null;
			Date now=null;
			now=sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
			timeCount=allotMappingRuleService.countAllotTime(ruleMap);
			if(timeCount==0){//未设置自动分件时间  默认
				startTime=sdf.parse("00:00");
				endTime=sdf.parse("23:59");
			}else if(timeCount==1){
				AllotTime allotTime=allotMappingRuleService.queryAllotTime(ruleMap);
				if(allotTime!=null){
					startTime=sdf.parse(allotTime.getStartTime());
					endTime=sdf.parse(allotTime.getEndTime());
				}
			}
			//同时满足在开始和结束时间段内参与分件
			if(now.getTime()>=startTime.getTime()&&now.getTime()<=endTime.getTime()){
				List<String> ruleCodeList=allotMappingRuleService.queryRuleCode(ruleType);
				ObjectMapper mapper = new ObjectMapper();
				int groupNum=0;
				int applyNum=0;
				int pingNum=0;
				if(ruleCodeList.isEmpty()!=true){
					for (String ruleCode : ruleCodeList) {
						//查询ruleCode映射了几个组 组装数据
						ruleMap.put("ruleCode", ruleCode);
						List<AllotMappingRule> ruleList=allotMappingRuleService.queryAllMappingRule(ruleMap);
						List<String> codeList=new ArrayList<String>();
						AllotMappingRule amr=new AllotMappingRule();
						//将该规则映射的组放到集合中
						if(ruleList.size()>0){
							groupNum=ruleList.size();
							for(int i=0;i<ruleList.size();i++){
								amr=ruleList.get(i);
								if(amr!=null){
									if(amr.getLevelCode()!=null){
										codeList.add(amr.getLevelCode());
									}
								}
							}
						}
						String ruleScript="";
						Map<String,Object> map=new HashMap<String,Object>();
						if(amr!=null&&ruleList.size()>0){
							ruleScript=amr.getRuleScript();
							JSONObject  jasonObject = JSONObject.fromObject(ruleScript);
							map=mapper.readValue(ruleScript, Map.class);
							map.put("currNode", "02");
							map.put("secondNode", "1");
							map.put("strType","1");
							map.put("shareWay", "creditYdjAutoFast");
							map.put("userCode","system");
							map.put("ydjFlag","1");
							map.put("orgLevel","5");
							log.info("AllotAction.ydjCreditMain 易达金征信环节自动分件解析规则集合"+map);
							Map<String,Object> allMap=new HashMap<String,Object>();
							//查询符合该规则的申请件总数  
							allMap=allotApplyService.saveReviewPoolByCondition(map);
							List<AllotApply> list =(List<AllotApply>) allMap.get("list2");
							applyNum=list.size();
							log.info("AllotAction.ydjCreditMain 满足易达金征信环节自动分件解析规则申请件集合"+list);
							//向下取整
							int x=applyNum/groupNum;
							int pinNum=(int)Math.floor(x);
							log.info("AllotFastAction.ydjFastCreditMain 平均每组分到申请件个数-"+pinNum);
							int b=pinNum*(groupNum);
							log.info("AllotFastAction.ydjFastCreditMain 平均分件总件数-"+b);
							//取余(多余的件)
							int yuNum=applyNum%groupNum;
							log.info("AllotFastAction.ydjFastCreditMain 平均分件后剩余个数-"+yuNum);
							 String levelCode="";
							//申请件详细信息集合
							List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
							//申请件编号集合  
							List<String> lifeList=new ArrayList<String>();
							for(int i=0;i<groupNum;i++){//循环平均分配
								levelCode=codeList.get(i);
								//查询组中文名
								String currName="";
								AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(levelCode);
								if(allotCommon!=null){
									currName=allotCommon.getOrgName();
								}
								int allotNum=pinNum;
								while(allotNum>0&&allotNum<=applyNum){
									AllotApply allotApp=new AllotApply();
									allotApp=list.get(allotNum-1);
									String appNo=allotApp.getAppNo();
									Map<String, Object> lifeMap = new HashMap<String, Object>();
									lifeMap.put("appId", appNo);
									lifeMap.put("currNode", "02");
									lifeMap.put("currStatus", "0");
									lifeMap.put("ydjFlag", "1");
									int abc = allotApplyService.queryHandCount(lifeMap);
									//套卡区分
									if(abc==1){
										AllotApply allotApply2=allotApplyService.queryHandByAppId(lifeMap);
										Date sqlDate=new Date();
										allotApply2.setLstTeamDate(sqlDate);
										String appId=allotApply2.getAppId();
										lifeList.add(appId);
										allotApply2.setCurrGroup(levelCode);
										allotApply2.setLastGroup(currName);
										allotApply2.setGroupDate(sqlDate);
										allotApply2.setCurrStatus("1");
										allotApplyList.add(allotApply2);
									}else if(abc==2){//套卡(算一件)
										List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
										for(AllotApply app:appList){
											Date sqlDate=new Date();
											app.setLstTeamDate(sqlDate);
											String appId=app.getAppId();
											lifeList.add(appId);
											app.setCurrGroup(levelCode);
											app.setLastGroup(currName);
											app.setGroupDate(sqlDate);
											app.setCurrStatus("1");
											allotApplyList.add(app);
										}
									}
									list.remove(allotNum-1);
									allotNum--;
									applyNum--;
								}
								//开始批量分件
								if(allotApplyList.size()>0){
									//自动批量记录历史
									long nowDate=System.currentTimeMillis() ;
									allotApplyHisService.saveHisBatch(lifeList);
									log.info("AllotAction.ydjCreditMain 易达金征信自动批量记录历史成功");
									long nowDate2=System.currentTimeMillis() ;
									log.info("易达金征信自动分件自动分件--批量记录历史所用时间[" + (nowDate2-nowDate) + "] ms!");
									//传实体自动批量分件
									AllotApply appBatch=new AllotApply();
									appBatch=allotApplyList.get(0);
									appBatch.setIds(lifeList);
									allotApplyService.updateBatchList(appBatch);
									long nowDate3=System.currentTimeMillis() ;
									log.info("易达金征信自动分件--传实体批量分件所用时间[" + (nowDate3-nowDate2) + "] ms!--本次分件申请件数量"+pingNum+"个.");
								}
								//集合清空
								allotApplyList.clear();
								//组分件
								map.put("currGroup", levelCode);
								List<String> currStatusList=new ArrayList<String>();
								currStatusList.add("1");
								map.put("currStatusList", currStatusList);
								//组内循环平均分配
								fastGToU(map);
								lifeList.clear();
							}
						}else{
							log.info("该规则已不存在");
						}
					}
				}else{
					log.info("易达金征信环节快速件没有配置规则或没有将规则映射组");
				}
			}
		} catch (Exception e) {
			log.error("AllotFastAction.ydjFastCreditMain occur error "+e+"  "+new Date(System.currentTimeMillis()));
		}
		log.info("AllotFastAction.ydjFastCreditMain Out  "+new Date(System.currentTimeMillis()));
	}
	

	
	/**
	* @Description: 易达金特殊件审批main分配主方法
	* @author uatqf990106
	* @version  V1.0
	* @param context  
	 */
	public void ydjFastApprovalMain() {
		log.info("AllotFastAction.ydjFastApprovalMain Enter  "+ new Date(System.currentTimeMillis()));
		try {
			//规则映射表  根据快速规则分件
			String ruleType="1";
			String shareWay="approvalYdjAutoFast";
			String delStatus="0";
			String userCode="system";
			//List<AllotMappingRule> allotRuleList=allotMappingRuleService.queryRule(ruleType);
			//ObjectMapper mapper = new ObjectMapper();
			AllotCommon allotCommon=new AllotCommon();
			AllotApply allotApply=new AllotApply();
				Map<String,Object> dataMap=new HashMap<String,Object>();
				dataMap.put("currNode", "03");
				dataMap.put("secondNode", "1");
				dataMap.put("strType","1");
				dataMap.put("shareWay", shareWay);
				dataMap.put("userCode",userCode);
				dataMap.put("ydjFlag","1");
				dataMap.put("orgLevel","5");
				//平均分件
				Map<String,Object> allMap=new HashMap<String,Object>();
				allMap=allotApplyService.saveReviewPoolByCondition(dataMap);
				List<AllotApply> fastList =(List<AllotApply>) allMap.get("list2");
				log.info("AllotFastAction.ydjFastApprovalMain 易达金快速件审批环节申请件集合-"+fastList.toString());
				int fastNum=fastList.size();
				List<AllotCommon> groupList=new ArrayList<AllotCommon>();
				groupList=allotCommonService.queryAllotGroup(dataMap);
				log.info("AllotFastAction.ydjFastApprovalMain 易达金审批环节组集合-"+groupList.toString());
				int groupNum=groupList.size();
				if (fastList != null && fastNum > 0 && groupList != null && groupNum > 0) {
					if(groupNum>=fastNum){//组大于等于申请件数量  随机分配
						Random random = new Random();
						while(fastNum>0){
							int index =random.nextInt((int)groupList.size());
							allotCommon=groupList.get(index);
							allotApply=fastList.get(0);
							Map<String, Object> lifeMap = new HashMap<String, Object>();
							String appNo=allotApply.getAppNo();
							lifeMap.put("appId", appNo);
							lifeMap.put("currNode", "03");
							lifeMap.put("currStatus", "0");
							lifeMap.put("ydjFlag", "1");
							String currGroup="";
							String currName="";
							if(allotCommon!=null){
								currGroup=allotCommon.getOrgNo();
								currName=allotCommon.getOrgName();
							}
							int abc = allotApplyService.queryHandCount(lifeMap);
							int result=0;
							//套卡区分
							if(abc==1){//该环节只有一件(标准卡1件放行   易达金除了捞件情况 其他单件不分  )
								AllotApply allotApply2=allotApplyService.queryHandByAppId(lifeMap);
								Date sqlDate=new Date();
								allotApply2.setLstTeamDate(sqlDate);
								String appId=allotApply2.getAppId();
								//lifeList.add(appId);
								//逆向标识 1和2 捞件两种情况  只有一件时候 可以分下去
								String backFlag=allotApply2.getBackFlag();
								//易达金标识
								String  yf=allotApply2.getYdjFlag();
								//套卡标识
								String matchingCardFlag=allotApply2.getMatchingCardFlag();
								//捞件情况01  02只捞回一件 仍可以分件
								String laoJianFlag=allotApply2.getLaoJianFlag();
								if("0".equals(matchingCardFlag)||("2".equals(yf))||("1".equals(yf)&&("1".equals(matchingCardFlag)||"2".equals(matchingCardFlag))
										&&("01".equals(laoJianFlag)||"02".equals(laoJianFlag)))){//非易达金套卡 或者 捞件
									allotApply2.setCurrGroup(currGroup);
									allotApply2.setLastGroup(currName);
									//分组日期 按征信来 不用变
									allotApply2.setCurrStatus("1");
									allotApply2.setDelStatus(delStatus);
									//历史记录
									saveAllotHis(allotApply2);
									result=allotApplyService.updateAllotApply(allotApply2);
								}
							}else if(abc==2){//套卡(算一件)
								List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
								for(AllotApply app:appList){
									Date sqlDate=new Date();
									app.setLstTeamDate(sqlDate);
									String appId=app.getAppId();
									//lifeList.add(appId);
									String matchingCardFlag=app.getMatchingCardFlag();
									String yf=app.getYdjFlag();
									app.setCurrGroup(currGroup);
									app.setLastGroup(currName);
									//分组日期 按征信来 不用变
									app.setCurrStatus("1");
									app.setDelStatus(delStatus);
									//历史记录
									saveAllotHis(app);
									result=allotApplyService.updateAllotApply(app);
								}
							}
							if(result!=0){
								fastList.remove(0);
								fastNum--;
							}
							//组内循环平均分配
							dataMap.put("currGroup", currGroup);
							List<String> currStatusList=new ArrayList<String>();
							currStatusList.add("1");
							dataMap.put("currStatusList", currStatusList);
							//组内循环平均分配
							fastGToU(dataMap);
						}
					}else{//申请件数量多于组数量
						//向下取整
						int x=fastNum/groupNum;
						int pinNum=(int)Math.floor(x);
						log.info("AllotFastAction.ydjFastApprovalMain 平均每组分到申请件个数-"+pinNum);
						int b=pinNum*(groupNum);
						log.info("AllotFastAction.ydjFastApprovalMain 平均分件总件数-"+b);
						//取余(多余的件)
						int yuNum=fastNum%groupNum;
						log.info("AllotFastAction.ydjFastApprovalMain 平均分件后剩余个数-"+yuNum);
						for(int i=0;i<groupNum;i++){//循环平均分配
							allotCommon=groupList.get(i);
							String currGroup="";
							String currName="";
							if(allotCommon!=null){
								currGroup=allotCommon.getOrgNo();
								currName=allotCommon.getOrgName();
							}
							int allotNum=pinNum;
							while(allotNum>0&&allotNum<=fastNum){
								int bin=0;
								AllotApply allotApp=new AllotApply();
								allotApp=fastList.get(pinNum-1);
								String appNo=allotApp.getAppNo();
								Map<String, Object> lifeMap = new HashMap<String, Object>();
								lifeMap.put("appId", appNo);
								lifeMap.put("currNode", "03");
								lifeMap.put("currStatus", "0");
								lifeMap.put("ydjFlag", "1");
								int abc = allotApplyService.queryHandCount(lifeMap);
								int result=0;
								//套卡区分
								if(abc==1){//该环节只有一件(标准卡1件放行   易达金除了捞件情况 其他单件不分  )
									AllotApply allotApply2=allotApplyService.queryHandByAppId(lifeMap);
									Date sqlDate=new Date();
									allotApply2.setLstTeamDate(sqlDate);
									String appId=allotApply2.getAppId();
									//lifeList.add(appId);
									//逆向标识 1和2 捞件两种情况  只有一件时候 可以分下去
									String backFlag=allotApply2.getBackFlag();
									//易达金标识
									String  yf=allotApply2.getYdjFlag();
									//套卡标识
									String matchingCardFlag=allotApply2.getMatchingCardFlag();
									//捞件情况01  02只捞回一件 仍可以分件
									String laoJianFlag=allotApply2.getLaoJianFlag();
									if("0".equals(matchingCardFlag)||("2".equals(yf))||("1".equals(yf)&&("1".equals(matchingCardFlag)||"2".equals(matchingCardFlag))
											&&("01".equals(laoJianFlag)||"02".equals(laoJianFlag)))){//非易达金套卡 或者 捞件
										allotApply2.setCurrGroup(currGroup);
										allotApply2.setLastGroup(currName);
										//分组日期 按征信来 不用变
										allotApply2.setCurrStatus("1");
										allotApply2.setDelStatus(delStatus);
										//历史记录
										saveAllotHis(allotApply2);
										result=allotApplyService.updateAllotApply(allotApply2);
									}
								}else if(abc==2){//套卡(算一件)
									List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
									for(AllotApply app:appList){
										Date sqlDate=new Date();
										app.setLstTeamDate(sqlDate);
										String appId=app.getAppId();
										//lifeList.add(appId);
										String matchingCardFlag=app.getMatchingCardFlag();
										String yf=app.getYdjFlag();
										app.setCurrGroup(currGroup);
										app.setLastGroup(currName);
										//分组日期 按征信来 不用变
										app.setCurrStatus("1");
										app.setDelStatus(delStatus);
										//历史记录
										saveAllotHis(app);
										result=allotApplyService.updateAllotApply(app);
									}
								}
								if(result!=0){
									fastList.remove(pinNum-1);
									fastNum--;
									allotNum--;
								}
							}
							dataMap.put("currGroup", currGroup);
							List<String> currStatusList=new ArrayList<String>();
							currStatusList.add("1");
							dataMap.put("currStatusList", currStatusList);
							//组内循环平均分配
							fastGToU(dataMap);
						}
						
					}
				}
		} catch (Exception e) {
			log.error("AllotFastAction.ydjFastApprovalMain occur error "+e+"  "+new Date(System.currentTimeMillis()));
		}
		log.info("AllotFastAction.ydjFastApprovalMain Out  "+new Date(System.currentTimeMillis()));
	}
	
	/**
	 * 从申请表查出符合规则的编号
	 * @param comShareQueue
	 * @return
	 * @throws CoreException
	 */
	public static  StringBuffer findAppId(AllotRule allotRule){
		log.info("AllotFastAction.findAppId Enter"+System.currentTimeMillis());
		String rulesInfo = StringUtils.isNotBlank(allotRule.getRuleScript()) ? allotRule.getRuleScript() : "";
		// 解析规则字符串
		String[] rules = rulesInfo.split("\\;");
		StringBuffer conditions = new StringBuffer("");
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
				conditions.append(OPERATOR + "  " + rr + "  ");
			}
		}
		log.info("AllotFastAction.findAppId Out"+System.currentTimeMillis());
		return conditions;
	}
	
	/**
	 * 快速分件 组到人平均分件
	 * @param groupCode
	 * @param comShareQueue
	 * @throws CoreException
	 */
	public boolean fastGToU(Map dataMap) {
		log.info("AllotFastAction.fastGToU Enter"+new Date(System.currentTimeMillis()));
		boolean result=false;
		// 组存储表
		String currGroup=(String) dataMap.get("currGroup")==null?"":(String) dataMap.get("currGroup");
		String ydjFlag=(String) dataMap.get("ydjFlag")==null?"":(String) dataMap.get("ydjFlag");
		String currNode=(String) dataMap.get("currNode")==null?"":(String) dataMap.get("currNode");
		List<AllotApply> allotAppList=new ArrayList<AllotApply>();
		AllotCommon allotCommom=new AllotCommon();
			try {
				dataMap.put("submitStr", "submit");
				//易达金非捞件的申请件 只看易达金条件()
				String ydjStr="AND (allot.MATCHING_CARD_FLAG in ('0','2') or allot.LAOJIANFLAG='01' or allot.LAOJIANFLAG='02')";
				dataMap.put("autoAllot","1");
				dataMap.put("ydjStr", ydjStr);
				//dataMap.put("prodStr", "and ac.APP_PROD  in('0084','0085')");
				//申请件前15位集合
				allotAppList =allotApplyService.queryAllotApplyByGroup(dataMap);
				if(allotAppList.size()>0){
					//申请件详细信息集合
					List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
					//申请件编号集合  
					List<String> lifeList=new ArrayList<String>();
					//查询该组信息
					allotCommom=allotCommonService.queryGroupByOrgNo(currGroup);
					//全部组员信息
					List<AllotCommon>  allotUserList=allotCommonService.queryAllotUserByOrgId(dataMap, allotCommom);
					if (allotUserList != null && allotUserList.size() > 0 ) {
						for (int i = 0; i < allotAppList.size(); i++) {
							int j = i % allotUserList.size();
							AllotApply allotApply = allotAppList.get(i);
							AllotCommon allotUser=allotUserList.get(j);
							String currUser=allotUser.getUserCode();
							if(currUser==null&&"".equals(currUser)){
								currUser="system";
							}
							String lastUser=currUser;
							String currName=allotUser.getUserName();
							Map<String, Object> lifeMap = new HashMap<String, Object>();
							String appNo=allotApply.getAppNo();
							if(appNo==null||"".equals(appNo)){
								appNo=allotApply.getAppId().substring(0,15);
							}
							lifeMap.put("appId", appNo);
							lifeMap.put("currNode", currNode);
							//组中未分配
							lifeMap.put("currStatus", "1");
							lifeMap.put("ydjFlag", ydjFlag);
							int abc = allotApplyService.queryHandCount(lifeMap);
							//套卡区分
							if(abc==1){//该环节只有一件(标准卡1件放行   易达金除了捞件情况 其他单件不分  )
								AllotApply allotApply2=allotApplyService.queryHandByAppId(lifeMap);
								String appId=allotApply2.getAppId();
								lifeList.add(appId);
								//逆向标识 1和2 捞件两种情况  只有一件时候 可以分下去
								String backFlag=allotApply2.getBackFlag();
								//易达金标识
								String  yf=allotApply2.getYdjFlag();
								//套卡标识
								String matchingCardFlag=allotApply2.getMatchingCardFlag();
								//捞件情况01  02只捞回一件 仍可以分件
								String laoJianFlag=allotApply2.getLaoJianFlag();
								if("0".equals(matchingCardFlag)||("2".equals(yf))||("1".equals(yf)&&("1".equals(matchingCardFlag)||"2".equals(matchingCardFlag))
										&&("01".equals(laoJianFlag)||"02".equals(laoJianFlag)))){//非易达金套卡 或者 捞件
									Date sqlDate=new Date();
									allotApply2.setLstTeamDate(sqlDate);
									allotApply2.setUserDate(sqlDate);
									allotApply2.setCurrUser(currUser);
									allotApply2.setLastUser(lastUser);
									allotApply2.setCurrUserName(currName);
									allotApply2.setCurrStatus("3");
									allotApplyList.add(allotApply2);
								}
							}else if(abc==2){//套卡(算一件)
								List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
								for(AllotApply app:appList){
									Date now =new Date();
									app.setLstTeamDate(now);
									app.setUserDate(now);
									app.setCurrStatus("3");
									app.setCurrUser(currUser);
									app.setLastUser(lastUser);
									app.setCurrUserName(currName);
									allotApplyList.add(app);
								}
							}
						}
						if(allotApplyList.size()>0){
							//批量记录历史
							long nowDate=System.currentTimeMillis() ;
							allotApplyHisService.saveHisBatch(lifeList);
							log.info("AllotAction.fastGToU  自动分件批量记录历史成功");
							long nowDate2=System.currentTimeMillis() ;
							log.info("自动分件--批量记录组到人历史所用时间[" + (nowDate2-nowDate) + "] ms!");
							//申请件分到人不同  
							allotApplyService.updateBatchApply(allotApplyList);
							long nowDate3=System.currentTimeMillis() ;
							log.info("自动分件--批量分件组到人所用时间[" + (nowDate3-nowDate2) + "] ms!");
						}
					} else {
						log.info("AllotFastAction.fastGToU 易达金快速件组内没有该环节的组员");
					}
				}else{
					log.info("AllotFastAction.fastGToU   易达金快速件 组内没有待分配的件");
					result=true;
				}
			} catch (CoreException e) {
				log.error("AllotFastAction.fastGToU occur 易达金快速件发生异常 - error", e);
			}
			log.info("AllotFastAction.fastGToU Out"+new Date(System.currentTimeMillis()));
		return result;
	}
	/**
	* @Description:历史记录入库
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 * @throws CoreException 
	 */
	public void saveAllotHis(AllotApply allotApply) {
		log.info("AllotFastAction.saveAllotHis Enter");
		AllotApplyAllotHis allotApplyHis=new AllotApplyAllotHis();
		try {
			String autoId=UUIDGenerator.getUUID();
			allotApplyHis.setId(autoId);
			allotApplyHis.setAppId(allotApply.getAppId());
			allotApplyHis.setCurrOptQueue(allotApply.getCurrQueue()==null?"":allotApply.getCurrQueue());
			allotApplyHis.setLastOptQueue(allotApply.getLastQueue()==null?"":allotApply.getLastQueue());
			allotApplyHis.setCurrOptGroup(allotApply.getCurrGroup()==null?"":allotApply.getCurrGroup());
			allotApplyHis.setLastOptGroup(allotApply.getLastGroup()==null?"":allotApply.getLastGroup());
			allotApplyHis.setCurrOptUser(allotApply.getCurrUser()==null?"":allotApply.getCurrUser());
			allotApplyHis.setLastOptUser(allotApply.getLastUser()==null?"":allotApply.getLastUser());
			allotApplyHis.setCurrStatus(allotApply.getCurrStatus()==null?"":allotApply.getCurrStatus());
			allotApplyHis.setDelStatus(allotApply.getDelStatus()==null?"":allotApply.getDelStatus());
			allotApplyHis.setCurrNode(allotApply.getCurrNode()==null?"":allotApply.getCurrNode());
			allotApplyHis.setCrtTeamDate(allotApply.getCrtTeamDate()==null?new Date():allotApply.getCrtTeamDate());
			allotApplyHis.setLstTeamDate(allotApply.getLstTeamDate()==null?new Date():allotApply.getLstTeamDate());
			allotApplyHis.setSubmitStatus(allotApply.getSubmitStatus()==null?"":allotApply.getSubmitStatus());
			allotApplyHis.setSubmitType(allotApply.getSubmitType()==null?"":allotApply.getSubmitType());
			allotApplyHis.setSubmitMemo(allotApply.getSubmitMemo()==null?"":allotApply.getSubmitMemo());
			allotApplyHis.setReviewStatus(allotApply.getReviewStatus()==null?"":allotApply.getReviewStatus());
			allotApplyHis.setQueueDate(allotApply.getQueueDate());
			allotApplyHis.setGroupDate(allotApply.getGroupDate());
			allotApplyHis.setUserDate(allotApply.getUserDate());
			Date date=new Date();
			allotApplyHis.setRecordTeamDate(date);
			allotApplyHis.setYdjFlag(allotApply.getYdjFlag());
			allotApplyHis.setMatchingCardFlag(allotApply.getMatchingCardFlag());
			allotApplyHis.setBackFlag(allotApply.getBackFlag()==null?"":allotApply.getBackFlag());
			allotApplyHis.setSynFlag(allotApply.getSynFlag());
			allotApplyHis.setProcessId(allotApply.getProcessId());
			allotApplyHis.setLaojianflag(allotApply.getLaoJianFlag()==null?"":allotApply.getLaoJianFlag());
			int result=0;
			if(allotApplyHis!=null){
				result=allotApplyHisService.saveAllotApplyHis(allotApplyHis);
			}
			log.info("AllotFastAction.saveAllotHis 记录历史结果result="+result);
			if(result==0){
				log.info("AllotFastAction.saveAllotHis 记录历史失败");
			}else{
				log.info("AllotFastAction.saveAllotHis 记录历史成功");
			}
		}catch (Exception e) {
			log.error("AllotFastAction.saveAllotHis occur error", e);
		}
		log.info("AllotFastAction.saveAllotHis Out");
	} 
}
