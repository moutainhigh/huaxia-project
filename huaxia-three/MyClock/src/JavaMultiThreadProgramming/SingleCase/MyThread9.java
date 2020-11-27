package JavaMultiThreadProgramming.SingleCase;

public class MyThread9 extends Thread{
	@Override
	public void run(){
		for(int i=0;i<5 ;i++){
			System.out.println(MyObject9.getInstance().hashCode());
		}
	}
}