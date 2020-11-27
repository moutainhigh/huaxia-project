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
 * @comments:catch 中会把中断标志清空
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月6日下午3:59:11
 */
public class ThreadisInterrupted2 {

	/**
	 * 
	 */
	public ThreadisInterrupted2() {
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
					try{
						TimeUnit.MINUTES.sleep(1);
					}catch(InterruptedException e){
						System.out.printf(" i am be interrupted ?%s\n",this.toString());
					}
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
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.printf("Thread is interrupted ? %s\n",thread.isInterrupted());
	}

}
