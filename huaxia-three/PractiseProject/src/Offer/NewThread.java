/**
 * Title: NewThread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月23日下午5:36:33
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:NewThread2020年9月23日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月23日下午5:36:33
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
	 *@Date: 2020年9月23日下午5:36:33
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		NewThread newThread = new NewThread();
		newThread.start();
	}
	
	/**
	 * 线程体
	 */
	public void run(){
		System.out.println("create a thread by extends Thread");
	}
}
