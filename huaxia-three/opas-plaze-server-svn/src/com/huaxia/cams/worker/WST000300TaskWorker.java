package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.cams.modules.id5.Id5Receiver;
import com.huaxia.opas.domain.TaskRequestInfo;
import com.huaxia.opas.service.EducationService;
import com.huaxia.opas.service.TaskRequestInfoService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.IdcardValidator;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 
 * 学历信息
 *
 */
public class WST000300TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WST000300TaskWorker.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	private TaskCallStatusService taskCallStatusService;

	private EducationService educationService;

	private TaskRequestInfoService taskRequestInfoService;
	private Integer PLAZE_EDUCATION_THREAD_SLEEPTIME_MM;
	private Integer PLAZE_EDUCATION_SINGLE_SLEEPTIME_MM;
	private final String taskType;
	private final Integer querySize;
	private final String PLAZE_EDUCATION_URL;
	public WST000300TaskWorker() {
		// 获取配置参数
		taskType = appConfig.getValue("id5_TrnCode_code");
		querySize = Integer.valueOf(appConfig.getValue("PLAZE_EDU_QUERY_SIZE"));

		// 初始化服务接口
		taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		educationService = (EducationService) SpringContextUtil.getBean("educationService");
		taskRequestInfoService = (TaskRequestInfoService) SpringContextUtil.getBean("taskRequestInfoService");
		PLAZE_EDUCATION_THREAD_SLEEPTIME_MM = Integer
				.valueOf(appConfig.getValue("PLAZE_EDUCATION_THREAD_SLEEPTIME_MM"));
		PLAZE_EDUCATION_SINGLE_SLEEPTIME_MM = Integer
				.valueOf(appConfig.getValue("PLAZE_EDUCATION_SINGLE_SLEEPTIME_MM"));
		PLAZE_EDUCATION_URL = appConfig.getValue("id5.webservice.url");
	}

	@Override
	public void run() {
	while (true) {
		try{		
			
			try {
				Thread.sleep(PLAZE_EDUCATION_THREAD_SLEEPTIME_MM);
			} catch (InterruptedException e) {

			}
			List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);

			if (taskStatusList != null && taskStatusList.size() > 0) {
				int size = taskStatusList.size();

				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 学历] 查询学历任务条数：{}", size);
				}
				// if (!isSkipCheck() || size > 0) {
				// -------------- 学历开关模式 --------------
				// ON-表示联机模式
				// OFF-表示文件模式
				// ---------------------------------------
				// if (ModeSwitch.ON.name().equals(getModeSwitch())) {
				for (TaskCallStatus task : taskStatusList) {
					String appId = task.getAppId();
					String certType = "";
					if (task.getCertType() != null) {
						certType = task.getCertType().trim();
					}
					String idCard = "";
					if (task.getCertNo() != null) {
						idCard = task.getCertNo().trim();
					}
					int countEducation = educationService.getCountByAppId(appId);
					if (countEducation > 0) {
						continue;
					}
					boolean paramErrorFlag = false;// 参数错误标识
					if (certType == null || !"01".equals(certType) || idCard == null || "".equals(idCard)
							|| !IdcardValidator.judgeIdcardRight(idCard)) {
						paramErrorFlag = true;
						if (logger.isDebugEnabled()) {
							logger.debug("[客户端 & 学历] 请求的证件号为不正确，流水号为：{},证件号：{}", appId, idCard);
						}
					}
					if (task.getName() == null || "".equals(task.getName())) {
						paramErrorFlag = true;
						if ( logger.isDebugEnabled() ) {
							logger.debug("[客户端 & 学历] 请求的姓名为空，流水号为：{}", appId);
						}
					}
					// 插入历史表
					taskCallStatusService.saveTaskStatusHistory(appId, taskType);
					if ( paramErrorFlag ) {
						// 参数异常继续流转
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS, taskType, null,
								TaskStatusUtil.CURR_USER, null, null);
						continue;
					}
					// 更新任务请求信息状态标志
					TaskRequestInfo taskRequestInfo = new TaskRequestInfo();
					taskRequestInfo.setAppId(appId);
					taskRequestInfo.setReqEducation("1");
					taskRequestInfoService.update(taskRequestInfo);
					// 请求开始
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null,
							TaskStatusUtil.CURR_USER, "1", null);
					// 组装请求报文
					String queryMode = "1";
					boolean requestFlag = false;// 内部请求是否正常标识
					// 创建请求客户端
					Client client = null;
					Object[] result = null;
					URL url = null;
					try {
					String requestParamsStr = assemblingParameters(task.getTaskId(), taskType, task.getCertType(),
							task.getCertNo(), task.getName(), task.getMobile(), queryMode);
					
						url = new URL(PLAZE_EDUCATION_URL);
						HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
						httpConnection.setReadTimeout(120000);// 设置http连接的读超时，单位是毫秒
						httpConnection.connect();
						client = new Client(httpConnection.getInputStream(), null);
						client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "120000");// 设置发送超时限制，单位是毫秒
						client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
						client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
						Object[] obj = new Object[] { requestParamsStr };
						result = client.invoke("invoke", obj);
					} catch (Exception e) {
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 学历] 申请件[{}]调用第三方查询平台服务异常:{}"+appId,e);
						}
						requestFlag = true ;
					} finally {
						if (client != null) {
							client.close();
							client = null;
						}
					}
					// 插入历史表
					taskCallStatusService.saveTaskStatusHistory(appId, taskType);
					if( requestFlag ){
						// 对方服务异常
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
								taskType, null, TaskStatusUtil.CURR_USER, null, "1");
						continue ;
					}
					boolean responseFlag = false; //报文返回是否异常
					try{
						Id5Receiver id5Receiver = new Id5Receiver();
						id5Receiver.getResult(String.valueOf(result[0]), appId);
					}catch(Exception e){
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 学历] 学历报文解析异常getResult" + appId + " Exception:{}", e);
						}
						responseFlag = true;
					}
					if( responseFlag ){
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
								taskType, null, TaskStatusUtil.CURR_USER, null, "1");	
						continue ;
					}

					try {
						Thread.sleep(PLAZE_EDUCATION_SINGLE_SLEEPTIME_MM);
					} catch (InterruptedException e) {

					}
				}
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 学历] 未查询到学历任务条数!");
				}
			}
	
	}catch(Exception e){
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 & 学历] 线程异常中断 重启  Exception:{}", e);
			}
	}
	}
	}

	public String assemblingParameters(String trnId, String TrnCode, String CertType, String CertNo, String name,
			String mobile, String queryMode) {
		JSONObject jsonObjectDmzReq = new JSONObject();
		JSONObject responseObjectDmzReq = new JSONObject();

		// 请求头报文组装
		JSONObject responseHeadObjectDmzReq = new JSONObject();
		responseHeadObjectDmzReq.put("REQUEST_CHANNEL", "CAMS");
		responseHeadObjectDmzReq.put("TRN_ID", trnId);
		responseHeadObjectDmzReq.put("TRN_CODE", TrnCode);

		responseObjectDmzReq.put("HEAD", responseHeadObjectDmzReq);
		// 请求体报文组装
		JSONObject responseBodyObjectDmzReq = new JSONObject();
		responseBodyObjectDmzReq.put("CERT_TYPE", CertType);
		responseBodyObjectDmzReq.put("CERT_NO", CertNo);
		responseBodyObjectDmzReq.put("NAME", name);
		responseBodyObjectDmzReq.put("MOBILE", mobile);
		responseBodyObjectDmzReq.put("QUERY_MODE", queryMode);

		responseObjectDmzReq.put("BODY", responseBodyObjectDmzReq);

		jsonObjectDmzReq.put("REQUEST", responseObjectDmzReq);

		return jsonObjectDmzReq.toString();
	}

}
