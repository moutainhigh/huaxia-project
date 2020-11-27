package com.huaxia.opas.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

public class ServerStart {

	private static Logger logger = LoggerFactory.getLogger(ServerStart.class);

	private static ApplicationContext applicationContext;

	public static Object getBean(String beanId) {
		return applicationContext.getBean(beanId);
	}

	public static void main(String[] args) throws Exception {
		Thread.currentThread().setName("DAO");
		long start = System.currentTimeMillis();
		try {
			// [第1步] 初始化应用上下文

			// 加载 Spring 配置 及 dubbo 配置
			Bootstrap boot = new Bootstrap();
			boot.setContextConfigLocation("config/dao-config.xml,config/dao-service.xml,config/model-service.xml,config/dubbo-service.xml,config/dubbo-reference.xml,config/opas-timer.xml");
			boot.init();

			applicationContext = boot.getApplicationContext();

			// [第2步] 加载配置表缓存

			// log.error("init ComNodeDefinitionService cache");

			// ComNodeDefinitionService dao = (ComNodeDefinitionService)
			// getBean("comNodeDefinitionService");
			// dao.initCache();

			// log.error("init ETLService cache");
			//
			// ETLService etl = (ETLService) getBean("etlServiceImpl");
			// etl.startInitCache();
			
			logger.info("\n*********************************************************");
			logger.info("\n***** [DAO] Atomic Server start success!");
			logger.info("\n***** [DAO] Start Server cost [" + (System.currentTimeMillis() - start) + "] ms!");
			logger.info("\n*********************************************************");

			synchronized (ServerStart.class) {
				ServerStart.class.wait();
			}
		} catch (Exception e) {
			logger.error("\n*********************************************************");
			logger.error("\n***** [DAO] Atomic Server start error!");
			logger.error("\n*********************************************************");
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
