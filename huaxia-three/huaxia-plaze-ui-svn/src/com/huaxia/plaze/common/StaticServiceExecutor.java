package com.huaxia.plaze.common;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//	静态线程池
public class StaticServiceExecutor {

	public static ExecutorService EXECUTOR = Executors.newFixedThreadPool(200);

}
