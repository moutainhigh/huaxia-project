package com.huaxia.opas.action.riskCheck;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.riskcheck.WSPlatformRiskInvest;
import com.huaxia.opas.service.riskcheck.WSPlatformRiskInvestService;

/**
 * 网申平台风险排查
 * @author wenyh
 *
 */
public class WSPlatformRiskInvestAction implements Action{
	private static Logger log = LoggerFactory.getLogger(WSPlatformRiskInvestAction.class);
	@Resource(name = "wSPlatformRiskInvestService")
	private WSPlatformRiskInvestService wSPlatformRiskInvestService;
	
	/**
	 * 网申平台风险排查队列列表数据查询方法
	 * @param context
	 * @throws CoreException
	 */
	public void queryWSPlatformRiskInvestList(Context ctx)throws CoreException{
		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		String appId = dataMap.get("appId").toString();
		if(appId != null && !"".equals(appId)){
			appId = appId.trim().toUpperCase();
		}
		String businessNumber = dataMap.get("businessNumber").toString();
		if(businessNumber != null && !"".equals(businessNumber)){
			businessNumber = businessNumber.trim();
		}
		String moduleNumber = dataMap.get("moduleNumber").toString();
		if(moduleNumber != null && !"".equals(moduleNumber)){
			moduleNumber = moduleNumber.trim();
		}
		String riskType = (String) dataMap.get("riskType");
		if(riskType != null && !"".equals(riskType)){
			riskType = riskType.trim();
		}
		String startDate = (String) dataMap.get("startDate");
		if(startDate != null && !"".equals(startDate)){
			startDate = startDate.trim();
		}
		String endDate = (String) dataMap.get("endDate");
		if(endDate != null && !"".equals(endDate)){
			endDate = endDate.trim();
		}
		params.put("appId", appId);
		params.put("businessNumber", businessNumber);
		params.put("moduleNumber", moduleNumber);
		params.put("riskType",riskType);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		count = wSPlatformRiskInvestService.queryWSPlatformRiskInvestCount(params);
		if (count > 0) {
			list = wSPlatformRiskInvestService.queryWSPlatformRiskInvestList(params, curNum, pageNum);
		}
		
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 平台调查结果查看队列列表数据查询方法
	 * @param context
	 * @throws CoreException
	 */
	public void queryWSPlatformInvestResultList(Context ctx)throws CoreException{
		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		String appId = dataMap.get("appId").toString();
		if(appId != null && !"".equals(appId)){
			appId = appId.trim().toUpperCase();
		}
		String businessNumber = dataMap.get("businessNumber").toString();
		if(businessNumber != null && !"".equals(businessNumber)){
			businessNumber = businessNumber.trim();
		}
		String moduleNumber = dataMap.get("moduleNumber").toString();
		if(moduleNumber != null && !"".equals(moduleNumber)){
			moduleNumber = moduleNumber.trim();
		}
		String riskType = (String) dataMap.get("riskType");
		if(riskType != null && !"".equals(riskType)){
			riskType = riskType.trim();
		}
		String startDate = (String) dataMap.get("startDate");
		if(startDate != null && !"".equals(startDate)){
			startDate = startDate.trim();
		}
		String endDate = (String) dataMap.get("endDate");
		if(endDate != null && !"".equals(endDate)){
			endDate = endDate.trim();
		}
		String alInvestResult = (String) dataMap.get("alInvestResult");
		if(alInvestResult != null && !"".equals(alInvestResult)){
			alInvestResult = alInvestResult.trim();
		}
		String misszdResult = (String) dataMap.get("misszdResult");
		if(misszdResult != null && !"".equals(misszdResult)){
			misszdResult = misszdResult.trim();
		}
		String finalAuditResult = (String) dataMap.get("finalAuditResult");
		if(finalAuditResult != null && !"".equals(finalAuditResult)){
			finalAuditResult = finalAuditResult.trim();
		}
		params.put("appId", appId);
		params.put("businessNumber", businessNumber);
		params.put("moduleNumber", moduleNumber);
		params.put("riskType",riskType);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("alInvestResult", alInvestResult);
		params.put("misszdResult", misszdResult);
		params.put("finalAuditResult", finalAuditResult);
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));
		
		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		count = wSPlatformRiskInvestService.queryWSPlatformInvestResultCount(params);
		if (count > 0) {
			list = wSPlatformRiskInvestService.queryWSPlatformInvestResultList(params, curNum, pageNum);
		}
		
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 根据appId查询出备注信息方法
	 * @param context
	 * @throws CoreException
	 */
	public void checkRemarkInfoByAppId(Context ctx)throws CoreException{
		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		String appId = dataMap.get("appId").toString();
		if(appId != null && !"".equals(appId)){
			appId = appId.trim().toUpperCase();
		}
		params.put("appId", appId);
		String remarkInfo = wSPlatformRiskInvestService.checkRemarkInfoByAppId(params);
		
		dataMap.put("remarkInfo", remarkInfo);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * 网申平台风险排查队列列表数据查询方法
	 * @param context
	 * @throws CoreException
	 */
	public void queryNotContinueCirculateAppIdList(Context ctx)throws CoreException{
		Map dataMap = ctx.getDataMap();
		// 参数
		Map<String, String> params = new HashMap<String, String>();
		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}
		int count = 0;
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		count = wSPlatformRiskInvestService.queryWSPlatformRiskInvestCountNotParam(params);
		if (count > 0) {
			list = wSPlatformRiskInvestService.queryWSPlatformRiskInvestListNotParam(params, curNum, pageNum);
		}
		
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}
	
