package JavaMultiThreadProgramming.ThreadPriority;
/**
 * 线程优先级高的线程首先执行
 * @author liuwei
 *
 */
public class Run2 {
	public static void main(String args[]){
		for(int i=0;i<5;i++){
			MyThread1  thread1 = new MyThread1();
			thread1.setPriority(1);
			thread1.start();
			MyThread2 thread2 = new MyThread2();
			thread2.setPriority(10);
			thread2.start();
		}
	}
}
