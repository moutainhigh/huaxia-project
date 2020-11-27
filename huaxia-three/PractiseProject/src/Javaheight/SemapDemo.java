/**
 * Title: SemapDemo.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��14������4:35:03
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @class_name:SemapDemo2020��10��14��
 * @comments:�ź������ƣ�һ��ֻ�ܷ��ʸ������޵��߳�ִ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��14������4:35:03
 */
public class SemapDemo implements Runnable{
	final Semaphore semp = new Semaphore(5);
	/**
	 * Constructor
	 */
	public SemapDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��10��14������4:35:03
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService exec = Executors.newFixedThreadPool(20);
		final SemapDemo  demo = new SemapDemo();
		for(int i=0;i<20;i++){
			exec.submit(demo);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			semp.acquire();
			Thread.sleep(2000);
			System.out.println(Thread.currentThread().getId()+":done");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			semp.release();
		}
	}

}
