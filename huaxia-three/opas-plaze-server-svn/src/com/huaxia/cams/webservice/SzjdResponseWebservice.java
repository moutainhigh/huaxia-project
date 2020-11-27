package com.huaxia.cams.webservice;

import javax.annotation.Resource;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.huaxia.cams.modules.szjd.domain.SzjdResponse;
import com.huaxia.cams.modules.szjd.service.SzjdResponseService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.service.TaskCallStatusService;

import net.sf.json.JSONObject;

@Service
@WebService(serviceName = "szjdResponseService", endpointInterface = "com.huaxia.cams.webservice.MessageService")
public class SzjdResponseWebservice implements MessageService {
	
	private final static Logger logger = LoggerFactory.getLogger(SzjdResponseWebservice.class);
	
	private final static AppConfig appConfig = AppConfig.getInstance();
	
	@Resource
	private TaskCallStatusService taskCallStatusService;
	
	@Resource
	private SzjdResponseService szjdResponseService;

	@Override
	public int onMessage(String message) throws Exception {
		
		/** 接收三方平台传过来的请求参数,分解成各变量 */
		String appId = "";
		String requestChannel = "";
		String factor1 = "";
		String factor2 = "";
		String factor3 = "";
		String factor4 = "";
		String factor5 = "";
		String factorNum = "";
		String idNum = "";
		String idType = "";
		String name = "";
		String originateOrgCode = "";
		String originateUserCode = "";
		String position = "";
		String productDate = "";
		String queryReason = "";
		String resultCode = "";
		String resultDesc = "";
		String resultType = "";
		String score = "";
		String scoreDate = "";
		String serviceCode = "";
		
		SzjdResponse szjd = new SzjdResponse();
		try {
			JSONObject jsonObject = JSONObject.fromObject(message);
			if (jsonObject != null) {
				if (jsonObject.containsKey("REQUEST") && jsonObject.getString("REQUEST") != null
						&& !"".equals(jsonObject.getString("REQUEST"))) {
					JSONObject jsonRequet = JSONObject.fromObject(jsonObject.getString("REQUEST"));
					if (jsonRequet.containsKey("HEAD") && jsonRequet.getString("HEAD") != null
							&& !"".equals(jsonRequet.getString("HEAD"))) {
						JSONObject jsonHead = JSONObject.fromObject(jsonRequet.getString("HEAD"));
						if (jsonHead.containsKey("REQUEST_CHANNEL") && jsonHead.getString("REQUEST_CHANNEL") != null
								&& !"".equals(jsonHead.getString("REQUEST_CHANNEL"))) {
							requestChannel = jsonHead.getString("REQUEST_CHANNEL");
							szjd.setCrtUser(requestChannel);
						}
						if (jsonHead.containsKey("TRN_ID") && jsonHead.getString("TRN_ID") != null
								&& !"".equals(jsonHead.getString("TRN_ID"))) {
							appId = jsonHead.getString("TRN_ID");
							szjd.setAppId(appId);
						}
					}
					
					if (jsonRequet.containsKey("BODY") && jsonRequet.getString("BODY") != null
							&& !"".equals(jsonRequet.getString("BODY"))) {
						JSONObject jsonBody = JSONObject.fromObject(jsonRequet.getString("BODY"));
						if (jsonBody.containsKey("factor1")) {
							factor1 = jsonBody.getString("factor1");
							szjd.setFactor1(factor1);
						}
						if (jsonBody.containsKey("factor2")) {
							factor2 = jsonBody.getString("factor2");
							szjd.setFactor2(factor2);
						}
						if (jsonBody.containsKey("factor3")) {
							factor3 = jsonBody.getString("factor3");
							szjd.setFactor3(factor3);
						}
						if (jsonBody.containsKey("factor4")) {
							factor4 = jsonBody.getString("factor4");
							szjd.setFactor4(factor4);
						}
						if (jsonBody.containsKey("factor5")) {
							factor5 = jsonBody.getString("factor5");
							szjd.setFactor5(factor5);
						}
						if (jsonBody.containsKey("factorNum")) {
							factorNum = jsonBody.getString("factorNum");
							szjd.setFactorNum(factorNum);
						}
						if (jsonBody.containsKey("idNum")) {
							idNum = jsonBody.getString("idNum");
							szjd.setIdNum(idNum);
						}
						if (jsonBody.containsKey("idType")) {
							idType = jsonBody.getString("idType");
							szjd.setIdType(idType);
						}
						if (jsonBody.containsKey("name")) {
							name = jsonBody.getString("name");
							szjd.setName(name);
						}
						if (jsonBody.containsKey("originateOrgCode")) {
							originateOrgCode = jsonBody.getString("originateOrgCode");
							szjd.setOriginateOrgCode(originateOrgCode);
						}
						if (jsonBody.containsKey("originateUserCode")) {
							originateUserCode = jsonBody.getString("originateUserCode");
							szjd.setOriginateUserCode(originateUserCode);
						}
						if (jsonBody.containsKey("position")) {
							position = jsonBody.getString("position");
							szjd.setPosition(position);
						}
						if (jsonBody.containsKey("productDate")) {
							productDate = jsonBody.getString("productDate");
							szjd.setProductDate(productDate);
						}
						if (jsonBody.containsKey("queryReason")) {
							queryReason = jsonBody.getString("queryReason");
							szjd.setQueryReason(queryReason);
						}
						if (jsonBody.containsKey("resultCode")) {
							resultCode = jsonBody.getString("resultCode");
							szjd.setResultCode(resultCode);
						}
						if (jsonBody.containsKey("resultDesc")) {
							resultDesc = jsonBody.getString("resultDesc");
							szjd.setResultDesc(resultDesc);
						}
						if (jsonBody.containsKey("resultType")) {
							resultType = jsonBody.getString("resultType");
							szjd.setResultType(resultType);
						}
						if (jsonBody.containsKey("score")) {
							score = jsonBody.getString("score");
							szjd.setScore(score);
						}
						if (jsonBody.containsKey("scoreDate")) {
							scoreDate = jsonBody.getString("scoreDate");
							szjd.setScoreDate(scoreDate);
						}
						if (jsonBody.containsKey("serviceCode")) {
							serviceCode = jsonBody.getString("serviceCode");
							szjd.setServiceCode(serviceCode);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("数字解读解析错误"+appId, e);
			taskCallStatusService.saveTaskStatusHistory(appId, appConfig.getValue("hz.task_type"));
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
					appConfig.getValue("szjd.task_type"), null, TaskStatusUtil.CURR_USER, null, "1");
			return 1;
		}
		
		try{
			szjdResponseService.saveSzjdResponse(szjd);
			taskCallStatusService.saveTaskStatusHistory(appId, appConfig.getValue("hz.task_type"));
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
					appConfig.getValue("szjd.task_type"), null, TaskStatusUtil.CURR_USER, null, null);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("数字解读入库错误"+appId, e);
			taskCallStatusService.saveTaskStatusHistory(appId, appConfig.getValue("hz.task_type"));
			taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,
					appConfig.getValue("szjd.task_type"), null, TaskStatusUtil.CURR_USER, null, "1");
			return 1;
		}
		
		return 0;
	}
	
}
