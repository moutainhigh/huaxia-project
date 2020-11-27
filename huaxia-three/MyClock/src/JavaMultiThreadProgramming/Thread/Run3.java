package JavaMultiThreadProgramming.Thread;

public class Run3 {
	 public static void main(String args[]){
		 try{
			 SynchronizedObject object = new SynchronizedObject();
			 MyThread3 thread = new MyThread3(object);
			 thread.start();
			 Thread.sleep(500);
			 thread.stop();
			 System.out.println(object.getUsername()+" "+object.getPassword());
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
}
