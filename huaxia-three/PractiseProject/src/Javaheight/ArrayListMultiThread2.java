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
import java.util.Collections;
import java.util.List;

/**
 * @class_name:ArrayListMultiThread2020年10月13日
 * @comments:使用synchonized方法是线程安全
 * @param:对类进行扩展
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月13日下午2:05:42
 */
public class ArrayListMultiThread2 {

	/**
	 * Constructor
	 */
	public ArrayListMultiThread2() {
		// TODO Auto-generated constructor stub
	}
	static List<Integer> al = Collections.synchronizedList(new ArrayList<Integer>(10));
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
