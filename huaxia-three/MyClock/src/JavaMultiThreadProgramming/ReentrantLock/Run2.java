package JavaMultiThreadProgramming.ReentrantLock;

public class Run2 {
	public static void main(String args[]){
		MyService2 service = new MyService2();
		MyThread2 a1= new MyThread2(service);
		MyThread2 a2= new MyThread2(service);
		MyThread2 a3= new MyThread2(service);
		MyThread2 a4= new MyThread2(service);
		MyThread2 a5= new MyThread2(service);
		a1.start();
		a2.start();
		a3.start();
		a4.start();
		a5.start();
	}
}
