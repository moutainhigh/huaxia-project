package liuwei.huateng.huaxia.com.cn.ThreadPerMessage;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TaskHandler implements Runnable {
	private  Request request;
	
	public TaskHandler(Request request) {
		// TODO Auto-generated constructor stub
		this.request = request;
;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Begin handle "+request);
		slowly();
		System.out.println("End handle "+request);
	}
	/**
	 * @Title: slowly
	 *@Description: TODO,模拟处理时间
	 *@author: LiuWei
	 *@Date: 2019年12月16日上午11:26:05
	 */
	private void slowly(){
		try {
//			System.out.println(Thread.currentThread().getName()+"start");
			TimeUnit.SECONDS.sleep(new Random().nextInt(10));
//			System.out.println(Thread.currentThread().getName()+"end");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
