package JavaMultiThreadProgramming.SingleCase;

public class MyThread6 extends Thread{
	@Override
	public void run(){
		System.out.println(MyObject6.getInstance().hashCode());
	}
}