/**
 * Title: SynchronizedDefect.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��11������10:05:37
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:SynchronizedDefect2019��12��11��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��11������10:05:37
 */
public class SynchronizedDefect2 {
	/**
	 * @Title: syncMethod
	 *@Description: ����synchronized��ȱ��
	 *@author: LiuWei
	 *@Date: 2019��12��11������10:06:22
	 */
	public synchronized void syncMethod(){
		try {
			TimeUnit.HOURS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public SynchronizedDefect2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��11������10:05:37
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final SynchronizedDefect2 defect= new SynchronizedDefect2();
		Thread t1 = new Thread(new Runnable(){
			@Override
			public void run(){
		
					defect.syncMethod();
							}
		},"T1");
		t1.start();
		try {
			TimeUnit.MILLISECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread t2 = new Thread(new Runnable(){
			@Override
			public void run(){
					defect.syncMethod();
			}
		},"T");
		t2.start();
		try {
			TimeUnit.MILLISECONDS.sleep(2);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.interrupt();
		System.out.println(t1.isInterrupted());
		System.out.println(t1.getState());
		System.out.println(t2.isInterrupted());
		System.out.println(t2.getState());
		t1.interrupt();
		System.out.println(t1.isInterrupted());
		System.out.println(t1.getState());
		System.out.println(t2.isInterrupted());
		System.out.println(t2.getState());
	}

}
