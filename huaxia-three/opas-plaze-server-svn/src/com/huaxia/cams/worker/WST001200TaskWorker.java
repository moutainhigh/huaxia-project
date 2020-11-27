package com.huaxia.cams.worker;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.tripartite.QyhyInfo;
import com.huaxia.opas.domain.tripartite.QyhyInfoBasic;
import com.huaxia.opas.domain.tripartite.QyhyInfoData;
import com.huaxia.opas.domain.tripartite.QyhyInfoMetaData;
import com.huaxia.opas.domain.tripartite.QyhyInfoOrgBasic;
import com.huaxia.opas.domain.tripartite.QyhyInfoOrgDetail;
import com.huaxia.opas.domain.tripartite.QyhyInfoPerson;
import com.huaxia.opas.domain.tripartite.QyhyInfoPunishBreak;
import com.huaxia.opas.domain.tripartite.QyhyInfoShareHolder;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStore;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreBasic;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreData;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreMetaData;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreOrgBasic;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreOrgDetail;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStorePerson;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStorePunishBreak;
import com.huaxia.opas.domain.tripartite.qyhystore.QyhyStoreShareHolder;
import com.huaxia.opas.service.tripartite.QyhyInfoService;
import com.huaxia.opas.service.tripartite.QyhyStoreService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.opas.util.qyhy.QyhyRequestMessageUtil;
import com.huaxia.opas.util.qyhy.QyhyResponseParseUtil;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.GuidUtil;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * 企业行业
 *
 */
public class WST001200TaskWorker implements Runnable {

	private final static Logger logger = LoggerFactory.getLogger(WST001200TaskWorker.class);

	private final static AppConfig appConfig = AppConfig.getInstance();

	private final String qyhyTaskType;// 企业行业任务类型

	private final String qyhyTradeCode; // 企业行业交易码(T001200)

	private final String qyhyWebServiceUrl; // 企业行业接口请求url

	private Integer qyhyTaskQueryNum;// 每次企业行业查询任务最大条数

	private TaskCallStatusService taskCallStatusService;

	private QyhyInfoService qyhyInfoService;
    private QyhyStoreService qyhyStoreService;
	private Integer PLAZE_QYHY_THREAD_SLEEPTIME_MM;

	private Integer PLAZE_QYHY_SINGLE_SLEEPTIME_MM;
	private Integer qyhyStoreQueryDays;//企业信息库 查询天数控制

	public WST001200TaskWorker() {
		// 获取配置参数
		qyhyTaskQueryNum = Integer.valueOf(appConfig.getValue("PLAZE_QYHYTASK_QUERY_SIZE"));
		qyhyTaskType = appConfig.getValue("PLAZE_QYHY_TASK_TYPE");
		qyhyTradeCode = appConfig.getValue("qyhy.trade_code");
		qyhyWebServiceUrl = appConfig.getValue("qyhy.webservice.url");
		// 初始化服务接口
		taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		qyhyStoreService = ((QyhyStoreService) SpringContextUtil.getBean("qyhyStoreService"));
		qyhyInfoService = (QyhyInfoService) SpringContextUtil.getBean("qyhyInfoService");
		PLAZE_QYHY_THREAD_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_QYHY_THREAD_SLEEPTIME_MM"));
		PLAZE_QYHY_SINGLE_SLEEPTIME_MM = Integer.valueOf(appConfig.getValue("PLAZE_QYHY_SINGLE_SLEEPTIME_MM"));
		qyhyStoreQueryDays=Integer.valueOf(appConfig.getValue("QYHY_STORE_QUERY_DAYS_CONTROL"));
	}

