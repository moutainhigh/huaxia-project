/**
 * Title: DaemonDemo.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月13日上午10:52:32
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:DaemonDemo2020年10月13日
 * @comments: 守护线程，当所有的线程执行完之后结束
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月13日上午10:52:32
 */
public class DaemonDemo {

	/**
	 * Constructor
	 */
	public DaemonDemo() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月13日上午10:52:32
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t = new DaemonT();
		t.setDaemon(true);
		t.start();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @class_name:DaemonT2020年10月13日
	 * @comments: DaemonT 准备设置为守护线程
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年10月13日上午10:53:39
	 */
	public static class DaemonT extends Thread{
		public void run(){
			while(true){
				System.out.println("I am alive。");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
