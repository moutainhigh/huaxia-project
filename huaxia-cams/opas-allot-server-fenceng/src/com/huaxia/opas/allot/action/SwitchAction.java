package com.huaxia.opas.allot.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.common.util.UUIDGenerator;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotSwitch;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.allot.AllotMappingRuleService;
import com.huaxia.opas.service.allot.AllotQueueService;
import com.huaxia.opas.service.allot.AllotRuleService;
import com.huaxia.opas.service.allot.AllotSwitchService;
/**
 * 申请件分配  分件开关   控制器
 * @author 王德彬
 *
 */
public class SwitchAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(SwitchAction.class);
	
	@Resource(name="allotApplyService")
	private AllotApplyService allotApplyService;

	@Resource(name="allotMappingRuleService")
	private AllotMappingRuleService allotMappingRuleService;
	
	@Resource(name="allotQueueService")
	private AllotQueueService allotQueueService;
	
	@Resource(name="allotRuleService")
	private AllotRuleService allotRuleService;
	
	@Resource(name="allotCommonService")
	private AllotCommonService allotCommonService;
	
	@Resource(name = "allotSwitchService")
	private AllotSwitchService allotSwitchService;
	
	private static String OPERATOR = "and";
	/**
	* @Description: 分件开关 保存或者更新
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void saveOrUpdateSwitch(Context context){
		log.info("SwitchAction.saveOrUpdateSwitch Enter  进入自动分件开关保存或更新操作");
		//判断标卡还是易达金  switchCode 标卡  1  全局 (2 队列  ) 3 组  易达金  4 全局   5组  
		String switchCode=(String)context.getData("switchCode")==null?"":(String)context.getData("switchCode");
		String switchStatus=(String)context.getData("switchStatus")==null?"":(String)context.getData("switchStatus");
		AllotSwitch allotSwitch =new AllotSwitch();
		try {
			int count=0;
			int result=0;
			count=allotSwitchService.countAllotSwitch(switchCode);
			if(count==0){//不存在  插入 
				allotSwitch.setSwitchId(UUIDGenerator.getUUID());
				allotSwitch.setSwitchCode(switchCode);
				allotSwitch.setSwitchStatus(switchStatus);
				if("1".equals(switchCode)){
					allotSwitch.setSwitchName("标卡全局自动开关");
				}else if("2".equals(switchCode)){
					allotSwitch.setSwitchName("标卡队列自动开关");
				}else if("3".equals(switchCode)){
					allotSwitch.setSwitchName("标卡组自动开关");
				}else if("4".equals(switchCode)){
					allotSwitch.setSwitchName("易达金全局自动开关");
				}else if("5".equals(switchCode)){
					allotSwitch.setSwitchName("易达金组自动开关");
				}else if("A".equals(switchCode)){
					allotSwitch.setSwitchName("标卡录入审查批量开关");
				}else if("B".equals(switchCode)){
					allotSwitch.setSwitchName("标卡征信批量开关");
				}else if("C".equals(switchCode)){
					allotSwitch.setSwitchName("标卡审批批量开关");
				}else if("D".equals(switchCode)){
					allotSwitch.setSwitchName("易达金征信批量开关");
				}else if("E".equals(switchCode)){
					allotSwitch.setSwitchName("易达金审批批量开关");
				}
				result=allotSwitchService.saveAllotSwitch(allotSwitch);
			}else if(count>0){//已经存在  需修改
				allotSwitch.setSwitchStatus(switchStatus);
				allotSwitch.setSwitchCode(switchCode);
				result=allotSwitchService.updateAllotSwitch(allotSwitch);
			}
			if(result!=0){
				context.setData("isSuccess", "true");
			}else{
				context.setData("isSuccess", "false");
			}
		} catch (CoreException e) {
			log.error("SwitchAction.updateAllotSwitch occur error"+e);
			context.setData("isSuccess", "false");
		}
	}
	/**
	* @Description: 分件开关  查询返回前台 
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void queryAllotSwitch(Context context) {
		AllotSwitch allotSwitch= new AllotSwitch();
		String switchStatus="";
		String switchStatus2="";
		
		String switchStatusA="";
		String switchStatusB="";
		String switchStatusC="";
		String switchStatusD="";
		String switchStatusE="";
		try {
			String ydjFlag=(String)context.getData("ydjFlag")==null?"":(String)context.getData("ydjFlag");
			String switchCode="";
			String switchCode2="";
			
			String switchCodeA="";
			String switchCodeB="";
			String switchCodeC="";
			String switchCodeD="";
			String switchCodeE="";
			if("1".equals(ydjFlag)){//易达金
				switchCode="4";
				switchCode2="5";
				switchCodeD="D";
				switchCodeE="E";
			}else if("2".equals(ydjFlag)){//标准卡 
				switchCode="1";
				switchCode2="3";
				switchCodeA="A";
				switchCodeB="B";
				switchCodeC="C";
			}
			allotSwitch= allotSwitchService.queryAllotSwitchByCode(switchCode);
			if(allotSwitch!=null){
				switchStatus=allotSwitch.getSwitchStatus();
			}else {
				//不存在  关闭状态
				switchStatus="0";
			}
			AllotSwitch allotSwitch2=allotSwitchService.queryAllotSwitchByCode(switchCode2);
			if(allotSwitch2!=null){
				switchStatus2=allotSwitch2.getSwitchStatus();
			}else{
				//不存在  关闭状态
				switchStatus2="0";
			}
			
			/* 标卡和易达金批量提交开关-wenyh */
			//录入审查批量开关
			AllotSwitch allotSwitchA=allotSwitchService.queryAllotSwitchByCode(switchCodeA);
			if(allotSwitchA!=null){
				switchStatusA=allotSwitchA.getSwitchStatus();
			}else{
				//不存在  关闭状态
				switchStatusA="0";
			}
			//征信批量开关
			AllotSwitch allotSwitchB=allotSwitchService.queryAllotSwitchByCode(switchCodeB);
			if(allotSwitchB!=null){
				switchStatusB=allotSwitchB.getSwitchStatus();
			}else{
				//不存在  关闭状态
				switchStatusB="0";
			}
			//审批批量开关
			AllotSwitch allotSwitchC=allotSwitchService.queryAllotSwitchByCode(switchCodeC);
			if(allotSwitchC!=null){
				switchStatusC=allotSwitchC.getSwitchStatus();
			}else{
				//不存在  关闭状态
				switchStatusC="0";
			}
			//征信批量开关
			AllotSwitch allotSwitchD=allotSwitchService.queryAllotSwitchByCode(switchCodeD);
			if(allotSwitchD!=null){
				switchStatusD=allotSwitchD.getSwitchStatus();
			}else{
				//不存在  关闭状态
				switchStatusD="0";
			}
			//审批批量开关
			AllotSwitch allotSwitchE=allotSwitchService.queryAllotSwitchByCode(switchCodeE);
			if(allotSwitchE!=null){
				switchStatusE=allotSwitchE.getSwitchStatus();
			}else{
				//不存在  关闭状态
				switchStatusE="0";
			}
			
			context.setData("switchStatus", "switchStatus");
			context.setData("switchStatus2", "switchStatus2");
		} catch (CoreException e) {
			log.error("SwitchAction.queryAllotSwitch occur error", e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		 dataMap.put("switchStatus", switchStatus);
		 dataMap.put("switchStatus2", switchStatus2);
		 
		 dataMap.put("switchStatusA", switchStatusA);
		 dataMap.put("switchStatusB", switchStatusB);
		 dataMap.put("switchStatusC", switchStatusC);
		 dataMap.put("switchStatusD", switchStatusD);
		 dataMap.put("switchStatusE", switchStatusE);
		 context.setDataMap(dataMap);
	}
	/**
	* @Description: 分件开关  更新
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void updateAllotSwitch(Context context){
		if (log.isInfoEnabled()) {
			log.info("SwitchAction.updateAllotSwitch Enter");
			log.info(context.toString());
		}
		String switchId=(String)context.getData("switchId");
		String switchCode=(String)context.getData("switchCode");
		String switchStatus=(String)context.getData("switchStatus");
		AllotSwitch allotSwitch =new AllotSwitch();
		allotSwitch.setSwitchId(switchId);
		allotSwitch.setSwitchStatus(switchStatus);
		try {
			if(!"0".equals(switchCode)){//判断全局位置否
				String switchCode2="0";
				AllotSwitch allotSwitch2 = allotSwitchService.queryAllotSwitchByCode(switchCode2);
				if("1".equals(allotSwitch2.getSwitchStatus())){//判断全局模式自动  
					allotSwitchService.updateAllotSwitch(allotSwitch);
				}else{
					context.setData("errorMsg", "分件模式总开关为手动模式,需先将总开关改为自动模式");
					System.out.println("分件模式总开关为手动模式,需先将总开关改为自动模式");
				}
			}else{//全局是
				allotSwitchService.updateAllotSwitch(allotSwitch);
			}
		} catch (CoreException e) {
			log.error("SwitchAction.updateAllotSwitch occur error"+e);
		}
	}
	
	/**
	 * 检测批量提交开关方法-wenyh
	 * @param context
	 */
	public void checkBatchSwitch(Context context){
		AllotSwitch allotSwitch= new AllotSwitch();
		String switchStatus="";
		try {
			String node = (String)context.getData("node")==null?"":(String)context.getData("node");
			String ydjFlag = (String)context.getData("ydjFlag")==null?"":(String)context.getData("ydjFlag");
			String switchCode = "";
			if("1".equals(ydjFlag)){//易达金
				if("02".equals(node)){//征信
					switchCode="D";
				}else if("03".equals(node)){//审批
					switchCode="E";
				}
			}else if("2".equals(ydjFlag)){//标准卡 
				if("01".equals(node)){//录入审查
					switchCode="A";
				}else if("02".equals(node)){//征信
					switchCode="B";
				}else if("03".equals(node)){//审批
					switchCode="C";
				}
			}
			allotSwitch= allotSwitchService.queryAllotSwitchByCode(switchCode);
			if(allotSwitch!=null){
				switchStatus=allotSwitch.getSwitchStatus();
			}else {
				//不存在  关闭状态
				switchStatus="0";
			}
			
			context.setData("switchStatus", "switchStatus");
		} catch (CoreException e) {
			log.error("SwitchAction.queryAllotSwitch occur error" + e);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		 dataMap.put("switchStatus", switchStatus);
		 context.setDataMap(dataMap);
	}
}
