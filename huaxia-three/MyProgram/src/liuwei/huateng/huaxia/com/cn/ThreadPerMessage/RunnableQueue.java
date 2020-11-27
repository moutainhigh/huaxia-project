/**
 * Title: RunnableQueue.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午3:17:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

/**
 * @class_name:RunnableQueue2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午3:17:07
 */
public interface RunnableQueue {
	//当有新的任务进来时，首先进入offer队列中
	void offer(Runnable runnable);
	//工作线程通过take方法获取Runnable
	Runnable take();
	//获取任务队列中任务的数量
	int size();
}
