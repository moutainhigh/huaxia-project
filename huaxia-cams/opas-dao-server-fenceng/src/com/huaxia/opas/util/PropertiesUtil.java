package com.huaxia.opas.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 配置文件实用类
 * @author pccc
 */
public class PropertiesUtil {
	
	private static final Logger logger=LoggerFactory.getLogger(PropertiesUtil.class);
	private static Properties props;
	private static Properties propsYdj;
	static{
		loadProps();
	}
	synchronized static private void loadProps(){
		props=new Properties();
		propsYdj=new Properties();
		InputStream in=null;
		InputStream in2=null;
		try {
			in=PropertiesUtil.class.getResourceAsStream("/config/properties/CreditResultKeyWord.properties");
			in2=PropertiesUtil.class.getResourceAsStream("/config/properties/YdjCreditResultKeyWord.properties");
			props.load(in);
			propsYdj.load(in2);
		} catch (Exception e) {
			logger.info("加载征信策略配置文件报错"+e.getMessage());
		}finally{
			try {
				if(in!=null){
					in.close();
				}
			} catch (Exception e2) {
				logger.info("关闭征信策略流报错"+e2.getMessage());
			}
			try {
				if(in2!=null){
					in2.close();
				}
			} catch (Exception e2) {
				logger.info("关闭征信策略流报错"+e2.getMessage());
			}
		}
	}
	public static String getProperty(String key,String flag){
		if(null==props||null==propsYdj){
			logger.info("开始加载征信策略配置文件");
			loadProps();
		}
		String reStr="";
		if("1".equals(flag)){//易达金
			reStr=propsYdj.getProperty(key)==null?"不存在该征信策略描述":propsYdj.getProperty(key);
		}else{//标准卡及易达金套卡
			reStr=props.getProperty(key)==null?"不存在该征信策略描述":props.getProperty(key);
		}
		return reStr;
	}
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
