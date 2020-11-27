package JavaMultiThreadProgramming.ReentrantLock.Condition.ConditionTest.test;

import java.util.concurrent.locks.ReentrantLock;

public class Service {
	private ReentrantLock lock = new ReentrantLock();
    public void serviceMethod1(){
    	try{
    		lock.lock();
    		System.out.println("ThreadName"+Thread.currentThread().getName()+"进入方法区") ;
    		Thread.sleep(Integer.MAX_VALUE);
    	}catch(InterruptedException e){
    		e.printStackTrace();
    	}finally{
    		lock.unlock();
    	}
    }
    public static  void main(String args[]){
    	  final Service service = new Service();
    	  Runnable runnable =new Runnable(){
    		  @Override
    		  public void run(){
    			  service.serviceMethod1();
    		  }
    	  };
    	  Thread[]  threadArray = new Thread[10];
    	  for(int i=0;i<10;i++){
    		  threadArray[i] = new Thread(runnable);
    	  }
    	  for(int i=0;i<10;i++){
    		  threadArray[i].start();
    	  }
    	  try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	  System.out.println("有线程数"+service.lock.getQueueLength()+"再等待获取锁");
    }
}
