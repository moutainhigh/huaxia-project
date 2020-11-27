package com.huaxia.cams.worker;

import java.io.File;
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
import com.huaxia.opas.domain.SimplifyPolice;
import com.huaxia.opas.domain.TaskRequestInfo;
import com.huaxia.opas.interfaces.in.MessageParser;
import com.huaxia.opas.parser.SimplifyPoliceMessageParser;
import com.huaxia.opas.service.SimplifyPoliceService;
import com.huaxia.opas.service.TaskRequestInfoService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.CommonUtil;
import com.huaxia.opas.util.ImageGenUtil;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 
 * 简项公安
 *
 */
public class WSTF00200TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WSTF00200TaskWorker.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	private TaskCallStatusService taskCallStatusService;

	private SimplifyPoliceService simplifyPoliceService;

	private TaskRequestInfoService taskRequestInfoService;
	private Integer PLAZE_POLICE_THREAD_SLEEPTIME_MM;
	private Integer PLAZE_POLICE_SINGLE_SLEEPTIME_MM;
	private final Integer querySize;
	private final String taskType = "F00200";
	private final String PLAZE_POLICE_URL;
	// 简项公安相片文件名后缀
	private String photoSubfix;

	// 简项公安相片生成路径
	private String photoSavePath;

	// 报文处理
	private static MessageParser<SimplifyPolice> messageParser = new SimplifyPoliceMessageParser();

	public WSTF00200TaskWorker() {
		// 获取配置参数
		this.querySize = Integer.valueOf(appConfig.getValue("PLAZE_POLICE_QUERY_SIZE"));
		this.photoSubfix = appConfig.getValue("PLAZE_POLICE_PHOTO_SUBFIX");
		this.photoSavePath = appConfig.getValue("PLAZE_POLICE_PHOTO_GENERATE_PATH");

		// 初始化服务接口
		this.taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		this.simplifyPoliceService = (SimplifyPoliceService) SpringContextUtil.getBean("simplifyPoliceService");
		this.taskRequestInfoService = (TaskRequestInfoService) SpringContextUtil.getBean("taskRequestInfoService");
		PLAZE_POLICE_THREAD_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_POLICE_THREAD_SLEEPTIME_MM"));
		PLAZE_POLICE_SINGLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_POLICE_SINGLE_SLEEPTIME_MM"));
		PLAZE_POLICE_URL=appConfig.getValue("simpolice.webservice.url");
	}

	@Override
	public void run() {
	while (true) {
		try{
	
			try {
				Thread.sleep(PLAZE_POLICE_THREAD_SLEEPTIME_MM);
			} catch (InterruptedException e) {

			}
			List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);

			if (taskStatusList != null && taskStatusList.size() > 0) {
				int size = taskStatusList.size();

				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 简项公安] 查询简项公安任务条数：{}", size);
				}

				// if (!isSkipCheck() || size > 0) {
				// -------------- 简项公安开关模式 --------------
				// ON-表示联机模式
				// OFF-表示文件模式
				// ---------------------------------------
				// if (ModeSwitch.ON.name().equals(getModeSwitch())) {
				for (TaskCallStatus task : taskStatusList) {
					// 检查三方表是否已存在 （存在 则 更改为2后continue ）
					/*int policeCount = simplifyPoliceService.getCountByAppId(task.getAppId());
					if (policeCount > 0) {
						continue;
					}*/
					String appId = task.getAppId();
					// 更新任务请求信息状态标志
					TaskRequestInfo taskRequestInfo = new TaskRequestInfo();
					taskRequestInfo.setAppId(appId);
					taskRequestInfo.setReqPolice("1");
					taskRequestInfoService.update(taskRequestInfo);
					// 插入历史表
					taskCallStatusService.saveTaskStatusHistory(appId, taskType);
					// 请求开始
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null,
							TaskStatusUtil.CURR_USER, "1", null);

					boolean requestFlag = false;// 内部请求是否正常标识

					// 组装请求报文
					String queryMode = "1";
					Client client = null;
					Object[] result = null;
					URL url = null;
					try {
					// 连接字符串，实现数据库的查询和实现
					Map<String, Object> head = new HashMap<String, Object>();
					head.put("REQUEST_CHANNEL", "CAMS");
					head.put("TRN_ID", task.getTaskId());
					head.put("TRN_CODE", "WST000200");
					Map<String, Object> body = new HashMap<String, Object>();
					body.put("QUERY_MODE", queryMode);
					body.put("CERT_TYPE", task.getCertType());
					body.put("CERT_NO", task.getCertNo());
					body.put("NAME", task.getName());
					body.put("MOBILE", task.getMobile());

					Map<String, Object> request = new HashMap<String, Object>();
					request.put("HEAD", head);
					request.put("BODY", body);

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("REQUEST", request);

					JSONObject json = JSONObject.fromObject(params);
					String paramsStr = JSON.toJSONString(json);
					
					url = new URL(PLAZE_POLICE_URL);
					HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
					httpConnection.setReadTimeout(120000);// 设置http连接的读超时，单位是毫秒
					httpConnection.connect();
					client = new Client(httpConnection.getInputStream(), null);
					client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "120000");// 设置发送超时限制，单位是毫秒
					client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
					client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
					Object[] obj = new Object[] { paramsStr };
					result = client.invoke("invoke", obj);
					} catch (Exception e) {
						requestFlag = true;
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 简项公安] 请求处理异常  " + appId + " Exception:{}", e);
						}
					} finally {
						if (client != null) {
							client.close();
							client = null;
						}
					}
					//入任务历史表
					taskCallStatusService.saveTaskStatusHistory(appId, taskType);
					if ( requestFlag ) {
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
								taskType, null, TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
					
					boolean responseFlag = false; //报文返回是否异常
					try{
						getResult( String.valueOf( result[0] ), appId );						
					}catch(Exception e){
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 简项公安] 解析异常getResult" + appId + " Exception:{}", e);
						}
						responseFlag = true ;
					}
					if( responseFlag ){
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
								taskType, null, TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
					
					
					try {
						Thread.sleep(PLAZE_POLICE_SINGLE_SLEEPTIME_MM);
					} catch (InterruptedException e) {

					}
				}
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 简项公安] 未查询到简项公安任务条数!");
				}
			}
			
	}catch(Exception e){
		if (logger.isErrorEnabled()) {
			logger.error("[客户端 & 简项公安] 线程异常中断 重启  Exception:{}", e);
		}
	}
	}
	}

	private void getResult(String str, String APP_ID) {
		JSONObject json = JSONObject.fromObject(str);
		String code = "";
		String message = null;
		if (json.containsKey("RESPONSE") && !"".equals(json.getString("RESPONSE"))) {
			JSONObject jsonRequest = JSONObject.fromObject(json.getString("RESPONSE"));

			if (jsonRequest.containsKey("BODY") && !"".equals(jsonRequest.getString("BODY"))) {
				JSONObject jsonRequestBody = JSONObject.fromObject(jsonRequest.getString("BODY"));
				code = jsonRequestBody.getString("RESPONSE_CODE");
				message = jsonRequestBody.getString("RESPONSE_BODY");
			}
		}

		if (message != null && !"".equals(message) && "000000".equals(code)) {

			// 解析响应消息并获取响应报文
			String responseXml = message;

			SimplifyPolice simplifyPolice = null;
			// 删除 原先存在的公安照片
			/** 附卡的公安照片名称为appid+F.jpg */
			CommonUtil.rmHardDiskFile(photoSavePath + File.separator, APP_ID + "F" + photoSubfix, APP_ID, "简项公安照片");
			try {
				simplifyPolice = messageParser.parse(responseXml);
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("[简项公安 & 简项公安] 数据解析异常   " + APP_ID + " Exception:{}", e);
				}
				// 解析异常
				taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.PARSE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
				return;
			}

			try {
				if (simplifyPolice != null) {
					simplifyPolice.setAppId(APP_ID);
					simplifyPolice.setCrtUser("PLAZE");
					simplifyPolice.setCardFlag("1");
					simplifyPoliceService.saveSimPoliceUpdateDataAdapterAction(simplifyPolice, APP_ID);
					// 针对简项公安中相片生成在指定配置目录中
					String photo = simplifyPolice.getXp();
					if (photo != null && !"".equals(photo)) {
						/** 附卡的公安照片名称为appid+F.jpg */
						String fileSavePath = photoSavePath + File.separator + APP_ID + "F" + photoSubfix;
						boolean imageGenerateResult = ImageGenUtil.generate(photo, fileSavePath);
						if (imageGenerateResult) {
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 简项公安] 简项公安照片生成成功, 照片路径为: {}", fileSavePath);
							}
						} else {
							if (logger.isWarnEnabled()) {
								logger.warn("[客户端 & 简项公安] 简项公安照片生成失败, 申请件编号: {}", simplifyPolice.getAppId());
							}
						}
					}
					taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.SUCCESS, taskType,
							null, TaskStatusUtil.CURR_USER, null, null);
				} else {
					// 对方服务响应异常
					taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.SERVE_RESPOSE_ERROE, taskType,
							null, TaskStatusUtil.CURR_USER, null, "1");
					return;
				}

			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("[简项公安 & 简项公安] 数据入库异常   " + APP_ID + " Exception:{}", e);
				}
				taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.SAVE_ERROE, taskType, null,
						TaskStatusUtil.CURR_USER, null, "1");
				return;
			}
		} else {
			// 对方服务响应异常
			taskCallStatusService.updateSingleTaskStatus(APP_ID, TaskStatusUtil.SERVE_RESPOSE_ERROE, taskType, null,
					TaskStatusUtil.CURR_USER, null, "1");
		}
	}

}
