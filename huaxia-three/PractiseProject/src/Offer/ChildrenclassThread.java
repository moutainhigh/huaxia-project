/**
 * Title: ChildrenclassThread.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��9��24������9:48:19
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Offer;

/**
 * @class_name:ChildrenclassThread2020��9��24��
 * @comments:ͨ��Runnable�ӿ�ʵ���߳�
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��9��24������9:48:19
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
	 *@Date: 2020��9��24������9:48:19
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChildrenclassThread childrenThread = new ChildrenclassThread();
		Thread thread = new Thread(childrenThread);
		thread.start();
	}
}
