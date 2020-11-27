package JavaMultiThreadProgramming.ThreadState;

public class MyThread4 extends Thread {
	@Override
	public void run(){
		MyService.serviceMethod();
	}
}
