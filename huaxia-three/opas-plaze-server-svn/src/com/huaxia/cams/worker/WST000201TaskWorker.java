package com.huaxia.cams.worker;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huaxia.cams.modules.police.domain.PoliceXpInfo;
import com.huaxia.cams.modules.police.service.PoliceXpService;
import com.huaxia.cams.modules.police.util.Base64Test;
import com.huaxia.cams.modules.police.util.ResponseParseHelper;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import sun.misc.BASE64Encoder;

/**
 * 公安人像比对
 * @author User
 *
 */
public class WST000201TaskWorker implements Runnable{

	private static final Logger logger = LoggerFactory.getLogger(WST000201TaskWorker.class);
	
	private static final AppConfig appConfig = AppConfig.getInstance();
	
	private TaskCallStatusService taskCallStatusService;
	
	private PoliceXpService policeXpService;
	
	private  String taskType;
	private Integer querySize;
	private String requestUrl;
	private String camsImageUrl;
	private String camsImageUrlBefore;
	private String camsImageUrlAfter;
	
	public WST000201TaskWorker(){
		//获取配置参数
		taskType = appConfig.getValue("police.xp.task_type");
		querySize = Integer.valueOf(appConfig.getValue("police.xp.query_size"));
		requestUrl = appConfig.getValue("police.xp.plaze.webservice.url");
		camsImageUrl = appConfig.getValue("cams.image.get_url_by_appid");
		camsImageUrlBefore = appConfig.getValue("cams.image.url.before");
		camsImageUrlAfter = appConfig.getValue("cams.image.url.after");
		//初始化服务接口
		taskCallStatusService = (TaskCallStatusService)SpringContextUtil.getBean("taskCallStatusService");
		policeXpService = (PoliceXpService)SpringContextUtil.getBean("policeXpService");
	}
	@Override
	public void run() {

		while(true){
			
			try {
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
				}
				List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);
				
				if(taskStatusList != null && taskStatusList.size()>0){
					logger.info("[人像比对] 查询到的任务条数={}", taskStatusList.size());
					for(TaskCallStatus task :taskStatusList){
						String appId = task.getAppId(); 	//申请件编号
						String name = task.getName();		//姓名
						String certNo = task.getCertNo();	//证件号
						
						//如果数据表存在该appid的数据，跳过不发起查询
						int count = policeXpService.getCountByAppId(task.getAppId());
						if(count>0){
							continue;
						}
						//验证请求参数
						boolean paramFlag = false;
						if(StringUtils.isBlank(name)){
							paramFlag = true;
							if(logger.isInfoEnabled()){
								logger.info("【人像比对】 请求的姓名为空，流水号为:{}",appId);
							}
						}
						if(StringUtils.isBlank(certNo)){
							paramFlag = true;
							if(logger.isInfoEnabled()){
								logger.info("[人像比对] 请求的证件号为空，流水号为:{}",appId);
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
						
						//测试获取照片
//						int num = (int) (Math.random()*9+1);
//						String p = "D:/feiqiu/renxiang/"+num+".jpg";
//						String strImg = Base64Test.GetImageStr(p);
//						//调用接口获取照片
//						String xp = strImg.replace("\r\n", "");
						
						//调用接口获取照片
						String xp = getImage(certNo,appId);
						if(xp == null){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						String queryParam = getRequestJson(task,xp);
						try {
							url = new URL(requestUrl);
							HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
							httpConnection.setReadTimeout(120000);//设置http连接的读超时，单位毫秒
							httpConnection.connect();
							client = new Client(httpConnection.getInputStream(),null);
							client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "120000");//设置发送超时限制，单位是毫秒
							client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
							client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
							Object[] obj = new Object[]{queryParam};
							result = client.invoke("invoke", obj);
						
						} catch (Exception e) {
							requestFlag = true;
							if(logger.isErrorEnabled()){
								logger.error("【人像比对】 请求处理异常"+appId+"Exception:{}", e);
							}
						}finally{
							if(client != null){
								client.close();
								client = null;
							}
						}
					//如任务历史表
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						if(requestFlag){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						boolean responseFlag = false; //返回报文是否异常
						try {
							responseParse( String.valueOf( result[0] ), appId ,xp);
						} catch (Exception e) {
							if(logger.isErrorEnabled()){
								logger.error("【人像比对】解析异常responseParse"+appId+"Exception:{}", e);
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
					logger.debug("[公安人像比对] 未查询到人像比对任务条数！！");
				}
			} catch (Exception e) {
				if(logger.isErrorEnabled()){
					logger.error("[公安人像比对] 线程异常中断 重启Exception:{}", e);
				}
			}
		}
	}
	/**
	 * 调用影像系统获取照片
	 * @param certNo
	 * @param appId
	 * @return
	 */
	private String getImage(String certNo,String appId){
		try {
			String xp = null;
			Client client = null;
			URL url = null;
			JSONObject jsonObject = null;
			String imageUrl = null;
			try {
				client = new Client(new URL(camsImageUrl));
				client.setTimeout(12000);
				Object[] response = client.invoke("invoke", new Object[]{appId});
				jsonObject = JSON.parseObject(String.valueOf(response[0]));
				if(!"success".equals(jsonObject.getString("message"))){
					logger.info("编号为"+appId+"获取照片url失败");
					return null;
				}
				JSONArray jsonArray = JSONArray.parseArray(jsonObject.getString("data"));
				if(jsonArray ==null ||jsonArray.size()<1){
					logger.info("编号为"+appId+"获取照片url失败,data数组为空");
					return null;
				}
				imageUrl = jsonArray.getString(0);
			} catch (Exception e) {
				logger.error("[人像比对]请求获取imageUrl异常Exception:{}", e);
				return null;
			}finally{
				if(client != null){
					client.close();
					client = null;
				}
			}
			InputStream is = null;
			ByteArrayOutputStream outStream = null;
			HttpURLConnection httpUrl = null;
			try {
				if(StringUtils.isNotBlank(imageUrl)){
					logger.info("转换前图片地址={}",imageUrl);
					imageUrl = imageUrl.replace(camsImageUrlBefore, camsImageUrlAfter);
					logger.info("转换后图片地址={}", imageUrl);
				}
				url = new URL(imageUrl);
				httpUrl = (HttpURLConnection)url.openConnection();
				httpUrl.setReadTimeout(120000);
				httpUrl.connect();
				is = httpUrl.getInputStream();
				outStream = new ByteArrayOutputStream();
				byte[] date = new byte[1024];
				int len = 0;
				while((len = is.read(date)) != -1){
					outStream.write(date, 0, len);
				}
				BASE64Encoder encoder = new BASE64Encoder();
				xp = encoder.encode(outStream.toByteArray()).replace("\r\n", "");
			} catch (Exception e) {
				logger.error("[人像比对]根据imageurl获取base64字符串异常Exception:{}", e);
				return null;
			}finally{
				if(is != null){
					try {
						is.close();
					} catch (IOException e) {
						logger.error("[人像比对]Exception:{}", e);
					}
				}
				if(outStream != null){
					try {
						outStream.close();
					} catch (IOException e) {
						logger.error("[人像比对]Exception:{}", e);
					}
				}
				if(httpUrl != null){
					httpUrl.disconnect();
				}
			}
			return xp;
		} catch (Exception e) {
			logger.error("[人像比对]获取照片异常Exception:{}", e);
		}
		return null;
	}
	private void responseParse(String str, String appId,String xp) {

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
			
			PoliceXpInfo policeXpInfo = null;
			try {
				policeXpInfo =ResponseParseHelper.parseXml(responseXml);
			} catch (Exception e) {
				if(logger.isErrorEnabled()){
					logger.error("【人像比对 审批三方模块】报文解析异常"+appId+"Exception:{}", e);
				}
				// 解析异常
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
				return;
			}
			
			try {
				if(policeXpInfo != null){
					policeXpInfo.setAppId(appId);
					policeXpInfo.setCrtUser("PLAZE");
					policeXpInfo.setXp(xp);
					policeXpService.savePoliceXpInfo(policeXpInfo);
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
					logger.error("[审批三方模块 & 人像比对] 数据入库异常   " + appId + " Exception:{}", e);
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
	public static String getRequestJson(TaskCallStatus task,String xp){
		JSONObject jsonObjectDmzReq = new JSONObject();
		JSONObject responseObjectDmzReq = new JSONObject();
		JSONObject responseHeadObjectDmzReq = new JSONObject();
		responseHeadObjectDmzReq.put("REQUEST_CHANNEL", "CAMS");
		responseHeadObjectDmzReq.put("TRN_ID", task.getTaskId());
		responseHeadObjectDmzReq.put("APP_ID", task.getAppId());
		responseHeadObjectDmzReq.put("TRN_CODE", "WST000201");
		responseObjectDmzReq.put("HEAD", responseHeadObjectDmzReq);
		JSONObject responseBodyObjectDmzReq = new JSONObject();
		responseBodyObjectDmzReq.put("QUERY_MODE", "1");
		responseBodyObjectDmzReq.put("CERT_TYPE", task.getCertType());
		responseBodyObjectDmzReq.put("CERT_NO", task.getCertNo());
		responseBodyObjectDmzReq.put("NAME", task.getName());
		responseBodyObjectDmzReq.put("MOBILE", task.getMobile());
		responseBodyObjectDmzReq.put("XP", xp);
		
		responseObjectDmzReq.put("BODY", responseBodyObjectDmzReq);
		jsonObjectDmzReq.put("REQUEST",responseObjectDmzReq);
		return jsonObjectDmzReq.toString();
	}
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-*.xml");
		Thread thread = new Thread(new WST000201TaskWorker());
		thread.start();
	}
}
