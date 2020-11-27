/**
 * Title: TicketWindowRunnable.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月5日下午3:25:43
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.ONE;

/**
 * @class_name:TicketWindowRunnable2019年12月5日
 * @comments:模拟银行的处理窗口问题。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月5日下午3:25:43
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
			System.out.println(Thread.currentThread()+" 的号码是："+(index++));
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
	 *@Date: 2019年12月5日下午3:25:43
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final TicketWindowRunnable task = new TicketWindowRunnable();
		Thread windowThread1 = new Thread(task,"一号窗口");
		Thread windowThread2 = new Thread(task,"二号窗口");
		Thread windowThread3 = new Thread(task,"三号窗口");
		Thread windowThread4 = new Thread(task,"四号窗口");
		windowThread1.start();
		windowThread2.start();
		windowThread3.start();
		windowThread4.start();
	}

}
