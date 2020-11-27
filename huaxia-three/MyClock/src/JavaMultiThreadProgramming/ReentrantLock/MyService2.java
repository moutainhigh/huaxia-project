package JavaMultiThreadProgramming.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyService2 {
	private Lock lock = new ReentrantLock();
	public void testMethod(){
//		lock.lock();
		for(int i=0;i<5;i++){
			System.out.println("ThreadName="+Thread.currentThread().getName()+ (" "+(i+1)));
		}
//		lock.unlock();
	}
}
