package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.BRZX;
import com.huaxia.opas.domain.TaskRequestInfo;
import com.huaxia.opas.handler.message.BRZXMessageOperator;
import com.huaxia.opas.handler.message.MessageOperator;
import com.huaxia.opas.service.BRZXService;
import com.huaxia.opas.service.TaskRequestInfoService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 
 * 百融征信
 *
 */
public class WST000400TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WST000400TaskWorker.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	private TaskCallStatusService taskCallStatusService;

	private BRZXService brzxService;

	private TaskRequestInfoService taskRequestInfoService;
	private Integer PLAZE_BRZX_THREAD_SLEEPTIME_MM;
	private Integer PLAZE_BRZX_SINGLE_SLEEPTIME_MM;
	private String BRZX_SF_URL;
	private final Integer querySize;
	private final String taskType = "000400";

	// 报文处理
	private static MessageOperator<BRZX> messageOperator = new BRZXMessageOperator();
	// 报文存储路径
	// private String xmlSavePath;

	public WST000400TaskWorker() {
		// 获取配置参数
		querySize = Integer.valueOf(appConfig.getValue("PLAZE_BRZX_QUERY_SIZE"));

		// 初始化服务接口
		this.taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		this.brzxService = (BRZXService) SpringContextUtil.getBean("brzxService");
		this.taskRequestInfoService = (TaskRequestInfoService) SpringContextUtil.getBean("taskRequestInfoService");
		PLAZE_BRZX_THREAD_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_BRZX_THREAD_SLEEPTIME_MM"));
		PLAZE_BRZX_SINGLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_BRZX_SINGLE_SLEEPTIME_MM"));
		BRZX_SF_URL = appConfig.getValue("bairong.sp.plaze.webservice");
		// xmlSavePath = appConfig.getValue("PLAZE_BRZX_HTTP_XML_PATH");
	}

	@Override
	public void run() {
	 while (true) {
		try{	
		
			try {
				Thread.sleep(PLAZE_BRZX_THREAD_SLEEPTIME_MM);
			} catch (InterruptedException e) {

			}
			List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);

			if (taskStatusList != null && taskStatusList.size() > 0) {
				int size = taskStatusList.size();

				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 百融征信] 查询百融任务条数：{}", size);
				}

				// if (!isSkipCheck() || size > 0) {
				// if (ModeSwitch.ON.name().equals(getModeSwitch())) {
				for (TaskCallStatus task : taskStatusList) {
					int brzxCount = brzxService.selectAllTaskCountByAppId(task.getAppId());
					if (brzxCount > 0) {
						continue;
					}
					String appId = task.getAppId();
					// 更新任务请求信息状态标志
					TaskRequestInfo taskRequestInfo = new TaskRequestInfo();
					taskRequestInfo.setAppId(appId);
					taskRequestInfo.setReqBRZX("1");
					taskRequestInfoService.update(taskRequestInfo);
					// 插入历史表
					taskCallStatusService.saveTaskStatusHistory(appId, taskType);
					// 请求开始
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null,
							TaskStatusUtil.CURR_USER, "1", null);
					boolean requestFlag = false;// 内部请求是否正常标识
					Client client = null;
					Object[] obj2 = null;
					try {
						// 组装请求报文
						JSONObject jsonObjectReq = new JSONObject();
						JSONObject responseObjectReq = new JSONObject();

						JSONObject responseHeadObjectReq = new JSONObject();
						responseHeadObjectReq.put("REQUEST_CHANNEL", "CAMS");
						responseHeadObjectReq.put("TRN_ID", task.getTaskId());
						responseHeadObjectReq.put("TRN_CODE", "WST000400");

						responseObjectReq.put("HEAD", responseHeadObjectReq);

						JSONObject responseBodyObjectReq = new JSONObject();
						responseBodyObjectReq.put("QUERY_MODE", "1");
						responseBodyObjectReq.put("CERT_TYPE", task.getCertType());
						responseBodyObjectReq.put("CERT_NO", task.getCertNo());
						responseBodyObjectReq.put("NAME", task.getName());
						responseBodyObjectReq.put("MOBILE", task.getMobile());

						responseObjectReq.put("BODY", responseBodyObjectReq);

						jsonObjectReq.put("REQUEST", responseObjectReq);

							URL url = new URL(BRZX_SF_URL);
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
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 百融征信] 请求处理异常  " + appId + " Exception:{}", e);
							}
							requestFlag = true;
						} finally {
							if (client != null) {
								client.close();
								client = null;
							}
						}
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						if( requestFlag ){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						boolean responseFlag = false; //报文返回是否异常
						try{
							// 保存请求参数信息
							Map<String, String> params = new HashMap<>();
							params.put("appId", appId);
							params.put("mobile", task.getMobile());
							params.put("certNo", task.getCertNo());
							params.put("name", task.getName());
							getResult(obj2[0].toString(), params);
						}catch(Exception e){
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 百融征信] 解析异常getResult" + appId + " Exception:{}", e);
							}
							responseFlag = true ;
						}
						if( responseFlag ){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
					 
					try {
						Thread.sleep(PLAZE_BRZX_SINGLE_SLEEPTIME_MM);
					} catch (InterruptedException e) {

					}
				}
				/*
				 * } else if (ModeSwitch.OFF.name().equals(getModeSwitch())) {
				 * fileOperator.invoke(); }
				 */
				// }
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 百融征信] 未查询到百融任务条数!");
				}
			}
			
	}catch(Exception e){
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 &  百融征信] 线程异常中断 重启  Exception:{}", e);
			}
	}
	}
	}

	/**
	 * @Title:getResult
	 * @Description:解析入库三方返回报文
	 * @param json
	 * @param params
	 * @author: wss
	 * @Date:2019年4月3日上午10:35:54
	 */
	public void getResult(String json, Map<String, String> params) {
		if (json == null || "".equals(json)) {
			if (logger.isWarnEnabled()) {
				logger.warn("[客户端 & BRZX] 接收APS响应信息为空!!!");
			}
		} else {
			if (logger.isInfoEnabled()) {
				logger.info("brzxResponseSuccess:{}", "BRZX APS back success!!");
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug("brzxResponseJson:{}", json);
		}

		// 获取appId
		String appId = params.get("appId");

		JSONObject jsonObject = JSONObject.fromObject(json);
		if (!jsonObject.isNullObject() && !jsonObject.isEmpty()) {
			// 审批解析三方返回报文
			String responseCode = "";// 响应代码
			String trnId = ""; // 三方交互id
			String responseBody = "";// 返回报文体

			Map<String, String> response = parseResponseInfo(json);
			trnId = response.get("trnId");
			responseBody = response.get("responseBody");
			responseCode = response.get("responseCode");
			if("999999".equals(responseCode)){
				taskCallStatusService.saveTaskStatusHistory(appId, taskType);
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
						taskType, null, TaskStatusUtil.CURR_USER, null, "1");
				return;
			}
			// CommonUtil.downLoadMessageContent(responseBody, xmlSavePath,
			// appId, null, ".json", "百融征信");
			BRZX brzx = null;
			try {
				brzx = messageOperator.operate(responseBody);
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("[客户端 & 百融征信] 报文数据解析异常   " + appId + " Exception:{}", e);
				}
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
			}
			if (brzx != null) {
				brzx.setAppId(appId);
				if (brzx.getCode() != null) {

					if (!"999999".equals(brzx.getCode())) {
						try {
							brzxService.saveBrzxUpdateDataAdapterAction(brzx, appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS, taskType, null,
									TaskStatusUtil.CURR_USER, null, null);
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 百融征信 ] 报文数据持久化成功, 申请件编号: {}", appId);
							}
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 百融征信] 报文数据入库异常   " + appId + " Exception:{}", e);
							}
							// 入库异常
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE, taskType,
									null, TaskStatusUtil.CURR_USER, null, "1");
						}
					} else {
						// 对方服务响应异常
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
								taskType, null, TaskStatusUtil.CURR_USER, null, "1");
					}

				} else {
					// 响应码异常
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.RESPOSE_CODE_ERROR, taskType,
							null, TaskStatusUtil.CURR_USER, null, "1");

				}
			} else {// 报文没返回
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.NOT_RETURN, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
			}
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
