package JavaGaoFa;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 死锁后中断事件发生，然后就会解锁，释放资源。线程结束
 * 中断后直接退出，执行接下来的程序
 * @author liuwei
 *
 */
public class IntLock implements Runnable{
	public static ReentrantLock lock1 = new ReentrantLock();
	public static ReentrantLock lock2 = new ReentrantLock();
	int lock;
	public IntLock(int lock){
		this.lock = lock;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			if(lock ==1){
				lock1.lockInterruptibly();
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				lock2.lockInterruptibly();
				System.out.println(Thread.currentThread().getName()+"：执行了");
			}else{
				lock2.lockInterruptibly();
				try{
					Thread.sleep(500);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				lock1.lockInterruptibly();
				System.out.println(Thread.currentThread().getName()+"：执行了");
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}finally{
			if(lock1.isHeldByCurrentThread())
				lock1.unlock();
			if(lock2.isHeldByCurrentThread())
				lock2.unlock();
//			System.out.println(Thread.currentThread().getId()+"：线程退出");
			System.out.println(Thread.currentThread().getName()+"：线程退出");
		}
	}
	public static void main(String args[]){
		IntLock r1 = new IntLock(1);
		IntLock r2 = new IntLock(2);
		Thread t1  = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.interrupt();
	}
}
