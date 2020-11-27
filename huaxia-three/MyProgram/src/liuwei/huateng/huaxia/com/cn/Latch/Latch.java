/**
 * Title: Latch.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月12日下午2:41:39
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Latch;

import java.nio.channels.InterruptedByTimeoutException;

/**
 * @class_name:Latch2019年12月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月12日下午2:41:39
 */
public abstract class Latch {

	protected int limit;
	public Latch(int limit) {
		// TODO Auto-generated constructor stub
		this.limit = limit;
	}
	//等待线程
	public abstract void await() throws InterruptedException;
	//计数器减一的方法
	public abstract void countDown();
	//获取当前还有多少个线程没有完成任务
	public abstract int getUnarrived();
}
