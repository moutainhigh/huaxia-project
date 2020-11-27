package com.huaxia.opas.caller;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;
import com.huaxia.opas.service.BankService;
import com.huaxia.opas.service.PBOCService;
import net.sf.json.JSONObject;

/**
 * 人行二期任务请求
 * 
 * @author gaoh
 *
 */
public class BankTaskCaller implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(BankTaskCaller.class);
	
	private final static AppConfig appConfig = AppConfig.getInstance();

	private TaskCallStatusService taskCallStatusService;

	private PBOCService pbocService;
	
	private BankService bankService;
	
	// 人行查询天数控制
	private String pbocSearchDaysControl;

	private Integer PLAZE_PBOC_THREAD_SLEEPTIME_MM;
	private Integer PLAZE_PBOC_SINGLE_SLEEPTIME_MM;
	private String PLAZE_PBOC_NOTSEARCH_TIME_START;
	private String PLAZE_PBOC_NOTSEARCH_TIME_END;
	private final String BANK_TASK_TYPE;
	private Integer querySize;
	
	public String getPbocSearchDaysControl() {
		return pbocSearchDaysControl;
	}

	public void setPbocSearchDaysControl(String pbocSearchDaysControl) {
		this.pbocSearchDaysControl = pbocSearchDaysControl;
	}

	public BankService getBankService() {
		return bankService;
	}

	public void setBankService(BankService bankService) {
		this.bankService = bankService;
	}
	public TaskCallStatusService getTaskCallStatusService() {
		return taskCallStatusService;
	}

	public void setTaskCallStatusService(TaskCallStatusService taskCallStatusService) {
		this.taskCallStatusService = taskCallStatusService;
	}

	public PBOCService getPbocService() {
		return pbocService;
	}

	public void setPbocService(PBOCService pbocService) {
		this.pbocService = pbocService;
	}
	
	public Integer getQuerySize() {
		return querySize;
	}

	public void setQuerySize(Integer querySize) {
		this.querySize = querySize;
	}

	public BankTaskCaller() {
		setQuerySize(Integer.valueOf(appConfig.getValue("PLAZE_PBOC_QUERY_SIZE")));
		setPbocSearchDaysControl(appConfig.getValue("PLAZE_PBOC_SEARCH_DAYS_CONTROL"));
		// 初始化服务接口
		setTaskCallStatusService((TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService"));
		setPbocService((PBOCService) SpringContextUtil.getBean("pbocService"));
		setBankService((BankService) SpringContextUtil.getBean("bankService"));
		PLAZE_PBOC_THREAD_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_PBOC_THREAD_SLEEPTIME_MM"));
		PLAZE_PBOC_SINGLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_PBOC_SINGLE_SLEEPTIME_MM"));
		PLAZE_PBOC_NOTSEARCH_TIME_START = appConfig.getValue("PLAZE_PBOC_NOTSEARCH_TIME_START");
		PLAZE_PBOC_NOTSEARCH_TIME_END = appConfig.getValue("PLAZE_PBOC_NOTSEARCH_TIME_END");
		BANK_TASK_TYPE=appConfig.getValue("BANK_TASK_TYPE");
	}

	@Override
	public void run() {
		while (true) {
			// 创建http客户端
			HttpClient httpClient = new HttpClient();

			// 获取人行接口URL
			String url = appConfig.getValue("PLAZE_BANK_HTTP_URL");
			int startHour = Integer.parseInt(PLAZE_PBOC_NOTSEARCH_TIME_START.substring(0, 2));
			int startMinute = Integer.parseInt(PLAZE_PBOC_NOTSEARCH_TIME_START.substring(2, 4));
			int startSecond = Integer.parseInt(PLAZE_PBOC_NOTSEARCH_TIME_START.substring(4, 6));
			int startTimeSecondSum = ( startHour * 3600 ) +( startMinute * 60 ) + startSecond ;
			
			int endHour = Integer.parseInt(PLAZE_PBOC_NOTSEARCH_TIME_END.substring(0, 2));
			int endMinute = Integer.parseInt(PLAZE_PBOC_NOTSEARCH_TIME_END.substring(2, 4));
			int endSecond = Integer.parseInt(PLAZE_PBOC_NOTSEARCH_TIME_END.substring(4, 6));
			int endTimeSecondSum = ( endHour * 3600) +( endMinute * 60 ) + endSecond ;
			
			try {
				while (true) {
					try {
						Thread.sleep(PLAZE_PBOC_THREAD_SLEEPTIME_MM);
					} catch (InterruptedException e) {

					}
					Calendar calendar = Calendar.getInstance();
					int currHour = calendar.get(Calendar.HOUR_OF_DAY);
					int currMinute = calendar.get(Calendar.MINUTE);
					int currSecond = calendar.get(Calendar.SECOND);
					int currTimeSecondSum = ( currHour * 3600 ) +( currMinute * 60 ) + currSecond ;
					
					//当前时间大于PLAZE_PBOC_NOTSEARCH_TIME_START
					if( currTimeSecondSum >= startTimeSecondSum ){
						if (logger.isInfoEnabled()) {
							logger.info("PBOC start时间比较,message:{}", "当前时间大于"+PLAZE_PBOC_NOTSEARCH_TIME_START);
						}
						continue;
					}
					//当前时间小于PLAZE_PBOC_NOTSEARCH_TIME_END
					if( currTimeSecondSum <= endTimeSecondSum ){
						if (logger.isInfoEnabled()) {
							logger.info("PBOC end时间比较,message:{}", "当前时间小于"+PLAZE_PBOC_NOTSEARCH_TIME_END);
						}
						continue;
					}
					
					List<TaskCallStatus> taskStatusList = getTaskCallStatusService().queryAllTaskByType(BANK_TASK_TYPE, querySize);

					if (taskStatusList != null && taskStatusList.size() > 0) {
						int size = taskStatusList.size();

						if ( size > 0 ) {
							
								for (TaskCallStatus task : taskStatusList) {
									int countPboc = pbocService.queryCountPbocByAppId(task.getAppId());
									if (countPboc > 0) {
										// 更新任务状态为"2" 进行下次循环
										/*
										 * getTaskCallStatusService().
										 * updateSingleStatus(task.getAppId(),
										 * AppConst.TASK_STATUS_SUCCESS,
										 * BANK_TASK_TYPE);
										 */
										continue;
									}
									// 人行查询30天逻辑
									boolean is30Days = true;
									String appId = task.getAppId();// 当前请求申请件
									String name = task.getName();// 当前请求被查询人姓名
									String idNo = task.getCertNo();// 当前请求被查询人证件号
									String idType = task.getCertType();//当前请求被查询人证件类型
									//插入历史表
									getTaskCallStatusService().saveTaskStatusHistory(appId, BANK_TASK_TYPE);;
									boolean paramErrorFlag = false; // 参数错误标志 
									if( idNo == null || "".equals(idNo) || 
										name == null || "".equals(name) ||
										idType == null || "".equals(idType) ){
										paramErrorFlag = true;
									}else{
										if( idNo.length() != 18 ){
											paramErrorFlag = true;
										}
									}
									String updateStatus = TaskStatusUtil.SUCCESS;
									boolean noRequestFlag = false;
									if( paramErrorFlag ){
										updateStatus = TaskStatusUtil.PARAM_ERROE;
										noRequestFlag = true;
									}
									if( !"10".equals(idType) ){
										noRequestFlag = true;
									}
									if( noRequestFlag ){
										getTaskCallStatusService().updateSingleTaskStatus(appId, updateStatus, BANK_TASK_TYPE, null,
												TaskStatusUtil.CURR_USER , null, null);
										//并行期使用
										//插入历史表
										taskCallStatusService.saveTaskStatusHistory(appId, BANK_TASK_TYPE);
										//删除成功的任务
										//taskCallStatusService.deleteTaskCallStatus(appId, BANK_TASK_TYPE, updateStatus);
										taskCallStatusService.deleteTaskForBankTest(appId, BANK_TASK_TYPE, updateStatus);
										continue;
									}
									Map<String, String> params = new HashMap<String, String>();
									params.put("appId", appId);
									params.put("name", name);
									params.put("idNo", idNo);
									params.put("dayControl", pbocSearchDaysControl);
									// 同一人 30内 最近查过人行的申请件
									String lateAppId = pbocService.queryLateAppIdByDayNameIdNo(params);
									try {
										if (lateAppId != null && !"".equals(lateAppId)) {// 存在的话，走人行复制逻辑
											is30Days = false;
											Map<String, Object> paramsClone = new HashMap<String, Object>();
											paramsClone.put("appId", appId);
											paramsClone.put("lateAppId", lateAppId);
											// 经过存储过程 复制lateAppId的人行数据到当前的appId中
											bankService.saveCloneBankData(paramsClone);
											String callResult = "";
											if (paramsClone.get("callResult") != null) {
												callResult = paramsClone.get("callResult").toString();
											}
											if ("0".equals(callResult)) {// 复制成功
												getTaskCallStatusService().updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS, BANK_TASK_TYPE, null,
														TaskStatusUtil.CURR_USER , null, null);
												//并行期使用
												//插入历史表
												taskCallStatusService.saveTaskStatusHistory(appId, BANK_TASK_TYPE);
												//删除成功的任务
												//taskCallStatusService.deleteTaskCallStatus(appId, BANK_TASK_TYPE, TaskStatusUtil.SUCCESS);
												taskCallStatusService.deleteTaskForBankTest(appId, BANK_TASK_TYPE, TaskStatusUtil.SUCCESS);
											} else {
												// 复制失败
												getTaskCallStatusService().updateSingleTaskStatus(appId, TaskStatusUtil.COPY_ERROE, BANK_TASK_TYPE, null,
														TaskStatusUtil.CURR_USER , null, "1");
											}
										}
									} catch (Exception e) {
										if (logger.isErrorEnabled()) {
											logger.error("bankCopy30Error:"+appId+" Exception:{}",e);
										}
										// 复制失败
										getTaskCallStatusService().updateSingleTaskStatus(appId, TaskStatusUtil.COPY_ERROE, BANK_TASK_TYPE, null,
												TaskStatusUtil.CURR_USER , null, "1");
									}
									if (is30Days) {// 人行正常查询逻辑 非人行30天内复制逻辑
										//请求开始
										getTaskCallStatusService().updateSingleTaskStatus(appId, TaskStatusUtil.START,BANK_TASK_TYPE, null,
												TaskStatusUtil.CURR_USER , "1",null);
										// 人行查询请求
										// 因人行通道只有一条, 目前采用对接首航方式.
										PostMethod post = new PostMethod(url);
										JSONObject json = new JSONObject();
										json.put("api", "102");
									//	json.put("appId", appId);
										json.put("name", name);
										json.put("idNo", idNo);
										json.put("idType", idType);
										if (logger.isInfoEnabled()) {
											logger.info("bankParam: appId:{}",appId);
										}
										boolean requestError=false;//判断请求是否异常
										try {
											String jsonStr = URLEncoder.encode(json.toString(), "UTF-8");
											post.setParameter("json", jsonStr);
											post.addRequestHeader("Content-type",
													"application/x-www-form-urlencoded; charset=UTF-8");
											int state = httpClient.executeMethod(post);
											if (state == 200) {
												JSONObject responseObject = JSONObject.fromObject(
														URLDecoder.decode(post.getResponseBodyAsString(), "UTF-8"));
												String responseCode = responseObject.getString("code");
												if ("9000".equals(responseCode)) {
													if (logger.isInfoEnabled()) {
														logger.info("bankResponseCode: appId:{}, responseCode:{}",  appId,responseCode);
													}
												} else {
													requestError=true;
													if (logger.isInfoEnabled()) {
														logger.info("bankError:appId:{},异常代码:{}",  appId,responseCode);
													}
												}
											}else{
												requestError=true;
												if (logger.isInfoEnabled()) {
													logger.info("bankError:appId:{},state:{}", appId,state);
												}
											}
										} catch (Exception e) {
											requestError=true;
											if (logger.isInfoEnabled()) {
												logger.error("bankError:"+appId+" Exception:{}",e);
											}
										}
										if(requestError){
											//插入历史表
											getTaskCallStatusService().saveTaskStatusHistory(appId, BANK_TASK_TYPE);
											//对方服务响应异常
											getTaskCallStatusService().updateSingleTaskStatus(appId,TaskStatusUtil.SERVE_RESPOSE_ERROE, BANK_TASK_TYPE, null,
													TaskStatusUtil.CURR_USER , null, "1");
										}
									}

									try {
										Thread.sleep(PLAZE_PBOC_SINGLE_SLEEPTIME_MM);
									} catch (InterruptedException e) {

									}
								}
							
						}
					} else {
						if (logger.isDebugEnabled()) {
							logger.debug("[客户端 & 人行] 未查询到人行任务条数!");
						}
					}
				}
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("[客户端 & 人行] 线程异常中断!{}", e);
				}
			}

			if (logger.isInfoEnabled()) {
				logger.info("[客户端 & 人行] 线程已经异常中断重启!");
			}
		}
	}

}
