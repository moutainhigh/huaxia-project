/**
 * Title: ThreadDemo_4.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������4:03:49
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

/**
 * @class_name:ThreadDemo_42020��8��27��
 * @comments: ���̵߳�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������4:03:49
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
	 *@Date: 2020��8��27������4:03:49
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
			System.out.println(Thread.currentThread().getName()+"����Ʊ"+tickets);
			tickets -=1;
		}
	}
}