/**
 * Title: App11_1.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年8月21日上午11:10:44
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package PractisePackage.javaCore;

/**
 * @class_name:App11_12020年8月21日
 * @comments:线程的运行 线程的 两个实现 方式，继承Thread类，实现Rannable接口
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日上午11:10:44
 */
public class App11_2 {
	
	/**
	 * Constructor
	 */
	public App11_2() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年8月21日上午11:10:45
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyThread2 you = new MyThread2("你");
		MyThread2 she = new MyThread2("她");
		Thread t1 =new Thread(you);
		Thread t2 = new Thread(she);
		t1.start();
		t2.start();
		System.out.println("主方法main()运行结束 "	);
	}

}
/**
 * 
 * @class_name:MyThread2020年8月21日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年8月21日上午11:13:24
 */
class MyThread2 implements Runnable{
	private String who;
	public MyThread2(String str){
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
			System.out.println(who+"正在运行 ");
		}
	}
}
