package JavaMultiThreadProgramming.ReentrantLock.RentrantLock2;

public class ThreadA extends Thread {
	private MyService service;
	public ThreadA(MyService service){
		suspend();
		this.service = service;
	}
	@Override
	public void run(){
		service.methodA();
	}
}
