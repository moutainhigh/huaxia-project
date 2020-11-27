package JavaMultiThreadProgramming.synPackage.JVM;

public class ThreadB2 extends Thread{
	private Service2 service;
	public ThreadB2(Service2 service){
		super();
		this.service = service;
	}
	@Override
	public void run(){
		service.print(new Object());
	}
}
