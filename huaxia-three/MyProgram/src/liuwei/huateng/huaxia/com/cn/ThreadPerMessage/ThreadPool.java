/**
 * Title: ThreadPool.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午3:05:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

/**
 * @class_name:ThreadPool2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午3:05:44
 */
public interface ThreadPool {
	//提交任务到线程池
	void execute(Runnable runnable);
	//关闭线程池
	void shutdown();
	//获取线程池的初始化大小
	int getInitSize();
	//获取线程池的最大线程数量
	int getMaxSize();
	//获取线程池的核心线程数量
	int getCoreSize();
	//获取线程池中用于缓存任务队列的大小
	int getQueueSize();
	//获取线程池中活跃线程的数量
	int getActiveCount();
	//查询线程池是否已经被关闭
	boolean isShutdown();
}
