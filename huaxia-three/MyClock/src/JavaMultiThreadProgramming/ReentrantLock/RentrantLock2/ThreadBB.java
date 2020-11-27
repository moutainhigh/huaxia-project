package JavaMultiThreadProgramming.ReentrantLock.RentrantLock2;

public class ThreadBB extends Thread {
	private MyService service;
	public ThreadBB(MyService service){
		suspend();
		this.service = service;
	}
	@Override
	public void run(){
		service.methodB();
	}
}
