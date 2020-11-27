/**
 * Title: Test.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午5:08:30
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @class_name:Test2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午5:08:30
 */
public class Test {
	
	/**
	 * 
	 */
	public Test() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月16日下午5:08:30
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//流水线上有5个工人
		final ProductionChannel channel = new ProductionChannel(5);
		final AtomicInteger productionNo= new AtomicInteger();
		//增加8个工人生产
		for(int i=0;i<8;i++){
			new Thread(new Runnable(){
				@Override
				public void run(){
					while(true){
						channel.offerProduction(new Production(productionNo.getAndIncrement()));
						try {
							TimeUnit.SECONDS.sleep(new Random().nextInt(10));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}).start();
		}
	}

}
