package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * 数字解读
 *
 */
public class WST000101TaskWorker implements Runnable {
	
private final static Logger logger = LoggerFactory.getLogger(WST000101TaskWorker.class);
	
	private final static AppConfig appConfig = AppConfig.getInstance();
	
	private TaskCallStatusService taskCallStatusService;
	private final Integer         querySize;
	private final String          taskType;
	
	public WST000101TaskWorker() {
		this.taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		this.querySize             = Integer.valueOf(appConfig.getValue("szjd.query_size"));
		this.taskType              = appConfig.getValue("szjd.task_type");
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
				
				/** 根据任务类型,查询条数,查询出需要发起杭州区域数据查询的任务 */
				List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);
				
				if (taskStatusList != null && taskStatusList.size() > 0) {
					
					for (TaskCallStatus task : taskStatusList) {
						
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
						
						String appId    = task.getAppId   ();   //申请件编号
						String name     = task.getName    ();   //姓名
						String certNo   = task.getCertNo  ();   //证件号
						String certType = task.getCertType();   //证件类型
						String mobile   = task.getMobile  ();   //手机号
						String trnId    = task.getAppId   ();   //appid映射成trnId
						
						/** 请求参数错误标识 */
						boolean paramErrorFlag = false;
						if(name == null || "".equals(name)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 数字解读] 请求的姓名为空，流水号为：{}", appId);
							}
						}
						if(certNo == null || "".equals(certNo)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 数字解读] 请求的证件号为空，流水号为：{}", appId);
							}
						}
						if(certType == null || "".equals(certType)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 数字解读] 请求的手机号为空，流水号为：{}", appId);
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
						head.put("TRN_CODE", appConfig.getValue("szjd.trn.code"));
						
						Map<String, Object> body = new HashMap<String, Object>();
						body.put("QUERY_MODE", "3");
						body.put("CERT_TYPE", certType);
						body.put("CERT_NO", certNo);
						body.put("NAME", name);
						body.put("MOBILE", mobile);
						body.put("QUERY_REASON", appConfig.getValue("szjd.query.reason"));
						
						/** 获取当前时间前一个月最后一天作为product_date */
						Calendar cal = Calendar.getInstance();
						cal.set(Calendar.DAY_OF_MONTH, 0);
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						String productDate = sdf.format(cal.getTime());
						
						body.put("PRODUCT_DATE", productDate);

						Map<String, Object> request = new HashMap<String, Object>();
						request.put("HEAD", head);
						request.put("BODY", body);

						Map<String, Object> params = new HashMap<String, Object>();
						params.put("REQUEST", request);

						JSONObject json = JSONObject.fromObject(params);
						String paramsStr = json.toString();
						
						Client client = null;
						Object[] result = null;
						
						try {
							URL url = new URL(appConfig.getValue("general.plaze.webservice.url"));
							HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
							httpConnection.setReadTimeout(
									Integer.parseInt(appConfig.getValue("szjd.plaze.webservice.read_timeout")));// 设置http连接的读超时，单位是毫秒
							httpConnection.connect();
							client = new Client(httpConnection.getInputStream(), null);
							// 设置发送超时限制，单位是毫秒
							client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,
									appConfig.getValue("szjd.plaze.webservice.http_timeout"));
							client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
							client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
							result = client.invoke("invoke", new Object[] { paramsStr });
						} catch (Exception e) {
							e.printStackTrace();
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 数字解读] 申请件[{}]调用第三方查询平台服务异常:{}", appId, e);
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
						
						/** 开始解析报文,获取返回码 */
						String responseCode = "";
						try {
							JSONObject jsonResponse = JSONObject.fromObject(String.valueOf(result[0]));
							if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
									&& !"".equals(jsonResponse.getString("RESPONSE"))) {
								JSONObject jsonRes = JSONObject.fromObject(jsonResponse.getString("RESPONSE"));
								if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
										&& !"".equals(jsonRes.getString("BODY"))) {
									JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
									if (jsonBody.containsKey("RESPONSE_CODE") && jsonBody.getString("RESPONSE_CODE") != null
											&& !"".equals(jsonBody.getString("RESPONSE_CODE"))) {
										responseCode = jsonBody.getString("RESPONSE_CODE");
									}
								}
							}
							
							if("999999".equals(responseCode)){
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							
						}catch (Exception e1) {
							/** 报文解析异常 */
							e1.printStackTrace();
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						
					}
					
				}else{
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 数字解读] 未查询到数字解读任务条数!");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("数字解读线程异常,准备重启",e);
			}
			
		}
		
		
	}
	
}
