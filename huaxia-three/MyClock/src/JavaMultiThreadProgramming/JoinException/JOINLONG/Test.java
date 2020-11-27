package JavaMultiThreadProgramming.JoinException.JOINLONG;

public class Test {
	public static void main(String args[]){
		try{
			MyThread threadTest = new MyThread();
			threadTest.start();
			threadTest.join(2000);
			System.out.println(" end time = "+System.currentTimeMillis());
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}