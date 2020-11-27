package JavaGaoFa;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行的线程
 * @author liuwei
 *
 */
public class ScheduleExecutorServiceDemo {
	public static void main(String args[]){
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(10);
		ses.scheduleAtFixedRate(new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis()/1000);
			}
			
		}, 0, 2, TimeUnit.SECONDS);
	}
}
