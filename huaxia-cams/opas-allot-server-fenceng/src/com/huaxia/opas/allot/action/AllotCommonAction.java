package com.huaxia.opas.allot.action;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.allot.AllotApply;
import com.huaxia.opas.domain.allot.AllotApplyAllot;
import com.huaxia.opas.domain.allot.AllotCommon;
import com.huaxia.opas.domain.apply.ApplyRemark;
import com.huaxia.opas.service.allot.AllotApplyHisService;
import com.huaxia.opas.service.allot.AllotApplyService;
import com.huaxia.opas.service.allot.AllotCommonService;
import com.huaxia.opas.service.allot.AllotMethodService;
import com.huaxia.opas.service.apply.ApplyLifeCicleService;
import com.huaxia.opas.service.apply.ApplyRemarkService;
import com.huaxia.opas.service.decision.SysButtonCommonService;
/**
 * 申请件 挂起  解挂  备注
 * @author 王德彬
 * @version v1.1(初始v1.0)
 * @history 修改历史记录
 * ------------------------------------------------
 *  2017-10-17  修复池中挂起或释放操作员申请件的问题
 * ------------------------------------------------
 */
public class AllotCommonAction implements Action {
	private static final Logger log = LoggerFactory.getLogger(AllotCommonAction.class);
	
	@Resource(name="allotApplyService")
	private AllotApplyService allotApplyService;
	
	@Resource(name="applyRemarkService")
	private ApplyRemarkService applyRemarkService;
	
	@Resource(name="allotApplyHisService")
	private AllotApplyHisService allotApplyHisService;
	
	@Resource(name = "applyLifeCicleService")
	private ApplyLifeCicleService applyLifeCicleService;
	
	@Resource(name = "allotCommonService")
	private AllotCommonService allotCommonService;
	
