/**
 * Title: ThreadDemo_4.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月27日下午4:03:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

/**
 * @class_name:ThreadDemo_42020年8月27日
 * @comments: 多线程的运行
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月27日下午4:03:49
 */
public class ThreadDemo_4 {

	/**
	 * Constructor
	 */
	public ThreadDemo_4() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月27日下午4:03:49
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestThread3().start();
		new TestThread3().start();
		new TestThread3().start();
		new TestThread3().start();
	}

}
class TestThread3 extends Thread
{
	private int tickets = 5;
	public void run(){
		while(tickets >0){
			System.out.println(Thread.currentThread().getName()+"出售票"+tickets);
			tickets -=1;
		}
	}
}