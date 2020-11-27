
package JavaMultiThreadProgramming.ThreadSafe.synchron;

public class MyObject {
	public void methodA(){
		try{
			System.out.println("begin methodA threadNmae ="+Thread.currentThread().getName());
			Thread.sleep(5000);
			System.out.println("end");
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
