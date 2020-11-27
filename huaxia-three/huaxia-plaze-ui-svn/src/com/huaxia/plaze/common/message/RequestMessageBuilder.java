package com.huaxia.plaze.common.message;

import java.util.Map;

import com.huaxia.plaze.common.MessageBuilder;

import net.sf.json.JSONObject;

/**
 * 请求报文构造器
 * 
 * @author zhiguo.li
 *
 */
public final class RequestMessageBuilder implements MessageBuilder {

	@Override
	public String build(Map<String, String> args) {
		if (args != null && args.size() > 0) {
			JSONObject jsonRoot = new JSONObject();
			JSONObject jsonRequest = new JSONObject();

			// 请求头报文组装
			JSONObject jsonHead = new JSONObject();
			jsonHead.put(MessageBuilder.REQUEST_CHANNEL, args.get(MessageBuilder.REQUEST_CHANNEL));
			jsonHead.put(MessageBuilder.TRN_ID, args.get(MessageBuilder.TRN_ID));
			jsonHead.put(MessageBuilder.TRN_CODE, args.get(MessageBuilder.TRN_CODE));
			jsonRequest.put("HEAD", jsonHead);

			// 请求体报文组装
			JSONObject jsonBody = new JSONObject();
			jsonBody.put(MessageBuilder.QUERY_MODE, args.get(MessageBuilder.QUERY_MODE));
			jsonBody.put(MessageBuilder.CERT_TYPE, args.get(MessageBuilder.CERT_TYPE));
			jsonBody.put(MessageBuilder.CERT_NO, args.get(MessageBuilder.CERT_NO));
			jsonBody.put(MessageBuilder.NAME, args.get(MessageBuilder.NAME));
			jsonBody.put(MessageBuilder.MOBILE, args.get(MessageBuilder.MOBILE));
			jsonRequest.put("BODY", jsonBody);

			jsonRoot.put("REQUEST", jsonRequest);
			return jsonRoot.toString();
		}
		return "";
	}

}
