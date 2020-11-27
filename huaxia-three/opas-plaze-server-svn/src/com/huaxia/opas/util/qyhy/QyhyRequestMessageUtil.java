package com.huaxia.opas.util.qyhy;

import net.sf.json.JSONObject;

public class QyhyRequestMessageUtil {

	public static String assemblingParameters(String trnId, String TrnCode, String enterprise,String CertType, String CertNo, String name,
			String mobile, String queryMode) {
		JSONObject jsonObjectDmzReq = new JSONObject();
		JSONObject responseObjectDmzReq = new JSONObject();

		// 请求头报文组装
		JSONObject responseHeadObjectDmzReq = new JSONObject();
		responseHeadObjectDmzReq.put("REQUEST_CHANNEL", "CAMS");
		responseHeadObjectDmzReq.put("TRN_ID", trnId);
		responseHeadObjectDmzReq.put("TRN_CODE", TrnCode);

		responseObjectDmzReq.put("HEAD", responseHeadObjectDmzReq);
		// 请求体报文组装
		JSONObject responseBodyObjectDmzReq = new JSONObject();
		responseBodyObjectDmzReq.put("ENTERPRISE_NAME", enterprise);
		responseBodyObjectDmzReq.put("CERT_TYPE", CertType);
		responseBodyObjectDmzReq.put("CERT_NO", CertNo);
		responseBodyObjectDmzReq.put("NAME", name);
		responseBodyObjectDmzReq.put("MOBILE", mobile);
		responseBodyObjectDmzReq.put("QUERY_MODE", queryMode);

		responseObjectDmzReq.put("BODY", responseBodyObjectDmzReq);

		jsonObjectDmzReq.put("REQUEST", responseObjectDmzReq);

		return jsonObjectDmzReq.toString();
	}

	public static void main(String[] args) {
		String testPa = assemblingParameters("1111", "T001200","北京动物园", "10", "622926198501293785", "张三", "13354260593", "1");
		System.out.println(testPa);
	}
}