	/**
	 * @Description: 申请件 批量人工调查 备注
	 * @author wenyh
	 * @version  V1.0
	 * @param context  
	 */
	public void remark(Context context) {
		try {
			String appId = "";
			Map<String,Object> map = context.getDataMap();
			List<String> applyList = (List<String>) map.get("list");
			String alInvestResult = (String)map.get("alInvestResult")!=null?(String)map.get("alInvestResult"):"";
			String misszdResult = (String)map.get("misszdResult")!=null?(String)map.get("misszdResult"):"";
			String remark = (String)map.get("remark")!=null?(String)map.get("remark"):"";
			String remarkUser = (String)map.get("userCode");
			int result = 0;
			if(applyList.size() > 0){
				int sum = applyList.size();
				while(sum > 0){
					appId = applyList.get(sum - 1).trim();
					WSPlatformRiskInvest wspri = new WSPlatformRiskInvest();
					wspri.setAppId(appId);
					wspri.setAlInvestResult(alInvestResult);
					wspri.setMisszdResult(misszdResult);
					wspri.setRemark(remark);
					wspri.setLastOptUser(remarkUser);
					wspri.setLastOptDate(new Date());
					result = wSPlatformRiskInvestService.updateRemark(wspri);
					applyList.remove(sum - 1);
					sum --;
				}
			}
			if(result == 0){
				context.setData("isSuccess",false);
			}else{
				context.setData("isSuccess",true);
				log.info("WSPlatformRiskInvestAction.remark 备注成功");
			}
		} catch (Exception e) {
			log.error("WSPlatformRiskInvestAction.remark occur error", e);
			context.setData("isSuccess",false);
			context.setData("exMsg","备注失败!");
		}
	}
	
	/**
	 * @Description: 申请件 批量拒绝流转
	 * @author wenyh
	 * @version  V1.0
	 * @param context  
	 */
	public void refuse(Context context) {
		try {
			String appId = "";
			Map<String,Object> map = context.getDataMap();
			List<String> applyList = (List<String>) map.get("list");
			String circulateFlag = (String)map.get("circulateFlag")!=null?(String)map.get("circulateFlag"):"";
			String refuseCirculateReason = (String)map.get("refuseCirculateReason")!=null?(String)map.get("refuseCirculateReason"):"";
			String refuseUser = (String)map.get("userCode");
			int result = 0;
			if(applyList.size() > 0){
				int sum = applyList.size();
				while(sum > 0){
					appId = applyList.get(sum - 1).trim();
					WSPlatformRiskInvest wspri = new WSPlatformRiskInvest();
					wspri.setAppId(appId);
					wspri.setCirculateFlag(circulateFlag);
					wspri.setRefuseCirculateReason(refuseCirculateReason);
					wspri.setLastOptUser(refuseUser);
					wspri.setLastOptDate(new Date());
					result = wSPlatformRiskInvestService.updateRefuse(wspri);
					applyList.remove(sum - 1);
					sum --;
				}
			}
			if(result == 0){
				context.setData("isSuccess",false);
			}else{
				context.setData("isSuccess",true);
				log.info("WSPlatformRiskInvestAction.Refuse 批量拒绝流转成功");
			}
		} catch (Exception e) {
			log.error("WSPlatformRiskInvestAction.Refuse occur error", e);
			context.setData("isSuccess",false);
			context.setData("exMsg","批量拒绝流转失败!");
		}
	}
	
