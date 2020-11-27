/**
 * Title: VolatileTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午5:21:20
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Volatile;

import java.util.concurrent.CountDownLatch;

/**
 * @class_name:VolatileTest2019年12月5日
 * @comments: volatile 不保证原子性，保证可见性
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午5:21:20
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
	 *@Date: 2019年12月5日下午5:21:21
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
	 *@Date: 2019年12月5日下午5:28:51
	 */
	private static void inc(){
		i++;
	}
}
