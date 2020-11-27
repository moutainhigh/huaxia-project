package com.huaxia.opas.util.phone;

import com.huaxia.opas.domain.phone.PhoneIdentification;

import net.sf.json.JSONObject;

/**
 * 华道返回参数解析工具类
 */
public class PhoneHdResponseParseUtil {


	public static PhoneIdentification parsingResult(String parameter,String appId) {

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

				if (jsonRes.containsKey("BODY")) {
					JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
					if (jsonBody.containsKey("RESPONSE_BODY") && jsonBody.getString("RESPONSE_BODY") != null
							&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
						
						bodyStr = jsonBody.getString("RESPONSE_BODY");
						
						JSONObject phoneMsgJson = JSONObject.fromObject(bodyStr);
						JSONObject phoneBackMessageJson = new JSONObject();
						JSONObject data = new JSONObject();
						//CODE映射responseCode
						if(phoneMsgJson.containsKey("CODE")){
							phoneBackMessageJson.put("responseCode", phoneMsgJson.getString("CODE"));
						}
						//RESULTDESC映射data里的result
						if(phoneMsgJson.containsKey("RESULTDESC")){
							data.put("result", phoneMsgJson.getString("RESULTDESC"));
						}
						//RESULT映射data里的resultCode  此处华道需要与汇安融映射 1-0  2-1  3-(-1)
						if(phoneMsgJson.containsKey("RESULT")){
							
							if(phoneMsgJson.getString("RESULT").equals("1")){
								data.put("resultCode", "0");
							}else if (phoneMsgJson.getString("RESULT").equals("2")){
								data.put("resultCode", "1");
							}else if (phoneMsgJson.getString("RESULT").equals("3")){
								data.put("resultCode", "-1");
							}
							
						}
						phoneBackMessageJson.put("data", data);
						
						//存入CRT_USER,区分汇安融和华道信息,如果json字符串存在key-CRT_USER属于华道信息,否则是汇安融信息
						phoneBackMessageJson.put("CRT_USER", "HD");
						
						bodyStr = phoneBackMessageJson.toString();
						
						return PhoneHarResponseParseUtil.parsingResult(bodyStr, appId);
						
					}
				}

			}

		}
		return null;
	}

}
