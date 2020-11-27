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
				System.out.println(Thread.currentThread()+" �ĺ����ǣ�"+(index = (Integer.parseInt(index)+1)+"")	);
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
		final TicketWindowRunnable4 task = new TicketWindowRunnable4();
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