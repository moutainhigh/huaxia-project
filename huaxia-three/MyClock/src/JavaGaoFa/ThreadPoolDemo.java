package JavaGaoFa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池的使用
 * 固定大小的线程池
 * @author liuwei
 *
 */
public class ThreadPoolDemo {
	public static class MyTask implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println(System.currentTimeMillis()+":Thread ID："+Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]){
		MyTask task =new MyTask();
		ExecutorService es = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++){
			es.submit(task);
		}
	}
}
