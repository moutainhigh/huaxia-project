package ThinkInJava;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 使用锁机制，自己，锁机制，基本上也需要把return语句给锁上
 * @author User
 *
 */
public class MutexEvenGenerator implements Runnable{

	public MutexEvenGenerator() {
		// TODO Auto-generated constructor stub
	}
	private Lock lock = new ReentrantLock();
	private int currentEvenValue =0;
	public  int next(){
		lock.lock();
		try{
			++currentEvenValue;
			Thread.yield();
			return currentEvenValue;
		}finally{
			lock.unlock();
		}
	}
	public  int next2(){
		++currentEvenValue;
		Thread.yield();
		return currentEvenValue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(10);
				System.out.println(Thread.currentThread().getName()+"："+next());
//				Thread.sleep(1);
//				System.err.println(Thread.currentThread().getName()+"："+next2());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]){
		MutexEvenGenerator syn = new MutexEvenGenerator();
		Thread t1 = new Thread(syn);
		t1.start();
		Thread t2 = new Thread(syn);
		t2.start();
	}
}
