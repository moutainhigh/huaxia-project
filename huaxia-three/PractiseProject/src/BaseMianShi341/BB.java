/**
 * Title: BB.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��22������4:13:38
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package BaseMianShi341;

/**
 * @class_name:BB2020��9��22��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��22������4:13:38
 */
public class BB extends Thread {
	int time;
	/**
	 * Constructor
	 */
	public BB(int t) {
		// TODO Auto-generated constructor stub
		time = t;
	}

	/**
	 * @param arg0Constructor
	 */
	public BB(Runnable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0Constructor
	 */
	public BB(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public BB(ThreadGroup arg0, Runnable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public BB(ThreadGroup arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1Constructor
	 */
	public BB(Runnable arg0, String arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2Constructor
	 */
	public BB(ThreadGroup arg0, Runnable arg1, String arg2) {
		super(arg0, arg1, arg2);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3Constructor
	 */
	public BB(ThreadGroup arg0, Runnable arg1, String arg2, long arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��22������4:13:38
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BB b1 = new BB(500);
		b1.setName("�߳�1");
		b1.start();
		BB b2 = new BB(200);
		b2.setName("�߳�2");
		b2.start();
		BB b3 = new BB(300);
		b3.setName("�߳�3");
		b3.start();
	}
	/**
	 * Thread��run����
	 */
	public void run(){
		for(int i=1;i<=5;i++){
			System.out.println(Thread.currentThread().getName()+" "+i+"��");
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(i == 5){
				System.out.println(Thread.currentThread().getName()+"�˳���");
			}
		}
	}
}
