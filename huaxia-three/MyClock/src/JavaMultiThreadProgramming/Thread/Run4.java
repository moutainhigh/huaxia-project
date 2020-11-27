package JavaMultiThreadProgramming.Thread;

public class Run4 {
	 public static void main(String args[]){
		 try{
			 MyThread4 thread = new MyThread4();
			 thread.start();
			 Thread.sleep(2000);
			 thread.interrupt();
			}catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}
   