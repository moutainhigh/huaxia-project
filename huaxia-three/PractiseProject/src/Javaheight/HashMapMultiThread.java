/**
 * Title: HashMapMultiThread.java
 * Description:
 * @autor:刘伟/liuwei
 * @date 2020年10月13日下午2:54:22
 * @version 1.0
 * Company: huateng/huaxia bank
 */
package Javaheight;

import java.util.HashMap;
import java.util.Map;

/**
 * @class_name:HashMapMultiThread2020年10月13日
 * @comments:
 * @param:
 * @return:
 * @author 刘伟/liuwei
 * @createtime:2020年10月13日下午2:54:22
 */
public class HashMapMultiThread {
	static Map<String,String> map  = new HashMap<String,String>();
	public static class AddThread implements Runnable{
		int start  = 0;
		public AddThread(int start){
			this.start = start;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int i=start;i<10000;i+=2){
				map.put(Integer.toString(i), Integer.toBinaryString(i));
			}
		}
	}
	/**
	 * Constructor
	 */
	public HashMapMultiThread() {
		// TODO Auto-generated constructor stub
	}

	/**@Title: main
	 *@Description: TODO
	 *@param args
	 *@author: LiuWei
	 *@Date: 2020年10月13日下午2:54:22
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 =new Thread(new HashMapMultiThread.AddThread(0));
		Thread t2 =new Thread(new HashMapMultiThread.AddThread(1));
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
		System.out.println(map.size());

	}

}
