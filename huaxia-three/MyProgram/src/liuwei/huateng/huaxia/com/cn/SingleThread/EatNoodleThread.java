/**
 * Title: EatNoodleThread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月13日上午10:33:18
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.SingleThread;

import java.util.concurrent.TimeUnit;

/**
 * @class_name:EatNoodleThread2019年12月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月13日上午10:33:18
 */
public class EatNoodleThread extends Thread {
	private  String name;
	//左手边的餐具
	private  Tableware leftTool;
	//右手边的餐具
	private  Tableware rightTool;
	public EatNoodleThread(String name,Tableware leftTool,Tableware rightTool){
		this.name = name;
		this.leftTool = leftTool;
		this.rightTool = rightTool;
	}
	@Override
	public void run(){
		while(true){
			this.eat();
		}
	}
	//吃面条的过程
	private void eat(){
		synchronized(leftTool)
		{
			System.out.println(name+" take up "+leftTool+"(left)");
			synchronized(rightTool){
				System.out.println(name+" take up "+rightTool+"(right)");
				System.out.println(name+" is eating now.");
				System.out.println(name+" put down "+rightTool+"(right)");
			}
			System.out.println(name+" put down "+leftTool+"(right)");
		}
	}
	/**
	 * 
	 */
	public EatNoodleThread() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public EatNoodleThread(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 */
	public EatNoodleThread(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EatNoodleThread(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EatNoodleThread(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public EatNoodleThread(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 */
	public EatNoodleThread(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public EatNoodleThread(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月13日上午10:33:18
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tableware fork = new Tableware("fork");
		Tableware knife = new Tableware("knife");
		new EatNoodleThread("A",fork,knife).start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new EatNoodleThread("B",knife,fork).start();
	}

}
