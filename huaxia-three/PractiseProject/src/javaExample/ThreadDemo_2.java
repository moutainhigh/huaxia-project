/**
 * Title: ThreadDemo_2.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��27������3:52:55
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package javaExample;

/**
 * @class_name:ThreadDemo_22020��8��27��
 * @comments:���̵߳�Demo��
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������3:52:55
 */
public class ThreadDemo_2 {

	/**
	 * Constructor
	 */
	public ThreadDemo_2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��27������3:52:55
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestThread2().start();
		for(int i=0;i<5;i++)
		{
			System.out.println("Main�߳�������");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
/**
 * 
 * @class_name:TestThread22020��8��27��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��27������3:54:55
 */
class TestThread2 extends Thread
{
	public void run(){
		for(int i=0;i<5;i++){
			System.out.println("TestThread������");
			 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}