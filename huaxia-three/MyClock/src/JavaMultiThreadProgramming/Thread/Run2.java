package JavaMultiThreadProgramming.Thread;

public class Run2 {
	 public static void main(String args[]){
		 try{
			 MyThread2 thread = new MyThread2();
			 thread.start();
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}
