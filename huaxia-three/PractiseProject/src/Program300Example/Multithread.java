/**
 * Title: Multithread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年9月21日下午4:48:47
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Program300Example;

/**
 * @class_name:Multithread2020年9月21日
 * @comments:线程的生命周期
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年9月21日下午4:48:47
 */
public class Multithread {

	/**
	 * Constructor
	 */
	public Multithread() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年9月21日下午4:48:47
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ThreadState state = new ThreadState();
		Thread thread = new Thread(state);
		System.out.println("新建线程："+thread.getState());
		thread.start();
		System.out.println("启动线程："+thread.getState());
		Thread.sleep(100);
		System.out.println("计时等待："+thread.getState());
		Thread.sleep(1000);
		System.out.println("等待线程："+thread.getState());
		state.notifyNow();
		System.out.println("唤醒线程："+thread.getState());
		Thread.sleep(1000);
		System.out.println("终止线程："+thread.getState());
	}
}
