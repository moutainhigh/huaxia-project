package com.huaxia.opas.action.archive;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;

import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.huateng.neofp.core.Action;
import com.huateng.neofp.core.Context;
import com.huateng.neofp.core.CoreException;
import com.huaxia.opas.domain.archive.ArBatch;
import com.huaxia.opas.domain.archive.ArDetail;
import com.huaxia.opas.service.apply.BizInpApplService;
import com.huaxia.opas.service.archive.ArQueueService;
import com.huaxia.opas.service.workflow.TopbpmService;
import com.huaxia.opas.util.CommonProperties;
import com.ibm.icu.text.SimpleDateFormat;

public class ArQueueAction implements Action {
	private static Logger logger = LoggerFactory.getLogger(ArQueueAction.class);

	@Resource(name = "arQueueService")
	private ArQueueService arQueueService;

	@Resource(name = "bizInpApplService")
	private BizInpApplService bizInpApplService;

	@Resource(name = "topbpmService")
	private TopbpmService topbpmService;
	/**
	 * 查出归档队列信息
	 * @throws Exception 
	 * 
	 * @throws ParseException
	 */
	public void listArQueue(Context ctx) throws Exception {
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		ArDetail arDetail = gson.fromJson(gson.toJson(map), ArDetail.class);
		// 将前台的查询参数转换为大写
		String appid = arDetail.getAppId();
		if (appid != null && !"".equals(appid)) {
			String appID = appid.toUpperCase();
			arDetail.setAppId(appID);
		}

		int curNum = 0;
		int curPage = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("page") == null ? 0 : ctx.getDataMap().get("page")));
		int pageNum = Integer
				.parseInt(String.valueOf(ctx.getDataMap().get("rows") == null ? 0 : ctx.getDataMap().get("rows")));

		if (curPage > 1) {
			curNum = (curPage - 1) * pageNum;
		}

		List<ArDetail> list = new ArrayList<ArDetail>();
		int count = 0;
		count = arQueueService.queryArQueueCount(arDetail);
		if (count > 0) {
			list = arQueueService.queryArQueueList(arDetail, curNum, pageNum);
		}
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("total", count);
		dataMap.put("rows", list);
		ctx.setDataMap(dataMap);
	}

	/**
	 * 放弃该待归档信息
	 * 
	 * @throws Exception
	 * @throws CoreException
	 *
	 */
	public void giveUpFiles(Context ctx) throws CoreException {
		Map map = ctx.getDataMap();
		String userId = (String) map.get("userId");
		try {
			List appIdList = (ArrayList) map.get("appId");
			if (appIdList == null || appIdList.isEmpty()) {
				throw new CoreException("请选择放弃信息!");
			}
			for (int i = 0; i < appIdList.size(); i++) {
				ArDetail arDetail = new ArDetail();
				arDetail.setAppId((String) appIdList.get(i));
				arDetail.setOperator(userId);
				arQueueService.updateArQueueToDel(arDetail);
			}
			ctx.setData("isSuccess", true);
		} catch (CoreException e) {
			throw e;
		}
	}

	/*
	 * 选中信息归档
	 */
	public void updAndInsArQueue(Context ctx) throws MalformedURLException, Exception {
		// 工具方法,获取20位归档编号
		String fileNo = getArchiveBatchNo();

		Map map = ctx.getDataMap();
		String userId = (String) map.get("userId");
		Map appIdMap = new HashMap();
		Map appIdMap2 = new HashMap();
		String intputXml = "";

		try {
			// 判断选择的待归档的件
			List appIdList = (ArrayList) map.get("appId");
			if (appIdList == null || appIdList.isEmpty()) {
				throw new CoreException("请选择归档信息!");
			}

			int result = 0;
			boolean callWorkflowResult = false;

			for (int i = 0; i < appIdList.size(); i++) {
				String appId = (String) appIdList.get(i);

				// 更新归档批次信息
				/*ArDetail arDetail = new ArDetail();
				arDetail.setOperator(userId);
				arDetail.setAppId(appId);
				arDetail.setFileNo(fileNo);
				result += arQueueService.updateFileNoForAr(arDetail);*/
				
			    Map<String, String> params = new HashMap<String, String>();
			    params.put("userId",userId);
			    params.put("appId", appId);
			    params.put("batchNo", fileNo);
				result += arQueueService.updateArchiveBatchNo(params);

				if (logger.isInfoEnabled()) {
					logger.info("[手动归档] 归档申请件编号:{}", appId);
					logger.info("[手动归档] 归档批次号:{}", params.get("batchNo"));
					logger.info("[手动归档] 归档记录数:{}", result);
				}

				appIdMap.put("appId", appId);
				appIdMap.put("userId",userId);
				// 判断MATCHING_CARD_FLAG是否为0,套卡标志
				Map<String, Object> matchingFlagMap = arQueueService.queryMatchingFlag(appIdMap);
				String matchingFlag = matchingFlagMap.get("MATCHINGFLAG") != null
						? matchingFlagMap.get("MATCHINGFLAG").toString() : "";

				if ("0".equals(matchingFlag)) {
					// 单卡
					// 获取分件表的processId
					Map<String, Object> needMap = bizInpApplService.queryProcessIdByAppId(appIdMap);
					String processId = needMap.get("PROCESSID") != null ? needMap.get("PROCESSID").toString() : "";

					// 查询分件表的YDJ_FLAG字段
					Map<String, Object> flagMap = bizInpApplService.queryYdjFlag(appIdMap);
					String ydjFlag = flagMap.get("YDJFLAG") != null ? flagMap.get("YDJFLAG").toString() : "";
					// 判断YDJ_FLAG,调用不同的节点
					if ("1".equals(ydjFlag)) {
						// 易达金
						intputXml = "{'processId':'" + processId + "','nodeId':'"
								+ CommonProperties.getParoperMap().get("nodeId_gd_ydj") + "','isBack':'" + 0 + "'}";
					} else if ("2".equals(ydjFlag)) {
						// 标准卡
						intputXml = "{'processId':'" + processId + "','nodeId':'"
								+ CommonProperties.getParoperMap().get("nodeId_gd_bzk") + "','isBack':'" + 0 + "'}";
					}

					callWorkflowResult = callWorkflow(intputXml);
					if(callWorkflowResult){
						ctx.setData("exMsg","工作流任务繁忙,请稍后重试.");
					}
				} else {
					// 套卡
					// 拼接另一张套卡的appid,查询同步标志SYN_FLAG
					// 截取前15位
					String appId2 = appId.substring(0, 15);
					// 截取最后一位
					String end = appId.substring(15, 16);
					if ("1".equals(end)) {
						// 拼接appId
						appId2 = appId2 + "2";
					} else if ("2".equals(end)) {
						appId2 = appId2 + "1";
					}
					appIdMap2.put("appId", appId2);

					// 查询同步标志SYN_FLAG
					Map<String, Object> synMap = arQueueService.querysynFlag(appIdMap2);
					String synFlag = synMap.get("SYNFLAG") != null ? synMap.get("SYNFLAG").toString() : "";
					if ("1".equals(synFlag)) {
						// 套卡已经同步,调工作流将两张卡一起归档,工作流归档只看前15位,调用一次即可将两张卡完成归档
						// 获取分件表的processId
						Map<String, Object> needMap = bizInpApplService.queryProcessIdByAppId(appIdMap);
						String processId = needMap.get("PROCESSID") != null ? needMap.get("PROCESSID").toString() : "";

						// 套卡只有易达金
						intputXml = "{'processId':'" + processId + "','nodeId':'"
								+ CommonProperties.getParoperMap().get("nodeId_gd_ydj") + "','isBack':'" + 0 + "'}";

						callWorkflowResult = callWorkflow(intputXml);
						if("false".equals(callWorkflowResult)){
							ctx.setData("exMsg","工作流任务繁忙,请稍后重试.");
						}
					} else {
						// 套卡未同步标志,修改数据库同步标志
						arQueueService.updatesynFlag(appIdMap);
					}
				}

				if (logger.isInfoEnabled()) {
					logger.info("[手动归档] 调用工作流结果:{}", callWorkflowResult);
				}
			}

			if (result > 0) {
				// 记录归档批次的操作
				ArBatch arBatch = new ArBatch();
				arBatch.setFileNo(fileNo);
				arBatch.setFileApp("" + result);
				arBatch.setFileOperator(userId);
				arQueueService.insertArBatch(arBatch);

				ctx.setData("isSuccess", true);
				ctx.setData("fileNo", fileNo);
			}
		} catch (CoreException e) {
			throw e;
		}
	}

	public boolean callWorkflow(String message) throws Exception{

//		String workflowUrl = CommonProperties.getParoperMap().get("process_IP").toString();
//		Client client = null;

//			client = new Client(
//					new URL("http://" + workflowUrl + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
//			Object[] obj = new Object[] { message };
//			Object[] invokeBack = null;
			String invokeBack = "";
			try {
//				invokeBack=client.invoke("signal", obj);
				invokeBack = topbpmService.signal(message).toString();
			}catch(Exception e){
				e.printStackTrace();
			}	
			if(invokeBack != null && "1".equals(invokeBack.toString())){
				return false;
			}else{
				return true;		
			}
		}

	/**
	 * 将列表中全部信息归档
	 * 
	 * @throws Exception
	 */
	public void updAndInsAll(Context ctx) throws Exception {
		Gson gson = new Gson();
		Map map = ctx.getDataMap();
		String userId = (String) map.get("userId");
		String fileNo = getArchiveBatchNo();
		Map appIdMap = new HashMap();
		Map appIdMap2 = new HashMap();
		String intputXml = "";
		int counts = 0;
		ArDetail arDetail1 = gson.fromJson(gson.toJson(map), ArDetail.class);
		// 查询剩余的未归档件
		List<String> ids = new ArrayList<String>();
		List<ArDetail> list = new ArrayList<ArDetail>();
		list = arQueueService.queryArDetailList(arDetail1);
		// 获取查询所有的件的ID集合
		for (ArDetail a1 : list) {
			String id = a1.getAppId();
			ids.add(id);
			arDetail1.setIds(ids);
		}
		try {
			// 循环调工作流,完成归档
			List<String> appIds = arDetail1.getIds();
			for (String appId : appIds) {
				// 归档实体类
				ArDetail arDetail = new ArDetail();
				arDetail.setAppId(appId);
				// set归档编号
				arDetail.setFileNo(fileNo);
				arDetail.setOperator(userId);
				// 修改归档编号
				int x = arQueueService.updateFileNoForAr(arDetail);
				counts = counts + x;
				// 获取appid,查询条件
				appIdMap.put("appId", appId);
				appIdMap.put("userId",userId);
				// 判断MATCHING_CARD_FLAG是否为0,套卡标志
				Map<String, Object> matchingFlagMap = arQueueService.queryMatchingFlag(appIdMap);
				String matchingFlag = matchingFlagMap.get("MATCHINGFLAG") != null
						? matchingFlagMap.get("MATCHINGFLAG").toString() : "";

				if ("0".equals(matchingFlag)) {
					// 单卡
					// 调用工作流
					Map<String, String> streamMap = CommonProperties.getParoperMap();
					// 获取分件表的processId
					Map<String, Object> needMap = bizInpApplService.queryProcessIdByAppId(appIdMap);
					String processId = needMap.get("PROCESSID") != null ? needMap.get("PROCESSID").toString() : "";
					// 服务ip地址
//					String processIp = streamMap.get("process_IP").toString();
					// 拼接工作流URL
//					Client client = new Client(
//							new URL("http://" + processIp + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
					// 查询分件表的YDJ_FLAG字段
					Map<String, Object> flagMap = bizInpApplService.queryYdjFlag(appIdMap);
					String ydjFlag = flagMap.get("YDJFLAG") != null ? flagMap.get("YDJFLAG").toString() : "";
					// 判断YDJ_FLAG,调用不同的节点
					if ("1".equals(ydjFlag)) {
						// 易达金
						intputXml = "{'processId':'" + processId + "','nodeId':'"
								+ CommonProperties.getParoperMap().get("nodeId_gd_ydj") + "','isBack':'" + 0 + "'}";
					} else if ("2".equals(ydjFlag)) {
						// 标准卡
						intputXml = "{'processId':'" + processId + "','nodeId':'"
								+ CommonProperties.getParoperMap().get("nodeId_gd_bzk") + "','isBack':'" + 0 + "'}";
					}
//					Object[] obj = new Object[] { intputXml.toString() };
//					Object[] res = client.invoke("signal", obj);
					String res = topbpmService.signal(intputXml.toString()).toString();
					if("2".equals(res) || res == null || "".equals(res)){
						ctx.setData("exMsg","工作流任务繁忙,请稍后重试.");
					}
				} else {
					// 套卡
					// 拼接另一张套卡的appid,查询同步标志SYN_FLAG
					// 截取前15位
					String appId2 = appId.substring(0, 15);
					// 截取最后一位
					String end = appId.substring(15, 16);
					if ("1".equals(end)) {
						// 拼接appId
						appId2 = appId2 + "2";
					} else if ("2".equals(end)) {
						appId2 = appId2 + "1";
					}
					appIdMap2.put("appId", appId2);
					// 查询同步标志SYN_FLAG
					Map<String, Object> synMap = arQueueService.querysynFlag(appIdMap2);
					String synFlag = synMap.get("SYNFLAG") != null ? synMap.get("SYNFLAG").toString() : "";
					if ("1".equals(synFlag)) {
						// 套卡已经同步,调工作流将两张卡一起归档,工作流归档只看前15位,调用一次即可将两张卡完成归档
						// 调用工作流
						Map<String, String> streamMap = CommonProperties.getParoperMap();
						// 获取分件表的processId
						Map<String, Object> needMap = bizInpApplService.queryProcessIdByAppId(appIdMap);
						String processId = needMap.get("PROCESSID") != null ? needMap.get("PROCESSID").toString() : "";
						// 服务ip地址
//						String processIp = streamMap.get("process_IP").toString();
						// 拼接工作流URL
//						Client client = new Client(
//								new URL("http://" + processIp + "/topbpm_test/services/BranchBankSearchServiceI?wsdl"));
						// 套卡只有易达金
						intputXml = "{'processId':'" + processId + "','nodeId':'"
								+ CommonProperties.getParoperMap().get("nodeId_gd_ydj") + "','isBack':'" + 0 + "'}";

//						Object[] obj = new Object[] { intputXml.toString() };
//						Object[] res = client.invoke("signal", obj);
						String res = topbpmService.signal(intputXml.toString()).toString();
						if("2".equals(res) || res == null || "".equals(res)){
							ctx.setData("exMsg","工作流任务繁忙,请稍后重试.");
						}
					} else {
						// 套卡未同步标志,修改数据库同步标志
						arQueueService.updatesynFlag(appIdMap);
					}
				}
			}
			if (counts > 0) {
				ArBatch arBatch = new ArBatch();
				arBatch.setFileNo(fileNo);
				arBatch.setFileApp("" + counts);
				arBatch.setFileOperator(userId);
				arQueueService.insertArBatch(arBatch);
				ctx.setData("isSuccess", true);
				ctx.setData("fileNo", fileNo);
			} else if (counts == 0) {
				System.out.println("没有拿到归档的信息.");
			}
		} catch (Exception e) {
			ctx.setData("exMsg", e.getMessage());
			throw e;
		}
	}

	/**
	 * 修改待归档件信息状态
	 * 
	 * @throws ParseException
	 */
	public void updateStatuApp(Context ctx) {
		int sign = updateAppFlag(ctx);
		if (sign > 0) {
			ctx.setData("isSuccess", true);
		} else if (sign == 0) {
			ctx.setData("isNull", true);
		} else {
			System.out.println("更改补件码标识状态出现问题");
			logger.error("更改补件码标识状态出现问题.....");
		}
	}

	/**
	 * 修改待归档补件信息状态
	 * 
	 * @throws ParseException
	 */
	public int updateAppFlag(Context ctx) {
		Map map = ctx.getDataMap();
		String userId = (String) map.get("userId");
		ArDetail arDetail = new ArDetail();
		String appid = (String) map.get("appId_input");
		if (appid != null && !"".equals(appid)) {
			String appID = appid.toUpperCase();
			arDetail.setAppId(appID);
		}
		arDetail.setOperator(userId);
		return arQueueService.updateAppFlag(arDetail);
	}

	/**
	 * 工具方法，获取20位归档编号
	 */
	public String getArchiveBatchNo() {
		Date currDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String dateStr = sdf.format(currDate);
		int rannum = new Random().nextInt(999 - 100 + 1) + 100;
		return dateStr + rannum;
	}
}
