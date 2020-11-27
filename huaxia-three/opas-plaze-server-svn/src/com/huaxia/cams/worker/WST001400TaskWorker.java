package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.huaxia.opas.domain.fico.Fico;
import com.huaxia.opas.handler.message.FicoMessageOperator;
import com.huaxia.opas.handler.message.MessageOperator;
import com.huaxia.opas.service.fico.FicoService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 
 * FICO大数据评分
 *
 */
public class WST001400TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WST001400TaskWorker.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	private TaskCallStatusService taskCallStatusService;

	// 报文处理
	private static MessageOperator<Fico> messageOperator = new FicoMessageOperator();

	private FicoService ficoService;

	private final Integer PLAZE_FICO_THREAD_SLEEPTIME_MM;
	private final Integer PLAZE_FICO_SINGLE_SLEEPTIME_MM;
	private final Integer PLAZE_FICO_MULTIPLE_SLEEPTIME_MM;
	private final Integer querySize;
	private final String taskType = "001400";

	public WST001400TaskWorker() {
		// 初始化配置参数
		querySize = Integer.valueOf(appConfig.getValue("PLAZE_FICO_QUERY_SIZE"));
		// 初始化服务接口
		taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		ficoService = (FicoService) SpringContextUtil.getBean("ficoService");
		PLAZE_FICO_THREAD_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_FICO_THREAD_SLEEPTIME_MM"));
		PLAZE_FICO_SINGLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_FICO_SINGLE_SLEEPTIME_MM"));
		PLAZE_FICO_MULTIPLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_FICO_MULTIPLE_SLEEPTIME_MM"));
	}

	@Override
	public void run() {
		String username = appConfig.getValue("PLAZE_FICO_CLIENTID");
		String password = appConfig.getValue("PLAZE_FICO_PASSWORD");
	 while (true) {
	  try {		
		  
			try {
				Thread.sleep(PLAZE_FICO_MULTIPLE_SLEEPTIME_MM);
			} catch (InterruptedException e) {

			}
			List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);

			if (taskStatusList != null && taskStatusList.size() > 0) {
				int size = taskStatusList.size();

				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & fico大数据] 查询fico大数据任务条数：{}", size);
				}

				for (TaskCallStatus task : taskStatusList) {
					String appId = task.getAppId();
					// 构建请求参数
					String mobile = task.getMobile();
					String certNo = task.getCertNo();
					String crtUser = task.getName();
					String certType = task.getCertType();
					String trnId = task.getTaskId();// 交易编号

					// 构建请求参数
					// 格式：任务类型##,##申请件编号##,##请求参数
					boolean paramErrorFlag = false;// 参数错误标识
					if (certNo == null || "".equals(certNo)) {
						paramErrorFlag = true;
						if (logger.isDebugEnabled()) {
							logger.debug("[客户端 & fico大数据] 请求的证件号为空，流水号为：{}", appId);
						}

					}
					if (mobile == null || "".equals(mobile) || mobile.trim().length() != 11) {
						paramErrorFlag = true;
						if (logger.isDebugEnabled()) {
							logger.debug("[客户端 & fico大数据] 请求的手机号数据异常（空或非11位），流水号为：{}", appId);
						}
					}
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

					// 针对于易达金套的标准卡进行处理
					Map<String, String> paramsJudge = new HashMap<String, String>();
					paramsJudge.put("appId", appId);
					Map<String, String> judgeParam = ficoService.selectBizInpApplC1JudgeFlag(paramsJudge);
					String appIdJudge = appId;
					if (judgeParam != null) {
						String ydjFlag = judgeParam.get("YDJ_FLAG");
						String matchingCardFlag = judgeParam.get("MATCHING_CARD_FLAG");
						if ("1".equals(ydjFlag)) {// 针对易达金卡
							if (!"1".equals(matchingCardFlag)) {// 若不是易达金配的附属卡
								String weihao = appId.substring(15, 16);
								if ("1".equals(weihao)) {
									appIdJudge = appId.substring(0, 15) + "2";
								} else {
									appIdJudge = appId.substring(0, 15) + "1";
								}
							}
						}
					}
					// 注意 ： 针对于易达金卡 查的pbocFlag这个标识 是 查其配发的标准卡的
					Fico fico = ficoService.selectConfirmPboc(appIdJudge);
					String pbocFlag = "und";
					if (fico != null) {

						pbocFlag = fico.getPboc();
						if (pbocFlag != null && "0".equals(pbocFlag)) {
							pbocFlag = "false";
						}
						if (pbocFlag != null && "1".equals(pbocFlag)) {
							pbocFlag = "true";
						}
					}
					String mobHeader = mobile.substring(0, 3);
					boolean requestFlag = false;// 内部请求是否正常标识
					Client client = null;
					Object[] result = null;
					try {
						MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
						String inputXML = "<Request clientID=\"" + username + "\" pboc=\"" + pbocFlag
								+ "\" mobHeader=\"" + mobHeader + "\" password=\"" + password
								+ "\" serviceCode=\"" + appConfig.getValue("serviceCode") + "\" idCard=\""
								+ Hex.encodeHexString(messageDigest.digest(certNo.getBytes("UTF-8")))
								+ "\" mobile=\"" + Hex.encodeHexString(messageDigest.digest(mobile.getBytes("UTF-8")))
								+ "\" />";
						// 连接字符串，实现数据库的查询和实现
						Map<String, Object> head = new HashMap<String, Object>();
						head.put("REQUEST_CHANNEL", "CAMS");
						head.put("TRN_ID", trnId);

						Map<String, Object> body = new HashMap<String, Object>();
						body.put("QUERY_MODE", "1");
						body.put("CERT_TYPE", certType);
						body.put("CERT_NO", certNo);
						body.put("NAME", crtUser);
						body.put("MOBILE", mobile);
						body.put("APPID", appId);
						body.put("INPUTXML", inputXML);

						Map<String, Object> request = new HashMap<String, Object>();
						request.put("HEAD", head);
						request.put("BODY", body);

						Map<String, Object> params = new HashMap<String, Object>();
						params.put("REQUEST", request);

						JSONObject json = JSONObject.fromObject(params);
						String paramsStr = JSON.toJSONString(json);
						URL url = new URL(appConfig.getValue("fico.plaze.webservice.url"));
						HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
						// 设置http连接的读超时，单位是毫秒
						httpConnection.setReadTimeout(120000);
						httpConnection.connect();
						client = new Client(httpConnection.getInputStream(), null);
						// 设置发送超时限制，单位是毫秒
						client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, String.valueOf(120000));
						client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
						client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
						result = client.invoke("invoke", new Object[] { paramsStr });
						} catch (Exception e) {
							if (logger.isInfoEnabled()) {
								logger.info("appId=" + appId + "fico的webservice连链接异常", e);
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
					// 解析响应数据入库
					try {
						String message = "";
							// 分解请求参数
							JSONObject jsonResponse = JSONObject.fromObject(String.valueOf(result[0]));
							if (jsonResponse.containsKey("RESPONSE")
									&& jsonResponse.getString("RESPONSE") != null
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
										message = jsonBody.getString("RESPONSE_BODY");
									}
									if (jsonBody.containsKey("APPID") && jsonBody.getString("APPID") != null
											&& !"".equals(jsonBody.getString("APPID"))) {
										appId = jsonBody.getString("APPID");
									}
								}
							}
							
							if("999".equals(message)){
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							
							// 解析入库
							Fico ficoData = null;
							try {
								ficoData = messageOperator.operate(message);
							} catch (Exception e) {
								if (logger.isErrorEnabled()) {
									logger.error("[Fico评分]  数据解析异常   " + appId + " Exception:{}", e);
								}
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							}

							try {
								if (ficoData != null) {
									ficoData.setAppid(appId);
									ficoData.setUuid(GuidUtil.getGuid());
									ficoData.setCrtUser("SYSTEM");
									ficoService.saveFicoUpdateDataAdapterAction(ficoData, appId, taskType);
									taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
											taskType, null, TaskStatusUtil.CURR_USER, null, null);
									if (logger.isDebugEnabled()) {
										logger.debug("[客户端 & fico大数据] 报文数据持久化成功, 申请件编号: {}", appId);
									}
								} else {
									taskCallStatusService.updateSingleTaskStatus(appId,
											TaskStatusUtil.NOT_RETURN, taskType, null, TaskStatusUtil.CURR_USER,
											null, "1");
								}
							} catch (Exception e) {
								if (logger.isErrorEnabled()) {
									logger.error("[Fico评分] 数据入库异常   " + appId + " Exception:{}", e);
								}
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							}

						 
					} catch (Exception e) {
						if (logger.isErrorEnabled()) {
							logger.error("[Fico评分] 数据解析异常   " + appId + " Exception:{}", e);
						}
						responseFlag = true;
					}
					if( responseFlag ){
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
								taskType, null, TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}

					try {
						Thread.sleep(PLAZE_FICO_SINGLE_SLEEPTIME_MM);
					} catch (InterruptedException e) {

					}
				}
				
				
			} else {
				// 未查到数据时延迟
				try {
					Thread.sleep(PLAZE_FICO_THREAD_SLEEPTIME_MM);
				} catch (InterruptedException e) {
					// continue;
				}
				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & fico大数据] 未查询到fico大数据任务条数!");
				}
			}
			
	}catch(Exception e){
		if (logger.isErrorEnabled()) {
			logger.error("[客户端 & fico大数据] 线程异常中断 重启  Exception:{}", e);
		}
	}
	}
	}
	

}
