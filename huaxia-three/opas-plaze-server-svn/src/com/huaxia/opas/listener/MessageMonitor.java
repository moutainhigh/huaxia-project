package com.huaxia.opas.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huaxia.opas.common.AppConst;
import com.huaxia.opas.entity.ShareZone;
import com.huaxia.opas.service.plaze.Listener;

/**
 * 消息监控
 * 
 * @author zhiguo.li
 *
 */
public class MessageMonitor implements Listener, Runnable {

	private static final long serialVersionUID = 1937020334574589910L;

	private final static Logger logger = LoggerFactory.getLogger(MessageMonitor.class);

	// 数据共享区
	private static ShareZone shareZone;
	
	public MessageMonitor() {}
	
	public MessageMonitor(ShareZone shareZone) {
		MessageMonitor.shareZone = shareZone;
	}

	@Override
	public void monitor(String message) throws Exception {
		if (message == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("[消息监控] 暂未收到任何消息!");
			}
			Thread.sleep(AppConst.THREAD_SLEEP_MILLIS * 3);
			return;
		}

		// 将消息添加到任务队列中
		shareZone.getQueue().offer(message);

		if (logger.isDebugEnabled()) {
			logger.debug("[消息监控] 新添加消息{}到队列中!", message);
		}
		
		Thread.sleep(AppConst.THREAD_SLEEP_MILLIS * 2/3);
	}

	@Override
	public void run() {
		if (logger.isDebugEnabled()) {
			logger.debug("[消息监控] 任务消息接收线程已启动");
		}
	}

}
