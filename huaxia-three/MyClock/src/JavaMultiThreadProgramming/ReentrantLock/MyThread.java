package JavaMultiThreadProgramming.ReentrantLock;

public class MyThread extends Thread{
	private MyService service;
	public MyThread(MyService myservice){
		super();
		this.service = myservice;
	}
	@Override
	public void run(){
		service.testMethod();
	}
}
