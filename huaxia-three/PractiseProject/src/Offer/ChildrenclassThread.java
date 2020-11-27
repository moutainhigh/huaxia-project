/**
 * Title: ChildrenclassThread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月24日上午9:48:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:ChildrenclassThread2020年9月24日
 * @comments:通过Runnable接口实现线程
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月24日上午9:48:19
 */
public class ChildrenclassThread implements Runnable {

	/**
	 * Constructor
	 */
	public ChildrenclassThread() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("create a thread by implements Runnable ");
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月24日上午9:48:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChildrenclassThread childrenThread = new ChildrenclassThread();
		Thread thread = new Thread(childrenThread);
		thread.start();
	}
}
