/**
 * Title: ThreadisInterrupted.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月6日下午3:59:11
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:ThreadisInterrupted2019年12月6日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月6日下午3:59:11
 */
public class ThreadisInterrupted {

	/**
	 * 
	 */
	public ThreadisInterrupted() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月6日下午3:59:11
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new Thread(new Runnable(){
			@Override
			public void run(){
				while(true){
					//do nothing ，justempty loop
				}
			}
		});
		thread.start();
		try {
			TimeUnit.MILLISECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());
		thread.interrupt();
		System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());
	}

}
