/**
 * Title: TestExecutorService.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��24������10:06:07
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @class_name:TestExecutorService2020��9��24��
 * @comments: �̳߳ص�ʹ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��24������10:06:07
 */
public class TestExecutorService {

	/**
	 * Constructor
	 */
	public TestExecutorService() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��24������10:06:07
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		for(int i=0;i<10;i++){
			threadPool.execute(new Runnable(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println(Thread.currentThread().getName()+" is running");
				}
				
			});
		};
	}
}
