package JavaMultiThreadProgramming.SingleCase;
/**
 * hashCode是同一个值，懒汉模式多线程会可能会取出不同的对象
 * @author liuwei
 *
 */
public class Run2 {
	public static void main(String args[]){
		MyThread2 t1 = new MyThread2();
		MyThread2 t2 = new MyThread2();
		MyThread2 t3 = new MyThread2();
		t1.start();
		t2.start();
		t3.start();
	}
}
