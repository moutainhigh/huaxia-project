package com.huaxia.plaze.ui.id5.caller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.util.AppConfig;
import com.huaxia.plaze.ui.id5.service.EducationService;
import com.huaxia.plaze.ui.id5.util.RequestMessageBuilder;

import net.sf.json.JSONObject;

public class EducationCallWorker implements Callable<Boolean> {

	private final static Logger logger = LoggerFactory.getLogger(EducationCallWorker.class);

	private Map<String, String> object;

	private EducationService educationService;

	public EducationCallWorker(EducationService educationService, Map<String, String> args) {
		this.educationService = educationService;
		this.object = args;
	}

	@Override
	public Boolean call() throws Exception {
		final String trnId = object.get("TRN_ID");
		String certType = object.get("CERT_TYPE");
		String mobile = object.get("MOBILE");

		String name = object.get("NAME");
		if (StringUtils.isBlank(name)) {
			logger.warn("[学历信息] 交易[{}]参数[ 姓名 ]校验失败", trnId);
			return false;
		}

		String certNo = object.get("CERT_NO");
		if (StringUtils.isBlank(certNo)) {
			logger.warn("[学历信息] 交易[{}]参数[ 证件号码 ]校验失败", trnId);
			return false;
		}

		AppConfig appConfig = AppConfig.getInstance();

		// 组装请求报文
		String queryMode = "1";
		String code = appConfig.getValue("id5.education.webservice.trade_code");
		final String requestMessage = RequestMessageBuilder.build(trnId, code, certType, certNo, name, mobile, queryMode);

		// 创建请求客户端
		Client client = null;
		try {
			URL url = new URL(AppConfig.getInstance().getValue("id5.education.webservice.url"));
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();

			// 设置HTTP连接的读超时（单位是毫秒）
			httpConnection.setReadTimeout(30000);

			httpConnection.connect();
			client = new Client(httpConnection.getInputStream(), null);

			// 设置发送超时限制（单位是毫秒）
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "30000");
			client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
			client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");

			Object[] response = client.invoke("invoke", new Object[] { requestMessage });
			String responseMessage = response[0].toString();
			if (StringUtils.isNotBlank(responseMessage)) {
				JSONObject jsonObject = JSONObject.fromObject(responseMessage);
				JSONObject responseObject = jsonObject.getJSONObject("RESPONSE");
				JSONObject bodyObject = responseObject.getJSONObject("BODY");
				String responseCode = bodyObject.getString("RESPONSE_CODE");
				if ("000000".equals(responseCode)) {
					int result = educationService.updateStatusById(trnId, "1");
					if (result > 0) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("WebService交易[{}]请求异常[{}]", new Object[] { trnId, e.getMessage() }, e);

			int result = educationService.updateStatusById(trnId, "2");
			if (result > 0) {
				return false;
			}
		} finally {
			if (client != null) {
				client.close();
			}
		}
		
		return false;
	}

	public static void main(String[] args) {
		String resultStr = "{\"RESPONSE\":{\"BODY\":{\"RESPONSE_BODY\":\"{\\\"status\\\":\\\"1\\\",\\\"code\\\":\\\"00\\\",\\\"errorDesc\\\":\\\"查询成功\\\",\\\"checkResult\\\":\\\"00\\\"}\",\"RESPONSE_DESC\":\"处理成功\",\"RESPONSE_CODE\":\"000000\",\"CARRIER\":\"TELECOM\"},\"HEAD\":{\"TRN_ID\":\"9a701236f7354a84a191939e9eb1bfe3\",\"RESPONSE_CHANNEL\":\"SINGLE\"}}}";
		if (StringUtils.isNotBlank(resultStr)) {
			JSONObject jsonObject = JSONObject.fromObject(resultStr);
			JSONObject responseObject = jsonObject.getJSONObject("RESPONSE");
			JSONObject bodyObject = responseObject.getJSONObject("BODY");
			String responseCode = bodyObject.getString("RESPONSE_CODE");
			System.out.println(responseCode);
		}
	}

}
