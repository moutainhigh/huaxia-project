/**
 * Title: VolatileTest.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��5������5:21:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

import java.util.concurrent.CountDownLatch;

/**
 * @class_name:VolatileTest2019��12��5��
 * @comments: volatile ����֤ԭ���ԣ���֤�ɼ���
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��5������5:21:20
 */
public class VolatileTest {

	private static volatile int i=0;
	/**
	 * 
	 */
	public VolatileTest() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��5������5:21:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<10;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					for(int x =0;x<1000;x++){
						inc();
					}
				}
			}).start();
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
	}
	/**
	 * @Title: inc
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019��12��5������5:28:51
	 */
	private static void inc(){
		i++;
	}
}
