package JavaMultiThreadProgramming.ReentrantLock.RentrantLock2;

public class ThreadB extends Thread {
	private MyService service;
	public ThreadB(MyService service){
		suspend();
		this.service = service;
	}
	@Override
	public void run(){
		service.methodB();
	}
}
