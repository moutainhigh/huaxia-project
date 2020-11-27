package com.huaxia.opas.action.checking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.checking.QualityChecking;
import com.huaxia.opas.domain.sysparm.ApproveReasonCode;
import com.huaxia.opas.domain.system.ApUser;
import com.huaxia.opas.service.checking.QualityCheckingService;
import com.huaxia.opas.service.system.ApUserService;
import com.huaxia.opas.util.SequenceUtil;

/**
 * 添加质检操作员查询选择问题
 * @author wangtao
 *@version v1.1   (初始版本v1.0)2017-10-10
 *
 *修改某一操作员选择多个时无法查询问题
 *@author wangtao
 *@version v1.2  2017-10-12
 *修改操作员显示中文名
 *@author wangtao
 *@version v1.3 2017-10-16
 */

public class QualityCheckingAction implements Action{

	private static Logger logger=LoggerFactory.getLogger(QualityCheckingAction.class);
	
	@Resource(name="qualityCheckingService")
	private QualityCheckingService qualityCheckingService ;
	
	@Resource(name = "apUserService")
	private ApUserService apUserService;
	/**
	 * 质检查询条件操作员队列展示(组长质检)
	 * @param ctx
	 */
	public void queryOperator(Context ctx){
		String roleCode = ctx.getData("roleCode");
		String userName = ctx.getData("userName");
		String userCode = ctx.getData("userCode");
		//根据当前的登录用户得到其所属的组代码
		String userUuid = ctx.getData("userUuid");
		String orgNo = qualityCheckingService.queryUserOrgNo(userUuid);
		Map<String,Object> map = new HashMap<>();
		map.put("roleCode", roleCode);
		map.put("orgNo", orgNo);
		map.put("userCode", userCode);
		map.put("userName", userName);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<ApUser> operatorList = new ArrayList<>();
		int count = qualityCheckingService.queryOperatorCount(map);
		if(count > 0){
			operatorList = qualityCheckingService.queryOperatorList(map,curNum,pageNum);
		}
		
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("total", count);
		dataMap.put("rows", operatorList);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 质检员显示所有操作员(质检员质检)
	 * @param ctx
	 */
	public void queryOperatorOfChecker(Context ctx){
		String roleCode = ctx.getData("roleCode");
		String userName = ctx.getData("userName");
		String userCode = ctx.getData("userCode");
		Map<String,Object>  map = new HashMap<>();
		map.put("roleCode", roleCode);
		map.put("orgNo", "");
		map.put("userCode", userCode);
		map.put("userName", userName);
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<ApUser> operatorList = new ArrayList<>();
		int count = qualityCheckingService.queryOperatorCount(map);
		if(count > 0){
			operatorList = qualityCheckingService.queryOperatorList(map,curNum,pageNum);
		}
		Map<String,Object> dataMap = new HashMap<>();
		dataMap.put("total", count);
		dataMap.put("rows", operatorList);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 根据查询条件展示质检列表
	 * @param ctx
	 */
	public void queryQualityCheckingList(Context ctx){
		Map<String, Object> dataMap = ctx.getDataMap();
		//流水号变大写
		dataMap.put("appId", dataMap.get("appId").toString().toUpperCase()) ;
		String groupFlag = (String) dataMap.get("groupFlag");

		String userRoleCode = (String)dataMap.get("userRoleCode");
		String queueTypeObj = (String)dataMap.get("queueTypeObj");
		System.out.println(userRoleCode+"--------------------"+queueTypeObj);
		if("1".equals(groupFlag)){//组长质检
			String userUuid = ctx.getData("userUuid");
			String orgNo = qualityCheckingService.queryUserOrgNo(userUuid);
			dataMap.put("orgNo", orgNo);
			Map<String,Object> map = new HashMap<>();
			String userName = ctx.getData("userName");
			String userCode = ctx.getData("userCode");
			map.put("orgNo", orgNo);
			map.put("userCode", userCode);
			map.put("userName", userName);
			List<String> operatorList = qualityCheckingService.queryOperatorAllList(map);
			if(operatorList!=null){
				dataMap.put("operatorList", operatorList);
			}else{
				dataMap.put("operatorList", "");
			}
			
			//如果选择队列
			if (dataMap.get("userRoleCode") != null && !dataMap.get("userRoleCode").toString().equals("")){
				addCurrNodeMap(dataMap);
			}
		}else{
			dataMap.put("operatorList", "");
		}
		String examinerCode = (String) dataMap.get("examinerCode");
		if(examinerCode!=null&&!"".equals(examinerCode)){
			String[] examiners = examinerCode.split(",");
			dataMap.put("examinerCode", examiners);
			dataMap.put("examinerCodeLength",examiners.length-1);
		}
		String crediterCode = (String)dataMap.get("crediterCode");
		if(crediterCode!=null&&!"".equals(crediterCode)){
			String[] crediters = crediterCode.split(",");
			dataMap.put("crediterCode", crediters);
			dataMap.put("crediterCodeLength", crediters.length-1);
		}
		String auditorCode = (String) dataMap.get("auditorCode");
		if(auditorCode!=null&&!"".equals(auditorCode)){
			String[] auditorCodes = auditorCode.split(",");
			dataMap.put("auditorCode", auditorCodes);
			dataMap.put("auditorCodesLength", auditorCodes.length-1);
		}
		String	totalOperatorCode = (String) dataMap.get("totalOperatorCode");
		if(totalOperatorCode!=null&&!"".equals(totalOperatorCode)){
			String[] totalOperatorCodes = totalOperatorCode.split(",");
			dataMap.put("totalOperatorCode", totalOperatorCodes);
			dataMap.put("totalOperatorCodesLength", totalOperatorCodes.length-1);
		}
		int curNum = 0;
		int curPage = Integer.parseInt(String.valueOf(ctx.getDataMap().get("page")==null?0:ctx.getDataMap().get("page")));
		int pageNum = Integer.parseInt(String.valueOf(ctx.getDataMap().get("rows")==null?0:ctx.getDataMap().get("rows")));
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		List<QualityChecking> qualityCheckingList = new ArrayList<>();
		int count = qualityCheckingService.queryQualityCheckingCount(dataMap);
		if(count > 0){
			qualityCheckingList = qualityCheckingService.queryQualityCheckingList(dataMap,curNum,pageNum);
		}
		
		Map<String, Object> dataMap1 = new HashMap<>();
		dataMap1.put("total", count);
		dataMap1.put("rows", qualityCheckingList);
		ctx.setDataMap(dataMap1);
	}
	
	/**
	 * 根据质检队列节点补充对应参数
	 * @param map
	 */
	private void addCurrNodeMap(Map<String, Object> map){
		String userRoleCode = (String)map.get("userRoleCode");
		String queueTypeObj = (String)map.get("queueTypeObj");
		
		if(userRoleCode != null && !("").equals(userRoleCode)){//一级队列节点不为空
			if (("1").equals(userRoleCode)){//录入环节
				if(queueTypeObj != null && !("").equals(queueTypeObj)){//二级队列节点不为空情况
					if (("DE1").equals(queueTypeObj)){//录入未完成
						map.put("delStatus", "0");
						map.put("currNode", "01");
						map.put("currStatus", "3");
					}else if (("DE2".equals(queueTypeObj))){//录入已挂起
						map.put("delStatus", "4");
						map.put("currNode", "01");
					}
				}
			}else if (("2").equals(userRoleCode)){//征信环节
				if(queueTypeObj != null && !("").equals(queueTypeObj)){//二级队列节点不为空情况
					if (("CI1").equals(queueTypeObj)){//征信未完成
						map.put("delStatus", "0");
						map.put("currNode", "02");
						map.put("currStatus", "3");
					}else if (("CI2".equals(queueTypeObj))){//征信已完成
//						map.put("queueTypeObj", "CI2");
					}else if (("CI3".equals(queueTypeObj))){//征信待补件
						map.put("delStatus", "2");
						map.put("currNode", "02");
						map.put("currStatus", "3");
					}else if (("CI4".equals(queueTypeObj))){//征信已退回
						map.put("delStatus", "3");
						map.put("currNode", "02");
						map.put("currStatus", "3");
					}else if (("CI5".equals(queueTypeObj))){//征信已挂起
						map.put("currNode", "02");
						map.put("currStatus", "4");
					}
				}
			}else if (("3").equals(userRoleCode)){//审批环节
				if(queueTypeObj != null && !("").equals(queueTypeObj)){//二级队列节点不为空情况
					if (("LL1").equals(queueTypeObj)){//审批未完成
						map.put("delStatus", "0");
						map.put("currNode", "03");
						map.put("currStatus", "3");
						map.put("checkMeuoFlag", "1");
					}else if (("LL2".equals(queueTypeObj))){//审批已完成
						map.put("delStatus", "1");
						map.put("currNode", "04");
						map.put("checkMeuoFlag", "1");
					}else if (("LL3".equals(queueTypeObj))){//审批待补件
						map.put("delStatus", "2");
						map.put("currNode", "03");
						map.put("currStatus", "3");
						map.put("checkMeuoFlag", "1");
					}else if (("LL4".equals(queueTypeObj))){//审批已挂起
						map.put("currNode", "03");
						map.put("currStatus", "4");
						map.put("checkMeuoFlag", "1");
					}
				}
			}else if (("4").equals(userRoleCode)){//征审环节
				if(queueTypeObj != null && !("").equals(queueTypeObj)){//二级队列节点不为空情况
					if (("HY1").equals(queueTypeObj)){//征信未完成
						map.put("delStatus", "0");
						map.put("currNode", "02");
						map.put("currStatus", "3");
					}else if (("HY2".equals(queueTypeObj))){//征信待补件
						map.put("delStatus", "2");
						map.put("currNode", "02");
						map.put("currStatus", "3");
					}else if (("HY3".equals(queueTypeObj))){//征信已退回
						map.put("delStatus", "3");
						map.put("currNode", "02");
						map.put("currStatus", "3");
					}else if (("HY4".equals(queueTypeObj))){//审批未完成
						map.put("delStatus", "0");
						map.put("currNode", "03");
						map.put("currStatus", "3");
						map.put("checkMeuoFlag", "2");
					}else if (("HY5".equals(queueTypeObj))){//审批已完成
						map.put("delStatus", "1");
						map.put("currNode", "04");
						map.put("checkMeuoFlag", "2");
					}else if (("HY6".equals(queueTypeObj))){//审批待补件
						map.put("delStatus", "2");
						map.put("currNode", "03");
						map.put("currStatus", "3");
						map.put("checkMeuoFlag", "2");
					}else if (("HY7".equals(queueTypeObj))){//征审已挂起
//						map.put("queueTypeObj", "HY7");
					}
				}
			}
		}
		
	}
	
	/**
	 * 保存/提交/叫停质检情况记录
	 * @param ctx
	 */
	public void saveQualityChecking(Context ctx){
		Map<String, Object> dataMap = ctx.getDataMap();
		Gson json = new Gson();
		QualityChecking qualityChecking = json.fromJson(json.toJson(dataMap), QualityChecking.class);
		qualityChecking.setAutoId(SequenceUtil.getUUID());
		String appId = (String)dataMap.get("appId");
		String userRecord = (String)dataMap.get("userRecord");
		//根据当前的appId查询该件是属于哪个组成员的
		String userUuid = qualityCheckingService.queryUserUuid(appId);
		//判断是否该质检是外包组质检
		String orgNo = qualityCheckingService.queryUserOrgNo(userUuid);
		if(orgNo!=null&&orgNo.length()>3&&"WB3".equals(orgNo.substring(0,3))){
			qualityChecking.setEmployeeFlag("1");
		}
		//根据appId去申请件分配表查询LAOJIANFLAG捞件标识
		Map<String, Object> map = qualityCheckingService.querySomeFlagByAppId(appId);
		String ydjFlag =(String)map.get("YDJ_FLAG");
		String laoJianFlag = (String)map.get("LAOJIANFLAG");
		String matchingCardFlag = (String)map.get("MATCHING_CARD_FLAG");
		String currOptUser = (String)map.get("CURR_OPT_USER");
		String currNode =(String)map.get("CURR_NODE");
		qualityChecking.setCurrOptUser(currOptUser);
		qualityChecking.setCurrNode(currNode);
		qualityChecking.setUserRecord(userRecord);
		qualityChecking.setYdjFlag(ydjFlag);
		try{
			int count = qualityCheckingService.queryQualityCheckingCountByAppId((String)dataMap.get("appId"));
			if(count > 0 ){
				QualityChecking qc = qualityCheckingService.selectQualityChecking((String)dataMap.get("appId"));
				String stopFlag = qc.getStopFlag();
				if("2".equals(stopFlag)){
					qualityChecking.setStopFlag("2");
				}
			}
			qualityCheckingService.saveQualityChecking(qualityChecking);
//			if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
////				if("1".equals(matchingCardFlag)){
////					String appId0 = appId.substring(0,15)+"2";
////					qualityChecking.setAppId(appId0);
////				}else if("2".equals(matchingCardFlag)){
////					String appId0 = appId.substring(0,15)+"1";
////					qualityChecking.setAppId(appId0);
////				}
//				if(!"0".equals(matchingCardFlag)){
//					if("1".equals(appId.substring(15,16))){
//						qualityChecking.setAppId(appId.substring(0,15)+"2");
//					}else{
//						qualityChecking.setAppId(appId.substring(0,15)+"1");
//					}
//				}
//				qualityChecking.setAutoId(SequenceUtil.getUUID());
//				qualityCheckingService.saveQualityChecking(qualityChecking);
//			}
			ctx.setData("isSuccess", true);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	
	/**
	 * 叫停申请件释放操作 质检只插入不更新
	 * @param ctx
	 * @throws CoreException 
	 * @throws ParseException 
	 */
	public void releaseQualityChecking(Context ctx) throws CoreException, ParseException{
		Map<String, Object> dataMap = ctx.getDataMap();
		Gson json = new Gson();
		String crtDate = (String) dataMap.get("crtDate");
		if(crtDate!=null&&!"".equals(crtDate)){
			 Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(crtDate);
			 dataMap.put("crtDate", date);
		}else{
			dataMap.remove("crtDate");
		}
		String appId = (String)dataMap.get("appId");
		//根据appId去申请件分配表查询LAOJIANFLAG捞件标识
		Map<String, Object> map = qualityCheckingService.querySomeFlagByAppId(appId);
		String ydjFlag = (String)map.get("YDJ_FLAG");
		String laoJianFlag = (String)map.get("LAOJIANFLAG");
		String matchingCardFlag = (String)map.get("MATCHING_CARD_FLAG");
		String currOptUser = (String)map.get("CURR_OPT_USER");
		String currNode = (String)map.get("CURR_NODE");
		QualityChecking qualityChecking = json.fromJson(json.toJson(dataMap), QualityChecking.class);
		qualityChecking.setCurrOptUser(currOptUser);
		qualityChecking.setCurrNode(currNode);
		String userCode = ctx.getData("userCode");
		if(userCode==null||"".equals(userCode)){
			userCode = ctx.getData("userId");
		}
		if(userCode!=null&&!"".equals(userCode)){
			ApUser apUser = apUserService.queryApUserByUserCode(userCode);
			qualityChecking.setMemo(qualityChecking.getMemo()+" 由"+apUser.getUserName()+"释放");
		}
		qualityChecking.setAutoId(SequenceUtil.getUUID());
		qualityChecking.setStopFlag("1");
		qualityChecking.setBreakFlag("1");
		try{
			qualityCheckingService.saveQualityChecking(qualityChecking);
			if("1".equals(ydjFlag)&&!"01".equals(laoJianFlag)&&!"02".equals(laoJianFlag)){
//				if("1".equals(matchingCardFlag)){
//					String appId0 = appId.substring(0,15)+"2";
//					qualityChecking.setAppId(appId0);
//				}else if("2".equals(matchingCardFlag)){
//					String appId0 = appId.substring(0,15)+"1";
//					qualityChecking.setAppId(appId0);
//				}
				if(!"0".equals(matchingCardFlag)){
					if("1".equals(appId.substring(15,16))){
						qualityChecking.setAppId(appId.substring(0,15)+"2");
					}else{
						qualityChecking.setAppId(appId.substring(0,15)+"1");
					}
				}
				qualityChecking.setAutoId(SequenceUtil.getUUID());
				qualityCheckingService.saveQualityChecking(qualityChecking);
			}
			ctx.setData("isSuccess", true);
		}catch(Exception e){
			ctx.setData("isSuccess", false);
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}
	/**
	 *根据appId查看质检结果
	 * @param ctx
	 */
	public void selectQualityChecking1(Context ctx){
		Map<String, Object> dataMap = ctx.getDataMap();
		String appId = (String) dataMap.get("appId");
		String userCode = "";
		String userName = "";
		try{
			QualityChecking qualityChecking = qualityCheckingService.selectQualityChecking(appId);
			if(qualityChecking!=null&&qualityChecking.getCrtUser()!=null&&!"".equals(qualityChecking.getCrtUser())){
				String crtUser = qualityChecking.getCrtUser();
				ApUser apUser1 = apUserService.queryApUserByUserCode(crtUser);
				if(apUser1!=null){
					userName = apUser1.getUserName();
					ctx.setData("userName", userName);
				}
			}else{
				ctx.setData("userName", userName);
			}
			ctx.setData("userName", userName);
			ctx.setData("qualityChecking", qualityChecking);
			ctx.setData("isSuccess", true);
		}catch(Exception e){
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
		}
	}
		
	
	/**
	 *根据appId查看质检结果
	 * @param ctx
	 */
	public void selectQualityChecking(Context ctx){
		Map<String, Object> dataMap = ctx.getDataMap();
		String appId = (String) dataMap.get("appId");
		String flag = (String) dataMap.get("flag");
		String userCode = "";
	    userCode = ctx.getData("userId");
	    if("".equals(userCode)&&userCode!=null){
	    	userCode = (String) dataMap.get("userCode");
	    }
		String userName = "";
		try{
			if("0".equals(flag)){
				if(userCode!=null&&!"".equals(userCode)){
					ApUser apUser = apUserService.queryApUserByUserCode(userCode);
					if(apUser!=null){
						userName = apUser.getUserName();
					}
				}
			}
			QualityChecking qualityChecking = qualityCheckingService.selectQualityChecking(appId);
			if(!"0".equals(flag)){
				if(qualityChecking!=null&&qualityChecking.getCrtUser()!=null&&!"".equals(qualityChecking.getCrtUser())){
					String crtUser = qualityChecking.getCrtUser();
					ApUser apUser1 = apUserService.queryApUserByUserCode(crtUser);
					userName = apUser1.getUserName();
					if(apUser1!=null){
						ctx.setData("userName", userName);
					}
				}else{
					ctx.setData("userName", userName);
				}
			}
			ctx.setData("userName", userName);
			ctx.setData("qualityChecking", qualityChecking);
			ctx.setData("isSuccess", true);
		}catch(Exception e){
			ctx.setData("exMsg", e.getMessage());
			ctx.setData("isSuccess", false);
		}
	}
	
	/**
	 * 查询所有的拒绝码和违例码
	 * @return
	 */
	public void queryReasonCode(Context ctx){
		try{
			List<ApproveReasonCode> refuseCodeList = qualityCheckingService.queryRefuseCode();
			List<ApproveReasonCode> violateCodeList = qualityCheckingService.queryBreakCode();
			if(ctx.getSessionAttribute("userId")!=null){
				logger.info(ctx.getSessionAttribute("userId").toString());
				logger.info(ctx.getSessionAttribute("userId").toString().substring(0,32));
			}
			List<Map<String, String>> userRoleCodeList = queryUserRoleCode((String)ctx.getSessionAttribute("userId"));
			ctx.setData("refuseCodeList", refuseCodeList);
			ctx.setData("violateCodeList", violateCodeList);
			ctx.setData("userRoleCodeList", userRoleCodeList);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询当前用户权限
	 * 
	 */
	private List<Map<String, String>> queryUserRoleCode(String userId){
		List<Map<String, String>> list = new ArrayList<>();
		List<String> userCodeList = qualityCheckingService.queryListRoleCodeByUserCode(userId);
		Map<String, String> deMap = new HashMap<>();
		Map<String, String> ciMap = new HashMap<>();
		Map<String, String> lMap = new HashMap<>();
		Map<String, String> hyMap = new HashMap<>();
		deMap.put("reasonCode", "1");
		deMap.put("reasonDesc", "录入环节");
		list.add(deMap);
		for (String tempStr : userCodeList) {
			if (tempStr.indexOf("CI")!=-1) {//征信
				ciMap.put("reasonCode", "2");
				ciMap.put("reasonDesc", "征信环节");
			} else if (tempStr.indexOf("L")!=-1){//审批
				lMap.put("reasonCode", "3");
				lMap.put("reasonDesc", "审批环节");
			} else if (tempStr.indexOf("HY")!=-1){//征审合一
				hyMap.put("reasonCode", "4");
				hyMap.put("reasonDesc", "征审合一");
			}
		}
		if(!ciMap.isEmpty() && !list.contains(ciMap)){
			list.add(ciMap);
		}
		if(!lMap.isEmpty() && !list.contains(lMap)){
			list.add(lMap);
		}
		if(!hyMap.isEmpty() && !list.contains(hyMap)){
			list.add(hyMap);
		}
		return list;
	}
	
}
