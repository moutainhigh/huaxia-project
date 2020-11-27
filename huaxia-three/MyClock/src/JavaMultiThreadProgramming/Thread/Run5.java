package JavaMultiThreadProgramming.Thread;

public class Run5 {
	 public static void main(String args[]){
		 try{
			 MyThread5 thread = new MyThread5();
			 thread.start();
			 Thread.sleep(5000);
			//A段
			 thread.suspend();
			 System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
			 Thread.sleep(5000);
			 System.out.println("A="+System.currentTimeMillis()+" i="+thread.getI());
			 //B段
			 thread.resume();
			 Thread.sleep(5000);
		 	//C段
			 thread.suspend();
			 System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());
			 Thread.sleep(5000);
			 System.out.println("B="+System.currentTimeMillis()+" i="+thread.getI());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}
   