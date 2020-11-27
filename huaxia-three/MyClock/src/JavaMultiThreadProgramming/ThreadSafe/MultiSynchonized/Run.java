package JavaMultiThreadProgramming.ThreadSafe.MultiSynchonized;
/**
 * 实现可重入锁机制
 * @author liuwei
 *
 */
public class Run {
	public static void main(String args[]){
		MyThread t1 = new MyThread();
		t1.start();
	}
}