	@Override
	public void run() {
	 while (true) {
	   try {
			try {
				Thread.sleep(PLAZE_QYHY_THREAD_SLEEPTIME_MM);
			} catch (InterruptedException e) {

			}

			List<Map<String, String>> taskStatusList = taskCallStatusService.queryTaskForQyhy(qyhyTaskType, qyhyTaskQueryNum);

			if (taskStatusList != null && taskStatusList.size() > 0) {
				int size = taskStatusList.size();

				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 企业行业] 查询到企业行业任务条数：{}", size);
				}

				for (Map<String, String> task : taskStatusList) {
					String appId = task.get("APPID");
					int countQyhy = qyhyInfoService.getCountByAppId(appId);
					if (countQyhy > 0) {
						continue;
					}
					String enterprise = task.get("ENTERPRISE");
					boolean paramErrorFlag = false;// 参数错误标识
					if (enterprise == null || "".equals(enterprise)) {
						paramErrorFlag = true;
						if (logger.isDebugEnabled()) {
							logger.debug("[客户端 & 企业行业] 请求的单位全称为空，流水号为：{}", appId);
						}
					}
					taskCallStatusService.saveTaskStatusHistory(appId, qyhyTaskType);
					if (paramErrorFlag) {
						// 参数异常
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARAM_ERROE, qyhyTaskType,
								null, TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
					// 请求开始
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.START, qyhyTaskType, null,
							TaskStatusUtil.CURR_USER, "1", null);
					//判断此单位全称 30天内是否入过企业信息库，如果入过 则从企业信息库取
					String relateUuid = qyhyStoreService.findRelateUuidByEnterPriseDays(enterprise,qyhyStoreQueryDays);
					boolean isCopy30Days = true;//30十天复制逻辑
					try {
					if(relateUuid!=null&&!"".equals(relateUuid)){//进行企业库复制逻辑
						isCopy30Days=false;
						
						Map<String, Object> paramsClone = new HashMap<String, Object>();
						paramsClone.put("relateUuid", relateUuid);
						paramsClone.put("appId", appId);
						qyhyStoreService.saveCloneQyhyInfoData(paramsClone);
						String callResult = "";
						if (paramsClone.get("callResult") != null) {
							callResult = paramsClone.get("callResult").toString();
						}
						if ("0".equals(callResult)) {// 复制成功
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS, qyhyTaskType, null,
									TaskStatusUtil.CURR_USER, null, null);
						} else {
							// 复制失败
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.COPY_ERROE, qyhyTaskType, null,
									TaskStatusUtil.CURR_USER , null, "1");
						}
					}
					} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 企业行业信息] 企业信息库复制逻辑异常  "+appId+" Exception:{}",e);
							}
							// 复制失败
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.COPY_ERROE, qyhyTaskType, null,
									TaskStatusUtil.CURR_USER , null, "1");
					}
					//若是走了三十天复制逻辑
					if( !isCopy30Days ){
						continue;
					}
					// 创建请求客户端
					Client client = null;
					Object[] result = null;
					URL url = null;
					try {
						// 组装请求报文
						String queryMode = "3";
						String requestParamsStr = QyhyRequestMessageUtil.assemblingParameters(task.get("TRNID"),
							qyhyTradeCode, enterprise, task.get("IDENTITY_TYPE"), task.get("IDENTITY_NO"),
							task.get("NAME"), task.get("MOBILE"), queryMode);
						url = new URL(qyhyWebServiceUrl);
						HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
						httpConnection.setReadTimeout(240000);// 设置http连接的读超时，单位是毫秒
						httpConnection.connect();
						client = new Client(httpConnection.getInputStream(), null);
						client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "240000");// 设置发送超时限制，单位是毫秒
						client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
						client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");

						Object[] obj = new Object[] { requestParamsStr };
						result = client.invoke("invoke", obj);
					} catch (Exception e) {
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 企业行业] 申请件[{}]调用第三方查询平台服务异常:{}"+appId, e);
						}
						taskCallStatusService.saveTaskStatusHistory(appId, qyhyTaskType);
						// 对方服务异常
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
								qyhyTaskType, null, TaskStatusUtil.CURR_USER, null, "1");
					} finally {
						if (client != null) {
							client.close();
							client = null;
						}
					}
					
					
					// 解析返回报文
					// 企业行业原始报文
					String responseBody = QyhyResponseParseUtil.parsingResult(String.valueOf(result[0]));
					
					/** 此处不方便获取返回码,直接判断原始报文内容,如果原始报文是999,说明是拼接返回的报文 */
					if("999".equals(responseBody)){
						taskCallStatusService.saveTaskStatusHistory(appId, qyhyTaskType);
						taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
								qyhyTaskType, null, TaskStatusUtil.CURR_USER, null, "1");
						continue;
					}
					
					// 如果需要用到响应代码：RESPONSE_CODE、或者交易编号：trnId,可以从QyhyResponseParseUtil类中拼接返回
					if (responseBody != null && !"".equals(responseBody)) {
						// 插入历史表
						taskCallStatusService.saveTaskStatusHistory(appId, qyhyTaskType);
						// 报文处理 ，解析原始报文
						QyhyInfo qyhyInfo = parseQyhyInfo(responseBody, appId, enterprise);
						try {
							if (qyhyInfo != null) {
								 Map<String, String> updateMap = qyhyInfoService.saveQyhyInfoUpdateDataAdapterAction(qyhyInfo, appId, qyhyTaskType);
								 String status = updateMap.get("status");
								 String errorCode = updateMap.get("errorCode");
								 String failureAddNum = updateMap.get("failureAddNum");
								 //根据service 返回参数  修改任务状态 
								taskCallStatusService.updateSingleTaskStatus(appId, status, qyhyTaskType,
											errorCode, TaskStatusUtil.CURR_USER, null, failureAddNum);	 
								 
								if (logger.isDebugEnabled()) {
									logger.debug("[客户端 & 企业行业信息] 报文数据持久化成功, 申请件编号: {}", appId);
								}
								if(qyhyInfo.getQyhyStore()!=null){
								 try {
									 qyhyStoreService.saveQyhyStoreUpdateDataAdapterAction(qyhyInfo.getQyhyStore(),enterprise);
								 }catch (Exception e){
										if (logger.isWarnEnabled()) {
											logger.warn("[客户端 & 企业行业信息]企业信息库 持久化失败, 申请件编号: {}", appId+" 异常信息："+e.getStackTrace());
									   }
									}
								}
							}
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 企业行业信息] 报文数据持久化失败   " + appId + " Exception:{}", e);
							}
							// 入库异常
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE, qyhyTaskType,
									null, TaskStatusUtil.CURR_USER, null, "1");
						}

					}

					try {
						Thread.sleep(PLAZE_QYHY_SINGLE_SLEEPTIME_MM);
					} catch (InterruptedException e) {

					}
				}

			} else {
				if (logger.isDebugEnabled()) {
					logger.debug("[客户端 & 企业行业] 未查询到企业行业任务条数!");
				}
			}
			
	}catch(Exception e){
		if (logger.isErrorEnabled()) {
			logger.error("[客户端 & 企业行业] 线程异常中断 重启  Exception:{}", e);
		}
	}
	}
	}

    /**
     *@Title:parseQyhyInfo
     *@Description:企业行业信息解析报文
     *@param message
     *@param appId
     *@param enterprise
     *@return
     *@author: gaohui
     *@Date:2018年4月23日下午3:38:06
     */
	public QyhyInfo parseQyhyInfo(String message,String appId,String enterprise) {
		
		try{
		if (message == null || "".equals(message)) {
			if (logger.isErrorEnabled()) {
				logger.error("[客户端 & 企业行业信息] 数据解析异常! {}","企业行业信息报文为空"+appId);
			}
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE, qyhyTaskType, null,
					TaskStatusUtil.CURR_USER , null, "1");
			return null;
		}
		JSONObject json = JSONObject.fromObject(message);
		if (json.containsKey("success")&&!json.getBoolean("success") &&json.containsKey("error_code")
				&& "999999".equals(json.getString("error_code"))) {
		/*	String errorCode = json.getString("error_code");
			String errorDesc = json.getString("error_message");
			getLogRecordResultService().save(appId, "企业行业信息", false, errorCode, errorDesc);*/
			if (logger.isWarnEnabled()) {
				logger.warn("企业行业信息查询Proxy响应失败, 申请件编号: {}", appId);
			}
			//对方服务响应异常
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE, qyhyTaskType, null,
					TaskStatusUtil.CURR_USER , null, "1");
			return null;
		} 
		QyhyInfo qyhyInfo = new QyhyInfo();
		QyhyStore qyhyStore=new QyhyStore();//企业信息库
		//===========1.企业行业信息 成功失败 参数存储表
		QyhyInfoData qyhyInfoData =new QyhyInfoData();
		qyhyInfoData.setAppId(appId);
		Integer code=0;
		String msg="";
		String relateUuid=GuidUtil.getGuid();//关联的uuid
		boolean insertStoreFlag=false;//是否 将企业信息库 入库的标志 
		//响应代码 200成功 非200是具体错误代码
		if (json.containsKey("CODE") && json.getString("CODE") != null && !"".equals(json.getString("CODE"))) {
			code=Integer.parseInt(json.getString("CODE"));
			qyhyInfoData.setCode(code);
		}else{
			//对方服务响应码异常 为空
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.RESPOSE_CODE_ERROR, qyhyTaskType, null,
					TaskStatusUtil.CURR_USER , null, "1");
			return null;
		}
		//返回的信息
		if (json.containsKey("MSG") && json.getString("MSG") != null && !"".equals(json.getString("MSG"))) {
			msg=json.getString("MSG");
			qyhyInfoData.setMsg(msg);
		}
		if(code==200){
			QyhyStoreData qyhyStoreData =new QyhyStoreData();//(1).企业信息库 企业行业信息 成功失败 参数存储表
			qyhyStoreData.setEnterprise(enterprise);
			qyhyStoreData.setCode(code);
			qyhyStoreData.setMsg(msg);
			qyhyStoreData.setRelateUuid(relateUuid);
			qyhyStore.setQyhyStoreData(qyhyStoreData);
		}
		qyhyInfo.setQyhyInfoData(qyhyInfoData);
		
		if (json.containsKey("ENT_INFO") && json.getString("ENT_INFO") != null && !"".equals(json.getString("ENT_INFO"))) {
		
			JSONObject entInfo = JSONObject.fromObject(json.getString("ENT_INFO"));
			
			//===========2.SHAREHOLDER 股东及出资信息 	
			if (entInfo.containsKey("SHAREHOLDER") && entInfo.getString("SHAREHOLDER") != null && !"".equals(entInfo.getString("SHAREHOLDER"))) {
				
				JSONArray qyhyInfoShareHolderArray = JSONArray.fromObject(entInfo.get("SHAREHOLDER"));
				List<QyhyInfoShareHolder> qyhyInfoShareHolderList =new ArrayList<QyhyInfoShareHolder>();
				List<QyhyStoreShareHolder> qyhyStoreShareHolderList =new ArrayList<QyhyStoreShareHolder>();//(2).企业信息库 SHAREHOLDER 股东及出资信息 	
				for (int i = 0; i < qyhyInfoShareHolderArray.size(); i++) {
					if(qyhyInfoShareHolderArray.get(i)!=null){
						QyhyInfoShareHolder qyhyInfoShareHolder=new QyhyInfoShareHolder();
						QyhyStoreShareHolder qyhyStoreShareHolder=new QyhyStoreShareHolder();
						JSONObject qyhyInfoShareHolderObj = (JSONObject) qyhyInfoShareHolderArray.get(i);
						int addControl=0;
						//出资日期
						if (qyhyInfoShareHolderObj.containsKey("CONDATE") && qyhyInfoShareHolderObj.getString("CONDATE") != null 
								&& !"".equals(qyhyInfoShareHolderObj.getString("CONDATE"))) {
							qyhyInfoShareHolder.setCondate(qyhyInfoShareHolderObj.getString("CONDATE"));
							qyhyStoreShareHolder.setCondate(qyhyInfoShareHolderObj.getString("CONDATE"));
							addControl++;
						}
						//认缴出资额（万元）
						if (qyhyInfoShareHolderObj.containsKey("SUBCONAM") && qyhyInfoShareHolderObj.getString("SUBCONAM") != null 
								&& !"".equals(qyhyInfoShareHolderObj.getString("SUBCONAM"))) {
							qyhyInfoShareHolder.setSubconam(qyhyInfoShareHolderObj.getString("SUBCONAM"));
							qyhyStoreShareHolder.setSubconam(qyhyInfoShareHolderObj.getString("SUBCONAM"));
							addControl++;
						}
						//出资比例
						if (qyhyInfoShareHolderObj.containsKey("FUNDEDRATIO") && qyhyInfoShareHolderObj.getString("FUNDEDRATIO") != null 
								&& !"".equals(qyhyInfoShareHolderObj.getString("FUNDEDRATIO"))) {
							qyhyInfoShareHolder.setFundedratio(qyhyInfoShareHolderObj.getString("FUNDEDRATIO"));
							qyhyStoreShareHolder.setFundedratio(qyhyInfoShareHolderObj.getString("FUNDEDRATIO"));
							addControl++;
						}
						//股东类型
						if (qyhyInfoShareHolderObj.containsKey("INVTYPE") && qyhyInfoShareHolderObj.getString("INVTYPE") != null 
								&& !"".equals(qyhyInfoShareHolderObj.getString("INVTYPE"))) {
							qyhyInfoShareHolder.setInvtype(qyhyInfoShareHolderObj.getString("INVTYPE"));
							qyhyStoreShareHolder.setInvtype(qyhyInfoShareHolderObj.getString("INVTYPE"));
							addControl++;
						}
						//出资方式
						if (qyhyInfoShareHolderObj.containsKey("CONFORM") && qyhyInfoShareHolderObj.getString("CONFORM") != null 
								&& !"".equals(qyhyInfoShareHolderObj.getString("CONFORM"))) {
							qyhyInfoShareHolder.setConform(qyhyInfoShareHolderObj.getString("CONFORM"));
							qyhyStoreShareHolder.setConform(qyhyInfoShareHolderObj.getString("CONFORM"));
							addControl++;
						}
						//认缴出资币种
						if (qyhyInfoShareHolderObj.containsKey("REGCAPCUR") && qyhyInfoShareHolderObj.getString("REGCAPCUR") != null 
								&& !"".equals(qyhyInfoShareHolderObj.getString("REGCAPCUR"))) {
							qyhyInfoShareHolder.setRegcapcur(qyhyInfoShareHolderObj.getString("REGCAPCUR"));
							qyhyStoreShareHolder.setRegcapcur(qyhyInfoShareHolderObj.getString("REGCAPCUR"));
							addControl++;
						}
						//股东名称
						if (qyhyInfoShareHolderObj.containsKey("SHANAME") && qyhyInfoShareHolderObj.getString("SHANAME") != null 
								&& !"".equals(qyhyInfoShareHolderObj.getString("SHANAME"))) {
							qyhyInfoShareHolder.setShaname(qyhyInfoShareHolderObj.getString("SHANAME"));
							qyhyStoreShareHolder.setShaname(qyhyInfoShareHolderObj.getString("SHANAME"));
							addControl++;
						}
						//国别
						if (qyhyInfoShareHolderObj.containsKey("COUNTRY") && qyhyInfoShareHolderObj.getString("COUNTRY") != null 
								&& !"".equals(qyhyInfoShareHolderObj.getString("COUNTRY"))) {
							qyhyInfoShareHolder.setCountry(qyhyInfoShareHolderObj.getString("COUNTRY"));
							qyhyStoreShareHolder.setCountry(qyhyInfoShareHolderObj.getString("COUNTRY"));
							addControl++;
						}
						if(addControl>0){
							qyhyInfoShareHolder.setAppId(appId);
							qyhyInfoShareHolderList.add(qyhyInfoShareHolder);
							qyhyStoreShareHolder.setRelateUuid(relateUuid);
							qyhyStoreShareHolderList.add(qyhyStoreShareHolder);
						}
						
					}
				}
				if(qyhyInfoShareHolderList!=null&&qyhyInfoShareHolderList.size()>0){
				   qyhyInfo.setQyhyInfoShareHolderList(qyhyInfoShareHolderList);
				   qyhyStore.setQyhyStoreShareHolderList(qyhyStoreShareHolderList);
				   insertStoreFlag=true;
				}
			}
			//===========3.BASIC 照面信息
			if (entInfo.containsKey("BASIC") && entInfo.getString("BASIC") != null && !"".equals(entInfo.getString("BASIC"))) {
				QyhyInfoBasic qyhyInfoBasic =new QyhyInfoBasic();
				QyhyStoreBasic qyhyStoreBasic =new QyhyStoreBasic();//(3).企业信息库 BASIC 照面信息
				JSONObject qyhyInfoBasicObj = JSONObject.fromObject(entInfo.getString("BASIC"));
				int addControl=0;
				//许可经营项目
				if (qyhyInfoBasicObj.containsKey("ABUITEM") && qyhyInfoBasicObj.getString("ABUITEM") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ABUITEM"))) {
					qyhyInfoBasic.setAbuitem(qyhyInfoBasicObj.getString("ABUITEM"));
					qyhyStoreBasic.setAbuitem(qyhyInfoBasicObj.getString("ABUITEM"));
					addControl++;
				}
				//国民经济行业代码
				if (qyhyInfoBasicObj.containsKey("INDUSTRYCOCODE") && qyhyInfoBasicObj.getString("INDUSTRYCOCODE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("INDUSTRYCOCODE"))) {
					qyhyInfoBasic.setIndustrycocode(qyhyInfoBasicObj.getString("INDUSTRYCOCODE"));
					qyhyStoreBasic.setIndustrycocode(qyhyInfoBasicObj.getString("INDUSTRYCOCODE"));
					addControl++;
				}
				//国民经济代码名称
				if (qyhyInfoBasicObj.containsKey("INDUSTRYCONAME") && qyhyInfoBasicObj.getString("INDUSTRYCONAME") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("INDUSTRYCONAME"))) {
					qyhyInfoBasic.setIndustryconame(qyhyInfoBasicObj.getString("INDUSTRYCONAME"));
					qyhyStoreBasic.setIndustryconame(qyhyInfoBasicObj.getString("INDUSTRYCONAME"));
					addControl++;
				}
				//企业英文名称
				if (qyhyInfoBasicObj.containsKey("ENTNAMEENG") && qyhyInfoBasicObj.getString("ENTNAMEENG") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ENTNAMEENG"))) {
					qyhyInfoBasic.setEntnameeng(qyhyInfoBasicObj.getString("ENTNAMEENG"));
					qyhyStoreBasic.setEntnameeng(qyhyInfoBasicObj.getString("ENTNAMEENG"));
					addControl++;
				}
				//原注册号
				if (qyhyInfoBasicObj.containsKey("ORIREGNO") && qyhyInfoBasicObj.getString("ORIREGNO") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ORIREGNO"))) {
					qyhyInfoBasic.setOriregno(qyhyInfoBasicObj.getString("ORIREGNO"));
					qyhyStoreBasic.setOriregno(qyhyInfoBasicObj.getString("ORIREGNO"));
					addControl++;
				}
				//组织机构代码
				if (qyhyInfoBasicObj.containsKey("ORGCODES") && qyhyInfoBasicObj.getString("ORGCODES") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ORGCODES"))) {
					qyhyInfoBasic.setOrgcodes(qyhyInfoBasicObj.getString("ORGCODES"));
					qyhyStoreBasic.setOrgcodes(qyhyInfoBasicObj.getString("ORGCODES"));
					addControl++;
				}
				//注册号
				if (qyhyInfoBasicObj.containsKey("REGNO") && qyhyInfoBasicObj.getString("REGNO") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REGNO"))) {
					qyhyInfoBasic.setRegno(qyhyInfoBasicObj.getString("REGNO"));
					qyhyStoreBasic.setRegno(qyhyInfoBasicObj.getString("REGNO"));
					addControl++;
				}
				//实收资本(万元)
				if (qyhyInfoBasicObj.containsKey("RECCAP") && qyhyInfoBasicObj.getString("RECCAP") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("RECCAP"))) {
					qyhyInfoBasic.setReccap(qyhyInfoBasicObj.getString("RECCAP"));
					qyhyStoreBasic.setReccap(qyhyInfoBasicObj.getString("RECCAP"));
					addControl++;
				}
				//统一信用代码
				if (qyhyInfoBasicObj.containsKey("CREDITCODE") && qyhyInfoBasicObj.getString("CREDITCODE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("CREDITCODE"))) {
					qyhyInfoBasic.setCreditcode(qyhyInfoBasicObj.getString("CREDITCODE"));
					qyhyStoreBasic.setCreditcode(qyhyInfoBasicObj.getString("CREDITCODE"));
					addControl++;
				}
				//经营期限至
				if (qyhyInfoBasicObj.containsKey("OPTO") && qyhyInfoBasicObj.getString("OPTO") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("OPTO"))) {
					qyhyInfoBasic.setOpto(qyhyInfoBasicObj.getString("OPTO"));
					qyhyStoreBasic.setOpto(qyhyInfoBasicObj.getString("OPTO"));
					addControl++;
				}
				//经营期限自
				if (qyhyInfoBasicObj.containsKey("OPFROM") && qyhyInfoBasicObj.getString("OPFROM") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("OPFROM"))) {
					qyhyInfoBasic.setOpfrom(qyhyInfoBasicObj.getString("OPFROM"));
					qyhyStoreBasic.setOpfrom(qyhyInfoBasicObj.getString("OPFROM"));
					addControl++;
				}
				//企业名称
				if (qyhyInfoBasicObj.containsKey("ENTNAME") && qyhyInfoBasicObj.getString("ENTNAME") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ENTNAME"))) {
					qyhyInfoBasic.setEntname(qyhyInfoBasicObj.getString("ENTNAME"));
					qyhyStoreBasic.setEntname(qyhyInfoBasicObj.getString("ENTNAME"));
					addControl++;
				}
				//企业ID
				if (qyhyInfoBasicObj.containsKey("ID") && qyhyInfoBasicObj.getString("ID") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ID"))) {
					qyhyInfoBasic.setId(qyhyInfoBasicObj.getString("ID"));
					qyhyStoreBasic.setId(qyhyInfoBasicObj.getString("ID"));
					addControl++;
				}
				//注册资本（万元）
				if (qyhyInfoBasicObj.containsKey("REGCAP") && qyhyInfoBasicObj.getString("REGCAP") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REGCAP"))) {
					qyhyInfoBasic.setRegcap(qyhyInfoBasicObj.getString("REGCAP"));
					qyhyStoreBasic.setRegcap(qyhyInfoBasicObj.getString("REGCAP"));
					addControl++;
				}
				//成立日期
				if (qyhyInfoBasicObj.containsKey("ESDATE") && qyhyInfoBasicObj.getString("ESDATE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ESDATE"))) {
					qyhyInfoBasic.setEsdate(qyhyInfoBasicObj.getString("ESDATE"));
					qyhyStoreBasic.setEsdate(qyhyInfoBasicObj.getString("ESDATE"));
					addControl++;
				}
				//法定代表人/负责人/执行事务合伙人
				if (qyhyInfoBasicObj.containsKey("FRNAME") && qyhyInfoBasicObj.getString("FRNAME") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("FRNAME"))) {
					qyhyInfoBasic.setFrname(qyhyInfoBasicObj.getString("FRNAME"));
					qyhyStoreBasic.setFrname(qyhyInfoBasicObj.getString("FRNAME"));
					addControl++;
				}
				//登记机关
				if (qyhyInfoBasicObj.containsKey("REGORG") && qyhyInfoBasicObj.getString("REGORG") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REGORG"))) {
					qyhyInfoBasic.setRegorg(qyhyInfoBasicObj.getString("REGORG"));
					qyhyStoreBasic.setRegorg(qyhyInfoBasicObj.getString("REGORG"));
					addControl++;
				}
				//经营状态
				if (qyhyInfoBasicObj.containsKey("ENTSTATUS") && qyhyInfoBasicObj.getString("ENTSTATUS") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ENTSTATUS"))) {
					qyhyInfoBasic.setEntstatus(qyhyInfoBasicObj.getString("ENTSTATUS"));
					qyhyStoreBasic.setEntstatus(qyhyInfoBasicObj.getString("ENTSTATUS"));
					addControl++;
				}
				//企业类型
				if (qyhyInfoBasicObj.containsKey("ENTTYPE") && qyhyInfoBasicObj.getString("ENTTYPE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ENTTYPE"))) {
					qyhyInfoBasic.setEnttype(qyhyInfoBasicObj.getString("ENTTYPE"));
					qyhyStoreBasic.setEnttype(qyhyInfoBasicObj.getString("ENTTYPE"));
					addControl++;
				}
				//所在省份
				if (qyhyInfoBasicObj.containsKey("REGORGPROVINCE") && qyhyInfoBasicObj.getString("REGORGPROVINCE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REGORGPROVINCE"))) {
					qyhyInfoBasic.setRegorgprovince(qyhyInfoBasicObj.getString("REGORGPROVINCE"));
					qyhyStoreBasic.setRegorgprovince(qyhyInfoBasicObj.getString("REGORGPROVINCE"));
					addControl++;
				}
				//所在城市
				if (qyhyInfoBasicObj.containsKey("REGORGCITY") && qyhyInfoBasicObj.getString("REGORGCITY") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REGORGCITY"))) {
					qyhyInfoBasic.setRegorgcity(qyhyInfoBasicObj.getString("REGORGCITY"));
					qyhyStoreBasic.setRegorgcity(qyhyInfoBasicObj.getString("REGORGCITY"));
					addControl++;
				}
				//所在区/县
				if (qyhyInfoBasicObj.containsKey("REGORGDISTRICT") && qyhyInfoBasicObj.getString("REGORGDISTRICT") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REGORGDISTRICT"))) {
					qyhyInfoBasic.setRegorgdistrict(qyhyInfoBasicObj.getString("REGORGDISTRICT"));
					qyhyStoreBasic.setRegorgdistrict(qyhyInfoBasicObj.getString("REGORGDISTRICT"));
					addControl++;
				}
				//注册资本币种
				if (qyhyInfoBasicObj.containsKey("REGCAPCUR") && qyhyInfoBasicObj.getString("REGCAPCUR") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REGCAPCUR"))) {
					qyhyInfoBasic.setRegcapcur(qyhyInfoBasicObj.getString("REGCAPCUR"));
					qyhyStoreBasic.setRegcapcur(qyhyInfoBasicObj.getString("REGCAPCUR"));
					addControl++;
				}
				//最后年检年度
				if (qyhyInfoBasicObj.containsKey("ANCHEYEAR") && qyhyInfoBasicObj.getString("ANCHEYEAR") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ANCHEYEAR"))) {
					qyhyInfoBasic.setAncheyear(qyhyInfoBasicObj.getString("ANCHEYEAR"));
					qyhyStoreBasic.setAncheyear(qyhyInfoBasicObj.getString("ANCHEYEAR"));
					addControl++;
				}
				//邮箱
				if (qyhyInfoBasicObj.containsKey("EMAIL") && qyhyInfoBasicObj.getString("EMAIL") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("EMAIL"))) {
					qyhyInfoBasic.setEmail(qyhyInfoBasicObj.getString("EMAIL"));
					qyhyStoreBasic.setEmail(qyhyInfoBasicObj.getString("EMAIL"));
					addControl++;
				}
				//住址
				if (qyhyInfoBasicObj.containsKey("DOM") && qyhyInfoBasicObj.getString("DOM") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("DOM"))) {
					qyhyInfoBasic.setDom(qyhyInfoBasicObj.getString("DOM"));
					qyhyStoreBasic.setDom(qyhyInfoBasicObj.getString("DOM"));
					addControl++;
				}
				//联系人电话
				if (qyhyInfoBasicObj.containsKey("TEL") && qyhyInfoBasicObj.getString("TEL") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("TEL"))) {
					qyhyInfoBasic.setTel(qyhyInfoBasicObj.getString("TEL"));
					qyhyStoreBasic.setTel(qyhyInfoBasicObj.getString("TEL"));
					addControl++;
				}
				//员工人数
				if (qyhyInfoBasicObj.containsKey("EMPNUM") && qyhyInfoBasicObj.getString("EMPNUM") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("EMPNUM"))) {
					qyhyInfoBasic.setEmpnum(qyhyInfoBasicObj.getString("EMPNUM"));
					qyhyStoreBasic.setEmpnum(qyhyInfoBasicObj.getString("EMPNUM"));
					addControl++;
				}
				//吊销日期
				if (qyhyInfoBasicObj.containsKey("REVDATE") && qyhyInfoBasicObj.getString("REVDATE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REVDATE"))) {
					qyhyInfoBasic.setRevdate(qyhyInfoBasicObj.getString("REVDATE"));
					qyhyStoreBasic.setRevdate(qyhyInfoBasicObj.getString("REVDATE"));
					addControl++;
				}
				//注销日期
				if (qyhyInfoBasicObj.containsKey("CANDATE") && qyhyInfoBasicObj.getString("CANDATE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("CANDATE"))) {
					qyhyInfoBasic.setCandate(qyhyInfoBasicObj.getString("CANDATE"));
					qyhyStoreBasic.setCandate(qyhyInfoBasicObj.getString("CANDATE"));
					addControl++;
				}
				//住所所在行政区划
				if (qyhyInfoBasicObj.containsKey("DOMDISTRICT") && qyhyInfoBasicObj.getString("DOMDISTRICT") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("DOMDISTRICT"))) {
					qyhyInfoBasic.setDomdistrict(qyhyInfoBasicObj.getString("DOMDISTRICT"));
					qyhyStoreBasic.setDomdistrict(qyhyInfoBasicObj.getString("DOMDISTRICT"));
					addControl++;
				}
				//注册地址行政编号
				if (qyhyInfoBasicObj.containsKey("REGORGCODE") && qyhyInfoBasicObj.getString("REGORGCODE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("REGORGCODE"))) {
					qyhyInfoBasic.setRegorgcode(qyhyInfoBasicObj.getString("REGORGCODE"));
					qyhyStoreBasic.setRegorgcode(qyhyInfoBasicObj.getString("REGORGCODE"));
					addControl++;
				}
				//中数处理过后的经营业务范围
				if (qyhyInfoBasicObj.containsKey("ZSOPSCOPE") && qyhyInfoBasicObj.getString("ZSOPSCOPE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ZSOPSCOPE"))) {
					qyhyInfoBasic.setZsopscope(qyhyInfoBasicObj.getString("ZSOPSCOPE"));
					qyhyStoreBasic.setZsopscope(qyhyInfoBasicObj.getString("ZSOPSCOPE"));
					addControl++;
				}
				//曾用名
				if (qyhyInfoBasicObj.containsKey("ENTNAME_OLD") && qyhyInfoBasicObj.getString("ENTNAME_OLD") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ENTNAME_OLD"))) {
					qyhyInfoBasic.setEntnameOld(qyhyInfoBasicObj.getString("ENTNAME_OLD"));
					qyhyStoreBasic.setEntnameOld(qyhyInfoBasicObj.getString("ENTNAME_OLD"));
					addControl++;
				}
				//核准日期
				if (qyhyInfoBasicObj.containsKey("APPRDATE") && qyhyInfoBasicObj.getString("APPRDATE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("APPRDATE"))) {
					qyhyInfoBasic.setApprdate(qyhyInfoBasicObj.getString("APPRDATE"));
					qyhyStoreBasic.setApprdate(qyhyInfoBasicObj.getString("APPRDATE"));
					addControl++;
				}
				//企业(机构)类型编码
				if (qyhyInfoBasicObj.containsKey("ENTTYPECODE") && qyhyInfoBasicObj.getString("ENTTYPECODE") != null 
						&& !"".equals(qyhyInfoBasicObj.getString("ENTTYPECODE"))) {
					qyhyInfoBasic.setEnttypecode(qyhyInfoBasicObj.getString("ENTTYPECODE"));
					qyhyStoreBasic.setEnttypecode(qyhyInfoBasicObj.getString("ENTTYPECODE"));
					addControl++;
				}
				if(addControl>0){
					qyhyInfoBasic.setAppId(appId);
					qyhyInfo.setQyhyInfoBasic(qyhyInfoBasic);
					
					qyhyStoreBasic.setRelateUuid(relateUuid);
					qyhyStore.setQyhyStoreBasic(qyhyStoreBasic);
					insertStoreFlag=true;
				}
			}
			//===========4.PERSON 主要管理人员
			if (entInfo.containsKey("PERSON") && entInfo.getString("PERSON") != null && !"".equals(entInfo.getString("PERSON"))) {
				
				JSONArray qyhyInfoPersonArray = JSONArray.fromObject(entInfo.get("PERSON"));
				List<QyhyInfoPerson> qyhyInfoPersonList =new ArrayList<QyhyInfoPerson>();
				List<QyhyStorePerson> qyhyStorePersonList =new ArrayList<QyhyStorePerson>();//(4).企业信息库 PERSON 主要管理人员
				for (int i = 0; i < qyhyInfoPersonArray.size(); i++) {
					if(qyhyInfoPersonArray.get(i)!=null){
						QyhyInfoPerson qyhyInfoPerson=new QyhyInfoPerson();
						QyhyStorePerson qyhyStorePerson=new QyhyStorePerson();
						JSONObject qyhyInfoPersonObj = (JSONObject) qyhyInfoPersonArray.get(i);
						int addControl=0;
						//企业名称
						if (qyhyInfoPersonObj.containsKey("ENTNAME") && qyhyInfoPersonObj.getString("ENTNAME") != null 
								&& !"".equals(qyhyInfoPersonObj.getString("ENTNAME"))) {
							qyhyInfoPerson.setEntname(qyhyInfoPersonObj.getString("ENTNAME"));
							qyhyStorePerson.setEntname(qyhyInfoPersonObj.getString("ENTNAME"));
							addControl++;
						}
						//人员姓名
						if (qyhyInfoPersonObj.containsKey("PERNAME") && qyhyInfoPersonObj.getString("PERNAME") != null 
								&& !"".equals(qyhyInfoPersonObj.getString("PERNAME"))) {
							qyhyInfoPerson.setPername(qyhyInfoPersonObj.getString("PERNAME"));
							qyhyStorePerson.setPername(qyhyInfoPersonObj.getString("PERNAME"));
							addControl++;
						}
						//职位
						if (qyhyInfoPersonObj.containsKey("POSITION") && qyhyInfoPersonObj.getString("POSITION") != null 
								&& !"".equals(qyhyInfoPersonObj.getString("POSITION"))) {
							qyhyInfoPerson.setPosition(qyhyInfoPersonObj.getString("POSITION"));
							qyhyStorePerson.setPosition(qyhyInfoPersonObj.getString("POSITION"));
							addControl++;
						}
						//人员总数量
						if (qyhyInfoPersonObj.containsKey("PERSONAMOUNT") && qyhyInfoPersonObj.getString("PERSONAMOUNT") != null 
								&& !"".equals(qyhyInfoPersonObj.getString("PERSONAMOUNT"))) {
							qyhyInfoPerson.setPersonamount(qyhyInfoPersonObj.getString("PERSONAMOUNT"));
							qyhyStorePerson.setPersonamount(qyhyInfoPersonObj.getString("PERSONAMOUNT"));
							addControl++;
						}
						if(addControl>0){
							qyhyInfoPerson.setAppId(appId);
							qyhyInfoPersonList.add(qyhyInfoPerson);	
							
							qyhyStorePerson.setRelateUuid(relateUuid);
							qyhyStorePersonList.add(qyhyStorePerson);
						}
					}
				}
				if(qyhyInfoPersonList!=null&&qyhyInfoPersonList.size()>0){
					qyhyInfo.setQyhyInfoPersonList(qyhyInfoPersonList);
					qyhyStore.setQyhyStorePersonList(qyhyStorePersonList);
					insertStoreFlag=true;
				}
			}
			//===========5.PUNISHBREAK 失信被执行人信息		
	        if (entInfo.containsKey("PUNISHBREAK") && entInfo.getString("PUNISHBREAK") != null && !"".equals(entInfo.getString("PUNISHBREAK"))) {
				
				JSONArray qyhyInfoPunishBreakArray = JSONArray.fromObject(entInfo.get("PUNISHBREAK"));
				List<QyhyInfoPunishBreak> qyhyInfoPunishBreakList =new ArrayList<QyhyInfoPunishBreak>();
				List<QyhyStorePunishBreak> qyhyStorePunishBreakList =new ArrayList<QyhyStorePunishBreak>();//(5).企业信息库  PUNISHBREAK 失信被执行人信息		
				for (int i = 0; i < qyhyInfoPunishBreakArray.size(); i++) {
					if(qyhyInfoPunishBreakArray.get(i)!=null){
						QyhyInfoPunishBreak qyhyInfoPunishBreak=new QyhyInfoPunishBreak();
						QyhyStorePunishBreak qyhyStorePunishBreak=new QyhyStorePunishBreak();
						JSONObject qyhyInfoPunishBreakObj = (JSONObject) qyhyInfoPunishBreakArray.get(i);
						int addControl=0;
						//发布时间
						if (qyhyInfoPunishBreakObj.containsKey("PUBLISHDATECLEAN") && qyhyInfoPunishBreakObj.getString("PUBLISHDATECLEAN") != null 
								&& !"".equals(qyhyInfoPunishBreakObj.getString("PUBLISHDATECLEAN"))) {
							qyhyInfoPunishBreak.setPublishdateclean(qyhyInfoPunishBreakObj.getString("PUBLISHDATECLEAN"));
							qyhyStorePunishBreak.setPublishdateclean(qyhyInfoPunishBreakObj.getString("PUBLISHDATECLEAN"));
							addControl++;
						}
						//执行法院
						if (qyhyInfoPunishBreakObj.containsKey("COURTNAME") && qyhyInfoPunishBreakObj.getString("COURTNAME") != null 
								&& !"".equals(qyhyInfoPunishBreakObj.getString("COURTNAME"))) {
							qyhyInfoPunishBreak.setCourtname(qyhyInfoPunishBreakObj.getString("COURTNAME"));
							qyhyStorePunishBreak.setCourtname(qyhyInfoPunishBreakObj.getString("COURTNAME"));
							addControl++;
						}
						//生效法律文书确定的义务
						if (qyhyInfoPunishBreakObj.containsKey("DUTY") && qyhyInfoPunishBreakObj.getString("DUTY") != null 
								&& !"".equals(qyhyInfoPunishBreakObj.getString("DUTY"))) {
							qyhyInfoPunishBreak.setDuty(qyhyInfoPunishBreakObj.getString("DUTY"));
							qyhyStorePunishBreak.setDuty(qyhyInfoPunishBreakObj.getString("DUTY"));
							addControl++;
						}
						//被执行人履行情况
						if (qyhyInfoPunishBreakObj.containsKey("PERFORMANCE") && qyhyInfoPunishBreakObj.getString("PERFORMANCE") != null 
								&& !"".equals(qyhyInfoPunishBreakObj.getString("PERFORMANCE"))) {
							qyhyInfoPunishBreak.setPerformance(qyhyInfoPunishBreakObj.getString("PERFORMANCE"));
							qyhyStorePunishBreak.setPerformance(qyhyInfoPunishBreakObj.getString("PERFORMANCE"));
							addControl++;
						}
						//失信被执行人行为具体情形
						if (qyhyInfoPunishBreakObj.containsKey("DISRUPTTYPENAME") && qyhyInfoPunishBreakObj.getString("DISRUPTTYPENAME") != null 
								&& !"".equals(qyhyInfoPunishBreakObj.getString("DISRUPTTYPENAME"))) {
							qyhyInfoPunishBreak.setDisrupttypename(qyhyInfoPunishBreakObj.getString("DISRUPTTYPENAME"));
							qyhyStorePunishBreak.setDisrupttypename(qyhyInfoPunishBreakObj.getString("DISRUPTTYPENAME"));
							addControl++;
						}
						//案件状态
						if (qyhyInfoPunishBreakObj.containsKey("CASESTATE") && qyhyInfoPunishBreakObj.getString("CASESTATE") != null 
								&& !"".equals(qyhyInfoPunishBreakObj.getString("CASESTATE"))) {
							qyhyInfoPunishBreak.setCasestate(qyhyInfoPunishBreakObj.getString("CASESTATE"));
							qyhyStorePunishBreak.setCasestate(qyhyInfoPunishBreakObj.getString("CASESTATE"));
							addControl++;
						}
						if(addControl>0){
							qyhyInfoPunishBreak.setAppId(appId);
							qyhyInfoPunishBreakList.add(qyhyInfoPunishBreak);	
							
							qyhyStorePunishBreak.setRelateUuid(relateUuid);
							qyhyStorePunishBreakList.add(qyhyStorePunishBreak);
						}
					}
				}
				if(qyhyInfoPunishBreakList!=null&&qyhyInfoPunishBreakList.size()>0){
					qyhyInfo.setQyhyInfoPunishBreakList(qyhyInfoPunishBreakList);
					qyhyStore.setQyhyStorePunishBreakList(qyhyStorePunishBreakList);
					insertStoreFlag=true;
				}
			}
	      //===========6.ORGBASIC 组织机构列表	
          if (entInfo.containsKey("ORGBASIC") && entInfo.getString("ORGBASIC") != null && !"".equals(entInfo.getString("ORGBASIC"))) {
				
				JSONArray qyhyInfoOrgBasicArray = JSONArray.fromObject(entInfo.get("ORGBASIC"));
				 List<QyhyInfoOrgBasic> qyhyInfoOrgBasicList =new ArrayList<QyhyInfoOrgBasic>();
				 List<QyhyStoreOrgBasic> qyhyStoreOrgBasicList =new ArrayList<QyhyStoreOrgBasic>();//(6).企业信息库  ORGBASIC 组织机构列表	
				for (int i = 0; i < qyhyInfoOrgBasicArray.size(); i++) {
					if(qyhyInfoOrgBasicArray.get(i)!=null){
						QyhyInfoOrgBasic qyhyInfoOrgBasic=new QyhyInfoOrgBasic();
						QyhyStoreOrgBasic qyhyStoreOrgBasic=new QyhyStoreOrgBasic();
						JSONObject qyhyInfoOrgBasicObj = (JSONObject) qyhyInfoOrgBasicArray.get(i);
						int addControl=0;
						//组织机构代码
						if (qyhyInfoOrgBasicObj.containsKey("JGDM") && qyhyInfoOrgBasicObj.getString("JGDM") != null 
								&& !"".equals(qyhyInfoOrgBasicObj.getString("JGDM"))) {
							qyhyInfoOrgBasic.setJgdm(qyhyInfoOrgBasicObj.getString("JGDM"));
							qyhyStoreOrgBasic.setJgdm(qyhyInfoOrgBasicObj.getString("JGDM"));
							addControl++;
						}
						//组织机构名称
						if (qyhyInfoOrgBasicObj.containsKey("JGMC") && qyhyInfoOrgBasicObj.getString("JGMC") != null 
								&& !"".equals(qyhyInfoOrgBasicObj.getString("JGMC"))) {
							qyhyInfoOrgBasic.setJgmc(qyhyInfoOrgBasicObj.getString("JGMC"));
							qyhyStoreOrgBasic.setJgmc(qyhyInfoOrgBasicObj.getString("JGMC"));
							addControl++;
						}
						//机构地址
						if (qyhyInfoOrgBasicObj.containsKey("JGDZ") && qyhyInfoOrgBasicObj.getString("JGDZ") != null 
								&& !"".equals(qyhyInfoOrgBasicObj.getString("JGDZ"))) {
							qyhyInfoOrgBasic.setJgdz(qyhyInfoOrgBasicObj.getString("JGDZ"));
							qyhyStoreOrgBasic.setJgdz(qyhyInfoOrgBasicObj.getString("JGDZ"));
							addControl++;
						}
						//质疑标志
						if (qyhyInfoOrgBasicObj.containsKey("ZYBZ") && qyhyInfoOrgBasicObj.getString("ZYBZ") != null 
								&& !"".equals(qyhyInfoOrgBasicObj.getString("ZYBZ"))) {
							qyhyInfoOrgBasic.setZybz(qyhyInfoOrgBasicObj.getString("ZYBZ"));
							qyhyStoreOrgBasic.setZybz(qyhyInfoOrgBasicObj.getString("ZYBZ"));
							addControl++;
						}
						if(addControl>0){
							qyhyInfoOrgBasic.setAppId(appId);
							qyhyInfoOrgBasicList.add(qyhyInfoOrgBasic);
							
							qyhyStoreOrgBasic.setRelateUuid(relateUuid);
							qyhyStoreOrgBasicList.add(qyhyStoreOrgBasic);
						}
					}
				}
				if(qyhyInfoOrgBasicList!=null&&qyhyInfoOrgBasicList.size()>0){
					qyhyInfo.setQyhyInfoOrgBasicList(qyhyInfoOrgBasicList);
					qyhyStore.setQyhyStoreOrgBasicList(qyhyStoreOrgBasicList);
					insertStoreFlag=true;
				}
			}
            //===========7.ORGDETAIL 组织机构详情
          if (entInfo.containsKey("ORGDETAIL") && entInfo.getString("ORGDETAIL") != null && !"".equals(entInfo.getString("ORGDETAIL"))) {
        	  
        	    QyhyInfoOrgDetail qyhyInfoOrgDetail =new QyhyInfoOrgDetail();
        	    QyhyStoreOrgDetail qyhyStoreOrgDetail =new QyhyStoreOrgDetail();//(7).企业信息库  ORGDETAIL 组织机构详情
				JSONObject qyhyInfoOrgDetailObj = JSONObject.fromObject(entInfo.getString("ORGDETAIL"));
				int addControl=0;
				//组织机构代码
				if (qyhyInfoOrgDetailObj.containsKey("JGDM") && qyhyInfoOrgDetailObj.getString("JGDM") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JGDM"))) {
					qyhyInfoOrgDetail.setJgdm(qyhyInfoOrgDetailObj.getString("JGDM"));
					qyhyStoreOrgDetail.setJgdm(qyhyInfoOrgDetailObj.getString("JGDM"));
					addControl++;
				}
				//组织机构名称
				if (qyhyInfoOrgDetailObj.containsKey("JGMC") && qyhyInfoOrgDetailObj.getString("JGMC") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JGMC"))) {
					qyhyInfoOrgDetail.setJgmc(qyhyInfoOrgDetailObj.getString("JGMC"));
					qyhyStoreOrgDetail.setJgmc(qyhyInfoOrgDetailObj.getString("JGMC"));
					addControl++;
				}
				//统一社会信用代码
				if (qyhyInfoOrgDetailObj.containsKey("CREDITCODE") && qyhyInfoOrgDetailObj.getString("CREDITCODE") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("CREDITCODE"))) {
					qyhyInfoOrgDetail.setCreditcode(qyhyInfoOrgDetailObj.getString("CREDITCODE"));
					qyhyStoreOrgDetail.setCreditcode(qyhyInfoOrgDetailObj.getString("CREDITCODE"));
					addControl++;
				}
				//注册号
				if (qyhyInfoOrgDetailObj.containsKey("REGNO") && qyhyInfoOrgDetailObj.getString("REGNO") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("REGNO"))) {
					qyhyInfoOrgDetail.setRegno(qyhyInfoOrgDetailObj.getString("REGNO"));
					qyhyStoreOrgDetail.setRegno(qyhyInfoOrgDetailObj.getString("REGNO"));
					addControl++;
				}
				//法人代表姓名
				if (qyhyInfoOrgDetailObj.containsKey("FDDBR") && qyhyInfoOrgDetailObj.getString("FDDBR") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("FDDBR"))) {
					qyhyInfoOrgDetail.setFddbr(qyhyInfoOrgDetailObj.getString("FDDBR"));
					qyhyStoreOrgDetail.setFddbr(qyhyInfoOrgDetailObj.getString("FDDBR"));
					addControl++;
				}
				//办证机构
				if (qyhyInfoOrgDetailObj.containsKey("BZJG") && qyhyInfoOrgDetailObj.getString("BZJG") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("BZJG"))) {
					qyhyInfoOrgDetail.setBzjg(qyhyInfoOrgDetailObj.getString("BZJG"));
					qyhyStoreOrgDetail.setBzjg(qyhyInfoOrgDetailObj.getString("BZJG"));
					addControl++;
				}
				//注册日期
				if (qyhyInfoOrgDetailObj.containsKey("ZCRQ") && qyhyInfoOrgDetailObj.getString("ZCRQ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("ZCRQ"))) {
					qyhyInfoOrgDetail.setZcrq(qyhyInfoOrgDetailObj.getString("ZCRQ"));
					qyhyStoreOrgDetail.setZcrq(qyhyInfoOrgDetailObj.getString("ZCRQ"));
					addControl++;
				}
				//行政区划
				if (qyhyInfoOrgDetailObj.containsKey("XZQH") && qyhyInfoOrgDetailObj.getString("XZQH") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("XZQH"))) {
					qyhyInfoOrgDetail.setXzqh(qyhyInfoOrgDetailObj.getString("XZQH"));
					qyhyStoreOrgDetail.setXzqh(qyhyInfoOrgDetailObj.getString("XZQH"));
					addControl++;
				}
				//机构类型
				if (qyhyInfoOrgDetailObj.containsKey("JGLX") && qyhyInfoOrgDetailObj.getString("JGLX") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JGLX"))) {
					qyhyInfoOrgDetail.setJglx(qyhyInfoOrgDetailObj.getString("JGLX"));
					qyhyStoreOrgDetail.setJglx(qyhyInfoOrgDetailObj.getString("JGLX"));
					addControl++;
				}
				//机构地址
				if (qyhyInfoOrgDetailObj.containsKey("JGDZ") && qyhyInfoOrgDetailObj.getString("JGDZ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JGDZ"))) {
					qyhyInfoOrgDetail.setJgdz(qyhyInfoOrgDetailObj.getString("JGDZ"));
					qyhyStoreOrgDetail.setJgdz(qyhyInfoOrgDetailObj.getString("JGDZ"));
					addControl++;
				}
				//代码证办证日期
				if (qyhyInfoOrgDetailObj.containsKey("BZRQ") && qyhyInfoOrgDetailObj.getString("BZRQ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("BZRQ"))) {
					qyhyInfoOrgDetail.setBzrq(qyhyInfoOrgDetailObj.getString("BZRQ"));
					qyhyStoreOrgDetail.setBzrq(qyhyInfoOrgDetailObj.getString("BZRQ"));
					addControl++;
				}
				//代码证作废日期
				if (qyhyInfoOrgDetailObj.containsKey("ZFRQ") && qyhyInfoOrgDetailObj.getString("ZFRQ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("ZFRQ"))) {
					qyhyInfoOrgDetail.setZfrq(qyhyInfoOrgDetailObj.getString("ZFRQ"));
					qyhyStoreOrgDetail.setZfrq(qyhyInfoOrgDetailObj.getString("ZFRQ"));
					addControl++;
				}
				//质疑标志
				if (qyhyInfoOrgDetailObj.containsKey("ZYBZ") && qyhyInfoOrgDetailObj.getString("ZYBZ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("ZYBZ"))) {
					qyhyInfoOrgDetail.setZybz(qyhyInfoOrgDetailObj.getString("ZYBZ"));
					qyhyStoreOrgDetail.setZybz(qyhyInfoOrgDetailObj.getString("ZYBZ"));
					addControl++;
				}
				//经营范围
				if (qyhyInfoOrgDetailObj.containsKey("JYFW") && qyhyInfoOrgDetailObj.getString("JYFW") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JYFW"))) {
					qyhyInfoOrgDetail.setJyfw(qyhyInfoOrgDetailObj.getString("JYFW"));
					qyhyStoreOrgDetail.setJyfw(qyhyInfoOrgDetailObj.getString("JYFW"));
					addControl++;
				}
				//经营状态
				if (qyhyInfoOrgDetailObj.containsKey("JYZT") && qyhyInfoOrgDetailObj.getString("JYZT") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JYZT"))) {
					qyhyInfoOrgDetail.setJyzt(qyhyInfoOrgDetailObj.getString("JYZT"));
					qyhyStoreOrgDetail.setJyzt(qyhyInfoOrgDetailObj.getString("JYZT"));
					addControl++;
				}
				//注册资本
				if (qyhyInfoOrgDetailObj.containsKey("ZCZJ") && qyhyInfoOrgDetailObj.getString("ZCZJ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("ZCZJ"))) {
					qyhyInfoOrgDetail.setZczj(qyhyInfoOrgDetailObj.getString("ZCZJ"));
					qyhyStoreOrgDetail.setZczj(qyhyInfoOrgDetailObj.getString("ZCZJ"));
					addControl++;
				}
				//校核标志
				if (qyhyInfoOrgDetailObj.containsKey("JHBZ") && qyhyInfoOrgDetailObj.getString("JHBZ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JHBZ"))) {
					qyhyInfoOrgDetail.setJhbz(qyhyInfoOrgDetailObj.getString("JHBZ"));
					qyhyStoreOrgDetail.setJhbz(qyhyInfoOrgDetailObj.getString("JHBZ"));
					addControl++;
				}
				//发照日期
				if (qyhyInfoOrgDetailObj.containsKey("BGRQ") && qyhyInfoOrgDetailObj.getString("BGRQ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("BGRQ"))) {
					qyhyInfoOrgDetail.setBgrq(qyhyInfoOrgDetailObj.getString("BGRQ"));
					qyhyStoreOrgDetail.setBgrq(qyhyInfoOrgDetailObj.getString("BGRQ"));
					addControl++;
				}
				//经济类型名称
				if (qyhyInfoOrgDetailObj.containsKey("JJLXDM") && qyhyInfoOrgDetailObj.getString("JJLXDM") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JJLXDM"))) {
					qyhyInfoOrgDetail.setJjlxdm(qyhyInfoOrgDetailObj.getString("JJLXDM"));
					qyhyStoreOrgDetail.setJjlxdm(qyhyInfoOrgDetailObj.getString("JJLXDM"));
					addControl++;
				}
				//生产经营地址
				if (qyhyInfoOrgDetailObj.containsKey("JYDZ") && qyhyInfoOrgDetailObj.getString("JYDZ") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JYDZ"))) {
					qyhyInfoOrgDetail.setJydz(qyhyInfoOrgDetailObj.getString("JYDZ"));
					qyhyStoreOrgDetail.setJydz(qyhyInfoOrgDetailObj.getString("JYDZ"));
					addControl++;
				}
				//经济行业名称
				if (qyhyInfoOrgDetailObj.containsKey("JJHYDM") && qyhyInfoOrgDetailObj.getString("JJHYDM") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("JJHYDM"))) {
					qyhyInfoOrgDetail.setJjhydm(qyhyInfoOrgDetailObj.getString("JJHYDM"));
					qyhyStoreOrgDetail.setJjhydm(qyhyInfoOrgDetailObj.getString("JJHYDM"));
					addControl++;
				}
				//上级主管部门
				if (qyhyInfoOrgDetailObj.containsKey("ZGMC") && qyhyInfoOrgDetailObj.getString("ZGMC") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("ZGMC"))) {
					qyhyInfoOrgDetail.setZgmc(qyhyInfoOrgDetailObj.getString("ZGMC"));
					qyhyStoreOrgDetail.setZgmc(qyhyInfoOrgDetailObj.getString("ZGMC"));
					addControl++;
				}
				//电话号码
				if (qyhyInfoOrgDetailObj.containsKey("DHHM") && qyhyInfoOrgDetailObj.getString("DHHM") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("DHHM"))) {
					qyhyInfoOrgDetail.setDhhm(qyhyInfoOrgDetailObj.getString("DHHM"));
					qyhyStoreOrgDetail.setDhhm(qyhyInfoOrgDetailObj.getString("DHHM"));
					addControl++;
				}
				//电子邮箱
				if (qyhyInfoOrgDetailObj.containsKey("EMAIL") && qyhyInfoOrgDetailObj.getString("EMAIL") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("EMAIL"))) {
					qyhyInfoOrgDetail.setEmail(qyhyInfoOrgDetailObj.getString("EMAIL"));
					qyhyStoreOrgDetail.setEmail(qyhyInfoOrgDetailObj.getString("EMAIL"));
					addControl++;
				}
				//职工人数
				if (qyhyInfoOrgDetailObj.containsKey("ZGRS") && qyhyInfoOrgDetailObj.getString("ZGRS") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("ZGRS"))) {
					qyhyInfoOrgDetail.setZgrs(qyhyInfoOrgDetailObj.getString("ZGRS"));
					qyhyStoreOrgDetail.setZgrs(qyhyInfoOrgDetailObj.getString("ZGRS"));
					addControl++;
				}
				//网址
				if (qyhyInfoOrgDetailObj.containsKey("URL") && qyhyInfoOrgDetailObj.getString("URL") != null 
						&& !"".equals(qyhyInfoOrgDetailObj.getString("URL"))) {
					qyhyInfoOrgDetail.setUrl(qyhyInfoOrgDetailObj.getString("URL"));
					qyhyStoreOrgDetail.setUrl(qyhyInfoOrgDetailObj.getString("URL"));
					addControl++;
				}
				if(addControl>0){
					qyhyInfoOrgDetail.setAppId(appId);
					qyhyInfo.setQyhyInfoOrgDetail(qyhyInfoOrgDetail);
					
					qyhyStoreOrgDetail.setRelateUuid(relateUuid);
					qyhyStore.setQyhyStoreOrgDetail(qyhyStoreOrgDetail);
					insertStoreFlag=true;
				}
          }
          //===========8.METADATA 元数据来源		
          if (entInfo.containsKey("METADATA") && entInfo.getString("METADATA") != null 
        		  && !"".equals(entInfo.getString("METADATA"))) {
          	    QyhyInfoMetaData qyhyInfoMetaData =new QyhyInfoMetaData();
          	    QyhyStoreMetaData qyhyStoreMetaData =new QyhyStoreMetaData();//(8).企业信息库 METADATA 元数据来源		
				JSONObject qyhyInfoMetaDataObj = JSONObject.fromObject(entInfo.getString("METADATA"));
				int addControl=0;
				if (qyhyInfoMetaDataObj.containsKey("SOURCE") && qyhyInfoMetaDataObj.getString("SOURCE") != null 
						&& !"".equals(qyhyInfoMetaDataObj.getString("SOURCE"))) {
					qyhyInfoMetaData.setSource(qyhyInfoMetaDataObj.getString("SOURCE"));
					qyhyStoreMetaData.setSource(qyhyInfoMetaDataObj.getString("SOURCE"));
					addControl++;
				}
				if(addControl>0){
					qyhyInfoMetaData.setAppId(appId);
					qyhyInfo.setQyhyInfoMetaData(qyhyInfoMetaData);
					
					qyhyStoreMetaData.setRelateUuid(relateUuid);
					qyhyStore.setQyhyStoreMetaData(qyhyStoreMetaData);
				}
          }
		}
		if(code==200&&insertStoreFlag){
			qyhyInfo.setQyhyStore(qyhyStore);
		}
		return qyhyInfo;
		}catch(Exception e){
			if (logger.isErrorEnabled()) {
				 logger.error("[客户端 & 企业行业信息] 数据解析异常   "+appId+" Exception:{}",e);
			 }
			//解析异常
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, qyhyTaskType, null,
					TaskStatusUtil.CURR_USER , null, "1");
			return null;
		}
	}
	
	
	
	
}
