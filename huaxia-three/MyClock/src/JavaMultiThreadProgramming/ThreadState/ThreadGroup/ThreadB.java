package JavaMultiThreadProgramming.ThreadState.ThreadGroup;

public class ThreadB extends Thread{
	@Override
	public void run(){
		try{
			while( !Thread.currentThread().isInterrupted() ){
				System.out.println("ThreadName="+Thread.currentThread().getName());
				Thread.sleep(3000);
			}
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
