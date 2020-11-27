package com.huaxia.opas.common.util;

import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* @Description:              
* Copyright: Copyright (c) 2016  
* Company: HUATENG  
* @Author uatqf990106   
* @Version 1.0    
* Created at 2016-4-16 下午5:27:13  
 */
public class InsUtil {
	private static final Logger log = LoggerFactory.getLogger(InsUtil.class);
	/**
	* @Description: 
	* @author uatqf990106
	* @version  V1.0
	* @param obj
	* @return  
	 */
	public static String getJson(Object obj) {
		ObjectMapper om = new ObjectMapper();
		StringWriter sw = new StringWriter();
		try {
			JsonGenerator jg = new JsonFactory().createJsonGenerator(sw);
			om.writeValue(jg, obj);
			jg.close();
		} catch (Exception e) {
			log.error("getJson:", e);
		}
		return sw.toString();
	}

	/**
	* @Description: 
	* @author uatqf990106
	* @version  V1.0
	* @param pojo
	* @return  
	 */
	public static Map<String, Object> beanToMap(Object pojo) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			PropertyUtilsBean bean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptor = bean.getPropertyDescriptors(pojo);
			for (int i = 0; i < descriptor.length; i++) {
				String name = descriptor[i].getName();
				if (!"class".equals(name)) {
					map.put(name, bean.getNestedProperty(pojo, name));
				}
			}
		} catch (Exception e) {
			log.error("beanToMap:", e);
		}
		return map;
	}
	
	/**
	* @Description: 
	* @author uatqf990106
	* @version  V1.0
	* @param map
	* @param classs
	* @return  
	 */
	public static  Object mapToBean(Map map,Class classs){
		Object obj=new Object();
		try {
			Set keySet=map.keySet();
			Iterator it=keySet.iterator();
			while(it.hasNext()){
				String key=(String) it.next();
				String value=(String) map.get(key);
				map.put(key, "'"+value+"'");
			}
			obj=JSONObject.toBean(JSONObject.fromObject(map.toString()),classs);	
		} catch (Exception e) {
			log.error("mapToBean:", e);
		}
		return obj;
	}
	
	/**
	* @Description: string的json转换为Map
	* @author uatqf990106
	* @version  V1.0
	* @param json
	* @return  
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map<String,Object> jsonToMap(String json) {
		ObjectMapper om = new ObjectMapper();
		Map<String,Object> map = new HashMap();
		try {
			map = (Map<String,Object>) om.readValue(json, Map.class);
		} catch (Exception e) {
			log.error("string的json转换为Map异常:", e);
		}
		return map;
	}
	
	/**
	* @Description: 判断字符是否含有中文
	* @author uatqf990106
	* @version  V1.0
	* @param c
	* @return  
	 */
	private static final boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	
	/**
	* @Description: 判断字符串是否含有中文
	* @author uatqf990106
	* @version  V1.0
	* @param strName
	* @return  
	 */
	public static final boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		
	}
}