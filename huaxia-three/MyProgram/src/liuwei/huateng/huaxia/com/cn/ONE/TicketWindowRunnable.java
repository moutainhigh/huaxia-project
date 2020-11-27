/**
 * Title: TicketWindowRunnable.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2019��12��5������3:25:43
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:TicketWindowRunnable2019��12��5��
 * @comments:ģ�����еĴ��������⡣
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2019��12��5������3:25:43
 */
public class TicketWindowRunnable implements Runnable {

	private int index = 1;
	
	private final static int MAX = 50;
	
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
			System.out.println(Thread.currentThread()+" �ĺ����ǣ�"+(index++));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019��12��5������3:25:43
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