	@Resource(name = "allotMethodService")
	private AllotMethodService allotMethodService;
	@Resource(name="sysButtonCommonService")
	private SysButtonCommonService sysButtonCommonService;
	/**
	* @Description: 申请件  挂起  未分配挂起  已分配挂起
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void handUp(Context context)  {
		try {
			Map<String,Object> map = context.getDataMap();
			List<String> applyList=(List<String>) map.get("list");
			List<String> applyList2=new ArrayList<String>();
			applyList2.addAll(applyList);
			String node=(String)map.get("node")!=null?(String)map.get("node"):"";
			String secondNode=(String)map.get("secondNode")!=null?(String)map.get("secondNode"):"";
			String ydjFlag=(String)map.get("ydjFlag")!=null?(String)map.get("ydjFlag"):"";
			String userCode=(String)map.get("userCode")!=null?(String)map.get("userCode"):"";
			String rs=(String)map.get("remarkInfo")!=null?(String)map.get("remarkInfo"):"";
			List<AllotApply> appList=new ArrayList<AllotApply>();
			List<ApplyRemark> remarkList=new ArrayList<ApplyRemark>();
			String flag="1",appId="", taoId="";;
			Map<String, Object> lifeMap = new HashMap<String, Object>();
			lifeMap.put("currNode", node);
			lifeMap.put("secondNode", secondNode);
			lifeMap.put("ydjFlag", ydjFlag);
			lifeMap.put("userCode", userCode);
			lifeMap.put("applyList", applyList);
			lifeMap.put("flag", flag);
			int result=0;
			//二期 挂起备注
			if(applyList2.size()>0){
				StringBuffer remarkInfo=new StringBuffer("");
				if("1".equals(ydjFlag)&&"02".equals(node)&&"1".equals(secondNode)){
					remarkInfo.append("易达金征信池列表挂起备注："+rs);
				}else if("1".equals(ydjFlag)&&"02".equals(node)&&"3".equals(secondNode)){
					remarkInfo.append("易达金征信组列表挂起备注："+rs);
				}else if("1".equals(ydjFlag)&&"03".equals(node)&&"1".equals(secondNode)){
					remarkInfo.append("易达金审批池列表挂起备注："+rs);
				}else if("1".equals(ydjFlag)&&"03".equals(node)&&"3".equals(secondNode)){
					remarkInfo.append("易达金审批组列表挂起备注："+rs);
				}else if("2".equals(ydjFlag)&&"01".equals(node)&&"1".equals(secondNode)){
					remarkInfo.append("标准卡审查池列表挂起备注："+rs);
				}else if("2".equals(ydjFlag)&&"01".equals(node)&&"3".equals(secondNode)){
					remarkInfo.append("标准卡审查组列表挂起备注："+rs);
				}else if("2".equals(ydjFlag)&&"02".equals(node)&&"1".equals(secondNode)){
					remarkInfo.append("标准卡征信池列表挂起备注："+rs);
				}else if("2".equals(ydjFlag)&&"02".equals(node)&&"2".equals(secondNode)){
					remarkInfo.append("标准卡征信队列列表挂起备注："+rs);
				}else if("2".equals(ydjFlag)&&"02".equals(node)&&"3".equals(secondNode)){
					remarkInfo.append("标准卡征信组列表挂起备注："+rs);
				}else if("2".equals(ydjFlag)&&"03".equals(node)&&"1".equals(secondNode)){
					remarkInfo.append("标准卡审批池列表挂起备注："+rs);
				}else if("2".equals(ydjFlag)&&"03".equals(node)&&"3".equals(secondNode)){
					remarkInfo.append("标准卡审批组列表挂起备注："+rs);
				}else if("".equals(ydjFlag)&&"05".equals(node)&&"1".equals(secondNode)){
					remarkInfo.append("反欺诈池列表挂起备注："+rs);
				}else if("".equals(ydjFlag)&&"05".equals(node)&&"3".equals(secondNode)){
					remarkInfo.append("反欺诈组列表挂起备注："+rs);
				}
				int num=applyList2.size();
				while(num>0){
					appId=applyList2.get(num-1).trim();
					//查询当前环节当前状态申请件
					ApplyRemark remark=new ApplyRemark();
					remark.setAppId(appId);
					remark.setRemarkInfo(remarkInfo.toString());
					remark.setRemarkUser(userCode);
					remark.setRemarkTime(new Date());
					remark.setCurrNode(node);
					remarkList.add(remark);
					applyList2.remove(num-1);
					num--;
					//查询是否有套卡
					if(appId.endsWith("2")){
						taoId = appId.substring(0,15)+"1";
					}else if(appId.endsWith("1")){
						taoId=appId.substring(0,15)+"2";
					}
					AllotApplyAllot allot_fk=sysButtonCommonService.queryAllotApplyAllot(taoId);
					if(allot_fk!=null){
						ApplyRemark remark2=new ApplyRemark();
						BeanUtils.copyProperties(remark, remark2);
						remark2.setAppId(taoId);
						remarkList.add(remark2);
						if(applyList2.contains(taoId)){//两者都有
							applyList2.remove(taoId);
							num--;
						}
					}
				}
			}
			if(applyList.size()>0){
				List<String> lifeList=new ArrayList<String>();
				if("1".equals(secondNode)){//池中  (挂起池中或者队列  或者组 、组员 的件)
					int sum=applyList.size();
					while(sum>0){
						appId=applyList.get(sum-1).trim();
						String appNo=appId.substring(0,15);
						lifeMap.put("appId", appNo);
						//查询当前环节当前状态申请件
						int appNum = allotApplyService.queryHandCount(lifeMap);
						if(appNum==1){
							lifeList.add(appId);
							AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
							String currStatus=allotApply.getCurrStatus();
							if("0".equals(currStatus)){//挂起池中的件
								allotApply.setCurrStatus("5");
							}else if("6".equals(currStatus)){//挂起队列中的件
								allotApply.setCurrStatus("7");
							}else if("1".equals(currStatus)){//挂起组中的件
								allotApply.setCurrStatus("2");
							}else if("3".equals(currStatus)){//挂起组员中的件
								allotApply.setCurrStatus("4");
							}
							appList.add(allotApply);
							applyList.remove(sum-1);
							sum--;
						}else if(appNum==2){
							List<AllotApply> allotAppList = allotApplyService.queryHand(lifeMap);
							for(int j=0;j<allotAppList.size();j++){
								AllotApply allotApply=allotAppList.get(j);
								taoId=allotApply.getAppId();
								lifeList.add(taoId);
								String currStatus=allotApply.getCurrStatus();
								if("0".equals(currStatus)){//挂起池中的件
									allotApply.setCurrStatus("5");
								}else if("6".equals(currStatus)){//挂起队列中的件
									allotApply.setCurrStatus("7");
								}else if("1".equals(currStatus)){//挂起组中的件
									allotApply.setCurrStatus("2");
								}else if("3".equals(currStatus)){//挂起组员中的件
									allotApply.setCurrStatus("4");
								}
								appList.add(allotApply);
								if(applyList.contains(taoId)){
									applyList.remove(sum-1);
									sum--;
								}
							}
						}else{//申请件已人工提交不在当前环节
							applyList.remove(sum-1);
							sum--;
						}
					}
				}else if("2".equals(secondNode)){//队列中(挂起队列  或组、组员的件)
					for(int i=0;i<applyList.size();i++){
						appId=applyList.get(i);
						lifeMap.put("appId", appId);
						lifeList.add(appId);
						AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
						String currStatus=allotApply.getCurrStatus();
						if("6".equals(currStatus)){
							allotApply.setCurrStatus("7");
						}else if("1".equals(currStatus)){
							allotApply.setCurrStatus("2");
						}else if("3".equals(currStatus)){
							allotApply.setCurrStatus("4");
						}
						appList.add(allotApply);
					}
				}else if("3".equals(secondNode)){//组中
						int sum=applyList.size();
						while(sum>0){
							appId=applyList.get(sum-1).trim();
							String appNo=appId.substring(0,15);
							lifeMap.put("appId", appNo);
							//查询当前环节当前状态申请件
							int appNum = allotApplyService.queryHandCount(lifeMap);
							if(appNum==1){
								lifeList.add(appId);
								AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
								if("1".equals(allotApply.getCurrStatus())){
									allotApply.setCurrStatus("2");
								}else if("3".equals(allotApply.getCurrStatus())){
									allotApply.setCurrStatus("4");
								}
								appList.add(allotApply);
								applyList.remove(sum-1);
								sum--;
							}else if(appNum==2){
								List<AllotApply> allotAppList = allotApplyService.queryHand(lifeMap);
								for(int j=0;j<allotAppList.size();j++){
									AllotApply allotApply=allotAppList.get(j);
									taoId=allotApply.getAppId();
									lifeList.add(taoId);
									if("1".equals(allotApply.getCurrStatus())){
										allotApply.setCurrStatus("2");
									}else if("3".equals(allotApply.getCurrStatus())){
										allotApply.setCurrStatus("4");
									}
									appList.add(allotApply);
									if(applyList.contains(taoId)){
										applyList.remove(sum-1);
										sum--;
									}
								}
							}else{//申请件已人工提交不在当前环节
								applyList.remove(sum-1);
								sum--;
							}
						}
				}
				//组装数据  审批二期新增挂起备注
				lifeMap.put("remarkList",remarkList);
				lifeMap.put("allotApplyList",appList );
				lifeMap.put("lifeList", lifeList);
				result=allotMethodService.updateBatchMethod(lifeMap);
				if(result==0){
					context.setData("isSuccess",false);
				}else{
					context.setData("isSuccess",true);
				}
			}else{
				context.setData("isSuccess",false);
				context.setData("exMsg","未选择申请件");
			}
		} catch (Exception e) {
			log.error("AllotCommonAction.handUp occur error", e);
			context.setData("isSuccess",false);
			context.setData("exMsg","挂起失败");
		}
	}
	/**
	* @Description: 申请件  解挂
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void release(Context context) {
		try {
			Map<String,Object> map = context.getDataMap();
			List<String> applyList=(List<String>) map.get("list");
			String node=(String)map.get("node")!=null?(String)map.get("node"):"";
			String secondNode=(String)map.get("secondNode")!=null?(String)map.get("secondNode"):"";
			String ydjFlag=(String)map.get("ydjFlag")!=null?(String)map.get("ydjFlag"):"";
			String userCode=(String)map.get("userCode")!=null?(String)map.get("userCode"):"";
			List<AllotApply> appList=new ArrayList<AllotApply>();
			String flag="2",appId="",taoId="";
			Map<String, Object> lifeMap = new HashMap<String, Object>();
			lifeMap.put("currNode", node);
			lifeMap.put("secondNode", secondNode);
			lifeMap.put("ydjFlag", ydjFlag);
			lifeMap.put("userCode", userCode);
			lifeMap.put("applyList", applyList);
			lifeMap.put("flag", flag);
			int result=0;
			if(applyList.size()>0){
				List<String> lifeList=new ArrayList<String>();
				if("1".equals(secondNode)){//池中  (释放池中、队列 或者组、组员中的件)
					int sum=applyList.size();
					while(sum>0){
						appId=applyList.get(sum-1).trim();
						String appNo=appId.substring(0,15);
						lifeMap.put("appId", appNo);
						//查询当前环节当前状态申请件
						int appNum = allotApplyService.queryHandCount(lifeMap);
						if(appNum==1){
							lifeList.add(appId);
							AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
							String currStatus=allotApply.getCurrStatus();
							if("5".equals(currStatus)){
								allotApply.setCurrStatus("0");
							}else if("7".equals(currStatus)){
								allotApply.setCurrStatus("6");
							}else if("2".equals(currStatus)){
								allotApply.setCurrStatus("1");
							}else if("4".equals(currStatus)){
								allotApply.setCurrStatus("3");
							}
							appList.add(allotApply);
							applyList.remove(sum-1);
							sum--;
						}else if(appNum==2){
							List<AllotApply> allotAppList = allotApplyService.queryHand(lifeMap);
							for(int j=0;j<allotAppList.size();j++){
								AllotApply allotApply=allotAppList.get(j);
								taoId=allotApply.getAppId();
								lifeList.add(taoId);
								String currStatus=allotApply.getCurrStatus();
								if("5".equals(currStatus)){
									allotApply.setCurrStatus("0");
								}else if("7".equals(currStatus)){
									allotApply.setCurrStatus("6");
								}else if("2".equals(currStatus)){
									allotApply.setCurrStatus("1");
								}else if("4".equals(currStatus)){
									allotApply.setCurrStatus("3");
								}
								appList.add(allotApply);
								if(applyList.contains(taoId)){
									applyList.remove(sum-1);
									sum--;
								}
							}
						}else{//申请件已人工提交不在当前环节
							applyList.remove(sum-1);
							sum--;
						}
					}
				}else if("2".equals(secondNode)){//队列中
					for(int i=0;i<applyList.size();i++){
						 appId=applyList.get(i);
						lifeMap.put("appId", appId);
						lifeList.add(appId);
						AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
						String currStatus=allotApply.getCurrStatus();
						if("7".equals(currStatus)){
							allotApply.setCurrStatus("6");
						}else if("2".equals(currStatus)){
							allotApply.setCurrStatus("1");
						}else if("4".equals(currStatus)){
							allotApply.setCurrStatus("3");
						}
						appList.add(allotApply);
					}
				}else if("3".equals(secondNode)){//组中
						int sum=applyList.size();
						while(sum>0){
							appId=applyList.get(sum-1).trim();
							String appNo=appId.substring(0,15);
							lifeMap.put("appId", appNo);
							//查询当前环节当前状态申请件
							int appNum = allotApplyService.queryHandCount(lifeMap);
							if(appNum==1){
								lifeList.add(appId);
								AllotApply allotApply=allotApplyService.queryHandByAppId(lifeMap);
								if("2".equals(allotApply.getCurrStatus())){
									allotApply.setCurrStatus("1");
								}else if("4".equals(allotApply.getCurrStatus())){
									allotApply.setCurrStatus("3");
								}
								appList.add(allotApply);
								applyList.remove(sum-1);
								sum--;
							}else if(appNum==2){
								List<AllotApply> allotAppList = allotApplyService.queryHand(lifeMap);
								for(int j=0;j<allotAppList.size();j++){
									AllotApply allotApply=allotAppList.get(j);
									taoId=allotApply.getAppId();
									lifeList.add(taoId);
									if("2".equals(allotApply.getCurrStatus())){
										allotApply.setCurrStatus("1");
									}else if("4".equals(allotApply.getCurrStatus())){
										allotApply.setCurrStatus("3");
									}
									appList.add(allotApply);
									if(applyList.contains(taoId)){
										applyList.remove(sum-1);
										sum--;
									}
								}
							}else{//申请件已人工提交不在当前环节
								applyList.remove(sum-1);
								sum--;
							}
						}
				}
				//组装数据
				lifeMap.put("allotApplyList",appList );
				lifeMap.put("lifeList", lifeList);
				result=allotMethodService.updateBatchMethod(lifeMap);
				if(result==0){
					context.setData("isSuccess",false);
				}else{
					context.setData("isSuccess",true);
				}
			}else{
				context.setData("isSuccess",false);
				context.setData("exMsg","未选择申请件");
			}
		}catch (Exception e) {
			log.error("AllotCommonAction.release occur error", e);
			context.setData("isSuccess",false);
			context.setData("exMsg","释放异常");
		}
	}
	/**
	* @Description: 申请件  备注
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void remark(Context context) {
		try {
			Map<String, ApplyRemark> linkedHashMap = new LinkedHashMap<String, ApplyRemark>();
			linkedHashMap = context.getData("jsondata");
			Gson gson = new Gson();
			ApplyRemark applyRemark = gson.fromJson(gson.toJson(linkedHashMap), ApplyRemark.class);
			String node=(String) context.getData("node")!=null?(String) context.getData("node"):"";
			String secondNode=(String) context.getData("secondNode")!=null?(String) context.getData("secondNode"):"";
			String ydjFlag=(String) context.getData("ydjFlag")!=null?(String) context.getData("ydjFlag"):"";
			String userCode=(String) context.getData("userCode")!=null?(String) context.getData("userCode"):"";
			String flag="3", appId="";
			Map<String, Object> lifeMap = new HashMap<String, Object>();
			lifeMap.put("node", node);
			lifeMap.put("secondNode", secondNode);
			lifeMap.put("ydjFlag", ydjFlag);
			lifeMap.put("userCode", userCode);
			lifeMap.put("flag", flag);
			List<String> lifeList=new ArrayList<String>();
			String info=((String)applyRemark.getRemarkInfo()).trim();
			StringBuffer remarkInfo=new StringBuffer("");
			if("01".equals(node)){
				if("1".equals(secondNode)){
					remarkInfo.append("审查环节池全流程备注：");
				}else if("3".equals(secondNode)){
					remarkInfo.append("审查环节组全流程备注：");
				}
			}else if("02".equals(node)){
				if("1".equals(secondNode)){
					remarkInfo.append("征信环节池全流程备注：");
				}else if("2".equals(secondNode)){
					remarkInfo.append("征信环节队列全流程备注：");
				}else if("3".equals(secondNode)){
					remarkInfo.append("征信环节组全流程备注：");
				}
			}else if("03".equals(node)){
				if("1".equals(secondNode)){
					remarkInfo.append("审批环节池全流程备注：");
				}else if("3".equals(secondNode)){
					remarkInfo.append("审批环节组全流程备注：");
				}
			}else if("05".equals(node)){
				if("1".equals(secondNode)){
					remarkInfo.append("反欺诈环节池全流程备注：");
				}else if("3".equals(secondNode)){
					remarkInfo.append("反欺诈环节组全流程备注：");
				}
			}
			remarkInfo.append(info);
			Map<String,Object> appMap = context.getDataMap();
			List<String> applyList=(List<String>) appMap.get("list");
			Map<String,Object> map = context.getDataMap();
			int result=0;
			List<ApplyRemark> remarkList=new ArrayList<ApplyRemark>();
			if(applyList.size()>0){
				int sum=applyList.size();
				while(sum>0){
					appId=applyList.get(sum-1).trim();
					String appNo=appId.substring(0,15);
					lifeMap.put("appId", appNo);
					//查询当前环节当前状态申请件
					int appNum = allotApplyService.queryHandCount(lifeMap);
					ApplyRemark remark=new ApplyRemark();
					lifeList.add(appId);
					String remarkUser=(String)map.get("userCode");
					remark.setAppId(appId);
					remark.setRemarkInfo(remarkInfo.toString());
					remark.setRemarkUser(remarkUser);
					remark.setRemarkTime(new Date());
					remark.setCurrNode(node);
					remarkList.add(remark);
					applyList.remove(sum-1);
					sum--;
					if(appNum==2){
						//套卡
						ApplyRemark applyRemark2=new ApplyRemark();
						String taokaId="";
						if(appId.endsWith("2")){
							taokaId = appId.substring(0,15)+"1";
						}else if(appId.endsWith("1")){
							taokaId=appId.substring(0,15)+"2";
						}
						applyRemark2.setAppId(taokaId);
						lifeList.add(taokaId);
						applyRemark2.setRemarkInfo(remarkInfo.toString());
						applyRemark2.setRemarkUser(userCode);
						applyRemark2.setRemarkTime(new Date());
						applyRemark2.setCurrNode(node);
						remarkList.add(applyRemark2);
						if(applyList.contains(taokaId)){//两者都有
							applyList.remove(sum-1);
							sum--;
						}
					}
				}
			}
			//组装数据
			lifeMap.put("remarkList",remarkList);
			lifeMap.put("lifeList", lifeList);
			result=allotMethodService.saveRemark(lifeMap);
			if(result==0){
				context.setData("isSuccess",false);
			}else{
				context.setData("isSuccess",true);
				log.info("AllotCommonAction.remark 全流程备注成功");
			}
		} catch (Exception e) {
			log.error("AllotCommonAction.remark occur error", e);
			context.setData("isSuccess",false);
			context.setData("exMsg","备注失败!");
		}
	}
	/**
	* @Description: 自动分件是否参与
	* @author 王德彬
	* @version  V1.0
	* @param context  
	 */
	public void updateAutoStatus(Context context) {
		Map<String,Object> map = context.getDataMap();
		List<String> applyList=(List<String>) map.get("list");
		String autoStatus=(String)map.get("autoStatus")!=null?(String)map.get("autoStatus"):"";
		AllotCommon ac=new AllotCommon();
		ac.setIds(applyList);
		ac.setAutoStatus(autoStatus);
		int result=0;
		//批量修改是否参与自动分件
		try {
			result=allotCommonService.updateAutoStatus(ac);
		} catch (CoreException e) {
			log.error("自动分件是否参与异常:", e);
		}
		if(result==0){
			context.setData("isSuccess",false);
		}else{
			context.setData("isSuccess",true);
		}
	}
}
