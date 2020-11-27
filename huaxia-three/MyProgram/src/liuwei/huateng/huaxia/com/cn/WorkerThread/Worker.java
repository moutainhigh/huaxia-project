/**
 * Title: Work.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午4:25:45
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @class_name:Work2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午4:25:45
 */
public class Worker extends Thread {
	private  ProductionChannel channel;
	private final static Random random = new Random(System.currentTimeMillis());
	/**
	 * 
	 */
	public Worker(String workerName,ProductionChannel channel) {
		// TODO Auto-generated constructor stub
		super(workerName);
		this.channel = channel;
	}

	@Override
	public void run(){
		while(true){
			try{
				//从传送带撒花姑娘获取产品
				Production production = channel.takeProduction();
				System.out.println(Thread.currentThread().getName()+" process the"+production);
				//对产品进行加工
				production.create();
				TimeUnit.SECONDS.sleep(random.nextInt(10));
			}catch(InterruptedException e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * @param arg0
	 */
	public Worker(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public Worker(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public Worker(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public Worker(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public Worker(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public Worker(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public Worker(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月16日下午4:25:45
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
