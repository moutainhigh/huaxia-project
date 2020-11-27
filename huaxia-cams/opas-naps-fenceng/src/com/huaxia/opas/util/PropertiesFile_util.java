package com.huaxia.opas.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;



public class PropertiesFile_util {
	 private static Logger LOGGER = LogManager.getLogger(PropertiesFile_util.class);
	
	private static Properties pro= new Properties();
	public  static Properties getPropertiesObject(){

		
		/*InputStream resourceAsStream = PropertiesFile_util.class.getClassLoader().getResourceAsStream(filename);
		try {
			pro.load(resourceAsStream);
		} catch (FileNotFoundException e1) {
			LOGGER.error("=======读取配置文件错误！！！=======");
			e1.printStackTrace();
		} catch (Exception e) {
			LOGGER.error("=======读取配置文件错误！！！=======");
			e.printStackTrace();
		}*/
		InputStream resourceAsStream;
		try {
			resourceAsStream = new BufferedInputStream(new FileInputStream(new File("/app/opas/conf/host.properties")));
//			resourceAsStream = new BufferedInputStream(new FileInputStream(new File("D:/HUAXIA/huaxia-configuration/host.properties")));
			//file:D:/HUAXIA/huaxia-configuration/host.properties"
			try {
				pro.load(resourceAsStream);
			} catch (IOException e) {
				//LOGGER.error("=======读取配置文件错误！！！=======");
				e.printStackTrace();
			}
		} catch (FileNotFoundException e2) {
			//LOGGER.error("=======读取配置文件错误！！！=======");
			e2.printStackTrace();
		}
		return pro;
	}
	
}
