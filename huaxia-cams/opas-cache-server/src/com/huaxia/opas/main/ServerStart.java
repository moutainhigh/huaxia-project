package com.huaxia.opas.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerStart {

	private static Logger logger = LoggerFactory.getLogger(ServerStart.class);

	/**
	 * 服务器启动主方法
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Thread.currentThread().setName("CACHE");
		long start = System.currentTimeMillis();
		try {
			// 第一步：初始化应用上下文=========================================================
			Bootstrap boot = new Bootstrap();
			boot.setContextConfigLocation(
					"config/cache-config.xml,config/cache-service.xml,config/dubbo-config.xml,config/dubbo-reference.xml,config/dubbo-service.xml");

			boot.init();

			logger.info("\n*********************************************************");
			logger.info("\n***** [CACHE] Atomic Server start success!");
			logger.info("\n***** [CACHE] Start Server cost [" + (System.currentTimeMillis() - start) + "] ms!");
			logger.info("\n*********************************************************");

			// 让主线程一直等待
			synchronized (ServerStart.class) {
				ServerStart.class.wait();
			}
		} catch (Exception e) {
			logger.error("\n*********************************************************");
			logger.error("\n***** [CACHE] Atomic Server start error!");
			logger.error("\n*********************************************************");
			logger.error(e.getMessage(), e);
			throw e;
		}
	}
}
