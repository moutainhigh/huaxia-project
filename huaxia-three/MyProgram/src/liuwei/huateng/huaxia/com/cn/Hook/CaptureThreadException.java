/**
 * Title: CaptureThreadException.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月11日下午2:10:01
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Hook;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.TimeUnit;

/**
 * @class_name:CaptureThreadException2019年12月11日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月11日下午2:10:01
 */
public class CaptureThreadException {

	/**
	 * 
	 */
	public CaptureThreadException() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月11日下午2:10:01
	 */
	public static void main(String[] args) {
		// 重写了异常的方法，uncaughtException，线程出现异常时候，会调用改方法，重写即可覆盖
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler(){
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				// TODO Auto-generated method stub
				System.out.println(t.getName()+" occure exception");
				e.printStackTrace();
			}
		});
		final Thread thread = new Thread(new Runnable(){
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(1/0);
			}	
		},"Test-Thread");
		thread.start();
	}

}
