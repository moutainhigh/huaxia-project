/**
 * Title: TicketWindowRunnable.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2019年12月9日上午10:13:23
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package liuwei.huateng.huaxia.com.cn.synchronize;

/**
 * @class_name:TicketWindowRunnable2019年12月9日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2019年12月9日上午10:13:23
 */
public class TicketWindowRunnable4 implements Runnable {

	private static String index ="1";
	private final static int MAX = 500;
	private final static Object MUTEX = new Object();
	/**
	 * 
	 */
	public TicketWindowRunnable4() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(index){
			while(Integer.parseInt(index) < MAX){
				System.out.println(Thread.currentThread()+" 的号码是："+(index = (Integer.parseInt(index)+1)+"")	);
		}
		}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2019年12月9日上午10:13:23
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final TicketWindowRunnable4 task = new TicketWindowRunnable4();
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
