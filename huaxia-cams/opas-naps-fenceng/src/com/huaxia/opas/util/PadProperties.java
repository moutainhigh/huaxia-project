package com.huaxia.opas.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *里面放置了调用Pad接口的路径，以及存量客户电销的文件的上传路径,调用影像的地址
 * @author wangtao
 *
 */
public class PadProperties {
private static final Logger logger = LoggerFactory.getLogger(PadProperties.class);
	
	private Properties property;
	
	private static Map<String, String> paroperMap = new HashMap<String, String>();

	public void init() {
		String padUrl = (String) property.get("PAD_URL");
		String stockPath = (String)property.get("STOCK_UPLOAD");
		String imageUrl = (String)property.get("IMAGE_URL");
		String imagePort = (String) property.get("IMAGE_PORT");
		if(padUrl!=null){
			paroperMap.put("padUrl", padUrl);
		}
		if(stockPath!=null){
			paroperMap.put("stockPath", stockPath);
		}
		if(imageUrl!=null){
			paroperMap.put("imageUrl", imageUrl);
		}
		if(imagePort!=null){
			paroperMap.put("imagePort", imagePort);
		}
		logger.info("调用pad接口的padUrl="+padUrl);
		logger.info("存量客户电销上传txt的路径stockPath="+stockPath);
		logger.info("调用影像的地址imageUrl="+imageUrl + "端口：" + imagePort);
	}

	public Properties getProperty() {
		return property;
	}

	public void setProperty(Properties property) {
		this.property = property;
	}

	public static Map<String, String> getParoperMap() {
		return paroperMap;
	}

	public static void setParoperMap(Map<String, String> paroperMap) {
		PadProperties.paroperMap = paroperMap;
	}
}
