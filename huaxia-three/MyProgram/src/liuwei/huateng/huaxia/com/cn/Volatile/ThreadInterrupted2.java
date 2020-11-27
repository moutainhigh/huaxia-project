/**
 * Title: ThreadInterrupted2.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月6日下午4:50:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:ThreadInterrupted22019年12月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月6日下午4:50:26
 */
public class ThreadInterrupted2 {

	/**
	 * 
	 */
	public ThreadInterrupted2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月6日下午4:50:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new Thread()
				{
			@Override
			public void run(){
				while(true){
					System.out.println(Thread.interrupted());
				}
			}
				};
				
				thread.setDaemon(true);
				thread.start();
				try {
					TimeUnit.MILLISECONDS.sleep(2);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				thread.interrupt();
	}

}
