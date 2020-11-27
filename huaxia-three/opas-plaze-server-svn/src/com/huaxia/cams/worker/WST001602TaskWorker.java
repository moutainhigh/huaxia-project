package com.huaxia.cams.worker;

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

import com.huaxia.cams.modules.pengyuan.domain.PyPwslResponse;
import com.huaxia.cams.modules.pengyuan.service.PyPwslResponseService;
import com.huaxia.cams.modules.pengyuan.service.RecordService;
import com.huaxia.cams.modules.pengyuan.util.PyPwslResponseHelper;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.opas.util.UUIDGen;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;
/**
 * 区域数据-深圳-鹏元- 个人高信分 任务处理器
 * 
 * @author User
 *
 */
public class WST001602TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WST001602TaskWorker.class);

	private String taskType;

	private AppConfig appConfig;

	private TaskCallStatusService taskCallStatusService;
	
	private RecordService recordService;

	private String requestUrl;
	
	private Integer querySize;
	
	private PyPwslResponseService pyPwslResponseService;

	{
		appConfig = AppConfig.getInstance();
		taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		pyPwslResponseService = (PyPwslResponseService)SpringContextUtil.getBean("pyPwslResponseService");
		taskType = appConfig.getValue("area.py.pwsl.task_type");
		requestUrl = appConfig.getValue("area.py.pwsl.plaze.webservice.url");
		querySize=Integer.parseInt(appConfig.getValue("area.py.pwsl.query_size"));
		recordService=(RecordService)SpringContextUtil.getBean("recordService");
	}

	@Override
	public void run() {
		while (true) {
			
			try {
				/** 每次循环前,停顿2秒 */
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
				}
				/** 根据任务类型,查询条数,查询出需要发起个人高信分查询的任务 */
				List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);
				if (taskStatusList != null && taskStatusList.size() > 0){
					logger.debug("[客户端 & 区域数据-深圳-个人高信分] 查询到高信分任务条数：{}", taskStatusList.size());
					/** 遍历集合,逐条发起查询请求 */
					for (TaskCallStatus task : taskStatusList){
						String appId    = task.getAppId ();   //申请件编号
						String name     = task.getName  ();   //姓名
						String certNo   = task.getCertNo();   //证件号
						int countRecord = recordService.selecPwsltCountByAppId(appId);
						/** 如果数据表存在该条appId的数据,跳过不发起查询 */
						if(countRecord > 0){
							continue;
						}
						/** 请求参数错误标识 */
						boolean paramErrorFlag = false;
						if(StringUtils.isBlank(name)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 区域数据-深圳-个人高信分] 请求的姓名为空，流水号为：{}", appId);
							}
						}
						if(StringUtils.isBlank(certNo)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 区域数据-深圳-个人高信分] 请求的证件号为空，流水号为：{}", appId);
							}
						}
						/** 请求参数判断完成后,将任务表待查询数据存入历史表,准备发起第三方查询,
						 *  如果参数错误,更改状态 */
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						if(paramErrorFlag){
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, taskType,null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						/** 开始发起第三方查询请求,更改状态 0 → 1 */
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null,TaskStatusUtil.CURR_USER, "1", null);
						//生成trnId组装请求报文
						String trnId=task.getTaskId();
						String paramsStr=getRequestJson(trnId, certNo, name);
						//开始请求客户端
						Client client = null ;
						String result = "";
						try{
							URL url = new URL(requestUrl);
							HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection() ;	
							httpConnection.setReadTimeout(30000);//设置http连接的读超时，单位是毫秒
							httpConnection.connect();
							client = new Client(httpConnection.getInputStream(),null);
							client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "30000");//设置发送超时限制，单位是毫秒
							client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
							client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
							String param =paramsStr;
							Object[] obj = new Object[]{param} ;
							Object[] obj2 = client.invoke("invoke", obj);
							result = obj2[0].toString();
						}catch(Exception e){
							logger.error("[客户端 &  区域数据-深圳-个人高信分] 申请件[{}]调用第三方查询平台服务异常:{}", appId, e);
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}finally {
							if (client != null) {
								client.close();
								client = null;
							}
						}
						//解析报文
						if(StringUtils.isNotBlank(result)){
							PyPwslResponse pyPwslResponse=PyPwslResponseHelper.getQueryModel1Response(result, appId);
							if (!PyPwslResponseHelper.checkResponse(pyPwslResponse)) {
								logger.error("[区域数据-深圳-鹏元-个人高信分] 解析三方平台返回报文异常");
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								//查找结果为空，则不卡件，正常流转。三方返回结果一般不会解析错误（除非没有查到数据，三方和审批报文解析方法一致）
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
										taskType, null, TaskStatusUtil.CURR_USER, null, null);
								continue;
							}
							// 保存解析后的返回报文
							try{
								int rwoChage=pyPwslResponseService.savePyPwslResponse(pyPwslResponse);
								if(rwoChage<1){
									logger.error("[区域数据-深圳-鹏元-个人高信分] 保存解析后报文异常，TRN_ID={}", pyPwslResponse.getPyPwslCrs().getTrnId());
									/** 入库异常 */
									taskCallStatusService.saveTaskStatusHistory(appId, taskType);
									taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,taskType, null, TaskStatusUtil.CURR_USER, null, "1");
									continue;
								}
								//入库正常
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,taskType, null, TaskStatusUtil.CURR_USER, null, null);
							}catch(Exception e){
								/** 入库异常 */
								e.printStackTrace();
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
						}
						//每条间隔20毫秒
						try {
							Thread.sleep(20);
						} catch (InterruptedException e) {
						}
					}
				}
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("[客户端 & 深圳鹏远-高信分] 线程异常中断 重启  Exception:{}", e);
				}
			}
			
		}

	}
	
	
	public static String getRequestJson(String trnId,String certNo,String name){
		JSONObject jsonObjectDmzReq = new JSONObject();
		JSONObject responseObjectDmzReq = new JSONObject();
		JSONObject responseHeadObjectDmzReq = new JSONObject();
		responseHeadObjectDmzReq.put("REQUEST_CHANNEL", "CAMS");
		responseHeadObjectDmzReq.put("TRN_ID", trnId);
		responseHeadObjectDmzReq.put("TRN_CODE", "WST001602");
		responseObjectDmzReq.put("HEAD", responseHeadObjectDmzReq);
		JSONObject responseBodyObjectDmzReq = new JSONObject();
		responseBodyObjectDmzReq.put("CERT_NO", certNo);
		responseBodyObjectDmzReq.put("NAME", name);
		responseBodyObjectDmzReq.put("QUERY_MODE", "2");
		responseObjectDmzReq.put("BODY", responseBodyObjectDmzReq);
		jsonObjectDmzReq.put("REQUEST",responseObjectDmzReq);
		return jsonObjectDmzReq.toString();
	}
	
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-*.xml");
		Thread thead  =new Thread(new WST001602TaskWorker());
		thead.start();
	}

}
