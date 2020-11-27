/**
 * Title: MyTest.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月16日下午3:52:29
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.TwoPhaseTermination;

import java.io.IOException;

/**
 * @class_name:MyTest2019年12月16日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月16日下午3:52:29
 */
public class MyTest extends Thread {
	@Override
	public void run(){
		try {
			this.chat();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{//释放资源
			this.release();
		}
	}
	/**
	 * @Title: release
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2019年12月16日下午3:56:36
	 */
	private void release(){
		System.out.println(Thread.currentThread().getName()+" relaese");
	}
	/**
	 * @Title: chat
	 *@Description: TODO
	 *@throws IOException
	 *@author: LiuWei
	 *@Date: 2019年12月16日下午3:55:07
	 */
	private void chat() throws IOException
	{
		System.out.println(Thread.currentThread().getName()+"Hello World！");
	}
	/**
	 * 
	 */
	public MyTest() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public MyTest(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public MyTest(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MyTest(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MyTest(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public MyTest(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public MyTest(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public MyTest(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月16日下午3:52:29
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<5;i++)
		{
			new Thread(new MyTest(),i+"").start();
		}
	}

}
