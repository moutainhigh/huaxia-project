/**
 * Title: ArrayListMultiThread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月13日下午2:05:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

import java.util.ArrayList;

/**
 * @class_name:ArrayListMultiThread2020年10月13日
 * @comments:ArrayList是线程不安全的。
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月13日下午2:05:42
 */
public class ArrayListMultiThread {

	/**
	 * Constructor
	 */
	public ArrayListMultiThread() {
		// TODO Auto-generated constructor stub
	}
	static ArrayList<Integer> al = new ArrayList<Integer>(10);
	/**
	 * 
	 * @class_name:AddThread2020年10月13日
	 * @comments:
	 * @param:
	 * @return:
	 * @author 刘伟/liuwei
	 * @createtime:2020年10月13日下午2:07:23
	 */
	public static class AddThread implements Runnable{
		public void run(){
			for(int i=0;i< 1000000;i++){
				al.add(i);
			}
		}
	}
	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月13日下午2:05:42
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new Thread(new AddThread())	;
		Thread t2 = new Thread(new AddThread());
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
		System.out.println(al.size());
	}

}
