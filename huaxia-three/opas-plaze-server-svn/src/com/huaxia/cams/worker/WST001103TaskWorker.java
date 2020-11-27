package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.cams.modules.unicom.domain.UnicomAddress;
import com.huaxia.cams.modules.unicom.service.UnicomAddressService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.opas.util.UniaddinforUtil;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 联通地址类信息(居住地址)
 * @author lipengfei
 *
 */
public class WST001103TaskWorker implements Runnable{

	// 日志
	private final static Logger logger = LoggerFactory.getLogger(WST001103TaskWorker.class);
	// 引入参数
	private final static AppConfig appConfig = AppConfig.getInstance();
	// 地址类信息工具类service
	private UnicomAddressService unicomAddressService;
	// 三方通用工具类service
	private TaskCallStatusService taskCallStatusService;
	// 单批查询数量
	private Integer querySize;
	// 查询任务类型
	private String taskType;
	// 查询URL
	private String uniaddinforUrl;
	// 读取超时时间
	private Integer readOuttime;
	// 每批请求睡眠时间
	private Integer sleeptime;
	// 每笔请求睡眠时间
	private Integer requestsleeptime;
	// 请求参数校验标志
	private boolean paramErrorFlag = false;
	// 响应报文代码
	private String responseCode;
	// 响应报文体
	private String responseBody;
	
	// 注入service和变量
	public WST001103TaskWorker() {
		this.unicomAddressService = (UnicomAddressService) SpringContextUtil.getBean("unicomAddressService");
		this.taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		this.querySize = Integer.valueOf(appConfig.getValue("unicom.address.live.query_size"));
		this.taskType = appConfig.getValue("unicom.address.live.task_type");
		this.uniaddinforUrl = appConfig.getValue("unicom.address.live.plaze.webservice.url");
		this.readOuttime = Integer.valueOf(appConfig.getValue("unicom.address.live.webservice.read_timeout"));
		this.sleeptime = Integer.valueOf(appConfig.getValue("unicom.address.webservice.sleep_time"));
		this.requestsleeptime = Integer.valueOf(appConfig.getValue("unicom.address.webservice.request_sleep_time"));
	}
	
	@Override
	public void run() {

		// 循环扫描任务表task_call_status,只要TASK_STATUS字段为0,即发起查询
		while (true) {
			try {
				try {
					// 循环扫表间隔时间,2秒
					Thread.sleep(sleeptime);
				} catch (InterruptedException e) {
					logger.error("循环扫描任务表异常");
				}

				// 获取查询任务(任务标志,查询数量)
				List<Map<String, String>> taskStatusList = taskCallStatusService.queryTaskForUnicomCodda(taskType,querySize);
				if (taskStatusList != null && taskStatusList.size() > 0) {
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 联通地址类信息(居住地址)] 查询到联通地址类信息(居住地址)任务条数：{}", taskStatusList.size());
					}
					// 遍历集合,发起查询
					for (Map<String, String> task : taskStatusList) {
						// 获取appId
						String appId = task.get("APP_ID");
						int countAddress = unicomAddressService.countByAppId(appId,"360016");
						// 如果返回结果表存在此条APPID,跳过不发起查询
						if (countAddress > 0) {
							continue;
						}
						// 请求参数非空校验
						paramErrorFlag = UniaddinforUtil.checkParameter(task);
						// 保存交易代码
						task.put("TRN_CODE", "WST001103");
						// 保存appId和任务类型进历史表,任务状态发生改变以前,保存历史表  taskType=0
						taskCallStatusService.saveTaskStatusHistory(appId, taskType);
						// 参数异常,taskType 0改3,失败次数+1,跳过不发起查询
						if (paramErrorFlag) {
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, taskType,
									null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						// 参数正常,taskType 0改1
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, taskType, null,
								TaskStatusUtil.CURR_USER, "1", null);
						// 组装请求报文
						String queryUniaddInfor = UniaddinforUtil.queryUniaddInfor(task);
						// 发起任务
						Client client = null;
						Object[] result = null;
						try {
							URL url = new URL(uniaddinforUrl);
							HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
							// 设置读取超时时间
							httpConnection.setReadTimeout(readOuttime);
							httpConnection.connect();
							client = new Client(httpConnection.getInputStream(), null);
							// 设置发送超时时间
							client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,
									appConfig.getValue("unicom.address.work.webservice.http_timeout"));
							client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
							client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
							result = client.invoke("invoke", new Object[] { queryUniaddInfor });
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 联通地址类信息(居住地址)] 申请件[{}]调用第三方查询平台服务异常:{}", appId, e);
							}
							// 保存历史表 taskType=1
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							// taskType 1改7,对方服务响应异常
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						} finally {
							// 不要忘记关闭操作
							if (client != null) {
								client.close();
								client = null;
							}
						}

