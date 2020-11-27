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

import com.huaxia.cams.modules.hz.domain.Data;
import com.huaxia.cams.modules.hz.domain.HzCollection;
import com.huaxia.cams.modules.hz.domain.data.EnterpriseInfo;
import com.huaxia.cams.modules.hz.domain.data.FyNaturalPerson;
import com.huaxia.cams.modules.hz.domain.data.GjjLoanInfo;
import com.huaxia.cams.modules.hz.domain.data.Gjjxx;
import com.huaxia.cams.modules.hz.domain.data.Grxx;
import com.huaxia.cams.modules.hz.domain.data.MarryInfo;
import com.huaxia.cams.modules.hz.domain.data.RsjZxAc01;
import com.huaxia.cams.modules.hz.domain.data.SbFeeinfogrid;
import com.huaxia.cams.modules.hz.domain.data.VehicleInfo;
import com.huaxia.cams.modules.hz.domain.data.VehiclePenaltyInfo;
import com.huaxia.cams.modules.hz.domain.data.WaterAffairsInfo;
import com.huaxia.cams.modules.hz.service.HzService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author dingguofeng
 * 
 * 杭州区域数据
 *
 */
public class WST001800TaskWorker implements Runnable {
	
	private final static Logger logger = LoggerFactory.getLogger(WST001800TaskWorker.class);
	
	private final static AppConfig appConfig = AppConfig.getInstance();
	
	private HzService hzService;
	private TaskCallStatusService taskCallStatusService;
	private final Integer         querySize;
	private final String          taskType;
	
	public WST001800TaskWorker() {
		this.hzService             = (HzService) SpringContextUtil.getBean("hzService");
		this.taskCallStatusService = (TaskCallStatusService) SpringContextUtil.getBean("taskCallStatusService");
		this.querySize             = Integer.valueOf(appConfig.getValue("hz.query_size"));
		this.taskType              = appConfig.getValue("hz.task_type");
	}

