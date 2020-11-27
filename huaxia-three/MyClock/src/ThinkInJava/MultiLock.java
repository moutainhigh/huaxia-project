package ThinkInJava;
/*
 * 测试一些线程的互斥和阻塞
 */
public class MultiLock {
	public synchronized void f1(int count){
		if(count-- >0){
			 System.out.println("f1() calling f2() with count "+count);
			 f2(count);
		}
	}
	public synchronized void f2(int count){
		if(count-- >0){
			System.out.println("f2() calling f1() with count "+count);
			f1(count);
		}
	}
	public static void main(String args[]){
		final MultiLock multilock = new MultiLock();
		new Thread(){
			public void run(){
				multilock.f1(10);
			}
		}.start();
	}
}
