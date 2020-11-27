package com.huaxia.opas.service.allot.impl;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.dao.AbstractDAO;
import com.huaxia.opas.dao.allot.AllotApplyAllotHisDao;
import com.huaxia.opas.dao.allot.AllotApplyDao;
import com.huaxia.opas.dao.allot.AllotBzkResultDao;
import com.huaxia.opas.dao.allot.AllotCommonDao;
import com.huaxia.opas.dao.allot.AllotYdjResultDao;
import com.huaxia.opas.dao.apply.ApplyLifeCicleDao;
import com.huaxia.opas.dao.decision.BizApprovalDao;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.domain.decision.OpaBzkSysResultInfo;
import com.huaxia.opas.domain.decision.YdjSysresultInfo;
import com.huaxia.opas.domain.thirdparty.BizApproval;
import com.huaxia.opas.service.allot.WorkFlowService;
import com.huaxia.opas.service.rule.InvokeRuleService;
import com.huaxia.opas.util.PropertiesUtil;
import com.huaxia.opas.util.StringUtil;
import com.huaxia.opas.util.UUIDGenerator;
import com.ibm.icu.text.SimpleDateFormat;
/**
 * 分件与工作流交互 
 *
 * @author wangdebin
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-16 修复审批退回流程生命周期不全问题
 *  2017-10-23 修复征信环节最后操作员错误问题
 *  2017-10-23 修复关键信息修改申请件从新到人 最后操作员错误问题
 *  2017-10-31 修改退回件再提交到审批人员件丢失的问题
 *  2017-11-01 征信策略结果和结果描述修改
 *  2017-11-13 关键信息修改最后操作员及重新回到征信问题
 *  2017-12-07 捞件重新回到原审批组问题
 *  2018-01-03 审批表重置INIT_SAVE_FLAG为0
 *  2019-04-17 工作流捞件标识ruleOpt修改标准卡14-108,15-109易达金 14-106  15-107
 * ------------------------------------------------
 */
public class WorkFlowServiceImpl extends AbstractDAO implements WorkFlowService, Serializable {

	private static final long serialVersionUID = -4697896429783479594L;

	private static final Logger logger = LoggerFactory.getLogger(WorkFlowServiceImpl.class);

	@Resource(name = "allotApplyDao")
	private AllotApplyDao allotApplyDao;
	
	@Resource(name = "allotApplyAllotHisDao")
	private AllotApplyAllotHisDao allotApplyHisDao;
	
	@Resource(name = "allotCommonDao")
	private AllotCommonDao allotCommonDao;

	@Resource(name = "allotBzkResultDao")
	private AllotBzkResultDao allotBzkResultDao;

	@Resource(name = "allotYdjResultDao")
	private AllotYdjResultDao allotYdjResultDao;

	@Resource(name = "invokeRuleService")
	private InvokeRuleService invokeRuleService;
	
	@Resource(name = "applyLifeCicleDao")
	private ApplyLifeCicleDao applyLifeCicleDao;
	
	@Resource(name = "bizApprovalDao")
	private BizApprovalDao bizApprovalDao;
	
	public ApplyLifeCicleDao getApplyLifeCicleDao() {
		return applyLifeCicleDao;
	}

	public void setApplyLifeCicleDao(ApplyLifeCicleDao applyLifeCicleDao) {
		this.applyLifeCicleDao = applyLifeCicleDao;
	}

	public AllotApplyDao getAllotApplyDao() {
		return allotApplyDao;
	}

	public void setAllotApplyDao(AllotApplyDao allotApplyDao) {
		this.allotApplyDao = allotApplyDao;
	}

	public AllotBzkResultDao getAllotBzkResultDao() {
		return allotBzkResultDao;
	}

	public void setAllotBzkResultDao(AllotBzkResultDao allotBzkResultDao) {
		this.allotBzkResultDao = allotBzkResultDao;
	}

	public AllotYdjResultDao getAllotYdjResultDao() {
		return allotYdjResultDao;
	}

	public void setAllotYdjResultDao(AllotYdjResultDao allotYdjResultDao) {
		this.allotYdjResultDao = allotYdjResultDao;
	}

	public AllotCommonDao getAllotCommonDao() {
		return allotCommonDao;
	}
	
	public void setAllotCommonDao(AllotCommonDao allotCommonDao) {
		this.allotCommonDao = allotCommonDao;
	}
	
	
	public AllotApplyAllotHisDao getAllotApplyHisDao() {
		return allotApplyHisDao;
	}

