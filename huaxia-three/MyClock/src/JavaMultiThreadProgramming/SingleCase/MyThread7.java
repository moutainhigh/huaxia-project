package JavaMultiThreadProgramming.SingleCase;

public class MyThread7 extends Thread{
	@Override
	public void run(){
		System.out.println(MyObject7.getInstance().hashCode());
	}
}