package com.huaxia.opas.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderSupport;

/**
 * @author user
 */
public class CommonUtil {
	/**
	 * 判断多个字符串是否为空，返回第一个不为空的字符串
	 */
	public static String geNotNullString(String... strings) {
		String res = null;
		for (String str : strings) {
			if (str != null && !"".equals(str))
				return str;
		}
		return res;
	}
	/**
	 * 判断多个字符串是否为空，返回第一个不为空的字符串
	 */
	public static Map<String,Object> geNotNullMap(String... strings) {
		Map<String,Object> res = null;
		int num = 1;
		for (String str : strings) {
			if (str != null && !"".equals(str)){
				res = new HashMap<String,Object>();
				res.put("res", str);
				res.put("num", num);
				return res;
			}
			num += 1;
		}
		return res;
	}
	public static void main(String[] args) {
		System.out.println(getAnothorCardAppId("1704010623P50642","2"));
	}
	public static String getAnothorCardAppId(String appId,String tkFlag){
		char lastChar = appId.charAt(appId.length()-1);
		if(!"0".equals(tkFlag)){
			if("2".equals(lastChar+"")){
				return appId.substring(0, appId.length()-1)+"1";
			}else if("1".equals(lastChar+"")){
				return appId.substring(0, appId.length()-1)+"2";
			}
		}
		return null;
	}
	/**
	 * 判断两个集合中相同的键key的值是否相同，并返回他们【不相同】的键集合
	 * 
	 * @param map
	 * @param mubiaoMap
	 * @author xuzhiguo
	 */
	public static List<String> mapCompareToMap(Map<String, Object> map, Map<String, Object> mubiaoMap) {
		List<String> list = new ArrayList<String>();
		if (map == null) {
			return null;
		} else if (mubiaoMap == null) {
			list.addAll(map.keySet());
		} else {
			for (String str : map.keySet()) {
				if (map.get(str) != mubiaoMap.get(str) && map.get(str) != null && !"".equals(map.get(str)))
					list.add(str);
			}
		}
		return list;
	}

	/**
	 * 判断两个集合中相同的键key的值是否相同，并返回他们【不相同】的键集合
	 * 
	 * @param map
	 * @param mubiaoMap
	 * @author xuzhiguo
	 */
	public static List<String> mapCompareToMapStr(Map<String, String> map, Map<String, String> mubiaoMap) {
		List<String> list = new ArrayList<String>();
		if (map == null) {
			return null;
		} else if (mubiaoMap == null) {
			list.addAll(map.keySet());
		} else {
			for (String str : map.keySet()) {
				if (map.get(str) != mubiaoMap.get(str) && map.get(str) != null && !"".equals(map.get(str)))
					list.add(str);
			}
		}
		return list;
	}
}
