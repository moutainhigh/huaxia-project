package UnderstandingTheJVM;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 测试线程的死循环
 * 和线程锁等待演示
 * @author liuwei
 *
 */
public class TestThread {
	public static void createBusyThread(){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true);
			}
		},"testBusyThread");
		thread.start();
	}
	public static void createLockThread(final Object lock){
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
			 synchronized(lock){
				 try{
					 lock.wait();
				 }catch(InterruptedException e){
					 e.printStackTrace();
				 }
			 }
			}
		},"testLockdThread");
		thread.start();
	}
	public static void main(String args[]) throws Exception{
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		br.readLine();
		createBusyThread();
		br.readLine();
		Object obj = new Object();
		createLockThread(obj);
	}
}
