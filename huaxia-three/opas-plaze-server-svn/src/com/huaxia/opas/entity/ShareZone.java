package com.huaxia.opas.entity;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 数据共享区
 * 
 * @author zhiguo.li
 *
 */
public final class ShareZone {

	// 任务队列
	private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();

	public LinkedBlockingQueue<String> getQueue() {
		return queue;
	}

	public void setQueue(LinkedBlockingQueue<String> queue) {
		this.queue = queue;
	}

}
