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
import com.huaxia.cams.modules.unicom.domain.UnicomOnline;
import com.huaxia.cams.modules.unicom.service.UnicomOnlineService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 
 * @author dingguofeng
 * 
 * 在网时长(联通)
 *
 */
public class WST001101TaskWorker implements Runnable {
	
	private final static Logger logger = LoggerFactory.getLogger(WST001101TaskWorker.class);
	
	private final static AppConfig appConfig = AppConfig.getInstance();
	
	private UnicomOnlineService   unicomOnlineService;
	private TaskCallStatusService taskCallStatusService;
	private final Integer         querySize;
	private final String          taskType;
	
	public WST001101TaskWorker(){
		this.unicomOnlineService   = (UnicomOnlineService) SpringContextUtil.getBean("unicomOnlineService");
		this.taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		this.querySize             = Integer.valueOf(appConfig.getValue("unicom.online.query_size"));
		this.taskType              = appConfig.getValue("unicom.online.task_type");
	}

	@Override
	public void run() {

		/** 死循环,重复扫描任务 */
		while(true){
			
			try {
				/** 每次循环前,停顿2秒 */
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				
				/** 根据任务类型,查询条数,查询出需要发起在网时长查询的任务 */
				List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);
				
				if (taskStatusList != null && taskStatusList.size() > 0) {
					
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 在网时长] 查询到在网时长任务条数：{}", taskStatusList.size());
					}
					
					/** 遍历集合,逐条发起查询请求 */
					for (TaskCallStatus task : taskStatusList) {
						
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
						
						int countOnline = unicomOnlineService.countByAppId(task.getAppId());
						/** 如果数据表存在该条appId的数据,跳过不发起查询 */
						if(countOnline > 0){
							continue;
						}
						
						String appId    = task.getAppId ();   //申请件编号
						String name     = task.getName  ();   //姓名
						String certNo   = task.getCertNo();   //证件号
						String certType = task.getCertType(); //证件类型
						String mobile   = task.getMobile();   //手机号
						String trnId    = task.getTaskId();   //trnId
						
						/** 请求参数错误标识 */
						boolean paramErrorFlag = false;
						if(name == null || "".equals(name)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 在网时长] 请求的姓名为空，流水号为：{}", appId);
							}
						}
						if(certNo == null || "".equals(certNo)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 在网时长] 请求的证件号为空，流水号为：{}", appId);
							}
						}
						if(mobile == null || "".equals(mobile)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 在网时长] 请求的手机号为空，流水号为：{}", appId);
							}
						}
						
						/** 请求参数判断完成后,将任务表待查询数据存入历史表,准备发起第三方查询,
						 *  如果参数错误,更改状态 */
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						if(paramErrorFlag){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, taskType,
									null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						
						/** 开始发起第三方查询请求,更改状态 0 → 1 */
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null,
								TaskStatusUtil.CURR_USER, "1", null);
						
						/** 拼接请求参数 */
						Map<String, Object> head = new HashMap<String, Object>();
						head.put("REQUEST_CHANNEL", "CAMS");
						head.put("TRN_ID", trnId);
						head.put("TRN_CODE", "WST001101");
						
						Map<String, Object> body = new HashMap<String, Object>();
						body.put("QUERY_MODE", "3");
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
						
						Client client = null;
						Object[] result = null;
						
						try {
							URL url = new URL(appConfig.getValue("unicom.online.plaze.webservice.url"));
							HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
							httpConnection.setConnectTimeout(
									Integer.parseInt(appConfig.getValue("unicom.online.plaze.webservice.connection_timeout")));// 设置http连接的读超时，单位是毫秒
							httpConnection.connect();
							client = new Client(httpConnection.getInputStream(), null);
							// 设置发送超时限制，单位是毫秒
							client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,
									appConfig.getValue("unicom.online.plaze.webservice.http_timeout"));
							client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
							client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
							result = client.invoke("invoke", new Object[] { paramsStr });
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 联通在网时长] 申请件[{}]调用第三方查询平台服务异常:{}", appId, e);
							}
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						} finally {
							if (client != null) {
								client.close();
							}
						}
						
						String returnMessage = "";
						String carrier = "";
						String responseCode = "";
						/** 开始解析三方平台返回来的响应文件 */
						/** 解析报文,将在网时长信息封装到实体类中 */
						UnicomOnline unicomOnline = new UnicomOnline();
						try {
							JSONObject jsonResponse = JSONObject.fromObject(String.valueOf(result[0]));
							if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
									&& !"".equals(jsonResponse.getString("RESPONSE"))) {
								JSONObject jsonRes = JSONObject.fromObject(jsonResponse.getString("RESPONSE"));

								if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
										&& !"".equals(jsonRes.getString("BODY"))) {
									JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
									if (jsonBody.containsKey("RESPONSE_BODY")
											&& jsonBody.getString("RESPONSE_BODY") != null
											&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
										returnMessage = jsonBody.getString("RESPONSE_BODY");
									}
									if (jsonBody.containsKey("CARRIER")
											&& jsonBody.getString("CARRIER") != null
											&& !"".equals(jsonBody.getString("CARRIER"))) {
										carrier = jsonBody.getString("CARRIER");
									}
									if (jsonBody.containsKey("RESPONSE_CODE")
											&& jsonBody.getString("RESPONSE_CODE") != null
											&& !"".equals(jsonBody.getString("RESPONSE_CODE"))) {
										responseCode = jsonBody.getString("RESPONSE_CODE");
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
							
							/** 如果返回码是999999,说明是dmz请求数据源异常后拼接返回的报文 */
							if("999999".equals(responseCode)){
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							
							unicomOnline.setCrtUser(TaskStatusUtil.CURR_USER);
							unicomOnline.setAppId(appId);
							unicomOnline.setCarrier(carrier);
							JSONObject js = JSONObject.fromObject(returnMessage);
							if(js.containsKey("status") && !"".equals(js.getString("status")) && js.getString("status") != null){
								unicomOnline.setStatus(js.getString("status"));
							}
							if(js.containsKey("code") && !"".equals(js.getString("code")) && js.getString("code") != null){
								unicomOnline.setCode(js.getString("code"));
							}
							if(js.containsKey("errorDesc") && !"".equals(js.getString("errorDesc")) && js.getString("errorDesc") != null){
								unicomOnline.setErrorDesc(js.getString("errorDesc"));
							}
							if(js.containsKey("checkResult") && !"".equals(js.getString("checkResult")) && js.getString("checkResult") != null && !"null".equals(js.getString("checkResult"))){
								unicomOnline.setCheckResult(js.getString("checkResult"));
							}
						} catch (Exception e1) {
							/** 报文解析异常 */
							e1.printStackTrace();
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						
						/** 判断code码的值,来决定流程是否继续流转 */
						if(unicomOnline.getCode() != null && ("00".equals(unicomOnline.getCode()) || "14".equals(unicomOnline.getCode()) || "16".equals(unicomOnline.getCode()))){
							try {
								unicomOnlineService.saveUnicomOnline(unicomOnline);
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
										taskType, null, TaskStatusUtil.CURR_USER, null, null);
							} catch (Exception e) {
								/** 入库异常 */
								e.printStackTrace();
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
						} else {
							/** 如果不是00,14,16流程停止,状态显示响应码异常 */
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.RESPOSE_CODE_ERROR,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						
						
					}
					
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 在网时长] 未查询到在网时长任务条数!");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("联通在网时长线程异常,准备重启",e);
			}
			
		}
		
	}

}
