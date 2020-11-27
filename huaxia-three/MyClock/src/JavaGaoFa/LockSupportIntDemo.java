package JavaGaoFa;

import java.util.concurrent.locks.LockSupport;

/**
 * 测试线程阻塞类
 * @author liuwei
 *
 */
public class LockSupportIntDemo {
	public static Object  u= new Object();
	static ChangeObjectThread t1 =new ChangeObjectThread("t1");
	static ChangeObjectThread t2 =new ChangeObjectThread("t2");
	public static class ChangeObjectThread extends Thread{
		public ChangeObjectThread(String name){
			super.setName(name);
		}
		@Override
		public void run(){
		 synchronized(u){
			 System.out.println("in "+getName());
			 LockSupport.park();
			 if(Thread.interrupted()){
				 System.out.println("被中断了");
			 }
			 System.out.println("out "+getName());
		 }
		}
	}
	public static void main(String args[]){
		t1.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();
		t1.interrupt();
//		LockSupport.unpark(t1);
		LockSupport.unpark(t2);
	}
}
