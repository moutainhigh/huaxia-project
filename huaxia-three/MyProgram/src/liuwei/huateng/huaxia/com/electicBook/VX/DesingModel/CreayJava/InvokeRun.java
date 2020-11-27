/**
 * Title: InvokeRun.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年1月22日上午11:08:52
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.electicBook.VX.DesingModel.CreayJava;

/**
 * @class_name:InvokeRun2020年1月22日
 * @comments: 重写run方法
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年1月22日上午11:08:52
 */
public class InvokeRun extends Thread {
	private int i;
	/**
	 * 重写run方法
	 */
	public void run()
	{
		for(;i<100;i++)
		{
			System.out.println(Thread.currentThread().getName() + " " +i);
		}
	}
	/**
	 * 
	 */
	public InvokeRun() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public InvokeRun(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public InvokeRun(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InvokeRun(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InvokeRun(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public InvokeRun(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public InvokeRun(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public InvokeRun(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年1月22日上午11:08:52
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<100;i++)
		{
			System.err.println(Thread.currentThread().getName()+" "+i);
			if(i == 20)
			{
				new InvokeRun().run();
				new InvokeRun().run();
			}
		}
	}

}
