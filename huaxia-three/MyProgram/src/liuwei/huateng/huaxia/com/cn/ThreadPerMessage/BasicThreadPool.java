/**
 * Title: BasicThreadPool.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午3:10:46
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.ThreadFactory;

import com.sun.corba.se.spi.orbutil.threadpool.NoSuchWorkQueueException;
import com.sun.corba.se.spi.orbutil.threadpool.ThreadPool;
import com.sun.corba.se.spi.orbutil.threadpool.WorkQueue;

/**
 * @class_name:BasicThreadPool2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午3:10:46
 */
public class BasicThreadPool extends Thread implements ThreadPool {
	//初始化线程数量
	private  int initSize;
	//线程池最大线程数量
	private int maxSize;
	//线程池核心线程数量
	private int coreSize;
	//当前活跃的线程数量
	private int activeCount;
	//创建线程所需的工厂
	private ThreadFactory threadFactory;
	//任务队列
	private  RunnableQueue runnableQueue;
	//线程池是否已经被shutdown
	private volatile boolean isShutdown = false;
	//工作线程队列
//	private Queue<ThreadTask> threadQueue = new ArrayDeque<>();
	
	/**
	 * 
	 */
	public BasicThreadPool() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public BasicThreadPool(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public BasicThreadPool(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BasicThreadPool(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BasicThreadPool(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public BasicThreadPool(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public BasicThreadPool(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public BasicThreadPool(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.io.Closeable#close()
	 */
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#averageWorkCompletionTime()
	 */
	@Override
	public long averageWorkCompletionTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#currentNumberOfThreads()
	 */
	@Override
	public int currentNumberOfThreads() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#currentProcessedCount()
	 */
	@Override
	public long currentProcessedCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#getAnyWorkQueue()
	 */
	@Override
	public WorkQueue getAnyWorkQueue() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#getWorkQueue(int)
	 */
	@Override
	public WorkQueue getWorkQueue(int arg0) throws NoSuchWorkQueueException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#idleTimeoutForThreads()
	 */
	@Override
	public long idleTimeoutForThreads() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#maximumNumberOfThreads()
	 */
	@Override
	public int maximumNumberOfThreads() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#minimumNumberOfThreads()
	 */
	@Override
	public int minimumNumberOfThreads() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#numberOfAvailableThreads()
	 */
	@Override
	public int numberOfAvailableThreads() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#numberOfBusyThreads()
	 */
	@Override
	public int numberOfBusyThreads() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.sun.corba.se.spi.orbutil.threadpool.ThreadPool#numberOfWorkQueues()
	 */
	@Override
	public int numberOfWorkQueues() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月16日下午3:10:46
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
