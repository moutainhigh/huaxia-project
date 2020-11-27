package com.huaxia.opas.util;

import org.springframework.context.ApplicationContext;


public class ContextUtil {
	private static ApplicationContext context;
	
	public static void setApplicationContext(ApplicationContext context){
		ContextUtil.context = context;
	}
	/**
	 * get Spring bean
	 * @param beanId
	 * @return
	 */
	public static Object getBean(String beanId){
		return context.getBean(beanId);
	}
}
