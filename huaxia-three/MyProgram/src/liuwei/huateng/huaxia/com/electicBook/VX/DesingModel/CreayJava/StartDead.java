/**
 * Title: StartDead.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月22日上午11:17:54
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CreayJava;

/**
 * @class_name:StartDead2020年1月22日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月22日上午11:17:54
 */
public class StartDead extends Thread {
	private int i;
	/**
	 * 重写run方法
	 */
	public void run()
	{
		for(;i<100;i++)
		{
			System.out.println(getName()+ " "+i);
		}
	}
	/**
	 * 
	 */
	public StartDead() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public StartDead(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public StartDead(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public StartDead(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public StartDead(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public StartDead(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public StartDead(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public StartDead(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月22日上午11:17:54
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//创建线程对象
		StartDead sd =new StartDead();
		for(int i=0;i<300;i++)
		{
			System.out.println(Thread.currentThread().getName()+" "+i);
			if(i == 20)
			{
				sd.start();
				System.out.println(sd.isAlive());
			}
			//当线程处于新建、死亡两种状态时候，isAlive方法返回false
			//当i>20时候，该线程已经启动过了，如果sd.isAlive为假时候，那就是死亡状态
			//死亡后的线程不能再次启动
			if(i > 20 && !sd.isAlive())
			{
				//试图再次启动该线程
				sd.start();
			}
		}
	}
}
