package com.huaxia.opas.util;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.cams.modules.unicom.domain.UnicomAddress;

import net.sf.json.JSONObject;

/**
 * 联通地址类信息工具类
 * 
 * @author lipengfei
 *
 */
public class UniaddinforUtil {

	private final static Logger logger = LoggerFactory.getLogger(UniaddinforUtil.class);

	// 审核请求参数,如果有参数异常,返回true
	public static boolean checkParameter(Map<String, String> map) {
		// 校验标志
		boolean paramErrorFlag = false;
		// 获取参数
		String appId = map.get("APP_ID"); // 申请件编号
		String name = map.get("NAME"); // 姓名
		String mobile = map.get("MOBILE"); // 手机号
		String trnId = map.get("TRN_ID"); // 交易编号
		String codda = map.get("CODDA"); // 详细工作地址
		// 校验参数
		if ("".equals(appId) || appId == null) {
			paramErrorFlag = true;
			if (logger.isDebugEnabled()) {
				logger.debug("[客户端 & 联通地址类信息(工作地址)] 请求的申请件编号为空，流水号为：{}", appId);
			}
		}
		if ("".equals(name) || name == null) {
			paramErrorFlag = true;
			if (logger.isDebugEnabled()) {
				logger.debug("[客户端 & 联通地址类信息(工作地址)] 请求的用户名为空，流水号为：{}", appId);
			}
		}
		if ("".equals(mobile) || mobile == null) {
			paramErrorFlag = true;
			if (logger.isDebugEnabled()) {
				logger.debug("[客户端 & 联通地址类信息(工作地址)] 请求的手机号为空，流水号为：{}", appId);
			}
		}
		if ("".equals(trnId) || trnId == null) {
			paramErrorFlag = true;
			if (logger.isDebugEnabled()) {
				logger.debug("[客户端 & 联通地址类信息(工作地址)] 请求的交易编号为空，流水号为：{}", appId);
			}
		}
		if ("".equals(codda) || codda == null) {
			paramErrorFlag = true;
			if (logger.isDebugEnabled()) {
				logger.debug("[客户端 & 联通地址类信息(工作地址)] 请求的详细地址为空，流水号为：{}", appId);
			}
		}
		return paramErrorFlag;
	}

	// 组装请求报文,以第三方查询平台报文交互接口1.0.8为准
	public static String queryUniaddInfor(Map<String, String> map) {
		// 获取参数
		String appId = map.get("APP_ID"); // 申请件编号
		String name = map.get("NAME"); // 姓名
		String mobile = map.get("MOBILE"); // 手机号
		String trnId = map.get("TRN_ID"); // 交易编号
		String codda = map.get("CODDA"); // 详细工作地址
		String trnCode = map.get("TRN_CODE"); // 交易代码
		// 报文头
		Map<String, Object> head = new HashMap<String, Object>();
		head.put("REQUEST_CHANNEL", "CAMS"); // 请求渠道
		head.put("TRN_ID", trnId); // 交易编号
		head.put("TRN_CODE", trnCode); // 交易代码
		// 报文体
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("QUERY_MODE", "3"); // 查询类型
		body.put("NAME", name); // 姓名
		body.put("MOBILE", mobile); // 手机号
		body.put("CODDA", codda); // 详细地址
		// 报文
		Map<String, Object> request = new HashMap<String, Object>();
		request.put("HEAD", head);
		request.put("BODY", body);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("REQUEST", request);
		// map转换成json,json转换成String.
		JSONObject json = JSONObject.fromObject(params);
		String queryUniaddInfor = json.toString();

//		logger.info("联通地址类信息请求报文:{}",queryUniaddInfor);
		
		return queryUniaddInfor;
	}

