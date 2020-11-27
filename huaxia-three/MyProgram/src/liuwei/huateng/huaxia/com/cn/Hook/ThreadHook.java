/**
 * Title: ThreadHook.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月11日下午2:24:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Hook;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:ThreadHook2019年12月11日
 * @comments:Hook
 * Hook线程会启动多个，在JVM退出的时候就会启动执行Hook线程
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月11日下午2:24:23
 */
public class ThreadHook {
	
	/**
	 * 
	 */
	public ThreadHook() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月11日下午2:24:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run(){
				System.out.println("The hook thread 1 runing.");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("The hook thread 1 will exit.");
			}
		});
		Runtime.getRuntime().addShutdownHook(new Thread(){
			@Override
			public void run(){
				System.out.println("The hook thread 2 runing.");
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("The hook thread 2 will exit.");
			}
		});
		System.out.println("The program will is stopping.");
	}

}
