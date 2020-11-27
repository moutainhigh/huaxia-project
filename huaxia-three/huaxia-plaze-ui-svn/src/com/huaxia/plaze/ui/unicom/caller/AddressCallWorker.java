package com.huaxia.plaze.ui.unicom.caller;

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
import com.huaxia.plaze.ui.unicom.service.AddressSingleService;
import com.huaxia.plaze.ui.unicom.util.AddressRequest;

import net.sf.json.JSONObject;

public class AddressCallWorker implements Callable<Boolean> {

	private final static Logger logger = LoggerFactory.getLogger(AddressCallWorker.class);

	private Map<String, String> object;

	private AddressSingleService addressSingleService;

	public AddressCallWorker(AddressSingleService addressSingleService, Map<String, String> object) {
		this.object = object;
		this.addressSingleService = addressSingleService;
	}

	@Override
	public Boolean call() throws Exception {

		String trnId = object.get("TRN_ID");
		String mobile = object.get("MOBILE");
		if (StringUtils.isBlank(mobile)) {
			logger.info("[联通地质类信息单条实时查询] 交易[{}]参数[ 手机号]校验失败", trnId);
			return false;
		}
		String address = object.get("ADDRESS");
		if (StringUtils.isBlank(address)) {
			logger.info("[联通地质类信息单条实时查询] 交易[{}]参数[ 地址 ]校验失败", trnId);
			return false;
		}
		String apiKey = object.get("API_KEY");
		if (StringUtils.isBlank(apiKey)) {
			logger.info("[联通地质类信息单条实时查询] 交易[{}]参数[接口（产品）ID号 ]校验失败", trnId);
			return false;
		}
		AppConfig appConfig = AppConfig.getInstance();
		// 组装请求报文
		String queryModel = "1";
		String code = "";
		if ("360015".equals(apiKey)) {
			code = appConfig.getValue("unicom.address.work.trade_code");
		} else {
			code = appConfig.getValue("unicom.address.live.trade_code");
		}
		String requestJson = AddressRequest.build(trnId, code, mobile, address, queryModel, apiKey);

		Client client = null;
		try {
			String urlParam = null;
			if ("360015".equals(apiKey)) {
				urlParam = appConfig.getValue("unicom.address.webservice.work.url");
			} else {
				urlParam = appConfig.getValue("unicom.address.webservice.live.url");
			}
			URL url = new URL(urlParam);
			HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
			// 设置HTTP连接的组曲超时（单位毫秒）
			httpConnection.setReadTimeout(30000);
			httpConnection.connect();
			client = new Client(httpConnection.getInputStream(), null);
			// 设置发送超时限制（单位毫秒）
			client.setProperty(CommonsHttpMessageSender.HTTP_TIMEOUT, "30000");
			client.setProperty(CommonsHttpMessageSender.DISABLE_KEEP_ALIVE, "true");
			client.setProperty(CommonsHttpMessageSender.DISABLE_EXPECT_CONTINUE, "true");

			Object[] response = client.invoke("invoke", new Object[] { requestJson });
			String responseJson = response[0].toString();
			if (StringUtils.isNotBlank(responseJson)) {
				JSONObject jsonObject = JSONObject.fromObject(responseJson);
				JSONObject responseObject = jsonObject.getJSONObject("RESPONSE");
				JSONObject bodyObject = responseObject.getJSONObject("BODY");
				String responseCode = bodyObject.getString("RESPONSE_CODE");
				if ("000000".equals(responseCode)) {
					int result = addressSingleService.updateStatusById(trnId, "1");
					if (result > 0) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("WebService 交易[{}]请求异常[{}]", new Object[] { trnId, e.getMessage() }, e);
			int result = addressSingleService.updateStatusById(trnId, "2");
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

}
