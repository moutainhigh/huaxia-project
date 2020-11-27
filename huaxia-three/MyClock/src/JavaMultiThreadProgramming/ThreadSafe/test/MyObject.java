
package JavaMultiThreadProgramming.ThreadSafe.test;

public class MyObject {
	synchronized public void methodA(){
		try{
			System.out.println("begin methodA threadNmae ="+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("endA");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
  public void methodB(){
		try{
			System.out.println("begin methodB threadNmae ="+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end B");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
