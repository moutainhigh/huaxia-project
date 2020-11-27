package JavaMultiThreadProgramming.innerClass.lock;

public class Run {
	public static void main(String args[]){
		MyService service =new MyService();
		ThreadA a =new ThreadA(service);
		a.setName("A");
		ThreadB b =new ThreadB(service);
		b.setName("B");
		a.start();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b.start();
	}
}
