/**
 * Title: ThreadState.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日下午4:50:16
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:ThreadState2020年9月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日下午4:50:16
 */
public class ThreadState implements Runnable {
	/**
	 * @Title: waitForASecond
	 *@Description: TODO
	 *@throws InterruptedException
	 *@author: LiuWei
	 *@Date: 2020年9月21日下午4:51:55
	 */
	public synchronized void waitForASecond() throws InterruptedException{
		wait(500);
	}
	/**
	 * @Title: waitForYears
	 *@Description: TODO
	 *@throws InterruptedException
	 *@author: LiuWei
	 *@Date: 2020年9月21日下午4:53:29
	 */
	public synchronized void waitForYears() throws InterruptedException{
		wait();//线程永久等待，直到其他线程调用notify或notifyAll方法
	}
	/**
	 * @Title: notifyNow
	 *@Description: TODO
	 *@throws InterruptedException
	 *@author: LiuWei
	 *@Date: 2020年9月21日下午4:54:15
	 */
	public synchronized void notifyNow() throws InterruptedException{
		notify();
	}
	/**
	 * Constructor
	 */
	public ThreadState() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月21日下午4:50:16
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try{
			waitForASecond();
			waitForYears();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
