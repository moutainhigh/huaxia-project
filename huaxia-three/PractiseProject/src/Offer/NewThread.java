/**
 * Title: NewThread.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��23������5:36:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:NewThread2020��9��23��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��23������5:36:33
 */
public class NewThread extends Thread {

	/**
	 * Constructor
	 */
	public NewThread() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��9��23������5:36:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewThread newThread = new NewThread();
		newThread.start();
	}
	
	/**
	 * �߳���
	 */
	public void run(){
		System.out.println("create a thread by extends Thread");
	}
}
