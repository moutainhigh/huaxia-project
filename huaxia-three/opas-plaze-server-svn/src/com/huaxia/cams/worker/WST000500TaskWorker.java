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
import com.huaxia.opas.domain.tripartite.SeaAir;
import com.huaxia.opas.service.tripartite.SeaAirService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

/**
 * 
 * 海航信息
 *
 */
public class WST000500TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WST000500TaskWorker.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	// 信审同步地址（默认）
	private String defaultXinShenTongBuUrl;

	private final static String SeaAirTaskType = "000500";// 海航任务类型
	private final Integer querySize;

	private SeaAirService seaAirService;
	private TaskCallStatusService taskCallStatusService;
	private Integer PLAZE_SEAAIR_THREAD_SLEEPTIME_MM;
	private Integer PLAZE_SEAAIR_SINGLE_SLEEPTIME_MM;
	private String  PLAZE_SEAAIR_URL;
	public WST000500TaskWorker() {
		// 获取配置参数
		seaAirService = (SeaAirService) SpringContextUtil.getBean("seaAirService");
		taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		defaultXinShenTongBuUrl = appConfig.getValue("DEFAULT_XINSHEN_TONGBU_URL");
		querySize = Integer.valueOf(appConfig.getValue("PLAZE_SEAAIR_TASK_QUERY_SIZE"));

		PLAZE_SEAAIR_THREAD_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_SEAAIR_THREAD_SLEEPTIME_MM"));
		PLAZE_SEAAIR_SINGLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_SEAAIR_SINGLE_SLEEPTIME_MM"));
		PLAZE_SEAAIR_URL = appConfig.getValue("hnair.dmz.webservice.url");
	}

	@Override
	public void run() {
	 while (true) {
	  try{
			try {
				Thread.sleep(PLAZE_SEAAIR_THREAD_SLEEPTIME_MM);
			} catch (InterruptedException e) {

			}
			List<TaskCallStatus> listTask = taskCallStatusService.queryAllTaskByType(SeaAirTaskType, querySize);
			for (TaskCallStatus task : listTask) {
				String appId = task.getAppId();
				// 构建请求参数
				String mobile = task.getMobile();
				String certNo = task.getCertNo();
				String crtUser = task.getName();
				String certType = task.getCertType();
				String trnId = task.getTaskId();// 交易编号
				int countSeaAir = seaAirService.getCountByAppId(appId);// 查看海航数据表是否存在此appId的数据若存在
				if (countSeaAir > 0) {
					continue;
				}
				// 通过调用 proxy工程,进行海航报文查询
				// 构造请求报文
				String identityNo = task.getCertNo();// 身份证号

				boolean paramErrorFlag = false;// 参数错误标识
				if (identityNo == null || "".equals(identityNo)) {
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 海航] 请求的证件号为空，流水号为：{}", appId);
					}
					paramErrorFlag = true;
				}
				if (mobile == null || "".equals(mobile) || mobile.trim().length() != 11) {
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 海航] 请求的手机号数据异常（空或非11位），流水号为：{}", appId);
					}
					paramErrorFlag = true;
				}
				taskCallStatusService.saveTaskStatusHistory(appId, SeaAirTaskType);
				if (paramErrorFlag) {
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, SeaAirTaskType,
							null, TaskStatusUtil.CURR_USER, null, "1");
					continue;
				}
				// 请求开始
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, SeaAirTaskType, null,
						TaskStatusUtil.CURR_USER, "1", null);
				String mobileReq = mobile.substring(0, 3) + "****" + mobile.substring(7, 11);
				String strInparams = "{'id':'" + identityNo + "','mobile':'" + mobileReq + "'}";
				boolean requestFlag = false;// 内部请求是否正常标识
				Client client = null;
				Object[] result = null;
				try {
					Map<String, Object> head = new HashMap<String, Object>();
					head.put("REQUEST_CHANNEL", "CAMS");
					head.put("TRN_ID", trnId);

					Map<String, Object> body = new HashMap<String, Object>();
					body.put("QUERY_MODE", "1");
					body.put("CERT_TYPE", certType);
					body.put("CERT_NO", certNo);
					body.put("NAME", crtUser);
					body.put("MOBILE", mobile);
					body.put("APPID", appId);
					body.put("REQUESTBODY", strInparams);

					Map<String, Object> request = new HashMap<String, Object>();
					request.put("HEAD", head);
					request.put("BODY", body);

					Map<String, Object> params = new HashMap<String, Object>();
					params.put("REQUEST", request);

					JSONObject json = JSONObject.fromObject(params);
					String paramsStr = JSON.toJSONString(json);

						URL url = new URL(PLAZE_SEAAIR_URL);
						HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
						httpConnection.setReadTimeout(120000);// 设置http连接的读超时，单位是毫秒
						httpConnection.connect();
						client = new Client(httpConnection.getInputStream(), null);
						// 设置发送超时限制，单位是毫秒
						client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, String.valueOf(120000));
						client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
						client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
						result = client.invoke("invoke", new Object[] { paramsStr });
					}catch (Exception e) {
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 海航] 请求处理异常  " + appId + " Exception:{}", e);
						}
						requestFlag = true;
					} finally {
						if (client != null) {
							client.close();
							client = null;
						}
				    }
					taskCallStatusService.saveTaskStatusHistory(appId, SeaAirTaskType);
					if( requestFlag ){
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
								SeaAirTaskType, null, TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
					boolean responseFlag = false; //报文返回是否异常
						// 解析响应数据入库
						try {
							String message = "";
								// 分解请求参数
								JSONObject jsonResponse = JSONObject.fromObject(String.valueOf(result[0]));
								if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
										&& !"".equals(jsonResponse.getString("RESPONSE"))) {
									JSONObject jsonRes = JSONObject.fromObject(jsonResponse.getString("RESPONSE"));

									if (jsonRes.containsKey("HEAD") && jsonRes.getString("HEAD") != null
											&& !"".equals(jsonRes.getString("HEAD"))) {
										JSONObject jsonHead = JSONObject.fromObject(jsonRes.getString("HEAD"));
										if (jsonHead.containsKey("TRN_ID") && jsonHead.getString("TRN_ID") != null
												&& !"".equals(jsonHead.getString("TRN_ID"))) {
											trnId = jsonHead.getString("TRN_ID");
										}
									}

									if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
											&& !"".equals(jsonRes.getString("BODY"))) {
										JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
										if (jsonBody.containsKey("RESPONSE_BODY")
												&& jsonBody.getString("RESPONSE_BODY") != null
												&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
											message = jsonBody.getString("RESPONSE_BODY");
										}
										if (jsonBody.containsKey("APPID") && jsonBody.getString("APPID") != null
												&& !"".equals(jsonBody.getString("APPID"))) {
											appId = jsonBody.getString("APPID");
										}
									}

								}

								if("999".equals(message)){
									taskCallStatusService.saveTaskStatusHistory(appId, SeaAirTaskType);
									taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
											SeaAirTaskType, null, TaskStatusUtil.CURR_USER, null, "1");
									continue;
								}
								
								// 报文处理,数据解析入库，同步方法调用
								SeaAir seaAir = parseSeaAir(message, appId, SeaAirTaskType);
								try {
									if (seaAir != null) {
										seaAir.setAppId(appId);
										seaAirService.save(seaAir);
										if (logger.isDebugEnabled()) {
											logger.debug("[客户端 & 海航] 报文数据持久化成功, 申请件编号: {}", appId);
										}
										// 删除调用webservice修改主卡进件表的方式,直接调用service方法修改
										String cid = seaAir.getCid();// 海航报文中的会员号
										if (cid != null && !"".equals(cid)) {
											String appIdFront = appId.substring(0, 15);
											Map<String,Object> params=new HashMap<String,Object>();
											params.put("appId", appIdFront);
											params.put("seaMemberId", cid);
											params.put("lstUpdUser", "SeaSfSYSTEM");
											seaAirService.updateCardC1C2SeaMemberId(params);
											//updateC1C2MemberId(appIdFront, cid, defaultXinShenTongBuUrl);
										}
										// 成功
										taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
												SeaAirTaskType, null, TaskStatusUtil.CURR_USER, null, null);
									}
								} catch (Exception e) {
									if (logger.isErrorEnabled()) {
										logger.error("[客户端 & 海航] 报文数据入库失败   " + appId + " Exception:{}", e);
									}
									taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,
											SeaAirTaskType, null, TaskStatusUtil.CURR_USER, null, "1");
								}


					} catch (Exception e) {
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 海航] 数据解析异常 getResult" + appId + " Exception:{}", e);
						}
						responseFlag = true ;
					}
					if( responseFlag ){
						// 报文解析
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, SeaAirTaskType, null,
								TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
				 
				try {
					Thread.sleep(PLAZE_SEAAIR_SINGLE_SLEEPTIME_MM);
				} catch (InterruptedException e) {

				}
			}

	}catch(Exception e){
		if (logger.isErrorEnabled()) {
			logger.error("[客户端 & 海航] 线程异常中断 重启  Exception:{}", e);
		}
	}
	}
	}

	/**
	 * @Title:parseSeaAir
	 * @Description:海航解析报文
	 * @param message
	 * @return
	 * @throws Exception
	 * @author: gaohui
	 * @Date:2017年12月27日下午2:52:42
	 */
	public SeaAir parseSeaAir(String message, String appId, String SeaAirTaskType) {
		try {
			if (message == null || "".equals(message)) {
				if (logger.isErrorEnabled()) {
					logger.error("[客户端 & 海航] 数据解析异常! {}", "海航报文为空");
				}
				return null;
			}
			JSONObject json = JSONObject.fromObject(message);
			if (json.containsKey("success") && !json.getBoolean("success") && json.containsKey("error_code")
					&& "999999".equals(json.getString("error_code"))) {

				// 对方服务响应异常
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE, SeaAirTaskType,
						null, TaskStatusUtil.CURR_USER, null, "1");
				return null;
			}
			SeaAir seaAir = new SeaAir();
			// 响应代码
			if (json.containsKey("errorCode") && json.getString("errorCode") != null
					&& !"".equals(json.getString("errorCode"))) {
				seaAir.setErrorcode(json.getString("errorCode"));
			}
			// 响应结果说明
			if (json.containsKey("errorMessage") && json.getString("errorMessage") != null
					&& !"".equals(json.getString("errorMessage"))) {
				seaAir.setErrormessage(json.getString("errorMessage"));
			}
			// 服务器端事务或交易编号
			if (json.containsKey("sTId") && json.getString("sTId") != null && !"".equals(json.getString("sTId"))) {
				seaAir.setStid(json.getString("sTId"));
			}
			// 客户器端事务或交易编号
			if (json.containsKey("cTId") && json.getString("cTId") != null && !"".equals(json.getString("cTId"))) {
				seaAir.setCtid(json.getString("cTId"));
			}
			// 扩展信息
			if (json.containsKey("extendsInfo") && json.getString("extendsInfo") != null
					&& !"".equals(json.getString("extendsInfo"))) {
				seaAir.setExtendsinfo(json.getString("extendsInfo"));
			}
			// 是否有过飞行记录
			if (json.containsKey("vipFlg") && json.getString("vipFlg") != null
					&& !"".equals(json.getString("vipFlg"))) {
				seaAir.setVipflg(json.getString("vipFlg"));
			}
			// 会员卡号
			if (json.containsKey("cid") && json.getString("cid") != null && !"".equals(json.getString("cid"))) {
				seaAir.setCid(json.getString("cid"));
			}
			// 最近一年飞行次数
			if (json.containsKey("flyCnt") && json.getString("flyCnt") != null
					&& !"".equals(json.getString("flyCnt"))) {
				seaAir.setFlycnt(Integer.parseInt(json.getString("flyCnt").split("[.]")[0]));// 避免飞行次数为类似
																								// 0.0的数据报错
			}
			// 会员级别
			if (json.containsKey("grade") && json.getString("grade") != null && !"".equals(json.getString("grade"))) {
				seaAir.setGrade(json.getString("grade"));
			}
			// 手机号是否与入参一致
			if (json.containsKey("resrv1") && json.getString("resrv1") != null
					&& !"".equals(json.getString("resrv1"))) {
				seaAir.setResrv1(json.getString("resrv1"));
			}
			// 是否有兑奖资格
			if (json.containsKey("resrv2") && json.getString("resrv2") != null
					&& !"".equals(json.getString("resrv2"))) {
				seaAir.setResrv2(json.getString("resrv2"));
			}
			// 预留字段3
			if (json.containsKey("resrv3") && json.getString("resrv3") != null
					&& !"".equals(json.getString("resrv3"))) {
				seaAir.setResrv3(json.getString("resrv3"));
			}
			return seaAir;
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 & 海航] 数据解析异常   " + appId + " Exception:{}", e);
			}
			// 报文解析
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, SeaAirTaskType, null,
					TaskStatusUtil.CURR_USER, null, "1");
			return null;
		}
	}

	/**
	 * @Title:updateC1C2MemberId
	 * @Description:同步主卡和附卡海航的会员号
	 * @param appId
	 * @param cid
	 *            海航 会员号
	 * @author: gaohui
	 * @Date:2018年1月23日上午10:26:37
	 */
	public void updateC1C2MemberId(String appId, String cid, String defaultXinShenTongBuUrl) {
		JSONObject json = new JSONObject();
		Client client = null;
		try {
			json.put("appId", appId);
			json.put("seaMemberId", cid);
			String param = json.toString();
			Object[] obj = new Object[] { param };
			// 生产 /was/tomcat/*plugin
			// "http://106.32.6.27:9096/opas-naps-plugin/webservice/SeaAirCallBackWebService?wsdl"
			// C4_BUEMB C4_BUEMB2
			client = new Client(new URL(defaultXinShenTongBuUrl));
			client.invoke("single", obj);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 & 海航] 同步海航会员号异常   " + appId + " Exception:{}", e);
			}
		} finally {
			if (client != null) {
				client.close();
				client = null;
			}
		}
	}

}
