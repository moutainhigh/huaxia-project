package ThinkInJava;
/**
 * 测试一些线程同步和非同步的方法的
 * 一些数据同步的问题
 * @author liuwei
 *
 */
public class SynchronizedEvenGennerator implements Runnable{
	public SynchronizedEvenGennerator() {
		// TODO Auto-generated constructor stub
	}
	private int currentEvenValue =0;
	public synchronized int next(){
		++currentEvenValue;
		Thread.yield();
		return currentEvenValue;
	}
	public  int next2(){
		++currentEvenValue;
		Thread.yield();
		return currentEvenValue;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				Thread.sleep(10);
				System.out.println(Thread.currentThread().getName()+"："+next());
				Thread.sleep(1);
				System.err.println(Thread.currentThread().getName()+"："+next2());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void main(String args[]){
		SynchronizedEvenGennerator syn = new SynchronizedEvenGennerator();
		Thread t1 = new Thread(syn);
		t1.start();
		Thread t2 = new Thread(syn);
		t2.start();
	}
}
