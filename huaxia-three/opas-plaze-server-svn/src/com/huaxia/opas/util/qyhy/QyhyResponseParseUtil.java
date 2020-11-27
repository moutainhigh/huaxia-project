package com.huaxia.opas.util.qyhy;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 企业行业返回参数解析工具类
 */
public class QyhyResponseParseUtil {

	public static String parsingResult(String parameter) {

		if (parameter != null) {
			String responseBody = "";
			// 分解返回参数参数
			JSONObject jsonResponse = JSON.parseObject(parameter);
			if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
					&& !"".equals(jsonResponse.getString("RESPONSE"))) {
				JSONObject jsonRes = JSON.parseObject(jsonResponse.getString("RESPONSE"));

				if (jsonRes.containsKey("HEAD") && jsonRes.getString("HEAD") != null
						&& !"".equals(jsonRes.getString("HEAD"))) {
					JSONObject jsonHead = JSON.parseObject(jsonRes.getString("HEAD"));
					if (jsonHead.containsKey("TRN_ID") && jsonHead.getString("TRN_ID") != null
							&& !"".equals(jsonHead.getString("TRN_ID"))) {
						String trnId = jsonHead.getString("TRN_ID");
					}
				}

				if (jsonRes.containsKey("BODY")&& jsonRes.getString("BODY") != null
						&& !"".equals(jsonRes.getString("BODY"))) {
					JSONObject jsonBody = JSON.parseObject(jsonRes.getString("BODY"));
					if (jsonBody.containsKey("RESPONSE_BODY") && jsonBody.getString("RESPONSE_BODY") != null
							&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
						responseBody = jsonBody.getString("RESPONSE_BODY");
					//	System.out.println("======================bodyStr=" + responseBody + "=========================");
						
					}
					if (jsonBody.containsKey("RESPONSE_CODE") && jsonBody.getString("RESPONSE_CODE") != null
							&& !"".equals(jsonBody.getString("RESPONSE_CODE"))) {
						String responseCode = jsonBody.getString("RESPONSE_CODE");
					}
				}

			}
			return responseBody;

		}
		return null;
	}

	public static void main(String[] args) {

		String returnStr = "{\"data\":{\"result\":\"一致\",\"resultCode\":0},\"isSuccess\":true,\"msgKey\":\"kQraOtn9j0jwxsSN\",\"responseCode\":\"E00000000\",\"responseDesc\":\"响应成功！\"}";
		if (StringUtils.isNotBlank(returnStr)) {
			// 连接字符串，实现数据库的查询和实现
			Map<String, Object> head = new HashMap<String, Object>();
			head.put("REQUEST_SYSTEM", "PLAZE");
			head.put("TRN_ID", "11111");

			Map<String, Object> body = new HashMap<String, Object>();
			body.put("RESPONSE_CODE", "E000000");
			body.put("RESPONSE_DESC", "响应成功");
			body.put("RESULT_DESC", String.valueOf(returnStr));

			Map<String, Object> response = new HashMap<String, Object>();
			response.put("HEAD", head);
			response.put("BODY", body);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("RESPONSE", response);

			JSONObject jsonStr = new JSONObject(params);
			System.out.println("===================返回参数=============================");
			System.out.println(JSON.toJSONString(jsonStr));
			String test = parsingResult(jsonStr.toString());
			System.out.println(test);

		}
	}
}
