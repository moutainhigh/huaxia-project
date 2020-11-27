package com.huaxia.opas.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 公共查询导出
 * 
 * @author zhiguo.li
 *
 */
public class PublicSearchExport {
	
	private static final Logger logger = LoggerFactory.getLogger(PublicSearchExport.class);
	
	private Properties property;
	
	private static Map<String, String> paroperMap = new HashMap<String, String>();

	public void init() {
		String searchFilePath = (String) property.get("PUBLIC_SEARCH_EXPORT_PATH");

		if (searchFilePath != null) {
			paroperMap.put("PUBLIC_SEARCH_EXPORT_PATH", searchFilePath);
		}
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
		PublicSearchExport.paroperMap = paroperMap;
	}

}
