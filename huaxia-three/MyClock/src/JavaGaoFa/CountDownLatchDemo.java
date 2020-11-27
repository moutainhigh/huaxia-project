package JavaGaoFa;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *  倒计时器,CountDownLatch
 * @author liwuei
 *
 */
public class CountDownLatchDemo implements Runnable {
	static final CountDownLatch end = new CountDownLatch(10);
	static final CountDownLatchDemo demo= new CountDownLatchDemo();
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			//模拟检查任务
			Thread.sleep(new Random().nextInt(10)*1000);
			System.out.println("chech complete");
			end.countDown();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	public static void main(String args[]){
		ExecutorService exec =Executors.newFixedThreadPool(20);
		for(int i=0;i<10;i++){
			exec.submit(demo);
		}
		//等待检查
		try {
			//await方法当CountDownLatch的值为0时，才会往下执行
			end.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//发射火箭
		System.out.println("Fire");
		exec.shutdown();
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
		System.out.println("Hello World!");
	}
}
