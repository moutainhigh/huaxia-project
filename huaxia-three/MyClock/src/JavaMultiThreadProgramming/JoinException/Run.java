package JavaMultiThreadProgramming.JoinException;

public class Run {
	public static void main(String args[]){
		try{
			ThreadB b = new ThreadB();
			b.start();
			Thread.sleep(5);
			ThreadC c = new ThreadC(b);
			c.start();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
