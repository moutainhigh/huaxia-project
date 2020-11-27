package JavaMultiThreadProgramming.ReentrantLock;

public class MyThread2 extends Thread{
	private MyService2 service;
	public MyThread2(MyService2 myservice){
		super();
		this.service = myservice;
	}
	@Override
	public void run(){
		service.testMethod();
	}
}
