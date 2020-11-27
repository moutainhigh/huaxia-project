package JavaMultiThreadProgramming.innerClass.atomicNoSafe;

public class MyThread extends Thread {
	private MyService myService;
	public MyThread(MyService myService){
		super();
		this.myService = myService;
	}
	@Override
	public void run(){
		myService.addNum();
	}
}
