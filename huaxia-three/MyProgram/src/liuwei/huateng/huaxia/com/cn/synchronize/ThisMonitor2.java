/**
 * Title: ThisMonitor.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月9日下午1:32:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:ThisMonitor2019年12月9日
 * @comments: 没有synchronized关键字，两个方法同时执行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月9日下午1:32:32
 */
public class ThisMonitor2 implements Runnable {
	/**
	 * @Title: method1
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年12月9日下午1:34:51
	 */
	public void method1(){
		System.out.println(Thread.currentThread().getName()+" enter to method1");
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * @Title: method2
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年12月9日下午1:34:57
	 */
	public void method2(){
		System.out.println(Thread.currentThread().getName()+" enter to methods");
		try {
			TimeUnit.MINUTES.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public ThisMonitor2() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(Thread.currentThread().getName().equals("t1")){
			method1();
		}else if(Thread.currentThread().getName().equals("t2")){
			method2();
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月9日下午1:32:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThisMonitor2 thisMonitor = new ThisMonitor2();
		new Thread(thisMonitor,"t1").start();
		new Thread(thisMonitor,"t2").start();
	}

}