	/**
	 * @Description: 申请件 批量继续流转
	 * @author wenyh
	 * @version  V1.0
	 * @param context  
	 */
	public void continueCirculateBatchSubmit(Context context) {
		try {
			String appId = "";
			Map<String,Object> map = context.getDataMap();
			List<String> applyList = new ArrayList(new HashSet<>((List<String>) map.get("list")));//做去重处理
			log.info("wSPlatformRiskInvestService.selectAppIds 防止重复操作申请件的过滤方法开始", applyList);
			List<Map<String, String>> appIdsMap =  wSPlatformRiskInvestService.selectAppIds(applyList);//防止重复操作申请件的过滤方法
			List<String> list = new ArrayList<String>();
			if (appIdsMap != null && appIdsMap.size() > 0) {
				for (Map<String, String> appIdMap : appIdsMap) {
					list.add(appIdMap.get("appId"));
				}
			}
			log.info("wSPlatformRiskInvestService.selectAppIds 防止重复操作申请件的过滤方法结束", list);
			List<String> appIdTempList = new ArrayList<String>();
			if(list.size() > 0){
				int tempNum = wSPlatformRiskInvestService.insertTempBatchAppId(list);
				if(tempNum > 0){
					log.info("wSPlatformRiskInvestService.insertTempBatchAppId 批量把申请件插入异步临时表成功");
					List<Map<String, String>> appIdsTempMap = wSPlatformRiskInvestService.selectTempAppIds();
					if (appIdsTempMap != null && appIdsTempMap.size() > 0) {
						for (Map<String, String> appIdTempMap : appIdsTempMap) {
							appIdTempList.add(appIdTempMap.get("appId"));
						}
					}
				}else{
					log.info("wSPlatformRiskInvestService.insertTempBatchAppId 申请件重复插入异步临时表");
				}
			}
			String circulateFlag = (String)map.get("circulateFlag")!=null?(String)map.get("circulateFlag"):"";
			String lastOptUser = (String)map.get("userCode");
			int result = 0;
			while(appIdTempList.size() > 0){
				appId = appIdTempList.get(appIdTempList.size() - 1).trim();
				Map<String,Object> paramMap = new HashMap<String, Object>();
				paramMap.put("appId", appId);
				log.info("wSPlatformRiskInvestService.continueCirculateInvokeBpm 继续流转开始调工作流...");
				String resultFlag = wSPlatformRiskInvestService.continueCirculateInvokeBpm(paramMap);
				if("1".equals(resultFlag)){
					log.info("wSPlatformRiskInvestService.continueCirculateInvokeBpm 继续流转调工作流成功！");
					WSPlatformRiskInvest wspri = new WSPlatformRiskInvest();
					wspri.setAppId(appId);
					wspri.setCirculateFlag(circulateFlag);
					wspri.setLastOptUser(lastOptUser);
					wspri.setLastOptDate(new Date());
					log.info("wSPlatformRiskInvestService.updateContinueCirculateSubmit 开始修改申请件的继续流转的标识...", appId);
					result = wSPlatformRiskInvestService.updateContinueCirculateSubmit(wspri);
					if(result == 0){
						log.info("wSPlatformRiskInvestService.updateContinueCirculateSubmit 修改申请件的继续流转的标识失败！", appId);
						int deleteResult = wSPlatformRiskInvestService.deleteTempAppId(appId);
						if(deleteResult > 0){
							log.info("wSPlatformRiskInvestService.deleteTempAppId 修改申请件的继续流转的标识失败，删除异步临时表中的申请件成功！", appId);
						}else{
							log.info("wSPlatformRiskInvestService.deleteTempAppId 修改申请件的继续流转的标识失败，删除异步临时表中的申请件失败！", appId);
						}
					}else{
						log.info("wSPlatformRiskInvestService.updateContinueCirculateSubmit 修改申请件的继续流转的标识成功！", appId);
					}
				}else{
					log.info("wSPlatformRiskInvestService.continueCirculateInvokeBpm 继续流转调工作流失败！", appId);
					int deleteResult = wSPlatformRiskInvestService.deleteTempAppId(appId);
					if(deleteResult > 0){
						log.info("wSPlatformRiskInvestService.deleteTempAppId 继续流转调工作流失败，删除异步临时表中的申请件成功！", appId);
					}else{
						log.info("wSPlatformRiskInvestService.deleteTempAppId 继续流转调工作流失败，删除异步临时表中的申请件失败！", appId);
					}
				}
				appIdTempList.remove(appIdTempList.size() - 1);
			}
			if(result == 0){
				context.setData("isSuccess",false);
			}else{
				context.setData("isSuccess",true);
				log.info("WSPlatformRiskInvestAction.continueCirculateBatchSubmit 批量继续流转成功！");
			}
		} catch (Exception e) {
			log.error("WSPlatformRiskInvestAction.continueCirculateBatchSubmit occur error", e);
			context.setData("isSuccess",false);
			context.setData("exMsg","批量继续流转失败!");
		}
	}
	
	/**
	 * 定时任务-定时自动删除异步临时表的申请表数据的方法
	 * 1.异步临时表的数据保存一周
	 * 2.每周日凌晨3点定时清理异步临时表中的数据
	 */
	public void autoDeleteTempAppIds(){
		try{
			int result = wSPlatformRiskInvestService.autoDeleteTempAppIdByFlag();
			if(result > 0){
				log.info("wSPlatformRiskInvestService.autoDeleteTempAppIdByFlag 定时自动删除异步临时表的申请表数据成功！");
			}else{
				log.info("wSPlatformRiskInvestService.autoDeleteTempAppIdByFlag 定时自动删除异步临时表的申请表数据失败！");
			}
		} catch (Exception e) {
			log.error("定时自动删除异步临时表的申请表数据异常>>>>>>>>>>>>>>>>>>");
		}
	}
	
}
