package JavaMultiThreadProgramming.synPackage.test2;

public class Run {
	public static void main(String args[]){
		Task task = new Task();
		MyThread1 a = new MyThread1(task);
		a.setName("a");
		a.start();
		MyThread2 b = new MyThread2(task);
		b.setName("b");
		b.start();
	}
}
