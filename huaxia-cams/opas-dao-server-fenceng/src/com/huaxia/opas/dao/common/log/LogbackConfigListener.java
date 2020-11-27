package com.huaxia.opas.dao.common.log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * logback配置监听器
 * @author Administrator
 *
 */
public class LogbackConfigListener implements ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
//		LogbackWebConfigurer.initLogging(event.getServletContext()); 
		// Only perform custom logback initialization in case of a config file.
		String location = event.getServletContext().getInitParameter(LogbackWebConfigurer.CONFIG_LOCATION_PARAM);
		if (location != null) {
			try {
				LogbackConfigurer.initLogging(location);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
//		LogbackWebConfigurer.shutdownLogging(event.getServletContext());
		
		LogbackConfigurer.shutdownLogging();
	}

}
