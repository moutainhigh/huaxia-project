package com.huaxia.plaze.ui.unicom.util;

import net.sf.json.JSONObject;

public final class RequestMessageBuilder {

	public static String build(String trnId, String trnCode, String certType, String certNo, String name, String mobile,
			String queryMode) {
		JSONObject jsonRoot = new JSONObject();
		JSONObject jsonRequest = new JSONObject();

		// 请求头报文组装
		JSONObject jsonHead = new JSONObject();
		jsonHead.put("REQUEST_CHANNEL", "SINGLE");
		jsonHead.put("TRN_ID", trnId);
		jsonHead.put("TRN_CODE", trnCode);
		jsonRequest.put("HEAD", jsonHead);

		// 请求体报文组装
		JSONObject jsonBody = new JSONObject();
		jsonBody.put("CERT_TYPE", certType);
		jsonBody.put("CERT_NO", certNo);
		jsonBody.put("NAME", name);
		jsonBody.put("MOBILE", mobile);
		jsonBody.put("QUERY_MODE", queryMode);
		jsonRequest.put("BODY", jsonBody);

		jsonRoot.put("REQUEST", jsonRequest);
		return jsonRoot.toString();
	}

	public static void main(String[] args) {
		String request = RequestMessageBuilder.build("1111", "T001110", "10", "622926198501293785", "张三", "13354260593",
				"1");
		System.out.println(request);
	}
}