	public void setAllotApplyHisDao(AllotApplyAllotHisDao allotApplyHisDao) {
		this.allotApplyHisDao = allotApplyHisDao;
	}
	@Override
	public  void saveAllotApply(Map<String,String> map) throws Exception {
		logger.info("WorkFlowService.saveAllotApply 工作流调用人工节点 ---Enter--");
		int result=0,num=0;
		//流水号
		String appId=map.get("APP_ID")!=null?map.get("APP_ID"):"";
		logger.info("WorkFlowService.saveAllotApply --流水号--"+appId);
		//节点标识
		String currNode=map.get("NODE_ID")!=null?map.get("NODE_ID"):"";
		logger.info("WorkFlowService.saveAllotApply --节点标识--"+currNode);
		//调用策略标识
		String celveFlag=map.get("CELVE_FLAG")!=null?map.get("CELVE_FLAG"):"";
		logger.info("WorkFlowService.saveAllotApply --策略标识--"+celveFlag);
		//易达金标识
		String ydjFlag=map.get("YDJ_FLAG")!=null?map.get("YDJ_FLAG"):"";
		logger.info("WorkFlowService.saveAllotApply --易达金标识--"+ydjFlag);
		//套卡标识
		String matchingCardFlag=map.get("TAO_FLAG")!=null?map.get("TAO_FLAG"):"";
		logger.info("WorkFlowService.saveAllotApply --套卡标识--"+matchingCardFlag);
		//工作流件编号
		String processId=map.get("PROCESS_ID")!=null?map.get("PROCESS_ID"):"";
		logger.info("WorkFlowService.saveAllotApply --工作流件编号--"+processId);
		//LAOJIANFLAG  捞件标识
		String laoJianFlag=map.get("LAOJIANFLAG")!=null?map.get("LAOJIANFLAG"):"";
		logger.info("WorkFlowService.saveAllotApply --捞件标识--"+laoJianFlag);
		//keyMessageFlag 修改关键信息标识
		String keyMessageFlag=map.get("keyMessageFlag")!=null?map.get("keyMessageFlag"):"";
		logger.info("WorkFlowService.saveAllotApply --修改关键信息标识--"+keyMessageFlag);
		String ruleOpt=map.get("ruleOpt")==null?"":map.get("ruleOpt");
		logger.info("WorkFlowService.saveAllotApply --ruleOpt标识--"+ruleOpt);
		//isBack 判断标识
		String isBack=map.get("isBack")!=null?map.get("isBack"):"";
		logger.info("WorkFlowService.saveAllotApply --审批判断标识--"+isBack);
		//普通审批退回征信特用
		AllotApply tuiApp=null;
		//正常流转
		int count=0;
		count=getAllotApplyDao().selectHandCount(appId);
		AllotApply allotApply=new AllotApply();
		if(count>0){
			List<AllotApply> upList=allotApplyDao.selectApplyListByAppId(appId);
			if(upList!=null&&upList.size()>0){
				allotApply=upList.get(0);
				logger.info(appId+"该申请件在分件表已存在,需要更新操作。。。。。。。");
			}
		}
		allotApply.setAppId(appId);
		allotApply.setLaoJianFlag(laoJianFlag);
		allotApply.setProcessId(processId);
		allotApply.setYdjFlag(ydjFlag);
		allotApply.setMatchingCardFlag(matchingCardFlag);
		//记录生命周期区分标识、操作人、隐藏标志符 记录审批退回征信使用
		String flag="",demoCode="",back_synFlag="";
		Date now =new Date();
		Map<String,Object> appMap=new HashMap<String,Object>();
		appMap.put("appId", appId);
		appMap.put("currNode", currNode);
		//历史记录插入  判断非易达金征信  或者标准卡录入
		if(("2".equals(ydjFlag)&&("02".equals(currNode)||"03".equals(currNode)))
				||("1".equals(ydjFlag)&&"03".equals(currNode))){
			allotApplyHisDao.insertCopyOpasAllotToAllotHis(appMap);
		}
		AllotCommon allotCommon=null;
		AllotApplyAllotHis his=null;
		if("01".equals(currNode)&&"2".equals(ydjFlag)){//标准卡审查
			logger.info("WorkFlowService.saveAllotApply --标准卡审查--appId="+appId);
			//判断申请件是否修改关键信息重新跑的件  是的话非批量件直接到原录入员   批量件到审查池
			if("1".equals(keyMessageFlag)){
				//查询历史记录 取录入环节最新记录赋值
				/**
				 * 20200917 蔡长龙
				 * 生产存在特殊情况
				 * 比如发起关键信息之前直接免录入到征信电核,而发起关键信息修改之后又到了录入环节,
				 * 此时查不到录入环节记录 ,将件分配到录入审查池
				 */
				
				Map allotHisMap=allotApplyHisDao.queryHisByMap(appMap);
				String currUser= "";
				if(null!=allotHisMap){
					//转换
					currUser=allotHisMap.get("currUser")!=null?(String)allotHisMap.get("currUser"):"";
				}
				//关键信息复核人员为最后操作员
				appMap.put("operateDesc", "关键信息修改复核[通过],申请件重新发起[全流程]!");
				appMap.put("operateResult", "完成");
				String lastUser=StringUtils.trimToEmpty(applyLifeCicleDao.queryOperater(appMap));
				if("".equals(currUser)){//审查批量处理件
					flag="20";
					allotApply.setCurrStatus("0");
				}else{//审查员提交件
					flag="21";
					allotApply.setCurrStatus("3");
					//审查员有可能换过组
					allotCommon=allotCommonDao.selectGroupByUserCode(currUser);
					if(allotCommon!=null){
						allotApply.setCurrGroup(allotCommon.getOrgNo()!=null?allotCommon.getOrgNo():"");
						allotApply.setLastGroup(allotCommon.getOrgName()!=null?allotCommon.getOrgName():"");
						allotApply.setCurrUserName(allotCommon.getUserName()!=null?allotCommon.getUserName():"");
					}
					allotApply.setCurrUser(currUser);
					allotApply.setGroupDate(now);
					allotApply.setUserDate(now);
					demoCode=currUser;
				}
				if(!"".equals(lastUser)){
					String[] a =lastUser.split("-");
					allotApply.setLastUser(a[1]);
				}
				allotApply.setCrtTeamDate(now);
				allotApply.setLstTeamDate(now);
				allotApply.setCurrNode("01");
				allotApply.setDelStatus("0");
			}else{
				allotApply.setCurrNode("01");
				allotApply.setCrtTeamDate(now);
				allotApply.setLstTeamDate(now);
				allotApply.setCurrQueue("");
				allotApply.setLastQueue("");
				allotApply.setCurrGroup("");
				allotApply.setLastGroup("");
				allotApply.setCurrUser("");
				allotApply.setCurrUserName("");
				allotApply.setLastUser("");
				allotApply.setQueueDate(null);
				allotApply.setGroupDate(null);
				allotApply.setUserDate(null);
				allotApply.setGroupTeamDate(null);
				allotApply.setCurrStatus("0");
				allotApply.setDelStatus("0");
				allotApply.setSynFlag("0");
				allotApply.setSubmitStatus("");
				allotApply.setSubmitType("");
				allotApply.setSubmitMemo("");
				allotApply.setReviewStatus("");
				flag="1";
			}
			
		}else if("02".equals(currNode)&&"1".equals(celveFlag)){//调征信策略
			logger.info("WorkFlowService.saveAllotApply --调征信策略--appId="+appId);
			//三期版本
			num=saveCreditResult4(appId,ydjFlag,matchingCardFlag);
			result=100;
			flag="2";
		}else if("02".equals(currNode)&&"2".equals(celveFlag)&&"1".equals(ydjFlag)){//易达金征信
			logger.info("WorkFlowService.saveAllotApply --易达金征信环节--appId="+appId);
			//正常流转还是征信从审批收回或审批退回
				if("8".equals(ruleOpt)){//征信从审批 收回
					logger.info("WorkFlowService.saveAllotApply --易达金征信环节--征信从审批 收回--appId="+appId);
					flag="3";
					String specialExamineUser=map.get("specialExamineUser");
					String[] strList=specialExamineUser.split("-");
					if(strList.length>0){
						int id=Integer.parseInt(strList[0].toString());
						if(id==4){
							String currUser=strList[1].trim();
							allotCommon=allotCommonDao.selectGroupByUserCode(currUser);
							if(allotCommon!=null){
								allotApply.setCurrGroup(allotCommon.getOrgNo());
								allotApply.setLastGroup(allotCommon.getOrgName());
								allotApply.setCurrUserName(allotCommon.getUserName());
							}
							allotApply.setCurrUser(currUser);
							allotApply.setLastUser(currUser);
							//分组时间不变,继续累积GROUP_DATE
							allotApply.setUserDate(now);
							allotApply.setGroupTeamDate(now);
							allotApply.setCurrStatus("3");
							allotApply.setDelStatus("0");
							demoCode=currUser;
						}
					}
				}else if("106".equals(ruleOpt)||"107".equals(ruleOpt)||("1".equals(keyMessageFlag)&&!"17".equals(ruleOpt))){//捞件和修改关键信息件
					//取最近环节历史记录
					his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(appMap);
					//关键信息复核人员为最后操作员
					appMap.put("operateDesc", "关键信息修改复核[通过],申请件重新发起[全流程]!");
					appMap.put("operateResult", "完成");
					String lastUser=StringUtils.trimToEmpty(applyLifeCicleDao.queryOperater(appMap));
					if(his!=null){
						String group=his.getCurrOptGroup()!=null?his.getCurrOptGroup():"";
						String groupName=his.getLastOptGroup()!=null?his.getLastOptGroup():"";
						String user=his.getCurrOptUser()!=null?his.getCurrOptUser():"";
						String currUserName=his.getCurrUserName()!=null?his.getCurrUserName():"";
						String status=his.getCurrStatus()!=null?his.getCurrStatus():"";
						allotApply.setCurrGroup(group);
						allotApply.setLastGroup(groupName);
						allotApply.setGroupDate(now);
						allotApply.setGroupTeamDate(now);
						if("106".equals(ruleOpt)||"107".equals(ruleOpt)){//二次决策或审批被拒 捞件到原征信组
							if("106".equals(ruleOpt)){
								logger.info("WorkFlowService.saveAllotApply --易达金征信环节--二次决策 捞件到原征信组--appId="+appId);
								flag="4";
							}else if("107".equals(ruleOpt)){
								logger.info("WorkFlowService.saveAllotApply --易达金征信环节--审批被拒 捞件到原征信组--appId="+appId);
								flag="5";
							}
							//分组时间重新计算
							allotApply.setCurrUser("");
							allotApply.setCurrUserName("");
							allotApply.setLastUser("");
							allotApply.setUserDate(null);
							allotApply.setCurrStatus("1");
							allotApply.setSynFlag("0");
							demoCode=group;
						}else if("1".equals(keyMessageFlag)){// 关键信息修改过的件 到原来征信人或原操作组
							logger.info("WorkFlowService.saveAllotApply --易达金征信环节--关键信息修改过的件 到原来征信人--appId="+appId);
							flag="6";
							allotApply.setCurrUser(user);
							allotApply.setCurrUserName(currUserName);
							if(!"".equals(lastUser)){
								String[] a =lastUser.split("-");
								allotApply.setLastUser(a[1]);
							}
							//征信员有可能换过组
							allotCommon=allotCommonDao.selectGroupByUserCode(user);
							if(allotCommon!=null){
								allotApply.setCurrGroup(allotCommon.getOrgNo()!=null?allotCommon.getOrgNo():"");
								allotApply.setLastGroup(allotCommon.getOrgName()!=null?allotCommon.getOrgName():"");
								allotApply.setCurrUserName(allotCommon.getUserName()!=null?allotCommon.getUserName():"");
							}
							//分组时间不变,继续累积GROUP_DATE
							allotApply.setUserDate(now);
							allotApply.setCurrStatus(status);
							demoCode=user;
							//查询该申请件是否是审批人退回再修改  是将backflag改为4
							Map<String,Object> hisMap=new HashMap<String,Object>();
							hisMap.put("appId", appId);
							hisMap.put("currNode", "03");
							AllotApplyAllotHis shenHis=allotApplyHisDao.queryAllotApplyAllotHisByAppId(hisMap);
							if(shenHis!=null&&("A".equals(his.getBackFlag()))&&"3".equals(shenHis.getCurrStatus())){
								allotApply.setBackFlag("4");
							}
						}
						allotApply.setDelStatus("0");
					}
				}else if("17".equals(ruleOpt)){//审批退回动作(空跑,审批页面已改)
					//查询分件表   判断普通审批退回到原征审合一审批(工作流节点均为02)  还是  普通审批退回普通征信
					Map tuiMap=new HashMap();
					tuiMap.put("appId", appId);
					tuiApp=allotApplyDao.selectSingleByAppId(tuiMap);
					//修改curr_status状态(审批退回征信时修改过)
					if(tuiApp!=null){
						//普通审批退回到原征审合一审批(工作流节点均为02)
						if("03".equals(tuiApp.getCurrNode())&&"2".equals(tuiApp.getCheck_meuoFlag())){
							flag="24";//将隐藏curr_status 9改回3
							tuiApp.setCurrStatus("3");
						}else{//普通审批退回普通征信
							back_synFlag=tuiApp.getSynFlag();
							String tuiStatus=tuiApp.getCurrUser()==null?"":tuiApp.getCurrUser();
							if("".equals(tuiStatus)){
								tuiApp.setCurrStatus("1");
								tuiApp.setBatchCreditFlag("0");
							}else{
								tuiApp.setCurrStatus("3");
							}
							flag="7";
							logger.info("WorkFlowService.saveAllotApply --易达金征信环节--审批退回--appId="+appId);
						}
					}
					result=99;
				}else if("".equals(ruleOpt)&&"11".equals(isBack)){//征审合一审批已完成从待归档收回
					Map tuiMap=new HashMap();
					tuiMap.put("appId", appId);
					tuiApp=allotApplyDao.selectSingleByAppId(tuiMap);
					//修改curr_status状态(征审合一审批已完成修改过)
					if(tuiApp==null){
						tuiApp=new AllotApply();
						tuiApp.setAppId(appId);
					}
					tuiApp.setCurrStatus("3");
					flag="24";//不记录生命周期 但需要更新操作
					result=99;
				}else{//正常流转
					logger.info("WorkFlowService.saveAllotApply --易达金征信环节--正常流转--appId="+appId);
					flag="8";
					allotApply.setCurrStatus("0");
					allotApply.setDelStatus("0");
					allotApply.setSynFlag("0");
				}
				allotApply.setCrtTeamDate(now);
				allotApply.setLstTeamDate(now);
				allotApply.setCurrNode("02");
				allotApply.setSubmitStatus("");
				allotApply.setSubmitType("");
				allotApply.setSubmitMemo("");
				allotApply.setReviewStatus("");
		}else if("02".equals(currNode)&&"0".equals(celveFlag)&&"2".equals(ydjFlag)){//标准卡征信
				logger.info("WorkFlowService.saveAllotApply 标准卡征信--appId="+appId);
				//正常流转还是审批收回
				if("8".equals(ruleOpt)){//征信从审批 收回
					flag="9";
					String specialExamineUser=map.get("specialExamineUser");
					String[] strList=specialExamineUser.split("-");
					if(strList.length>0){
						int id=Integer.parseInt(strList[0].toString());
						if(id==4){
							String currUser=strList[1].trim();
							allotCommon=allotCommonDao.selectGroupByUserCode(currUser);
							//取最近环节历史记录
							his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(appMap);
							if(his!=null){
								if(his.getCurrOptQueue()!=null){
									allotApply.setCurrQueue(his.getCurrOptQueue());
								}
								if(his.getLastOptQueue()!=null){
									allotApply.setLastQueue(his.getLastOptQueue());;
								}
							}
							if(allotCommon!=null){
								allotApply.setCurrGroup(allotCommon.getOrgNo()!=null?allotCommon.getOrgNo():"");
								allotApply.setLastGroup(allotCommon.getOrgName()!=null?allotCommon.getOrgName():"");
								allotApply.setCurrUserName(allotCommon.getUserName()!=null?allotCommon.getUserName():"");
							}
							allotApply.setCurrUser(currUser);
							allotApply.setLastUser(currUser);
							allotApply.setQueueDate(now);
							//分组时间不变,继续累积GROUP_DATE
							allotApply.setGroupTeamDate(now);
							allotApply.setUserDate(now);
							allotApply.setCurrStatus("3");
							allotApply.setDelStatus("0");
							demoCode=currUser;
						}
					}
				}else if("108".equals(ruleOpt)||"109".equals(ruleOpt)||("1".equals(keyMessageFlag)&&!"17".equals(ruleOpt))){
					//取最近环节历史记录
					his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(appMap);
					if("2".equals(ydjFlag)&&"1".equals(matchingCardFlag)&&!"".equals(laoJianFlag)&&his==null){//标卡2码无历史记录
						String taoId="";
						if(appId.endsWith("1")){
							taoId=appId.substring(0,15).toString()+"2";
						}else if(appId.endsWith("2")){
							taoId=appId.substring(0,15).toString()+"1";
						}else{
							taoId=appId;
						}
						//appId重新赋值
						appMap.put("appId", taoId);
						//重查询1码历史
						his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(appMap);
					}
					if(his!=null){//征信历史记录不为空
						String queue=his.getCurrOptQueue()!=null?his.getCurrOptQueue():"";
						String queueName=his.getLastOptGroup()!=null?his.getLastOptGroup():"";
						String group=his.getCurrOptGroup()!=null?his.getCurrOptGroup():"";
						String groupName=his.getLastOptGroup()!=null?his.getLastOptGroup():"";
						String user=his.getCurrOptUser()!=null?his.getCurrOptUser():"";
						String lastUser="";
						String currUserName=his.getCurrUserName()!=null?his.getCurrUserName():"";
						String status=his.getCurrStatus()!=null?his.getCurrStatus():"";
						allotApply.setCurrQueue(queue);
						allotApply.setLastQueue(queueName);
						allotApply.setCurrGroup(group);
						allotApply.setLastGroup(groupName);
						allotApply.setQueueDate(now);
						allotApply.setGroupDate(now);
						allotApply.setGroupTeamDate(now);
						if("108".equals(ruleOpt)||"109".equals(ruleOpt)){//二次决策或审批被拒 捞件到原征信组
							if("108".equals(ruleOpt)){
								flag="10";
							}else if("109".equals(ruleOpt)){
								flag="11";
							}
							//时间重新来算
							allotApply.setCurrUser("");
							allotApply.setCurrUserName("");
							allotApply.setLastUser("");
							allotApply.setUserDate(null);
							allotApply.setCurrStatus("1");
							allotApply.setSynFlag("0");
							demoCode=group;
						}else if("1".equals(keyMessageFlag)){// 关键信息修改过的件 到原来征信人或组
							flag="12";
							allotApply.setCurrUser(user);
							allotApply.setCurrUserName(currUserName);
							//修改关键信息特殊 最后操作员为录入提交人员  查询生命周期
							appMap.put("operateResult", "审查环节提交成功");
							lastUser=StringUtils.trimToEmpty(applyLifeCicleDao.queryOperater(appMap));
							if(!"".equals(lastUser)){
								String[] a =lastUser.split("-");
								allotApply.setLastUser(a[1]);
							}
							//征信员有可能换过组
							allotCommon=allotCommonDao.selectGroupByUserCode(user);
							if(allotCommon!=null){
								allotApply.setCurrGroup(allotCommon.getOrgNo()!=null?allotCommon.getOrgNo():"");
								allotApply.setLastGroup(allotCommon.getOrgName()!=null?allotCommon.getOrgName():"");
								allotApply.setCurrUserName(allotCommon.getUserName()!=null?allotCommon.getUserName():"");
							}
							//进入风险队列时间及分组时间不变,继续累积QUEUE_DATE   GROUP_DATE
							allotApply.setUserDate(now);
							allotApply.setCurrStatus(status);
							demoCode=user;
							//查询该申请件是否是审批退回再修改  是将backflag改为4
							Map<String,Object> hisMap=new HashMap<String,Object>();
							hisMap.put("appId", appId);
							hisMap.put("currNode", "03");
							AllotApplyAllotHis shenHis=allotApplyHisDao.queryAllotApplyAllotHisByAppId(hisMap);
							if(shenHis!=null&&("A".equals(his.getBackFlag()))&&"3".equals(shenHis.getCurrStatus())){
								allotApply.setBackFlag("4");
							}
						}
						allotApply.setDelStatus("0");
					}else{//不存在征信历史记录  智能语音免征信件捞件 到征信池
						logger.info("WorkFlowService.saveAllotApply appId= "+appId+"该件该环节历史记录为空");
						flag="14";
						allotApply.setCurrQueue("");
						allotApply.setLastQueue("");
						allotApply.setCurrGroup("");
						allotApply.setLastGroup("");
						allotApply.setCurrUser("");
						allotApply.setCurrUserName("");
						allotApply.setLastUser("");
						allotApply.setQueueDate(null);
						allotApply.setGroupDate(null);
						allotApply.setUserDate(null);
						allotApply.setCurrStatus("0");
						allotApply.setDelStatus("0");
						allotApply.setSynFlag("0");
					}
				}else if("17".equals(ruleOpt)){//审批退回动作(空跑,审批页面更改)
					//查询分件表   判断普通审批退回到原征审合一审批(工作流节点均为02)  还是  普通审批退回普通征信 2019.11.14 智能语音正核过的件 审批首次退回征信
					Map tuiMap=new HashMap();
					tuiMap.put("appId", appId);
					tuiApp=allotApplyDao.selectSingleByAppId(tuiMap);
					//修改curr_status状态(审批退回征信时修改过)
					if(tuiApp!=null){
						//普通审批退回到原征审合一审批(工作流节点均为02)
						if("03".equals(tuiApp.getCurrNode())&&"2".equals(tuiApp.getCheck_meuoFlag())){
							flag="24";//将隐藏curr_status 9改回3
							tuiApp.setCurrStatus("3");
						}else if("1".equals(tuiApp.getApproveReturn())){//智能语音正核过的件
							his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(appMap);
							AllotCommon acmon = allotCommonDao.selectGroupByUserCode(his.getLastOptUser());
							if(acmon!=null){
								tuiApp.setCurrGroup(acmon.getOrgNo());
								tuiApp.setCurrUserName(acmon.getUserName());					
							}else{
								logger.info("申请件:{}智能语音审批退回未能找到审批操作员所在组信息",appId);
							}
							tuiApp.setCurrUser(his.getLastOptUser());
							tuiApp.setCurrStatus("3");
							flag="26";
						}else{//普通审批退回普通征信(征信批量提交件修改退回到审批人征信环节)
							back_synFlag=tuiApp.getSynFlag();
							if("1".equals(tuiApp.getBatchCreditFlag())){
								flag="25";
							}else{
								flag="13";
							}
							tuiApp.setCurrStatus("3");
						}
						tuiApp.setBatchCreditFlag("0");
					}
					result=99;
				}else if("".equals(ruleOpt)&&"11".equals(isBack)){//征审合一审批已完成从待归档收回
					Map tuiMap=new HashMap();
					tuiMap.put("appId", appId);
					tuiApp=allotApplyDao.selectSingleByAppId(tuiMap);
					//修改curr_status状态(征审合一审批已完成修改过)
					if(tuiApp==null){
						tuiApp=new AllotApply();
						tuiApp.setAppId(appId);
					}
					tuiApp.setCurrStatus("3");
					flag="24";//不记录生命周期 但需要更新操作
					result=99;
				}else if("2".equals(allotApply.getSourceType())&&!"1".equals(allotApply.getConclusion())){//征信电核件提交智能语音   无人接听或者未通过的件
					flag="27";
					demoCode=allotApply.getCurrUser();
				}else{//正常到征信池
					flag="14";
					allotApply.setCurrQueue("");
					allotApply.setLastQueue("");
					allotApply.setCurrGroup("");
					allotApply.setLastGroup("");
					allotApply.setCurrUser("");
					allotApply.setCurrUserName("");
					allotApply.setLastUser("");
					allotApply.setQueueDate(null);
					allotApply.setGroupDate(null);
					allotApply.setUserDate(null);
					allotApply.setCurrStatus("0");
					allotApply.setDelStatus("0");
					allotApply.setSynFlag("0");
					allotApply.setCrtTeamDate(now);
				}
				allotApply.setLstTeamDate(now);
				allotApply.setCurrNode("02");
				allotApply.setSubmitStatus("");
				allotApply.setSubmitType("");
				allotApply.setSubmitMemo("");
				allotApply.setReviewStatus("");
	}else if ("03".equals(currNode)){//审批
		String backFlag="",group="",user="",lastUser="",groupName="",currName="",systemBatch="";
		String specialExamineUser=map.get("specialExamineUser");
		logger.info("WorkFlowService.saveAllotApply 审批环节申请件编号为"+appId+"--specialExamineUser值为【"+specialExamineUser+"】");
		int id=0;
		if(!"".equals(specialExamineUser)&&specialExamineUser!=null){
			String[] strList=specialExamineUser.split("-");
			if(strList.length>0){
				id=Integer.parseInt(strList[0].toString());
				//征信员
				lastUser=strList[1].trim();
				if(strList.length>2){
					user=strList[2];
				}
			}
		}
		//判断是否修改关键信息修改 查历史是否为空    是的话 取值判断backFlag 标记为A还是B 
		if("1".equals(keyMessageFlag)&&id!=4){
			List<String> backFlagList=new ArrayList<String>();
			backFlagList.add("A");
			backFlagList.add("B");
			appMap.put("backFlagList", backFlagList);
			his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(appMap);
			if(his!=null){
				backFlag=his.getBackFlag()==null?"":his.getBackFlag();
			}
		}
		//审批修改关键信息  到原审批员 
		if("B".equals(backFlag)){
			group=his.getCurrOptGroup()==null?"":his.getCurrOptGroup();
			groupName=his.getLastOptGroup()==null?"":his.getLastOptGroup();
			user=his.getCurrOptUser()==null?"":his.getCurrOptUser();
			currName=his.getCurrUserName()==null?"":his.getCurrUserName();
			//审批员有可能换过组
			allotCommon=allotCommonDao.selectGroupByUserCode(user);
			if(allotCommon!=null){
				allotApply.setCurrGroup(allotCommon.getOrgNo()!=null?allotCommon.getOrgNo():"");
				allotApply.setLastGroup(allotCommon.getOrgName()!=null?allotCommon.getOrgName():"");
				allotApply.setCurrUserName(allotCommon.getUserName()!=null?allotCommon.getUserName():"");
			}else{
				allotApply.setCurrGroup(group);
				allotApply.setLastGroup(groupName);
				allotApply.setCurrUserName(currName);
			}
			allotApply.setCurrUser(user);
			//最后操作员取最后征信人员  specialExamineUser
			allotApply.setLastUser(lastUser);
			allotApply.setGroupTeamDate(now);
			allotApply.setUserDate(now);
			allotApply.setCurrStatus("3");
			allotApply.setDelStatus("0");
			allotApply.setBackFlag("0");
			allotApply.setCurrNode("03");
			allotApply.setSynFlag("0");
			demoCode=user;
			flag="22";
		}else{//非修改关键信息件   根据specialExamineUser判断
			if(!"".equals(specialExamineUser)&&specialExamineUser!=null){
					allotCommon=allotCommonDao.selectGroupByUserCode(lastUser);
					//征信员所在的组
					String orgNo=allotCommon.getOrgNo();
					String orgName=allotCommon.getOrgName();
					String orgId=allotCommon.getOrgId();
					//风险队列时间保留
					if(id==1){//普通征信提交到审批  分件
						String havShen=selectApproveRole(orgId);
						Map shenMap=new HashMap();
						shenMap.put("appId", appId);
						shenMap.put("currNode", "03");
						//取最近当前环节历史记录  如果不为空  证明为审批被拒重新捞回  如果为空 证明前边环节被拒捞回
						AllotApplyAllotHis his2=allotApplyHisDao.queryAllotApplyAllotHisByAppId(shenMap);
						if("2".equals(ydjFlag)&&"1".equals(matchingCardFlag)&&!"".equals(laoJianFlag)&&his2==null){//标卡2码无历史记录
							String taoId=appId.substring(0,15).toString()+"1";
							//appId重新赋值
							shenMap.put("appId", taoId);
							//重查询1码历史
							his2=allotApplyHisDao.queryAllotApplyAllotHisByAppId(shenMap);
						}
						if("".equals(laoJianFlag)||his2==null){//非捞件件或审批环节之前被拒捞回
							 if("1".equals(havShen)){//没有审批员  到审批池
								 flag="15";
								 allotApply.setCurrGroup("");
								 allotApply.setLastGroup("");
								 //分组时间不能清空  需要保留征信环节分组时间
								 allotApply.setCurrStatus("0");
							 }else if("2".equals(havShen)){//1、有审批员  直接到该组(如果不是捞件) 
								 flag="18";
								 allotApply.setCurrGroup(orgNo);
								 demoCode=orgNo;
								 allotApply.setLastGroup(orgName);
								 allotApply.setGroupTeamDate(now);
								 //分组时间不能重新赋值  需要保留征信环节分组时间
								 allotApply.setCurrStatus("1");
							 }
						}else if(!"".equals(laoJianFlag)&&his2!=null){//审批被拒捞件到最后的组
							flag="23";
							allotApply.setCurrGroup(his2.getCurrOptGroup());
							demoCode=his2.getCurrOptGroup();
							allotApply.setLastGroup(his2.getLastOptGroup());
							//分组时间不能重新赋值  需要保留征信环节分组时间
							allotApply.setCurrStatus("1");
						}
						allotApply.setDelStatus("0");
						allotApply.setCurrUser("");
						allotApply.setCurrUserName("");
						allotApply.setLastUser(lastUser);
						allotApply.setUserDate(null);
					}else if(id==2){//特殊件 征信直接到审批员(审批员与征信员不一定是同一个人)
						flag="16";
						AllotCommon acmon=allotCommonDao.selectGroupByUserCode(user);
						if(acmon!=null){
							allotApply.setCurrUserName(acmon.getUserName());
							allotApply.setCurrGroup(acmon.getOrgNo());
							allotApply.setLastGroup(acmon.getOrgName());
						}else{
							allotApply.setCurrGroup(orgNo);
							allotApply.setLastGroup(orgName);
						}
						allotApply.setCurrUser(user);
						allotApply.setLastUser(lastUser);
						allotApply.setGroupTeamDate(now);
						allotApply.setUserDate(now);
						allotApply.setCurrStatus("3");
						allotApply.setDelStatus("0");
						demoCode=user;
					}else if(id==3){//征审合一(征信员与审批员是同一个人)
						flag="17";
						allotApply.setUserDate(now);
						demoCode=lastUser;
					}else if(id==5){//该件审批退回  又提交到审批  到原审批人
						flag="19";
						Map shenMap=new HashMap();
						shenMap.put("appId", appId);
						shenMap.put("currNode", "03");
						shenMap.put("backFlag", "4");
						//取最近环节历史记录
						AllotApplyAllotHis his2=allotApplyHisDao.queryAllotApplyAllotHisByAppId(shenMap);
						if(his2!=null){
							//此处注意  当前人取历史记录中的lastoptuser 不要更改;
							user=StringUtils.trimToEmpty(his2.getLastOptUser());
							//审批员有可能换过组
							allotCommon=allotCommonDao.selectGroupByUserCode(user);
							if(allotCommon!=null){
								allotApply.setCurrGroup(allotCommon.getOrgNo()!=null?allotCommon.getOrgNo():"");
								allotApply.setLastGroup(allotCommon.getOrgName()!=null?allotCommon.getOrgName():"");
								allotApply.setCurrUserName(allotCommon.getUserName()!=null?allotCommon.getUserName():"");
							}else{
								group=StringUtils.trimToEmpty(his2.getCurrOptGroup());
								groupName=StringUtils.trimToEmpty(his2.getLastOptGroup());
								currName=StringUtils.trimToEmpty(his2.getCurrUserName());
								if(!"".equals(group)){
									allotApply.setCurrGroup(group);
								}
								if(!"".equals(groupName)){
									allotApply.setLastGroup(groupName);
								}
								allotApply.setCurrUserName(currName);
							}
							allotApply.setCurrUser(user);
							allotApply.setLastUser(lastUser);
							allotApply.setGroupTeamDate(now);
							allotApply.setUserDate(now);
							allotApply.setCurrStatus("3");
							allotApply.setDelStatus("0");
							allotApply.setBackFlag("0");
							demoCode=user;
						}
					}else if(id==6){//征审合一件分件或转移到普通审批员  空跑流程
						flag="2";
						result=100;
					}
			}else{//普通审批收回 及政审合一的转上级或退回审批 到非征审合一角色  2019.11.14 智能语音件免征信到审批池
				Map tuiMap=new HashMap();
				tuiMap.put("appId", appId);
				tuiApp=allotApplyDao.selectSingleByAppId(tuiMap);
				if(tuiApp==null||"0".equals(tuiApp.getApproveReturn())){//智能语音免征信到审批池
					allotApply.setCurrGroup("");
					allotApply.setLastGroup("");
					allotApply.setGroupDate(now);
					allotApply.setCurrStatus("0");
					allotApply.setDelStatus("0");
					allotApply.setLastUser(allotApply.getCurrUser());
					allotApply.setCurrUser("");
					allotApply.setCurrUserName("");
					flag="15";
				}else{//普通审批收回 及政审合一的转上级或退回审批 到非征审合一角色 
					tuiApp.setCurrStatus("3");//修改curr_status状态(审批退回时修改过)
					flag="24";//不记录生命周期 但需要更新操作
					result=99;
				}
			}
			allotApply.setCrtTeamDate(now);
			allotApply.setLstTeamDate(now);
			allotApply.setCurrNode("03");
			allotApply.setSynFlag("0");
		}
		//如果是批量提交件  批量标识更改
		if("systemBatch".equals(user)){
			allotApply.setBatchCreditFlag("1");
		}
		}
		if(result!=99&&result!=100){
			if(count==0){
				result=getAllotApplyDao().insertAllotApply(allotApply);
			}else if(count>0){
				if("1".equals(flag)||"8".equals(flag)||"14".equals(flag)){
					result=getAllotApplyDao().update(allotApply);
				}else{
					result=getAllotApplyDao().updateAllotApply(allotApply);
					//套卡最后操作时间相同
					if((!"0".equals(matchingCardFlag))&&("".equals(laoJianFlag)||"00".equals(laoJianFlag))){
						Map timeMap=new HashMap();
						timeMap.put("appId", appId.substring(0, 15));
						getAllotApplyDao().updateLstOptTime(timeMap);
					}
				}
			}
		}else if(result==99){//审批退回征信
			getAllotApplyDao().updateAllotApply(tuiApp);
		}
		logger.info("flag标识============="+flag);
		if(result==0){
			logger.info("WorkFlowService.saveAllotApply Outer ,appId="+appId+"导入失败");
			throw  new RuntimeException("WorkFlowService.saveAllotApply Outer,appId="+appId+" 导入失败");
		}else{
			//由于跟工作流记录重复调征信策略不记录生命周期    或征审合一件分件或转移到普通审批或普通审批退回到征审合一审批
			if(!"2".equals(flag)&&(!"24".equals(flag))){
				saveAppLifeCicle(appId,flag,demoCode,back_synFlag);
			}
			//征信提交到审批  判断该申请件在审批表是否存在,存在 更新 INIT_SAVE_FLAG为0(要排除征审合一提交上级 12)
			if("03".equals(currNode)&&(!"12".equals(isBack)&&!"24".equals(flag))){
				BizApproval approval=bizApprovalDao.selectByAppId(appId);
				if(approval!=null&&!"0".equals(approval.getInitSaveFlag())){
					Map<String,String> approvalMap=new HashMap<String,String>();
					approvalMap.put("appId", appId.substring(0, 15));
					bizApprovalDao.updateInitSaveFlag(approvalMap);
				}
			}
			logger.info("WorkFlowService.saveAllotApply Outer,appId="+appId+" 导入成功");
		} 
	}
	
