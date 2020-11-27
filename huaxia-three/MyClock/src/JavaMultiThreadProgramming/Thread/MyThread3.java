package JavaMultiThreadProgramming.Thread;

public class MyThread3 extends Thread{
	private SynchronizedObject object;
	public MyThread3(SynchronizedObject object){
		this.object = object;
	}
	@Override
	public void run(){
		object.printString("b", "bb");
	}
}
