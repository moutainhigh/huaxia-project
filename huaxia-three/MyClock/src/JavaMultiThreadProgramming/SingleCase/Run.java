package JavaMultiThreadProgramming.SingleCase;
/**
 * hashCode是同一个值，说明对象是同一个值
 * @author liuwei
 *
 */
public class Run {
	public static void main(String args[]){
		MyThread t1 = new MyThread();
		MyThread t2 = new MyThread();
		MyThread t3 = new MyThread();
		t1.start();
		t2.start();
		t3.start();
	}
}
