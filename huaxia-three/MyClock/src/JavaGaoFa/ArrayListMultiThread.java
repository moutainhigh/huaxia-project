package JavaGaoFa;

import java.util.ArrayList;

/**
 * 多线程下的ArrayLIst
 * 出现问题
 * Exception in thread "Thread-1" java.lang.ArrayIndexOutOfBoundsException: 109
	at java.util.ArrayList.add(Unknown Source)
	at JavaGaoFa.ArrayListMultiThread$AddThread.run(ArrayListMultiThread.java:17)
	at java.lang.Thread.run(Unknown Source)
100003

 * @author liuwei
 *
 */
public class ArrayListMultiThread {
	static ArrayList<Integer> al = new ArrayList<Integer>(10);
	public static class AddThread implements Runnable{
		@Override
		public void run() {
			// TODO Auto-generated method stub
		for(int i=0;i<100000;i++){
			al.add(i);
		}
		}
	}
	public static void main(String args[]){
		Thread t1= new Thread(new AddThread());
		Thread t2 = new Thread(new AddThread());
		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(al.size());
	}
}
