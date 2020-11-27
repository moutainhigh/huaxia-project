package JavaMultiThreadProgramming.JoinException;

public class ThreadB extends Thread{
 @Override
 public void run(){
	 try{
		 ThreadA  a= new ThreadA();
		 a.start();
		 a.join();
		 System.out.println("线程B再run end处打印了");
	 }catch(InterruptedException e){
		 System.out.println("线程B再catch处打印了");
		 e.printStackTrace();
	 }
 }
}
