/**
 * Title: ProgramerTravel.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月12日下午3:41:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.Latch;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @class_name:ProgramerTravel2019年12月12日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月12日下午3:41:22
 */
public class ProgramerTravel extends Thread {

	//门阀
	private    Latch latch;
	//程序员
	private   String programmer;
	//交通工具
	private   String transportation;
	
	public ProgramerTravel(Latch latch,String programmer,String transportation){
		this.latch = latch;
		this.programmer = programmer;
		this.transportation = transportation;
	}
	@Override
	public void run(){
		System.out.println(programmer+" start take the transportation ["+ transportation+" ]");
		try {
			TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(programmer+" arrived by "+ transportation);
		//完成任务使计数器减一
		latch.countDown();
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月12日下午3:41:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Latch latch = new CountDownLatch(4);
		new ProgramerTravel(latch,"Alex","Bus").start();
		new ProgramerTravel(latch,"Gavin","Walking").start();
		new ProgramerTravel(latch,"Jack","Subway").start();
		new ProgramerTravel(latch,"Dillon","Bicycle").start();
		try { 
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("== all of programmer arrived ==");
	}

}
