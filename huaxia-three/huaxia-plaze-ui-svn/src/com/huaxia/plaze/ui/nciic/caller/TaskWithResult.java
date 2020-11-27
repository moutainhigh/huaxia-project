package com.huaxia.plaze.ui.nciic.caller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.huaxia.opas.util.AppConfig;
import com.huaxia.plaze.ui.nciic.domain.SimplifyPoliceSingle;
import com.huaxia.plaze.ui.nciic.service.SimplifyPoliceService;
import com.huaxia.util.SpringContextUtil;

import net.sf.json.JSONObject;

public class TaskWithResult implements Callable<Boolean> {

	private SimplifyPoliceService simplifyPoliceService;

	private static final Logger logger = LoggerFactory.getLogger(TaskWithResult.class);

	private SimplifyPoliceSingle single;

	private final static AppConfig appConfig = AppConfig.getInstance();

	public TaskWithResult(SimplifyPoliceSingle single) {
		this.single = single;
	}

	/**
	 * 任务的具体过程，一旦任务传给ExecutorService的submit方法， 则该方法自动在一个线程上执行
	 * 
	 * @return
	 */
	public Boolean call() throws Exception {
		simplifyPoliceService =(SimplifyPoliceService) SpringContextUtil.getBean("simplifyPoliceService");

		// 组装请求报文
		String queryMode = "1";

		// 连接字符串，实现数据库的查询和实现
		Map<String, Object> head = new HashMap<String, Object>();
		head.put("REQUEST_CHANNEL", "PLAZE");
		head.put("TRN_ID", single.getTrnId());
		head.put("TRN_CODE", "T000200");
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("QUERY_MODE", queryMode);
		body.put("CERT_TYPE", null);
		body.put("CERT_NO", single.getCertNo());
		body.put("NAME", single.getName());
		body.put("MOBILE", null);

		Map<String, Object> request = new HashMap<String, Object>();
		request.put("HEAD", head);
		request.put("BODY", body);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("REQUEST", request);

		JSONObject jsons = JSONObject.fromObject(params);
		String paramsStr = JSON.toJSONString(jsons);

		Client client = null;
		try {
			client = new Client(new URL(appConfig.getValue("nciic.police.webservice.url")));
			client.setTimeout(120000);
			// 设置发送超时限制，单位是毫秒
			Object[] str = client.invoke("invoke", new Object[] { paramsStr });
			JSONObject json = JSONObject.fromObject(str[0]);
			String code = "";
			String message = null;
			if (json.containsKey("RESPONSE") && !"".equals(json.getString("RESPONSE"))) {
				JSONObject jsonRequest = JSONObject.fromObject(json.getString("RESPONSE"));
				if (jsonRequest.containsKey("BODY") && !"".equals(jsonRequest.getString("BODY"))) {
					JSONObject jsonRequestBody = JSONObject.fromObject(jsonRequest.getString("BODY"));
					code = jsonRequestBody.getString("RESPONSE_CODE");
					message = jsonRequestBody.getString("RESPONSE_BODY");
				}
			}

			if (message != null && !"".equals(message) && "000000".equals(code)) {
				logger.info(single.toString() + "发起查询成功");
				single.setQueryStatus("1");
				int result = simplifyPoliceService.updateStatusSingle(single);
				if (result > 0) {
					logger.info(single.toString() + "更新状态成功");
				} else {
					logger.error(single.toString() + "更新状态失败");
				}
				return true;
			} else {
				logger.info(single.toString() + "发起查询失败");
				single.setQueryStatus("2");
				int result = simplifyPoliceService.updateStatusSingle(single);
				if (result > 0) {
					logger.info(single.toString() + "更新状态成功");
				} else {
					logger.error(single.toString() + "更新状态失败");
				}
				return false;
			}
		} catch (Exception e) {
			logger.error(single.toString() + "发起查询失败");
			logger.error(e.getMessage(), e);
			return false;
		}
	}

}
