/**
 * Title: Work.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��16������4:25:45
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.WorkerThread;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @class_name:Work2019��12��16��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��16������4:25:45
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
				//�Ӵ��ʹ����������ȡ��Ʒ
				Production production = channel.takeProduction();
				System.out.println(Thread.currentThread().getName()+" process the"+production);
				//�Բ�Ʒ���мӹ�
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
	 *@Date: 2019��12��16������4:25:45
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
