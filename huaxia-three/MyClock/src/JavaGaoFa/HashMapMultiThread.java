package JavaGaoFa;

import java.util.HashMap;
import java.util.Map;

/**
 * 多线程下的HashMap
 * HashMap 链表成环，无限执行
 * @author liuwei
 *
 */
public class HashMapMultiThread {
	static Map<String ,String> map =new HashMap<String ,String>();
	public static class AddThread implements Runnable{
		int start = 0;
		public AddThread(int start){
			this.start = start;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
		for(int i=start;i<100000;i+=2){
			map.put(Integer.toString(i), Integer.toBinaryString(i));
			System.out.println(Thread.currentThread().getName()+":"+Integer.toString(i)+" "+ Integer.toBinaryString(i));
		}
		}
	}
	public static void main(String args[]){
		Thread t1= new Thread(new AddThread(0));
		Thread t2 = new Thread(new AddThread(1));
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(map.size());
	}
}
