/**
 * Title: App11_1.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��8��21������11:10:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

/**
 * @class_name:App11_12020��8��21��
 * @comments:�̵߳�����
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������11:10:44
 */
public class App11_1 {
	
	/**
	 * Constructor
	 */
	public App11_1() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020��8��21������11:10:45
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread you = new MyThread("��");
		MyThread she = new MyThread("��");
		you.start();
		she.start();
		System.out.println("������main()���н��� "	);
	}

}
/**
 * 
 * @class_name:MyThread2020��8��21��
 * @comments:
 * @param:
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��8��21������11:13:24
 */
class MyThread extends Thread{
	private String who;
	public MyThread(String str){
		who = str;
	}
	public void run(){
		for(int i=0;i<5;i++)
		{
			try {
				Thread.sleep((int)Math.random()*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(who+"�������� ");
		}
	}
}
