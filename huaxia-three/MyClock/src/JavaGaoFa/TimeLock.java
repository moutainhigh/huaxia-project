package JavaGaoFa;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 限时申请锁
 * 给获取锁的时间有个限制，例如五秒获取不到锁就不进行申请了
 * @author liuwei
 *
 */
public class TimeLock implements Runnable{
	public static ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			if(lock.tryLock(5,TimeUnit.SECONDS)){
				Thread.sleep(6000);
			}else{
				System.out.println("get lock failed");
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			if(lock.isHeldByCurrentThread())
				lock.unlock();
		}
	}
	public static void main(String args[]){
		TimeLock tl = new TimeLock();
		Thread t1 = new Thread(tl);
		Thread t2 = new Thread(tl);
		t1.start();
		t2.start();
	}
	
}
