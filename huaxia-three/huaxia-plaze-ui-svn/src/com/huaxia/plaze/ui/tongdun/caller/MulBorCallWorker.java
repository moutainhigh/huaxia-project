package com.huaxia.plaze.ui.tongdun.caller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.lang.StringUtils;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.CommonsHttpMessageSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.util.AppConfig;
import com.huaxia.plaze.common.MessageBuilder;
import com.huaxia.plaze.ui.tongdun.service.MulBorSingleService;
import com.huaxia.plaze.ui.unicom.util.RequestMessageBuilder;

import net.sf.json.JSONObject;

public class MulBorCallWorker implements Callable<Boolean> {

	private final static Logger logger = LoggerFactory.getLogger(MulBorCallWorker.class);

	private Map<String, String> object;

	private MulBorSingleService mulBorSingleService;

	public MulBorCallWorker(MulBorSingleService mulBorSingleService, Map<String, String> args) {
		this.mulBorSingleService = mulBorSingleService;
		this.object = args;
	}

	@Override
	public Boolean call() throws Exception {
		String trnId = object.get("TRN_ID");
		String certType = object.get("CERT_TYPE");

		String name = object.get("NAME");
		if (StringUtils.isBlank(name)) {
			logger.warn("[同盾多头借贷] 交易[{}]参数[ 姓名 ]校验失败", trnId);
			return false;
		}

		String certNo = object.get("CERT_NO");
		if (StringUtils.isBlank(certNo)) {
			logger.warn("[同盾多头借贷] 交易[{}]参数[ 证件号码 ]校验失败", trnId);
			return false;
		}

		String mobile = object.get("MOBILE");
		if (StringUtils.isBlank(mobile)) {
			logger.warn("[同盾多头借贷] 交易[{}]参数[ 手机号码 ]校验失败", trnId);
			return false;
		}

		AppConfig appConfig = AppConfig.getInstance();

		// 组装请求报文
		Map<String, String> args = new HashMap<String, String>();
		args.put(MessageBuilder.REQUEST_CHANNEL, object.get(MessageBuilder.REQUEST_CHANNEL));
		args.put(MessageBuilder.QUERY_MODE, "1");
		args.put(MessageBuilder.TRN_CODE, "001500");
		String code = appConfig.getValue("tongdun.mulbor.webservice.trade_code");
		String requestMessage = RequestMessageBuilder.build(trnId, code, certType, certNo, name, mobile, "2");

		// 创建请求客户端
		Client client = null;
		try {
			URL url = new URL(AppConfig.getInstance().getValue("tongdun.dtjd.plaze.webservice.url"));
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
					int result = mulBorSingleService.updateStatusById(trnId, "1");
					if (result > 0) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("WebService 交易[{}]请求异常[{}]", new Object[] { trnId, e.getMessage() }, e);

			int result = mulBorSingleService.updateStatusById(trnId, "2");
			if (result > 0) {
				return true;
			}
		} finally {
			if (client != null) {
				client.close();
			}
		}

		return false;
	}

	public static void main(String[] args) {
		String resultStr = "{\"RESPONSE\":{\"BODY\":{\"RESPONSE_BODY\":\"{\\\"status\\\":\\\"1\\\",\\\"code\\\":\\\"00\\\",\\\"errorDesc\\\":\\\"查询成功\\\",\\\"checkResult\\\":\\\"00\\\"}\",\"RESPONSE_DESC\":\"处理成功\",\"RESPONSE_CODE\":\"000000\",\"CARRIER\":\"TELECOM\"},\"HEAD\":{\"TRN_ID\":\"9a701236f7354a84a191939e9eb1bfe3\",\"RESPONSE_CHANNEL\":\"PLAZE\"}}}";
		if (StringUtils.isNotBlank(resultStr)) {
			JSONObject jsonObject = JSONObject.fromObject(resultStr);
			JSONObject responseObject = jsonObject.getJSONObject("RESPONSE");
			JSONObject bodyObject = responseObject.getJSONObject("BODY");
			String responseCode = bodyObject.getString("RESPONSE_CODE");
			System.out.println(responseCode);
		}
	}

}
