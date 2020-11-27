package JavaGaoFa;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 线程安全的自动增长的数组
 * 如果不是线程安全的，就会造成混乱，每个数组就不是10000；
[10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000, 10000]
 * @author liuwei
 *
 */
public class AtomicIntegerArrayDemo {
	static AtomicIntegerArray arr = new AtomicIntegerArray(10);
	public static class AddThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(int k=0;k<10000;k++){
				arr.getAndIncrement(k%arr.length());
			}
		}
	}
	public static void main(String args[]) throws InterruptedException{
		Thread[] ts = new Thread[10];
		for(int k=0;k<10;k++){
			ts[k] = new Thread(new AddThread());
		}
		for(int k=0;k<10;k++){
			ts[k].start();
		}
		for(int k=0;k<10;k++){
			ts[k].join();
		}
		System.out.println(arr);
	}
}