	@Override
	public void run() {
		
		/** 死循环,重复扫描任务 */
		while(true){
			
			try {
				
				/** 每次循环前,停顿2秒 */
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
				}
				
				/** 根据任务类型,查询条数,查询出需要发起杭州区域数据查询的任务 */
				List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType(taskType, querySize);
				
				if (taskStatusList != null && taskStatusList.size() > 0) {
					
					for (TaskCallStatus task : taskStatusList) {
						
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
						}
						
						String appId    = task.getAppId   ();   //申请件编号
						String name     = task.getName    ();   //姓名
						String certNo   = task.getCertNo  ();   //证件号
						String certType = task.getCertType();   //证件类型
						String mobile   = task.getMobile  ();   //手机号
						String trnId    = task.getAppId   ();   //appid映射成trnId
						
						/** 请求参数错误标识 */
						boolean paramErrorFlag = false;
						if(name == null || "".equals(name)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 杭州区域数据] 请求的姓名为空，流水号为：{}", appId);
							}
						}
						if(certNo == null || "".equals(certNo)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 杭州区域数据] 请求的证件号为空，流水号为：{}", appId);
							}
						}
						if(mobile == null || "".equals(mobile)){
							paramErrorFlag = true;
							if (logger.isDebugEnabled()) {
								logger.debug("[客户端 & 杭州区域数据] 请求的手机号为空，流水号为：{}", appId);
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
						
						/** 拼接请求参数 */
						Map<String, Object> head = new HashMap<String, Object>();
						head.put("REQUEST_CHANNEL", "CAMS");
						head.put("TRN_ID", trnId);
						head.put("TRN_CODE", appConfig.getValue("hz.trn.code"));
						
						Map<String, Object> body = new HashMap<String, Object>();
						body.put("QUERY_MODE", "3");
						body.put("CERT_TYPE", certType);
						body.put("CERT_NO", certNo);
						body.put("NAME", name);
						body.put("MOBILE", mobile);

						Map<String, Object> request = new HashMap<String, Object>();
						request.put("HEAD", head);
						request.put("BODY", body);

						Map<String, Object> params = new HashMap<String, Object>();
						params.put("REQUEST", request);

						JSONObject json = JSONObject.fromObject(params);
						String paramsStr = json.toString();
						
						Client client = null;
						Object[] result = null;
						
						try {
							URL url = new URL(appConfig.getValue("general.plaze.webservice.url"));
							HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
							httpConnection.setReadTimeout(
									Integer.parseInt(appConfig.getValue("hz.plaze.webservice.read_timeout")));// 设置http连接的读超时，单位是毫秒
							httpConnection.connect();
							client = new Client(httpConnection.getInputStream(), null);
							// 设置发送超时限制，单位是毫秒
							client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT,
									appConfig.getValue("hz.plaze.webservice.http_timeout"));
							client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
							client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");
							result = client.invoke("invoke", new Object[] { paramsStr });
						} catch (Exception e) {
							e.printStackTrace();
							if (logger.isErrorEnabled()) {
								logger.error("[客户端 & 杭州区域数据] 申请件[{}]调用第三方查询平台服务异常:{}", appId, e);
							}
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						} finally {
							if (client != null) {
								client.close();
							}
						}
						
						/** 开始解析报文 */
						String returnMessage = "";
						String responseCode = "";
						HzCollection hzCollection;
						try {
							JSONObject jsonResponse = JSONObject.fromObject(String.valueOf(result[0]));
							if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
									&& !"".equals(jsonResponse.getString("RESPONSE"))) {
								JSONObject jsonRes = JSONObject.fromObject(jsonResponse.getString("RESPONSE"));
								if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
										&& !"".equals(jsonRes.getString("BODY"))) {
									JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
									if (jsonBody.containsKey("RESPONSE_BODY")
											&& jsonBody.getString("RESPONSE_BODY") != null
											&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
										returnMessage = jsonBody.getString("RESPONSE_BODY");
									}
									if (jsonBody.containsKey("RESPONSE_CODE") && jsonBody.getString("RESPONSE_CODE") != null
											&& !"".equals(jsonBody.getString("RESPONSE_CODE"))) {
										responseCode = jsonBody.getString("RESPONSE_CODE");
									}
								}
							}
							
							/** 如果返回码是999999,说明是dmz请求数据源异常后拼接返回的报文 */
							if("999999".equals(responseCode)){
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							
							hzCollection = parseHz(returnMessage, appId, TaskStatusUtil.CURR_USER);
							
						}catch (Exception e1) {
							/** 报文解析异常 */
							e1.printStackTrace();
							logger.error(appId+"杭州区域数据报文解析异常", e1);
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						
						if(hzCollection.getCode() != null && ("0000".equals(hzCollection.getCode()) || "1009".equals(hzCollection.getCode()) || "1021".equals(hzCollection.getCode()))){
							try {
								hzService.saveHzCollection(hzCollection);
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS,
										taskType, null, TaskStatusUtil.CURR_USER, null, null);
							} catch (Exception e) {
								/** 入库异常 */
								e.printStackTrace();
								logger.error(appId+"杭州区域数据入库异常", e);
								taskCallStatusService.saveTaskStatusHistory(appId, taskType);
								taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SAVE_ERROE,
										taskType, null, TaskStatusUtil.CURR_USER, null, "1");
								continue;
							}
							
						}else{
							/** 如果不是0000,1009流程停止,状态显示响应码异常 */
							taskCallStatusService.saveTaskStatusHistory(appId, taskType);
							taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.RESPOSE_CODE_ERROR,
									taskType, null, TaskStatusUtil.CURR_USER, null, "1");
							continue;
						}
						
					}
					
				}else{
					if (logger.isDebugEnabled()) {
						logger.debug("[客户端 & 杭州区域数据] 未查询到杭州区域数据任务条数!");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("杭州区域数据线程异常,准备重启",e);
			}
			
		}

	}
	
	private HzCollection parseHz(String message,String appId,String crtUser){
		HzCollection hzCollection = new HzCollection();
		hzCollection.setAppId(appId);
		hzCollection.setCrtUser(crtUser);
		if(message != null && message != ""){
			JSONObject jsonMessage = JSONObject.fromObject(message);
			if(jsonMessage.containsKey("code")){
				hzCollection.setCode(jsonMessage.getString("code"));
			}
			if(jsonMessage.containsKey("msg")){
				hzCollection.setMsg(jsonMessage.getString("msg"));
			}
			if(jsonMessage.containsKey("orderNo")){
				hzCollection.setOrderNo(jsonMessage.getString("orderNo"));
			}
			if(jsonMessage.containsKey("data") && jsonMessage.getString("data") != null
					&& !"".equals(jsonMessage.getString("data"))){
				Data data = new Data();
				data.setAppId(appId);
				data.setCrtUser(crtUser);
				JSONObject jsonData = JSONObject.fromObject(jsonMessage.getString("data"));
				if(jsonData.containsKey("pid")){
					data.setPid(jsonData.getString("pid"));
				}
				if(jsonData.containsKey("datastatus")){
					data.setDataStatus(jsonData.getString("datastatus"));
				}
				if(jsonData.containsKey("name")){
					data.setName(jsonData.getString("name"));
				}
				if(jsonData.containsKey("api_channel_type") && jsonData.getString("api_channel_type") != null
						&& !"".equals(jsonData.getString("api_channel_type"))){
					JSONObject jsonApiChannelType = JSONObject.fromObject(jsonData.getString("api_channel_type"));
					if(jsonApiChannelType.containsKey("szf0001")){
						data.setApiChannelType(jsonApiChannelType.getString("szf0001"));
					}
				}
				
				/** data标签下的数组格式 */
				
				/** 个人信息 */
				if(jsonData.containsKey("grxx") && jsonData.getJSONArray("grxx") != null){
					JSONArray jsonArrayGrxx = jsonData.getJSONArray("grxx");
					if(jsonArrayGrxx.size() > 0){
						List<Grxx> grxxs = new ArrayList<Grxx>();
						for(int i = 0;i<jsonArrayGrxx.size();i++){
							JSONObject jsonGrxx = jsonArrayGrxx.getJSONObject(i);
							Grxx grxx = new Grxx();
							grxx.setAppId(appId);
							grxx.setCrtUser(crtUser);
							if(jsonGrxx.containsKey("xm")){
								grxx.setXm(jsonGrxx.getString("xm"));
							}
							if(jsonGrxx.containsKey("sfzh")){
								grxx.setSfzh(jsonGrxx.getString("sfzh"));
							}
							if(jsonGrxx.containsKey("xb")){
								grxx.setXb(jsonGrxx.getString("xb"));
							}
							if(jsonGrxx.containsKey("csrq")){
								grxx.setCsrq(jsonGrxx.getString("csrq"));
							}
							if(jsonGrxx.containsKey("mz")){
								grxx.setMz(jsonGrxx.getString("mz"));
							}
							if(jsonGrxx.containsKey("addr")){
								grxx.setAddr(jsonGrxx.getString("addr"));
							}
							if(jsonGrxx.containsKey("hksx")){
								grxx.setHksx(jsonGrxx.getString("hksx"));
							}
							if(jsonGrxx.containsKey("jxdm")){
								grxx.setJxdm(jsonGrxx.getString("jxdm"));
							}
							if(jsonGrxx.containsKey("zzdz")){
								grxx.setZzdz(jsonGrxx.getString("zzdz"));
							}
							if(jsonGrxx.containsKey("djrq")){
								grxx.setDjrq(jsonGrxx.getString("djrq"));
							}
							if(jsonGrxx.containsKey("zxbz")){
								grxx.setZxbz(jsonGrxx.getString("zxbz"));
							}
							grxxs.add(grxx);
						}
						data.setGrxxs(grxxs);
					}
				}
				
				/** 婚姻信息 */
				if(jsonData.containsKey("marryinfo") && jsonData.getJSONArray("marryinfo") != null){
					JSONArray jsonArrayMarryInfo = jsonData.getJSONArray("marryinfo");
					if(jsonArrayMarryInfo.size()>0){
						List<MarryInfo> marryInfos = new ArrayList<MarryInfo>();
						for(int i = 0;i<jsonArrayMarryInfo.size();i++){
							JSONObject jsonMarryInfo = jsonArrayMarryInfo.getJSONObject(i);
							MarryInfo marryInfo = new MarryInfo();
							marryInfo.setAppId(appId);
							marryInfo.setCrtUser(crtUser);
							if(jsonMarryInfo.containsKey("businesstype")){
								marryInfo.setBusinessType(jsonMarryInfo.getString("businesstype"));
							}
							marryInfos.add(marryInfo);
						}
						data.setMarryInfos(marryInfos);
					}
				}
				
				/** 公积金信息 */
				if(jsonData.containsKey("gjjxx") && jsonData.getJSONArray("gjjxx") != null){
					JSONArray jsonArrayGjjxx = jsonData.getJSONArray("gjjxx");
					if(jsonArrayGjjxx.size()>0){
						List<Gjjxx> gjjxxs = new ArrayList<Gjjxx>();
						for(int i = 0;i<jsonArrayGjjxx.size();i++){
							JSONObject jsonGjjxx = jsonArrayGjjxx.getJSONObject(i);
							Gjjxx gjjxx = new Gjjxx();
							gjjxx.setAppId(appId);
							gjjxx.setCrtUser(crtUser);
							if(jsonGjjxx.containsKey("dept_name")){
								gjjxx.setDeptName(jsonGjjxx.getString("dept_name"));
							}
							if(jsonGjjxx.containsKey("status")){
								gjjxx.setStatus(jsonGjjxx.getString("status"));
							}
							if(jsonGjjxx.containsKey("set_date")){
								gjjxx.setSetDate(jsonGjjxx.getString("set_date"));
							}
							if(jsonGjjxx.containsKey("pay_ym")){
								gjjxx.setPayYm(jsonGjjxx.getString("pay_ym"));
							}
							if(jsonGjjxx.containsKey("lxys")){
								gjjxx.setLxys(jsonGjjxx.getString("lxys"));
							}
							if(jsonGjjxx.containsKey("ljys")){
								gjjxx.setLjys(jsonGjjxx.getString("ljys"));
							}
							if(jsonGjjxx.containsKey("pay_base")){
								gjjxx.setPayBase(jsonGjjxx.getString("pay_base"));
							}
							if(jsonGjjxx.containsKey("ppay_amt")){
								gjjxx.setpPayAmt(jsonGjjxx.getString("ppay_amt"));
							}
							if(jsonGjjxx.containsKey("cpay_amt")){
								gjjxx.setcPayAmt(jsonGjjxx.getString("cpay_amt"));
							}
							if(jsonGjjxx.containsKey("pay_amt")){
								gjjxx.setPayAmt(jsonGjjxx.getString("pay_amt"));
							}
							if(jsonGjjxx.containsKey("grjcjz")){
								gjjxx.setGrjcjz(jsonGjjxx.getString("grjcjz"));
							}
							gjjxxs.add(gjjxx);
						}
						data.setGjjxxs(gjjxxs);
					}
				}
				
				/** 社保基本信息 */
				if(jsonData.containsKey("rsj_zx_ac01") && jsonData.getJSONArray("rsj_zx_ac01") != null){
					JSONArray jsonArrayRsjZxAc01 = jsonData.getJSONArray("rsj_zx_ac01");
					if(jsonArrayRsjZxAc01.size()>0){
						List<RsjZxAc01> rsjZxAc01s = new ArrayList<RsjZxAc01>();
						for(int i = 0;i<jsonArrayRsjZxAc01.size();i++){
							JSONObject jsonRsjZxAc01 = jsonArrayRsjZxAc01.getJSONObject(i);
							RsjZxAc01 rsjZxAc01 = new RsjZxAc01();
							rsjZxAc01.setAppId(appId);
							rsjZxAc01.setCrtUser(crtUser);
							if(jsonRsjZxAc01.containsKey("aac002")){
								rsjZxAc01.setAac002(jsonRsjZxAc01.getString("aac002"));
							}
							if(jsonRsjZxAc01.containsKey("aab004")){
								rsjZxAc01.setAab004(jsonRsjZxAc01.getString("aab004"));
							}
							if(jsonRsjZxAc01.containsKey("aae002")){
								rsjZxAc01.setAae002(jsonRsjZxAc01.getString("aae002"));
							}
							if(jsonRsjZxAc01.containsKey("lxys")){
								rsjZxAc01.setLxys(jsonRsjZxAc01.getString("lxys"));
							}
							if(jsonRsjZxAc01.containsKey("ljys")){
								rsjZxAc01.setLjys(jsonRsjZxAc01.getString("ljys"));
							}
							if(jsonRsjZxAc01.containsKey("bac601")){
								rsjZxAc01.setBac601(jsonRsjZxAc01.getString("bac601"));
							}
							if(jsonRsjZxAc01.containsKey("bac603")){
								rsjZxAc01.setBac603(jsonRsjZxAc01.getString("bac603"));
							}
							if(jsonRsjZxAc01.containsKey("bad006")){
								rsjZxAc01.setBad006(jsonRsjZxAc01.getString("bad006"));
							}
							if(jsonRsjZxAc01.containsKey("bad007")){
								rsjZxAc01.setBad007(jsonRsjZxAc01.getString("bad007"));
							}
							if(jsonRsjZxAc01.containsKey("bad008")){
								rsjZxAc01.setBad008(jsonRsjZxAc01.getString("bad008"));
							}
							if(jsonRsjZxAc01.containsKey("bad009")){
								rsjZxAc01.setBad009(jsonRsjZxAc01.getString("bad009"));
							}
							rsjZxAc01s.add(rsjZxAc01);
						}
						data.setRsjZxAc01s(rsjZxAc01s);
					}
				}
				
				/** 社保缴费信息 */
				if(jsonData.containsKey("sb_feeinfogrid") && jsonData.getJSONArray("sb_feeinfogrid") != null){
					JSONArray jsonArraySbFeeinfogrid = jsonData.getJSONArray("sb_feeinfogrid");
					if(jsonArraySbFeeinfogrid.size()>0){
						List<SbFeeinfogrid> sbFeeinfogrids = new ArrayList<SbFeeinfogrid>();
						for(int i = 0;i<jsonArraySbFeeinfogrid.size();i++){
							JSONObject jsonSbFeeinfogrid = jsonArraySbFeeinfogrid.getJSONObject(i);
							SbFeeinfogrid sbFeeinfogrid = new SbFeeinfogrid();
							sbFeeinfogrid.setAppId(appId);
							sbFeeinfogrid.setCrtUser(crtUser);
							if(jsonSbFeeinfogrid.containsKey("aae003")){
								sbFeeinfogrid.setAae003(jsonSbFeeinfogrid.getString("aae003"));
							}
							if(jsonSbFeeinfogrid.containsKey("aae020_100")){
								sbFeeinfogrid.setAae020_100(jsonSbFeeinfogrid.getString("aae020_100"));
							}
							if(jsonSbFeeinfogrid.containsKey("aae180_100")){
								sbFeeinfogrid.setAae180_100(jsonSbFeeinfogrid.getString("aae180_100"));
							}
							if(jsonSbFeeinfogrid.containsKey("aab004")){
								sbFeeinfogrid.setAab004(jsonSbFeeinfogrid.getString("aab004"));
							}
							if(jsonSbFeeinfogrid.containsKey("aaa115")){
								sbFeeinfogrid.setAaa115(jsonSbFeeinfogrid.getString("aaa115"));
							}
							sbFeeinfogrids.add(sbFeeinfogrid);
						}
						data.setSbFeeinfogrids(sbFeeinfogrids);
					}
				}
				
				/** 车辆信息 */
				if(jsonData.containsKey("gaj_jj_grjdcdjxx") && jsonData.getJSONArray("gaj_jj_grjdcdjxx") != null){
					JSONArray jsonArrayVehicleInfo = jsonData.getJSONArray("gaj_jj_grjdcdjxx");
					if(jsonArrayVehicleInfo.size()>0){
						List<VehicleInfo> vehicleInfos = new ArrayList<VehicleInfo>();
						for(int i = 0;i<jsonArrayVehicleInfo.size();i++){
							JSONObject jsonVehicleInfo = jsonArrayVehicleInfo.getJSONObject(i);
							VehicleInfo vehicleInfo = new VehicleInfo();
							vehicleInfo.setAppId(appId);
							vehicleInfo.setCrtUser(crtUser);
							if(jsonVehicleInfo.containsKey("dybj")){
								vehicleInfo.setDybj(jsonVehicleInfo.getString("dybj"));
							}
							if(jsonVehicleInfo.containsKey("hpzl")){
								vehicleInfo.setHpzl(jsonVehicleInfo.getString("hpzl"));
							}
							if(jsonVehicleInfo.containsKey("djrq")){
								vehicleInfo.setDjrq(jsonVehicleInfo.getString("djrq"));
							}
							vehicleInfos.add(vehicleInfo);
						}
						data.setVehicleInfos(vehicleInfos);
					}
				}
				
				/** 企业信息 */
				if(jsonData.containsKey("scjgj_hz_qyhznr") && jsonData.getJSONArray("scjgj_hz_qyhznr") != null){
					JSONArray jsonArrayEnterpriseInfo = jsonData.getJSONArray("scjgj_hz_qyhznr");
					if(jsonArrayEnterpriseInfo.size()>0){
						List<EnterpriseInfo> enterpriseInfos = new ArrayList<EnterpriseInfo>();
						for(int i = 0;i<jsonArrayEnterpriseInfo.size();i++){
							JSONObject jsonEnterpriseInfo = jsonArrayEnterpriseInfo.getJSONObject(i);
							EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
							enterpriseInfo.setAppId(appId);
							enterpriseInfo.setCrtUser(crtUser);
							if(jsonEnterpriseInfo.containsKey("uniscid")){
								enterpriseInfo.setUniscid(jsonEnterpriseInfo.getString("uniscid"));
							}
							if(jsonEnterpriseInfo.containsKey("qymc")){
								enterpriseInfo.setQymc(jsonEnterpriseInfo.getString("qymc"));
							}
							if(jsonEnterpriseInfo.containsKey("qylx")){
								enterpriseInfo.setQylx(jsonEnterpriseInfo.getString("qylx"));
							}
							if(jsonEnterpriseInfo.containsKey("fddbr")){
								enterpriseInfo.setFddbr(jsonEnterpriseInfo.getString("fddbr"));
							}
							if(jsonEnterpriseInfo.containsKey("sfzjhm")){
								enterpriseInfo.setSfzjhm(jsonEnterpriseInfo.getString("sfzjhm"));
							}
							if(jsonEnterpriseInfo.containsKey("zczb")){
								enterpriseInfo.setZczb(jsonEnterpriseInfo.getString("zczb"));
							}
							if(jsonEnterpriseInfo.containsKey("jyfw")){
								enterpriseInfo.setJyfw(jsonEnterpriseInfo.getString("jyfw"));
							}
							if(jsonEnterpriseInfo.containsKey("hzrq")){
								enterpriseInfo.setHzrq(jsonEnterpriseInfo.getString("hzrq"));
							}
							if(jsonEnterpriseInfo.containsKey("clrq")){
								enterpriseInfo.setClrq(jsonEnterpriseInfo.getString("clrq"));
							}
							if(jsonEnterpriseInfo.containsKey("jyqsrq")){
								enterpriseInfo.setJyqsrq(jsonEnterpriseInfo.getString("jyqsrq"));
							}
							if(jsonEnterpriseInfo.containsKey("jyjzrq")){
								enterpriseInfo.setJyjzrq(jsonEnterpriseInfo.getString("jyjzrq"));
							}
							if(jsonEnterpriseInfo.containsKey("zzyxqxqsrq")){
								enterpriseInfo.setZzyxqxqsrq(jsonEnterpriseInfo.getString("zzyxqxqsrq"));
							}
							if(jsonEnterpriseInfo.containsKey("zzyxqxjzrq")){
								enterpriseInfo.setZzyxqxjzrq(jsonEnterpriseInfo.getString("zzyxqxjzrq"));
							}
							enterpriseInfos.add(enterpriseInfo);
						}
						data.setEnterpriseInfos(enterpriseInfos);
					}
				}
				
				/** 公积金贷款信息 */
				if(jsonData.containsKey("gjj_loan_info") && jsonData.getJSONArray("gjj_loan_info") != null){
					JSONArray jsonArrayGjjLoanInfo = jsonData.getJSONArray("gjj_loan_info");
					if(jsonArrayGjjLoanInfo.size()>0){
						List<GjjLoanInfo> gjjLoanInfos = new ArrayList<GjjLoanInfo>();
						for(int i = 0;i<jsonArrayGjjLoanInfo.size();i++){
							JSONObject jsonGjjLoanInfo = jsonArrayGjjLoanInfo.getJSONObject(i);
							GjjLoanInfo gjjLoanInfo = new GjjLoanInfo();
							gjjLoanInfo.setAppId(appId);
							gjjLoanInfo.setCrtUser(crtUser);
							if(jsonGjjLoanInfo.containsKey("ln_amt")){
								gjjLoanInfo.setLnAmt(jsonGjjLoanInfo.getString("ln_amt"));
							}
							if(jsonGjjLoanInfo.containsKey("ln_bal")){
								gjjLoanInfo.setLnBal(jsonGjjLoanInfo.getString("ln_bal"));
							}
							if(jsonGjjLoanInfo.containsKey("ln_term")){
								gjjLoanInfo.setLnTerm(jsonGjjLoanInfo.getString("ln_term"));
							}
							if(jsonGjjLoanInfo.containsKey("rem_term")){
								gjjLoanInfo.setRemTerm(jsonGjjLoanInfo.getString("rem_term"));
							}
							if(jsonGjjLoanInfo.containsKey("sum_ovl_day")){
								gjjLoanInfo.setSumOvlDay(jsonGjjLoanInfo.getString("sum_ovl_day"));
							}
							gjjLoanInfos.add(gjjLoanInfo);
						}
						data.setGjjLoanInfos(gjjLoanInfos);
					}
				}
				
				/** 法院失信 */
				if(jsonData.containsKey("fy_natural_person") && jsonData.getJSONArray("fy_natural_person") != null){
					JSONArray jsonArrayFyNaturalPerson = jsonData.getJSONArray("fy_natural_person");
					if(jsonArrayFyNaturalPerson.size()>0){
						List<FyNaturalPerson> fyNaturalPersons = new ArrayList<FyNaturalPerson>();
						for(int i = 0;i<jsonArrayFyNaturalPerson.size();i++){
							JSONObject jsonFyNaturalPerson = jsonArrayFyNaturalPerson.getJSONObject(i);
							FyNaturalPerson fyNaturalPerson = new FyNaturalPerson();
							fyNaturalPerson.setAppId(appId);
							fyNaturalPerson.setCrtUser(crtUser);
							if(jsonFyNaturalPerson.containsKey("sqzxbd")){
								fyNaturalPerson.setSqzxbd(jsonFyNaturalPerson.getString("sqzxbd"));
							}
							if(jsonFyNaturalPerson.containsKey("ah")){
								fyNaturalPerson.setAh(jsonFyNaturalPerson.getString("ah"));
							}
							if(jsonFyNaturalPerson.containsKey("ay")){
								fyNaturalPerson.setAy(jsonFyNaturalPerson.getString("ay"));
							}
							if(jsonFyNaturalPerson.containsKey("zxfymc")){
								fyNaturalPerson.setZxfymc(jsonFyNaturalPerson.getString("zxfymc"));
							}
							if(jsonFyNaturalPerson.containsKey("ajzt")){
								fyNaturalPerson.setAjzt(jsonFyNaturalPerson.getString("ajzt"));
							}
							fyNaturalPersons.add(fyNaturalPerson);
						}
						data.setFyNaturalPersons(fyNaturalPersons);
					}
				}
				
				/** 车辆处罚信息 */
				if(jsonData.containsKey("gaj_jj_jdcjsyjtwfxx") && jsonData.getJSONArray("gaj_jj_jdcjsyjtwfxx") != null){
					JSONArray jsonArrayVehiclePenaltyInfo = jsonData.getJSONArray("gaj_jj_jdcjsyjtwfxx");
					if(jsonArrayVehiclePenaltyInfo.size()>0){
						List<VehiclePenaltyInfo> vehiclePenaltyInfos = new ArrayList<VehiclePenaltyInfo>();
						for(int i = 0;i<jsonArrayVehiclePenaltyInfo.size();i++){
							JSONObject jsonVehiclePenaltyInfo = jsonArrayVehiclePenaltyInfo.getJSONObject(i);
							VehiclePenaltyInfo vehiclePenaltyInfo = new VehiclePenaltyInfo();
							vehiclePenaltyInfo.setAppId(appId);
							vehiclePenaltyInfo.setCrtUser(crtUser);
							if(jsonVehiclePenaltyInfo.containsKey("wfsj")){
								vehiclePenaltyInfo.setWfsj(jsonVehiclePenaltyInfo.getString("wfsj"));
							}
							if(jsonVehiclePenaltyInfo.containsKey("wfjfs")){
								vehiclePenaltyInfo.setWfjfs(jsonVehiclePenaltyInfo.getString("wfjfs"));
							}
							if(jsonVehiclePenaltyInfo.containsKey("dsr")){
								vehiclePenaltyInfo.setDsr(jsonVehiclePenaltyInfo.getString("dsr"));
							}
							vehiclePenaltyInfos.add(vehiclePenaltyInfo);
						}
						data.setVehiclePenaltyInfos(vehiclePenaltyInfos);
					}
				}
				
				/** 水务缴费信息 */
				if(jsonData.containsKey("swj_t_serv_usage_mv") && jsonData.getJSONArray("swj_t_serv_usage_mv") != null){
					JSONArray jsonArrayWaterAffairsInfo = jsonData.getJSONArray("swj_t_serv_usage_mv");
					if(jsonArrayWaterAffairsInfo.size()>0){
						List<WaterAffairsInfo> waterAffairsInfos = new ArrayList<WaterAffairsInfo>();
						for(int i = 0;i<jsonArrayWaterAffairsInfo.size();i++){
							JSONObject jsonWaterAffairsInfo = jsonArrayWaterAffairsInfo.getJSONObject(i);
							WaterAffairsInfo waterAffairsInfo = new WaterAffairsInfo();
							waterAffairsInfo.setAppId(appId);
							waterAffairsInfo.setCrtUser(crtUser);
							if(jsonWaterAffairsInfo.containsKey("serv_code")){
								waterAffairsInfo.setServCode(jsonWaterAffairsInfo.getString("serv_code"));
							}
							if(jsonWaterAffairsInfo.containsKey("collection_dt")){
								waterAffairsInfo.setCollectionDt(jsonWaterAffairsInfo.getString("collection_dt"));
							}
							if(jsonWaterAffairsInfo.containsKey("payment_status")){
								waterAffairsInfo.setPaymentStatus(jsonWaterAffairsInfo.getString("payment_status"));
							}
							waterAffairsInfos.add(waterAffairsInfo);
						}
						data.setWaterAffairsInfos(waterAffairsInfos);
					}
				}
				
				hzCollection.setData(data);
			}
		}
		return hzCollection;
	}

}
