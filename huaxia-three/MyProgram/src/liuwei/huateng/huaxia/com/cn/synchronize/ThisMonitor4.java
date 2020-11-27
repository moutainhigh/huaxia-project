/**
 * Title: ThisMonitor.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��9������1:32:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:ThisMonitor2019��12��9��
 * @comments: ��synchoronized���εķ�����ֻ��һ������ֻ�з��غ�����������ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��9������1:32:32
 */
public class ThisMonitor4 implements Runnable {
	/**
	 * @Title: method1
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��9������1:34:51
	 */
	public synchronized void method1(){
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
	 *@Date: 2019��12��9������1:34:57
	 */
	public void method2(){
		synchronized(this){
			System.out.println(Thread.currentThread().getName()+" enter to methods");
			try {
				TimeUnit.MINUTES.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * 
	 */
	public ThisMonitor4() {
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
	 *@Date: 2019��12��9������1:32:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThisMonitor4 thisMonitor = new ThisMonitor4();
		new Thread(thisMonitor,"t1").start();
		new Thread(thisMonitor,"t2").start();
	}

}
