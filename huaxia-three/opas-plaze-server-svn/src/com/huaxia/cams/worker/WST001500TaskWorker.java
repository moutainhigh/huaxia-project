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

import com.alibaba.fastjson.JSON;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.plaze.modules.tongdun.adapter.MultipleBorrowAdapter;
import com.huaxia.plaze.modules.tongdun.domain.MulBorInfo;
import com.huaxia.plaze.modules.tongdun.service.MultipleBorrowService;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 
 * 多头借贷
 *
 */
public class WST001500TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WST001500TaskWorker.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	private MultipleBorrowService multipleBorrowService;
	private TaskCallStatusService taskCallStatusService;
	private final Integer PLAZE_MULBOR_THREAD_SLEEPTIME_MM;
	private final Integer PLAZE_MULBOR_SINGLE_SLEEPTIME_MM;
	private final Integer PLAZE_MULBOR_MULTIPLE_SLEEPTIME_MM;
	private final Integer querySize;
	private final String taskType;

	public WST001500TaskWorker() {
		this.querySize = Integer.valueOf(appConfig.getValue("tongdun.dtjd.query_size"));
		this.taskType = appConfig.getValue("tongdun.dtjd.task_type");
		this.taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		this.multipleBorrowService = (MultipleBorrowService) SpringContextUtil.getBean("multipleBorrowService");

		PLAZE_MULBOR_THREAD_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("tongdun.dtjd.thread_sleeptime_mm"));
		PLAZE_MULBOR_SINGLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("tongdun.dtjd.single_sleeptime_mm"));
		PLAZE_MULBOR_MULTIPLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("tongdun.dtjd.multiple_sleeptime_mm"));
	}

	@Override
	public void run() {
	 while (true) {
	  try {
			try {
				Thread.sleep(PLAZE_MULBOR_MULTIPLE_SLEEPTIME_MM);
			} catch (InterruptedException e) {
			}

		        List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);
				if (taskStatusList != null && taskStatusList.size() > 0) {
					int size = taskStatusList.size();
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 多头借贷] 查询多头借贷任务条数：{}", size);
					}
					for (TaskCallStatus task : taskStatusList) {
						String appId = task.getAppId();
						String trnId = task.getTaskId(); // 交易编号
						String mobile = task.getMobile();
						String certNo = task.getCertNo();
						String name = task.getName();
						String certType = task.getCertType();

						boolean paramErrorFlag = false;// 参数错误标识
						if (certNo == null || "".equals(certNo)) {
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 多头借贷] 请求的证件号为空，流水号为：{}", appId);
							}
						}
						if (mobile == null || "".equals(mobile) || mobile.trim().length() != 11) {
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 多头借贷] 请求的手机号数据异常（空或非11位），流水号为：{}", appId);
							}
						}
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						if (paramErrorFlag) {
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, taskType,
									null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						// 请求开始
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null,
								TaskStatusUtil.CURR_USER, "1", null);
						Client client = null;
						Object[] result = null;
						boolean requestFlag = false;// 内部请求是否正常标识
						try {
						// 构建多头借贷请求报文
						Map<String, Object> head = new HashMap<String, Object>();
						head.put("REQUEST_CHANNEL", "CAMS");
						head.put("TRN_ID", trnId);
						head.put("TRN_CODE", "WST001500");

						Map<String, Object> body = new HashMap<String, Object>();
						body.put("QUERY_MODE", "1");
						body.put("CERT_TYPE", certType);
						body.put("CERT_NO", certNo);
						body.put("NAME", name);
						body.put("MOBILE", mobile);

						Map<String, Object> request = new HashMap<String, Object>();
						request.put("HEAD", head);
						request.put("BODY", body);

						Map<String, Object> params = new HashMap<String, Object>();
						params.put("REQUEST", request);

						JSONObject json = JSONObject.fromObject(params);
						String paramsStr = JSON.toJSONString(json);

						
						URL url = new URL(appConfig.getValue("tongdun.dtjd.plaze.webservice.url"));
						HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
						httpConnection.setReadTimeout(
								Integer.parseInt(appConfig.getValue("tongdun.dtjd.plaze.webservice.read_timeout")));// 设置http连接的读超时，单位是毫秒
						httpConnection.connect();
						client = new Client(httpConnection.getInputStream(), null);
						// 设置发送超时限制，单位是毫秒
						client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,
								appConfig.getValue("tongdun.dtjd.plaze.webservice.http_timeout"));
						client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
						client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
						result = client.invoke("invoke", new Object[] { paramsStr });
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 多头借贷] 申请件[{}]调用第三方查询平台服务异常:{}"+appId,e);
							}
							requestFlag = true;
						} finally {
							if (client != null) {
								client.close();
								client=null;
							}
						}
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						if( requestFlag ){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						boolean responseFlag = false; //报文返回是否异常
						String returnMessage = null;
						String responseCode = "";
						String responseDesc = "";
						try {
							// 分解请求参数
							JSONObject jsonResponse = JSONObject.fromObject(String.valueOf(result[0]));
							if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
									&& !"".equals(jsonResponse.getString("RESPONSE"))) {
								JSONObject jsonRes = JSONObject.fromObject(jsonResponse.getString("RESPONSE"));

								if (jsonRes.containsKey("HEAD") && jsonRes.getString("HEAD") != null
										&& !"".equals(jsonRes.getString("HEAD"))) {
									JSONObject jsonHead = JSONObject.fromObject(jsonRes.getString("HEAD"));
									if (jsonHead.containsKey("TRN_ID") && jsonHead.getString("TRN_ID") != null
											&& !"".equals(jsonHead.getString("TRN_ID"))) {
										trnId = jsonHead.getString("TRN_ID");
									}
								}
								if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
										&& !"".equals(jsonRes.getString("BODY"))) {
									JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
									if (jsonBody.containsKey("RESPONSE_BODY")
											&& jsonBody.getString("RESPONSE_BODY") != null
											&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
										returnMessage = jsonBody.getString("RESPONSE_BODY");
									}
									if (jsonBody.containsKey("RESPONSE_DESC")
											&& jsonBody.getString("RESPONSE_DESC") != null
											&& !"".equals(jsonBody.getString("RESPONSE_DESC"))) {
										responseDesc = jsonBody.getString("RESPONSE_DESC");
									}
									if (jsonBody.containsKey("RESPONSE_CODE") && jsonBody.getString("RESPONSE_CODE") != null
											&& !"".equals(jsonBody.getString("RESPONSE_CODE"))) {
										responseCode = jsonBody.getString("RESPONSE_CODE");
										// 999998
										// 代表dmz异常，更改任务表状态7，不进行数据解析入库，进行另一个任务
										if (responseCode.equals("999998")) {
											taskCallStatusService.saveTaskStatusHistory(appId, taskType);
											taskCallStatusService.updateSingleTaskStatus(appId,
													TaskStatusUtil.SERVE_RESPOSE_ERROE, taskType, null,
													TaskStatusUtil.CURR_USER, null, "1");
											continue;
										}
										// 三方平台数量达到上限后者时间不在范围内，不进行数据入库，执行另一个任务
										if (responseCode.equals("999994") || responseCode.equals("999993")) {
											taskCallStatusService.saveTaskStatusHistory(appId, taskType);
											taskCallStatusService.updateSingleTaskStatus(appId,
													TaskStatusUtil.QUERY_LIMIT, taskType, null,
													TaskStatusUtil.CURR_USER, null, "1");
											continue;
										}	
									}
								}
							}
							
							if("999".equals(returnMessage)){
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}

							MulBorInfo mulbor = null;
							try {
								mulbor = new MultipleBorrowAdapter().parseMulBorInfo(returnMessage, appId, "PLAZE",
										certNo, mobile, null);
							} catch (Exception e) {
								if (logger.isErrorEnabled()) {
									logger.error("[客户端 & 多头借贷] 申请件[{}]响应报文解析异常:{}"+appId, e);
								}
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;

							}

							try {
								if (mulbor != null) {
									// 更新任务表的状态
									if ("false".equals(mulbor.getMulBorBase().getSuccess())) {
										// 报文响应码 success 为false 调用失败
										taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.RESPOSE_CODE_ERROR,
												taskType, null, TaskStatusUtil.CURR_USER, null, "1");
										continue;

									} else {
										multipleBorrowService.save(mulbor);
										taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
												taskType, null, TaskStatusUtil.CURR_USER, null, null);
									}
								}else{
									taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
											taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								}
							} catch (Exception e) {
								if (logger.isErrorEnabled()) {
									logger.error("[客户端 & 多头借贷] 申请件[{}]响应报文数据持久化异常:{}"+appId, e);
								}
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							}
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 多头借贷] 申请件[{}]响应报文数据持久化异常:{}"+appId, e);
							}
							responseFlag = true;
						}
						if( responseFlag ){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						
						try {
							Thread.sleep(PLAZE_MULBOR_SINGLE_SLEEPTIME_MM);
						} catch (InterruptedException e) {
						}
					}
				} else {
					// 未查到数据时延迟
					try {
						Thread.sleep(PLAZE_MULBOR_THREAD_SLEEPTIME_MM);
					} catch (InterruptedException e) {
					}

					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 多头借贷] 未查询到多头借贷任务条数!");
					}
				}
		 
	}catch(Exception e){
		if (logger.isErrorEnabled()) {
			logger.error("[客户端 & 多头借贷] 线程异常中断 重启  Exception:{}", e);
		}
	}
	}
	}

	
	
	
}
