package JavaMultiThreadProgramming.SingleCase;

public class MyThread5 extends Thread{
	@Override
	public void run(){
		System.out.println(MyObject5.getInstance().hashCode());
	}
}