						// 解析返回报文
						JSONObject jsonResponse = JSONObject.fromObject(String.valueOf(result[0]));
						
//						logger.info("联通居住地址接收三方平台的返回报文>>"+jsonResponse.toString());
						
						Map<String,String> reponseMap = UniaddinforUtil.parseReturnMsg(jsonResponse);
						responseCode = reponseMap.get("responseCode");
						responseBody = reponseMap.get("responseBody");
							
						// 999999是DMZ返回的错误码,是请求数据源异常
						if ("999999".equals(responseCode)) {
							// 保存历史表 taskType=1
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							// taskType 1改7,对方服务响应异常
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						// 三方查询平台数量上限999994,不在查询时间范围内999993
						} else if(responseCode.equals("999994") || responseCode.equals("999993")){
							// 保存历史表 taskType=1
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							// taskType 1改15,查询数量已达上限或不在可查询时间范围内
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.QUERY_LIMIT, taskType,
									null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						} else {
							// 解析报文体,并获取保存报文体的实体类
							UnicomAddress unicomAddress = UniaddinforUtil.parseReturnBody(responseBody);
							// 保存appid
							unicomAddress.setAppId(appId);
							// 保存运营商
							unicomAddress.setCarrier(reponseMap.get("carrier"));
							// 保存返回码中文描述
							unicomAddress.setResponseResultDesc(reponseMap.get("checkDesc"));
							// 保存产品编码
							unicomAddress.setTrn_id(reponseMap.get("TRN_ID"));
							// 保存手机号
							unicomAddress.setMobile(reponseMap.get("mobile"));
							// 保存产品ID
							unicomAddress.setApiKey("360016");
							// 判断ResponseCode,ResponseCode是DMZ返回结果报文中的查询状态
							String code = unicomAddress.getResponseCode();
							if (code != null && !"".equals(code) && "00".equals(code)) {
								try {
									// 查询结果正常,入库unicomAddress,将状态改成2
									unicomAddressService.saveUnicomAddress(unicomAddress);
									// 保存历史表 taskType=1
									taskCallStatusService.saveTaskStatusHistory(appId, taskType);
									// 修改状态 1改2,查询成功
									taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
											taskType, null, TaskStatusUtil.CURR_USER, null, null);
								} catch (Exception e) {
									// 入库异常,将状态改为12入库异常
									if (logger.isErrorEnabled()) {
										logger.error("[客户端 & 联通地址类信息(居住地址)] 报文数据持久化失败   " + appId + " Exception:{}", e);
									}
									// 保存历史表 taskType=1
									taskCallStatusService.saveTaskStatusHistory(appId, taskType);
									// 修改状态 1改12,入库异常
									taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,
											taskType, null, TaskStatusUtil.CURR_USER, null, "1");
									continue;
								}
							} else if (code != null && !"".equals(code) && "14".equals(code)) {
								// 查询结果显示不支持该手机号,入库unicomAddress
								unicomAddressService.saveUnicomAddress(unicomAddress);
								// 保存历史表 taskType=1
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								// 修改状态 1改2,查询成功
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
										taskType, null, TaskStatusUtil.CURR_USER, null, null);
							} else if (code != null && !"".equals(code) && "16".equals(code)) {
								// 查询无该手机号记录,入库unicomAddress
								unicomAddressService.saveUnicomAddress(unicomAddress);
								// 保存历史表 taskType=1
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								// 修改状态 1改2,查询成功
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
										taskType, null, TaskStatusUtil.CURR_USER, null, null);
							} else if (code != null && !"".equals(code) && "报文解析异常".equals(code)) {
								// 保存历史表 taskType=1
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								// 修改状态 1改8,报文解析异常
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							} else {
								// 保存历史表 taskType=1
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								// 查询状态1改11,报文响应码异常
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.RESPOSE_CODE_ERROR,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
						}
						
					}
					// 每笔之间的查询间隔 ,0.2秒
					try {
						Thread.sleep(requestsleeptime);
					} catch (InterruptedException e) {

					}
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 联通地址类信息(居住地址)] 未查询到联通地址类信息(居住地址)任务条数!");
					}
				}

			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("[客户端 & 联通地址类信息(居住地址)] 线程异常中断 重启  Exception:{}", e);
				}
			}
		}
	}

}
