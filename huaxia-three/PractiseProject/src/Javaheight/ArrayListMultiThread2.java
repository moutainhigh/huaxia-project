/**
 * Title: ArrayListMultiThread.java
 * Description:
 * @autor:��ΰ/liuwei
 * @date 2020��10��13������2:05:42
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @class_name:ArrayListMultiThread2020��10��13��
 * @comments:ʹ��synchonized�������̰߳�ȫ
 * @param:���������չ
 * @return:
 * @author ��ΰ/liuwei
 * @createtime:2020��10��13������2:05:42
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
	 * @class_name:AddThread2020��10��13��
	 * @comments:
	 * @param:
	 * @return:
	 * @author ��ΰ/liuwei
	 * @createtime:2020��10��13������2:07:23
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
	 *@Date: 2020��10��13������2:05:42
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
