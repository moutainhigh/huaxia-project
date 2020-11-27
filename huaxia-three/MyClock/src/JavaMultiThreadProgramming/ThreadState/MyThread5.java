package JavaMultiThreadProgramming.ThreadState;

public class MyThread5 extends Thread {
	@Override
	public void run(){
		try{
			synchronized(Lock.lock){
				Lock.lock.wait();
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
