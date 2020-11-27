/**
 * Title: ThreadInterrupt.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月6日下午3:50:00
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:ThreadInterrupt2019年12月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月6日下午3:50:00
 */
public class ThreadInterrupt {

	/**
	 * 
	 */
	public ThreadInterrupt() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月6日下午3:50:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread =new Thread(new Runnable(){
			@Override
			public void run(){
				try{
					TimeUnit.MINUTES.sleep(1);
				}catch(InterruptedException e){
					System.out.println("oh,i am be interrupted.");
				}
			}
		});
		thread.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		thread.interrupt();
	}

}
