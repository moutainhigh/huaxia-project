package JavaMultiThreadProgramming.SingleCase;

public class MyThread extends Thread{
	@Override
	public void run(){
		System.out.println(MyObject.getInstance().hashCode());
	}
}
