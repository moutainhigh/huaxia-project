/**
 * Title: ThreadPoolDemo.java
 * Description: �̶���С���̳߳�
 * @autor:��ΰ/liuwei
 * @date 2020��10��15������10:17:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 
 * @class_name:MyTask2020��10��15��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��15������10:21:03
 */
	class MyTask implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(System.currentTimeMillis()+":Thread ID:"+Thread.currentThread().getId());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

/**
 * @class_name:ThreadPoolDemo2020��10��15��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��15������10:17:19
 */
public class ThreadPoolDemo {

	/**
	 * Constructor
	 */
	public ThreadPoolDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��10��15������10:17:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyTask task = new MyTask();
		ExecutorService es = Executors.newFixedThreadPool(5);
		for(int i=0;i<10;i++){
			es.submit(task);
		ExecutorService ww= Executors.newFixedThreadPool(10);
	}
	}
}


