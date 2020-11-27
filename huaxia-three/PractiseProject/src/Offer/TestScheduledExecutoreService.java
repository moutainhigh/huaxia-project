/**
 * Title: TestScheduledExecutoreService.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月24日上午10:20:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @class_name:TestScheduledExecutoreService2020年9月24日
 * @comments:
 * @param:定时执行的线程池
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月24日上午10:20:41
 */
public class TestScheduledExecutoreService {
	
	/**
	 * Constructor
	 */
	public TestScheduledExecutoreService() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月24日上午10:20:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		//创建一个延迟3秒的线程
		scheduledThreadPool.schedule(new Runnable(){
			@Override
			public void run() {
				// TODO  Auto-generated method stub
				System.out.println("delay 3 seconds execu.");
			}
		}, 3, TimeUnit.SECONDS);
		//创建一个延迟1秒并且每3秒执行一次的线程
		scheduledThreadPool.scheduleAtFixedRate(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("delay 1 seconds,repeat execute every 3 seconds");
			}
		}, 1,3, TimeUnit.SECONDS);
	}
}
