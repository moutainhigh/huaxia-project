package com.huaxia.cams.worker;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.huaxia.cams.modules.jianbing.service.JianBingResponseService;
import com.huaxia.cams.modules.jianbing.service.JianBingService;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.util.SpringContextUtil;
import com.huaxia.opas.util.TaskStatusUtil;
import com.huaxia.support.cams.domain.TaskCallStatus;
import com.huaxia.support.cams.service.TaskCallStatusService;

import net.sf.json.JSONObject;

/**
 * 51易达金任务处理器
 * 
 * @author liyanan
 *
 */
public class WST000600TaskWorker implements Runnable {

	private AppConfig appConfig = AppConfig.getInstance();
	private final static Logger logger = LoggerFactory.getLogger(WST000600TaskWorker.class);

	private TaskCallStatusService taskCallStatusService = (TaskCallStatusService) SpringContextUtil
			.getBean("taskCallStatusService");
	private JianBingResponseService jianBingResponseService = (JianBingResponseService) SpringContextUtil
			.getBean(JianBingResponseService.class);
	private JianBingService JianBingService = (JianBingService) SpringContextUtil.getBean("jianBingService");

	@Override
	public void run() {
		while (true) {

			List<TaskCallStatus> taskStatusList = taskCallStatusService.queryAllTaskByType("000600", 10);
			if (taskStatusList != null && taskStatusList.size() > 0) {
				int size = taskStatusList.size();
				logger.info("[客户端 & 51易达金] 查询51易达金任务条数：{}", size);
				String appId = null;
				for (TaskCallStatus task : taskStatusList) {
					try {
						// 获取appid
						appId = task.getAppId();
						// 获取applyId也就是c1表中的C4_BUEMB字段
						String applyId = JianBingService.queryApplyId(appId);
						// 写数据到task_status_history
						taskCallStatusService.saveTaskStatusHistory(task.getAppId(), "000600");
						// 将task_call_status表中对应数据状态改为1
						taskCallStatusService.updateSingleTaskStatus(task.getAppId(), TaskStatusUtil.START, "000600",
								null, TaskStatusUtil.CURR_USER, "1", null);
						// 请求开始，根据要求——————拼接请求对象
						String requestStr = getJson(task.getTaskId(), applyId, task.getCertNo(), task.getCertType(),
								task.getName(), task.getMobile());
						Client client = null;
						// 创建调用huaxia-plaze-server中的接口的对象
						client = new Client(new URL(appConfig.getValue("jianbing.webservice.url")));
						client.setTimeout(600);
						// 调用huaxia-plaze-server中的方法，返回请求信息
						Object[] result = client.invoke("invoke", new Object[] { requestStr });
						// 将返回信息写到logger
						logger.info("請求返回信息" + result[0].toString());
						// 结果处理
						getResult(result[0].toString(), appId, task.getTaskId());
					} catch (Exception e) {
						logger.error("[客户端 & 51易达金] 请求处理异常  " + appId + " Exception:{}", e);
						taskCallStatusService.saveTaskStatusHistory(task.getAppId(), "000600");
						taskCallStatusService.updateSingleTaskStatus(task.getAppId(),
								TaskStatusUtil.INTERNAL_CALL_ERROE, "000600", null, TaskStatusUtil.CURR_USER, "1",
								null);
					}
				}
			} else {
				// logger.info("[客户端 & 51易达金] 没有扫描到51易达金任务");
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static String getJson(String trnId, String applyId, String certNo, String certType, String name,
			String mobile) {
		SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = formate.format(new Date());
		String queryMode = "1";
		// 连接字符串，实现数据库的查询和实现
		Map<String, Object> head = new HashMap<String, Object>();

		head.put("REQUEST_CHANNEL", "CAMS");
		head.put("TRN_ID", trnId);
		// 交易代码表中 jianbing 对应的交易代码
		head.put("TRN_CODE", "T000600");
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("QUERY_MODE", queryMode);

		body.put("F", "loadApplyExtInfo");
		body.put("APPLY_ID", applyId);
		// 产品编号 固定的 0801
		// body.put("PRODUCT_CID", "0801");
		body.put("PRODUCT_CID", "133");
		body.put("TIME", time);

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
		String paramsStr = JSON.toJSONString(json);
		return paramsStr;
	}

	private void getResult(String bodyStr, String appId, String trnId) {
		JSONObject json = JSONObject.fromObject(bodyStr);
		String code = "";
		String message = null;
		if (json.containsKey("RESPONSE") && !"".equals(json.getString("RESPONSE"))) {
			JSONObject jsonRequest = JSONObject.fromObject(json.getString("RESPONSE"));

			if (jsonRequest.containsKey("BODY") && !"".equals(jsonRequest.getString("BODY"))) {
				JSONObject jsonRequestBody = JSONObject.fromObject(jsonRequest.getString("BODY"));
				code = jsonRequestBody.getString("RESPONSE_CODE");
				message = jsonRequestBody.getString("RESPONSE_BODY");
			}

			if (message != null && !"".equals(message) && "000000".equals(code)) {
				Map<String, Object> map = new HashMap<>();
				map.put("bodyStr", message);
				map.put("trnId", trnId);
				map.put("appId", appId);
				map.put("crtUser", TaskStatusUtil.CURR_USER);
				int result = 0;
				try {
					// 将数据保存到————7张表中
					result = jianBingResponseService.save(map);
				} catch (Exception e) {
					logger.error("[客户端 & 51易达金]响应数据入库异常,错误数据appId为  " + appId + " ,Exception:{}", e);
					e.printStackTrace();
				}
				if (result <= 0) {
					logger.info("[客户端 & 51易达金] 入库期间出错,错误数据trnId为:{}", trnId);
					taskCallStatusService.saveTaskStatusHistory(appId, "000600");
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.PARSE_ERROE, "000600",
							null, TaskStatusUtil.CURR_USER, "1", null);//解析异常 TaskStatusUtil.PARSE_ERROE
				} else {
					taskCallStatusService.saveTaskStatusHistory(appId, "000600");
					taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SUCCESS, "000600", null,
							TaskStatusUtil.CURR_USER, "1", null);
					logger.info("[客户端 & 51易达金] 51易达金任务执行完成");
				}

			} else {
				logger.info("[客户端 & 51易达金] 未能获取到message,错误数据trnId为:{}", trnId);
				logger.info("出错响应数据的响应码为:{}", code);
				taskCallStatusService.saveTaskStatusHistory(appId, "000600");
				taskCallStatusService.updateSingleTaskStatus(appId, TaskStatusUtil.SERVE_RESPOSE_ERROE, "000600", null,
						TaskStatusUtil.CURR_USER, "1", null);

			}
		}
	}

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring-*.xml");
		Thread thead = new Thread(new WST000600TaskWorker());
		thead.start();
	}
}
