package JavaMultiThreadProgramming.ThreadSafe.test2;

public class ThreadB extends Thread{	
	private MyObject object;
	public ThreadB(MyObject object){
		super();
		this.object = object;
	}
	@Override
	public void run(){
		super.run();
		object.methodB();
	}
}
