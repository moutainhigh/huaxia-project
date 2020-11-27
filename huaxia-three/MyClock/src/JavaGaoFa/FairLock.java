package JavaGaoFa;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 获取一些公平锁，锁的分配不是公平的，一般来说会随机分配给已经获得资源的线程
 * 公平锁是基于时间顺序的锁
 * @author liwei
 *
 */
public class FairLock implements Runnable {
	//公平锁
	public static ReentrantLock fairLock = new ReentrantLock(true);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				fairLock.lock();
				System.out.println(Thread.currentThread().getName()+" 获得锁 ");
			}finally{
				fairLock.unlock();
			}
		}
	}
	public static void main(String args[]){
		FairLock r1 =new FairLock();
		Thread t1= new Thread(r1,"Thread_t1");
		Thread t2 =new Thread(r1,"Thread_t2");
		t1.start();
		t2.start();
	}
}
