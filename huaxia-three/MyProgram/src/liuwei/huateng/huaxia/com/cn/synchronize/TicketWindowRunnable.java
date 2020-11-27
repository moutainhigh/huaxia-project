/**
 * Title: TicketWindowRunnable.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��9������10:13:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

/**
 * @class_name:TicketWindowRunnable2019��12��9��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��9������10:13:23
 */
public class TicketWindowRunnable implements Runnable {

	private int index =1;
	private final static int MAX = 500;
	private final static Object MUTEX = new Object();
	/**
	 * 
	 */
	public TicketWindowRunnable() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
			while(index <= MAX){
				synchronized(MUTEX){
				System.out.println(Thread.currentThread()+" �ĺ����ǣ�"+(index++	));
			}
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��9������10:13:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final TicketWindowRunnable task = new TicketWindowRunnable();
		Thread windowThread1 = new Thread(task,"һ�Ŵ���");
		Thread windowThread2 = new Thread(task,"���Ŵ���");
		Thread windowThread3 = new Thread(task,"���Ŵ���");
		Thread windowThread4 = new Thread(task,"�ĺŴ���");
		windowThread1.start();
		windowThread2.start();
		windowThread3.start();
		windowThread4.start();
	}

}