	public String  selectApproveRole(String orgId) throws Exception{
		int countShen=0;
		//两者情况1、 征信员 与审批员都拥有   继续到该组  2、 只有征信员  到审批池
		//审批角色  
		List<String> roleCodes=new ArrayList<String>();
		 roleCodes.add("L1");
		 roleCodes.add("L2");
		 roleCodes.add("L3");
		//同时排查掉组长的审批权 用L1 L2或L3去查拥有审批权限人的数量,如果为1说明只有组长由审批权限
		Map<String,Object> roleMap=new HashMap<String,Object>();
		roleMap.put("orgId", orgId);
		roleMap.put("roleCodes", roleCodes);
		countShen=getAllotCommonDao().selectCountShen(roleMap);
		//判断该组是否拥有审批员
		String havShen=null;
		if(countShen<2){//一个或零个有审批权限 
			havShen="1";
		}else{//多人有审批权
			havShen="2";
		}
		return havShen;
	}
	/**
	 * 征信策略18.11.10版本
	 * @author wangdebin
	 * @version v1.2
	 * @history 
	 */
	public int saveCreditResult4(String appId,String ydjFlag,String matchingCardFlag) throws Exception {
		String node = "credittactics";
		logger.info("[分件]调用征信策略开始  申请件流水号="+appId+",易达金标识="+ydjFlag+",套卡标识="+matchingCardFlag);
		Map map = invokeRuleService.invokeRule(appId, node);
		//标准卡征信策略结论  或易达金征信策略分层
		List<String> riskLevels=(List<String>) map.get("riskLevels");
		logger.info("[分件] 调用征信策略结果-风险等级：" + riskLevels);
		//标准卡征信策略结论描述(易达金不使用)
		List<String> outResults=(List<String>) map.get("outResults");
		logger.info("[分件] 调用征信策略结果-风险结果描述：" + outResults);
		//策略规则id 
		List<String> ruleIds=(List<String>) map.get("ruleIds");
		String ruleId=ruleIds.get(0);
		logger.info("[分件] 调用征信策略规则代码：" + ruleId);
		//数据库征信结论代码、数据库征信结论描述代码、数据库征信结论汉字描述、易达金与标准卡区分标识
		String creditDecisionResult="",creditDecisionLayer="",creditDecisionDesc="",flag="";
		if("1".equals(ydjFlag)&&!"1".equals(matchingCardFlag)){//易达金
			flag="1";
		}else{//标准卡及易达金套卡
			flag="2";
		}
		int count=0,num=0;
		if(riskLevels.isEmpty()!=true){
			String tempStr=PropertiesUtil.getProperty(ruleId,flag);
			if("不存在该征信策略描述".equals(tempStr)){
				creditDecisionDesc=tempStr;
			}else{
				creditDecisionDesc=new String(tempStr.getBytes("ISO8859-1"),"UTF-8");
			}
			creditDecisionResult=riskLevels.get(0);
			//易达金描述层级直接命名ruleId  标准卡由于描述与层级不是唯一对应关系,部分需要转换
			if("1".equals(ydjFlag)&&("2".equals(matchingCardFlag)||"0".equals(matchingCardFlag))){//易达金套卡主卡或易达金卡
				creditDecisionLayer=ruleId;
				count=allotYdjResultDao.selectCount(appId);
				YdjSysresultInfo info=new YdjSysresultInfo();
				info.setAppId(appId);
				info.setCreditDecisionResult(creditDecisionResult);
				info.setCreditDecisionDesc(creditDecisionDesc);
				info.setCreditDecisionLayer(creditDecisionLayer);
				if(outResults.size()>0){
					StringBuffer sb=new StringBuffer();
					for(int j=0;j<outResults.size();j++){
						if(j!=(outResults.size()-1)){
							sb.append(outResults.get(j).toString()+"+");
						}else{
							sb.append(outResults.get(j).toString());
						}
					}
					info.setCrtUser(sb.toString());
				}else{
					info.setCrtUser("system");
				}
				if(count==0){
					info.setAutoId(UUIDGenerator.getUUID());
					num=allotYdjResultDao.insertYdjResultInfo(info);
				}else{
					num=allotYdjResultDao.updateYdjResultInfo(info);
				}
				if(num==0){
					logger.info("WorkFlowService.saveCreditResult Outer 征信策略返回数据导入失败");
				}else{
					logger.info("WorkFlowService.saveCreditResult Outer 征信策略返回数据导入成功");
				}
			}else if(("2".equals(ydjFlag))||("1".equals(ydjFlag)&&"1".equals(matchingCardFlag))){//标准卡 或者 易达金套卡配发卡
				if("LR6-2".equals(ruleId)||"LRD6-2".equals(ruleId)){
					creditDecisionLayer="LR6-2";
				}else if("WLR6-2".equals(ruleId)||"WLRD6-2".equals(ruleId)){
					creditDecisionLayer="WLR6-2";
				}else if("LR6-3".equals(ruleId)||"LRD6-3".equals(ruleId)){
					creditDecisionLayer="LR6-3";
				}else if("WLR6-3".equals(ruleId)||"WLRD6-3".equals(ruleId)){
					creditDecisionLayer="WLR6-3";
				}else if("LR6-4".equals(ruleId)||"LRD6-4".equals(ruleId)){
					creditDecisionLayer="LR6-4";
				}else if("WLR6-4".equals(ruleId)||"WLRD6-4".equals(ruleId)){
					creditDecisionLayer="WLR6-4";
				}else if("LR6_TY_6".equals(ruleId)||"LR6_TY_3".equals(ruleId)||"LR6_TY_D6".equals(ruleId)
				   ||"WLR6_TY_6".equals(ruleId)||"WLR6_TY_3".equals(ruleId)||"WLR6_TY_D6".equals(ruleId)){
					creditDecisionLayer="LR6_TY_6";
				}else if("LR7_TY_6".equals(ruleId)||"LR7_TY_3".equals(ruleId)||"LR7_TY_D6".equals(ruleId)
				||"WLR7_TY_6".equals(ruleId)||"WLR7_TY_3".equals(ruleId)||"WLR7_TY_D6".equals(ruleId)){
					creditDecisionLayer="LR7_TY_6";
				}else if("LR8_TY_6".equals(ruleId)||"LR8_TY_3".equals(ruleId)||"LR8_TY_D6".equals(ruleId)
				||"WLR8_TY_6".equals(ruleId)||"WLR8_TY_3".equals(ruleId)||"WLR8_TY_D6".equals(ruleId)){
					creditDecisionLayer="LR8_TY_6";
				}else if("GFXRGZH".equals(ruleId)||"WGFXRGZH".equals(ruleId)){
					creditDecisionLayer="GFXRGZH";
				}else if("RGZH".equals(ruleId)||"WRGZH".equals(ruleId)){
					creditDecisionLayer="RGZH";
				}else{
					creditDecisionLayer=ruleId;
				}
				count=allotBzkResultDao.selectCount(appId);
				OpaBzkSysResultInfo info=new OpaBzkSysResultInfo();
				info.setAppId(appId);
				info.setCreditDecisionResult(creditDecisionResult.trim()==null?null:creditDecisionResult.trim());
				info.setCreditDecisionDesc(creditDecisionDesc.trim()==null?null:creditDecisionDesc.trim());
				info.setCreditDecisionLayer(creditDecisionLayer.trim()==null?null:creditDecisionLayer.trim());
				if(outResults.size()>0){
					StringBuffer sb=new StringBuffer();
					for(int j=0;j<outResults.size();j++){
						if(j!=(outResults.size()-1)){
							sb.append(outResults.get(j).toString()+"+");
						}else{
							sb.append(outResults.get(j).toString());
						}
					}
					info.setCrtUser(sb.toString());
				}else{
					info.setCrtUser("system");
				}
				if(count==0){
					info.setAutoId(UUIDGenerator.getUUID());
					num=allotBzkResultDao.insertBzkResultInfo(info);
				}else {
					num=allotBzkResultDao.updateBzkResultInfo(info);
				}
				if(num==0){
					logger.info("WorkFlowService.saveCreditResult Outer 征信策略返回数据导入失败");
				}else{
					logger.info("WorkFlowService.saveCreditResult Outer 征信策略返回数据导入成功");
				}
			}
		}else{
			logger.info("WorkFlowService.saveCreditResult Outer 本次调用征信策略未触碰任何一个征信策略规则");
		}
		return num;
	}
	@Override
	public int saveAppLifeCicle(String appId,String flag,String demoCode,String back_synFlag) throws Exception {
		logger.info("WorkFlowService.saveAppLifeCicle 开始记录生命周期");
		int result=0;
		flag=flag!=null?flag:"";
		ApplyLifeCicle a=new ApplyLifeCicle();
		String currUserName="";
		Map<String,Object> userMap=new HashMap<String,Object>();
		Map<String,Object> hisMap=new HashMap<String,Object>();
		hisMap.put("appId", appId);
		if("3".equals(flag)|| "6".equals(flag)|| "9".equals(flag)|| "12".equals(flag)
			||"16".equals(flag)||"17".equals(flag)||"19".equals(flag)||"21".equals(flag)||"22".equals(flag)||"27".equals(flag)
			){
			if(demoCode!=null&&!"".equals(demoCode)){
				userMap.put("userCode", demoCode);
				AllotCommon allotCommon=allotCommonDao.selectUser(userMap);
				if(allotCommon!=null){
					currUserName=allotCommon.getUserName();
				}
			}
		}else if("4".equals(flag)||"5".equals(flag)||"10".equals(flag)
				||"11".equals(flag)||"18".equals(flag)||"23".equals(flag)){
			if(demoCode!=null&&!"".equals(demoCode)){
				AllotCommon allotCommon=allotCommonDao.selectGroupByOrgNo(demoCode);
				if(allotCommon!=null){
					currUserName=allotCommon.getOrgName();
				}
			}
		}
		if("1".equals(flag)||"20".equals(flag)){
			a.setOperateDesc("进入标准卡审查池");
		}else if("2".equals(flag)){
			a.setOperateDesc("调征信策略");
		}else if("3".equals(flag)){
			a.setOperateDesc("收回至"+currUserName+"未完成队列");
		}else if("4".equals(flag)){
			a.setOperateDesc("二次决策被拒捞件到征信组-"+currUserName);
		}else if("5".equals(flag)){
			a.setOperateDesc("审批被拒捞件到征信组-"+currUserName);
		}else if("6".equals(flag)){
			a.setOperateDesc("关键信息修改过的件 到征信员-"+currUserName+"未完成队列");
		}else if("7".equals(flag)){//易达金审批退回征信环节
			//查询分配表  取当前人
			AllotApply allotApply=allotApplyDao.selectHandByAppId(hisMap);
			if(allotApply!=null){
				String currUser=allotApply.getCurrUser()==null?"":allotApply.getCurrUser();
				if(!"".equals(currUser)){
					userMap.put("userCode", currUser);
					AllotCommon allotCommon=allotCommonDao.selectUser(userMap);
					if(allotCommon!=null){
						currUserName=allotCommon.getUserName();
						a.setOperateDesc("审批退回动作,到征信员-"+currUserName+"已退回队列");
					}else{
						a.setOperateDesc("审批退回动作,到征信员已退回队列。");
					}
				}else{
					demoCode=allotApply.getCurrGroup()==null?"":allotApply.getCurrGroup();
					if(!"".equals(demoCode)){
						AllotCommon allotCommon=allotCommonDao.selectGroupByOrgNo(demoCode);
						if(allotCommon!=null){
							currUserName=allotCommon.getOrgName();
							a.setOperateDesc("审批退回动作,到征信组(征信批量提交件)-"+currUserName);
						}
					}else{
						a.setOperateDesc("审批退回动作,到征信组(征信批量提交件)。");
					}
				}
			}
		}else if("8".equals(flag)){
			a.setOperateDesc("进入征信池");
		}else if("9".equals(flag)){
			a.setOperateDesc("收回至"+currUserName+"未完成队列");
		}else if("10".equals(flag)){
			a.setOperateDesc("二次决策被拒捞件到征信组-"+currUserName);
		}else if("11".equals(flag)){
			a.setOperateDesc("审批被拒捞件到征信组-"+currUserName);
		}else if("12".equals(flag)){
			a.setOperateDesc("关键信息修改过的件到征信员-"+currUserName+"未完成队列");
		}else if("13".equals(flag)||"25".equals(flag)){//标准卡审批退回征信环节
			hisMap.put("currNode", "02");
			hisMap.put("backFlag", "4");
			//取最近环节历史记录
			AllotApplyAllotHis his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(hisMap);
			if(his!=null&&!"".equals(his.getCurrOptUser())){//普通件审批退回征信（征信员）批量件审批退回征信（无征信员，到审批员征信已退回队列）
				userMap.put("userCode", his.getCurrOptUser());
				AllotCommon allotCommon=allotCommonDao.selectUser(userMap);
				if(allotCommon!=null){
					currUserName=allotCommon.getUserName();
					if("13".equals(flag)){
						a.setOperateDesc("审批退回动作,到征信员-"+currUserName+"已退回队列");
					}else{
						a.setOperateDesc("审批退回动作,征信批量提交件到审批员-"+currUserName+"征信已退回队列");
					}
				}else{
					a.setOperateDesc("审批退回动作,到征信员已退回队列");
				}
			}else{
				a.setOperateDesc("审批退回动作,到征信员已退回队列");
			}
		}else if("14".equals(flag)){
			a.setOperateDesc("进入征信池");
		}else if("15".equals(flag)){
			a.setOperateDesc("征信提交到审批池");
		}else if("16".equals(flag)){
			a.setOperateDesc("征信直接到审批员-"+currUserName+"未完成队列");
		}else if("17".equals(flag)){
			a.setOperateDesc("征审合一到审批员-"+currUserName+"未完成队列");
		}else if("18".equals(flag)){
			a.setOperateDesc("征审组相同,直接到审批组-"+currUserName);
		}else if("19".equals(flag)){
			a.setOperateDesc("审批退回征信,再提交到审批员-"+currUserName+"未完成队列");
		}else if("21".equals(flag)){
			a.setOperateDesc("关键信息修改过的件到审查员-"+currUserName+"未完成队列");
		}else if("22".equals(flag)){
			a.setOperateDesc("关键信息修改过的件到审批员-"+currUserName+"未完成队列");
		}else if("23".equals(flag)){
			a.setOperateDesc("审批被拒捞件到审批组-"+currUserName);
		}else if("26".equals(flag)){
			hisMap.put("currNode", "02");
			hisMap.put("backFlag", "4");
			//取最近环节历史记录
			AllotApplyAllotHis his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(hisMap);
			userMap.put("userCode", his.getLastOptUser());
			AllotCommon allotCommon=allotCommonDao.selectUser(userMap);
			a.setOperateDesc("智能语音提交审批退回动作,到征信员-"+allotCommon.getUserName()+"未完成队列");
		}else if("27".equals(flag)){
			a.setOperateDesc("征信提交到智能语音,智能语音未通过或无人接听件,退回到原征信员-"+currUserName+"未完成队列");
		}
		a.setAppId(appId);
		a.setOperateResult("完成");
		if("3".equals(flag)||"9".equals(flag)){//征信收回
			a.setCrtUser(currUserName+"-"+demoCode);
			a.setOperater(currUserName);
		}else if("7".equals(flag)||"13".equals(flag)){//审批退回征信  查询历史表查最后操作员
			//判断back_synFlag是否为隐藏情况
			if("1".equals(back_synFlag)){//隐藏 系统帮助退回征信
				a.setCrtUser("system");
				a.setOperater("system");
			}else{//主动退回征信
				hisMap.put("currNode", "03");
				hisMap.put("backFlag", "4");
				//取最近环节历史记录
				AllotApplyAllotHis his=new AllotApplyAllotHis();
				his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(hisMap);
				String caozuoCode=StringUtils.trimToEmpty(his.getLastOptUser());
				if(his!=null&&!"".equals(caozuoCode)){
					userMap.put("userCode",caozuoCode);
					AllotCommon allotCommon=allotCommonDao.selectUser(userMap);
					if(allotCommon!=null){
						String caozuoName=allotCommon.getUserName();
						a.setCrtUser(caozuoName+"-"+caozuoCode);
						a.setOperater(caozuoName+"-"+caozuoCode);
					}
				}else{
					a.setCrtUser("system");
					a.setOperater("system");
				}
			}
		}else if("26".equals(flag)){
			hisMap.put("currNode", "02");
			hisMap.put("backFlag", "4");
			AllotApplyAllotHis his=new AllotApplyAllotHis();
			his=allotApplyHisDao.queryAllotApplyAllotHisByAppId(hisMap);
			userMap.put("userCode",his.getLastOptUser());
			AllotCommon allotCommon=allotCommonDao.selectUser(userMap);
			a.setCrtUser(allotCommon.getUserName()+"-"+his.getLastOptUser());
			a.setOperater(allotCommon.getUserName()+"-"+his.getLastOptUser());
		}else{
			a.setCrtUser("system");
			a.setOperater("system");
		}
		a.setCrtDate(new Date());
		a.setUuid(StringUtil.randomUUIDPlainString());
		result=applyLifeCicleDao.addApplyLifeCicle(a);
		if(result==0){
			logger.info("WorkFlowService.saveAppLifeCicle 生命周期记录失败");
		}else{
			logger.info("WorkFlowService.saveAppLifeCicle 生命周期记录成功");
		}
		return result;
	}
}
