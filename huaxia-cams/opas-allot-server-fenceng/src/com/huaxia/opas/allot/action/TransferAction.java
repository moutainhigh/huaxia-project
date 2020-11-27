package com.huaxia.opas.allot.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.util.UUIDGenerator;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllotHis;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.allot.AllotQueue;
import com.huaxia.opas.domain.apply.ApplyLifeCicle;
import com.huaxia.opas.service.allot.AllotApplyHisService;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.allot.AllotMappingRuleService;
import com.huaxia.opas.service.allot.AllotMethodService;
import com.huaxia.opas.service.allot.AllotQueueService;
import com.huaxia.opas.service.allot.AllotRuleService;
import com.huaxia.opas.service.allot.AllotSwitchService;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.util.StringUtil;
/**
 * 类说明：件转移 回收   人之间   组之间  队列之间 
 * @author wangdebin 
 * @version 1.0
  * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-20  修复申请件前15位取值的问题
 *  2017-10-24  修复组员之间申请件转移最后操作员赋值问题
 * ------------------------------------------------
 */
public class TransferAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(TransferAction.class);
	
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
	
	@Resource(name = "applyLifeCicleService")
	private ApplyLifeCicleService applyLifeCicleService;
	
	@Resource(name = "allotMethodService")
	private AllotMethodService allotMethodService;
	
	private static final int commitCountEveryTime=10000;
	/**
	* @Description: 队列之间转移  同组组员之间  组之间可互转 
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void transferApply(Context context) throws ParseException, JsonGenerationException, JsonMappingException, IOException {
		try {
			//转移方式
			int id = Integer.parseInt((String) context.getData("id"));
			String transferId=String.valueOf(id);
			String node =(String) context.getData("node")!=null?(String) context.getData("node"):"";
			String secondNode =(String) context.getData("secondNode")!=null?(String) context.getData("secondNode"):"";
			String currCode =(String) context.getData("currCode")!=null?(String) context.getData("currCode"):"";
			String userCode =(String) context.getData("userCode")!=null?(String) context.getData("userCode"):"";
			String ydjFlag =(String) context.getData("ydjFlag")!=null?(String) context.getData("ydjFlag"):"";
			String currStatusStr =(String) context.getData("currStatusStr")!=null?(String) context.getData("currStatusStr"):"";
			//转移特殊包含挂起情况
			context.setData("isHangUp", "1");
			context.setData("currNode", node);
			String flag="4";
			Map<String, Object> lifeMap = new HashMap<String, Object>();
			//获取分件表分件状态  考虑到挂起标签和页面转移队列情况
			List<String> currStatusList=getCurrStatusList(context,"2");
			lifeMap.put("currStatusList", currStatusList);
			context.setData("currStatusList", currStatusList);
			if(StringUtils.isNotEmpty(currStatusStr)){//选择具体队列
				String[] tmp = currStatusStr.split(",");
				List<String> currList = new ArrayList<String>();
				for (int i = 0; i < tmp.length; i++) {
					currList.add(tmp[i]);
				}
				context.setData("currStatusList", currList);
				lifeMap.put("currStatusList", currList);
			}
			lifeMap.put("wayId", transferId);
			lifeMap.put("currNode", node);
			lifeMap.put("secondNode", secondNode);
			lifeMap.put("ydjFlag", ydjFlag);
			lifeMap.put("currCode", currCode);
			lifeMap.put("userCode", userCode);
			lifeMap.put("flag", flag);
			// 按件数转移
			if (id == 1 || id ==3 ) {
				if(id==3){
					AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(userCode);
					currCode=allotCommon.getOrgNo();
					context.setData("currCode", currCode);
					lifeMap.put("currCode", currCode);
				}
				String transferType =(String) context.getData("transferType")!=null?(String) context.getData("transferType"):"";
				String value = context.getData("queueInfo")!=null?(String) context.getData("queueInfo"):"";
				//新增对转移件数或回收件数统计 按实际需要查询符合条件的申请件
				List<AllotApply> applyList=new ArrayList<AllotApply>();
				int transferNum=transferNum(value);
				applyList=search(context, transferType,transferNum);
				int total=applyList.size();
				if(total>0){
					String[] eums = value.split(";");
					int  bin=0;
					for (String eum : eums) {
						List<String> lifeList=new ArrayList<String>();
						int num = 0;
						if (eum == null || "".equals(eum)) {
							continue;
						}
						String[] chr = eum.split(":");
						String code = chr[0];
						lifeMap.put("code", code);
						num = Integer.parseInt(chr[1].trim());
						int result=0;
						if (num != 0 && num <= total) {
							List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
							for(int i = num - 1; i > -1; i--){
								 AllotApply allotApply=applyList.get(i);
								if("1".equals(ydjFlag)||"".equals(ydjFlag)){//易达金  反欺诈  转移
									String appNo=allotApply.getAppId().substring(0,15);
									lifeMap.put("appId", appNo);
									int appNum = allotApplyService.queryHandCount(lifeMap);
									if(appNum==1){//普通件
										allotApply=allotApplyService.queryHandByAppId(lifeMap);
										String appId2=allotApply.getAppId();
										lifeList.add(appId2);
										AllotApply app=transferCommon(context, allotApply, code);
										allotApplyList.add(app);
									}else if(appNum==2){//套卡件
										List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
										for(AllotApply apply:appList){
											String appId3=apply.getAppId();
											lifeList.add(appId3);
											AllotApply app=transferCommon(context, apply, code);
											allotApplyList.add(app);
										}
									}
								}
								else if("2".equals(ydjFlag)){//标准卡  转移
									lifeList.add(allotApply.getAppId());
									AllotApply app=transferCommon(context, allotApply, code);
									allotApplyList.add(app);
								}
								applyList.remove(i);// 已转移的就从待转移的任务件数中拿掉
							}
							if(allotApplyList.size()>0){
								//二期更改分批次更新
								result=updateBatchList(allotApplyList, lifeList, lifeMap);
								lifeList.clear();
							}
							if(result==0){
								log.info("TransferAction.transferApply 转移申请件异常");
								context.setData("exMsg","转移申请件异常");
								break;
							}else{ 
								bin=1;
								continue;
							}
						}else if(num >total){
							context.setData("exMsg","符合要求的转移申请件少于要转移的申请件数量");
							log.info("TransferAction.transferApply 符合要求的转移申请件少于要转移的申请件数量");
						}
					}
					if(bin==0){
						context.setData("isSuccess",false);
					}else if(bin==1){
						log.info("TransferAction.transferApply 转移成功");
						context.setData("isSuccess",true);
					}
				}else if(total==0){
					log.info("TransferAction.transferApply 要转移的申请件为零");
					context.setData("isSuccess",false);
					context.setData("exMsg","要转移的申请件为零");
				}
			}else if (id == 2 || id==4) {
				String appId = (String) context.getData("appId");
				int count = 0;
				List<String> lifeList=new ArrayList<String>();
				if(appId.trim().length()==15||appId.trim().length()==16){
					int result=0;
					String appNo="";
					if(appId.trim().length()==15){
						appNo=appId.trim().toString().toUpperCase();
					}else if(appId.trim().length()==16){
						appNo=appId.trim().toString().substring(0, 15).toUpperCase();
					}
					//组特定件转移校验申请件是否在该组名下
					String groupNo="";
					if("3".equals(secondNode)){
						AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(userCode);
						if(allotCommon!=null){
							groupNo=StringUtils.trimToEmpty(allotCommon.getOrgNo());
							//当前操作人的组
							lifeMap.put("currGroup", groupNo);
						}
					}
					
					lifeMap.put("appId", appNo);
					count = allotApplyService.queryHandCount(lifeMap);
					if (count == 1) {//普通
						AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
						String currNode=allotApply.getCurrNode();
						String currStatus=allotApply.getCurrStatus();
						String appId4=allotApply.getAppId();
						lifeList.add(appId4);
						//欺诈审批和欺诈调查
						String fraudFlag=StringUtils.trimToEmpty(allotApply.getBatchApprovalFlag());
						if(currNode.equals(node)&&currStatusList.contains(currStatus)&&!"2".equals(fraudFlag)){//申请件是否处于该环节或者可转移状态
							AllotApply app=transferCommon(context, allotApply, currCode);
							//组装数据
							lifeMap.put("lifeList", lifeList);
							lifeMap.put("app", app);
							lifeMap.put("diaoliu", "yes");
							result=allotMethodService.updateMethod(lifeMap);
							lifeList.clear();
							log.info("TransferAction.transferApply"+appId4+"按特定件转移成功");
							if(result!=0){
								context.setData("isSuccess",true);
							}else{
								context.setData("isSuccess",false);
								context.setData("exMsg","转移与其它操作发生冲突,转移失败");
							}
						}else{
							if(currNode.equals(node)&&!currStatusList.contains(currStatus)){
								context.setData("isSuccess",false);
								context.setData("exMsg","要转移的申请件不能在此处转移");
							}else if(!currNode.equals(node)){
								context.setData("isSuccess",false);
								context.setData("exMsg","要转移的申请件不处于该环节");
							}else if("2".equals(fraudFlag)){
								context.setData("isSuccess",false);
								context.setData("exMsg","要转移的申请件在欺诈审批不能通过转移方式转移");
							}
						}
					} else if (count >1) {//套卡
						List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
						for (int i = 0; i < appList.size(); i++) {
							AllotApply allotApply= appList.get(i);
							String appId5=allotApply.getAppId();
							lifeList.add(appId5);
							String currNode=allotApply.getCurrNode();
							String currStatus=allotApply.getCurrStatus();
							//欺诈审批和欺诈调查
							String fraudFlag=StringUtils.trimToEmpty(allotApply.getBatchApprovalFlag());
							if(currNode.equals(node)&&currStatusList.contains(currStatus)&&!"2".equals(fraudFlag)){
								AllotApply app=transferCommon(context, allotApply, currCode);
								lifeMap.put("lifeList", lifeList);
								lifeMap.put("app", app);
								//套卡只有一件要调工作流
								if(i==1){
									lifeMap.put("diaoliu", "yes");
								}
								result=allotMethodService.updateMethod(lifeMap);
								//清除集合数据
								lifeList.clear();
								log.info("TransferAction.transferApply"+appId5+"按特定件转移成功");
								if(result==0){
									context.setData("isSuccess",false);
									context.setData("exMsg","转移失败");
									break;
								}else{
									count--;
									context.setData("isSuccess",true);
								}
							}else{
								if(currNode.equals(node)&&!currStatusList.contains(currStatus)){
									context.setData("isSuccess",false);
									context.setData("exMsg","要转移的申请件不能在此处转移");
								}else if(!currNode.equals(node)){
									context.setData("isSuccess",false);
									context.setData("exMsg","要转移的申请件不处于该环节");
								}else if("2".equals(fraudFlag)){
									context.setData("isSuccess",false);
									context.setData("exMsg","要转移的申请件在欺诈审批不能通过转移方式转移");
								}
								break;
							}
						}
					}else if(count==0){
						int number=0;
						number=allotApplyService.queryHandCount(appNo);
						if(number>0){
							List<AllotApply> appList = allotApplyService.queryApplyListByAppId(appNo);
							AllotApply allotApply=appList.get(0);
							String currNode=allotApply.getCurrNode();
							String curr=allotApply.getCurrStatus();
							String del=allotApply.getDelStatus();
							if(!currNode.equals(node)){
								context.setData("exMsg","该件不处于该环节");
							}else if(!currStatusList.contains(curr)){
								if("1".equals(curr)||"2".equals(curr)){
									context.setData("exMsg","申请件在组里,不能通过此方式转移");
								}else if("3".equals(curr)||"4".equals(curr)){
									context.setData("exMsg","申请件已分到组员,不得通过此方式转移");
								}else  if("0".equals(curr)||"5".equals(curr)){
									context.setData("exMsg","申请件在池里,不能通过此方式转移");
								}else  if("6".equals(curr)||"7".equals(curr)){
									context.setData("exMsg","申请件在队列里,不能通过此方式转移");
								}
							}else if(!"0".equals(del)&&!"2".equals(del)&&!"3".equals(del)){
								context.setData("exMsg","要转移的申请件不符合转移要求。");
							}else{
								context.setData("exMsg","要转移的申请件不在本组内。");
							}
						}else{
							context.setData("exMsg","该件不存在");
						}
					}
			}else{
				log.info("TransferAction.transferApply 要转移的申请件不满足15或16位");
				context.setData("isSuccess",false);
				context.setData("exMsg","要转移的申请件不满足15或16位");
			}
			}
		} catch (Exception e) {
			log.error("TransferAction.transferApply occur error", e);
			context.setData("isSuccess",false);
			context.setData("exMsg","发生异常");
		}
	}
	
	/**
	* @Description: 回收  可跨级
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 * @throws ParseException 
	 */
	public void recoveryApply(Context context) throws ParseException {
		try {
			//回收方式
			int id = Integer.parseInt((String) context.getData("id"));
			String node = (String) context.getData("node");
			int recoveryNode =Integer.parseInt((String) context.getData("recoveryNode"));
			String currCode =(String) context.getData("currCode")!=null?(String) context.getData("currCode"):"";
			String userCode =(String) context.getData("userCode")!=null?(String) context.getData("userCode"):"";
			String secondNode =(String) context.getData("secondNode")!=null?(String) context.getData("secondNode"):"";
			String ydjFlag =(String) context.getData("ydjFlag")!=null?(String) context.getData("ydjFlag"):"";
			String currStatusStr =(String) context.getData("currStatusStr")!=null?(String) context.getData("currStatusStr"):"";
			String flag="5";
			Map<String, Object> lifeMap = new HashMap<String, Object>();
			String transferId=String.valueOf(id);
			String renode=String.valueOf(recoveryNode);
			lifeMap.put("wayId", transferId);
			lifeMap.put("currNode", node);
			lifeMap.put("ydjFlag", ydjFlag);
			lifeMap.put("userCode", userCode);
			lifeMap.put("currCode", currCode);
			lifeMap.put("recoveryNode", renode);
			lifeMap.put("flag", flag);
			if(StringUtils.isNotEmpty(currStatusStr)){//具体队列
				String[] tmp = currStatusStr.split(",");
				List<String> currList = new ArrayList<String>();
				for (int i = 0; i < tmp.length; i++) {
					currList.add(tmp[i]);
				}
				context.setData("currStatusList", currList);
				lifeMap.put("currStatusList", currList);
			}
			List<String> lifeList=new ArrayList<String>();
			// 按件数回收
			if (id == 1 ) {
				String recoveryType =(String) context.getData("recoveryType")!=null?(String) context.getData("recoveryType"):"";
				List<AllotApply> applyList=new ArrayList<AllotApply>();
				int recoveryNum = Integer.parseInt((String) context.getData("recoveryNum"));
				applyList=search(context, recoveryType,recoveryNum);
				int total=applyList.size();
				if(total>0&&recoveryNum != 0 && recoveryNum <= total){
						int result=0;
						//申请件集合
						List<AllotApply> allotApplyList=new ArrayList<AllotApply>();
						for(int i = recoveryNum - 1; i > -1; i--){
							AllotApply allotApply=new AllotApply();
							if("1".equals(ydjFlag)||"".equals(ydjFlag)){//易达金  欺诈  回收  
								String appNo=applyList.get(i).getAppId().substring(0, 15);
								lifeMap.put("appId", appNo);
								int appNum = allotApplyService.queryHandCount(lifeMap);
								if(appNum==1){//普通件
									allotApply=allotApplyService.queryHandByAppId(lifeMap);
									String appId2=allotApply.getAppId();
									lifeList.add(appId2);
									AllotApply app=recoveryCommon(allotApply,recoveryNode,node);
									allotApplyList.add(app);
									
								}else if(appNum==2){//套卡件
									List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
									for(AllotApply apply:appList){
										String appId2=apply.getAppId();
										lifeList.add(appId2);
										AllotApply app=recoveryCommon(apply,recoveryNode,node);
										allotApplyList.add(app);
									}
								}
							}
							else if ("2".equals(ydjFlag)){//标准卡回收
								allotApply=applyList.get(i);
								lifeList.add(allotApply.getAppId());
								AllotApply app=recoveryCommon(allotApply,recoveryNode,node);
								allotApplyList.add(app);
							}
							applyList.remove(i);
						}
						if(allotApplyList.size()>0){
							//二期更改分批次更新
							result=updateBatchList(allotApplyList, lifeList, lifeMap);
							lifeList.clear();
							allotApplyList.clear();
							log.info("TransferAction.recoveryApply  批量回收成功");
						}
						if(result==0){
							context.setData("isSuccess",false);
						}else{
							context.setData("isSuccess",true);
						}
				}else {
					context.setData("exMsg","要回收的申请件数量大于被回收方申请件数量");
					context.setData("isSuccess",false);
				}
			}else if (id == 2) {//按特定件回收
				String appId = (String) context.getData("appId");
				int count = 0;
				if(appId.trim().length()==15||appId.trim().length()==16){
					int result=0;
					String appNo="";
					if(appId.trim().length()==15){
						appNo=appId.trim().toString().toUpperCase();
					}else if(appId.trim().length()==16){
						appNo=appId.trim().toString().substring(0, 15).toUpperCase();
					}
					List<String> currStatusList=new ArrayList<String>();
					if("1".equals(ydjFlag)||"".equals(ydjFlag)){//易达金
						if("1".equals(secondNode)){
							currStatusList.add("1");
						}else if("3".equals(secondNode)){
							if(id==2){
								currStatusList.add("3");
							}else if(id==4){
								currStatusList.add("1");
							}
						}
					}else if("2".equals(ydjFlag)){//标准卡
						if("1".equals(secondNode)&&("01".equals(node)||"03".equals(node))){
							currStatusList.add("1");
						}if("1".equals(secondNode)&&("02".equals(node))){
							currStatusList.add("6");
						}else if("2".equals(secondNode)){
							currStatusList.add("1");
						}else if("3".equals(secondNode)){
							if(id==2){
								currStatusList.add("3");
							}else if(id==4){
								currStatusList.add("1");
							}
						}
					}
					//组特定件转移校验申请件是否在该组名下
					String groupNo="";
					if("3".equals(secondNode)){
						AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(userCode);
						if(allotCommon!=null){
							groupNo=StringUtils.trimToEmpty(allotCommon.getOrgNo());
							//当前操作人的组
							lifeMap.put("currGroup", groupNo);
						}
					}
					lifeMap.put("appId", appNo);
					count = allotApplyService.queryHandCount(lifeMap);
					if (count == 1) {//普通
						AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
						String currNode=allotApply.getCurrNode();
						String currStatus=allotApply.getCurrStatus();
						String appId3=allotApply.getAppId();
						lifeList.add(appId3);
						//欺诈审批和欺诈调查
						String fraudFlag=StringUtils.trimToEmpty(allotApply.getBatchApprovalFlag());
						if(currNode.equals(node)&&currStatusList.contains(currStatus)&&!"2".equals(fraudFlag)){//申请件是否处于该环节或者可回收状态
							AllotApply app=recoveryCommon(allotApply,recoveryNode,node);
							//组装数据
							lifeMap.put("lifeList", lifeList);
							lifeMap.put("app", app);
							result=allotMethodService.updateMethod(lifeMap);
							if(result==0){
								context.setData("exMsg","回收与其它操作发生冲突,回收失败");
							}
							lifeList.clear();
							log.info("TransferAction.recoveryApply"+appId3+ "回收成功");
						}else{
							if(currNode.equals(node)&&!currStatusList.contains(currStatus)){
								context.setData("exMsg","要回收的申请件为挂起状态或该件不能在此处回收");
							}else if(!currNode.equals(node)){
								context.setData("exMsg","要回收的申请件不处于该环节");
							}else if("2".equals(fraudFlag)){
								context.setData("exMsg","要回收的申请件在欺诈审批不能在此处回收");
							}
						}
					} else if (count >1) {//套卡
						List<AllotApply> appList = allotApplyService.queryHand(lifeMap);
						for (int i = 0; i < appList.size(); i++) {
							AllotApply allotApply= appList.get(i);
							String appId3=allotApply.getAppId();
							String currStatus=allotApply.getCurrStatus();
							lifeList.add(appId3);
							String currNode=allotApply.getCurrNode();
							//欺诈审批和欺诈调查
							String fraudFlag=StringUtils.trimToEmpty(allotApply.getBatchApprovalFlag());
							if(currNode.equals(node)&&currStatusList.contains(currStatus)&&!"2".equals(fraudFlag)){//申请件是否处于该环节或者可回收状态
								AllotApply app=recoveryCommon(allotApply,recoveryNode,node) ;
								//组装数据
								lifeMap.put("lifeList", lifeList);
								lifeMap.put("app", app);
								result=allotMethodService.updateMethod(lifeMap);
								lifeList.clear();
								if(result==0){
									context.setData("exMsg","回收失败");
									break;
								}else{
									count--;
								}
								
							}else{
								if(currNode.equals(node)&&!currStatusList.contains(currStatus)){
									context.setData("exMsg","要回收的申请件为挂起状态或该件不能在此处回收");
								}else if(!currNode.equals(node)){
									context.setData("exMsg","要回收的申请件不处于该环节");
								}else if("2".equals(fraudFlag)){
									context.setData("exMsg","要回收的申请件在欺诈审批不能在此处回收");
								}
								break;
							}
						}
					}else{
						context.setData("exMsg","要回收的申请件不在本组内。");
					}
					if(result==0){
						context.setData("isSuccess",false);
					}else {
						context.setData("isSuccess",true);
					}
				}else{
					log.info("TransferAction.recoveryApply"+appId+ "回收申请件不满15或16位");
					context.setData("isSuccess",false);
					context.setData("exMsg","要回收的申请件不满15或16位");
				}
			}
		} catch (Exception e) {
			log.error("TransferAction.recoveryApply occur error", e);
			context.setData("isSuccess",false);
			context.setData("exMsg","回收异常");
		} 
	}
	/**
	* @Description:转移  或回收按件数查询
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 * @throws CoreException 
	 */
	public List<AllotApply> search(Context context,String type,int totalWeight) {
		List<AllotApply> applyList=new ArrayList<AllotApply>();
		try {
			int id = Integer.parseInt((String) context.getData("id"));
			String node =(String) context.getData("node");
			String secondNode =(String) context.getData("secondNode");
			String currCode =(String) context.getData("currCode");
			String ydjFlag =(String) context.getData("ydjFlag");
			String delStatusStr =(String) context.getData("delStatusStr");
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap=context.getData("jsondata");
			//查询方法标识
			if("1".equals(ydjFlag)||"".equals(ydjFlag)){
				dataMap.put("strType","1");
			}else if("2".equals(ydjFlag)){
				dataMap.put("strType","4");
			}
			dataMap.put("secondNode", secondNode);
			dataMap.put("currNode", node);
			dataMap.put("ydjFlag", ydjFlag);
			dataMap.put("delStatusStr", delStatusStr);
			//排序 
			String checkStr="";
			Date date=new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String startDate=sdf.format(date);
			String nowStr="";
			dataMap.put("startDate",startDate);
			List<String> currStatusList=new  ArrayList<String>();
			if("1".equals(secondNode)){
				if("02".equals(node)){//征信
					if("1".equals(ydjFlag)){
						dataMap.put("currGroup",currCode);
						currStatusList.add("1");
						checkStr="allot.GROUP_DATE asc";
					}else if("2".equals(ydjFlag)){
						dataMap.put("currQueue",currCode);
						currStatusList.add("6");
						checkStr="allot.QUEUE_DATE asc";
					}
				}else if("01".equals(node)||"03".equals(node)||"05".equals(node)){
					dataMap.put("currGroup",currCode);
					currStatusList.add("1");
					checkStr="allot.GROUP_DATE asc";
				}
				
			}else if("2".equals(secondNode)&&"02".equals(node)){
				dataMap.put("currGroup",currCode);
				currStatusList.add("1");
				checkStr="allot.GROUP_DATE asc";
			}else if("3".equals(secondNode)){
				if(id==3||id==4){
					dataMap.put("currGroup",currCode);
					currStatusList.add("1");
					checkStr="allot.GROUP_DATE asc";
				}else if(id==1||id==2){
					dataMap.put("currUser",currCode);
					currStatusList.add("3");
					checkStr="allot.USER_DATE asc";
				}
			}
			dataMap.put("checkStr",checkStr);
			dataMap.put("currStatusFlag","true");
			dataMap.put("currStatusList",currStatusList);
			if("1".equals(context.getData("isHangUp"))){//added by jipengchun
				dataMap.put("currStatusList",context.getData("currStatusList"));
			}//added by jipengchun end
			if("1".equals(type)){//当日申请件选中
				if("1".equals(ydjFlag)||"".equals(ydjFlag)){//易达金或者反欺诈 
					if("1".equals(secondNode)){
						if("01".equals(node)){
							nowStr="AND to_char(allot.GROUP_DATE,'yyyy-MM-dd') =#{startDate}";
							//欺诈环节 区分欺诈调查和欺诈审批
							if("".equals(ydjFlag)){
								dataMap.put("fraudStr", "and allot.BATCH_APPROVAL_FLAG in ('0','1') ");
							}
						}else if("02".equals(node)||"03".equals(node)||"05".equals(node)){
							nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
						}
					}else if("3".equals(secondNode)){
						if(id==1){
							nowStr="AND to_char(allot.USER_DATE,'yyyy-MM-dd') =#{startDate}";
						}else if(id==3){
							if("01".equals(node)){
								nowStr="AND to_char(allot.GROUP_DATE,'yyyy-MM-dd') =#{startDate}";
							}else if("02".equals(node)||"03".equals(node)||"05".equals(node)){
								nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
							}
						}
					}
				}else if("2".equals(ydjFlag)){//标准卡
					if("1".equals(secondNode)&&("01".equals(node)||"03".equals(node))){
						if("01".equals(node)){
							nowStr="AND to_char(allot.GROUP_DATE,'yyyy-MM-dd') =#{startDate}";
						}else if("03".equals(node)){
							nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
						}
					}if("1".equals(secondNode)&&("02".equals(node))){
						nowStr="AND to_char(allot.QUEUE_DATE,'yyyy-MM-dd') =#{startDate}";
					}else if("2".equals(secondNode)){
						nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
					}else if("3".equals(secondNode)){
						if(id==1){
							nowStr="AND to_char(allot.USER_DATE,'yyyy-MM-dd') =#{startDate}";
						}else if(id==3){
							if("01".equals(node)){
								nowStr="AND to_char(allot.GROUP_DATE,'yyyy-MM-dd') =#{startDate}";
							}else if("02".equals(node)||"03".equals(node)||"05".equals(node)){
								nowStr="AND to_char(allot.GROUP_TEAM_DATE,'yyyy-MM-dd') =#{startDate}";
							}
						}
					}
				}
			}
			dataMap.put("nowStr",nowStr);
			//分页查询 二期新增
			dataMap.put("rownumFlag", "true");
			//二期优化查询
			applyList=queryApplyList(dataMap, totalWeight);
		}catch (Exception e) {
			log.error("TransferAction.search occur error", e);
		}
		return applyList;
	} 
	/**
	* @Description:转移公共部分
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 * @throws CoreException 
	 */
	public AllotApply transferCommon(Context context,AllotApply allotApply,String code) {
		String node =(String) context.getData("node");
		String secondNode =(String) context.getData("secondNode");
		int id = Integer.parseInt((String) context.getData("id"));
		String delStatus=allotApply.getDelStatus()!=null?allotApply.getDelStatus():"";
		try {
			String ydjFlag= allotApply.getYdjFlag();
			Date sqlDate=new Date();
			allotApply.setLstTeamDate(sqlDate);
			String currName="";
			if ("02".equals(node)&&"2".equals(ydjFlag)&&"1".equals(secondNode)) {// 标准卡征信池 组转移
				allotApply.setCurrQueue(code);
				AllotQueue allotQueue=allotQueueService.queryAllotQueueByCode(code);
				if(allotQueue!=null){
					currName=allotQueue.getQueueName();
				}
				allotApply.setLastQueue(currName);
				allotApply.setQueueDate(sqlDate);
			}else if ("02".equals(node)&&"1".equals(ydjFlag)&&"1".equals(secondNode)) {// 易达金征信池组转移
				allotApply.setCurrGroup(code);
				AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(code);
				if(allotCommon!=null){
					currName=allotCommon.getOrgName();
				}
				allotApply.setLastGroup(currName);
				//分组日期不变
				//allotApply.setGroupDate(sqlDate);
			}else if(("03".equals(node)||"01".equals(node)||"05".equals(node))&&"1".equals(secondNode)){//审查 或审批组转移
				allotApply.setCurrGroup(code);
				AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(code);
				if(allotCommon!=null){
					currName=allotCommon.getOrgName();
				}
				allotApply.setLastGroup(currName);
				if("01".equals(node)){//分组日期
					allotApply.setGroupDate(sqlDate);
				}
				allotApply.setGroupTeamDate(sqlDate);
			}else if("2".equals(secondNode)){//队列里组转移
				allotApply.setCurrGroup(code);
				AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(code);
				if(allotCommon!=null){
					currName=allotCommon.getOrgName();
				}
				allotApply.setLastGroup(currName);
				//分组日期不变
				//allotApply.setGroupDate(sqlDate);
				allotApply.setGroupTeamDate(sqlDate);
			}else if("3".equals(secondNode)){//组页面  组或组员之间转移
				if(id==1||id==2){
					Map<String,Object> userMap=new HashMap<String,Object>();
					userMap.put("userCode", code);
					AllotCommon allotCommon=allotCommonService.queryUser(userMap);
					if(allotCommon!=null){
						currName=allotCommon.getUserName();
					}
					allotApply.setLastUser(allotApply.getCurrUser());
					allotApply.setCurrUser(code);
					allotApply.setCurrUserName(currName);
					allotApply.setUserDate(sqlDate);
				}else if(id==3||id==4){
					AllotCommon allotCommon=allotCommonService.queryGroupByOrgNo(code);
					if(allotCommon!=null){
						currName=allotCommon.getOrgName();
					}
					allotApply.setLastGroup(currName);
					allotApply.setCurrGroup(code);
					allotApply.setGroupTeamDate(sqlDate);
					if("01".equals(node)){//分组日期  征信  审查 审批 不变 记录最原始分组日期
						allotApply.setGroupDate(sqlDate);
					}
				}
			}
			//退回件经转移变为未完成
			if("3".equals(delStatus)){
				allotApply.setDelStatus("0");
			}
		}catch (Exception e) {
			log.error("TransferAction.transferCommon occur error", e);
		}
		return allotApply;
	} 
	/**
	* @Description:回收公共部分
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 * @throws CoreException 
	 */
	public AllotApply recoveryCommon(AllotApply allotApply,int recoveryNode,String currNode) {
		String delStatus=allotApply.getDelStatus()!=null?allotApply.getDelStatus():"";
		try {
			Date sqlDate=new Date();
			allotApply.setLstTeamDate(sqlDate);
			allotApply.setCurrNode(currNode);
			if(recoveryNode==1){//申请回收到池
				allotApply.setCurrStatus("0");
				allotApply.setCurrQueue(null);
				allotApply.setLastQueue(null);
				allotApply.setCurrGroup(null);
				allotApply.setLastGroup(null);
				allotApply.setCurrUser(null);
				allotApply.setCurrUserName(null);
				allotApply.setQueueDate(null);
				allotApply.setGroupTeamDate(null);
				//审查  分组时间可变 征信   欺诈  审批 不变 记录最初始分组日期
				if("01".equals(currNode)){
					allotApply.setGroupDate(null);
				}
				allotApply.setUserDate(null);
			}else if(recoveryNode==2){//队列
				allotApply.setCurrGroup(null);
				allotApply.setLastGroup(null);
				allotApply.setCurrUser(null);
				allotApply.setCurrUserName(null);
				//记录最初始分组日期
				//allotApply.setGroupDate(null);
				allotApply.setUserDate(null);
				allotApply.setCurrStatus("6");
			}else if(recoveryNode==3){//组
				allotApply.setCurrUser(null);
				allotApply.setCurrUserName(null);
				allotApply.setUserDate(null);
				allotApply.setCurrStatus("1");
			}
			//退回件经回收变为未完成
			if("3".equals(delStatus)){
				allotApply.setDelStatus("0");
			}
			//征审合一件征信可以回收变为普通件  审批环节不可以
			if("02".equals(currNode)){
				allotApply.setCheck_meuoFlag("1");
			}
			
		}catch (Exception e) {
			log.error("TransferAction.recoveryCommon occur error", e);
		}
		return allotApply;
	} 
	/**
	 * 批量批次更新
	 * @param list、list2
	 * @param lifeMap
	 * @return
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
	 * 数量统计
	 * @param value
	 * @return
	 */
	public int transferNum(String value) throws Exception{
		//totalWeight-本次转移总共需要的件数量
		int totalWeight=0;
		String[] eums = value.split(";");
		if(eums.length!=0){
			for(int i=0;i<eums.length;i++){
				String pingEum=eums[i];
				String[] pingChr = pingEum.split(":");
				totalWeight=totalWeight+Integer.parseInt(pingChr[1].trim());
			}
		}
		return totalWeight;
	}
	/**
	 * totalWeight-本次转移或回收总共需要的件数量 
	 * @param dataMap  
	 * @return
	 */
	public List<AllotApply> queryApplyList(Map<String, Object> dataMap,int totalWeight) throws Exception{
		//开始查询和结束查询数量
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
		}else{
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
	public void fnChaXunId(Context context) throws Exception{
		try {
			Map<String, Object> dataMap=context.getDataMap(); 
			int id = Integer.parseInt((String) context.getData("id"));
			String currNode =StringUtils.trimToEmpty((String)context.getData("currNode"));
			String secondNode =StringUtils.trimToEmpty((String)context.getData("secondNode"));
			String currCode =StringUtils.trimToEmpty((String)context.getData("currCode"));
			String userCode =StringUtils.trimToEmpty((String)context.getData("userCode"));
			String ydjFlag =StringUtils.trimToEmpty((String)context.getData("ydjFlag"));
			String isHangUp =StringUtils.trimToEmpty((String)context.getData("isHangUp"));
			if(id==3){
				AllotCommon allotCommon=allotCommonService.queryGroupByUserCode(userCode);
				currCode=allotCommon.getOrgNo();
			}
			String checkStr="";
			List<String> currStatusList=new  ArrayList<String>();
			if("1".equals(secondNode)){
				if("02".equals(currNode)){//征信
					if("1".equals(ydjFlag)){
						dataMap.put("currGroup",currCode);
						currStatusList.add("1");
						checkStr="allot.GROUP_DATE asc";
					}else if("2".equals(ydjFlag)){
						dataMap.put("currQueue",currCode);
						currStatusList.add("6");
						checkStr="allot.QUEUE_DATE asc";
					}
				}else if("01".equals(currNode)||"03".equals(currNode)||"05".equals(currNode)){
					dataMap.put("currGroup",currCode);
					currStatusList.add("1");
					checkStr="allot.GROUP_DATE asc";
				}
				
			}else if("2".equals(secondNode)&&"02".equals(currNode)){
				dataMap.put("currGroup",currCode);
				currStatusList.add("1");
				checkStr="allot.GROUP_DATE asc";
			}else if("3".equals(secondNode)){
				if(id==3||id==4){
					dataMap.put("currGroup",currCode);
					currStatusList.add("1");
					checkStr="allot.GROUP_DATE asc";
				}else if(id==1||id==2){
					dataMap.put("currUser",currCode);
					currStatusList.add("3");
					checkStr="allot.USER_DATE asc";
				}
			}
			if("1".equals(isHangUp)){//转移情况
				currStatusList=getCurrStatusList(context,"1");
			}
			dataMap.put("currStatusList",currStatusList);
			dataMap.put("checkStr",checkStr);
			dataMap.put("currStatusFlag","true");
			Map<String, Object> map =allotApplyService.saveApplyByConditions(dataMap);
			context.setData("total", map.get("allResult"));
			context.setData("isSuccess", true);
		} catch (Exception e) {
			log.info("fnChaXun转移或回收条件查询报错"+e.getMessage());
		}
	}
	public List<String> getCurrStatusList(Context context,String strId) throws Exception{
		List<String> currStatusList=new  ArrayList<String>();
		Map<String, Object> dataMap=null;
		try {
			if("1".equals(strId)){
				dataMap=context.getDataMap();  
			}else if("2".equals(strId)){
				dataMap = context.getData("jsondata"); 
			}
			int id = Integer.parseInt((String) context.getData("id"));
			String currNode =StringUtils.trimToEmpty((String)context.getData("currNode"));
			String secondNode =StringUtils.trimToEmpty((String)context.getData("secondNode"));
			String ydjFlag =StringUtils.trimToEmpty((String)context.getData("ydjFlag"));
			String appStatus2="",currStatusStr="",delStatusStr="";
			if(dataMap!=null){
				appStatus2=StringUtils.trimToEmpty((String) dataMap.get("appStatus2"));
				currStatusStr=StringUtils.trimToEmpty((String) dataMap.get("currStatusStr"));//组员转移或回收
				delStatusStr=StringUtils.trimToEmpty((String) dataMap.get("delStatusStr"));//组员转移或回收
			}
			if("1".equals(secondNode)){//池环节
				if("02".equals(currNode)&&"1".equals(ydjFlag)&&!"24".equals(appStatus2)){//易达金征信环节挂起标签未选中
						currStatusList.add("1");
						currStatusList.add("2");
				}else if("02".equals(currNode)&&"1".equals(ydjFlag)&&"24".equals(appStatus2)){//易达金征信环节挂起标签选中
						currStatusList.add("1");
				}else if("02".equals(currNode)&&"2".equals(ydjFlag)&&!"24".equals(appStatus2)){//标准卡征信环节挂起标签未选中
						currStatusList.add("6");
						currStatusList.add("7");
				}else if("02".equals(currNode)&&"2".equals(ydjFlag)&&"24".equals(appStatus2)){//标准卡征信环节挂起标签选中
					currStatusList.add("7");
				}else if(("01".equals(currNode)||"03".equals(currNode)||"05".equals(currNode))
						&&!"24".equals(appStatus2)){//审查、审批、反欺诈挂起标签未选中
					currStatusList.add("1");
					currStatusList.add("2");
				}else if(("01".equals(currNode)||"03".equals(currNode)||"05".equals(currNode))
						&&"24".equals(appStatus2)){//审查、审批、反欺诈挂起标签选中
					currStatusList.add("2");
				}
			}else if("2".equals(secondNode)&&"02".equals(currNode)&&!"24".equals(appStatus2)){//标准卡风险队列挂起标签未选中
				currStatusList.add("1");
				currStatusList.add("2");
			}else if("2".equals(secondNode)&&"02".equals(currNode)&&"24".equals(appStatus2)){//标准卡风险队列挂起标签选中
				currStatusList.add("2");
			}else if("3".equals(secondNode)){//组环节
				if((id==3||id==4)&&!"24".equals(appStatus2)){//组转移或回收挂起标签未选中
					currStatusList.add("1");
					currStatusList.add("2");
				}else if((id==3||id==4)&&"24".equals(appStatus2)){//组转移或回收挂起标签选中
					currStatusList.add("2");
				}else if((id==1||id==2)&&!"24".equals(appStatus2)
						&&"".equals(currStatusStr)&&"".equals(delStatusStr)){//组员转移或回收挂起标签未选中、队列未选值
					currStatusList.add("3");
					currStatusList.add("4");
				}else if((id==1||id==2)&&!"24".equals(appStatus2)
						&&"3".equals(currStatusStr)&&!"".equals(delStatusStr)){//组员转移或回收挂起标签未选中、队列（未完成、补件、退回）选值
					currStatusList.add("3");
				}else if((id==1||id==2)&&("24".equals(appStatus2)||"4".equals(currStatusStr))){//组员转移或回收挂起标签选中或队列为已挂起队列
					currStatusList.add("4");
				}
			}
		} catch (Exception e) {
			log.info("getCurrStatusList获取分配标识报错"+e.getMessage());
		}
		return currStatusList;
	}
}
