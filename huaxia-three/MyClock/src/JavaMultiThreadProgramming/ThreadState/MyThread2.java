package JavaMultiThreadProgramming.ThreadState;

public class MyThread2 extends Thread {
	@Override
	public void run(){
		try{
			System.out.println("begin sleep");
			Thread.sleep(10000);
			System.out.println("end sleep");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
