package com.huaxia.plaze.ui.unicom.util;

import com.alibaba.fastjson.JSONObject;

public class AddressRequest {

	public static String build(String trnId, String code, String mobile, String address, String queryModel,
			String apiKey) {
		JSONObject jsonRoot = new JSONObject();
		JSONObject jsonRequest = new JSONObject();

		// 请求头报文组装
		JSONObject jsonHead = new JSONObject();
		jsonHead.put("REQUEST_CHANNEL", "SINGLE");
		jsonHead.put("TRN_ID", trnId);
		jsonHead.put("TRN_CODE", code);
		jsonRequest.put("HEAD", jsonHead);

		// 请求体报文组装
		JSONObject jsonBody = new JSONObject();
		
		jsonBody.put("MOBILE", mobile);
		jsonBody.put("CODDA", address);
		jsonBody.put("QUERY_MODE", queryModel);
		jsonBody.put("API_KEY", apiKey);
		
		jsonRequest.put("BODY", jsonBody);

		jsonRoot.put("REQUEST", jsonRequest);
		return jsonRoot.toString();
	}
	
	public static void main(String[] args) {
		String request = AddressRequest.build("123", "001102", "156516", "北京", "1", "360015");
		System.out.println(request);
	}
	
}
