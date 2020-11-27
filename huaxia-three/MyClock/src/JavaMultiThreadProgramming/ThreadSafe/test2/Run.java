package JavaMultiThreadProgramming.ThreadSafe.test2;
/**
 * 两个synchonized方法的话，一个执行，其他方法等待。
 * @author liuwei
 *
 */
public class Run {
	public static void main(String[] args){
		MyObject myobjectd = new MyObject();
		ThreadA athread = new ThreadA(myobjectd);
		athread.setName("A");
		athread.start();
		ThreadB bthread = new ThreadB(myobjectd);
		bthread.setName("B");
		bthread.start();
	}
}
