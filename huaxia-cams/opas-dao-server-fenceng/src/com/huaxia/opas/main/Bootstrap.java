package com.huaxia.opas.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.StringUtils;

public class Bootstrap {

	private static Logger log = LoggerFactory.getLogger(Bootstrap.class);

	/** 默认上下文类 */
	public static final Class DEFAULT_CONTEXT_CLASS = ClassPathXmlApplicationContext.class;

	/** 应用上下文 */
	private ApplicationContext applicationContext;

	/** 上下文CLASS类 */
	private Class contextClass;

	/** 上下文配置路径 */
	private String contextConfigLocation;

	public Bootstrap() {
		this.contextClass = DEFAULT_CONTEXT_CLASS;
	}

	/**
	 * 关闭Spring上下文
	 */
	public void close() {
		if (applicationContext instanceof ConfigurableApplicationContext) {
			((ConfigurableApplicationContext) applicationContext).close();
		}
	}

	/**
	 * 初始化
	 * 
	 * @throws BeansException
	 */
	public final void init() throws Exception {
		Runtime runtime = Runtime.getRuntime();

		// 注册应用关闭时的处理程序
		runtime.addShutdownHook(new Thread(new Runnable() {
			public void run() {
				log.error("shutdown application ....");
				close();
			}

		}, "detroy application context"));
		long l = System.currentTimeMillis();

		// 初始化Spring上下文
		if (log.isInfoEnabled()) {
			log.info("Spring applicationContext init start...");
		}
		try {
			applicationContext = initApplicationContext();
		} catch (Exception e) {
			log.error("Spring applicationContext init failed:", e);
			throw e;
		}

		if (log.isInfoEnabled()) {
			log.info("Spring applicationContext init succed");
			long l1 = System.currentTimeMillis() - l;
			log.info("init completed in " + l1 + " ms");
		}
	}

	/**
	 * 初始化应用上下文
	 * 
	 * @return
	 * @throws BeansException
	 */
	protected ApplicationContext initApplicationContext() throws BeansException {
		if (log.isInfoEnabled()) {
			log.info("tcp dispatcher will try to create custom ApplicationContext context of class '"
					+ contextClass.getName() + "'");
		}

		ClassPathXmlApplicationContext applicationContext = (ClassPathXmlApplicationContext) BeanUtils
				.instantiateClass(contextClass);
		applicationContext.setParent(null);

		// 设置上下文配置
		if (contextConfigLocation != null) {
			applicationContext
					.setConfigLocations(StringUtils.tokenizeToStringArray(contextConfigLocation, " ,;", true, true));
		}

		// 刷新上下文
		applicationContext.refresh();
		return applicationContext;
	}

	public void setContextClass(Class class1) {
		contextClass = class1;
	}

	public void setContextConfigLocation(String s) {
		contextConfigLocation = s;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void main(String args[]) throws Exception {
		Bootstrap bootstrap = new Bootstrap();
		bootstrap.setContextConfigLocation("/tcp-config.xml");
		bootstrap.init();
		System.exit(0);
	}

}
