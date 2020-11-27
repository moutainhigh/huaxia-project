package com.huaxia.opas.util.phone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.domain.phone.PhoneIdentification;

import net.sf.json.JSONObject;

/**
 * 汇安融返回参数解析工具类
 */
public class PhoneHarResponseParseUtil {

	private static final Logger logger = LoggerFactory.getLogger(PhoneHarResponseParseUtil.class);

	public static PhoneIdentification parsingResult(String parameter, String appId) {
		PhoneIdentification phoneIdentification = new PhoneIdentification();

		if (parameter != null) {
			String bodyStr = "";
			// 分解返回参数参数
			JSONObject jsonResponse = JSONObject.fromObject(parameter);
			if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
					&& !"".equals(jsonResponse.getString("RESPONSE"))) {
				JSONObject jsonRes = JSONObject.fromObject(jsonResponse.getString("RESPONSE"));

				if (jsonRes.containsKey("HEAD") && jsonRes.getString("HEAD") != null
						&& !"".equals(jsonRes.getString("HEAD"))) {
					JSONObject jsonHead = JSONObject.fromObject(jsonRes.getString("HEAD"));
					if (jsonHead.containsKey("TRN_ID") && jsonHead.getString("TRN_ID") != null
							&& !"".equals(jsonHead.getString("TRN_ID"))) {
					}
				}

				if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
						&& !"".equals(jsonRes.getString("BODY"))) {
					JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
					if (jsonBody.containsKey("RESPONSE_BODY") && jsonBody.getString("RESPONSE_BODY") != null
							&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
						bodyStr = jsonBody.getString("RESPONSE_BODY");
					}

					if (bodyStr == null || "".equals(bodyStr)) {
						if (logger.isErrorEnabled()) {
							logger.error("[客户端 & 手机实名制] 数据解析异常! {}", "手机实名制报文为空");
						}
						return null;
					}

					JSONObject json = JSONObject.fromObject(bodyStr);

					phoneIdentification.setAPP_ID(appId);

					// 判断是否存在CRT_USER,以区分汇安融 华道信息
					if (json.containsKey("CRT_USER") && json.getString("CRT_USER") != null
							&& !"".equals(json.getString("CRT_USER"))) {
						phoneIdentification.setCRT_USER(json.getString("CRT_USER"));
					} else {
						phoneIdentification.setCRT_USER("HAR");
					}

					// isSuccess 是否响应成功
					if (json.containsKey("isSuccess") && json.getString("isSuccess") != null
							&& !"".equals(json.getString("isSuccess"))) {
						phoneIdentification.setSUCCESS(json.getString("isSuccess"));
					}

					// responseCode 公共响应码 E00000000 为响应成功
					if (json.containsKey("responseCode") && json.getString("responseCode") != null
							&& !"".equals(json.getString("responseCode"))) {
						phoneIdentification.setRESPONSE_CODE(json.getString("responseCode"));
					}

					// responseDesc 公共响应码描述
					if (json.containsKey("responseDesc") && json.getString("responseDesc") != null
							&& !"".equals(json.getString("responseDesc"))) {
						phoneIdentification.setRESPONSE_DESC(json.getString("responseDesc"));
					}

					// data包含result,resultCode
					if (json.containsKey("data") && json.getString("data") != null
							&& !"".equals(json.getString("data"))) {

						String data = json.getString("data");
						JSONObject jsonOfData = JSONObject.fromObject(data);

						// resultCode 业务响应码
						if (jsonOfData.containsKey("resultCode") && jsonOfData.getString("resultCode") != null
								&& !"".equals(jsonOfData.getString("resultCode"))) {
							phoneIdentification.setRESULT_CODE(jsonOfData.getString("resultCode"));
						}

						// result 业务响应码描述
						if (jsonOfData.containsKey("result") && jsonOfData.getString("result") != null
								&& !"".equals(jsonOfData.getString("result"))) {
							phoneIdentification.setRESULT_DESC(jsonOfData.getString("result"));
						}

					}

					return phoneIdentification;

				}

			}

		}
		return null;
	}

}
