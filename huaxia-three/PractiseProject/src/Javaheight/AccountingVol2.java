/**
 * Title: AccountingVol.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月13日上午11:19:26
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

/**
 * @class_name:AccountingVol2020年10月13日
 * @comments:synchronized 作用的方法和变量上
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月13日上午11:19:26
 */
public class AccountingVol2 implements Runnable {
	public static AccountingVol2 instance = new AccountingVol2();
	static volatile int i= 0;
	/**
	 * @Title: instance
	 *@Description: TODO
	 *@author: LiuWei
	 *@Date: 2020年10月13日上午11:20:35
	 */
	public synchronized static void increase(){
		i++;
	}
	/**
	 * Constructor
	 */
	public AccountingVol2() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
			for(int j =0;j<1000000;j++){
				increase();
			}
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月13日上午11:19:26
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 =new Thread(instance);
		Thread t2 = new Thread(instance);
		t1.start();
		t2.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(i);
	}
}
