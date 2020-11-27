/**
 * Title: TestScheduledExecutoreService.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��24������10:20:41
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @class_name:TestScheduledExecutoreService2020��9��24��
 * @comments:
 * @param:��ʱִ�е��̳߳�
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��24������10:20:41
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
	 *@Date: 2020��9��24������10:20:41
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
		//����һ���ӳ�3����߳�
		scheduledThreadPool.schedule(new Runnable(){
			@Override
			public void run() {
				// TODO  Auto-generated method stub
				System.out.println("delay 3 seconds execu.");
			}
		}, 3, TimeUnit.SECONDS);
		//����һ���ӳ�1�벢��ÿ3��ִ��һ�ε��߳�
		scheduledThreadPool.scheduleAtFixedRate(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("delay 1 seconds,repeat execute every 3 seconds");
			}
		}, 1,3, TimeUnit.SECONDS);
	}
}
