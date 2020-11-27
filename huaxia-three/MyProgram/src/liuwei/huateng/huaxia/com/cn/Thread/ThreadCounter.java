/**
 * Title: ThreadCounter.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月9日上午9:46:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @class_name:ThreadCounter2019年12月9日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月9日上午9:46:19
 */
public class ThreadCounter extends Thread {

	final static AtomicInteger counter = new AtomicInteger(0);
	/**
	 * 
	 */
	public ThreadCounter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public void run(){
		try{
			System.out.println("The"+ counter.getAndIncrement()+" Thread be created.");
			TimeUnit.MINUTES.sleep(10);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	/**
	 * @param arg0
	 */
	public ThreadCounter(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public ThreadCounter(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ThreadCounter(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ThreadCounter(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public ThreadCounter(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public ThreadCounter(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public ThreadCounter(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月9日上午9:46:20
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			while(true){
				new ThreadCounter().start();
			}
		}catch(Throwable e){
			System.out.println("failed at="+counter.get());
		}
	}

}
