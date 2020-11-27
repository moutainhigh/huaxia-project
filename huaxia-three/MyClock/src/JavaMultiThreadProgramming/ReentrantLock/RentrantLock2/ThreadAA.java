package JavaMultiThreadProgramming.ReentrantLock.RentrantLock2;

public class ThreadAA extends Thread {
	private MyService service;
	public ThreadAA(MyService service){
		suspend();
		this.service = service;
	}
	@Override
	public void run(){
		service.methodA();
	}
}
