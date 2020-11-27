
package JavaMultiThreadProgramming.ThreadSafe.synchroni;

public class MyObject {
	synchronized public void methodA(){
		try{
			System.out.println("begin methodA threadNmae ="+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
