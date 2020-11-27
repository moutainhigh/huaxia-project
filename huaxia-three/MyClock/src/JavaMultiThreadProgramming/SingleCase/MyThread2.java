package JavaMultiThreadProgramming.SingleCase;

public class MyThread2 extends Thread{
	@Override
	public void run(){
		System.out.println(MyObject2.getInstance().hashCode());
	}
}
