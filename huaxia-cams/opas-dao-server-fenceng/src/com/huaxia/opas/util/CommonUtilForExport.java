package com.huaxia.opas.util;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.PropertyResourceConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.support.PropertiesLoaderSupport;

public class CommonUtilForExport {
	private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("config/opas-common.xml");
	/**
	 * 
	 * Properties工具 作者：wangkai
	 * 
	 * @version 2016-6-1
	 */
	public static Properties readProperties() throws IOException {

		if (applicationContext == null) {
			applicationContext = new ClassPathXmlApplicationContext("config/opas-common.xml");
		}
		AbstractApplicationContext abstractContext = (AbstractApplicationContext) applicationContext;
		Properties properties = new Properties();

		try {
			String[] postProcessorNames = abstractContext.getBeanNamesForType(
					BeanFactoryPostProcessor.class, true, true);

			for (String ppName : postProcessorNames) {
				BeanFactoryPostProcessor beanProcessor = abstractContext
						.getBean(ppName, BeanFactoryPostProcessor.class);
				if (beanProcessor instanceof PropertyResourceConfigurer) {
					PropertyResourceConfigurer propertyResourceConfigurer = (PropertyResourceConfigurer) beanProcessor;
					Method mergeProperties = PropertiesLoaderSupport.class
							.getDeclaredMethod("mergeProperties");
					// get the props
					mergeProperties.setAccessible(true);
					Properties props = (Properties) mergeProperties
							.invoke(propertyResourceConfigurer);
					Method convertProperties = PropertyResourceConfigurer.class
							.getDeclaredMethod("convertProperties",
									Properties.class);
					convertProperties.setAccessible(true);
					convertProperties.invoke(propertyResourceConfigurer, props);

					properties.putAll(props);
				}
			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return properties;
	}
}