	// 解析返回报文,以第三方查询平台报文交互接口1.0.8为准
	public static Map<String, String> parseReturnMsg(JSONObject jsonResponse) {
		Map<String, String> reponseMap = new HashMap<String, String>();

		if (jsonResponse.containsKey("RESPONSE") && jsonResponse.getString("RESPONSE") != null
				&& !"".equals(jsonResponse.getString("RESPONSE"))) {
			JSONObject jsonRes = JSONObject.fromObject(jsonResponse.getString("RESPONSE"));
			// 报文体
			if (jsonRes.containsKey("BODY") && jsonRes.getString("BODY") != null
					&& !"".equals(jsonRes.getString("BODY"))) {
				JSONObject jsonBody = JSONObject.fromObject(jsonRes.getString("BODY"));
				// 响应代码
				if (jsonBody.containsKey("RESPONSE_CODE") && jsonBody.getString("RESPONSE_CODE") != null
						&& !"".equals(jsonBody.getString("RESPONSE_CODE"))) {
					String responseCode = jsonBody.getString("RESPONSE_CODE");
					reponseMap.put("responseCode", responseCode);
				}
				// 响应报文体
				if (jsonBody.containsKey("RESPONSE_BODY") && jsonBody.getString("RESPONSE_BODY") != null
						&& !"".equals(jsonBody.getString("RESPONSE_BODY"))) {
					String responseBody = jsonBody.getString("RESPONSE_BODY");
					reponseMap.put("responseBody", responseBody);
				}
				// 手机服务商
				if (jsonBody.containsKey("CARRIER") && jsonBody.getString("CARRIER") != null
						&& !"".equals(jsonBody.getString("CARRIER"))) {
					String carrier = jsonBody.getString("CARRIER");
					reponseMap.put("carrier", carrier);
				}
				// 校验结果中文描述
				if (jsonBody.containsKey("checkDesc") && jsonBody.getString("checkDesc") != null
						&& !"".equals(jsonBody.getString("checkDesc"))) {
					String checkDesc = jsonBody.getString("checkDesc");
					reponseMap.put("checkDesc", checkDesc);
				}
				// 手机号
				if (jsonBody.containsKey("mobile") && jsonBody.getString("mobile") != null
						&& !"".equals(jsonBody.getString("mobile"))) {
					String mobile = jsonBody.getString("mobile");
					reponseMap.put("mobile", mobile);
				}
			}
			
			// 报文头
			if (jsonRes.containsKey("HEAD") && jsonRes.getString("HEAD") != null
					&& !"".equals(jsonRes.getString("HEAD"))) {
				JSONObject jsonHead = JSONObject.fromObject(jsonRes.getString("HEAD"));
				// 获取交易编码
				if (jsonHead.containsKey("TRN_ID") && jsonHead.getString("TRN_ID") != null
						&& !"".equals(jsonHead.getString("TRN_ID"))) {
					String trn_id = jsonHead.getString("TRN_ID");
					reponseMap.put("TRN_ID", trn_id);
				}
			} 
		}

		return reponseMap;
	}

	// 解析报文体部分
	public static UnicomAddress parseReturnBody(String responseBody) {
		UnicomAddress unicomAddress = new UnicomAddress();
		try {
			// 将报文体转换成JSONObject模式,解析后存入实体类中
			JSONObject js = JSONObject.fromObject(responseBody);
			// 是否成功
			if (js.containsKey("status") && !"".equals(js.getString("status")) && js.getString("status") != null) {
				unicomAddress.setSuccess(js.getString("status"));
			}
			// 响应编码
			if (js.containsKey("code") && !"".equals(js.getString("code")) && js.getString("code") != null) {
				unicomAddress.setResponseCode(js.getString("code"));
			}
			// 响应编码描述
			if (js.containsKey("errorDesc") && !"".equals(js.getString("errorDesc"))
					&& js.getString("errorDesc") != null) {
				unicomAddress.setResponseDesc(js.getString("errorDesc"));
			}
			// 校验结果
			if (js.containsKey("checkResult") && !"".equals(js.getString("checkResult"))
					&& js.getString("checkResult") != null) {
				unicomAddress.setResponseResult(js.getString("checkResult"));
			}
			// 创建用户,发起请求的位置
			unicomAddress.setCrtUser("CAMS");

			return unicomAddress;
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("[客户端 & 联通地址类信息(工作地址)] 报文体解析出现异常");
			unicomAddress.setResponseCode("报文解析异常");
			return unicomAddress;
		}
	}

}
