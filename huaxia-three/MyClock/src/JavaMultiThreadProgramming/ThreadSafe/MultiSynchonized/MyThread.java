package JavaMultiThreadProgramming.ThreadSafe.MultiSynchonized;

public class MyThread extends Thread{
	@Override
	public void run(){
		servoice service = new servoice();
		service.service1();
	}
}
