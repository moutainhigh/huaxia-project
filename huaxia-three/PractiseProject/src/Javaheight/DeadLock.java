/**
 * Title: DeadLock.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��15������2:11:21
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:DeadLock2020��10��15��
 * @comments:��ѧ�ҳԷ����������ʵ��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��15������2:11:21
 */
public class DeadLock extends Thread {
	protected Object tool;
	static Object fork1 = new Object();
	static Object fork2 = new Object();
	/**
	 * Constructor
	 */
	public DeadLock() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Constructor
	 */
	public DeadLock(Object obj) {
		// TODO Auto-generated constructor stub
		this.tool = obj;
		if(tool == fork1){
			this.setName("��ѧ��A");
		}else if(tool == fork2){
			this.setName("��ѧ��B");
		}
	}
	/**
	 * @param arg0Constructor
	 */
	public DeadLock(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0Constructor
	 */
	public DeadLock(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public DeadLock(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public DeadLock(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public DeadLock(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2Constructor
	 */
	public DeadLock(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3Constructor
	 */
	public DeadLock(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��10��15������2:11:21
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DeadLock A = new DeadLock(fork1);
		DeadLock B = new DeadLock(fork2);
		A.start();
		B.start();
	}
	public void run(){
		if(tool == fork1){
			synchronized(fork1){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(fork2){
					System.out.println("��ѧ��A��ʼ�Է���");
				}
			}
		}
		if(tool == fork2){
			synchronized(fork2){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized(fork1){
					System.out.println("��ѧ��B��ʼ�Է���");
				}
			}
		}
	}
}
