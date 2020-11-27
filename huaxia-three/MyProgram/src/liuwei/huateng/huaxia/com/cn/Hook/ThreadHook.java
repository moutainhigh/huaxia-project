/**
 * Title: ThreadHook.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��11������2:24:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Hook;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:ThreadHook2019��12��11��
 * @comments:Hook
 * Hook�̻߳������������JVM�˳���ʱ��ͻ�����ִ��Hook�߳�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��11������2:24:23
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
	 *@Date: 2019��12��11������2:24:23
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
