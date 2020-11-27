package JavaGaoFa;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition 条件，进行锁的沉睡和唤醒动作
 * 一旦condition进行了await()那么相关的锁就会被释放
 * 内部锁synchronized和重入锁ReentrantLock
 * @author liwei
 *
 */
public class ReenterLockCondition implements Runnable {
	//公平锁
	public static ReentrantLock lock = new ReentrantLock(true);
	public static Condition condition = lock.newCondition();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			lock.lock();
			condition.await();
			System.out.println("Thread is going on");
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	public static void main(String args[]){
		ReenterLockCondition r1 =new ReenterLockCondition();
		Thread t1= new Thread(r1,"Thread_t1");
		t1.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lock.lock();
		condition.signal();
		lock.unlock();
	}
}
