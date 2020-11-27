package JavaGaoFa;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 信号量机制实现线程同步
 * 其他线程等待
 * 一次只能获取五个线程
 * @author liuwei
 *
 */
public class SemapDemo implements Runnable{
	final Semaphore semp = new Semaphore(5);
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			semp.acquire();
			//模拟耗时动作
			Thread.sleep(2000);
//			System.out.println(Thread.currentThread().getId()+":done");
			System.out.println(Thread.currentThread().getName()+":done");
			semp.release();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		ExecutorService exec =Executors.newFixedThreadPool(20);
		final SemapDemo demo =new SemapDemo();
		for(int i=0;i<20;i++){
			exec.submit(demo);
		}
	}
}
