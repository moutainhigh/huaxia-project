package com.huaxia.opas.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @Description:    url配置方式解析
 * Copyright: Copyright (c) 2016  版权信息
 * Company: HUATENG  公司信息
 * @Author zhang.xinchun
 * @Version 1.0    版本信息
 * Created at 2016-4-28 下午7:34:46  创建日期
 * Modified by XXX at yyyy-mm-dd 修改信息  
 */
public class FilePathUtil {

	private static Properties pathProperties = null;
	public static final String URL_CONFIG_FILENAME = "/config/properties/host.properties";
	/**
	 * 
	* @Description: 根据classpath:config/properties/host.properties中key获取url
	* @author zhang.xinchun
	* @param key
	* @return
	* @throws IOException    参数
	* @return String    返回类型 
	 */
	public static String getFilePath(String key) throws IOException{
		reloadConfig();
		String filePath = pathProperties.getProperty(key);
		if(filePath != null){
			return filePath;
		}
		return "";
	}
	
	
	private static void reloadConfig() throws IOException {
		// 加载配置文件
		Properties prop = PropertiesUtil.readProperties(FilePathUtil.class, URL_CONFIG_FILENAME);
		// 如果加载完成则，替换总体配置文件。
		pathProperties = prop;
	}
	public static void main(String[] args) throws IOException {
		String url = FilePathUtil.getFilePath("COM_SEARCH_EXPORT_FILE_PATH");
		System.out.println(url);
	}

	
}
