package com.huaxia.opas.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.apache.axis.utils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class TransBean2Map {
	private static Logger logger = LoggerFactory.getLogger(BeanUtils.class);
	public static Map<String,Object> transBean2Map(Object obj){
		if(obj==null){
			return null;
		}
		Map<String,Object> map = new HashMap<String, Object>();
		try{
			BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				//过滤class属性
				if(!"class".equals(key)){
					Method getter = property.getReadMethod();
					Object value = getter.invoke(obj);
					map.put(key, value);
				}
			}
		}catch(Exception e){
			if (logger.isInfoEnabled()) {
				logger.info("transBean2Map error：{}", e);
			}
		}
		return map;
	}
}
