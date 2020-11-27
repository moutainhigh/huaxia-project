package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.huaxia.cams.modules.police.domain.NciicForeignerInfo;
import com.huaxia.cams.modules.police.service.PoliceForeignerService;
import com.huaxia.cams.modules.police.util.ResponseParseForeignerHelper;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

/**
 * 外国人永久居留证信息
 * @author User
 */
public class WST000202TaskWorker implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(WST000202TaskWorker.class);
	private static final AppConfig appConfig = AppConfig.getInstance();
	
	private TaskCallStatusService taskCallStatusService;
	private PoliceForeignerService policeForeignerService;
	private  String taskType;
	private Integer querySize;

	public WST000202TaskWorker(){
		//获取配置参数
		taskType = appConfig.getValue("police.foreigner.task_type");
		querySize = Integer.valueOf(appConfig.getValue("police.foreigner.query_size"));
		//初始化服务接口
		taskCallStatusService = (TaskCallStatusService)SpringContextUtil.getBean("taskCallStatusService");
		policeForeignerService = (PoliceForeignerService)SpringContextUtil.getBean("policeForeignerService");
	}
	@Override
	public void run() {
		while(true){
			try {
				try {
					Thread.sleep(2000);
				} catch (Exception e) {
				}
				List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);
				if(taskStatusList != null && taskStatusList.size()>0){
					logger.info("[外国人永久居留证] 查询到的任务条数={}", taskStatusList.size());
					for(TaskCallStatus task :taskStatusList){
						String appId = task.getAppId(); 	//申请件编号
						String name = task.getName();		//姓名
						String certNo = task.getCertNo();	//证件号
						String birth = null;				//出生日期
						String expiryDate = null;			//证件有效期
						//验证请求参数
						boolean paramFlag = false;
						if(StringUtils.isBlank(name)){
							paramFlag = true;
							if(logger.isInfoEnabled()){
								logger.info("【外国人永久居留证】 请求的姓名为空，流水号为:{}",appId);
							}
						}
						if(StringUtils.isBlank(certNo)){
							paramFlag = true;
							if(logger.isInfoEnabled()){
								logger.info("[外国人永久居留证] 请求的证件号为空，流水号为:{}",appId);
							}
						}
						//从OPAS_BIZ_INP_APPL_C1表查询出生日期和证件有效期
						Map<String,String> map = policeForeignerService.getBirthAndIDTE(task.getAppId());
						if(map == null || map.size() == 0){
							paramFlag = true;
							if(logger.isInfoEnabled()){
								logger.info("【外国人永久居留证】 请求OPAS_BIZ_INP_APPL_C1的出生日期和证件有效期为空，流水号为:{}",appId);
							}
						}else{
							Set keys = map.keySet();
							Iterator iterator = keys.iterator();
							while(iterator.hasNext()){
								String key = (String) iterator.next();
								if(key!=null && key.equals("BIRTH")){
									birth = map.get(key);
								}
								if(key!=null && key.equals("EXPIRY_DATE")){
									expiryDate = map.get(key);
								}
							}
						}
						if(StringUtils.isBlank(birth)){
							paramFlag = true;
							if(logger.isInfoEnabled()){
								logger.info("【外国人永久居留证】 请求的出生日期为空，流水号为:{}",appId);
							}
						}
						if(StringUtils.isBlank(expiryDate)){
							paramFlag = true;
							if(logger.isInfoEnabled()){
								logger.info("【外国人永久居留证】 请求的证件有效期为空，流水号为:{}",appId);
							}
						}
						//将任务表待查数据存入历史表
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						if(paramFlag){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null, TaskStatusUtil.CURR_USER, "1", null);
						boolean requestFlag = false;// 内部请求是否正常标识
						Client client = null;
						Object[] result = null;
						URL url = null;
						
						String queryParam = getRequestJson(task,birth,expiryDate);
						try {
							url = new URL(appConfig.getValue("police.foreigner.plaze.webservice.url"));
							HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
							httpConnection.setReadTimeout(
									Integer.parseInt(appConfig.getValue("police.foreigner.plaze.webservice.read_timeout")));// 设置http连接的读超时，单位是毫秒
							httpConnection.connect();
							client = new Client(httpConnection.getInputStream(), null);
							// 设置发送超时限制，单位是毫秒
							client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,
									appConfig.getValue("police.foreigner.plaze.webservice.http_timeout"));
							client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
							client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
							Object[] obj = new Object[]{queryParam};
							result = client.invoke("invoke",obj);
						
						} catch (Exception e) {
							requestFlag = true;
							if(logger.isErrorEnabled()){
								logger.error("【外国人永久居留证】 请求处理异常"+appId+"Exception:{}", e);
							}
						}finally{
							if(client != null){
								client.close();
								client = null;
							}
						}
						//入任务历史表
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						if(requestFlag){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						boolean responseFlag = false; //返回报文是否异常
						try {
							responseParse( String.valueOf( result[0] ), appId,task.getCertType());
						} catch (Exception e) {
							if(logger.isErrorEnabled()){
								logger.error("【外国人永久居留证】解析异常responseParse"+appId+"Exception:{}", e);
							}
							responseFlag = true;
						}
						if(responseFlag){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						//单条查询间隔时间
						try {
							Thread.sleep(20);
						} catch (Exception e) {
						}
					
					}
					
				}else{
					logger.debug("[公安外国人永久居留证] 未查询到外国人永久居留证任务条数！！");
				}
			} catch (Exception e) {
				if(logger.isErrorEnabled()){
					logger.error("[公安外国人永久居留证] 线程异常中断 重启Exception:{}", e);
				}
			}
		}
	}
	
	private void responseParse(String str, String appId,String certType) {

		JSONObject json = JSONObject.parseObject(str);
		String code = "";
		String message = null;
		if (json.containsKey("RESPONSE") && !"".equals(json.getString("RESPONSE"))) {
			JSONObject jsonRequest = JSONObject.parseObject(json.getString("RESPONSE"));
			if (jsonRequest.containsKey("BODY") && !"".equals(jsonRequest.getString("BODY"))) {
				JSONObject jsonRequestBody = JSONObject.parseObject(jsonRequest.getString("BODY"));
				if (jsonRequestBody.containsKey("RESPONSE_BODY")
						&& jsonRequestBody.getString("RESPONSE_BODY") != null
						&& !"".equals(jsonRequestBody.getString("RESPONSE_BODY"))) {
					message = jsonRequestBody.getString("RESPONSE_BODY");
				}
				if (jsonRequestBody.containsKey("RESPONSE_CODE") && jsonRequestBody.getString("RESPONSE_CODE") != null
						&& !"".equals(jsonRequestBody.getString("RESPONSE_CODE"))) {
					code = jsonRequestBody.getString("RESPONSE_CODE");
					// 三方平台数量达到上限后者时间不在范围内，不进行数据入库，执行另一个任务
					if (code.equals("999994") || code.equals("999993")) {
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						taskCallStatusService.updateSingleTaskStatus(appId,
								TaskStatusUtil.QUERY_LIMIT, taskType, null,
								TaskStatusUtil.CURR_USER, null, "1");
						return;
					}	
				}
			}
		}
		if (message != null && !"".equals(message) && "000000".equals(code)) {
			// 解析响应消息并获取响应报文
			String responseXml = message;
			
			NciicForeignerInfo nciicForeignerInfo = null;
			try {
				nciicForeignerInfo =ResponseParseForeignerHelper.parseXml(responseXml);
			} catch (Exception e) {
				if(logger.isErrorEnabled()){
					logger.error("【外国人永久居留证 审批三方模块】报文解析异常"+appId+"Exception:{}", e);
				}
				// 解析异常
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
				return;
			}
			
			try {
				if(nciicForeignerInfo != null){
					nciicForeignerInfo.setAppId(appId);
					nciicForeignerInfo.setCrtUser("PLAZE");
					nciicForeignerInfo.setCardFlag("0");
					nciicForeignerInfo.setCertType(certType);
					policeForeignerService.savePoliceForeignerInfo(nciicForeignerInfo);
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS, taskType,
							null, TaskStatusUtil.CURR_USER, null, null);
				}else{
					// 对方服务响应异常
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE, taskType,
							null, TaskStatusUtil.CURR_USER, null, "1");
					return;
				}
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("[审批三方模块 & 外国人永久居留证] 数据入库异常   " + appId + " Exception:{}", e);
				}
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
				return;
			}
			
			
		}else{
			// 对方服务响应异常
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE, taskType, null,
					TaskStatusUtil.CURR_USER, null, "1");
		}
	}

	/**
	 * 构建请求参数
	 */
	public static String getRequestJson(TaskCallStatus task,String birth,String expiryDate){
		JSONObject jsonObjectDmzReq = new JSONObject();
		JSONObject responseObjectDmzReq = new JSONObject();
		JSONObject responseHeadObjectDmzReq = new JSONObject();
		responseHeadObjectDmzReq.put("REQUEST_CHANNEL", "CAMS");
		responseHeadObjectDmzReq.put("TRN_ID", task.getTaskId());
		responseHeadObjectDmzReq.put("APP_ID", task.getAppId());
		responseHeadObjectDmzReq.put("TRN_CODE", "WST000202");
		responseObjectDmzReq.put("HEAD", responseHeadObjectDmzReq);
		JSONObject responseBodyObjectDmzReq = new JSONObject();
		responseBodyObjectDmzReq.put("QUERY_MODE", "1");
		responseBodyObjectDmzReq.put("CERT_TYPE", task.getCertType());
		responseBodyObjectDmzReq.put("CERT_NO", task.getCertNo());
		responseBodyObjectDmzReq.put("NAME", task.getName());
		responseBodyObjectDmzReq.put("BIRTH", birth);
		responseBodyObjectDmzReq.put("EXPIRY_DATE", expiryDate);

		responseObjectDmzReq.put("BODY", responseBodyObjectDmzReq);
		jsonObjectDmzReq.put("REQUEST",responseObjectDmzReq);
		return jsonObjectDmzReq.toString();
	}
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-*.xml");
		Thread thread = new Thread(new WST000202TaskWorker());
		thread.start();
	}
}
