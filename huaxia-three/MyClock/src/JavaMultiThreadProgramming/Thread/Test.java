package JavaMultiThreadProgramming.Thread;

public class Test {
	 public static void main(String args[]){
		 final MyObject myobject = new MyObject();
		 Thread thread1 = new Thread(){
			 public void run(){
				 myobject.setValue("a", "aa");
			 }
		 };
		 thread1.setName("a");;
		 thread1.start();
		 try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Thread thread2 = new Thread(){
			 public void run(){
				 myobject.printUsernamePassword();
			 }
		 };
		 thread2.start();
	 }
}
   