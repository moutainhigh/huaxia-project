package JavaGaoFa;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 重入锁机制，另外一种加锁机制
 * ReentrantLock 方法不是用synchronized方法
 * @author liuwei
 *
 */
public class ReenterLock implements Runnable {
	public static ReentrantLock lock = new ReentrantLock();
	public static  Integer i=0;
	@Override
	public void run() {
		for(int j=0;j<10000000;j++){
			lock.lock();
			try{
				i++;
			}finally{
				lock.unlock();
			}
		}
	}
	public static void main(String args[]){
		ReenterLock tl = new ReenterLock();
		Thread t1 = new Thread(tl);
		Thread t2 = new Thread(tl);
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
	}

}
