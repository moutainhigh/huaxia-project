package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huaxia.cams.modules.baoxin.domain.BaoXinBase;
import com.huaxia.cams.modules.baoxin.service.BaoXinService;
import com.huaxia.cams.modules.pengyuan.service.RecordService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

public class WST001900TaskWorker implements Runnable{

	private final static Logger logger = LoggerFactory.getLogger(WST001900TaskWorker.class);
	private String taskType;

	private AppConfig appConfig;

	private TaskCallStatusService taskCallStatusService;
	
	private String requestUrl;
	
	private Integer querySize;
	
	private BaoXinService baoXinServiceImpl;
	{
		appConfig = AppConfig.getInstance();
		taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		taskType = appConfig.getValue("baoxin.task_type");
		requestUrl = appConfig.getValue("general.plaze.webservice.url");
		querySize=Integer.parseInt(appConfig.getValue("baoxin.query_size"));
		baoXinServiceImpl = (BaoXinService)SpringContextUtil.getBean("baoXinServiceImpl");
	}
	
	@Override
	public void run() {
		while(true){
			try {
				try {
					Thread.sleep(5000);
				} catch (Exception e) {
				}
				
				List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType,querySize);
				if(taskStatusList != null && taskStatusList.size() > 0){
					for(TaskCallStatus task : taskStatusList){
						String appId    = task.getAppId ();   //申请件编号
						String name     = task.getName  ();   //姓名
						String certNo   = task.getCertNo();   //证件号
						int count = baoXinServiceImpl.selectCountByAppId(appId);
						if(count>0){
							continue;
						}
						//请求参数错误标识
						boolean paramErrorFlag = false;
						if(StringUtils.isBlank(certNo)){
							paramErrorFlag = true;
							if(logger.isDebugEnabled()){
								logger.debug("客户端 & 保信汽车 请求证件号码为空，流水号为：{}",appId);
							}
						}
						if(StringUtils.isBlank(name)){
							paramErrorFlag = true;
							if(logger.isDebugEnabled()){
								logger.debug("客户端 & 保信汽车 请求姓名为空 ，流水号为{}",appId);
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
						String jsonRequest = getRequestJson(appId, certNo, name);
						
						Client client = null;
						Object[] result = null;
						try {
							URL url = new URL(requestUrl);
							HttpURLConnection httpConnection = (HttpURLConnection)url.openConnection();
							httpConnection.setReadTimeout(Integer.parseInt(appConfig.getValue("baoxin.plaze.webservice.read_timeout")));
							httpConnection.connect();
							client = new Client(httpConnection.getInputStream(),null);
							//设置发送超时限制，单位毫秒
							client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,
									appConfig.getValue("baoxin.plaze.webservice.http_timeout"));
							client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
							client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
							result = client.invoke("invoke", new Object[]{jsonRequest});
						} catch (Exception e) {
							logger.error("客户端 & 保信汽车调用第三方服务异常", e);
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}finally{
							if(client != null){
								client.close();
							}
						}
						try {
							BaoXinBase base = new BaoXinBase();
							JSONObject jsonObject = JSON.parseObject(String.valueOf(result[0]));
							JSONObject jsonResponse = jsonObject.getJSONObject("RESPONSE");
							JSONObject jsonBody = jsonResponse.getJSONObject("BODY");
							String responseCode = jsonBody.getString("RESPONSE_CODE");
							//如果返回码不是000000 说明请求数据源异常
							if("999999".equals(responseCode)){
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							//数量限制
							if (responseCode.equals("999994") || responseCode.equals("999993")) {
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId,
										TaskStatusUtil.QUERY_LIMIT, taskType, null,
										TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							//流程执行异常
							if("000101".equals(responseCode)){
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.STREAM_CALL_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							JSONObject messageBody = jsonBody.getJSONObject("RESPONSE_BODY");
							if(!"000000".equals(messageBody.getString("retCode")) || !"999999".equals(messageBody.getString("retCode"))){
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.RESPOSE_CODE_ERROR,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							base.setRetCode(messageBody.getString("retCode"));
							base.setRetMessage(messageBody.getString("retMessage"));
							base.setInsurerUuid(messageBody.getString("insurerUuid"));
							JSONObject financialProductsInfoRes = messageBody.getJSONObject("financialProductsInfoRes");
							base.setBankCode(financialProductsInfoRes.getString("bankCode"));
							base.setReturnTime(financialProductsInfoRes.getString("returnTime"));
							base.setRiskValueRange(financialProductsInfoRes.getString("riskValueRange"));
							base.setCarAge(financialProductsInfoRes.getString("carAge"));
							base.setAppId(appId);
							base.setCertNo(certNo);
							base.setCrtUser("CAMS");
							int num = baoXinServiceImpl.saveBase(base);
							if(num < 1){
								//入库异常
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							//入库正常
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,taskType, null, TaskStatusUtil.CURR_USER, null, null);
						} catch (Exception e) {
							logger.error("客户端&保信汽车解析异常",e);
							logger.error("客户端&报信汽车解析响应报文为："+String.valueOf(result[0]));
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						try {
							Thread.sleep(20);
						} catch (Exception e) {
						}
						
					}
				}
				
			} catch (Exception e) {
				logger.error("保信汽车，线程异常中断重启", e);
			}
		}
	}
	
	public static String getRequestJson(String appId,String certNo,String name){
		JSONObject jsonObjectDmzReq = new JSONObject();
		JSONObject responseObjectDmzReq = new JSONObject();
		JSONObject responseHeadObjectDmzReq = new JSONObject();
		responseHeadObjectDmzReq.put("REQUEST_CHANNEL", "CAMS");
		responseHeadObjectDmzReq.put("TRN_ID", appId);
		responseHeadObjectDmzReq.put("TRN_CODE", "WST001900");
		responseObjectDmzReq.put("HEAD", responseHeadObjectDmzReq);
		JSONObject responseBodyObjectDmzReq = new JSONObject();
		responseBodyObjectDmzReq.put("CERT_NO", certNo);
		responseBodyObjectDmzReq.put("NAME", name);
		responseBodyObjectDmzReq.put("QUERY_MODE", "1");
		responseObjectDmzReq.put("BODY", responseBodyObjectDmzReq);
		jsonObjectDmzReq.put("REQUEST",responseObjectDmzReq);
		return jsonObjectDmzReq.toString();
	}
}
