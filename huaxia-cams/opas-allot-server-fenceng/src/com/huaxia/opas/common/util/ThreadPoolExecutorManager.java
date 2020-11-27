package com.huaxia.opas.common.util;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池管理器
 * @author uatym990190
 */
public class ThreadPoolExecutorManager {
	private int corePoolSize;
	private int maximumPoolSize;
	private long keepAliveTime;
	private int workQueueSize;
//	private ThreadFactory threadFactory;
	private ThreadPoolExecutor threadPoolExecutor = null;
	/**
	 * 单例获取ThreadPoolExecutor
	 * @return
	 */
	public ThreadPoolExecutor getThreadPoolExecutorBean(){
		if(threadPoolExecutor == null){
			threadPoolExecutor = new ThreadPoolExecutor(
					this.corePoolSize,		// 保留线程数
					this.maximumPoolSize,	// 最大线程数
					this.keepAliveTime,		// 空余保留时间
					TimeUnit.SECONDS,		// 时间单位
					new LinkedBlockingQueue<Runnable>(workQueueSize)			// 队列
//					this.threadFactory		// 线程工程
			);
		}
		return threadPoolExecutor;
	}
	
	public void disConnect(){
		if(threadPoolExecutor != null){
			threadPoolExecutor.shutdown();
		}
	}
	
	public int getCorePoolSize() {
		return corePoolSize;
	}
	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}
	public long getKeepAliveTime() {
		return keepAliveTime;
	}
	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}
	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}
	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
	}
//	public ThreadFactory getThreadFactory() {
//		return threadFactory;
//	}
//	public void setThreadFactory(ThreadFactory threadFactory) {
//		this.threadFactory = threadFactory;
//	}

	public int getWorkQueueSize() {
		return workQueueSize;
	}

	public void setWorkQueueSize(int workQueueSize) {
		this.workQueueSize = workQueueSize;
	}

}
