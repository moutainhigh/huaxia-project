/**
 * Title: ReenterLock.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月14日下午4:11:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @class_name:ReenterLock2020年10月14日
 * @comments:ReentrantLock 锁的机制
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月14日下午4:11:16
 */
public class ReenterLock implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();
	public static int i =0;
	/**
	 * Constructor
	 */
	public ReenterLock() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int j = 0;j<10000000;j++){
			lock.lock();
			try{
				i++;
			}finally{
				lock.unlock();
			}
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月14日下午4:11:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReenterLock tl = new ReenterLock();
		Thread t1 = new Thread(tl);
		Thread t2 = new Thread(tl);
		t1.start();
		t2.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tl.i);
	}

}
