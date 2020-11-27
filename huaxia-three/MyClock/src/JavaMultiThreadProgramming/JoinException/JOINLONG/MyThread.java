package JavaMultiThreadProgramming.JoinException.JOINLONG;

public class MyThread extends Thread {
	@Override
	public void run(){
		try{
			System.out.println("Begin Timer = "+System.currentTimeMillis());
			Thread.sleep(5000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
