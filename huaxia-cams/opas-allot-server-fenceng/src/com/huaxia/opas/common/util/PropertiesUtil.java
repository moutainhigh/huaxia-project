package com.huaxia.opas.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 配置文件实用类
 * @author pccc
 */
public class PropertiesUtil {
	
	/**
	 * 读取属性文件
	 * @param clazz 
	 * @param propertiesFileName
	 * @return
	 * @throws IOException
	 */
	public static Properties readProperties(Class clazz, String propertiesFileName) throws IOException {
		return readProperties(null, clazz, propertiesFileName);
	}
	
	
	/**
	 * 读取属性文件
	 * @param prop
	 * @param clazz
	 * @param propertiesFileName
	 * @return
	 * @throws IOException
	 */
	public static Properties readProperties(Properties prop, Class clazz, String propertiesFileName) throws IOException {
		if (prop==null) {
			prop = new Properties();
		}
		InputStream in = null;
		try {
			in = clazz.getResourceAsStream(propertiesFileName);
			prop.load(in);
		} finally {
			IOUtil.close(in);
		}
		return prop;
	}
	
}
