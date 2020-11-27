package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.tripartite.TYAntifraud;
import com.huaxia.opas.domain.tripartite.TYAntifraudData;
import com.huaxia.opas.domain.tripartite.TYAntifraudRiskInfo;
import com.huaxia.opas.service.tripartite.TYAntifraudService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 腾讯天御评分
 *
 */
public class WST001000TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WST001000TaskWorker.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	private final String taskType;// 腾讯天御评分任务类型

	private Integer tyTaskQueryNum;// 每次天御分查询任务最大条数

	private TaskCallStatusService taskCallStatusService;

	private TYAntifraudService tyAntifraudService;

	private Integer PLAZE_TIANYU_THREAD_SLEEPTIME_MM;
	private Integer PLAZE_TIANYU_SINGLE_SLEEPTIME_MM;
	private String TIANYU_SF_URL;

	public WST001000TaskWorker() {
		// 获取配置参数
		tyTaskQueryNum = Integer.valueOf(appConfig.getValue("PLAZE_TYTASK_QUERY_SIZE"));
		taskType = appConfig.getValue("PLAZE_TIANYU_TASK_TYPE");
		// 初始化服务接口
		taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		tyAntifraudService = (TYAntifraudService) SpringContextUtil.getBean("tyAntifraudService");
		PLAZE_TIANYU_THREAD_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_TIANYU_THREAD_SLEEPTIME_MM"));
		PLAZE_TIANYU_SINGLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_TIANYU_SINGLE_SLEEPTIME_MM"));
		TIANYU_SF_URL = appConfig.getValue("tianyu.antifraud.plaze.webservice");
	}

	@Override
	public void run() {
	 while (true) {
	   try{		
		   
			try {
				Thread.sleep(PLAZE_TIANYU_THREAD_SLEEPTIME_MM);
			} catch (InterruptedException e) {

			}
			List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, tyTaskQueryNum);

			if (taskStatusList != null && taskStatusList.size() > 0) {
				int size = taskStatusList.size();
				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 腾讯天御评分] 查询到腾讯天御评分任务条数：{}", size);
				}
				for (TaskCallStatus task : taskStatusList) {
					int tyAntifraudCount = tyAntifraudService.getCountByAppId(task.getAppId());
					if (tyAntifraudCount > 0) {
						continue;
					}
					String appId = task.getAppId();
					// 构建请求参数

					boolean paramErrorFlag = false;// 参数错误标识
					if (task.getCertNo() == null || "".equals(task.getCertNo())) {
						paramErrorFlag = true;
						if (logger.isDebugEnabled()) {
							logger.debug("[客户端 & 天御分] 请求的证件号为空，流水号为：{}", appId);
						}
					}
					if (task.getMobile() == null || "".equals(task.getMobile())) {
						paramErrorFlag = true;
						if (logger.isDebugEnabled()) {
							logger.debug("[客户端 & 天御分] 请求的电话号码为空，流水号为：{}", appId);
						}
					}
					String trnId = task.getTaskId();
					// 插入历史表
					taskCallStatusService.saveTaskStatusHistory(appId, taskType);
					if (paramErrorFlag) {
						// 参数异常
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, taskType, null,
								TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
					// 请求开始
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null,
							TaskStatusUtil.CURR_USER, "1", null);

					boolean requestFlag = false;// 内部请求是否正常标识
					Object[] obj2 = null;
					Client client = null;
					try {
						// 组装请求报文
						JSONObject jsonObjectReq = new JSONObject();
						JSONObject responseObjectReq = new JSONObject();

						JSONObject responseHeadObjectReq = new JSONObject();
						responseHeadObjectReq.put("REQUEST_CHANNEL", "CAMS");
						responseHeadObjectReq.put("TRN_ID", trnId);
						responseHeadObjectReq.put("TRN_CODE", "WST001000");

						responseObjectReq.put("HEAD", responseHeadObjectReq);

						JSONObject responseBodyObjectReq = new JSONObject();
						responseBodyObjectReq.put("QUERY_MODE", "1");
						responseBodyObjectReq.put("CERT_TYPE", task.getCertType());
						responseBodyObjectReq.put("CERT_NO", task.getCertNo());
						responseBodyObjectReq.put("NAME", task.getName());
						responseBodyObjectReq.put("MOBILE", task.getMobile());

						responseObjectReq.put("BODY", responseBodyObjectReq);

						jsonObjectReq.put("REQUEST", responseObjectReq);


						URL url = new URL(TIANYU_SF_URL);
						HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
						httpConnection.setReadTimeout(120000);// 设置http连接的读超时，单位是毫秒
						httpConnection.connect();
						client = new Client(httpConnection.getInputStream(), null);
						client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "120000");// 设置发送超时限制，单位是毫秒
						client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
						client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
						String param = jsonObjectReq.toString();
						Object[] obj = new Object[] { param };
						obj2 = client.invoke("invoke", obj);
					} catch (Exception e) {
						requestFlag = true;
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 查询到腾讯天御评分] 请求处理异常  " + appId + " Exception:{}", e);
						}
					} finally {
						if (client != null) {
							client.close();
							client = null;
						}
				    }
					// 插入历史表
					taskCallStatusService.saveTaskStatusHistory(appId, taskType);
					if ( requestFlag ) {
						// 内部服务异常
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
								taskType, null, TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
					boolean responseFlag = false; //报文返回是否异常
					try {
						// 保存请求参数信息
						Map<String, String> params = new HashMap<>();
						params.put("appId", appId);
						params.put("mobile", task.getMobile());
						params.put("certNo", task.getCertNo());
						params.put("name", task.getName());
						getResult(obj2[0].toString(), params);
					}catch(Exception e){
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 查询到腾讯天御评分] 解析异常getResult" + appId + " Exception:{}", e);
						}
						 responseFlag = true;
					}
					if( responseFlag ){
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, taskType, null,
								TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
					
					try {
						Thread.sleep(PLAZE_TIANYU_SINGLE_SLEEPTIME_MM);
					} catch (InterruptedException e) {

					}
				}

			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 腾讯天御评分] 未查询到腾讯天御评分任务条数!");
				}
			}
	}catch(Exception e){
		if (logger.isErrorEnabled()) {
			logger.error("[客户端 & 腾讯天御评分] 线程异常中断 重启  Exception:{}", e);
		}
	}
	}
	}

	public void getResult(String json, Map<String, String> params) {
		if (json != null && !"".equals(json)) {
			// 解析响应消息并获取响应报文
			Map<String, String> response = parseResponseInfo(json);
			String appId = params.get("appId");
			String responseCode = response.get("response");
			if("999999".equals(responseCode)){
				taskCallStatusService.saveTaskStatusHistory(appId, taskType);
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
						taskType, null, TaskStatusUtil.CURR_USER, null, "1");
				return;
			}
			// 报文处理
			TYAntifraud tyAntifraud = parseTYAntifraud(response.get("responseBody"), appId);
			try {
				if (tyAntifraud != null) {
					tyAntifraudService.saveTYAntiUpdateDataAdapterAction(tyAntifraud, appId, taskType);
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 腾讯天御分] 报文数据持久化成功, 申请件编号: {}", appId);
					}
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS, taskType, null,
							TaskStatusUtil.CURR_USER, null, "1");
				}else{
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, taskType, null,
							TaskStatusUtil.CURR_USER, null, "1");
				}
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("[客户端 & 腾讯天御分] 报文数据持久化失败   " + appId + " Exception:{}", e);
				}
				// 入库异常
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
			}
		}
	}

	/**
	 * @Title:parseTYAntifraud
	 * @Description:腾讯天御分解析报文
	 * @param message
	 * @param appId
	 * @return
	 * @author: gaohui
	 * @Date:2018年2月8日下午5:13:31
	 */
	public TYAntifraud parseTYAntifraud(String message, String appId) {

		try {
			if (message == null || "".equals(message)) {
				if (logger.isErrorEnabled()) {
					logger.error("[客户端 & 腾讯天御分] 数据解析异常! {}", "腾讯天御分报文为空");
				}
				return null;
			}
			JSONObject json = JSONObject.fromObject(message);
			if (json.containsKey("success") && !json.getBoolean("success") && json.containsKey("error_code")
					&& "999999".equals(json.getString("error_code"))) {
				if (logger.isWarnEnabled()) {
					logger.warn("腾讯天御分信息查询Proxy响应失败, 申请件编号: {}", appId);
				}
				// 对方服务响应异常
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
				return null;
			}
			TYAntifraud tyAntifraud = new TYAntifraud();
			TYAntifraudData tyAntifraudData = new TYAntifraudData();
			tyAntifraudData.setAppId(appId);
			// 响应代码 0成功 非0是具体错误代码
			if (json.containsKey("code") && json.getString("code") != null && !"".equals(json.getString("code"))) {
				tyAntifraudData.setCode(json.getString("code"));
			} else {
				// 响应码异常
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.RESPOSE_CODE_ERROR, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
				return null;
			}
			// 响应结果说明
			if (json.containsKey("codeDesc") && json.getString("codeDesc") != null
					&& !"".equals(json.getString("codeDesc"))) {
				tyAntifraudData.setCodeDesc(json.getString("codeDesc"));
			}
			// 表示该条记录能否查到,1为能查到，-1为查不到
			if (json.containsKey("found") && json.getString("found") != null && !"".equals(json.getString("found"))) {
				tyAntifraudData.setFound(Integer.parseInt(json.getString("found")));
			}
			// 表示该条记录中的身份证能否查到,1为能查到，-1为查不到
			if (json.containsKey("idFound") && json.getString("idFound") != null
					&& !"".equals(json.getString("idFound"))) {
				tyAntifraudData.setIdFound(Integer.parseInt(json.getString("idFound")));
			}
			// 查询信息描述
			if (json.containsKey("message") && json.getString("message") != null
					&& !"".equals(json.getString("message"))) {
				tyAntifraudData.setMessage(json.getString("message"));
			}
			// 0~100：欺诈分值。值越高欺诈可能性越大
			if (json.containsKey("riskScore") && json.getString("riskScore") != null
					&& !"".equals(json.getString("riskScore"))) {
				tyAntifraudData.setRiskScore(Integer.parseInt(json.getString("riskScore")));
			}
			tyAntifraud.setTyAntifraudData(tyAntifraudData);
			// 主表 关联风险码信息
			if (json.containsKey("riskInfo") && json.getString("riskInfo") != null
					&& !"".equals(json.getString("riskInfo"))) {
				JSONArray riskInfoArray = JSONArray.fromObject(json.get("riskInfo"));
				List<TYAntifraudRiskInfo> tyRiskInfoList = new ArrayList<TYAntifraudRiskInfo>();
				for (int i = 0; i < riskInfoArray.size(); i++) {
					TYAntifraudRiskInfo tyRiskInfo = new TYAntifraudRiskInfo();
					JSONObject riskObject = (JSONObject) riskInfoArray.get(i);
					int addControl = 0;
					if (riskObject.containsKey("riskCode") && riskObject.getString("riskCode") != null
							&& !"".equals(riskObject.getString("riskCode"))) {
						tyRiskInfo.setRiskCode(riskObject.getString("riskCode"));
						addControl++;
					}
					if (riskObject.containsKey("riskCodeValue") && riskObject.getString("riskCodeValue") != null
							&& !"".equals(riskObject.getString("riskCodeValue"))) {
						tyRiskInfo.setRiskCodeValue(riskObject.getString("riskCodeValue"));
						addControl++;
					}
					if (addControl > 0) {
						tyRiskInfo.setAppId(appId);
						tyRiskInfoList.add(tyRiskInfo);
					}
				}
				if (tyRiskInfoList != null && tyRiskInfoList.size() > 0) {
					tyAntifraud.setListTYAntifraudRiskInfo(tyRiskInfoList);
				}

			}
			return tyAntifraud;
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 & 腾讯天御分] 数据解析异常   " + appId + " Exception:{}", e);
			}
			// 解析异常
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, taskType, null,
					TaskStatusUtil.CURR_USER, null, "1");
			return null;
		}
	}

	/**
	 * @Title:parseResponseInfo
	 * @Description:解析返回报文信息
	 * @param ResponseInfo
	 * @return
	 * @author: wss
	 * @Date:2019年3月27日下午4:42:59
	 */
	public Map<String, String> parseResponseInfo(String ResponseInfo) {
		// 分解返回参数
		Map<String, String> response = new HashMap<>();
		JSONObject jsonResponse = JSONObject.fromObject(ResponseInfo);
		if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
				&& !"".equals(jsonResponse.getString("RESPONSE"))) {
			JSONObject jsonRes = JSONObject.fromObject(jsonResponse.getString("RESPONSE"));

			if (jsonRes.containsKey("HEAD") && jsonRes.getString("HEAD") != null
					&& !"".equals(jsonRes.getString("HEAD"))) {
				JSONObject jsonHead = JSONObject.fromObject(jsonRes.getString("HEAD"));
				if (jsonHead.containsKey("TRN_ID") && jsonHead.getString("TRN_ID") != null
						&& !"".equals(jsonHead.getString("TRN_ID"))) {
					response.put("trnId", jsonHead.getString("TRN_ID"));
				}
				if (jsonHead.containsKey("RESPONSE_CHANNEL") && jsonHead.getString("RESPONSE_CHANNEL") != null
						&& !"".equals(jsonHead.getString("RESPONSE_CHANNEL"))) {
					response.put("rpChannel", jsonHead.getString("RESPONSE_CHANNEL"));
				}
			}

			if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
					&& !"".equals(jsonRes.getString("BODY"))) {
				JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
				if (jsonBody.containsKey("RESPONSE_BODY") && jsonBody.getString("RESPONSE_BODY") != null
						&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
					response.put("responseBody", jsonBody.getString("RESPONSE_BODY"));
				}
				if (jsonBody.containsKey("RESPONSE_CODE") && jsonBody.getString("RESPONSE_CODE") != null
						&& !"".equals(jsonBody.getString("RESPONSE_CODE"))) {
					response.put("responseCode", jsonBody.getString("RESPONSE_CODE"));
				}
				if (jsonBody.containsKey("RESPONSE_DESC") && jsonBody.getString("RESPONSE_DESC") != null
						&& !"".equals(jsonBody.getString("RESPONSE_DESC"))) {
					response.put("responseDesc", jsonBody.getString("RESPONSE_DESC"));
				}
			}

		}
		return response;
	}

}
