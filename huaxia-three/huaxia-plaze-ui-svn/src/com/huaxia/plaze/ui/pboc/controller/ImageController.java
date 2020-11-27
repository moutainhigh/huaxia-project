package com.huaxia.plaze.ui.pboc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.xfire.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huaxia.opas.util.AppConfig;

@Controller
@RequestMapping("pboc/image")
public class ImageController {

	private final static Logger logger = LoggerFactory.getLogger(SingleQueryController.class);

	public static final String CAMS_APPID_URL = AppConfig.getInstance().getValue("cams.image.get_appid_by_certno");

	public static final String CAMS_IMAGE_URL = AppConfig.getInstance().getValue("cams.image.get_image_by_appid");

	// 根据证件号查询申请件appId列表
	@RequestMapping("appid")
	@ResponseBody
	public String showView(String certNo) {
		Client client = null;
		try {
			client = new Client(new URL(CAMS_APPID_URL));
			client.setTimeout(120000);
			Object[] response = client.invoke("GetAppidByIDCard", new Object[] { certNo });
			String message = (String) response[0];
			logger.info("获取指定证件号码[{}]对应申请件编号[{}]", new Object[] { certNo, message });
			return message;
		} catch (Exception e) {
			logger.error("获取指定证件号码[{}]对应申请件编号异常[{}]", new Object[] {certNo, e.getMessage()}, e);
		}
		return "";
	}

	// 根据证件号查询申请件appId列表
	@RequestMapping("image")
	@ResponseBody
	public Object getImage(String appId)
			throws JsonGenerationException, org.codehaus.jackson.map.JsonMappingException, IOException {
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("result", false);
		Client client = null;
		try {
			client = new Client(new URL(CAMS_IMAGE_URL));
			client.setTimeout(120000);
			Object[] obj = new Object[] { appId };
			Object[] images = client.invoke("FindImageUrlByAppId", obj);
			logger.info("申请件影像信息[{}]", String.valueOf(images[0]));
			response.put("result", true);
			response.put("images", String.valueOf(images[0]));
		} catch (Exception e) {
			logger.error("获取申请件影像信息异常[{}]", new Object[] { e.getMessage() }, e);
		}
		return new ObjectMapper().writeValueAsString(response);
	}

}
